package com.restfb.exception;

public class FacebookGraphException extends FacebookException
{
  private static final long serialVersionUID = 1L;
  private String errorMessage;
  private String errorType;

  public FacebookGraphException(String paramString1, String paramString2)
  {
    super(String.format("Received Facebook error response of type %s: %s", new Object[] { paramString1, paramString2 }));
    this.errorType = paramString1;
    this.errorMessage = paramString2;
  }

  public String getErrorMessage()
  {
    return this.errorMessage;
  }

  public String getErrorType()
  {
    return this.errorType;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.exception.FacebookGraphException
 * JD-Core Version:    0.6.0
 */