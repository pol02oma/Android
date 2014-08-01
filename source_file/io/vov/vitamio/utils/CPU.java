package io.vov.vitamio.utils;

import android.os.Build;
import java.util.HashMap;
import java.util.Map;

public class CPU
{
  public static final int FEATURE_ARM_NEON = 32;
  public static final int FEATURE_ARM_V5TE = 1;
  public static final int FEATURE_ARM_V6 = 2;
  public static final int FEATURE_ARM_V7A = 8;
  public static final int FEATURE_ARM_VFP = 4;
  public static final int FEATURE_ARM_VFPV3 = 16;
  public static final int FEATURE_MIPS = 128;
  public static final int FEATURE_X86 = 64;
  private static int cachedFeature;
  private static String cachedFeatureString;
  private static final Map<String, String> cpuinfo = new HashMap();

  static
  {
    cachedFeature = -1;
    cachedFeatureString = null;
  }

  private static int getCachedFeature()
  {
    if (cachedFeatureString == null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      if ((0x1 & cachedFeature) > 0)
        localStringBuffer.append("V5TE ");
      if ((0x2 & cachedFeature) > 0)
        localStringBuffer.append("V6 ");
      if ((0x4 & cachedFeature) > 0)
        localStringBuffer.append("VFP ");
      if ((0x8 & cachedFeature) > 0)
        localStringBuffer.append("V7A ");
      if ((0x10 & cachedFeature) > 0)
        localStringBuffer.append("VFPV3 ");
      if ((0x20 & cachedFeature) > 0)
        localStringBuffer.append("NEON ");
      if ((0x40 & cachedFeature) > 0)
        localStringBuffer.append("X86 ");
      if ((0x80 & cachedFeature) > 0)
        localStringBuffer.append("MIPS ");
      cachedFeatureString = localStringBuffer.toString();
    }
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = cachedFeatureString;
    Log.d("GET CPU FATURE: %s", arrayOfObject);
    return cachedFeature;
  }

