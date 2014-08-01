package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class SizeFileFilter extends AbstractFileFilter
  implements Serializable
{
  private final boolean acceptLarger;
  private final long size;

  public SizeFileFilter(long paramLong)
  {
    this(paramLong, true);
  }

  public SizeFileFilter(long paramLong, boolean paramBoolean)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("The size must be non-negative");
    this.size = paramLong;
    this.acceptLarger = paramBoolean;
  }

  public boolean accept(File paramFile)
  {
    int i;
    if (paramFile.length() < this.size)
      i = 1;
    while (this.acceptLarger)
    {
      if (i == 0)
      {
        return true;
        i = 0;
        continue;
      }
      return false;
    }
    return i;
  }

  public String toString()
  {
    if (this.acceptLarger);
    for (String str = ">="; ; str = "<")
      return super.toString() + "(" + str + this.size + ")";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.SizeFileFilter
 * JD-Core Version:    0.6.0
 */