package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class CanWriteFileFilter extends AbstractFileFilter
  implements Serializable
{
  public static final IOFileFilter CANNOT_WRITE;
  public static final IOFileFilter CAN_WRITE = new CanWriteFileFilter();

  static
  {
    CANNOT_WRITE = new NotFileFilter(CAN_WRITE);
  }

  public boolean accept(File paramFile)
  {
    return paramFile.canWrite();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.CanWriteFileFilter
 * JD-Core Version:    0.6.0
 */