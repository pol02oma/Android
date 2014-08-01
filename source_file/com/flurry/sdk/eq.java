package com.flurry.sdk;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class eq
{
  private static eq a;
  private final Thread.UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
  private final Map<Thread.UncaughtExceptionHandler, Void> c = new WeakHashMap();

  private eq()
  {
    Thread.setDefaultUncaughtExceptionHandler(new a(null));
  }

  public static eq a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new eq();
      eq localeq = a;
      return localeq;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void a(Thread paramThread, Throwable paramThrowable)
  {
    Iterator localIterator = b().iterator();
    while (localIterator.hasNext())
    {
      Thread.UncaughtExceptionHandler localUncaughtExceptionHandler = (Thread.UncaughtExceptionHandler)localIterator.next();
      try
      {
        localUncaughtExceptionHandler.uncaughtException(paramThread, paramThrowable);
      }
      catch (Throwable localThrowable)
      {
      }
    }
  }

  private Set<Thread.UncaughtExceptionHandler> b()
  {
    synchronized (this.c)
    {
      Set localSet = this.c.keySet();
      return localSet;
    }
  }

  private void b(Thread paramThread, Throwable paramThrowable)
  {
    if (this.b != null)
      this.b.uncaughtException(paramThread, paramThrowable);
  }

  public void a(Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
  {
    synchronized (this.c)
    {
      this.c.put(paramUncaughtExceptionHandler, null);
      return;
    }
  }

  final class a
    implements Thread.UncaughtExceptionHandler
  {
    private a()
    {
    }

    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      eq.a(eq.this, paramThread, paramThrowable);
      eq.b(eq.this, paramThread, paramThrowable);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.eq
 * JD-Core Version:    0.6.0
 */