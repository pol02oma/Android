package org.apache.http.impl.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

public abstract class GGSSchemeBase extends AuthSchemeBase
{
  private final Base64 base64codec = new Base64();
  private final Log log = LogFactory.getLog(getClass());
  private State state = State.UNINITIATED;
  private final boolean stripPort;
  private byte[] token;

  GGSSchemeBase()
  {
    this(false);
  }

  GGSSchemeBase(boolean paramBoolean)
  {
    this.stripPort = paramBoolean;
  }

  @Deprecated
  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    return authenticate(paramCredentials, paramHttpRequest, null);
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    switch (1.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[this.state.ordinal()])
    {
    default:
      throw new IllegalStateException("Illegal state: " + this.state);
    case 1:
      throw new AuthenticationException(getSchemeName() + " authentication has not been initiated");
    case 2:
      throw new AuthenticationException(getSchemeName() + " authentication has failed");
    case 3:
    case 4:
    }
    while (true)
    {
      HttpHost localHttpHost;
      try
      {
        if (!isProxy())
          break label229;
        str2 = "http.proxy_host";
        localHttpHost = (HttpHost)paramHttpContext.getAttribute(str2);
        if (localHttpHost != null)
          break label236;
        throw new AuthenticationException("Authentication host is not set in the execution context");
      }
      catch (GSSException localGSSException)
      {
        this.state = State.FAILED;
        if (localGSSException.getMajor() == 9)
          break label214;
      }
      if (localGSSException.getMajor() != 8)
        break;
      label214: throw new InvalidCredentialsException(localGSSException.getMessage(), localGSSException);
      label229: String str2 = "http.target_host";
      continue;
      label236: if ((!this.stripPort) && (localHttpHost.getPort() > 0));
      String str3;
      for (Object localObject = localHttpHost.toHostString(); ; localObject = str3)
      {
        if (this.log.isDebugEnabled())
          this.log.debug("init " + (String)localObject);
        this.token = generateToken(this.token, (String)localObject);
        this.state = State.TOKEN_GENERATED;
        String str1 = new String(this.base64codec.encode(this.token));
        if (this.log.isDebugEnabled())
          this.log.debug("Sending response '" + str1 + "' back to the auth server");
        return new BasicHeader("Authorization", "Negotiate " + str1);
        str3 = localHttpHost.getHostName();
      }
    }
    if (localGSSException.getMajor() == 13)
      throw new InvalidCredentialsException(localGSSException.getMessage(), localGSSException);
    if ((localGSSException.getMajor() == 10) || (localGSSException.getMajor() == 19) || (localGSSException.getMajor() == 20))
      throw new AuthenticationException(localGSSException.getMessage(), localGSSException);
    throw new AuthenticationException(localGSSException.getMessage());
  }

  protected byte[] generateGSSToken(byte[] paramArrayOfByte, Oid paramOid, String paramString)
    throws GSSException
  {
    byte[] arrayOfByte = paramArrayOfByte;
    if (arrayOfByte == null)
      arrayOfByte = new byte[0];
    GSSManager localGSSManager = getManager();
    GSSContext localGSSContext = localGSSManager.createContext(localGSSManager.createName("HTTP@" + paramString, GSSName.NT_HOSTBASED_SERVICE).canonicalize(paramOid), paramOid, null, 0);
    localGSSContext.requestMutualAuth(true);
    localGSSContext.requestCredDeleg(true);
    return localGSSContext.initSecContext(arrayOfByte, 0, arrayOfByte.length);
  }

  protected abstract byte[] generateToken(byte[] paramArrayOfByte, String paramString)
    throws GSSException;

  protected GSSManager getManager()
  {
    return GSSManager.getInstance();
  }

  public boolean isComplete()
  {
    return (this.state == State.TOKEN_GENERATED) || (this.state == State.FAILED);
  }

  protected void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException
  {
    String str = paramCharArrayBuffer.substringTrimmed(paramInt1, paramInt2);
    if (this.log.isDebugEnabled())
      this.log.debug("Received challenge '" + str + "' from the auth server");
    if (this.state == State.UNINITIATED)
    {
      this.token = this.base64codec.decode(str.getBytes());
      this.state = State.CHALLENGE_RECEIVED;
      return;
    }
    this.log.debug("Authentication already attempted");
    this.state = State.FAILED;
  }

  static enum State
  {
    static
    {
      CHALLENGE_RECEIVED = new State("CHALLENGE_RECEIVED", 1);
      TOKEN_GENERATED = new State("TOKEN_GENERATED", 2);
      FAILED = new State("FAILED", 3);
      State[] arrayOfState = new State[4];
      arrayOfState[0] = UNINITIATED;
      arrayOfState[1] = CHALLENGE_RECEIVED;
      arrayOfState[2] = TOKEN_GENERATED;
      arrayOfState[3] = FAILED;
      $VALUES = arrayOfState;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.auth.GGSSchemeBase
 * JD-Core Version:    0.6.0
 */