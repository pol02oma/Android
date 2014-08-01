package org.apache.http.client;

import org.apache.http.conn.routing.HttpRoute;

public abstract interface BackoffManager
{
  public abstract void backOff(HttpRoute paramHttpRoute);

  public abstract void probe(HttpRoute paramHttpRoute);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.BackoffManager
 * JD-Core Version:    0.6.0
 */