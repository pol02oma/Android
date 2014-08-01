package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

class ay
{
  private static String Vm;
  static Map<String, String> Vn = new HashMap();

  static void bq(String paramString)
  {
    monitorenter;
    try
    {
      Vm = paramString;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  static void c(Context paramContext, String paramString)
  {
    cz.a(paramContext, "gtm_install_referrer", "referrer", paramString);
    e(paramContext, paramString);
  }

  static String d(Context paramContext, String paramString)
  {
    if (Vm == null)
      monitorenter;
    try
    {
      if (Vm == null)
      {
        SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("gtm_install_referrer", 0);
        if (localSharedPreferences == null)
          break label51;
        Vm = localSharedPreferences.getString("referrer", "");
      }
      while (true)
      {
        return l(Vm, paramString);
        label51: Vm = "";
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  static String e(Context paramContext, String paramString1, String paramString2)
  {
    String str = (String)Vn.get(paramString1);
    SharedPreferences localSharedPreferences;
    if (str == null)
    {
      localSharedPreferences = paramContext.getSharedPreferences("gtm_click_referrers", 0);
      if (localSharedPreferences == null)
        break label59;
    }
    label59: for (str = localSharedPreferences.getString(paramString1, ""); ; str = "")
    {
      Vn.put(paramString1, str);
      return l(str, paramString2);
    }
  }

  static void e(Context paramContext, String paramString)
  {
    String str = l(paramString, "conv");
    if ((str != null) && (str.length() > 0))
    {
      Vn.put(str, paramString);
      cz.a(paramContext, "gtm_click_referrers", str, paramString);
    }
  }

  static String l(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      if (paramString1.length() > 0)
        return paramString1;
      return null;
    }
    return Uri.parse("http://hostname/?" + paramString1).getQueryParameter(paramString2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ay
 * JD-Core Version:    0.6.0
 */