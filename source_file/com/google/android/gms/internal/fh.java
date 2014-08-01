package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class fh extends fb
  implements SafeParcelable
{
  public static final fi CREATOR = new fi();
  private final fe CC;
  private final Parcel CJ;
  private final int CK;
  private int CL;
  private int CM;
  private final String mClassName;
  private final int wj;

  fh(int paramInt, Parcel paramParcel, fe paramfe)
  {
    this.wj = paramInt;
    this.CJ = ((Parcel)er.f(paramParcel));
    this.CK = 2;
    this.CC = paramfe;
    if (this.CC == null);
    for (this.mClassName = null; ; this.mClassName = this.CC.eD())
    {
      this.CL = 2;
      return;
    }
  }

  private fh(SafeParcelable paramSafeParcelable, fe paramfe, String paramString)
  {
    this.wj = 1;
    this.CJ = Parcel.obtain();
    paramSafeParcelable.writeToParcel(this.CJ, 0);
    this.CK = 1;
    this.CC = ((fe)er.f(paramfe));
    this.mClassName = ((String)er.f(paramString));
    this.CL = 2;
  }

  public static <T extends fb,  extends SafeParcelable> fh a(T paramT)
  {
    String str = paramT.getClass().getCanonicalName();
    fe localfe = b(paramT);
    return new fh((SafeParcelable)paramT, localfe, str);
  }

  private static void a(fe paramfe, fb paramfb)
  {
    Class localClass1 = paramfb.getClass();
    if (!paramfe.b(localClass1))
    {
      HashMap localHashMap = paramfb.en();
      paramfe.a(localClass1, paramfb.en());
      Iterator localIterator = localHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        fb.a locala = (fb.a)localHashMap.get((String)localIterator.next());
        Class localClass2 = locala.ev();
        if (localClass2 == null)
          continue;
        try
        {
          a(paramfe, (fb)localClass2.newInstance());
        }
        catch (InstantiationException localInstantiationException)
        {
          throw new IllegalStateException("Could not instantiate an object of type " + locala.ev().getCanonicalName(), localInstantiationException);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          throw new IllegalStateException("Could not access object of type " + locala.ev().getCanonicalName(), localIllegalAccessException);
        }
      }
    }
  }

  private void a(StringBuilder paramStringBuilder, int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default:
      throw new IllegalArgumentException("Unknown type = " + paramInt);
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
      paramStringBuilder.append(paramObject);
      return;
    case 7:
      paramStringBuilder.append("\"").append(fp.ap(paramObject.toString())).append("\"");
      return;
    case 8:
      paramStringBuilder.append("\"").append(fk.d((byte[])(byte[])paramObject)).append("\"");
      return;
    case 9:
      paramStringBuilder.append("\"").append(fk.e((byte[])(byte[])paramObject));
      paramStringBuilder.append("\"");
      return;
    case 10:
      fq.a(paramStringBuilder, (HashMap)paramObject);
      return;
    case 11:
    }
    throw new IllegalArgumentException("Method does not accept concrete type.");
  }

  private void a(StringBuilder paramStringBuilder, fb.a<?, ?> parama, Parcel paramParcel, int paramInt)
  {
    switch (parama.em())
    {
    default:
      throw new IllegalArgumentException("Unknown field out type = " + parama.em());
    case 0:
      b(paramStringBuilder, parama, a(parama, Integer.valueOf(a.g(paramParcel, paramInt))));
      return;
    case 1:
      b(paramStringBuilder, parama, a(parama, a.i(paramParcel, paramInt)));
      return;
    case 2:
      b(paramStringBuilder, parama, a(parama, Long.valueOf(a.h(paramParcel, paramInt))));
      return;
    case 3:
      b(paramStringBuilder, parama, a(parama, Float.valueOf(a.j(paramParcel, paramInt))));
      return;
    case 4:
      b(paramStringBuilder, parama, a(parama, Double.valueOf(a.k(paramParcel, paramInt))));
      return;
    case 5:
      b(paramStringBuilder, parama, a(parama, a.l(paramParcel, paramInt)));
      return;
    case 6:
      b(paramStringBuilder, parama, a(parama, Boolean.valueOf(a.c(paramParcel, paramInt))));
      return;
    case 7:
      b(paramStringBuilder, parama, a(parama, a.m(paramParcel, paramInt)));
      return;
    case 8:
    case 9:
      b(paramStringBuilder, parama, a(parama, a.p(paramParcel, paramInt)));
      return;
    case 10:
      b(paramStringBuilder, parama, a(parama, c(a.o(paramParcel, paramInt))));
      return;
    case 11:
    }
    throw new IllegalArgumentException("Method does not accept concrete type.");
  }

  private void a(StringBuilder paramStringBuilder, String paramString, fb.a<?, ?> parama, Parcel paramParcel, int paramInt)
  {
    paramStringBuilder.append("\"").append(paramString).append("\":");
    if (parama.ex())
    {
      a(paramStringBuilder, parama, paramParcel, paramInt);
      return;
    }
    b(paramStringBuilder, parama, paramParcel, paramInt);
  }

  private void a(StringBuilder paramStringBuilder, HashMap<String, fb.a<?, ?>> paramHashMap, Parcel paramParcel)
  {
    HashMap localHashMap = c(paramHashMap);
    paramStringBuilder.append('{');
    int i = a.o(paramParcel);
    int j = 0;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.n(paramParcel);
      Map.Entry localEntry = (Map.Entry)localHashMap.get(Integer.valueOf(a.S(k)));
      if (localEntry == null)
        continue;
      if (j != 0)
        paramStringBuilder.append(",");
      a(paramStringBuilder, (String)localEntry.getKey(), (fb.a)localEntry.getValue(), paramParcel, k);
      j = 1;
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    paramStringBuilder.append('}');
  }

  private static fe b(fb paramfb)
  {
    fe localfe = new fe(paramfb.getClass());
    a(localfe, paramfb);
    localfe.eB();
    localfe.eA();
    return localfe;
  }

  private void b(StringBuilder paramStringBuilder, fb.a<?, ?> parama, Parcel paramParcel, int paramInt)
  {
    if (parama.es())
    {
      paramStringBuilder.append("[");
      switch (parama.em())
      {
      default:
        throw new IllegalStateException("Unknown field type out.");
      case 0:
        fj.a(paramStringBuilder, a.r(paramParcel, paramInt));
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      }
      while (true)
      {
        paramStringBuilder.append("]");
        return;
        fj.a(paramStringBuilder, a.t(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.s(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.u(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.v(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.w(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.q(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.x(paramParcel, paramInt));
        continue;
        throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
        Parcel[] arrayOfParcel = a.A(paramParcel, paramInt);
        int j = arrayOfParcel.length;
        for (int k = 0; k < j; k++)
        {
          if (k > 0)
            paramStringBuilder.append(",");
          arrayOfParcel[k].setDataPosition(0);
          a(paramStringBuilder, parama.ez(), arrayOfParcel[k]);
        }
      }
    }
    switch (parama.em())
    {
    default:
      throw new IllegalStateException("Unknown field type out");
    case 0:
      paramStringBuilder.append(a.g(paramParcel, paramInt));
      return;
    case 1:
      paramStringBuilder.append(a.i(paramParcel, paramInt));
      return;
    case 2:
      paramStringBuilder.append(a.h(paramParcel, paramInt));
      return;
    case 3:
      paramStringBuilder.append(a.j(paramParcel, paramInt));
      return;
    case 4:
      paramStringBuilder.append(a.k(paramParcel, paramInt));
      return;
    case 5:
      paramStringBuilder.append(a.l(paramParcel, paramInt));
      return;
    case 6:
      paramStringBuilder.append(a.c(paramParcel, paramInt));
      return;
    case 7:
      String str2 = a.m(paramParcel, paramInt);
      paramStringBuilder.append("\"").append(fp.ap(str2)).append("\"");
      return;
    case 8:
      byte[] arrayOfByte2 = a.p(paramParcel, paramInt);
      paramStringBuilder.append("\"").append(fk.d(arrayOfByte2)).append("\"");
      return;
    case 9:
      byte[] arrayOfByte1 = a.p(paramParcel, paramInt);
      paramStringBuilder.append("\"").append(fk.e(arrayOfByte1));
      paramStringBuilder.append("\"");
      return;
    case 10:
      Bundle localBundle = a.o(paramParcel, paramInt);
      Set localSet = localBundle.keySet();
      localSet.size();
      paramStringBuilder.append("{");
      Iterator localIterator = localSet.iterator();
      for (int i = 1; localIterator.hasNext(); i = 0)
      {
        String str1 = (String)localIterator.next();
        if (i == 0)
          paramStringBuilder.append(",");
        paramStringBuilder.append("\"").append(str1).append("\"");
        paramStringBuilder.append(":");
        paramStringBuilder.append("\"").append(fp.ap(localBundle.getString(str1))).append("\"");
      }
      paramStringBuilder.append("}");
      return;
    case 11:
    }
    Parcel localParcel = a.z(paramParcel, paramInt);
    localParcel.setDataPosition(0);
    a(paramStringBuilder, parama.ez(), localParcel);
  }

  private void b(StringBuilder paramStringBuilder, fb.a<?, ?> parama, Object paramObject)
  {
    if (parama.er())
    {
      b(paramStringBuilder, parama, (ArrayList)paramObject);
      return;
    }
    a(paramStringBuilder, parama.el(), paramObject);
  }

  private void b(StringBuilder paramStringBuilder, fb.a<?, ?> parama, ArrayList<?> paramArrayList)
  {
    paramStringBuilder.append("[");
    int i = paramArrayList.size();
    for (int j = 0; j < i; j++)
    {
      if (j != 0)
        paramStringBuilder.append(",");
      a(paramStringBuilder, parama.el(), paramArrayList.get(j));
    }
    paramStringBuilder.append("]");
  }

  public static HashMap<String, String> c(Bundle paramBundle)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, paramBundle.getString(str));
    }
    return localHashMap;
  }

  private static HashMap<Integer, Map.Entry<String, fb.a<?, ?>>> c(HashMap<String, fb.a<?, ?>> paramHashMap)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localHashMap.put(Integer.valueOf(((fb.a)localEntry.getValue()).eu()), localEntry);
    }
    return localHashMap;
  }

  protected Object ak(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }

  protected boolean al(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }

  public int describeContents()
  {
    return 0;
  }

  public Parcel eF()
  {
    switch (this.CL)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return this.CJ;
      this.CM = b.p(this.CJ);
      b.D(this.CJ, this.CM);
      this.CL = 2;
      continue;
      b.D(this.CJ, this.CM);
      this.CL = 2;
    }
  }

  fe eG()
  {
    switch (this.CK)
    {
    default:
      throw new IllegalStateException("Invalid creation type: " + this.CK);
    case 0:
      return null;
    case 1:
      return this.CC;
    case 2:
    }
    return this.CC;
  }

  public HashMap<String, fb.a<?, ?>> en()
  {
    if (this.CC == null)
      return null;
    return this.CC.ao(this.mClassName);
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public String toString()
  {
    er.b(this.CC, "Cannot convert to JSON on client side.");
    Parcel localParcel = eF();
    localParcel.setDataPosition(0);
    StringBuilder localStringBuilder = new StringBuilder(100);
    a(localStringBuilder, this.CC.ao(this.mClassName), localParcel);
    return localStringBuilder.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    fi.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.fh
 * JD-Core Version:    0.6.0
 */