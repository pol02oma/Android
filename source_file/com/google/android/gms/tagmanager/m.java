package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class m extends aj
{
  private static final String ID = a.L.toString();
  private static final String VALUE = b.eH.toString();

  public m()
  {
    super(str, arrayOfString);
  }

  public static String iB()
  {
    return ID;
  }

  public static String iC()
  {
    return VALUE;
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    return (d.a)paramMap.get(VALUE);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.m
 * JD-Core Version:    0.6.0
 */