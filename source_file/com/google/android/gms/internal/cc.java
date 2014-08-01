package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;

public class cc extends eh<ch>
{
  private final int oa;

  public cc(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener, int paramInt)
  {
    super(paramContext, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
    this.oa = paramInt;
  }

  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    Bundle localBundle = new Bundle();
    paramen.g(parame, this.oa, getContext().getPackageName(), localBundle);
  }

  protected String aF()
  {
    return "com.google.android.gms.ads.service.START";
  }

  protected String aG()
  {
    return "com.google.android.gms.ads.internal.request.IAdRequestService";
  }

  public ch aH()
  {
    return (ch)super.eb();
  }

  protected ch o(IBinder paramIBinder)
  {
    return ch.a.q(paramIBinder);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cc
 * JD-Core Version:    0.6.0
 */