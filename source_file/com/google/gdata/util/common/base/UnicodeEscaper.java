package com.google.gdata.util.common.base;

import java.io.IOException;

public abstract class UnicodeEscaper
  implements Escaper
{
  private static final int DEST_PAD = 32;
  private static final ThreadLocal<char[]> DEST_TL = new ThreadLocal()
  {
    protected char[] initialValue()
    {
      return new char[1024];
    }
  };

  protected static final int codePointAt(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2)
    {
      int i = paramInt1 + 1;
      int j = paramCharSequence.charAt(paramInt1);
      if ((j < 55296) || (j > 57343))
        return j;
      if (j <= 56319)
      {
        if (i == paramInt2)
          return -j;
        char c = paramCharSequence.charAt(i);
        if (Character.isLowSurrogate(c))
          return Character.toCodePoint(j, c);
        throw new IllegalArgumentException("Expected low surrogate but got char '" + c + "' with value " + c + " at index " + i);
      }
      throw new IllegalArgumentException("Unexpected low surrogate character '" + j + "' with value " + j + " at index " + (i - 1));
    }
    throw new IndexOutOfBoundsException("Index exceeds specified range");
  }

  private static final char[] growBuffer(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    char[] arrayOfChar = new char[paramInt2];
    if (paramInt1 > 0)
      System.arraycopy(paramArrayOfChar, 0, arrayOfChar, 0, paramInt1);
    return arrayOfChar;
  }

  public Appendable escape(Appendable paramAppendable)
  {
    Preconditions.checkNotNull(paramAppendable);
    return new Appendable(paramAppendable)
    {
      char[] decodedChars = new char[2];
      int pendingHighSurrogate = -1;

      private void outputChars(char[] paramArrayOfChar, int paramInt)
        throws IOException
      {
        for (int i = 0; i < paramInt; i++)
          this.val$out.append(paramArrayOfChar[i]);
      }

      public Appendable append(char paramChar)
        throws IOException
      {
        if (this.pendingHighSurrogate != -1)
        {
          if (!Character.isLowSurrogate(paramChar))
            throw new IllegalArgumentException("Expected low surrogate character but got '" + paramChar + "' with value " + paramChar);
          char[] arrayOfChar2 = UnicodeEscaper.this.escape(Character.toCodePoint((char)this.pendingHighSurrogate, paramChar));
          if (arrayOfChar2 != null)
            outputChars(arrayOfChar2, arrayOfChar2.length);
          while (true)
          {
            this.pendingHighSurrogate = -1;
            return this;
            this.val$out.append((char)this.pendingHighSurrogate);
            this.val$out.append(paramChar);
          }
        }
        if (Character.isHighSurrogate(paramChar))
        {
          this.pendingHighSurrogate = paramChar;
          return this;
        }
        if (Character.isLowSurrogate(paramChar))
          throw new IllegalArgumentException("Unexpected low surrogate character '" + paramChar + "' with value " + paramChar);
        char[] arrayOfChar1 = UnicodeEscaper.this.escape(paramChar);
        if (arrayOfChar1 != null)
        {
          outputChars(arrayOfChar1, arrayOfChar1.length);
          return this;
        }
        this.val$out.append(paramChar);
        return this;
      }

      public Appendable append(CharSequence paramCharSequence)
        throws IOException
      {
        return append(paramCharSequence, 0, paramCharSequence.length());
      }

      public Appendable append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
        throws IOException
      {
        int i = paramInt1;
        int j;
        int i2;
        if (i < paramInt2)
        {
          j = i;
          if (this.pendingHighSurrogate != -1)
          {
            i2 = i + 1;
            char c = paramCharSequence.charAt(i);
            if (!Character.isLowSurrogate(c))
              throw new IllegalArgumentException("Expected low surrogate character but got " + c);
            char[] arrayOfChar2 = UnicodeEscaper.this.escape(Character.toCodePoint((char)this.pendingHighSurrogate, c));
            if (arrayOfChar2 == null)
              break label161;
            outputChars(arrayOfChar2, arrayOfChar2.length);
            j++;
          }
        }
        int k;
        while (true)
        {
          this.pendingHighSurrogate = -1;
          i = i2;
          k = UnicodeEscaper.this.nextEscapeIndex(paramCharSequence, i, paramInt2);
          if (k > j)
            this.val$out.append(paramCharSequence, j, k);
          if (k != paramInt2)
            break;
          return this;
          label161: this.val$out.append((char)this.pendingHighSurrogate);
        }
        int m = UnicodeEscaper.codePointAt(paramCharSequence, k, paramInt2);
        if (m < 0)
        {
          this.pendingHighSurrogate = (-m);
          return this;
        }
        char[] arrayOfChar1 = UnicodeEscaper.this.escape(m);
        if (arrayOfChar1 != null)
        {
          outputChars(arrayOfChar1, arrayOfChar1.length);
          label227: if (!Character.isSupplementaryCodePoint(m))
            break label277;
        }
        label277: for (int i1 = 2; ; i1 = 1)
        {
          i = k + i1;
          j = i;
          break;
          int n = Character.toChars(m, this.decodedChars, 0);
          outputChars(this.decodedChars, n);
          break label227;
        }
      }
    };
  }

  public String escape(String paramString)
  {
    int i = paramString.length();
    int j = nextEscapeIndex(paramString, 0, i);
    if (j == i)
      return paramString;
    return escapeSlow(paramString, j);
  }

  protected abstract char[] escape(int paramInt);

  protected final String escapeSlow(String paramString, int paramInt)
  {
    int i = paramString.length();
    char[] arrayOfChar1 = (char[])DEST_TL.get();
    int j = 0;
    int k = 0;
    if (paramInt < i)
    {
      int i1 = codePointAt(paramString, paramInt, i);
      if (i1 < 0)
        throw new IllegalArgumentException("Trailing high surrogate at end of input");
      char[] arrayOfChar2 = escape(i1);
      if (arrayOfChar2 != null)
      {
        int i3 = paramInt - k;
        int i4 = j + i3 + arrayOfChar2.length;
        if (arrayOfChar1.length < i4)
          arrayOfChar1 = growBuffer(arrayOfChar1, j, 32 + (i4 + (i - paramInt)));
        if (i3 > 0)
        {
          paramString.getChars(k, paramInt, arrayOfChar1, j);
          j += i3;
        }
        if (arrayOfChar2.length > 0)
        {
          System.arraycopy(arrayOfChar2, 0, arrayOfChar1, j, arrayOfChar2.length);
          j += arrayOfChar2.length;
        }
      }
      if (Character.isSupplementaryCodePoint(i1));
      for (int i2 = 2; ; i2 = 1)
      {
        k = paramInt + i2;
        paramInt = nextEscapeIndex(paramString, k, i);
        break;
      }
    }
    int m = i - k;
    if (m > 0)
    {
      int n = j + m;
      if (arrayOfChar1.length < n)
        arrayOfChar1 = growBuffer(arrayOfChar1, j, n);
      paramString.getChars(k, i, arrayOfChar1, j);
      j = n;
    }
    return new String(arrayOfChar1, 0, j);
  }

  protected int nextEscapeIndex(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    int j;
    if (i < paramInt2)
    {
      j = codePointAt(paramCharSequence, i, paramInt2);
      if ((j >= 0) && (escape(j) == null));
    }
    else
    {
      return i;
    }
    if (Character.isSupplementaryCodePoint(j));
    for (int k = 2; ; k = 1)
    {
      i += k;
      break;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.gdata.util.common.base.UnicodeEscaper
 * JD-Core Version:    0.6.0
 */