package org.apache.http.client;

import org.apache.http.ProtocolException;
import org.apache.http.annotation.Immutable;

@Immutable
public class RedirectException extends ProtocolException
{
  private static final long serialVersionUID = 4418824536372559326L;

  public RedirectException()
  {
  }

  public RedirectException(String paramString)
  {
    super(paramString);
  }

  public RedirectException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.RedirectException
 * JD-Core Version:    0.6.0
 */