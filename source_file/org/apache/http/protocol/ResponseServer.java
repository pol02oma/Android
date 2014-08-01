package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.Immutable;
import org.apache.http.params.HttpParams;

@Immutable
public class ResponseServer
  implements HttpResponseInterceptor
{
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (!paramHttpResponse.containsHeader("Server"))
    {
      String str = (String)paramHttpResponse.getParams().getParameter("http.origin-server");
      if (str != null)
        paramHttpResponse.addHeader("Server", str);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.ResponseServer
 * JD-Core Version:    0.6.0
 */