package org.apache.james.mime4j.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class LineReaderInputStreamAdaptor extends LineReaderInputStream
{
  private final LineReaderInputStream bis;
  private boolean eof = false;
  private final int maxLineLen;
  private boolean used = false;

  public LineReaderInputStreamAdaptor(InputStream paramInputStream)
  {
    this(paramInputStream, -1);
  }

  public LineReaderInputStreamAdaptor(InputStream paramInputStream, int paramInt)
  {
    super(paramInputStream);
    if ((paramInputStream instanceof LineReaderInputStream));
    for (this.bis = ((LineReaderInputStream)paramInputStream); ; this.bis = null)
    {
      this.maxLineLen = paramInt;
      return;
    }
  }

  private int doReadLine(ByteArrayBuffer paramByteArrayBuffer)
    throws MaxLineLimitException, IOException
  {
    int i = 0;
    int j;
    do
    {
      j = this.in.read();
      if (j == -1)
        break;
      paramByteArrayBuffer.append(j);
      i++;
      if ((this.maxLineLen <= 0) || (paramByteArrayBuffer.length() < this.maxLineLen))
        continue;
      throw new MaxLineLimitException("Maximum line length limit exceeded");
    }
    while (j != 10);
    if ((i == 0) && (j == -1))
      i = -1;
    return i;
  }

  public boolean eof()
  {
    return this.eof;
  }

  public boolean isUsed()
  {
    return this.used;
  }

  public int read()
    throws IOException
  {
    int i = this.in.read();
    if (i == -1);
    for (boolean bool = true; ; bool = false)
    {
      this.eof = bool;
      this.used = true;
      return i;
    }
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if (i == -1);
    for (boolean bool = true; ; bool = false)
    {
      this.eof = bool;
      this.used = true;
      return i;
    }
  }

  public int readLine(ByteArrayBuffer paramByteArrayBuffer)
    throws MaxLineLimitException, IOException
  {
    int i;
    if (this.bis != null)
    {
      i = this.bis.readLine(paramByteArrayBuffer);
      if (i != -1)
        break label44;
    }
    label44: for (boolean bool = true; ; bool = false)
    {
      this.eof = bool;
      this.used = true;
      return i;
      i = doReadLine(paramByteArrayBuffer);
      break;
    }
  }

  public long skip(long paramLong)
    throws IOException
  {
    long l;
    if (paramLong <= 0L)
    {
      l = 0L;
      return l;
    }
    if (paramLong > 8192L);
    for (int i = 8192; ; i = (int)paramLong)
    {
      byte[] arrayOfByte = new byte[i];
      l = 0L;
      while (true)
      {
        if (paramLong <= 0L)
          break label67;
        int j = read(arrayOfByte);
        if (j == -1)
          break;
        l += j;
        paramLong -= j;
      }
      label67: break;
    }
  }

  public String toString()
  {
    return "[LineReaderInputStreamAdaptor: " + this.bis + "]";
  }

  public boolean unread(ByteArrayBuffer paramByteArrayBuffer)
  {
    if (this.bis != null)
      return this.bis.unread(paramByteArrayBuffer);
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.io.LineReaderInputStreamAdaptor
 * JD-Core Version:    0.6.0
 */