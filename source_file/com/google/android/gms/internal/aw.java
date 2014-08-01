package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import java.util.Iterator;
import java.util.List;

public final class aw
{
  private final bf kH;
  private final Context mContext;
  private final cd mf;
  private final Object mg = new Object();
  private final ay mh;
  private boolean mi = false;
  private bb mj;

  public aw(Context paramContext, cd paramcd, bf parambf, ay paramay)
  {
    this.mContext = paramContext;
    this.mf = paramcd;
    this.kH = parambf;
    this.mh = paramay;
  }

  public bc a(long paramLong1, long paramLong2)
  {
    da.s("Starting mediation.");
    Iterator localIterator1 = this.mh.mr.iterator();
    while (localIterator1.hasNext())
    {
      ax localax = (ax)localIterator1.next();
      da.u("Trying mediation network: " + localax.mm);
      Iterator localIterator2 = localax.mn.iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        bc localbc2;
        synchronized (this.mg)
        {
          if (this.mi)
          {
            bc localbc1 = new bc(-1);
            return localbc1;
          }
          this.mj = new bb(this.mContext, str, this.kH, this.mh, localax, this.mf.oc, this.mf.kQ, this.mf.kN);
          localbc2 = this.mj.b(paramLong1, paramLong2);
          if (localbc2.mL == 0)
          {
            da.s("Adapter succeeded.");
            return localbc2;
          }
        }
        if (localbc2.mN == null)
          continue;
        cz.pT.post(new Runnable(localbc2)
        {
          public void run()
          {
            try
            {
              this.mk.mN.destroy();
              return;
            }
            catch (RemoteException localRemoteException)
            {
              da.b("Could not destroy mediation adapter.", localRemoteException);
            }
          }
        });
      }
    }
    return new bc(1);
  }

  public void cancel()
  {
    synchronized (this.mg)
    {
      this.mi = true;
      if (this.mj != null)
        this.mj.cancel();
      return;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.aw
 * JD-Core Version:    0.6.0
 */