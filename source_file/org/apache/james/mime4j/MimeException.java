package org.apache.james.mime4j;

public class MimeException extends Exception
{
  private static final long serialVersionUID = 8352821278714188542L;

  public MimeException(String paramString)
  {
    super(paramString);
  }

  public MimeException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  public MimeException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.MimeException
 * JD-Core Version:    0.6.0
 */