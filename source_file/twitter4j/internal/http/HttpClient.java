package twitter4j.internal.http;

import twitter4j.TwitterException;

public abstract interface HttpClient
{
  public abstract HttpResponse request(HttpRequest paramHttpRequest)
    throws TwitterException;

  public abstract void shutdown();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.http.HttpClient
 * JD-Core Version:    0.6.0
 */