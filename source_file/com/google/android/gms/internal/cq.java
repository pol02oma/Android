package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;

public class cq
{
  private final Object mg = new Object();
  private int pA = 0;
  private long pB = -1L;
  private long pC = -1L;
  private int pD = 0;
  private int pE = -1;
  private final String pz;

  public cq(String paramString)
  {
    this.pz = paramString;
  }

  public void aJ()
  {
    monitorenter;
    try
    {
      synchronized (this.mg)
      {
        this.pD = (1 + this.pD);
        monitorexit;
        return;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public void aK()
  {
    synchronized (this.mg)
    {
      this.pA = (1 + this.pA);
      return;
    }
  }

  public Bundle b(String paramString, Context paramContext)
  {
    synchronized (this.mg)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("session_id", this.pz);
      localBundle.putLong("basets", this.pC);
      localBundle.putLong("currts", this.pB);
      localBundle.putString("seq_num", paramString);
      localBundle.putInt("preqs", this.pE);
      localBundle.putInt("pclick", this.pA);
      localBundle.putInt("pimp", this.pD);
      cm localcm = new cm(paramContext);
      localBundle.putInt("gnt", localcm.oY);
      if (localcm.oX == 1)
      {
        localBundle.putString("net", "wi");
        return localBundle;
      }
      localBundle.putString("net", "ed");
    }
  }

  public void b(z paramz, long paramLong)
  {
    synchronized (this.mg)
    {
      if (this.pC == -1L)
      {
        this.pC = paramLong;
        this.pB = this.pC;
        if ((paramz.extras != null) && (paramz.extras.getInt("gw", 2) == 1))
          return;
      }
      else
      {
        this.pB = paramLong;
      }
    }
    this.pE = (1 + this.pE);
    monitorexit;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cq
 * JD-Core Version:    0.6.0
 */