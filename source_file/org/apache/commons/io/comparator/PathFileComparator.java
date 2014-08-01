package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.IOCase;

public class PathFileComparator extends AbstractFileComparator
  implements Serializable
{
  public static final Comparator<File> PATH_COMPARATOR = new PathFileComparator();
  public static final Comparator<File> PATH_INSENSITIVE_COMPARATOR;
  public static final Comparator<File> PATH_INSENSITIVE_REVERSE;
  public static final Comparator<File> PATH_REVERSE = new ReverseComparator(PATH_COMPARATOR);
  public static final Comparator<File> PATH_SYSTEM_COMPARATOR;
  public static final Comparator<File> PATH_SYSTEM_REVERSE;
  private final IOCase caseSensitivity;

  static
  {
    PATH_INSENSITIVE_COMPARATOR = new PathFileComparator(IOCase.INSENSITIVE);
    PATH_INSENSITIVE_REVERSE = new ReverseComparator(PATH_INSENSITIVE_COMPARATOR);
    PATH_SYSTEM_COMPARATOR = new PathFileComparator(IOCase.SYSTEM);
    PATH_SYSTEM_REVERSE = new ReverseComparator(PATH_SYSTEM_COMPARATOR);
  }

  public PathFileComparator()
  {
    this.caseSensitivity = IOCase.SENSITIVE;
  }

  public PathFileComparator(IOCase paramIOCase)
  {
    if (paramIOCase == null)
      paramIOCase = IOCase.SENSITIVE;
    this.caseSensitivity = paramIOCase;
  }

  public int compare(File paramFile1, File paramFile2)
  {
    return this.caseSensitivity.checkCompareTo(paramFile1.getPath(), paramFile2.getPath());
  }

  public String toString()
  {
    return super.toString() + "[caseSensitivity=" + this.caseSensitivity + "]";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.comparator.PathFileComparator
 * JD-Core Version:    0.6.0
 */