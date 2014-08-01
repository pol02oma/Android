package org.apache.commons.logging;

public class LogConfigurationException extends RuntimeException
{
  protected Throwable cause = null;

  public LogConfigurationException()
  {
  }

  public LogConfigurationException(String paramString)
  {
    super(paramString);
  }

  public LogConfigurationException(String paramString, Throwable paramThrowable)
  {
    super(paramString + " (Caused by " + paramThrowable + ")");
    this.cause = paramThrowable;
  }

  public LogConfigurationException(Throwable paramThrowable)
  {
  }

  public Throwable getCause()
  {
    return this.cause;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.LogConfigurationException
 * JD-Core Version:    0.6.0
 */