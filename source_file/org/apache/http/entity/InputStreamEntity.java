package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class InputStreamEntity extends AbstractHttpEntity
{
  private static final int BUFFER_SIZE = 2048;
  private final InputStream content;
  private final long length;

  public InputStreamEntity(InputStream paramInputStream, long paramLong)
  {
    this(paramInputStream, paramLong, null);
  }

  public InputStreamEntity(InputStream paramInputStream, long paramLong, ContentType paramContentType)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Source input stream may not be null");
    this.content = paramInputStream;
    this.length = paramLong;
    if (paramContentType != null)
      setContentType(paramContentType.toString());
  }

  @Deprecated
  public void consumeContent()
    throws IOException
  {
    this.content.close();
  }

  public InputStream getContent()
    throws IOException
  {
    return this.content;
  }

  public long getContentLength()
  {
    return this.length;
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public boolean isStreaming()
  {
    return true;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    InputStream localInputStream = this.content;
    byte[] arrayOfByte;
    try
    {
      arrayOfByte = new byte[2048];
      if (this.length < 0L)
        while (true)
        {
          int i = localInputStream.read(arrayOfByte);
          if (i == -1)
            break;
          paramOutputStream.write(arrayOfByte, 0, i);
        }
    }
    finally
    {
      localInputStream.close();
    }
    long l = this.length;
    while (true)
    {
      int j;
      if (l > 0L)
      {
        j = localInputStream.read(arrayOfByte, 0, (int)Math.min(2048L, l));
        if (j != -1);
      }
      else
      {
        localInputStream.close();
        return;
      }
      paramOutputStream.write(arrayOfByte, 0, j);
      l -= j;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.entity.InputStreamEntity
 * JD-Core Version:    0.6.0
 */