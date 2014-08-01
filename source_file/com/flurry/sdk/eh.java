package com.flurry.sdk;

import android.location.Criteria;

public class eh
{
  public static final Integer a = Integer.valueOf(159);
  public static final String b = null;
  public static final Boolean c = Boolean.valueOf(true);
  public static final Boolean d = Boolean.valueOf(true);
  public static final String e = null;
  public static final Boolean f = Boolean.valueOf(true);
  public static final Criteria g = null;
  public static final Long h = Long.valueOf(10000L);
  public static final Boolean i = Boolean.valueOf(true);
  public static final Long j = Long.valueOf(0L);
  public static final Byte k = Byte.valueOf(-1);
  private static ei l;

  public static ei a()
  {
    monitorenter;
    try
    {
      if (l == null)
      {
        l = new ei();
        b();
      }
      ei localei = l;
      return localei;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static void b()
  {
    if (l == null)
      l = new ei();
    l.a("AgentVersion", a);
    l.a("VesionName", b);
    l.a("CaptureUncaughtExceptions", c);
    l.a("UseHttps", d);
    l.a("ReportUrl", e);
    l.a("ReportLocation", f);
    l.a("LocationCriteria", g);
    l.a("ContinueSessionMillis", h);
    l.a("LogEvents", i);
    l.a("Age", j);
    l.a("Gender", k);
    l.a("UserId", "");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.eh
 * JD-Core Version:    0.6.0
 */