package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpClientConnection;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.conn.scheme.SchemeRegistry;

@ThreadSafe
public class BasicClientConnectionManager
  implements ClientConnectionManager
{
  private static final AtomicLong COUNTER = new AtomicLong();
  public static final String MISUSE_MESSAGE = "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";

  @GuardedBy("this")
  private ManagedClientConnectionImpl conn;
  private final ClientConnectionOperator connOperator;
  private final Log log = LogFactory.getLog(getClass());

  @GuardedBy("this")
  private HttpPoolEntry poolEntry;
  private final SchemeRegistry schemeRegistry;

  @GuardedBy("this")
  private volatile boolean shutdown;

  public BasicClientConnectionManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public BasicClientConnectionManager(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    this.schemeRegistry = paramSchemeRegistry;
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
  }

  private void assertNotShutdown()
  {
    if (this.shutdown)
      throw new IllegalStateException("Connection manager has been shut down");
  }

  private void shutdownConnection(HttpClientConnection paramHttpClientConnection)
  {
    try
    {
      paramHttpClientConnection.shutdown();
      return;
    }
    catch (IOException localIOException)
    {
      while (!this.log.isDebugEnabled());
      this.log.debug("I/O exception shutting down connection", localIOException);
    }
  }

  public void closeExpiredConnections()
  {
    monitorenter;
    try
    {
      assertNotShutdown();
      long l = System.currentTimeMillis();
      if ((this.poolEntry != null) && (this.poolEntry.isExpired(l)))
      {
        this.poolEntry.close();
        this.poolEntry.getTracker().reset();
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit must not be null.");
    monitorenter;
    try
    {
      assertNotShutdown();
      long l1 = paramTimeUnit.toMillis(paramLong);
      if (l1 < 0L)
        l1 = 0L;
      long l2 = System.currentTimeMillis() - l1;
      if ((this.poolEntry != null) && (this.poolEntry.getUpdated() <= l2))
      {
        this.poolEntry.close();
        this.poolEntry.getTracker().reset();
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry);
  }

  protected void finalize()
    throws Throwable
  {
    try
    {
      shutdown();
      return;
    }
    finally
    {
      super.finalize();
    }
    throw localObject;
  }

  ManagedClientConnection getConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("Route may not be null.");
    monitorenter;
    try
    {
      assertNotShutdown();
      if (this.log.isDebugEnabled())
        this.log.debug("Get connection for route " + paramHttpRoute);
      if (this.conn != null)
        throw new IllegalStateException("Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
    }
    finally
    {
      monitorexit;
    }
    if ((this.poolEntry != null) && (!this.poolEntry.getPlannedRoute().equals(paramHttpRoute)))
    {
      this.poolEntry.close();
      this.poolEntry = null;
    }
    if (this.poolEntry == null)
    {
      String str = Long.toString(COUNTER.getAndIncrement());
      OperatedClientConnection localOperatedClientConnection = this.connOperator.createConnection();
      this.poolEntry = new HttpPoolEntry(this.log, str, paramHttpRoute, localOperatedClientConnection, 0L, TimeUnit.MILLISECONDS);
    }
    long l = System.currentTimeMillis();
    if (this.poolEntry.isExpired(l))
    {
      this.poolEntry.close();
      this.poolEntry.getTracker().reset();
    }
    this.conn = new ManagedClientConnectionImpl(this, this.connOperator, this.poolEntry);
    ManagedClientConnectionImpl localManagedClientConnectionImpl = this.conn;
    monitorexit;
    return localManagedClientConnectionImpl;
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    if (!(paramManagedClientConnection instanceof ManagedClientConnectionImpl))
      throw new IllegalArgumentException("Connection class mismatch, connection not obtained from this manager");
    synchronized ((ManagedClientConnectionImpl)paramManagedClientConnection)
    {
      if (this.log.isDebugEnabled())
        this.log.debug("Releasing connection " + paramManagedClientConnection);
      if (???.getPoolEntry() == null)
        return;
      ClientConnectionManager localClientConnectionManager = ???.getManager();
      if ((localClientConnectionManager != null) && (localClientConnectionManager != this))
        throw new IllegalStateException("Connection not obtained from this manager");
    }
    monitorenter;
    try
    {
      if (this.shutdown)
      {
        shutdownConnection(???);
        monitorexit;
        return;
      }
      try
      {
        if ((???.isOpen()) && (!???.isMarkedReusable()))
          shutdownConnection(???);
        HttpPoolEntry localHttpPoolEntry = this.poolEntry;
        TimeUnit localTimeUnit;
        if (paramTimeUnit != null)
        {
          localTimeUnit = paramTimeUnit;
          localHttpPoolEntry.updateExpiry(paramLong, localTimeUnit);
          if (this.log.isDebugEnabled())
            if (paramLong <= 0L)
              break label298;
        }
        label298: for (String str = "for " + paramLong + " " + paramTimeUnit; ; str = "indefinitely")
        {
          this.log.debug("Connection can be kept alive " + str);
          ???.detach();
          this.conn = null;
          if (this.poolEntry.isClosed())
            this.poolEntry = null;
          monitorexit;
          monitorexit;
          return;
          localTimeUnit = TimeUnit.MILLISECONDS;
          break;
        }
      }
      finally
      {
        ???.detach();
        this.conn = null;
        if (this.poolEntry.isClosed())
          this.poolEntry = null;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject2;
  }

  public final ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    return new ClientConnectionRequest(paramHttpRoute, paramObject)
    {
      public void abortRequest()
      {
      }

      public ManagedClientConnection getConnection(long paramLong, TimeUnit paramTimeUnit)
      {
        return BasicClientConnectionManager.this.getConnection(this.val$route, this.val$state);
      }
    };
  }

  public void shutdown()
  {
    monitorenter;
    try
    {
      this.shutdown = true;
      try
      {
        if (this.poolEntry != null)
          this.poolEntry.close();
        this.poolEntry = null;
        this.conn = null;
        monitorexit;
        return;
      }
      finally
      {
        localObject2 = finally;
        this.poolEntry = null;
        this.conn = null;
        throw localObject2;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.BasicClientConnectionManager
 * JD-Core Version:    0.6.0
 */