package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

@Deprecated
public class WildcardFilter extends AbstractFileFilter
  implements Serializable
{
  private final String[] wildcards;

  public WildcardFilter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("The wildcard must not be null");
    this.wildcards = new String[] { paramString };
  }

  public WildcardFilter(List<String> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("The wildcard list must not be null");
    this.wildcards = ((String[])paramList.toArray(new String[paramList.size()]));
  }

  public WildcardFilter(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("The wildcard array must not be null");
    this.wildcards = new String[paramArrayOfString.length];
    System.arraycopy(paramArrayOfString, 0, this.wildcards, 0, paramArrayOfString.length);
  }

  public boolean accept(File paramFile)
  {
    if (paramFile.isDirectory());
    while (true)
    {
      return false;
      for (String str : this.wildcards)
        if (FilenameUtils.wildcardMatch(paramFile.getName(), str))
          return true;
    }
  }

  public boolean accept(File paramFile, String paramString)
  {
    if ((paramFile != null) && (new File(paramFile, paramString).isDirectory()));
    while (true)
    {
      return false;
      String[] arrayOfString = this.wildcards;
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
        if (FilenameUtils.wildcardMatch(paramString, arrayOfString[j]))
          return true;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.filefilter.WildcardFilter
 * JD-Core Version:    0.6.0
 */