package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class iv
  implements Parcelable.Creator<ir.b.a>
{
  static void a(ir.b.a parama, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    Set localSet = parama.hB();
    if (localSet.contains(Integer.valueOf(1)))
      b.c(paramParcel, 1, parama.getVersionCode());
    if (localSet.contains(Integer.valueOf(2)))
      b.c(paramParcel, 2, parama.getLeftImageOffset());
    if (localSet.contains(Integer.valueOf(3)))
      b.c(paramParcel, 3, parama.getTopImageOffset());
    b.D(paramParcel, i);
  }

  public ir.b.a aL(Parcel paramParcel)
  {
    int i = 0;
    int j = a.o(paramParcel);
    HashSet localHashSet = new HashSet();
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
        i = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(3));
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new ir.b.a(localHashSet, m, k, i);
  }

  public ir.b.a[] bI(int paramInt)
  {
    return new ir.b.a[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.iv
 * JD-Core Version:    0.6.0
 */