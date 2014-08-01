package twitter4j;

public abstract interface RateLimitStatusListener
{
  public abstract void onRateLimitReached(RateLimitStatusEvent paramRateLimitStatusEvent);

  public abstract void onRateLimitStatus(RateLimitStatusEvent paramRateLimitStatusEvent);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.RateLimitStatusListener
 * JD-Core Version:    0.6.0
 */