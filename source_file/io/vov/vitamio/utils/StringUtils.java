package io.vov.vitamio.utils;

import java.util.Arrays;
import java.util.Iterator;

public class StringUtils
{
  public static int convertToInt(String paramString)
    throws NumberFormatException
  {
    int i = 0;
    while (true)
    {
      int j;
      if ((i >= paramString.length()) || (Character.isDigit(paramString.charAt(i))))
      {
        j = paramString.length();
        if (((j <= 0) || (Character.isDigit(paramString.charAt(j - 1)))) && (j <= i))
          break;
      }
      else
      {
        try
        {
          int k = Integer.parseInt(paramString.substring(i, j));
          return k;
          i++;
          continue;
          j--;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          Log.e("convertToInt", localNumberFormatException);
          throw new NumberFormatException();
        }
      }
    }
    throw new NumberFormatException();
  }

  public static String fixLastSlash(String paramString)
  {
    if (paramString == null);
    for (String str = "/"; ; str = paramString.trim() + "/")
    {
      if ((str.length() > 2) && (str.charAt(-2 + str.length()) == '/'))
        str = str.substring(0, -1 + str.length());
      return str;
    }
  }

  public static String generateTime(long paramLong)
  {
    int i = (int)(paramLong / 1000L);
    int j = i % 60;
    int k = i / 60 % 60;
    int m = i / 3600;
    if (m > 0)
    {
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = Integer.valueOf(m);
      arrayOfObject2[1] = Integer.valueOf(k);
      arrayOfObject2[2] = Integer.valueOf(j);
      return String.format("%02d:%02d:%02d", arrayOfObject2);
    }
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(k);
    arrayOfObject1[1] = Integer.valueOf(j);
    return String.format("%02d:%02d", arrayOfObject1);
  }

  public static String join(Iterable<? extends Object> paramIterable, CharSequence paramCharSequence)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramIterable != null)
    {
      Iterator localIterator = paramIterable.iterator();
      if (localIterator.hasNext())
      {
        localStringBuilder.append(String.valueOf(localIterator.next()));
        while (localIterator.hasNext())
          localStringBuilder.append(paramCharSequence).append(String.valueOf(localIterator.next()));
      }
    }
    return localStringBuilder.toString();
  }

  public static String join(Object[] paramArrayOfObject, CharSequence paramCharSequence)
  {
    return join(Arrays.asList(paramArrayOfObject), paramCharSequence);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.utils.StringUtils
 * JD-Core Version:    0.6.0
 */