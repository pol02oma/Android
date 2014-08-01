package org.apache.james.mime4j.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class ContentUtil
{
  public static String decode(Charset paramCharset, ByteSequence paramByteSequence)
  {
    return decode(paramCharset, paramByteSequence, 0, paramByteSequence.length());
  }

  public static String decode(Charset paramCharset, ByteSequence paramByteSequence, int paramInt1, int paramInt2)
  {
    if (paramByteSequence == null)
      return null;
    if (paramCharset == null)
      paramCharset = Charset.defaultCharset();
    if ((paramByteSequence instanceof ByteArrayBuffer))
      return decode(paramCharset, ((ByteArrayBuffer)paramByteSequence).buffer(), paramInt1, paramInt2);
    return decode(paramCharset, paramByteSequence.toByteArray(), paramInt1, paramInt2);
  }

  private static String decode(Charset paramCharset, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return paramCharset.decode(ByteBuffer.wrap(paramArrayOfByte, paramInt1, paramInt2)).toString();
  }

  public static String decode(ByteSequence paramByteSequence)
  {
    if (paramByteSequence == null)
      return null;
    return decode(paramByteSequence, 0, paramByteSequence.length());
  }

  public static String decode(ByteSequence paramByteSequence, int paramInt1, int paramInt2)
  {
    if (paramByteSequence == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder(paramInt2);
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
      localStringBuilder.append((char)(0xFF & paramByteSequence.byteAt(i)));
    return localStringBuilder.toString();
  }

  public static ByteSequence encode(String paramString)
  {
    ByteArrayBuffer localByteArrayBuffer;
    if (paramString == null)
      localByteArrayBuffer = null;
    while (true)
    {
      return localByteArrayBuffer;
      localByteArrayBuffer = new ByteArrayBuffer(paramString.length());
      for (int i = 0; i < paramString.length(); i++)
        localByteArrayBuffer.append((byte)paramString.charAt(i));
    }
  }

  public static ByteSequence encode(Charset paramCharset, String paramString)
  {
    if (paramString == null)
      return null;
    if (paramCharset == null)
      paramCharset = Charset.defaultCharset();
    ByteBuffer localByteBuffer = paramCharset.encode(CharBuffer.wrap(paramString));
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(localByteBuffer.remaining());
    localByteArrayBuffer.append(localByteBuffer.array(), localByteBuffer.position(), localByteBuffer.remaining());
    return localByteArrayBuffer;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.util.ContentUtil
 * JD-Core Version:    0.6.0
 */