package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus.a;
import com.google.android.gms.plus.internal.e;

public final class ii
  implements Account
{
  private final Api.b<e> Rw;

  public ii(Api.b<e> paramb)
  {
    this.Rw = paramb;
  }

  private static e a(GoogleApiClient paramGoogleApiClient, Api.b<e> paramb)
  {
    boolean bool1 = true;
    boolean bool2;
    e locale;
    if (paramGoogleApiClient != null)
    {
      bool2 = bool1;
      er.b(bool2, "GoogleApiClient parameter is required.");
      er.a(paramGoogleApiClient.isConnected(), "GoogleApiClient must be connected.");
      locale = (e)paramGoogleApiClient.a(paramb);
      if (locale == null)
        break label56;
    }
    while (true)
    {
      er.a(bool1, "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
      return locale;
      bool2 = false;
      break;
      label56: bool1 = false;
    }
  }

  public void clearDefaultAccount(GoogleApiClient paramGoogleApiClient)
  {
    a(paramGoogleApiClient, this.Rw).clearDefaultAccount();
  }

  public String getAccountName(GoogleApiClient paramGoogleApiClient)
  {
    return a(paramGoogleApiClient, this.Rw).getAccountName();
  }

  public PendingResult<Status> revokeAccessAndDisconnect(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.b(new a(this.Rw)
    {
      protected void a(e parame)
      {
        parame.k(this);
      }
    });
  }

  private static abstract class a extends Plus.a<Status>
  {
    a(Api.b<e> paramb)
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
 * Qualified Name:     com.google.android.gms.internal.ii
 * JD-Core Version:    0.6.0
 */