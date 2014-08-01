package io.vov.vitamio.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.Locale;

public class Device
{
  public static String getDeviceFeatures(Context paramContext)
  {
    return getIdentifiers(paramContext) + getSystemFeatures() + getScreenFeatures(paramContext);
  }

  @SuppressLint({"NewApi"})
  public static String getIdentifiers(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getPair("serial", Build.SERIAL));
    localStringBuilder.append(getPair("android_id", Settings.Secure.getString(paramContext.getContentResolver(), "android_id")));
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    localStringBuilder.append(getPair("sim_country_iso", localTelephonyManager.getSimCountryIso()));
    localStringBuilder.append(getPair("network_operator_name", localTelephonyManager.getNetworkOperatorName()));
    localStringBuilder.append(getPair("unique_id", Crypto.md5(localStringBuilder.toString())));
    return localStringBuilder.toString();
  }

  public static String getLocale()
  {
    Locale localLocale = Locale.getDefault();
    if (localLocale != null)
    {
      String str = localLocale.getLanguage();
      Log.i("getLocale " + str, new Object[0]);
      if (str != null)
        return str.toLowerCase();
    }
    return "en";
  }

  private static String getPair(String paramString1, String paramString2)
  {
    String str1;
    if (paramString1 == null)
    {
      str1 = "";
      if (paramString2 != null)
        break label51;
    }
    label51: for (String str2 = ""; ; str2 = paramString2.trim())
    {
      return "&" + str1 + "=" + str2;
      str1 = paramString1.trim();
      break;
    }
  }

  public static String getScreenFeatures(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    localStringBuilder.append(getPair("screen_density", "" + localDisplayMetrics.density));
    localStringBuilder.append(getPair("screen_density_dpi", "" + localDisplayMetrics.densityDpi));
    localStringBuilder.append(getPair("screen_height_pixels", "" + localDisplayMetrics.heightPixels));
    localStringBuilder.append(getPair("screen_width_pixels", "" + localDisplayMetrics.widthPixels));
    localStringBuilder.append(getPair("screen_scaled_density", "" + localDisplayMetrics.scaledDensity));
    localStringBuilder.append(getPair("screen_xdpi", "" + localDisplayMetrics.xdpi));
    localStringBuilder.append(getPair("screen_ydpi", "" + localDisplayMetrics.ydpi));
    return localStringBuilder.toString();
  }

  public static String getSystemFeatures()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getPair("android_release", Build.VERSION.RELEASE));
    localStringBuilder.append(getPair("android_sdk_int", "" + Build.VERSION.SDK_INT));
    localStringBuilder.append(getPair("device_cpu_abi", Build.CPU_ABI));
    localStringBuilder.append(getPair("device_model", Build.MODEL));
    localStringBuilder.append(getPair("device_manufacturer", Build.MANUFACTURER));
    localStringBuilder.append(getPair("device_board", Build.BOARD));
    localStringBuilder.append(getPair("device_fingerprint", Build.FINGERPRINT));
    localStringBuilder.append(getPair("device_cpu_feature", CPU.getFeatureString()));
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.utils.Device
 * JD-Core Version:    0.6.0
 */