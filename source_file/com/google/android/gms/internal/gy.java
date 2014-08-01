package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.games.multiplayer.Participant;
import java.util.ArrayList;

public final class gy
  implements SafeParcelable, Invitation
{
  public static final gx CREATOR = new gx();
  private final ArrayList<InvitationEntity> IF;
  private final int wj;

  gy(int paramInt, ArrayList<InvitationEntity> paramArrayList)
  {
    this.wj = paramInt;
    this.IF = paramArrayList;
    fR();
  }

  private void fR()
  {
    if (!this.IF.isEmpty());
    for (boolean bool = true; ; bool = false)
    {
      ed.v(bool);
      Invitation localInvitation1 = (Invitation)this.IF.get(0);
      int i = this.IF.size();
      for (int j = 1; j < i; j++)
      {
        Invitation localInvitation2 = (Invitation)this.IF.get(j);
        ed.a(localInvitation1.getInviter().equals(localInvitation2.getInviter()), "All the invitations must be from the same inviter");
      }
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof gy))
      return false;
    if (this == paramObject)
      return true;
    gy localgy = (gy)paramObject;
    if (localgy.IF.size() != this.IF.size())
      return false;
    int i = this.IF.size();
    for (int j = 0; j < i; j++)
      if (!((Invitation)this.IF.get(j)).equals((Invitation)localgy.IF.get(j)))
        return false;
    return true;
  }

  public ArrayList<Invitation> fS()
  {
    return new ArrayList(this.IF);
  }

  public Invitation freeze()
  {
    return this;
  }

  public int getAvailableAutoMatchSlots()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }

  public long getCreationTimestamp()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }

  public Game getGame()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }

  public String getInvitationId()
  {
    return ((InvitationEntity)this.IF.get(0)).getInvitationId();
  }

  public int getInvitationType()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }

  public Participant getInviter()
  {
    return ((InvitationEntity)this.IF.get(0)).getInviter();
  }

  public ArrayList<Participant> getParticipants()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }

  public int getVariant()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public int hashCode()
  {
    return ep.hashCode(this.IF.toArray());
  }

  public boolean isDataValid()
  {
    return true;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    gx.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.gy
 * JD-Core Version:    0.6.0
 */