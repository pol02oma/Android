package twitter4j;

import java.io.Serializable;

public abstract interface TwitterResponse extends Serializable
{
  public static final int NONE = 0;
  public static final int READ = 1;
  public static final int READ_WRITE = 2;
  public static final int READ_WRITE_DIRECTMESSAGES = 3;

  public abstract int getAccessLevel();

  public abstract RateLimitStatus getRateLimitStatus();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.TwitterResponse
 * JD-Core Version:    0.6.0
 */