package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

public class ExtensionFileComparator extends AbstractFileComparator
  implements Serializable
{
  public static final Comparator<File> EXTENSION_COMPARATOR = new ExtensionFileComparator();
  public static final Comparator<File> EXTENSION_INSENSITIVE_COMPARATOR;
  public static final Comparator<File> EXTENSION_INSENSITIVE_REVERSE;
  public static final Comparator<File> EXTENSION_REVERSE = new ReverseComparator(EXTENSION_COMPARATOR);
  public static final Comparator<File> EXTENSION_SYSTEM_COMPARATOR;
  public static final Comparator<File> EXTENSION_SYSTEM_REVERSE;
  private final IOCase caseSensitivity;

  static
  {
    EXTENSION_INSENSITIVE_COMPARATOR = new ExtensionFileComparator(IOCase.INSENSITIVE);
    EXTENSION_INSENSITIVE_REVERSE = new ReverseComparator(EXTENSION_INSENSITIVE_COMPARATOR);
    EXTENSION_SYSTEM_COMPARATOR = new ExtensionFileComparator(IOCase.SYSTEM);
    EXTENSION_SYSTEM_REVERSE = new ReverseComparator(EXTENSION_SYSTEM_COMPARATOR);
  }

  public ExtensionFileComparator()
  {
    this.caseSensitivity = IOCase.SENSITIVE;
  }

  public ExtensionFileComparator(IOCase paramIOCase)
  {
    if (paramIOCase == null)
      paramIOCase = IOCase.SENSITIVE;
    this.caseSensitivity = paramIOCase;
  }

  public int compare(File paramFile1, File paramFile2)
  {
    String str1 = FilenameUtils.getExtension(paramFile1.getName());
    String str2 = FilenameUtils.getExtension(paramFile2.getName());
    return this.caseSensitivity.checkCompareTo(str1, str2);
  }

  public String toString()
  {
    return super.toString() + "[caseSensitivity=" + this.caseSensitivity + "]";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.comparator.ExtensionFileComparator
 * JD-Core Version:    0.6.0
 */