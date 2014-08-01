package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class di
{
  private static final Object XI = null;
  private static Long XJ = new Long(0L);
  private static Double XK = new Double(0.0D);
  private static dh XL = dh.v(0L);
  private static String XM = new String("");
  private static Boolean XN = new Boolean(false);
  private static List<Object> XO = new ArrayList(0);
  private static Map<Object, Object> XP = new HashMap();
  private static d.a XQ = r(XM);

  public static d.a bI(String paramString)
  {
    d.a locala = new d.a();
    locala.type = 5;
    locala.gd = paramString;
    return locala;
  }

  private static dh bJ(String paramString)
  {
    try
    {
      dh localdh = dh.bH(paramString);
      return localdh;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      bh.t("Failed to convert '" + paramString + "' to a number.");
    }
    return XL;
  }

  private static Long bK(String paramString)
  {
    dh localdh = bJ(paramString);
    if (localdh == XL)
      return XJ;
    return Long.valueOf(localdh.longValue());
  }

  private static Double bL(String paramString)
  {
    dh localdh = bJ(paramString);
    if (localdh == XL)
      return XK;
    return Double.valueOf(localdh.doubleValue());
  }

  private static Boolean bM(String paramString)
  {
    if ("true".equalsIgnoreCase(paramString))
      return Boolean.TRUE;
    if ("false".equalsIgnoreCase(paramString))
      return Boolean.FALSE;
    return XN;
  }

  private static double getDouble(Object paramObject)
  {
    if ((paramObject instanceof Number))
      return ((Number)paramObject).doubleValue();
    bh.t("getDouble received non-Number");
    return 0.0D;
  }

  public static String j(d.a parama)
  {
    return m(o(parama));
  }

  public static dh k(d.a parama)
  {
    return n(o(parama));
  }

  public static Object ko()
  {
    return XI;
  }

  public static Long kp()
  {
    return XJ;
  }

  public static Double kq()
  {
    return XK;
  }

  public static Boolean kr()
  {
    return XN;
  }

  public static dh ks()
  {
    return XL;
  }

  public static String kt()
  {
    return XM;
  }

  public static d.a ku()
  {
    return XQ;
  }

  public static Long l(d.a parama)
  {
    return o(o(parama));
  }

  public static Double m(d.a parama)
  {
    return p(o(parama));
  }

  public static String m(Object paramObject)
  {
    if (paramObject == null)
      return XM;
    return paramObject.toString();
  }

  public static dh n(Object paramObject)
  {
    if ((paramObject instanceof dh))
      return (dh)paramObject;
    if (t(paramObject))
      return dh.v(u(paramObject));
    if (s(paramObject))
      return dh.a(Double.valueOf(getDouble(paramObject)));
    return bJ(m(paramObject));
  }

  public static Boolean n(d.a parama)
  {
    return q(o(parama));
  }

  public static Long o(Object paramObject)
  {
    if (t(paramObject))
      return Long.valueOf(u(paramObject));
    return bK(m(paramObject));
  }

  public static Object o(d.a parama)
  {
    int i = 0;
    if (parama == null)
      return XI;
    switch (parama.type)
    {
    default:
      bh.t("Failed to convert a value of type: " + parama.type);
      return XI;
    case 1:
      return parama.fY;
    case 2:
      ArrayList localArrayList = new ArrayList(parama.fZ.length);
      d.a[] arrayOfa2 = parama.fZ;
      int k = arrayOfa2.length;
      while (i < k)
      {
        Object localObject3 = o(arrayOfa2[i]);
        if (localObject3 == XI)
          return XI;
        localArrayList.add(localObject3);
        i++;
      }
      return localArrayList;
    case 3:
      if (parama.ga.length != parama.gb.length)
      {
        bh.t("Converting an invalid value to object: " + parama.toString());
        return XI;
      }
      HashMap localHashMap = new HashMap(parama.gb.length);
      while (i < parama.ga.length)
      {
        Object localObject1 = o(parama.ga[i]);
        Object localObject2 = o(parama.gb[i]);
        if ((localObject1 == XI) || (localObject2 == XI))
          return XI;
        localHashMap.put(localObject1, localObject2);
        i++;
      }
      return localHashMap;
    case 4:
      bh.t("Trying to convert a macro reference to object");
      return XI;
    case 5:
      bh.t("Trying to convert a function id to object");
      return XI;
    case 6:
      return Long.valueOf(parama.ge);
    case 7:
      StringBuffer localStringBuffer = new StringBuffer();
      d.a[] arrayOfa1 = parama.gg;
      int j = arrayOfa1.length;
      while (i < j)
      {
        String str = j(arrayOfa1[i]);
        if (str == XM)
          return XI;
        localStringBuffer.append(str);
        i++;
      }
      return localStringBuffer.toString();
    case 8:
    }
    return Boolean.valueOf(parama.gf);
  }

  public static Double p(Object paramObject)
  {
    if (s(paramObject))
      return Double.valueOf(getDouble(paramObject));
    return bL(m(paramObject));
  }

  public static Boolean q(Object paramObject)
  {
    if ((paramObject instanceof Boolean))
      return (Boolean)paramObject;
    return bM(m(paramObject));
  }

  public static d.a r(Object paramObject)
  {
    boolean bool1 = false;
    d.a locala1 = new d.a();
    if ((paramObject instanceof d.a))
      return (d.a)paramObject;
    if ((paramObject instanceof String))
    {
      locala1.type = 1;
      locala1.fY = ((String)paramObject);
    }
    while (true)
    {
      locala1.gi = bool1;
      return locala1;
      if ((paramObject instanceof List))
      {
        locala1.type = 2;
        List localList = (List)paramObject;
        ArrayList localArrayList3 = new ArrayList(localList.size());
        Iterator localIterator2 = localList.iterator();
        boolean bool4 = false;
        if (localIterator2.hasNext())
        {
          d.a locala4 = r(localIterator2.next());
          if (locala4 == XQ)
            return XQ;
          if ((bool4) || (locala4.gi));
          for (boolean bool5 = true; ; bool5 = false)
          {
            localArrayList3.add(locala4);
            bool4 = bool5;
            break;
          }
        }
        locala1.fZ = ((d.a[])localArrayList3.toArray(new d.a[0]));
        bool1 = bool4;
        continue;
      }
      if ((paramObject instanceof Map))
      {
        locala1.type = 3;
        Set localSet = ((Map)paramObject).entrySet();
        ArrayList localArrayList1 = new ArrayList(localSet.size());
        ArrayList localArrayList2 = new ArrayList(localSet.size());
        Iterator localIterator1 = localSet.iterator();
        boolean bool2 = false;
        if (localIterator1.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator1.next();
          d.a locala2 = r(localEntry.getKey());
          d.a locala3 = r(localEntry.getValue());
          if ((locala2 == XQ) || (locala3 == XQ))
            return XQ;
          if ((bool2) || (locala2.gi) || (locala3.gi));
          for (boolean bool3 = true; ; bool3 = false)
          {
            localArrayList1.add(locala2);
            localArrayList2.add(locala3);
            bool2 = bool3;
            break;
          }
        }
        locala1.ga = ((d.a[])localArrayList1.toArray(new d.a[0]));
        locala1.gb = ((d.a[])localArrayList2.toArray(new d.a[0]));
        bool1 = bool2;
        continue;
      }
      if (s(paramObject))
      {
        locala1.type = 1;
        locala1.fY = paramObject.toString();
        bool1 = false;
        continue;
      }
      if (t(paramObject))
      {
        locala1.type = 6;
        locala1.ge = u(paramObject);
        bool1 = false;
        continue;
      }
      if (!(paramObject instanceof Boolean))
        break;
      locala1.type = 8;
      locala1.gf = ((Boolean)paramObject).booleanValue();
      bool1 = false;
    }
    StringBuilder localStringBuilder = new StringBuilder().append("Converting to Value from unknown object type: ");
    if (paramObject == null);
    for (String str = "null"; ; str = paramObject.getClass().toString())
    {
      bh.t(str);
      return XQ;
    }
  }

  private static boolean s(Object paramObject)
  {
    return ((paramObject instanceof Double)) || ((paramObject instanceof Float)) || (((paramObject instanceof dh)) && (((dh)paramObject).kj()));
  }

  private static boolean t(Object paramObject)
  {
    return ((paramObject instanceof Byte)) || ((paramObject instanceof Short)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || (((paramObject instanceof dh)) && (((dh)paramObject).kk()));
  }

  private static long u(Object paramObject)
  {
    if ((paramObject instanceof Number))
      return ((Number)paramObject).longValue();
    bh.t("getInt64 received non-Number");
    return 0L;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.di
 * JD-Core Version:    0.6.0
 */