package twitter4j.management;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class APIStatistics
  implements APIStatisticsMBean
{
  private final InvocationStatisticsCalculator API_STATS_CALCULATOR;
  private final int HISTORY_SIZE;
  private final Map<String, InvocationStatisticsCalculator> METHOD_STATS_MAP;

  public APIStatistics(int paramInt)
  {
    this.API_STATS_CALCULATOR = new InvocationStatisticsCalculator("API", paramInt);
    this.METHOD_STATS_MAP = new HashMap(100);
    this.HISTORY_SIZE = paramInt;
  }

  private InvocationStatisticsCalculator getMethodStatistics(String paramString)
  {
    monitorenter;
    try
    {
      InvocationStatisticsCalculator localInvocationStatisticsCalculator = (InvocationStatisticsCalculator)this.METHOD_STATS_MAP.get(paramString);
      if (localInvocationStatisticsCalculator == null)
      {
        localInvocationStatisticsCalculator = new InvocationStatisticsCalculator(paramString, this.HISTORY_SIZE);
        this.METHOD_STATS_MAP.put(paramString, localInvocationStatisticsCalculator);
      }
      return localInvocationStatisticsCalculator;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public long getAverageTime()
  {
    return this.API_STATS_CALCULATOR.getAverageTime();
  }

  public long getCallCount()
  {
    return this.API_STATS_CALCULATOR.getCallCount();
  }

  public long getErrorCount()
  {
    return this.API_STATS_CALCULATOR.getErrorCount();
  }

  public Iterable<? extends InvocationStatistics> getInvocationStatistics()
  {
    monitorenter;
    try
    {
      Collection localCollection = this.METHOD_STATS_MAP.values();
      monitorexit;
      return localCollection;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public Map<String, String> getMethodLevelSummariesAsString()
  {
    monitorenter;
    HashMap localHashMap;
    try
    {
      localHashMap = new HashMap();
      Iterator localIterator = this.METHOD_STATS_MAP.values().iterator();
      while (localIterator.hasNext())
      {
        InvocationStatisticsCalculator localInvocationStatisticsCalculator = (InvocationStatisticsCalculator)localIterator.next();
        localHashMap.put(localInvocationStatisticsCalculator.getName(), localInvocationStatisticsCalculator.toString());
      }
    }
    finally
    {
      monitorexit;
    }
    monitorexit;
    return localHashMap;
  }

  public String getMethodLevelSummary(String paramString)
  {
    monitorenter;
    try
    {
      String str = ((InvocationStatisticsCalculator)this.METHOD_STATS_MAP.get(paramString)).toString();
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String getName()
  {
    return this.API_STATS_CALCULATOR.getName();
  }

  public long getTotalTime()
  {
    return this.API_STATS_CALCULATOR.getTotalTime();
  }

  public void methodCalled(String paramString, long paramLong, boolean paramBoolean)
  {
    monitorenter;
    try
    {
      getMethodStatistics(paramString).increment(paramLong, paramBoolean);
      this.API_STATS_CALCULATOR.increment(paramLong, paramBoolean);
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

  public void reset()
  {
    monitorenter;
    try
    {
      this.API_STATS_CALCULATOR.reset();
      this.METHOD_STATS_MAP.clear();
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.management.APIStatistics
 * JD-Core Version:    0.6.0
 */