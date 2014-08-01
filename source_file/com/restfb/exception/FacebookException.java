package com.restfb.exception;

public abstract class FacebookException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  public FacebookException(String paramString)
  {
    super(paramString);
  }

  public FacebookException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.exception.FacebookException
 * JD-Core Version:    0.6.0
 */