package com.google.android.gms.common.api;

import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.er;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class a
{
  public static abstract class a<R extends Result, A extends Api.a>
    implements PendingResult<R>, a.c<R>, b.c<A>
  {
    private final Api.b<A> zc;
    private final Object zd = new Object();
    private a.b<R> ze;
    private final CountDownLatch zf = new CountDownLatch(1);
    private final ArrayList<PendingResult.a> zg = new ArrayList();
    private ResultCallback<R> zh;
    private volatile R zi;
    private volatile boolean zj;
    private boolean zk;
    private boolean zl;
    private b.a zm;

    protected a()
    {
      this((Api.b)null);
    }

    protected a(Api.b<A> paramb)
    {
      this.zc = paramb;
    }

    private void a(RemoteException paramRemoteException)
    {
      a(d(new Status(8, paramRemoteException.getLocalizedMessage(), null)));
    }

    private R ds()
    {
      while (true)
      {
        synchronized (this.zd)
        {
          if (!this.zj)
          {
            bool = true;
            er.a(bool, "Result has already been consumed.");
            er.a(isReady(), "Result is not ready.");
            Result localResult = this.zi;
            dt();
            return localResult;
          }
        }
        boolean bool = false;
      }
    }

    private void dv()
    {
      if ((this.zi != null) && ((this instanceof Releasable)));
      try
      {
        ((Releasable)this).release();
        return;
      }
      catch (Exception localException)
      {
        Log.w("GoogleApi", "Unable to release " + this, localException);
      }
    }

    protected abstract void a(A paramA)
      throws RemoteException;

    public final void a(R paramR)
    {
      boolean bool1 = true;
      while (true)
      {
        synchronized (this.zd)
        {
          if (!this.zl)
            continue;
          if (!(paramR instanceof Releasable))
            continue;
          ((Releasable)paramR).release();
          return;
          if (!isReady())
          {
            bool2 = bool1;
            er.a(bool2, "Results have already been set");
            if (this.zj)
              break label97;
            er.a(bool1, "Result has already been consumed");
            this.zi = paramR;
            if (!this.zk)
              break;
            dv();
            return;
          }
        }
        boolean bool2 = false;
        continue;
        label97: bool1 = false;
      }
      this.zf.countDown();
      Status localStatus = this.zi.getStatus();
      if (this.zh != null)
      {
        this.ze.dw();
        this.ze.a(this.zh, ds());
      }
      Iterator localIterator = this.zg.iterator();
      while (localIterator.hasNext())
        ((PendingResult.a)localIterator.next()).k(localStatus);
      this.zg.clear();
      monitorexit;
    }

    public void a(b.a parama)
    {
      this.zm = parama;
    }

    public final R await()
    {
      boolean bool1;
      if (!this.zj)
        bool1 = true;
      while (true)
      {
        er.a(bool1, "Results has already been consumed");
        boolean bool2;
        if (!isReady())
        {
          Looper localLooper1 = Looper.myLooper();
          Looper localLooper2 = Looper.getMainLooper();
          bool2 = false;
          if (localLooper1 == localLooper2);
        }
        else
        {
          bool2 = true;
        }
        er.a(bool2, "await must not be called on the UI thread");
        try
        {
          this.zf.await();
          er.a(isReady(), "Result is not ready.");
          return ds();
          bool1 = false;
        }
        catch (InterruptedException localInterruptedException)
        {
          synchronized (this.zd)
          {
            a(d(Status.zR));
            this.zl = true;
          }
        }
      }
    }

    public final R await(long paramLong, TimeUnit paramTimeUnit)
    {
      boolean bool1;
      if (!this.zj)
      {
        bool1 = true;
        er.a(bool1, "Result has already been consumed.");
        boolean bool2;
        if (!isReady())
        {
          Looper localLooper1 = Looper.myLooper();
          Looper localLooper2 = Looper.getMainLooper();
          bool2 = false;
          if (localLooper1 == localLooper2);
        }
        else
        {
          bool2 = true;
        }
        er.a(bool2, "await must not be called on the UI thread");
      }
      try
      {
        while (true)
        {
          if (!this.zf.await(paramLong, paramTimeUnit));
          synchronized (this.zd)
          {
            a(d(Status.zS));
            this.zl = true;
            er.a(isReady(), "Result is not ready.");
            return ds();
            bool1 = false;
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        synchronized (this.zd)
        {
          a(d(Status.zR));
          this.zl = true;
        }
      }
    }

    public final void b(A paramA)
      throws DeadObjectException
    {
      this.ze = new a.b(paramA.getLooper());
      try
      {
        a(paramA);
        return;
      }
      catch (DeadObjectException localDeadObjectException)
      {
        a(localDeadObjectException);
        throw localDeadObjectException;
      }
      catch (RemoteException localRemoteException)
      {
        a(localRemoteException);
      }
    }

    protected abstract R d(Status paramStatus);

    public final Api.b<A> dp()
    {
      return this.zc;
    }

    public int dr()
    {
      return 0;
    }

    void dt()
    {
      this.zj = true;
      this.zi = null;
      if (this.zm != null)
        this.zm.b(this);
    }

    public void du()
    {
      dv();
      this.zk = true;
    }

    public final boolean isReady()
    {
      return this.zf.getCount() == 0L;
    }

    public final void setResultCallback(ResultCallback<R> paramResultCallback)
    {
      if (!this.zj);
      for (boolean bool = true; ; bool = false)
      {
        er.a(bool, "Result has already been consumed.");
        synchronized (this.zd)
        {
          if (isReady())
          {
            this.ze.a(paramResultCallback, ds());
            return;
          }
          this.zh = paramResultCallback;
        }
      }
    }

    public final void setResultCallback(ResultCallback<R> paramResultCallback, long paramLong, TimeUnit paramTimeUnit)
    {
      if (!this.zj);
      for (boolean bool = true; ; bool = false)
      {
        er.a(bool, "Result has already been consumed.");
        synchronized (this.zd)
        {
          if (isReady())
          {
            this.ze.a(paramResultCallback, ds());
            return;
          }
          this.zh = paramResultCallback;
          this.ze.a(this, paramTimeUnit.toMillis(paramLong));
        }
      }
    }
  }

  public static class b<R extends Result> extends Handler
  {
    public b()
    {
      this(Looper.getMainLooper());
    }

    public b(Looper paramLooper)
    {
      super();
    }

    public void a(ResultCallback<R> paramResultCallback, R paramR)
    {
      sendMessage(obtainMessage(1, new Pair(paramResultCallback, paramR)));
    }

    public void a(a.a<R, ?> parama, long paramLong)
    {
      sendMessageDelayed(obtainMessage(2, parama), paramLong);
    }

    protected void b(ResultCallback<R> paramResultCallback, R paramR)
    {
      paramResultCallback.onResult(paramR);
    }

    public void dw()
    {
      removeMessages(2);
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        Log.wtf("GoogleApi", "Don't know how to handle this message.");
        return;
      case 1:
        Pair localPair = (Pair)paramMessage.obj;
        b((ResultCallback)localPair.first, (Result)localPair.second);
        return;
      case 2:
      }
      a.a locala = (a.a)paramMessage.obj;
      locala.a(locala.d(Status.zS));
    }
  }

  public static abstract interface c<R>
  {
    public abstract void b(R paramR);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.api.a
 * JD-Core Version:    0.6.0
 */