  // ERROR //
  public static int getFeature()
  {
    // Byte code:
    //   0: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   3: ifle +7 -> 10
    //   6: invokestatic 87	io/vov/vitamio/utils/CPU:getCachedFeature	()I
    //   9: ireturn
    //   10: iconst_1
    //   11: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   14: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   17: invokeinterface 93 1 0
    //   22: ifeq +121 -> 143
    //   25: aconst_null
    //   26: astore 13
    //   28: new 95	java/io/BufferedReader
    //   31: dup
    //   32: new 97	java/io/FileReader
    //   35: dup
    //   36: new 99	java/io/File
    //   39: dup
    //   40: ldc 101
    //   42: invokespecial 104	java/io/File:<init>	(Ljava/lang/String;)V
    //   45: invokespecial 107	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   48: invokespecial 110	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   51: astore 14
    //   53: aload 14
    //   55: invokevirtual 113	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   58: astore 19
    //   60: aload 19
    //   62: ifnull +162 -> 224
    //   65: aload 19
    //   67: invokevirtual 118	java/lang/String:trim	()Ljava/lang/String;
    //   70: ldc 120
    //   72: invokevirtual 124	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   75: ifne -22 -> 53
    //   78: aload 19
    //   80: ldc 126
    //   82: invokevirtual 130	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   85: astore 20
    //   87: aload 20
    //   89: arraylength
    //   90: iconst_1
    //   91: if_icmple -38 -> 53
    //   94: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   97: aload 20
    //   99: iconst_0
    //   100: aaload
    //   101: invokevirtual 118	java/lang/String:trim	()Ljava/lang/String;
    //   104: aload 20
    //   106: iconst_1
    //   107: aaload
    //   108: invokevirtual 118	java/lang/String:trim	()Ljava/lang/String;
    //   111: invokeinterface 134 3 0
    //   116: pop
    //   117: goto -64 -> 53
    //   120: astore 17
    //   122: aload 14
    //   124: astore 13
    //   126: ldc 136
    //   128: aload 17
    //   130: invokestatic 140	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   133: aload 13
    //   135: ifnull +8 -> 143
    //   138: aload 13
    //   140: invokevirtual 143	java/io/BufferedReader:close	()V
    //   143: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   146: invokeinterface 93 1 0
    //   151: ifne +342 -> 493
    //   154: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   157: invokeinterface 147 1 0
    //   162: invokeinterface 153 1 0
    //   167: astore_0
    //   168: aload_0
    //   169: invokeinterface 158 1 0
    //   174: ifeq +114 -> 288
    //   177: aload_0
    //   178: invokeinterface 162 1 0
    //   183: checkcast 115	java/lang/String
    //   186: astore 11
    //   188: iconst_2
    //   189: anewarray 4	java/lang/Object
    //   192: astore 12
    //   194: aload 12
    //   196: iconst_0
    //   197: aload 11
    //   199: aastore
    //   200: aload 12
    //   202: iconst_1
    //   203: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   206: aload 11
    //   208: invokeinterface 166 2 0
    //   213: aastore
    //   214: ldc 168
    //   216: aload 12
    //   218: invokestatic 78	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   221: goto -53 -> 168
    //   224: aload 14
    //   226: ifnull -83 -> 143
    //   229: aload 14
    //   231: invokevirtual 143	java/io/BufferedReader:close	()V
    //   234: goto -91 -> 143
    //   237: astore 22
    //   239: ldc 136
    //   241: aload 22
    //   243: invokestatic 140	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   246: goto -103 -> 143
    //   249: astore 18
    //   251: ldc 136
    //   253: aload 18
    //   255: invokestatic 140	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   258: goto -115 -> 143
    //   261: astore 15
    //   263: aload 13
    //   265: ifnull +8 -> 273
    //   268: aload 13
    //   270: invokevirtual 143	java/io/BufferedReader:close	()V
    //   273: aload 15
    //   275: athrow
    //   276: astore 16
    //   278: ldc 136
    //   280: aload 16
    //   282: invokestatic 140	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   285: goto -12 -> 273
    //   288: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   291: ldc 170
    //   293: invokeinterface 166 2 0
    //   298: checkcast 115	java/lang/String
    //   301: astore_1
    //   302: aload_1
    //   303: invokestatic 175	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   306: ifne +274 -> 580
    //   309: aload_1
    //   310: invokestatic 181	io/vov/vitamio/utils/StringUtils:convertToInt	(Ljava/lang/String;)I
    //   313: istore 9
    //   315: iconst_1
    //   316: anewarray 4	java/lang/Object
    //   319: astore 10
    //   321: aload 10
    //   323: iconst_0
    //   324: iload 9
    //   326: invokestatic 187	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   329: aastore
    //   330: ldc 189
    //   332: aload 10
    //   334: invokestatic 78	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   337: iload 9
    //   339: bipush 7
    //   341: if_icmplt +156 -> 497
    //   344: iconst_1
    //   345: istore 5
    //   347: iconst_1
    //   348: istore 6
    //   350: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   353: ldc 191
    //   355: invokeinterface 166 2 0
    //   360: checkcast 115	java/lang/String
    //   363: astore 7
    //   365: aload 7
    //   367: ifnull +29 -> 396
    //   370: aload 7
    //   372: ldc 193
    //   374: invokevirtual 196	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   377: ifne +13 -> 390
    //   380: aload 7
    //   382: ldc 198
    //   384: invokevirtual 196	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   387: ifeq +9 -> 396
    //   390: iconst_1
    //   391: istore 5
    //   393: iconst_1
    //   394: istore 6
    //   396: aload 7
    //   398: ifnull +29 -> 427
    //   401: aload 7
    //   403: ldc 200
    //   405: invokevirtual 196	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   408: ifne +13 -> 421
    //   411: aload 7
    //   413: ldc 202
    //   415: invokevirtual 196	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   418: ifeq +9 -> 427
    //   421: iconst_1
    //   422: istore 5
    //   424: iconst_0
    //   425: istore 6
    //   427: iload 5
    //   429: ifeq +11 -> 440
    //   432: iconst_2
    //   433: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   436: ior
    //   437: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   440: iload 6
    //   442: ifeq +12 -> 454
    //   445: bipush 8
    //   447: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   450: ior
    //   451: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   454: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   457: ldc 204
    //   459: invokeinterface 166 2 0
    //   464: checkcast 115	java/lang/String
    //   467: astore 8
    //   469: aload 8
    //   471: ifnull +22 -> 493
    //   474: aload 8
    //   476: ldc 206
    //   478: invokevirtual 196	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   481: ifeq +56 -> 537
    //   484: bipush 52
    //   486: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   489: ior
    //   490: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   493: invokestatic 87	io/vov/vitamio/utils/CPU:getCachedFeature	()I
    //   496: ireturn
    //   497: iconst_0
    //   498: istore 5
    //   500: iconst_0
    //   501: istore 6
    //   503: iload 9
    //   505: bipush 6
    //   507: if_icmplt -157 -> 350
    //   510: iconst_1
    //   511: istore 5
    //   513: iconst_0
    //   514: istore 6
    //   516: goto -166 -> 350
    //   519: astore 4
    //   521: ldc 136
    //   523: aload 4
    //   525: invokestatic 140	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   528: iconst_0
    //   529: istore 5
    //   531: iconst_0
    //   532: istore 6
    //   534: goto -184 -> 350
    //   537: aload 8
    //   539: ldc 208
    //   541: invokevirtual 196	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   544: ifeq +15 -> 559
    //   547: bipush 20
    //   549: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   552: ior
    //   553: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   556: goto -63 -> 493
    //   559: aload 8
    //   561: ldc 210
    //   563: invokevirtual 196	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   566: ifeq -73 -> 493
    //   569: iconst_4
    //   570: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   573: ior
    //   574: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   577: goto -84 -> 493
    //   580: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   583: ldc 212
    //   585: invokeinterface 166 2 0
    //   590: checkcast 115	java/lang/String
    //   593: astore_2
    //   594: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   597: ldc 214
    //   599: invokeinterface 166 2 0
    //   604: checkcast 115	java/lang/String
    //   607: astore_3
    //   608: aload_2
    //   609: invokestatic 175	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   612: ifne +24 -> 636
    //   615: aload_2
    //   616: ldc 216
    //   618: invokevirtual 196	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   621: ifeq +15 -> 636
    //   624: bipush 64
    //   626: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   629: ior
    //   630: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   633: goto -140 -> 493
    //   636: aload_3
    //   637: invokestatic 175	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   640: ifne -147 -> 493
    //   643: aload_3
    //   644: ldc 218
    //   646: invokevirtual 196	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   649: ifeq -156 -> 493
    //   652: sipush 128
    //   655: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   658: ior
    //   659: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   662: goto -169 -> 493
    //   665: astore 15
    //   667: aload 14
    //   669: astore 13
    //   671: goto -408 -> 263
    //   674: astore 17
    //   676: aconst_null
    //   677: astore 13
    //   679: goto -553 -> 126
    //
    // Exception table:
    //   from	to	target	type
    //   53	60	120	java/lang/Exception
    //   65	117	120	java/lang/Exception
    //   229	234	237	java/io/IOException
    //   138	143	249	java/io/IOException
    //   28	53	261	finally
    //   126	133	261	finally
    //   268	273	276	java/io/IOException
    //   309	337	519	java/lang/NumberFormatException
    //   53	60	665	finally
    //   65	117	665	finally
    //   28	53	674	java/lang/Exception
  }

  public static String getFeatureString()
  {
    getFeature();
    return cachedFeatureString;
  }

  public static boolean isDroidXDroid2()
  {
    return (Build.MODEL.trim().equalsIgnoreCase("DROIDX")) || (Build.MODEL.trim().equalsIgnoreCase("DROID2")) || (Build.FINGERPRINT.toLowerCase().contains("shadow")) || (Build.FINGERPRINT.toLowerCase().contains("droid2"));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.utils.CPU
 * JD-Core Version:    0.6.0
 */