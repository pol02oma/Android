package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

public final class x extends af.a
{
  private final AdListener lc;

  public x(AdListener paramAdListener)
  {
    this.lc = paramAdListener;
  }

  public void onAdClosed()
  {
    this.lc.onAdClosed();
  }

  public void onAdFailedToLoad(int paramInt)
  {
    this.lc.onAdFailedToLoad(paramInt);
  }

  public void onAdLeftApplication()
  {
    this.lc.onAdLeftApplication();
  }

  public void onAdLoaded()
  {
    this.lc.onAdLoaded();
  }

  public void onAdOpened()
  {
    this.lc.onAdOpened();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.x
 * JD-Core Version:    0.6.0
 */