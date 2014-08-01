package twitter4j;

import java.io.Serializable;
import java.util.Date;

public abstract interface Trends extends TwitterResponse, Comparable<Trends>, Serializable
{
  public abstract Date getAsOf();

  public abstract Location getLocation();

  public abstract Date getTrendAt();

  public abstract Trend[] getTrends();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.Trends
 * JD-Core Version:    0.6.0
 */