package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.HttpRequest;

public abstract interface HttpUriRequest extends HttpRequest
{
  public abstract void abort()
    throws UnsupportedOperationException;

  public abstract String getMethod();

  public abstract URI getURI();

  public abstract boolean isAborted();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.methods.HttpUriRequest
 * JD-Core Version:    0.6.0
 */