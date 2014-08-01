package com.flurry.sdk;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

public class es<T extends fj>
{
  private static final String a = es.class.getSimpleName();
  private final ek<Object, T> b = new ek();
  private final HashMap<T, Object> c = new HashMap();
  private final HashMap<T, Future<?>> d = new HashMap();
  private final ThreadPoolExecutor e;

  public es(String paramString)
  {
    this(paramString, 1, 1000);
  }

  public es(String paramString, int paramInt1, int paramInt2)
  {
    this(paramString, paramInt1, paramInt1, paramInt2, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
  }

  public es(String paramString, int paramInt1, int paramInt2, long paramLong, TimeUnit paramTimeUnit, BlockingQueue<Runnable> paramBlockingQueue)
  {
    this.e = new ThreadPoolExecutor(paramInt1, paramInt2, paramLong, paramTimeUnit, paramBlockingQueue)
    {
      protected void afterExecute(Runnable paramRunnable, Throwable paramThrowable)
      {
        super.afterExecute(paramRunnable, paramThrowable);
        fj localfj;
        if ((paramRunnable instanceof es.a))
          localfj = (fj)((es.a)paramRunnable).a();
        synchronized (es.a(es.this))
        {
          while (true)
          {
            es.a(es.this).remove(localfj);
            es.a(es.this, localfj);
            new fi(localfj)
            {
              public void a()
              {
                this.a.b();
              }
            }
            .run();
            return;
            if (!(paramRunnable instanceof fj))
              break;
            localfj = (fj)paramRunnable;
          }
          ex.a(6, es.a(), "Unknown runnable class: " + paramRunnable.getClass().getName());
          return;
        }
      }

      protected void beforeExecute(Thread paramThread, Runnable paramRunnable)
      {
        super.beforeExecute(paramThread, paramRunnable);
        if ((paramRunnable instanceof es.a));
        for (fj localfj = (fj)((es.a)paramRunnable).a(); ; localfj = (fj)paramRunnable)
        {
          new fi(localfj)
          {
            public void a()
            {
              this.a.d();
            }
          }
          .run();
          return;
          if (!(paramRunnable instanceof fj))
            break;
        }
        ex.a(6, es.a(), "Unknown runnable class: " + paramRunnable.getClass().getName());
      }

      protected <V> RunnableFuture<V> newTaskFor(Runnable paramRunnable, V paramV)
      {
        es.a locala = new es.a(paramRunnable, paramV);
        synchronized (es.a(es.this))
        {
          es.a(es.this).put((fj)paramRunnable, locala);
          return locala;
        }
      }

      protected <V> RunnableFuture<V> newTaskFor(Callable<V> paramCallable)
      {
        throw new UnsupportedOperationException("Callable not supported");
      }
    };
    this.e.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy()
    {
      public void rejectedExecution(Runnable paramRunnable, ThreadPoolExecutor paramThreadPoolExecutor)
      {
        super.rejectedExecution(paramRunnable, paramThreadPoolExecutor);
        fj localfj;
        if ((paramRunnable instanceof es.a))
          localfj = (fj)((es.a)paramRunnable).a();
        synchronized (es.a(es.this))
        {
          while (true)
          {
            es.a(es.this).remove(localfj);
            es.a(es.this, localfj);
            new fi(localfj)
            {
              public void a()
              {
                this.a.c();
              }
            }
            .run();
            return;
            if (!(paramRunnable instanceof fj))
              break;
            localfj = (fj)paramRunnable;
          }
          ex.a(6, es.a(), "Unknown runnable class: " + paramRunnable.getClass().getName());
          return;
        }
      }
    });
    ff localff = new ff(paramString, 1);
    this.e.setThreadFactory(localff);
  }

  private void a(T paramT)
  {
    monitorenter;
    try
    {
      c(this.c.get(paramT), paramT);
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

  private void b(Object paramObject, T paramT)
  {
    monitorenter;
    try
    {
      this.b.a(paramObject, paramT);
      this.c.put(paramT, paramObject);
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

  private void c(Object paramObject, T paramT)
  {
    monitorenter;
    try
    {
      this.b.b(paramObject, paramT);
      this.c.remove(paramT);
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

  public long a(Object paramObject)
  {
    long l = 0L;
    monitorenter;
    if (paramObject == null);
    while (true)
    {
      monitorexit;
      return l;
      try
      {
        List localList = this.b.a(paramObject);
        if (localList == null)
          continue;
        int i = localList.size();
        l = i;
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  public void a(Object paramObject, T paramT)
  {
    monitorenter;
    if ((paramObject == null) || (paramT == null));
    while (true)
    {
      monitorexit;
      return;
      try
      {
        b(paramObject, paramT);
        this.e.submit(paramT);
      }
      finally
      {
        monitorexit;
      }
    }
  }

  static class a<V> extends FutureTask<V>
  {
    private final WeakReference<Callable<V>> a = null;
    private final WeakReference<Runnable> b;

    public a(Runnable paramRunnable, V paramV)
    {
      super(paramV);
      this.b = new WeakReference(paramRunnable);
    }

    public Runnable a()
    {
      return (Runnable)this.b.get();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.es
 * JD-Core Version:    0.6.0
 */