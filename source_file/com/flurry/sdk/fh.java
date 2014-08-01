package com.flurry.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class fh
{
  private static final String a = fh.class.getSimpleName();

  public static String a(String paramString)
  {
    return a(paramString, 255);
  }

  public static String a(String paramString, int paramInt)
  {
    if (paramString == null)
      paramString = "";
    do
      return paramString;
    while (paramString.length() <= paramInt);
    return paramString.substring(0, paramInt);
  }

  public static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    char[] arrayOfChar = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfByte[j];
      int m = (byte)(k & 0xF);
      localStringBuilder.append(arrayOfChar[(byte)((k & 0xF0) >> 4)]);
      localStringBuilder.append(arrayOfChar[m]);
    }
    return localStringBuilder.toString();
  }

  public static void a(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public static boolean a(long paramLong)
  {
    int i;
    if (paramLong != 0L)
    {
      boolean bool = System.currentTimeMillis() < paramLong;
      i = 0;
      if (bool);
    }
    else
    {
      i = 1;
    }
    return i;
  }

  public static boolean a(Intent paramIntent)
  {
    return eg.a().c().queryIntentActivities(paramIntent, 65536).size() > 0;
  }

  public static String b(String paramString)
  {
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      ex.a(5, a, "Cannot encode '" + paramString + "'");
    }
    return "";
  }

  public static boolean b(Intent paramIntent)
  {
    boolean bool = false;
    if (paramIntent != null)
    {
      ComponentName localComponentName = paramIntent.resolveActivity(eg.a().c());
      bool = eg.a().b().getPackageName().equals(localComponentName.getPackageName());
    }
    return bool;
  }

  public static String c(String paramString)
  {
    try
    {
      String str = URLDecoder.decode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      ex.a(5, a, "Cannot decode '" + paramString + "'");
    }
    return "";
  }

  public static byte[] d(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramString.getBytes(), 0, paramString.length());
      byte[] arrayOfByte = localMessageDigest.digest();
      return arrayOfByte;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      ex.a(6, a, "Unsupported SHA1: " + localNoSuchAlgorithmException.getMessage());
    }
    return null;
  }

  public static String e(String paramString)
  {
    return paramString.replace("'", "\\'").replace("\\n", "").replace("\\r", "").replace("\\t", "");
  }

  public static Map<String, String> f(String paramString)
  {
    HashMap localHashMap = new HashMap();
    if (!TextUtils.isEmpty(paramString))
    {
      String[] arrayOfString1 = paramString.split("&");
      int i = arrayOfString1.length;
      for (int j = 0; j < i; j++)
      {
        String[] arrayOfString2 = arrayOfString1[j].split("=");
        if (arrayOfString2[0].equals("event"))
          continue;
        localHashMap.put(c(arrayOfString2[0]), c(arrayOfString2[1]));
      }
    }
    return localHashMap;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.fh
 * JD-Core Version:    0.6.0
 */