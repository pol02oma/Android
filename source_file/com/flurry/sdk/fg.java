package com.flurry.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;

public class fg
{
  public static int a(int paramInt)
  {
    DisplayMetrics localDisplayMetrics = b();
    return Math.round(paramInt / localDisplayMetrics.density);
  }

  @SuppressLint({"NewApi"})
  public static Point a()
  {
    Display localDisplay = ((WindowManager)eg.a().b().getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    if (Build.VERSION.SDK_INT >= 13)
    {
      localDisplay.getSize(localPoint);
      return localPoint;
    }
    localPoint.x = localDisplay.getWidth();
    localPoint.y = localDisplay.getHeight();
    return localPoint;
  }

  public static int b(int paramInt)
  {
    DisplayMetrics localDisplayMetrics = b();
    return Math.round(paramInt * localDisplayMetrics.density);
  }

  public static DisplayMetrics b()
  {
    Display localDisplay = ((WindowManager)eg.a().b().getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }

  public static float c()
  {
    return b().density;
  }

  public static Pair<Integer, Integer> c(int paramInt)
  {
    int i = f();
    int j = g();
    switch (paramInt)
    {
    default:
      return Pair.create(Integer.valueOf(i), Integer.valueOf(j));
    case 2:
    }
    return Pair.create(Integer.valueOf(j), Integer.valueOf(i));
  }

  public static int d()
  {
    return a().x;
  }

  public static int e()
  {
    return a().y;
  }

  public static int f()
  {
    return a(d());
  }

  public static int g()
  {
    return a(e());
  }

  public static int h()
  {
    Point localPoint = a();
    if (localPoint.x == localPoint.y)
      return 3;
    if (localPoint.x < localPoint.y)
      return 1;
    return 2;
  }

  public static Pair<Integer, Integer> i()
  {
    return Pair.create(Integer.valueOf(f()), Integer.valueOf(g()));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.fg
 * JD-Core Version:    0.6.0
 */