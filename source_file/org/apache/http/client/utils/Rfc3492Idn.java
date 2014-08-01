package org.apache.http.client.utils;

import java.util.StringTokenizer;
import org.apache.http.annotation.Immutable;

@Immutable
public class Rfc3492Idn
  implements Idn
{
  private static final String ACE_PREFIX = "xn--";
  private static final int base = 36;
  private static final int damp = 700;
  private static final char delimiter = '-';
  private static final int initial_bias = 72;
  private static final int initial_n = 128;
  private static final int skew = 38;
  private static final int tmax = 26;
  private static final int tmin = 1;

  private int adapt(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean);
    int j;
    int k;
    for (int i = paramInt1 / 700; ; i = paramInt1 / 2)
    {
      j = i + i / paramInt2;
      for (k = 0; j > 455; k += 36)
        j /= 35;
    }
    return k + j * 36 / (j + 38);
  }

  private int digit(char paramChar)
  {
    if ((paramChar >= 'A') && (paramChar <= 'Z'))
      return paramChar - 'A';
    if ((paramChar >= 'a') && (paramChar <= 'z'))
      return paramChar - 'a';
    if ((paramChar >= '0') && (paramChar <= '9'))
      return 26 + (paramChar - '0');
    throw new IllegalArgumentException("illegal digit: " + paramChar);
  }

  protected String decode(String paramString)
  {
    int i = 128;
    int j = 72;
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    int k = paramString.lastIndexOf('-');
    int m = 0;
    if (k != -1)
    {
      localStringBuilder.append(paramString.subSequence(0, k));
      paramString = paramString.substring(k + 1);
    }
    if (paramString.length() > 0)
    {
      int n = m;
      int i1 = 1;
      int i2 = 36;
      label84: int i5;
      int i6;
      if (paramString.length() == 0)
      {
        i5 = m - n;
        i6 = 1 + localStringBuilder.length();
        if (n != 0)
          break label253;
      }
      label251: label253: for (boolean bool = true; ; bool = false)
      {
        j = adapt(i5, i6, bool);
        i += m / (1 + localStringBuilder.length());
        int i7 = m % (1 + localStringBuilder.length());
        localStringBuilder.insert(i7, (char)i);
        m = i7 + 1;
        break;
        char c = paramString.charAt(0);
        paramString = paramString.substring(1);
        int i3 = digit(c);
        m += i3 * i1;
        int i4;
        if (i2 <= j + 1)
          i4 = 1;
        while (true)
        {
          if (i3 < i4)
            break label251;
          i1 *= (36 - i4);
          i2 += 36;
          break;
          if (i2 >= j + 26)
          {
            i4 = 26;
            continue;
          }
          i4 = i2 - j;
        }
        break label84;
      }
    }
    return localStringBuilder.toString();
  }

  public String toUnicode(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ".");
    while (localStringTokenizer.hasMoreTokens())
    {
      String str = localStringTokenizer.nextToken();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append('.');
      if (str.startsWith("xn--"))
        str = decode(str.substring(4));
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.utils.Rfc3492Idn
 * JD-Core Version:    0.6.0
 */