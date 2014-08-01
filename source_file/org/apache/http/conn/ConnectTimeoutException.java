package org.apache.http.conn;

import java.io.InterruptedIOException;
import org.apache.http.annotation.Immutable;

@Immutable
public class ConnectTimeoutException extends InterruptedIOException
{
  private static final long serialVersionUID = -4816682903149535989L;

  public ConnectTimeoutException()
  {
  }

  public ConnectTimeoutException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.ConnectTimeoutException
 * JD-Core Version:    0.6.0
 */