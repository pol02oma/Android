package com.flurry.sdk;

import android.os.Build.VERSION;
import android.text.TextUtils;

public final class ez extends fb
{
  private final int a;

  public ez(String paramString, int paramInt)
  {
    super(a(paramString, paramInt));
    this.a = paramInt;
  }

  private static fc a(String paramString, int paramInt)
  {
    boolean bool = b(paramString, paramInt);
    fc localfc = null;
    if (bool)
      localfc = fa.a(paramString);
    return localfc;
  }

  private static boolean b(String paramString, int paramInt)
  {
    return (!TextUtils.isEmpty(paramString)) && (Build.VERSION.SDK_INT >= paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.ez
 * JD-Core Version:    0.6.0
 */