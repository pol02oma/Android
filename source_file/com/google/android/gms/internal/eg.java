package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.Field;

public abstract class eg
  implements SafeParcelable
{
  private static final Object Br = new Object();
  private static ClassLoader Bs = null;
  private static Integer Bt = null;
  private boolean Bu = false;

  private static boolean a(Class<?> paramClass)
  {
    try
    {
      boolean bool = "SAFE_PARCELABLE_NULL_STRING".equals(paramClass.getField("NULL").get(null));
      return bool;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      return false;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
    }
    return false;
  }

  protected static boolean ae(String paramString)
  {
    ClassLoader localClassLoader = dX();
    if (localClassLoader == null)
      return true;
    try
    {
      boolean bool = a(localClassLoader.loadClass(paramString));
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  protected static ClassLoader dX()
  {
    synchronized (Br)
    {
      ClassLoader localClassLoader = Bs;
      return localClassLoader;
    }
  }

  protected static Integer dY()
  {
    synchronized (Br)
    {
      Integer localInteger = Bt;
      return localInteger;
    }
  }

  protected boolean dZ()
  {
    return this.Bu;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.eg
 * JD-Core Version:    0.6.0
 */