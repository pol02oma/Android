package twitter4j.internal.logging;

final class JULLoggerFactory extends LoggerFactory
{
  public Logger getLogger(Class paramClass)
  {
    return new JULLogger(java.util.logging.Logger.getLogger(paramClass.getName()));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.logging.JULLoggerFactory
 * JD-Core Version:    0.6.0
 */