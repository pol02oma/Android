package org.apache.http;

public abstract interface Header
{
  public abstract HeaderElement[] getElements()
    throws ParseException;

  public abstract String getName();

  public abstract String getValue();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.Header
 * JD-Core Version:    0.6.0
 */