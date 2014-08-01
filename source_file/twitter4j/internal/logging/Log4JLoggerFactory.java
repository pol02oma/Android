package twitter4j.internal.logging;

final class Log4JLoggerFactory extends LoggerFactory
{
  public Logger getLogger(Class paramClass)
  {
    return new Log4JLogger(org.apache.log4j.Logger.getLogger(paramClass));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.logging.Log4JLoggerFactory
 * JD-Core Version:    0.6.0
 */