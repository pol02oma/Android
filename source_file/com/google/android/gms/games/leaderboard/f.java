package com.google.android.gms.games.leaderboard;

import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.gq;
import com.google.android.gms.internal.gu;

public final class f
  implements LeaderboardVariant
{
  private final int Jd;
  private final int Je;
  private final boolean Jf;
  private final long Jg;
  private final String Jh;
  private final long Ji;
  private final String Jj;
  private final String Jk;
  private final long Jl;
  private final String Jm;
  private final String Jn;
  private final String Jo;

  public f(LeaderboardVariant paramLeaderboardVariant)
  {
    this.Jd = paramLeaderboardVariant.getTimeSpan();
    this.Je = paramLeaderboardVariant.getCollection();
    this.Jf = paramLeaderboardVariant.hasPlayerInfo();
    this.Jg = paramLeaderboardVariant.getRawPlayerScore();
    this.Jh = paramLeaderboardVariant.getDisplayPlayerScore();
    this.Ji = paramLeaderboardVariant.getPlayerRank();
    this.Jj = paramLeaderboardVariant.getDisplayPlayerRank();
    this.Jk = paramLeaderboardVariant.getPlayerScoreTag();
    this.Jl = paramLeaderboardVariant.getNumScores();
    this.Jm = paramLeaderboardVariant.ga();
    this.Jn = paramLeaderboardVariant.gb();
    this.Jo = paramLeaderboardVariant.gc();
  }

  static int a(LeaderboardVariant paramLeaderboardVariant)
  {
    Object[] arrayOfObject = new Object[11];
    arrayOfObject[0] = Integer.valueOf(paramLeaderboardVariant.getTimeSpan());
    arrayOfObject[1] = Integer.valueOf(paramLeaderboardVariant.getCollection());
    arrayOfObject[2] = Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo());
    arrayOfObject[3] = Long.valueOf(paramLeaderboardVariant.getRawPlayerScore());
    arrayOfObject[4] = paramLeaderboardVariant.getDisplayPlayerScore();
    arrayOfObject[5] = Long.valueOf(paramLeaderboardVariant.getPlayerRank());
    arrayOfObject[6] = paramLeaderboardVariant.getDisplayPlayerRank();
    arrayOfObject[7] = Long.valueOf(paramLeaderboardVariant.getNumScores());
    arrayOfObject[8] = paramLeaderboardVariant.ga();
    arrayOfObject[9] = paramLeaderboardVariant.gc();
    arrayOfObject[10] = paramLeaderboardVariant.gb();
    return ep.hashCode(arrayOfObject);
  }

  static boolean a(LeaderboardVariant paramLeaderboardVariant, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof LeaderboardVariant))
      i = 0;
    LeaderboardVariant localLeaderboardVariant;
    do
    {
      do
        return i;
      while (paramLeaderboardVariant == paramObject);
      localLeaderboardVariant = (LeaderboardVariant)paramObject;
    }
    while ((ep.equal(Integer.valueOf(localLeaderboardVariant.getTimeSpan()), Integer.valueOf(paramLeaderboardVariant.getTimeSpan()))) && (ep.equal(Integer.valueOf(localLeaderboardVariant.getCollection()), Integer.valueOf(paramLeaderboardVariant.getCollection()))) && (ep.equal(Boolean.valueOf(localLeaderboardVariant.hasPlayerInfo()), Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo()))) && (ep.equal(Long.valueOf(localLeaderboardVariant.getRawPlayerScore()), Long.valueOf(paramLeaderboardVariant.getRawPlayerScore()))) && (ep.equal(localLeaderboardVariant.getDisplayPlayerScore(), paramLeaderboardVariant.getDisplayPlayerScore())) && (ep.equal(Long.valueOf(localLeaderboardVariant.getPlayerRank()), Long.valueOf(paramLeaderboardVariant.getPlayerRank()))) && (ep.equal(localLeaderboardVariant.getDisplayPlayerRank(), paramLeaderboardVariant.getDisplayPlayerRank())) && (ep.equal(Long.valueOf(localLeaderboardVariant.getNumScores()), Long.valueOf(paramLeaderboardVariant.getNumScores()))) && (ep.equal(localLeaderboardVariant.ga(), paramLeaderboardVariant.ga())) && (ep.equal(localLeaderboardVariant.gc(), paramLeaderboardVariant.gc())) && (ep.equal(localLeaderboardVariant.gb(), paramLeaderboardVariant.gb())));
    return false;
  }

  static String b(LeaderboardVariant paramLeaderboardVariant)
  {
    ep.a locala1 = ep.e(paramLeaderboardVariant).a("TimeSpan", gu.aW(paramLeaderboardVariant.getTimeSpan())).a("Collection", gq.aW(paramLeaderboardVariant.getCollection()));
    Object localObject1;
    String str1;
    label77: Object localObject2;
    label107: ep.a locala4;
    if (paramLeaderboardVariant.hasPlayerInfo())
    {
      localObject1 = Long.valueOf(paramLeaderboardVariant.getRawPlayerScore());
      ep.a locala2 = locala1.a("RawPlayerScore", localObject1);
      if (!paramLeaderboardVariant.hasPlayerInfo())
        break label201;
      str1 = paramLeaderboardVariant.getDisplayPlayerScore();
      ep.a locala3 = locala2.a("DisplayPlayerScore", str1);
      if (!paramLeaderboardVariant.hasPlayerInfo())
        break label208;
      localObject2 = Long.valueOf(paramLeaderboardVariant.getPlayerRank());
      locala4 = locala3.a("PlayerRank", localObject2);
      if (!paramLeaderboardVariant.hasPlayerInfo())
        break label215;
    }
    label201: label208: label215: for (String str2 = paramLeaderboardVariant.getDisplayPlayerRank(); ; str2 = "none")
    {
      return locala4.a("DisplayPlayerRank", str2).a("NumScores", Long.valueOf(paramLeaderboardVariant.getNumScores())).a("TopPageNextToken", paramLeaderboardVariant.ga()).a("WindowPageNextToken", paramLeaderboardVariant.gc()).a("WindowPagePrevToken", paramLeaderboardVariant.gb()).toString();
      localObject1 = "none";
      break;
      str1 = "none";
      break label77;
      localObject2 = "none";
      break label107;
    }
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public String ga()
  {
    return this.Jm;
  }

  public String gb()
  {
    return this.Jn;
  }

  public String gc()
  {
    return this.Jo;
  }

  public LeaderboardVariant gd()
  {
    return this;
  }

  public int getCollection()
  {
    return this.Je;
  }

  public String getDisplayPlayerRank()
  {
    return this.Jj;
  }

  public String getDisplayPlayerScore()
  {
    return this.Jh;
  }

  public long getNumScores()
  {
    return this.Jl;
  }

  public long getPlayerRank()
  {
    return this.Ji;
  }

  public String getPlayerScoreTag()
  {
    return this.Jk;
  }

  public long getRawPlayerScore()
  {
    return this.Jg;
  }

  public int getTimeSpan()
  {
    return this.Jd;
  }

  public boolean hasPlayerInfo()
  {
    return this.Jf;
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
 * Qualified Name:     com.google.android.gms.games.leaderboard.f
 * JD-Core Version:    0.6.0
 */