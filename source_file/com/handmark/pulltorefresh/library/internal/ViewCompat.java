package com.handmark.pulltorefresh.library.internal;

import android.graphics.drawable.Drawable;
import android.view.View;

public class ViewCompat
{
  public static void postOnAnimation(View paramView, Runnable paramRunnable)
  {
    paramView.postDelayed(paramRunnable, 16L);
  }

  public static void setBackground(View paramView, Drawable paramDrawable)
  {
    paramView.setBackgroundDrawable(paramDrawable);
  }

  public static void setLayerType(View paramView, int paramInt)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.internal.ViewCompat
 * JD-Core Version:    0.6.0
 */