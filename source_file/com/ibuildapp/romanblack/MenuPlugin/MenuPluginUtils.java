package com.ibuildapp.romanblack.MenuPlugin;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Arrays;

public class MenuPluginUtils
{
  public static int BackColorToFontColor(int paramInt)
  {
    int i = 0xFF & paramInt >> 16;
    int j = 0xFF & paramInt >> 8;
    int k = 0xFF & paramInt >> 0;
    if (0.299D * i + 0.587D * j + 0.114D * k > 127.0D)
      return -16777216;
    return -1;
  }

  public static void clearCache(String paramString1, String paramString2, String paramString3)
  {
    File[] arrayOfFile = new File(paramString3).listFiles();
    for (int i = 0; ; i++)
    {
      if (i < arrayOfFile.length)
      {
        if (!arrayOfFile[i].isFile())
          continue;
        String str = arrayOfFile[i].getName();
        if ((!str.contains(paramString1)) || (str.compareTo(paramString2) == 0))
          continue;
        arrayOfFile[i].delete();
      }
      return;
    }
  }

  public static String downloadImg(String paramString1, String paramString2)
  {
    try
    {
      URLConnection localURLConnection = new URL(URLDecoder.decode(paramString1)).openConnection();
      localURLConnection.setConnectTimeout(10000);
      localURLConnection.setReadTimeout(10000);
      localBufferedInputStream = new BufferedInputStream(localURLConnection.getInputStream());
      byte[] arrayOfByte = new byte[512];
      Arrays.fill(arrayOfByte, 0);
      File localFile1 = new File(paramString2);
      File localFile2 = new File(localFile1.getParent());
      if (!localFile2.exists())
        localFile2.mkdirs();
      if (!localFile1.exists())
        localFile1.createNewFile();
      localFileOutputStream = new FileOutputStream(localFile1);
      while (true)
      {
        int i = localBufferedInputStream.read(arrayOfByte, 0, 512);
        if (i == -1)
          break;
        localFileOutputStream.write(arrayOfByte, 0, i);
        Arrays.fill(arrayOfByte, 0);
      }
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      BufferedInputStream localBufferedInputStream;
      FileOutputStream localFileOutputStream;
      Log.e("IMAGE ADAPTER - SocketTimeoutException", "An error has occurred downloading the image: " + paramString1);
      return null;
      localBufferedInputStream.close();
      localFileOutputStream.flush();
      localFileOutputStream.close();
      Log.d("", "");
      return paramString2;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.e("IMAGE ADAPTER - IllegalArgumentException", "An error has occurred downloading the image: " + paramString1);
      return null;
    }
    catch (Exception localException)
    {
      Log.e("IMAGE ADAPTER + Exception", "An error has occurred downloading the image: " + paramString1);
    }
    return null;
  }

  public static String findCacheFile(String paramString1, String paramString2)
  {
    File[] arrayOfFile = new File(paramString2).listFiles();
    for (int i = 0; i < arrayOfFile.length; i++)
    {
      if (!arrayOfFile[i].isFile())
        continue;
      String str = arrayOfFile[i].getName();
      if (str.contains(paramString1))
        return str;
    }
    return null;
  }

  public static Bitmap getRoundedCornerBitmap(Bitmap paramBitmap, int paramInt)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    float f = paramInt;
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawRoundRect(localRectF, f, f, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }

