package org.apache.http.conn;

import java.net.ConnectException;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Immutable;

@Immutable
public class HttpHostConnectException extends ConnectException
{
  private static final long serialVersionUID = -3194482710275220224L;
  private final HttpHost host;

  public HttpHostConnectException(HttpHost paramHttpHost, ConnectException paramConnectException)
  {
    super("Connection to " + paramHttpHost + " refused");
    this.host = paramHttpHost;
    initCause(paramConnectException);
  }

  public HttpHost getHost()
  {
    return this.host;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.HttpHostConnectException
 * JD-Core Version:    0.6.0
 */