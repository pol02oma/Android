package twitter4j.auth;

import twitter4j.TwitterException;

public abstract interface OAuthSupport
{
  public abstract AccessToken getOAuthAccessToken()
    throws TwitterException;

  public abstract AccessToken getOAuthAccessToken(String paramString)
    throws TwitterException;

  public abstract AccessToken getOAuthAccessToken(String paramString1, String paramString2)
    throws TwitterException;

  public abstract AccessToken getOAuthAccessToken(RequestToken paramRequestToken)
    throws TwitterException;

  public abstract AccessToken getOAuthAccessToken(RequestToken paramRequestToken, String paramString)
    throws TwitterException;

  public abstract RequestToken getOAuthRequestToken()
    throws TwitterException;

  public abstract RequestToken getOAuthRequestToken(String paramString)
    throws TwitterException;

  public abstract RequestToken getOAuthRequestToken(String paramString1, String paramString2)
    throws TwitterException;

  public abstract void setOAuthAccessToken(AccessToken paramAccessToken);

  public abstract void setOAuthConsumer(String paramString1, String paramString2);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.auth.OAuthSupport
 * JD-Core Version:    0.6.0
 */