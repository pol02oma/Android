package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jn
  implements Parcelable.Creator<jm>
{
  static void a(jm paramjm, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramjm.getVersionCode());
    b.c(paramParcel, 2, paramjm.ZE);
    b.a(paramParcel, 3, paramjm.ZF, false);
    b.a(paramParcel, 4, paramjm.ZG);
    b.a(paramParcel, 5, paramjm.ZH, false);
    b.a(paramParcel, 6, paramjm.ZI);
    b.c(paramParcel, 7, paramjm.ZJ);
    b.D(paramParcel, i);
  }

  public jm bi(Parcel paramParcel)
  {
    String str1 = null;
    int i = 0;
    int j = a.o(paramParcel);
    double d = 0.0D;
    long l = 0L;
    int k = -1;
    String str2 = null;
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
        break;
      case 2:
        i = a.g(paramParcel, n);
        break;
      case 3:
        str2 = a.m(paramParcel, n);
        break;
      case 4:
        d = a.k(paramParcel, n);
        break;
      case 5:
        str1 = a.m(paramParcel, n);
        break;
      case 6:
        l = a.h(paramParcel, n);
        break;
      case 7:
        k = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new jm(m, i, str2, d, str1, l, k);
  }

  public jm[] co(int paramInt)
  {
    return new jm[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jn
 * JD-Core Version:    0.6.0
 */