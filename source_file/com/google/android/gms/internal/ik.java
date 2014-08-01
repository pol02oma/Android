package com.google.android.gms.internal;

import android.net.Uri;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Moments;
import com.google.android.gms.plus.Moments.LoadMomentsResult;
import com.google.android.gms.plus.Plus.a;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;

public final class ik
  implements Moments
{
  private final Api.b<e> Rw;

  public ik(Api.b<e> paramb)
  {
    this.Rw = paramb;
  }

  public PendingResult<Moments.LoadMomentsResult> load(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.a(new a(this.Rw)
    {
      protected void a(e parame)
      {
        parame.i(this);
      }
    });
  }

  public PendingResult<Moments.LoadMomentsResult> load(GoogleApiClient paramGoogleApiClient, int paramInt, String paramString1, Uri paramUri, String paramString2, String paramString3)
  {
    return paramGoogleApiClient.a(new a(this.Rw, paramInt, paramString1, paramUri, paramString2, paramString3)
    {
      protected void a(e parame)
      {
        parame.a(this, this.HW, this.Rz, this.RA, this.RB, this.RC);
      }
    });
  }

  public PendingResult<Status> remove(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.b(new b(this.Rw, paramString)
    {
      protected void a(e parame)
      {
        parame.removeMoment(this.RE);
        a(Status.zQ);
      }
    });
  }

  public PendingResult<Status> write(GoogleApiClient paramGoogleApiClient, Moment paramMoment)
  {
    return paramGoogleApiClient.b(new c(this.Rw, paramMoment)
    {
      protected void a(e parame)
      {
        parame.a(this, this.RD);
      }
    });
  }

  private static abstract class a extends Plus.a<Moments.LoadMomentsResult>
  {
    a(Api.b<e> paramb)
    {
      super();
    }

    public Moments.LoadMomentsResult M(Status paramStatus)
    {
      return new Moments.LoadMomentsResult(paramStatus)
      {
        public MomentBuffer getMomentBuffer()
        {
          return null;
        }

        public String getNextPageToken()
        {
          return null;
        }

        public Status getStatus()
        {
          return this.vb;
        }

        public String getUpdated()
        {
          return null;
        }

        public void release()
        {
        }
      };
    }
  }

  private static abstract class b extends Plus.a<Status>
  {
    b(Api.b<e> paramb)
    {
      super();
    }

    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }

  private static abstract class c extends Plus.a<Status>
  {
    c(Api.b<e> paramb)
    {
      super();
    }

    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ik
 * JD-Core Version:    0.6.0
 */