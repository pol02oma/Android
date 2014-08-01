package org.apache.http.impl.cookie;

import java.util.Date;
import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Immutable
public class BasicMaxAgeHandler extends AbstractCookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for max-age attribute");
    int i;
    try
    {
      i = Integer.parseInt(paramString);
      if (i < 0)
        throw new MalformedCookieException("Negative max-age attribute: " + paramString);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new MalformedCookieException("Invalid max-age attribute: " + paramString);
    }
    paramSetCookie.setExpiryDate(new Date(System.currentTimeMillis() + 1000L * i));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.BasicMaxAgeHandler
 * JD-Core Version:    0.6.0
 */