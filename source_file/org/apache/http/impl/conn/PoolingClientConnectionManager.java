package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.pool.ConnPoolControl;
import org.apache.http.pool.PoolStats;

@ThreadSafe
public class PoolingClientConnectionManager
  implements ClientConnectionManager, ConnPoolControl<HttpRoute>
{
  private final DnsResolver dnsResolver;
  private final Log log = LogFactory.getLog(getClass());
  private final ClientConnectionOperator operator;
  private final HttpConnPool pool;
  private final SchemeRegistry schemeRegistry;

  public PoolingClientConnectionManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public PoolingClientConnectionManager(SchemeRegistry paramSchemeRegistry)
  {
    this(paramSchemeRegistry, -1L, TimeUnit.MILLISECONDS);
  }

  public PoolingClientConnectionManager(SchemeRegistry paramSchemeRegistry, long paramLong, TimeUnit paramTimeUnit)
  {
    this(paramSchemeRegistry, paramLong, paramTimeUnit, new SystemDefaultDnsResolver());
  }

  public PoolingClientConnectionManager(SchemeRegistry paramSchemeRegistry, long paramLong, TimeUnit paramTimeUnit, DnsResolver paramDnsResolver)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    if (paramDnsResolver == null)
      throw new IllegalArgumentException("DNS resolver may not be null");
    this.schemeRegistry = paramSchemeRegistry;
    this.dnsResolver = paramDnsResolver;
    this.operator = createConnectionOperator(paramSchemeRegistry);
    this.pool = new HttpConnPool(this.log, 2, 20, paramLong, paramTimeUnit);
  }

  public PoolingClientConnectionManager(SchemeRegistry paramSchemeRegistry, DnsResolver paramDnsResolver)
  {
    this(paramSchemeRegistry, -1L, TimeUnit.MILLISECONDS, paramDnsResolver);
  }

  private String format(HttpRoute paramHttpRoute, Object paramObject)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[route: ").append(paramHttpRoute).append("]");
    if (paramObject != null)
      localStringBuilder.append("[state: ").append(paramObject).append("]");
    return localStringBuilder.toString();
  }

  private String format(HttpPoolEntry paramHttpPoolEntry)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[id: ").append(paramHttpPoolEntry.getId()).append("]");
    localStringBuilder.append("[route: ").append(paramHttpPoolEntry.getRoute()).append("]");
    Object localObject = paramHttpPoolEntry.getState();
    if (localObject != null)
      localStringBuilder.append("[state: ").append(localObject).append("]");
    return localStringBuilder.toString();
  }

  private String formatStats(HttpRoute paramHttpRoute)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    PoolStats localPoolStats1 = this.pool.getTotalStats();
    PoolStats localPoolStats2 = this.pool.getStats(paramHttpRoute);
    localStringBuilder.append("[total kept alive: ").append(localPoolStats1.getAvailable()).append("; ");
    localStringBuilder.append("route allocated: ").append(localPoolStats2.getLeased() + localPoolStats2.getAvailable());
    localStringBuilder.append(" of ").append(localPoolStats2.getMax()).append("; ");
    localStringBuilder.append("total allocated: ").append(localPoolStats1.getLeased() + localPoolStats1.getAvailable());
    localStringBuilder.append(" of ").append(localPoolStats1.getMax()).append("]");
    return localStringBuilder.toString();
  }

  public void closeExpiredConnections()
  {
    this.log.debug("Closing expired connections");
    this.pool.closeExpired();
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Closing connections idle longer than " + paramLong + " " + paramTimeUnit);
    this.pool.closeIdle(paramLong, paramTimeUnit);
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry, this.dnsResolver);
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

  public int getDefaultMaxPerRoute()
  {
    return this.pool.getDefaultMaxPerRoute();
  }

  public int getMaxPerRoute(HttpRoute paramHttpRoute)
  {
    return this.pool.getMaxPerRoute(paramHttpRoute);
  }

  public int getMaxTotal()
  {
    return this.pool.getMaxTotal();
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  public PoolStats getStats(HttpRoute paramHttpRoute)
  {
    return this.pool.getStats(paramHttpRoute);
  }

  public PoolStats getTotalStats()
  {
    return this.pool.getTotalStats();
  }

  ManagedClientConnection leaseConnection(Future<HttpPoolEntry> paramFuture, long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ConnectionPoolTimeoutException
  {
    HttpPoolEntry localHttpPoolEntry;
    try
    {
      localHttpPoolEntry = (HttpPoolEntry)paramFuture.get(paramLong, paramTimeUnit);
      if ((localHttpPoolEntry == null) || (paramFuture.isCancelled()))
        throw new InterruptedException();
    }
    catch (ExecutionException localExecutionException)
    {
      Object localObject = localExecutionException.getCause();
      if (localObject == null)
        localObject = localExecutionException;
      this.log.error("Unexpected exception leasing connection from pool", (Throwable)localObject);
      throw new InterruptedException();
      if (localHttpPoolEntry.getConnection() == null)
        throw new IllegalStateException("Pool entry with no connection");
    }
    catch (TimeoutException localTimeoutException)
    {
      throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
    }
    if (this.log.isDebugEnabled())
      this.log.debug("Connection leased: " + format(localHttpPoolEntry) + formatStats((HttpRoute)localHttpPoolEntry.getRoute()));
    ManagedClientConnectionImpl localManagedClientConnectionImpl = new ManagedClientConnectionImpl(this, this.operator, localHttpPoolEntry);
    return (ManagedClientConnection)localManagedClientConnectionImpl;
  }

  // ERROR //
  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 269
    //   4: ifne +14 -> 18
    //   7: new 64	java/lang/IllegalArgumentException
    //   10: dup
    //   11: ldc_w 278
    //   14: invokespecial 69	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   17: athrow
    //   18: aload_1
    //   19: checkcast 269	org/apache/http/impl/conn/ManagedClientConnectionImpl
    //   22: astore 5
    //   24: aload 5
    //   26: invokevirtual 282	org/apache/http/impl/conn/ManagedClientConnectionImpl:getManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   29: aload_0
    //   30: if_acmpeq +14 -> 44
    //   33: new 255	java/lang/IllegalStateException
    //   36: dup
    //   37: ldc_w 284
    //   40: invokespecial 258	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   43: athrow
    //   44: aload 5
    //   46: monitorenter
    //   47: aload 5
    //   49: invokevirtual 288	org/apache/http/impl/conn/ManagedClientConnectionImpl:detach	()Lorg/apache/http/impl/conn/HttpPoolEntry;
    //   52: astore 7
    //   54: aload 7
    //   56: ifnonnull +7 -> 63
    //   59: aload 5
    //   61: monitorexit
    //   62: return
    //   63: aload 5
    //   65: invokevirtual 291	org/apache/http/impl/conn/ManagedClientConnectionImpl:isOpen	()Z
    //   68: ifeq +20 -> 88
    //   71: aload 5
    //   73: invokevirtual 294	org/apache/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   76: istore 11
    //   78: iload 11
    //   80: ifne +8 -> 88
    //   83: aload 5
    //   85: invokevirtual 295	org/apache/http/impl/conn/ManagedClientConnectionImpl:shutdown	()V
    //   88: aload 5
    //   90: invokevirtual 294	org/apache/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   93: ifeq +115 -> 208
    //   96: aload 4
    //   98: ifnull +247 -> 345
    //   101: aload 4
    //   103: astore 9
    //   105: aload 7
    //   107: lload_2
    //   108: aload 9
    //   110: invokevirtual 298	org/apache/http/impl/conn/HttpPoolEntry:updateExpiry	(JLjava/util/concurrent/TimeUnit;)V
    //   113: aload_0
    //   114: getfield 62	org/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   117: invokeinterface 179 1 0
    //   122: ifeq +86 -> 208
    //   125: lload_2
    //   126: lconst_0
    //   127: lcmp
    //   128: ifle +225 -> 353
    //   131: new 93	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   138: ldc_w 300
    //   141: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: lload_2
    //   145: invokevirtual 184	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   148: ldc 186
    //   150: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: aload 4
    //   155: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   158: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   161: astore 10
    //   163: aload_0
    //   164: getfield 62	org/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   167: new 93	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   174: ldc_w 302
    //   177: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: aload_0
    //   181: aload 7
    //   183: invokespecial 265	org/apache/http/impl/conn/PoolingClientConnectionManager:format	(Lorg/apache/http/impl/conn/HttpPoolEntry;)Ljava/lang/String;
    //   186: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: ldc_w 304
    //   192: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: aload 10
    //   197: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: invokeinterface 170 2 0
    //   208: aload_0
    //   209: getfield 88	org/apache/http/impl/conn/PoolingClientConnectionManager:pool	Lorg/apache/http/impl/conn/HttpConnPool;
    //   212: aload 7
    //   214: aload 5
    //   216: invokevirtual 294	org/apache/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   219: invokevirtual 308	org/apache/http/impl/conn/HttpConnPool:release	(Lorg/apache/http/pool/PoolEntry;Z)V
    //   222: aload_0
    //   223: getfield 62	org/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   226: invokeinterface 179 1 0
    //   231: ifeq +52 -> 283
    //   234: aload_0
    //   235: getfield 62	org/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   238: new 93	java/lang/StringBuilder
    //   241: dup
    //   242: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   245: ldc_w 310
    //   248: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: aload_0
    //   252: aload 7
    //   254: invokespecial 265	org/apache/http/impl/conn/PoolingClientConnectionManager:format	(Lorg/apache/http/impl/conn/HttpPoolEntry;)Ljava/lang/String;
    //   257: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: aload_0
    //   261: aload 7
    //   263: invokevirtual 123	org/apache/http/impl/conn/HttpPoolEntry:getRoute	()Ljava/lang/Object;
    //   266: checkcast 208	org/apache/http/conn/routing/HttpRoute
    //   269: invokespecial 267	org/apache/http/impl/conn/PoolingClientConnectionManager:formatStats	(Lorg/apache/http/conn/routing/HttpRoute;)Ljava/lang/String;
    //   272: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   278: invokeinterface 170 2 0
    //   283: aload 5
    //   285: monitorexit
    //   286: return
    //   287: astore 6
    //   289: aload 5
    //   291: monitorexit
    //   292: aload 6
    //   294: athrow
    //   295: astore 12
    //   297: aload_0
    //   298: getfield 62	org/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   301: invokeinterface 179 1 0
    //   306: ifeq -218 -> 88
    //   309: aload_0
    //   310: getfield 62	org/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   313: ldc_w 312
    //   316: aload 12
    //   318: invokeinterface 314 3 0
    //   323: goto -235 -> 88
    //   326: astore 8
    //   328: aload_0
    //   329: getfield 88	org/apache/http/impl/conn/PoolingClientConnectionManager:pool	Lorg/apache/http/impl/conn/HttpConnPool;
    //   332: aload 7
    //   334: aload 5
    //   336: invokevirtual 294	org/apache/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   339: invokevirtual 308	org/apache/http/impl/conn/HttpConnPool:release	(Lorg/apache/http/pool/PoolEntry;Z)V
    //   342: aload 8
    //   344: athrow
    //   345: getstatic 39	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   348: astore 9
    //   350: goto -245 -> 105
    //   353: ldc_w 316
    //   356: astore 10
    //   358: goto -195 -> 163
    //
    // Exception table:
    //   from	to	target	type
    //   47	54	287	finally
    //   59	62	287	finally
    //   208	283	287	finally
    //   283	286	287	finally
    //   289	292	287	finally
    //   328	345	287	finally
    //   83	88	295	java/io/IOException
    //   63	78	326	finally
    //   83	88	326	finally
    //   88	96	326	finally
    //   105	125	326	finally
    //   131	163	326	finally
    //   163	208	326	finally
    //   297	323	326	finally
    //   345	350	326	finally
  }

  public ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("HTTP route may not be null");
    if (this.log.isDebugEnabled())
      this.log.debug("Connection request: " + format(paramHttpRoute, paramObject) + formatStats(paramHttpRoute));
    return new ClientConnectionRequest(this.pool.lease(paramHttpRoute, paramObject))
    {
      public void abortRequest()
      {
        this.val$future.cancel(true);
      }

      public ManagedClientConnection getConnection(long paramLong, TimeUnit paramTimeUnit)
        throws InterruptedException, ConnectionPoolTimeoutException
      {
        return PoolingClientConnectionManager.this.leaseConnection(this.val$future, paramLong, paramTimeUnit);
      }
    };
  }

  public void setDefaultMaxPerRoute(int paramInt)
  {
    this.pool.setDefaultMaxPerRoute(paramInt);
  }

  public void setMaxPerRoute(HttpRoute paramHttpRoute, int paramInt)
  {
    this.pool.setMaxPerRoute(paramHttpRoute, paramInt);
  }

  public void setMaxTotal(int paramInt)
  {
    this.pool.setMaxTotal(paramInt);
  }

  public void shutdown()
  {
    this.log.debug("Connection manager is shutting down");
    try
    {
      this.pool.shutdown();
      this.log.debug("Connection manager shut down");
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        this.log.debug("I/O exception shutting down connection manager", localIOException);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.PoolingClientConnectionManager
 * JD-Core Version:    0.6.0
 */