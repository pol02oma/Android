package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.util.Map;

abstract class dd extends cd
{
  public dd(String paramString)
  {
    super(paramString);
  }

  protected boolean a(d.a parama1, d.a parama2, Map<String, d.a> paramMap)
  {
    String str1 = di.j(parama1);
    String str2 = di.j(parama2);
    if ((str1 == di.kt()) || (str2 == di.kt()))
      return false;
    return a(str1, str2, paramMap);
  }

  protected abstract boolean a(String paramString1, String paramString2, Map<String, d.a> paramMap);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.dd
 * JD-Core Version:    0.6.0
 */