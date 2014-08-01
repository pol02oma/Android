package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class cv extends aj
{
  private static final String ID = a.ad.toString();

  public cv()
  {
    super(ID, new String[0]);
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    return di.r(Integer.valueOf(Build.VERSION.SDK_INT));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cv
 * JD-Core Version:    0.6.0
 */