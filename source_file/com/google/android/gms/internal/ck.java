package com.google.android.gms.internal;

import java.util.Map;

public final class ck
{
  private final Object mg = new Object();
  private int nX = -2;
  private dd ng;
  public final ar oA = new ar()
  {
    public void a(dd paramdd, Map<String, String> paramMap)
    {
      synchronized (ck.a(ck.this))
      {
        String str = (String)paramMap.get("url");
        if (str == null)
        {
          da.w("URL missing in loadAdUrl GMSG.");
          return;
        }
        if (str.contains("%40mediation_adapters%40"))
        {
          str = str.replaceAll("%40mediation_adapters%40", cs.b(paramdd.getContext(), (String)paramMap.get("check_adapters"), ck.b(ck.this)));
          da.v("Ad request URL modified to " + str);
        }
        ck.a(ck.this, str);
        ck.a(ck.this).notify();
        return;
      }
    }
  };
  private String ox;
  private String oy;
  public final ar oz = new ar()
  {
    public void a(dd paramdd, Map<String, String> paramMap)
    {
      synchronized (ck.a(ck.this))
      {
        String str1 = (String)paramMap.get("type");
        String str2 = (String)paramMap.get("errors");
        da.w("Invalid " + str1 + " request error: " + str2);
        ck.a(ck.this, 1);
        ck.a(ck.this).notify();
        return;
      }
    }
  };

  public ck(String paramString)
  {
    this.ox = paramString;
  }

  public String aI()
  {
    synchronized (this.mg)
    {
      while (this.oy == null)
      {
        int i = this.nX;
        if (i != -2)
          break;
        try
        {
          this.mg.wait();
        }
        catch (InterruptedException localInterruptedException)
        {
          da.w("Ad request service was interrupted.");
          return null;
        }
      }
      String str = this.oy;
      return str;
    }
  }

  public void b(dd paramdd)
  {
    synchronized (this.mg)
    {
      this.ng = paramdd;
      return;
    }
  }

  public int getErrorCode()
  {
    synchronized (this.mg)
    {
      int i = this.nX;
      return i;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ck
 * JD-Core Version:    0.6.0
 */