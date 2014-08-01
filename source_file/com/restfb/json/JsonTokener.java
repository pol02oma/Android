package com.restfb.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class JsonTokener
{
  private int index;
  private char lastChar;
  private Reader reader;
  private boolean useLastChar;

  public JsonTokener(Reader paramReader)
  {
    if (paramReader.markSupported());
    while (true)
    {
      this.reader = paramReader;
      this.useLastChar = false;
      this.index = 0;
      return;
      paramReader = new BufferedReader(paramReader);
    }
  }

  public JsonTokener(String paramString)
  {
    this(new StringReader(paramString));
  }

  public static int dehexchar(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0';
    if ((paramChar >= 'A') && (paramChar <= 'F'))
      return paramChar - '7';
    if ((paramChar >= 'a') && (paramChar <= 'f'))
      return paramChar - 'W';
    return -1;
  }

  public void back()
  {
    if ((this.useLastChar) || (this.index <= 0))
      throw new JsonException("Stepping back two steps is not supported");
    this.index = (-1 + this.index);
    this.useLastChar = true;
  }

  public boolean more()
  {
    if (next() == 0)
      return false;
    back();
    return true;
  }

  public char next()
  {
    if (this.useLastChar)
    {
      this.useLastChar = false;
      if (this.lastChar != 0)
        this.index = (1 + this.index);
      return this.lastChar;
    }
    int i;
    try
    {
      i = this.reader.read();
      if (i <= 0)
      {
        this.lastChar = '\000';
        return '\000';
      }
    }
    catch (IOException localIOException)
    {
      throw new JsonException(localIOException);
    }
    this.index = (1 + this.index);
    this.lastChar = (char)i;
    return this.lastChar;
  }

  public char next(char paramChar)
  {
    char c = next();
    if (c != paramChar)
      throw syntaxError("Expected '" + paramChar + "' and instead saw '" + c + "'");
    return c;
  }

  public String next(int paramInt)
  {
    if (paramInt == 0)
      return "";
    char[] arrayOfChar = new char[paramInt];
    boolean bool = this.useLastChar;
    int i = 0;
    if (bool)
    {
      this.useLastChar = false;
      arrayOfChar[0] = this.lastChar;
      i = 1;
    }
    while (i < paramInt)
      try
      {
        int j = this.reader.read(arrayOfChar, i, paramInt - i);
        if (j == -1)
          break;
        i += j;
      }
      catch (IOException localIOException)
      {
        throw new JsonException(localIOException);
      }
    this.index = (i + this.index);
    if (i < paramInt)
      throw syntaxError("Substring bounds error");
    this.lastChar = arrayOfChar[(paramInt - 1)];
    return new String(arrayOfChar);
  }

  public char nextClean()
  {
    int i;
    do
      i = next();
    while ((i != 0) && (i <= 32));
    return i;
  }

  public String nextString(char paramChar)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      char c1 = next();
      switch (c1)
      {
      default:
        if (c1 != paramChar)
          break;
        return localStringBuilder.toString();
      case '\000':
      case '\n':
      case '\r':
        throw syntaxError("Unterminated string");
      case '\\':
        char c2 = next();
        switch (c2)
        {
        default:
          throw syntaxError("Illegal escape.");
        case 'b':
          localStringBuilder.append('\b');
          break;
        case 't':
          localStringBuilder.append('\t');
          break;
        case 'n':
          localStringBuilder.append('\n');
          break;
        case 'f':
          localStringBuilder.append('\f');
          break;
        case 'r':
          localStringBuilder.append('\r');
          break;
        case 'u':
          localStringBuilder.append((char)Integer.parseInt(next(4), 16));
          break;
        case '"':
        case '\'':
        case '/':
        case '\\':
          localStringBuilder.append(c2);
        }
        break;
      }
      localStringBuilder.append(c1);
    }
  }

  public String nextTo(char paramChar)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      char c = next();
      if ((c == paramChar) || (c == 0) || (c == '\n') || (c == '\r'))
      {
        if (c != 0)
          back();
        return localStringBuilder.toString().trim();
      }
      localStringBuilder.append(c);
    }
  }

  public String nextTo(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      char c = next();
      if ((paramString.indexOf(c) >= 0) || (c == 0) || (c == '\n') || (c == '\r'))
      {
        if (c != 0)
          back();
        return localStringBuilder.toString().trim();
      }
      localStringBuilder.append(c);
    }
  }

  public Object nextValue()
  {
    char c = nextClean();
    StringBuilder localStringBuilder;
    switch (c)
    {
    default:
      localStringBuilder = new StringBuilder();
    case '"':
    case '\'':
    case '{':
    case '(':
    case '[':
    }
    while ((c >= ' ') && (",:]}/\\\"[{;=#".indexOf(c) < 0))
    {
      localStringBuilder.append(c);
      c = next();
      continue;
      return nextString(c);
      back();
      return new JsonObject(this);
      back();
      return new JsonArray(this);
    }
    back();
    String str = localStringBuilder.toString().trim();
    if (str.equals(""))
      throw syntaxError("Missing value");
    return JsonObject.stringToValue(str);
  }

  public char skipTo(char paramChar)
  {
    try
    {
      int i = this.index;
      this.reader.mark(2147483647);
      char c;
      do
      {
        c = next();
        if (c != 0)
          continue;
        this.reader.reset();
        this.index = i;
        return c;
      }
      while (c != paramChar);
      back();
      return c;
    }
    catch (IOException localIOException)
    {
    }
    throw new JsonException(localIOException);
  }

  public JsonException syntaxError(String paramString)
  {
    return new JsonException(paramString + toString());
  }

  public String toString()
  {
    return " at character " + this.index;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.json.JsonTokener
 * JD-Core Version:    0.6.0
 */