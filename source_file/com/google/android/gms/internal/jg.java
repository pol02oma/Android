package com.google.android.gms.internal;

import android.accounts.Account;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;

public class jg extends eh<je>
{
  private final int Zw;
  private final int mTheme;
  private final Activity nd;
  private final String vi;

  public jg(Activity paramActivity, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, int paramInt1, String paramString, int paramInt2)
  {
    super(paramActivity, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
    this.nd = paramActivity;
    this.Zw = paramInt1;
    this.vi = paramString;
    this.mTheme = paramInt2;
  }

  @Deprecated
  public jg(Activity paramActivity, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener, int paramInt1, String paramString, int paramInt2)
  {
    this(paramActivity, paramActivity.getMainLooper(), new eh.c(paramConnectionCallbacks), new eh.g(paramOnConnectionFailedListener), paramInt1, paramString, paramInt2);
  }

  private Bundle kx()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", this.Zw);
    localBundle.putString("androidPackageName", this.nd.getPackageName());
    if (!TextUtils.isEmpty(this.vi))
      localBundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(this.vi, "com.google"));
    localBundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", this.mTheme);
    return localBundle;
  }

  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    paramen.a(parame, 4323000);
  }

  protected je aE(IBinder paramIBinder)
  {
    return je.a.aC(paramIBinder);
  }

  protected String aF()
  {
    return "com.google.android.gms.wallet.service.BIND";
  }

  protected String aG()
  {
    return "com.google.android.gms.wallet.internal.IOwService";
  }

  public void changeMaskedWallet(String paramString1, String paramString2, int paramInt)
  {
    Bundle localBundle = kx();
    a locala = new a(paramInt);
    try
    {
      ((je)eb()).a(paramString1, paramString2, localBundle, locala);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("WalletClientImpl", "RemoteException changing masked wallet", localRemoteException);
      locala.a(8, null, null);
    }
  }

  public void checkForPreAuthorization(int paramInt)
  {
    Bundle localBundle = kx();
    a locala = new a(paramInt);
    try
    {
      ((je)eb()).a(localBundle, locala);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", localRemoteException);
      locala.a(8, false, null);
    }
  }

  public void loadFullWallet(FullWalletRequest paramFullWalletRequest, int paramInt)
  {
    a locala = new a(paramInt);
    Bundle localBundle = kx();
    try
    {
      ((je)eb()).a(paramFullWalletRequest, localBundle, locala);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("WalletClientImpl", "RemoteException getting full wallet", localRemoteException);
      locala.a(8, null, null);
    }
  }

  public void loadMaskedWallet(MaskedWalletRequest paramMaskedWalletRequest, int paramInt)
  {
    Bundle localBundle = kx();
    a locala = new a(paramInt);
    try
    {
      ((je)eb()).a(paramMaskedWalletRequest, localBundle, locala);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("WalletClientImpl", "RemoteException getting masked wallet", localRemoteException);
      locala.a(8, null, null);
    }
  }

  public void notifyTransactionStatus(NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest)
  {
    Bundle localBundle = kx();
    try
    {
      ((je)eb()).a(paramNotifyTransactionStatusRequest, localBundle);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
  }

  final class a extends jf.a
  {
    private final int Bq;

    public a(int arg2)
    {
      int i;
      this.Bq = i;
    }

    public void a(int paramInt, FullWallet paramFullWallet, Bundle paramBundle)
    {
      PendingIntent localPendingIntent1 = null;
      if (paramBundle != null)
        localPendingIntent1 = (PendingIntent)paramBundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
      ConnectionResult localConnectionResult = new ConnectionResult(paramInt, localPendingIntent1);
      if (localConnectionResult.hasResolution())
        try
        {
          localConnectionResult.startResolutionForResult(jg.b(jg.this), this.Bq);
          return;
        }
        catch (IntentSender.SendIntentException localSendIntentException)
        {
          Log.w("WalletClientImpl", "Exception starting pending intent", localSendIntentException);
          return;
        }
      Intent localIntent = new Intent();
      int i;
      PendingIntent localPendingIntent2;
      if (localConnectionResult.isSuccess())
      {
        i = -1;
        localIntent.putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", paramFullWallet);
        localPendingIntent2 = jg.b(jg.this).createPendingResult(this.Bq, localIntent, 1073741824);
        if (localPendingIntent2 == null)
        {
          Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
          return;
        }
      }
      else
      {
        if (paramInt == 408);
        for (i = 0; ; i = 1)
        {
          localIntent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", paramInt);
          break;
        }
      }
      try
      {
        localPendingIntent2.send(i);
        return;
      }
      catch (PendingIntent.CanceledException localCanceledException)
      {
        Log.w("WalletClientImpl", "Exception setting pending result", localCanceledException);
      }
    }

    public void a(int paramInt, MaskedWallet paramMaskedWallet, Bundle paramBundle)
    {
      PendingIntent localPendingIntent1 = null;
      if (paramBundle != null)
        localPendingIntent1 = (PendingIntent)paramBundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
      ConnectionResult localConnectionResult = new ConnectionResult(paramInt, localPendingIntent1);
      if (localConnectionResult.hasResolution())
        try
        {
          localConnectionResult.startResolutionForResult(jg.b(jg.this), this.Bq);
          return;
        }
        catch (IntentSender.SendIntentException localSendIntentException)
        {
          Log.w("WalletClientImpl", "Exception starting pending intent", localSendIntentException);
          return;
        }
      Intent localIntent = new Intent();
      int i;
      PendingIntent localPendingIntent2;
      if (localConnectionResult.isSuccess())
      {
        i = -1;
        localIntent.putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", paramMaskedWallet);
        localPendingIntent2 = jg.b(jg.this).createPendingResult(this.Bq, localIntent, 1073741824);
        if (localPendingIntent2 == null)
        {
          Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
          return;
        }
      }
      else
      {
        if (paramInt == 408);
        for (i = 0; ; i = 1)
        {
          localIntent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", paramInt);
          break;
        }
      }
      try
      {
        localPendingIntent2.send(i);
        return;
      }
      catch (PendingIntent.CanceledException localCanceledException)
      {
        Log.w("WalletClientImpl", "Exception setting pending result", localCanceledException);
      }
    }

    public void a(int paramInt, boolean paramBoolean, Bundle paramBundle)
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED", paramBoolean);
      PendingIntent localPendingIntent = jg.b(jg.this).createPendingResult(this.Bq, localIntent, 1073741824);
      if (localPendingIntent == null)
      {
        Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
        return;
      }
      try
      {
        localPendingIntent.send(-1);
        return;
      }
      catch (PendingIntent.CanceledException localCanceledException)
      {
        Log.w("WalletClientImpl", "Exception setting pending result", localCanceledException);
      }
    }

    public void e(int paramInt, Bundle paramBundle)
    {
      er.b(paramBundle, "Bundle should not be null");
      ConnectionResult localConnectionResult = new ConnectionResult(paramInt, (PendingIntent)paramBundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
      if (localConnectionResult.hasResolution())
        try
        {
          localConnectionResult.startResolutionForResult(jg.b(jg.this), this.Bq);
          return;
        }
        catch (IntentSender.SendIntentException localSendIntentException)
        {
          Log.w("WalletClientImpl", "Exception starting pending intent", localSendIntentException);
          return;
        }
      Log.e("WalletClientImpl", "Create Wallet Objects confirmation UI will not be shown connection result: " + localConnectionResult);
      Intent localIntent = new Intent();
      localIntent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413);
      PendingIntent localPendingIntent = jg.b(jg.this).createPendingResult(this.Bq, localIntent, 1073741824);
      if (localPendingIntent == null)
      {
        Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
        return;
      }
      try
      {
        localPendingIntent.send(1);
        return;
      }
      catch (PendingIntent.CanceledException localCanceledException)
      {
        Log.w("WalletClientImpl", "Exception setting pending result", localCanceledException);
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jg
 * JD-Core Version:    0.6.0
 */