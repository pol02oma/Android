package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class EmptyFileFilter extends AbstractFileFilter
  implements Serializable
{
  public static final IOFileFilter EMPTY = new EmptyFileFilter();
  public static final IOFileFilter NOT_EMPTY = new NotFileFilter(EMPTY);

  public boolean accept(File paramFile)
  {
    int i = 1;
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j;
      if (arrayOfFile != null)
      {
        int k = arrayOfFile.length;
        j = 0;
        if (k != 0);
      }
      else
      {
        j = i;
      }
      return j;
    }
    if (paramFile.length() == 0L);
    while (true)
    {
      return i;
      i = 0;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.EmptyFileFilter
 * JD-Core Version:    0.6.0
 */