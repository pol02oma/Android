package twitter4j;

import java.io.Serializable;

public abstract interface AccountTotals extends TwitterResponse, Serializable
{
  public abstract int getFavorites();

  public abstract int getFollowers();

  public abstract int getFriends();

  public abstract int getUpdates();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.AccountTotals
 * JD-Core Version:    0.6.0
 */