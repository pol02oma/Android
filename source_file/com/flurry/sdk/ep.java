package com.flurry.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

public class ep
{
  private static final String a = ep.class.getSimpleName();
  private static String b;
  private static String c;

  public static String a()
  {
    if (!TextUtils.isEmpty(b))
      return b;
    if (!TextUtils.isEmpty(c))
      return c;
    c = b();
    return c;
  }

  public static void a(String paramString)
  {
    b = paramString;
  }

  private static String b()
  {
    try
    {
      Context localContext = eg.a().b();
      PackageInfo localPackageInfo = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0);
      if (localPackageInfo.versionName != null)
        return localPackageInfo.versionName;
      if (localPackageInfo.versionCode != 0)
      {
        String str = Integer.toString(localPackageInfo.versionCode);
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      ex.a(6, a, "", localThrowable);
    }
    return "Unknown";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.ep
 * JD-Core Version:    0.6.0
 */