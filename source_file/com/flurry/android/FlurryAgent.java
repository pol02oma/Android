package com.flurry.android;

import android.content.Context;
import android.location.Criteria;
import android.os.Build.VERSION;
import com.flurry.sdk.bx;
import com.flurry.sdk.eg;
import com.flurry.sdk.eh;
import com.flurry.sdk.ei;
import com.flurry.sdk.ex;
import com.flurry.sdk.ey;
import com.flurry.sdk.fh;
import java.util.Date;
import java.util.Map;

public final class FlurryAgent
{
  private static final String a = FlurryAgent.class.getSimpleName();

  public static void endTimedEvent(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String eventId passed to endTimedEvent was null.");
      return;
    }
    try
    {
      bx.a().b(paramString);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "Failed to signify the end of event: " + paramString, localThrowable);
    }
  }

  public static void endTimedEvent(String paramString, Map<String, String> paramMap)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String eventId passed to endTimedEvent was null.");
      return;
    }
    if (paramMap == null)
    {
      ex.b(a, "String eventId passed to endTimedEvent was null.");
      return;
    }
    try
    {
      bx.a().b(paramString, paramMap);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "Failed to signify the end of event: " + paramString, localThrowable);
    }
  }

  public static int getAgentVersion()
  {
    return bx.a().b();
  }

  public static String getReleaseVersion()
  {
    return bx.a().g();
  }

  public static boolean getUseHttps()
  {
    return ((Boolean)eh.a().a("UseHttps")).booleanValue();
  }

  public static void logEvent(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String eventId passed to logEvent was null.");
      return;
    }
    try
    {
      bx.a().a(paramString);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "Failed to log event: " + paramString, localThrowable);
    }
  }

  public static void logEvent(String paramString, Map<String, String> paramMap)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String eventId passed to logEvent was null.");
      return;
    }
    if (paramMap == null)
    {
      ex.b(a, "String parameters passed to logEvent was null.");
      return;
    }
    try
    {
      bx.a().a(paramString, paramMap);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "Failed to log event: " + paramString, localThrowable);
    }
  }

  public static void logEvent(String paramString, Map<String, String> paramMap, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String eventId passed to logEvent was null.");
      return;
    }
    if (paramMap == null)
    {
      ex.b(a, "String parameters passed to logEvent was null.");
      return;
    }
    try
    {
      bx.a().a(paramString, paramMap, paramBoolean);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "Failed to log event: " + paramString, localThrowable);
    }
  }

  public static void logEvent(String paramString, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String eventId passed to logEvent was null.");
      return;
    }
    try
    {
      bx.a().a(paramString, paramBoolean);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "Failed to log event: " + paramString, localThrowable);
    }
  }

  public static void onEndSession(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramContext == null)
      throw new NullPointerException("Null context");
    ey.a().g(paramContext);
    try
    {
      bx.a().a(paramContext);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "", localThrowable);
    }
  }

  @Deprecated
  public static void onError(String paramString1, String paramString2, String paramString3)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString1 == null)
    {
      ex.b(a, "String errorId passed to onError was null.");
      return;
    }
    if (paramString2 == null)
    {
      ex.b(a, "String message passed to onError was null.");
      return;
    }
    if (paramString3 == null)
    {
      ex.b(a, "String errorClass passed to onError was null.");
      return;
    }
    try
    {
      bx.a().a(paramString1, paramString2, paramString3);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "", localThrowable);
    }
  }

  public static void onError(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString1 == null)
    {
      ex.b(a, "String errorId passed to onError was null.");
      return;
    }
    if (paramString2 == null)
    {
      ex.b(a, "String message passed to onError was null.");
      return;
    }
    if (paramThrowable == null)
    {
      ex.b(a, "Throwable passed to onError was null.");
      return;
    }
    try
    {
      bx.a().a(paramString1, paramString2, paramThrowable);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "", localThrowable);
    }
  }

  @Deprecated
  public static void onEvent(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String eventId passed to onEvent was null.");
      return;
    }
    try
    {
      bx.a().c(paramString);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "", localThrowable);
    }
  }

  @Deprecated
  public static void onEvent(String paramString, Map<String, String> paramMap)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String eventId passed to onEvent was null.");
      return;
    }
    if (paramMap == null)
    {
      ex.b(a, "Parameters Map passed to onEvent was null.");
      return;
    }
    try
    {
      bx.a().c(paramString, paramMap);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "", localThrowable);
    }
  }

  public static void onPageView()
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    try
    {
      bx.a().i();
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.b(a, "", localThrowable);
    }
  }

  public static void onStartSession(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramContext == null)
      throw new NullPointerException("Null context");
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("Api key not specified");
    eg.a(paramContext);
    try
    {
      bx.a().a(paramContext, paramString);
      ey.a().f(paramContext);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        ex.b(a, "", localThrowable);
    }
  }

  public static void setAge(int paramInt)
  {
    if (Build.VERSION.SDK_INT < 10)
      ex.b(a, "Device SDK Version older than 10");
    do
      return;
    while ((paramInt <= 0) || (paramInt >= 110));
    long l = new Date(new Date(System.currentTimeMillis() - 31449600000L * paramInt).getYear(), 1, 1).getTime();
    eh.a().a("Age", Long.valueOf(l));
  }

  public static void setCaptureUncaughtExceptions(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    eh.a().a("CaptureUncaughtExceptions", Boolean.valueOf(paramBoolean));
  }

  public static void setContinueSessionMillis(long paramLong)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramLong < 5000L)
    {
      ex.b(a, "Invalid time set for session resumption: " + paramLong);
      return;
    }
    eh.a().a("ContinueSessionMillis", Long.valueOf(paramLong));
  }

  public static void setGender(byte paramByte)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    switch (paramByte)
    {
    default:
      eh.a().a("Gender", Byte.valueOf(-1));
      return;
    case 0:
    case 1:
    }
    eh.a().a("Gender", Byte.valueOf(paramByte));
  }

  static void setInternalLoggingEnabled(boolean paramBoolean)
  {
    bx.a().a(paramBoolean);
  }

  public static void setLocationCriteria(Criteria paramCriteria)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    eh.a().a("LocationCriteria", paramCriteria);
  }

  public static void setLogEnabled(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramBoolean)
    {
      ex.b();
      return;
    }
    ex.a();
  }

  public static void setLogEvents(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    eh.a().a("LogEvents", Boolean.valueOf(paramBoolean));
  }

  public static void setLogLevel(int paramInt)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    ex.a(paramInt);
  }

  public static void setReportLocation(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    eh.a().a("ReportLocation", Boolean.valueOf(paramBoolean));
  }

  public static void setReportUrl(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    eh.a().a("ReportUrl", paramString);
  }

  public static void setUseHttps(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    eh.a().a("UseHttps", Boolean.valueOf(paramBoolean));
  }

  public static void setUserId(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String userId passed to setUserId was null.");
      return;
    }
    eh.a().a("UserId", fh.a(paramString));
  }

  public static void setVersionName(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      ex.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      ex.b(a, "String versionName passed to setVersionName was null.");
      return;
    }
    eh.a().a("VesionName", paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.android.FlurryAgent
 * JD-Core Version:    0.6.0
 */