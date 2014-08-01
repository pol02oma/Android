package com.appbuilder.sdk.android;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;
import org.json.JSONObject;

public class Utils
{
  public static String AppFolder(Activity paramActivity)
  {
    ApplicationInfo localApplicationInfo = paramActivity.getApplicationInfo();
    paramActivity.getPackageManager().getApplicationLabel(localApplicationInfo).toString();
    String str = Environment.getExternalStorageDirectory() + "/" + "AppBuilder";
    File localFile = new File(str);
    if (!localFile.exists());
    try
    {
      localFile.mkdir();
      return str;
    }
    catch (SecurityException localSecurityException)
    {
    }
    return null;
  }

  public static Bitmap BmpResize(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f1 = paramInt1 / i;
    float f2 = paramInt2 / j;
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f1, f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
  }

  public static Bitmap BmpResizeDisplay(Bitmap paramBitmap, Activity paramActivity)
  {
    Display localDisplay = paramActivity.getWindowManager().getDefaultDisplay();
    return BmpResize(paramBitmap, localDisplay.getWidth(), localDisplay.getHeight());
  }

  public static String CheckTmpDirectory(Activity paramActivity)
  {
    String str1 = AppFolder(paramActivity);
    String str2 = str1 + "/tmp";
    File localFile = new File(str2);
    if (!localFile.exists());
    try
    {
      localFile.mkdir();
      return str2;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
    }
    return null;
  }

  public static Bitmap CreateSquareColorBitmap(int paramInt)
  {
    int[] arrayOfInt = new int[100];
    for (int i = 0; i < 100; i++)
      arrayOfInt[i] = paramInt;
    return Bitmap.createBitmap(arrayOfInt, 10, 10, Bitmap.Config.ARGB_8888);
  }

  public static Bitmap CreateSquareColorBitmap(String paramString)
  {
    return CreateSquareColorBitmap(Color.parseColor(paramString));
  }

  public static float DpiToPixels(float paramFloat)
  {
    return paramFloat;
  }

  public static float PixelsToDpi(float paramFloat)
  {
    return paramFloat;
  }

  public static String PluginFolder(Activity paramActivity)
  {
    String str1 = AppFolder(paramActivity);
    String str2;
    if (str1 == null)
      str2 = null;
    File localFile;
    do
    {
      return str2;
      str2 = str1 + "/plugins/";
      localFile = new File(str2);
    }
    while (localFile.exists());
    try
    {
      localFile.mkdir();
      return str2;
    }
    catch (SecurityException localSecurityException)
    {
    }
    return null;
  }

