package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class jk
  implements Parcelable.Creator<jj>
{
  static void a(jj paramjj, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramjj.getVersionCode());
    b.a(paramParcel, 2, paramjj.ZA, false);
    b.a(paramParcel, 3, paramjj.ZB, false);
    b.b(paramParcel, 4, paramjj.ZC, false);
    b.D(paramParcel, i);
  }

  public jj bh(Parcel paramParcel)
  {
    String str1 = null;
    int i = a.o(paramParcel);
    int j = 0;
    ArrayList localArrayList = fj.eH();
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
        localArrayList = a.c(paramParcel, k, jh.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new jj(j, str2, str1, localArrayList);
  }

  public jj[] cn(int paramInt)
  {
    return new jj[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jk
 * JD-Core Version:    0.6.0
 */