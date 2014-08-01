package org.apache.http.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.StatusLine;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.impl.entity.EntityDeserializer;
import org.apache.http.impl.entity.EntitySerializer;
import org.apache.http.impl.entity.LaxContentLengthStrategy;
import org.apache.http.impl.entity.StrictContentLengthStrategy;
import org.apache.http.impl.io.DefaultHttpResponseParser;
import org.apache.http.impl.io.HttpRequestWriter;
import org.apache.http.io.EofSensor;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;

@NotThreadSafe
public abstract class AbstractHttpClientConnection
  implements HttpClientConnection
{
  private final EntityDeserializer entitydeserializer = createEntityDeserializer();
  private final EntitySerializer entityserializer = createEntitySerializer();
  private EofSensor eofSensor = null;
  private SessionInputBuffer inbuffer = null;
  private HttpConnectionMetricsImpl metrics = null;
  private SessionOutputBuffer outbuffer = null;
  private HttpMessageWriter<HttpRequest> requestWriter = null;
  private HttpMessageParser<HttpResponse> responseParser = null;

  protected abstract void assertOpen()
    throws IllegalStateException;

  protected HttpConnectionMetricsImpl createConnectionMetrics(HttpTransportMetrics paramHttpTransportMetrics1, HttpTransportMetrics paramHttpTransportMetrics2)
  {
    return new HttpConnectionMetricsImpl(paramHttpTransportMetrics1, paramHttpTransportMetrics2);
  }

  protected EntityDeserializer createEntityDeserializer()
  {
    return new EntityDeserializer(new LaxContentLengthStrategy());
  }

  protected EntitySerializer createEntitySerializer()
  {
    return new EntitySerializer(new StrictContentLengthStrategy());
  }

  protected HttpResponseFactory createHttpResponseFactory()
  {
    return new DefaultHttpResponseFactory();
  }

  protected HttpMessageWriter<HttpRequest> createRequestWriter(SessionOutputBuffer paramSessionOutputBuffer, HttpParams paramHttpParams)
  {
    return new HttpRequestWriter(paramSessionOutputBuffer, null, paramHttpParams);
  }

  protected HttpMessageParser<HttpResponse> createResponseParser(SessionInputBuffer paramSessionInputBuffer, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    return new DefaultHttpResponseParser(paramSessionInputBuffer, null, paramHttpResponseFactory, paramHttpParams);
  }

  protected void doFlush()
    throws IOException
  {
    this.outbuffer.flush();
  }

  public void flush()
    throws IOException
  {
    assertOpen();
    doFlush();
  }

  public HttpConnectionMetrics getMetrics()
  {
    return this.metrics;
  }

  protected void init(SessionInputBuffer paramSessionInputBuffer, SessionOutputBuffer paramSessionOutputBuffer, HttpParams paramHttpParams)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Input session buffer may not be null");
    if (paramSessionOutputBuffer == null)
      throw new IllegalArgumentException("Output session buffer may not be null");
    this.inbuffer = paramSessionInputBuffer;
    this.outbuffer = paramSessionOutputBuffer;
    if ((paramSessionInputBuffer instanceof EofSensor))
      this.eofSensor = ((EofSensor)paramSessionInputBuffer);
    this.responseParser = createResponseParser(paramSessionInputBuffer, createHttpResponseFactory(), paramHttpParams);
    this.requestWriter = createRequestWriter(paramSessionOutputBuffer, paramHttpParams);
    this.metrics = createConnectionMetrics(paramSessionInputBuffer.getMetrics(), paramSessionOutputBuffer.getMetrics());
  }

  protected boolean isEof()
  {
    return (this.eofSensor != null) && (this.eofSensor.isEof());
  }

  public boolean isResponseAvailable(int paramInt)
    throws IOException
  {
    assertOpen();
    try
    {
      boolean bool = this.inbuffer.isDataAvailable(paramInt);
      return bool;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
    }
    return false;
  }

  public boolean isStale()
  {
    if (!isOpen());
    do
      return true;
    while (isEof());
    try
    {
      this.inbuffer.isDataAvailable(1);
      boolean bool = isEof();
      return bool;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      return false;
    }
    catch (IOException localIOException)
    {
    }
    return true;
  }

  public void receiveResponseEntity(HttpResponse paramHttpResponse)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    assertOpen();
    paramHttpResponse.setEntity(this.entitydeserializer.deserialize(this.inbuffer, paramHttpResponse));
  }

  public HttpResponse receiveResponseHeader()
    throws HttpException, IOException
  {
    assertOpen();
    HttpResponse localHttpResponse = (HttpResponse)this.responseParser.parse();
    if (localHttpResponse.getStatusLine().getStatusCode() >= 200)
      this.metrics.incrementResponseCount();
    return localHttpResponse;
  }

  public void sendRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws HttpException, IOException
  {
    if (paramHttpEntityEnclosingRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    assertOpen();
    if (paramHttpEntityEnclosingRequest.getEntity() == null)
      return;
    this.entityserializer.serialize(this.outbuffer, paramHttpEntityEnclosingRequest, paramHttpEntityEnclosingRequest.getEntity());
  }

  public void sendRequestHeader(HttpRequest paramHttpRequest)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    assertOpen();
    this.requestWriter.write(paramHttpRequest);
    this.metrics.incrementRequestCount();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.AbstractHttpClientConnection
 * JD-Core Version:    0.6.0
 */