package org.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.http.annotation.Immutable;

@Immutable
public class JdkIdn
  implements Idn
{
  private final Method toUnicode;

  public JdkIdn()
    throws ClassNotFoundException
  {
    Class localClass = Class.forName("java.net.IDN");
    try
    {
      this.toUnicode = localClass.getMethod("toUnicode", new Class[] { String.class });
      return;
    }
    catch (SecurityException localSecurityException)
    {
      throw new IllegalStateException(localSecurityException.getMessage(), localSecurityException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
    throw new IllegalStateException(localNoSuchMethodException.getMessage(), localNoSuchMethodException);
  }

  public String toUnicode(String paramString)
  {
    Throwable localThrowable;
    try
    {
      String str = (String)this.toUnicode.invoke(null, new Object[] { paramString });
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalStateException(localIllegalAccessException.getMessage(), localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localThrowable = localInvocationTargetException.getCause();
    }
    throw new RuntimeException(localThrowable.getMessage(), localThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.utils.JdkIdn
 * JD-Core Version:    0.6.0
 */