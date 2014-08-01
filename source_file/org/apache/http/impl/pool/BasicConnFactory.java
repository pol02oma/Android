package org.apache.http.impl.pool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Immutable;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.pool.ConnFactory;

@Immutable
public class BasicConnFactory
  implements ConnFactory<HttpHost, HttpClientConnection>
{
  private final HttpParams params;
  private final SSLSocketFactory sslfactory;

  public BasicConnFactory(SSLSocketFactory paramSSLSocketFactory, HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP params may not be null");
    this.sslfactory = paramSSLSocketFactory;
    this.params = paramHttpParams;
  }

  public BasicConnFactory(HttpParams paramHttpParams)
  {
    this(null, paramHttpParams);
  }

  protected HttpClientConnection create(Socket paramSocket, HttpParams paramHttpParams)
    throws IOException
  {
    DefaultHttpClientConnection localDefaultHttpClientConnection = new DefaultHttpClientConnection();
    localDefaultHttpClientConnection.bind(paramSocket, paramHttpParams);
    return localDefaultHttpClientConnection;
  }

  public HttpClientConnection create(HttpHost paramHttpHost)
    throws IOException
  {
    String str = paramHttpHost.getSchemeName();
    boolean bool = "http".equalsIgnoreCase(str);
    Socket localSocket = null;
    if (bool)
      localSocket = new Socket();
    if (("https".equalsIgnoreCase(str)) && (this.sslfactory != null))
      localSocket = this.sslfactory.createSocket();
    if (localSocket == null)
      throw new IOException(str + " scheme is not supported");
    int i = HttpConnectionParams.getConnectionTimeout(this.params);
    localSocket.setSoTimeout(HttpConnectionParams.getSoTimeout(this.params));
    localSocket.connect(new InetSocketAddress(paramHttpHost.getHostName(), paramHttpHost.getPort()), i);
    return create(localSocket, this.params);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.pool.BasicConnFactory
 * JD-Core Version:    0.6.0
 */