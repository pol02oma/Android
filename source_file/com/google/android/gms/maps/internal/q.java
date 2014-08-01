package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.er;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class q
{
  private static Context PB;
  private static c PC;

  public static c A(Context paramContext)
    throws GooglePlayServicesNotAvailableException
  {
    er.f(paramContext);
    if (PC != null)
      return PC;
    B(paramContext);
    PC = C(paramContext);
    try
    {
      PC.a(com.google.android.gms.dynamic.c.h(getRemoteContext(paramContext).getResources()), 4323000);
      return PC;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  private static void B(Context paramContext)
    throws GooglePlayServicesNotAvailableException
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
    switch (i)
    {
    default:
      throw new GooglePlayServicesNotAvailableException(i);
    case 0:
    }
  }

  private static c C(Context paramContext)
  {
    if (ha())
    {
      Log.i(q.class.getSimpleName(), "Making Creator statically");
      return (c)c(hb());
    }
    return c.a.U((IBinder)a(getRemoteContext(paramContext).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
  }

  private static <T> T a(ClassLoader paramClassLoader, String paramString)
  {
    try
    {
      Object localObject = c(((ClassLoader)er.f(paramClassLoader)).loadClass(paramString));
      return localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    throw new IllegalStateException("Unable to find dynamic class " + paramString);
  }

  private static <T> T c(Class<?> paramClass)
  {
    try
    {
      Object localObject = paramClass.newInstance();
      return localObject;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new IllegalStateException("Unable to instantiate the dynamic class " + paramClass.getName());
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    throw new IllegalStateException("Unable to call the default constructor of " + paramClass.getName());
  }

  private static Context getRemoteContext(Context paramContext)
  {
    if (PB == null)
    {
      if (!ha())
        break label20;
      PB = paramContext;
    }
    while (true)
    {
      return PB;
      label20: PB = GooglePlayServicesUtil.getRemoteContext(paramContext);
    }
  }

  private static boolean ha()
  {
    return false;
  }

  private static Class<?> hb()
  {
    try
    {
      if (Build.VERSION.SDK_INT < 15)
        return Class.forName("com.google.android.gms.maps.internal.CreatorImplGmm6");
      Class localClass = Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    throw new RuntimeException(localClassNotFoundException);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.internal.q
 * JD-Core Version:    0.6.0
 */