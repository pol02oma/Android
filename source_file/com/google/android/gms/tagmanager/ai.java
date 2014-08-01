package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.c.c;
import com.google.android.gms.internal.c.d;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ai
{
  private static void a(DataLayer paramDataLayer, c.d paramd)
  {
    d.a[] arrayOfa = paramd.fd;
    int i = arrayOfa.length;
    for (int j = 0; j < i; j++)
      paramDataLayer.bg(di.j(arrayOfa[j]));
  }

  public static void a(DataLayer paramDataLayer, c.i parami)
  {
    if (parami.fT == null)
    {
      bh.w("supplemental missing experimentSupplemental");
      return;
    }
    a(paramDataLayer, parami.fT);
    b(paramDataLayer, parami.fT);
    c(paramDataLayer, parami.fT);
  }

  private static void b(DataLayer paramDataLayer, c.d paramd)
  {
    d.a[] arrayOfa = paramd.fc;
    int i = arrayOfa.length;
    for (int j = 0; j < i; j++)
    {
      Map localMap = c(arrayOfa[j]);
      if (localMap == null)
        continue;
      paramDataLayer.push(localMap);
    }
  }

  private static Map<String, Object> c(d.a parama)
  {
    Object localObject = di.o(parama);
    if (!(localObject instanceof Map))
    {
      bh.w("value: " + localObject + " is not a map value, ignored.");
      return null;
    }
    return (Map)localObject;
  }

  private static void c(DataLayer paramDataLayer, c.d paramd)
  {
    c.c[] arrayOfc = paramd.fe;
    int i = arrayOfc.length;
    int j = 0;
    while (j < i)
    {
      c.c localc = arrayOfc[j];
      if (localc.eX == null)
      {
        bh.w("GaExperimentRandom: No key");
        j++;
        continue;
      }
      Object localObject1 = paramDataLayer.get(localc.eX);
      Long localLong;
      label64: Map localMap;
      if (!(localObject1 instanceof Number))
      {
        localLong = null;
        long l1 = localc.eY;
        long l2 = localc.eZ;
        if ((!localc.fa) || (localLong == null) || (localLong.longValue() < l1) || (localLong.longValue() > l2))
        {
          if (l1 > l2)
            break label251;
          localObject1 = Long.valueOf(Math.round(Math.random() * (l2 - l1) + l1));
        }
        paramDataLayer.bg(localc.eX);
        localMap = paramDataLayer.b(localc.eX, localObject1);
        if (localc.fb > 0L)
        {
          if (localMap.containsKey("gtm"))
            break label259;
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = "lifetime";
          arrayOfObject[1] = Long.valueOf(localc.fb);
          localMap.put("gtm", DataLayer.mapOf(arrayOfObject));
        }
      }
      while (true)
      {
        paramDataLayer.push(localMap);
        break;
        localLong = Long.valueOf(((Number)localObject1).longValue());
        break label64;
        label251: bh.w("GaExperimentRandom: random range invalid");
        break;
        label259: Object localObject2 = localMap.get("gtm");
        if ((localObject2 instanceof Map))
        {
          ((Map)localObject2).put("lifetime", Long.valueOf(localc.fb));
          continue;
        }
        bh.w("GaExperimentRandom: gtm not a map");
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ai
 * JD-Core Version:    0.6.0
 */