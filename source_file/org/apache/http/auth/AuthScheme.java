package org.apache.http.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;

public abstract interface AuthScheme
{
  @Deprecated
  public abstract Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException;

  public abstract String getParameter(String paramString);

  public abstract String getRealm();

  public abstract String getSchemeName();

  public abstract boolean isComplete();

  public abstract boolean isConnectionBased();

  public abstract void processChallenge(Header paramHeader)
    throws MalformedChallengeException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.AuthScheme
 * JD-Core Version:    0.6.0
 */