package twitter4j.management;

public abstract interface InvocationStatistics
{
  public abstract long getAverageTime();

  public abstract long getCallCount();

  public abstract long getErrorCount();

  public abstract String getName();

  public abstract long getTotalTime();

  public abstract void reset();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.management.InvocationStatistics
 * JD-Core Version:    0.6.0
 */