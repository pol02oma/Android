package com.restfb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DateUtils
{
  public static final String FACEBOOK_LONG_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
  public static final String FACEBOOK_LONG_DATE_FORMAT_WITHOUT_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss";
  public static final String FACEBOOK_MONTH_YEAR_DATE_FORMAT = "yyyy-MM";
  public static final String FACEBOOK_SHORT_DATE_FORMAT = "MM/dd/yyyy";
  private static final Logger logger = Logger.getLogger(DateUtils.class.getName());

  public static Date toDateFromLongFormat(String paramString)
  {
    Date localDate = toDateWithFormatString(paramString, "yyyy-MM-dd'T'HH:mm:ssZ");
    if (localDate == null)
      localDate = toDateWithFormatString(paramString, "yyyy-MM-dd'T'HH:mm:ss");
    return localDate;
  }

  public static Date toDateFromMonthYearFormat(String paramString)
  {
    if ("0000-00".equals(paramString))
      return null;
    return toDateWithFormatString(paramString, "yyyy-MM");
  }

  public static Date toDateFromShortFormat(String paramString)
  {
    return toDateWithFormatString(paramString, "MM/dd/yyyy");
  }

  private static Date toDateWithFormatString(String paramString1, String paramString2)
  {
    if (paramString1 == null);
    do
    {
      return null;
      try
      {
        Date localDate = new SimpleDateFormat(paramString2).parse(paramString1);
        return localDate;
      }
      catch (ParseException localParseException)
      {
      }
    }
    while (!logger.isLoggable(Level.FINE));
    logger.fine("Unable to parse date '" + paramString1 + "' using format string '" + paramString2 + "': " + localParseException);
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.util.DateUtils
 * JD-Core Version:    0.6.0
 */