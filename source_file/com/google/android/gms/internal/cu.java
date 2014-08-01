package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class cu
{
  private static final ThreadFactory pK = new ThreadFactory()
  {
    private final AtomicInteger pN = new AtomicInteger(1);

    public Thread newThread(Runnable paramRunnable)
    {
      return new Thread(paramRunnable, "AdWorker #" + this.pN.getAndIncrement());
    }
  };
  private static final ThreadPoolExecutor pL = new ThreadPoolExecutor(0, 10, 65L, TimeUnit.SECONDS, new SynchronousQueue(true), pK);

  public static void execute(Runnable paramRunnable)
  {
    try
    {
      pL.execute(new Runnable(paramRunnable)
      {
        public void run()
        {
          Process.setThreadPriority(10);
          this.pM.run();
        }
      });
      return;
    }
    catch (RejectedExecutionException localRejectedExecutionException)
    {
      da.b("Too many background threads already running. Aborting task.", localRejectedExecutionException);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cu
 * JD-Core Version:    0.6.0
 */