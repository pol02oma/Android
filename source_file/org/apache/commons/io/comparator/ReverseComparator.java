package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

class ReverseComparator extends AbstractFileComparator
  implements Serializable
{
  private final Comparator<File> delegate;

  public ReverseComparator(Comparator<File> paramComparator)
  {
    if (paramComparator == null)
      throw new IllegalArgumentException("Delegate comparator is missing");
    this.delegate = paramComparator;
  }

  public int compare(File paramFile1, File paramFile2)
  {
    return this.delegate.compare(paramFile2, paramFile1);
  }

  public String toString()
  {
    return super.toString() + "[" + this.delegate.toString() + "]";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.comparator.ReverseComparator
 * JD-Core Version:    0.6.0
 */