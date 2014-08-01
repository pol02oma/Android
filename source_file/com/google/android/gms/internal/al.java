package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.AppEventListener;

public final class al
{
  private final be lF = new be();
  private ag lG;
  private AdListener lc;
  private AppEventListener lq;
  private String ls;
  private final Context mContext;

  public al(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private void k(String paramString)
    throws RemoteException
  {
    if (this.ls == null)
      l(paramString);
    this.lG = y.a(this.mContext, new ab(), this.ls, this.lF);
    if (this.lc != null)
      this.lG.a(new x(this.lc));
    if (this.lq != null)
      this.lG.a(new ad(this.lq));
  }

  private void l(String paramString)
  {
    if (this.lG == null)
      throw new IllegalStateException("The ad unit ID must be set on InterstitialAd before " + paramString + " is called.");
  }

  public void a(aj paramaj)
  {
    try
    {
      if (this.lG == null)
        k("loadAd");
      if (this.lG.a(new z(this.mContext, paramaj)))
        this.lF.c(paramaj.ak());
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to load ad.", localRemoteException);
    }
  }

  public AdListener getAdListener()
  {
    return this.lc;
  }

  public String getAdUnitId()
  {
    return this.ls;
  }

  public AppEventListener getAppEventListener()
  {
    return this.lq;
  }

  public boolean isLoaded()
  {
    try
    {
      if (this.lG == null)
        return false;
      boolean bool = this.lG.isReady();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to check if ad is ready.", localRemoteException);
    }
    return false;
  }

  public void setAdListener(AdListener paramAdListener)
  {
    try
    {
      this.lc = paramAdListener;
      ag localag;
      if (this.lG != null)
      {
        localag = this.lG;
        if (paramAdListener == null)
          break label40;
      }
      label40: for (x localx = new x(paramAdListener); ; localx = null)
      {
        localag.a(localx);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to set the AdListener.", localRemoteException);
    }
  }

  public void setAdUnitId(String paramString)
  {
    if (this.ls != null)
      throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
    this.ls = paramString;
  }

  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    try
    {
      this.lq = paramAppEventListener;
      ag localag;
      if (this.lG != null)
      {
        localag = this.lG;
        if (paramAppEventListener == null)
          break label40;
      }
      label40: for (ad localad = new ad(paramAppEventListener); ; localad = null)
      {
        localag.a(localad);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to set the AppEventListener.", localRemoteException);
    }
  }

  public void show()
  {
    try
    {
      l("show");
      this.lG.showInterstitial();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to show interstitial.", localRemoteException);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.al
 * JD-Core Version:    0.6.0
 */