package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class aa extends aj
{
  private static final String ID = a.Q.toString();

  public aa()
  {
    super(ID, new String[0]);
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if ((!str2.startsWith(str1)) && (!str1.equals("unknown")))
      str2 = str1 + " " + str2;
    return di.r(str2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.aa
 * JD-Core Version:    0.6.0
 */