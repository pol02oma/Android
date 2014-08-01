package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jo
  implements Parcelable.Creator<jl>
{
  static void a(jl paramjl, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramjl.getVersionCode());
    b.a(paramParcel, 2, paramjl.label, false);
    b.a(paramParcel, 3, paramjl.ZD, paramInt, false);
    b.a(paramParcel, 4, paramjl.type, false);
    b.a(paramParcel, 5, paramjl.YM, paramInt, false);
    b.D(paramParcel, i);
  }

  public jl bj(Parcel paramParcel)
  {
    jr localjr = null;
    int i = a.o(paramParcel);
    int j = 0;
    String str1 = null;
    jm localjm = null;
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
        localjm = (jm)a.a(paramParcel, k, jm.CREATOR);
        break;
      case 4:
        str1 = a.m(paramParcel, k);
        break;
      case 5:
        localjr = (jr)a.a(paramParcel, k, jr.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new jl(j, str2, localjm, str1, localjr);
  }

  public jl[] cp(int paramInt)
  {
    return new jl[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jo
 * JD-Core Version:    0.6.0
 */