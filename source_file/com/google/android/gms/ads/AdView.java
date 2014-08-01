package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.internal.ak;

public final class AdView extends ViewGroup
{
  private final ak kD;

  public AdView(Context paramContext)
  {
    super(paramContext);
    this.kD = new ak(this);
  }

  public AdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.kD = new ak(this, paramAttributeSet, false);
  }

  public AdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.kD = new ak(this, paramAttributeSet, false);
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

  public String getAdUnitId()
  {
    return this.kD.getAdUnitId();
  }

  public void loadAd(AdRequest paramAdRequest)
  {
    this.kD.a(paramAdRequest.N());
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

  public void resume()
  {
    this.kD.resume();
  }

  public void setAdListener(AdListener paramAdListener)
  {
    this.kD.setAdListener(paramAdListener);
  }

  public void setAdSize(AdSize paramAdSize)
  {
    this.kD.setAdSizes(new AdSize[] { paramAdSize });
  }

  public void setAdUnitId(String paramString)
  {
    this.kD.setAdUnitId(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.ads.AdView
 * JD-Core Version:    0.6.0
 */