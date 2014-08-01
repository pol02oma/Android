package com.flurry.sdk;

import java.util.Timer;
import java.util.TimerTask;

class cn
{
  private Timer a;
  private a b;
  private b c;

  cn(b paramb)
  {
    this.c = paramb;
  }

  public void a()
  {
    monitorenter;
    try
    {
      if (this.a != null)
      {
        this.a.cancel();
        this.a = null;
      }
      this.b = null;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(long paramLong)
  {
    monitorenter;
    try
    {
      if (b())
        a();
      this.a = new Timer("FlurrySessionTimer");
      this.b = new a(this.c);
      this.a.schedule(this.b, paramLong);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean b()
  {
    return this.a != null;
  }

  class a extends TimerTask
  {
    private cn.b b;

    a(cn.b arg2)
    {
      Object localObject;
      this.b = localObject;
    }

    public void run()
    {
      cn.this.a();
      if (this.b != null)
        this.b.n();
    }
  }

  public static abstract interface b
  {
    public abstract void n();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.cn
 * JD-Core Version:    0.6.0
 */