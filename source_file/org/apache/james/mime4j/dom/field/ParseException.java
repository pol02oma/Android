package org.apache.james.mime4j.dom.field;

import org.apache.james.mime4j.MimeException;

public class ParseException extends MimeException
{
  private static final long serialVersionUID = 1L;

  protected ParseException(String paramString)
  {
    super(paramString);
  }

  protected ParseException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  protected ParseException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.field.ParseException
 * JD-Core Version:    0.6.0
 */