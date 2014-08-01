package org.apache.http.params;

public abstract class HttpAbstractParamBean
{
  protected final HttpParams params;

  public HttpAbstractParamBean(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.params = paramHttpParams;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.params.HttpAbstractParamBean
 * JD-Core Version:    0.6.0
 */