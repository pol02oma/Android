package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.internal.er;
import java.util.ArrayList;

public final class b extends com.google.android.gms.common.data.b
  implements Invitation
{
  private final Game IM;
  private final ArrayList<Participant> Ju;
  private final d Jx;

  b(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    super(paramDataHolder, paramInt1);
    this.IM = new com.google.android.gms.games.b(paramDataHolder, paramInt1);
    this.Ju = new ArrayList(paramInt2);
    String str = getString("external_inviter_id");
    int i = 0;
    Object localObject = null;
    while (i < paramInt2)
    {
      d locald = new d(this.zU, i + this.zW);
      if (locald.getParticipantId().equals(str))
        localObject = locald;
      this.Ju.add(locald);
      i++;
    }
    this.Jx = ((d)er.b(localObject, "Must have a valid inviter!"));
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    return InvitationEntity.a(this, paramObject);
  }

  public Invitation freeze()
  {
    return new InvitationEntity(this);
  }

  public int getAvailableAutoMatchSlots()
  {
    if (!getBoolean("has_automatch_criteria"))
      return 0;
    return getInteger("automatch_max_players");
  }

  public long getCreationTimestamp()
  {
    return Math.max(getLong("creation_timestamp"), getLong("last_modified_timestamp"));
  }

  public Game getGame()
  {
    return this.IM;
  }

  public String getInvitationId()
  {
    return getString("external_invitation_id");
  }

  public int getInvitationType()
  {
    return getInteger("type");
  }

  public Participant getInviter()
  {
    return this.Jx;
  }

  public ArrayList<Participant> getParticipants()
  {
    return this.Ju;
  }

  public int getVariant()
  {
    return getInteger("variant");
  }

  public int hashCode()
  {
    return InvitationEntity.a(this);
  }

  public String toString()
  {
    return InvitationEntity.b(this);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((InvitationEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.b
 * JD-Core Version:    0.6.0
 */