package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ju
  implements Parcelable.Creator<jt>
{
  static void a(jt paramjt, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramjt.getVersionCode());
    b.a(paramParcel, 2, paramjt.ZN, false);
    b.a(paramParcel, 3, paramjt.description, false);
    b.D(paramParcel, i);
  }

  public jt bm(Parcel paramParcel)
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
    return new jt(j, str2, str1);
  }

  public jt[] cs(int paramInt)
  {
    return new jt[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ju
 * JD-Core Version:    0.6.0
 */