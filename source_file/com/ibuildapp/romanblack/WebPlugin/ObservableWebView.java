package com.ibuildapp.romanblack.WebPlugin;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.webkit.WebView;

class ObservableWebView extends WebView
{
  private OnScrollChangedCallback mOnScrollChangedCallback;

  public ObservableWebView(Context paramContext)
  {
    super(paramContext);
  }

  public ObservableWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ObservableWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public OnScrollChangedCallback getOnScrollChangedCallback()
  {
    return this.mOnScrollChangedCallback;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    invalidate();
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mOnScrollChangedCallback != null)
      this.mOnScrollChangedCallback.onScroll(paramInt1, paramInt2);
  }

  public void setOnScrollChangedCallback(OnScrollChangedCallback paramOnScrollChangedCallback)
  {
    this.mOnScrollChangedCallback = paramOnScrollChangedCallback;
  }

  public static abstract interface OnScrollChangedCallback
  {
    public abstract void onScroll(int paramInt1, int paramInt2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.WebPlugin.ObservableWebView
 * JD-Core Version:    0.6.0
 */