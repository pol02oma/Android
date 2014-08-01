package twitter4j;

import java.io.Serializable;

public abstract interface AccountSettings extends TwitterResponse, Serializable
{
  public abstract String getLanguage();

  public abstract String getScreenName();

  public abstract String getSleepEndTime();

  public abstract String getSleepStartTime();

  public abstract TimeZone getTimeZone();

  public abstract Location[] getTrendLocations();

  public abstract boolean isAlwaysUseHttps();

  public abstract boolean isDiscoverableByEmail();

  public abstract boolean isGeoEnabled();

  public abstract boolean isSleepTimeEnabled();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.AccountSettings
 * JD-Core Version:    0.6.0
 */