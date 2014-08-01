package com.google.android.gms.analytics;

import android.content.Context;
import java.util.Map;

class ai extends k<aj>
{
  public ai(Context paramContext)
  {
    super(paramContext, new a());
  }

  private static class a
    implements k.a<aj>
  {
    private final aj uJ = new aj();

    public void a(String paramString, int paramInt)
    {
      if ("ga_sessionTimeout".equals(paramString))
      {
        this.uJ.uM = paramInt;
        return;
      }
      aa.w("int configuration name not recognized:  " + paramString);
    }

    public void a(String paramString1, String paramString2)
    {
      this.uJ.uQ.put(paramString1, paramString2);
    }

    public void b(String paramString1, String paramString2)
    {
      if ("ga_trackingId".equals(paramString1))
      {
        this.uJ.uK = paramString2;
        return;
      }
      if ("ga_sampleFrequency".equals(paramString1))
        try
        {
          this.uJ.uL = Double.parseDouble(paramString2);
          return;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          aa.t("Error parsing ga_sampleFrequency value: " + paramString2);
          return;
        }
      aa.w("string configuration name not recognized:  " + paramString1);
    }

    public void c(String paramString, boolean paramBoolean)
    {
      int i = 1;
      if ("ga_autoActivityTracking".equals(paramString))
      {
        aj localaj3 = this.uJ;
        if (paramBoolean);
        while (true)
        {
          localaj3.uN = i;
          return;
          i = 0;
        }
      }
      if ("ga_anonymizeIp".equals(paramString))
      {
        aj localaj2 = this.uJ;
        if (paramBoolean);
        while (true)
        {
          localaj2.uO = i;
          return;
          i = 0;
        }
      }
      if ("ga_reportUncaughtExceptions".equals(paramString))
      {
        aj localaj1 = this.uJ;
        if (paramBoolean);
        while (true)
        {
          localaj1.uP = i;
          return;
          i = 0;
        }
      }
      aa.w("bool configuration name not recognized:  " + paramString);
    }

    public aj cA()
    {
      return this.uJ;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.ai
 * JD-Core Version:    0.6.0
 */