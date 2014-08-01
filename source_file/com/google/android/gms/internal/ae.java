package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.ads.AdSize;

public final class ae
{
  private final AdSize[] lr;
  private final String ls;

  public ae(Context paramContext, AttributeSet paramAttributeSet)
  {
    TypedArray localTypedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.AdsAttrs);
    String str1 = localTypedArray.getString(0);
    String str2 = localTypedArray.getString(i);
    int j;
    if (!TextUtils.isEmpty(str1))
    {
      j = i;
      if (TextUtils.isEmpty(str2))
        break label108;
      label54: if ((j == 0) || (i != 0))
        break label113;
    }
    for (this.lr = f(str1); ; this.lr = f(str2))
    {
      this.ls = localTypedArray.getString(2);
      if (!TextUtils.isEmpty(this.ls))
        return;
      throw new IllegalArgumentException("Required XML attribute \"adUnitId\" was missing.");
      j = 0;
      break;
      label108: i = 0;
      break label54;
      label113: if ((j != 0) || (i == 0))
        break label134;
    }
    label134: if ((j != 0) && (i != 0))
      throw new IllegalArgumentException("Either XML attribute \"adSize\" or XML attribute \"supportedAdSizes\" should be specified, but not both.");
    throw new IllegalArgumentException("Required XML attribute \"adSize\" was missing.");
  }

  private static AdSize[] f(String paramString)
  {
    String[] arrayOfString1 = paramString.split("\\s*,\\s*");
    AdSize[] arrayOfAdSize = new AdSize[arrayOfString1.length];
    int i = 0;
    if (i < arrayOfString1.length)
    {
      String str = arrayOfString1[i].trim();
      String[] arrayOfString2;
      if (str.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$"))
      {
        arrayOfString2 = str.split("[xX]");
        arrayOfString2[0] = arrayOfString2[0].trim();
        arrayOfString2[1] = arrayOfString2[1].trim();
      }
      while (true)
      {
        try
        {
          if (!"FULL_WIDTH".equals(arrayOfString2[0]))
            continue;
          int j = -1;
          boolean bool = "AUTO_HEIGHT".equals(arrayOfString2[1]);
          if (!bool)
            continue;
          int m = -2;
          arrayOfAdSize[i] = new AdSize(j, m);
          i++;
          break;
          j = Integer.parseInt(arrayOfString2[0]);
          continue;
          int k = Integer.parseInt(arrayOfString2[1]);
          m = k;
          continue;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + str);
        }
        if ("BANNER".equals(str))
        {
          arrayOfAdSize[i] = AdSize.BANNER;
          continue;
        }
        if ("FULL_BANNER".equals(str))
        {
          arrayOfAdSize[i] = AdSize.FULL_BANNER;
          continue;
        }
        if ("LEADERBOARD".equals(str))
        {
          arrayOfAdSize[i] = AdSize.LEADERBOARD;
          continue;
        }
        if ("MEDIUM_RECTANGLE".equals(str))
        {
          arrayOfAdSize[i] = AdSize.MEDIUM_RECTANGLE;
          continue;
        }
        if ("SMART_BANNER".equals(str))
        {
          arrayOfAdSize[i] = AdSize.SMART_BANNER;
          continue;
        }
        if (!"WIDE_SKYSCRAPER".equals(str))
          break label297;
        arrayOfAdSize[i] = AdSize.WIDE_SKYSCRAPER;
      }
      label297: throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + str);
    }
    if (arrayOfAdSize.length == 0)
      throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + paramString);
    return arrayOfAdSize;
  }

  public AdSize[] c(boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.lr.length != 1))
      throw new IllegalArgumentException("The adSizes XML attribute is only allowed on PublisherAdViews.");
    return this.lr;
  }

  public String getAdUnitId()
  {
    return this.ls;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ae
 * JD-Core Version:    0.6.0
 */