package org.apache.commons.io.input;

import java.io.Reader;
import java.io.Serializable;

public class CharSequenceReader extends Reader
  implements Serializable
{
  private final CharSequence charSequence;
  private int idx;
  private int mark;

  public CharSequenceReader(CharSequence paramCharSequence)
  {
    if (paramCharSequence != null);
    while (true)
    {
      this.charSequence = paramCharSequence;
      return;
      paramCharSequence = "";
    }
  }

  public void close()
  {
    this.idx = 0;
    this.mark = 0;
  }

  public void mark(int paramInt)
  {
    this.mark = this.idx;
  }

  public boolean markSupported()
  {
    return true;
  }

  public int read()
  {
    if (this.idx >= this.charSequence.length())
      return -1;
    CharSequence localCharSequence = this.charSequence;
    int i = this.idx;
    this.idx = (i + 1);
    return localCharSequence.charAt(i);
  }

  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i;
    if (this.idx >= this.charSequence.length())
      i = -1;
    label138: 
    while (true)
    {
      return i;
      if (paramArrayOfChar == null)
        throw new NullPointerException("Character array is missing");
      if ((paramInt2 < 0) || (paramInt1 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length))
        throw new IndexOutOfBoundsException("Array Size=" + paramArrayOfChar.length + ", offset=" + paramInt1 + ", length=" + paramInt2);
      i = 0;
      for (int j = 0; ; j++)
      {
        if (j >= paramInt2)
          break label138;
        int k = read();
        if (k == -1)
          break;
        paramArrayOfChar[(paramInt1 + j)] = (char)k;
        i++;
      }
    }
  }

  public void reset()
  {
    this.idx = this.mark;
  }

  public long skip(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("Number of characters to skip is less than zero: " + paramLong);
    if (this.idx >= this.charSequence.length())
      return -1L;
    int i = (int)Math.min(this.charSequence.length(), paramLong + this.idx);
    int j = i - this.idx;
    this.idx = i;
    return j;
  }

  public String toString()
  {
    return this.charSequence.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.CharSequenceReader
 * JD-Core Version:    0.6.0
 */