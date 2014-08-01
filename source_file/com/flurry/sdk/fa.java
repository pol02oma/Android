package com.flurry.sdk;

import android.text.TextUtils;
import java.lang.reflect.Method;

public final class fa
{
  private static final String a = fa.class.getSimpleName();

  public static fc a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    try
    {
      localfc = (fc)Class.forName(paramString).getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
      return localfc;
    }
    catch (Exception localException)
    {
      while (true)
      {
        ex.a(5, a, "FlurryModule " + paramString + " is not available:", localException);
        fc localfc = null;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.fa
 * JD-Core Version:    0.6.0
 */