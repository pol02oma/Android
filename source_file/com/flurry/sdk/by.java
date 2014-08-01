package com.flurry.sdk;

import android.content.Context;
import java.io.File;
import java.util.List;
import java.util.Map;

public class by
{
  private static final String b = by.class.getSimpleName();
  boolean a;
  private final bz c = new bz();
  private final File d;
  private String e;

  public by()
  {
    this(eg.a().b());
  }

  public by(Context paramContext)
  {
    this.d = paramContext.getFileStreamPath(".flurryinstallreceiver.");
    ex.a(3, b, "Referrer file name if it exists:  " + this.d);
  }

  private void b()
  {
    if (this.a)
      return;
    this.a = true;
    ex.a(4, b, "Loading referrer info from file: " + this.d.getAbsolutePath());
    String str = et.b(this.d);
    ex.a(b, "Referrer file contents: " + str);
    b(str);
  }

  private void b(String paramString)
  {
    if (paramString == null)
      return;
    this.e = paramString;
  }

  private void c()
  {
    et.a(this.d, this.e);
  }

  public Map<String, List<String>> a(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      b();
      Map localMap = this.c.a(this.e);
      if (paramBoolean)
        a();
      return localMap;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a()
  {
    monitorenter;
    try
    {
      this.d.delete();
      this.e = null;
      this.a = true;
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

  public void a(String paramString)
  {
    monitorenter;
    try
    {
      this.a = true;
      b(paramString);
      c();
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.by
 * JD-Core Version:    0.6.0
 */