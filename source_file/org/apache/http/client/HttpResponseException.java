package org.apache.http.client;

import org.apache.http.annotation.Immutable;

@Immutable
public class HttpResponseException extends ClientProtocolException
{
  private static final long serialVersionUID = -7186627969477257933L;
  private final int statusCode;

  public HttpResponseException(int paramInt, String paramString)
  {
    super(paramString);
    this.statusCode = paramInt;
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.HttpResponseException
 * JD-Core Version:    0.6.0
 */