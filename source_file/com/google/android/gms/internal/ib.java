package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.R.string;
import java.util.LinkedHashSet;
import java.util.Locale;

public class ib
{
  private static final String TAG = ib.class.getSimpleName();
  private final hl<hg> Lk;
  private final LinkedHashSet<String> OA;
  private final Locale Or;
  private final ic Os;
  private final id Oz;
  private final Context mContext;

  public ib(Context paramContext, Locale paramLocale, hl<hg> paramhl)
  {
    this.mContext = paramContext;
    this.Lk = paramhl;
    this.Or = paramLocale;
    this.Oz = new id(paramContext, paramLocale);
    this.OA = new LinkedHashSet();
    this.OA.add(paramContext.getString(R.string.location_client_powered_by_google));
    String str = this.mContext.getPackageName();
    try
    {
      i = this.mContext.getPackageManager().getPackageInfo(str, 0).versionCode;
      this.Os = new ic(this.Lk, str, i);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        int i = -1;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ib
 * JD-Core Version:    0.6.0
 */