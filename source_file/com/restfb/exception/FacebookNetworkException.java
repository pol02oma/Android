package com.restfb.exception;

public class FacebookNetworkException extends FacebookException
{
  private static final long serialVersionUID = 1L;
  private Integer httpStatusCode;

  public FacebookNetworkException(String paramString, Integer paramInteger)
  {
    this(paramString, null, paramInteger);
  }

  public FacebookNetworkException(String paramString, Throwable paramThrowable)
  {
    this(paramString, paramThrowable, null);
  }

  public FacebookNetworkException(String paramString, Throwable paramThrowable, Integer paramInteger)
  {
    super(String.format("A network error occurred while trying to communicate with Facebook: %s (HTTP status code %d)", new Object[] { paramString, paramInteger }), paramThrowable);
    this.httpStatusCode = paramInteger;
  }

  public Integer getHttpStatusCode()
  {
    return this.httpStatusCode;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.exception.FacebookNetworkException
 * JD-Core Version:    0.6.0
 */