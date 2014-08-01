package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class co
{
  private final Object mg = new Object();
  private boolean oI = false;
  private final LinkedList<a> pj;
  private final String pk;
  private final String pl;
  private long pm = -1L;
  private long pn = -1L;
  private long po = -1L;
  private long pp = 0L;
  private long pq = -1L;
  private long pr = -1L;

  public co(String paramString1, String paramString2)
  {
    this.pk = paramString1;
    this.pl = paramString2;
    this.pj = new LinkedList();
  }

  public void aJ()
  {
    synchronized (this.mg)
    {
      if ((this.pr != -1L) && (this.pn != -1L))
      {
        this.pn = SystemClock.elapsedRealtime();
        cp.aQ().aJ();
        cp.a(this);
      }
      return;
    }
  }

  public void aK()
  {
    synchronized (this.mg)
    {
      if (this.pr != -1L)
      {
        a locala = new a();
        locala.aO();
        this.pj.add(locala);
        this.pp = (1L + this.pp);
        cp.aQ().aK();
        cp.a(this);
      }
      return;
    }
  }

  public void aL()
  {
    synchronized (this.mg)
    {
      if ((this.pr != -1L) && (!this.pj.isEmpty()))
      {
        a locala = (a)this.pj.getLast();
        if (locala.aM() == -1L)
        {
          locala.aN();
          cp.a(this);
        }
      }
      return;
    }
  }

  public void f(z paramz)
  {
    synchronized (this.mg)
    {
      this.pq = SystemClock.elapsedRealtime();
      cp.aQ().b(paramz, this.pq);
      return;
    }
  }

  public void g(long paramLong)
  {
    synchronized (this.mg)
    {
      this.pr = paramLong;
      if (this.pr != -1L)
        cp.a(this);
      return;
    }
  }

  public void h(long paramLong)
  {
    synchronized (this.mg)
    {
      if (this.pr != -1L)
      {
        this.pm = paramLong;
        cp.a(this);
      }
      return;
    }
  }

  public void k(boolean paramBoolean)
  {
    synchronized (this.mg)
    {
      if (this.pr != -1L)
      {
        this.po = SystemClock.elapsedRealtime();
        if (!paramBoolean)
        {
          this.pn = this.po;
          cp.a(this);
        }
      }
      return;
    }
  }

  public void l(boolean paramBoolean)
  {
    synchronized (this.mg)
    {
      if (this.pr != -1L)
      {
        this.oI = paramBoolean;
        cp.a(this);
      }
      return;
    }
  }

  public Bundle toBundle()
  {
    Bundle localBundle;
    ArrayList localArrayList;
    synchronized (this.mg)
    {
      localBundle = new Bundle();
      localBundle.putString("seqnum", this.pk);
      localBundle.putString("slotid", this.pl);
      localBundle.putBoolean("ismediation", this.oI);
      localBundle.putLong("treq", this.pq);
      localBundle.putLong("tresponse", this.pr);
      localBundle.putLong("timp", this.pn);
      localBundle.putLong("tload", this.po);
      localBundle.putLong("pcc", this.pp);
      localBundle.putLong("tfetch", this.pm);
      localArrayList = new ArrayList();
      Iterator localIterator = this.pj.iterator();
      if (localIterator.hasNext())
        localArrayList.add(((a)localIterator.next()).toBundle());
    }
    localBundle.putParcelableArrayList("tclick", localArrayList);
    monitorexit;
    return localBundle;
  }

  private static final class a
  {
    private long ps = -1L;
    private long pt = -1L;

    public long aM()
    {
      return this.pt;
    }

    public void aN()
    {
      this.pt = SystemClock.elapsedRealtime();
    }

    public void aO()
    {
      this.ps = SystemClock.elapsedRealtime();
    }

    public Bundle toBundle()
    {
      Bundle localBundle = new Bundle();
      localBundle.putLong("topen", this.ps);
      localBundle.putLong("tclose", this.pt);
      return localBundle;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.co
 * JD-Core Version:    0.6.0
 */