package org.apache.http.impl.cookie;

import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Immutable
public class BasicExpiresHandler extends AbstractCookieAttributeHandler
{
  private final String[] datepatterns;

  public BasicExpiresHandler(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("Array of date patterns may not be null");
    this.datepatterns = paramArrayOfString;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for expires attribute");
    try
    {
      paramSetCookie.setExpiryDate(DateUtils.parseDate(paramString, this.datepatterns));
      return;
    }
    catch (DateParseException localDateParseException)
    {
    }
    throw new MalformedCookieException("Unable to parse expires attribute: " + paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.BasicExpiresHandler
 * JD-Core Version:    0.6.0
 */