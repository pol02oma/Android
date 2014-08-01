package com.google.android.gms.games;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.fx;
import com.google.android.gms.internal.gf;
import com.google.android.gms.internal.gg;
import com.google.android.gms.internal.gh;
import com.google.android.gms.internal.gi;
import com.google.android.gms.internal.gj;
import com.google.android.gms.internal.gk;
import com.google.android.gms.internal.gl;
import com.google.android.gms.internal.gm;
import com.google.android.gms.internal.gn;
import com.google.android.gms.internal.go;
import com.google.android.gms.internal.gp;
import com.google.android.gms.internal.gw;

public final class Games
{
  public static final Api API;
  public static final Achievements Achievements;
  public static final String EXTRA_PLAYER_IDS = "players";
  public static final Scope FY;
  public static final Api FZ;
  public static final Multiplayer Ga;
  public static final GamesMetadata GamesMetadata;
  public static final gw Gb;
  public static final Invitations Invitations;
  public static final Leaderboards Leaderboards;
  public static final Notifications Notifications;
  public static final Players Players;
  public static final RealTimeMultiplayer RealTimeMultiplayer;
  public static final Requests Requests;
  public static final Scope SCOPE_GAMES;
  public static final TurnBasedMultiplayer TurnBasedMultiplayer;
  static final Api.b<fx> va = new Api.b()
  {
    public fx e(Context paramContext, Looper paramLooper, ee paramee, GoogleApiClient.ApiOptions paramApiOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      Games.GamesOptions localGamesOptions1 = new Games.GamesOptions(null);
      if (paramApiOptions != null)
        er.b(paramApiOptions instanceof Games.GamesOptions, "Must provide valid GamesOptions!");
      for (Games.GamesOptions localGamesOptions2 = (Games.GamesOptions)paramApiOptions; ; localGamesOptions2 = localGamesOptions1)
        return new fx(paramContext, paramLooper, paramee.dV(), paramee.dR(), paramConnectionCallbacks, paramOnConnectionFailedListener, paramee.dU(), paramee.dS(), paramee.dW(), localGamesOptions2.Gc, localGamesOptions2.Gd, localGamesOptions2.Ge, localGamesOptions2.Gf, localGamesOptions2.Gg);
    }

    public int getPriority()
    {
      return 1;
    }
  };

  static
  {
    SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
    Api.b localb1 = va;
    Scope[] arrayOfScope1 = new Scope[1];
    arrayOfScope1[0] = SCOPE_GAMES;
    API = new Api(localb1, arrayOfScope1);
    FY = new Scope("https://www.googleapis.com/auth/games.firstparty");
    Api.b localb2 = va;
    Scope[] arrayOfScope2 = new Scope[1];
    arrayOfScope2[0] = FY;
    FZ = new Api(localb2, arrayOfScope2);
    GamesMetadata = new gh();
    Achievements = new gf();
    Leaderboards = new gj();
    Invitations = new gi();
    TurnBasedMultiplayer = new gp();
    RealTimeMultiplayer = new gn();
    Ga = new gk();
    Players = new gm();
    Notifications = new gl();
    Requests = new go();
    Gb = new gg();
  }

  public static fx c(GoogleApiClient paramGoogleApiClient)
  {
    boolean bool1 = true;
    boolean bool2;
    fx localfx;
    if (paramGoogleApiClient != null)
    {
      bool2 = bool1;
      er.b(bool2, "GoogleApiClient parameter is required.");
      er.a(paramGoogleApiClient.isConnected(), "GoogleApiClient must be connected.");
      localfx = (fx)paramGoogleApiClient.a(va);
      if (localfx == null)
        break label55;
    }
    while (true)
    {
      er.a(bool1, "GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
      return localfx;
      bool2 = false;
      break;
      label55: bool1 = false;
    }
  }

  public static String getAppId(GoogleApiClient paramGoogleApiClient)
  {
    return c(paramGoogleApiClient).fB();
  }

  public static String getCurrentAccountName(GoogleApiClient paramGoogleApiClient)
  {
    return c(paramGoogleApiClient).fn();
  }

  public static int getSdkVariant(GoogleApiClient paramGoogleApiClient)
  {
    return c(paramGoogleApiClient).fA();
  }

  public static Intent getSettingsIntent(GoogleApiClient paramGoogleApiClient)
  {
    return c(paramGoogleApiClient).fz();
  }

  public static void setGravityForPopups(GoogleApiClient paramGoogleApiClient, int paramInt)
  {
    c(paramGoogleApiClient).aT(paramInt);
  }

  public static void setViewForPopups(GoogleApiClient paramGoogleApiClient, View paramView)
  {
    er.f(paramView);
    c(paramGoogleApiClient).e(paramView);
  }

  public static PendingResult<Status> signOut(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.b(new b()
    {
      protected void a(fx paramfx)
      {
        paramfx.b(this);
      }
    });
  }

  public static final class GamesOptions
    implements GoogleApiClient.ApiOptions
  {
    final boolean Gc;
    final boolean Gd;
    final int Ge;
    final boolean Gf;
    final int Gg;

    private GamesOptions()
    {
      this.Gc = false;
      this.Gd = true;
      this.Ge = 17;
      this.Gf = false;
      this.Gg = 4368;
    }

    private GamesOptions(Builder paramBuilder)
    {
      this.Gc = paramBuilder.Gc;
      this.Gd = paramBuilder.Gd;
      this.Ge = paramBuilder.Ge;
      this.Gf = paramBuilder.Gf;
      this.Gg = paramBuilder.Gg;
    }

    public static Builder builder()
    {
      return new Builder(null);
    }

    public static final class Builder
    {
      boolean Gc = false;
      boolean Gd = true;
      int Ge = 17;
      boolean Gf = false;
      int Gg = 4368;

      public Games.GamesOptions build()
      {
        return new Games.GamesOptions(this, null);
      }

      public Builder setSdkVariant(int paramInt)
      {
        this.Gg = paramInt;
        return this;
      }

      public Builder setShowConnectingPopup(boolean paramBoolean)
      {
        this.Gd = paramBoolean;
        this.Ge = 17;
        return this;
      }

      public Builder setShowConnectingPopup(boolean paramBoolean, int paramInt)
      {
        this.Gd = paramBoolean;
        this.Ge = paramInt;
        return this;
      }
    }
  }

  public static abstract class a<R extends Result> extends a.a<R, fx>
    implements PendingResult<R>
  {
    public a()
    {
      super();
    }
  }

  private static abstract class b extends Games.a<Status>
  {
    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.Games
 * JD-Core Version:    0.6.0
 */