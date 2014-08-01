package com.google.android.gms.tagmanager;

import I;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.c.b;
import com.google.android.gms.internal.c.e;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.c.g;
import com.google.android.gms.internal.c.h;
import com.google.android.gms.internal.d.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class cr
{
  private static d.a a(int paramInt, c.f paramf, d.a[] paramArrayOfa, Set<Integer> paramSet)
    throws cr.g
  {
    int i = 0;
    if (paramSet.contains(Integer.valueOf(paramInt)))
      bw("Value cycle detected.  Current value reference: " + paramInt + "." + "  Previous value references: " + paramSet + ".");
    d.a locala1 = (d.a)a(paramf.fi, paramInt, "values");
    if (paramArrayOfa[paramInt] != null)
      return paramArrayOfa[paramInt];
    paramSet.add(Integer.valueOf(paramInt));
    int j = locala1.type;
    d.a locala2 = null;
    switch (j)
    {
    default:
    case 2:
    case 3:
    case 4:
    case 7:
    case 1:
    case 5:
    case 6:
    case 8:
    }
    while (true)
    {
      if (locala2 == null)
        bw("Invalid value: " + locala1);
      paramArrayOfa[paramInt] = locala2;
      paramSet.remove(Integer.valueOf(paramInt));
      return locala2;
      c.h localh3 = h(locala1);
      locala2 = g(locala1);
      locala2.fZ = new d.a[localh3.fK.length];
      int[] arrayOfInt4 = localh3.fK;
      int i11 = arrayOfInt4.length;
      int i14;
      for (int i12 = 0; i < i11; i12 = i14)
      {
        int i13 = arrayOfInt4[i];
        d.a[] arrayOfa4 = locala2.fZ;
        i14 = i12 + 1;
        arrayOfa4[i12] = a(i13, paramf, paramArrayOfa, paramSet);
        i++;
      }
      locala2 = g(locala1);
      c.h localh2 = h(locala1);
      if (localh2.fL.length != localh2.fM.length)
        bw("Uneven map keys (" + localh2.fL.length + ") and map values (" + localh2.fM.length + ")");
      locala2.ga = new d.a[localh2.fL.length];
      locala2.gb = new d.a[localh2.fL.length];
      int[] arrayOfInt2 = localh2.fL;
      int i2 = arrayOfInt2.length;
      int i3 = 0;
      int i10;
      for (int i4 = 0; i3 < i2; i4 = i10)
      {
        int i9 = arrayOfInt2[i3];
        d.a[] arrayOfa3 = locala2.ga;
        i10 = i4 + 1;
        arrayOfa3[i4] = a(i9, paramf, paramArrayOfa, paramSet);
        i3++;
      }
      int[] arrayOfInt3 = localh2.fM;
      int i5 = arrayOfInt3.length;
      int i8;
      for (int i6 = 0; i < i5; i6 = i8)
      {
        int i7 = arrayOfInt3[i];
        d.a[] arrayOfa2 = locala2.gb;
        i8 = i6 + 1;
        arrayOfa2[i6] = a(i7, paramf, paramArrayOfa, paramSet);
        i++;
      }
      locala2 = g(locala1);
      locala2.gc = di.j(a(h(locala1).fP, paramf, paramArrayOfa, paramSet));
      continue;
      locala2 = g(locala1);
      c.h localh1 = h(locala1);
      locala2.gg = new d.a[localh1.fO.length];
      int[] arrayOfInt1 = localh1.fO;
      int k = arrayOfInt1.length;
      int i1;
      for (int m = 0; i < k; m = i1)
      {
        int n = arrayOfInt1[i];
        d.a[] arrayOfa1 = locala2.gg;
        i1 = m + 1;
        arrayOfa1[m] = a(n, paramf, paramArrayOfa, paramSet);
        i++;
      }
      locala2 = locala1;
    }
  }

  private static a a(c.b paramb, c.f paramf, d.a[] paramArrayOfa, int paramInt)
    throws cr.g
  {
    b localb = a.jE();
    int[] arrayOfInt = paramb.eS;
    int i = arrayOfInt.length;
    int j = 0;
    if (j < i)
    {
      Integer localInteger = Integer.valueOf(arrayOfInt[j]);
      c.e locale = (c.e)a(paramf.fj, localInteger.intValue(), "properties");
      String str = (String)a(paramf.fh, locale.key, "keys");
      d.a locala = (d.a)a(paramArrayOfa, locale.value, "values");
      if (b.dM.toString().equals(str))
        localb.i(locala);
      while (true)
      {
        j++;
        break;
        localb.b(str, locala);
      }
    }
    return localb.jH();
  }

  private static e a(c.g paramg, List<a> paramList1, List<a> paramList2, List<a> paramList3, c.f paramf)
  {
    f localf = e.jM();
    int[] arrayOfInt1 = paramg.fy;
    int i = arrayOfInt1.length;
    for (int j = 0; j < i; j++)
      localf.b((a)paramList3.get(Integer.valueOf(arrayOfInt1[j]).intValue()));
    int[] arrayOfInt2 = paramg.fz;
    int k = arrayOfInt2.length;
    for (int m = 0; m < k; m++)
      localf.c((a)paramList3.get(Integer.valueOf(arrayOfInt2[m]).intValue()));
    int[] arrayOfInt3 = paramg.fA;
    int n = arrayOfInt3.length;
    for (int i1 = 0; i1 < n; i1++)
      localf.d((a)paramList1.get(Integer.valueOf(arrayOfInt3[i1]).intValue()));
    int[] arrayOfInt4 = paramg.fC;
    int i2 = arrayOfInt4.length;
    for (int i3 = 0; i3 < i2; i3++)
    {
      Integer localInteger4 = Integer.valueOf(arrayOfInt4[i3]);
      localf.by(paramf.fi[localInteger4.intValue()].fY);
    }
    int[] arrayOfInt5 = paramg.fB;
    int i4 = arrayOfInt5.length;
    for (int i5 = 0; i5 < i4; i5++)
      localf.e((a)paramList1.get(Integer.valueOf(arrayOfInt5[i5]).intValue()));
    int[] arrayOfInt6 = paramg.fD;
    int i6 = arrayOfInt6.length;
    for (int i7 = 0; i7 < i6; i7++)
    {
      Integer localInteger3 = Integer.valueOf(arrayOfInt6[i7]);
      localf.bz(paramf.fi[localInteger3.intValue()].fY);
    }
    int[] arrayOfInt7 = paramg.fE;
    int i8 = arrayOfInt7.length;
    for (int i9 = 0; i9 < i8; i9++)
      localf.f((a)paramList2.get(Integer.valueOf(arrayOfInt7[i9]).intValue()));
    int[] arrayOfInt8 = paramg.fG;
    int i10 = arrayOfInt8.length;
    for (int i11 = 0; i11 < i10; i11++)
    {
      Integer localInteger2 = Integer.valueOf(arrayOfInt8[i11]);
      localf.bA(paramf.fi[localInteger2.intValue()].fY);
    }
    int[] arrayOfInt9 = paramg.fF;
    int i12 = arrayOfInt9.length;
    for (int i13 = 0; i13 < i12; i13++)
      localf.g((a)paramList2.get(Integer.valueOf(arrayOfInt9[i13]).intValue()));
    int[] arrayOfInt10 = paramg.fH;
    int i14 = arrayOfInt10.length;
    for (int i15 = 0; i15 < i14; i15++)
    {
      Integer localInteger1 = Integer.valueOf(arrayOfInt10[i15]);
      localf.bB(paramf.fi[localInteger1.intValue()].fY);
    }
    return localf.jX();
  }

  private static <T> T a(T[] paramArrayOfT, int paramInt, String paramString)
    throws cr.g
  {
    if ((paramInt < 0) || (paramInt >= paramArrayOfT.length))
      bw("Index out of bounds detected: " + paramInt + " in " + paramString);
    return paramArrayOfT[paramInt];
  }

  public static c b(c.f paramf)
    throws cr.g
  {
    int i = 0;
    d.a[] arrayOfa = new d.a[paramf.fi.length];
    for (int j = 0; j < paramf.fi.length; j++)
      a(j, paramf, arrayOfa, new HashSet(0));
    d locald = c.jI();
    ArrayList localArrayList1 = new ArrayList();
    for (int k = 0; k < paramf.fl.length; k++)
      localArrayList1.add(a(paramf.fl[k], paramf, arrayOfa, k));
    ArrayList localArrayList2 = new ArrayList();
    for (int m = 0; m < paramf.fm.length; m++)
      localArrayList2.add(a(paramf.fm[m], paramf, arrayOfa, m));
    ArrayList localArrayList3 = new ArrayList();
    for (int n = 0; n < paramf.fk.length; n++)
    {
      a locala = a(paramf.fk[n], paramf, arrayOfa, n);
      locald.a(locala);
      localArrayList3.add(locala);
    }
    c.g[] arrayOfg = paramf.fn;
    int i1 = arrayOfg.length;
    while (i < i1)
    {
      locald.a(a(arrayOfg[i], localArrayList1, localArrayList3, localArrayList2, paramf));
      i++;
    }
    locald.bx(paramf.fr);
    locald.bW(paramf.fw);
    return locald.jL();
  }

  public static void b(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1024];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1)
        return;
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }

  private static void bw(String paramString)
    throws cr.g
  {
    bh.t(paramString);
    throw new g(paramString);
  }

  public static d.a g(d.a parama)
  {
    d.a locala = new d.a();
    locala.type = parama.type;
    locala.gh = ((int[])parama.gh.clone());
    if (parama.gi)
      locala.gi = parama.gi;
    return locala;
  }

  private static c.h h(d.a parama)
    throws cr.g
  {
    if ((c.h)parama.a(c.h.fI) == null)
      bw("Expected a ServingValue and didn't get one. Value is: " + parama);
    return (c.h)parama.a(c.h.fI);
  }

  public static class a
  {
    private final Map<String, d.a> Ws;
    private final d.a Wt;

    private a(Map<String, d.a> paramMap, d.a parama)
    {
      this.Ws = paramMap;
      this.Wt = parama;
    }

    public static cr.b jE()
    {
      return new cr.b(null);
    }

    public void a(String paramString, d.a parama)
    {
      this.Ws.put(paramString, parama);
    }

    public Map<String, d.a> jF()
    {
      return Collections.unmodifiableMap(this.Ws);
    }

    public d.a jG()
    {
      return this.Wt;
    }

    public String toString()
    {
      return "Properties: " + jF() + " pushAfterEvaluate: " + this.Wt;
    }
  }

  public static class b
  {
    private final Map<String, d.a> Ws = new HashMap();
    private d.a Wt;

    public b b(String paramString, d.a parama)
    {
      this.Ws.put(paramString, parama);
      return this;
    }

    public b i(d.a parama)
    {
      this.Wt = parama;
      return this;
    }

    public cr.a jH()
    {
      return new cr.a(this.Ws, this.Wt, null);
    }
  }

  public static class c
  {
    private final String Un;
    private final List<cr.e> Wu;
    private final Map<String, List<cr.a>> Wv;
    private final int Ww;

    private c(List<cr.e> paramList, Map<String, List<cr.a>> paramMap, String paramString, int paramInt)
    {
      this.Wu = Collections.unmodifiableList(paramList);
      this.Wv = Collections.unmodifiableMap(paramMap);
      this.Un = paramString;
      this.Ww = paramInt;
    }

    public static cr.d jI()
    {
      return new cr.d(null);
    }

    public String getVersion()
    {
      return this.Un;
    }

    public List<cr.e> jJ()
    {
      return this.Wu;
    }

    public Map<String, List<cr.a>> jK()
    {
      return this.Wv;
    }

    public String toString()
    {
      return "Rules: " + jJ() + "  Macros: " + this.Wv;
    }
  }

  public static class d
  {
    private String Un = "";
    private final List<cr.e> Wu = new ArrayList();
    private final Map<String, List<cr.a>> Wv = new HashMap();
    private int Ww = 0;

    public d a(cr.a parama)
    {
      String str = di.j((d.a)parama.jF().get(b.cT.toString()));
      Object localObject = (List)this.Wv.get(str);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.Wv.put(str, localObject);
      }
      ((List)localObject).add(parama);
      return (d)this;
    }

    public d a(cr.e parame)
    {
      this.Wu.add(parame);
      return this;
    }

    public d bW(int paramInt)
    {
      this.Ww = paramInt;
      return this;
    }

    public d bx(String paramString)
    {
      this.Un = paramString;
      return this;
    }

    public cr.c jL()
    {
      return new cr.c(this.Wu, this.Wv, this.Un, this.Ww, null);
    }
  }

  public static class e
  {
    private final List<cr.a> WA;
    private final List<cr.a> WB;
    private final List<cr.a> WC;
    private final List<String> WD;
    private final List<String> WE;
    private final List<String> WF;
    private final List<String> WG;
    private final List<cr.a> Wx;
    private final List<cr.a> Wy;
    private final List<cr.a> Wz;

    private e(List<cr.a> paramList1, List<cr.a> paramList2, List<cr.a> paramList3, List<cr.a> paramList4, List<cr.a> paramList5, List<cr.a> paramList6, List<String> paramList7, List<String> paramList8, List<String> paramList9, List<String> paramList10)
    {
      this.Wx = Collections.unmodifiableList(paramList1);
      this.Wy = Collections.unmodifiableList(paramList2);
      this.Wz = Collections.unmodifiableList(paramList3);
      this.WA = Collections.unmodifiableList(paramList4);
      this.WB = Collections.unmodifiableList(paramList5);
      this.WC = Collections.unmodifiableList(paramList6);
      this.WD = Collections.unmodifiableList(paramList7);
      this.WE = Collections.unmodifiableList(paramList8);
      this.WF = Collections.unmodifiableList(paramList9);
      this.WG = Collections.unmodifiableList(paramList10);
    }

    public static cr.f jM()
    {
      return new cr.f(null);
    }

    public List<cr.a> jN()
    {
      return this.Wx;
    }

    public List<cr.a> jO()
    {
      return this.Wy;
    }

    public List<cr.a> jP()
    {
      return this.Wz;
    }

    public List<cr.a> jQ()
    {
      return this.WA;
    }

    public List<cr.a> jR()
    {
      return this.WB;
    }

    public List<String> jS()
    {
      return this.WD;
    }

    public List<String> jT()
    {
      return this.WE;
    }

    public List<String> jU()
    {
      return this.WF;
    }

    public List<String> jV()
    {
      return this.WG;
    }

    public List<cr.a> jW()
    {
      return this.WC;
    }

    public String toString()
    {
      return "Positive predicates: " + jN() + "  Negative predicates: " + jO() + "  Add tags: " + jP() + "  Remove tags: " + jQ() + "  Add macros: " + jR() + "  Remove macros: " + jW();
    }
  }

  public static class f
  {
    private final List<cr.a> WA = new ArrayList();
    private final List<cr.a> WB = new ArrayList();
    private final List<cr.a> WC = new ArrayList();
    private final List<String> WD = new ArrayList();
    private final List<String> WE = new ArrayList();
    private final List<String> WF = new ArrayList();
    private final List<String> WG = new ArrayList();
    private final List<cr.a> Wx = new ArrayList();
    private final List<cr.a> Wy = new ArrayList();
    private final List<cr.a> Wz = new ArrayList();

    public f b(cr.a parama)
    {
      this.Wx.add(parama);
      return this;
    }

    public f bA(String paramString)
    {
      this.WD.add(paramString);
      return this;
    }

    public f bB(String paramString)
    {
      this.WE.add(paramString);
      return this;
    }

    public f by(String paramString)
    {
      this.WF.add(paramString);
      return this;
    }

    public f bz(String paramString)
    {
      this.WG.add(paramString);
      return this;
    }

    public f c(cr.a parama)
    {
      this.Wy.add(parama);
      return this;
    }

    public f d(cr.a parama)
    {
      this.Wz.add(parama);
      return this;
    }

    public f e(cr.a parama)
    {
      this.WA.add(parama);
      return this;
    }

    public f f(cr.a parama)
    {
      this.WB.add(parama);
      return this;
    }

    public f g(cr.a parama)
    {
      this.WC.add(parama);
      return this;
    }

    public cr.e jX()
    {
      return new cr.e(this.Wx, this.Wy, this.Wz, this.WA, this.WB, this.WC, this.WD, this.WE, this.WF, this.WG, null);
    }
  }

  public static class g extends Exception
  {
    public g(String paramString)
    {
      super();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cr
 * JD-Core Version:    0.6.0
 */