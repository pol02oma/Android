package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class b extends aj
{
  private static final String ID = com.google.android.gms.internal.a.F.toString();
  private final a TC;

  public b(Context paramContext)
  {
    this(a.E(paramContext));
  }

  b(a parama)
  {
    super(ID, new String[0]);
    this.TC = parama;
  }

  public boolean iy()
  {
    return false;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    String str = this.TC.iu();
    if (str == null)
      return di.ku();
    return di.r(str);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.b
 * JD-Core Version:    0.6.0
 */