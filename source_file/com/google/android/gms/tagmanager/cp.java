package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.j;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class cp
  implements o.e
{
  private final String TM;
  private String Ui;
  private bg<c.j> Wi;
  private r Wj;
  private final ScheduledExecutorService Wl;
  private final a Wm;
  private ScheduledFuture<?> Wn;
  private boolean mClosed;
  private final Context mContext;

  public cp(Context paramContext, String paramString, r paramr)
  {
    this(paramContext, paramString, paramr, null, null);
  }

  cp(Context paramContext, String paramString, r paramr, b paramb, a parama)
  {
    this.Wj = paramr;
    this.mContext = paramContext;
    this.TM = paramString;
    if (paramb == null)
      paramb = new b()
      {
        public ScheduledExecutorService jB()
        {
          return Executors.newSingleThreadScheduledExecutor();
        }
      };
    this.Wl = paramb.jB();
    if (parama == null)
    {
      this.Wm = new a()
      {
        public co a(r paramr)
        {
          return new co(cp.a(cp.this), cp.b(cp.this), paramr);
        }
      };
      return;
    }
    this.Wm = parama;
  }

  private co bv(String paramString)
  {
    co localco = this.Wm.a(this.Wj);
    localco.a(this.Wi);
    localco.bf(this.Ui);
    localco.bu(paramString);
    return localco;
  }

  private void jA()
  {
    monitorenter;
    try
    {
      if (this.mClosed)
        throw new IllegalStateException("called method after closed");
    }
    finally
    {
      monitorexit;
    }
    monitorexit;
  }

  public void a(bg<c.j> parambg)
  {
    monitorenter;
    try
    {
      jA();
      this.Wi = parambg;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void bf(String paramString)
  {
    monitorenter;
    try
    {
      jA();
      this.Ui = paramString;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void d(long paramLong, String paramString)
  {
    monitorenter;
    try
    {
      bh.v("loadAfterDelay: containerId=" + this.TM + " delay=" + paramLong);
      jA();
      if (this.Wi == null)
        throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
    }
    finally
    {
      monitorexit;
    }
    if (this.Wn != null)
      this.Wn.cancel(false);
    this.Wn = this.Wl.schedule(bv(paramString), paramLong, TimeUnit.MILLISECONDS);
    monitorexit;
  }

  public void release()
  {
    monitorenter;
    try
    {
      jA();
      if (this.Wn != null)
        this.Wn.cancel(false);
      this.Wl.shutdown();
      this.mClosed = true;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  static abstract interface a
  {
    public abstract co a(r paramr);
  }

  static abstract interface b
  {
    public abstract ScheduledExecutorService jB();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cp
 * JD-Core Version:    0.6.0
 */