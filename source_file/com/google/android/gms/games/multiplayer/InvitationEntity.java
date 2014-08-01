package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.fy;
import java.util.ArrayList;

public final class InvitationEntity extends fy
  implements Invitation
{
  public static final Parcelable.Creator<InvitationEntity> CREATOR = new a();
  private final String GP;
  private final GameEntity Jq;
  private final long Jr;
  private final int Js;
  private final ParticipantEntity Jt;
  private final ArrayList<ParticipantEntity> Ju;
  private final int Jv;
  private final int Jw;
  private final int wj;

  InvitationEntity(int paramInt1, GameEntity paramGameEntity, String paramString, long paramLong, int paramInt2, ParticipantEntity paramParticipantEntity, ArrayList<ParticipantEntity> paramArrayList, int paramInt3, int paramInt4)
  {
    this.wj = paramInt1;
    this.Jq = paramGameEntity;
    this.GP = paramString;
    this.Jr = paramLong;
    this.Js = paramInt2;
    this.Jt = paramParticipantEntity;
    this.Ju = paramArrayList;
    this.Jv = paramInt3;
    this.Jw = paramInt4;
  }

  InvitationEntity(Invitation paramInvitation)
  {
    this.wj = 2;
    this.Jq = new GameEntity(paramInvitation.getGame());
    this.GP = paramInvitation.getInvitationId();
    this.Jr = paramInvitation.getCreationTimestamp();
    this.Js = paramInvitation.getInvitationType();
    this.Jv = paramInvitation.getVariant();
    this.Jw = paramInvitation.getAvailableAutoMatchSlots();
    String str = paramInvitation.getInviter().getParticipantId();
    Object localObject = null;
    ArrayList localArrayList = paramInvitation.getParticipants();
    int i = localArrayList.size();
    this.Ju = new ArrayList(i);
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      if (localParticipant.getParticipantId().equals(str))
        localObject = localParticipant;
      this.Ju.add((ParticipantEntity)localParticipant.freeze());
    }
    er.b(localObject, "Must have a valid inviter!");
    this.Jt = ((ParticipantEntity)localObject.freeze());
  }

  static int a(Invitation paramInvitation)
  {
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = paramInvitation.getGame();
    arrayOfObject[1] = paramInvitation.getInvitationId();
    arrayOfObject[2] = Long.valueOf(paramInvitation.getCreationTimestamp());
    arrayOfObject[3] = Integer.valueOf(paramInvitation.getInvitationType());
    arrayOfObject[4] = paramInvitation.getInviter();
    arrayOfObject[5] = paramInvitation.getParticipants();
    arrayOfObject[6] = Integer.valueOf(paramInvitation.getVariant());
    arrayOfObject[7] = Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots());
    return ep.hashCode(arrayOfObject);
  }

  static boolean a(Invitation paramInvitation, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof Invitation))
      i = 0;
    Invitation localInvitation;
    do
    {
      do
        return i;
      while (paramInvitation == paramObject);
      localInvitation = (Invitation)paramObject;
    }
    while ((ep.equal(localInvitation.getGame(), paramInvitation.getGame())) && (ep.equal(localInvitation.getInvitationId(), paramInvitation.getInvitationId())) && (ep.equal(Long.valueOf(localInvitation.getCreationTimestamp()), Long.valueOf(paramInvitation.getCreationTimestamp()))) && (ep.equal(Integer.valueOf(localInvitation.getInvitationType()), Integer.valueOf(paramInvitation.getInvitationType()))) && (ep.equal(localInvitation.getInviter(), paramInvitation.getInviter())) && (ep.equal(localInvitation.getParticipants(), paramInvitation.getParticipants())) && (ep.equal(Integer.valueOf(localInvitation.getVariant()), Integer.valueOf(paramInvitation.getVariant()))) && (ep.equal(Integer.valueOf(localInvitation.getAvailableAutoMatchSlots()), Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots()))));
    return false;
  }

  static String b(Invitation paramInvitation)
  {
    return ep.e(paramInvitation).a("Game", paramInvitation.getGame()).a("InvitationId", paramInvitation.getInvitationId()).a("CreationTimestamp", Long.valueOf(paramInvitation.getCreationTimestamp())).a("InvitationType", Integer.valueOf(paramInvitation.getInvitationType())).a("Inviter", paramInvitation.getInviter()).a("Participants", paramInvitation.getParticipants()).a("Variant", Integer.valueOf(paramInvitation.getVariant())).a("AvailableAutoMatchSlots", Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots())).toString();
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public Invitation freeze()
  {
    return this;
  }

  public int getAvailableAutoMatchSlots()
  {
    return this.Jw;
  }

  public long getCreationTimestamp()
  {
    return this.Jr;
  }

  public Game getGame()
  {
    return this.Jq;
  }

  public String getInvitationId()
  {
    return this.GP;
  }

  public int getInvitationType()
  {
    return this.Js;
  }

  public Participant getInviter()
  {
    return this.Jt;
  }

  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.Ju);
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
      a.a(this, paramParcel, paramInt);
    while (true)
    {
      return;
      this.Jq.writeToParcel(paramParcel, paramInt);
      paramParcel.writeString(this.GP);
      paramParcel.writeLong(this.Jr);
      paramParcel.writeInt(this.Js);
      this.Jt.writeToParcel(paramParcel, paramInt);
      int i = this.Ju.size();
      paramParcel.writeInt(i);
      for (int j = 0; j < i; j++)
        ((ParticipantEntity)this.Ju.get(j)).writeToParcel(paramParcel, paramInt);
    }
  }

  static final class a extends a
  {
    public InvitationEntity an(Parcel paramParcel)
    {
      if ((InvitationEntity.b(InvitationEntity.fk())) || (InvitationEntity.at(InvitationEntity.class.getCanonicalName())))
        return super.an(paramParcel);
      GameEntity localGameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(paramParcel);
      String str = paramParcel.readString();
      long l = paramParcel.readLong();
      int i = paramParcel.readInt();
      ParticipantEntity localParticipantEntity = (ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(paramParcel);
      int j = paramParcel.readInt();
      ArrayList localArrayList = new ArrayList(j);
      for (int k = 0; k < j; k++)
        localArrayList.add(ParticipantEntity.CREATOR.createFromParcel(paramParcel));
      return new InvitationEntity(2, localGameEntity, str, l, i, localParticipantEntity, localArrayList, -1, 0);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.InvitationEntity
 * JD-Core Version:    0.6.0
 */