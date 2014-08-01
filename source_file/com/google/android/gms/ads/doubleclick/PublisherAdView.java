package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.ak;

public final class PublisherAdView extends ViewGroup
{
  private final ak kD;

  public PublisherAdView(Context paramContext)
  {
    super(paramContext);
    this.kD = new ak(this);
  }

  public PublisherAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.kD = new ak(this, paramAttributeSet, true);
  }

  public PublisherAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.kD = new ak(this, paramAttributeSet, true);
  }

  public void destroy()
  {
    this.kD.destroy();
  }

  public AdListener getAdListener()
  {
    return this.kD.getAdListener();
  }

  public AdSize getAdSize()
  {
    return this.kD.getAdSize();
  }

  public AdSize[] getAdSizes()
  {
    return this.kD.getAdSizes();
  }

  public String getAdUnitId()
  {
    return this.kD.getAdUnitId();
  }

  public AppEventListener getAppEventListener()
  {
    return this.kD.getAppEventListener();
  }

  public void loadAd(PublisherAdRequest paramPublisherAdRequest)
  {
    this.kD.a(paramPublisherAdRequest.N());
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView = getChildAt(0);
    if ((localView != null) && (localView.getVisibility() != 8))
    {
      int i = localView.getMeasuredWidth();
      int j = localView.getMeasuredHeight();
      int k = (paramInt3 - paramInt1 - i) / 2;
      int m = (paramInt4 - paramInt2 - j) / 2;
      localView.layout(k, m, i + k, j + m);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    View localView = getChildAt(0);
    AdSize localAdSize = getAdSize();
    int j;
    int i;
    if ((localView != null) && (localView.getVisibility() != 8))
    {
      measureChild(localView, paramInt1, paramInt2);
      j = localView.getMeasuredWidth();
      i = localView.getMeasuredHeight();
    }
    while (true)
    {
      int k = Math.max(j, getSuggestedMinimumWidth());
      int m = Math.max(i, getSuggestedMinimumHeight());
      setMeasuredDimension(View.resolveSize(k, paramInt1), View.resolveSize(m, paramInt2));
      return;
      if (localAdSize != null)
      {
        Context localContext = getContext();
        j = localAdSize.getWidthInPixels(localContext);
        i = localAdSize.getHeightInPixels(localContext);
        continue;
      }
      i = 0;
      j = 0;
    }
  }

  public void pause()
  {
    this.kD.pause();
  }

  public void recordManualImpression()
  {
    this.kD.recordManualImpression();
  }

  public void resume()
  {
    this.kD.resume();
  }

  public void setAdListener(AdListener paramAdListener)
  {
    this.kD.setAdListener(paramAdListener);
  }

  public void setAdSizes(AdSize[] paramArrayOfAdSize)
  {
    if ((paramArrayOfAdSize == null) || (paramArrayOfAdSize.length < 1))
      throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
    this.kD.a(paramArrayOfAdSize);
  }

  public void setAdUnitId(String paramString)
  {
    this.kD.setAdUnitId(paramString);
  }

  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    this.kD.setAppEventListener(paramAppEventListener);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.ads.doubleclick.PublisherAdView
 * JD-Core Version:    0.6.0
 */