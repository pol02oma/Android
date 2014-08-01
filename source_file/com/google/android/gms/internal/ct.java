package com.google.android.gms.internal;

public abstract class ct
{
  private final Runnable kW = new Runnable()
  {
    public final void run()
    {
      ct.a(ct.this, Thread.currentThread());
      ct.this.aB();
    }
  };
  private volatile Thread pI;

  public abstract void aB();

  public final void cancel()
  {
    onStop();
    if (this.pI != null)
      this.pI.interrupt();
  }

  public abstract void onStop();

  public final void start()
  {
    cu.execute(this.kW);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ct
 * JD-Core Version:    0.6.0
 */