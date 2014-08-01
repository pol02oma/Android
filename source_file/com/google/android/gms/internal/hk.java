package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hk
  implements Parcelable.Creator<hj>
{
  static void a(hj paramhj, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, paramhj.getRequestId(), false);
    b.c(paramParcel, 1000, paramhj.getVersionCode());
    b.a(paramParcel, 2, paramhj.getExpirationTime());
    b.a(paramParcel, 3, paramhj.gn());
    b.a(paramParcel, 4, paramhj.getLatitude());
    b.a(paramParcel, 5, paramhj.getLongitude());
    b.a(paramParcel, 6, paramhj.go());
    b.c(paramParcel, 7, paramhj.gp());
    b.c(paramParcel, 8, paramhj.getNotificationResponsiveness());
    b.c(paramParcel, 9, paramhj.gq());
    b.D(paramParcel, i);
  }

  public hj av(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    String str = null;
    int k = 0;
    short s = 0;
    double d1 = 0.0D;
    double d2 = 0.0D;
    float f = 0.0F;
    long l = 0L;
    int m = 0;
    int n = -1;
    while (paramParcel.dataPosition() < i)
    {
      int i1 = a.n(paramParcel);
      switch (a.S(i1))
      {
      default:
        a.b(paramParcel, i1);
        break;
      case 1:
        str = a.m(paramParcel, i1);
        break;
      case 1000:
        j = a.g(paramParcel, i1);
        break;
      case 2:
        l = a.h(paramParcel, i1);
        break;
      case 3:
        s = a.f(paramParcel, i1);
        break;
      case 4:
        d1 = a.k(paramParcel, i1);
        break;
      case 5:
        d2 = a.k(paramParcel, i1);
        break;
      case 6:
        f = a.j(paramParcel, i1);
        break;
      case 7:
        k = a.g(paramParcel, i1);
        break;
      case 8:
        m = a.g(paramParcel, i1);
        break;
      case 9:
        n = a.g(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new hj(j, str, k, s, d1, d2, f, l, m, n);
  }

  public hj[] bp(int paramInt)
  {
    return new hj[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hk
 * JD-Core Version:    0.6.0
 */