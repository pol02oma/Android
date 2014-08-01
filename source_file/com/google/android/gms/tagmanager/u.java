package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class u extends aj
{
  private static final String ID = a.N.toString();
  private static final String NAME = b.dn.toString();
  private static final String UC = b.cm.toString();
  private final DataLayer TN;

  public u(DataLayer paramDataLayer)
  {
    super(str, arrayOfString);
    this.TN = paramDataLayer;
  }

  public boolean iy()
  {
    return false;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    Object localObject = this.TN.get(di.j((d.a)paramMap.get(NAME)));
    if (localObject == null)
    {
      d.a locala = (d.a)paramMap.get(UC);
      if (locala != null)
        return locala;
      return di.ku();
    }
    return di.r(localObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.u
 * JD-Core Version:    0.6.0
 */