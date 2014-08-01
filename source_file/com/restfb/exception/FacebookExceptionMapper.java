package com.restfb.exception;

public abstract interface FacebookExceptionMapper
{
  public abstract FacebookException exceptionForTypeAndMessage(Integer paramInteger, String paramString1, String paramString2);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.exception.FacebookExceptionMapper
 * JD-Core Version:    0.6.0
 */