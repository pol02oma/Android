package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.internal.al;

public final class InterstitialAd
{
  private final al kE;

  public InterstitialAd(Context paramContext)
  {
    this.kE = new al(paramContext);
  }

  public AdListener getAdListener()
  {
    return this.kE.getAdListener();
  }

  public String getAdUnitId()
  {
    return this.kE.getAdUnitId();
  }

  public boolean isLoaded()
  {
    return this.kE.isLoaded();
  }

  public void loadAd(AdRequest paramAdRequest)
  {
    this.kE.a(paramAdRequest.N());
  }

  public void setAdListener(AdListener paramAdListener)
  {
    this.kE.setAdListener(paramAdListener);
  }

  public void setAdUnitId(String paramString)
  {
    this.kE.setAdUnitId(paramString);
  }

  public void show()
  {
    this.kE.show();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.ads.InterstitialAd
 * JD-Core Version:    0.6.0
 */