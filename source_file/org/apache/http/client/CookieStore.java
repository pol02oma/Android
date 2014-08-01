package org.apache.http.client;

import java.util.Date;
import java.util.List;
import org.apache.http.cookie.Cookie;

public abstract interface CookieStore
{
  public abstract void addCookie(Cookie paramCookie);

  public abstract void clear();

  public abstract boolean clearExpired(Date paramDate);

  public abstract List<Cookie> getCookies();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.CookieStore
 * JD-Core Version:    0.6.0
 */