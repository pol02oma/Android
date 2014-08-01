package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;

public final class ak
{
  private final be lF = new be();
  private ag lG;
  private ViewGroup lH;
  private AdListener lc;
  private AppEventListener lq;
  private AdSize[] lr;
  private String ls;

  public ak(ViewGroup paramViewGroup)
  {
    this.lH = paramViewGroup;
  }

  public ak(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean)
  {
    this.lH = paramViewGroup;
    Context localContext = paramViewGroup.getContext();
    try
    {
      ae localae = new ae(localContext, paramAttributeSet);
      this.lr = localae.c(paramBoolean);
      this.ls = localae.getAdUnitId();
      if (paramViewGroup.isInEditMode())
        cz.a(paramViewGroup, new ab(localContext, this.lr[0]), "Ads by Google");
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      cz.a(paramViewGroup, new ab(localContext, AdSize.BANNER), localIllegalArgumentException.getMessage(), localIllegalArgumentException.getMessage());
    }
  }

  private void am()
  {
    try
    {
      b localb = this.lG.P();
      if (localb == null)
        return;
      this.lH.addView((View)c.b(localb));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to get an ad frame.", localRemoteException);
    }
  }

  private void an()
    throws RemoteException
  {
    if (((this.lr == null) || (this.ls == null)) && (this.lG == null))
      throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
    Context localContext = this.lH.getContext();
    this.lG = y.a(localContext, new ab(localContext, this.lr), this.ls, this.lF);
    if (this.lc != null)
      this.lG.a(new x(this.lc));
    if (this.lq != null)
      this.lG.a(new ad(this.lq));
    am();
  }

  public void a(aj paramaj)
  {
    try
    {
      if (this.lG == null)
        an();
      if (this.lG.a(new z(this.lH.getContext(), paramaj)))
        this.lF.c(paramaj.ak());
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to load ad.", localRemoteException);
    }
  }

  public void a(AdSize[] paramArrayOfAdSize)
  {
    this.lr = paramArrayOfAdSize;
    try
    {
      if (this.lG != null)
        this.lG.a(new ab(this.lH.getContext(), this.lr));
      this.lH.requestLayout();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        da.b("Failed to set the ad size.", localRemoteException);
    }
  }

  public void destroy()
  {
    try
    {
      if (this.lG != null)
        this.lG.destroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to destroy AdView.", localRemoteException);
    }
  }

  public AdListener getAdListener()
  {
    return this.lc;
  }

  public AdSize getAdSize()
  {
    try
    {
      if (this.lG != null)
      {
        AdSize localAdSize = this.lG.Q().ai();
        return localAdSize;
      }
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to get the current AdSize.", localRemoteException);
      if (this.lr != null)
        return this.lr[0];
    }
    return null;
  }

  public AdSize[] getAdSizes()
  {
    return this.lr;
  }

  public String getAdUnitId()
  {
    return this.ls;
  }

  public AppEventListener getAppEventListener()
  {
    return this.lq;
  }

  public void pause()
  {
    try
    {
      if (this.lG != null)
        this.lG.pause();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to call pause.", localRemoteException);
    }
  }

  public void recordManualImpression()
  {
    try
    {
      this.lG.Z();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to record impression.", localRemoteException);
    }
  }

  public void resume()
  {
    try
    {
      if (this.lG != null)
        this.lG.resume();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to call resume.", localRemoteException);
    }
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

  public void setAdSizes(AdSize[] paramArrayOfAdSize)
  {
    if (this.lr != null)
      throw new IllegalStateException("The ad size can only be set once on AdView.");
    a(paramArrayOfAdSize);
  }

  public void setAdUnitId(String paramString)
  {
    if (this.ls != null)
      throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ak
 * JD-Core Version:    0.6.0
 */