  // ERROR //
  public static String getUrlHeaderLastModify(String paramString)
  {
    // Byte code:
    //   0: new 61	java/net/URL
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 68	java/net/URL:<init>	(Ljava/lang/String;)V
    //   8: astore_1
    //   9: aload_1
    //   10: invokevirtual 72	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   13: astore 8
    //   15: aload 8
    //   17: sipush 10000
    //   20: invokevirtual 78	java/net/URLConnection:setConnectTimeout	(I)V
    //   23: aload 8
    //   25: sipush 10000
    //   28: invokevirtual 81	java/net/URLConnection:setReadTimeout	(I)V
    //   31: aload 8
    //   33: invokevirtual 240	java/net/URLConnection:getHeaderFields	()Ljava/util/Map;
    //   36: astore 9
    //   38: aload 9
    //   40: ifnonnull +6 -> 46
    //   43: ldc 242
    //   45: areturn
    //   46: aload 9
    //   48: invokeinterface 248 1 0
    //   53: invokeinterface 254 1 0
    //   58: astore 10
    //   60: aload 10
    //   62: invokeinterface 259 1 0
    //   67: ifeq +57 -> 124
    //   70: aload 10
    //   72: invokeinterface 263 1 0
    //   77: checkcast 40	java/lang/String
    //   80: astore 11
    //   82: aload 11
    //   84: ifnull -24 -> 60
    //   87: aload 11
    //   89: ldc_w 265
    //   92: invokevirtual 48	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   95: ifne -35 -> 60
    //   98: aload 9
    //   100: aload 11
    //   102: invokeinterface 269 2 0
    //   107: checkcast 271	java/util/List
    //   110: iconst_0
    //   111: invokeinterface 274 2 0
    //   116: checkcast 40	java/lang/String
    //   119: astore 12
    //   121: aload 12
    //   123: areturn
    //   124: aconst_null
    //   125: areturn
    //   126: astore 16
    //   128: ldc 150
    //   130: ldc 150
    //   132: invokestatic 153	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   135: pop
    //   136: goto -12 -> 124
    //   139: astore 15
    //   141: ldc 150
    //   143: ldc 150
    //   145: invokestatic 153	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   148: pop
    //   149: goto -25 -> 124
    //   152: astore 6
    //   154: goto -13 -> 141
    //   157: astore 14
    //   159: goto -35 -> 124
    //   162: astore 5
    //   164: goto -40 -> 124
    //   167: astore_3
    //   168: goto -40 -> 128
    //   171: astore 13
    //   173: goto -49 -> 124
    //   176: astore_2
    //   177: goto -53 -> 124
    //
    // Exception table:
    //   from	to	target	type
    //   0	9	126	java/net/SocketTimeoutException
    //   0	9	139	java/lang/IllegalArgumentException
    //   9	38	152	java/lang/IllegalArgumentException
    //   46	60	152	java/lang/IllegalArgumentException
    //   60	82	152	java/lang/IllegalArgumentException
    //   87	121	152	java/lang/IllegalArgumentException
    //   0	9	157	java/io/IOException
    //   9	38	162	java/io/IOException
    //   46	60	162	java/io/IOException
    //   60	82	162	java/io/IOException
    //   87	121	162	java/io/IOException
    //   9	38	167	java/net/SocketTimeoutException
    //   46	60	167	java/net/SocketTimeoutException
    //   60	82	167	java/net/SocketTimeoutException
    //   87	121	167	java/net/SocketTimeoutException
    //   0	9	171	java/net/MalformedURLException
    //   9	38	176	java/net/MalformedURLException
    //   46	60	176	java/net/MalformedURLException
    //   60	82	176	java/net/MalformedURLException
    //   87	121	176	java/net/MalformedURLException
  }

