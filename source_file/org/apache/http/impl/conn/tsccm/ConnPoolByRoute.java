package org.apache.http.impl.conn.tsccm;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

@Deprecated
public class ConnPoolByRoute extends AbstractConnPool
{
  protected final ConnPerRoute connPerRoute;
  private final long connTTL;
  private final TimeUnit connTTLTimeUnit;
  protected final Queue<BasicPoolEntry> freeConnections;
  protected final Set<BasicPoolEntry> leasedConnections;
  private final Log log = LogFactory.getLog(getClass());
  protected volatile int maxTotalConnections;
  protected volatile int numConnections;
  protected final ClientConnectionOperator operator;
  private final Lock poolLock;
  protected final Map<HttpRoute, RouteSpecificPool> routeToPool;
  protected volatile boolean shutdown;
  protected final Queue<WaitingThread> waitingThreads;

  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, ConnPerRoute paramConnPerRoute, int paramInt)
  {
    this(paramClientConnectionOperator, paramConnPerRoute, paramInt, -1L, TimeUnit.MILLISECONDS);
  }

  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, ConnPerRoute paramConnPerRoute, int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramClientConnectionOperator == null)
      throw new IllegalArgumentException("Connection operator may not be null");
    if (paramConnPerRoute == null)
      throw new IllegalArgumentException("Connections per route may not be null");
    this.poolLock = this.poolLock;
    this.leasedConnections = this.leasedConnections;
    this.operator = paramClientConnectionOperator;
    this.connPerRoute = paramConnPerRoute;
    this.maxTotalConnections = paramInt;
    this.freeConnections = createFreeConnQueue();
    this.waitingThreads = createWaitingThreadQueue();
    this.routeToPool = createRouteToPoolMap();
    this.connTTL = paramLong;
    this.connTTLTimeUnit = paramTimeUnit;
  }

  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, HttpParams paramHttpParams)
  {
    this(paramClientConnectionOperator, ConnManagerParams.getMaxConnectionsPerRoute(paramHttpParams), ConnManagerParams.getMaxTotalConnections(paramHttpParams));
  }

  private void closeConnection(BasicPoolEntry paramBasicPoolEntry)
  {
    OperatedClientConnection localOperatedClientConnection = paramBasicPoolEntry.getConnection();
    if (localOperatedClientConnection != null);
    try
    {
      localOperatedClientConnection.close();
      return;
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error closing connection", localIOException);
    }
  }

  public void closeExpiredConnections()
  {
    this.log.debug("Closing expired connections");
    long l = System.currentTimeMillis();
    this.poolLock.lock();
    try
    {
      Iterator localIterator = this.freeConnections.iterator();
      while (localIterator.hasNext())
      {
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)localIterator.next();
        if (!localBasicPoolEntry.isExpired(l))
          continue;
        if (this.log.isDebugEnabled())
          this.log.debug("Closing connection expired @ " + new Date(localBasicPoolEntry.getExpiry()));
        localIterator.remove();
        deleteEntry(localBasicPoolEntry);
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
    this.poolLock.unlock();
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit must not be null.");
    if (paramLong < 0L)
      paramLong = 0L;
    if (this.log.isDebugEnabled())
      this.log.debug("Closing connections idle longer than " + paramLong + " " + paramTimeUnit);
    long l = System.currentTimeMillis() - paramTimeUnit.toMillis(paramLong);
    this.poolLock.lock();
    try
    {
      Iterator localIterator = this.freeConnections.iterator();
      while (localIterator.hasNext())
      {
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)localIterator.next();
        if (localBasicPoolEntry.getUpdated() > l)
          continue;
        if (this.log.isDebugEnabled())
          this.log.debug("Closing connection last used @ " + new Date(localBasicPoolEntry.getUpdated()));
        localIterator.remove();
        deleteEntry(localBasicPoolEntry);
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
    this.poolLock.unlock();
  }

  protected BasicPoolEntry createEntry(RouteSpecificPool paramRouteSpecificPool, ClientConnectionOperator paramClientConnectionOperator)
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Creating new connection [" + paramRouteSpecificPool.getRoute() + "]");
    BasicPoolEntry localBasicPoolEntry = new BasicPoolEntry(paramClientConnectionOperator, paramRouteSpecificPool.getRoute(), this.connTTL, this.connTTLTimeUnit);
    this.poolLock.lock();
    try
    {
      paramRouteSpecificPool.createdEntry(localBasicPoolEntry);
      this.numConnections = (1 + this.numConnections);
      this.leasedConnections.add(localBasicPoolEntry);
      return localBasicPoolEntry;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  protected Queue<BasicPoolEntry> createFreeConnQueue()
  {
    return new LinkedList();
  }

  protected Map<HttpRoute, RouteSpecificPool> createRouteToPoolMap()
  {
    return new HashMap();
  }

  protected Queue<WaitingThread> createWaitingThreadQueue()
  {
    return new LinkedList();
  }

  public void deleteClosedConnections()
  {
    this.poolLock.lock();
    try
    {
      Iterator localIterator = this.freeConnections.iterator();
      while (localIterator.hasNext())
      {
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)localIterator.next();
        if (localBasicPoolEntry.getConnection().isOpen())
          continue;
        localIterator.remove();
        deleteEntry(localBasicPoolEntry);
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
    this.poolLock.unlock();
  }

  protected void deleteEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    HttpRoute localHttpRoute = paramBasicPoolEntry.getPlannedRoute();
    if (this.log.isDebugEnabled())
      this.log.debug("Deleting connection [" + localHttpRoute + "][" + paramBasicPoolEntry.getState() + "]");
    this.poolLock.lock();
    try
    {
      closeConnection(paramBasicPoolEntry);
      RouteSpecificPool localRouteSpecificPool = getRoutePool(localHttpRoute, true);
      localRouteSpecificPool.deleteEntry(paramBasicPoolEntry);
      this.numConnections = (-1 + this.numConnections);
      if (localRouteSpecificPool.isUnused())
        this.routeToPool.remove(localHttpRoute);
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  protected void deleteLeastUsedEntry()
  {
    this.poolLock.lock();
    try
    {
      BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)this.freeConnections.remove();
      if (localBasicPoolEntry != null)
        deleteEntry(localBasicPoolEntry);
      while (true)
      {
        return;
        if (!this.log.isDebugEnabled())
          continue;
        this.log.debug("No free connection to delete");
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  public void freeEntry(BasicPoolEntry paramBasicPoolEntry, boolean paramBoolean, long paramLong, TimeUnit paramTimeUnit)
  {
    HttpRoute localHttpRoute = paramBasicPoolEntry.getPlannedRoute();
    if (this.log.isDebugEnabled())
      this.log.debug("Releasing connection [" + localHttpRoute + "][" + paramBasicPoolEntry.getState() + "]");
    this.poolLock.lock();
    try
    {
      if (this.shutdown)
      {
        closeConnection(paramBasicPoolEntry);
        return;
      }
      this.leasedConnections.remove(paramBasicPoolEntry);
      RouteSpecificPool localRouteSpecificPool = getRoutePool(localHttpRoute, true);
      String str;
      if ((paramBoolean) && (localRouteSpecificPool.getCapacity() >= 0))
        if (this.log.isDebugEnabled())
        {
          if (paramLong > 0L)
          {
            str = "for " + paramLong + " " + paramTimeUnit;
            this.log.debug("Pooling connection [" + localHttpRoute + "][" + paramBasicPoolEntry.getState() + "]; keep alive " + str);
          }
        }
        else
        {
          localRouteSpecificPool.freeEntry(paramBasicPoolEntry);
          paramBasicPoolEntry.updateExpiry(paramLong, paramTimeUnit);
          this.freeConnections.add(paramBasicPoolEntry);
        }
      while (true)
      {
        notifyWaitingThread(localRouteSpecificPool);
        return;
        str = "indefinitely";
        break;
        closeConnection(paramBasicPoolEntry);
        localRouteSpecificPool.dropEntry();
        this.numConnections = (-1 + this.numConnections);
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  public int getConnectionsInPool()
  {
    this.poolLock.lock();
    try
    {
      int i = this.numConnections;
      return i;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  public int getConnectionsInPool(HttpRoute paramHttpRoute)
  {
    this.poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool = getRoutePool(paramHttpRoute, false);
      int i = 0;
      if (localRouteSpecificPool != null)
      {
        int j = localRouteSpecificPool.getEntryCount();
        i = j;
      }
      return i;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  protected BasicPoolEntry getEntryBlocking(HttpRoute paramHttpRoute, Object paramObject, long paramLong, TimeUnit paramTimeUnit, WaitingThreadAborter paramWaitingThreadAborter)
    throws ConnectionPoolTimeoutException, InterruptedException
  {
    boolean bool1 = paramLong < 0L;
    Date localDate = null;
    if (bool1)
      localDate = new Date(System.currentTimeMillis() + paramTimeUnit.toMillis(paramLong));
    Object localObject1 = null;
    this.poolLock.lock();
    RouteSpecificPool localRouteSpecificPool;
    WaitingThread localWaitingThread;
    try
    {
      localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
      localWaitingThread = null;
      if (localObject1 != null)
        break label213;
      if (this.shutdown)
        throw new IllegalStateException("Connection pool shut down");
    }
    finally
    {
      this.poolLock.unlock();
    }
    if (this.log.isDebugEnabled())
      this.log.debug("[" + paramHttpRoute + "] total kept alive: " + this.freeConnections.size() + ", total issued: " + this.leasedConnections.size() + ", total allocated: " + this.numConnections + " out of " + this.maxTotalConnections);
    BasicPoolEntry localBasicPoolEntry = getFreeEntry(localRouteSpecificPool, paramObject);
    localObject1 = localBasicPoolEntry;
    if (localObject1 != null)
    {
      label213: this.poolLock.unlock();
      return localObject1;
    }
    if (localRouteSpecificPool.getCapacity() > 0);
    for (int i = 1; ; i = 0)
      while (true)
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Available capacity: " + localRouteSpecificPool.getCapacity() + " out of " + localRouteSpecificPool.getMaxEntries() + " [" + paramHttpRoute + "][" + paramObject + "]");
        if ((i != 0) && (this.numConnections < this.maxTotalConnections))
        {
          localObject1 = createEntry(localRouteSpecificPool, this.operator);
          break;
        }
        if ((i != 0) && (!this.freeConnections.isEmpty()))
        {
          deleteLeastUsedEntry();
          localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
          localObject1 = createEntry(localRouteSpecificPool, this.operator);
          break;
        }
        if (this.log.isDebugEnabled())
          this.log.debug("Need to wait for connection [" + paramHttpRoute + "][" + paramObject + "]");
        if (localWaitingThread == null)
        {
          localWaitingThread = newWaitingThread(this.poolLock.newCondition(), localRouteSpecificPool);
          paramWaitingThreadAborter.setWaitingThread(localWaitingThread);
        }
        try
        {
          localRouteSpecificPool.queueThread(localWaitingThread);
          this.waitingThreads.add(localWaitingThread);
          boolean bool2 = localWaitingThread.await(localDate);
          localRouteSpecificPool.removeThread(localWaitingThread);
          this.waitingThreads.remove(localWaitingThread);
          if ((bool2) || (localDate == null) || (localDate.getTime() > System.currentTimeMillis()))
            break;
          throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
        finally
        {
          localRouteSpecificPool.removeThread(localWaitingThread);
          this.waitingThreads.remove(localWaitingThread);
        }
      }
  }

  protected BasicPoolEntry getFreeEntry(RouteSpecificPool paramRouteSpecificPool, Object paramObject)
  {
    BasicPoolEntry localBasicPoolEntry = null;
    this.poolLock.lock();
    int i = 0;
    while (i == 0)
    {
      try
      {
        localBasicPoolEntry = paramRouteSpecificPool.allocEntry(paramObject);
        if (localBasicPoolEntry == null)
          break label221;
        if (this.log.isDebugEnabled())
          this.log.debug("Getting free connection [" + paramRouteSpecificPool.getRoute() + "][" + paramObject + "]");
        this.freeConnections.remove(localBasicPoolEntry);
        if (localBasicPoolEntry.isExpired(System.currentTimeMillis()))
        {
          if (this.log.isDebugEnabled())
            this.log.debug("Closing expired free connection [" + paramRouteSpecificPool.getRoute() + "][" + paramObject + "]");
          closeConnection(localBasicPoolEntry);
          paramRouteSpecificPool.dropEntry();
          this.numConnections = (-1 + this.numConnections);
          continue;
        }
      }
      finally
      {
        this.poolLock.unlock();
      }
      this.leasedConnections.add(localBasicPoolEntry);
      i = 1;
      continue;
      label221: i = 1;
      if (!this.log.isDebugEnabled())
        continue;
      this.log.debug("No free connections [" + paramRouteSpecificPool.getRoute() + "][" + paramObject + "]");
    }
    this.poolLock.unlock();
    return localBasicPoolEntry;
  }

  protected Lock getLock()
  {
    return this.poolLock;
  }

  public int getMaxTotalConnections()
  {
    return this.maxTotalConnections;
  }

  protected RouteSpecificPool getRoutePool(HttpRoute paramHttpRoute, boolean paramBoolean)
  {
    this.poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool = (RouteSpecificPool)this.routeToPool.get(paramHttpRoute);
      if ((localRouteSpecificPool == null) && (paramBoolean))
      {
        localRouteSpecificPool = newRouteSpecificPool(paramHttpRoute);
        this.routeToPool.put(paramHttpRoute, localRouteSpecificPool);
      }
      return localRouteSpecificPool;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  protected void handleLostEntry(HttpRoute paramHttpRoute)
  {
    this.poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
      localRouteSpecificPool.dropEntry();
      if (localRouteSpecificPool.isUnused())
        this.routeToPool.remove(paramHttpRoute);
      this.numConnections = (-1 + this.numConnections);
      notifyWaitingThread(localRouteSpecificPool);
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  protected RouteSpecificPool newRouteSpecificPool(HttpRoute paramHttpRoute)
  {
    return new RouteSpecificPool(paramHttpRoute, this.connPerRoute);
  }

  protected WaitingThread newWaitingThread(Condition paramCondition, RouteSpecificPool paramRouteSpecificPool)
  {
    return new WaitingThread(paramCondition, paramRouteSpecificPool);
  }

  protected void notifyWaitingThread(RouteSpecificPool paramRouteSpecificPool)
  {
    this.poolLock.lock();
    if (paramRouteSpecificPool != null);
    try
    {
      WaitingThread localWaitingThread;
      if (paramRouteSpecificPool.hasThread())
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Notifying thread waiting on pool [" + paramRouteSpecificPool.getRoute() + "]");
        localWaitingThread = paramRouteSpecificPool.nextThread();
      }
      while (true)
      {
        if (localWaitingThread != null)
          localWaitingThread.wakeup();
        return;
        if (!this.waitingThreads.isEmpty())
        {
          if (this.log.isDebugEnabled())
            this.log.debug("Notifying thread waiting on any pool");
          localWaitingThread = (WaitingThread)this.waitingThreads.remove();
          continue;
        }
        boolean bool = this.log.isDebugEnabled();
        localWaitingThread = null;
        if (!bool)
          continue;
        this.log.debug("Notifying no-one, there are no waiting threads");
        localWaitingThread = null;
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  public PoolEntryRequest requestPoolEntry(HttpRoute paramHttpRoute, Object paramObject)
  {
    return new PoolEntryRequest(new WaitingThreadAborter(), paramHttpRoute, paramObject)
    {
      public void abortRequest()
      {
        ConnPoolByRoute.this.poolLock.lock();
        try
        {
          this.val$aborter.abort();
          return;
        }
        finally
        {
          ConnPoolByRoute.this.poolLock.unlock();
        }
        throw localObject;
      }

      public BasicPoolEntry getPoolEntry(long paramLong, TimeUnit paramTimeUnit)
        throws InterruptedException, ConnectionPoolTimeoutException
      {
        return ConnPoolByRoute.this.getEntryBlocking(this.val$route, this.val$state, paramLong, paramTimeUnit, this.val$aborter);
      }
    };
  }

  public void setMaxTotalConnections(int paramInt)
  {
    this.poolLock.lock();
    try
    {
      this.maxTotalConnections = paramInt;
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw localObject;
  }

  public void shutdown()
  {
    this.poolLock.lock();
    try
    {
      boolean bool = this.shutdown;
      if (bool)
        return;
      this.shutdown = true;
      Iterator localIterator1 = this.leasedConnections.iterator();
      while (localIterator1.hasNext())
      {
        BasicPoolEntry localBasicPoolEntry2 = (BasicPoolEntry)localIterator1.next();
        localIterator1.remove();
        closeConnection(localBasicPoolEntry2);
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
    Iterator localIterator2 = this.freeConnections.iterator();
    while (localIterator2.hasNext())
    {
      BasicPoolEntry localBasicPoolEntry1 = (BasicPoolEntry)localIterator2.next();
      localIterator2.remove();
      if (this.log.isDebugEnabled())
        this.log.debug("Closing connection [" + localBasicPoolEntry1.getPlannedRoute() + "][" + localBasicPoolEntry1.getState() + "]");
      closeConnection(localBasicPoolEntry1);
    }
    Iterator localIterator3 = this.waitingThreads.iterator();
    while (localIterator3.hasNext())
    {
      WaitingThread localWaitingThread = (WaitingThread)localIterator3.next();
      localIterator3.remove();
      localWaitingThread.wakeup();
    }
    this.routeToPool.clear();
    this.poolLock.unlock();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.tsccm.ConnPoolByRoute
 * JD-Core Version:    0.6.0
 */