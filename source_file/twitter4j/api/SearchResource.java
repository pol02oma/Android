package twitter4j.api;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.TwitterException;

public abstract interface SearchResource
{
  public abstract QueryResult search(Query paramQuery)
    throws TwitterException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.api.SearchResource
 * JD-Core Version:    0.6.0
 */