package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.a;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.GamesMetadata.LoadGamesResult;

public final class gh
  implements GamesMetadata
{
  public Game getCurrentGame(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fq();
  }

  public PendingResult<GamesMetadata.LoadGamesResult> loadGame(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.a(new a()
    {
      protected void a(fx paramfx)
      {
        paramfx.g(this);
      }
    });
  }

  private static abstract class a extends Games.a<GamesMetadata.LoadGamesResult>
  {
    public GamesMetadata.LoadGamesResult u(Status paramStatus)
    {
      return new GamesMetadata.LoadGamesResult(paramStatus)
      {
        public GameBuffer getGames()
        {
          return new GameBuffer(DataHolder.empty(14));
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
 * Qualified Name:     com.google.android.gms.internal.gh
 * JD-Core Version:    0.6.0
 */