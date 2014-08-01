package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import com.google.android.gms.internal.fy;

public final class GameEntity extends fy
  implements Game
{
  public static final Parcelable.Creator<GameEntity> CREATOR = new a();
  private final String FE;
  private final String FF;
  private final String FG;
  private final String FH;
  private final String FI;
  private final Uri FJ;
  private final Uri FK;
  private final Uri FL;
  private final boolean FM;
  private final boolean FN;
  private final String FO;
  private final int FP;
  private final int FQ;
  private final int FR;
  private final boolean FS;
  private final boolean FT;
  private final String FU;
  private final String FV;
  private final String FW;
  private final boolean FX;
  private final int wj;
  private final String wk;

  GameEntity(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Uri paramUri1, Uri paramUri2, Uri paramUri3, boolean paramBoolean1, boolean paramBoolean2, String paramString7, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean3, boolean paramBoolean4, String paramString8, String paramString9, String paramString10, boolean paramBoolean5)
  {
    this.wj = paramInt1;
    this.wk = paramString1;
    this.FE = paramString2;
    this.FF = paramString3;
    this.FG = paramString4;
    this.FH = paramString5;
    this.FI = paramString6;
    this.FJ = paramUri1;
    this.FU = paramString8;
    this.FK = paramUri2;
    this.FV = paramString9;
    this.FL = paramUri3;
    this.FW = paramString10;
    this.FM = paramBoolean1;
    this.FN = paramBoolean2;
    this.FO = paramString7;
    this.FP = paramInt2;
    this.FQ = paramInt3;
    this.FR = paramInt4;
    this.FS = paramBoolean3;
    this.FT = paramBoolean4;
    this.FX = paramBoolean5;
  }

  public GameEntity(Game paramGame)
  {
    this.wj = 2;
    this.wk = paramGame.getApplicationId();
    this.FF = paramGame.getPrimaryCategory();
    this.FG = paramGame.getSecondaryCategory();
    this.FH = paramGame.getDescription();
    this.FI = paramGame.getDeveloperName();
    this.FE = paramGame.getDisplayName();
    this.FJ = paramGame.getIconImageUri();
    this.FU = paramGame.getIconImageUrl();
    this.FK = paramGame.getHiResImageUri();
    this.FV = paramGame.getHiResImageUrl();
    this.FL = paramGame.getFeaturedImageUri();
    this.FW = paramGame.getFeaturedImageUrl();
    this.FM = paramGame.isPlayEnabledGame();
    this.FN = paramGame.isInstanceInstalled();
    this.FO = paramGame.getInstancePackageName();
    this.FP = paramGame.getGameplayAclStatus();
    this.FQ = paramGame.getAchievementTotalCount();
    this.FR = paramGame.getLeaderboardCount();
    this.FS = paramGame.isRealTimeMultiplayerEnabled();
    this.FT = paramGame.isTurnBasedMultiplayerEnabled();
    this.FX = paramGame.isMuted();
  }

  static int a(Game paramGame)
  {
    Object[] arrayOfObject = new Object[18];
    arrayOfObject[0] = paramGame.getApplicationId();
    arrayOfObject[1] = paramGame.getDisplayName();
    arrayOfObject[2] = paramGame.getPrimaryCategory();
    arrayOfObject[3] = paramGame.getSecondaryCategory();
    arrayOfObject[4] = paramGame.getDescription();
    arrayOfObject[5] = paramGame.getDeveloperName();
    arrayOfObject[6] = paramGame.getIconImageUri();
    arrayOfObject[7] = paramGame.getHiResImageUri();
    arrayOfObject[8] = paramGame.getFeaturedImageUri();
    arrayOfObject[9] = Boolean.valueOf(paramGame.isPlayEnabledGame());
    arrayOfObject[10] = Boolean.valueOf(paramGame.isInstanceInstalled());
    arrayOfObject[11] = paramGame.getInstancePackageName();
    arrayOfObject[12] = Integer.valueOf(paramGame.getGameplayAclStatus());
    arrayOfObject[13] = Integer.valueOf(paramGame.getAchievementTotalCount());
    arrayOfObject[14] = Integer.valueOf(paramGame.getLeaderboardCount());
    arrayOfObject[15] = Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled());
    arrayOfObject[16] = Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled());
    arrayOfObject[17] = Boolean.valueOf(paramGame.isMuted());
    return ep.hashCode(arrayOfObject);
  }

  static boolean a(Game paramGame, Object paramObject)
  {
    boolean bool1 = true;
    if (!(paramObject instanceof Game))
      bool1 = false;
    while (true)
    {
      return bool1;
      if (paramGame == paramObject)
        continue;
      Game localGame = (Game)paramObject;
      Boolean localBoolean;
      if ((ep.equal(localGame.getApplicationId(), paramGame.getApplicationId())) && (ep.equal(localGame.getDisplayName(), paramGame.getDisplayName())) && (ep.equal(localGame.getPrimaryCategory(), paramGame.getPrimaryCategory())) && (ep.equal(localGame.getSecondaryCategory(), paramGame.getSecondaryCategory())) && (ep.equal(localGame.getDescription(), paramGame.getDescription())) && (ep.equal(localGame.getDeveloperName(), paramGame.getDeveloperName())) && (ep.equal(localGame.getIconImageUri(), paramGame.getIconImageUri())) && (ep.equal(localGame.getHiResImageUri(), paramGame.getHiResImageUri())) && (ep.equal(localGame.getFeaturedImageUri(), paramGame.getFeaturedImageUri())) && (ep.equal(Boolean.valueOf(localGame.isPlayEnabledGame()), Boolean.valueOf(paramGame.isPlayEnabledGame()))) && (ep.equal(Boolean.valueOf(localGame.isInstanceInstalled()), Boolean.valueOf(paramGame.isInstanceInstalled()))) && (ep.equal(localGame.getInstancePackageName(), paramGame.getInstancePackageName())) && (ep.equal(Integer.valueOf(localGame.getGameplayAclStatus()), Integer.valueOf(paramGame.getGameplayAclStatus()))) && (ep.equal(Integer.valueOf(localGame.getAchievementTotalCount()), Integer.valueOf(paramGame.getAchievementTotalCount()))) && (ep.equal(Integer.valueOf(localGame.getLeaderboardCount()), Integer.valueOf(paramGame.getLeaderboardCount()))) && (ep.equal(Boolean.valueOf(localGame.isRealTimeMultiplayerEnabled()), Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled()))))
      {
        localBoolean = Boolean.valueOf(localGame.isTurnBasedMultiplayerEnabled());
        if ((!paramGame.isTurnBasedMultiplayerEnabled()) || (!ep.equal(Boolean.valueOf(localGame.isMuted()), Boolean.valueOf(paramGame.isMuted()))))
          break label409;
      }
      label409: for (boolean bool2 = bool1; !ep.equal(localBoolean, Boolean.valueOf(bool2)); bool2 = false)
        return false;
    }
  }

  static String b(Game paramGame)
  {
    return ep.e(paramGame).a("ApplicationId", paramGame.getApplicationId()).a("DisplayName", paramGame.getDisplayName()).a("PrimaryCategory", paramGame.getPrimaryCategory()).a("SecondaryCategory", paramGame.getSecondaryCategory()).a("Description", paramGame.getDescription()).a("DeveloperName", paramGame.getDeveloperName()).a("IconImageUri", paramGame.getIconImageUri()).a("IconImageUrl", paramGame.getIconImageUrl()).a("HiResImageUri", paramGame.getHiResImageUri()).a("HiResImageUrl", paramGame.getHiResImageUrl()).a("FeaturedImageUri", paramGame.getFeaturedImageUri()).a("FeaturedImageUrl", paramGame.getFeaturedImageUrl()).a("PlayEnabledGame", Boolean.valueOf(paramGame.isPlayEnabledGame())).a("InstanceInstalled", Boolean.valueOf(paramGame.isInstanceInstalled())).a("InstancePackageName", paramGame.getInstancePackageName()).a("AchievementTotalCount", Integer.valueOf(paramGame.getAchievementTotalCount())).a("LeaderboardCount", Integer.valueOf(paramGame.getLeaderboardCount())).a("RealTimeMultiplayerEnabled", Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled())).a("TurnBasedMultiplayerEnabled", Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled())).toString();
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public Game freeze()
  {
    return this;
  }

  public int getAchievementTotalCount()
  {
    return this.FQ;
  }

  public String getApplicationId()
  {
    return this.wk;
  }

  public String getDescription()
  {
    return this.FH;
  }

  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FH, paramCharArrayBuffer);
  }

  public String getDeveloperName()
  {
    return this.FI;
  }

  public void getDeveloperName(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FI, paramCharArrayBuffer);
  }

  public String getDisplayName()
  {
    return this.FE;
  }

  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FE, paramCharArrayBuffer);
  }

  public Uri getFeaturedImageUri()
  {
    return this.FL;
  }

  public String getFeaturedImageUrl()
  {
    return this.FW;
  }

  public int getGameplayAclStatus()
  {
    return this.FP;
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

  public String getInstancePackageName()
  {
    return this.FO;
  }

  public int getLeaderboardCount()
  {
    return this.FR;
  }

  public String getPrimaryCategory()
  {
    return this.FF;
  }

  public String getSecondaryCategory()
  {
    return this.FG;
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

  public boolean isInstanceInstalled()
  {
    return this.FN;
  }

  public boolean isMuted()
  {
    return this.FX;
  }

  public boolean isPlayEnabledGame()
  {
    return this.FM;
  }

  public boolean isRealTimeMultiplayerEnabled()
  {
    return this.FS;
  }

  public boolean isTurnBasedMultiplayerEnabled()
  {
    return this.FT;
  }

  public String toString()
  {
    return b(this);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    if (!dZ())
    {
      a.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.wk);
    paramParcel.writeString(this.FE);
    paramParcel.writeString(this.FF);
    paramParcel.writeString(this.FG);
    paramParcel.writeString(this.FH);
    paramParcel.writeString(this.FI);
    String str1;
    String str2;
    label90: String str3;
    label110: int j;
    if (this.FJ == null)
    {
      str1 = null;
      paramParcel.writeString(str1);
      if (this.FK != null)
        break label189;
      str2 = null;
      paramParcel.writeString(str2);
      Uri localUri = this.FL;
      str3 = null;
      if (localUri != null)
        break label201;
      paramParcel.writeString(str3);
      if (!this.FM)
        break label213;
      j = i;
      label126: paramParcel.writeInt(j);
      if (!this.FN)
        break label219;
    }
    while (true)
    {
      paramParcel.writeInt(i);
      paramParcel.writeString(this.FO);
      paramParcel.writeInt(this.FP);
      paramParcel.writeInt(this.FQ);
      paramParcel.writeInt(this.FR);
      return;
      str1 = this.FJ.toString();
      break;
      label189: str2 = this.FK.toString();
      break label90;
      label201: str3 = this.FL.toString();
      break label110;
      label213: j = 0;
      break label126;
      label219: i = 0;
    }
  }

  static final class a extends a
  {
    public GameEntity aj(Parcel paramParcel)
    {
      if ((GameEntity.b(GameEntity.fk())) || (GameEntity.at(GameEntity.class.getCanonicalName())))
        return super.aj(paramParcel);
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      String str4 = paramParcel.readString();
      String str5 = paramParcel.readString();
      String str6 = paramParcel.readString();
      String str7 = paramParcel.readString();
      Uri localUri1;
      String str8;
      Uri localUri2;
      label88: String str9;
      Uri localUri3;
      label102: boolean bool1;
      if (str7 == null)
      {
        localUri1 = null;
        str8 = paramParcel.readString();
        if (str8 != null)
          break label183;
        localUri2 = null;
        str9 = paramParcel.readString();
        if (str9 != null)
          break label193;
        localUri3 = null;
        if (paramParcel.readInt() <= 0)
          break label203;
        bool1 = true;
        label112: if (paramParcel.readInt() <= 0)
          break label209;
      }
      label183: label193: label203: label209: for (boolean bool2 = true; ; bool2 = false)
      {
        return new GameEntity(2, str1, str2, str3, str4, str5, str6, localUri1, localUri2, localUri3, bool1, bool2, paramParcel.readString(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), false, false, null, null, null, false);
        localUri1 = Uri.parse(str7);
        break;
        localUri2 = Uri.parse(str8);
        break label88;
        localUri3 = Uri.parse(str9);
        break label102;
        bool1 = false;
        break label112;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.GameEntity
 * JD-Core Version:    0.6.0
 */