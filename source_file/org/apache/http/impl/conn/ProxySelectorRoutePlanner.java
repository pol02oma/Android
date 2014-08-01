package org.apache.http.impl.conn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.protocol.HttpContext;

@NotThreadSafe
public class ProxySelectorRoutePlanner
  implements HttpRoutePlanner
{
  protected ProxySelector proxySelector;
  protected final SchemeRegistry schemeRegistry;

  public ProxySelectorRoutePlanner(SchemeRegistry paramSchemeRegistry, ProxySelector paramProxySelector)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("SchemeRegistry must not be null.");
    this.schemeRegistry = paramSchemeRegistry;
    this.proxySelector = paramProxySelector;
  }

  protected Proxy chooseProxy(List<Proxy> paramList, HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    if ((paramList == null) || (paramList.isEmpty()))
      throw new IllegalArgumentException("Proxy list must not be empty.");
    Object localObject = null;
    int i = 0;
    if ((localObject == null) && (i < paramList.size()))
    {
      Proxy localProxy = (Proxy)paramList.get(i);
      switch (1.$SwitchMap$java$net$Proxy$Type[localProxy.type().ordinal()])
      {
      default:
      case 1:
      case 2:
      }
      while (true)
      {
        i++;
        break;
        localObject = localProxy;
      }
    }
    if (localObject == null)
      localObject = Proxy.NO_PROXY;
    return (Proxy)localObject;
  }

  protected HttpHost determineProxy(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    ProxySelector localProxySelector = this.proxySelector;
    if (localProxySelector == null)
      localProxySelector = ProxySelector.getDefault();
    if (localProxySelector == null);
    Proxy localProxy;
    while (true)
    {
      return null;
      try
      {
        URI localURI = new URI(paramHttpHost.toURI());
        localProxy = chooseProxy(localProxySelector.select(localURI), paramHttpHost, paramHttpRequest, paramHttpContext);
        if (localProxy.type() != Proxy.Type.HTTP)
          continue;
        if ((localProxy.address() instanceof InetSocketAddress))
          break;
        throw new HttpException("Unable to handle non-Inet proxy address: " + localProxy.address());
      }
      catch (URISyntaxException localURISyntaxException)
      {
        throw new HttpException("Cannot convert host to URI: " + paramHttpHost, localURISyntaxException);
      }
    }
    InetSocketAddress localInetSocketAddress = (InetSocketAddress)localProxy.address();
    return new HttpHost(getHost(localInetSocketAddress), localInetSocketAddress.getPort());
  }

  public HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalStateException("Request must not be null.");
    HttpRoute localHttpRoute1 = ConnRouteParams.getForcedRoute(paramHttpRequest.getParams());
    if (localHttpRoute1 != null)
      return localHttpRoute1;
    if (paramHttpHost == null)
      throw new IllegalStateException("Target host must not be null.");
    InetAddress localInetAddress = ConnRouteParams.getLocalAddress(paramHttpRequest.getParams());
    HttpHost localHttpHost = determineProxy(paramHttpHost, paramHttpRequest, paramHttpContext);
    boolean bool = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName()).isLayered();
    if (localHttpHost == null);
    for (HttpRoute localHttpRoute2 = new HttpRoute(paramHttpHost, localInetAddress, bool); ; localHttpRoute2 = new HttpRoute(paramHttpHost, localInetAddress, localHttpHost, bool))
      return localHttpRoute2;
  }

  protected String getHost(InetSocketAddress paramInetSocketAddress)
  {
    if (paramInetSocketAddress.isUnresolved())
      return paramInetSocketAddress.getHostName();
    return paramInetSocketAddress.getAddress().getHostAddress();
  }

  public ProxySelector getProxySelector()
  {
    return this.proxySelector;
  }

  public void setProxySelector(ProxySelector paramProxySelector)
  {
    this.proxySelector = paramProxySelector;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.ProxySelectorRoutePlanner
 * JD-Core Version:    0.6.0
 */