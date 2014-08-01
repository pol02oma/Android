package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.internal.da;

public final class CustomEventAdapter
  implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters>
{
  private View m;
  private CustomEventBanner n;
  private CustomEventInterstitial o;

  private static <T> T a(String paramString)
  {
    try
    {
      Object localObject = Class.forName(paramString).newInstance();
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      da.w("Could not instantiate custom event adapter: " + paramString + ". " + localThrowable.getMessage());
    }
    return null;
  }

  private void a(View paramView)
  {
    this.m = paramView;
  }

  public void destroy()
  {
    if (this.n != null)
      this.n.destroy();
    if (this.o != null)
      this.o.destroy();
  }

  public Class<CustomEventExtras> getAdditionalParametersType()
  {
    return CustomEventExtras.class;
  }

  public View getBannerView()
  {
    return this.m;
  }

  public Class<CustomEventServerParameters> getServerParametersType()
  {
    return CustomEventServerParameters.class;
  }

  public void requestBannerAd(MediationBannerListener paramMediationBannerListener, Activity paramActivity, CustomEventServerParameters paramCustomEventServerParameters, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, CustomEventExtras paramCustomEventExtras)
  {
    this.n = ((CustomEventBanner)a(paramCustomEventServerParameters.className));
    if (this.n == null)
    {
      paramMediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
      return;
    }
    if (paramCustomEventExtras == null);
    for (Object localObject = null; ; localObject = paramCustomEventExtras.getExtra(paramCustomEventServerParameters.label))
    {
      this.n.requestBannerAd(new a(this, paramMediationBannerListener), paramActivity, paramCustomEventServerParameters.label, paramCustomEventServerParameters.parameter, paramAdSize, paramMediationAdRequest, localObject);
      return;
    }
  }

  public void requestInterstitialAd(MediationInterstitialListener paramMediationInterstitialListener, Activity paramActivity, CustomEventServerParameters paramCustomEventServerParameters, MediationAdRequest paramMediationAdRequest, CustomEventExtras paramCustomEventExtras)
  {
    this.o = ((CustomEventInterstitial)a(paramCustomEventServerParameters.className));
    if (this.o == null)
    {
      paramMediationInterstitialListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
      return;
    }
    if (paramCustomEventExtras == null);
    for (Object localObject = null; ; localObject = paramCustomEventExtras.getExtra(paramCustomEventServerParameters.label))
    {
      this.o.requestInterstitialAd(new b(this, paramMediationInterstitialListener), paramActivity, paramCustomEventServerParameters.label, paramCustomEventServerParameters.parameter, paramMediationAdRequest, localObject);
      return;
    }
  }

  public void showInterstitial()
  {
    this.o.showInterstitial();
  }

  private static final class a
    implements CustomEventBannerListener
  {
    private final MediationBannerListener k;
    private final CustomEventAdapter p;

    public a(CustomEventAdapter paramCustomEventAdapter, MediationBannerListener paramMediationBannerListener)
    {
      this.p = paramCustomEventAdapter;
      this.k = paramMediationBannerListener;
    }

    public void onClick()
    {
      da.s("Custom event adapter called onFailedToReceiveAd.");
      this.k.onClick(this.p);
    }

    public void onDismissScreen()
    {
      da.s("Custom event adapter called onFailedToReceiveAd.");
      this.k.onDismissScreen(this.p);
    }

    public void onFailedToReceiveAd()
    {
      da.s("Custom event adapter called onFailedToReceiveAd.");
      this.k.onFailedToReceiveAd(this.p, AdRequest.ErrorCode.NO_FILL);
    }

    public void onLeaveApplication()
    {
      da.s("Custom event adapter called onFailedToReceiveAd.");
      this.k.onLeaveApplication(this.p);
    }

    public void onPresentScreen()
    {
      da.s("Custom event adapter called onFailedToReceiveAd.");
      this.k.onPresentScreen(this.p);
    }

    public void onReceivedAd(View paramView)
    {
      da.s("Custom event adapter called onReceivedAd.");
      CustomEventAdapter.a(this.p, paramView);
      this.k.onReceivedAd(this.p);
    }
  }

  private class b
    implements CustomEventInterstitialListener
  {
    private final MediationInterstitialListener l;
    private final CustomEventAdapter p;

    public b(CustomEventAdapter paramMediationInterstitialListener, MediationInterstitialListener arg3)
    {
      this.p = paramMediationInterstitialListener;
      Object localObject;
      this.l = localObject;
    }

    public void onDismissScreen()
    {
      da.s("Custom event adapter called onDismissScreen.");
      this.l.onDismissScreen(this.p);
    }

    public void onFailedToReceiveAd()
    {
      da.s("Custom event adapter called onFailedToReceiveAd.");
      this.l.onFailedToReceiveAd(this.p, AdRequest.ErrorCode.NO_FILL);
    }

    public void onLeaveApplication()
    {
      da.s("Custom event adapter called onLeaveApplication.");
      this.l.onLeaveApplication(this.p);
    }

    public void onPresentScreen()
    {
      da.s("Custom event adapter called onPresentScreen.");
      this.l.onPresentScreen(this.p);
    }

    public void onReceivedAd()
    {
      da.s("Custom event adapter called onReceivedAd.");
      this.l.onReceivedAd(CustomEventAdapter.this);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.ads.mediation.customevent.CustomEventAdapter
 * JD-Core Version:    0.6.0
 */