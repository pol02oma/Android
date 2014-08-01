package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.er;
import com.google.android.gms.plus.PlusOneDummyView;

public final class g
{
  private static Context PB;
  private static c Rl;

  private static c D(Context paramContext)
    throws g.a
  {
    er.f(paramContext);
    ClassLoader localClassLoader;
    if (Rl == null)
    {
      if (PB == null)
      {
        PB = GooglePlayServicesUtil.getRemoteContext(paramContext);
        if (PB == null)
          throw new a("Could not get remote context.");
      }
      localClassLoader = PB.getClassLoader();
    }
    try
    {
      Rl = c.a.az((IBinder)localClassLoader.loadClass("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl").newInstance());
      return Rl;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new a("Could not load creator class.");
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new a("Could not instantiate creator.");
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    throw new a("Could not access creator.");
  }

  public static View a(Context paramContext, int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    if (paramString == null)
      try
      {
        throw new NullPointerException();
      }
      catch (Exception localException)
      {
        return new PlusOneDummyView(paramContext, paramInt1);
      }
    View localView = (View)com.google.android.gms.dynamic.c.b(D(paramContext).a(com.google.android.gms.dynamic.c.h(paramContext), paramInt1, paramInt2, paramString, paramInt3));
    return localView;
  }

  public static class a extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.plus.internal.g
 * JD-Core Version:    0.6.0
 */