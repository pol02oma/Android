package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ct
{
  private static final by<d.a> WH = new by(di.ku(), true);
  private final DataLayer TN;
  private final cr.c WI;
  private final ag WJ;
  private final Map<String, aj> WK;
  private final Map<String, aj> WL;
  private final Map<String, aj> WM;
  private final k<cr.a, by<d.a>> WN;
  private final k<String, b> WO;
  private final Set<cr.e> WP;
  private final Map<String, c> WQ;
  private volatile String WR;
  private int WS;

  public ct(Context paramContext, cr.c paramc, DataLayer paramDataLayer, s.a parama1, s.a parama2, ag paramag)
  {
    if (paramc == null)
      throw new NullPointerException("resource cannot be null");
    this.WI = paramc;
    this.WP = new HashSet(paramc.jJ());
    this.TN = paramDataLayer;
    this.WJ = paramag;
    1 local1 = new l.a()
    {
      public int a(cr.a parama, by<d.a> paramby)
      {
        return ((d.a)paramby.getObject()).eW();
      }
    };
    this.WN = new l().a(1048576, local1);
    2 local2 = new l.a()
    {
      public int a(String paramString, ct.b paramb)
      {
        return paramString.length() + paramb.getSize();
      }
    };
    this.WO = new l().a(1048576, local2);
    this.WK = new HashMap();
    b(new i(paramContext));
    b(new s(parama2));
    b(new w(paramDataLayer));
    b(new dj(paramContext, paramDataLayer));
    this.WL = new HashMap();
    c(new q());
    c(new ad());
    c(new ae());
    c(new al());
    c(new am());
    c(new bd());
    c(new be());
    c(new ci());
    c(new dc());
    this.WM = new HashMap();
    a(new b(paramContext));
    a(new c(paramContext));
    a(new e(paramContext));
    a(new f(paramContext));
    a(new g(paramContext));
    a(new h(paramContext));
    a(new m());
    a(new p(this.WI.getVersion()));
    a(new s(parama1));
    a(new u(paramDataLayer));
    a(new z(paramContext));
    a(new aa());
    a(new ac());
    a(new ah(this));
    a(new an());
    a(new ao());
    a(new ax(paramContext));
    a(new az());
    a(new bc());
    a(new bk(paramContext));
    a(new bz());
    a(new cc());
    a(new cf());
    a(new ch());
    a(new cj(paramContext));
    a(new cu());
    a(new cv());
    a(new de());
    this.WQ = new HashMap();
    Iterator localIterator1 = this.WP.iterator();
    while (localIterator1.hasNext())
    {
      cr.e locale = (cr.e)localIterator1.next();
      if (paramag.jb())
      {
        a(locale.jR(), locale.jS(), "add macro");
        a(locale.jW(), locale.jT(), "remove macro");
        a(locale.jP(), locale.jU(), "add tag");
        a(locale.jQ(), locale.jV(), "remove tag");
      }
      for (int i = 0; i < locale.jR().size(); i++)
      {
        cr.a locala3 = (cr.a)locale.jR().get(i);
        String str2 = "Unknown";
        if ((paramag.jb()) && (i < locale.jS().size()))
          str2 = (String)locale.jS().get(i);
        c localc2 = c(this.WQ, h(locala3));
        localc2.b(locale);
        localc2.a(locale, locala3);
        localc2.a(locale, str2);
      }
      for (int j = 0; j < locale.jW().size(); j++)
      {
        cr.a locala2 = (cr.a)locale.jW().get(j);
        String str1 = "Unknown";
        if ((paramag.jb()) && (j < locale.jT().size()))
          str1 = (String)locale.jT().get(j);
        c localc1 = c(this.WQ, h(locala2));
        localc1.b(locale);
        localc1.b(locale, locala2);
        localc1.b(locale, str1);
      }
    }
    Iterator localIterator2 = this.WI.jK().entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      Iterator localIterator3 = ((List)localEntry.getValue()).iterator();
      while (localIterator3.hasNext())
      {
        cr.a locala1 = (cr.a)localIterator3.next();
        if (di.n((d.a)locala1.jF().get(com.google.android.gms.internal.b.ds.toString())).booleanValue())
          continue;
        c(this.WQ, (String)localEntry.getKey()).i(locala1);
      }
    }
  }

  private by<d.a> a(d.a parama, Set<String> paramSet, dk paramdk)
  {
    if (!parama.gi)
      return new by(parama, true);
    switch (parama.type)
    {
    case 5:
    case 6:
    default:
      bh.t("Unknown type: " + parama.type);
      return WH;
    case 2:
      d.a locala3 = cr.g(parama);
      locala3.fZ = new d.a[parama.fZ.length];
      for (int k = 0; k < parama.fZ.length; k++)
      {
        by localby5 = a(parama.fZ[k], paramSet, paramdk.bS(k));
        if (localby5 == WH)
          return WH;
        locala3.fZ[k] = ((d.a)localby5.getObject());
      }
      return new by(locala3, false);
    case 3:
      d.a locala2 = cr.g(parama);
      if (parama.ga.length != parama.gb.length)
      {
        bh.t("Invalid serving value: " + parama.toString());
        return WH;
      }
      locala2.ga = new d.a[parama.ga.length];
      locala2.gb = new d.a[parama.ga.length];
      for (int j = 0; j < parama.ga.length; j++)
      {
        by localby3 = a(parama.ga[j], paramSet, paramdk.bT(j));
        by localby4 = a(parama.gb[j], paramSet, paramdk.bU(j));
        if ((localby3 == WH) || (localby4 == WH))
          return WH;
        locala2.ga[j] = ((d.a)localby3.getObject());
        locala2.gb[j] = ((d.a)localby4.getObject());
      }
      return new by(locala2, false);
    case 4:
      if (paramSet.contains(parama.gc))
      {
        bh.t("Macro cycle detected.  Current macro reference: " + parama.gc + "." + "  Previous macro references: " + paramSet.toString() + ".");
        return WH;
      }
      paramSet.add(parama.gc);
      by localby2 = dl.a(a(parama.gc, paramSet, paramdk.jq()), parama.gh);
      paramSet.remove(parama.gc);
      return localby2;
    case 7:
    }
    d.a locala1 = cr.g(parama);
    locala1.gg = new d.a[parama.gg.length];
    for (int i = 0; i < parama.gg.length; i++)
    {
      by localby1 = a(parama.gg[i], paramSet, paramdk.bV(i));
      if (localby1 == WH)
        return WH;
      locala1.gg[i] = ((d.a)localby1.getObject());
    }
    return new by(locala1, false);
  }

  private by<d.a> a(String paramString, Set<String> paramSet, bj parambj)
  {
    this.WS = (1 + this.WS);
    b localb = (b)this.WO.get(paramString);
    if ((localb != null) && (!this.WJ.jb()))
    {
      a(localb.jG(), paramSet);
      this.WS = (-1 + this.WS);
      return localb.ka();
    }
    c localc = (c)this.WQ.get(paramString);
    if (localc == null)
    {
      bh.t(jZ() + "Invalid macro: " + paramString);
      this.WS = (-1 + this.WS);
      return WH;
    }
    by localby1 = a(paramString, localc.kb(), localc.kc(), localc.kd(), localc.kf(), localc.ke(), paramSet, parambj.iS());
    if (((Set)localby1.getObject()).isEmpty());
    for (cr.a locala = localc.kg(); locala == null; locala = (cr.a)((Set)localby1.getObject()).iterator().next())
    {
      this.WS = (-1 + this.WS);
      return WH;
      if (((Set)localby1.getObject()).size() <= 1)
        continue;
      bh.w(jZ() + "Multiple macros active for macroName " + paramString);
    }
    by localby2 = a(this.WM, locala, paramSet, parambj.jh());
    boolean bool;
    if ((localby1.jr()) && (localby2.jr()))
    {
      bool = true;
      if (localby2 != WH)
        break label399;
    }
    label399: for (by localby3 = WH; ; localby3 = new by(localby2.getObject(), bool))
    {
      d.a locala1 = locala.jG();
      if (localby3.jr())
        this.WO.e(paramString, new b(localby3, locala1));
      a(locala1, paramSet);
      this.WS = (-1 + this.WS);
      return localby3;
      bool = false;
      break;
    }
  }

  private by<d.a> a(Map<String, aj> paramMap, cr.a parama, Set<String> paramSet, ck paramck)
  {
    boolean bool1 = true;
    d.a locala = (d.a)parama.jF().get(com.google.android.gms.internal.b.cI.toString());
    by localby1;
    if (locala == null)
    {
      bh.t("No function id in properties");
      localby1 = WH;
    }
    String str;
    aj localaj;
    do
    {
      return localby1;
      str = locala.gd;
      localaj = (aj)paramMap.get(str);
      if (localaj == null)
      {
        bh.t(str + " has no backing implementation.");
        return WH;
      }
      localby1 = (by)this.WN.get(parama);
    }
    while ((localby1 != null) && (!this.WJ.jb()));
    HashMap localHashMap = new HashMap();
    Iterator localIterator = parama.jF().entrySet().iterator();
    boolean bool2 = bool1;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      cm localcm = paramck.bs((String)localEntry.getKey());
      by localby3 = a((d.a)localEntry.getValue(), paramSet, localcm.e((d.a)localEntry.getValue()));
      if (localby3 == WH)
        return WH;
      if (localby3.jr())
        parama.a((String)localEntry.getKey(), (d.a)localby3.getObject());
      for (boolean bool3 = bool2; ; bool3 = false)
      {
        localHashMap.put(localEntry.getKey(), localby3.getObject());
        bool2 = bool3;
        break;
      }
    }
    if (!localaj.a(localHashMap.keySet()))
    {
      bh.t("Incorrect keys for function " + str + " required " + localaj.jd() + " had " + localHashMap.keySet());
      return WH;
    }
    if ((bool2) && (localaj.iy()));
    while (true)
    {
      by localby2 = new by(localaj.u(localHashMap), bool1);
      if (bool1)
        this.WN.e(parama, localby2);
      paramck.d((d.a)localby2.getObject());
      return localby2;
      bool1 = false;
    }
  }

  private by<Set<cr.a>> a(Set<cr.e> paramSet, Set<String> paramSet1, a parama, cs paramcs)
  {
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    Iterator localIterator = paramSet.iterator();
    boolean bool1 = true;
    if (localIterator.hasNext())
    {
      cr.e locale = (cr.e)localIterator.next();
      cn localcn = paramcs.jp();
      by localby = a(locale, paramSet1, localcn);
      if (((Boolean)localby.getObject()).booleanValue())
        parama.a(locale, localHashSet1, localHashSet2, localcn);
      if ((bool1) && (localby.jr()));
      for (boolean bool2 = true; ; bool2 = false)
      {
        bool1 = bool2;
        break;
      }
    }
    localHashSet1.removeAll(localHashSet2);
    paramcs.b(localHashSet1);
    return new by(localHashSet1, bool1);
  }

  private void a(d.a parama, Set<String> paramSet)
  {
    if (parama == null);
    while (true)
    {
      return;
      by localby = a(parama, paramSet, new bw());
      if (localby == WH)
        continue;
      Object localObject1 = di.o((d.a)localby.getObject());
      if ((localObject1 instanceof Map))
      {
        Map localMap2 = (Map)localObject1;
        this.TN.push(localMap2);
        return;
      }
      if (!(localObject1 instanceof List))
        break;
      Iterator localIterator = ((List)localObject1).iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = localIterator.next();
        if ((localObject2 instanceof Map))
        {
          Map localMap1 = (Map)localObject2;
          this.TN.push(localMap1);
          continue;
        }
        bh.w("pushAfterEvaluate: value not a Map");
      }
    }
    bh.w("pushAfterEvaluate: value not a Map or List");
  }

  private static void a(List<cr.a> paramList, List<String> paramList1, String paramString)
  {
    if (paramList.size() != paramList1.size())
      bh.u("Invalid resource: imbalance of rule names of functions for " + paramString + " operation. Using default rule name instead");
  }

  private static void a(Map<String, aj> paramMap, aj paramaj)
  {
    if (paramMap.containsKey(paramaj.jc()))
      throw new IllegalArgumentException("Duplicate function type name: " + paramaj.jc());
    paramMap.put(paramaj.jc(), paramaj);
  }

  private static c c(Map<String, c> paramMap, String paramString)
  {
    c localc = (c)paramMap.get(paramString);
    if (localc == null)
    {
      localc = new c();
      paramMap.put(paramString, localc);
    }
    return localc;
  }

  private static String h(cr.a parama)
  {
    return di.j((d.a)parama.jF().get(com.google.android.gms.internal.b.cT.toString()));
  }

  private String jZ()
  {
    if (this.WS <= 1)
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Integer.toString(this.WS));
    for (int i = 2; i < this.WS; i++)
      localStringBuilder.append(' ');
    localStringBuilder.append(": ");
    return localStringBuilder.toString();
  }

  by<Boolean> a(cr.a parama, Set<String> paramSet, ck paramck)
  {
    by localby = a(this.WL, parama, paramSet, paramck);
    Boolean localBoolean = di.n((d.a)localby.getObject());
    paramck.d(di.r(localBoolean));
    return new by(localBoolean, localby.jr());
  }

  by<Boolean> a(cr.e parame, Set<String> paramSet, cn paramcn)
  {
    Iterator localIterator1 = parame.jO().iterator();
    boolean bool1 = true;
    if (localIterator1.hasNext())
    {
      by localby2 = a((cr.a)localIterator1.next(), paramSet, paramcn.jj());
      if (((Boolean)localby2.getObject()).booleanValue())
      {
        paramcn.f(di.r(Boolean.valueOf(false)));
        return new by(Boolean.valueOf(false), localby2.jr());
      }
      if ((bool1) && (localby2.jr()));
      for (boolean bool2 = true; ; bool2 = false)
      {
        bool1 = bool2;
        break;
      }
    }
    Iterator localIterator2 = parame.jN().iterator();
    while (localIterator2.hasNext())
    {
      by localby1 = a((cr.a)localIterator2.next(), paramSet, paramcn.jk());
      if (!((Boolean)localby1.getObject()).booleanValue())
      {
        paramcn.f(di.r(Boolean.valueOf(false)));
        return new by(Boolean.valueOf(false), localby1.jr());
      }
      if ((bool1) && (localby1.jr()))
      {
        bool1 = true;
        continue;
      }
      bool1 = false;
    }
    paramcn.f(di.r(Boolean.valueOf(true)));
    return new by(Boolean.valueOf(true), bool1);
  }

  by<Set<cr.a>> a(String paramString, Set<cr.e> paramSet, Map<cr.e, List<cr.a>> paramMap1, Map<cr.e, List<String>> paramMap2, Map<cr.e, List<cr.a>> paramMap3, Map<cr.e, List<String>> paramMap4, Set<String> paramSet1, cs paramcs)
  {
    return a(paramSet, paramSet1, new a(paramMap1, paramMap2, paramMap3, paramMap4)
    {
      public void a(cr.e parame, Set<cr.a> paramSet1, Set<cr.a> paramSet2, cn paramcn)
      {
        List localList1 = (List)this.WU.get(parame);
        List localList2 = (List)this.WV.get(parame);
        if (localList1 != null)
        {
          paramSet1.addAll(localList1);
          paramcn.jl().b(localList1, localList2);
        }
        List localList3 = (List)this.WW.get(parame);
        List localList4 = (List)this.WX.get(parame);
        if (localList3 != null)
        {
          paramSet2.addAll(localList3);
          paramcn.jm().b(localList3, localList4);
        }
      }
    }
    , paramcs);
  }

  by<Set<cr.a>> a(Set<cr.e> paramSet, cs paramcs)
  {
    return a(paramSet, new HashSet(), new a()
    {
      public void a(cr.e parame, Set<cr.a> paramSet1, Set<cr.a> paramSet2, cn paramcn)
      {
        paramSet1.addAll(parame.jP());
        paramSet2.addAll(parame.jQ());
        paramcn.jn().b(parame.jP(), parame.jU());
        paramcn.jo().b(parame.jQ(), parame.jV());
      }
    }
    , paramcs);
  }

  void a(aj paramaj)
  {
    a(this.WM, paramaj);
  }

  void b(aj paramaj)
  {
    a(this.WK, paramaj);
  }

  public by<d.a> bC(String paramString)
  {
    this.WS = 0;
    af localaf = this.WJ.bl(paramString);
    by localby = a(paramString, new HashSet(), localaf.iY());
    localaf.ja();
    return localby;
  }

  void bD(String paramString)
  {
    monitorenter;
    try
    {
      this.WR = paramString;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void ba(String paramString)
  {
    monitorenter;
    af localaf;
    try
    {
      bD(paramString);
      localaf = this.WJ.bm(paramString);
      t localt = localaf.iZ();
      Iterator localIterator = ((Set)a(this.WP, localt.iS()).getObject()).iterator();
      while (localIterator.hasNext())
      {
        cr.a locala = (cr.a)localIterator.next();
        a(this.WK, locala, new HashSet(), localt.iR());
      }
    }
    finally
    {
      monitorexit;
    }
    localaf.ja();
    bD(null);
    monitorexit;
  }

  void c(aj paramaj)
  {
    a(this.WL, paramaj);
  }

  public void f(List<c.i> paramList)
  {
    monitorenter;
    while (true)
    {
      c.i locali;
      try
      {
        Iterator localIterator = paramList.iterator();
        if (!localIterator.hasNext())
          break;
        locali = (c.i)localIterator.next();
        if ((locali.name == null) || (!locali.name.startsWith("gaExperiment:")))
        {
          bh.v("Ignored supplemental: " + locali);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      ai.a(this.TN, locali);
    }
    monitorexit;
  }

  String jY()
  {
    monitorenter;
    try
    {
      String str = this.WR;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  static abstract interface a
  {
    public abstract void a(cr.e parame, Set<cr.a> paramSet1, Set<cr.a> paramSet2, cn paramcn);
  }

  private static class b
  {
    private by<d.a> WY;
    private d.a Wt;

    public b(by<d.a> paramby, d.a parama)
    {
      this.WY = paramby;
      this.Wt = parama;
    }

    public int getSize()
    {
      int i = ((d.a)this.WY.getObject()).eW();
      if (this.Wt == null);
      for (int j = 0; ; j = this.Wt.eW())
        return j + i;
    }

    public d.a jG()
    {
      return this.Wt;
    }

    public by<d.a> ka()
    {
      return this.WY;
    }
  }

  private static class c
  {
    private final Set<cr.e> WP = new HashSet();
    private final Map<cr.e, List<cr.a>> WZ = new HashMap();
    private final Map<cr.e, List<cr.a>> Xa = new HashMap();
    private final Map<cr.e, List<String>> Xb = new HashMap();
    private final Map<cr.e, List<String>> Xc = new HashMap();
    private cr.a Xd;

    public void a(cr.e parame, cr.a parama)
    {
      Object localObject = (List)this.WZ.get(parame);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.WZ.put(parame, localObject);
      }
      ((List)localObject).add(parama);
    }

    public void a(cr.e parame, String paramString)
    {
      Object localObject = (List)this.Xb.get(parame);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.Xb.put(parame, localObject);
      }
      ((List)localObject).add(paramString);
    }

    public void b(cr.e parame)
    {
      this.WP.add(parame);
    }

    public void b(cr.e parame, cr.a parama)
    {
      Object localObject = (List)this.Xa.get(parame);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.Xa.put(parame, localObject);
      }
      ((List)localObject).add(parama);
    }

    public void b(cr.e parame, String paramString)
    {
      Object localObject = (List)this.Xc.get(parame);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.Xc.put(parame, localObject);
      }
      ((List)localObject).add(paramString);
    }

    public void i(cr.a parama)
    {
      this.Xd = parama;
    }

    public Set<cr.e> kb()
    {
      return this.WP;
    }

    public Map<cr.e, List<cr.a>> kc()
    {
      return this.WZ;
    }

    public Map<cr.e, List<String>> kd()
    {
      return this.Xb;
    }

    public Map<cr.e, List<String>> ke()
    {
      return this.Xc;
    }

    public Map<cr.e, List<cr.a>> kf()
    {
      return this.Xa;
    }

    public cr.a kg()
    {
      return this.Xd;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ct
 * JD-Core Version:    0.6.0
 */