package org.apache.commons.logging;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Set;
import org.apache.commons.logging.impl.NoOpLog;

public class LogSource
{
  protected static boolean jdk14IsAvailable;
  protected static boolean log4jIsAvailable;
  protected static Constructor logImplctor;
  protected static Hashtable logs = new Hashtable();

  static
  {
    log4jIsAvailable = false;
    jdk14IsAvailable = false;
    logImplctor = null;
    try
    {
      if (Class.forName("org.apache.log4j.Logger") != null)
        log4jIsAvailable = true;
    }
    catch (Throwable localThrowable2)
    {
      try
      {
        if ((Class.forName("java.util.logging.Logger") != null) && (Class.forName("org.apache.commons.logging.impl.Jdk14Logger") != null))
        {
          jdk14IsAvailable = true;
          localObject = null;
        }
      }
      catch (Throwable localThrowable2)
      {
        try
        {
          while (true)
          {
            Object localObject = System.getProperty("org.apache.commons.logging.log");
            if (localObject == null)
            {
              String str = System.getProperty("org.apache.commons.logging.Log");
              localObject = str;
            }
            label76: if (localObject == null)
              break;
            try
            {
              setLogImplementation((String)localObject);
              return;
              log4jIsAvailable = false;
              continue;
              localThrowable1 = localThrowable1;
              log4jIsAvailable = false;
              continue;
              jdk14IsAvailable = false;
              continue;
              localThrowable2 = localThrowable2;
              jdk14IsAvailable = false;
            }
            catch (Throwable localThrowable6)
            {
              try
              {
                setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
                return;
              }
              catch (Throwable localThrowable7)
              {
                return;
              }
            }
          }
          try
          {
            if (log4jIsAvailable)
            {
              setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
              return;
            }
          }
          catch (Throwable localThrowable4)
          {
            try
            {
              setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
              return;
            }
            catch (Throwable localThrowable5)
            {
              return;
            }
            if (jdk14IsAvailable)
            {
              setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
              return;
            }
            setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
            return;
          }
        }
        catch (Throwable localThrowable3)
        {
          break label76;
        }
      }
    }
  }

  public static Log getInstance(Class paramClass)
  {
    return getInstance(paramClass.getName());
  }

  public static Log getInstance(String paramString)
  {
    Log localLog = (Log)logs.get(paramString);
    if (localLog == null)
    {
      localLog = makeNewLogInstance(paramString);
      logs.put(paramString, localLog);
    }
    return localLog;
  }

  public static String[] getLogNames()
  {
    return (String[])logs.keySet().toArray(new String[logs.size()]);
  }

  public static Log makeNewLogInstance(String paramString)
  {
    try
    {
      Object[] arrayOfObject = { paramString };
      localObject = (Log)logImplctor.newInstance(arrayOfObject);
      if (localObject == null)
        localObject = new NoOpLog(paramString);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Object localObject = null;
    }
  }

  public static void setLogImplementation(Class paramClass)
    throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException
  {
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = "".getClass();
    logImplctor = paramClass.getConstructor(arrayOfClass);
  }

  public static void setLogImplementation(String paramString)
    throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException
  {
    try
    {
      Class localClass = Class.forName(paramString);
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = "".getClass();
      logImplctor = localClass.getConstructor(arrayOfClass);
      return;
    }
    catch (Throwable localThrowable)
    {
      logImplctor = null;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.LogSource
 * JD-Core Version:    0.6.0
 */