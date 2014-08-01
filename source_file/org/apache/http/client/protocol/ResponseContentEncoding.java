package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.protocol.HttpContext;

@Immutable
public class ResponseContentEncoding
  implements HttpResponseInterceptor
{
  public static final String UNCOMPRESSED = "http.client.response.uncompressed";

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    HeaderElement localHeaderElement;
    String str;
    if (localHttpEntity != null)
    {
      Header localHeader = localHttpEntity.getContentEncoding();
      if (localHeader != null)
      {
        HeaderElement[] arrayOfHeaderElement = localHeader.getElements();
        if (arrayOfHeaderElement.length < 0)
        {
          localHeaderElement = arrayOfHeaderElement[0];
          str = localHeaderElement.getName().toLowerCase(Locale.US);
          if ((!"gzip".equals(str)) && (!"x-gzip".equals(str)))
            break label116;
          paramHttpResponse.setEntity(new GzipDecompressingEntity(paramHttpResponse.getEntity()));
          if (paramHttpContext != null)
            paramHttpContext.setAttribute("http.client.response.uncompressed", Boolean.valueOf(true));
        }
      }
    }
    label116: 
    do
      while (true)
      {
        return;
        if (!"deflate".equals(str))
          break;
        paramHttpResponse.setEntity(new DeflateDecompressingEntity(paramHttpResponse.getEntity()));
        if (paramHttpContext == null)
          continue;
        paramHttpContext.setAttribute("http.client.response.uncompressed", Boolean.valueOf(true));
        return;
      }
    while ("identity".equals(str));
    throw new HttpException("Unsupported Content-Coding: " + localHeaderElement.getName());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.protocol.ResponseContentEncoding
 * JD-Core Version:    0.6.0
 */