package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.fm;

public final class d
  implements LeaderboardScore
{
  private final long IQ;
  private final String IR;
  private final String IS;
  private final long IT;
  private final long IU;
  private final String IV;
  private final Uri IW;
  private final Uri IX;
  private final PlayerEntity IY;
  private final String IZ;
  private final String Ja;
  private final String Jb;

  public d(LeaderboardScore paramLeaderboardScore)
  {
    this.IQ = paramLeaderboardScore.getRank();
    this.IR = ((String)er.f(paramLeaderboardScore.getDisplayRank()));
    this.IS = ((String)er.f(paramLeaderboardScore.getDisplayScore()));
    this.IT = paramLeaderboardScore.getRawScore();
    this.IU = paramLeaderboardScore.getTimestampMillis();
    this.IV = paramLeaderboardScore.getScoreHolderDisplayName();
    this.IW = paramLeaderboardScore.getScoreHolderIconImageUri();
    this.IX = paramLeaderboardScore.getScoreHolderHiResImageUri();
    Player localPlayer = paramLeaderboardScore.getScoreHolder();
    if (localPlayer == null);
    for (PlayerEntity localPlayerEntity = null; ; localPlayerEntity = (PlayerEntity)localPlayer.freeze())
    {
      this.IY = localPlayerEntity;
      this.IZ = paramLeaderboardScore.getScoreTag();
      this.Ja = paramLeaderboardScore.getScoreHolderIconImageUrl();
      this.Jb = paramLeaderboardScore.getScoreHolderHiResImageUrl();
      return;
    }
  }

  static int a(LeaderboardScore paramLeaderboardScore)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = Long.valueOf(paramLeaderboardScore.getRank());
    arrayOfObject[1] = paramLeaderboardScore.getDisplayRank();
    arrayOfObject[2] = Long.valueOf(paramLeaderboardScore.getRawScore());
    arrayOfObject[3] = paramLeaderboardScore.getDisplayScore();
    arrayOfObject[4] = Long.valueOf(paramLeaderboardScore.getTimestampMillis());
    arrayOfObject[5] = paramLeaderboardScore.getScoreHolderDisplayName();
    arrayOfObject[6] = paramLeaderboardScore.getScoreHolderIconImageUri();
    arrayOfObject[7] = paramLeaderboardScore.getScoreHolderHiResImageUri();
    arrayOfObject[8] = paramLeaderboardScore.getScoreHolder();
    return ep.hashCode(arrayOfObject);
  }

  static boolean a(LeaderboardScore paramLeaderboardScore, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof LeaderboardScore))
      i = 0;
    LeaderboardScore localLeaderboardScore;
    do
    {
      do
        return i;
      while (paramLeaderboardScore == paramObject);
      localLeaderboardScore = (LeaderboardScore)paramObject;
    }
    while ((ep.equal(Long.valueOf(localLeaderboardScore.getRank()), Long.valueOf(paramLeaderboardScore.getRank()))) && (ep.equal(localLeaderboardScore.getDisplayRank(), paramLeaderboardScore.getDisplayRank())) && (ep.equal(Long.valueOf(localLeaderboardScore.getRawScore()), Long.valueOf(paramLeaderboardScore.getRawScore()))) && (ep.equal(localLeaderboardScore.getDisplayScore(), paramLeaderboardScore.getDisplayScore())) && (ep.equal(Long.valueOf(localLeaderboardScore.getTimestampMillis()), Long.valueOf(paramLeaderboardScore.getTimestampMillis()))) && (ep.equal(localLeaderboardScore.getScoreHolderDisplayName(), paramLeaderboardScore.getScoreHolderDisplayName())) && (ep.equal(localLeaderboardScore.getScoreHolderIconImageUri(), paramLeaderboardScore.getScoreHolderIconImageUri())) && (ep.equal(localLeaderboardScore.getScoreHolderHiResImageUri(), paramLeaderboardScore.getScoreHolderHiResImageUri())) && (ep.equal(localLeaderboardScore.getScoreHolder(), paramLeaderboardScore.getScoreHolder())) && (ep.equal(localLeaderboardScore.getScoreTag(), paramLeaderboardScore.getScoreTag())));
    return false;
  }

  static String b(LeaderboardScore paramLeaderboardScore)
  {
    ep.a locala = ep.e(paramLeaderboardScore).a("Rank", Long.valueOf(paramLeaderboardScore.getRank())).a("DisplayRank", paramLeaderboardScore.getDisplayRank()).a("Score", Long.valueOf(paramLeaderboardScore.getRawScore())).a("DisplayScore", paramLeaderboardScore.getDisplayScore()).a("Timestamp", Long.valueOf(paramLeaderboardScore.getTimestampMillis())).a("DisplayName", paramLeaderboardScore.getScoreHolderDisplayName()).a("IconImageUri", paramLeaderboardScore.getScoreHolderIconImageUri()).a("IconImageUrl", paramLeaderboardScore.getScoreHolderIconImageUrl()).a("HiResImageUri", paramLeaderboardScore.getScoreHolderHiResImageUri()).a("HiResImageUrl", paramLeaderboardScore.getScoreHolderHiResImageUrl());
    if (paramLeaderboardScore.getScoreHolder() == null);
    for (Object localObject = null; ; localObject = paramLeaderboardScore.getScoreHolder())
      return locala.a("Player", localObject).a("ScoreTag", paramLeaderboardScore.getScoreTag()).toString();
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public LeaderboardScore fZ()
  {
    return this;
  }

  public String getDisplayRank()
  {
    return this.IR;
  }

  public void getDisplayRank(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.IR, paramCharArrayBuffer);
  }

  public String getDisplayScore()
  {
    return this.IS;
  }

  public void getDisplayScore(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.IS, paramCharArrayBuffer);
  }

  public long getRank()
  {
    return this.IQ;
  }

  public long getRawScore()
  {
    return this.IT;
  }

  public Player getScoreHolder()
  {
    return this.IY;
  }

  public String getScoreHolderDisplayName()
  {
    if (this.IY == null)
      return this.IV;
    return this.IY.getDisplayName();
  }

  public void getScoreHolderDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (this.IY == null)
    {
      fm.b(this.IV, paramCharArrayBuffer);
      return;
    }
    this.IY.getDisplayName(paramCharArrayBuffer);
  }

  public Uri getScoreHolderHiResImageUri()
  {
    if (this.IY == null)
      return this.IX;
    return this.IY.getHiResImageUri();
  }

  public String getScoreHolderHiResImageUrl()
  {
    if (this.IY == null)
      return this.Jb;
    return this.IY.getHiResImageUrl();
  }

  public Uri getScoreHolderIconImageUri()
  {
    if (this.IY == null)
      return this.IW;
    return this.IY.getIconImageUri();
  }

  public String getScoreHolderIconImageUrl()
  {
    if (this.IY == null)
      return this.Ja;
    return this.IY.getIconImageUrl();
  }

  public String getScoreTag()
  {
    return this.IZ;
  }

  public long getTimestampMillis()
  {
    return this.IU;
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.leaderboard.d
 * JD-Core Version:    0.6.0
 */