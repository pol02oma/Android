package org.apache.http.impl.client;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSession;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.RequestClientConnControl;
import org.apache.http.client.protocol.RequestProxyAuthentication;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

public class ProxyClient
{
  private final AuthSchemeRegistry authSchemeRegistry;
  private final HttpAuthenticator authenticator;
  private final HttpProcessor httpProcessor;
  private final HttpParams params;
  private final AuthState proxyAuthState;
  private final ProxyAuthenticationStrategy proxyAuthStrategy;
  private final HttpRequestExecutor requestExec;
  private final ConnectionReuseStrategy reuseStrategy;

  public ProxyClient()
  {
    this(new BasicHttpParams());
  }

  public ProxyClient(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    HttpRequestInterceptor[] arrayOfHttpRequestInterceptor = new HttpRequestInterceptor[5];
    arrayOfHttpRequestInterceptor[0] = new RequestContent();
    arrayOfHttpRequestInterceptor[1] = new RequestTargetHost();
    arrayOfHttpRequestInterceptor[2] = new RequestClientConnControl();
    arrayOfHttpRequestInterceptor[3] = new RequestUserAgent();
    arrayOfHttpRequestInterceptor[4] = new RequestProxyAuthentication();
    this.httpProcessor = new ImmutableHttpProcessor(arrayOfHttpRequestInterceptor);
    this.requestExec = new HttpRequestExecutor();
    this.proxyAuthStrategy = new ProxyAuthenticationStrategy();
    this.authenticator = new HttpAuthenticator();
    this.proxyAuthState = new AuthState();
    this.authSchemeRegistry = new AuthSchemeRegistry();
    this.authSchemeRegistry.register("Basic", new BasicSchemeFactory());
    this.authSchemeRegistry.register("Digest", new DigestSchemeFactory());
    this.authSchemeRegistry.register("NTLM", new NTLMSchemeFactory());
    this.authSchemeRegistry.register("negotiate", new SPNegoSchemeFactory());
    this.authSchemeRegistry.register("Kerberos", new KerberosSchemeFactory());
    this.reuseStrategy = new DefaultConnectionReuseStrategy();
    this.params = paramHttpParams;
  }

  public AuthSchemeRegistry getAuthSchemeRegistry()
  {
    return this.authSchemeRegistry;
  }

  public HttpParams getParams()
  {
    return this.params;
  }

  public Socket tunnel(HttpHost paramHttpHost1, HttpHost paramHttpHost2, Credentials paramCredentials)
    throws IOException, HttpException
  {
    ProxyConnection localProxyConnection = new ProxyConnection(new HttpRoute(paramHttpHost1));
    BasicHttpContext localBasicHttpContext = new BasicHttpContext();
    HttpResponse localHttpResponse;
    while (true)
    {
      if (!localProxyConnection.isOpen())
        localProxyConnection.bind(new Socket(paramHttpHost1.getHostName(), paramHttpHost1.getPort()), this.params);
      String str = paramHttpHost2.getHostName();
      int i = paramHttpHost2.getPort();
      if (i < 0)
        i = 80;
      StringBuilder localStringBuilder = new StringBuilder(6 + str.length());
      localStringBuilder.append(str);
      localStringBuilder.append(':');
      localStringBuilder.append(Integer.toString(i));
      BasicHttpRequest localBasicHttpRequest = new BasicHttpRequest("CONNECT", localStringBuilder.toString(), HttpProtocolParams.getVersion(this.params));
      localBasicHttpRequest.setParams(this.params);
      BasicCredentialsProvider localBasicCredentialsProvider = new BasicCredentialsProvider();
      localBasicCredentialsProvider.setCredentials(new AuthScope(paramHttpHost1), paramCredentials);
      localBasicHttpContext.setAttribute("http.target_host", paramHttpHost2);
      localBasicHttpContext.setAttribute("http.proxy_host", paramHttpHost1);
      localBasicHttpContext.setAttribute("http.connection", localProxyConnection);
      localBasicHttpContext.setAttribute("http.request", localBasicHttpRequest);
      localBasicHttpContext.setAttribute("http.auth.proxy-scope", this.proxyAuthState);
      localBasicHttpContext.setAttribute("http.auth.credentials-provider", localBasicCredentialsProvider);
      localBasicHttpContext.setAttribute("http.authscheme-registry", this.authSchemeRegistry);
      this.requestExec.preProcess(localBasicHttpRequest, this.httpProcessor, localBasicHttpContext);
      localHttpResponse = this.requestExec.execute(localBasicHttpRequest, localProxyConnection, localBasicHttpContext);
      localHttpResponse.setParams(this.params);
      this.requestExec.postProcess(localHttpResponse, this.httpProcessor, localBasicHttpContext);
      if (localHttpResponse.getStatusLine().getStatusCode() < 200)
        throw new HttpException("Unexpected response to CONNECT request: " + localHttpResponse.getStatusLine());
      if (!HttpClientParams.isAuthenticating(this.params))
        continue;
      if ((!this.authenticator.isAuthenticationRequested(paramHttpHost1, localHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, localBasicHttpContext)) || (!this.authenticator.authenticate(paramHttpHost1, localHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, localBasicHttpContext)))
        break;
      if (this.reuseStrategy.keepAlive(localHttpResponse, localBasicHttpContext))
      {
        EntityUtils.consume(localHttpResponse.getEntity());
        continue;
      }
      localProxyConnection.close();
    }
    if (localHttpResponse.getStatusLine().getStatusCode() > 299)
    {
      HttpEntity localHttpEntity = localHttpResponse.getEntity();
      if (localHttpEntity != null)
        localHttpResponse.setEntity(new BufferedHttpEntity(localHttpEntity));
      localProxyConnection.close();
      throw new TunnelRefusedException("CONNECT refused by proxy: " + localHttpResponse.getStatusLine(), localHttpResponse);
    }
    return localProxyConnection.getSocket();
  }

  static class ProxyConnection extends DefaultHttpClientConnection
    implements HttpRoutedConnection
  {
    private final HttpRoute route;

    ProxyConnection(HttpRoute paramHttpRoute)
    {
      this.route = paramHttpRoute;
    }

    public HttpRoute getRoute()
    {
      return this.route;
    }

    public SSLSession getSSLSession()
    {
      return null;
    }

    public Socket getSocket()
    {
      return super.getSocket();
    }

    public boolean isSecure()
    {
      return false;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.ProxyClient
 * JD-Core Version:    0.6.0
 */