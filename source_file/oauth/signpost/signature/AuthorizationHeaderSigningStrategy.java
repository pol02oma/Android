package oauth.signpost.signature;

import oauth.signpost.OAuth;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;

public class AuthorizationHeaderSigningStrategy
  implements SigningStrategy
{
  private static final long serialVersionUID = 1L;

  public String writeSignature(String paramString, HttpRequest paramHttpRequest, HttpParameters paramHttpParameters)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OAuth ");
    if (paramHttpParameters.containsKey("realm"))
    {
      localStringBuilder.append(paramHttpParameters.getAsHeaderElement("realm"));
      localStringBuilder.append(", ");
    }
    if (paramHttpParameters.containsKey("oauth_token"))
    {
      localStringBuilder.append(paramHttpParameters.getAsHeaderElement("oauth_token"));
      localStringBuilder.append(", ");
    }
    if (paramHttpParameters.containsKey("oauth_callback"))
    {
      localStringBuilder.append(paramHttpParameters.getAsHeaderElement("oauth_callback"));
      localStringBuilder.append(", ");
    }
    if (paramHttpParameters.containsKey("oauth_verifier"))
    {
      localStringBuilder.append(paramHttpParameters.getAsHeaderElement("oauth_verifier"));
      localStringBuilder.append(", ");
    }
    localStringBuilder.append(paramHttpParameters.getAsHeaderElement("oauth_consumer_key"));
    localStringBuilder.append(", ");
    localStringBuilder.append(paramHttpParameters.getAsHeaderElement("oauth_version"));
    localStringBuilder.append(", ");
    localStringBuilder.append(paramHttpParameters.getAsHeaderElement("oauth_signature_method"));
    localStringBuilder.append(", ");
    localStringBuilder.append(paramHttpParameters.getAsHeaderElement("oauth_timestamp"));
    localStringBuilder.append(", ");
    localStringBuilder.append(paramHttpParameters.getAsHeaderElement("oauth_nonce"));
    localStringBuilder.append(", ");
    localStringBuilder.append(OAuth.toHeaderElement("oauth_signature", paramString));
    String str = localStringBuilder.toString();
    paramHttpRequest.setHeader("Authorization", str);
    return str;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.signature.AuthorizationHeaderSigningStrategy
 * JD-Core Version:    0.6.0
 */