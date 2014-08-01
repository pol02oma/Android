package org.apache.http;

public class HttpException extends Exception
{
  private static final long serialVersionUID = -5437299376222011036L;

  public HttpException()
  {
  }

  public HttpException(String paramString)
  {
    super(paramString);
  }

  public HttpException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpException
 * JD-Core Version:    0.6.0
 */