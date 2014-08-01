package oauth.signpost;

import java.io.Serializable;
import java.util.Map;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import oauth.signpost.http.HttpParameters;

public abstract interface OAuthProvider extends Serializable
{
  public abstract String getAccessTokenEndpointUrl();

  public abstract String getAuthorizationWebsiteUrl();

  @Deprecated
  public abstract Map<String, String> getRequestHeaders();

  public abstract String getRequestTokenEndpointUrl();

  public abstract HttpParameters getResponseParameters();

  public abstract boolean isOAuth10a();

  public abstract void removeListener(OAuthProviderListener paramOAuthProviderListener);

  public abstract void retrieveAccessToken(OAuthConsumer paramOAuthConsumer, String paramString)
    throws OAuthMessageSignerException, OAuthNotAuthorizedException, OAuthExpectationFailedException, OAuthCommunicationException;

  public abstract String retrieveRequestToken(OAuthConsumer paramOAuthConsumer, String paramString)
    throws OAuthMessageSignerException, OAuthNotAuthorizedException, OAuthExpectationFailedException, OAuthCommunicationException;

  public abstract void setListener(OAuthProviderListener paramOAuthProviderListener);

  public abstract void setOAuth10a(boolean paramBoolean);

  @Deprecated
  public abstract void setRequestHeader(String paramString1, String paramString2);

  public abstract void setResponseParameters(HttpParameters paramHttpParameters);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.OAuthProvider
 * JD-Core Version:    0.6.0
 */