package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;

public final class ad extends ai.a
{
  private final AppEventListener lq;

  public ad(AppEventListener paramAppEventListener)
  {
    this.lq = paramAppEventListener;
  }

  public void onAppEvent(String paramString1, String paramString2)
  {
    this.lq.onAppEvent(paramString1, paramString2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ad
 * JD-Core Version:    0.6.0
 */