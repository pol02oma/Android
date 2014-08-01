package com.appbuilder.sdk.android;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CacheManager
{
  public static void clean(Widget paramWidget)
  {
    File localFile = new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder());
    if (localFile == null);
    while (true)
    {
      return;
      if (!localFile.exists())
        continue;
      File[] arrayOfFile = localFile.listFiles();
      for (int i = 0; i < arrayOfFile.length; i++)
        arrayOfFile[i].delete();
    }
  }

  public static void delete(Widget paramWidget, String paramString)
  {
    File localFile = new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder() + "/" + paramString);
    if (localFile == null);
    do
      return;
    while (!localFile.exists());
    localFile.delete();
  }

  public static byte[] getBytes(Widget paramWidget, String paramString)
  {
    File localFile = new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder() + "/" + paramString);
    if (localFile == null);
    do
      return null;
    while (!localFile.exists());
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      for (int i = localFileInputStream.read(); i != -1; i = localFileInputStream.read())
        localByteArrayOutputStream.write(i);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      localFileInputStream.close();
      return arrayOfByte;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static String[] getCacheIds(Widget paramWidget)
  {
    File localFile = new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder());
    if (!localFile.exists())
      return new String[0];
    ArrayList localArrayList = new ArrayList();
    File[] arrayOfFile = localFile.listFiles();
    for (int i = 0; i < arrayOfFile.length; i++)
      localArrayList.add(arrayOfFile[i].getName());
    String[] arrayOfString = new String[localArrayList.size()];
    for (int j = 0; j < localArrayList.size(); j++)
      arrayOfString[j] = ((String)localArrayList.get(j));
    return arrayOfString;
  }

  public static Object getObject(Widget paramWidget, String paramString)
  {
    File localFile = new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder() + "/" + paramString);
    if (localFile == null)
      return null;
    if (!localFile.exists())
      return null;
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
      Object localObject = localObjectInputStream.readObject();
      localObjectInputStream.close();
      localFileInputStream.close();
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static String saveBytes(Widget paramWidget, byte[] paramArrayOfByte)
  {
    for (int i = 1; new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder() + "/" + i).exists(); i++);
    String str = "" + i;
    if (str == null);
    do
      return null;
    while (str.length() == 0);
    return saveBytes(paramWidget, paramArrayOfByte, str);
  }

  public static String saveBytes(Widget paramWidget, byte[] paramArrayOfByte, String paramString)
  {
    return saveBytes(paramWidget, paramArrayOfByte, paramString, false);
  }

  public static String saveBytes(Widget paramWidget, byte[] paramArrayOfByte, String paramString, boolean paramBoolean)
  {
    File localFile1 = new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder());
    File localFile2;
    try
    {
      if (!localFile1.exists())
        localFile1.mkdirs();
      localFile2 = new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder() + "/" + paramString);
      if (localFile2 == null)
        return null;
    }
    catch (Exception localException1)
    {
      return null;
    }
    if ((!localFile2.exists()) || (paramBoolean))
      try
      {
        localFile2.delete();
        localFile2.createNewFile();
        try
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
          localFileOutputStream.write(paramArrayOfByte);
          localFileOutputStream.close();
          return paramString;
        }
        catch (Exception localException2)
        {
          return null;
        }
      }
      catch (IOException localIOException)
      {
        return null;
      }
    return null;
  }

  public static String saveObject(Widget paramWidget, Object paramObject)
  {
    for (int i = 1; new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder() + "/" + i).exists(); i++);
    String str = "" + i;
    if (str == null);
    do
      return null;
    while (str.length() == 0);
    return saveObject(paramWidget, paramObject, str);
  }

  public static String saveObject(Widget paramWidget, Object paramObject, String paramString)
  {
    return saveObject(paramWidget, paramObject, paramString, false);
  }

  public static String saveObject(Widget paramWidget, Object paramObject, String paramString, boolean paramBoolean)
  {
    File localFile1 = new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder());
    File localFile2;
    try
    {
      if (!localFile1.exists())
        localFile1.mkdirs();
      localFile2 = new File(paramWidget.getCachePath() + "/" + paramWidget.getPluginId() + "-" + paramWidget.getOrder() + "/" + paramString);
      if (localFile2 == null)
        return null;
    }
    catch (Exception localException1)
    {
      return null;
    }
    if ((!localFile2.exists()) || (paramBoolean))
      try
      {
        localFile2.delete();
        localFile2.createNewFile();
        try
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
          ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
          localObjectOutputStream.writeObject(paramObject);
          localObjectOutputStream.close();
          localFileOutputStream.close();
          return paramString;
        }
        catch (Exception localException2)
        {
          return null;
        }
      }
      catch (IOException localIOException)
      {
        return null;
      }
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.CacheManager
 * JD-Core Version:    0.6.0
 */