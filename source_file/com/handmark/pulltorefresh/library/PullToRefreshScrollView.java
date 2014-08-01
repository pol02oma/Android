package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView>
{
  public PullToRefreshScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public PullToRefreshScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public PullToRefreshScrollView(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
  }

  public PullToRefreshScrollView(Context paramContext, PullToRefreshBase.Mode paramMode, PullToRefreshBase.AnimationStyle paramAnimationStyle)
  {
    super(paramContext, paramMode, paramAnimationStyle);
  }

  protected ScrollView createRefreshableView(Context paramContext, AttributeSet paramAttributeSet)
  {
    ScrollView localScrollView = new ScrollView(paramContext, paramAttributeSet);
    localScrollView.setId(R.id.scrollview);
    return localScrollView;
  }

  public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
  {
    return PullToRefreshBase.Orientation.VERTICAL;
  }

  protected boolean isReadyForPullEnd()
  {
    View localView = ((ScrollView)this.mRefreshableView).getChildAt(0);
    if (localView != null)
      return ((ScrollView)this.mRefreshableView).getScrollY() >= localView.getHeight() - getHeight();
    return false;
  }

  protected boolean isReadyForPullStart()
  {
    return ((ScrollView)this.mRefreshableView).getScrollY() == 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.PullToRefreshScrollView
 * JD-Core Version:    0.6.0
 */