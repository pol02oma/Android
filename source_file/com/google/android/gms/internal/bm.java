package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class bm
  implements Parcelable.Creator<bn>
{
  static void a(bn parambn, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, parambn.versionCode);
    b.a(paramParcel, 2, parambn.mY, false);
    b.a(paramParcel, 3, parambn.mZ, false);
    b.a(paramParcel, 4, parambn.mimeType, false);
    b.a(paramParcel, 5, parambn.packageName, false);
    b.a(paramParcel, 6, parambn.na, false);
    b.a(paramParcel, 7, parambn.nb, false);
    b.a(paramParcel, 8, parambn.nc, false);
    b.D(paramParcel, i);
  }

  public bn d(Parcel paramParcel)
  {
    String str1 = null;
    int i = a.o(paramParcel);
    int j = 0;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
    String str7 = null;
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
        str7 = a.m(paramParcel, k);
        break;
      case 3:
        str6 = a.m(paramParcel, k);
        break;
      case 4:
        str5 = a.m(paramParcel, k);
        break;
      case 5:
        str4 = a.m(paramParcel, k);
        break;
      case 6:
        str3 = a.m(paramParcel, k);
        break;
      case 7:
        str2 = a.m(paramParcel, k);
        break;
      case 8:
        str1 = a.m(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new bn(j, str7, str6, str5, str4, str3, str2, str1);
  }

  public bn[] i(int paramInt)
  {
    return new bn[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bm
 * JD-Core Version:    0.6.0
 */