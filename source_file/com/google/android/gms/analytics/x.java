package com.google.android.gms.analytics;

import android.text.TextUtils;

class x
{
  private String tK;
  private final long tL;
  private final long tM;
  private String tN = "https:";

  x(String paramString, long paramLong1, long paramLong2)
  {
    this.tK = paramString;
    this.tL = paramLong1;
    this.tM = paramLong2;
  }

  void E(String paramString)
  {
    this.tK = paramString;
  }

  void F(String paramString)
  {
    if ((paramString == null) || (TextUtils.isEmpty(paramString.trim())));
    do
      return;
    while (!paramString.toLowerCase().startsWith("http:"));
    this.tN = "http:";
  }

  String ch()
  {
    return this.tK;
  }

  long ci()
  {
    return this.tL;
  }

  long cj()
  {
    return this.tM;
  }

  String ck()
  {
    return this.tN;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.x
 * JD-Core Version:    0.6.0
 */