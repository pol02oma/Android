package com.google.android.gms.internal;

import android.accounts.Account;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.identity.intents.UserAddressRequest;

public class hc extends eh<he>
{
  private a KA;
  private final int mTheme;
  private Activity nd;
  private final String vi;

  public hc(Activity paramActivity, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, int paramInt)
  {
    super(paramActivity, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
    this.vi = paramString;
    this.nd = paramActivity;
    this.mTheme = paramInt;
  }

  protected he K(IBinder paramIBinder)
  {
    return he.a.M(paramIBinder);
  }

  public void a(UserAddressRequest paramUserAddressRequest, int paramInt)
  {
    gj();
    this.KA = new a(paramInt, this.nd);
    try
    {
      Bundle localBundle1 = new Bundle();
      localBundle1.putString("com.google.android.gms.identity.intents.EXTRA_CALLING_PACKAGE_NAME", getContext().getPackageName());
      if (!TextUtils.isEmpty(this.vi))
        localBundle1.putParcelable("com.google.android.gms.identity.intents.EXTRA_ACCOUNT", new Account(this.vi, "com.google"));
      localBundle1.putInt("com.google.android.gms.identity.intents.EXTRA_THEME", this.mTheme);
      gi().a(this.KA, paramUserAddressRequest, localBundle1);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("AddressClientImpl", "Exception requesting user address", localRemoteException);
      Bundle localBundle2 = new Bundle();
      localBundle2.putInt("com.google.android.gms.identity.intents.EXTRA_ERROR_CODE", 555);
      this.KA.c(1, localBundle2);
    }
  }

  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    paramen.d(parame, 4323000, getContext().getPackageName());
  }

  protected String aF()
  {
    return "com.google.android.gms.identity.service.BIND";
  }

  protected String aG()
  {
    return "com.google.android.gms.identity.intents.internal.IAddressService";
  }

  public void disconnect()
  {
    super.disconnect();
    if (this.KA != null)
    {
      a.a(this.KA, null);
      this.KA = null;
    }
  }

  protected he gi()
  {
    return (he)super.eb();
  }

  protected void gj()
  {
    super.bm();
  }

  public static final class a extends hd.a
  {
    private final int Bq;
    private Activity nd;

    public a(int paramInt, Activity paramActivity)
    {
      this.Bq = paramInt;
      this.nd = paramActivity;
    }

    private void setActivity(Activity paramActivity)
    {
      this.nd = paramActivity;
    }

    public void c(int paramInt, Bundle paramBundle)
    {
      PendingIntent localPendingIntent1;
      if (paramInt == 1)
      {
        Intent localIntent = new Intent();
        localIntent.putExtras(paramBundle);
        localPendingIntent1 = this.nd.createPendingResult(this.Bq, localIntent, 1073741824);
        if (localPendingIntent1 != null);
      }
      while (true)
      {
        return;
        try
        {
          localPendingIntent1.send(1);
          return;
        }
        catch (PendingIntent.CanceledException localCanceledException1)
        {
          Log.w("AddressClientImpl", "Exception settng pending result", localCanceledException1);
          return;
        }
        PendingIntent localPendingIntent2 = null;
        if (paramBundle != null)
          localPendingIntent2 = (PendingIntent)paramBundle.getParcelable("com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT");
        ConnectionResult localConnectionResult = new ConnectionResult(paramInt, localPendingIntent2);
        if (localConnectionResult.hasResolution())
          try
          {
            localConnectionResult.startResolutionForResult(this.nd, this.Bq);
            return;
          }
          catch (IntentSender.SendIntentException localSendIntentException)
          {
            Log.w("AddressClientImpl", "Exception starting pending intent", localSendIntentException);
            return;
          }
        try
        {
          PendingIntent localPendingIntent3 = this.nd.createPendingResult(this.Bq, new Intent(), 1073741824);
          if (localPendingIntent3 == null)
            continue;
          localPendingIntent3.send(1);
          return;
        }
        catch (PendingIntent.CanceledException localCanceledException2)
        {
          Log.w("AddressClientImpl", "Exception setting pending result", localCanceledException2);
        }
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hc
 * JD-Core Version:    0.6.0
 */