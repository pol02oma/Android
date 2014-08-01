package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.ArrayList;

public class hy
  implements Parcelable.Creator<hx>
{
  static void a(hx paramhx, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, paramhx.getId(), false);
    b.a(paramParcel, 2, paramhx.gE(), false);
    b.a(paramParcel, 3, paramhx.gF(), paramInt, false);
    b.a(paramParcel, 4, paramhx.gx(), paramInt, false);
    b.a(paramParcel, 5, paramhx.gy());
    b.a(paramParcel, 6, paramhx.gz(), paramInt, false);
    b.a(paramParcel, 7, paramhx.gG(), false);
    b.a(paramParcel, 8, paramhx.gA(), paramInt, false);
    b.a(paramParcel, 9, paramhx.gB());
    b.a(paramParcel, 10, paramhx.getRating());
    b.c(paramParcel, 11, paramhx.gC());
    b.a(paramParcel, 12, paramhx.gD());
    b.b(paramParcel, 13, paramhx.gw(), false);
    b.c(paramParcel, 1000, paramhx.wj);
    b.D(paramParcel, i);
  }

  public hx aB(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    String str1 = null;
    ArrayList localArrayList = null;
    Bundle localBundle = null;
    hz localhz = null;
    LatLng localLatLng = null;
    float f1 = 0.0F;
    LatLngBounds localLatLngBounds = null;
    String str2 = null;
    Uri localUri = null;
    boolean bool = false;
    float f2 = 0.0F;
    int k = 0;
    long l = 0L;
    while (paramParcel.dataPosition() < i)
    {
      int m = a.n(paramParcel);
      switch (a.S(m))
      {
      default:
        a.b(paramParcel, m);
        break;
      case 1:
        str1 = a.m(paramParcel, m);
        break;
      case 2:
        localBundle = a.o(paramParcel, m);
        break;
      case 3:
        localhz = (hz)a.a(paramParcel, m, hz.CREATOR);
        break;
      case 4:
        localLatLng = (LatLng)a.a(paramParcel, m, LatLng.CREATOR);
        break;
      case 5:
        f1 = a.j(paramParcel, m);
        break;
      case 6:
        localLatLngBounds = (LatLngBounds)a.a(paramParcel, m, LatLngBounds.CREATOR);
        break;
      case 7:
        str2 = a.m(paramParcel, m);
        break;
      case 8:
        localUri = (Uri)a.a(paramParcel, m, Uri.CREATOR);
        break;
      case 9:
        bool = a.c(paramParcel, m);
        break;
      case 10:
        f2 = a.j(paramParcel, m);
        break;
      case 11:
        k = a.g(paramParcel, m);
        break;
      case 12:
        l = a.h(paramParcel, m);
        break;
      case 13:
        localArrayList = a.c(paramParcel, m, ht.CREATOR);
        break;
      case 1000:
        j = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new hx(j, str1, localArrayList, localBundle, localhz, localLatLng, f1, localLatLngBounds, str2, localUri, bool, f2, k, l);
  }

  public hx[] bw(int paramInt)
  {
    return new hx[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hy
 * JD-Core Version:    0.6.0
 */