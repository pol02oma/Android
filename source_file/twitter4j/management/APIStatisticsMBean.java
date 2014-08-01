package twitter4j.management;

import java.util.Map;

public abstract interface APIStatisticsMBean extends InvocationStatistics
{
  public abstract Iterable<? extends InvocationStatistics> getInvocationStatistics();

  public abstract Map<String, String> getMethodLevelSummariesAsString();

  public abstract String getMethodLevelSummary(String paramString);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.management.APIStatisticsMBean
 * JD-Core Version:    0.6.0
 */