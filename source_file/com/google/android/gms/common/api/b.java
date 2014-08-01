package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.ei.b;
import com.google.android.gms.internal.er;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class b
  implements GoogleApiClient
{
  private int zA;
  private int zB = 4;
  private int zC = 0;
  private boolean zD = false;
  private int zE;
  private long zF = 5000L;
  final Handler zG;
  private final Bundle zH = new Bundle();
  private final Map<Api.b<?>, Api.a> zI = new HashMap();
  private boolean zJ;
  final Set<c> zK = new HashSet();
  final GoogleApiClient.ConnectionCallbacks zL = new GoogleApiClient.ConnectionCallbacks()
  {
    public void onConnected(Bundle paramBundle)
    {
      b.a(b.this).lock();
      try
      {
        if (b.b(b.this) == 1)
        {
          if (paramBundle != null)
            b.c(b.this).putAll(paramBundle);
          b.d(b.this);
        }
        return;
      }
      finally
      {
        b.a(b.this).unlock();
      }
      throw localObject;
    }

    public void onConnectionSuspended(int paramInt)
    {
      b.a(b.this).lock();
      while (true)
      {
        try
        {
          b.a(b.this, paramInt);
          switch (paramInt)
          {
          default:
            return;
          case 2:
            b.this.connect();
            continue;
          case 1:
          }
        }
        finally
        {
          b.a(b.this).unlock();
        }
        boolean bool = b.e(b.this);
        if (bool)
        {
          b.a(b.this).unlock();
          return;
        }
        b.b(b.this, 2);
        b.this.zG.sendMessageDelayed(b.this.zG.obtainMessage(1), b.f(b.this));
      }
    }
  };
  private final ei.b zM = new ei.b()
  {
    public Bundle cY()
    {
      return null;
    }

    public boolean dC()
    {
      return b.g(b.this);
    }

    public boolean isConnected()
    {
      return b.this.isConnected();
    }
  };
  private final a zm = new a()
  {
    public void b(b.c paramc)
    {
      b.a(b.this).lock();
      try
      {
        b.this.zK.remove(paramc);
        return;
      }
      finally
      {
        b.a(b.this).unlock();
      }
      throw localObject;
    }
  };
  private final Lock zv = new ReentrantLock();
  private final Condition zw = this.zv.newCondition();
  private final ei zx = new ei(paramContext, paramLooper, this.zM);
  final Queue<c<?>> zy = new LinkedList();
  private ConnectionResult zz;

  public b(Context paramContext, Looper paramLooper, ee paramee, Map<Api, GoogleApiClient.ApiOptions> paramMap, Set<GoogleApiClient.ConnectionCallbacks> paramSet, Set<GoogleApiClient.OnConnectionFailedListener> paramSet1)
  {
    this.zG = new b(paramLooper);
    Iterator localIterator1 = paramSet.iterator();
    while (localIterator1.hasNext())
    {
      GoogleApiClient.ConnectionCallbacks localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)localIterator1.next();
      this.zx.registerConnectionCallbacks(localConnectionCallbacks);
    }
    Iterator localIterator2 = paramSet1.iterator();
    while (localIterator2.hasNext())
    {
      GoogleApiClient.OnConnectionFailedListener localOnConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener)localIterator2.next();
      this.zx.registerConnectionFailedListener(localOnConnectionFailedListener);
    }
    Iterator localIterator3 = paramMap.keySet().iterator();
    while (localIterator3.hasNext())
    {
      Api localApi = (Api)localIterator3.next();
      Api.b localb = localApi.dp();
      GoogleApiClient.ApiOptions localApiOptions = (GoogleApiClient.ApiOptions)paramMap.get(localApi);
      this.zI.put(localb, localb.b(paramContext, paramLooper, paramee, localApiOptions, this.zL, new GoogleApiClient.OnConnectionFailedListener(localb)
      {
        public void onConnectionFailed(ConnectionResult paramConnectionResult)
        {
          b.a(b.this).lock();
          try
          {
            if ((b.h(b.this) == null) || (this.zO.getPriority() < b.i(b.this)))
            {
              b.a(b.this, paramConnectionResult);
              b.c(b.this, this.zO.getPriority());
            }
            b.d(b.this);
            return;
          }
          finally
          {
            b.a(b.this).unlock();
          }
          throw localObject;
        }
      }));
    }
  }

  private void G(int paramInt)
  {
    this.zv.lock();
    try
    {
      if (this.zB == 3)
        break label317;
      if (paramInt != -1)
        break label134;
      if (isConnecting())
      {
        Iterator localIterator3 = this.zy.iterator();
        while (localIterator3.hasNext())
        {
          if (((c)localIterator3.next()).dr() == 1)
            continue;
          localIterator3.remove();
        }
      }
    }
    finally
    {
      this.zv.unlock();
    }
    this.zy.clear();
    if ((this.zz == null) && (!this.zy.isEmpty()))
    {
      this.zD = true;
      this.zv.unlock();
      return;
    }
    label134: boolean bool1 = isConnecting();
    boolean bool2 = isConnected();
    this.zB = 3;
    if (bool1)
    {
      if (paramInt == -1)
        this.zz = null;
      this.zw.signalAll();
    }
    Iterator localIterator1 = this.zK.iterator();
    while (localIterator1.hasNext())
      ((c)localIterator1.next()).du();
    this.zK.clear();
    this.zJ = false;
    Iterator localIterator2 = this.zI.values().iterator();
    while (localIterator2.hasNext())
    {
      Api.a locala = (Api.a)localIterator2.next();
      if (!locala.isConnected())
        continue;
      locala.disconnect();
    }
    this.zJ = true;
    this.zB = 4;
    if (bool2)
    {
      if (paramInt != -1)
        this.zx.P(paramInt);
      this.zJ = false;
    }
    label317: this.zv.unlock();
  }

  private <A extends Api.a> void a(c<A> paramc)
    throws DeadObjectException
  {
    this.zv.lock();
    try
    {
      er.a(isConnected(), "GoogleApiClient is not connected yet.");
      if (paramc.dp() != null);
      for (boolean bool = true; ; bool = false)
      {
        er.a(bool, "This task can not be executed or enqueued (it's probably a Batch or malformed)");
        if ((paramc instanceof Releasable))
        {
          this.zK.add(paramc);
          paramc.a(this.zm);
        }
        paramc.b(a(paramc.dp()));
        return;
      }
    }
    finally
    {
      this.zv.unlock();
    }
    throw localObject;
  }

  private boolean dA()
  {
    this.zv.lock();
    try
    {
      int i = this.zC;
      if (i != 0)
      {
        j = 1;
        return j;
      }
      int j = 0;
    }
    finally
    {
      this.zv.unlock();
    }
  }

  private void dB()
  {
    this.zv.lock();
    try
    {
      this.zC = 0;
      this.zG.removeMessages(1);
      return;
    }
    finally
    {
      this.zv.unlock();
    }
    throw localObject;
  }

  private void dy()
  {
    this.zv.lock();
    while (true)
    {
      try
      {
        this.zE = (-1 + this.zE);
        if (this.zE != 0)
          continue;
        if (this.zz != null)
        {
          this.zD = false;
          G(3);
          if (!dA())
            continue;
          this.zC = (-1 + this.zC);
          if (!dA())
            continue;
          this.zG.sendMessageDelayed(this.zG.obtainMessage(1), this.zF);
          this.zJ = false;
          return;
          this.zx.a(this.zz);
          continue;
        }
      }
      finally
      {
        this.zv.unlock();
      }
      this.zB = 2;
      dB();
      this.zw.signalAll();
      dz();
      if (!this.zD)
        break;
      this.zD = false;
      G(-1);
    }
    if (this.zH.isEmpty());
    for (Bundle localBundle = null; ; localBundle = this.zH)
    {
      this.zx.b(localBundle);
      break;
    }
  }

  private void dz()
  {
    er.a(isConnected(), "GoogleApiClient is not connected yet.");
    this.zv.lock();
    try
    {
      while (true)
      {
        boolean bool = this.zy.isEmpty();
        if (bool)
          break;
        try
        {
          a((c)this.zy.remove());
        }
        catch (DeadObjectException localDeadObjectException)
        {
          Log.w("GoogleApiClientImpl", "Service died while flushing queue", localDeadObjectException);
        }
      }
    }
    finally
    {
      this.zv.unlock();
    }
    this.zv.unlock();
  }

  public <C extends Api.a> C a(Api.b<C> paramb)
  {
    Api.a locala = (Api.a)this.zI.get(paramb);
    er.b(locala, "Appropriate Api was not requested.");
    return locala;
  }

  public <A extends Api.a, T extends a.a<? extends Result, A>> T a(T paramT)
  {
    this.zv.lock();
    try
    {
      if (isConnected())
        b(paramT);
      while (true)
      {
        return paramT;
        this.zy.add(paramT);
      }
    }
    finally
    {
      this.zv.unlock();
    }
    throw localObject;
  }

  public <A extends Api.a, T extends a.a<? extends Result, A>> T b(T paramT)
  {
    er.a(isConnected(), "GoogleApiClient is not connected yet.");
    dz();
    try
    {
      a(paramT);
      return paramT;
    }
    catch (DeadObjectException localDeadObjectException)
    {
      G(1);
    }
    return paramT;
  }

  public ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit)
  {
    boolean bool1;
    if (Looper.myLooper() != Looper.getMainLooper())
      bool1 = true;
    while (true)
    {
      er.a(bool1, "blockingConnect must not be called on the UI thread");
      this.zv.lock();
      try
      {
        connect();
        long l = paramTimeUnit.toNanos(paramLong);
        label40: boolean bool2 = isConnecting();
        if (bool2)
          try
          {
            l = this.zw.awaitNanos(l);
            if (l > 0L)
              break label40;
            ConnectionResult localConnectionResult5 = new ConnectionResult(14, null);
            this.zv.unlock();
            return localConnectionResult5;
            bool1 = false;
          }
          catch (InterruptedException localInterruptedException)
          {
            Thread.currentThread().interrupt();
            ConnectionResult localConnectionResult4 = new ConnectionResult(15, null);
            this.zv.unlock();
            return localConnectionResult4;
          }
        if (isConnected())
        {
          ConnectionResult localConnectionResult3 = ConnectionResult.yI;
          return localConnectionResult3;
        }
        if (this.zz != null)
        {
          ConnectionResult localConnectionResult2 = this.zz;
          return localConnectionResult2;
        }
        ConnectionResult localConnectionResult1 = new ConnectionResult(13, null);
        return localConnectionResult1;
      }
      finally
      {
        this.zv.unlock();
      }
    }
    throw localObject;
  }

  public void connect()
  {
    this.zv.lock();
    try
    {
      this.zD = false;
      if (!isConnected())
      {
        boolean bool = isConnecting();
        if (!bool);
      }
      else
      {
        return;
      }
      this.zJ = true;
      this.zz = null;
      this.zB = 1;
      this.zH.clear();
      this.zE = this.zI.size();
      Iterator localIterator = this.zI.values().iterator();
      while (localIterator.hasNext())
        ((Api.a)localIterator.next()).connect();
    }
    finally
    {
      this.zv.unlock();
    }
    this.zv.unlock();
  }

  public void disconnect()
  {
    dB();
    G(-1);
  }

  public boolean isConnected()
  {
    this.zv.lock();
    try
    {
      int i = this.zB;
      if (i == 2)
      {
        j = 1;
        return j;
      }
      int j = 0;
    }
    finally
    {
      this.zv.unlock();
    }
  }

  public boolean isConnecting()
  {
    int i = 1;
    this.zv.lock();
    try
    {
      int j = this.zB;
      if (j == i)
        return i;
      i = 0;
    }
    finally
    {
      this.zv.unlock();
    }
  }

  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.zx.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }

  public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.zx.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }

  public void reconnect()
  {
    disconnect();
    connect();
  }

  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zx.registerConnectionCallbacks(paramConnectionCallbacks);
  }

  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zx.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }

  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zx.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }

  public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zx.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }

  static abstract interface a
  {
    public abstract void b(b.c paramc);
  }

  class b extends Handler
  {
    b(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1)
      {
        b.a(b.this).lock();
        try
        {
          if ((!b.this.isConnected()) && (!b.this.isConnecting()))
            b.this.connect();
          return;
        }
        finally
        {
          b.a(b.this).unlock();
        }
      }
      Log.wtf("GoogleApiClientImpl", "Don't know how to handle this message.");
    }
  }

  static abstract interface c<A extends Api.a>
  {
    public abstract void a(b.a parama);

    public abstract void b(A paramA)
      throws DeadObjectException;

    public abstract Api.b<A> dp();

    public abstract int dr();

    public abstract void du();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.api.b
 * JD-Core Version:    0.6.0
 */