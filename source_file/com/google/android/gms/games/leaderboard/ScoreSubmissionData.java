package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.gu;
import java.util.HashMap;

public final class ScoreSubmissionData
{
  private static final String[] IH = { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
  private String Gh;
  private String IJ;
  private HashMap<Integer, Result> Jp;
  private int yJ;

  public ScoreSubmissionData(DataHolder paramDataHolder)
  {
    this.yJ = paramDataHolder.getStatusCode();
    this.Jp = new HashMap();
    int i = paramDataHolder.getCount();
    if (i == 3);
    for (boolean bool = true; ; bool = false)
    {
      er.x(bool);
      for (int j = 0; j < i; j++)
      {
        int k = paramDataHolder.I(j);
        if (j == 0)
        {
          this.IJ = paramDataHolder.getString("leaderboardId", j, k);
          this.Gh = paramDataHolder.getString("playerId", j, k);
        }
        if (!paramDataHolder.getBoolean("hasResult", j, k))
          continue;
        a(new Result(paramDataHolder.getLong("rawScore", j, k), paramDataHolder.getString("formattedScore", j, k), paramDataHolder.getString("scoreTag", j, k), paramDataHolder.getBoolean("newBest", j, k)), paramDataHolder.getInteger("timeSpan", j, k));
      }
    }
  }

  private void a(Result paramResult, int paramInt)
  {
    this.Jp.put(Integer.valueOf(paramInt), paramResult);
  }

  public String getLeaderboardId()
  {
    return this.IJ;
  }

  public String getPlayerId()
  {
    return this.Gh;
  }

  public Result getScoreResult(int paramInt)
  {
    return (Result)this.Jp.get(Integer.valueOf(paramInt));
  }

  public String toString()
  {
    ep.a locala = ep.e(this).a("PlayerId", this.Gh).a("StatusCode", Integer.valueOf(this.yJ));
    int i = 0;
    if (i < 3)
    {
      Result localResult = (Result)this.Jp.get(Integer.valueOf(i));
      locala.a("TimesSpan", gu.aW(i));
      if (localResult == null);
      for (String str = "null"; ; str = localResult.toString())
      {
        locala.a("Result", str);
        i++;
        break;
      }
    }
    return locala.toString();
  }

  public static final class Result
  {
    public final String formattedScore;
    public final boolean newBest;
    public final long rawScore;
    public final String scoreTag;

    public Result(long paramLong, String paramString1, String paramString2, boolean paramBoolean)
    {
      this.rawScore = paramLong;
      this.formattedScore = paramString1;
      this.scoreTag = paramString2;
      this.newBest = paramBoolean;
    }

    public String toString()
    {
      return ep.e(this).a("RawScore", Long.valueOf(this.rawScore)).a("FormattedScore", this.formattedScore).a("ScoreTag", this.scoreTag).a("NewBest", Boolean.valueOf(this.newBest)).toString();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.leaderboard.ScoreSubmissionData
 * JD-Core Version:    0.6.0
 */