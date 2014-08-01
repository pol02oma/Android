package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class as extends Thread
  implements ar
{
  private static as Vc;
  private final LinkedBlockingQueue<Runnable> Vb = new LinkedBlockingQueue();
  private volatile at Vd;
  private volatile boolean mClosed = false;
  private final Context mContext;
  private volatile boolean sa = false;

  private as(Context paramContext)
  {
    super("GAThread");
    if (paramContext != null);
    for (this.mContext = paramContext.getApplicationContext(); ; this.mContext = paramContext)
    {
      start();
      return;
    }
  }

  static as H(Context paramContext)
  {
    if (Vc == null)
      Vc = new as(paramContext);
    return Vc;
  }

  private String a(Throwable paramThrowable)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream localPrintStream = new PrintStream(localByteArrayOutputStream);
    paramThrowable.printStackTrace(localPrintStream);
    localPrintStream.flush();
    return new String(localByteArrayOutputStream.toByteArray());
  }

  public void a(Runnable paramRunnable)
  {
    this.Vb.add(paramRunnable);
  }

  void b(String paramString, long paramLong)
  {
    a(new Runnable(this, paramLong, paramString)
    {
      public void run()
      {
        if (as.a(as.this) == null)
        {
          cy localcy = cy.kh();
          localcy.a(as.b(as.this), this.Ve);
          as.a(as.this, localcy.ki());
        }
        as.a(as.this).e(this.Vf, this.Vg);
      }
    });
  }

  public void bn(String paramString)
  {
    b(paramString, System.currentTimeMillis());
  }

  public void run()
  {
    while (!this.mClosed)
      try
      {
        Runnable localRunnable = (Runnable)this.Vb.take();
        if (this.sa)
          continue;
        localRunnable.run();
      }
      catch (InterruptedException localInterruptedException)
      {
        bh.u(localInterruptedException.toString());
      }
      catch (Throwable localThrowable)
      {
        bh.t("Error on GAThread: " + a(localThrowable));
        bh.t("Google Analytics is shutting down.");
        this.sa = true;
      }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.as
 * JD-Core Version:    0.6.0
 */