package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class g extends aj
{
  private static final String ID = a.I.toString();
  private final Context mContext;

  public g(Context paramContext)
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
      PackageManager localPackageManager = this.mContext.getPackageManager();
      d.a locala = di.r(localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
      return locala;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      bh.c("App name is not found.", localNameNotFoundException);
    }
    return di.ku();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.g
 * JD-Core Version:    0.6.0
 */