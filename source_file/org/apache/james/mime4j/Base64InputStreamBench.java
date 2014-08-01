package org.apache.james.mime4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.james.mime4j.codec.Base64InputStream;
import org.apache.james.mime4j.codec.CodecUtil;

public class Base64InputStreamBench
{
  private static void compare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if (paramArrayOfByte1.length != paramArrayOfByte2.length)
      throw new AssertionError("length: " + paramArrayOfByte1.length + ", " + paramArrayOfByte2.length);
    for (int i = 0; i < paramArrayOfByte1.length; i++)
    {
      if (paramArrayOfByte1[i] == paramArrayOfByte2[i])
        continue;
      throw new AssertionError("value @ " + i);
    }
  }

  private static byte[] encode(byte[] paramArrayOfByte)
    throws IOException
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    CodecUtil.encodeBase64(localByteArrayInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  private static byte[] initData(int paramInt)
  {
    Random localRandom = new Random(paramInt);
    byte[] arrayOfByte = new byte[paramInt];
    localRandom.nextBytes(arrayOfByte);
    return arrayOfByte;
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    byte[] arrayOfByte1 = initData(2097152);
    byte[] arrayOfByte2 = encode(arrayOfByte1);
    testDecode(arrayOfByte1, arrayOfByte2);
    NullOutputStream localNullOutputStream = new NullOutputStream();
    for (int i = 0; i < 5; i++)
      CodecUtil.copy(new Base64InputStream(new ByteArrayInputStream(arrayOfByte2)), localNullOutputStream);
    Thread.sleep(100L);
    long l1 = System.currentTimeMillis();
    for (int j = 0; j < 50; j++)
      CodecUtil.copy(new Base64InputStream(new ByteArrayInputStream(arrayOfByte2)), localNullOutputStream);
    long l2 = System.currentTimeMillis() - l1;
    long l3 = 50L * arrayOfByte1.length;
    double d = l3 / 1024.0D / 1024.0D / (l2 / 1000.0D);
    System.out.println(l2 + " ms");
    System.out.println(l3 + " bytes");
    System.out.println(d + " mb/sec");
  }

  private static void testDecode(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws IOException
  {
    Base64InputStream localBase64InputStream = new Base64InputStream(new ByteArrayInputStream(paramArrayOfByte2));
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    CodecUtil.copy(localBase64InputStream, localByteArrayOutputStream);
    compare(paramArrayOfByte1, localByteArrayOutputStream.toByteArray());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.Base64InputStreamBench
 * JD-Core Version:    0.6.0
 */