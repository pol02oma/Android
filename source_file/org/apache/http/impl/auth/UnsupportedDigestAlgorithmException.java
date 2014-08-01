package org.apache.http.impl.auth;

import org.apache.http.annotation.Immutable;

@Immutable
public class UnsupportedDigestAlgorithmException extends RuntimeException
{
  private static final long serialVersionUID = 319558534317118022L;

  public UnsupportedDigestAlgorithmException()
  {
  }

  public UnsupportedDigestAlgorithmException(String paramString)
  {
    super(paramString);
  }

  public UnsupportedDigestAlgorithmException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.auth.UnsupportedDigestAlgorithmException
 * JD-Core Version:    0.6.0
 */