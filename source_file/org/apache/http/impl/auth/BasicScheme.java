package org.apache.http.impl.auth;

import java.security.Principal;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.params.AuthParams;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;

@NotThreadSafe
public class BasicScheme extends RFC2617Scheme
{
  private boolean complete = false;

  public BasicScheme()
  {
    this(null);
  }

  public BasicScheme(ChallengeState paramChallengeState)
  {
    super(paramChallengeState);
  }

  public static Header authenticate(Credentials paramCredentials, String paramString, boolean paramBoolean)
  {
    if (paramCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null");
    if (paramString == null)
      throw new IllegalArgumentException("charset may not be null");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramCredentials.getUserPrincipal().getName());
    localStringBuilder.append(":");
    String str;
    byte[] arrayOfByte;
    CharArrayBuffer localCharArrayBuffer;
    if (paramCredentials.getPassword() == null)
    {
      str = "null";
      localStringBuilder.append(str);
      arrayOfByte = Base64.encodeBase64(EncodingUtils.getBytes(localStringBuilder.toString(), paramString));
      localCharArrayBuffer = new CharArrayBuffer(32);
      if (!paramBoolean)
        break label153;
      localCharArrayBuffer.append("Proxy-Authorization");
    }
    while (true)
    {
      localCharArrayBuffer.append(": Basic ");
      localCharArrayBuffer.append(arrayOfByte, 0, arrayOfByte.length);
      return new BufferedHeader(localCharArrayBuffer);
      str = paramCredentials.getPassword();
      break;
      label153: localCharArrayBuffer.append("Authorization");
    }
  }

  @Deprecated
  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    return authenticate(paramCredentials, paramHttpRequest, new BasicHttpContext());
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    if (paramCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null");
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    return authenticate(paramCredentials, AuthParams.getCredentialCharset(paramHttpRequest.getParams()), isProxy());
  }

  public String getSchemeName()
  {
    return "basic";
  }

  public boolean isComplete()
  {
    return this.complete;
  }

  public boolean isConnectionBased()
  {
    return false;
  }

  public void processChallenge(Header paramHeader)
    throws MalformedChallengeException
  {
    super.processChallenge(paramHeader);
    this.complete = true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.auth.BasicScheme
 * JD-Core Version:    0.6.0
 */