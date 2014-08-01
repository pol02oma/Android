package org.apache.http.cookie;

import org.apache.http.annotation.Immutable;

@Immutable
public class CookieRestrictionViolationException extends MalformedCookieException
{
  private static final long serialVersionUID = 7371235577078589013L;

  public CookieRestrictionViolationException()
  {
  }

  public CookieRestrictionViolationException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.cookie.CookieRestrictionViolationException
 * JD-Core Version:    0.6.0
 */