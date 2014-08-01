package org.apache.commons.io;

import java.io.File;
import java.io.IOException;

public class FileExistsException extends IOException
{
  private static final long serialVersionUID = 1L;

  public FileExistsException()
  {
  }

  public FileExistsException(File paramFile)
  {
    super("File " + paramFile + " exists");
  }

  public FileExistsException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.FileExistsException
 * JD-Core Version:    0.6.0
 */