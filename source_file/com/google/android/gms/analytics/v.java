package com.google.android.gms.analytics;

import android.content.Context;

class v extends k<w>
{
  public v(Context paramContext)
  {
    super(paramContext, new a());
  }

  private static class a
    implements k.a<w>
  {
    private final w tx = new w();

    public void a(String paramString, int paramInt)
    {
      if ("ga_dispatchPeriod".equals(paramString))
      {
        this.tx.tz = paramInt;
        return;
      }
      aa.w("int configuration name not recognized:  " + paramString);
    }

    public void a(String paramString1, String paramString2)
    {
    }

    public void b(String paramString1, String paramString2)
    {
      if ("ga_appName".equals(paramString1))
      {
        this.tx.qR = paramString2;
        return;
      }
      if ("ga_appVersion".equals(paramString1))
      {
        this.tx.qS = paramString2;
        return;
      }
      if ("ga_logLevel".equals(paramString1))
      {
        this.tx.ty = paramString2;
        return;
      }
      aa.w("string configuration name not recognized:  " + paramString1);
    }

    public w bU()
    {
      return this.tx;
    }

    public void c(String paramString, boolean paramBoolean)
    {
      if ("ga_dryRun".equals(paramString))
      {
        w localw = this.tx;
        if (paramBoolean);
        for (int i = 1; ; i = 0)
        {
          localw.tA = i;
          return;
        }
      }
      aa.w("bool configuration name not recognized:  " + paramString);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.v
 * JD-Core Version:    0.6.0
 */