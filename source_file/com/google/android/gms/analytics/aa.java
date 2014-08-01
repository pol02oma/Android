package com.google.android.gms.analytics;

public class aa
{
  private static GoogleAnalytics tV;

  public static boolean cm()
  {
    Logger localLogger = getLogger();
    int i = 0;
    if (localLogger != null)
    {
      int j = getLogger().getLogLevel();
      i = 0;
      if (j == 0)
        i = 1;
    }
    return i;
  }

  private static Logger getLogger()
  {
    if (tV == null)
      tV = GoogleAnalytics.cf();
    if (tV != null)
      return tV.getLogger();
    return null;
  }

  public static void t(String paramString)
  {
    Logger localLogger = getLogger();
    if (localLogger != null)
      localLogger.error(paramString);
  }

  public static void u(String paramString)
  {
    Logger localLogger = getLogger();
    if (localLogger != null)
      localLogger.info(paramString);
  }

  public static void v(String paramString)
  {
    Logger localLogger = getLogger();
    if (localLogger != null)
      localLogger.verbose(paramString);
  }

  public static void w(String paramString)
  {
    Logger localLogger = getLogger();
    if (localLogger != null)
      localLogger.warn(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.aa
 * JD-Core Version:    0.6.0
 */