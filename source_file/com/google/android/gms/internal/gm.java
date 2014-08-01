package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.a;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.Players.LoadPlayersResult;

public final class gm
  implements Players
{
  public Player getCurrentPlayer(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fp();
  }

  public String getCurrentPlayerId(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fo();
  }

  public Intent getPlayerSearchIntent(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fy();
  }

  public PendingResult<Players.LoadPlayersResult> loadConnectedPlayers(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    return paramGoogleApiClient.a(new a(paramBoolean)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, this.HH);
      }
    });
  }

  public PendingResult<Players.LoadPlayersResult> loadInvitablePlayers(GoogleApiClient paramGoogleApiClient, int paramInt, boolean paramBoolean)
  {
    return paramGoogleApiClient.a(new a(paramInt, paramBoolean)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, this.Ih, false, this.HH);
      }
    });
  }

  public PendingResult<Players.LoadPlayersResult> loadMoreInvitablePlayers(GoogleApiClient paramGoogleApiClient, int paramInt)
  {
    return paramGoogleApiClient.a(new a(paramInt)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, this.Ih, true, false);
      }
    });
  }

  public PendingResult<Players.LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(GoogleApiClient paramGoogleApiClient, int paramInt)
  {
    return paramGoogleApiClient.a(new a(paramInt)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, "playedWith", this.Ih, true, false);
      }
    });
  }

  public PendingResult<Players.LoadPlayersResult> loadPlayer(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.a(new a(paramString)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, this.If);
      }
    });
  }

  public PendingResult<Players.LoadPlayersResult> loadRecentlyPlayedWithPlayers(GoogleApiClient paramGoogleApiClient, int paramInt, boolean paramBoolean)
  {
    return paramGoogleApiClient.a(new a(paramInt, paramBoolean)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, "playedWith", this.Ih, false, this.HH);
      }
    });
  }

  private static abstract class a extends Games.a<Players.LoadPlayersResult>
  {
    public Players.LoadPlayersResult A(Status paramStatus)
    {
      return new Players.LoadPlayersResult(paramStatus)
      {
        public PlayerBuffer getPlayers()
        {
          return new PlayerBuffer(DataHolder.empty(14));
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
 * Qualified Name:     com.google.android.gms.internal.gm
 * JD-Core Version:    0.6.0
 */