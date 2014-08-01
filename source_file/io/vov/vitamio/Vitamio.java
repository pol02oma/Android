package io.vov.vitamio;

import android.content.Context;
import io.vov.vitamio.utils.CPU;
import java.util.ArrayList;
import java.util.List;
import java.util.List<Ljava.lang.String;>;

public class Vitamio
{
  private static final String[] LIBS_ARM_CODECS = { "libvvo.7.so", "libvvo.8.so", "libffmpeg.so", "libOMX.9.so", "libOMX.11.so", "libOMX.14.so", "libOMX.18.so" };
  private static final String[] LIBS_AV;
  private static final String LIBS_LOCK = ".lock";
  private static final String[] LIBS_MIPS_CODECS;
  private static final String[] LIBS_PLAYER;
  private static final String[] LIBS_SCANNER;
  private static final String[] LIBS_X86_CODECS = { "libffmpeg.so", "libOMX.9.so", "libOMX.14.so", "libOMX.18.so" };
  private static final int VITAMIO_ARMV6 = 60;
  private static final int VITAMIO_ARMV6_VFP = 61;
  private static final int VITAMIO_ARMV7_NEON = 71;
  private static final int VITAMIO_ARMV7_VFPV3 = 70;
  private static final int VITAMIO_MIPS = 40;
  private static final int VITAMIO_NOT_SUPPORTED = -1;
  private static final int VITAMIO_X86 = 50;
  private static String vitamioLibraryPath;
  private static String vitamioPackage;
  private static final int vitamioType;

  static
  {
    LIBS_MIPS_CODECS = new String[] { "libffmpeg.so", "libOMX.14.so" };
    LIBS_PLAYER = new String[] { "libvplayer.so" };
    LIBS_SCANNER = new String[] { "libvscanner.so" };
    LIBS_AV = new String[] { "libvao.0.so", "libvvo.0.so", "libvvo.9.so", "libvvo.j.so" };
    int i = CPU.getFeature();
    if ((i & 0x20) > 0)
      vitamioType = 71;
    while (true)
    {
      System.loadLibrary("vinit");
      return;
      if (((i & 0x10) > 0) && ((i & 0x8) > 0))
      {
        vitamioType = 70;
        continue;
      }
      if (((i & 0x4) > 0) && ((i & 0x2) > 0))
      {
        vitamioType = 61;
        continue;
      }
      if ((i & 0x2) > 0)
      {
        vitamioType = 60;
        continue;
      }
      if ((i & 0x40) > 0)
      {
        vitamioType = 50;
        continue;
      }
      if ((i & 0x80) > 0)
      {
        vitamioType = 40;
        continue;
      }
      vitamioType = -1;
    }
  }

