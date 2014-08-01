package org.apache.http;

public abstract interface HeaderElement
{
  public abstract String getName();

  public abstract NameValuePair getParameter(int paramInt);

  public abstract NameValuePair getParameterByName(String paramString);

  public abstract int getParameterCount();

  public abstract NameValuePair[] getParameters();

  public abstract String getValue();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.HeaderElement
 * JD-Core Version:    0.6.0
 */