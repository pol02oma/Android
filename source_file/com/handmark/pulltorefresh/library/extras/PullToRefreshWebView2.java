package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import java.util.concurrent.atomic.AtomicBoolean;

public class PullToRefreshWebView2 extends PullToRefreshWebView
{
  static final String DEF_JS_READY_PULL_DOWN_CALL = "javascript:isReadyForPullDown();";
  static final String DEF_JS_READY_PULL_UP_CALL = "javascript:isReadyForPullUp();";
  static final String JS_INTERFACE_PKG = "ptr";
  private final AtomicBoolean mIsReadyForPullDown = new AtomicBoolean(false);
  private final AtomicBoolean mIsReadyForPullUp = new AtomicBoolean(false);
  private JsValueCallback mJsCallback;

  public PullToRefreshWebView2(Context paramContext)
  {
    super(paramContext);
  }

  public PullToRefreshWebView2(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public PullToRefreshWebView2(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
  }

  protected WebView createRefreshableView(Context paramContext, AttributeSet paramAttributeSet)
  {
    WebView localWebView = super.createRefreshableView(paramContext, paramAttributeSet);
    this.mJsCallback = new JsValueCallback();
    localWebView.addJavascriptInterface(this.mJsCallback, "ptr");
    return localWebView;
  }

  protected boolean isReadyForPullEnd()
  {
    ((WebView)getRefreshableView()).loadUrl("javascript:isReadyForPullUp();");
    return this.mIsReadyForPullUp.get();
  }

  protected boolean isReadyForPullStart()
  {
    ((WebView)getRefreshableView()).loadUrl("javascript:isReadyForPullDown();");
    return this.mIsReadyForPullDown.get();
  }

  final class JsValueCallback
  {
    JsValueCallback()
    {
    }

    public void isReadyForPullDownResponse(boolean paramBoolean)
    {
      PullToRefreshWebView2.this.mIsReadyForPullDown.set(paramBoolean);
    }

    public void isReadyForPullUpResponse(boolean paramBoolean)
    {
      PullToRefreshWebView2.this.mIsReadyForPullUp.set(paramBoolean);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.extras.PullToRefreshWebView2
 * JD-Core Version:    0.6.0
 */