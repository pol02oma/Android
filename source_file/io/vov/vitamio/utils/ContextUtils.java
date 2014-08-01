package io.vov.vitamio.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class ContextUtils
{
  public static String fixLastSlash(String paramString)
  {
    if (paramString == null);
    for (String str = "/"; ; str = paramString.trim() + "/")
    {
      if ((str.length() > 2) && (str.charAt(-2 + str.length()) == '/'))
        str = str.substring(0, -1 + str.length());
      return str;
    }
  }

  public static String getDataDir(Context paramContext)
  {
    ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
    if (localApplicationInfo.dataDir != null)
      return fixLastSlash(localApplicationInfo.dataDir);
    return "/data/data/" + localApplicationInfo.packageName + "/";
  }

  public static int getVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getApplicationInfo().packageName, 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      Log.e("getVersionInt", localException);
    }
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.utils.ContextUtils
 * JD-Core Version:    0.6.0
 */