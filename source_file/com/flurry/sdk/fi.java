package com.flurry.sdk;

import java.io.PrintStream;
import java.io.PrintWriter;

public abstract class fi
  implements Runnable
{
  private static final String a = fi.class.getSimpleName();
  PrintStream i;
  PrintWriter j;

  public abstract void a();

  public final void run()
  {
    try
    {
      a();
      return;
    }
    catch (Throwable localThrowable)
    {
      if (this.i == null)
        break label33;
    }
    localThrowable.printStackTrace(this.i);
    while (true)
    {
      ex.a(6, a, "", localThrowable);
      return;
      label33: if (this.j != null)
      {
        localThrowable.printStackTrace(this.j);
        continue;
      }
      localThrowable.printStackTrace();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.fi
 * JD-Core Version:    0.6.0
 */