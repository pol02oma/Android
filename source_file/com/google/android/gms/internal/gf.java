package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.a;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;

public final class gf
  implements Achievements
{
  public Intent getAchievementsIntent(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fs();
  }

  public void increment(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt)
  {
    paramGoogleApiClient.b(new b(paramString, paramString, paramInt)
    {
      public void a(fx paramfx)
      {
        paramfx.a(null, this.HJ, this.HK);
      }
    });
  }

  public PendingResult<Achievements.UpdateAchievementResult> incrementImmediate(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt)
  {
    return paramGoogleApiClient.b(new b(paramString, paramString, paramInt)
    {
      public void a(fx paramfx)
      {
        paramfx.a(this, this.HJ, this.HK);
      }
    });
  }

  public PendingResult<Achievements.LoadAchievementsResult> load(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    return paramGoogleApiClient.a(new a(paramBoolean)
    {
      public void a(fx paramfx)
      {
        paramfx.c(this, this.HH);
      }
    });
  }

  public void reveal(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    paramGoogleApiClient.b(new b(paramString, paramString)
    {
      public void a(fx paramfx)
      {
        paramfx.b(null, this.HJ);
      }
    });
  }

  public PendingResult<Achievements.UpdateAchievementResult> revealImmediate(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.b(new b(paramString, paramString)
    {
      public void a(fx paramfx)
      {
        paramfx.b(this, this.HJ);
      }
    });
  }

  public void setSteps(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt)
  {
    paramGoogleApiClient.b(new b(paramString, paramString, paramInt)
    {
      public void a(fx paramfx)
      {
        paramfx.b(null, this.HJ, this.HK);
      }
    });
  }

  public PendingResult<Achievements.UpdateAchievementResult> setStepsImmediate(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt)
  {
    return paramGoogleApiClient.b(new b(paramString, paramString, paramInt)
    {
      public void a(fx paramfx)
      {
        paramfx.b(this, this.HJ, this.HK);
      }
    });
  }

  public void unlock(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    paramGoogleApiClient.b(new b(paramString, paramString)
    {
      public void a(fx paramfx)
      {
        paramfx.c(null, this.HJ);
      }
    });
  }

  public PendingResult<Achievements.UpdateAchievementResult> unlockImmediate(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.b(new b(paramString, paramString)
    {
      public void a(fx paramfx)
      {
        paramfx.c(this, this.HJ);
      }
    });
  }

  private static abstract class a extends Games.a<Achievements.LoadAchievementsResult>
  {
    public Achievements.LoadAchievementsResult s(Status paramStatus)
    {
      return new Achievements.LoadAchievementsResult(paramStatus)
      {
        public AchievementBuffer getAchievements()
        {
          return new AchievementBuffer(DataHolder.empty(14));
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

  private static abstract class b extends Games.a<Achievements.UpdateAchievementResult>
  {
    private final String uS;

    public b(String paramString)
    {
      this.uS = paramString;
    }

    public Achievements.UpdateAchievementResult t(Status paramStatus)
    {
      return new Achievements.UpdateAchievementResult(paramStatus)
      {
        public String getAchievementId()
        {
          return gf.b.a(gf.b.this);
        }

        public Status getStatus()
        {
          return this.vb;
        }
      };
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.gf
 * JD-Core Version:    0.6.0
 */