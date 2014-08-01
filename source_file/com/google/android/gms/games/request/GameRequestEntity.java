package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import java.util.ArrayList;
import java.util.Arrays;

public final class GameRequestEntity
  implements SafeParcelable, GameRequest
{
  public static final GameRequestEntityCreator CREATOR = new GameRequestEntityCreator();
  private final int AI;
  private final String Hh;
  private final GameEntity Jq;
  private final long Jr;
  private final byte[] Kf;
  private final PlayerEntity Km;
  private final ArrayList<PlayerEntity> Kn;
  private final long Ko;
  private final Bundle Kp;
  private final int wj;

  GameRequestEntity(int paramInt1, GameEntity paramGameEntity, PlayerEntity paramPlayerEntity, byte[] paramArrayOfByte, String paramString, ArrayList<PlayerEntity> paramArrayList, int paramInt2, long paramLong1, long paramLong2, Bundle paramBundle)
  {
    this.wj = paramInt1;
    this.Jq = paramGameEntity;
    this.Km = paramPlayerEntity;
    this.Kf = paramArrayOfByte;
    this.Hh = paramString;
    this.Kn = paramArrayList;
    this.AI = paramInt2;
    this.Jr = paramLong1;
    this.Ko = paramLong2;
    this.Kp = paramBundle;
  }

  public GameRequestEntity(GameRequest paramGameRequest)
  {
    this.wj = 1;
    this.Jq = new GameEntity(paramGameRequest.getGame());
    this.Km = new PlayerEntity(paramGameRequest.getSender());
    this.Hh = paramGameRequest.getRequestId();
    this.AI = paramGameRequest.getType();
    this.Jr = paramGameRequest.getCreationTimestamp();
    this.Ko = paramGameRequest.getExpirationTimestamp();
    byte[] arrayOfByte = paramGameRequest.getData();
    if (arrayOfByte == null)
      this.Kf = null;
    while (true)
    {
      ArrayList localArrayList = paramGameRequest.fU();
      int i = localArrayList.size();
      this.Kn = new ArrayList(i);
      this.Kp = new Bundle();
      for (int j = 0; j < i; j++)
      {
        Player localPlayer = (Player)((Player)localArrayList.get(j)).freeze();
        String str = localPlayer.getPlayerId();
        this.Kn.add((PlayerEntity)localPlayer);
        this.Kp.putInt(str, paramGameRequest.getRecipientStatus(str));
      }
      this.Kf = new byte[arrayOfByte.length];
      System.arraycopy(arrayOfByte, 0, this.Kf, 0, arrayOfByte.length);
    }
  }

  static int a(GameRequest paramGameRequest)
  {
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = paramGameRequest.getGame();
    arrayOfObject[1] = paramGameRequest.fU();
    arrayOfObject[2] = paramGameRequest.getRequestId();
    arrayOfObject[3] = paramGameRequest.getSender();
    arrayOfObject[4] = b(paramGameRequest);
    arrayOfObject[5] = Integer.valueOf(paramGameRequest.getType());
    arrayOfObject[6] = Long.valueOf(paramGameRequest.getCreationTimestamp());
    arrayOfObject[7] = Long.valueOf(paramGameRequest.getExpirationTimestamp());
    return ep.hashCode(arrayOfObject);
  }

  static boolean a(GameRequest paramGameRequest, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof GameRequest))
      i = 0;
    GameRequest localGameRequest;
    do
    {
      do
        return i;
      while (paramGameRequest == paramObject);
      localGameRequest = (GameRequest)paramObject;
    }
    while ((ep.equal(localGameRequest.getGame(), paramGameRequest.getGame())) && (ep.equal(localGameRequest.fU(), paramGameRequest.fU())) && (ep.equal(localGameRequest.getRequestId(), paramGameRequest.getRequestId())) && (ep.equal(localGameRequest.getSender(), paramGameRequest.getSender())) && (Arrays.equals(b(localGameRequest), b(paramGameRequest))) && (ep.equal(Integer.valueOf(localGameRequest.getType()), Integer.valueOf(paramGameRequest.getType()))) && (ep.equal(Long.valueOf(localGameRequest.getCreationTimestamp()), Long.valueOf(paramGameRequest.getCreationTimestamp()))) && (ep.equal(Long.valueOf(localGameRequest.getExpirationTimestamp()), Long.valueOf(paramGameRequest.getExpirationTimestamp()))));
    return false;
  }

  private static int[] b(GameRequest paramGameRequest)
  {
    ArrayList localArrayList = paramGameRequest.fU();
    int i = localArrayList.size();
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++)
      arrayOfInt[j] = paramGameRequest.getRecipientStatus(((Player)localArrayList.get(j)).getPlayerId());
    return arrayOfInt;
  }

  static String c(GameRequest paramGameRequest)
  {
    return ep.e(paramGameRequest).a("Game", paramGameRequest.getGame()).a("Sender", paramGameRequest.getSender()).a("Recipients", paramGameRequest.fU()).a("Data", paramGameRequest.getData()).a("RequestId", paramGameRequest.getRequestId()).a("Type", Integer.valueOf(paramGameRequest.getType())).a("CreationTimestamp", Long.valueOf(paramGameRequest.getCreationTimestamp())).a("ExpirationTimestamp", Long.valueOf(paramGameRequest.getExpirationTimestamp())).toString();
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public ArrayList<Player> fU()
  {
    return new ArrayList(this.Kn);
  }

  public GameRequest freeze()
  {
    return this;
  }

  public long getCreationTimestamp()
  {
    return this.Jr;
  }

  public byte[] getData()
  {
    return this.Kf;
  }

  public long getExpirationTimestamp()
  {
    return this.Ko;
  }

  public Game getGame()
  {
    return this.Jq;
  }

  public Player getRecipient()
  {
    if (this.Kn.isEmpty())
      return null;
    return (Player)this.Kn.get(0);
  }

  public int getRecipientStatus()
  {
    return this.Kp.getInt(((PlayerEntity)this.Kn.get(0)).getPlayerId(), 0);
  }

  public int getRecipientStatus(String paramString)
  {
    return this.Kp.getInt(paramString, 0);
  }

  public String getRequestId()
  {
    return this.Hh;
  }

  public Player getSender()
  {
    return this.Km;
  }

  public int getType()
  {
    return this.AI;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public Bundle gf()
  {
    return this.Kp;
  }

  public int hashCode()
  {
    return a(this);
  }

  public boolean isConsumed()
  {
    return getRecipientStatus() == 1;
  }

  public boolean isDataValid()
  {
    return true;
  }

  public String toString()
  {
    return c(this);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    GameRequestEntityCreator.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.request.GameRequestEntity
 * JD-Core Version:    0.6.0
 */