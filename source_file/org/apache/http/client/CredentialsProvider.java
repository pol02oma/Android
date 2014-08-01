package org.apache.http.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;

public abstract interface CredentialsProvider
{
  public abstract void clear();

  public abstract Credentials getCredentials(AuthScope paramAuthScope);

  public abstract void setCredentials(AuthScope paramAuthScope, Credentials paramCredentials);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.CredentialsProvider
 * JD-Core Version:    0.6.0
 */