  // ERROR //
  private static String copyCompressedLib(Context paramContext, int paramInt, String paramString)
  {
    // Byte code:
    //   0: sipush 1024
    //   3: newarray byte
    //   5: astore_3
    //   6: aconst_null
    //   7: astore 4
    //   9: aconst_null
    //   10: astore 5
    //   12: aconst_null
    //   13: astore 6
    //   15: aconst_null
    //   16: astore 7
    //   18: invokestatic 102	io/vov/vitamio/Vitamio:getLibraryPath	()Ljava/lang/String;
    //   21: astore 13
    //   23: new 104	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   30: aload 13
    //   32: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: aload_2
    //   36: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: astore 7
    //   44: new 114	java/io/File
    //   47: dup
    //   48: aload 13
    //   50: invokespecial 116	java/io/File:<init>	(Ljava/lang/String;)V
    //   53: astore 14
    //   55: aload 14
    //   57: invokevirtual 120	java/io/File:exists	()Z
    //   60: istore 15
    //   62: aconst_null
    //   63: astore 5
    //   65: aconst_null
    //   66: astore 6
    //   68: aconst_null
    //   69: astore 4
    //   71: iload 15
    //   73: ifeq +30 -> 103
    //   76: aload 14
    //   78: invokevirtual 123	java/io/File:isDirectory	()Z
    //   81: istore 16
    //   83: aconst_null
    //   84: astore 5
    //   86: aconst_null
    //   87: astore 6
    //   89: aconst_null
    //   90: astore 4
    //   92: iload 16
    //   94: ifne +9 -> 103
    //   97: aload 14
    //   99: invokevirtual 126	java/io/File:delete	()Z
    //   102: pop
    //   103: aload 14
    //   105: invokevirtual 120	java/io/File:exists	()Z
    //   108: istore 18
    //   110: aconst_null
    //   111: astore 5
    //   113: aconst_null
    //   114: astore 6
    //   116: aconst_null
    //   117: astore 4
    //   119: iload 18
    //   121: ifne +9 -> 130
    //   124: aload 14
    //   126: invokevirtual 129	java/io/File:mkdirs	()Z
    //   129: pop
    //   130: new 114	java/io/File
    //   133: dup
    //   134: aload 7
    //   136: invokespecial 116	java/io/File:<init>	(Ljava/lang/String;)V
    //   139: astore 20
    //   141: aload 20
    //   143: invokevirtual 120	java/io/File:exists	()Z
    //   146: istore 21
    //   148: aconst_null
    //   149: astore 5
    //   151: aconst_null
    //   152: astore 6
    //   154: aconst_null
    //   155: astore 4
    //   157: iload 21
    //   159: ifeq +30 -> 189
    //   162: aload 20
    //   164: invokevirtual 132	java/io/File:isFile	()Z
    //   167: istore 22
    //   169: aconst_null
    //   170: astore 5
    //   172: aconst_null
    //   173: astore 6
    //   175: aconst_null
    //   176: astore 4
    //   178: iload 22
    //   180: ifne +9 -> 189
    //   183: aload 20
    //   185: invokevirtual 126	java/io/File:delete	()Z
    //   188: pop
    //   189: aload 20
    //   191: invokevirtual 120	java/io/File:exists	()Z
    //   194: istore 24
    //   196: aconst_null
    //   197: astore 5
    //   199: aconst_null
    //   200: astore 6
    //   202: aconst_null
    //   203: astore 4
    //   205: iload 24
    //   207: ifne +9 -> 216
    //   210: aload 20
    //   212: invokevirtual 135	java/io/File:createNewFile	()Z
    //   215: pop
    //   216: aload_0
    //   217: invokevirtual 141	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   220: iload_1
    //   221: invokevirtual 147	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   224: astore 4
    //   226: new 149	java/io/BufferedInputStream
    //   229: dup
    //   230: aload 4
    //   232: invokespecial 152	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   235: astore 11
    //   237: new 154	java/io/FileOutputStream
    //   240: dup
    //   241: aload 7
    //   243: invokespecial 155	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   246: astore 12
    //   248: aload 11
    //   250: aload_3
    //   251: invokevirtual 159	java/io/BufferedInputStream:read	([B)I
    //   254: iconst_m1
    //   255: if_icmpeq +69 -> 324
    //   258: aload 12
    //   260: aload_3
    //   261: invokevirtual 163	java/io/FileOutputStream:write	([B)V
    //   264: goto -16 -> 248
    //   267: astore 10
    //   269: aload 12
    //   271: astore 6
    //   273: aload 11
    //   275: astore 5
    //   277: ldc 165
    //   279: aload 10
    //   281: invokestatic 171	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   284: aload 6
    //   286: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   289: aload 5
    //   291: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   294: aload 4
    //   296: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   299: aconst_null
    //   300: areturn
    //   301: astore 9
    //   303: ldc 165
    //   305: aload 9
    //   307: invokestatic 171	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   310: goto -94 -> 216
    //   313: astore 10
    //   315: aconst_null
    //   316: astore 5
    //   318: aconst_null
    //   319: astore 6
    //   321: goto -44 -> 277
    //   324: aload 12
    //   326: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   329: aload 11
    //   331: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   334: aload 4
    //   336: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   339: aload 7
    //   341: areturn
    //   342: astore 8
    //   344: aload 6
    //   346: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   349: aload 5
    //   351: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   354: aload 4
    //   356: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   359: aload 8
    //   361: athrow
    //   362: astore 8
    //   364: aload 11
    //   366: astore 5
    //   368: aconst_null
    //   369: astore 6
    //   371: goto -27 -> 344
    //   374: astore 8
    //   376: aload 12
    //   378: astore 6
    //   380: aload 11
    //   382: astore 5
    //   384: goto -40 -> 344
    //   387: astore 10
    //   389: aload 11
    //   391: astore 5
    //   393: aconst_null
    //   394: astore 6
    //   396: goto -119 -> 277
    //
    // Exception table:
    //   from	to	target	type
    //   248	264	267	java/lang/Exception
    //   18	62	301	java/lang/Exception
    //   76	83	301	java/lang/Exception
    //   97	103	301	java/lang/Exception
    //   103	110	301	java/lang/Exception
    //   124	130	301	java/lang/Exception
    //   130	148	301	java/lang/Exception
    //   162	169	301	java/lang/Exception
    //   183	189	301	java/lang/Exception
    //   189	196	301	java/lang/Exception
    //   210	216	301	java/lang/Exception
    //   216	237	313	java/lang/Exception
    //   303	310	313	java/lang/Exception
    //   18	62	342	finally
    //   76	83	342	finally
    //   97	103	342	finally
    //   103	110	342	finally
    //   124	130	342	finally
    //   130	148	342	finally
    //   162	169	342	finally
    //   183	189	342	finally
    //   189	196	342	finally
    //   210	216	342	finally
    //   216	237	342	finally
    //   277	284	342	finally
    //   303	310	342	finally
    //   237	248	362	finally
    //   248	264	374	finally
    //   237	248	387	java/lang/Exception
  }

