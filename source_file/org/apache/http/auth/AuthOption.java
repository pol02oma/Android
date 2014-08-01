package org.apache.http.auth;

import org.apache.http.annotation.Immutable;

@Immutable
public final class AuthOption
{
  private final AuthScheme authScheme;
  private final Credentials creds;

  public AuthOption(AuthScheme paramAuthScheme, Credentials paramCredentials)
  {
    if (paramAuthScheme == null)
      throw new IllegalArgumentException("Auth scheme may not be null");
    if (paramCredentials == null)
      throw new IllegalArgumentException("User credentials may not be null");
    this.authScheme = paramAuthScheme;
    this.creds = paramCredentials;
  }

  public AuthScheme getAuthScheme()
  {
    return this.authScheme;
  }

  public Credentials getCredentials()
  {
    return this.creds;
  }

  public String toString()
  {
    return this.authScheme.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.AuthOption
 * JD-Core Version:    0.6.0
 */