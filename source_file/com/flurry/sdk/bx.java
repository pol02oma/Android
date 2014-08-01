package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.flurry.android.FlurryAgent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class bx
  implements cl.a, ei.a, fd, Thread.UncaughtExceptionHandler
{
  private static final String a = bx.class.getSimpleName();
  private static bx b;
  private String c = "";
  private boolean d;
  private cl e;
  private Map<String, cl> f = new HashMap();
  private cd g;

  private bx()
  {
    fe.a().a(this);
    eq.a().a(this);
    p();
  }

  public static bx a()
  {
    if (b == null)
      b = new bx();
    return b;
  }

  private Map<String, List<String>> b(Context paramContext)
  {
    boolean bool = paramContext instanceof Activity;
    Object localObject1 = null;
    if (bool)
    {
      Bundle localBundle = ((Activity)paramContext).getIntent().getExtras();
      localObject1 = null;
      if (localBundle != null)
      {
        ex.a(3, a, "Launch Options Bundle is present " + localBundle.toString());
        HashMap localHashMap = new HashMap();
        Iterator localIterator = localBundle.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          if (str1 == null)
            continue;
          Object localObject2 = localBundle.get(str1);
          if (localObject2 != null);
          for (String str2 = localObject2.toString(); ; str2 = "null")
          {
            localHashMap.put(str1, new ArrayList(Arrays.asList(new String[] { str2 })));
            ex.a(3, a, "Launch options Key: " + str1 + ". Its value: " + str2);
            break;
          }
        }
        localObject1 = localHashMap;
      }
    }
    return localObject1;
  }

  public static int m()
  {
    return 0;
  }

  private void p()
  {
    ei localei = eh.a();
    this.d = ((Boolean)localei.a("CaptureUncaughtExceptions")).booleanValue();
    localei.a("CaptureUncaughtExceptions", this);
    ex.a(4, a, "initSettings, CrashReportingEnabled = " + this.d);
    String str = (String)localei.a("VesionName");
    localei.a("VesionName", this);
    ep.a(str);
    ex.a(4, a, "initSettings, VersionName = " + str);
  }

  private void q()
  {
    if (this.g == null)
      this.g = new cd();
  }

  public void a(Context paramContext)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.d();
  }

  public void a(Context paramContext, String paramString)
  {
    fe.a().b();
    en.a().b();
    q();
    if (this.f.isEmpty())
      en.a().c();
    cl localcl;
    if (this.f.containsKey(paramString))
      localcl = (cl)this.f.get(paramString);
    while (true)
    {
      localcl.c();
      a(localcl);
      return;
      localcl = new cl(paramContext, paramString, this);
      localcl.a(b(paramContext));
      this.f.put(paramString, localcl);
    }
  }

  void a(cl paramcl)
  {
    this.e = paramcl;
  }

  public void a(String paramString)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.a(paramString, null, false);
  }

  public void a(String paramString, Object paramObject)
  {
    if (paramString.equals("CaptureUncaughtExceptions"))
    {
      this.d = ((Boolean)paramObject).booleanValue();
      ex.a(4, a, "onSettingUpdate, CrashReportingEnabled = " + this.d);
      return;
    }
    if (paramString.equals("VesionName"))
    {
      String str = (String)paramObject;
      ep.a(str);
      ex.a(4, a, "onSettingUpdate, VersionName = " + str);
      return;
    }
    ex.a(6, a, "onSettingUpdate internal error!");
  }

  @Deprecated
  public void a(String paramString1, String paramString2, String paramString3)
  {
    StackTraceElement[] arrayOfStackTraceElement1 = Thread.currentThread().getStackTrace();
    StackTraceElement[] arrayOfStackTraceElement2;
    if ((arrayOfStackTraceElement1 != null) && (arrayOfStackTraceElement1.length > 2))
    {
      arrayOfStackTraceElement2 = new StackTraceElement[-2 + arrayOfStackTraceElement1.length];
      System.arraycopy(arrayOfStackTraceElement1, 2, arrayOfStackTraceElement2, 0, arrayOfStackTraceElement2.length);
    }
    while (true)
    {
      Throwable localThrowable = new Throwable(paramString2);
      localThrowable.setStackTrace(arrayOfStackTraceElement2);
      cl localcl = h();
      if (localcl != null)
        localcl.a(paramString1, paramString2, paramString3, localThrowable);
      return;
      arrayOfStackTraceElement2 = arrayOfStackTraceElement1;
    }
  }

  public void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.a(paramString1, paramString2, paramThrowable.getClass().getName(), paramThrowable);
  }

  public void a(String paramString, Map<String, String> paramMap)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.a(paramString, paramMap, false);
  }

  public void a(String paramString, Map<String, String> paramMap, boolean paramBoolean)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.a(paramString, paramMap, paramBoolean);
  }

  public void a(String paramString, boolean paramBoolean)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.a(paramString, null, paramBoolean);
  }

  public void a(boolean paramBoolean)
  {
    ex.a(paramBoolean);
  }

  public int b()
  {
    int i = ((Integer)eh.a().a("AgentVersion")).intValue();
    ex.a(4, a, "getAgentVersion() = " + i);
    return i;
  }

  public void b(String paramString)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.a(paramString, null);
  }

  public void b(String paramString, Map<String, String> paramMap)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.a(paramString, paramMap);
  }

  public void b(boolean paramBoolean)
  {
  }

  int c()
  {
    return 3;
  }

  public void c(String paramString)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.a(paramString, null, false);
  }

  public void c(String paramString, Map<String, String> paramMap)
  {
    cl localcl = h();
    if (localcl != null)
      localcl.a(paramString, paramMap, false);
  }

  int d()
  {
    return 4;
  }

  public void d(String paramString)
  {
    if (!this.f.containsKey(paramString))
      ex.a(6, a, "Ended session is not in the session map! Maybe it was already destroyed.");
    while (true)
    {
      if (this.f.isEmpty())
      {
        ex.a(5, a, "LocationProvider is going to be unsubscribed");
        en.a().d();
      }
      return;
      cl localcl = h();
      if ((localcl != null) && (TextUtils.equals(localcl.k(), paramString)))
        a(null);
      this.f.remove(paramString);
    }
  }

  int e()
  {
    return 0;
  }

  String f()
  {
    return this.c;
  }

  public String g()
  {
    if (f().length() > 0);
    for (String str = "."; ; str = "")
    {
      Locale localLocale = Locale.getDefault();
      Object[] arrayOfObject = new Object[6];
      arrayOfObject[0] = Integer.valueOf(b());
      arrayOfObject[1] = Integer.valueOf(c());
      arrayOfObject[2] = Integer.valueOf(d());
      arrayOfObject[3] = Integer.valueOf(e());
      arrayOfObject[4] = str;
      arrayOfObject[5] = f();
      return String.format(localLocale, "Flurry_Android_%d_%d.%d.%d%s%s", arrayOfObject);
    }
  }

  public cl h()
  {
    return this.e;
  }

  public void i()
  {
    cl localcl = h();
    if (localcl != null)
      localcl.g();
  }

  public String j()
  {
    cl localcl = h();
    String str = null;
    if (localcl != null)
      str = localcl.k();
    return str;
  }

  public String k()
  {
    cl localcl = h();
    String str = null;
    if (localcl != null)
      str = localcl.l();
    return str;
  }

  public String l()
  {
    cl localcl = h();
    String str = null;
    if (localcl != null)
      str = localcl.m();
    return str;
  }

  public Location n()
  {
    return en.a().e();
  }

  public cd o()
  {
    return this.g;
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    String str;
    if (this.d)
    {
      str = "";
      StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
      if ((arrayOfStackTraceElement == null) || (arrayOfStackTraceElement.length <= 0))
        break label159;
      StringBuilder localStringBuilder = new StringBuilder();
      if (paramThrowable.getMessage() != null)
        localStringBuilder.append(" (" + paramThrowable.getMessage() + ")\n");
      str = localStringBuilder.toString();
    }
    while (true)
    {
      FlurryAgent.onError("uncaught", str, paramThrowable);
      Iterator localIterator = new HashMap(this.f).values().iterator();
      while (localIterator.hasNext())
      {
        cl localcl = (cl)localIterator.next();
        if (localcl == null)
          continue;
        localcl.d();
        localcl.e();
      }
      label159: if (paramThrowable.getMessage() == null)
        continue;
      str = paramThrowable.getMessage();
    }
    en.a().f();
  }

  public static class a
  {
    public int a;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.bx
 * JD-Core Version:    0.6.0
 */