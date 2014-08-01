package org.apache.commons.logging.impl;

import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.log.Hierarchy;
import org.apache.log.Logger;

public class LogKitLogger
  implements Log, Serializable
{
  protected transient Logger logger = null;
  protected String name = null;

  public LogKitLogger(String paramString)
  {
    this.name = paramString;
    this.logger = getLogger();
  }

  public void debug(Object paramObject)
  {
    if (paramObject != null)
      getLogger().debug(String.valueOf(paramObject));
  }

  public void debug(Object paramObject, Throwable paramThrowable)
  {
    if (paramObject != null)
      getLogger().debug(String.valueOf(paramObject), paramThrowable);
  }

  public void error(Object paramObject)
  {
    if (paramObject != null)
      getLogger().error(String.valueOf(paramObject));
  }

  public void error(Object paramObject, Throwable paramThrowable)
  {
    if (paramObject != null)
      getLogger().error(String.valueOf(paramObject), paramThrowable);
  }

  public void fatal(Object paramObject)
  {
    if (paramObject != null)
      getLogger().fatalError(String.valueOf(paramObject));
  }

  public void fatal(Object paramObject, Throwable paramThrowable)
  {
    if (paramObject != null)
      getLogger().fatalError(String.valueOf(paramObject), paramThrowable);
  }

  public Logger getLogger()
  {
    if (this.logger == null)
      this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(this.name);
    return this.logger;
  }

  public void info(Object paramObject)
  {
    if (paramObject != null)
      getLogger().info(String.valueOf(paramObject));
  }

  public void info(Object paramObject, Throwable paramThrowable)
  {
    if (paramObject != null)
      getLogger().info(String.valueOf(paramObject), paramThrowable);
  }

  public boolean isDebugEnabled()
  {
    return getLogger().isDebugEnabled();
  }

  public boolean isErrorEnabled()
  {
    return getLogger().isErrorEnabled();
  }

  public boolean isFatalEnabled()
  {
    return getLogger().isFatalErrorEnabled();
  }

  public boolean isInfoEnabled()
  {
    return getLogger().isInfoEnabled();
  }

  public boolean isTraceEnabled()
  {
    return getLogger().isDebugEnabled();
  }

  public boolean isWarnEnabled()
  {
    return getLogger().isWarnEnabled();
  }

  public void trace(Object paramObject)
  {
    debug(paramObject);
  }

  public void trace(Object paramObject, Throwable paramThrowable)
  {
    debug(paramObject, paramThrowable);
  }

  public void warn(Object paramObject)
  {
    if (paramObject != null)
      getLogger().warn(String.valueOf(paramObject));
  }

  public void warn(Object paramObject, Throwable paramThrowable)
  {
    if (paramObject != null)
      getLogger().warn(String.valueOf(paramObject), paramThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.impl.LogKitLogger
 * JD-Core Version:    0.6.0
 */