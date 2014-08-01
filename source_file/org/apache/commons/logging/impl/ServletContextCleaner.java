package org.apache.commons.logging.impl;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.LogFactory;

public class ServletContextCleaner
  implements ServletContextListener
{
  static Class class$java$lang$ClassLoader;
  private Class[] RELEASE_SIGNATURE;

  public ServletContextCleaner()
  {
    Class[] arrayOfClass = new Class[1];
    Class localClass;
    if (class$java$lang$ClassLoader == null)
    {
      localClass = class$("java.lang.ClassLoader");
      class$java$lang$ClassLoader = localClass;
    }
    while (true)
    {
      arrayOfClass[0] = localClass;
      this.RELEASE_SIGNATURE = arrayOfClass;
      return;
      localClass = class$java$lang$ClassLoader;
    }
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
  }

  public void contextDestroyed(ServletContextEvent paramServletContextEvent)
  {
    ClassLoader localClassLoader1 = Thread.currentThread().getContextClassLoader();
    Object[] arrayOfObject = { localClassLoader1 };
    Object localObject = localClassLoader1;
    while (localObject != null)
      try
      {
        Class localClass = ((ClassLoader)localObject).loadClass("org.apache.commons.logging.LogFactory");
        localClass.getMethod("release", this.RELEASE_SIGNATURE).invoke(null, arrayOfObject);
        ClassLoader localClassLoader2 = localClass.getClassLoader().getParent();
        localObject = localClassLoader2;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        localObject = null;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        System.err.println("LogFactory instance found which does not support release method!");
        localObject = null;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        System.err.println("LogFactory instance found which is not accessable!");
        localObject = null;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        System.err.println("LogFactory instance release method failed!");
        localObject = null;
      }
    LogFactory.release(localClassLoader1);
  }

  public void contextInitialized(ServletContextEvent paramServletContextEvent)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.impl.ServletContextCleaner
 * JD-Core Version:    0.6.0
 */