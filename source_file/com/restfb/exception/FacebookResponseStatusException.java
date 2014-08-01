package com.restfb.exception;

public class FacebookResponseStatusException extends FacebookException
{
  private static final long serialVersionUID = 1L;
  private Integer errorCode;
  private String errorMessage;

  public FacebookResponseStatusException(Integer paramInteger, String paramString)
  {
    super(String.format("Received Facebook error response (code %d): %s", new Object[] { paramInteger, paramString }));
    this.errorCode = paramInteger;
    this.errorMessage = paramString;
  }

  public Integer getErrorCode()
  {
    return this.errorCode;
  }

  public String getErrorMessage()
  {
    return this.errorMessage;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.exception.FacebookResponseStatusException
 * JD-Core Version:    0.6.0
 */