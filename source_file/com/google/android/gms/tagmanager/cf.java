package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class cf extends aj
{
  private static final String ID = a.Z.toString();
  private static final String Wa = b.dl.toString();
  private static final String Wb = b.dk.toString();

  public cf()
  {
    super(ID, new String[0]);
  }

  public boolean iy()
  {
    return false;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    d.a locala1 = (d.a)paramMap.get(Wa);
    d.a locala2 = (d.a)paramMap.get(Wb);
    double d1;
    double d2;
    if ((locala1 != null) && (locala1 != di.ku()) && (locala2 != null) && (locala2 != di.ku()))
    {
      dh localdh1 = di.k(locala1);
      dh localdh2 = di.k(locala2);
      if ((localdh1 != di.ks()) && (localdh2 != di.ks()))
      {
        double d3 = localdh1.doubleValue();
        d1 = localdh2.doubleValue();
        if (d3 <= d1)
          d2 = d3;
      }
    }
    while (true)
    {
      return di.r(Long.valueOf(Math.round(d2 + Math.random() * (d1 - d2))));
      d1 = 2147483647.0D;
      d2 = 0.0D;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cf
 * JD-Core Version:    0.6.0
 */