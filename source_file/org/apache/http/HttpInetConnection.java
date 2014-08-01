package org.apache.http;

import java.net.InetAddress;

public abstract interface HttpInetConnection extends HttpConnection
{
  public abstract InetAddress getLocalAddress();

  public abstract int getLocalPort();

  public abstract InetAddress getRemoteAddress();

  public abstract int getRemotePort();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpInetConnection
 * JD-Core Version:    0.6.0
 */