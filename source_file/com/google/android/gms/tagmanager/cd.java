package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

abstract class cd extends aj
{
  private static final String US = b.bt.toString();
  private static final String VQ = b.bu.toString();

  public cd(String paramString)
  {
    super(paramString, arrayOfString);
  }

  protected abstract boolean a(d.a parama1, d.a parama2, Map<String, d.a> paramMap);

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    Iterator localIterator = paramMap.values().iterator();
    while (localIterator.hasNext())
      if ((d.a)localIterator.next() == di.ku())
        return di.r(Boolean.valueOf(false));
    d.a locala1 = (d.a)paramMap.get(US);
    d.a locala2 = (d.a)paramMap.get(VQ);
    if ((locala1 == null) || (locala2 == null));
    for (boolean bool = false; ; bool = a(locala1, locala2, paramMap))
      return di.r(Boolean.valueOf(bool));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cd
 * JD-Core Version:    0.6.0
 */