package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class p extends aj
{
  private static final String ID = a.O.toString();
  private final String Un;

  public p(String paramString)
  {
    super(ID, new String[0]);
    this.Un = paramString;
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    if (this.Un == null)
      return di.ku();
    return di.r(this.Un);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.p
 * JD-Core Version:    0.6.0
 */