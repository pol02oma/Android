package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.util.Log;
import android.view.View;

@TargetApi(9)
public final class OverscrollHelper
{
  static final float DEFAULT_OVERSCROLL_SCALE = 1.0F;
  static final String LOG_TAG = "OverscrollHelper";

  static boolean isAndroidOverScrollEnabled(View paramView)
  {
    return true;
  }

  public static void overScrollBy(PullToRefreshBase<?> paramPullToRefreshBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float paramFloat, boolean paramBoolean)
  {
    int i;
    int j;
    int k;
    PullToRefreshBase.Mode localMode;
    int m;
    switch (1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[paramPullToRefreshBase.getPullToRefreshScrollDirection().ordinal()])
    {
    default:
      i = paramInt3;
      j = paramInt4;
      k = paramPullToRefreshBase.getScrollY();
      if ((!paramPullToRefreshBase.isPullToRefreshOverScrollEnabled()) || (paramPullToRefreshBase.isRefreshing()))
        break;
      localMode = paramPullToRefreshBase.getMode();
      if ((localMode.permitsPullToRefresh()) && (!paramBoolean) && (i != 0))
      {
        m = i + j;
        Log.d("OverscrollHelper", "OverScroll. DeltaX: " + paramInt1 + ", ScrollX: " + paramInt2 + ", DeltaY: " + paramInt3 + ", ScrollY: " + paramInt4 + ", NewY: " + m + ", ScrollRange: " + paramInt5 + ", CurrentScroll: " + k);
        if (m < 0 - paramInt6)
        {
          if (!localMode.showHeaderLoadingLayout())
            break;
          if (k == 0)
            paramPullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
          paramPullToRefreshBase.setHeaderScroll((int)(paramFloat * (k + m)));
        }
      }
    case 1:
    }
    label282: 
    do
    {
      do
      {
        do
        {
          return;
          i = paramInt1;
          j = paramInt2;
          k = paramPullToRefreshBase.getScrollX();
          break;
          if (m <= paramInt5 + paramInt6)
            break label282;
        }
        while (!localMode.showFooterLoadingLayout());
        if (k == 0)
          paramPullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
        paramPullToRefreshBase.setHeaderScroll((int)(paramFloat * (k + m - paramInt5)));
        return;
      }
      while ((Math.abs(m) > paramInt6) && (Math.abs(m - paramInt5) > paramInt6));
      paramPullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
      return;
    }
    while ((!paramBoolean) || (PullToRefreshBase.State.OVERSCROLLING != paramPullToRefreshBase.getState()));
    paramPullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
  }

  public static void overScrollBy(PullToRefreshBase<?> paramPullToRefreshBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean)
  {
    overScrollBy(paramPullToRefreshBase, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, 0, 1.0F, paramBoolean);
  }

  public static void overScrollBy(PullToRefreshBase<?> paramPullToRefreshBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    overScrollBy(paramPullToRefreshBase, paramInt1, paramInt2, paramInt3, paramInt4, 0, paramBoolean);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.OverscrollHelper
 * JD-Core Version:    0.6.0
 */