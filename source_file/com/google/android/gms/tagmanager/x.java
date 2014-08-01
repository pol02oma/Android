package com.google.android.gms.tagmanager;

import android.util.Log;

class x
  implements bi
{
  private int rc = 5;

  public void b(String paramString, Throwable paramThrowable)
  {
    if (this.rc <= 5)
      Log.w("GoogleTagManager", paramString, paramThrowable);
  }

  public void c(String paramString, Throwable paramThrowable)
  {
    if (this.rc <= 6)
      Log.e("GoogleTagManager", paramString, paramThrowable);
  }

  public void s(String paramString)
  {
    if (this.rc <= 3)
      Log.d("GoogleTagManager", paramString);
  }

  public void setLogLevel(int paramInt)
  {
    this.rc = paramInt;
  }

  public void t(String paramString)
  {
    if (this.rc <= 6)
      Log.e("GoogleTagManager", paramString);
  }

  public void u(String paramString)
  {
    if (this.rc <= 4)
      Log.i("GoogleTagManager", paramString);
  }

  public void v(String paramString)
  {
    if (this.rc <= 2)
      Log.v("GoogleTagManager", paramString);
  }

  public void w(String paramString)
  {
    if (this.rc <= 5)
      Log.w("GoogleTagManager", paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.x
 * JD-Core Version:    0.6.0
 */