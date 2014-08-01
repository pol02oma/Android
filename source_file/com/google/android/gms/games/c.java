package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class c
  implements Parcelable.Creator<PlayerEntity>
{
  static void a(PlayerEntity paramPlayerEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, paramPlayerEntity.getPlayerId(), false);
    b.c(paramParcel, 1000, paramPlayerEntity.getVersionCode());
    b.a(paramParcel, 2, paramPlayerEntity.getDisplayName(), false);
    b.a(paramParcel, 3, paramPlayerEntity.getIconImageUri(), paramInt, false);
    b.a(paramParcel, 4, paramPlayerEntity.getHiResImageUri(), paramInt, false);
    b.a(paramParcel, 5, paramPlayerEntity.getRetrievedTimestamp());
    b.c(paramParcel, 6, paramPlayerEntity.fl());
    b.a(paramParcel, 7, paramPlayerEntity.getLastPlayedWithTimestamp());
    b.a(paramParcel, 8, paramPlayerEntity.getIconImageUrl(), false);
    b.a(paramParcel, 9, paramPlayerEntity.getHiResImageUrl(), false);
    b.D(paramParcel, i);
  }

  public PlayerEntity[] aQ(int paramInt)
  {
    return new PlayerEntity[paramInt];
  }

  public PlayerEntity ak(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    String str1 = null;
    String str2 = null;
    Uri localUri1 = null;
    Uri localUri2 = null;
    long l1 = 0L;
    int k = 0;
    long l2 = 0L;
    String str3 = null;
    String str4 = null;
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
      case 1000:
        j = a.g(paramParcel, m);
        break;
      case 2:
        str2 = a.m(paramParcel, m);
        break;
      case 3:
        localUri1 = (Uri)a.a(paramParcel, m, Uri.CREATOR);
        break;
      case 4:
        localUri2 = (Uri)a.a(paramParcel, m, Uri.CREATOR);
        break;
      case 5:
        l1 = a.h(paramParcel, m);
        break;
      case 6:
        k = a.g(paramParcel, m);
        break;
      case 7:
        l2 = a.h(paramParcel, m);
        break;
      case 8:
        str3 = a.m(paramParcel, m);
        break;
      case 9:
        str4 = a.m(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new PlayerEntity(j, str1, str2, localUri1, localUri2, l1, k, l2, str3, str4);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.c
 * JD-Core Version:    0.6.0
 */