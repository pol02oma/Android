package com.ibuildapp.romanblack.NewsPlugin;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PullToRefreshListView extends ListView
  implements AbsListView.OnScrollListener
{
  private static final int PULL_TO_REFRESH = 2;
  private static final int REFRESHING = 4;
  private static final int RELEASE_TO_REFRESH = 3;
  private static final String TAG = "PullToRefreshListView";
  private static final int TAP_TO_REFRESH = 1;
  private boolean mBounceHack;
  private int mCurrentScrollState;
  private RotateAnimation mFlipAnimation;
  private LayoutInflater mInflater;
  private int mLastMotionY;
  private OnRefreshListener mOnRefreshListener;
  private AbsListView.OnScrollListener mOnScrollListener;
  private int mRefreshOriginalTopPadding;
  private int mRefreshState;
  private RelativeLayout mRefreshView;
  private int mRefreshViewHeight;
  private ImageView mRefreshViewImage;
  private TextView mRefreshViewLastUpdated;
  private ProgressBar mRefreshViewProgress;
  private TextView mRefreshViewText;
  private RotateAnimation mReverseFlipAnimation;
  private boolean refreshOn = true;

  public PullToRefreshListView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public PullToRefreshListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public PullToRefreshListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void applyHeaderPadding(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getHistorySize();
    for (int j = 0; j < i; j++)
    {
      if (this.mRefreshState != 3)
        continue;
      if (isVerticalFadingEdgeEnabled())
        setVerticalScrollBarEnabled(false);
      int k = (int)(((int)paramMotionEvent.getHistoricalY(j) - this.mLastMotionY - this.mRefreshViewHeight) / 1.7D);
      this.mRefreshView.setPadding(this.mRefreshView.getPaddingLeft(), k, this.mRefreshView.getPaddingRight(), this.mRefreshView.getPaddingBottom());
    }
  }

  private void init(Context paramContext)
  {
    this.mFlipAnimation = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
    this.mFlipAnimation.setInterpolator(new LinearInterpolator());
    this.mFlipAnimation.setDuration(250L);
    this.mFlipAnimation.setFillAfter(true);
    this.mReverseFlipAnimation = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    this.mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
    this.mReverseFlipAnimation.setDuration(250L);
    this.mReverseFlipAnimation.setFillAfter(true);
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.mRefreshView = ((RelativeLayout)this.mInflater.inflate(R.layout.romanblack_rss_pull_to_refresh_header, this, false));
    this.mRefreshViewText = ((TextView)this.mRefreshView.findViewById(R.id.romanblack_rss_pull_to_refresh_text));
    this.mRefreshViewImage = ((ImageView)this.mRefreshView.findViewById(R.id.romanblack_rss_pull_to_refresh_image));
    this.mRefreshViewProgress = ((ProgressBar)this.mRefreshView.findViewById(R.id.romanblack_rss_pull_to_refresh_progress));
    this.mRefreshViewLastUpdated = ((TextView)this.mRefreshView.findViewById(R.id.romanblack_rss_pull_to_refresh_updated_at));
    this.mRefreshViewImage.setMinimumHeight(50);
    this.mRefreshView.setOnClickListener(new OnClickRefreshListener(null));
    this.mRefreshOriginalTopPadding = this.mRefreshView.getPaddingTop();
    this.mRefreshState = 1;
    addHeaderView(this.mRefreshView);
    super.setOnScrollListener(this);
    measureView(this.mRefreshView);
    this.mRefreshViewHeight = this.mRefreshView.getMeasuredHeight();
  }

  private void measureView(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    if (localLayoutParams == null)
      localLayoutParams = new ViewGroup.LayoutParams(-1, -2);
    int i = ViewGroup.getChildMeasureSpec(0, 0, localLayoutParams.width);
    int j = localLayoutParams.height;
    if (j > 0);
    for (int k = View.MeasureSpec.makeMeasureSpec(j, 1073741824); ; k = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(i, k);
      return;
    }
  }

  private void resetHeader()
  {
    if (this.mRefreshState != 1)
    {
      this.mRefreshState = 1;
      resetHeaderPadding();
      this.mRefreshViewText.setText(R.string.romanblack_rss_ptr_tap);
      this.mRefreshViewImage.setImageResource(R.drawable.romanblack_rss_ic_pulltorefresh_arrow);
      this.mRefreshViewImage.clearAnimation();
      this.mRefreshViewImage.setVisibility(8);
      this.mRefreshViewProgress.setVisibility(8);
    }
  }

  private void resetHeaderPadding()
  {
    this.mRefreshView.setPadding(this.mRefreshView.getPaddingLeft(), this.mRefreshOriginalTopPadding, this.mRefreshView.getPaddingRight(), this.mRefreshView.getPaddingBottom());
  }

  protected void onAttachedToWindow()
  {
    setSelection(1);
    super.onAttachedToWindow();
  }

  public void onRefresh()
  {
    Log.d("PullToRefreshListView", "onRefresh");
    if (this.mOnRefreshListener != null)
      this.mOnRefreshListener.onRefresh();
  }

  public void onRefreshComplete()
  {
    Log.d("PullToRefreshListView", "onRefreshComplete");
    resetHeader();
    if (this.mRefreshView.getBottom() > 0)
    {
      invalidateViews();
      setSelection(1);
    }
  }

  public void onRefreshComplete(CharSequence paramCharSequence)
  {
    setLastUpdated(paramCharSequence);
    onRefreshComplete();
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.refreshOn)
    {
      if ((this.mCurrentScrollState != 1) || (this.mRefreshState == 4))
        break label210;
      if (paramInt1 != 0)
        break label194;
      this.mRefreshViewImage.setVisibility(0);
      if (((this.mRefreshView.getBottom() < 20 + this.mRefreshViewHeight) && (this.mRefreshView.getTop() < 0)) || (this.mRefreshState == 3))
        break label125;
      this.mRefreshViewText.setText(R.string.romanblack_rss_ptr_release);
      this.mRefreshViewImage.clearAnimation();
      this.mRefreshViewImage.startAnimation(this.mFlipAnimation);
      this.mRefreshState = 3;
    }
    while (true)
    {
      if (this.mOnScrollListener != null)
        this.mOnScrollListener.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
      return;
      label125: if ((this.mRefreshView.getBottom() >= 20 + this.mRefreshViewHeight) || (this.mRefreshState == 2))
        continue;
      this.mRefreshViewText.setText(R.string.romanblack_rss_ptr_pull);
      if (this.mRefreshState != 1)
      {
        this.mRefreshViewImage.clearAnimation();
        this.mRefreshViewImage.startAnimation(this.mReverseFlipAnimation);
      }
      this.mRefreshState = 2;
      continue;
      label194: this.mRefreshViewImage.setVisibility(8);
      resetHeader();
      continue;
      label210: if ((this.mCurrentScrollState == 2) && (paramInt1 == 0) && (this.mRefreshState != 4))
      {
        setSelection(1);
        this.mBounceHack = true;
        continue;
      }
      if ((!this.mBounceHack) || (this.mCurrentScrollState != 2))
        continue;
      setSelection(1);
    }
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    this.mCurrentScrollState = paramInt;
    if (this.mCurrentScrollState == 0)
      this.mBounceHack = false;
    if (this.mOnScrollListener != null)
      this.mOnScrollListener.onScrollStateChanged(paramAbsListView, paramInt);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getY();
    this.mBounceHack = false;
    if (this.refreshOn)
      switch (paramMotionEvent.getAction())
      {
      default:
      case 1:
      case 0:
      case 2:
      }
    while (true)
    {
      return super.onTouchEvent(paramMotionEvent);
      if (!isVerticalScrollBarEnabled())
        setVerticalScrollBarEnabled(true);
      if ((getFirstVisiblePosition() != 0) || (this.mRefreshState == 4))
        continue;
      if (((this.mRefreshView.getBottom() >= this.mRefreshViewHeight) || (this.mRefreshView.getTop() >= 0)) && (this.mRefreshState == 3))
      {
        this.mRefreshState = 4;
        prepareForRefresh();
        onRefresh();
        continue;
      }
      if ((this.mRefreshView.getBottom() >= this.mRefreshViewHeight) && (this.mRefreshView.getTop() > 0))
        continue;
      resetHeader();
      setSelection(1);
      continue;
      this.mLastMotionY = i;
      continue;
      applyHeaderPadding(paramMotionEvent);
    }
  }

  public void prepareForRefresh()
  {
    resetHeaderPadding();
    this.mRefreshViewImage.setVisibility(8);
    this.mRefreshViewImage.setImageDrawable(null);
    this.mRefreshViewProgress.setVisibility(0);
    this.mRefreshViewText.setText(R.string.romanblack_rss_loading);
    this.mRefreshState = 4;
  }

  public void refreshOff()
  {
    removeHeaderView(this.mRefreshView);
    this.mRefreshViewImage.setMinimumHeight(0);
    this.mRefreshView.setVisibility(8);
    this.refreshOn = false;
    setSelection(0);
  }

  public void refreshOn()
  {
    addHeaderView(this.mRefreshView);
    this.mRefreshViewImage.setMinimumHeight(50);
    this.mRefreshView.setVisibility(0);
    this.refreshOn = true;
    setSelection(1);
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    super.setAdapter(paramListAdapter);
    setSelection(1);
  }

  public void setLastUpdated(CharSequence paramCharSequence)
  {
    if (paramCharSequence != null)
    {
      this.mRefreshViewLastUpdated.setVisibility(0);
      this.mRefreshViewLastUpdated.setText(paramCharSequence);
      return;
    }
    this.mRefreshViewLastUpdated.setVisibility(8);
  }

  public void setOnRefreshListener(OnRefreshListener paramOnRefreshListener)
  {
    this.mOnRefreshListener = paramOnRefreshListener;
  }

  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    this.mOnScrollListener = paramOnScrollListener;
  }

  private class OnClickRefreshListener
    implements View.OnClickListener
  {
    private OnClickRefreshListener()
    {
    }

    public void onClick(View paramView)
    {
      if (PullToRefreshListView.this.mRefreshState != 4)
      {
        PullToRefreshListView.this.prepareForRefresh();
        PullToRefreshListView.this.onRefresh();
      }
    }
  }

  public static abstract interface OnRefreshListener
  {
    public abstract void onRefresh();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.PullToRefreshListView
 * JD-Core Version:    0.6.0
 */