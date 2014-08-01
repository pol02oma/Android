package twitter4j;

import java.io.Serializable;

public abstract interface RateLimitStatus extends Serializable
{
  public abstract int getLimit();

  public abstract int getRemaining();

  public abstract int getRemainingHits();

  public abstract int getResetTimeInSeconds();

  public abstract int getSecondsUntilReset();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.RateLimitStatus
 * JD-Core Version:    0.6.0
 */