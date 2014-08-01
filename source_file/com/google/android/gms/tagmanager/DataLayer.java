package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Map<Ljava.lang.String;Ljava.lang.Object;>;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer
{
  public static final String EVENT_KEY = "event";
  public static final Object OBJECT_NOT_PRESENT = new Object();
  static final String[] Ur = "gtm.lifetime".toString().split("\\.");
  private static final Pattern Us = Pattern.compile("(\\d+)\\s*([smhd]?)");
  private final ConcurrentHashMap<b, Integer> Ut;
  private final Map<String, Object> Uu;
  private final ReentrantLock Uv;
  private final LinkedList<Map<String, Object>> Uw;
  private final c Ux;
  private final CountDownLatch Uy;

  DataLayer()
  {
    this(new c()
    {
      public void a(DataLayer.c.a parama)
      {
        parama.b(new ArrayList());
      }

      public void a(List<DataLayer.a> paramList, long paramLong)
      {
      }

      public void bi(String paramString)
      {
      }
    });
  }

  DataLayer(c paramc)
  {
    this.Ux = paramc;
    this.Ut = new ConcurrentHashMap();
    this.Uu = new HashMap();
    this.Uv = new ReentrantLock();
    this.Uw = new LinkedList();
    this.Uy = new CountDownLatch(1);
    iP();
  }

  private Object A(Map<String, Object> paramMap)
  {
    String[] arrayOfString = Ur;
    int i = arrayOfString.length;
    int j = 0;
    Object localObject2;
    for (Object localObject1 = paramMap; ; localObject1 = localObject2)
    {
      String str;
      if (j < i)
      {
        str = arrayOfString[j];
        if (!(localObject1 instanceof Map))
          localObject1 = null;
      }
      else
      {
        return localObject1;
      }
      localObject2 = ((Map)localObject1).get(str);
      j++;
    }
  }

  private List<a> B(Map<String, Object> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    a(paramMap, "", localArrayList);
    return localArrayList;
  }

  private void C(Map<String, Object> paramMap)
  {
    synchronized (this.Uu)
    {
      Iterator localIterator = paramMap.keySet().iterator();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        a(b(str, paramMap.get(str)), this.Uu);
      }
    }
    monitorexit;
    D(paramMap);
  }

  private void D(Map<String, Object> paramMap)
  {
    Iterator localIterator = this.Ut.keySet().iterator();
    while (localIterator.hasNext())
      ((b)localIterator.next()).v(paramMap);
  }

  private void a(Map<String, Object> paramMap, String paramString, Collection<a> paramCollection)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder().append(paramString);
      if (paramString.length() == 0);
      String str2;
      for (String str1 = ""; ; str1 = ".")
      {
        str2 = str1 + (String)localEntry.getKey();
        if (!(localEntry.getValue() instanceof Map))
          break label124;
        a((Map)localEntry.getValue(), str2, paramCollection);
        break;
      }
      label124: if (str2.equals("gtm.lifetime"))
        continue;
      paramCollection.add(new a(str2, localEntry.getValue()));
    }
  }

  static Long bh(String paramString)
  {
    Matcher localMatcher = Us.matcher(paramString);
    if (!localMatcher.matches())
    {
      bh.u("unknown _lifetime: " + paramString);
      return null;
    }
    long l1;
    try
    {
      long l2 = Long.parseLong(localMatcher.group(1));
      l1 = l2;
      if (l1 <= 0L)
      {
        bh.u("non-positive _lifetime: " + paramString);
        return null;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
      {
        bh.w("illegal number in _lifetime value: " + paramString);
        l1 = 0L;
      }
      String str = localMatcher.group(2);
      if (str.length() == 0)
        return Long.valueOf(l1);
      switch (str.charAt(0))
      {
      default:
        bh.w("unknown units in _lifetime: " + paramString);
        return null;
      case 's':
      case 'm':
      case 'h':
      case 'd':
      }
    }
    return Long.valueOf(l1 * 1000L);
    return Long.valueOf(60L * (l1 * 1000L));
    return Long.valueOf(60L * (60L * (l1 * 1000L)));
    return Long.valueOf(24L * (60L * (60L * (l1 * 1000L))));
  }

  private void iP()
  {
    this.Ux.a(new DataLayer.c.a()
    {
      public void b(List<DataLayer.a> paramList)
      {
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          DataLayer.a locala = (DataLayer.a)localIterator.next();
          DataLayer.a(DataLayer.this, DataLayer.this.b(locala.UA, locala.UB));
        }
        DataLayer.a(DataLayer.this).countDown();
      }
    });
  }

  private void iQ()
  {
    int j;
    for (int i = 0; ; i = j)
    {
      Map localMap = (Map)this.Uw.poll();
      if (localMap != null)
      {
        C(localMap);
        j = i + 1;
        if (j <= 500)
          continue;
        this.Uw.clear();
        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
      }
      return;
    }
  }

  public static List<Object> listOf(Object[] paramArrayOfObject)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramArrayOfObject.length; i++)
      localArrayList.add(paramArrayOfObject[i]);
    return localArrayList;
  }

  public static Map<String, Object> mapOf(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length % 2 != 0)
      throw new IllegalArgumentException("expected even number of key-value pairs");
    HashMap localHashMap = new HashMap();
    for (int i = 0; i < paramArrayOfObject.length; i += 2)
    {
      if (!(paramArrayOfObject[i] instanceof String))
        throw new IllegalArgumentException("key is not a string: " + paramArrayOfObject[i]);
      localHashMap.put((String)paramArrayOfObject[i], paramArrayOfObject[(i + 1)]);
    }
    return localHashMap;
  }

  private void x(Map<String, Object> paramMap)
  {
    this.Uv.lock();
    try
    {
      this.Uw.offer(paramMap);
      if (this.Uv.getHoldCount() == 1)
        iQ();
      y(paramMap);
      return;
    }
    finally
    {
      this.Uv.unlock();
    }
    throw localObject;
  }

  private void y(Map<String, Object> paramMap)
  {
    Long localLong = z(paramMap);
    if (localLong == null)
      return;
    List localList = B(paramMap);
    localList.remove("gtm.lifetime");
    this.Ux.a(localList, localLong.longValue());
  }

  private Long z(Map<String, Object> paramMap)
  {
    Object localObject = A(paramMap);
    if (localObject == null)
      return null;
    return bh(localObject.toString());
  }

  void a(b paramb)
  {
    this.Ut.put(paramb, Integer.valueOf(0));
  }

  void a(List<Object> paramList1, List<Object> paramList2)
  {
    while (paramList2.size() < paramList1.size())
      paramList2.add(null);
    int i = 0;
    if (i < paramList1.size())
    {
      Object localObject = paramList1.get(i);
      if ((localObject instanceof List))
      {
        if (!(paramList2.get(i) instanceof List))
          paramList2.set(i, new ArrayList());
        a((List)localObject, (List)paramList2.get(i));
      }
      while (true)
      {
        i++;
        break;
        if ((localObject instanceof Map))
        {
          if (!(paramList2.get(i) instanceof Map))
            paramList2.set(i, new HashMap());
          a((Map)localObject, (Map)paramList2.get(i));
          continue;
        }
        if (localObject == OBJECT_NOT_PRESENT)
          continue;
        paramList2.set(i, localObject);
      }
    }
  }

  void a(Map<String, Object> paramMap1, Map<String, Object> paramMap2)
  {
    Iterator localIterator = paramMap1.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramMap1.get(str);
      if ((localObject instanceof List))
      {
        if (!(paramMap2.get(str) instanceof List))
          paramMap2.put(str, new ArrayList());
        a((List)localObject, (List)paramMap2.get(str));
        continue;
      }
      if ((localObject instanceof Map))
      {
        if (!(paramMap2.get(str) instanceof Map))
          paramMap2.put(str, new HashMap());
        a((Map)localObject, (Map)paramMap2.get(str));
        continue;
      }
      paramMap2.put(str, localObject);
    }
  }

  Map<String, Object> b(String paramString, Object paramObject)
  {
    HashMap localHashMap1 = new HashMap();
    String[] arrayOfString = paramString.toString().split("\\.");
    int i = 0;
    HashMap localHashMap2;
    for (Object localObject = localHashMap1; i < -1 + arrayOfString.length; localObject = localHashMap2)
    {
      localHashMap2 = new HashMap();
      ((Map)localObject).put(arrayOfString[i], localHashMap2);
      i++;
    }
    ((Map)localObject).put(arrayOfString[(-1 + arrayOfString.length)], paramObject);
    return (Map<String, Object>)localHashMap1;
  }

  void bg(String paramString)
  {
    push(paramString, null);
    this.Ux.bi(paramString);
  }

  public Object get(String paramString)
  {
    while (true)
    {
      int j;
      Object localObject3;
      synchronized (this.Uu)
      {
        Map localMap2 = this.Uu;
        String[] arrayOfString = paramString.split("\\.");
        int i = arrayOfString.length;
        localObject2 = localMap2;
        j = 0;
        if (j >= i)
          continue;
        String str = arrayOfString[j];
        if (!(localObject2 instanceof Map))
          return null;
        localObject3 = ((Map)localObject2).get(str);
        if (localObject3 == null)
        {
          return null;
          return localObject2;
        }
      }
      j++;
      Object localObject2 = localObject3;
    }
  }

  public void push(String paramString, Object paramObject)
  {
    push(b(paramString, paramObject));
  }

  public void push(Map<String, Object> paramMap)
  {
    try
    {
      this.Uy.await();
      x(paramMap);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        bh.w("DataLayer.push: unexpected InterruptedException");
    }
  }

  public void pushEvent(String paramString, Map<String, Object> paramMap)
  {
    HashMap localHashMap = new HashMap(paramMap);
    localHashMap.put("event", paramString);
    push(localHashMap);
  }

  static final class a
  {
    public final String UA;
    public final Object UB;

    a(String paramString, Object paramObject)
    {
      this.UA = paramString;
      this.UB = paramObject;
    }

    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof a));
      a locala;
      do
      {
        return false;
        locala = (a)paramObject;
      }
      while ((!this.UA.equals(locala.UA)) || (!this.UB.equals(locala.UB)));
      return true;
    }

    public int hashCode()
    {
      Integer[] arrayOfInteger = new Integer[2];
      arrayOfInteger[0] = Integer.valueOf(this.UA.hashCode());
      arrayOfInteger[1] = Integer.valueOf(this.UB.hashCode());
      return Arrays.hashCode(arrayOfInteger);
    }

    public String toString()
    {
      return "Key: " + this.UA + " value: " + this.UB.toString();
    }
  }

  static abstract interface b
  {
    public abstract void v(Map<String, Object> paramMap);
  }

  static abstract interface c
  {
    public abstract void a(a parama);

    public abstract void a(List<DataLayer.a> paramList, long paramLong);

    public abstract void bi(String paramString);

    public static abstract interface a
    {
      public abstract void b(List<DataLayer.a> paramList);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.DataLayer
 * JD-Core Version:    0.6.0
 */