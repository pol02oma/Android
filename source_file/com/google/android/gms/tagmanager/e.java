package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class e extends aj
{
  private static final String ID = a.ah.toString();
  private static final String TD = b.bS.toString();
  private static final String TE = b.bV.toString();
  private final Context kL;

  public e(Context paramContext)
  {
    super(str, arrayOfString);
    this.kL = paramContext;
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    d.a locala1 = (d.a)paramMap.get(TE);
    if (locala1 == null)
      return di.ku();
    String str1 = di.j(locala1);
    d.a locala2 = (d.a)paramMap.get(TD);
    if (locala2 != null);
    for (String str2 = di.j(locala2); ; str2 = null)
    {
      String str3 = ay.e(this.kL, str1, str2);
      if (str3 == null)
        break;
      return di.r(str3);
    }
    return di.ku();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.e
 * JD-Core Version:    0.6.0
 */