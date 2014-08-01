package com.flurry.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class eg
{
  private static eg a;
  private final Context b;
  private final Handler c;
  private final Handler d;

  private eg(Context paramContext)
  {
    this.b = paramContext.getApplicationContext();
    this.c = new Handler(Looper.getMainLooper());
    HandlerThread localHandlerThread = new HandlerThread("BackgroundHandler");
    localHandlerThread.start();
    this.d = new Handler(localHandlerThread.getLooper());
  }

  public static eg a()
  {
    return a;
  }

  public static void a(Context paramContext)
  {
    monitorenter;
    while (true)
    {
      try
      {
        eg localeg = a;
        if (localeg != null)
          return;
        if (paramContext == null)
          throw new IllegalArgumentException("Context cannot be null");
      }
      finally
      {
        monitorexit;
      }
      a = new eg(paramContext);
    }
  }

  public void a(Runnable paramRunnable)
  {
    if (paramRunnable == null)
      return;
    this.c.post(paramRunnable);
  }

  public Context b()
  {
    return this.b;
  }

  public PackageManager c()
  {
    return this.b.getPackageManager();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.eg
 * JD-Core Version:    0.6.0
 */