package com.flurry.sdk;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class cf
  implements fd
{
  protected static String d;
  Set<String> e = new HashSet();
  protected ExecutorService f;
  protected ExecutorService g;
  ci h;
  protected String i = "defaultDataKey_";

  public cf(String paramString1, String paramString2)
  {
    d = paramString2;
    fe.a().a(this);
    this.f = Executors.newSingleThreadExecutor(new ff("FlurryAgent", 1));
    this.g = Executors.newCachedThreadPool(new ff("FlurryAgent", 1));
    a(paramString1);
  }

  protected String a(String paramString1, String paramString2)
  {
    return this.i + paramString1 + "_" + paramString2;
  }

  protected void a(a parama)
  {
    this.f.submit(new Runnable(parama)
    {
      public void run()
      {
        cf.this.f();
        try
        {
          cf.this.g();
          if (this.a != null)
            this.a.a();
          return;
        }
        catch (Exception localException)
        {
          ex.a(6, cf.d, "retransmitNotSentBlocks error", localException);
        }
      }
    });
  }

  protected void a(String paramString)
  {
    this.f.submit(new Runnable(paramString)
    {
      public void run()
      {
        cf.this.f();
        try
        {
          cf.this.h = new ci(this.a);
          return;
        }
        catch (Exception localException)
        {
          ex.a(6, cf.d, "initialization of FlurryDataSenderIndex error", localException);
        }
      }
    });
  }

  protected void a(String paramString1, String paramString2, int paramInt)
  {
    this.f.submit(new Runnable(paramString1, paramString2)
    {
      public void run()
      {
        cf.this.f();
        if (!cf.this.h.a(this.a, this.b))
          ex.a(6, cf.d, "Internal error. Block wasn't deleted with id = " + this.a);
        if (!cf.this.e.remove(this.a))
          ex.a(6, cf.d, "Internal error. Block with id = " + this.a + " was not in progress state");
      }
    });
  }

  protected abstract void a(byte[] paramArrayOfByte, String paramString1, String paramString2);

  protected void a(byte[] paramArrayOfByte, String paramString1, String paramString2, a parama)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
    {
      ex.a(6, d, "Report that has to be sent is EMPTY or NULL");
      return;
    }
    c(paramArrayOfByte, paramString1, paramString2);
    a(parama);
  }

  protected void b(String paramString1, String paramString2)
  {
    this.f.submit(new Runnable(paramString1)
    {
      public void run()
      {
        cf.this.f();
        if (!cf.this.e.remove(this.a))
          ex.a(6, cf.d, "Internal error. Block with id = " + this.a + " was not in progress state");
      }
    });
  }

  public void b(boolean paramBoolean)
  {
    ex.a(4, d, "onNetworkStateChanged : isNetworkEnable = " + paramBoolean);
    if (paramBoolean)
      d();
  }

  public void b(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    a(paramArrayOfByte, paramString1, paramString2, null);
  }

  protected int c()
  {
    return this.e.size();
  }

  protected void c(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    this.f.submit(new Runnable(paramArrayOfByte, paramString1, paramString2)
    {
      public void run()
      {
        cf.this.f();
        try
        {
          cf.this.d(this.a, this.b, this.c);
          return;
        }
        catch (Exception localException)
        {
          ex.a(6, cf.d, "storeData error", localException);
        }
      }
    });
  }

  protected String d(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    String str1 = a(paramString1, paramString2);
    cg localcg = new cg();
    localcg.a(paramArrayOfByte);
    String str2 = localcg.a();
    this.h.a(localcg, str1);
    return str2;
  }

  protected void d()
  {
    a(null);
  }

  protected boolean e()
  {
    return c() <= 8;
  }

  protected void f()
  {
    long l = Thread.currentThread().getId();
    Thread.currentThread().setName("DataSender Main Single Thread , id = " + l);
  }

  protected void g()
  {
    if (!fe.a().c())
    {
      ex.a(5, d, "Reports were not sent! No Internet connection!");
      return;
    }
    label57: String str1;
    do
    {
      Iterator localIterator;
      do
      {
        List localList1 = this.h.a();
        if ((localList1 == null) || (localList1.isEmpty()))
        {
          ex.a(4, d, "No more reports to send.");
          return;
        }
        localIterator = localList1.iterator();
      }
      while (!localIterator.hasNext());
      str1 = (String)localIterator.next();
    }
    while (!e());
    List localList2 = this.h.c(str1);
    ex.a(4, d, "Number of not sent blocks = " + localList2.size());
    int j = 0;
    label128: String str2;
    if (j < localList2.size())
    {
      str2 = (String)localList2.get(j);
      if (!this.e.contains(str2))
        break label174;
    }
    while (true)
    {
      j++;
      break label128;
      break label57;
      label174: if (!e())
        break;
      byte[] arrayOfByte = new cg(str2).b();
      if ((arrayOfByte == null) || (arrayOfByte.length == 0))
      {
        ex.a(6, d, "Internal ERROR! Report is empty!");
        this.h.a(str2, str1);
        continue;
      }
      this.e.add(str2);
      a(arrayOfByte, str2, str1);
    }
  }

  public static abstract interface a
  {
    public abstract void a();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.cf
 * JD-Core Version:    0.6.0
 */