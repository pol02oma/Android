package org.apache.http;

public abstract interface RequestLine
{
  public abstract String getMethod();

  public abstract ProtocolVersion getProtocolVersion();

  public abstract String getUri();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.RequestLine
 * JD-Core Version:    0.6.0
 */