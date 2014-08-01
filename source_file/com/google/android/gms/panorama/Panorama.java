package com.google.android.gms.panorama;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
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
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.ih;

public final class Panorama
{
  public static final Api API;
  static final Api.b<ih> va = new Api.b()
  {
    public ih g(Context paramContext, Looper paramLooper, ee paramee, GoogleApiClient.ApiOptions paramApiOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return new ih(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener);
    }

    public int getPriority()
    {
      return 2147483647;
    }
  };

  static
  {
    API = new Api(va, new Scope[0]);
  }

  public static PendingResult<PanoramaResult> loadPanoramaInfo(GoogleApiClient paramGoogleApiClient, Uri paramUri)
  {
    return paramGoogleApiClient.a(new b(paramUri)
    {
      protected void a(ih paramih)
      {
        paramih.a(this, this.Qz, false);
      }
    });
  }

  public static PendingResult<PanoramaResult> loadPanoramaInfoAndGrantAccess(GoogleApiClient paramGoogleApiClient, Uri paramUri)
  {
    return paramGoogleApiClient.a(new b(paramUri)
    {
      protected void a(ih paramih)
      {
        paramih.a(this, this.Qz, true);
      }
    });
  }

  public static abstract interface PanoramaResult extends Result
  {
    public abstract Intent getViewerIntent();
  }

  public static abstract interface a extends Panorama.PanoramaResult
  {
  }

  private static abstract class b extends a.a<Panorama.PanoramaResult, ih>
  {
    public b()
    {
      super();
    }

    public Panorama.PanoramaResult J(Status paramStatus)
    {
      return new Panorama.PanoramaResult(paramStatus)
      {
        public Status getStatus()
        {
          return this.vb;
        }

        public Intent getViewerIntent()
        {
          return null;
        }
      };
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.panorama.Panorama
 * JD-Core Version:    0.6.0
 */