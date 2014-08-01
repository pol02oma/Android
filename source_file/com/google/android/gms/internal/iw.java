package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class iw
  implements Parcelable.Creator<ir.b.b>
{
  static void a(ir.b.b paramb, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    Set localSet = paramb.hB();
    if (localSet.contains(Integer.valueOf(1)))
      b.c(paramParcel, 1, paramb.getVersionCode());
    if (localSet.contains(Integer.valueOf(2)))
      b.c(paramParcel, 2, paramb.getHeight());
    if (localSet.contains(Integer.valueOf(3)))
      b.a(paramParcel, 3, paramb.getUrl(), true);
    if (localSet.contains(Integer.valueOf(4)))
      b.c(paramParcel, 4, paramb.getWidth());
    b.D(paramParcel, i);
  }

  public ir.b.b aM(Parcel paramParcel)
  {
    int i = 0;
    int j = a.o(paramParcel);
    HashSet localHashSet = new HashSet();
    String str = null;
    int k = 0;
    int m = 0;
    while (paramParcel.dataPosition() < j)
    {
      int n = a.n(paramParcel);
      switch (a.S(n))
      {
      default:
        a.b(paramParcel, n);
        break;
      case 1:
        m = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2:
        k = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 3:
        str = a.m(paramParcel, n);
        localHashSet.add(Integer.valueOf(3));
        break;
      case 4:
        i = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(4));
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new ir.b.b(localHashSet, m, k, str, i);
  }

  public ir.b.b[] bJ(int paramInt)
  {
    return new ir.b.b[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.iw
 * JD-Core Version:    0.6.0
 */