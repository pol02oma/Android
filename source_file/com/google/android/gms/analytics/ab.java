package com.google.android.gms.analytics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class ab
{
  private final Map<String, Integer> tW = new HashMap();
  private final Map<String, String> tX = new HashMap();
  private final boolean tY;
  private final String tZ;

  ab(String paramString, boolean paramBoolean)
  {
    this.tY = paramBoolean;
    this.tZ = paramString;
  }

  void c(String paramString, int paramInt)
  {
    if (!this.tY)
      return;
    Integer localInteger = (Integer)this.tW.get(paramString);
    if (localInteger == null)
      localInteger = Integer.valueOf(0);
    this.tW.put(paramString, Integer.valueOf(paramInt + localInteger.intValue()));
  }

  String cn()
  {
    if (!this.tY)
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.tZ);
    Iterator localIterator1 = this.tW.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str2 = (String)localIterator1.next();
      localStringBuilder.append("&").append(str2).append("=").append(this.tW.get(str2));
    }
    Iterator localIterator2 = this.tX.keySet().iterator();
    while (localIterator2.hasNext())
    {
      String str1 = (String)localIterator2.next();
      localStringBuilder.append("&").append(str1).append("=").append((String)this.tX.get(str1));
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.ab
 * JD-Core Version:    0.6.0
 */