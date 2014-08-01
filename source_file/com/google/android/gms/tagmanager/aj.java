package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class aj
{
  private final Set<String> UW;
  private final String UX;

  public aj(String paramString, String[] paramArrayOfString)
  {
    this.UX = paramString;
    this.UW = new HashSet(paramArrayOfString.length);
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
    {
      String str = paramArrayOfString[j];
      this.UW.add(str);
    }
  }

  boolean a(Set<String> paramSet)
  {
    return paramSet.containsAll(this.UW);
  }

  public abstract boolean iy();

  public String jc()
  {
    return this.UX;
  }

  public Set<String> jd()
  {
    return this.UW;
  }

  public abstract d.a u(Map<String, d.a> paramMap);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.aj
 * JD-Core Version:    0.6.0
 */