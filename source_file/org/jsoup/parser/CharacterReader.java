package org.jsoup.parser;

import org.jsoup.helper.Validate;

class CharacterReader
{
  static final char EOF = '￿';
  private final String input;
  private final int length;
  private int mark = 0;
  private int pos = 0;

  CharacterReader(String paramString)
  {
    Validate.notNull(paramString);
    String str = paramString.replaceAll("\r\n?", "\n");
    this.input = str;
    this.length = str.length();
  }

  void advance()
  {
    this.pos = (1 + this.pos);
  }

  char consume()
  {
    if (isEmpty());
    for (int i = 65535; ; i = this.input.charAt(this.pos))
    {
      this.pos = (1 + this.pos);
      return i;
    }
  }

  String consumeAsString()
  {
    String str = this.input;
    int i = this.pos;
    int j = this.pos;
    this.pos = (j + 1);
    return str.substring(i, j);
  }

  String consumeDigitSequence()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      int j = this.input.charAt(this.pos);
      if ((j < 48) || (j > 57))
        break;
      this.pos = (1 + this.pos);
    }
    return this.input.substring(i, this.pos);
  }

  String consumeHexSequence()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      int j = this.input.charAt(this.pos);
      if (((j < 48) || (j > 57)) && ((j < 65) || (j > 70)) && ((j < 97) || (j > 102)))
        break;
      this.pos = (1 + this.pos);
    }
    return this.input.substring(i, this.pos);
  }

  String consumeLetterSequence()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      int j = this.input.charAt(this.pos);
      if (((j < 65) || (j > 90)) && ((j < 97) || (j > 122)))
        break;
      this.pos = (1 + this.pos);
    }
    return this.input.substring(i, this.pos);
  }

  String consumeTo(char paramChar)
  {
    int i = this.input.indexOf(paramChar, this.pos);
    if (i != -1)
    {
      String str = this.input.substring(this.pos, i);
      this.pos += str.length();
      return str;
    }
    return consumeToEnd();
  }

  String consumeTo(String paramString)
  {
    int i = this.input.indexOf(paramString, this.pos);
    if (i != -1)
    {
      String str = this.input.substring(this.pos, i);
      this.pos += str.length();
      return str;
    }
    return consumeToEnd();
  }

  String consumeToAny(char[] paramArrayOfChar)
  {
    int i = this.pos;
    while (true)
    {
      int j;
      int k;
      if (!isEmpty())
      {
        j = this.input.charAt(this.pos);
        k = paramArrayOfChar.length;
      }
      for (int m = 0; m < k; m++)
      {
        if (paramArrayOfChar[m] != j)
          continue;
        if (this.pos <= i)
          break label86;
        return this.input.substring(i, this.pos);
      }
      this.pos = (1 + this.pos);
    }
    label86: return "";
  }

  String consumeToEnd()
  {
    String str = this.input.substring(this.pos, this.input.length());
    this.pos = this.input.length();
    return str;
  }

  boolean containsIgnoreCase(String paramString)
  {
    String str1 = paramString.toLowerCase();
    String str2 = paramString.toUpperCase();
    return (this.input.indexOf(str1, this.pos) > -1) || (this.input.indexOf(str2, this.pos) > -1);
  }

  char current()
  {
    if (isEmpty())
      return 65535;
    return this.input.charAt(this.pos);
  }

  boolean isEmpty()
  {
    return this.pos >= this.length;
  }

  void mark()
  {
    this.mark = this.pos;
  }

  boolean matchConsume(String paramString)
  {
    if (matches(paramString))
    {
      this.pos += paramString.length();
      return true;
    }
    return false;
  }

  boolean matchConsumeIgnoreCase(String paramString)
  {
    if (matchesIgnoreCase(paramString))
    {
      this.pos += paramString.length();
      return true;
    }
    return false;
  }

  boolean matches(char paramChar)
  {
    return (!isEmpty()) && (this.input.charAt(this.pos) == paramChar);
  }

  boolean matches(String paramString)
  {
    return this.input.startsWith(paramString, this.pos);
  }

  boolean matchesAny(char[] paramArrayOfChar)
  {
    if (isEmpty());
    while (true)
    {
      return false;
      int i = this.input.charAt(this.pos);
      int j = paramArrayOfChar.length;
      for (int k = 0; k < j; k++)
        if (paramArrayOfChar[k] == i)
          return true;
    }
  }

  boolean matchesDigit()
  {
    if (isEmpty());
    int i;
    do
    {
      return false;
      i = this.input.charAt(this.pos);
    }
    while ((i < 48) || (i > 57));
    return true;
  }

  boolean matchesIgnoreCase(String paramString)
  {
    return this.input.regionMatches(true, this.pos, paramString, 0, paramString.length());
  }

  boolean matchesLetter()
  {
    if (isEmpty());
    int i;
    do
    {
      return false;
      i = this.input.charAt(this.pos);
    }
    while (((i < 65) || (i > 90)) && ((i < 97) || (i > 122)));
    return true;
  }

  int pos()
  {
    return this.pos;
  }

  void rewindToMark()
  {
    this.pos = this.mark;
  }

  public String toString()
  {
    return this.input.substring(this.pos);
  }

  void unconsume()
  {
    this.pos = (-1 + this.pos);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.CharacterReader
 * JD-Core Version:    0.6.0
 */