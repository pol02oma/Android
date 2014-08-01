package com.appbuilder.sdk.android;

import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public abstract interface TopBarInterface
{
  public abstract void designButton(TextView paramTextView, BarDesigner.TitleDesign paramTitleDesign);

  public abstract void disableSwipe();

  public abstract void drawTopBarLeftButton(View paramView);

  public abstract void drawTopBarRightButton(View paramView);

  public abstract void drawTopBarTitleView(View paramView, int paramInt);

  public abstract void hideTopBar();

  public abstract void invisibleTopBar();

  public abstract void setTitleLineAmount(int paramInt);

  public abstract void setTopBarBackground(int paramInt);

  public abstract void setTopBarBackground(Bitmap paramBitmap);

  public abstract void setTopBarLeftButtonOnClickListener(View.OnClickListener paramOnClickListener);

  public abstract void setTopBarLeftButtonText(String paramString, boolean paramBoolean, View.OnClickListener paramOnClickListener);

  public abstract void setTopBarRightButtonOnClickListener(View.OnClickListener paramOnClickListener);

  public abstract void setTopBarRightButtonText(String paramString, boolean paramBoolean, View.OnClickListener paramOnClickListener);

  public abstract void setTopBarTitle(String paramString);

  public abstract void setTopBarTitleColor(int paramInt);

  public abstract void swipeBlock();

  public abstract void visibleTopBar();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.TopBarInterface
 * JD-Core Version:    0.6.0
 */