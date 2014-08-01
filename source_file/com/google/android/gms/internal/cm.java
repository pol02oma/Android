package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.Locale;

public final class cm
{
  public final int oM;
  public final boolean oN;
  public final boolean oO;
  public final String oP;
  public final String oQ;
  public final boolean oR;
  public final boolean oS;
  public final boolean oT;
  public final String oU;
  public final String oV;
  public final int oW;
  public final int oX;
  public final int oY;
  public final int oZ;
  public final int pa;
  public final int pb;
  public final float pc;
  public final int pd;
  public final int pe;

  public cm(Context paramContext)
  {
    AudioManager localAudioManager = (AudioManager)paramContext.getSystemService("audio");
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    Locale localLocale = Locale.getDefault();
    PackageManager localPackageManager = paramContext.getPackageManager();
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    this.oM = localAudioManager.getMode();
    boolean bool2;
    if (a(localPackageManager, "geo:0,0?q=donuts") != null)
    {
      bool2 = bool1;
      this.oN = bool2;
      if (a(localPackageManager, "http://www.google.com") == null)
        break label249;
    }
    while (true)
    {
      this.oO = bool1;
      this.oP = localTelephonyManager.getNetworkOperator();
      this.oQ = localLocale.getCountry();
      this.oR = cz.aW();
      this.oS = localAudioManager.isMusicActive();
      this.oT = localAudioManager.isSpeakerphoneOn();
      this.oU = localLocale.getLanguage();
      this.oV = a(localPackageManager);
      this.oW = localAudioManager.getStreamVolume(3);
      this.oX = a(paramContext, localConnectivityManager, localPackageManager);
      this.oY = localTelephonyManager.getNetworkType();
      this.oZ = localTelephonyManager.getPhoneType();
      this.pa = localAudioManager.getRingerMode();
      this.pb = localAudioManager.getStreamVolume(2);
      this.pc = localDisplayMetrics.density;
      this.pd = localDisplayMetrics.widthPixels;
      this.pe = localDisplayMetrics.heightPixels;
      return;
      bool2 = false;
      break;
      label249: bool1 = false;
    }
  }

  private static int a(Context paramContext, ConnectivityManager paramConnectivityManager, PackageManager paramPackageManager)
  {
    int i = -2;
    if (cv.a(paramPackageManager, paramContext.getPackageName(), "android.permission.ACCESS_NETWORK_STATE"))
    {
      NetworkInfo localNetworkInfo = paramConnectivityManager.getActiveNetworkInfo();
      if (localNetworkInfo != null)
        i = localNetworkInfo.getType();
    }
    else
    {
      return i;
    }
    return -1;
  }

  private static ResolveInfo a(PackageManager paramPackageManager, String paramString)
  {
    return paramPackageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)), 65536);
  }

  private static String a(PackageManager paramPackageManager)
  {
    ResolveInfo localResolveInfo = a(paramPackageManager, "market://details?id=com.google.android.gms.ads");
    if (localResolveInfo == null);
    while (true)
    {
      return null;
      ActivityInfo localActivityInfo = localResolveInfo.activityInfo;
      if (localActivityInfo == null)
        continue;
      try
      {
        PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(localActivityInfo.packageName, 0);
        if (localPackageInfo == null)
          continue;
        String str = localPackageInfo.versionCode + "." + localActivityInfo.packageName;
        return str;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
      }
    }
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cm
 * JD-Core Version:    0.6.0
 */