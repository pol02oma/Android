package org.apache.http.client.protocol;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.cookie.SetCookie2;
import org.apache.http.protocol.HttpContext;

@Immutable
public class RequestAddCookies
  implements HttpRequestInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
      return;
    CookieStore localCookieStore = (CookieStore)paramHttpContext.getAttribute("http.cookie-store");
    if (localCookieStore == null)
    {
      this.log.debug("Cookie store not specified in HTTP context");
      return;
    }
    CookieSpecRegistry localCookieSpecRegistry = (CookieSpecRegistry)paramHttpContext.getAttribute("http.cookiespec-registry");
    if (localCookieSpecRegistry == null)
    {
      this.log.debug("CookieSpec registry not specified in HTTP context");
      return;
    }
    HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    if (localHttpHost == null)
    {
      this.log.debug("Target host not set in the context");
      return;
    }
    HttpRoutedConnection localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
    if (localHttpRoutedConnection == null)
    {
      this.log.debug("HTTP connection not set in the context");
      return;
    }
    String str1 = HttpClientParams.getCookiePolicy(paramHttpRequest.getParams());
    if (this.log.isDebugEnabled())
      this.log.debug("CookieSpec selected: " + str1);
    URI localURI;
    int i;
    label278: CookieOrigin localCookieOrigin;
    CookieSpec localCookieSpec;
    ArrayList localArrayList2;
    Date localDate;
    Iterator localIterator1;
    if ((paramHttpRequest instanceof HttpUriRequest))
    {
      localURI = ((HttpUriRequest)paramHttpRequest).getURI();
      String str3 = localHttpHost.getHostName();
      i = localHttpHost.getPort();
      if (i < 0)
      {
        if (localHttpRoutedConnection.getRoute().getHopCount() != 1)
          break label552;
        i = localHttpRoutedConnection.getRemotePort();
      }
      String str4 = localURI.getPath();
      boolean bool = localHttpRoutedConnection.isSecure();
      localCookieOrigin = new CookieOrigin(str3, i, str4, bool);
      localCookieSpec = localCookieSpecRegistry.getCookieSpec(str1, paramHttpRequest.getParams());
      ArrayList localArrayList1 = new ArrayList(localCookieStore.getCookies());
      localArrayList2 = new ArrayList();
      localDate = new Date();
      localIterator1 = localArrayList1.iterator();
    }
    while (true)
    {
      Cookie localCookie2;
      while (true)
      {
        if (!localIterator1.hasNext())
          break label649;
        localCookie2 = (Cookie)localIterator1.next();
        if (localCookie2.isExpired(localDate))
          break label600;
        if (!localCookieSpec.match(localCookie2, localCookieOrigin))
          continue;
        if (this.log.isDebugEnabled())
          this.log.debug("Cookie " + localCookie2 + " match " + localCookieOrigin);
        localArrayList2.add(localCookie2);
        continue;
        try
        {
          String str2 = paramHttpRequest.getRequestLine().getUri();
          localURI = new URI(str2);
        }
        catch (URISyntaxException localURISyntaxException)
        {
          ProtocolException localProtocolException = new ProtocolException("Invalid request URI: " + paramHttpRequest.getRequestLine().getUri(), localURISyntaxException);
          throw localProtocolException;
        }
      }
      label552: String str5 = localHttpHost.getSchemeName();
      if (str5.equalsIgnoreCase("http"))
      {
        i = 80;
        break label278;
      }
      if (str5.equalsIgnoreCase("https"))
      {
        i = 443;
        break label278;
      }
      i = 0;
      break label278;
      label600: if (!this.log.isDebugEnabled())
        continue;
      this.log.debug("Cookie " + localCookie2 + " expired");
    }
    label649: if (!localArrayList2.isEmpty())
    {
      Iterator localIterator3 = localCookieSpec.formatCookies(localArrayList2).iterator();
      while (localIterator3.hasNext())
        paramHttpRequest.addHeader((Header)localIterator3.next());
    }
    int j = localCookieSpec.getVersion();
    if (j > 0)
    {
      int k = 0;
      Iterator localIterator2 = localArrayList2.iterator();
      while (localIterator2.hasNext())
      {
        Cookie localCookie1 = (Cookie)localIterator2.next();
        if ((j == localCookie1.getVersion()) && ((localCookie1 instanceof SetCookie2)))
          continue;
        k = 1;
      }
      if (k != 0)
      {
        Header localHeader = localCookieSpec.getVersionHeader();
        if (localHeader != null)
          paramHttpRequest.addHeader(localHeader);
      }
    }
    paramHttpContext.setAttribute("http.cookie-spec", localCookieSpec);
    paramHttpContext.setAttribute("http.cookie-origin", localCookieOrigin);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.protocol.RequestAddCookies
 * JD-Core Version:    0.6.0
 */