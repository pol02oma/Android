package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.params.HttpParams;

@Immutable
public class HttpRequestExecutor
{
  private static final void closeConnection(HttpClientConnection paramHttpClientConnection)
  {
    try
    {
      paramHttpClientConnection.close();
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  protected boolean canResponseHaveBody(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
  {
    if ("HEAD".equalsIgnoreCase(paramHttpRequest.getRequestLine().getMethod()));
    int i;
    do
    {
      return false;
      i = paramHttpResponse.getStatusLine().getStatusCode();
    }
    while ((i < 200) || (i == 204) || (i == 304) || (i == 205));
    return true;
  }

  protected HttpResponse doReceiveResponse(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpClientConnection == null)
      throw new IllegalArgumentException("HTTP connection may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    HttpResponse localHttpResponse = null;
    for (int i = 0; (localHttpResponse == null) || (i < 200); i = localHttpResponse.getStatusLine().getStatusCode())
    {
      localHttpResponse = paramHttpClientConnection.receiveResponseHeader();
      if (!canResponseHaveBody(paramHttpRequest, localHttpResponse))
        continue;
      paramHttpClientConnection.receiveResponseEntity(localHttpResponse);
    }
    return localHttpResponse;
  }

  protected HttpResponse doSendRequest(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpClientConnection == null)
      throw new IllegalArgumentException("HTTP connection may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    paramHttpContext.setAttribute("http.connection", paramHttpClientConnection);
    paramHttpContext.setAttribute("http.request_sent", Boolean.FALSE);
    paramHttpClientConnection.sendRequestHeader(paramHttpRequest);
    boolean bool1 = paramHttpRequest instanceof HttpEntityEnclosingRequest;
    HttpResponse localHttpResponse = null;
    int i;
    if (bool1)
    {
      i = 1;
      ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
      boolean bool2 = ((HttpEntityEnclosingRequest)paramHttpRequest).expectContinue();
      localHttpResponse = null;
      if (bool2)
      {
        boolean bool3 = localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0);
        localHttpResponse = null;
        if (!bool3)
        {
          paramHttpClientConnection.flush();
          boolean bool4 = paramHttpClientConnection.isResponseAvailable(paramHttpRequest.getParams().getIntParameter("http.protocol.wait-for-continue", 2000));
          localHttpResponse = null;
          if (bool4)
          {
            localHttpResponse = paramHttpClientConnection.receiveResponseHeader();
            if (canResponseHaveBody(paramHttpRequest, localHttpResponse))
              paramHttpClientConnection.receiveResponseEntity(localHttpResponse);
            int j = localHttpResponse.getStatusLine().getStatusCode();
            if (j >= 200)
              break label300;
            if (j != 100)
              throw new ProtocolException("Unexpected response: " + localHttpResponse.getStatusLine());
            localHttpResponse = null;
          }
        }
      }
    }
    while (true)
    {
      if (i != 0)
        paramHttpClientConnection.sendRequestEntity((HttpEntityEnclosingRequest)paramHttpRequest);
      paramHttpClientConnection.flush();
      paramHttpContext.setAttribute("http.request_sent", Boolean.TRUE);
      return localHttpResponse;
      label300: i = 0;
    }
  }

  public HttpResponse execute(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpClientConnection == null)
      throw new IllegalArgumentException("Client connection may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    try
    {
      Object localObject = doSendRequest(paramHttpRequest, paramHttpClientConnection, paramHttpContext);
      if (localObject == null)
      {
        HttpResponse localHttpResponse = doReceiveResponse(paramHttpRequest, paramHttpClientConnection, paramHttpContext);
        localObject = localHttpResponse;
      }
      return localObject;
    }
    catch (IOException localIOException)
    {
      closeConnection(paramHttpClientConnection);
      throw localIOException;
    }
    catch (HttpException localHttpException)
    {
      closeConnection(paramHttpClientConnection);
      throw localHttpException;
    }
    catch (RuntimeException localRuntimeException)
    {
      closeConnection(paramHttpClientConnection);
    }
    throw localRuntimeException;
  }

  public void postProcess(HttpResponse paramHttpResponse, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    paramHttpContext.setAttribute("http.response", paramHttpResponse);
    paramHttpProcessor.process(paramHttpResponse, paramHttpContext);
  }

  public void preProcess(HttpRequest paramHttpRequest, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    paramHttpContext.setAttribute("http.request", paramHttpRequest);
    paramHttpProcessor.process(paramHttpRequest, paramHttpContext);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.HttpRequestExecutor
 * JD-Core Version:    0.6.0
 */