package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpContext;

@Immutable
public class RequestClientConnControl
  implements HttpRequestInterceptor
{
  private static final String PROXY_CONN_DIRECTIVE = "Proxy-Connection";
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
      paramHttpRequest.setHeader("Proxy-Connection", "Keep-Alive");
    HttpRoute localHttpRoute;
    do
    {
      return;
      HttpRoutedConnection localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
      if (localHttpRoutedConnection == null)
      {
        this.log.debug("HTTP connection not set in the context");
        return;
      }
      localHttpRoute = localHttpRoutedConnection.getRoute();
      if (((localHttpRoute.getHopCount() != 1) && (!localHttpRoute.isTunnelled())) || (paramHttpRequest.containsHeader("Connection")))
        continue;
      paramHttpRequest.addHeader("Connection", "Keep-Alive");
    }
    while ((localHttpRoute.getHopCount() != 2) || (localHttpRoute.isTunnelled()) || (paramHttpRequest.containsHeader("Proxy-Connection")));
    paramHttpRequest.addHeader("Proxy-Connection", "Keep-Alive");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.protocol.RequestClientConnControl
 * JD-Core Version:    0.6.0
 */