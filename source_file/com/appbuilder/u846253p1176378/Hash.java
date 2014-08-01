package com.appbuilder.u846253p1176378;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash
{
  public static String MD5(File paramFile)
  {
    return calculateFileAlg(paramFile, "MD5");
  }

  public static String MD5(String paramString)
  {
    return calculateStringAlg(paramString, "MD5");
  }

  public static String SHA1(File paramFile)
  {
    return calculateFileAlg(paramFile, "SHA1");
  }

  public static String SHA1(String paramString)
  {
    return calculateStringAlg(paramString, "SHA1");
  }

  // ERROR //
  private static String calculateFileAlg(File paramFile, String paramString)
  {
    // Byte code:
    //   0: new 30	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 33	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   8: astore_2
    //   9: aload_1
    //   10: invokestatic 39	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   13: astore 9
    //   15: sipush 8192
    //   18: newarray byte
    //   20: astore 10
    //   22: aload_2
    //   23: aload 10
    //   25: invokevirtual 45	java/io/InputStream:read	([B)I
    //   28: istore 11
    //   30: iload 11
    //   32: ifle +40 -> 72
    //   35: aload 9
    //   37: aload 10
    //   39: iconst_0
    //   40: iload 11
    //   42: invokevirtual 49	java/security/MessageDigest:update	([BII)V
    //   45: goto -23 -> 22
    //   48: astore 7
    //   50: aload 7
    //   52: invokevirtual 52	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   55: aload_2
    //   56: invokevirtual 55	java/io/InputStream:close	()V
    //   59: ldc 57
    //   61: areturn
    //   62: astore 14
    //   64: aload 14
    //   66: invokevirtual 58	java/io/FileNotFoundException:printStackTrace	()V
    //   69: ldc 57
    //   71: areturn
    //   72: aload 9
    //   74: invokevirtual 62	java/security/MessageDigest:digest	()[B
    //   77: invokestatic 66	com/appbuilder/u846253p1176378/Hash:getHexString	([B)Ljava/lang/String;
    //   80: astore 12
    //   82: aload_2
    //   83: invokevirtual 55	java/io/InputStream:close	()V
    //   86: aload 12
    //   88: areturn
    //   89: astore 13
    //   91: aload 13
    //   93: invokevirtual 67	java/io/IOException:printStackTrace	()V
    //   96: aload 12
    //   98: areturn
    //   99: astore 8
    //   101: aload 8
    //   103: invokevirtual 67	java/io/IOException:printStackTrace	()V
    //   106: goto -47 -> 59
    //   109: astore 5
    //   111: aload 5
    //   113: invokevirtual 67	java/io/IOException:printStackTrace	()V
    //   116: aload_2
    //   117: invokevirtual 55	java/io/InputStream:close	()V
    //   120: goto -61 -> 59
    //   123: astore 6
    //   125: aload 6
    //   127: invokevirtual 67	java/io/IOException:printStackTrace	()V
    //   130: goto -71 -> 59
    //   133: astore_3
    //   134: aload_2
    //   135: invokevirtual 55	java/io/InputStream:close	()V
    //   138: aload_3
    //   139: athrow
    //   140: astore 4
    //   142: aload 4
    //   144: invokevirtual 67	java/io/IOException:printStackTrace	()V
    //   147: goto -9 -> 138
    //
    // Exception table:
    //   from	to	target	type
    //   9	22	48	java/security/NoSuchAlgorithmException
    //   22	30	48	java/security/NoSuchAlgorithmException
    //   35	45	48	java/security/NoSuchAlgorithmException
    //   72	82	48	java/security/NoSuchAlgorithmException
    //   0	9	62	java/io/FileNotFoundException
    //   82	86	89	java/io/IOException
    //   55	59	99	java/io/IOException
    //   9	22	109	java/io/IOException
    //   22	30	109	java/io/IOException
    //   35	45	109	java/io/IOException
    //   72	82	109	java/io/IOException
    //   116	120	123	java/io/IOException
    //   9	22	133	finally
    //   22	30	133	finally
    //   35	45	133	finally
    //   50	55	133	finally
    //   72	82	133	finally
    //   111	116	133	finally
    //   134	138	140	java/io/IOException
  }

  private static String calculateStringAlg(String paramString1, String paramString2)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(paramString2);
      localMessageDigest.update(paramString1.getBytes());
      String str = getHexString(localMessageDigest.digest());
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return "";
  }

  private static String getHexString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i++)
      localStringBuffer.append(Integer.toHexString(0xFF & paramArrayOfByte[i]));
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.Hash
 * JD-Core Version:    0.6.0
 */