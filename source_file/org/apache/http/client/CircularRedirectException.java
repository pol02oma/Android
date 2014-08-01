package org.apache.http.client;

import org.apache.http.annotation.Immutable;

@Immutable
public class CircularRedirectException extends RedirectException
{
  private static final long serialVersionUID = 6830063487001091803L;

  public CircularRedirectException()
  {
  }

  public CircularRedirectException(String paramString)
  {
    super(paramString);
  }

  public CircularRedirectException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.CircularRedirectException
 * JD-Core Version:    0.6.0
 */