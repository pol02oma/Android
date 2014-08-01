package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.ThreadSafe;

@ThreadSafe
public final class ImmutableHttpProcessor
  implements HttpProcessor
{
  private final HttpRequestInterceptor[] requestInterceptors;
  private final HttpResponseInterceptor[] responseInterceptors;

  public ImmutableHttpProcessor(HttpRequestInterceptorList paramHttpRequestInterceptorList, HttpResponseInterceptorList paramHttpResponseInterceptorList)
  {
    if (paramHttpRequestInterceptorList != null)
    {
      int k = paramHttpRequestInterceptorList.getRequestInterceptorCount();
      this.requestInterceptors = new HttpRequestInterceptor[k];
      for (int m = 0; m < k; m++)
        this.requestInterceptors[m] = paramHttpRequestInterceptorList.getRequestInterceptor(m);
    }
    this.requestInterceptors = new HttpRequestInterceptor[0];
    if (paramHttpResponseInterceptorList != null)
    {
      int i = paramHttpResponseInterceptorList.getResponseInterceptorCount();
      this.responseInterceptors = new HttpResponseInterceptor[i];
      for (int j = 0; j < i; j++)
        this.responseInterceptors[j] = paramHttpResponseInterceptorList.getResponseInterceptor(j);
    }
    this.responseInterceptors = new HttpResponseInterceptor[0];
  }

  public ImmutableHttpProcessor(HttpRequestInterceptor[] paramArrayOfHttpRequestInterceptor)
  {
    this(paramArrayOfHttpRequestInterceptor, null);
  }

  public ImmutableHttpProcessor(HttpRequestInterceptor[] paramArrayOfHttpRequestInterceptor, HttpResponseInterceptor[] paramArrayOfHttpResponseInterceptor)
  {
    if (paramArrayOfHttpRequestInterceptor != null)
    {
      int k = paramArrayOfHttpRequestInterceptor.length;
      this.requestInterceptors = new HttpRequestInterceptor[k];
      for (int m = 0; m < k; m++)
        this.requestInterceptors[m] = paramArrayOfHttpRequestInterceptor[m];
    }
    this.requestInterceptors = new HttpRequestInterceptor[0];
    if (paramArrayOfHttpResponseInterceptor != null)
    {
      int i = paramArrayOfHttpResponseInterceptor.length;
      this.responseInterceptors = new HttpResponseInterceptor[i];
      for (int j = 0; j < i; j++)
        this.responseInterceptors[j] = paramArrayOfHttpResponseInterceptor[j];
    }
    this.responseInterceptors = new HttpResponseInterceptor[0];
  }

  public ImmutableHttpProcessor(HttpResponseInterceptor[] paramArrayOfHttpResponseInterceptor)
  {
    this(null, paramArrayOfHttpResponseInterceptor);
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; i < this.requestInterceptors.length; i++)
      this.requestInterceptors[i].process(paramHttpRequest, paramHttpContext);
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; i < this.responseInterceptors.length; i++)
      this.responseInterceptors[i].process(paramHttpResponse, paramHttpContext);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.ImmutableHttpProcessor
 * JD-Core Version:    0.6.0
 */