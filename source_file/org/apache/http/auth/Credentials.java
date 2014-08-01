package org.apache.http.auth;

import java.security.Principal;

public abstract interface Credentials
{
  public abstract String getPassword();

  public abstract Principal getUserPrincipal();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.Credentials
 * JD-Core Version:    0.6.0
 */