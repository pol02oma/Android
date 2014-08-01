package io.vov.vitamio.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RemoteViews.RemoteView;

@RemoteViews.RemoteView
public class CenterLayout extends ViewGroup
{
  private int mHeight;
  private int mPaddingBottom = 0;
  private int mPaddingLeft = 0;
  private int mPaddingRight = 0;
  private int mPaddingTop = 0;
  private int mWidth;

  public CenterLayout(Context paramContext)
  {
    super(paramContext);
  }

  public CenterLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public CenterLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }

  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getChildCount();
    this.mWidth = getMeasuredWidth();
    this.mHeight = getMeasuredHeight();
    int j = 0;
    if (j < i)
    {
      View localView = getChildAt(j);
      LayoutParams localLayoutParams;
      int k;
      int m;
      label101: int n;
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        k = this.mPaddingLeft + localLayoutParams.x;
        if (localLayoutParams.width <= 0)
          break label173;
        m = k + (int)((this.mWidth - localLayoutParams.width) / 2.0D);
        n = this.mPaddingTop + localLayoutParams.y;
        if (localLayoutParams.height <= 0)
          break label197;
      }
      label173: label197: for (int i1 = n + (int)((this.mHeight - localLayoutParams.height) / 2.0D); ; i1 = n + (int)((this.mHeight - localView.getMeasuredHeight()) / 2.0D))
      {
        localView.layout(m, i1, m + localView.getMeasuredWidth(), i1 + localView.getMeasuredHeight());
        j++;
        break;
        m = k + (int)((this.mWidth - localView.getMeasuredWidth()) / 2.0D);
        break label101;
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getChildCount();
    int j = 0;
    int k = 0;
    measureChildren(paramInt1, paramInt2);
    for (int m = 0; m < i; m++)
    {
      View localView = getChildAt(m);
      if (localView.getVisibility() == 8)
        continue;
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      int i2 = localLayoutParams.x + localView.getMeasuredWidth();
      int i3 = localLayoutParams.y + localView.getMeasuredHeight();
      k = Math.max(k, i2);
      j = Math.max(j, i3);
    }
    int n = k + (this.mPaddingLeft + this.mPaddingRight);
    int i1 = Math.max(j + (this.mPaddingTop + this.mPaddingBottom), getSuggestedMinimumHeight());
    setMeasuredDimension(resolveSize(Math.max(n, getSuggestedMinimumWidth()), paramInt1), resolveSize(i1, paramInt2));
  }

  public static class LayoutParams extends ViewGroup.LayoutParams
  {
    public int x;
    public int y;

    public LayoutParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      super(paramInt2);
      this.x = paramInt3;
      this.y = paramInt4;
    }

    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.widget.CenterLayout
 * JD-Core Version:    0.6.0
 */