package org.apache.http.impl;

import java.util.Locale;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultHttpResponseFactory
  implements HttpResponseFactory
{
  protected final ReasonPhraseCatalog reasonCatalog;

  public DefaultHttpResponseFactory()
  {
    this(EnglishReasonPhraseCatalog.INSTANCE);
  }

  public DefaultHttpResponseFactory(ReasonPhraseCatalog paramReasonPhraseCatalog)
  {
    if (paramReasonPhraseCatalog == null)
      throw new IllegalArgumentException("Reason phrase catalog must not be null.");
    this.reasonCatalog = paramReasonPhraseCatalog;
  }

  protected Locale determineLocale(HttpContext paramHttpContext)
  {
    return Locale.getDefault();
  }

  public HttpResponse newHttpResponse(ProtocolVersion paramProtocolVersion, int paramInt, HttpContext paramHttpContext)
  {
    if (paramProtocolVersion == null)
      throw new IllegalArgumentException("HTTP version may not be null");
    Locale localLocale = determineLocale(paramHttpContext);
    return new BasicHttpResponse(new BasicStatusLine(paramProtocolVersion, paramInt, this.reasonCatalog.getReason(paramInt, localLocale)), this.reasonCatalog, localLocale);
  }

  public HttpResponse newHttpResponse(StatusLine paramStatusLine, HttpContext paramHttpContext)
  {
    if (paramStatusLine == null)
      throw new IllegalArgumentException("Status line may not be null");
    Locale localLocale = determineLocale(paramHttpContext);
    return new BasicHttpResponse(paramStatusLine, this.reasonCatalog, localLocale);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.DefaultHttpResponseFactory
 * JD-Core Version:    0.6.0
 */