package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class TrueFileFilter
  implements IOFileFilter, Serializable
{
  public static final IOFileFilter INSTANCE;
  public static final IOFileFilter TRUE = new TrueFileFilter();

  static
  {
    INSTANCE = TRUE;
  }

  public boolean accept(File paramFile)
  {
    return true;
  }

  public boolean accept(File paramFile, String paramString)
  {
    return true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.TrueFileFilter
 * JD-Core Version:    0.6.0
 */