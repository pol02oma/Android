package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class dj extends dg
{
  private static final String ID = a.aQ.toString();
  private static final String XR = b.bg.toString();
  private static final String XS = b.bp.toString();
  private static final String XT = b.bo.toString();
  private static final String XU = b.er.toString();
  private static final String XV = b.et.toString();
  private static final String XW = b.ev.toString();
  private static Map<String, String> XX;
  private static Map<String, String> XY;
  private final DataLayer TN;
  private final Set<String> XZ;
  private final df Ya;

  public dj(Context paramContext, DataLayer paramDataLayer)
  {
    this(paramContext, paramDataLayer, new df(paramContext));
  }

  dj(Context paramContext, DataLayer paramDataLayer, df paramdf)
  {
    super(ID, new String[0]);
    this.TN = paramDataLayer;
    this.Ya = paramdf;
    this.XZ = new HashSet();
    this.XZ.add("");
    this.XZ.add("0");
    this.XZ.add("false");
  }

  private Map<String, String> E(Map<String, d.a> paramMap)
  {
    d.a locala = (d.a)paramMap.get(XV);
    if (locala != null)
      return c(locala);
    if (XX == null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("transactionId", "&ti");
      localHashMap.put("transactionAffiliation", "&ta");
      localHashMap.put("transactionTax", "&tt");
      localHashMap.put("transactionShipping", "&ts");
      localHashMap.put("transactionTotal", "&tr");
      localHashMap.put("transactionCurrency", "&cu");
      XX = localHashMap;
    }
    return XX;
  }

  private Map<String, String> F(Map<String, d.a> paramMap)
  {
    d.a locala = (d.a)paramMap.get(XW);
    if (locala != null)
      return c(locala);
    if (XY == null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("name", "&in");
      localHashMap.put("sku", "&ic");
      localHashMap.put("category", "&iv");
      localHashMap.put("price", "&ip");
      localHashMap.put("quantity", "&iq");
      localHashMap.put("currency", "&cu");
      XY = localHashMap;
    }
    return XY;
  }

  private void a(Tracker paramTracker, Map<String, d.a> paramMap)
  {
    String str = bN("transactionId");
    if (str == null)
      bh.t("Cannot find transactionId in data layer.");
    while (true)
    {
      return;
      LinkedList localLinkedList = new LinkedList();
      Map localMap1;
      try
      {
        localMap1 = p((d.a)paramMap.get(XT));
        localMap1.put("&t", "transaction");
        Iterator localIterator1 = E(paramMap).entrySet().iterator();
        while (localIterator1.hasNext())
        {
          Map.Entry localEntry2 = (Map.Entry)localIterator1.next();
          b(localMap1, (String)localEntry2.getValue(), bN((String)localEntry2.getKey()));
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        bh.c("Unable to send transaction", localIllegalArgumentException);
        return;
      }
      localLinkedList.add(localMap1);
      List localList = kv();
      if (localList != null)
      {
        Iterator localIterator2 = localList.iterator();
        while (localIterator2.hasNext())
        {
          Map localMap2 = (Map)localIterator2.next();
          if (localMap2.get("name") == null)
          {
            bh.t("Unable to send transaction item hit due to missing 'name' field.");
            return;
          }
          Map localMap3 = p((d.a)paramMap.get(XT));
          localMap3.put("&t", "item");
          localMap3.put("&ti", str);
          Iterator localIterator4 = F(paramMap).entrySet().iterator();
          while (localIterator4.hasNext())
          {
            Map.Entry localEntry1 = (Map.Entry)localIterator4.next();
            b(localMap3, (String)localEntry1.getValue(), (String)localMap2.get(localEntry1.getKey()));
          }
          localLinkedList.add(localMap3);
        }
      }
      Iterator localIterator3 = localLinkedList.iterator();
      while (localIterator3.hasNext())
        paramTracker.send((Map)localIterator3.next());
    }
  }

  private void b(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    if (paramString2 != null)
      paramMap.put(paramString1, paramString2);
  }

  private String bN(String paramString)
  {
    Object localObject = this.TN.get(paramString);
    if (localObject == null)
      return null;
    return localObject.toString();
  }

  private Map<String, String> c(d.a parama)
  {
    Object localObject = di.o(parama);
    if (!(localObject instanceof Map))
      return null;
    Map localMap = (Map)localObject;
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Iterator localIterator = localMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localLinkedHashMap.put(localEntry.getKey().toString(), localEntry.getValue().toString());
    }
    return localLinkedHashMap;
  }

  private boolean d(Map<String, d.a> paramMap, String paramString)
  {
    d.a locala = (d.a)paramMap.get(paramString);
    if (locala == null)
      return false;
    return di.n(locala).booleanValue();
  }

  private List<Map<String, String>> kv()
  {
    Object localObject = this.TN.get("transactionProducts");
    if (localObject == null)
      return null;
    if (!(localObject instanceof List))
      throw new IllegalArgumentException("transactionProducts should be of type List.");
    Iterator localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext())
    {
      if ((localIterator.next() instanceof Map))
        continue;
      throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
    }
    return (List)localObject;
  }

  private Map<String, String> p(d.a parama)
  {
    if (parama == null)
      return new HashMap();
    Map localMap = c(parama);
    if (localMap == null)
      return new HashMap();
    String str = (String)localMap.get("&aip");
    if ((str != null) && (this.XZ.contains(str.toLowerCase())))
      localMap.remove("&aip");
    return localMap;
  }

  public void w(Map<String, d.a> paramMap)
  {
    Tracker localTracker = this.Ya.bF("_GTM_DEFAULT_TRACKER_");
    if (d(paramMap, XS))
    {
      localTracker.send(p((d.a)paramMap.get(XT)));
      return;
    }
    if (d(paramMap, XU))
    {
      a(localTracker, paramMap);
      return;
    }
    bh.w("Ignoring unknown tag.");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.dj
 * JD-Core Version:    0.6.0
 */