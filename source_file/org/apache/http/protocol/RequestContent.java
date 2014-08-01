package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;

@Immutable
public class RequestContent
  implements HttpRequestInterceptor
{
  private final boolean overwrite;

  public RequestContent()
  {
    this(false);
  }

  public RequestContent(boolean paramBoolean)
  {
    this.overwrite = paramBoolean;
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      if (!this.overwrite)
        break label83;
      paramHttpRequest.removeHeaders("Transfer-Encoding");
      paramHttpRequest.removeHeaders("Content-Length");
    }
    ProtocolVersion localProtocolVersion;
    HttpEntity localHttpEntity;
    while (true)
    {
      localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
      localHttpEntity = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if (localHttpEntity != null)
        break;
      paramHttpRequest.addHeader("Content-Length", "0");
      return;
      label83: if (paramHttpRequest.containsHeader("Transfer-Encoding"))
        throw new ProtocolException("Transfer-encoding header already present");
      if (!paramHttpRequest.containsHeader("Content-Length"))
        continue;
      throw new ProtocolException("Content-Length header already present");
    }
    if ((localHttpEntity.isChunked()) || (localHttpEntity.getContentLength() < 0L))
    {
      if (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))
        throw new ProtocolException("Chunked transfer encoding not allowed for " + localProtocolVersion);
      paramHttpRequest.addHeader("Transfer-Encoding", "chunked");
    }
    while (true)
    {
      if ((localHttpEntity.getContentType() != null) && (!paramHttpRequest.containsHeader("Content-Type")))
        paramHttpRequest.addHeader(localHttpEntity.getContentType());
      if ((localHttpEntity.getContentEncoding() == null) || (paramHttpRequest.containsHeader("Content-Encoding")))
        break;
      paramHttpRequest.addHeader(localHttpEntity.getContentEncoding());
      return;
      paramHttpRequest.addHeader("Content-Length", Long.toString(localHttpEntity.getContentLength()));
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.RequestContent
 * JD-Core Version:    0.6.0
 */