package twitter4j.auth;

import twitter4j.conf.Configuration;

public final class AuthorizationFactory
{
  public static Authorization getInstance(Configuration paramConfiguration)
  {
    String str1 = paramConfiguration.getOAuthConsumerKey();
    String str2 = paramConfiguration.getOAuthConsumerSecret();
    Object localObject;
    if ((str1 != null) && (str2 != null))
      if (paramConfiguration.isApplicationOnlyAuthEnabled())
      {
        OAuth2Authorization localOAuth2Authorization = new OAuth2Authorization(paramConfiguration);
        String str5 = paramConfiguration.getOAuth2TokenType();
        String str6 = paramConfiguration.getOAuth2AccessToken();
        if ((str5 != null) && (str6 != null))
          localOAuth2Authorization.setOAuth2Token(new OAuth2Token(str5, str6));
        localObject = localOAuth2Authorization;
      }
    while (true)
    {
      if (localObject == null)
        localObject = NullAuthorization.getInstance();
      return localObject;
      OAuthAuthorization localOAuthAuthorization = new OAuthAuthorization(paramConfiguration);
      String str7 = paramConfiguration.getOAuthAccessToken();
      String str8 = paramConfiguration.getOAuthAccessTokenSecret();
      if ((str7 != null) && (str8 != null))
        localOAuthAuthorization.setOAuthAccessToken(new AccessToken(str7, str8));
      localObject = localOAuthAuthorization;
      continue;
      String str3 = paramConfiguration.getUser();
      String str4 = paramConfiguration.getPassword();
      localObject = null;
      if (str3 == null)
        continue;
      localObject = null;
      if (str4 == null)
        continue;
      localObject = new BasicAuthorization(str3, str4);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.auth.AuthorizationFactory
 * JD-Core Version:    0.6.0
 */