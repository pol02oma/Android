package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class an
  implements Parcelable.Creator<am>
{
  static void a(am paramam, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramam.versionCode);
    b.c(paramParcel, 2, paramam.lI);
    b.c(paramParcel, 3, paramam.backgroundColor);
    b.c(paramParcel, 4, paramam.lJ);
    b.c(paramParcel, 5, paramam.lK);
    b.c(paramParcel, 6, paramam.lL);
    b.c(paramParcel, 7, paramam.lM);
    b.c(paramParcel, 8, paramam.lN);
    b.c(paramParcel, 9, paramam.lO);
    b.a(paramParcel, 10, paramam.lP, false);
    b.c(paramParcel, 11, paramam.lQ);
    b.a(paramParcel, 12, paramam.lR, false);
    b.c(paramParcel, 13, paramam.lS);
    b.c(paramParcel, 14, paramam.lT);
    b.a(paramParcel, 15, paramam.lU, false);
    b.D(paramParcel, i);
  }

  public am c(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    int i5 = 0;
    String str1 = null;
    int i6 = 0;
    String str2 = null;
    int i7 = 0;
    int i8 = 0;
    String str3 = null;
    while (paramParcel.dataPosition() < i)
    {
      int i9 = a.n(paramParcel);
      switch (a.S(i9))
      {
      default:
        a.b(paramParcel, i9);
        break;
      case 1:
        j = a.g(paramParcel, i9);
        break;
      case 2:
        k = a.g(paramParcel, i9);
        break;
      case 3:
        m = a.g(paramParcel, i9);
        break;
      case 4:
        n = a.g(paramParcel, i9);
        break;
      case 5:
        i1 = a.g(paramParcel, i9);
        break;
      case 6:
        i2 = a.g(paramParcel, i9);
        break;
      case 7:
        i3 = a.g(paramParcel, i9);
        break;
      case 8:
        i4 = a.g(paramParcel, i9);
        break;
      case 9:
        i5 = a.g(paramParcel, i9);
        break;
      case 10:
        str1 = a.m(paramParcel, i9);
        break;
      case 11:
        i6 = a.g(paramParcel, i9);
        break;
      case 12:
        str2 = a.m(paramParcel, i9);
        break;
      case 13:
        i7 = a.g(paramParcel, i9);
        break;
      case 14:
        i8 = a.g(paramParcel, i9);
        break;
      case 15:
        str3 = a.m(paramParcel, i9);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new am(j, k, m, n, i1, i2, i3, i4, i5, str1, i6, str2, i7, i8, str3);
  }

  public am[] e(int paramInt)
  {
    return new am[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.an
 * JD-Core Version:    0.6.0
 */