package com.flurry.sdk;

import java.util.concurrent.ThreadFactory;

public class ff
  implements ThreadFactory
{
  private final ThreadGroup a;
  private final int b;

  public ff(String paramString, int paramInt)
  {
    this.a = new ThreadGroup(paramString);
    this.b = paramInt;
  }

  public Thread newThread(Runnable paramRunnable)
  {
    Thread localThread = new Thread(this.a, paramRunnable);
    localThread.setName(this.a.getName() + ":" + localThread.getId());
    localThread.setPriority(this.b);
    return localThread;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.ff
 * JD-Core Version:    0.6.0
 */