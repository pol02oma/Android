package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

public class PullToRefreshHorizontalScrollView extends PullToRefreshBase<HorizontalScrollView>
{
  public PullToRefreshHorizontalScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public PullToRefreshHorizontalScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public PullToRefreshHorizontalScrollView(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
  }

  public PullToRefreshHorizontalScrollView(Context paramContext, PullToRefreshBase.Mode paramMode, PullToRefreshBase.AnimationStyle paramAnimationStyle)
  {
    super(paramContext, paramMode, paramAnimationStyle);
  }

  protected HorizontalScrollView createRefreshableView(Context paramContext, AttributeSet paramAttributeSet)
  {
    HorizontalScrollView localHorizontalScrollView = new HorizontalScrollView(paramContext, paramAttributeSet);
    localHorizontalScrollView.setId(R.id.scrollview);
    return localHorizontalScrollView;
  }

  public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
  {
    return PullToRefreshBase.Orientation.HORIZONTAL;
  }

  protected boolean isReadyForPullEnd()
  {
    View localView = ((HorizontalScrollView)this.mRefreshableView).getChildAt(0);
    if (localView != null)
      return ((HorizontalScrollView)this.mRefreshableView).getScrollX() >= localView.getWidth() - getWidth();
    return false;
  }

  protected boolean isReadyForPullStart()
  {
    return ((HorizontalScrollView)this.mRefreshableView).getScrollX() == 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.PullToRefreshHorizontalScrollView
 * JD-Core Version:    0.6.0
 */