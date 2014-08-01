package oauth.signpost.signature;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import oauth.signpost.OAuth;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;

public class HmacSha1MessageSigner extends OAuthMessageSigner
{
  private static final String MAC_NAME = "HmacSHA1";

  public String getSignatureMethod()
  {
    return "HMAC-SHA1";
  }

  public String sign(HttpRequest paramHttpRequest, HttpParameters paramHttpParameters)
    throws OAuthMessageSignerException
  {
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec((OAuth.percentEncode(getConsumerSecret()) + '&' + OAuth.percentEncode(getTokenSecret())).getBytes("UTF-8"), "HmacSHA1");
      Mac localMac = Mac.getInstance("HmacSHA1");
      localMac.init(localSecretKeySpec);
      String str1 = new SignatureBaseString(paramHttpRequest, paramHttpParameters).generate();
      OAuth.debugOut("SBS", str1);
      String str2 = base64Encode(localMac.doFinal(str1.getBytes("UTF-8"))).trim();
      return str2;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      throw new OAuthMessageSignerException(localGeneralSecurityException);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new OAuthMessageSignerException(localUnsupportedEncodingException);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.signature.HmacSha1MessageSigner
 * JD-Core Version:    0.6.0
 */