package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hq
  implements Parcelable.Creator<hp>
{
  static void a(hp paramhp, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramhp.wj);
    b.a(paramParcel, 2, paramhp.gt(), false);
    b.a(paramParcel, 3, paramhp.getTag(), false);
    b.D(paramParcel, i);
  }

  public hp ax(Parcel paramParcel)
  {
    String str1 = null;
    int i = a.o(paramParcel);
    int j = 0;
    String str2 = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default:
        a.b(paramParcel, k);
        break;
      case 1:
        j = a.g(paramParcel, k);
        break;
      case 2:
        str2 = a.m(paramParcel, k);
        break;
      case 3:
        str1 = a.m(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new hp(j, str2, str1);
  }

  public hp[] br(int paramInt)
  {
    return new hp[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hq
 * JD-Core Version:    0.6.0
 */