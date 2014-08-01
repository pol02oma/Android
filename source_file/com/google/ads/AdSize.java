package com.google.ads;

import android.content.Context;

@Deprecated
public final class AdSize
{
  public static final int AUTO_HEIGHT = -2;
  public static final AdSize BANNER;
  public static final int FULL_WIDTH = -1;
  public static final AdSize IAB_BANNER;
  public static final AdSize IAB_LEADERBOARD;
  public static final AdSize IAB_MRECT;
  public static final AdSize IAB_WIDE_SKYSCRAPER;
  public static final int LANDSCAPE_AD_HEIGHT = 32;
  public static final int LARGE_AD_HEIGHT = 90;
  public static final int PORTRAIT_AD_HEIGHT = 50;
  public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
  private final com.google.android.gms.ads.AdSize c;

  static
  {
    BANNER = new AdSize(320, 50, "mb");
    IAB_MRECT = new AdSize(300, 250, "as");
    IAB_BANNER = new AdSize(468, 60, "as");
    IAB_LEADERBOARD = new AdSize(728, 90, "as");
    IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");
  }

  public AdSize(int paramInt1, int paramInt2)
  {
    this(new com.google.android.gms.ads.AdSize(paramInt1, paramInt2));
  }

  private AdSize(int paramInt1, int paramInt2, String paramString)
  {
    this(new com.google.android.gms.ads.AdSize(paramInt1, paramInt2));
  }

  public AdSize(com.google.android.gms.ads.AdSize paramAdSize)
  {
    this.c = paramAdSize;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof AdSize))
      return false;
    AdSize localAdSize = (AdSize)paramObject;
    return this.c.equals(localAdSize.c);
  }

  public AdSize findBestSize(AdSize[] paramArrayOfAdSize)
  {
    Object localObject1 = null;
    if (paramArrayOfAdSize == null)
      return localObject1;
    float f1 = 0.0F;
    int i = getWidth();
    int j = getHeight();
    int k = paramArrayOfAdSize.length;
    int m = 0;
    label29: AdSize localAdSize;
    float f2;
    if (m < k)
    {
      localAdSize = paramArrayOfAdSize[m];
      int n = localAdSize.getWidth();
      int i1 = localAdSize.getHeight();
      if (!isSizeAppropriate(n, i1))
        break label118;
      f2 = n * i1 / (i * j);
      if (f2 > 1.0F)
        f2 = 1.0F / f2;
      if (f2 <= f1)
        break label118;
    }
    for (Object localObject2 = localAdSize; ; localObject2 = localObject1)
    {
      m++;
      localObject1 = localObject2;
      f1 = f2;
      break label29;
      break;
      label118: f2 = f1;
    }
  }

  public int getHeight()
  {
    return this.c.getHeight();
  }

  public int getHeightInPixels(Context paramContext)
  {
    return this.c.getHeightInPixels(paramContext);
  }

  public int getWidth()
  {
    return this.c.getWidth();
  }

  public int getWidthInPixels(Context paramContext)
  {
    return this.c.getWidthInPixels(paramContext);
  }

  public int hashCode()
  {
    return this.c.hashCode();
  }

  public boolean isAutoHeight()
  {
    return this.c.isAutoHeight();
  }

  public boolean isCustomAdSize()
  {
    return false;
  }

  public boolean isFullWidth()
  {
    return this.c.isFullWidth();
  }

  public boolean isSizeAppropriate(int paramInt1, int paramInt2)
  {
    int i = getWidth();
    int j = getHeight();
    return (paramInt1 <= 1.25F * i) && (paramInt1 >= 0.8F * i) && (paramInt2 <= 1.25F * j) && (paramInt2 >= 0.8F * j);
  }

  public String toString()
  {
    return this.c.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.ads.AdSize
 * JD-Core Version:    0.6.0
 */