package org.apache.james.mime4j.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public final class MimeUtil
{
  public static final String ENC_7BIT = "7bit";
  public static final String ENC_8BIT = "8bit";
  public static final String ENC_BASE64 = "base64";
  public static final String ENC_BINARY = "binary";
  public static final String ENC_QUOTED_PRINTABLE = "quoted-printable";
  private static final ThreadLocal<DateFormat> RFC822_DATE_FORMAT;
  private static int counter;
  private static final Random random = new Random();

  static
  {
    counter = 0;
    RFC822_DATE_FORMAT = new ThreadLocal()
    {
      protected DateFormat initialValue()
      {
        return new MimeUtil.Rfc822DateFormat();
      }
    };
  }

  public static String createUniqueBoundary()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("-=Part.");
    localStringBuilder.append(Integer.toHexString(nextCounterValue()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(random.nextLong()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(System.currentTimeMillis()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(random.nextLong()));
    localStringBuilder.append("=-");
    return localStringBuilder.toString();
  }

  public static String createUniqueMessageId(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder("<Mime4j.");
    localStringBuilder.append(Integer.toHexString(nextCounterValue()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(random.nextLong()));
    localStringBuilder.append('.');
    localStringBuilder.append(Long.toHexString(System.currentTimeMillis()));
    if (paramString != null)
    {
      localStringBuilder.append('@');
      localStringBuilder.append(paramString);
    }
    localStringBuilder.append('>');
    return localStringBuilder.toString();
  }

  public static String fold(String paramString, int paramInt)
  {
    int i = paramString.length();
    if (paramInt + i <= 76)
      return paramString;
    StringBuilder localStringBuilder = new StringBuilder();
    int j = -paramInt;
    int m;
    for (int k = indexOfWsp(paramString, 0); ; k = m)
    {
      if (k == i)
      {
        localStringBuilder.append(paramString.substring(Math.max(0, j)));
        return localStringBuilder.toString();
      }
      m = indexOfWsp(paramString, k + 1);
      if (m - j <= 76)
        continue;
      localStringBuilder.append(paramString.substring(Math.max(0, j), k));
      localStringBuilder.append("\r\n");
      j = k;
    }
  }

  public static String formatDate(Date paramDate, TimeZone paramTimeZone)
  {
    DateFormat localDateFormat = (DateFormat)RFC822_DATE_FORMAT.get();
    if (paramTimeZone == null)
      localDateFormat.setTimeZone(TimeZone.getDefault());
    while (true)
    {
      return localDateFormat.format(paramDate);
      localDateFormat.setTimeZone(paramTimeZone);
    }
  }

  private static int indexOfWsp(String paramString, int paramInt)
  {
    int i = paramString.length();
    for (int j = paramInt; j < i; j++)
    {
      int k = paramString.charAt(j);
      if ((k == 32) || (k == 9))
        return j;
    }
    return i;
  }

  public static boolean isBase64Encoding(String paramString)
  {
    return "base64".equalsIgnoreCase(paramString);
  }

  public static boolean isMessage(String paramString)
  {
    return (paramString != null) && (paramString.equalsIgnoreCase("message/rfc822"));
  }

  public static boolean isMultipart(String paramString)
  {
    return (paramString != null) && (paramString.toLowerCase().startsWith("multipart/"));
  }

  public static boolean isQuotedPrintableEncoded(String paramString)
  {
    return "quoted-printable".equalsIgnoreCase(paramString);
  }

  public static boolean isSameMimeType(String paramString1, String paramString2)
  {
    return (paramString1 != null) && (paramString2 != null) && (paramString1.equalsIgnoreCase(paramString2));
  }

  private static int nextCounterValue()
  {
    monitorenter;
    try
    {
      int i = counter;
      counter = i + 1;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static String unfold(String paramString)
  {
    int i = paramString.length();
    for (int j = 0; ; j++)
    {
      if (j < i)
      {
        int k = paramString.charAt(j);
        if ((k != 13) && (k != 10))
          continue;
        paramString = unfold0(paramString, j);
      }
      return paramString;
    }
  }

  private static String unfold0(String paramString, int paramInt)
  {
    int i = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(i);
    if (paramInt > 0)
      localStringBuilder.append(paramString.substring(0, paramInt));
    for (int j = paramInt + 1; j < i; j++)
    {
      char c = paramString.charAt(j);
      if ((c == '\r') || (c == '\n'))
        continue;
      localStringBuilder.append(c);
    }
    return localStringBuilder.toString();
  }

  private static final class Rfc822DateFormat extends SimpleDateFormat
  {
    private static final long serialVersionUID = 1L;

    public Rfc822DateFormat()
    {
      super(Locale.US);
    }

    public StringBuffer format(Date paramDate, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
    {
      StringBuffer localStringBuffer = super.format(paramDate, paramStringBuffer, paramFieldPosition);
      int i = (this.calendar.get(15) + this.calendar.get(16)) / 1000 / 60;
      if (i < 0)
      {
        localStringBuffer.append('-');
        i = -i;
      }
      while (true)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(i / 60);
        arrayOfObject[1] = Integer.valueOf(i % 60);
        localStringBuffer.append(String.format("%02d%02d", arrayOfObject));
        return localStringBuffer;
        localStringBuffer.append('+');
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.util.MimeUtil
 * JD-Core Version:    0.6.0
 */