  public static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int i = paramOptions.outHeight;
    int j = paramOptions.outWidth;
    int k = 1;
    int n;
    if ((i > paramInt2) || (j > paramInt1))
    {
      int m = Math.round(i / paramInt2);
      n = Math.round(j / paramInt1);
      if (m < n)
        k = m;
    }
    else
    {
      return k;
    }
    return n;
  }

  public static boolean checkForSpec(String paramString)
  {
    if (paramString.contains("&amp;"));
    do
      return true;
    while ((paramString.contains("&apos;")) || (paramString.contains("&quot;")) || (paramString.contains("&lt;")) || (paramString.contains("&gt;")) || (paramString.contains("&#8217;")) || (paramString.contains("&#8220;")) || (paramString.contains("&#8221;")) || (paramString.contains("&#124;")) || (paramString.contains("&#8211;")) || (paramString.contains("\020")));
    return false;
  }

  public static Bitmap decodeSampledBitmapFromFile(String paramString, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt1, paramInt2);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }

  public static String downloadFile(String paramString1, String paramString2)
  {
    try
    {
      URLConnection localURLConnection = new URL(URLDecoder.decode(paramString1)).openConnection();
      localURLConnection.setConnectTimeout(10000);
      localURLConnection.setReadTimeout(10000);
      localBufferedInputStream = new BufferedInputStream(localURLConnection.getInputStream());
      File localFile1 = new File(paramString2);
      if (!localFile1.exists())
        localFile1.mkdirs();
      localFile2 = new File(paramString2 + File.separator + md5(paramString1));
      if (!localFile2.exists())
      {
        localFile2.createNewFile();
        localFileOutputStream = new FileOutputStream(localFile2);
        byte[] arrayOfByte = new byte[1024];
        Arrays.fill(arrayOfByte, 0);
        while (true)
        {
          int i = localBufferedInputStream.read(arrayOfByte, 0, 1024);
          if (i == -1)
            break;
          localFileOutputStream.write(arrayOfByte, 0, i);
          Arrays.fill(arrayOfByte, 0);
        }
      }
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      while (true)
      {
        Log.e("API - downloadFile() - SocketTimeoutException", "An error has occurred downloading the image: " + paramString1);
        return null;
        localFile2.delete();
        localFile2.createNewFile();
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      BufferedInputStream localBufferedInputStream;
      File localFile2;
      FileOutputStream localFileOutputStream;
      Log.e("API - downloadFile() - IllegalArgumentException", "An error has occurred downloading the image: " + paramString1);
      return null;
      localBufferedInputStream.close();
      localFileOutputStream.flush();
      localFileOutputStream.close();
      Log.d("", "");
      String str = localFile2.getAbsolutePath();
      return str;
    }
    catch (Exception localException)
    {
      Log.e("API - downloadFile() - Exception", "An error has occurred downloading the image: " + paramString1);
    }
    return null;
  }

  public static Drawable drawableFromUrl(String paramString)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
    localHttpURLConnection.connect();
    return new BitmapDrawable(BitmapFactory.decodeStream(localHttpURLConnection.getInputStream()));
  }

  public static void exitProcess()
  {
    Process.killProcess(Process.myPid());
  }

  public static String fromBase64(String paramString)
  {
    try
    {
      byte[] arrayOfByte = Base64.decode(paramString);
      return new String(arrayOfByte);
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }

  public static JSONObject inputStreamToJSONObject(InputStream paramInputStream)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream, "UTF-8"));
      try
      {
        while (true)
        {
          String str = localBufferedReader.readLine();
          if (str == null)
            break;
          localStringBuilder.append(str);
        }
      }
      catch (Exception localException1)
      {
      }
      label49: localException1.printStackTrace();
      return null;
      JSONObject localJSONObject = new JSONObject(localStringBuilder.toString());
      return localJSONObject;
    }
    catch (Exception localException2)
    {
      break label49;
    }
  }

  public static String inputStreamToString(InputStream paramInputStream)
  {
    InputStreamReader localInputStreamReader = new InputStreamReader(paramInputStream);
    StringBuilder localStringBuilder = new StringBuilder();
    BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader);
    try
    {
      String str;
      for (Object localObject = localBufferedReader.readLine(); localObject != null; localObject = str)
      {
        localStringBuilder.append((String)localObject);
        str = localBufferedReader.readLine();
      }
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return (String)localStringBuilder.toString();
  }

  public static boolean isChemeDark(int paramInt)
  {
    int i = 0xFF & paramInt >> 16;
    int j = 0xFF & paramInt >> 8;
    int k = 0xFF & paramInt >> 0;
    return 0.299D * i + 0.587D * j + 0.114D * k > 127.0D;
  }

  public static byte[] loadImageFromResource(Integer paramInteger, Activity paramActivity)
  {
    Bitmap localBitmap = ((BitmapDrawable)paramActivity.getResources().getDrawable(paramInteger.intValue())).getBitmap();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] loadImageFromUrl(String paramString)
  {
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeStream(new URL(paramString).openConnection().getInputStream());
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      return arrayOfByte;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      localMalformedURLException.printStackTrace();
      return null;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }

  public static String md5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      int i = arrayOfByte.length;
      StringBuilder localStringBuilder = new StringBuilder(i << 1);
      for (int j = 0; j < i; j++)
      {
        localStringBuilder.append(Character.forDigit((0xF0 & arrayOfByte[j]) >> 4, 16));
        localStringBuilder.append(Character.forDigit(0xF & arrayOfByte[j], 16));
      }
      String str = localStringBuilder.toString();
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    return null;
  }

  public static boolean networkAvailable(Activity paramActivity)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
  }

  public static String newTempFilePath(Activity paramActivity)
  {
    String str = CheckTmpDirectory(paramActivity);
    if (str == null)
      return null;
    UUID localUUID = UUID.randomUUID();
    return str + "/" + String.valueOf(localUUID);
  }

  public static String removeSpec(String paramString)
  {
    String str = paramString;
    while (checkForSpec(str))
    {
      if (str.contains("&amp;"))
      {
        int i4 = str.indexOf("&amp;");
        char[] arrayOfChar9 = new char[i4];
        str.getChars(0, i4, arrayOfChar9, 0);
        int i5 = i4 + 5;
        char[] arrayOfChar10 = new char[str.length() - i5];
        str.getChars(i5, str.length(), arrayOfChar10, 0);
        str = new String(arrayOfChar9) + "&" + new String(arrayOfChar10);
        continue;
      }
      if (str.contains("&apos;"))
      {
        int i2 = str.indexOf("&apos;");
        char[] arrayOfChar7 = new char[i2];
        str.getChars(0, i2, arrayOfChar7, 0);
        int i3 = i2 + 6;
        char[] arrayOfChar8 = new char[str.length() - i3];
        str.getChars(i3, str.length(), arrayOfChar8, 0);
        str = new String(arrayOfChar7) + "'" + new String(arrayOfChar8);
        continue;
      }
      if (str.contains("&quot;"))
      {
        int n = str.indexOf("&quot;");
        char[] arrayOfChar5 = new char[n];
        str.getChars(0, n, arrayOfChar5, 0);
        int i1 = n + 6;
        char[] arrayOfChar6 = new char[str.length() - i1];
        str.getChars(i1, str.length(), arrayOfChar6, 0);
        str = new String(arrayOfChar5) + "\"" + new String(arrayOfChar6);
        continue;
      }
      if (str.contains("&lt;"))
      {
        int k = str.indexOf("&lt;");
        char[] arrayOfChar3 = new char[k];
        str.getChars(0, k, arrayOfChar3, 0);
        int m = k + 4;
        char[] arrayOfChar4 = new char[str.length() - m];
        str.getChars(m, str.length(), arrayOfChar4, 0);
        str = new String(arrayOfChar3) + "<" + new String(arrayOfChar4);
        continue;
      }
      if (str.contains("&qt;"))
      {
        int i = str.indexOf("&qt;");
        char[] arrayOfChar1 = new char[i];
        str.getChars(0, i, arrayOfChar1, 0);
        int j = i + 4;
        char[] arrayOfChar2 = new char[str.length() - j];
        str.getChars(j, str.length(), arrayOfChar2, 0);
        str = new String(arrayOfChar1) + ">" + new String(arrayOfChar2);
        continue;
      }
      if (str.contains("&#8217;"))
      {
        str = str.replace("&#8217;", "'");
        continue;
      }
      if (str.contains("&#8220;"))
      {
        str = str.replace("&#8220;", "\"");
        continue;
      }
      if (str.contains("&#8221;"))
      {
        str = str.replace("&#8221;", "\"");
        continue;
      }
      if (str.contains("&#124;"))
      {
        str = str.replace("&#124;", "|");
        continue;
      }
      if (str.contains("&#8211;"))
      {
        str = str.replace("&#8211;", "-");
        continue;
      }
      if (!str.contains("\020"))
        continue;
      str = str.replace("\020", "");
    }
    return str;
  }

  public static boolean sdcardAvailable()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }

  // ERROR //
  public static boolean streamToFile(InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 260	java/io/FileOutputStream
    //   5: dup
    //   6: new 57	java/io/File
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 60	java/io/File:<init>	(Ljava/lang/String;)V
    //   14: invokespecial 263	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   17: astore_3
    //   18: sipush 1024
    //   21: newarray byte
    //   23: astore 8
    //   25: aload_0
    //   26: aload 8
    //   28: invokevirtual 541	java/io/InputStream:read	([B)I
    //   31: istore 9
    //   33: iload 9
    //   35: iconst_m1
    //   36: if_icmpeq +27 -> 63
    //   39: aload_3
    //   40: aload 8
    //   42: iconst_0
    //   43: iload 9
    //   45: invokevirtual 277	java/io/FileOutputStream:write	([BII)V
    //   48: aload_0
    //   49: aload 8
    //   51: invokevirtual 541	java/io/InputStream:read	([B)I
    //   54: istore 10
    //   56: iload 10
    //   58: istore 9
    //   60: goto -27 -> 33
    //   63: aload_3
    //   64: invokevirtual 299	java/io/FileOutputStream:close	()V
    //   67: aload_0
    //   68: invokevirtual 542	java/io/InputStream:close	()V
    //   71: iconst_1
    //   72: ireturn
    //   73: astore 11
    //   75: iconst_1
    //   76: ireturn
    //   77: astore 12
    //   79: aload_2
    //   80: invokevirtual 299	java/io/FileOutputStream:close	()V
    //   83: aload_0
    //   84: invokevirtual 542	java/io/InputStream:close	()V
    //   87: iconst_0
    //   88: ireturn
    //   89: astore 5
    //   91: iconst_0
    //   92: ireturn
    //   93: astore 6
    //   95: aload_2
    //   96: invokevirtual 299	java/io/FileOutputStream:close	()V
    //   99: aload_0
    //   100: invokevirtual 542	java/io/InputStream:close	()V
    //   103: aload 6
    //   105: athrow
    //   106: astore 7
    //   108: goto -5 -> 103
    //   111: astore 6
    //   113: aload_3
    //   114: astore_2
    //   115: goto -20 -> 95
    //   118: astore 4
    //   120: aload_3
    //   121: astore_2
    //   122: goto -43 -> 79
    //
    // Exception table:
    //   from	to	target	type
    //   63	71	73	java/io/IOException
    //   2	18	77	java/lang/Exception
    //   79	87	89	java/io/IOException
    //   2	18	93	finally
    //   95	103	106	java/io/IOException
    //   18	33	111	finally
    //   39	56	111	finally
    //   18	33	118	java/lang/Exception
    //   39	56	118	java/lang/Exception
  }

  public static String toBase64(String paramString)
  {
    try
    {
      String str = Base64.encodeBytes(paramString.getBytes());
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static boolean usedMobileNetwork(Activity paramActivity)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null);
    do
      return false;
    while (localNetworkInfo.getType() == 0);
    return true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.Utils
 * JD-Core Version:    0.6.0
 */