  // ERROR //
  public static Bitmap proccessBitmap(String paramString)
  {
    // Byte code:
    //   0: new 280	android/graphics/BitmapFactory$Options
    //   3: dup
    //   4: invokespecial 281	android/graphics/BitmapFactory$Options:<init>	()V
    //   7: astore_1
    //   8: new 23	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 26	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: astore_2
    //   17: invokestatic 286	java/lang/System:gc	()V
    //   20: new 288	java/io/FileInputStream
    //   23: dup
    //   24: aload_2
    //   25: invokespecial 289	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   28: aconst_null
    //   29: aload_1
    //   30: invokestatic 295	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   33: astore 15
    //   35: aload 15
    //   37: astore 7
    //   39: aload 7
    //   41: areturn
    //   42: astore 13
    //   44: ldc 150
    //   46: ldc 150
    //   48: invokestatic 153	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   51: pop
    //   52: aconst_null
    //   53: astore 7
    //   55: goto -16 -> 39
    //   58: astore 8
    //   60: ldc 150
    //   62: ldc 150
    //   64: invokestatic 153	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   67: pop
    //   68: aconst_null
    //   69: areturn
    //   70: astore_3
    //   71: ldc 150
    //   73: ldc 150
    //   75: invokestatic 153	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   78: pop
    //   79: invokestatic 286	java/lang/System:gc	()V
    //   82: new 288	java/io/FileInputStream
    //   85: dup
    //   86: aload_2
    //   87: invokespecial 289	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   90: aconst_null
    //   91: aload_1
    //   92: invokestatic 295	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   95: astore 12
    //   97: aload 12
    //   99: astore 7
    //   101: goto -62 -> 39
    //   104: astore 10
    //   106: ldc 150
    //   108: ldc 150
    //   110: invokestatic 153	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aconst_null
    //   115: astore 7
    //   117: goto -78 -> 39
    //   120: astore 5
    //   122: ldc_w 297
    //   125: ldc_w 299
    //   128: invokestatic 141	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   131: pop
    //   132: aconst_null
    //   133: astore 7
    //   135: goto -96 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   17	35	42	java/lang/Exception
    //   8	17	58	java/lang/Exception
    //   44	52	58	java/lang/Exception
    //   71	82	58	java/lang/Exception
    //   106	114	58	java/lang/Exception
    //   122	132	58	java/lang/Exception
    //   17	35	70	java/lang/OutOfMemoryError
    //   82	97	104	java/lang/Exception
    //   82	97	120	java/lang/OutOfMemoryError
  }

