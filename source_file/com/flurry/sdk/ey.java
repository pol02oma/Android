package com.flurry.sdk;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ey
  implements fc
{
  private static ey a;
  private final List<fc> b = b();

  public static ey a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new ey();
      ey localey = a;
      return localey;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static List<fc> b()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new ez("com.flurry.android.impl.appcloud.AppCloudModule", 10));
    localArrayList.add(new ez("com.flurry.android.impl.ads.FlurryAdModule", 10));
    return Collections.unmodifiableList(localArrayList);
  }

  public void f(Context paramContext)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
      ((fc)localIterator.next()).f(paramContext);
  }

  public void g(Context paramContext)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
      ((fc)localIterator.next()).g(paramContext);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.ey
 * JD-Core Version:    0.6.0
 */