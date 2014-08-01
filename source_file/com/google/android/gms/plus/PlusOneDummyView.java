package com.google.android.gms.plus;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class PlusOneDummyView extends FrameLayout
{
  public static final String TAG = "PlusOneDummyView";

  public PlusOneDummyView(Context paramContext, int paramInt)
  {
    super(paramContext);
    Button localButton = new Button(paramContext);
    localButton.setEnabled(false);
    localButton.setBackgroundDrawable(hk().getDrawable(paramInt));
    Point localPoint = bA(paramInt);
    addView(localButton, new FrameLayout.LayoutParams(localPoint.x, localPoint.y, 17));
  }

  private Point bA(int paramInt)
  {
    int i = 24;
    int k = 20;
    Point localPoint = new Point();
    switch (paramInt)
    {
    default:
      int n = i;
      i = 38;
      k = n;
    case 1:
    case 0:
    case 2:
    }
    while (true)
    {
      DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
      float f1 = TypedValue.applyDimension(1, i, localDisplayMetrics);
      float f2 = TypedValue.applyDimension(1, k, localDisplayMetrics);
      localPoint.x = (int)(0.5D + f1);
      localPoint.y = (int)(0.5D + f2);
      return localPoint;
      int j = 32;
      continue;
      int m = 14;
      continue;
      j = 50;
    }
  }

  private d hk()
  {
    Object localObject = new b(getContext(), null);
    if (!((d)localObject).isValid())
      localObject = new c(getContext(), null);
    if (!((d)localObject).isValid())
      localObject = new a(getContext(), null);
    return (d)localObject;
  }

  private static class a
    implements PlusOneDummyView.d
  {
    private Context mContext;

    private a(Context paramContext)
    {
      this.mContext = paramContext;
    }

    public Drawable getDrawable(int paramInt)
    {
      return this.mContext.getResources().getDrawable(17301508);
    }

    public boolean isValid()
    {
      return true;
    }
  }

  private static class b
    implements PlusOneDummyView.d
  {
    private Context mContext;

    private b(Context paramContext)
    {
      this.mContext = paramContext;
    }

    public Drawable getDrawable(int paramInt)
    {
      while (true)
      {
        try
        {
          Resources localResources = this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
          switch (paramInt)
          {
          case 2:
            return localResources.getDrawable(localResources.getIdentifier(str, "drawable", "com.google.android.gms"));
            str = "ic_plusone_tall";
            continue;
          default:
          case 0:
          case 1:
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          return null;
        }
        String str = "ic_plusone_standard";
        continue;
        str = "ic_plusone_small";
        continue;
        str = "ic_plusone_medium";
      }
    }

    public boolean isValid()
    {
      try
      {
        this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
        return true;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
      }
      return false;
    }
  }

  private static class c
    implements PlusOneDummyView.d
  {
    private Context mContext;

    private c(Context paramContext)
    {
      this.mContext = paramContext;
    }

    public Drawable getDrawable(int paramInt)
    {
      String str;
      switch (paramInt)
      {
      default:
        str = "ic_plusone_standard_off_client";
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        int i = this.mContext.getResources().getIdentifier(str, "drawable", this.mContext.getPackageName());
        return this.mContext.getResources().getDrawable(i);
        str = "ic_plusone_small_off_client";
        continue;
        str = "ic_plusone_medium_off_client";
        continue;
        str = "ic_plusone_tall_off_client";
      }
    }

    public boolean isValid()
    {
      int i = this.mContext.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", this.mContext.getPackageName());
      int j = this.mContext.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", this.mContext.getPackageName());
      int k = this.mContext.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", this.mContext.getPackageName());
      int m = this.mContext.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", this.mContext.getPackageName());
      return (i != 0) && (j != 0) && (k != 0) && (m != 0);
    }
  }

  private static abstract interface d
  {
    public abstract Drawable getDrawable(int paramInt);

    public abstract boolean isValid();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.plus.PlusOneDummyView
 * JD-Core Version:    0.6.0
 */