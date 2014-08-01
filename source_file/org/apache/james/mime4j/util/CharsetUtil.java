package org.apache.james.mime4j.util;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

public class CharsetUtil
{
  public static final int CR = 13;
  public static final String CRLF = "\r\n";
  public static final Charset DEFAULT_CHARSET;
  public static final int HT = 9;
  public static final Charset ISO_8859_1;
  public static final int LF = 10;
  public static final int SP = 32;
  public static final Charset US_ASCII = Charset.forName("US-ASCII");
  public static final Charset UTF_8;

  static
  {
    ISO_8859_1 = Charset.forName("ISO-8859-1");
    UTF_8 = Charset.forName("UTF-8");
    DEFAULT_CHARSET = US_ASCII;
  }

  public static boolean isASCII(char paramChar)
  {
    return (0xFF80 & paramChar) == 0;
  }

  public static boolean isASCII(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("String may not be null");
    int i = paramString.length();
    for (int j = 0; j < i; j++)
      if (!isASCII(paramString.charAt(j)))
        return false;
    return true;
  }

  public static boolean isWhitespace(char paramChar)
  {
    return (paramChar == ' ') || (paramChar == '\t') || (paramChar == '\r') || (paramChar == '\n');
  }

  public static boolean isWhitespace(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("String may not be null");
    int i = paramString.length();
    for (int j = 0; j < i; j++)
      if (!isWhitespace(paramString.charAt(j)))
        return false;
    return true;
  }

  public static Charset lookup(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      Charset localCharset = Charset.forName(paramString);
      return localCharset;
    }
    catch (IllegalCharsetNameException localIllegalCharsetNameException)
    {
      return null;
    }
    catch (UnsupportedCharsetException localUnsupportedCharsetException)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.util.CharsetUtil
 * JD-Core Version:    0.6.0
 */