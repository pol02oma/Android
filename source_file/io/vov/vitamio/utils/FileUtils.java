package io.vov.vitamio.utils;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;

public class FileUtils
{
  private static final String FILE_NAME_RESERVED = "|\\?*<\":>+[]/'";

  public static void deleteDir(File paramFile)
  {
    if ((paramFile.exists()) && (paramFile.isDirectory()))
    {
      for (File localFile : paramFile.listFiles())
      {
        if (localFile.isDirectory())
          deleteDir(localFile);
        localFile.delete();
      }
      paramFile.delete();
    }
  }

  public static String getCanonical(File paramFile)
  {
    if (paramFile == null)
      return null;
    try
    {
      String str = paramFile.getCanonicalPath();
      return str;
    }
    catch (IOException localIOException)
    {
    }
    return paramFile.getAbsolutePath();
  }

  public static String getName(String paramString)
  {
    String str = getPath(paramString);
    if (str != null)
      return new File(str).getName();
    return null;
  }

  public static String getPath(String paramString)
  {
    Log.i("FileUtils#getPath(%s)", new Object[] { paramString });
    if (TextUtils.isEmpty(paramString))
      return null;
    if ((paramString.startsWith("file://")) && (paramString.length() > 7))
      return Uri.decode(paramString.substring(7));
    return Uri.decode(paramString);
  }

  public static String getUniqueFileName(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    char[] arrayOfChar = paramString1.toCharArray();
    int i = arrayOfChar.length;
    for (int j = 0; j < i; j++)
    {
      Character localCharacter = Character.valueOf(arrayOfChar[j]);
      if ("|\\?*<\":>+[]/'".indexOf(localCharacter.charValue()) != -1)
        continue;
      localStringBuilder.append(localCharacter);
    }
    String str1 = localStringBuilder.toString();
    if (str1.length() > 16)
      str1 = str1.substring(0, 16);
    String str2 = Crypto.md5(paramString2);
    String str3 = str1 + str2;
    try
    {
      File localFile = File.createTempFile(str3, null);
      if (localFile.exists())
      {
        localFile.delete();
        return str3;
      }
    }
    catch (IOException localIOException)
    {
    }
    return str2;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.utils.FileUtils
 * JD-Core Version:    0.6.0
 */