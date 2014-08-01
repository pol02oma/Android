package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jw
  implements Parcelable.Creator<jv>
{
  static void a(jv paramjv, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramjv.getVersionCode());
    b.a(paramParcel, 2, paramjv.ZK, false);
    b.a(paramParcel, 3, paramjv.oi, false);
    b.a(paramParcel, 4, paramjv.ZO, paramInt, false);
    b.a(paramParcel, 5, paramjv.ZP, paramInt, false);
    b.a(paramParcel, 6, paramjv.ZQ, paramInt, false);
    b.D(paramParcel, i);
  }

  public jv bn(Parcel paramParcel)
  {
    jt localjt1 = null;
    int i = a.o(paramParcel);
    int j = 0;
    jt localjt2 = null;
    jr localjr = null;
    String str1 = null;
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
        break;
      case 4:
        localjr = (jr)a.a(paramParcel, k, jr.CREATOR);
        break;
      case 5:
        localjt2 = (jt)a.a(paramParcel, k, jt.CREATOR);
        break;
      case 6:
        localjt1 = (jt)a.a(paramParcel, k, jt.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new jv(j, str2, str1, localjr, localjt2, localjt1);
  }

  public jv[] ct(int paramInt)
  {
    return new jv[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jw
 * JD-Core Version:    0.6.0
 */