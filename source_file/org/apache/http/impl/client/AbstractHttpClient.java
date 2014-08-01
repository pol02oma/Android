package org.apache.http.impl.client;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.BackoffManager;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ConnectionBackoffStrategy;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.impl.cookie.IgnoreSpecFactory;
import org.apache.http.impl.cookie.NetscapeDraftSpecFactory;
import org.apache.http.impl.cookie.RFC2109SpecFactory;
import org.apache.http.impl.cookie.RFC2965SpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.util.EntityUtils;

@ThreadSafe
public abstract class AbstractHttpClient
  implements HttpClient
{

  @GuardedBy("this")
  private BackoffManager backoffManager;

  @GuardedBy("this")
  private ClientConnectionManager connManager;

  @GuardedBy("this")
  private ConnectionBackoffStrategy connectionBackoffStrategy;

  @GuardedBy("this")
  private CookieStore cookieStore;

  @GuardedBy("this")
  private CredentialsProvider credsProvider;

  @GuardedBy("this")
  private HttpParams defaultParams;

  @GuardedBy("this")
  private ConnectionKeepAliveStrategy keepAliveStrategy;
  private final Log log = LogFactory.getLog(getClass());

  @GuardedBy("this")
  private BasicHttpProcessor mutableProcessor;

  @GuardedBy("this")
  private ImmutableHttpProcessor protocolProcessor;

  @GuardedBy("this")
  private AuthenticationStrategy proxyAuthStrategy;

  @GuardedBy("this")
  private RedirectStrategy redirectStrategy;

  @GuardedBy("this")
  private HttpRequestExecutor requestExec;

  @GuardedBy("this")
  private HttpRequestRetryHandler retryHandler;

  @GuardedBy("this")
  private ConnectionReuseStrategy reuseStrategy;

  @GuardedBy("this")
  private HttpRoutePlanner routePlanner;

  @GuardedBy("this")
  private AuthSchemeRegistry supportedAuthSchemes;

  @GuardedBy("this")
  private CookieSpecRegistry supportedCookieSpecs;

  @GuardedBy("this")
  private AuthenticationStrategy targetAuthStrategy;

  @GuardedBy("this")
  private UserTokenHandler userTokenHandler;

  protected AbstractHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    this.defaultParams = paramHttpParams;
    this.connManager = paramClientConnectionManager;
  }

  private static HttpHost determineTarget(HttpUriRequest paramHttpUriRequest)
    throws ClientProtocolException
  {
    URI localURI = paramHttpUriRequest.getURI();
    boolean bool = localURI.isAbsolute();
    HttpHost localHttpHost = null;
    if (bool)
    {
      localHttpHost = URIUtils.extractHost(localURI);
      if (localHttpHost == null)
        throw new ClientProtocolException("URI does not specify a valid host name: " + localURI);
    }
    return localHttpHost;
  }

  private final HttpProcessor getProtocolProcessor()
  {
    monitorenter;
    try
    {
      if (this.protocolProcessor == null)
      {
        BasicHttpProcessor localBasicHttpProcessor = getHttpProcessor();
        int i = localBasicHttpProcessor.getRequestInterceptorCount();
        HttpRequestInterceptor[] arrayOfHttpRequestInterceptor = new HttpRequestInterceptor[i];
        for (int j = 0; j < i; j++)
          arrayOfHttpRequestInterceptor[j] = localBasicHttpProcessor.getRequestInterceptor(j);
        int k = localBasicHttpProcessor.getResponseInterceptorCount();
        HttpResponseInterceptor[] arrayOfHttpResponseInterceptor = new HttpResponseInterceptor[k];
        for (int m = 0; m < k; m++)
          arrayOfHttpResponseInterceptor[m] = localBasicHttpProcessor.getResponseInterceptor(m);
        this.protocolProcessor = new ImmutableHttpProcessor(arrayOfHttpRequestInterceptor, arrayOfHttpResponseInterceptor);
      }
      ImmutableHttpProcessor localImmutableHttpProcessor = this.protocolProcessor;
      return localImmutableHttpProcessor;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    monitorenter;
    try
    {
      getHttpProcessor().addInterceptor(paramHttpRequestInterceptor);
      this.protocolProcessor = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt)
  {
    monitorenter;
    try
    {
      getHttpProcessor().addInterceptor(paramHttpRequestInterceptor, paramInt);
      this.protocolProcessor = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    monitorenter;
    try
    {
      getHttpProcessor().addInterceptor(paramHttpResponseInterceptor);
      this.protocolProcessor = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor, int paramInt)
  {
    monitorenter;
    try
    {
      getHttpProcessor().addInterceptor(paramHttpResponseInterceptor, paramInt);
      this.protocolProcessor = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void clearRequestInterceptors()
  {
    monitorenter;
    try
    {
      getHttpProcessor().clearRequestInterceptors();
      this.protocolProcessor = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void clearResponseInterceptors()
  {
    monitorenter;
    try
    {
      getHttpProcessor().clearResponseInterceptors();
      this.protocolProcessor = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected AuthSchemeRegistry createAuthSchemeRegistry()
  {
    AuthSchemeRegistry localAuthSchemeRegistry = new AuthSchemeRegistry();
    localAuthSchemeRegistry.register("Basic", new BasicSchemeFactory());
    localAuthSchemeRegistry.register("Digest", new DigestSchemeFactory());
    localAuthSchemeRegistry.register("NTLM", new NTLMSchemeFactory());
    localAuthSchemeRegistry.register("negotiate", new SPNegoSchemeFactory());
    localAuthSchemeRegistry.register("Kerberos", new KerberosSchemeFactory());
    return localAuthSchemeRegistry;
  }

  protected ClientConnectionManager createClientConnectionManager()
  {
    SchemeRegistry localSchemeRegistry = SchemeRegistryFactory.createDefault();
    HttpParams localHttpParams = getParams();
    String str = (String)localHttpParams.getParameter("http.connection-manager.factory-class-name");
    ClientConnectionManagerFactory localClientConnectionManagerFactory = null;
    if (str != null);
    try
    {
      localClientConnectionManagerFactory = (ClientConnectionManagerFactory)Class.forName(str).newInstance();
      if (localClientConnectionManagerFactory != null)
        return localClientConnectionManagerFactory.newInstance(localHttpParams, localSchemeRegistry);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new IllegalStateException("Invalid class name: " + str);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalAccessError(localIllegalAccessException.getMessage());
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new InstantiationError(localInstantiationException.getMessage());
    }
    return new BasicClientConnectionManager(localSchemeRegistry);
  }

  @Deprecated
  protected RequestDirector createClientRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectHandler paramRedirectHandler, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    return new DefaultRequestDirector(paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, paramRedirectHandler, paramAuthenticationHandler1, paramAuthenticationHandler2, paramUserTokenHandler, paramHttpParams);
  }

  @Deprecated
  protected RequestDirector createClientRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    return new DefaultRequestDirector(this.log, paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, paramRedirectStrategy, paramAuthenticationHandler1, paramAuthenticationHandler2, paramUserTokenHandler, paramHttpParams);
  }

  protected RequestDirector createClientRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationStrategy paramAuthenticationStrategy1, AuthenticationStrategy paramAuthenticationStrategy2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    return new DefaultRequestDirector(this.log, paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, paramRedirectStrategy, paramAuthenticationStrategy1, paramAuthenticationStrategy2, paramUserTokenHandler, paramHttpParams);
  }

  protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy()
  {
    return new DefaultConnectionKeepAliveStrategy();
  }

  protected ConnectionReuseStrategy createConnectionReuseStrategy()
  {
    return new DefaultConnectionReuseStrategy();
  }

  protected CookieSpecRegistry createCookieSpecRegistry()
  {
    CookieSpecRegistry localCookieSpecRegistry = new CookieSpecRegistry();
    localCookieSpecRegistry.register("best-match", new BestMatchSpecFactory());
    localCookieSpecRegistry.register("compatibility", new BrowserCompatSpecFactory());
    localCookieSpecRegistry.register("netscape", new NetscapeDraftSpecFactory());
    localCookieSpecRegistry.register("rfc2109", new RFC2109SpecFactory());
    localCookieSpecRegistry.register("rfc2965", new RFC2965SpecFactory());
    localCookieSpecRegistry.register("ignoreCookies", new IgnoreSpecFactory());
    return localCookieSpecRegistry;
  }

  protected CookieStore createCookieStore()
  {
    return new BasicCookieStore();
  }

  protected CredentialsProvider createCredentialsProvider()
  {
    return new BasicCredentialsProvider();
  }

  protected HttpContext createHttpContext()
  {
    BasicHttpContext localBasicHttpContext = new BasicHttpContext();
    localBasicHttpContext.setAttribute("http.scheme-registry", getConnectionManager().getSchemeRegistry());
    localBasicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
    localBasicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
    localBasicHttpContext.setAttribute("http.cookie-store", getCookieStore());
    localBasicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
    return localBasicHttpContext;
  }

  protected abstract HttpParams createHttpParams();

  protected abstract BasicHttpProcessor createHttpProcessor();

  protected HttpRequestRetryHandler createHttpRequestRetryHandler()
  {
    return new DefaultHttpRequestRetryHandler();
  }

  protected HttpRoutePlanner createHttpRoutePlanner()
  {
    return new DefaultHttpRoutePlanner(getConnectionManager().getSchemeRegistry());
  }

  @Deprecated
  protected AuthenticationHandler createProxyAuthenticationHandler()
  {
    return new DefaultProxyAuthenticationHandler();
  }

  protected AuthenticationStrategy createProxyAuthenticationStrategy()
  {
    return new ProxyAuthenticationStrategy();
  }

  @Deprecated
  protected RedirectHandler createRedirectHandler()
  {
    return new DefaultRedirectHandler();
  }

  protected HttpRequestExecutor createRequestExecutor()
  {
    return new HttpRequestExecutor();
  }

  @Deprecated
  protected AuthenticationHandler createTargetAuthenticationHandler()
  {
    return new DefaultTargetAuthenticationHandler();
  }

  protected AuthenticationStrategy createTargetAuthenticationStrategy()
  {
    return new TargetAuthenticationStrategy();
  }

  protected UserTokenHandler createUserTokenHandler()
  {
    return new DefaultUserTokenHandler();
  }

  protected HttpParams determineParams(HttpRequest paramHttpRequest)
  {
    return new ClientParamsStack(null, getParams(), paramHttpRequest.getParams(), null);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpHost, paramHttpRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    if (paramResponseHandler == null)
      throw new IllegalArgumentException("Response handler must not be null.");
    HttpResponse localHttpResponse = execute(paramHttpHost, paramHttpRequest, paramHttpContext);
    try
    {
      Object localObject = paramResponseHandler.handleResponse(localHttpResponse);
      EntityUtils.consume(localHttpResponse.getEntity());
      return localObject;
    }
    catch (Exception localException1)
    {
      HttpEntity localHttpEntity = localHttpResponse.getEntity();
      try
      {
        EntityUtils.consume(localHttpEntity);
        if ((localException1 instanceof RuntimeException))
          throw ((RuntimeException)localException1);
      }
      catch (Exception localException2)
      {
        while (true)
          this.log.warn("Error consuming content after an exception.", localException2);
        if ((localException1 instanceof IOException))
          throw ((IOException)localException1);
      }
    }
    throw new UndeclaredThrowableException(localException1);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpUriRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return execute(determineTarget(paramHttpUriRequest), paramHttpUriRequest, paramResponseHandler, paramHttpContext);
  }

  public final HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpHost, paramHttpRequest, (HttpContext)null);
  }

  // ERROR //
  public final HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +14 -> 15
    //   4: new 444	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc_w 494
    //   11: invokespecial 447	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   14: athrow
    //   15: aload_0
    //   16: monitorenter
    //   17: aload_0
    //   18: invokevirtual 496	org/apache/http/impl/client/AbstractHttpClient:createHttpContext	()Lorg/apache/http/protocol/HttpContext;
    //   21: astore 5
    //   23: aload_3
    //   24: ifnonnull +150 -> 174
    //   27: aload 5
    //   29: astore 7
    //   31: aload_0
    //   32: aload_0
    //   33: invokevirtual 499	org/apache/http/impl/client/AbstractHttpClient:getRequestExecutor	()Lorg/apache/http/protocol/HttpRequestExecutor;
    //   36: aload_0
    //   37: invokevirtual 347	org/apache/http/impl/client/AbstractHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   40: aload_0
    //   41: invokevirtual 502	org/apache/http/impl/client/AbstractHttpClient:getConnectionReuseStrategy	()Lorg/apache/http/ConnectionReuseStrategy;
    //   44: aload_0
    //   45: invokevirtual 505	org/apache/http/impl/client/AbstractHttpClient:getConnectionKeepAliveStrategy	()Lorg/apache/http/conn/ConnectionKeepAliveStrategy;
    //   48: aload_0
    //   49: invokevirtual 508	org/apache/http/impl/client/AbstractHttpClient:getRoutePlanner	()Lorg/apache/http/conn/routing/HttpRoutePlanner;
    //   52: aload_0
    //   53: invokespecial 510	org/apache/http/impl/client/AbstractHttpClient:getProtocolProcessor	()Lorg/apache/http/protocol/HttpProcessor;
    //   56: aload_0
    //   57: invokevirtual 513	org/apache/http/impl/client/AbstractHttpClient:getHttpRequestRetryHandler	()Lorg/apache/http/client/HttpRequestRetryHandler;
    //   60: aload_0
    //   61: invokevirtual 517	org/apache/http/impl/client/AbstractHttpClient:getRedirectStrategy	()Lorg/apache/http/client/RedirectStrategy;
    //   64: aload_0
    //   65: invokevirtual 520	org/apache/http/impl/client/AbstractHttpClient:getTargetAuthenticationStrategy	()Lorg/apache/http/client/AuthenticationStrategy;
    //   68: aload_0
    //   69: invokevirtual 523	org/apache/http/impl/client/AbstractHttpClient:getProxyAuthenticationStrategy	()Lorg/apache/http/client/AuthenticationStrategy;
    //   72: aload_0
    //   73: invokevirtual 526	org/apache/http/impl/client/AbstractHttpClient:getUserTokenHandler	()Lorg/apache/http/client/UserTokenHandler;
    //   76: aload_0
    //   77: aload_2
    //   78: invokevirtual 528	org/apache/http/impl/client/AbstractHttpClient:determineParams	(Lorg/apache/http/HttpRequest;)Lorg/apache/http/params/HttpParams;
    //   81: invokevirtual 530	org/apache/http/impl/client/AbstractHttpClient:createClientRequestDirector	(Lorg/apache/http/protocol/HttpRequestExecutor;Lorg/apache/http/conn/ClientConnectionManager;Lorg/apache/http/ConnectionReuseStrategy;Lorg/apache/http/conn/ConnectionKeepAliveStrategy;Lorg/apache/http/conn/routing/HttpRoutePlanner;Lorg/apache/http/protocol/HttpProcessor;Lorg/apache/http/client/HttpRequestRetryHandler;Lorg/apache/http/client/RedirectStrategy;Lorg/apache/http/client/AuthenticationStrategy;Lorg/apache/http/client/AuthenticationStrategy;Lorg/apache/http/client/UserTokenHandler;Lorg/apache/http/params/HttpParams;)Lorg/apache/http/client/RequestDirector;
    //   84: astore 8
    //   86: aload_0
    //   87: invokevirtual 508	org/apache/http/impl/client/AbstractHttpClient:getRoutePlanner	()Lorg/apache/http/conn/routing/HttpRoutePlanner;
    //   90: astore 9
    //   92: aload_0
    //   93: invokevirtual 534	org/apache/http/impl/client/AbstractHttpClient:getConnectionBackoffStrategy	()Lorg/apache/http/client/ConnectionBackoffStrategy;
    //   96: astore 10
    //   98: aload_0
    //   99: invokevirtual 538	org/apache/http/impl/client/AbstractHttpClient:getBackoffManager	()Lorg/apache/http/client/BackoffManager;
    //   102: astore 11
    //   104: aload_0
    //   105: monitorexit
    //   106: aload 10
    //   108: ifnull +224 -> 332
    //   111: aload 11
    //   113: ifnull +219 -> 332
    //   116: aload_1
    //   117: ifnull +83 -> 200
    //   120: aload_1
    //   121: astore 14
    //   123: aload 9
    //   125: aload 14
    //   127: aload_2
    //   128: aload 7
    //   130: invokeinterface 544 4 0
    //   135: astore 15
    //   137: aload 8
    //   139: aload_1
    //   140: aload_2
    //   141: aload 7
    //   143: invokeinterface 547 4 0
    //   148: astore 18
    //   150: aload 10
    //   152: aload 18
    //   154: invokeinterface 553 2 0
    //   159: ifeq +161 -> 320
    //   162: aload 11
    //   164: aload 15
    //   166: invokeinterface 559 2 0
    //   171: aload 18
    //   173: areturn
    //   174: new 561	org/apache/http/protocol/DefaultedHttpContext
    //   177: dup
    //   178: aload_3
    //   179: aload 5
    //   181: invokespecial 564	org/apache/http/protocol/DefaultedHttpContext:<init>	(Lorg/apache/http/protocol/HttpContext;Lorg/apache/http/protocol/HttpContext;)V
    //   184: astore 6
    //   186: aload 6
    //   188: astore 7
    //   190: goto -159 -> 31
    //   193: astore 4
    //   195: aload_0
    //   196: monitorexit
    //   197: aload 4
    //   199: athrow
    //   200: aload_0
    //   201: aload_2
    //   202: invokevirtual 528	org/apache/http/impl/client/AbstractHttpClient:determineParams	(Lorg/apache/http/HttpRequest;)Lorg/apache/http/params/HttpParams;
    //   205: ldc_w 566
    //   208: invokeinterface 226 2 0
    //   213: checkcast 568	org/apache/http/HttpHost
    //   216: astore 14
    //   218: goto -95 -> 123
    //   221: astore 17
    //   223: aload 10
    //   225: aload 17
    //   227: invokeinterface 571 2 0
    //   232: ifeq +12 -> 244
    //   235: aload 11
    //   237: aload 15
    //   239: invokeinterface 559 2 0
    //   244: aload 17
    //   246: athrow
    //   247: astore 13
    //   249: new 74	org/apache/http/client/ClientProtocolException
    //   252: dup
    //   253: aload 13
    //   255: invokespecial 572	org/apache/http/client/ClientProtocolException:<init>	(Ljava/lang/Throwable;)V
    //   258: athrow
    //   259: astore 16
    //   261: aload 10
    //   263: aload 16
    //   265: invokeinterface 571 2 0
    //   270: ifeq +12 -> 282
    //   273: aload 11
    //   275: aload 15
    //   277: invokeinterface 559 2 0
    //   282: aload 16
    //   284: instanceof 492
    //   287: ifeq +9 -> 296
    //   290: aload 16
    //   292: checkcast 492	org/apache/http/HttpException
    //   295: athrow
    //   296: aload 16
    //   298: instanceof 437
    //   301: ifeq +9 -> 310
    //   304: aload 16
    //   306: checkcast 437	java/io/IOException
    //   309: athrow
    //   310: new 480	java/lang/reflect/UndeclaredThrowableException
    //   313: dup
    //   314: aload 16
    //   316: invokespecial 483	java/lang/reflect/UndeclaredThrowableException:<init>	(Ljava/lang/Throwable;)V
    //   319: athrow
    //   320: aload 11
    //   322: aload 15
    //   324: invokeinterface 575 2 0
    //   329: aload 18
    //   331: areturn
    //   332: aload 8
    //   334: aload_1
    //   335: aload_2
    //   336: aload 7
    //   338: invokeinterface 547 4 0
    //   343: astore 12
    //   345: aload 12
    //   347: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   17	23	193	finally
    //   31	106	193	finally
    //   174	186	193	finally
    //   195	197	193	finally
    //   137	150	221	java/lang/RuntimeException
    //   123	137	247	org/apache/http/HttpException
    //   137	150	247	org/apache/http/HttpException
    //   150	171	247	org/apache/http/HttpException
    //   200	218	247	org/apache/http/HttpException
    //   223	244	247	org/apache/http/HttpException
    //   244	247	247	org/apache/http/HttpException
    //   261	282	247	org/apache/http/HttpException
    //   282	296	247	org/apache/http/HttpException
    //   296	310	247	org/apache/http/HttpException
    //   310	320	247	org/apache/http/HttpException
    //   320	329	247	org/apache/http/HttpException
    //   332	345	247	org/apache/http/HttpException
    //   137	150	259	java/lang/Exception
  }

  public final HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpUriRequest, (HttpContext)null);
  }

  public final HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    if (paramHttpUriRequest == null)
      throw new IllegalArgumentException("Request must not be null.");
    return execute(determineTarget(paramHttpUriRequest), paramHttpUriRequest, paramHttpContext);
  }

  public final AuthSchemeRegistry getAuthSchemes()
  {
    monitorenter;
    try
    {
      if (this.supportedAuthSchemes == null)
        this.supportedAuthSchemes = createAuthSchemeRegistry();
      AuthSchemeRegistry localAuthSchemeRegistry = this.supportedAuthSchemes;
      return localAuthSchemeRegistry;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final BackoffManager getBackoffManager()
  {
    monitorenter;
    try
    {
      BackoffManager localBackoffManager = this.backoffManager;
      monitorexit;
      return localBackoffManager;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final ConnectionBackoffStrategy getConnectionBackoffStrategy()
  {
    monitorenter;
    try
    {
      ConnectionBackoffStrategy localConnectionBackoffStrategy = this.connectionBackoffStrategy;
      monitorexit;
      return localConnectionBackoffStrategy;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy()
  {
    monitorenter;
    try
    {
      if (this.keepAliveStrategy == null)
        this.keepAliveStrategy = createConnectionKeepAliveStrategy();
      ConnectionKeepAliveStrategy localConnectionKeepAliveStrategy = this.keepAliveStrategy;
      return localConnectionKeepAliveStrategy;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final ClientConnectionManager getConnectionManager()
  {
    monitorenter;
    try
    {
      if (this.connManager == null)
        this.connManager = createClientConnectionManager();
      ClientConnectionManager localClientConnectionManager = this.connManager;
      return localClientConnectionManager;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final ConnectionReuseStrategy getConnectionReuseStrategy()
  {
    monitorenter;
    try
    {
      if (this.reuseStrategy == null)
        this.reuseStrategy = createConnectionReuseStrategy();
      ConnectionReuseStrategy localConnectionReuseStrategy = this.reuseStrategy;
      return localConnectionReuseStrategy;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final CookieSpecRegistry getCookieSpecs()
  {
    monitorenter;
    try
    {
      if (this.supportedCookieSpecs == null)
        this.supportedCookieSpecs = createCookieSpecRegistry();
      CookieSpecRegistry localCookieSpecRegistry = this.supportedCookieSpecs;
      return localCookieSpecRegistry;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final CookieStore getCookieStore()
  {
    monitorenter;
    try
    {
      if (this.cookieStore == null)
        this.cookieStore = createCookieStore();
      CookieStore localCookieStore = this.cookieStore;
      return localCookieStore;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final CredentialsProvider getCredentialsProvider()
  {
    monitorenter;
    try
    {
      if (this.credsProvider == null)
        this.credsProvider = createCredentialsProvider();
      CredentialsProvider localCredentialsProvider = this.credsProvider;
      return localCredentialsProvider;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected final BasicHttpProcessor getHttpProcessor()
  {
    monitorenter;
    try
    {
      if (this.mutableProcessor == null)
        this.mutableProcessor = createHttpProcessor();
      BasicHttpProcessor localBasicHttpProcessor = this.mutableProcessor;
      return localBasicHttpProcessor;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final HttpRequestRetryHandler getHttpRequestRetryHandler()
  {
    monitorenter;
    try
    {
      if (this.retryHandler == null)
        this.retryHandler = createHttpRequestRetryHandler();
      HttpRequestRetryHandler localHttpRequestRetryHandler = this.retryHandler;
      return localHttpRequestRetryHandler;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final HttpParams getParams()
  {
    monitorenter;
    try
    {
      if (this.defaultParams == null)
        this.defaultParams = createHttpParams();
      HttpParams localHttpParams = this.defaultParams;
      return localHttpParams;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public final AuthenticationHandler getProxyAuthenticationHandler()
  {
    monitorenter;
    try
    {
      AuthenticationHandler localAuthenticationHandler = createProxyAuthenticationHandler();
      monitorexit;
      return localAuthenticationHandler;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final AuthenticationStrategy getProxyAuthenticationStrategy()
  {
    monitorenter;
    try
    {
      if (this.proxyAuthStrategy == null)
        this.proxyAuthStrategy = createProxyAuthenticationStrategy();
      AuthenticationStrategy localAuthenticationStrategy = this.proxyAuthStrategy;
      return localAuthenticationStrategy;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public final RedirectHandler getRedirectHandler()
  {
    monitorenter;
    try
    {
      RedirectHandler localRedirectHandler = createRedirectHandler();
      monitorexit;
      return localRedirectHandler;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final RedirectStrategy getRedirectStrategy()
  {
    monitorenter;
    try
    {
      if (this.redirectStrategy == null)
        this.redirectStrategy = new DefaultRedirectStrategy();
      RedirectStrategy localRedirectStrategy = this.redirectStrategy;
      return localRedirectStrategy;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final HttpRequestExecutor getRequestExecutor()
  {
    monitorenter;
    try
    {
      if (this.requestExec == null)
        this.requestExec = createRequestExecutor();
      HttpRequestExecutor localHttpRequestExecutor = this.requestExec;
      return localHttpRequestExecutor;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public HttpRequestInterceptor getRequestInterceptor(int paramInt)
  {
    monitorenter;
    try
    {
      HttpRequestInterceptor localHttpRequestInterceptor = getHttpProcessor().getRequestInterceptor(paramInt);
      monitorexit;
      return localHttpRequestInterceptor;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getRequestInterceptorCount()
  {
    monitorenter;
    try
    {
      int i = getHttpProcessor().getRequestInterceptorCount();
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public HttpResponseInterceptor getResponseInterceptor(int paramInt)
  {
    monitorenter;
    try
    {
      HttpResponseInterceptor localHttpResponseInterceptor = getHttpProcessor().getResponseInterceptor(paramInt);
      monitorexit;
      return localHttpResponseInterceptor;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getResponseInterceptorCount()
  {
    monitorenter;
    try
    {
      int i = getHttpProcessor().getResponseInterceptorCount();
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final HttpRoutePlanner getRoutePlanner()
  {
    monitorenter;
    try
    {
      if (this.routePlanner == null)
        this.routePlanner = createHttpRoutePlanner();
      HttpRoutePlanner localHttpRoutePlanner = this.routePlanner;
      return localHttpRoutePlanner;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public final AuthenticationHandler getTargetAuthenticationHandler()
  {
    monitorenter;
    try
    {
      AuthenticationHandler localAuthenticationHandler = createTargetAuthenticationHandler();
      monitorexit;
      return localAuthenticationHandler;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final AuthenticationStrategy getTargetAuthenticationStrategy()
  {
    monitorenter;
    try
    {
      if (this.targetAuthStrategy == null)
        this.targetAuthStrategy = createTargetAuthenticationStrategy();
      AuthenticationStrategy localAuthenticationStrategy = this.targetAuthStrategy;
      return localAuthenticationStrategy;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final UserTokenHandler getUserTokenHandler()
  {
    monitorenter;
    try
    {
      if (this.userTokenHandler == null)
        this.userTokenHandler = createUserTokenHandler();
      UserTokenHandler localUserTokenHandler = this.userTokenHandler;
      return localUserTokenHandler;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> paramClass)
  {
    monitorenter;
    try
    {
      getHttpProcessor().removeRequestInterceptorByClass(paramClass);
      this.protocolProcessor = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> paramClass)
  {
    monitorenter;
    try
    {
      getHttpProcessor().removeResponseInterceptorByClass(paramClass);
      this.protocolProcessor = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setAuthSchemes(AuthSchemeRegistry paramAuthSchemeRegistry)
  {
    monitorenter;
    try
    {
      this.supportedAuthSchemes = paramAuthSchemeRegistry;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setBackoffManager(BackoffManager paramBackoffManager)
  {
    monitorenter;
    try
    {
      this.backoffManager = paramBackoffManager;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setConnectionBackoffStrategy(ConnectionBackoffStrategy paramConnectionBackoffStrategy)
  {
    monitorenter;
    try
    {
      this.connectionBackoffStrategy = paramConnectionBackoffStrategy;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setCookieSpecs(CookieSpecRegistry paramCookieSpecRegistry)
  {
    monitorenter;
    try
    {
      this.supportedCookieSpecs = paramCookieSpecRegistry;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setCookieStore(CookieStore paramCookieStore)
  {
    monitorenter;
    try
    {
      this.cookieStore = paramCookieStore;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setCredentialsProvider(CredentialsProvider paramCredentialsProvider)
  {
    monitorenter;
    try
    {
      this.credsProvider = paramCredentialsProvider;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setHttpRequestRetryHandler(HttpRequestRetryHandler paramHttpRequestRetryHandler)
  {
    monitorenter;
    try
    {
      this.retryHandler = paramHttpRequestRetryHandler;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setKeepAliveStrategy(ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy)
  {
    monitorenter;
    try
    {
      this.keepAliveStrategy = paramConnectionKeepAliveStrategy;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setParams(HttpParams paramHttpParams)
  {
    monitorenter;
    try
    {
      this.defaultParams = paramHttpParams;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public void setProxyAuthenticationHandler(AuthenticationHandler paramAuthenticationHandler)
  {
    monitorenter;
    try
    {
      this.proxyAuthStrategy = new AuthenticationStrategyAdaptor(paramAuthenticationHandler);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setProxyAuthenticationStrategy(AuthenticationStrategy paramAuthenticationStrategy)
  {
    monitorenter;
    try
    {
      this.proxyAuthStrategy = paramAuthenticationStrategy;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public void setRedirectHandler(RedirectHandler paramRedirectHandler)
  {
    monitorenter;
    try
    {
      this.redirectStrategy = new DefaultRedirectStrategyAdaptor(paramRedirectHandler);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setRedirectStrategy(RedirectStrategy paramRedirectStrategy)
  {
    monitorenter;
    try
    {
      this.redirectStrategy = paramRedirectStrategy;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setReuseStrategy(ConnectionReuseStrategy paramConnectionReuseStrategy)
  {
    monitorenter;
    try
    {
      this.reuseStrategy = paramConnectionReuseStrategy;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setRoutePlanner(HttpRoutePlanner paramHttpRoutePlanner)
  {
    monitorenter;
    try
    {
      this.routePlanner = paramHttpRoutePlanner;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public void setTargetAuthenticationHandler(AuthenticationHandler paramAuthenticationHandler)
  {
    monitorenter;
    try
    {
      this.targetAuthStrategy = new AuthenticationStrategyAdaptor(paramAuthenticationHandler);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setTargetAuthenticationStrategy(AuthenticationStrategy paramAuthenticationStrategy)
  {
    monitorenter;
    try
    {
      this.targetAuthStrategy = paramAuthenticationStrategy;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setUserTokenHandler(UserTokenHandler paramUserTokenHandler)
  {
    monitorenter;
    try
    {
      this.userTokenHandler = paramUserTokenHandler;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.AbstractHttpClient
 * JD-Core Version:    0.6.0
 */