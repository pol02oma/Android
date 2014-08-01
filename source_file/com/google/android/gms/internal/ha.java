package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.request.GameRequestEntity;
import java.util.ArrayList;

public class ha
  implements Parcelable.Creator<gz>
{
  static void a(gz paramgz, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.b(paramParcel, 1, paramgz.fT(), false);
    b.c(paramParcel, 1000, paramgz.getVersionCode());
    b.D(paramParcel, i);
  }

  public gz[] aY(int paramInt)
  {
    return new gz[paramInt];
  }

  public gz am(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default:
        a.b(paramParcel, k);
        break;
      case 1:
        localArrayList = a.c(paramParcel, k, GameRequestEntity.CREATOR);
        break;
      case 1000:
        j = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new gz(j, localArrayList);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ha
 * JD-Core Version:    0.6.0
 */