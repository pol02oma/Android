package org.apache.http.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;

public abstract interface AuthCache
{
  public abstract void clear();

  public abstract AuthScheme get(HttpHost paramHttpHost);

  public abstract void put(HttpHost paramHttpHost, AuthScheme paramAuthScheme);

  public abstract void remove(HttpHost paramHttpHost);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.AuthCache
 * JD-Core Version:    0.6.0
 */