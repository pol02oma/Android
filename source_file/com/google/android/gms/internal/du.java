package com.google.android.gms.internal;

import android.util.Log;

public class du
{
  private static boolean ye = false;
  private final String mTag;
  private boolean yf;
  private boolean yg;
  private String yh;

  public du(String paramString)
  {
    this(paramString, di());
  }

  public du(String paramString, boolean paramBoolean)
  {
    this.mTag = paramString;
    this.yf = paramBoolean;
    this.yg = false;
  }

  public static boolean di()
  {
    return ye;
  }

  private String e(String paramString, Object[] paramArrayOfObject)
  {
    String str = String.format(paramString, paramArrayOfObject);
    if (this.yh != null)
      str = this.yh + str;
    return str;
  }

  public void U(String paramString)
  {
    this.yh = String.format("[%s] ", new Object[] { paramString });
  }

  public void a(String paramString, Object[] paramArrayOfObject)
  {
    if (dh())
      Log.v(this.mTag, e(paramString, paramArrayOfObject));
  }

  public void a(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if ((dg()) || (ye))
      Log.d(this.mTag, e(paramString, paramArrayOfObject), paramThrowable);
  }

  public void b(String paramString, Object[] paramArrayOfObject)
  {
    if ((dg()) || (ye))
      Log.d(this.mTag, e(paramString, paramArrayOfObject));
  }

  public void c(String paramString, Object[] paramArrayOfObject)
  {
    Log.i(this.mTag, e(paramString, paramArrayOfObject));
  }

  public void d(String paramString, Object[] paramArrayOfObject)
  {
    Log.w(this.mTag, e(paramString, paramArrayOfObject));
  }

  public boolean dg()
  {
    return this.yf;
  }

  public boolean dh()
  {
    return this.yg;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.du
 * JD-Core Version:    0.6.0
 */