package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import com.google.android.gms.internal.fy;
import java.util.ArrayList;

public final class RoomEntity extends fy
  implements Room
{
  public static final Parcelable.Creator<RoomEntity> CREATOR = new a();
  private final String FH;
  private final String GU;
  private final Bundle JO;
  private final String JS;
  private final int JT;
  private final int JU;
  private final long Jr;
  private final ArrayList<ParticipantEntity> Ju;
  private final int Jv;
  private final int wj;

  RoomEntity(int paramInt1, String paramString1, String paramString2, long paramLong, int paramInt2, String paramString3, int paramInt3, Bundle paramBundle, ArrayList<ParticipantEntity> paramArrayList, int paramInt4)
  {
    this.wj = paramInt1;
    this.GU = paramString1;
    this.JS = paramString2;
    this.Jr = paramLong;
    this.JT = paramInt2;
    this.FH = paramString3;
    this.Jv = paramInt3;
    this.JO = paramBundle;
    this.Ju = paramArrayList;
    this.JU = paramInt4;
  }

  public RoomEntity(Room paramRoom)
  {
    this.wj = 2;
    this.GU = paramRoom.getRoomId();
    this.JS = paramRoom.getCreatorId();
    this.Jr = paramRoom.getCreationTimestamp();
    this.JT = paramRoom.getStatus();
    this.FH = paramRoom.getDescription();
    this.Jv = paramRoom.getVariant();
    this.JO = paramRoom.getAutoMatchCriteria();
    ArrayList localArrayList = paramRoom.getParticipants();
    int i = localArrayList.size();
    this.Ju = new ArrayList(i);
    for (int j = 0; j < i; j++)
      this.Ju.add((ParticipantEntity)((Participant)localArrayList.get(j)).freeze());
    this.JU = paramRoom.getAutoMatchWaitEstimateSeconds();
  }

  static int a(Room paramRoom)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramRoom.getRoomId();
    arrayOfObject[1] = paramRoom.getCreatorId();
    arrayOfObject[2] = Long.valueOf(paramRoom.getCreationTimestamp());
    arrayOfObject[3] = Integer.valueOf(paramRoom.getStatus());
    arrayOfObject[4] = paramRoom.getDescription();
    arrayOfObject[5] = Integer.valueOf(paramRoom.getVariant());
    arrayOfObject[6] = paramRoom.getAutoMatchCriteria();
    arrayOfObject[7] = paramRoom.getParticipants();
    arrayOfObject[8] = Integer.valueOf(paramRoom.getAutoMatchWaitEstimateSeconds());
    return ep.hashCode(arrayOfObject);
  }

  static int a(Room paramRoom, String paramString)
  {
    ArrayList localArrayList = paramRoom.getParticipants();
    int i = localArrayList.size();
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      if (localParticipant.getParticipantId().equals(paramString))
        return localParticipant.getStatus();
    }
    throw new IllegalStateException("Participant " + paramString + " is not in room " + paramRoom.getRoomId());
  }

  static boolean a(Room paramRoom, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof Room))
      i = 0;
    Room localRoom;
    do
    {
      do
        return i;
      while (paramRoom == paramObject);
      localRoom = (Room)paramObject;
    }
    while ((ep.equal(localRoom.getRoomId(), paramRoom.getRoomId())) && (ep.equal(localRoom.getCreatorId(), paramRoom.getCreatorId())) && (ep.equal(Long.valueOf(localRoom.getCreationTimestamp()), Long.valueOf(paramRoom.getCreationTimestamp()))) && (ep.equal(Integer.valueOf(localRoom.getStatus()), Integer.valueOf(paramRoom.getStatus()))) && (ep.equal(localRoom.getDescription(), paramRoom.getDescription())) && (ep.equal(Integer.valueOf(localRoom.getVariant()), Integer.valueOf(paramRoom.getVariant()))) && (ep.equal(localRoom.getAutoMatchCriteria(), paramRoom.getAutoMatchCriteria())) && (ep.equal(localRoom.getParticipants(), paramRoom.getParticipants())) && (ep.equal(Integer.valueOf(localRoom.getAutoMatchWaitEstimateSeconds()), Integer.valueOf(paramRoom.getAutoMatchWaitEstimateSeconds()))));
    return false;
  }

  static String b(Room paramRoom)
  {
    return ep.e(paramRoom).a("RoomId", paramRoom.getRoomId()).a("CreatorId", paramRoom.getCreatorId()).a("CreationTimestamp", Long.valueOf(paramRoom.getCreationTimestamp())).a("RoomStatus", Integer.valueOf(paramRoom.getStatus())).a("Description", paramRoom.getDescription()).a("Variant", Integer.valueOf(paramRoom.getVariant())).a("AutoMatchCriteria", paramRoom.getAutoMatchCriteria()).a("Participants", paramRoom.getParticipants()).a("AutoMatchWaitEstimateSeconds", Integer.valueOf(paramRoom.getAutoMatchWaitEstimateSeconds())).toString();
  }

  static String b(Room paramRoom, String paramString)
  {
    ArrayList localArrayList = paramRoom.getParticipants();
    int i = localArrayList.size();
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      Player localPlayer = localParticipant.getPlayer();
      if ((localPlayer != null) && (localPlayer.getPlayerId().equals(paramString)))
        return localParticipant.getParticipantId();
    }
    return null;
  }

  static Participant c(Room paramRoom, String paramString)
  {
    ArrayList localArrayList = paramRoom.getParticipants();
    int i = localArrayList.size();
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      if (localParticipant.getParticipantId().equals(paramString))
        return localParticipant;
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramRoom.getRoomId());
  }

  static ArrayList<String> c(Room paramRoom)
  {
    ArrayList localArrayList1 = paramRoom.getParticipants();
    int i = localArrayList1.size();
    ArrayList localArrayList2 = new ArrayList(i);
    for (int j = 0; j < i; j++)
      localArrayList2.add(((Participant)localArrayList1.get(j)).getParticipantId());
    return localArrayList2;
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public Room freeze()
  {
    return this;
  }

  public Bundle getAutoMatchCriteria()
  {
    return this.JO;
  }

  public int getAutoMatchWaitEstimateSeconds()
  {
    return this.JU;
  }

  public long getCreationTimestamp()
  {
    return this.Jr;
  }

  public String getCreatorId()
  {
    return this.JS;
  }

  public String getDescription()
  {
    return this.FH;
  }

  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FH, paramCharArrayBuffer);
  }

  public Participant getParticipant(String paramString)
  {
    return c(this, paramString);
  }

  public String getParticipantId(String paramString)
  {
    return b(this, paramString);
  }

  public ArrayList<String> getParticipantIds()
  {
    return c(this);
  }

  public int getParticipantStatus(String paramString)
  {
    return a(this, paramString);
  }

  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.Ju);
  }

  public String getRoomId()
  {
    return this.GU;
  }

  public int getStatus()
  {
    return this.JT;
  }

  public int getVariant()
  {
    return this.Jv;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public int hashCode()
  {
    return a(this);
  }

  public boolean isDataValid()
  {
    return true;
  }

  public String toString()
  {
    return b(this);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (!dZ())
      b.a(this, paramParcel, paramInt);
    while (true)
    {
      return;
      paramParcel.writeString(this.GU);
      paramParcel.writeString(this.JS);
      paramParcel.writeLong(this.Jr);
      paramParcel.writeInt(this.JT);
      paramParcel.writeString(this.FH);
      paramParcel.writeInt(this.Jv);
      paramParcel.writeBundle(this.JO);
      int i = this.Ju.size();
      paramParcel.writeInt(i);
      for (int j = 0; j < i; j++)
        ((ParticipantEntity)this.Ju.get(j)).writeToParcel(paramParcel, paramInt);
    }
  }

  static final class a extends b
  {
    public RoomEntity aq(Parcel paramParcel)
    {
      if ((RoomEntity.b(RoomEntity.fk())) || (RoomEntity.at(RoomEntity.class.getCanonicalName())))
        return super.aq(paramParcel);
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      long l = paramParcel.readLong();
      int i = paramParcel.readInt();
      String str3 = paramParcel.readString();
      int j = paramParcel.readInt();
      Bundle localBundle = paramParcel.readBundle();
      int k = paramParcel.readInt();
      ArrayList localArrayList = new ArrayList(k);
      for (int m = 0; m < k; m++)
        localArrayList.add(ParticipantEntity.CREATOR.createFromParcel(paramParcel));
      return new RoomEntity(2, str1, str2, l, i, str3, j, localBundle, localArrayList, -1);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.realtime.RoomEntity
 * JD-Core Version:    0.6.0
 */