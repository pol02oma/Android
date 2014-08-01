package com.google.android.gms.analytics;

final class o
{
  private static String b(String paramString, int paramInt)
  {
    if (paramInt < 1)
    {
      aa.t("index out of range for " + paramString + " (" + paramInt + ")");
      return "";
    }
    return paramString + paramInt;
  }

  static String q(int paramInt)
  {
    return b("&cd", paramInt);
  }

  static String r(int paramInt)
  {
    return b("&cm", paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.o
 * JD-Core Version:    0.6.0
 */