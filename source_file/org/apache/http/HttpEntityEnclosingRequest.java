package org.apache.http;

public abstract interface HttpEntityEnclosingRequest extends HttpRequest
{
  public abstract boolean expectContinue();

  public abstract HttpEntity getEntity();

  public abstract void setEntity(HttpEntity paramHttpEntity);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpEntityEnclosingRequest
 * JD-Core Version:    0.6.0
 */