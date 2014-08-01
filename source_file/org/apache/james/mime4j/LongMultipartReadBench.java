package org.apache.james.mime4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.apache.james.mime4j.codec.CodecUtil;
import org.apache.james.mime4j.dom.Header;
import org.apache.james.mime4j.dom.MessageBuilder;
import org.apache.james.mime4j.message.DefaultMessageBuilder;
import org.apache.james.mime4j.message.SimpleContentHandler;
import org.apache.james.mime4j.parser.AbstractContentHandler;
import org.apache.james.mime4j.parser.MimeStreamParser;
import org.apache.james.mime4j.storage.DefaultStorageProvider;
import org.apache.james.mime4j.storage.MemoryStorageProvider;
import org.apache.james.mime4j.stream.BodyDescriptor;
import org.apache.james.mime4j.stream.EntityState;
import org.apache.james.mime4j.stream.MimeTokenStream;

public class LongMultipartReadBench
{
  private static Test createTest(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 0:
      return new MimeTokenStreamTest(null);
    case 1:
      return new AbstractContentHandlerTest(null);
    case 2:
      return new SimpleContentHandlerTest(null);
    case 3:
    }
    return new MessageTest(null);
  }

  private static byte[] loadMessage(String paramString)
    throws IOException
  {
    ClassLoader localClassLoader = LongMultipartReadBench.class.getClassLoader();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    InputStream localInputStream = localClassLoader.getResourceAsStream(paramString);
    if (localInputStream == null)
      return null;
    try
    {
      CodecUtil.copy(localInputStream, localByteArrayOutputStream);
      return localByteArrayOutputStream.toByteArray();
    }
    finally
    {
      localInputStream.close();
    }
    throw localObject;
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    byte[] arrayOfByte = loadMessage("long-multipart.msg");
    if (arrayOfByte == null)
    {
      System.err.println("Test message not found");
      return;
    }
    if (paramArrayOfString.length > 0);
    Test localTest;
    for (int i = Integer.parseInt(paramArrayOfString[0]); ; i = 0)
    {
      localTest = createTest(i);
      if (localTest != null)
        break;
      System.err.println("No such test: " + i);
      return;
    }
    if (paramArrayOfString.length > 1);
    for (int j = Integer.parseInt(paramArrayOfString[1]); ; j = 25000)
    {
      System.out.println("Multipart message read.");
      System.out.println("No of repetitions: " + j);
      System.out.println("Content length: " + arrayOfByte.length);
      System.out.println("Test: " + localTest.getClass().getSimpleName());
      System.out.print("Warmup... ");
      long l1 = System.currentTimeMillis();
      while (System.currentTimeMillis() - l1 < 1500L)
        localTest.run(arrayOfByte, 10);
    }
    System.out.println("done");
    System.out.println("--------------------------------");
    long l2 = System.currentTimeMillis();
    localTest.run(arrayOfByte, j);
    double d1 = (System.currentTimeMillis() - l2) / 1000.0D;
    double d2 = j * arrayOfByte.length / 1024.0D / 1024.0D;
    PrintStream localPrintStream1 = System.out;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Double.valueOf(d1);
    localPrintStream1.printf("Execution time: %f sec\n", arrayOfObject1);
    PrintStream localPrintStream2 = System.out;
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Double.valueOf(j / d1);
    localPrintStream2.printf("%.2f messages/sec\n", arrayOfObject2);
    PrintStream localPrintStream3 = System.out;
    Object[] arrayOfObject3 = new Object[1];
    arrayOfObject3[0] = Double.valueOf(d2 / d1);
    localPrintStream3.printf("%.2f mb/sec\n", arrayOfObject3);
  }

  private static final class AbstractContentHandlerTest
    implements LongMultipartReadBench.Test
  {
    public void run(byte[] paramArrayOfByte, int paramInt)
      throws Exception
    {
      1 local1 = new AbstractContentHandler()
      {
      };
      for (int i = 0; i < paramInt; i++)
      {
        MimeStreamParser localMimeStreamParser = new MimeStreamParser();
        localMimeStreamParser.setContentHandler(local1);
        localMimeStreamParser.parse(new ByteArrayInputStream(paramArrayOfByte));
      }
    }
  }

  private static final class MessageTest
    implements LongMultipartReadBench.Test
  {
    public void run(byte[] paramArrayOfByte, int paramInt)
      throws Exception
    {
      DefaultStorageProvider.setInstance(new MemoryStorageProvider());
      DefaultMessageBuilder localDefaultMessageBuilder = new DefaultMessageBuilder();
      for (int i = 0; i < paramInt; i++)
        localDefaultMessageBuilder.parseMessage(new ByteArrayInputStream(paramArrayOfByte));
    }
  }

  private static final class MimeTokenStreamTest
    implements LongMultipartReadBench.Test
  {
    public void run(byte[] paramArrayOfByte, int paramInt)
      throws Exception
    {
      MimeTokenStream localMimeTokenStream = new MimeTokenStream();
      for (int i = 0; i < paramInt; i++)
      {
        localMimeTokenStream.parse(new ByteArrayInputStream(paramArrayOfByte));
        for (EntityState localEntityState = localMimeTokenStream.getState(); localEntityState != EntityState.T_END_OF_STREAM; localEntityState = localMimeTokenStream.next());
      }
    }
  }

  private static final class SimpleContentHandlerTest
    implements LongMultipartReadBench.Test
  {
    public void run(byte[] paramArrayOfByte, int paramInt)
      throws Exception
    {
      1 local1 = new SimpleContentHandler()
      {
        public void body(BodyDescriptor paramBodyDescriptor, InputStream paramInputStream)
          throws IOException
        {
          byte[] arrayOfByte = new byte[4096];
          while (paramInputStream.read(arrayOfByte) != -1);
        }

        public void headers(Header paramHeader)
        {
        }
      };
      for (int i = 0; i < paramInt; i++)
      {
        MimeStreamParser localMimeStreamParser = new MimeStreamParser();
        localMimeStreamParser.setContentDecoding(true);
        localMimeStreamParser.setContentHandler(local1);
        localMimeStreamParser.parse(new ByteArrayInputStream(paramArrayOfByte));
      }
    }
  }

  private static abstract interface Test
  {
    public abstract void run(byte[] paramArrayOfByte, int paramInt)
      throws Exception;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.LongMultipartReadBench
 * JD-Core Version:    0.6.0
 */