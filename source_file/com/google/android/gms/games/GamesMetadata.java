package com.google.android.gms.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public abstract interface GamesMetadata
{
  public abstract Game getCurrentGame(GoogleApiClient paramGoogleApiClient);

  public abstract PendingResult<LoadGamesResult> loadGame(GoogleApiClient paramGoogleApiClient);

  public static abstract interface LoadGamesResult extends Releasable, Result
  {
    public abstract GameBuffer getGames();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.GamesMetadata
 * JD-Core Version:    0.6.0
 */