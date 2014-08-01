package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;

public class PullToRefreshGridView extends PullToRefreshAdapterViewBase<GridView>
{
  private Context ctx = null;
  private LinearLayout headersLayout = null;

  public PullToRefreshGridView(Context paramContext)
  {
    super(paramContext);
    this.ctx = paramContext;
  }

  public PullToRefreshGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.ctx = paramContext;
  }

  public PullToRefreshGridView(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
    this.ctx = paramContext;
  }

  public PullToRefreshGridView(Context paramContext, PullToRefreshBase.Mode paramMode, PullToRefreshBase.AnimationStyle paramAnimationStyle)
  {
    super(paramContext, paramMode, paramAnimationStyle);
    this.ctx = paramContext;
  }

  public void addHeaderView(View paramView)
  {
    if (this.headersLayout == null)
    {
      this.headersLayout = new LinearLayout(this.ctx);
      View localView = new View(this.ctx);
      localView.setBackgroundColor(-1);
      this.headersLayout.addView(localView, new LinearLayout.LayoutParams(-1, 100));
      super.addView(this.headersLayout, 1);
    }
  }

  protected final GridView createRefreshableView(Context paramContext, AttributeSet paramAttributeSet)
  {
    InternalGridView localInternalGridView = new InternalGridView(paramContext, paramAttributeSet);
    localInternalGridView.setId(R.id.gridview);
    return localInternalGridView;
  }

  public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
  {
    return PullToRefreshBase.Orientation.VERTICAL;
  }

  public void setColumnWidth(int paramInt)
  {
    try
    {
      ((GridView)getRefreshableView()).setColumnWidth(paramInt);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  public void setNumColumns(int paramInt)
  {
    try
    {
      ((GridView)getRefreshableView()).setNumColumns(paramInt);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener paramOnItemSelectedListener)
  {
    try
    {
      ((GridView)getRefreshableView()).setOnItemSelectedListener(paramOnItemSelectedListener);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  public void setStretchMode(int paramInt)
  {
    try
    {
      ((GridView)getRefreshableView()).setStretchMode(paramInt);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  class InternalGridView extends GridView
    implements EmptyViewMethodAccessor
  {
    public InternalGridView(Context paramAttributeSet, AttributeSet arg3)
    {
      super(localAttributeSet);
    }

    public void setEmptyView(View paramView)
    {
      PullToRefreshGridView.this.setEmptyView(paramView);
    }

    public void setEmptyViewInternal(View paramView)
    {
      super.setEmptyView(paramView);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.PullToRefreshGridView
 * JD-Core Version:    0.6.0
 */