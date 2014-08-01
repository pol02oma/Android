package com.google.android.gms.internal;

import android.util.Log;

public final class el
{
  private final String Cd;

  public el(String paramString)
  {
    this.Cd = ((String)er.f(paramString));
  }

  public boolean Q(int paramInt)
  {
    return Log.isLoggable(this.Cd, paramInt);
  }

  public void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (Q(6))
      Log.e(paramString1, paramString2, paramThrowable);
  }

  public void f(String paramString1, String paramString2)
  {
    if (Q(2))
      Log.v(paramString1, paramString2);
  }

  public void g(String paramString1, String paramString2)
  {
    if (Q(5))
      Log.w(paramString1, paramString2);
  }

  public void h(String paramString1, String paramString2)
  {
    if (Q(6))
      Log.e(paramString1, paramString2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.el
 * JD-Core Version:    0.6.0
 */