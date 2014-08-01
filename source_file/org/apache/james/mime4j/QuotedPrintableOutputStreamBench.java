package org.apache.james.mime4j;

import java.io.PrintStream;
import java.util.Random;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.james.mime4j.codec.QuotedPrintableOutputStream;

public class QuotedPrintableOutputStreamBench
{
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
    byte[] arrayOfByte = initData(1024);
    QuotedPrintableOutputStream localQuotedPrintableOutputStream = new QuotedPrintableOutputStream(new NullOutputStream(), true);
    for (int i = 0; i < 2000; i++)
      localQuotedPrintableOutputStream.write(arrayOfByte);
    Thread.sleep(100L);
    long l1 = System.currentTimeMillis();
    for (int j = 0; j < 500000; j++)
      localQuotedPrintableOutputStream.write(arrayOfByte);
    localQuotedPrintableOutputStream.close();
    long l2 = System.currentTimeMillis() - l1;
    long l3 = 500000L * arrayOfByte.length;
    double d = l3 / 1024.0D / 1024.0D / (l2 / 1000.0D);
    System.out.println(l2 + " ms");
    System.out.println(l3 + " bytes");
    System.out.println(d + " mb/sec");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.QuotedPrintableOutputStreamBench
 * JD-Core Version:    0.6.0
 */