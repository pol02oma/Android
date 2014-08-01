package org.apache.http.impl.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.NonRepeatableRequestException;
import org.apache.http.client.RedirectException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.BasicRouteDirector;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRouteDirector;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.util.EntityUtils;

@NotThreadSafe
public class DefaultRequestDirector
  implements RequestDirector
{
  private final HttpAuthenticator authenticator;
  protected final ClientConnectionManager connManager;
  private int execCount;
  protected final HttpProcessor httpProcessor;
  protected final ConnectionKeepAliveStrategy keepAliveStrategy;
  private final Log log;
  protected ManagedClientConnection managedConn;
  private int maxRedirects;
  protected final HttpParams params;

  @Deprecated
  protected final AuthenticationHandler proxyAuthHandler;
  protected final AuthState proxyAuthState;
  protected final AuthenticationStrategy proxyAuthStrategy;
  private int redirectCount;

  @Deprecated
  protected final RedirectHandler redirectHandler;
  protected final RedirectStrategy redirectStrategy;
  protected final HttpRequestExecutor requestExec;
  protected final HttpRequestRetryHandler retryHandler;
  protected final ConnectionReuseStrategy reuseStrategy;
  protected final HttpRoutePlanner routePlanner;

  @Deprecated
  protected final AuthenticationHandler targetAuthHandler;
  protected final AuthState targetAuthState;
  protected final AuthenticationStrategy targetAuthStrategy;
  protected final UserTokenHandler userTokenHandler;
  private HttpHost virtualHost;

  @Deprecated
  public DefaultRequestDirector(Log paramLog, HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    this(LogFactory.getLog(DefaultRequestDirector.class), paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, paramRedirectStrategy, new AuthenticationStrategyAdaptor(paramAuthenticationHandler1), new AuthenticationStrategyAdaptor(paramAuthenticationHandler2), paramUserTokenHandler, paramHttpParams);
  }

  public DefaultRequestDirector(Log paramLog, HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationStrategy paramAuthenticationStrategy1, AuthenticationStrategy paramAuthenticationStrategy2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    if (paramLog == null)
      throw new IllegalArgumentException("Log may not be null.");
    if (paramHttpRequestExecutor == null)
      throw new IllegalArgumentException("Request executor may not be null.");
    if (paramClientConnectionManager == null)
      throw new IllegalArgumentException("Client connection manager may not be null.");
    if (paramConnectionReuseStrategy == null)
      throw new IllegalArgumentException("Connection reuse strategy may not be null.");
    if (paramConnectionKeepAliveStrategy == null)
      throw new IllegalArgumentException("Connection keep alive strategy may not be null.");
    if (paramHttpRoutePlanner == null)
      throw new IllegalArgumentException("Route planner may not be null.");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP protocol processor may not be null.");
    if (paramHttpRequestRetryHandler == null)
      throw new IllegalArgumentException("HTTP request retry handler may not be null.");
    if (paramRedirectStrategy == null)
      throw new IllegalArgumentException("Redirect strategy may not be null.");
    if (paramAuthenticationStrategy1 == null)
      throw new IllegalArgumentException("Target authentication strategy may not be null.");
    if (paramAuthenticationStrategy2 == null)
      throw new IllegalArgumentException("Proxy authentication strategy may not be null.");
    if (paramUserTokenHandler == null)
      throw new IllegalArgumentException("User token handler may not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.log = paramLog;
    this.authenticator = new HttpAuthenticator(paramLog);
    this.requestExec = paramHttpRequestExecutor;
    this.connManager = paramClientConnectionManager;
    this.reuseStrategy = paramConnectionReuseStrategy;
    this.keepAliveStrategy = paramConnectionKeepAliveStrategy;
    this.routePlanner = paramHttpRoutePlanner;
    this.httpProcessor = paramHttpProcessor;
    this.retryHandler = paramHttpRequestRetryHandler;
    this.redirectStrategy = paramRedirectStrategy;
    this.targetAuthStrategy = paramAuthenticationStrategy1;
    this.proxyAuthStrategy = paramAuthenticationStrategy2;
    this.userTokenHandler = paramUserTokenHandler;
    this.params = paramHttpParams;
    if ((paramRedirectStrategy instanceof DefaultRedirectStrategyAdaptor))
    {
      this.redirectHandler = ((DefaultRedirectStrategyAdaptor)paramRedirectStrategy).getHandler();
      if (!(paramAuthenticationStrategy1 instanceof AuthenticationStrategyAdaptor))
        break label406;
      this.targetAuthHandler = ((AuthenticationStrategyAdaptor)paramAuthenticationStrategy1).getHandler();
      label323: if (!(paramAuthenticationStrategy2 instanceof AuthenticationStrategyAdaptor))
        break label414;
    }
    label406: label414: for (this.proxyAuthHandler = ((AuthenticationStrategyAdaptor)paramAuthenticationStrategy2).getHandler(); ; this.proxyAuthHandler = null)
    {
      this.managedConn = null;
      this.execCount = 0;
      this.redirectCount = 0;
      this.targetAuthState = new AuthState();
      this.proxyAuthState = new AuthState();
      this.maxRedirects = this.params.getIntParameter("http.protocol.max-redirects", 100);
      return;
      this.redirectHandler = null;
      break;
      this.targetAuthHandler = null;
      break label323;
    }
  }

  @Deprecated
  public DefaultRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectHandler paramRedirectHandler, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    this(LogFactory.getLog(DefaultRequestDirector.class), paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, new DefaultRedirectStrategyAdaptor(paramRedirectHandler), new AuthenticationStrategyAdaptor(paramAuthenticationHandler1), new AuthenticationStrategyAdaptor(paramAuthenticationHandler2), paramUserTokenHandler, paramHttpParams);
  }

  private void abortConnection()
  {
    ManagedClientConnection localManagedClientConnection = this.managedConn;
    if (localManagedClientConnection != null)
      this.managedConn = null;
    try
    {
      localManagedClientConnection.abortConnection();
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          localManagedClientConnection.releaseConnection();
          return;
          localIOException1 = localIOException1;
          if (!this.log.isDebugEnabled())
            continue;
          this.log.debug(localIOException1.getMessage(), localIOException1);
        }
      }
      catch (IOException localIOException2)
      {
        this.log.debug("Error releasing connection", localIOException2);
      }
    }
  }

  private void tryConnect(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    int i = 0;
    while (true)
    {
      paramHttpContext.setAttribute("http.request", localRequestWrapper);
      i++;
      try
      {
        if (!this.managedConn.isOpen())
          this.managedConn.open(localHttpRoute, paramHttpContext, this.params);
        while (true)
        {
          establishRoute(localHttpRoute, paramHttpContext);
          return;
          this.managedConn.setSocketTimeout(HttpConnectionParams.getSoTimeout(this.params));
        }
      }
      catch (IOException localIOException1)
      {
      }
      try
      {
        this.managedConn.close();
        label91: if (this.retryHandler.retryRequest(localIOException1, i, paramHttpContext))
        {
          if (!this.log.isInfoEnabled())
            continue;
          this.log.info("I/O exception (" + localIOException1.getClass().getName() + ") caught when connecting to the target host: " + localIOException1.getMessage());
          if (this.log.isDebugEnabled())
            this.log.debug(localIOException1.getMessage(), localIOException1);
          this.log.info("Retrying connect");
          continue;
        }
        throw localIOException1;
      }
      catch (IOException localIOException2)
      {
        break label91;
      }
    }
  }

  private HttpResponse tryExecute(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    Object localObject = null;
    while (true)
    {
      this.execCount = (1 + this.execCount);
      localRequestWrapper.incrementExecCount();
      if (!localRequestWrapper.isRepeatable())
      {
        this.log.debug("Cannot retry non-repeatable request");
        if (localObject != null)
          throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.", (Throwable)localObject);
        throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
      }
      try
      {
        if (!this.managedConn.isOpen())
        {
          if (!localHttpRoute.isTunnelled())
          {
            this.log.debug("Reopening the direct connection.");
            this.managedConn.open(localHttpRoute, paramHttpContext, this.params);
          }
        }
        else
        {
          if (this.log.isDebugEnabled())
            this.log.debug("Attempt " + this.execCount + " to execute request");
          return this.requestExec.execute(localRequestWrapper, this.managedConn, paramHttpContext);
        }
        this.log.debug("Proxied connection. Need to start over.");
        return null;
      }
      catch (IOException localIOException1)
      {
        this.log.debug("Closing the connection.");
      }
      try
      {
        this.managedConn.close();
        label225: if (this.retryHandler.retryRequest(localIOException1, localRequestWrapper.getExecCount(), paramHttpContext))
        {
          if (this.log.isInfoEnabled())
            this.log.info("I/O exception (" + localIOException1.getClass().getName() + ") caught when processing request: " + localIOException1.getMessage());
          if (this.log.isDebugEnabled())
            this.log.debug(localIOException1.getMessage(), localIOException1);
          this.log.info("Retrying request");
          localObject = localIOException1;
          continue;
        }
        throw localIOException1;
      }
      catch (IOException localIOException2)
      {
        break label225;
      }
    }
  }

  private RequestWrapper wrapRequest(HttpRequest paramHttpRequest)
    throws ProtocolException
  {
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
      return new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest)paramHttpRequest);
    return new RequestWrapper(paramHttpRequest);
  }

  protected HttpRequest createConnectRequest(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
  {
    HttpHost localHttpHost = paramHttpRoute.getTargetHost();
    String str = localHttpHost.getHostName();
    int i = localHttpHost.getPort();
    if (i < 0)
      i = this.connManager.getSchemeRegistry().getScheme(localHttpHost.getSchemeName()).getDefaultPort();
    StringBuilder localStringBuilder = new StringBuilder(6 + str.length());
    localStringBuilder.append(str);
    localStringBuilder.append(':');
    localStringBuilder.append(Integer.toString(i));
    return new BasicHttpRequest("CONNECT", localStringBuilder.toString(), HttpProtocolParams.getVersion(this.params));
  }

  protected boolean createTunnelToProxy(HttpRoute paramHttpRoute, int paramInt, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    throw new HttpException("Proxy chains are not supported.");
  }

  protected boolean createTunnelToTarget(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpHost localHttpHost1 = paramHttpRoute.getProxyHost();
    HttpHost localHttpHost2 = paramHttpRoute.getTargetHost();
    HttpResponse localHttpResponse;
    while (true)
    {
      if (!this.managedConn.isOpen())
        this.managedConn.open(paramHttpRoute, paramHttpContext, this.params);
      HttpRequest localHttpRequest = createConnectRequest(paramHttpRoute, paramHttpContext);
      localHttpRequest.setParams(this.params);
      paramHttpContext.setAttribute("http.target_host", localHttpHost2);
      paramHttpContext.setAttribute("http.proxy_host", localHttpHost1);
      paramHttpContext.setAttribute("http.connection", this.managedConn);
      paramHttpContext.setAttribute("http.request", localHttpRequest);
      this.requestExec.preProcess(localHttpRequest, this.httpProcessor, paramHttpContext);
      localHttpResponse = this.requestExec.execute(localHttpRequest, this.managedConn, paramHttpContext);
      localHttpResponse.setParams(this.params);
      this.requestExec.postProcess(localHttpResponse, this.httpProcessor, paramHttpContext);
      if (localHttpResponse.getStatusLine().getStatusCode() < 200)
        throw new HttpException("Unexpected response to CONNECT request: " + localHttpResponse.getStatusLine());
      if (!HttpClientParams.isAuthenticating(this.params))
        continue;
      if ((!this.authenticator.isAuthenticationRequested(localHttpHost1, localHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext)) || (!this.authenticator.authenticate(localHttpHost1, localHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext)))
        break;
      if (this.reuseStrategy.keepAlive(localHttpResponse, paramHttpContext))
      {
        this.log.debug("Connection kept alive");
        EntityUtils.consume(localHttpResponse.getEntity());
        continue;
      }
      this.managedConn.close();
    }
    if (localHttpResponse.getStatusLine().getStatusCode() > 299)
    {
      HttpEntity localHttpEntity = localHttpResponse.getEntity();
      if (localHttpEntity != null)
        localHttpResponse.setEntity(new BufferedHttpEntity(localHttpEntity));
      this.managedConn.close();
      throw new TunnelRefusedException("CONNECT refused by proxy: " + localHttpResponse.getStatusLine(), localHttpResponse);
    }
    this.managedConn.markReusable();
    return false;
  }

  protected HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    if (paramHttpHost == null)
      paramHttpHost = (HttpHost)paramHttpRequest.getParams().getParameter("http.default-host");
    if (paramHttpHost == null)
      throw new IllegalStateException("Target host must not be null, or set in parameters.");
    return this.routePlanner.determineRoute(paramHttpHost, paramHttpRequest, paramHttpContext);
  }

  protected void establishRoute(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    BasicRouteDirector localBasicRouteDirector = new BasicRouteDirector();
    HttpRoute localHttpRoute = this.managedConn.getRoute();
    int i = localBasicRouteDirector.nextStep(paramHttpRoute, localHttpRoute);
    switch (i)
    {
    default:
      throw new IllegalStateException("Unknown step indicator " + i + " from RouteDirector.");
    case 1:
    case 2:
      this.managedConn.open(paramHttpRoute, paramHttpContext, this.params);
    case 0:
    case 3:
    case 4:
    case 5:
      while (i <= 0)
      {
        return;
        boolean bool2 = createTunnelToTarget(paramHttpRoute, paramHttpContext);
        this.log.debug("Tunnel to target created.");
        this.managedConn.tunnelTarget(bool2, this.params);
        continue;
        int j = -1 + localHttpRoute.getHopCount();
        boolean bool1 = createTunnelToProxy(paramHttpRoute, j, paramHttpContext);
        this.log.debug("Tunnel to proxy created.");
        this.managedConn.tunnelProxy(paramHttpRoute.getHopTarget(j), bool1, this.params);
        continue;
        this.managedConn.layerProtocol(paramHttpContext, this.params);
      }
    case -1:
    }
    throw new HttpException("Unable to establish route: planned = " + paramHttpRoute + "; current = " + localHttpRoute);
  }

  // ERROR //
  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    // Byte code:
    //   0: aload_3
    //   1: ldc_w 590
    //   4: aload_0
    //   5: getfield 160	org/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lorg/apache/http/auth/AuthState;
    //   8: invokeinterface 224 3 0
    //   13: aload_3
    //   14: ldc_w 592
    //   17: aload_0
    //   18: getfield 162	org/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lorg/apache/http/auth/AuthState;
    //   21: invokeinterface 224 3 0
    //   26: aload_0
    //   27: aload_2
    //   28: invokespecial 594	org/apache/http/impl/client/DefaultRequestDirector:wrapRequest	(Lorg/apache/http/HttpRequest;)Lorg/apache/http/impl/client/RequestWrapper;
    //   31: astore 4
    //   33: aload 4
    //   35: aload_0
    //   36: getfield 134	org/apache/http/impl/client/DefaultRequestDirector:params	Lorg/apache/http/params/HttpParams;
    //   39: invokevirtual 595	org/apache/http/impl/client/RequestWrapper:setParams	(Lorg/apache/http/params/HttpParams;)V
    //   42: aload_0
    //   43: aload_1
    //   44: aload 4
    //   46: aload_3
    //   47: invokevirtual 596	org/apache/http/impl/client/DefaultRequestDirector:determineRoute	(Lorg/apache/http/HttpHost;Lorg/apache/http/HttpRequest;Lorg/apache/http/protocol/HttpContext;)Lorg/apache/http/conn/routing/HttpRoute;
    //   50: astore 5
    //   52: aload_0
    //   53: aload 4
    //   55: invokevirtual 597	org/apache/http/impl/client/RequestWrapper:getParams	()Lorg/apache/http/params/HttpParams;
    //   58: ldc_w 599
    //   61: invokeinterface 527 2 0
    //   66: checkcast 364	org/apache/http/HttpHost
    //   69: putfield 601	org/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lorg/apache/http/HttpHost;
    //   72: aload_0
    //   73: getfield 601	org/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lorg/apache/http/HttpHost;
    //   76: ifnull +57 -> 133
    //   79: aload_0
    //   80: getfield 601	org/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lorg/apache/http/HttpHost;
    //   83: invokevirtual 370	org/apache/http/HttpHost:getPort	()I
    //   86: iconst_m1
    //   87: if_icmpne +46 -> 133
    //   90: aload_1
    //   91: invokevirtual 370	org/apache/http/HttpHost:getPort	()I
    //   94: istore 40
    //   96: iload 40
    //   98: iconst_m1
    //   99: if_icmpeq +34 -> 133
    //   102: new 364	org/apache/http/HttpHost
    //   105: dup
    //   106: aload_0
    //   107: getfield 601	org/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lorg/apache/http/HttpHost;
    //   110: invokevirtual 367	org/apache/http/HttpHost:getHostName	()Ljava/lang/String;
    //   113: iload 40
    //   115: aload_0
    //   116: getfield 601	org/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lorg/apache/http/HttpHost;
    //   119: invokevirtual 379	org/apache/http/HttpHost:getSchemeName	()Ljava/lang/String;
    //   122: invokespecial 604	org/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   125: astore 41
    //   127: aload_0
    //   128: aload 41
    //   130: putfield 601	org/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lorg/apache/http/HttpHost;
    //   133: new 208	org/apache/http/impl/client/RoutedRequest
    //   136: dup
    //   137: aload 4
    //   139: aload 5
    //   141: invokespecial 607	org/apache/http/impl/client/RoutedRequest:<init>	(Lorg/apache/http/impl/client/RequestWrapper;Lorg/apache/http/conn/routing/HttpRoute;)V
    //   144: astore 6
    //   146: iconst_0
    //   147: istore 7
    //   149: iconst_0
    //   150: istore 8
    //   152: aconst_null
    //   153: astore 9
    //   155: iload 8
    //   157: ifne +670 -> 827
    //   160: aload 6
    //   162: invokevirtual 216	org/apache/http/impl/client/RoutedRequest:getRequest	()Lorg/apache/http/impl/client/RequestWrapper;
    //   165: astore 17
    //   167: aload 6
    //   169: invokevirtual 212	org/apache/http/impl/client/RoutedRequest:getRoute	()Lorg/apache/http/conn/routing/HttpRoute;
    //   172: astore 18
    //   174: aload_3
    //   175: ldc_w 609
    //   178: invokeinterface 612 2 0
    //   183: astore 19
    //   185: aload_0
    //   186: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   189: ifnonnull +128 -> 317
    //   192: aload_0
    //   193: getfield 114	org/apache/http/impl/client/DefaultRequestDirector:connManager	Lorg/apache/http/conn/ClientConnectionManager;
    //   196: aload 18
    //   198: aload 19
    //   200: invokeinterface 616 3 0
    //   205: astore 34
    //   207: aload_2
    //   208: instanceof 618
    //   211: ifeq +14 -> 225
    //   214: aload_2
    //   215: checkcast 618	org/apache/http/client/methods/AbortableHttpRequest
    //   218: aload 34
    //   220: invokeinterface 622 2 0
    //   225: aload_0
    //   226: getfield 134	org/apache/http/impl/client/DefaultRequestDirector:params	Lorg/apache/http/params/HttpParams;
    //   229: invokestatic 626	org/apache/http/client/params/HttpClientParams:getConnectionManagerTimeout	(Lorg/apache/http/params/HttpParams;)J
    //   232: lstore 35
    //   234: aload_0
    //   235: aload 34
    //   237: lload 35
    //   239: getstatic 632	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   242: invokeinterface 638 4 0
    //   247: putfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   250: aload_0
    //   251: getfield 134	org/apache/http/impl/client/DefaultRequestDirector:params	Lorg/apache/http/params/HttpParams;
    //   254: invokestatic 641	org/apache/http/params/HttpConnectionParams:isStaleCheckingEnabled	(Lorg/apache/http/params/HttpParams;)Z
    //   257: ifeq +60 -> 317
    //   260: aload_0
    //   261: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   264: invokeinterface 227 1 0
    //   269: ifeq +48 -> 317
    //   272: aload_0
    //   273: getfield 103	org/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   276: ldc_w 643
    //   279: invokeinterface 300 2 0
    //   284: aload_0
    //   285: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   288: invokeinterface 646 1 0
    //   293: ifeq +24 -> 317
    //   296: aload_0
    //   297: getfield 103	org/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   300: ldc_w 648
    //   303: invokeinterface 300 2 0
    //   308: aload_0
    //   309: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   312: invokeinterface 248 1 0
    //   317: aload_2
    //   318: instanceof 618
    //   321: ifeq +16 -> 337
    //   324: aload_2
    //   325: checkcast 618	org/apache/http/client/methods/AbortableHttpRequest
    //   328: aload_0
    //   329: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   332: invokeinterface 652 2 0
    //   337: aload_0
    //   338: aload 6
    //   340: aload_3
    //   341: invokespecial 654	org/apache/http/impl/client/DefaultRequestDirector:tryConnect	(Lorg/apache/http/impl/client/RoutedRequest;Lorg/apache/http/protocol/HttpContext;)V
    //   344: aload 17
    //   346: invokevirtual 658	org/apache/http/impl/client/RequestWrapper:getURI	()Ljava/net/URI;
    //   349: invokevirtual 663	java/net/URI:getUserInfo	()Ljava/lang/String;
    //   352: astore 21
    //   354: aload 21
    //   356: ifnull +38 -> 394
    //   359: aload_0
    //   360: getfield 160	org/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lorg/apache/http/auth/AuthState;
    //   363: astore 22
    //   365: new 665	org/apache/http/impl/auth/BasicScheme
    //   368: dup
    //   369: invokespecial 666	org/apache/http/impl/auth/BasicScheme:<init>	()V
    //   372: astore 23
    //   374: new 668	org/apache/http/auth/UsernamePasswordCredentials
    //   377: dup
    //   378: aload 21
    //   380: invokespecial 669	org/apache/http/auth/UsernamePasswordCredentials:<init>	(Ljava/lang/String;)V
    //   383: astore 24
    //   385: aload 22
    //   387: aload 23
    //   389: aload 24
    //   391: invokevirtual 673	org/apache/http/auth/AuthState:update	(Lorg/apache/http/auth/AuthScheme;Lorg/apache/http/auth/Credentials;)V
    //   394: aload 17
    //   396: invokevirtual 676	org/apache/http/impl/client/RequestWrapper:resetHeaders	()V
    //   399: aload_0
    //   400: aload 17
    //   402: aload 18
    //   404: invokevirtual 679	org/apache/http/impl/client/DefaultRequestDirector:rewriteRequestURI	(Lorg/apache/http/impl/client/RequestWrapper;Lorg/apache/http/conn/routing/HttpRoute;)V
    //   407: aload_0
    //   408: getfield 601	org/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lorg/apache/http/HttpHost;
    //   411: astore 25
    //   413: aload 25
    //   415: ifnonnull +10 -> 425
    //   418: aload 18
    //   420: invokevirtual 362	org/apache/http/conn/routing/HttpRoute:getTargetHost	()Lorg/apache/http/HttpHost;
    //   423: astore 25
    //   425: aload 18
    //   427: invokevirtual 428	org/apache/http/conn/routing/HttpRoute:getProxyHost	()Lorg/apache/http/HttpHost;
    //   430: astore 26
    //   432: aload_3
    //   433: ldc_w 438
    //   436: aload 25
    //   438: invokeinterface 224 3 0
    //   443: aload_3
    //   444: ldc_w 440
    //   447: aload 26
    //   449: invokeinterface 224 3 0
    //   454: aload_3
    //   455: ldc_w 442
    //   458: aload_0
    //   459: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   462: invokeinterface 224 3 0
    //   467: aload_0
    //   468: getfield 112	org/apache/http/impl/client/DefaultRequestDirector:requestExec	Lorg/apache/http/protocol/HttpRequestExecutor;
    //   471: aload 17
    //   473: aload_0
    //   474: getfield 122	org/apache/http/impl/client/DefaultRequestDirector:httpProcessor	Lorg/apache/http/protocol/HttpProcessor;
    //   477: aload_3
    //   478: invokevirtual 446	org/apache/http/protocol/HttpRequestExecutor:preProcess	(Lorg/apache/http/HttpRequest;Lorg/apache/http/protocol/HttpProcessor;Lorg/apache/http/protocol/HttpContext;)V
    //   481: aload_0
    //   482: aload 6
    //   484: aload_3
    //   485: invokespecial 681	org/apache/http/impl/client/DefaultRequestDirector:tryExecute	(Lorg/apache/http/impl/client/RoutedRequest;Lorg/apache/http/protocol/HttpContext;)Lorg/apache/http/HttpResponse;
    //   488: astore 9
    //   490: aload 9
    //   492: ifnull -337 -> 155
    //   495: aload_0
    //   496: getfield 134	org/apache/http/impl/client/DefaultRequestDirector:params	Lorg/apache/http/params/HttpParams;
    //   499: astore 27
    //   501: aload 9
    //   503: aload 27
    //   505: invokeinterface 449 2 0
    //   510: aload_0
    //   511: getfield 112	org/apache/http/impl/client/DefaultRequestDirector:requestExec	Lorg/apache/http/protocol/HttpRequestExecutor;
    //   514: astore 28
    //   516: aload_0
    //   517: getfield 122	org/apache/http/impl/client/DefaultRequestDirector:httpProcessor	Lorg/apache/http/protocol/HttpProcessor;
    //   520: astore 29
    //   522: aload 28
    //   524: aload 9
    //   526: aload 29
    //   528: aload_3
    //   529: invokevirtual 453	org/apache/http/protocol/HttpRequestExecutor:postProcess	(Lorg/apache/http/HttpResponse;Lorg/apache/http/protocol/HttpProcessor;Lorg/apache/http/protocol/HttpContext;)V
    //   532: aload_0
    //   533: getfield 116	org/apache/http/impl/client/DefaultRequestDirector:reuseStrategy	Lorg/apache/http/ConnectionReuseStrategy;
    //   536: aload 9
    //   538: aload_3
    //   539: invokeinterface 486 3 0
    //   544: istore 7
    //   546: iload 7
    //   548: ifeq +115 -> 663
    //   551: aload_0
    //   552: getfield 118	org/apache/http/impl/client/DefaultRequestDirector:keepAliveStrategy	Lorg/apache/http/conn/ConnectionKeepAliveStrategy;
    //   555: aload 9
    //   557: aload_3
    //   558: invokeinterface 687 3 0
    //   563: lstore 30
    //   565: aload_0
    //   566: getfield 103	org/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   569: invokeinterface 192 1 0
    //   574: ifeq +75 -> 649
    //   577: lload 30
    //   579: lconst_0
    //   580: lcmp
    //   581: ifle +528 -> 1109
    //   584: new 259	java/lang/StringBuilder
    //   587: dup
    //   588: invokespecial 260	java/lang/StringBuilder:<init>	()V
    //   591: ldc_w 689
    //   594: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   597: lload 30
    //   599: invokevirtual 692	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   602: ldc_w 694
    //   605: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   608: getstatic 632	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   611: invokevirtual 467	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   614: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   617: astore 32
    //   619: aload_0
    //   620: getfield 103	org/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   623: new 259	java/lang/StringBuilder
    //   626: dup
    //   627: invokespecial 260	java/lang/StringBuilder:<init>	()V
    //   630: ldc_w 696
    //   633: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: aload 32
    //   638: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   641: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   644: invokeinterface 300 2 0
    //   649: aload_0
    //   650: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   653: lload 30
    //   655: getstatic 632	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   658: invokeinterface 700 4 0
    //   663: aload_0
    //   664: aload 6
    //   666: aload 9
    //   668: aload_3
    //   669: invokevirtual 704	org/apache/http/impl/client/DefaultRequestDirector:handleResponse	(Lorg/apache/http/impl/client/RoutedRequest;Lorg/apache/http/HttpResponse;Lorg/apache/http/protocol/HttpContext;)Lorg/apache/http/impl/client/RoutedRequest;
    //   672: astore 33
    //   674: aload 33
    //   676: ifnonnull +202 -> 878
    //   679: iconst_1
    //   680: istore 8
    //   682: aload_0
    //   683: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   686: ifnull -531 -> 155
    //   689: aload 19
    //   691: ifnonnull +26 -> 717
    //   694: aload_0
    //   695: getfield 132	org/apache/http/impl/client/DefaultRequestDirector:userTokenHandler	Lorg/apache/http/client/UserTokenHandler;
    //   698: aload_3
    //   699: invokeinterface 710 2 0
    //   704: astore 19
    //   706: aload_3
    //   707: ldc_w 609
    //   710: aload 19
    //   712: invokeinterface 224 3 0
    //   717: aload 19
    //   719: ifnull -564 -> 155
    //   722: aload_0
    //   723: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   726: aload 19
    //   728: invokeinterface 713 2 0
    //   733: goto -578 -> 155
    //   736: astore 13
    //   738: new 715	java/io/InterruptedIOException
    //   741: dup
    //   742: ldc_w 717
    //   745: invokespecial 718	java/io/InterruptedIOException:<init>	(Ljava/lang/String;)V
    //   748: astore 14
    //   750: aload 14
    //   752: aload 13
    //   754: invokevirtual 722	java/io/InterruptedIOException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   757: pop
    //   758: aload 14
    //   760: athrow
    //   761: astore 37
    //   763: new 715	java/io/InterruptedIOException
    //   766: dup
    //   767: invokespecial 723	java/io/InterruptedIOException:<init>	()V
    //   770: astore 38
    //   772: aload 38
    //   774: aload 37
    //   776: invokevirtual 722	java/io/InterruptedIOException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   779: pop
    //   780: aload 38
    //   782: athrow
    //   783: astore 12
    //   785: aload_0
    //   786: invokespecial 724	org/apache/http/impl/client/DefaultRequestDirector:abortConnection	()V
    //   789: aload 12
    //   791: athrow
    //   792: astore 20
    //   794: aload_0
    //   795: getfield 103	org/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   798: invokeinterface 192 1 0
    //   803: ifeq +17 -> 820
    //   806: aload_0
    //   807: getfield 103	org/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   810: aload 20
    //   812: invokevirtual 725	org/apache/http/impl/client/TunnelRefusedException:getMessage	()Ljava/lang/String;
    //   815: invokeinterface 300 2 0
    //   820: aload 20
    //   822: invokevirtual 729	org/apache/http/impl/client/TunnelRefusedException:getResponse	()Lorg/apache/http/HttpResponse;
    //   825: astore 9
    //   827: aload 9
    //   829: ifnull +28 -> 857
    //   832: aload 9
    //   834: invokeinterface 492 1 0
    //   839: ifnull +18 -> 857
    //   842: aload 9
    //   844: invokeinterface 492 1 0
    //   849: invokeinterface 734 1 0
    //   854: ifne +212 -> 1066
    //   857: iload 7
    //   859: ifeq +12 -> 871
    //   862: aload_0
    //   863: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   866: invokeinterface 515 1 0
    //   871: aload_0
    //   872: invokevirtual 735	org/apache/http/impl/client/DefaultRequestDirector:releaseConnection	()V
    //   875: aload 9
    //   877: areturn
    //   878: iload 7
    //   880: ifeq +45 -> 925
    //   883: aload 9
    //   885: invokeinterface 492 1 0
    //   890: invokestatic 498	org/apache/http/util/EntityUtils:consume	(Lorg/apache/http/HttpEntity;)V
    //   893: aload_0
    //   894: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   897: invokeinterface 515 1 0
    //   902: aload 33
    //   904: invokevirtual 212	org/apache/http/impl/client/RoutedRequest:getRoute	()Lorg/apache/http/conn/routing/HttpRoute;
    //   907: aload 6
    //   909: invokevirtual 212	org/apache/http/impl/client/RoutedRequest:getRoute	()Lorg/apache/http/conn/routing/HttpRoute;
    //   912: invokevirtual 739	org/apache/http/conn/routing/HttpRoute:equals	(Ljava/lang/Object;)Z
    //   915: ifne +202 -> 1117
    //   918: aload_0
    //   919: invokevirtual 735	org/apache/http/impl/client/DefaultRequestDirector:releaseConnection	()V
    //   922: goto +195 -> 1117
    //   925: aload_0
    //   926: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   929: invokeinterface 248 1 0
    //   934: aload_0
    //   935: getfield 162	org/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lorg/apache/http/auth/AuthState;
    //   938: invokevirtual 743	org/apache/http/auth/AuthState:getState	()Lorg/apache/http/auth/AuthProtocolState;
    //   941: getstatic 749	org/apache/http/auth/AuthProtocolState:CHALLENGED	Lorg/apache/http/auth/AuthProtocolState;
    //   944: invokevirtual 753	org/apache/http/auth/AuthProtocolState:compareTo	(Ljava/lang/Enum;)I
    //   947: ifle +47 -> 994
    //   950: aload_0
    //   951: getfield 162	org/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lorg/apache/http/auth/AuthState;
    //   954: invokevirtual 757	org/apache/http/auth/AuthState:getAuthScheme	()Lorg/apache/http/auth/AuthScheme;
    //   957: ifnull +37 -> 994
    //   960: aload_0
    //   961: getfield 162	org/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lorg/apache/http/auth/AuthState;
    //   964: invokevirtual 757	org/apache/http/auth/AuthState:getAuthScheme	()Lorg/apache/http/auth/AuthScheme;
    //   967: invokeinterface 762 1 0
    //   972: ifeq +22 -> 994
    //   975: aload_0
    //   976: getfield 103	org/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   979: ldc_w 764
    //   982: invokeinterface 300 2 0
    //   987: aload_0
    //   988: getfield 162	org/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lorg/apache/http/auth/AuthState;
    //   991: invokevirtual 767	org/apache/http/auth/AuthState:reset	()V
    //   994: aload_0
    //   995: getfield 160	org/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lorg/apache/http/auth/AuthState;
    //   998: invokevirtual 743	org/apache/http/auth/AuthState:getState	()Lorg/apache/http/auth/AuthProtocolState;
    //   1001: getstatic 749	org/apache/http/auth/AuthProtocolState:CHALLENGED	Lorg/apache/http/auth/AuthProtocolState;
    //   1004: invokevirtual 753	org/apache/http/auth/AuthProtocolState:compareTo	(Ljava/lang/Enum;)I
    //   1007: ifle -105 -> 902
    //   1010: aload_0
    //   1011: getfield 160	org/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lorg/apache/http/auth/AuthState;
    //   1014: invokevirtual 757	org/apache/http/auth/AuthState:getAuthScheme	()Lorg/apache/http/auth/AuthScheme;
    //   1017: ifnull -115 -> 902
    //   1020: aload_0
    //   1021: getfield 160	org/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lorg/apache/http/auth/AuthState;
    //   1024: invokevirtual 757	org/apache/http/auth/AuthState:getAuthScheme	()Lorg/apache/http/auth/AuthScheme;
    //   1027: invokeinterface 762 1 0
    //   1032: ifeq -130 -> 902
    //   1035: aload_0
    //   1036: getfield 103	org/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   1039: ldc_w 769
    //   1042: invokeinterface 300 2 0
    //   1047: aload_0
    //   1048: getfield 160	org/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lorg/apache/http/auth/AuthState;
    //   1051: invokevirtual 767	org/apache/http/auth/AuthState:reset	()V
    //   1054: goto -152 -> 902
    //   1057: astore 11
    //   1059: aload_0
    //   1060: invokespecial 724	org/apache/http/impl/client/DefaultRequestDirector:abortConnection	()V
    //   1063: aload 11
    //   1065: athrow
    //   1066: new 771	org/apache/http/conn/BasicManagedEntity
    //   1069: dup
    //   1070: aload 9
    //   1072: invokeinterface 492 1 0
    //   1077: aload_0
    //   1078: getfield 151	org/apache/http/impl/client/DefaultRequestDirector:managedConn	Lorg/apache/http/conn/ManagedClientConnection;
    //   1081: iload 7
    //   1083: invokespecial 774	org/apache/http/conn/BasicManagedEntity:<init>	(Lorg/apache/http/HttpEntity;Lorg/apache/http/conn/ManagedClientConnection;Z)V
    //   1086: astore 16
    //   1088: aload 9
    //   1090: aload 16
    //   1092: invokeinterface 505 2 0
    //   1097: aload 9
    //   1099: areturn
    //   1100: astore 10
    //   1102: aload_0
    //   1103: invokespecial 724	org/apache/http/impl/client/DefaultRequestDirector:abortConnection	()V
    //   1106: aload 10
    //   1108: athrow
    //   1109: ldc_w 776
    //   1112: astore 32
    //   1114: goto -495 -> 619
    //   1117: aload 33
    //   1119: astore 6
    //   1121: goto -439 -> 682
    //
    // Exception table:
    //   from	to	target	type
    //   160	225	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   225	234	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   234	250	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   250	317	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   317	337	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   337	344	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   344	354	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   359	394	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   394	413	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   418	425	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   425	490	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   495	546	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   551	577	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   584	619	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   619	649	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   649	663	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   663	674	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   682	689	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   694	717	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   722	733	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   763	783	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   794	820	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   820	827	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   832	857	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   862	871	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   871	875	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   883	902	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   902	922	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   925	994	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   994	1054	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   1066	1097	736	org/apache/http/impl/conn/ConnectionShutdownException
    //   234	250	761	java/lang/InterruptedException
    //   160	225	783	org/apache/http/HttpException
    //   225	234	783	org/apache/http/HttpException
    //   234	250	783	org/apache/http/HttpException
    //   250	317	783	org/apache/http/HttpException
    //   317	337	783	org/apache/http/HttpException
    //   337	344	783	org/apache/http/HttpException
    //   344	354	783	org/apache/http/HttpException
    //   359	394	783	org/apache/http/HttpException
    //   394	413	783	org/apache/http/HttpException
    //   418	425	783	org/apache/http/HttpException
    //   425	490	783	org/apache/http/HttpException
    //   495	546	783	org/apache/http/HttpException
    //   551	577	783	org/apache/http/HttpException
    //   584	619	783	org/apache/http/HttpException
    //   619	649	783	org/apache/http/HttpException
    //   649	663	783	org/apache/http/HttpException
    //   663	674	783	org/apache/http/HttpException
    //   682	689	783	org/apache/http/HttpException
    //   694	717	783	org/apache/http/HttpException
    //   722	733	783	org/apache/http/HttpException
    //   763	783	783	org/apache/http/HttpException
    //   794	820	783	org/apache/http/HttpException
    //   820	827	783	org/apache/http/HttpException
    //   832	857	783	org/apache/http/HttpException
    //   862	871	783	org/apache/http/HttpException
    //   871	875	783	org/apache/http/HttpException
    //   883	902	783	org/apache/http/HttpException
    //   902	922	783	org/apache/http/HttpException
    //   925	994	783	org/apache/http/HttpException
    //   994	1054	783	org/apache/http/HttpException
    //   1066	1097	783	org/apache/http/HttpException
    //   337	344	792	org/apache/http/impl/client/TunnelRefusedException
    //   160	225	1057	java/io/IOException
    //   225	234	1057	java/io/IOException
    //   234	250	1057	java/io/IOException
    //   250	317	1057	java/io/IOException
    //   317	337	1057	java/io/IOException
    //   337	344	1057	java/io/IOException
    //   344	354	1057	java/io/IOException
    //   359	394	1057	java/io/IOException
    //   394	413	1057	java/io/IOException
    //   418	425	1057	java/io/IOException
    //   425	490	1057	java/io/IOException
    //   495	546	1057	java/io/IOException
    //   551	577	1057	java/io/IOException
    //   584	619	1057	java/io/IOException
    //   619	649	1057	java/io/IOException
    //   649	663	1057	java/io/IOException
    //   663	674	1057	java/io/IOException
    //   682	689	1057	java/io/IOException
    //   694	717	1057	java/io/IOException
    //   722	733	1057	java/io/IOException
    //   763	783	1057	java/io/IOException
    //   794	820	1057	java/io/IOException
    //   820	827	1057	java/io/IOException
    //   832	857	1057	java/io/IOException
    //   862	871	1057	java/io/IOException
    //   871	875	1057	java/io/IOException
    //   883	902	1057	java/io/IOException
    //   902	922	1057	java/io/IOException
    //   925	994	1057	java/io/IOException
    //   994	1054	1057	java/io/IOException
    //   1066	1097	1057	java/io/IOException
    //   160	225	1100	java/lang/RuntimeException
    //   225	234	1100	java/lang/RuntimeException
    //   234	250	1100	java/lang/RuntimeException
    //   250	317	1100	java/lang/RuntimeException
    //   317	337	1100	java/lang/RuntimeException
    //   337	344	1100	java/lang/RuntimeException
    //   344	354	1100	java/lang/RuntimeException
    //   359	394	1100	java/lang/RuntimeException
    //   394	413	1100	java/lang/RuntimeException
    //   418	425	1100	java/lang/RuntimeException
    //   425	490	1100	java/lang/RuntimeException
    //   495	546	1100	java/lang/RuntimeException
    //   551	577	1100	java/lang/RuntimeException
    //   584	619	1100	java/lang/RuntimeException
    //   619	649	1100	java/lang/RuntimeException
    //   649	663	1100	java/lang/RuntimeException
    //   663	674	1100	java/lang/RuntimeException
    //   682	689	1100	java/lang/RuntimeException
    //   694	717	1100	java/lang/RuntimeException
    //   722	733	1100	java/lang/RuntimeException
    //   763	783	1100	java/lang/RuntimeException
    //   794	820	1100	java/lang/RuntimeException
    //   820	827	1100	java/lang/RuntimeException
    //   832	857	1100	java/lang/RuntimeException
    //   862	871	1100	java/lang/RuntimeException
    //   871	875	1100	java/lang/RuntimeException
    //   883	902	1100	java/lang/RuntimeException
    //   902	922	1100	java/lang/RuntimeException
    //   925	994	1100	java/lang/RuntimeException
    //   994	1054	1100	java/lang/RuntimeException
    //   1066	1097	1100	java/lang/RuntimeException
  }

  protected RoutedRequest handleResponse(RoutedRequest paramRoutedRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpRoute localHttpRoute1 = paramRoutedRequest.getRoute();
    RequestWrapper localRequestWrapper1 = paramRoutedRequest.getRequest();
    HttpParams localHttpParams = localRequestWrapper1.getParams();
    if (HttpClientParams.isAuthenticating(localHttpParams))
    {
      Object localObject = (HttpHost)paramHttpContext.getAttribute("http.target_host");
      if (localObject == null)
        localObject = localHttpRoute1.getTargetHost();
      if (((HttpHost)localObject).getPort() < 0)
      {
        Scheme localScheme = this.connManager.getSchemeRegistry().getScheme((HttpHost)localObject);
        HttpHost localHttpHost3 = new HttpHost(((HttpHost)localObject).getHostName(), localScheme.getDefaultPort(), ((HttpHost)localObject).getSchemeName());
        localObject = localHttpHost3;
      }
      if ((this.authenticator.isAuthenticationRequested((HttpHost)localObject, paramHttpResponse, this.targetAuthStrategy, this.targetAuthState, paramHttpContext)) && (this.authenticator.authenticate((HttpHost)localObject, paramHttpResponse, this.targetAuthStrategy, this.targetAuthState, paramHttpContext)));
      HttpHost localHttpHost2;
      do
      {
        return paramRoutedRequest;
        localHttpHost2 = localHttpRoute1.getProxyHost();
      }
      while ((this.authenticator.isAuthenticationRequested(localHttpHost2, paramHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext)) && (this.authenticator.authenticate(localHttpHost2, paramHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext)));
    }
    if ((HttpClientParams.isRedirecting(localHttpParams)) && (this.redirectStrategy.isRedirected(localRequestWrapper1, paramHttpResponse, paramHttpContext)))
    {
      if (this.redirectCount >= this.maxRedirects)
        throw new RedirectException("Maximum redirects (" + this.maxRedirects + ") exceeded");
      this.redirectCount = (1 + this.redirectCount);
      this.virtualHost = null;
      HttpUriRequest localHttpUriRequest = this.redirectStrategy.getRedirect(localRequestWrapper1, paramHttpResponse, paramHttpContext);
      localHttpUriRequest.setHeaders(localRequestWrapper1.getOriginal().getAllHeaders());
      URI localURI = localHttpUriRequest.getURI();
      if (localURI.getHost() == null)
        throw new ProtocolException("Redirect URI does not specify a valid host name: " + localURI);
      HttpHost localHttpHost1 = new HttpHost(localURI.getHost(), localURI.getPort(), localURI.getScheme());
      if (!localHttpRoute1.getTargetHost().equals(localHttpHost1))
      {
        this.log.debug("Resetting target auth state");
        this.targetAuthState.reset();
        AuthScheme localAuthScheme = this.proxyAuthState.getAuthScheme();
        if ((localAuthScheme != null) && (localAuthScheme.isConnectionBased()))
        {
          this.log.debug("Resetting proxy auth state");
          this.proxyAuthState.reset();
        }
      }
      RequestWrapper localRequestWrapper2 = wrapRequest(localHttpUriRequest);
      localRequestWrapper2.setParams(localHttpParams);
      HttpRoute localHttpRoute2 = determineRoute(localHttpHost1, localRequestWrapper2, paramHttpContext);
      RoutedRequest localRoutedRequest = new RoutedRequest(localRequestWrapper2, localHttpRoute2);
      if (this.log.isDebugEnabled())
        this.log.debug("Redirecting to '" + localURI + "' via " + localHttpRoute2);
      return localRoutedRequest;
    }
    return (RoutedRequest)null;
  }

  protected void releaseConnection()
  {
    try
    {
      this.managedConn.releaseConnection();
      this.managedConn = null;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        this.log.debug("IOException releasing connection", localIOException);
    }
  }

  protected void rewriteRequestURI(RequestWrapper paramRequestWrapper, HttpRoute paramHttpRoute)
    throws ProtocolException
  {
    try
    {
      URI localURI1 = paramRequestWrapper.getURI();
      Object localObject;
      if ((paramHttpRoute.getProxyHost() != null) && (!paramHttpRoute.isTunnelled()))
        if (!localURI1.isAbsolute())
          localObject = URIUtils.rewriteURI(localURI1, paramHttpRoute.getTargetHost(), true);
      while (true)
      {
        paramRequestWrapper.setURI((URI)localObject);
        return;
        localObject = URIUtils.rewriteURI(localURI1);
        continue;
        if (localURI1.isAbsolute())
        {
          localObject = URIUtils.rewriteURI(localURI1, null);
          continue;
        }
        URI localURI2 = URIUtils.rewriteURI(localURI1);
        localObject = localURI2;
      }
    }
    catch (URISyntaxException localURISyntaxException)
    {
    }
    throw new ProtocolException("Invalid URI: " + paramRequestWrapper.getRequestLine().getUri(), localURISyntaxException);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.DefaultRequestDirector
 * JD-Core Version:    0.6.0
 */