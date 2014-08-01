package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

public class a
  implements Parcelable.Creator<InvitationEntity>
{
  static void a(InvitationEntity paramInvitationEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, paramInvitationEntity.getGame(), paramInt, false);
    b.c(paramParcel, 1000, paramInvitationEntity.getVersionCode());
    b.a(paramParcel, 2, paramInvitationEntity.getInvitationId(), false);
    b.a(paramParcel, 3, paramInvitationEntity.getCreationTimestamp());
    b.c(paramParcel, 4, paramInvitationEntity.getInvitationType());
    b.a(paramParcel, 5, paramInvitationEntity.getInviter(), paramInt, false);
    b.b(paramParcel, 6, paramInvitationEntity.getParticipants(), false);
    b.c(paramParcel, 7, paramInvitationEntity.getVariant());
    b.c(paramParcel, 8, paramInvitationEntity.getAvailableAutoMatchSlots());
    b.D(paramParcel, i);
  }

  public InvitationEntity an(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int i = 0;
    int j = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel);
    long l = 0L;
    int k = 0;
    ParticipantEntity localParticipantEntity = null;
    int m = 0;
    String str = null;
    GameEntity localGameEntity = null;
    int n = 0;
    while (paramParcel.dataPosition() < j)
    {
      int i1 = com.google.android.gms.common.internal.safeparcel.a.n(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.S(i1))
      {
      default:
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, i1);
        break;
      case 1:
        localGameEntity = (GameEntity)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, i1, GameEntity.CREATOR);
        break;
      case 1000:
        n = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, i1);
        break;
      case 2:
        str = com.google.android.gms.common.internal.safeparcel.a.m(paramParcel, i1);
        break;
      case 3:
        l = com.google.android.gms.common.internal.safeparcel.a.h(paramParcel, i1);
        break;
      case 4:
        m = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, i1);
        break;
      case 5:
        localParticipantEntity = (ParticipantEntity)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, i1, ParticipantEntity.CREATOR);
        break;
      case 6:
        localArrayList = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, i1, ParticipantEntity.CREATOR);
        break;
      case 7:
        k = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, i1);
        break;
      case 8:
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new InvitationEntity(n, localGameEntity, str, l, m, localParticipantEntity, localArrayList, k, i);
  }

  public InvitationEntity[] ba(int paramInt)
  {
    return new InvitationEntity[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.a
 * JD-Core Version:    0.6.0
 */