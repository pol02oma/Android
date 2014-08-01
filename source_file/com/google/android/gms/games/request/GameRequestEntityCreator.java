package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;

public class GameRequestEntityCreator
  implements Parcelable.Creator<GameRequestEntity>
{
  public static final int CONTENT_DESCRIPTION;

  static void a(GameRequestEntity paramGameRequestEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, paramGameRequestEntity.getGame(), paramInt, false);
    b.c(paramParcel, 1000, paramGameRequestEntity.getVersionCode());
    b.a(paramParcel, 2, paramGameRequestEntity.getSender(), paramInt, false);
    b.a(paramParcel, 3, paramGameRequestEntity.getData(), false);
    b.a(paramParcel, 4, paramGameRequestEntity.getRequestId(), false);
    b.b(paramParcel, 5, paramGameRequestEntity.fU(), false);
    b.c(paramParcel, 7, paramGameRequestEntity.getType());
    b.a(paramParcel, 9, paramGameRequestEntity.getCreationTimestamp());
    b.a(paramParcel, 10, paramGameRequestEntity.getExpirationTimestamp());
    b.a(paramParcel, 11, paramGameRequestEntity.gf(), false);
    b.D(paramParcel, i);
  }

  public GameRequestEntity createFromParcel(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    GameEntity localGameEntity = null;
    PlayerEntity localPlayerEntity = null;
    byte[] arrayOfByte = null;
    String str = null;
    ArrayList localArrayList = null;
    int k = 0;
    long l1 = 0L;
    long l2 = 0L;
    Bundle localBundle = null;
    while (paramParcel.dataPosition() < i)
    {
      int m = a.n(paramParcel);
      switch (a.S(m))
      {
      default:
        a.b(paramParcel, m);
        break;
      case 1:
        localGameEntity = (GameEntity)a.a(paramParcel, m, GameEntity.CREATOR);
        break;
      case 1000:
        j = a.g(paramParcel, m);
        break;
      case 2:
        localPlayerEntity = (PlayerEntity)a.a(paramParcel, m, PlayerEntity.CREATOR);
        break;
      case 3:
        arrayOfByte = a.p(paramParcel, m);
        break;
      case 4:
        str = a.m(paramParcel, m);
        break;
      case 5:
        localArrayList = a.c(paramParcel, m, PlayerEntity.CREATOR);
        break;
      case 7:
        k = a.g(paramParcel, m);
        break;
      case 9:
        l1 = a.h(paramParcel, m);
        break;
      case 10:
        l2 = a.h(paramParcel, m);
        break;
      case 11:
        localBundle = a.o(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new GameRequestEntity(j, localGameEntity, localPlayerEntity, arrayOfByte, str, localArrayList, k, l1, l2, localBundle);
  }

  public GameRequestEntity[] newArray(int paramInt)
  {
    return new GameRequestEntity[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.request.GameRequestEntityCreator
 * JD-Core Version:    0.6.0
 */