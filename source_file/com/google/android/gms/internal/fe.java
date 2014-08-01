package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class fe
  implements SafeParcelable
{
  public static final ff CREATOR = new ff();
  private final HashMap<String, HashMap<String, fb.a<?, ?>>> CE;
  private final ArrayList<a> CF;
  private final String CG;
  private final int wj;

  fe(int paramInt, ArrayList<a> paramArrayList, String paramString)
  {
    this.wj = paramInt;
    this.CF = null;
    this.CE = b(paramArrayList);
    this.CG = ((String)er.f(paramString));
    eA();
  }

  public fe(Class<? extends fb> paramClass)
  {
    this.wj = 1;
    this.CF = null;
    this.CE = new HashMap();
    this.CG = paramClass.getCanonicalName();
  }

  private static HashMap<String, HashMap<String, fb.a<?, ?>>> b(ArrayList<a> paramArrayList)
  {
    HashMap localHashMap = new HashMap();
    int i = paramArrayList.size();
    for (int j = 0; j < i; j++)
    {
      a locala = (a)paramArrayList.get(j);
      localHashMap.put(locala.className, locala.eE());
    }
    return localHashMap;
  }

  public void a(Class<? extends fb> paramClass, HashMap<String, fb.a<?, ?>> paramHashMap)
  {
    this.CE.put(paramClass.getCanonicalName(), paramHashMap);
  }

  public HashMap<String, fb.a<?, ?>> ao(String paramString)
  {
    return (HashMap)this.CE.get(paramString);
  }

  public boolean b(Class<? extends fb> paramClass)
  {
    return this.CE.containsKey(paramClass.getCanonicalName());
  }

  public int describeContents()
  {
    return 0;
  }

  public void eA()
  {
    Iterator localIterator1 = this.CE.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str = (String)localIterator1.next();
      HashMap localHashMap = (HashMap)this.CE.get(str);
      Iterator localIterator2 = localHashMap.keySet().iterator();
      while (localIterator2.hasNext())
        ((fb.a)localHashMap.get((String)localIterator2.next())).a(this);
    }
  }

  public void eB()
  {
    Iterator localIterator1 = this.CE.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str1 = (String)localIterator1.next();
      HashMap localHashMap1 = (HashMap)this.CE.get(str1);
      HashMap localHashMap2 = new HashMap();
      Iterator localIterator2 = localHashMap1.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str2 = (String)localIterator2.next();
        localHashMap2.put(str2, ((fb.a)localHashMap1.get(str2)).eq());
      }
      this.CE.put(str1, localHashMap2);
    }
  }

  ArrayList<a> eC()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.CE.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new a(str, (HashMap)this.CE.get(str)));
    }
    return localArrayList;
  }

  public String eD()
  {
    return this.CG;
  }

  int getVersionCode()
  {
    return this.wj;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1 = this.CE.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str1 = (String)localIterator1.next();
      localStringBuilder.append(str1).append(":\n");
      HashMap localHashMap = (HashMap)this.CE.get(str1);
      Iterator localIterator2 = localHashMap.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str2 = (String)localIterator2.next();
        localStringBuilder.append("  ").append(str2).append(": ");
        localStringBuilder.append(localHashMap.get(str2));
      }
    }
    return localStringBuilder.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ff.a(this, paramParcel, paramInt);
  }

  public static class a
    implements SafeParcelable
  {
    public static final fg CREATOR = new fg();
    final ArrayList<fe.b> CH;
    final String className;
    final int versionCode;

    a(int paramInt, String paramString, ArrayList<fe.b> paramArrayList)
    {
      this.versionCode = paramInt;
      this.className = paramString;
      this.CH = paramArrayList;
    }

    a(String paramString, HashMap<String, fb.a<?, ?>> paramHashMap)
    {
      this.versionCode = 1;
      this.className = paramString;
      this.CH = b(paramHashMap);
    }

    private static ArrayList<fe.b> b(HashMap<String, fb.a<?, ?>> paramHashMap)
    {
      if (paramHashMap == null)
        return null;
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localArrayList.add(new fe.b(str, (fb.a)paramHashMap.get(str)));
      }
      return localArrayList;
    }

    public int describeContents()
    {
      return 0;
    }

    HashMap<String, fb.a<?, ?>> eE()
    {
      HashMap localHashMap = new HashMap();
      int i = this.CH.size();
      for (int j = 0; j < i; j++)
      {
        fe.b localb = (fe.b)this.CH.get(j);
        localHashMap.put(localb.eX, localb.CI);
      }
      return localHashMap;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      fg.a(this, paramParcel, paramInt);
    }
  }

  public static class b
    implements SafeParcelable
  {
    public static final fd CREATOR = new fd();
    final fb.a<?, ?> CI;
    final String eX;
    final int versionCode;

    b(int paramInt, String paramString, fb.a<?, ?> parama)
    {
      this.versionCode = paramInt;
      this.eX = paramString;
      this.CI = parama;
    }

    b(String paramString, fb.a<?, ?> parama)
    {
      this.versionCode = 1;
      this.eX = paramString;
      this.CI = parama;
    }

    public int describeContents()
    {
      return 0;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      fd.a(this, paramParcel, paramInt);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.fe
 * JD-Core Version:    0.6.0
 */