package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class s extends aj
{
  private static final String ID = a.U.toString();
  private static final String TF;
  private static final String Up = b.cJ.toString();
  private final a Uq;

  static
  {
    TF = b.bi.toString();
  }

  public s(a parama)
  {
    super(str, arrayOfString);
    this.Uq = parama;
  }

  public boolean iy()
  {
    return false;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    String str = di.j((d.a)paramMap.get(Up));
    HashMap localHashMap = new HashMap();
    d.a locala1 = (d.a)paramMap.get(TF);
    if (locala1 != null)
    {
      Object localObject = di.o(locala1);
      if (!(localObject instanceof Map))
      {
        bh.w("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
        return di.ku();
      }
      Iterator localIterator = ((Map)localObject).entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localHashMap.put(localEntry.getKey().toString(), localEntry.getValue());
      }
    }
    try
    {
      d.a locala2 = di.r(this.Uq.b(str, localHashMap));
      return locala2;
    }
    catch (Exception localException)
    {
      bh.w("Custom macro/tag " + str + " threw exception " + localException.getMessage());
    }
    return di.ku();
  }

  public static abstract interface a
  {
    public abstract Object b(String paramString, Map<String, Object> paramMap);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.s
 * JD-Core Version:    0.6.0
 */