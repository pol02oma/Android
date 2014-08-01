package org.apache.http.impl.client;

import org.apache.http.annotation.Immutable;

@Immutable
public class TargetAuthenticationStrategy extends AuthenticationStrategyImpl
{
  public TargetAuthenticationStrategy()
  {
    super(401, "WWW-Authenticate", "http.auth.target-scheme-pref");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.TargetAuthenticationStrategy
 * JD-Core Version:    0.6.0
 */