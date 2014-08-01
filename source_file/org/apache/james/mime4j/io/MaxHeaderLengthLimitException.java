package org.apache.james.mime4j.io;

import org.apache.james.mime4j.MimeException;

public class MaxHeaderLengthLimitException extends MimeException
{
  private static final long serialVersionUID = 8924290744274769913L;

  public MaxHeaderLengthLimitException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.io.MaxHeaderLengthLimitException
 * JD-Core Version:    0.6.0
 */