package oauth.signpost;

import java.io.Serializable;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.signature.OAuthMessageSigner;
import oauth.signpost.signature.SigningStrategy;

public abstract interface OAuthConsumer extends Serializable
{
  public abstract String getConsumerKey();

  public abstract String getConsumerSecret();

  public abstract HttpParameters getRequestParameters();

  public abstract String getToken();

  public abstract String getTokenSecret();

  public abstract void setAdditionalParameters(HttpParameters paramHttpParameters);

  public abstract void setMessageSigner(OAuthMessageSigner paramOAuthMessageSigner);

  public abstract void setSendEmptyTokens(boolean paramBoolean);

  public abstract void setSigningStrategy(SigningStrategy paramSigningStrategy);

  public abstract void setTokenWithSecret(String paramString1, String paramString2);

  public abstract String sign(String paramString)
    throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException;

  public abstract HttpRequest sign(Object paramObject)
    throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException;

  public abstract HttpRequest sign(HttpRequest paramHttpRequest)
    throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.OAuthConsumer
 * JD-Core Version:    0.6.0
 */