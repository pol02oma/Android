package com.restfb.json;

public class JsonException extends RuntimeException
{
  private static final long serialVersionUID = 1L;
  private Throwable cause;

  public JsonException(String paramString)
  {
    super(paramString);
  }

  public JsonException(Throwable paramThrowable)
  {
    super(paramThrowable.getMessage());
    this.cause = paramThrowable;
  }

  public Throwable getCause()
  {
    return this.cause;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.json.JsonException
 * JD-Core Version:    0.6.0
 */