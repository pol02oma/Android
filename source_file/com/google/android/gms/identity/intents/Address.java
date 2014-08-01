package com.google.android.gms.identity.intents;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.hc;

public final class Address
{
  public static final Api API;
  static final Api.b<hc> va = new Api.b()
  {
    public hc f(Context paramContext, Looper paramLooper, ee paramee, GoogleApiClient.ApiOptions paramApiOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      er.b(paramContext instanceof Activity, "An Activity must be used for Address APIs");
      Address.AddressOptions localAddressOptions1 = new Address.AddressOptions();
      if (paramApiOptions != null)
        er.b(paramApiOptions instanceof Address.AddressOptions, "Must use AddressOptions with Address API");
      for (Address.AddressOptions localAddressOptions2 = (Address.AddressOptions)paramApiOptions; ; localAddressOptions2 = localAddressOptions1)
        return new hc((Activity)paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramee.getAccountName(), localAddressOptions2.theme);
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

  public static void requestUserAddress(GoogleApiClient paramGoogleApiClient, UserAddressRequest paramUserAddressRequest, int paramInt)
  {
    paramGoogleApiClient.a(new a(paramUserAddressRequest, paramInt)
    {
      protected void a(hc paramhc)
        throws RemoteException
      {
        paramhc.a(this.Kw, this.Kx);
        a(Status.zQ);
      }
    });
  }

  public static final class AddressOptions
    implements GoogleApiClient.ApiOptions
  {
    public final int theme;

    public AddressOptions()
    {
      this.theme = 0;
    }

    public AddressOptions(int paramInt)
    {
      this.theme = paramInt;
    }
  }

  private static abstract class a extends a.a<Status, hc>
  {
    public a()
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
 * Qualified Name:     com.google.android.gms.identity.intents.Address
 * JD-Core Version:    0.6.0
 */