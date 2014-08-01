package org.apache.commons.io;

import java.io.IOException;

public class IOExceptionWithCause extends IOException
{
  private static final long serialVersionUID = 1L;

  public IOExceptionWithCause(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }

  public IOExceptionWithCause(Throwable paramThrowable)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.IOExceptionWithCause
 * JD-Core Version:    0.6.0
 */