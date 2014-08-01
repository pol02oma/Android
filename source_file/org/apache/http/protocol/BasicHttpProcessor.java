package org.apache.http.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public final class BasicHttpProcessor
  implements HttpProcessor, HttpRequestInterceptorList, HttpResponseInterceptorList, Cloneable
{
  protected final List<HttpRequestInterceptor> requestInterceptors = new ArrayList();
  protected final List<HttpResponseInterceptor> responseInterceptors = new ArrayList();

  public final void addInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    addRequestInterceptor(paramHttpRequestInterceptor);
  }

  public final void addInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt)
  {
    addRequestInterceptor(paramHttpRequestInterceptor, paramInt);
  }

  public final void addInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    addResponseInterceptor(paramHttpResponseInterceptor);
  }

  public final void addInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor, int paramInt)
  {
    addResponseInterceptor(paramHttpResponseInterceptor, paramInt);
  }

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    if (paramHttpRequestInterceptor == null)
      return;
    this.requestInterceptors.add(paramHttpRequestInterceptor);
  }

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt)
  {
    if (paramHttpRequestInterceptor == null)
      return;
    this.requestInterceptors.add(paramInt, paramHttpRequestInterceptor);
  }

  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    if (paramHttpResponseInterceptor == null)
      return;
    this.responseInterceptors.add(paramHttpResponseInterceptor);
  }

  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor, int paramInt)
  {
    if (paramHttpResponseInterceptor == null)
      return;
    this.responseInterceptors.add(paramInt, paramHttpResponseInterceptor);
  }

  public void clearInterceptors()
  {
    clearRequestInterceptors();
    clearResponseInterceptors();
  }

  public void clearRequestInterceptors()
  {
    this.requestInterceptors.clear();
  }

  public void clearResponseInterceptors()
  {
    this.responseInterceptors.clear();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    BasicHttpProcessor localBasicHttpProcessor = (BasicHttpProcessor)super.clone();
    copyInterceptors(localBasicHttpProcessor);
    return localBasicHttpProcessor;
  }

  public BasicHttpProcessor copy()
  {
    BasicHttpProcessor localBasicHttpProcessor = new BasicHttpProcessor();
    copyInterceptors(localBasicHttpProcessor);
    return localBasicHttpProcessor;
  }

  protected void copyInterceptors(BasicHttpProcessor paramBasicHttpProcessor)
  {
    paramBasicHttpProcessor.requestInterceptors.clear();
    paramBasicHttpProcessor.requestInterceptors.addAll(this.requestInterceptors);
    paramBasicHttpProcessor.responseInterceptors.clear();
    paramBasicHttpProcessor.responseInterceptors.addAll(this.responseInterceptors);
  }

  public HttpRequestInterceptor getRequestInterceptor(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.requestInterceptors.size()))
      return null;
    return (HttpRequestInterceptor)this.requestInterceptors.get(paramInt);
  }

  public int getRequestInterceptorCount()
  {
    return this.requestInterceptors.size();
  }

  public HttpResponseInterceptor getResponseInterceptor(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.responseInterceptors.size()))
      return null;
    return (HttpResponseInterceptor)this.responseInterceptors.get(paramInt);
  }

  public int getResponseInterceptorCount()
  {
    return this.responseInterceptors.size();
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; i < this.requestInterceptors.size(); i++)
      ((HttpRequestInterceptor)this.requestInterceptors.get(i)).process(paramHttpRequest, paramHttpContext);
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; i < this.responseInterceptors.size(); i++)
      ((HttpResponseInterceptor)this.responseInterceptors.get(i)).process(paramHttpResponse, paramHttpContext);
  }

  public void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> paramClass)
  {
    Iterator localIterator = this.requestInterceptors.iterator();
    while (localIterator.hasNext())
    {
      if (!localIterator.next().getClass().equals(paramClass))
        continue;
      localIterator.remove();
    }
  }

  public void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> paramClass)
  {
    Iterator localIterator = this.responseInterceptors.iterator();
    while (localIterator.hasNext())
    {
      if (!localIterator.next().getClass().equals(paramClass))
        continue;
      localIterator.remove();
    }
  }

  public void setInterceptors(List<?> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List must not be null.");
    this.requestInterceptors.clear();
    this.responseInterceptors.clear();
    for (int i = 0; i < paramList.size(); i++)
    {
      Object localObject = paramList.get(i);
      if ((localObject instanceof HttpRequestInterceptor))
        addInterceptor((HttpRequestInterceptor)localObject);
      if (!(localObject instanceof HttpResponseInterceptor))
        continue;
      addInterceptor((HttpResponseInterceptor)localObject);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.BasicHttpProcessor
 * JD-Core Version:    0.6.0
 */