package com.flurry.sdk;

import java.io.File;

public final class et
{
  private static String a = et.class.getSimpleName();

  // ERROR //
  @java.lang.Deprecated
  public static void a(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: iconst_4
    //   5: getstatic 16	com/flurry/sdk/et:a	Ljava/lang/String;
    //   8: ldc 25
    //   10: invokestatic 30	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   13: return
    //   14: aload_1
    //   15: ifnonnull +38 -> 53
    //   18: iconst_4
    //   19: getstatic 16	com/flurry/sdk/et:a	Ljava/lang/String;
    //   22: new 32	java/lang/StringBuilder
    //   25: dup
    //   26: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   29: ldc 35
    //   31: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: aload_0
    //   35: invokevirtual 44	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   38: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: invokestatic 30	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   47: aload_0
    //   48: invokevirtual 51	java/io/File:delete	()Z
    //   51: pop
    //   52: return
    //   53: iconst_4
    //   54: getstatic 16	com/flurry/sdk/et:a	Ljava/lang/String;
    //   57: new 32	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   64: ldc 53
    //   66: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: aload_0
    //   70: invokevirtual 44	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   73: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokestatic 30	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   82: new 55	java/io/FileOutputStream
    //   85: dup
    //   86: aload_0
    //   87: invokespecial 58	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   90: astore_2
    //   91: aload_2
    //   92: aload_1
    //   93: invokevirtual 64	java/lang/String:getBytes	()[B
    //   96: invokevirtual 68	java/io/FileOutputStream:write	([B)V
    //   99: aload_2
    //   100: invokestatic 73	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   103: return
    //   104: astore_3
    //   105: aconst_null
    //   106: astore_2
    //   107: bipush 6
    //   109: getstatic 16	com/flurry/sdk/et:a	Ljava/lang/String;
    //   112: ldc 75
    //   114: aload_3
    //   115: invokestatic 78	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   118: aload_2
    //   119: invokestatic 73	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   122: return
    //   123: astore 4
    //   125: aconst_null
    //   126: astore_2
    //   127: aload_2
    //   128: invokestatic 73	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   131: aload 4
    //   133: athrow
    //   134: astore 4
    //   136: goto -9 -> 127
    //   139: astore_3
    //   140: goto -33 -> 107
    //
    // Exception table:
    //   from	to	target	type
    //   82	91	104	java/lang/Throwable
    //   82	91	123	finally
    //   91	99	134	finally
    //   107	118	134	finally
    //   91	99	139	java/lang/Throwable
  }

  public static boolean a(File paramFile)
  {
    if (paramFile == null);
    do
      return false;
    while (paramFile.getAbsoluteFile() == null);
    File localFile = paramFile.getParentFile();
    if (localFile == null)
      return true;
    if ((!localFile.mkdirs()) && (!localFile.isDirectory()))
    {
      ex.a(6, a, "Unable to create persistent dir: " + localFile);
      return false;
    }
    return true;
  }

  // ERROR //
  @java.lang.Deprecated
  public static String b(File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +10 -> 11
    //   4: aload_0
    //   5: invokevirtual 102	java/io/File:exists	()Z
    //   8: ifne +14 -> 22
    //   11: iconst_4
    //   12: getstatic 16	com/flurry/sdk/et:a	Ljava/lang/String;
    //   15: ldc 104
    //   17: invokestatic 30	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   20: aconst_null
    //   21: areturn
    //   22: iconst_4
    //   23: getstatic 16	com/flurry/sdk/et:a	Ljava/lang/String;
    //   26: new 32	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   33: ldc 106
    //   35: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_0
    //   39: invokevirtual 44	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   42: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokestatic 30	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   51: new 108	java/io/FileInputStream
    //   54: dup
    //   55: aload_0
    //   56: invokespecial 109	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   59: astore_1
    //   60: new 32	java/lang/StringBuilder
    //   63: dup
    //   64: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   67: astore_2
    //   68: sipush 1024
    //   71: newarray byte
    //   73: astore 5
    //   75: aload_1
    //   76: aload 5
    //   78: invokevirtual 113	java/io/FileInputStream:read	([B)I
    //   81: istore 6
    //   83: iload 6
    //   85: ifle +52 -> 137
    //   88: aload_2
    //   89: new 60	java/lang/String
    //   92: dup
    //   93: aload 5
    //   95: iconst_0
    //   96: iload 6
    //   98: invokespecial 116	java/lang/String:<init>	([BII)V
    //   101: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: goto -30 -> 75
    //   108: astore 4
    //   110: bipush 6
    //   112: getstatic 16	com/flurry/sdk/et:a	Ljava/lang/String;
    //   115: ldc 118
    //   117: aload 4
    //   119: invokestatic 78	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   122: aload_1
    //   123: invokestatic 73	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   126: aconst_null
    //   127: astore_2
    //   128: aload_2
    //   129: ifnull -109 -> 20
    //   132: aload_2
    //   133: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: areturn
    //   137: aload_1
    //   138: invokestatic 73	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   141: goto -13 -> 128
    //   144: astore 8
    //   146: aconst_null
    //   147: astore_1
    //   148: aload 8
    //   150: astore_3
    //   151: aload_1
    //   152: invokestatic 73	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   155: aload_3
    //   156: athrow
    //   157: astore_3
    //   158: goto -7 -> 151
    //   161: astore 4
    //   163: aconst_null
    //   164: astore_1
    //   165: goto -55 -> 110
    //
    // Exception table:
    //   from	to	target	type
    //   60	75	108	java/lang/Throwable
    //   75	83	108	java/lang/Throwable
    //   88	105	108	java/lang/Throwable
    //   51	60	144	finally
    //   60	75	157	finally
    //   75	83	157	finally
    //   88	105	157	finally
    //   110	122	157	finally
    //   51	60	161	java/lang/Throwable
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.et
 * JD-Core Version:    0.6.0
 */