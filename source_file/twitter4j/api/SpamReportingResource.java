package twitter4j.api;

import twitter4j.TwitterException;
import twitter4j.User;

public abstract interface SpamReportingResource
{
  public abstract User reportSpam(long paramLong)
    throws TwitterException;

  public abstract User reportSpam(String paramString)
    throws TwitterException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.api.SpamReportingResource
 * JD-Core Version:    0.6.0
 */