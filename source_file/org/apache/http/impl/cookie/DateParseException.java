package org.apache.http.impl.cookie;

import org.apache.http.annotation.Immutable;

@Immutable
public class DateParseException extends Exception
{
  private static final long serialVersionUID = 4417696455000643370L;

  public DateParseException()
  {
  }

  public DateParseException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.DateParseException
 * JD-Core Version:    0.6.0
 */