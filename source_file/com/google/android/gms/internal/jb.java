package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class jb
  implements Parcelable.Creator<ir.h>
{
  static void a(ir.h paramh, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    Set localSet = paramh.hB();
    if (localSet.contains(Integer.valueOf(1)))
      b.c(paramParcel, 1, paramh.getVersionCode());
    if (localSet.contains(Integer.valueOf(3)))
      b.c(paramParcel, 3, paramh.io());
    if (localSet.contains(Integer.valueOf(4)))
      b.a(paramParcel, 4, paramh.getValue(), true);
    if (localSet.contains(Integer.valueOf(5)))
      b.a(paramParcel, 5, paramh.getLabel(), true);
    if (localSet.contains(Integer.valueOf(6)))
      b.c(paramParcel, 6, paramh.getType());
    b.D(paramParcel, i);
  }

  public ir.h aR(Parcel paramParcel)
  {
    String str1 = null;
    int i = 0;
    int j = a.o(paramParcel);
    HashSet localHashSet = new HashSet();
    int k = 0;
    String str2 = null;
    int m = 0;
    while (paramParcel.dataPosition() < j)
    {
      int n = a.n(paramParcel);
      switch (a.S(n))
      {
      case 2:
      default:
        a.b(paramParcel, n);
        break;
      case 1:
        m = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 3:
        i = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(3));
        break;
      case 4:
        str1 = a.m(paramParcel, n);
        localHashSet.add(Integer.valueOf(4));
        break;
      case 5:
        str2 = a.m(paramParcel, n);
        localHashSet.add(Integer.valueOf(5));
        break;
      case 6:
        k = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(6));
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new ir.h(localHashSet, m, str2, k, str1, i);
  }

  public ir.h[] bO(int paramInt)
  {
    return new ir.h[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jb
 * JD-Core Version:    0.6.0
 */