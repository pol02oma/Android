package org.apache.http.impl.client;

import org.apache.http.annotation.Immutable;

@Immutable
public class ProxyAuthenticationStrategy extends AuthenticationStrategyImpl
{
  public ProxyAuthenticationStrategy()
  {
    super(407, "Proxy-Authenticate", "http.auth.proxy-scheme-pref");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.ProxyAuthenticationStrategy
 * JD-Core Version:    0.6.0
 */