  public static Bitmap proccessBitmap_v2(String paramString, int paramInt1, int paramInt2)
  {
    File localFile = new File(paramString);
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inSampleSize = 1;
    try
    {
      System.gc();
      Bitmap localBitmap3 = BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions);
      localObject = localBitmap3;
      int i = (((Bitmap)localObject).getWidth() - paramInt1) / 2;
      int j = (((Bitmap)localObject).getHeight() - paramInt2) / 2;
      ((paramInt1 - 4) / 0);
      ((paramInt2 - 4) / 0);
      Bitmap localBitmap1 = Bitmap.createBitmap((Bitmap)localObject, i, j, paramInt1, paramInt2, new Matrix(), true);
      ((Bitmap)localObject).recycle();
      return localBitmap1;
    }
    catch (Exception localException2)
    {
      while (true)
      {
        Log.d("", "");
        localObject = null;
      }
    }
    catch (OutOfMemoryError localOutOfMemoryError1)
    {
      while (true)
      {
        Object localObject;
        Log.d("", "");
        System.gc();
        try
        {
          Bitmap localBitmap2 = BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions);
          localObject = localBitmap2;
        }
        catch (Exception localException1)
        {
          Log.d("", "");
          localObject = null;
        }
        catch (OutOfMemoryError localOutOfMemoryError2)
        {
          Log.e("decodeImageFile", "OutOfMemoryError");
          localObject = null;
        }
      }
    }
  }

  // ERROR //
  public static Bitmap proccessBitmap_v3(String paramString, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: new 23	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 26	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore_3
    //   9: new 280	android/graphics/BitmapFactory$Options
    //   12: dup
    //   13: invokespecial 281	android/graphics/BitmapFactory$Options:<init>	()V
    //   16: astore 4
    //   18: aload 4
    //   20: iconst_1
    //   21: putfield 321	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   24: new 288	java/io/FileInputStream
    //   27: dup
    //   28: aload_3
    //   29: invokespecial 289	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   32: aconst_null
    //   33: aload 4
    //   35: invokestatic 295	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   38: pop
    //   39: aload 4
    //   41: getfield 324	android/graphics/BitmapFactory$Options:outWidth	I
    //   44: istore 8
    //   46: aload 4
    //   48: getfield 327	android/graphics/BitmapFactory$Options:outHeight	I
    //   51: istore 9
    //   53: iconst_1
    //   54: istore 10
    //   56: iload 8
    //   58: iconst_2
    //   59: idiv
    //   60: iload_1
    //   61: if_icmple +11 -> 72
    //   64: iload 9
    //   66: iconst_2
    //   67: idiv
    //   68: iload_2
    //   69: if_icmpgt +83 -> 152
    //   72: new 280	android/graphics/BitmapFactory$Options
    //   75: dup
    //   76: invokespecial 281	android/graphics/BitmapFactory$Options:<init>	()V
    //   79: astore 11
    //   81: aload 11
    //   83: iload 10
    //   85: putfield 305	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   88: invokestatic 286	java/lang/System:gc	()V
    //   91: new 288	java/io/FileInputStream
    //   94: dup
    //   95: aload_3
    //   96: invokespecial 289	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   99: aconst_null
    //   100: aload 11
    //   102: invokestatic 295	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   105: astore 25
    //   107: aload 25
    //   109: astore 17
    //   111: iload 8
    //   113: iload_1
    //   114: isub
    //   115: istore 18
    //   117: aload 17
    //   119: iload 18
    //   121: iconst_2
    //   122: idiv
    //   123: iload 9
    //   125: iload_2
    //   126: isub
    //   127: iconst_2
    //   128: idiv
    //   129: iload_1
    //   130: iload_2
    //   131: new 307	android/graphics/Matrix
    //   134: dup
    //   135: invokespecial 308	android/graphics/Matrix:<init>	()V
    //   138: iconst_1
    //   139: invokestatic 311	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
    //   142: astore 19
    //   144: aload 17
    //   146: invokevirtual 314	android/graphics/Bitmap:recycle	()V
    //   149: aload 19
    //   151: areturn
    //   152: iload 8
    //   154: iconst_2
    //   155: idiv
    //   156: istore 8
    //   158: iload 9
    //   160: iconst_2
    //   161: idiv
    //   162: istore 9
    //   164: iload 10
    //   166: iconst_2
    //   167: imul
    //   168: istore 10
    //   170: goto -114 -> 56
    //   173: astore 23
    //   175: ldc 150
    //   177: ldc 150
    //   179: invokestatic 153	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   182: pop
    //   183: aconst_null
    //   184: astore 17
    //   186: goto -75 -> 111
    //   189: astore 12
    //   191: ldc 150
    //   193: ldc 150
    //   195: invokestatic 141	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   198: pop
    //   199: aconst_null
    //   200: areturn
    //   201: astore 13
    //   203: ldc 150
    //   205: ldc 150
    //   207: invokestatic 153	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   210: pop
    //   211: invokestatic 286	java/lang/System:gc	()V
    //   214: new 288	java/io/FileInputStream
    //   217: dup
    //   218: aload_3
    //   219: invokespecial 289	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   222: aconst_null
    //   223: aload 11
    //   225: invokestatic 295	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   228: astore 22
    //   230: aload 22
    //   232: astore 17
    //   234: goto -123 -> 111
    //   237: astore 20
    //   239: ldc 150
    //   241: ldc 150
    //   243: invokestatic 153	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   246: pop
    //   247: aconst_null
    //   248: astore 17
    //   250: goto -139 -> 111
    //   253: astore 15
    //   255: ldc_w 297
    //   258: ldc_w 299
    //   261: invokestatic 141	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   264: pop
    //   265: aconst_null
    //   266: astore 17
    //   268: goto -157 -> 111
    //   271: astore 5
    //   273: goto -82 -> 191
    //
    // Exception table:
    //   from	to	target	type
    //   88	107	173	java/lang/Exception
    //   81	88	189	java/io/FileNotFoundException
    //   88	107	189	java/io/FileNotFoundException
    //   117	149	189	java/io/FileNotFoundException
    //   175	183	189	java/io/FileNotFoundException
    //   203	214	189	java/io/FileNotFoundException
    //   214	230	189	java/io/FileNotFoundException
    //   239	247	189	java/io/FileNotFoundException
    //   255	265	189	java/io/FileNotFoundException
    //   88	107	201	java/lang/OutOfMemoryError
    //   214	230	237	java/lang/Exception
    //   214	230	253	java/lang/OutOfMemoryError
    //   24	53	271	java/io/FileNotFoundException
    //   56	72	271	java/io/FileNotFoundException
    //   72	81	271	java/io/FileNotFoundException
    //   152	164	271	java/io/FileNotFoundException
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MenuPlugin.MenuPluginUtils
 * JD-Core Version:    0.6.0
 */