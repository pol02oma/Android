package com.google.android.gms.analytics;

import android.util.Log;

class l
  implements Logger
{
  private int rc = 1;

  private String z(String paramString)
  {
    return Thread.currentThread().toString() + ": " + paramString;
  }

  public void error(Exception paramException)
  {
    if (this.rc <= 3)
      Log.e("GAV3", null, paramException);
  }

  public void error(String paramString)
  {
    if (this.rc <= 3)
      Log.e("GAV3", z(paramString));
  }

  public int getLogLevel()
  {
    return this.rc;
  }

  public void info(String paramString)
  {
    if (this.rc <= 1)
      Log.i("GAV3", z(paramString));
  }

  public void setLogLevel(int paramInt)
  {
    this.rc = paramInt;
  }

  public void verbose(String paramString)
  {
    if (this.rc <= 0)
      Log.v("GAV3", z(paramString));
  }

  public void warn(String paramString)
  {
    if (this.rc <= 2)
      Log.w("GAV3", z(paramString));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.l
 * JD-Core Version:    0.6.0
 */