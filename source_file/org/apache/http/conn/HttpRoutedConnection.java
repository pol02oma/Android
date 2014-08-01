package org.apache.http.conn;

import javax.net.ssl.SSLSession;
import org.apache.http.HttpInetConnection;
import org.apache.http.conn.routing.HttpRoute;

public abstract interface HttpRoutedConnection extends HttpInetConnection
{
  public abstract HttpRoute getRoute();

  public abstract SSLSession getSSLSession();

  public abstract boolean isSecure();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.HttpRoutedConnection
 * JD-Core Version:    0.6.0
 */