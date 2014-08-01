package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class dc
  implements Parcelable.Creator<db>
{
  static void a(db paramdb, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramdb.versionCode);
    b.a(paramParcel, 2, paramdb.pU, false);
    b.c(paramParcel, 3, paramdb.pV);
    b.c(paramParcel, 4, paramdb.pW);
    b.a(paramParcel, 5, paramdb.pX);
    b.D(paramParcel, i);
  }

  public db h(Parcel paramParcel)
  {
    boolean bool = false;
    int i = a.o(paramParcel);
    String str = null;
    int j = 0;
    int k = 0;
    int m = 0;
    while (paramParcel.dataPosition() < i)
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
        str = a.m(paramParcel, n);
        break;
      case 3:
        k = a.g(paramParcel, n);
        break;
      case 4:
        j = a.g(paramParcel, n);
        break;
      case 5:
        bool = a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new db(m, str, k, j, bool);
  }

  public db[] o(int paramInt)
  {
    return new db[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.dc
 * JD-Core Version:    0.6.0
 */