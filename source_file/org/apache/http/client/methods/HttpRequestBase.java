package org.apache.http.client.methods;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

@NotThreadSafe
public abstract class HttpRequestBase extends AbstractHttpMessage
  implements HttpUriRequest, AbortableHttpRequest, Cloneable
{
  private Lock abortLock = new ReentrantLock();
  private volatile boolean aborted;
  private ClientConnectionRequest connRequest;
  private ConnectionReleaseTrigger releaseTrigger;
  private URI uri;

  private void cleanup()
  {
    if (this.connRequest != null)
    {
      this.connRequest.abortRequest();
      this.connRequest = null;
    }
    if (this.releaseTrigger != null);
    try
    {
      this.releaseTrigger.abortConnection();
      label37: this.releaseTrigger = null;
      return;
    }
    catch (IOException localIOException)
    {
      break label37;
    }
  }

  public void abort()
  {
    if (this.aborted)
      return;
    this.abortLock.lock();
    try
    {
      this.aborted = true;
      cleanup();
      return;
    }
    finally
    {
      this.abortLock.unlock();
    }
    throw localObject;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    HttpRequestBase localHttpRequestBase = (HttpRequestBase)super.clone();
    localHttpRequestBase.abortLock = new ReentrantLock();
    localHttpRequestBase.aborted = false;
    localHttpRequestBase.releaseTrigger = null;
    localHttpRequestBase.connRequest = null;
    localHttpRequestBase.headergroup = ((HeaderGroup)CloneUtils.clone(this.headergroup));
    localHttpRequestBase.params = ((HttpParams)CloneUtils.clone(this.params));
    return localHttpRequestBase;
  }

  public abstract String getMethod();

  public ProtocolVersion getProtocolVersion()
  {
    return HttpProtocolParams.getVersion(getParams());
  }

  public RequestLine getRequestLine()
  {
    String str1 = getMethod();
    ProtocolVersion localProtocolVersion = getProtocolVersion();
    URI localURI = getURI();
    String str2 = null;
    if (localURI != null)
      str2 = localURI.toASCIIString();
    if ((str2 == null) || (str2.length() == 0))
      str2 = "/";
    return new BasicRequestLine(str1, str2, localProtocolVersion);
  }

  public URI getURI()
  {
    return this.uri;
  }

  public boolean isAborted()
  {
    return this.aborted;
  }

  public void releaseConnection()
  {
    reset();
  }

  public void reset()
  {
    this.abortLock.lock();
    try
    {
      cleanup();
      this.aborted = false;
      return;
    }
    finally
    {
      this.abortLock.unlock();
    }
    throw localObject;
  }

  public void setConnectionRequest(ClientConnectionRequest paramClientConnectionRequest)
    throws IOException
  {
    if (this.aborted)
      throw new IOException("Request already aborted");
    this.abortLock.lock();
    try
    {
      this.connRequest = paramClientConnectionRequest;
      return;
    }
    finally
    {
      this.abortLock.unlock();
    }
    throw localObject;
  }

  public void setReleaseTrigger(ConnectionReleaseTrigger paramConnectionReleaseTrigger)
    throws IOException
  {
    if (this.aborted)
      throw new IOException("Request already aborted");
    this.abortLock.lock();
    try
    {
      this.releaseTrigger = paramConnectionReleaseTrigger;
      return;
    }
    finally
    {
      this.abortLock.unlock();
    }
    throw localObject;
  }

  public void setURI(URI paramURI)
  {
    this.uri = paramURI;
  }

  public String toString()
  {
    return getMethod() + " " + getURI() + " " + getProtocolVersion();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.methods.HttpRequestBase
 * JD-Core Version:    0.6.0
 */