package org.apache.http;

public abstract interface StatusLine
{
  public abstract ProtocolVersion getProtocolVersion();

  public abstract String getReasonPhrase();

  public abstract int getStatusCode();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.StatusLine
 * JD-Core Version:    0.6.0
 */