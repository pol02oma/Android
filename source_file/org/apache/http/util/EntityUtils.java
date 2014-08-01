package org.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;

public final class EntityUtils
{
  public static void consume(HttpEntity paramHttpEntity)
    throws IOException
  {
    if (paramHttpEntity == null);
    InputStream localInputStream;
    do
    {
      do
        return;
      while (!paramHttpEntity.isStreaming());
      localInputStream = paramHttpEntity.getContent();
    }
    while (localInputStream == null);
    localInputStream.close();
  }

  public static void consumeQuietly(HttpEntity paramHttpEntity)
  {
    try
    {
      consume(paramHttpEntity);
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  @Deprecated
  public static String getContentCharSet(HttpEntity paramHttpEntity)
    throws ParseException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    Header localHeader = paramHttpEntity.getContentType();
    String str = null;
    if (localHeader != null)
    {
      HeaderElement[] arrayOfHeaderElement = paramHttpEntity.getContentType().getElements();
      int i = arrayOfHeaderElement.length;
      str = null;
      if (i > 0)
      {
        NameValuePair localNameValuePair = arrayOfHeaderElement[0].getParameterByName("charset");
        str = null;
        if (localNameValuePair != null)
          str = localNameValuePair.getValue();
      }
    }
    return str;
  }

  @Deprecated
  public static String getContentMimeType(HttpEntity paramHttpEntity)
    throws ParseException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    Header localHeader = paramHttpEntity.getContentType();
    String str = null;
    if (localHeader != null)
    {
      HeaderElement[] arrayOfHeaderElement = paramHttpEntity.getContentType().getElements();
      int i = arrayOfHeaderElement.length;
      str = null;
      if (i > 0)
        str = arrayOfHeaderElement[0].getName();
    }
    return str;
  }

  public static byte[] toByteArray(HttpEntity paramHttpEntity)
    throws IOException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    InputStream localInputStream = paramHttpEntity.getContent();
    if (localInputStream == null)
      return null;
    try
    {
      if (paramHttpEntity.getContentLength() > 2147483647L)
        throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
    }
    finally
    {
      localInputStream.close();
    }
    int i = (int)paramHttpEntity.getContentLength();
    if (i < 0)
      i = 4096;
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(i);
    byte[] arrayOfByte1 = new byte[4096];
    while (true)
    {
      int j = localInputStream.read(arrayOfByte1);
      if (j == -1)
        break;
      localByteArrayBuffer.append(arrayOfByte1, 0, j);
    }
    byte[] arrayOfByte2 = localByteArrayBuffer.toByteArray();
    localInputStream.close();
    return arrayOfByte2;
  }

  public static String toString(HttpEntity paramHttpEntity)
    throws IOException, ParseException
  {
    return toString(paramHttpEntity, (Charset)null);
  }

  public static String toString(HttpEntity paramHttpEntity, String paramString)
    throws IOException, ParseException
  {
    return toString(paramHttpEntity, Charset.forName(paramString));
  }

  public static String toString(HttpEntity paramHttpEntity, Charset paramCharset)
    throws IOException, ParseException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    InputStream localInputStream = paramHttpEntity.getContent();
    if (localInputStream == null)
      return null;
    try
    {
      if (paramHttpEntity.getContentLength() > 2147483647L)
        throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
    }
    finally
    {
      localInputStream.close();
    }
    int i = (int)paramHttpEntity.getContentLength();
    if (i < 0)
      i = 4096;
    Charset localCharset = ContentType.getOrDefault(paramHttpEntity).getCharset();
    if (localCharset == null)
      localCharset = paramCharset;
    if (localCharset == null)
      localCharset = HTTP.DEF_CONTENT_CHARSET;
    InputStreamReader localInputStreamReader = new InputStreamReader(localInputStream, localCharset);
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(i);
    char[] arrayOfChar = new char[1024];
    while (true)
    {
      int j = localInputStreamReader.read(arrayOfChar);
      if (j == -1)
        break;
      localCharArrayBuffer.append(arrayOfChar, 0, j);
    }
    String str = localCharArrayBuffer.toString();
    localInputStream.close();
    return str;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.util.EntityUtils
 * JD-Core Version:    0.6.0
 */