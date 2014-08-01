package org.apache.http.protocol;

import java.io.IOException;
import java.net.InetAddress;
import org.apache.http.HttpConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpInetConnection;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;

@Immutable
public class RequestTargetHost
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
    if ((paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) && (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0)));
    HttpHost localHttpHost;
    while (true)
    {
      do
        return;
      while (paramHttpRequest.containsHeader("Host"));
      localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
      if (localHttpHost != null)
        break;
      HttpConnection localHttpConnection = (HttpConnection)paramHttpContext.getAttribute("http.connection");
      if ((localHttpConnection instanceof HttpInetConnection))
      {
        InetAddress localInetAddress = ((HttpInetConnection)localHttpConnection).getRemoteAddress();
        int i = ((HttpInetConnection)localHttpConnection).getRemotePort();
        if (localInetAddress != null)
          localHttpHost = new HttpHost(localInetAddress.getHostName(), i);
      }
      if (localHttpHost != null)
        break;
      if (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))
        continue;
      throw new ProtocolException("Target host missing");
    }
    paramHttpRequest.addHeader("Host", localHttpHost.toHostString());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.RequestTargetHost
 * JD-Core Version:    0.6.0
 */