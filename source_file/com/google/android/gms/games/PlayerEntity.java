package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ed;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import com.google.android.gms.internal.fy;

public final class PlayerEntity extends fy
  implements Player
{
  public static final Parcelable.Creator<PlayerEntity> CREATOR = new a();
  private final String FE;
  private final Uri FJ;
  private final Uri FK;
  private final String FU;
  private final String FV;
  private final String Gh;
  private final long Gi;
  private final int Gj;
  private final long Gk;
  private final int wj;

  PlayerEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, long paramLong1, int paramInt2, long paramLong2, String paramString3, String paramString4)
  {
    this.wj = paramInt1;
    this.Gh = paramString1;
    this.FE = paramString2;
    this.FJ = paramUri1;
    this.FU = paramString3;
    this.FK = paramUri2;
    this.FV = paramString4;
    this.Gi = paramLong1;
    this.Gj = paramInt2;
    this.Gk = paramLong2;
  }

  public PlayerEntity(Player paramPlayer)
  {
    this.wj = 4;
    this.Gh = paramPlayer.getPlayerId();
    this.FE = paramPlayer.getDisplayName();
    this.FJ = paramPlayer.getIconImageUri();
    this.FU = paramPlayer.getIconImageUrl();
    this.FK = paramPlayer.getHiResImageUri();
    this.FV = paramPlayer.getHiResImageUrl();
    this.Gi = paramPlayer.getRetrievedTimestamp();
    this.Gj = paramPlayer.fl();
    this.Gk = paramPlayer.getLastPlayedWithTimestamp();
    ed.d(this.Gh);
    ed.d(this.FE);
    if (this.Gi > 0L);
    for (boolean bool = true; ; bool = false)
    {
      ed.v(bool);
      return;
    }
  }

  static int a(Player paramPlayer)
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = paramPlayer.getPlayerId();
    arrayOfObject[1] = paramPlayer.getDisplayName();
    arrayOfObject[2] = paramPlayer.getIconImageUri();
    arrayOfObject[3] = paramPlayer.getHiResImageUri();
    arrayOfObject[4] = Long.valueOf(paramPlayer.getRetrievedTimestamp());
    return ep.hashCode(arrayOfObject);
  }

  static boolean a(Player paramPlayer, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof Player))
      i = 0;
    Player localPlayer;
    do
    {
      do
        return i;
      while (paramPlayer == paramObject);
      localPlayer = (Player)paramObject;
    }
    while ((ep.equal(localPlayer.getPlayerId(), paramPlayer.getPlayerId())) && (ep.equal(localPlayer.getDisplayName(), paramPlayer.getDisplayName())) && (ep.equal(localPlayer.getIconImageUri(), paramPlayer.getIconImageUri())) && (ep.equal(localPlayer.getHiResImageUri(), paramPlayer.getHiResImageUri())) && (ep.equal(Long.valueOf(localPlayer.getRetrievedTimestamp()), Long.valueOf(paramPlayer.getRetrievedTimestamp()))));
    return false;
  }

  static String b(Player paramPlayer)
  {
    return ep.e(paramPlayer).a("PlayerId", paramPlayer.getPlayerId()).a("DisplayName", paramPlayer.getDisplayName()).a("IconImageUri", paramPlayer.getIconImageUri()).a("IconImageUrl", paramPlayer.getIconImageUrl()).a("HiResImageUri", paramPlayer.getHiResImageUri()).a("HiResImageUrl", paramPlayer.getHiResImageUrl()).a("RetrievedTimestamp", Long.valueOf(paramPlayer.getRetrievedTimestamp())).toString();
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public int fl()
  {
    return this.Gj;
  }

  public Player freeze()
  {
    return this;
  }

  public String getDisplayName()
  {
    return this.FE;
  }

  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FE, paramCharArrayBuffer);
  }

  public Uri getHiResImageUri()
  {
    return this.FK;
  }

  public String getHiResImageUrl()
  {
    return this.FV;
  }

  public Uri getIconImageUri()
  {
    return this.FJ;
  }

  public String getIconImageUrl()
  {
    return this.FU;
  }

  public long getLastPlayedWithTimestamp()
  {
    return this.Gk;
  }

  public String getPlayerId()
  {
    return this.Gh;
  }

  public long getRetrievedTimestamp()
  {
    return this.Gi;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public boolean hasHiResImage()
  {
    return getHiResImageUri() != null;
  }

  public boolean hasIconImage()
  {
    return getIconImageUri() != null;
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
    {
      c.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.Gh);
    paramParcel.writeString(this.FE);
    String str1;
    String str2;
    if (this.FJ == null)
    {
      str1 = null;
      paramParcel.writeString(str1);
      Uri localUri = this.FK;
      str2 = null;
      if (localUri != null)
        break label84;
    }
    while (true)
    {
      paramParcel.writeString(str2);
      paramParcel.writeLong(this.Gi);
      return;
      str1 = this.FJ.toString();
      break;
      label84: str2 = this.FK.toString();
    }
  }

  static final class a extends c
  {
    public PlayerEntity ak(Parcel paramParcel)
    {
      if ((PlayerEntity.b(PlayerEntity.fk())) || (PlayerEntity.at(PlayerEntity.class.getCanonicalName())))
        return super.ak(paramParcel);
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      String str4 = paramParcel.readString();
      Uri localUri1;
      if (str3 == null)
      {
        localUri1 = null;
        if (str4 != null)
          break label99;
      }
      label99: for (Uri localUri2 = null; ; localUri2 = Uri.parse(str4))
      {
        return new PlayerEntity(4, str1, str2, localUri1, localUri2, paramParcel.readLong(), -1, -1L, null, null);
        localUri1 = Uri.parse(str3);
        break;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.PlayerEntity
 * JD-Core Version:    0.6.0
 */