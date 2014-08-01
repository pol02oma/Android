package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import com.google.android.gms.internal.fy;

public final class ParticipantEntity extends fy
  implements Participant
{
  public static final Parcelable.Creator<ParticipantEntity> CREATOR = new a();
  private final String FE;
  private final Uri FJ;
  private final Uri FK;
  private final String FU;
  private final String FV;
  private final String GZ;
  private final boolean JA;
  private final PlayerEntity JB;
  private final int JC;
  private final ParticipantResult JD;
  private final int Jy;
  private final String Jz;
  private final int wj;

  ParticipantEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, int paramInt2, String paramString3, boolean paramBoolean, PlayerEntity paramPlayerEntity, int paramInt3, ParticipantResult paramParticipantResult, String paramString4, String paramString5)
  {
    this.wj = paramInt1;
    this.GZ = paramString1;
    this.FE = paramString2;
    this.FJ = paramUri1;
    this.FK = paramUri2;
    this.Jy = paramInt2;
    this.Jz = paramString3;
    this.JA = paramBoolean;
    this.JB = paramPlayerEntity;
    this.JC = paramInt3;
    this.JD = paramParticipantResult;
    this.FU = paramString4;
    this.FV = paramString5;
  }

  public ParticipantEntity(Participant paramParticipant)
  {
    this.wj = 3;
    this.GZ = paramParticipant.getParticipantId();
    this.FE = paramParticipant.getDisplayName();
    this.FJ = paramParticipant.getIconImageUri();
    this.FK = paramParticipant.getHiResImageUri();
    this.Jy = paramParticipant.getStatus();
    this.Jz = paramParticipant.ge();
    this.JA = paramParticipant.isConnectedToRoom();
    Player localPlayer = paramParticipant.getPlayer();
    if (localPlayer == null);
    for (PlayerEntity localPlayerEntity = null; ; localPlayerEntity = new PlayerEntity(localPlayer))
    {
      this.JB = localPlayerEntity;
      this.JC = paramParticipant.getCapabilities();
      this.JD = paramParticipant.getResult();
      this.FU = paramParticipant.getIconImageUrl();
      this.FV = paramParticipant.getHiResImageUrl();
      return;
    }
  }

  static int a(Participant paramParticipant)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramParticipant.getPlayer();
    arrayOfObject[1] = Integer.valueOf(paramParticipant.getStatus());
    arrayOfObject[2] = paramParticipant.ge();
    arrayOfObject[3] = Boolean.valueOf(paramParticipant.isConnectedToRoom());
    arrayOfObject[4] = paramParticipant.getDisplayName();
    arrayOfObject[5] = paramParticipant.getIconImageUri();
    arrayOfObject[6] = paramParticipant.getHiResImageUri();
    arrayOfObject[7] = Integer.valueOf(paramParticipant.getCapabilities());
    arrayOfObject[8] = paramParticipant.getResult();
    return ep.hashCode(arrayOfObject);
  }

  static boolean a(Participant paramParticipant, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof Participant))
      i = 0;
    Participant localParticipant;
    do
    {
      do
        return i;
      while (paramParticipant == paramObject);
      localParticipant = (Participant)paramObject;
    }
    while ((ep.equal(localParticipant.getPlayer(), paramParticipant.getPlayer())) && (ep.equal(Integer.valueOf(localParticipant.getStatus()), Integer.valueOf(paramParticipant.getStatus()))) && (ep.equal(localParticipant.ge(), paramParticipant.ge())) && (ep.equal(Boolean.valueOf(localParticipant.isConnectedToRoom()), Boolean.valueOf(paramParticipant.isConnectedToRoom()))) && (ep.equal(localParticipant.getDisplayName(), paramParticipant.getDisplayName())) && (ep.equal(localParticipant.getIconImageUri(), paramParticipant.getIconImageUri())) && (ep.equal(localParticipant.getHiResImageUri(), paramParticipant.getHiResImageUri())) && (ep.equal(Integer.valueOf(localParticipant.getCapabilities()), Integer.valueOf(paramParticipant.getCapabilities()))) && (ep.equal(localParticipant.getResult(), paramParticipant.getResult())));
    return false;
  }

  static String b(Participant paramParticipant)
  {
    return ep.e(paramParticipant).a("Player", paramParticipant.getPlayer()).a("Status", Integer.valueOf(paramParticipant.getStatus())).a("ClientAddress", paramParticipant.ge()).a("ConnectedToRoom", Boolean.valueOf(paramParticipant.isConnectedToRoom())).a("DisplayName", paramParticipant.getDisplayName()).a("IconImage", paramParticipant.getIconImageUri()).a("IconImageUrl", paramParticipant.getIconImageUrl()).a("HiResImage", paramParticipant.getHiResImageUri()).a("HiResImageUrl", paramParticipant.getHiResImageUrl()).a("Capabilities", Integer.valueOf(paramParticipant.getCapabilities())).a("Result", paramParticipant.getResult()).toString();
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public Participant freeze()
  {
    return this;
  }

  public String ge()
  {
    return this.Jz;
  }

  public int getCapabilities()
  {
    return this.JC;
  }

  public String getDisplayName()
  {
    if (this.JB == null)
      return this.FE;
    return this.JB.getDisplayName();
  }

  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (this.JB == null)
    {
      fm.b(this.FE, paramCharArrayBuffer);
      return;
    }
    this.JB.getDisplayName(paramCharArrayBuffer);
  }

  public Uri getHiResImageUri()
  {
    if (this.JB == null)
      return this.FK;
    return this.JB.getHiResImageUri();
  }

  public String getHiResImageUrl()
  {
    if (this.JB == null)
      return this.FV;
    return this.JB.getHiResImageUrl();
  }

  public Uri getIconImageUri()
  {
    if (this.JB == null)
      return this.FJ;
    return this.JB.getIconImageUri();
  }

  public String getIconImageUrl()
  {
    if (this.JB == null)
      return this.FU;
    return this.JB.getIconImageUrl();
  }

  public String getParticipantId()
  {
    return this.GZ;
  }

  public Player getPlayer()
  {
    return this.JB;
  }

  public ParticipantResult getResult()
  {
    return this.JD;
  }

  public int getStatus()
  {
    return this.Jy;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public int hashCode()
  {
    return a(this);
  }

  public boolean isConnectedToRoom()
  {
    return this.JA;
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
    {
      c.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.GZ);
    paramParcel.writeString(this.FE);
    String str1;
    label39: String str2;
    label58: int i;
    label90: int j;
    if (this.FJ == null)
    {
      str1 = null;
      paramParcel.writeString(str1);
      Uri localUri = this.FK;
      str2 = null;
      if (localUri != null)
        break label144;
      paramParcel.writeString(str2);
      paramParcel.writeInt(this.Jy);
      paramParcel.writeString(this.Jz);
      if (!this.JA)
        break label156;
      i = 1;
      paramParcel.writeInt(i);
      PlayerEntity localPlayerEntity = this.JB;
      j = 0;
      if (localPlayerEntity != null)
        break label162;
    }
    while (true)
    {
      paramParcel.writeInt(j);
      if (this.JB == null)
        break;
      this.JB.writeToParcel(paramParcel, paramInt);
      return;
      str1 = this.FJ.toString();
      break label39;
      label144: str2 = this.FK.toString();
      break label58;
      label156: i = 0;
      break label90;
      label162: j = 1;
    }
  }

  static final class a extends c
  {
    public ParticipantEntity ao(Parcel paramParcel)
    {
      boolean bool1 = true;
      if ((ParticipantEntity.b(ParticipantEntity.fk())) || (ParticipantEntity.at(ParticipantEntity.class.getCanonicalName())))
        return super.ao(paramParcel);
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      Uri localUri1;
      String str4;
      Uri localUri2;
      label67: int i;
      String str5;
      boolean bool2;
      if (str3 == null)
      {
        localUri1 = null;
        str4 = paramParcel.readString();
        if (str4 != null)
          break label153;
        localUri2 = null;
        i = paramParcel.readInt();
        str5 = paramParcel.readString();
        if (paramParcel.readInt() <= 0)
          break label163;
        bool2 = bool1;
        label89: if (paramParcel.readInt() <= 0)
          break label169;
        label96: if (!bool1)
          break label174;
      }
      label153: label163: label169: label174: for (PlayerEntity localPlayerEntity = (PlayerEntity)PlayerEntity.CREATOR.createFromParcel(paramParcel); ; localPlayerEntity = null)
      {
        return new ParticipantEntity(3, str1, str2, localUri1, localUri2, i, str5, bool2, localPlayerEntity, 7, null, null, null);
        localUri1 = Uri.parse(str3);
        break;
        localUri2 = Uri.parse(str4);
        break label67;
        bool2 = false;
        break label89;
        bool1 = false;
        break label96;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.ParticipantEntity
 * JD-Core Version:    0.6.0
 */