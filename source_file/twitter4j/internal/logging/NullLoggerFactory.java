package twitter4j.internal.logging;

final class NullLoggerFactory extends LoggerFactory
{
  private static final Logger SINGLETON = new NullLogger();

  public Logger getLogger(Class paramClass)
  {
    return SINGLETON;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.logging.NullLoggerFactory
 * JD-Core Version:    0.6.0
 */