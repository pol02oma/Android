package org.apache.james.mime4j;

import java.io.IOException;

public class MimeIOException extends IOException
{
  private static final long serialVersionUID = 5393613459533735409L;

  public MimeIOException(MimeException paramMimeException)
  {
  }

  public MimeException getCause()
  {
    return (MimeException)super.getCause();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.MimeIOException
 * JD-Core Version:    0.6.0
 */