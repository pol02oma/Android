package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import com.google.android.gms.dynamic.c;

public final class bb
  implements bc.a
{
  private final bf kH;
  private final z kX;
  private final String mC;
  private final Context mContext;
  private final long mD;
  private final ax mE;
  private final ab mF;
  private final db mG;
  private bg mH;
  private int mI = -2;
  private final Object mg = new Object();

  public bb(Context paramContext, String paramString, bf parambf, ay paramay, ax paramax, z paramz, ab paramab, db paramdb)
  {
    this.mContext = paramContext;
    this.mC = paramString;
    this.kH = parambf;
    long l;
    if (paramay.ms != -1L)
      l = paramay.ms;
    while (true)
    {
      this.mD = l;
      this.mE = paramax;
      this.kX = paramz;
      this.mF = paramab;
      this.mG = paramdb;
      return;
      l = 10000L;
    }
  }

  private void a(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    while (true)
    {
      if (this.mI != -2)
        return;
      b(paramLong1, paramLong2, paramLong3, paramLong4);
    }
  }

  private void a(ba paramba)
  {
    try
    {
      if (this.mG.pW < 4100000)
      {
        if (this.mF.lo)
        {
          this.mH.a(c.h(this.mContext), this.kX, this.mE.mq, paramba);
          return;
        }
        this.mH.a(c.h(this.mContext), this.mF, this.kX, this.mE.mq, paramba);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not request ad from mediation adapter.", localRemoteException);
      f(5);
      return;
    }
    if (this.mF.lo)
    {
      this.mH.a(c.h(this.mContext), this.kX, this.mE.mq, this.mE.adJson, paramba);
      return;
    }
    this.mH.a(c.h(this.mContext), this.mF, this.kX, this.mE.mq, this.mE.adJson, paramba);
  }

  private bg ao()
  {
    da.u("Instantiating mediation adapter: " + this.mC);
    try
    {
      bg localbg = this.kH.m(this.mC);
      return localbg;
    }
    catch (RemoteException localRemoteException)
    {
      da.a("Could not instantiate mediation adapter: " + this.mC, localRemoteException);
    }
    return null;
  }

  private void b(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    long l1 = SystemClock.elapsedRealtime();
    long l2 = paramLong2 - (l1 - paramLong1);
    long l3 = paramLong4 - (l1 - paramLong3);
    if ((l2 <= 0L) || (l3 <= 0L))
    {
      da.u("Timed out waiting for adapter.");
      this.mI = 3;
      return;
    }
    try
    {
      this.mg.wait(Math.min(l2, l3));
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      this.mI = -1;
    }
  }

  public bc b(long paramLong1, long paramLong2)
  {
    synchronized (this.mg)
    {
      long l = SystemClock.elapsedRealtime();
      ba localba = new ba();
      cz.pT.post(new Runnable(localba)
      {
        public void run()
        {
          synchronized (bb.a(bb.this))
          {
            if (bb.b(bb.this) != -2)
              return;
            bb.a(bb.this, bb.c(bb.this));
            if (bb.d(bb.this) == null)
            {
              bb.this.f(4);
              return;
            }
          }
          this.mJ.a(bb.this);
          bb.a(bb.this, this.mJ);
          monitorexit;
        }
      });
      a(l, this.mD, paramLong1, paramLong2);
      bc localbc = new bc(this.mE, this.mH, this.mC, localba, this.mI);
      return localbc;
    }
  }

  public void cancel()
  {
    synchronized (this.mg)
    {
      try
      {
        if (this.mH != null)
          this.mH.destroy();
        this.mI = -1;
        this.mg.notify();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          da.b("Could not destroy mediation adapter.", localRemoteException);
      }
    }
  }

  public void f(int paramInt)
  {
    synchronized (this.mg)
    {
      this.mI = paramInt;
      this.mg.notify();
      return;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bb
 * JD-Core Version:    0.6.0
 */