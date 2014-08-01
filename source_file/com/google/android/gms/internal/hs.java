package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.LocationRequest;

public class hs
  implements Parcelable.Creator<hr>
{
  static void a(hr paramhr, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, paramhr.gu(), paramInt, false);
    b.c(paramParcel, 1000, paramhr.wj);
    b.a(paramParcel, 2, paramhr.gv(), paramInt, false);
    b.D(paramParcel, i);
  }

  public hr ay(Parcel paramParcel)
  {
    Object localObject1 = null;
    int i = a.o(paramParcel);
    int j = 0;
    Object localObject2 = null;
    if (paramParcel.dataPosition() < i)
    {
      int k = a.n(paramParcel);
      Object localObject3;
      Object localObject4;
      int m;
      switch (a.S(k))
      {
      default:
        a.b(paramParcel, k);
        localObject3 = localObject1;
        localObject4 = localObject2;
        m = j;
      case 1:
      case 1000:
      case 2:
      }
      while (true)
      {
        j = m;
        localObject2 = localObject4;
        localObject1 = localObject3;
        break;
        LocationRequest localLocationRequest = (LocationRequest)a.a(paramParcel, k, LocationRequest.CREATOR);
        m = j;
        localObject3 = localObject1;
        localObject4 = localLocationRequest;
        continue;
        int n = a.g(paramParcel, k);
        Object localObject5 = localObject1;
        localObject4 = localObject2;
        m = n;
        localObject3 = localObject5;
        continue;
        localObject3 = (hn)a.a(paramParcel, k, hn.CREATOR);
        localObject4 = localObject2;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return (hr)new hr(j, localObject2, localObject1);
  }

  public hr[] bs(int paramInt)
  {
    return new hr[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hs
 * JD-Core Version:    0.6.0
 */