  // ERROR //
  private static boolean extractLibs(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: invokestatic 185	java/lang/System:currentTimeMillis	()J
    //   3: lstore_2
    //   4: aload_0
    //   5: invokestatic 191	io/vov/vitamio/utils/ContextUtils:getVersionCode	(Landroid/content/Context;)I
    //   8: istore 4
    //   10: new 104	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   17: ldc 193
    //   19: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: iload 4
    //   24: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   27: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: iconst_0
    //   31: anewarray 4	java/lang/Object
    //   34: invokestatic 200	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   37: new 114	java/io/File
    //   40: dup
    //   41: new 104	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   48: invokestatic 102	io/vov/vitamio/Vitamio:getLibraryPath	()Ljava/lang/String;
    //   51: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: ldc 11
    //   56: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: invokespecial 116	java/io/File:<init>	(Ljava/lang/String;)V
    //   65: astore 5
    //   67: aload 5
    //   69: invokevirtual 120	java/io/File:exists	()Z
    //   72: ifeq +9 -> 81
    //   75: aload 5
    //   77: invokevirtual 126	java/io/File:delete	()Z
    //   80: pop
    //   81: aload_0
    //   82: iload_1
    //   83: ldc 202
    //   85: invokestatic 204	io/vov/vitamio/Vitamio:copyCompressedLib	(Landroid/content/Context;ILjava/lang/String;)Ljava/lang/String;
    //   88: astore 6
    //   90: new 104	java/lang/StringBuilder
    //   93: dup
    //   94: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   97: ldc 206
    //   99: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: invokestatic 185	java/lang/System:currentTimeMillis	()J
    //   105: lload_2
    //   106: lsub
    //   107: l2d
    //   108: ldc2_w 207
    //   111: ddiv
    //   112: invokevirtual 211	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   115: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   118: iconst_0
    //   119: anewarray 4	java/lang/Object
    //   122: invokestatic 200	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   125: aload 6
    //   127: invokestatic 102	io/vov/vitamio/Vitamio:getLibraryPath	()Ljava/lang/String;
    //   130: invokestatic 214	io/vov/vitamio/Vitamio:getVitamioType	()I
    //   133: invokestatic 218	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   136: invokestatic 222	io/vov/vitamio/Vitamio:native_initializeLibs	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   139: istore 7
    //   141: new 114	java/io/File
    //   144: dup
    //   145: aload 6
    //   147: invokespecial 116	java/io/File:<init>	(Ljava/lang/String;)V
    //   150: invokevirtual 126	java/io/File:delete	()Z
    //   153: pop
    //   154: aconst_null
    //   155: astore 9
    //   157: aload 5
    //   159: invokevirtual 135	java/io/File:createNewFile	()Z
    //   162: pop
    //   163: new 224	java/io/FileWriter
    //   166: dup
    //   167: aload 5
    //   169: invokespecial 227	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   172: astore 13
    //   174: aload 13
    //   176: iload 4
    //   178: invokestatic 218	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   181: invokevirtual 229	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   184: new 104	java/lang/StringBuilder
    //   187: dup
    //   188: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   191: ldc 231
    //   193: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: iload 7
    //   198: invokevirtual 234	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   201: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   204: iconst_0
    //   205: anewarray 4	java/lang/Object
    //   208: invokestatic 200	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   211: new 104	java/lang/StringBuilder
    //   214: dup
    //   215: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   218: ldc 236
    //   220: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: invokestatic 185	java/lang/System:currentTimeMillis	()J
    //   226: lload_2
    //   227: lsub
    //   228: l2d
    //   229: ldc2_w 207
    //   232: ddiv
    //   233: invokevirtual 211	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   236: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: iconst_0
    //   240: anewarray 4	java/lang/Object
    //   243: invokestatic 200	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   246: aload 13
    //   248: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   251: iconst_1
    //   252: ireturn
    //   253: astore 11
    //   255: ldc 238
    //   257: aload 11
    //   259: invokestatic 171	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   262: new 104	java/lang/StringBuilder
    //   265: dup
    //   266: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   269: ldc 231
    //   271: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: iload 7
    //   276: invokevirtual 234	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   279: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   282: iconst_0
    //   283: anewarray 4	java/lang/Object
    //   286: invokestatic 200	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   289: new 104	java/lang/StringBuilder
    //   292: dup
    //   293: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   296: ldc 236
    //   298: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: invokestatic 185	java/lang/System:currentTimeMillis	()J
    //   304: lload_2
    //   305: lsub
    //   306: l2d
    //   307: ldc2_w 207
    //   310: ddiv
    //   311: invokevirtual 211	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   314: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   317: iconst_0
    //   318: anewarray 4	java/lang/Object
    //   321: invokestatic 200	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   324: aload 9
    //   326: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   329: iconst_0
    //   330: ireturn
    //   331: astore 10
    //   333: new 104	java/lang/StringBuilder
    //   336: dup
    //   337: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   340: ldc 231
    //   342: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: iload 7
    //   347: invokevirtual 234	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   350: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   353: iconst_0
    //   354: anewarray 4	java/lang/Object
    //   357: invokestatic 200	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   360: new 104	java/lang/StringBuilder
    //   363: dup
    //   364: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   367: ldc 236
    //   369: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: invokestatic 185	java/lang/System:currentTimeMillis	()J
    //   375: lload_2
    //   376: lsub
    //   377: l2d
    //   378: ldc2_w 207
    //   381: ddiv
    //   382: invokevirtual 211	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   385: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   388: iconst_0
    //   389: anewarray 4	java/lang/Object
    //   392: invokestatic 200	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   395: aload 9
    //   397: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   400: aload 10
    //   402: athrow
    //   403: astore 10
    //   405: aload 13
    //   407: astore 9
    //   409: goto -76 -> 333
    //   412: astore 11
    //   414: aload 13
    //   416: astore 9
    //   418: goto -163 -> 255
    //
    // Exception table:
    //   from	to	target	type
    //   157	174	253	java/io/IOException
    //   157	174	331	finally
    //   255	262	331	finally
    //   174	184	403	finally
    //   174	184	412	java/io/IOException
  }

