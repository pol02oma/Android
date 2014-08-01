package com.handmark.pulltorefresh.library;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import java.util.HashSet;
import java.util.Iterator;

public class LoadingLayoutProxy
  implements ILoadingLayout
{
  private final HashSet<LoadingLayout> mLoadingLayouts = new HashSet();

  public void addLayout(LoadingLayout paramLoadingLayout)
  {
    if (paramLoadingLayout != null)
      this.mLoadingLayouts.add(paramLoadingLayout);
  }

  public void setLastUpdatedLabel(CharSequence paramCharSequence)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout)localIterator.next()).setLastUpdatedLabel(paramCharSequence);
  }

  public void setLoadingDrawable(Drawable paramDrawable)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout)localIterator.next()).setLoadingDrawable(paramDrawable);
  }

  public void setPullLabel(CharSequence paramCharSequence)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout)localIterator.next()).setPullLabel(paramCharSequence);
  }

  public void setRefreshingLabel(CharSequence paramCharSequence)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout)localIterator.next()).setRefreshingLabel(paramCharSequence);
  }

  public void setReleaseLabel(CharSequence paramCharSequence)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout)localIterator.next()).setReleaseLabel(paramCharSequence);
  }

  public void setTextTypeface(Typeface paramTypeface)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout)localIterator.next()).setTextTypeface(paramTypeface);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.LoadingLayoutProxy
 * JD-Core Version:    0.6.0
 */