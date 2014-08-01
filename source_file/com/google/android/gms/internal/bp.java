package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class bp
  implements Parcelable.Creator<bq>
{
  static void a(bq parambq, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, parambq.versionCode);
    b.a(paramParcel, 2, parambq.nr, paramInt, false);
    b.a(paramParcel, 3, parambq.at(), false);
    b.a(paramParcel, 4, parambq.au(), false);
    b.a(paramParcel, 5, parambq.av(), false);
    b.a(paramParcel, 6, parambq.aw(), false);
    b.a(paramParcel, 7, parambq.nw, false);
    b.a(paramParcel, 8, parambq.nx);
    b.a(paramParcel, 9, parambq.ny, false);
    b.a(paramParcel, 10, parambq.ax(), false);
    b.c(paramParcel, 11, parambq.orientation);
    b.c(paramParcel, 12, parambq.nA);
    b.a(paramParcel, 13, parambq.mZ, false);
    b.a(paramParcel, 14, parambq.kN, paramInt, false);
    b.D(paramParcel, i);
  }

  public bq e(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    bn localbn = null;
    IBinder localIBinder1 = null;
    IBinder localIBinder2 = null;
    IBinder localIBinder3 = null;
    IBinder localIBinder4 = null;
    String str1 = null;
    boolean bool = false;
    String str2 = null;
    IBinder localIBinder5 = null;
    int k = 0;
    int m = 0;
    String str3 = null;
    db localdb = null;
    while (paramParcel.dataPosition() < i)
    {
      int n = a.n(paramParcel);
      switch (a.S(n))
      {
      default:
        a.b(paramParcel, n);
        break;
      case 1:
        j = a.g(paramParcel, n);
        break;
      case 2:
        localbn = (bn)a.a(paramParcel, n, bn.CREATOR);
        break;
      case 3:
        localIBinder1 = a.n(paramParcel, n);
        break;
      case 4:
        localIBinder2 = a.n(paramParcel, n);
        break;
      case 5:
        localIBinder3 = a.n(paramParcel, n);
        break;
      case 6:
        localIBinder4 = a.n(paramParcel, n);
        break;
      case 7:
        str1 = a.m(paramParcel, n);
        break;
      case 8:
        bool = a.c(paramParcel, n);
        break;
      case 9:
        str2 = a.m(paramParcel, n);
        break;
      case 10:
        localIBinder5 = a.n(paramParcel, n);
        break;
      case 11:
        k = a.g(paramParcel, n);
        break;
      case 12:
        m = a.g(paramParcel, n);
        break;
      case 13:
        str3 = a.m(paramParcel, n);
        break;
      case 14:
        localdb = (db)a.a(paramParcel, n, db.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new bq(j, localbn, localIBinder1, localIBinder2, localIBinder3, localIBinder4, str1, bool, str2, localIBinder5, k, m, str3, localdb);
  }

  public bq[] j(int paramInt)
  {
    return new bq[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bp
 * JD-Core Version:    0.6.0
 */