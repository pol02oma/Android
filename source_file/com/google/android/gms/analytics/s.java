package com.google.android.gms.analytics;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.di;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

class s
  implements ag, c.b, c.c
{
  private final Context mContext;
  private final GoogleAnalytics rA;
  private final Queue<d> rB = new ConcurrentLinkedQueue();
  private volatile int rC;
  private volatile Timer rD;
  private volatile Timer rE;
  private volatile Timer rF;
  private boolean rG;
  private boolean rH;
  private boolean rI;
  private i rJ;
  private long rK = 300000L;
  private d rj;
  private final f rk;
  private boolean rm;
  private volatile long rw;
  private volatile a rx;
  private volatile b ry;
  private d rz;

  s(Context paramContext, f paramf)
  {
    this(paramContext, paramf, null, GoogleAnalytics.getInstance(paramContext));
  }

  s(Context paramContext, f paramf, d paramd, GoogleAnalytics paramGoogleAnalytics)
  {
    this.rz = paramd;
    this.mContext = paramContext;
    this.rk = paramf;
    this.rA = paramGoogleAnalytics;
    this.rJ = new i()
    {
      public long currentTimeMillis()
      {
        return System.currentTimeMillis();
      }
    };
    this.rC = 0;
    this.rx = a.rT;
  }

  private Timer a(Timer paramTimer)
  {
    if (paramTimer != null)
      paramTimer.cancel();
    return null;
  }

  private void aD()
  {
    monitorenter;
    try
    {
      if ((this.ry != null) && (this.rx == a.rO))
      {
        this.rx = a.rS;
        this.ry.disconnect();
      }
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

  private void bH()
  {
    this.rD = a(this.rD);
    this.rE = a(this.rE);
    this.rF = a(this.rF);
  }

  private void bJ()
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (Thread.currentThread().equals(this.rk.getThread()))
          continue;
        this.rk.bs().add(new Runnable()
        {
          public void run()
          {
            s.a(s.this);
          }
        });
        return;
        if (!this.rG)
          continue;
        bk();
        switch (3.rM[this.rx.ordinal()])
        {
        case 1:
          if (!this.rB.isEmpty())
          {
            d locald2 = (d)this.rB.poll();
            aa.v("Sending hit to store  " + locald2);
            this.rj.a(locald2.bO(), locald2.bP(), locald2.getPath(), locald2.bQ());
            continue;
          }
        case 2:
        case 6:
        case 3:
        case 4:
        case 5:
        }
      }
      finally
      {
        monitorexit;
      }
      if (!this.rm)
        continue;
      bK();
      continue;
      if (!this.rB.isEmpty())
      {
        d locald1 = (d)this.rB.peek();
        aa.v("Sending hit to service   " + locald1);
        if (!this.rA.isDryRunEnabled())
          this.ry.a(locald1.bO(), locald1.bP(), locald1.getPath(), locald1.bQ());
        while (true)
        {
          this.rB.poll();
          break;
          aa.v("Dry run enabled. Hit not actually sent to service.");
        }
      }
      this.rw = this.rJ.currentTimeMillis();
      continue;
      aa.v("Need to reconnect");
      if (this.rB.isEmpty())
        continue;
      bM();
      continue;
    }
  }

  private void bK()
  {
    this.rj.bp();
    this.rm = false;
  }

  private void bL()
  {
    monitorenter;
    while (true)
    {
      try
      {
        a locala1 = this.rx;
        a locala2 = a.rP;
        if (locala1 == locala2)
          return;
        bH();
        aa.v("falling back to local store");
        if (this.rz != null)
        {
          this.rj = this.rz;
          this.rx = a.rP;
          bJ();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      r localr = r.bB();
      localr.a(this.mContext, this.rk);
      this.rj = localr.bE();
    }
  }

  private void bM()
  {
    monitorenter;
    while (true)
    {
      try
      {
        if ((!this.rI) && (this.ry != null))
        {
          a locala1 = this.rx;
          a locala2 = a.rP;
          if (locala1 != locala2)
            try
            {
              this.rC = (1 + this.rC);
              a(this.rE);
              this.rx = a.rN;
              this.rE = new Timer("Failed Connect");
              this.rE.schedule(new c(null), 3000L);
              aa.v("connecting to Analytics service");
              this.ry.connect();
              monitorexit;
              return;
            }
            catch (SecurityException localSecurityException)
            {
              aa.w("security exception on connectToService");
              bL();
              continue;
            }
        }
      }
      finally
      {
        monitorexit;
      }
      aa.w("client not initialized.");
      bL();
    }
  }

  private void bN()
  {
    this.rD = a(this.rD);
    this.rD = new Timer("Service Reconnect");
    this.rD.schedule(new e(null), 5000L);
  }

  public void a(int paramInt, Intent paramIntent)
  {
    monitorenter;
    try
    {
      this.rx = a.rR;
      if (this.rC < 2)
      {
        aa.w("Service unavailable (code=" + paramInt + "), will retry.");
        bN();
      }
      while (true)
      {
        return;
        aa.w("Service unavailable (code=" + paramInt + "), using local store.");
        bL();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void b(Map<String, String> paramMap, long paramLong, String paramString, List<di> paramList)
  {
    aa.v("putHit called");
    this.rB.add(new d(paramMap, paramLong, paramString, paramList));
    bJ();
  }

  public void bI()
  {
    if (this.ry != null)
      return;
    this.ry = new c(this.mContext, this, this);
    bM();
  }

  public void bk()
  {
    aa.v("clearHits called");
    this.rB.clear();
    switch (3.rM[this.rx.ordinal()])
    {
    default:
      this.rG = true;
      return;
    case 1:
      this.rj.i(0L);
      this.rG = false;
      return;
    case 2:
    }
    this.ry.bk();
    this.rG = false;
  }

  public void bp()
  {
    switch (3.rM[this.rx.ordinal()])
    {
    default:
      this.rm = true;
    case 2:
      return;
    case 1:
    }
    bK();
  }

  public void br()
  {
    monitorenter;
    while (true)
    {
      try
      {
        boolean bool = this.rI;
        if (bool)
          return;
        aa.v("setForceLocalDispatch called.");
        this.rI = true;
        switch (3.rM[this.rx.ordinal()])
        {
        case 1:
        case 4:
        case 5:
        case 6:
        case 2:
          aD();
          continue;
        case 3:
        }
      }
      finally
      {
        monitorexit;
      }
      this.rH = true;
      continue;
    }
  }

  public void onConnected()
  {
    monitorenter;
    try
    {
      this.rE = a(this.rE);
      this.rC = 0;
      aa.v("Connected to service");
      this.rx = a.rO;
      if (this.rH)
      {
        aD();
        this.rH = false;
      }
      while (true)
      {
        return;
        bJ();
        this.rF = a(this.rF);
        this.rF = new Timer("disconnect check");
        this.rF.schedule(new b(null), this.rK);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void onDisconnected()
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (this.rx != a.rS)
          continue;
        aa.v("Disconnected from service");
        bH();
        this.rx = a.rT;
        return;
        aa.v("Unexpected disconnect.");
        this.rx = a.rR;
        if (this.rC < 2)
        {
          bN();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      bL();
    }
  }

  private static enum a
  {
    static
    {
      a[] arrayOfa = new a[7];
      arrayOfa[0] = rN;
      arrayOfa[1] = rO;
      arrayOfa[2] = rP;
      arrayOfa[3] = rQ;
      arrayOfa[4] = rR;
      arrayOfa[5] = rS;
      arrayOfa[6] = rT;
      rU = arrayOfa;
    }
  }

  private class b extends TimerTask
  {
    private b()
    {
    }

    public void run()
    {
      if ((s.b(s.this) == s.a.rO) && (s.e(s.this).isEmpty()) && (s.f(s.this) + s.g(s.this) < s.h(s.this).currentTimeMillis()))
      {
        aa.v("Disconnecting due to inactivity");
        s.i(s.this);
        return;
      }
      s.j(s.this).schedule(new b(s.this), s.g(s.this));
    }
  }

  private class c extends TimerTask
  {
    private c()
    {
    }

    public void run()
    {
      if (s.b(s.this) == s.a.rN)
        s.c(s.this);
    }
  }

  private static class d
  {
    private final Map<String, String> rV;
    private final long rW;
    private final String rX;
    private final List<di> rY;

    public d(Map<String, String> paramMap, long paramLong, String paramString, List<di> paramList)
    {
      this.rV = paramMap;
      this.rW = paramLong;
      this.rX = paramString;
      this.rY = paramList;
    }

    public Map<String, String> bO()
    {
      return this.rV;
    }

    public long bP()
    {
      return this.rW;
    }

    public List<di> bQ()
    {
      return this.rY;
    }

    public String getPath()
    {
      return this.rX;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("PATH: ");
      localStringBuilder.append(this.rX);
      if (this.rV != null)
      {
        localStringBuilder.append("  PARAMS: ");
        Iterator localIterator = this.rV.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          localStringBuilder.append((String)localEntry.getKey());
          localStringBuilder.append("=");
          localStringBuilder.append((String)localEntry.getValue());
          localStringBuilder.append(",  ");
        }
      }
      return localStringBuilder.toString();
    }
  }

  private class e extends TimerTask
  {
    private e()
    {
    }

    public void run()
    {
      s.d(s.this);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.s
 * JD-Core Version:    0.6.0
 */