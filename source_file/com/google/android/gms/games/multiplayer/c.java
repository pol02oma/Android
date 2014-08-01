package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.PlayerEntity;

public class c
  implements Parcelable.Creator<ParticipantEntity>
{
  static void a(ParticipantEntity paramParticipantEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, paramParticipantEntity.getParticipantId(), false);
    b.a(paramParcel, 2, paramParticipantEntity.getDisplayName(), false);
    b.a(paramParcel, 3, paramParticipantEntity.getIconImageUri(), paramInt, false);
    b.a(paramParcel, 4, paramParticipantEntity.getHiResImageUri(), paramInt, false);
    b.c(paramParcel, 5, paramParticipantEntity.getStatus());
    b.a(paramParcel, 6, paramParticipantEntity.ge(), false);
    b.a(paramParcel, 7, paramParticipantEntity.isConnectedToRoom());
    b.a(paramParcel, 8, paramParticipantEntity.getPlayer(), paramInt, false);
    b.c(paramParcel, 9, paramParticipantEntity.getCapabilities());
    b.a(paramParcel, 10, paramParticipantEntity.getResult(), paramInt, false);
    b.a(paramParcel, 11, paramParticipantEntity.getIconImageUrl(), false);
    b.a(paramParcel, 12, paramParticipantEntity.getHiResImageUrl(), false);
    b.c(paramParcel, 1000, paramParticipantEntity.getVersionCode());
    b.D(paramParcel, i);
  }

  public ParticipantEntity ao(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    String str1 = null;
    String str2 = null;
    Uri localUri1 = null;
    Uri localUri2 = null;
    int k = 0;
    String str3 = null;
    boolean bool = false;
    PlayerEntity localPlayerEntity = null;
    int m = 0;
    ParticipantResult localParticipantResult = null;
    String str4 = null;
    String str5 = null;
    while (paramParcel.dataPosition() < i)
    {
      int n = a.n(paramParcel);
      switch (a.S(n))
      {
      default:
        a.b(paramParcel, n);
        break;
      case 1:
        str1 = a.m(paramParcel, n);
        break;
      case 2:
        str2 = a.m(paramParcel, n);
        break;
      case 3:
        localUri1 = (Uri)a.a(paramParcel, n, Uri.CREATOR);
        break;
      case 4:
        localUri2 = (Uri)a.a(paramParcel, n, Uri.CREATOR);
        break;
      case 5:
        k = a.g(paramParcel, n);
        break;
      case 6:
        str3 = a.m(paramParcel, n);
        break;
      case 7:
        bool = a.c(paramParcel, n);
        break;
      case 8:
        localPlayerEntity = (PlayerEntity)a.a(paramParcel, n, PlayerEntity.CREATOR);
        break;
      case 9:
        m = a.g(paramParcel, n);
        break;
      case 10:
        localParticipantResult = (ParticipantResult)a.a(paramParcel, n, ParticipantResult.CREATOR);
        break;
      case 11:
        str4 = a.m(paramParcel, n);
        break;
      case 12:
        str5 = a.m(paramParcel, n);
        break;
      case 1000:
        j = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new ParticipantEntity(j, str1, str2, localUri1, localUri2, k, str3, bool, localPlayerEntity, m, localParticipantResult, str4, str5);
  }

  public ParticipantEntity[] bb(int paramInt)
  {
    return new ParticipantEntity[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.c
 * JD-Core Version:    0.6.0
 */