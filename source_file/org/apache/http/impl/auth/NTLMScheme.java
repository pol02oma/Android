package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.NTCredentials;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class NTLMScheme extends AuthSchemeBase
{
  private String challenge;
  private final NTLMEngine engine;
  private State state;

  public NTLMScheme(NTLMEngine paramNTLMEngine)
  {
    if (paramNTLMEngine == null)
      throw new IllegalArgumentException("NTLM engine may not be null");
    this.engine = paramNTLMEngine;
    this.state = State.UNINITIATED;
    this.challenge = null;
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    while (true)
    {
      NTCredentials localNTCredentials;
      String str;
      CharArrayBuffer localCharArrayBuffer;
      try
      {
        localNTCredentials = (NTCredentials)paramCredentials;
        if ((this.state == State.CHALLENGE_RECEIVED) || (this.state == State.FAILED))
        {
          str = this.engine.generateType1Msg(localNTCredentials.getDomain(), localNTCredentials.getWorkstation());
          this.state = State.MSG_TYPE1_GENERATED;
          localCharArrayBuffer = new CharArrayBuffer(32);
          if (!isProxy())
            break label222;
          localCharArrayBuffer.append("Proxy-Authorization");
          localCharArrayBuffer.append(": NTLM ");
          localCharArrayBuffer.append(str);
          return new BufferedHeader(localCharArrayBuffer);
        }
      }
      catch (ClassCastException localClassCastException)
      {
        throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + paramCredentials.getClass().getName());
      }
      if (this.state == State.MSG_TYPE2_RECEVIED)
      {
        str = this.engine.generateType3Msg(localNTCredentials.getUserName(), localNTCredentials.getPassword(), localNTCredentials.getDomain(), localNTCredentials.getWorkstation(), this.challenge);
        this.state = State.MSG_TYPE3_GENERATED;
        continue;
      }
      throw new AuthenticationException("Unexpected state: " + this.state);
      label222: localCharArrayBuffer.append("Authorization");
    }
  }

  public String getParameter(String paramString)
  {
    return null;
  }

  public String getRealm()
  {
    return null;
  }

  public String getSchemeName()
  {
    return "ntlm";
  }

  public boolean isComplete()
  {
    return (this.state == State.MSG_TYPE3_GENERATED) || (this.state == State.FAILED);
  }

  public boolean isConnectionBased()
  {
    return true;
  }

  protected void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException
  {
    String str = paramCharArrayBuffer.substringTrimmed(paramInt1, paramInt2);
    if (str.length() == 0)
    {
      if (this.state == State.UNINITIATED);
      for (this.state = State.CHALLENGE_RECEIVED; ; this.state = State.FAILED)
      {
        this.challenge = null;
        return;
      }
    }
    this.state = State.MSG_TYPE2_RECEVIED;
    this.challenge = str;
  }

  static enum State
  {
    static
    {
      CHALLENGE_RECEIVED = new State("CHALLENGE_RECEIVED", 1);
      MSG_TYPE1_GENERATED = new State("MSG_TYPE1_GENERATED", 2);
      MSG_TYPE2_RECEVIED = new State("MSG_TYPE2_RECEVIED", 3);
      MSG_TYPE3_GENERATED = new State("MSG_TYPE3_GENERATED", 4);
      FAILED = new State("FAILED", 5);
      State[] arrayOfState = new State[6];
      arrayOfState[0] = UNINITIATED;
      arrayOfState[1] = CHALLENGE_RECEIVED;
      arrayOfState[2] = MSG_TYPE1_GENERATED;
      arrayOfState[3] = MSG_TYPE2_RECEVIED;
      arrayOfState[4] = MSG_TYPE3_GENERATED;
      arrayOfState[5] = FAILED;
      $VALUES = arrayOfState;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.auth.NTLMScheme
 * JD-Core Version:    0.6.0
 */