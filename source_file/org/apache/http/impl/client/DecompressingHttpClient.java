package org.apache.http.impl.client;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.RequestAcceptEncoding;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class DecompressingHttpClient
  implements HttpClient
{
  private HttpRequestInterceptor acceptEncodingInterceptor;
  private HttpClient backend;
  private HttpResponseInterceptor contentEncodingInterceptor;

  public DecompressingHttpClient(HttpClient paramHttpClient)
  {
    this(paramHttpClient, new RequestAcceptEncoding(), new ResponseContentEncoding());
  }

  DecompressingHttpClient(HttpClient paramHttpClient, HttpRequestInterceptor paramHttpRequestInterceptor, HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    this.backend = paramHttpClient;
    this.acceptEncodingInterceptor = paramHttpRequestInterceptor;
    this.contentEncodingInterceptor = paramHttpResponseInterceptor;
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpHost, paramHttpRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    HttpResponse localHttpResponse = execute(paramHttpHost, paramHttpRequest, paramHttpContext);
    try
    {
      Object localObject2 = paramResponseHandler.handleResponse(localHttpResponse);
      HttpEntity localHttpEntity2;
      return localObject2;
    }
    finally
    {
      HttpEntity localHttpEntity1 = localHttpResponse.getEntity();
      if (localHttpEntity1 != null)
        EntityUtils.consume(localHttpEntity1);
    }
    throw localObject1;
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return execute(getHttpHost(paramHttpUriRequest), paramHttpUriRequest, paramResponseHandler);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return execute(getHttpHost(paramHttpUriRequest), paramHttpUriRequest, paramResponseHandler, paramHttpContext);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpHost, paramHttpRequest, (HttpContext)null);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    if (paramHttpContext == null);
    HttpResponse localHttpResponse;
    try
    {
      paramHttpContext = new BasicHttpContext();
      if ((paramHttpRequest instanceof HttpEntityEnclosingRequest));
      for (Object localObject = new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest)paramHttpRequest); ; localObject = new RequestWrapper(paramHttpRequest))
      {
        this.acceptEncodingInterceptor.process((HttpRequest)localObject, paramHttpContext);
        localHttpResponse = this.backend.execute(paramHttpHost, (HttpRequest)localObject, paramHttpContext);
        this.contentEncodingInterceptor.process(localHttpResponse, paramHttpContext);
        if (!Boolean.TRUE.equals(paramHttpContext.getAttribute("http.client.response.uncompressed")))
          break;
        localHttpResponse.removeHeaders("Content-Length");
        localHttpResponse.removeHeaders("Content-Encoding");
        localHttpResponse.removeHeaders("Content-MD5");
        return localHttpResponse;
      }
    }
    catch (HttpException localHttpException)
    {
      throw new ClientProtocolException(localHttpException);
    }
    return (HttpResponse)localHttpResponse;
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException, ClientProtocolException
  {
    return execute(getHttpHost(paramHttpUriRequest), paramHttpUriRequest, (HttpContext)null);
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return execute(getHttpHost(paramHttpUriRequest), paramHttpUriRequest, paramHttpContext);
  }

  public ClientConnectionManager getConnectionManager()
  {
    return this.backend.getConnectionManager();
  }

  HttpHost getHttpHost(HttpUriRequest paramHttpUriRequest)
  {
    return URIUtils.extractHost(paramHttpUriRequest.getURI());
  }

  public HttpParams getParams()
  {
    return this.backend.getParams();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.DecompressingHttpClient
 * JD-Core Version:    0.6.0
 */