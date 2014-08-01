package org.apache.http.message;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.annotation.Immutable;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

@Immutable
public class BasicHeaderValueParser
  implements HeaderValueParser
{
  private static final char[] ALL_DELIMITERS;
  public static final BasicHeaderValueParser DEFAULT = new BasicHeaderValueParser();
  private static final char ELEM_DELIMITER = ',';
  private static final char PARAM_DELIMITER = ';';

  static
  {
    ALL_DELIMITERS = new char[] { 59, 44 };
  }

  private static boolean isOneOf(char paramChar, char[] paramArrayOfChar)
  {
    if (paramArrayOfChar != null)
      for (int i = 0; i < paramArrayOfChar.length; i++)
        if (paramChar == paramArrayOfChar[i])
          return true;
    return false;
  }

  public static final HeaderElement[] parseElements(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramHeaderValueParser == null)
      paramHeaderValueParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramHeaderValueParser.parseElements(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final HeaderElement parseHeaderElement(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramHeaderValueParser == null)
      paramHeaderValueParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramHeaderValueParser.parseHeaderElement(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final NameValuePair parseNameValuePair(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramHeaderValueParser == null)
      paramHeaderValueParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramHeaderValueParser.parseNameValuePair(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final NameValuePair[] parseParameters(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramHeaderValueParser == null)
      paramHeaderValueParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramHeaderValueParser.parseParameters(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  protected HeaderElement createHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    return new BasicHeaderElement(paramString1, paramString2, paramArrayOfNameValuePair);
  }

  protected NameValuePair createNameValuePair(String paramString1, String paramString2)
  {
    return new BasicNameValuePair(paramString1, paramString2);
  }

  public HeaderElement[] parseElements(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    ArrayList localArrayList = new ArrayList();
    while (!paramParserCursor.atEnd())
    {
      HeaderElement localHeaderElement = parseHeaderElement(paramCharArrayBuffer, paramParserCursor);
      if ((localHeaderElement.getName().length() == 0) && (localHeaderElement.getValue() == null))
        continue;
      localArrayList.add(localHeaderElement);
    }
    return (HeaderElement[])localArrayList.toArray(new HeaderElement[localArrayList.size()]);
  }

  public HeaderElement parseHeaderElement(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    NameValuePair localNameValuePair = parseNameValuePair(paramCharArrayBuffer, paramParserCursor);
    boolean bool = paramParserCursor.atEnd();
    NameValuePair[] arrayOfNameValuePair = null;
    if (!bool)
    {
      int i = paramCharArrayBuffer.charAt(-1 + paramParserCursor.getPos());
      arrayOfNameValuePair = null;
      if (i != 44)
        arrayOfNameValuePair = parseParameters(paramCharArrayBuffer, paramParserCursor);
    }
    return createHeaderElement(localNameValuePair.getName(), localNameValuePair.getValue(), arrayOfNameValuePair);
  }

  public NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    return parseNameValuePair(paramCharArrayBuffer, paramParserCursor, ALL_DELIMITERS);
  }

  public NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor, char[] paramArrayOfChar)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getPos();
    int k = paramParserCursor.getUpperBound();
    int m = 0;
    char c2;
    label74: String str1;
    if (i < k)
    {
      c2 = paramCharArrayBuffer.charAt(i);
      m = 0;
      if (c2 != '=');
    }
    else
    {
      if (i != k)
        break label134;
      m = 1;
      str1 = paramCharArrayBuffer.substringTrimmed(j, k);
    }
    while (true)
    {
      if (m == 0)
        break label150;
      paramParserCursor.updatePos(i);
      return createNameValuePair(str1, null);
      if (isOneOf(c2, paramArrayOfChar))
      {
        m = 1;
        break label74;
      }
      i++;
      break;
      label134: str1 = paramCharArrayBuffer.substringTrimmed(j, i);
      i++;
    }
    label150: int n = i;
    int i1 = 0;
    int i2 = 0;
    label195: int i3;
    while (true)
    {
      char c1;
      if (i < k)
      {
        c1 = paramCharArrayBuffer.charAt(i);
        if ((c1 == '"') && (i2 == 0))
        {
          if (i1 != 0)
            break label246;
          i1 = 1;
        }
      }
      while (true)
      {
        if ((i1 == 0) && (i2 == 0) && (isOneOf(c1, paramArrayOfChar)))
        {
          m = 1;
          i3 = i;
        }
        while (true)
          if ((n < i3) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(n))))
          {
            n++;
            continue;
            label246: i1 = 0;
            break label195;
            if (i2 != 0)
            {
              i2 = 0;
              i++;
              break;
            }
            if ((i1 != 0) && (c1 == '\\'));
            for (i2 = 1; ; i2 = 0)
              break;
          }
      }
    }
    while ((i3 > n) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i3 - 1))))
      i3--;
    if ((i3 - n >= 2) && (paramCharArrayBuffer.charAt(n) == '"') && (paramCharArrayBuffer.charAt(i3 - 1) == '"'))
    {
      n++;
      i3--;
    }
    String str2 = paramCharArrayBuffer.substring(n, i3);
    if (m != 0)
      i++;
    paramParserCursor.updatePos(i);
    return createNameValuePair(str1, str2);
  }

  public NameValuePair[] parseParameters(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    while ((i < j) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i))))
      i++;
    paramParserCursor.updatePos(i);
    if (paramParserCursor.atEnd())
      return new NameValuePair[0];
    ArrayList localArrayList = new ArrayList();
    do
    {
      if (paramParserCursor.atEnd())
        break;
      localArrayList.add(parseNameValuePair(paramCharArrayBuffer, paramParserCursor));
    }
    while (paramCharArrayBuffer.charAt(-1 + paramParserCursor.getPos()) != ',');
    return (NameValuePair[])localArrayList.toArray(new NameValuePair[localArrayList.size()]);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.message.BasicHeaderValueParser
 * JD-Core Version:    0.6.0
 */