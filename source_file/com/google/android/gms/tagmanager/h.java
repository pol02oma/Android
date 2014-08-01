package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class h extends aj
{
  private static final String ID = a.J.toString();
  private final Context mContext;

  public h(Context paramContext)
  {
    super(ID, new String[0]);
    this.mContext = paramContext;
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    try
    {
      d.a locala = di.r(Integer.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode));
      return locala;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      bh.t("Package name " + this.mContext.getPackageName() + " not found. " + localNameNotFoundException.getMessage());
    }
    return di.ku();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.h
 * JD-Core Version:    0.6.0
 */