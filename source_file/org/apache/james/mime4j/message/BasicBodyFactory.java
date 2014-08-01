package org.apache.james.mime4j.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.james.mime4j.dom.BinaryBody;
import org.apache.james.mime4j.dom.TextBody;
import org.apache.james.mime4j.util.CharsetUtil;

public class BasicBodyFactory
  implements BodyFactory
{
  private static byte[] bufferContent(InputStream paramInputStream)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[2048];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1)
        break;
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    return localByteArrayOutputStream.toByteArray();
  }

  public BinaryBody binaryBody(InputStream paramInputStream)
    throws IOException
  {
    return new BasicBinaryBody(bufferContent(paramInputStream));
  }

  public BinaryBody binaryBody(byte[] paramArrayOfByte)
  {
    return new BasicBinaryBody(paramArrayOfByte);
  }

  public TextBody textBody(InputStream paramInputStream, String paramString)
    throws IOException
  {
    return new BasicTextBody(bufferContent(paramInputStream), paramString);
  }

  public TextBody textBody(String paramString)
  {
    return textBody(paramString, CharsetUtil.DEFAULT_CHARSET);
  }

  public TextBody textBody(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Text may not be null");
    Charset localCharset = Charset.forName(paramString2);
    try
    {
      StringBody localStringBody = new StringBody(paramString1, localCharset);
      return localStringBody;
    }
    catch (UnsupportedCharsetException localUnsupportedCharsetException)
    {
    }
    throw new UnsupportedEncodingException(localUnsupportedCharsetException.getMessage());
  }

  public TextBody textBody(String paramString, Charset paramCharset)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Text may not be null");
    return new StringBody(paramString, paramCharset);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.BasicBodyFactory
 * JD-Core Version:    0.6.0
 */