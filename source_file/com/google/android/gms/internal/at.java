package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.Map;

public final class at
  implements ar
{
  private static int a(DisplayMetrics paramDisplayMetrics, Map<String, String> paramMap, String paramString, int paramInt)
  {
    String str = (String)paramMap.get(paramString);
    if (str != null);
    try
    {
      int i = cz.a(paramDisplayMetrics, Integer.parseInt(str));
      paramInt = i;
      return paramInt;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      da.w("Could not parse " + paramString + " in a video GMSG: " + str);
    }
    return paramInt;
  }

  public void a(dd paramdd, Map<String, String> paramMap)
  {
    String str1 = (String)paramMap.get("action");
    if (str1 == null)
    {
      da.w("Action missing from video GMSG.");
      return;
    }
    bo localbo = paramdd.ba();
    if (localbo == null)
    {
      da.w("Could not get ad overlay for a video GMSG.");
      return;
    }
    boolean bool1 = "new".equalsIgnoreCase(str1);
    boolean bool2 = "position".equalsIgnoreCase(str1);
    if ((bool1) || (bool2))
    {
      DisplayMetrics localDisplayMetrics1 = paramdd.getContext().getResources().getDisplayMetrics();
      int i = a(localDisplayMetrics1, paramMap, "x", 0);
      int j = a(localDisplayMetrics1, paramMap, "y", 0);
      int k = a(localDisplayMetrics1, paramMap, "w", -1);
      int m = a(localDisplayMetrics1, paramMap, "h", -1);
      if ((bool1) && (localbo.ap() == null))
      {
        localbo.c(i, j, k, m);
        return;
      }
      localbo.b(i, j, k, m);
      return;
    }
    bs localbs = localbo.ap();
    if (localbs == null)
    {
      bs.a(paramdd, "no_video_view", null);
      return;
    }
    if ("click".equalsIgnoreCase(str1))
    {
      DisplayMetrics localDisplayMetrics2 = paramdd.getContext().getResources().getDisplayMetrics();
      int n = a(localDisplayMetrics2, paramMap, "x", 0);
      int i1 = a(localDisplayMetrics2, paramMap, "y", 0);
      long l = SystemClock.uptimeMillis();
      MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 0, n, i1, 0);
      localbs.b(localMotionEvent);
      localMotionEvent.recycle();
      return;
    }
    if ("controls".equalsIgnoreCase(str1))
    {
      String str3 = (String)paramMap.get("enabled");
      if (str3 == null)
      {
        da.w("Enabled parameter missing from controls video GMSG.");
        return;
      }
      localbs.i(Boolean.parseBoolean(str3));
      return;
    }
    if ("currentTime".equalsIgnoreCase(str1))
    {
      String str2 = (String)paramMap.get("time");
      if (str2 == null)
      {
        da.w("Time parameter missing from currentTime video GMSG.");
        return;
      }
      try
      {
        localbs.seekTo((int)(1000.0F * Float.parseFloat(str2)));
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        da.w("Could not parse time parameter from currentTime video GMSG: " + str2);
        return;
      }
    }
    if ("hide".equalsIgnoreCase(str1))
    {
      localbs.setVisibility(4);
      return;
    }
    if ("load".equalsIgnoreCase(str1))
    {
      localbs.ay();
      return;
    }
    if ("pause".equalsIgnoreCase(str1))
    {
      localbs.pause();
      return;
    }
    if ("play".equalsIgnoreCase(str1))
    {
      localbs.play();
      return;
    }
    if ("show".equalsIgnoreCase(str1))
    {
      localbs.setVisibility(0);
      return;
    }
    if ("src".equalsIgnoreCase(str1))
    {
      localbs.o((String)paramMap.get("src"));
      return;
    }
    da.w("Unknown video action: " + str1);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.at
 * JD-Core Version:    0.6.0
 */