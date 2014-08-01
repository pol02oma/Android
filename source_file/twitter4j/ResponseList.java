package twitter4j;

import java.util.List;

public abstract interface ResponseList<T> extends TwitterResponse, List<T>
{
  public abstract RateLimitStatus getRateLimitStatus();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.ResponseList
 * JD-Core Version:    0.6.0
 */