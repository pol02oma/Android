package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

public final class bj<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters>
  implements MediationBannerListener, MediationInterstitialListener
{
  private final bh mT;

  public bj(bh parambh)
  {
    this.mT = parambh;
  }

  public void onClick(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    da.s("Adapter called onClick.");
    if (!cz.aX())
    {
      da.w("onClick must be called on the main UI thread.");
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).O();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdClicked.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.O();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdClicked.", localRemoteException);
    }
  }

  public void onDismissScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    da.s("Adapter called onDismissScreen.");
    if (!cz.aX())
    {
      da.w("onDismissScreen must be called on the main UI thread.");
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdClosed();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdClosed.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdClosed();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdClosed.", localRemoteException);
    }
  }

  public void onDismissScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    da.s("Adapter called onDismissScreen.");
    if (!cz.aX())
    {
      da.w("onDismissScreen must be called on the main UI thread.");
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdClosed();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdClosed.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdClosed();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdClosed.", localRemoteException);
    }
  }

  public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter, AdRequest.ErrorCode paramErrorCode)
  {
    da.s("Adapter called onFailedToReceiveAd with error. " + paramErrorCode);
    if (!cz.aX())
    {
      da.w("onFailedToReceiveAd must be called on the main UI thread.");
      cz.pT.post(new Runnable(paramErrorCode)
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdFailedToLoad(bk.a(this.mV));
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdFailedToLoad.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdFailedToLoad(bk.a(paramErrorCode));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdFailedToLoad.", localRemoteException);
    }
  }

  public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter, AdRequest.ErrorCode paramErrorCode)
  {
    da.s("Adapter called onFailedToReceiveAd with error " + paramErrorCode + ".");
    if (!cz.aX())
    {
      da.w("onFailedToReceiveAd must be called on the main UI thread.");
      cz.pT.post(new Runnable(paramErrorCode)
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdFailedToLoad(bk.a(this.mV));
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdFailedToLoad.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdFailedToLoad(bk.a(paramErrorCode));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdFailedToLoad.", localRemoteException);
    }
  }

  public void onLeaveApplication(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    da.s("Adapter called onLeaveApplication.");
    if (!cz.aX())
    {
      da.w("onLeaveApplication must be called on the main UI thread.");
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdLeftApplication();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdLeftApplication.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdLeftApplication();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdLeftApplication.", localRemoteException);
    }
  }

  public void onLeaveApplication(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    da.s("Adapter called onLeaveApplication.");
    if (!cz.aX())
    {
      da.w("onLeaveApplication must be called on the main UI thread.");
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdLeftApplication();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdLeftApplication.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdLeftApplication();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdLeftApplication.", localRemoteException);
    }
  }

  public void onPresentScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    da.s("Adapter called onPresentScreen.");
    if (!cz.aX())
    {
      da.w("onPresentScreen must be called on the main UI thread.");
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdOpened();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdOpened.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdOpened();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdOpened.", localRemoteException);
    }
  }

  public void onPresentScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    da.s("Adapter called onPresentScreen.");
    if (!cz.aX())
    {
      da.w("onPresentScreen must be called on the main UI thread.");
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdOpened();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdOpened.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdOpened();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdOpened.", localRemoteException);
    }
  }

  public void onReceivedAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    da.s("Adapter called onReceivedAd.");
    if (!cz.aX())
    {
      da.w("onReceivedAd must be called on the main UI thread.");
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdLoaded();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdLoaded.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdLoaded();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdLoaded.", localRemoteException);
    }
  }

  public void onReceivedAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    da.s("Adapter called onReceivedAd.");
    if (!cz.aX())
    {
      da.w("onReceivedAd must be called on the main UI thread.");
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          try
          {
            bj.a(bj.this).onAdLoaded();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            da.b("Could not call onAdLoaded.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.mT.onAdLoaded();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call onAdLoaded.", localRemoteException);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bj
 * JD-Core Version:    0.6.0
 */