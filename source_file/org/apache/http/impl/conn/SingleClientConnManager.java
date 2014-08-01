package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.apache.http.params.HttpParams;

@Deprecated
@ThreadSafe
public class SingleClientConnManager
  implements ClientConnectionManager
{
  public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
  protected final boolean alwaysShutDown;
  protected final ClientConnectionOperator connOperator;

  @GuardedBy("this")
  protected volatile long connectionExpiresTime;
  protected volatile boolean isShutDown;

  @GuardedBy("this")
  protected volatile long lastReleaseTime;
  private final Log log = LogFactory.getLog(getClass());

  @GuardedBy("this")
  protected volatile ConnAdapter managedConn;
  protected final SchemeRegistry schemeRegistry;

  @GuardedBy("this")
  protected volatile PoolEntry uniquePoolEntry;

  public SingleClientConnManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public SingleClientConnManager(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry must not be null.");
    this.schemeRegistry = paramSchemeRegistry;
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
    this.uniquePoolEntry = new PoolEntry();
    this.managedConn = null;
    this.lastReleaseTime = -1L;
    this.alwaysShutDown = false;
    this.isShutDown = false;
  }

  public SingleClientConnManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    this(paramSchemeRegistry);
  }

  protected final void assertStillUp()
    throws IllegalStateException
  {
    if (this.isShutDown)
      throw new IllegalStateException("Manager is shut down.");
  }

  public void closeExpiredConnections()
  {
    long l = this.connectionExpiresTime;
    if (System.currentTimeMillis() >= l)
      closeIdleConnections(0L, TimeUnit.MILLISECONDS);
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    assertStillUp();
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit must not be null.");
    monitorenter;
    try
    {
      if ((this.managedConn == null) && (this.uniquePoolEntry.connection.isOpen()))
      {
        long l1 = System.currentTimeMillis() - paramTimeUnit.toMillis(paramLong);
        long l2 = this.lastReleaseTime;
        if (l2 > l1);
      }
      try
      {
        this.uniquePoolEntry.close();
        monitorexit;
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          this.log.debug("Problem closing idle connection.", localIOException);
      }
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

  public ManagedClientConnection getConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("Route may not be null.");
    assertStillUp();
    if (this.log.isDebugEnabled())
      this.log.debug("Get connection for route " + paramHttpRoute);
    monitorenter;
    try
    {
      if (this.managedConn != null)
        throw new IllegalStateException("Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
    }
    finally
    {
      monitorexit;
    }
    int i = 0;
    closeExpiredConnections();
    int j;
    if (this.uniquePoolEntry.connection.isOpen())
    {
      RouteTracker localRouteTracker = this.uniquePoolEntry.tracker;
      if (localRouteTracker != null)
      {
        boolean bool = localRouteTracker.toRoute().equals(paramHttpRoute);
        if (bool);
      }
      else
      {
        j = 1;
      }
    }
    while (true)
    {
      if (j != 0)
        i = 1;
      try
      {
        this.uniquePoolEntry.shutdown();
        if (i != 0)
          this.uniquePoolEntry = new PoolEntry();
        this.managedConn = new ConnAdapter(this.uniquePoolEntry, paramHttpRoute);
        ConnAdapter localConnAdapter = this.managedConn;
        monitorexit;
        return localConnAdapter;
      }
      catch (IOException localIOException)
      {
        while (true)
          this.log.debug("Problem shutting down connection.", localIOException);
      }
      i = 0;
      j = 0;
      continue;
      i = 1;
      j = 0;
    }
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    assertStillUp();
    if (!(paramManagedClientConnection instanceof ConnAdapter))
      throw new IllegalArgumentException("Connection class mismatch, connection not obtained from this manager.");
    if (this.log.isDebugEnabled())
      this.log.debug("Releasing connection " + paramManagedClientConnection);
    synchronized ((ConnAdapter)paramManagedClientConnection)
    {
      if (???.poolEntry == null)
        return;
      ClientConnectionManager localClientConnectionManager = ???.getManager();
      if ((localClientConnectionManager != null) && (localClientConnectionManager != this))
        throw new IllegalArgumentException("Connection not obtained from this manager.");
    }
    try
    {
      if ((???.isOpen()) && ((this.alwaysShutDown) || (!???.isMarkedReusable())))
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Released connection open but not reusable.");
        ???.shutdown();
      }
      ???.detach();
      monitorenter;
      try
      {
        this.managedConn = null;
        this.lastReleaseTime = System.currentTimeMillis();
        if (paramLong > 0L);
        for (this.connectionExpiresTime = (paramTimeUnit.toMillis(paramLong) + this.lastReleaseTime); ; this.connectionExpiresTime = 9223372036854775807L)
        {
          monitorexit;
          return;
        }
      }
      finally
      {
        monitorexit;
      }
    }
    catch (IOException localIOException)
    {
      if (this.log.isDebugEnabled())
        this.log.debug("Exception shutting down released connection.", localIOException);
      ???.detach();
      monitorenter;
      while (true)
      {
        try
        {
          this.managedConn = null;
          this.lastReleaseTime = System.currentTimeMillis();
          if (paramLong > 0L)
          {
            this.connectionExpiresTime = (paramTimeUnit.toMillis(paramLong) + this.lastReleaseTime);
            monitorexit;
            break;
          }
        }
        finally
        {
          monitorexit;
        }
        this.connectionExpiresTime = 9223372036854775807L;
      }
    }
    finally
    {
      ???.detach();
      monitorenter;
      try
      {
        this.managedConn = null;
        this.lastReleaseTime = System.currentTimeMillis();
        if (paramLong > 0L);
        for (this.connectionExpiresTime = (paramTimeUnit.toMillis(paramLong) + this.lastReleaseTime); ; this.connectionExpiresTime = 9223372036854775807L)
          throw localObject2;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject3;
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
        return SingleClientConnManager.this.getConnection(this.val$route, this.val$state);
      }
    };
  }

  // ERROR //
  protected void revokeConnection()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 80	org/apache/http/impl/conn/SingleClientConnManager:managedConn	Lorg/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull +4 -> 10
    //   9: return
    //   10: aload_1
    //   11: invokevirtual 235	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:detach	()V
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: getfield 78	org/apache/http/impl/conn/SingleClientConnManager:uniquePoolEntry	Lorg/apache/http/impl/conn/SingleClientConnManager$PoolEntry;
    //   20: invokevirtual 201	org/apache/http/impl/conn/SingleClientConnManager$PoolEntry:shutdown	()V
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_3
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_3
    //   30: athrow
    //   31: astore_2
    //   32: aload_0
    //   33: getfield 56	org/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   36: ldc 249
    //   38: aload_2
    //   39: invokeinterface 145 3 0
    //   44: goto -21 -> 23
    //
    // Exception table:
    //   from	to	target	type
    //   16	23	26	finally
    //   23	25	26	finally
    //   27	29	26	finally
    //   32	44	26	finally
    //   16	23	31	java/io/IOException
  }

  public void shutdown()
  {
    this.isShutDown = true;
    monitorenter;
    try
    {
      if (this.uniquePoolEntry != null)
        this.uniquePoolEntry.shutdown();
    }
    catch (IOException localIOException)
    {
    }
    finally
    {
      this.uniquePoolEntry = null;
      this.managedConn = null;
    }
    throw localObject1;
  }

  protected class ConnAdapter extends AbstractPooledConnAdapter
  {
    protected ConnAdapter(SingleClientConnManager.PoolEntry paramHttpRoute, HttpRoute arg3)
    {
      super(paramHttpRoute);
      markReusable();
      Object localObject;
      paramHttpRoute.route = localObject;
    }
  }

  protected class PoolEntry extends AbstractPoolEntry
  {
    protected PoolEntry()
    {
      super(null);
    }

    protected void close()
      throws IOException
    {
      shutdownEntry();
      if (this.connection.isOpen())
        this.connection.close();
    }

    protected void shutdown()
      throws IOException
    {
      shutdownEntry();
      if (this.connection.isOpen())
        this.connection.shutdown();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.SingleClientConnManager
 * JD-Core Version:    0.6.0
 */