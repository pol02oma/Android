package com.google.android.gms.internal;

public final class fn
  implements fl
{
  private static fn CN;

  public static fl eI()
  {
    monitorenter;
    try
    {
      if (CN == null)
        CN = new fn();
      fn localfn = CN;
      return localfn;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public long currentTimeMillis()
  {
    return System.currentTimeMillis();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.fn
 * JD-Core Version:    0.6.0
 */