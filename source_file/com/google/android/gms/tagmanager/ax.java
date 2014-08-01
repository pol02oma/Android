package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ax extends aj
{
  private static final String ID = a.am.toString();
  private static final String TD = b.bS.toString();
  private final Context kL;

  public ax(Context paramContext)
  {
    super(ID, new String[0]);
    this.kL = paramContext;
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    if ((d.a)paramMap.get(TD) != null);
    for (String str1 = di.j((d.a)paramMap.get(TD)); ; str1 = null)
    {
      String str2 = ay.d(this.kL, str1);
      if (str2 == null)
        break;
      return di.r(str2);
    }
    return di.ku();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ax
 * JD-Core Version:    0.6.0
 */