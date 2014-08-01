package org.apache.commons.io.comparator;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class AbstractFileComparator
  implements Comparator<File>
{
  public List<File> sort(List<File> paramList)
  {
    if (paramList != null)
      Collections.sort(paramList, this);
    return paramList;
  }

  public File[] sort(File[] paramArrayOfFile)
  {
    if (paramArrayOfFile != null)
      Arrays.sort(paramArrayOfFile, this);
    return paramArrayOfFile;
  }

  public String toString()
  {
    return getClass().getSimpleName();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.comparator.AbstractFileComparator
 * JD-Core Version:    0.6.0
 */