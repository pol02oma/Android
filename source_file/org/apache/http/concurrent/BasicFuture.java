package org.apache.http.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasicFuture<T>
  implements Future<T>, Cancellable
{
  private final FutureCallback<T> callback;
  private volatile boolean cancelled;
  private volatile boolean completed;
  private volatile Exception ex;
  private volatile T result;

  public BasicFuture(FutureCallback<T> paramFutureCallback)
  {
    this.callback = paramFutureCallback;
  }

  private T getResult()
    throws ExecutionException
  {
    if (this.ex != null)
      throw new ExecutionException(this.ex);
    return this.result;
  }

  public boolean cancel()
  {
    return cancel(true);
  }

  public boolean cancel(boolean paramBoolean)
  {
    int i = 1;
    monitorenter;
    try
    {
      boolean bool = this.completed;
      if (bool)
        i = 0;
      while (true)
      {
        return i;
        this.completed = true;
        this.cancelled = true;
        if (this.callback != null)
          this.callback.cancelled();
        notifyAll();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean completed(T paramT)
  {
    int i = 1;
    monitorenter;
    try
    {
      boolean bool = this.completed;
      if (bool)
        i = 0;
      while (true)
      {
        return i;
        this.completed = true;
        this.result = paramT;
        if (this.callback != null)
          this.callback.completed(paramT);
        notifyAll();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean failed(Exception paramException)
  {
    int i = 1;
    monitorenter;
    try
    {
      boolean bool = this.completed;
      if (bool)
        i = 0;
      while (true)
      {
        return i;
        this.completed = true;
        this.ex = paramException;
        if (this.callback != null)
          this.callback.failed(paramException);
        notifyAll();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public T get()
    throws InterruptedException, ExecutionException
  {
    monitorenter;
    try
    {
      while (!this.completed)
        wait();
    }
    finally
    {
      monitorexit;
    }
    Object localObject2 = getResult();
    monitorexit;
    return localObject2;
  }

  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    monitorenter;
    long l1;
    long l2;
    long l3;
    Object localObject2;
    try
    {
      l1 = paramTimeUnit.toMillis(paramLong);
      if (l1 <= 0L)
        l2 = 0L;
      while (true)
      {
        l3 = l1;
        if (!this.completed)
          break;
        Object localObject3 = getResult();
        localObject2 = localObject3;
        return localObject2;
        l2 = System.currentTimeMillis();
      }
      if (l3 <= 0L)
        throw new TimeoutException();
    }
    finally
    {
      monitorexit;
    }
    do
    {
      wait(l3);
      if (this.completed)
      {
        localObject2 = getResult();
        break;
      }
      l3 = l1 - (System.currentTimeMillis() - l2);
    }
    while (l3 > 0L);
    throw new TimeoutException();
  }

  public boolean isCancelled()
  {
    return this.cancelled;
  }

  public boolean isDone()
  {
    return this.completed;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.concurrent.BasicFuture
 * JD-Core Version:    0.6.0
 */