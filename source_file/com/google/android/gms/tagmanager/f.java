package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class f extends aj
{
  private static final String ID = a.H.toString();
  private final Context mContext;

  public f(Context paramContext)
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
    return di.r(this.mContext.getPackageName());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.f
 * JD-Core Version:    0.6.0
 */