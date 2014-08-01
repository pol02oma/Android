package org.apache.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.DefaultClientConnectionOperator;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.params.HttpParams;

@Deprecated
@ThreadSafe
public class ThreadSafeClientConnManager
  implements ClientConnectionManager
{
  protected final ClientConnectionOperator connOperator;
  protected final ConnPerRouteBean connPerRoute;
  protected final AbstractConnPool connectionPool;
  private final Log log;
  protected final ConnPoolByRoute pool;
  protected final SchemeRegistry schemeRegistry;

  public ThreadSafeClientConnManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public ThreadSafeClientConnManager(SchemeRegistry paramSchemeRegistry)
  {
    this(paramSchemeRegistry, -1L, TimeUnit.MILLISECONDS);
  }

  public ThreadSafeClientConnManager(SchemeRegistry paramSchemeRegistry, long paramLong, TimeUnit paramTimeUnit)
  {
    this(paramSchemeRegistry, paramLong, paramTimeUnit, new ConnPerRouteBean());
  }

  public ThreadSafeClientConnManager(SchemeRegistry paramSchemeRegistry, long paramLong, TimeUnit paramTimeUnit, ConnPerRouteBean paramConnPerRouteBean)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    this.log = LogFactory.getLog(getClass());
    this.schemeRegistry = paramSchemeRegistry;
    this.connPerRoute = paramConnPerRouteBean;
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
    this.pool = createConnectionPool(paramLong, paramTimeUnit);
    this.connectionPool = this.pool;
  }

  public ThreadSafeClientConnManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    this.log = LogFactory.getLog(getClass());
    this.schemeRegistry = paramSchemeRegistry;
    this.connPerRoute = new ConnPerRouteBean();
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
    this.pool = ((ConnPoolByRoute)createConnectionPool(paramHttpParams));
    this.connectionPool = this.pool;
  }

  public void closeExpiredConnections()
  {
    this.log.debug("Closing expired connections");
    this.pool.closeExpiredConnections();
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Closing connections idle longer than " + paramLong + " " + paramTimeUnit);
    this.pool.closeIdleConnections(paramLong, paramTimeUnit);
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry);
  }

  protected AbstractConnPool createConnectionPool(HttpParams paramHttpParams)
  {
    return new ConnPoolByRoute(this.connOperator, paramHttpParams);
  }

  protected ConnPoolByRoute createConnectionPool(long paramLong, TimeUnit paramTimeUnit)
  {
    return new ConnPoolByRoute(this.connOperator, this.connPerRoute, 20, paramLong, paramTimeUnit);
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

  public int getConnectionsInPool()
  {
    return this.pool.getConnectionsInPool();
  }

  public int getConnectionsInPool(HttpRoute paramHttpRoute)
  {
    return this.pool.getConnectionsInPool(paramHttpRoute);
  }

  public int getDefaultMaxPerRoute()
  {
    return this.connPerRoute.getDefaultMaxPerRoute();
  }

  public int getMaxForRoute(HttpRoute paramHttpRoute)
  {
    return this.connPerRoute.getMaxForRoute(paramHttpRoute);
  }

  public int getMaxTotal()
  {
    return this.pool.getMaxTotalConnections();
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  // ERROR //
  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 176
    //   4: ifne +13 -> 17
    //   7: new 52	java/lang/IllegalArgumentException
    //   10: dup
    //   11: ldc 178
    //   13: invokespecial 57	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_1
    //   18: checkcast 176	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter
    //   21: astore 5
    //   23: aload 5
    //   25: invokevirtual 182	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:getPoolEntry	()Lorg/apache/http/impl/conn/AbstractPoolEntry;
    //   28: ifnull +22 -> 50
    //   31: aload 5
    //   33: invokevirtual 186	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:getManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   36: aload_0
    //   37: if_acmpeq +13 -> 50
    //   40: new 52	java/lang/IllegalArgumentException
    //   43: dup
    //   44: ldc 188
    //   46: invokespecial 57	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   49: athrow
    //   50: aload 5
    //   52: monitorenter
    //   53: aload 5
    //   55: invokevirtual 182	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:getPoolEntry	()Lorg/apache/http/impl/conn/AbstractPoolEntry;
    //   58: checkcast 190	org/apache/http/impl/conn/tsccm/BasicPoolEntry
    //   61: astore 7
    //   63: aload 7
    //   65: ifnonnull +7 -> 72
    //   68: aload 5
    //   70: monitorexit
    //   71: return
    //   72: aload 5
    //   74: invokevirtual 193	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isOpen	()Z
    //   77: ifeq +16 -> 93
    //   80: aload 5
    //   82: invokevirtual 196	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   85: ifne +8 -> 93
    //   88: aload 5
    //   90: invokevirtual 197	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:shutdown	()V
    //   93: aload 5
    //   95: invokevirtual 196	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   98: istore 12
    //   100: aload_0
    //   101: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   104: invokeinterface 112 1 0
    //   109: ifeq +19 -> 128
    //   112: iload 12
    //   114: ifeq +45 -> 159
    //   117: aload_0
    //   118: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   121: ldc 199
    //   123: invokeinterface 104 2 0
    //   128: aload 5
    //   130: invokevirtual 202	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:detach	()V
    //   133: aload_0
    //   134: getfield 85	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:pool	Lorg/apache/http/impl/conn/tsccm/ConnPoolByRoute;
    //   137: aload 7
    //   139: iload 12
    //   141: lload_2
    //   142: aload 4
    //   144: invokevirtual 206	org/apache/http/impl/conn/tsccm/ConnPoolByRoute:freeEntry	(Lorg/apache/http/impl/conn/tsccm/BasicPoolEntry;ZJLjava/util/concurrent/TimeUnit;)V
    //   147: aload 5
    //   149: monitorexit
    //   150: return
    //   151: astore 6
    //   153: aload 5
    //   155: monitorexit
    //   156: aload 6
    //   158: athrow
    //   159: aload_0
    //   160: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   163: ldc 208
    //   165: invokeinterface 104 2 0
    //   170: goto -42 -> 128
    //   173: astore 10
    //   175: aload_0
    //   176: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   179: invokeinterface 112 1 0
    //   184: ifeq +16 -> 200
    //   187: aload_0
    //   188: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   191: ldc 210
    //   193: aload 10
    //   195: invokeinterface 213 3 0
    //   200: aload 5
    //   202: invokevirtual 196	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   205: istore 11
    //   207: aload_0
    //   208: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   211: invokeinterface 112 1 0
    //   216: ifeq +19 -> 235
    //   219: iload 11
    //   221: ifeq +36 -> 257
    //   224: aload_0
    //   225: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   228: ldc 199
    //   230: invokeinterface 104 2 0
    //   235: aload 5
    //   237: invokevirtual 202	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:detach	()V
    //   240: aload_0
    //   241: getfield 85	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:pool	Lorg/apache/http/impl/conn/tsccm/ConnPoolByRoute;
    //   244: aload 7
    //   246: iload 11
    //   248: lload_2
    //   249: aload 4
    //   251: invokevirtual 206	org/apache/http/impl/conn/tsccm/ConnPoolByRoute:freeEntry	(Lorg/apache/http/impl/conn/tsccm/BasicPoolEntry;ZJLjava/util/concurrent/TimeUnit;)V
    //   254: goto -107 -> 147
    //   257: aload_0
    //   258: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   261: ldc 208
    //   263: invokeinterface 104 2 0
    //   268: goto -33 -> 235
    //   271: astore 8
    //   273: aload 5
    //   275: invokevirtual 196	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   278: istore 9
    //   280: aload_0
    //   281: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   284: invokeinterface 112 1 0
    //   289: ifeq +19 -> 308
    //   292: iload 9
    //   294: ifeq +36 -> 330
    //   297: aload_0
    //   298: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   301: ldc 199
    //   303: invokeinterface 104 2 0
    //   308: aload 5
    //   310: invokevirtual 202	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:detach	()V
    //   313: aload_0
    //   314: getfield 85	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:pool	Lorg/apache/http/impl/conn/tsccm/ConnPoolByRoute;
    //   317: aload 7
    //   319: iload 9
    //   321: lload_2
    //   322: aload 4
    //   324: invokevirtual 206	org/apache/http/impl/conn/tsccm/ConnPoolByRoute:freeEntry	(Lorg/apache/http/impl/conn/tsccm/BasicPoolEntry;ZJLjava/util/concurrent/TimeUnit;)V
    //   327: aload 8
    //   329: athrow
    //   330: aload_0
    //   331: getfield 69	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   334: ldc 208
    //   336: invokeinterface 104 2 0
    //   341: goto -33 -> 308
    //
    // Exception table:
    //   from	to	target	type
    //   53	63	151	finally
    //   68	71	151	finally
    //   93	112	151	finally
    //   117	128	151	finally
    //   128	147	151	finally
    //   147	150	151	finally
    //   153	156	151	finally
    //   159	170	151	finally
    //   200	219	151	finally
    //   224	235	151	finally
    //   235	254	151	finally
    //   257	268	151	finally
    //   273	292	151	finally
    //   297	308	151	finally
    //   308	330	151	finally
    //   330	341	151	finally
    //   72	93	173	java/io/IOException
    //   72	93	271	finally
    //   175	200	271	finally
  }

  public ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    return new ClientConnectionRequest(this.pool.requestPoolEntry(paramHttpRoute, paramObject), paramHttpRoute)
    {
      public void abortRequest()
      {
        this.val$poolRequest.abortRequest();
      }

      public ManagedClientConnection getConnection(long paramLong, TimeUnit paramTimeUnit)
        throws InterruptedException, ConnectionPoolTimeoutException
      {
        if (this.val$route == null)
          throw new IllegalArgumentException("Route may not be null.");
        if (ThreadSafeClientConnManager.this.log.isDebugEnabled())
          ThreadSafeClientConnManager.this.log.debug("Get connection: " + this.val$route + ", timeout = " + paramLong);
        BasicPoolEntry localBasicPoolEntry = this.val$poolRequest.getPoolEntry(paramLong, paramTimeUnit);
        return new BasicPooledConnAdapter(ThreadSafeClientConnManager.this, localBasicPoolEntry);
      }
    };
  }

  public void setDefaultMaxPerRoute(int paramInt)
  {
    this.connPerRoute.setDefaultMaxPerRoute(paramInt);
  }

  public void setMaxForRoute(HttpRoute paramHttpRoute, int paramInt)
  {
    this.connPerRoute.setMaxForRoute(paramHttpRoute, paramInt);
  }

  public void setMaxTotal(int paramInt)
  {
    this.pool.setMaxTotalConnections(paramInt);
  }

  public void shutdown()
  {
    this.log.debug("Shutting down");
    this.pool.shutdown();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
 * JD-Core Version:    0.6.0
 */