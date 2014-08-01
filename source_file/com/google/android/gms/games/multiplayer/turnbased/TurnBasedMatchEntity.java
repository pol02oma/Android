package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import java.util.ArrayList;

public final class TurnBasedMatchEntity
  implements SafeParcelable, TurnBasedMatch
{
  public static final TurnBasedMatchEntityCreator CREATOR = new TurnBasedMatchEntityCreator();
  private final String FH;
  private final String GV;
  private final Bundle JO;
  private final String JS;
  private final GameEntity Jq;
  private final long Jr;
  private final ArrayList<ParticipantEntity> Ju;
  private final int Jv;
  private final String Ka;
  private final long Kb;
  private final String Kc;
  private final int Kd;
  private final int Ke;
  private final byte[] Kf;
  private final String Kg;
  private final byte[] Kh;
  private final int Ki;
  private final int Kj;
  private final boolean Kk;
  private final String Kl;
  private final int wj;

  TurnBasedMatchEntity(int paramInt1, GameEntity paramGameEntity, String paramString1, String paramString2, long paramLong1, String paramString3, long paramLong2, String paramString4, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte1, ArrayList<ParticipantEntity> paramArrayList, String paramString5, byte[] paramArrayOfByte2, int paramInt5, Bundle paramBundle, int paramInt6, boolean paramBoolean, String paramString6, String paramString7)
  {
    this.wj = paramInt1;
    this.Jq = paramGameEntity;
    this.GV = paramString1;
    this.JS = paramString2;
    this.Jr = paramLong1;
    this.Ka = paramString3;
    this.Kb = paramLong2;
    this.Kc = paramString4;
    this.Kd = paramInt2;
    this.Kj = paramInt6;
    this.Jv = paramInt3;
    this.Ke = paramInt4;
    this.Kf = paramArrayOfByte1;
    this.Ju = paramArrayList;
    this.Kg = paramString5;
    this.Kh = paramArrayOfByte2;
    this.Ki = paramInt5;
    this.JO = paramBundle;
    this.Kk = paramBoolean;
    this.FH = paramString6;
    this.Kl = paramString7;
  }

  public TurnBasedMatchEntity(TurnBasedMatch paramTurnBasedMatch)
  {
    this.wj = 2;
    this.Jq = new GameEntity(paramTurnBasedMatch.getGame());
    this.GV = paramTurnBasedMatch.getMatchId();
    this.JS = paramTurnBasedMatch.getCreatorId();
    this.Jr = paramTurnBasedMatch.getCreationTimestamp();
    this.Ka = paramTurnBasedMatch.getLastUpdaterId();
    this.Kb = paramTurnBasedMatch.getLastUpdatedTimestamp();
    this.Kc = paramTurnBasedMatch.getPendingParticipantId();
    this.Kd = paramTurnBasedMatch.getStatus();
    this.Kj = paramTurnBasedMatch.getTurnStatus();
    this.Jv = paramTurnBasedMatch.getVariant();
    this.Ke = paramTurnBasedMatch.getVersion();
    this.Kg = paramTurnBasedMatch.getRematchId();
    this.Ki = paramTurnBasedMatch.getMatchNumber();
    this.JO = paramTurnBasedMatch.getAutoMatchCriteria();
    this.Kk = paramTurnBasedMatch.isLocallyModified();
    this.FH = paramTurnBasedMatch.getDescription();
    this.Kl = paramTurnBasedMatch.getDescriptionParticipantId();
    byte[] arrayOfByte1 = paramTurnBasedMatch.getData();
    byte[] arrayOfByte2;
    if (arrayOfByte1 == null)
    {
      this.Kf = null;
      arrayOfByte2 = paramTurnBasedMatch.getPreviousMatchData();
      if (arrayOfByte2 != null)
        break label314;
      this.Kh = null;
    }
    while (true)
    {
      ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
      int i = localArrayList.size();
      this.Ju = new ArrayList(i);
      for (int j = 0; j < i; j++)
        this.Ju.add((ParticipantEntity)(ParticipantEntity)((Participant)localArrayList.get(j)).freeze());
      this.Kf = new byte[arrayOfByte1.length];
      System.arraycopy(arrayOfByte1, 0, this.Kf, 0, arrayOfByte1.length);
      break;
      label314: this.Kh = new byte[arrayOfByte2.length];
      System.arraycopy(arrayOfByte2, 0, this.Kh, 0, arrayOfByte2.length);
    }
  }

  static int a(TurnBasedMatch paramTurnBasedMatch)
  {
    Object[] arrayOfObject = new Object[18];
    arrayOfObject[0] = paramTurnBasedMatch.getGame();
    arrayOfObject[1] = paramTurnBasedMatch.getMatchId();
    arrayOfObject[2] = paramTurnBasedMatch.getCreatorId();
    arrayOfObject[3] = Long.valueOf(paramTurnBasedMatch.getCreationTimestamp());
    arrayOfObject[4] = paramTurnBasedMatch.getLastUpdaterId();
    arrayOfObject[5] = Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp());
    arrayOfObject[6] = paramTurnBasedMatch.getPendingParticipantId();
    arrayOfObject[7] = Integer.valueOf(paramTurnBasedMatch.getStatus());
    arrayOfObject[8] = Integer.valueOf(paramTurnBasedMatch.getTurnStatus());
    arrayOfObject[9] = paramTurnBasedMatch.getDescription();
    arrayOfObject[10] = Integer.valueOf(paramTurnBasedMatch.getVariant());
    arrayOfObject[11] = Integer.valueOf(paramTurnBasedMatch.getVersion());
    arrayOfObject[12] = paramTurnBasedMatch.getParticipants();
    arrayOfObject[13] = paramTurnBasedMatch.getRematchId();
    arrayOfObject[14] = Integer.valueOf(paramTurnBasedMatch.getMatchNumber());
    arrayOfObject[15] = paramTurnBasedMatch.getAutoMatchCriteria();
    arrayOfObject[16] = Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots());
    arrayOfObject[17] = Boolean.valueOf(paramTurnBasedMatch.isLocallyModified());
    return ep.hashCode(arrayOfObject);
  }

  static int a(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
    int i = localArrayList.size();
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      if (localParticipant.getParticipantId().equals(paramString))
        return localParticipant.getStatus();
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramTurnBasedMatch.getMatchId());
  }

  static boolean a(TurnBasedMatch paramTurnBasedMatch, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof TurnBasedMatch))
      i = 0;
    TurnBasedMatch localTurnBasedMatch;
    do
    {
      do
        return i;
      while (paramTurnBasedMatch == paramObject);
      localTurnBasedMatch = (TurnBasedMatch)paramObject;
    }
    while ((ep.equal(localTurnBasedMatch.getGame(), paramTurnBasedMatch.getGame())) && (ep.equal(localTurnBasedMatch.getMatchId(), paramTurnBasedMatch.getMatchId())) && (ep.equal(localTurnBasedMatch.getCreatorId(), paramTurnBasedMatch.getCreatorId())) && (ep.equal(Long.valueOf(localTurnBasedMatch.getCreationTimestamp()), Long.valueOf(paramTurnBasedMatch.getCreationTimestamp()))) && (ep.equal(localTurnBasedMatch.getLastUpdaterId(), paramTurnBasedMatch.getLastUpdaterId())) && (ep.equal(Long.valueOf(localTurnBasedMatch.getLastUpdatedTimestamp()), Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp()))) && (ep.equal(localTurnBasedMatch.getPendingParticipantId(), paramTurnBasedMatch.getPendingParticipantId())) && (ep.equal(Integer.valueOf(localTurnBasedMatch.getStatus()), Integer.valueOf(paramTurnBasedMatch.getStatus()))) && (ep.equal(Integer.valueOf(localTurnBasedMatch.getTurnStatus()), Integer.valueOf(paramTurnBasedMatch.getTurnStatus()))) && (ep.equal(localTurnBasedMatch.getDescription(), paramTurnBasedMatch.getDescription())) && (ep.equal(Integer.valueOf(localTurnBasedMatch.getVariant()), Integer.valueOf(paramTurnBasedMatch.getVariant()))) && (ep.equal(Integer.valueOf(localTurnBasedMatch.getVersion()), Integer.valueOf(paramTurnBasedMatch.getVersion()))) && (ep.equal(localTurnBasedMatch.getParticipants(), paramTurnBasedMatch.getParticipants())) && (ep.equal(localTurnBasedMatch.getRematchId(), paramTurnBasedMatch.getRematchId())) && (ep.equal(Integer.valueOf(localTurnBasedMatch.getMatchNumber()), Integer.valueOf(paramTurnBasedMatch.getMatchNumber()))) && (ep.equal(localTurnBasedMatch.getAutoMatchCriteria(), paramTurnBasedMatch.getAutoMatchCriteria())) && (ep.equal(Integer.valueOf(localTurnBasedMatch.getAvailableAutoMatchSlots()), Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots()))) && (ep.equal(Boolean.valueOf(localTurnBasedMatch.isLocallyModified()), Boolean.valueOf(paramTurnBasedMatch.isLocallyModified()))));
    return false;
  }

  static String b(TurnBasedMatch paramTurnBasedMatch)
  {
    return ep.e(paramTurnBasedMatch).a("Game", paramTurnBasedMatch.getGame()).a("MatchId", paramTurnBasedMatch.getMatchId()).a("CreatorId", paramTurnBasedMatch.getCreatorId()).a("CreationTimestamp", Long.valueOf(paramTurnBasedMatch.getCreationTimestamp())).a("LastUpdaterId", paramTurnBasedMatch.getLastUpdaterId()).a("LastUpdatedTimestamp", Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp())).a("PendingParticipantId", paramTurnBasedMatch.getPendingParticipantId()).a("MatchStatus", Integer.valueOf(paramTurnBasedMatch.getStatus())).a("TurnStatus", Integer.valueOf(paramTurnBasedMatch.getTurnStatus())).a("Description", paramTurnBasedMatch.getDescription()).a("Variant", Integer.valueOf(paramTurnBasedMatch.getVariant())).a("Data", paramTurnBasedMatch.getData()).a("Version", Integer.valueOf(paramTurnBasedMatch.getVersion())).a("Participants", paramTurnBasedMatch.getParticipants()).a("RematchId", paramTurnBasedMatch.getRematchId()).a("PreviousData", paramTurnBasedMatch.getPreviousMatchData()).a("MatchNumber", Integer.valueOf(paramTurnBasedMatch.getMatchNumber())).a("AutoMatchCriteria", paramTurnBasedMatch.getAutoMatchCriteria()).a("AvailableAutoMatchSlots", Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots())).a("LocallyModified", Boolean.valueOf(paramTurnBasedMatch.isLocallyModified())).a("DescriptionParticipantId", paramTurnBasedMatch.getDescriptionParticipantId()).toString();
  }

  static String b(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
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

  static Participant c(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
    int i = localArrayList.size();
    for (int j = 0; j < i; j++)
    {
      Participant localParticipant = (Participant)localArrayList.get(j);
      if (localParticipant.getParticipantId().equals(paramString))
        return localParticipant;
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramTurnBasedMatch.getMatchId());
  }

  static ArrayList<String> c(TurnBasedMatch paramTurnBasedMatch)
  {
    ArrayList localArrayList1 = paramTurnBasedMatch.getParticipants();
    int i = localArrayList1.size();
    ArrayList localArrayList2 = new ArrayList(i);
    for (int j = 0; j < i; j++)
      localArrayList2.add(((Participant)localArrayList1.get(j)).getParticipantId());
    return localArrayList2;
  }

  public boolean canRematch()
  {
    return (this.Kd == 2) && (this.Kg == null);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public TurnBasedMatch freeze()
  {
    return this;
  }

  public Bundle getAutoMatchCriteria()
  {
    return this.JO;
  }

  public int getAvailableAutoMatchSlots()
  {
    if (this.JO == null)
      return 0;
    return this.JO.getInt("max_automatch_players");
  }

  public long getCreationTimestamp()
  {
    return this.Jr;
  }

  public String getCreatorId()
  {
    return this.JS;
  }

  public byte[] getData()
  {
    return this.Kf;
  }

  public String getDescription()
  {
    return this.FH;
  }

  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FH, paramCharArrayBuffer);
  }

  public Participant getDescriptionParticipant()
  {
    return getParticipant(getDescriptionParticipantId());
  }

  public String getDescriptionParticipantId()
  {
    return this.Kl;
  }

  public Game getGame()
  {
    return this.Jq;
  }

  public long getLastUpdatedTimestamp()
  {
    return this.Kb;
  }

  public String getLastUpdaterId()
  {
    return this.Ka;
  }

  public String getMatchId()
  {
    return this.GV;
  }

  public int getMatchNumber()
  {
    return this.Ki;
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

  public String getPendingParticipantId()
  {
    return this.Kc;
  }

  public byte[] getPreviousMatchData()
  {
    return this.Kh;
  }

  public String getRematchId()
  {
    return this.Kg;
  }

  public int getStatus()
  {
    return this.Kd;
  }

  public int getTurnStatus()
  {
    return this.Kj;
  }

  public int getVariant()
  {
    return this.Jv;
  }

  public int getVersion()
  {
    return this.Ke;
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

  public boolean isLocallyModified()
  {
    return this.Kk;
  }

  public String toString()
  {
    return b(this);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    TurnBasedMatchEntityCreator.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchEntity
 * JD-Core Version:    0.6.0
 */