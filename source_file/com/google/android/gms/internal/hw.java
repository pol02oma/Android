package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hw
  implements Parcelable.Creator<hx.a>
{
  static void a(hx.a parama, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, parama.gt(), false);
    b.c(paramParcel, 1000, parama.wj);
    b.a(paramParcel, 2, parama.getTag(), false);
    b.a(paramParcel, 3, parama.gH(), false);
    b.c(paramParcel, 4, parama.gI());
    b.D(paramParcel, i);
  }

  public hx.a aA(Parcel paramParcel)
  {
    int i = 0;
    String str1 = null;
    int j = a.o(paramParcel);
    String str2 = null;
    String str3 = null;
    int k = 0;
    while (paramParcel.dataPosition() < j)
    {
      int m = a.n(paramParcel);
      switch (a.S(m))
      {
      default:
        a.b(paramParcel, m);
        break;
      case 1:
        str3 = a.m(paramParcel, m);
        break;
      case 1000:
        k = a.g(paramParcel, m);
        break;
      case 2:
        str2 = a.m(paramParcel, m);
        break;
      case 3:
        str1 = a.m(paramParcel, m);
        break;
      case 4:
        i = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new hx.a(k, str3, str2, str1, i);
  }

  public hx.a[] bu(int paramInt)
  {
    return new hx.a[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hw
 * JD-Core Version:    0.6.0
 */