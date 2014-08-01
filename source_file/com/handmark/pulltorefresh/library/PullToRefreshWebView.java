package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class PullToRefreshWebView extends PullToRefreshBase<WebView>
{
  private static final PullToRefreshBase.OnRefreshListener<WebView> defaultOnRefreshListener = new PullToRefreshBase.OnRefreshListener()
  {
    public void onRefresh(PullToRefreshBase<WebView> paramPullToRefreshBase)
    {
      ((WebView)paramPullToRefreshBase.getRefreshableView()).reload();
    }
  };
  private final WebChromeClient defaultWebChromeClient = new WebChromeClient()
  {
    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      if (paramInt == 100)
        PullToRefreshWebView.this.onRefreshComplete();
    }
  };

  public PullToRefreshWebView(Context paramContext)
  {
    super(paramContext);
    setOnRefreshListener(defaultOnRefreshListener);
    ((WebView)this.mRefreshableView).setWebChromeClient(this.defaultWebChromeClient);
  }

  public PullToRefreshWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setOnRefreshListener(defaultOnRefreshListener);
    ((WebView)this.mRefreshableView).setWebChromeClient(this.defaultWebChromeClient);
  }

  public PullToRefreshWebView(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
    setOnRefreshListener(defaultOnRefreshListener);
    ((WebView)this.mRefreshableView).setWebChromeClient(this.defaultWebChromeClient);
  }

  public PullToRefreshWebView(Context paramContext, PullToRefreshBase.Mode paramMode, PullToRefreshBase.AnimationStyle paramAnimationStyle)
  {
    super(paramContext, paramMode, paramAnimationStyle);
    setOnRefreshListener(defaultOnRefreshListener);
    ((WebView)this.mRefreshableView).setWebChromeClient(this.defaultWebChromeClient);
  }

  protected WebView createRefreshableView(Context paramContext, AttributeSet paramAttributeSet)
  {
    WebView localWebView = new WebView(paramContext, paramAttributeSet);
    localWebView.setId(R.id.webview);
    return localWebView;
  }

  public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
  {
    return PullToRefreshBase.Orientation.VERTICAL;
  }

  protected boolean isReadyForPullEnd()
  {
    float f = FloatMath.floor(((WebView)this.mRefreshableView).getContentHeight() * ((WebView)this.mRefreshableView).getScale());
    return ((WebView)this.mRefreshableView).getScrollY() >= f - ((WebView)this.mRefreshableView).getHeight();
  }

  protected boolean isReadyForPullStart()
  {
    return ((WebView)this.mRefreshableView).getScrollY() == 0;
  }

  protected void onPtrRestoreInstanceState(Bundle paramBundle)
  {
    super.onPtrRestoreInstanceState(paramBundle);
    ((WebView)this.mRefreshableView).restoreState(paramBundle);
  }

  protected void onPtrSaveInstanceState(Bundle paramBundle)
  {
    super.onPtrSaveInstanceState(paramBundle);
    ((WebView)this.mRefreshableView).saveState(paramBundle);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.PullToRefreshWebView
 * JD-Core Version:    0.6.0
 */