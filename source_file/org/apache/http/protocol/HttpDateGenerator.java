package org.apache.http.protocol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;

@ThreadSafe
public class HttpDateGenerator
{
  public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
  public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

  @GuardedBy("this")
  private long dateAsLong = 0L;

  @GuardedBy("this")
  private String dateAsText = null;

  @GuardedBy("this")
  private final DateFormat dateformat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

  public HttpDateGenerator()
  {
    this.dateformat.setTimeZone(GMT);
  }

  public String getCurrentDate()
  {
    monitorenter;
    try
    {
      long l = System.currentTimeMillis();
      if (l - this.dateAsLong > 1000L)
      {
        this.dateAsText = this.dateformat.format(new Date(l));
        this.dateAsLong = l;
      }
      String str = this.dateAsText;
      return str;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.HttpDateGenerator
 * JD-Core Version:    0.6.0
 */