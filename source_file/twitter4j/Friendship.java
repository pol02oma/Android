package twitter4j;

import java.io.Serializable;

public abstract interface Friendship extends Serializable
{
  public abstract long getId();

  public abstract String getName();

  public abstract String getScreenName();

  public abstract boolean isFollowedBy();

  public abstract boolean isFollowing();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.Friendship
 * JD-Core Version:    0.6.0
 */