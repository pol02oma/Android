package org.apache.commons.logging.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;

public class SimpleLog
  implements Log, Serializable
{
  protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
  public static final int LOG_LEVEL_ALL = 0;
  public static final int LOG_LEVEL_DEBUG = 2;
  public static final int LOG_LEVEL_ERROR = 5;
  public static final int LOG_LEVEL_FATAL = 6;
  public static final int LOG_LEVEL_INFO = 3;
  public static final int LOG_LEVEL_OFF = 7;
  public static final int LOG_LEVEL_TRACE = 1;
  public static final int LOG_LEVEL_WARN = 4;
  static Class class$java$lang$Thread;
  static Class class$org$apache$commons$logging$impl$SimpleLog;
  protected static DateFormat dateFormatter;
  protected static String dateTimeFormat;
  protected static boolean showDateTime = false;
  protected static boolean showLogName = false;
  protected static boolean showShortName = false;
  protected static final Properties simpleLogProps = new Properties();
  protected static final String systemPrefix = "org.apache.commons.logging.simplelog.";
  protected int currentLogLevel;
  protected String logName = null;
  private String shortLogName = null;

  static
  {
    showLogName = false;
    showShortName = true;
    showDateTime = false;
    dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
    dateFormatter = null;
    InputStream localInputStream = getResourceAsStream("simplelog.properties");
    if (localInputStream != null);
    try
    {
      simpleLogProps.load(localInputStream);
      localInputStream.close();
      label52: showLogName = getBooleanProperty("org.apache.commons.logging.simplelog.showlogname", showLogName);
      showShortName = getBooleanProperty("org.apache.commons.logging.simplelog.showShortLogname", showShortName);
      showDateTime = getBooleanProperty("org.apache.commons.logging.simplelog.showdatetime", showDateTime);
      if (showDateTime)
        dateTimeFormat = getStringProperty("org.apache.commons.logging.simplelog.dateTimeFormat", dateTimeFormat);
      try
      {
        dateFormatter = new SimpleDateFormat(dateTimeFormat);
        return;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
        dateFormatter = new SimpleDateFormat(dateTimeFormat);
        return;
      }
    }
    catch (IOException localIOException)
    {
      break label52;
    }
  }

  public SimpleLog(String paramString)
  {
    this.logName = paramString;
    setLevel(3);
    String str = getStringProperty("org.apache.commons.logging.simplelog.log." + this.logName);
    for (int i = String.valueOf(paramString).lastIndexOf("."); (str == null) && (i > -1); i = String.valueOf(paramString).lastIndexOf("."))
    {
      paramString = paramString.substring(0, i);
      str = getStringProperty("org.apache.commons.logging.simplelog.log." + paramString);
    }
    if (str == null)
      str = getStringProperty("org.apache.commons.logging.simplelog.defaultlog");
    if ("all".equalsIgnoreCase(str))
      setLevel(0);
    do
    {
      return;
      if ("trace".equalsIgnoreCase(str))
      {
        setLevel(1);
        return;
      }
      if ("debug".equalsIgnoreCase(str))
      {
        setLevel(2);
        return;
      }
      if ("info".equalsIgnoreCase(str))
      {
        setLevel(3);
        return;
      }
      if ("warn".equalsIgnoreCase(str))
      {
        setLevel(4);
        return;
      }
      if ("error".equalsIgnoreCase(str))
      {
        setLevel(5);
        return;
      }
      if (!"fatal".equalsIgnoreCase(str))
        continue;
      setLevel(6);
      return;
    }
    while (!"off".equalsIgnoreCase(str));
    setLevel(7);
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

  private static boolean getBooleanProperty(String paramString, boolean paramBoolean)
  {
    String str = getStringProperty(paramString);
    if (str == null)
      return paramBoolean;
    return "true".equalsIgnoreCase(str);
  }

  private static ClassLoader getContextClassLoader()
  {
    ClassLoader localClassLoader = null;
    if (0 == 0);
    try
    {
      if (class$java$lang$Thread == null)
      {
        Class localClass3 = class$("java.lang.Thread");
        class$java$lang$Thread = localClass3;
        localClass2 = localClass3;
        localMethod = localClass2.getMethod("getContextClassLoader", (Class[])null);
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      try
      {
        Class localClass2;
        Method localMethod;
        localClassLoader = (ClassLoader)localMethod.invoke(Thread.currentThread(), (Class[])null);
        if (localClassLoader == null)
        {
          if (class$org$apache$commons$logging$impl$SimpleLog == null)
          {
            localClass1 = class$("org.apache.commons.logging.impl.SimpleLog");
            class$org$apache$commons$logging$impl$SimpleLog = localClass1;
            localClassLoader = localClass1.getClassLoader();
          }
        }
        else
        {
          return localClassLoader;
          localClass2 = class$java$lang$Thread;
        }
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        while (true)
        {
          boolean bool = localInvocationTargetException.getTargetException() instanceof SecurityException;
          localClassLoader = null;
          if (bool)
            continue;
          throw new LogConfigurationException("Unexpected InvocationTargetException", localInvocationTargetException.getTargetException());
          localNoSuchMethodException = localNoSuchMethodException;
          localClassLoader = null;
          continue;
          Class localClass1 = class$org$apache$commons$logging$impl$SimpleLog;
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        while (true)
          localClassLoader = null;
      }
    }
  }

  private static InputStream getResourceAsStream(String paramString)
  {
    return (InputStream)AccessController.doPrivileged(new SimpleLog.1(paramString));
  }

  private static String getStringProperty(String paramString)
  {
    try
    {
      String str2 = System.getProperty(paramString);
      str1 = str2;
      if (str1 == null)
        str1 = simpleLogProps.getProperty(paramString);
      return str1;
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        String str1 = null;
    }
  }

  private static String getStringProperty(String paramString1, String paramString2)
  {
    String str = getStringProperty(paramString1);
    if (str == null)
      return paramString2;
    return str;
  }

  public final void debug(Object paramObject)
  {
    if (isLevelEnabled(2))
      log(2, paramObject, null);
  }

  public final void debug(Object paramObject, Throwable paramThrowable)
  {
    if (isLevelEnabled(2))
      log(2, paramObject, paramThrowable);
  }

  public final void error(Object paramObject)
  {
    if (isLevelEnabled(5))
      log(5, paramObject, null);
  }

  public final void error(Object paramObject, Throwable paramThrowable)
  {
    if (isLevelEnabled(5))
      log(5, paramObject, paramThrowable);
  }

  public final void fatal(Object paramObject)
  {
    if (isLevelEnabled(6))
      log(6, paramObject, null);
  }

  public final void fatal(Object paramObject, Throwable paramThrowable)
  {
    if (isLevelEnabled(6))
      log(6, paramObject, paramThrowable);
  }

  public int getLevel()
  {
    return this.currentLogLevel;
  }

  public final void info(Object paramObject)
  {
    if (isLevelEnabled(3))
      log(3, paramObject, null);
  }

  public final void info(Object paramObject, Throwable paramThrowable)
  {
    if (isLevelEnabled(3))
      log(3, paramObject, paramThrowable);
  }

  public final boolean isDebugEnabled()
  {
    return isLevelEnabled(2);
  }

  public final boolean isErrorEnabled()
  {
    return isLevelEnabled(5);
  }

  public final boolean isFatalEnabled()
  {
    return isLevelEnabled(6);
  }

  public final boolean isInfoEnabled()
  {
    return isLevelEnabled(3);
  }

  protected boolean isLevelEnabled(int paramInt)
  {
    return paramInt >= this.currentLogLevel;
  }

  public final boolean isTraceEnabled()
  {
    return isLevelEnabled(1);
  }

  public final boolean isWarnEnabled()
  {
    return isLevelEnabled(4);
  }

  protected void log(int paramInt, Object paramObject, Throwable paramThrowable)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Date localDate;
    if (showDateTime)
      localDate = new Date();
    while (true)
    {
      synchronized (dateFormatter)
      {
        String str = dateFormatter.format(localDate);
        localStringBuffer.append(str);
        localStringBuffer.append(" ");
        switch (paramInt)
        {
        default:
          if (!showShortName)
            break label351;
          if (this.shortLogName != null)
            continue;
          this.shortLogName = this.logName.substring(1 + this.logName.lastIndexOf("."));
          this.shortLogName = this.shortLogName.substring(1 + this.shortLogName.lastIndexOf("/"));
          localStringBuffer.append(String.valueOf(this.shortLogName)).append(" - ");
          localStringBuffer.append(String.valueOf(paramObject));
          if (paramThrowable == null)
            continue;
          localStringBuffer.append(" <");
          localStringBuffer.append(paramThrowable.toString());
          localStringBuffer.append(">");
          StringWriter localStringWriter = new StringWriter(1024);
          PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
          paramThrowable.printStackTrace(localPrintWriter);
          localPrintWriter.close();
          localStringBuffer.append(localStringWriter.toString());
          write(localStringBuffer);
          return;
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        }
      }
      localStringBuffer.append("[TRACE] ");
      continue;
      localStringBuffer.append("[DEBUG] ");
      continue;
      localStringBuffer.append("[INFO] ");
      continue;
      localStringBuffer.append("[WARN] ");
      continue;
      localStringBuffer.append("[ERROR] ");
      continue;
      localStringBuffer.append("[FATAL] ");
      continue;
      label351: if (!showLogName)
        continue;
      localStringBuffer.append(String.valueOf(this.logName)).append(" - ");
    }
  }

  public void setLevel(int paramInt)
  {
    this.currentLogLevel = paramInt;
  }

  public final void trace(Object paramObject)
  {
    if (isLevelEnabled(1))
      log(1, paramObject, null);
  }

  public final void trace(Object paramObject, Throwable paramThrowable)
  {
    if (isLevelEnabled(1))
      log(1, paramObject, paramThrowable);
  }

  public final void warn(Object paramObject)
  {
    if (isLevelEnabled(4))
      log(4, paramObject, null);
  }

  public final void warn(Object paramObject, Throwable paramThrowable)
  {
    if (isLevelEnabled(4))
      log(4, paramObject, paramThrowable);
  }

  protected void write(StringBuffer paramStringBuffer)
  {
    System.err.println(paramStringBuffer.toString());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.impl.SimpleLog
 * JD-Core Version:    0.6.0
 */