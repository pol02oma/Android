package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R.drawable;

public class RotateLoadingLayout extends LoadingLayout
{
  static final int ROTATION_ANIMATION_DURATION = 1200;
  private final Matrix mHeaderImageMatrix;
  private final Animation mRotateAnimation;
  private final boolean mRotateDrawableWhilePulling;
  private float mRotationPivotX;
  private float mRotationPivotY;

  public RotateLoadingLayout(Context paramContext, PullToRefreshBase.Mode paramMode, PullToRefreshBase.Orientation paramOrientation, TypedArray paramTypedArray)
  {
    super(paramContext, paramMode, paramOrientation, paramTypedArray);
    this.mRotateDrawableWhilePulling = paramTypedArray.getBoolean(15, true);
    this.mHeaderImage.setScaleType(ImageView.ScaleType.MATRIX);
    this.mHeaderImageMatrix = new Matrix();
    this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);
    this.mRotateAnimation = new RotateAnimation(0.0F, 720.0F, 1, 0.5F, 1, 0.5F);
    this.mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
    this.mRotateAnimation.setDuration(1200L);
    this.mRotateAnimation.setRepeatCount(-1);
    this.mRotateAnimation.setRepeatMode(1);
  }

  private void resetImageRotation()
  {
    if (this.mHeaderImageMatrix != null)
    {
      this.mHeaderImageMatrix.reset();
      this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);
    }
  }

  protected int getDefaultDrawableResId()
  {
    return R.drawable.default_ptr_rotate;
  }

  public void onLoadingDrawableSet(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      this.mRotationPivotX = Math.round(paramDrawable.getIntrinsicWidth() / 2.0F);
      this.mRotationPivotY = Math.round(paramDrawable.getIntrinsicHeight() / 2.0F);
    }
  }

  protected void onPullImpl(float paramFloat)
  {
    float f;
    if (this.mRotateDrawableWhilePulling)
      f = paramFloat * 90.0F;
    while (true)
    {
      this.mHeaderImageMatrix.setRotate(f, this.mRotationPivotX, this.mRotationPivotY);
      this.mHeaderImage.setImageMatrix(this.mHeaderImageMatrix);
      return;
      f = Math.max(0.0F, Math.min(180.0F, 360.0F * paramFloat - 180.0F));
    }
  }

  protected void pullToRefreshImpl()
  {
  }

  protected void refreshingImpl()
  {
    this.mHeaderImage.startAnimation(this.mRotateAnimation);
  }

  protected void releaseToRefreshImpl()
  {
  }

  protected void resetImpl()
  {
    this.mHeaderImage.clearAnimation();
    resetImageRotation();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.internal.RotateLoadingLayout
 * JD-Core Version:    0.6.0
 */