package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import java.util.ArrayList;

public class gx
  implements Parcelable.Creator<gy>
{
  static void a(gy paramgy, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.b(paramParcel, 1, paramgy.fS(), false);
    b.c(paramParcel, 1000, paramgy.getVersionCode());
    b.D(paramParcel, i);
  }

  public gy[] aX(int paramInt)
  {
    return new gy[paramInt];
  }

  public gy al(Parcel paramParcel)
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
        localArrayList = a.c(paramParcel, k, InvitationEntity.CREATOR);
        break;
      case 1000:
        j = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new gy(j, localArrayList);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.gx
 * JD-Core Version:    0.6.0
 */