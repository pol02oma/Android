package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;

class bm
{
  int iA()
  {
    return Build.VERSION.SDK_INT;
  }

  public bl ji()
  {
    if (iA() < 8)
      return new av();
    return new aw();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.bm
 * JD-Core Version:    0.6.0
 */