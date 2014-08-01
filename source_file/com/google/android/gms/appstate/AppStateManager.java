package com.google.android.gms.appstate;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.internal.dl;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.er;
import java.util.List;

public final class AppStateManager
{
  public static final Api API;
  public static final Scope SCOPE_APP_STATE;
  static final Api.b<dl> va = new Api.b()
  {
    public dl a(Context paramContext, Looper paramLooper, ee paramee, GoogleApiClient.ApiOptions paramApiOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return new dl(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramee.dR(), (String[])paramee.dT().toArray(new String[0]));
    }

    public int getPriority()
    {
      return 2147483647;
    }
  };

  static
  {
    SCOPE_APP_STATE = new Scope("https://www.googleapis.com/auth/appstate");
    Api.b localb = va;
    Scope[] arrayOfScope = new Scope[1];
    arrayOfScope[0] = SCOPE_APP_STATE;
    API = new Api(localb, arrayOfScope);
  }

  private static StateResult a(Status paramStatus)
  {
    return new StateResult(paramStatus)
    {
      public AppStateManager.StateConflictResult getConflictResult()
      {
        return null;
      }

      public AppStateManager.StateLoadedResult getLoadedResult()
      {
        return null;
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

  public static dl a(GoogleApiClient paramGoogleApiClient)
  {
    boolean bool1 = true;
    boolean bool2;
    dl localdl;
    if (paramGoogleApiClient != null)
    {
      bool2 = bool1;
      er.b(bool2, "GoogleApiClient parameter is required.");
      er.a(paramGoogleApiClient.isConnected(), "GoogleApiClient must be connected.");
      localdl = (dl)paramGoogleApiClient.a(va);
      if (localdl == null)
        break label55;
    }
    while (true)
    {
      er.a(bool1, "GoogleApiClient is not configured to use the AppState API. Pass AppStateManager.API into GoogleApiClient.Builder#addApi() to use this feature.");
      return localdl;
      bool2 = false;
      break;
      label55: bool1 = false;
    }
  }

  public static PendingResult<StateDeletedResult> delete(GoogleApiClient paramGoogleApiClient, int paramInt)
  {
    return paramGoogleApiClient.b(new b(paramInt)
    {
      protected void a(dl paramdl)
      {
        paramdl.a(this, this.vc);
      }

      public AppStateManager.StateDeletedResult c(Status paramStatus)
      {
        return new AppStateManager.StateDeletedResult(paramStatus)
        {
          public int getStateKey()
          {
            return AppStateManager.5.this.vc;
          }

          public Status getStatus()
          {
            return this.vb;
          }
        };
      }
    });
  }

  public static int getMaxNumKeys(GoogleApiClient paramGoogleApiClient)
  {
    return a(paramGoogleApiClient).cO();
  }

  public static int getMaxStateSize(GoogleApiClient paramGoogleApiClient)
  {
    return a(paramGoogleApiClient).cN();
  }

  public static PendingResult<StateListResult> list(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.a(new c()
    {
      protected void a(dl paramdl)
      {
        paramdl.a(this);
      }
    });
  }

  public static PendingResult<StateResult> load(GoogleApiClient paramGoogleApiClient, int paramInt)
  {
    return paramGoogleApiClient.a(new e(paramInt)
    {
      protected void a(dl paramdl)
      {
        paramdl.b(this, this.vc);
      }
    });
  }

  public static PendingResult<StateResult> resolve(GoogleApiClient paramGoogleApiClient, int paramInt, String paramString, byte[] paramArrayOfByte)
  {
    return paramGoogleApiClient.b(new e(paramInt, paramString, paramArrayOfByte)
    {
      protected void a(dl paramdl)
      {
        paramdl.a(this, this.vc, this.vf, this.vg);
      }
    });
  }

  public static PendingResult<Status> signOut(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.b(new d()
    {
      protected void a(dl paramdl)
      {
        paramdl.b(this);
      }
    });
  }

  public static void update(GoogleApiClient paramGoogleApiClient, int paramInt, byte[] paramArrayOfByte)
  {
    paramGoogleApiClient.b(new e(paramInt, paramArrayOfByte)
    {
      protected void a(dl paramdl)
      {
        paramdl.a(null, this.vc, this.vd);
      }
    });
  }

  public static PendingResult<StateResult> updateImmediate(GoogleApiClient paramGoogleApiClient, int paramInt, byte[] paramArrayOfByte)
  {
    return paramGoogleApiClient.b(new e(paramInt, paramArrayOfByte)
    {
      protected void a(dl paramdl)
      {
        paramdl.a(this, this.vc, this.vd);
      }
    });
  }

  public static abstract interface StateConflictResult extends Releasable, Result
  {
    public abstract byte[] getLocalData();

    public abstract String getResolvedVersion();

    public abstract byte[] getServerData();

    public abstract int getStateKey();
  }

  public static abstract interface StateDeletedResult extends Result
  {
    public abstract int getStateKey();
  }

  public static abstract interface StateListResult extends Result
  {
    public abstract AppStateBuffer getStateBuffer();
  }

  public static abstract interface StateLoadedResult extends Releasable, Result
  {
    public abstract byte[] getLocalData();

    public abstract int getStateKey();
  }

  public static abstract interface StateResult extends Releasable, Result
  {
    public abstract AppStateManager.StateConflictResult getConflictResult();

    public abstract AppStateManager.StateLoadedResult getLoadedResult();
  }

  public static abstract class a<R extends Result> extends a.a<R, dl>
    implements PendingResult<R>
  {
    public a()
    {
      super();
    }
  }

  private static abstract class b extends AppStateManager.a<AppStateManager.StateDeletedResult>
  {
  }

  private static abstract class c extends AppStateManager.a<AppStateManager.StateListResult>
  {
    public AppStateManager.StateListResult e(Status paramStatus)
    {
      return new AppStateManager.StateListResult(paramStatus)
      {
        public AppStateBuffer getStateBuffer()
        {
          return new AppStateBuffer(null);
        }

        public Status getStatus()
        {
          return this.vb;
        }
      };
    }
  }

  private static abstract class d extends AppStateManager.a<Status>
  {
    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }

  private static abstract class e extends AppStateManager.a<AppStateManager.StateResult>
  {
    public AppStateManager.StateResult g(Status paramStatus)
    {
      return AppStateManager.b(paramStatus);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.appstate.AppStateManager
 * JD-Core Version:    0.6.0
 */