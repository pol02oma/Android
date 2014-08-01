package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;

public class b
  implements Parcelable.Creator<RoomEntity>
{
  static void a(RoomEntity paramRoomEntity, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.p(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 1, paramRoomEntity.getRoomId(), false);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1000, paramRoomEntity.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramRoomEntity.getCreatorId(), false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 3, paramRoomEntity.getCreationTimestamp());
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 4, paramRoomEntity.getStatus());
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 5, paramRoomEntity.getDescription(), false);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 6, paramRoomEntity.getVariant());
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 7, paramRoomEntity.getAutoMatchCriteria(), false);
    com.google.android.gms.common.internal.safeparcel.b.b(paramParcel, 8, paramRoomEntity.getParticipants(), false);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 9, paramRoomEntity.getAutoMatchWaitEstimateSeconds());
    com.google.android.gms.common.internal.safeparcel.b.D(paramParcel, i);
  }

  public RoomEntity aq(Parcel paramParcel)
  {
    int i = 0;
    ArrayList localArrayList = null;
    int j = a.o(paramParcel);
    long l = 0L;
    Bundle localBundle = null;
    int k = 0;
    String str1 = null;
    int m = 0;
    String str2 = null;
    String str3 = null;
    int n = 0;
    while (paramParcel.dataPosition() < j)
    {
      int i1 = a.n(paramParcel);
      switch (a.S(i1))
      {
      default:
        a.b(paramParcel, i1);
        break;
      case 1:
        str3 = a.m(paramParcel, i1);
        break;
      case 1000:
        n = a.g(paramParcel, i1);
        break;
      case 2:
        str2 = a.m(paramParcel, i1);
        break;
      case 3:
        l = a.h(paramParcel, i1);
        break;
      case 4:
        m = a.g(paramParcel, i1);
        break;
      case 5:
        str1 = a.m(paramParcel, i1);
        break;
      case 6:
        k = a.g(paramParcel, i1);
        break;
      case 7:
        localBundle = a.o(paramParcel, i1);
        break;
      case 8:
        localArrayList = a.c(paramParcel, i1, ParticipantEntity.CREATOR);
        break;
      case 9:
        i = a.g(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new RoomEntity(n, str3, str2, l, m, str1, k, localBundle, localArrayList, i);
  }

  public RoomEntity[] bd(int paramInt)
  {
    return new RoomEntity[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.realtime.b
 * JD-Core Version:    0.6.0
 */