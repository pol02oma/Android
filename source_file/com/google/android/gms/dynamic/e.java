package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.er;

public abstract class e<T>
{
  private final String FC;
  private T FD;

  protected e(String paramString)
  {
    this.FC = paramString;
  }

  protected abstract T d(IBinder paramIBinder);

  protected final T z(Context paramContext)
    throws e.a
  {
    ClassLoader localClassLoader;
    if (this.FD == null)
    {
      er.f(paramContext);
      Context localContext = GooglePlayServicesUtil.getRemoteContext(paramContext);
      if (localContext == null)
        throw new a("Could not get remote context.");
      localClassLoader = localContext.getClassLoader();
    }
    try
    {
      this.FD = d((IBinder)localClassLoader.loadClass(this.FC).newInstance());
      return this.FD;
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

  public static class a extends Exception
  {
    public a(String paramString)
    {
      super();
    }

    public a(String paramString, Throwable paramThrowable)
    {
      super(paramThrowable);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.dynamic.e
 * JD-Core Version:    0.6.0
 */