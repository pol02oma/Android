package org.apache.james.mime4j.io;

import org.apache.james.mime4j.MimeException;

public class MaxHeaderLimitException extends MimeException
{
  private static final long serialVersionUID = 2154269045186186769L;

  public MaxHeaderLimitException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.io.MaxHeaderLimitException
 * JD-Core Version:    0.6.0
 */