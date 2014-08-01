package org.apache.http.protocol;

public abstract interface HttpContext
{
  public static final String RESERVED_PREFIX = "http.";

  public abstract Object getAttribute(String paramString);

  public abstract Object removeAttribute(String paramString);

  public abstract void setAttribute(String paramString, Object paramObject);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.HttpContext
 * JD-Core Version:    0.6.0
 */