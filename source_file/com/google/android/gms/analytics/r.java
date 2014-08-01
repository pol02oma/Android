package com.google.android.gms.analytics;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class r extends af
{
  private static final Object ri = new Object();
  private static r ru;
  private Context mContext;
  private Handler mHandler;
  private d rj;
  private volatile f rk;
  private int rl = 1800;
  private boolean rm = true;
  private boolean rn;
  private String ro;
  private boolean rp = true;
  private boolean rq = true;
  private e rr = new e()
  {
    public void p(boolean paramBoolean)
    {
      r.this.a(paramBoolean, r.a(r.this));
    }
  };
  private q rs;
  private boolean rt = false;

  public static r bB()
  {
    if (ru == null)
      ru = new r();
    return ru;
  }

  private void bC()
  {
    this.rs = new q(this);
    this.rs.o(this.mContext);
  }

  private void bD()
  {
    this.mHandler = new Handler(this.mContext.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramMessage)
      {
        if ((1 == paramMessage.what) && (r.bG().equals(paramMessage.obj)))
        {
          u.bR().r(true);
          r.this.dispatchLocalHits();
          u.bR().r(false);
          if ((r.b(r.this) > 0) && (!r.c(r.this)))
            r.d(r.this).sendMessageDelayed(r.d(r.this).obtainMessage(1, r.bG()), 1000 * r.b(r.this));
        }
        return true;
      }
    });
    if (this.rl > 0)
      this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, ri), 1000 * this.rl);
  }

  void a(Context paramContext, f paramf)
  {
    monitorenter;
    try
    {
      Context localContext = this.mContext;
      if (localContext != null);
      while (true)
      {
        return;
        this.mContext = paramContext.getApplicationContext();
        if (this.rk != null)
          continue;
        this.rk = paramf;
        if (this.rm)
        {
          dispatchLocalHits();
          this.rm = false;
        }
        if (!this.rn)
          continue;
        br();
        this.rn = false;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (this.rt != paramBoolean1)
          continue;
        boolean bool = this.rp;
        if (bool == paramBoolean2)
          return;
        if (((!paramBoolean1) && (paramBoolean2)) || (this.rl <= 0))
          continue;
        this.mHandler.removeMessages(1, ri);
        if ((paramBoolean1) || (!paramBoolean2) || (this.rl <= 0))
          continue;
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, ri), 1000 * this.rl);
        StringBuilder localStringBuilder = new StringBuilder().append("PowerSaveMode ");
        if (paramBoolean1)
          break label157;
        if (!paramBoolean2)
        {
          break label157;
          aa.v(str);
          this.rt = paramBoolean1;
          this.rp = paramBoolean2;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      String str = "terminated.";
      continue;
      label157: str = "initiated.";
    }
  }

  d bE()
  {
    monitorenter;
    try
    {
      if (this.rj != null)
        break label80;
      if (this.mContext == null)
        throw new IllegalStateException("Cant get a store unless we have a context");
    }
    finally
    {
      monitorexit;
    }
    this.rj = new ac(this.rr, this.mContext);
    if (this.ro != null)
    {
      this.rj.bq().A(this.ro);
      this.ro = null;
    }
    label80: if (this.mHandler == null)
      bD();
    if ((this.rs == null) && (this.rq))
      bC();
    d locald = this.rj;
    monitorexit;
    return locald;
  }

  void bF()
  {
    monitorenter;
    try
    {
      if ((!this.rt) && (this.rp) && (this.rl > 0))
      {
        this.mHandler.removeMessages(1, ri);
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, ri));
      }
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

  void br()
  {
    if (this.rk == null)
    {
      aa.v("setForceLocalDispatch() queued. It will be called once initialization is complete.");
      this.rn = true;
      return;
    }
    u.bR().a(u.a.tq);
    this.rk.br();
  }

  void dispatchLocalHits()
  {
    monitorenter;
    try
    {
      if (this.rk == null)
      {
        aa.v("Dispatch call queued. Dispatch will run once initialization is complete.");
        this.rm = true;
      }
      while (true)
      {
        return;
        u.bR().a(u.a.td);
        this.rk.bp();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void q(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      a(this.rt, paramBoolean);
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

  void setLocalDispatchPeriod(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.mHandler == null)
      {
        aa.v("Dispatch period set with null handler. Dispatch will run once initialization is complete.");
        this.rl = paramInt;
      }
      while (true)
      {
        return;
        u.bR().a(u.a.te);
        if ((!this.rt) && (this.rp) && (this.rl > 0))
          this.mHandler.removeMessages(1, ri);
        this.rl = paramInt;
        if ((paramInt <= 0) || (this.rt) || (!this.rp))
          continue;
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, ri), paramInt * 1000);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.r
 * JD-Core Version:    0.6.0
 */