  public static final String getLibraryPath()
  {
    return vitamioLibraryPath;
  }

  private static final List<String> getRequiredLibs()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (String[][])null;
    switch (vitamioType)
    {
    default:
    case 60:
    case 61:
    case 70:
    case 71:
    case 50:
    case 40:
    }
    while (localObject1 == null)
    {
      return localArrayList;
      localObject1 = new String[4][];
      localObject1[0] = LIBS_ARM_CODECS;
      localObject1[1] = LIBS_PLAYER;
      localObject1[2] = LIBS_SCANNER;
      localObject1[3] = LIBS_AV;
      continue;
      localObject1 = new String[4][];
      localObject1[0] = LIBS_X86_CODECS;
      localObject1[1] = LIBS_PLAYER;
      localObject1[2] = LIBS_SCANNER;
      localObject1[3] = LIBS_AV;
      continue;
      localObject1 = new String[4][];
      localObject1[0] = LIBS_MIPS_CODECS;
      localObject1[1] = LIBS_PLAYER;
      localObject1[2] = LIBS_SCANNER;
      localObject1[3] = LIBS_AV;
    }
    for (Object localObject3 : localObject1)
    {
      int k = localObject3.length;
      for (int m = 0; m < k; m++)
        localArrayList.add(localObject3[m]);
    }
    localArrayList.add(".lock");
    return (List<String>)localArrayList;
  }

  public static String getVitamioPackage()
  {
    return vitamioPackage;
  }

  public static int getVitamioType()
  {
    return vitamioType;
  }

  public static boolean initialize(Context paramContext, int paramInt)
  {
    return (isInitialized(paramContext)) || (extractLibs(paramContext, paramInt));
  }

  // ERROR //
  public static boolean isInitialized(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 269	android/content/Context:getPackageName	()Ljava/lang/String;
    //   4: putstatic 257	io/vov/vitamio/Vitamio:vitamioPackage	Ljava/lang/String;
    //   7: new 104	java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   14: aload_0
    //   15: invokestatic 273	io/vov/vitamio/utils/ContextUtils:getDataDir	(Landroid/content/Context;)Ljava/lang/String;
    //   18: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: ldc_w 275
    //   24: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: putstatic 240	io/vov/vitamio/Vitamio:vitamioLibraryPath	Ljava/lang/String;
    //   33: new 114	java/io/File
    //   36: dup
    //   37: invokestatic 102	io/vov/vitamio/Vitamio:getLibraryPath	()Ljava/lang/String;
    //   40: invokespecial 116	java/io/File:<init>	(Ljava/lang/String;)V
    //   43: astore_1
    //   44: aload_1
    //   45: invokevirtual 120	java/io/File:exists	()Z
    //   48: ifeq +199 -> 247
    //   51: aload_1
    //   52: invokevirtual 123	java/io/File:isDirectory	()Z
    //   55: ifeq +192 -> 247
    //   58: aload_1
    //   59: invokevirtual 279	java/io/File:list	()[Ljava/lang/String;
    //   62: astore_2
    //   63: aload_2
    //   64: ifnull +183 -> 247
    //   67: aload_2
    //   68: invokestatic 285	java/util/Arrays:sort	([Ljava/lang/Object;)V
    //   71: invokestatic 287	io/vov/vitamio/Vitamio:getRequiredLibs	()Ljava/util/List;
    //   74: invokeinterface 291 1 0
    //   79: astore_3
    //   80: aload_3
    //   81: invokeinterface 296 1 0
    //   86: ifeq +40 -> 126
    //   89: aload_3
    //   90: invokeinterface 300 1 0
    //   95: checkcast 37	java/lang/String
    //   98: astore 13
    //   100: aload_2
    //   101: aload 13
    //   103: invokestatic 304	java/util/Arrays:binarySearch	([Ljava/lang/Object;Ljava/lang/Object;)I
    //   106: ifge -26 -> 80
    //   109: ldc_w 306
    //   112: iconst_1
    //   113: anewarray 4	java/lang/Object
    //   116: dup
    //   117: iconst_0
    //   118: aload 13
    //   120: aastore
    //   121: invokestatic 308	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   124: iconst_0
    //   125: ireturn
    //   126: new 114	java/io/File
    //   129: dup
    //   130: new 104	java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   137: invokestatic 102	io/vov/vitamio/Vitamio:getLibraryPath	()Ljava/lang/String;
    //   140: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc 11
    //   145: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: invokespecial 116	java/io/File:<init>	(Ljava/lang/String;)V
    //   154: astore 4
    //   156: aconst_null
    //   157: astore 5
    //   159: new 310	java/io/BufferedReader
    //   162: dup
    //   163: new 312	java/io/FileReader
    //   166: dup
    //   167: aload 4
    //   169: invokespecial 313	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   172: invokespecial 316	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   175: astore 6
    //   177: aload_0
    //   178: invokestatic 191	io/vov/vitamio/utils/ContextUtils:getVersionCode	(Landroid/content/Context;)I
    //   181: istore 10
    //   183: aload 6
    //   185: invokevirtual 319	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   188: invokestatic 324	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   191: invokevirtual 327	java/lang/Integer:intValue	()I
    //   194: istore 11
    //   196: iconst_2
    //   197: anewarray 4	java/lang/Object
    //   200: astore 12
    //   202: aload 12
    //   204: iconst_0
    //   205: iload 10
    //   207: invokestatic 330	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   210: aastore
    //   211: aload 12
    //   213: iconst_1
    //   214: iload 11
    //   216: invokestatic 330	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   219: aastore
    //   220: ldc_w 332
    //   223: aload 12
    //   225: invokestatic 335	io/vov/vitamio/utils/Log:i	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   228: iload 11
    //   230: iload 10
    //   232: if_icmpne +10 -> 242
    //   235: aload 6
    //   237: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   240: iconst_1
    //   241: ireturn
    //   242: aload 6
    //   244: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   247: iconst_0
    //   248: ireturn
    //   249: astore 7
    //   251: ldc_w 337
    //   254: aload 7
    //   256: invokestatic 171	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   259: aload 5
    //   261: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   264: goto -17 -> 247
    //   267: astore 9
    //   269: ldc_w 337
    //   272: aload 9
    //   274: invokestatic 171	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   277: aload 5
    //   279: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   282: goto -35 -> 247
    //   285: astore 8
    //   287: aload 5
    //   289: invokestatic 177	io/vov/vitamio/utils/IOUtils:closeSilently	(Ljava/io/Closeable;)V
    //   292: aload 8
    //   294: athrow
    //   295: astore 8
    //   297: aload 6
    //   299: astore 5
    //   301: goto -14 -> 287
    //   304: astore 9
    //   306: aload 6
    //   308: astore 5
    //   310: goto -41 -> 269
    //   313: astore 7
    //   315: aload 6
    //   317: astore 5
    //   319: goto -68 -> 251
    //
    // Exception table:
    //   from	to	target	type
    //   159	177	249	java/io/IOException
    //   159	177	267	java/lang/NumberFormatException
    //   159	177	285	finally
    //   251	259	285	finally
    //   269	277	285	finally
    //   177	228	295	finally
    //   177	228	304	java/lang/NumberFormatException
    //   177	228	313	java/io/IOException
  }

  private static native boolean native_initializeLibs(String paramString1, String paramString2, String paramString3);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.Vitamio
 * JD-Core Version:    0.6.0
 */