package org.apache.http.conn.ssl;

import org.apache.http.annotation.Immutable;

@Immutable
public class AllowAllHostnameVerifier extends AbstractVerifier
{
  public final String toString()
  {
    return "ALLOW_ALL";
  }

  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.ssl.AllowAllHostnameVerifier
 * JD-Core Version:    0.6.0
 */