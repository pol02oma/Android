package twitter4j.management;

public class InvocationStatisticsCalculator
  implements InvocationStatistics
{
  private long callCount;
  private long errorCount;
  private int index;
  private String name;
  private long[] times;
  private long totalTime;

  public InvocationStatisticsCalculator(String paramString, int paramInt)
  {
    this.name = paramString;
    this.times = new long[paramInt];
  }

  public long getAverageTime()
  {
    monitorenter;
    try
    {
      int i = Math.min(Math.abs((int)this.callCount), this.times.length);
      long l2;
      if (i == 0)
        l2 = 0L;
      while (true)
      {
        return l2;
        long l1 = 0L;
        for (int j = 0; j < i; j++)
          l1 += this.times[j];
        l2 = l1 / i;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public long getCallCount()
  {
    return this.callCount;
  }

  public long getErrorCount()
  {
    return this.errorCount;
  }

  public String getName()
  {
    return this.name;
  }

  public long getTotalTime()
  {
    return this.totalTime;
  }

  void increment(long paramLong, boolean paramBoolean)
  {
    long l1 = 1L;
    this.callCount = (l1 + this.callCount);
    long l2 = this.errorCount;
    if (paramBoolean)
      l1 = 0L;
    this.errorCount = (l1 + l2);
    this.totalTime = (paramLong + this.totalTime);
    this.times[this.index] = paramLong;
    int i = 1 + this.index;
    this.index = i;
    if (i >= this.times.length)
      this.index = 0;
  }

  public void reset()
  {
    monitorenter;
    try
    {
      this.callCount = 0L;
      this.errorCount = 0L;
      this.totalTime = 0L;
      this.times = new long[this.times.length];
      this.index = 0;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("calls=").append(getCallCount()).append(",").append("errors=").append(getErrorCount()).append(",").append("totalTime=").append(getTotalTime()).append(",").append("avgTime=").append(getAverageTime());
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.management.InvocationStatisticsCalculator
 * JD-Core Version:    0.6.0
 */