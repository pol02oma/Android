package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.a;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

public final class gj
  implements Leaderboards
{
  public Intent getAllLeaderboardsIntent(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fr();
  }

  public Intent getLeaderboardIntent(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return Games.c(paramGoogleApiClient).au(paramString);
  }

  public PendingResult<Leaderboards.LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2)
  {
    return paramGoogleApiClient.a(new b(paramString, paramInt1, paramInt2)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, null, this.HT, this.HU, this.HV);
      }
    });
  }

  public PendingResult<Leaderboards.LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean)
  {
    return paramGoogleApiClient.a(new a(paramString, paramBoolean)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, this.HT, this.HH);
      }
    });
  }

  public PendingResult<Leaderboards.LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    return paramGoogleApiClient.a(new a(paramBoolean)
    {
      protected void a(fx paramfx)
      {
        paramfx.b(this, this.HH);
      }
    });
  }

  public PendingResult<Leaderboards.LoadScoresResult> loadMoreScores(GoogleApiClient paramGoogleApiClient, LeaderboardScoreBuffer paramLeaderboardScoreBuffer, int paramInt1, int paramInt2)
  {
    return paramGoogleApiClient.a(new c(paramLeaderboardScoreBuffer, paramInt1, paramInt2)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, this.HX, this.HW, this.HY);
      }
    });
  }

  public PendingResult<Leaderboards.LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    return loadPlayerCenteredScores(paramGoogleApiClient, paramString, paramInt1, paramInt2, paramInt3, false);
  }

  public PendingResult<Leaderboards.LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    return paramGoogleApiClient.a(new c(paramString, paramInt1, paramInt2, paramInt3, paramBoolean)
    {
      protected void a(fx paramfx)
      {
        paramfx.b(this, this.HT, this.HU, this.HV, this.HW, this.HH);
      }
    });
  }

  public PendingResult<Leaderboards.LoadScoresResult> loadTopScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    return loadTopScores(paramGoogleApiClient, paramString, paramInt1, paramInt2, paramInt3, false);
  }

  public PendingResult<Leaderboards.LoadScoresResult> loadTopScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    return paramGoogleApiClient.a(new c(paramString, paramInt1, paramInt2, paramInt3, paramBoolean)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, this.HT, this.HU, this.HV, this.HW, this.HH);
      }
    });
  }

  public void submitScore(GoogleApiClient paramGoogleApiClient, String paramString, long paramLong)
  {
    submitScore(paramGoogleApiClient, paramString, paramLong, null);
  }

  public void submitScore(GoogleApiClient paramGoogleApiClient, String paramString1, long paramLong, String paramString2)
  {
    Games.c(paramGoogleApiClient).a(null, paramString1, paramLong, paramString2);
  }

  public PendingResult<Leaderboards.SubmitScoreResult> submitScoreImmediate(GoogleApiClient paramGoogleApiClient, String paramString, long paramLong)
  {
    return submitScoreImmediate(paramGoogleApiClient, paramString, paramLong, null);
  }

  public PendingResult<Leaderboards.SubmitScoreResult> submitScoreImmediate(GoogleApiClient paramGoogleApiClient, String paramString1, long paramLong, String paramString2)
  {
    return paramGoogleApiClient.b(new d(paramString1, paramLong, paramString2)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, this.HT, this.HZ, this.Ia);
      }
    });
  }

  private static abstract class a extends Games.a<Leaderboards.LeaderboardMetadataResult>
  {
    public Leaderboards.LeaderboardMetadataResult w(Status paramStatus)
    {
      return new Leaderboards.LeaderboardMetadataResult(paramStatus)
      {
        public LeaderboardBuffer getLeaderboards()
        {
          return new LeaderboardBuffer(DataHolder.empty(14));
        }

        public Status getStatus()
        {
          return this.vb;
        }

        public void release()
        {
        }
      };
    }
  }

  private static abstract class b extends Games.a<Leaderboards.LoadPlayerScoreResult>
  {
    public Leaderboards.LoadPlayerScoreResult x(Status paramStatus)
    {
      return new Leaderboards.LoadPlayerScoreResult(paramStatus)
      {
        public LeaderboardScore getScore()
        {
          return null;
        }

        public Status getStatus()
        {
          return this.vb;
        }
      };
    }
  }

  private static abstract class c extends Games.a<Leaderboards.LoadScoresResult>
  {
    public Leaderboards.LoadScoresResult y(Status paramStatus)
    {
      return new Leaderboards.LoadScoresResult(paramStatus)
      {
        public Leaderboard getLeaderboard()
        {
          return null;
        }

        public LeaderboardScoreBuffer getScores()
        {
          return new LeaderboardScoreBuffer(DataHolder.empty(14));
        }

        public Status getStatus()
        {
          return this.vb;
        }

        public void release()
        {
        }
      };
    }
  }

  protected static abstract class d extends Games.a<Leaderboards.SubmitScoreResult>
  {
    public Leaderboards.SubmitScoreResult z(Status paramStatus)
    {
      return new Leaderboards.SubmitScoreResult(paramStatus)
      {
        public ScoreSubmissionData getScoreData()
        {
          return new ScoreSubmissionData(DataHolder.empty(14));
        }

        public Status getStatus()
        {
          return this.vb;
        }

        public void release()
        {
        }
      };
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.gj
 * JD-Core Version:    0.6.0
 */