package twitter4j.internal.logging;

final class SLF4JLoggerFactory extends LoggerFactory
{
  public Logger getLogger(Class paramClass)
  {
    return new SLF4JLogger(org.slf4j.LoggerFactory.getLogger(paramClass));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.logging.SLF4JLoggerFactory
 * JD-Core Version:    0.6.0
 */