package org.apache.http.conn;

import org.apache.http.annotation.Immutable;

@Immutable
public class ConnectionPoolTimeoutException extends ConnectTimeoutException
{
  private static final long serialVersionUID = -7898874842020245128L;

  public ConnectionPoolTimeoutException()
  {
  }

  public ConnectionPoolTimeoutException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.ConnectionPoolTimeoutException
 * JD-Core Version:    0.6.0
 */