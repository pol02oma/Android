package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class ap
{
  private final long UZ;
  private String Va;
  private final long tL;
  private final long tM;

  ap(long paramLong1, long paramLong2, long paramLong3)
  {
    this.tL = paramLong1;
    this.tM = paramLong2;
    this.UZ = paramLong3;
  }

  void F(String paramString)
  {
    if ((paramString == null) || (TextUtils.isEmpty(paramString.trim())))
      return;
    this.Va = paramString;
  }

  long ci()
  {
    return this.tL;
  }

  long je()
  {
    return this.UZ;
  }

  String jf()
  {
    return this.Va;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ap
 * JD-Core Version:    0.6.0
 */