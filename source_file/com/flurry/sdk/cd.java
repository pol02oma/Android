package com.flurry.sdk;

import android.widget.Toast;
import java.util.concurrent.ExecutorService;

public class cd extends cf
  implements ei.a
{
  static String a;
  static String b = "http://data.flurry.com/aap.do";
  static String c = "https://data.flurry.com/aap.do";
  private boolean j;

  public cd()
  {
    this(null);
  }

  cd(cf.a parama)
  {
    super("Analytics", cd.class.getSimpleName());
    this.i = "AnalyticsData_";
    b();
    a(parama);
  }

  private void b()
  {
    ei localei = eh.a();
    this.j = ((Boolean)localei.a("UseHttps")).booleanValue();
    localei.a("UseHttps", this);
    ex.a(4, d, "initSettings, UseHttps = " + this.j);
    String str = (String)localei.a("ReportUrl");
    localei.a("ReportUrl", this);
    b(str);
    ex.a(4, d, "initSettings, ReportUrl = " + str);
  }

  private void b(String paramString)
  {
    if ((paramString != null) && (!paramString.endsWith(".do")))
      ex.a(5, d, "overriding analytics agent report URL without an endpoint, are you sure?");
    a = paramString;
  }

  String a()
  {
    if (a != null)
      return a;
    if (this.j)
      return c;
    return b;
  }

  public void a(String paramString, Object paramObject)
  {
    if (paramString.equals("UseHttps"))
    {
      this.j = ((Boolean)paramObject).booleanValue();
      ex.a(4, d, "onSettingUpdate, UseHttps = " + this.j);
      return;
    }
    if (paramString.equals("ReportUrl"))
    {
      String str = (String)paramObject;
      b(str);
      ex.a(4, d, "onSettingUpdate, ReportUrl = " + str);
      return;
    }
    ex.a(6, d, "onSettingUpdate internal error!");
  }

  protected void a(String paramString1, String paramString2, int paramInt)
  {
    this.f.submit(new Runnable(paramInt)
    {
      public void run()
      {
        if (this.a == 200)
        {
          cl localcl = bx.a().h();
          if (localcl != null)
            localcl.b();
        }
      }
    });
    super.a(paramString1, paramString2, paramInt);
  }

  protected void a(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    String str = a();
    ex.a(4, d, "FlurryDataSender: start upload data " + paramArrayOfByte + " with id = " + paramString1 + " to " + str);
    1 local1 = new ch()
    {
      public void a(int paramInt, String paramString1, String paramString2, String paramString3)
      {
        ex.e(cf.d, "FlurryDataSender: report " + paramString2 + " sent. HTTP response: " + paramInt + " : " + paramString1);
        if ((ex.c() <= 3) && (ex.d()))
          eg.a().a(new Runnable(paramInt)
          {
            public void run()
            {
              Toast.makeText(eg.a().b(), "SD HTTP Response Code: " + this.a, 0).show();
            }
          });
        cd.this.a(paramString2, paramString3, paramInt);
        cd.this.d();
      }

      public void a(String paramString1, String paramString2)
      {
        ex.e(cf.d, "FlurryDataSender: could not send report " + paramString1);
        cd.this.b(paramString1, paramString2);
      }
    };
    this.g.submit(new ce(str, paramString1, paramString2, paramArrayOfByte, local1));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.cd
 * JD-Core Version:    0.6.0
 */