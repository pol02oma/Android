package org.apache.james.mime4j.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class BufferedLineReaderInputStream extends LineReaderInputStream
{
  private byte[] buffer;
  private int buflen;
  private int bufpos;
  private final int maxLineLen;
  private byte[] origBuffer;
  private int origBuflen;
  private int origBufpos;
  boolean tempBuffer = false;
  private boolean truncated;

  public BufferedLineReaderInputStream(InputStream paramInputStream, int paramInt)
  {
    this(paramInputStream, paramInt, -1);
  }

  public BufferedLineReaderInputStream(InputStream paramInputStream, int paramInt1, int paramInt2)
  {
    super(paramInputStream);
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramInt1 <= 0)
      throw new IllegalArgumentException("Buffer size may not be negative or zero");
    this.buffer = new byte[paramInt1];
    this.bufpos = 0;
    this.buflen = 0;
    this.maxLineLen = paramInt2;
    this.truncated = false;
  }

  private int bufferLen()
  {
    return this.buflen - this.bufpos;
  }

  private void clear()
  {
    this.bufpos = 0;
    this.buflen = 0;
  }

  private void expand(int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = bufferLen();
    if (i > 0)
      System.arraycopy(this.buffer, this.bufpos, arrayOfByte, this.bufpos, i);
    this.buffer = arrayOfByte;
  }

  protected byte[] buf()
  {
    return this.buffer;
  }

  public int byteAt(int paramInt)
  {
    if ((paramInt < this.bufpos) || (paramInt > this.buflen))
      throw new IndexOutOfBoundsException("looking for " + paramInt + " in " + this.bufpos + "/" + this.buflen);
    return 0xFF & this.buffer[paramInt];
  }

  public int capacity()
  {
    return this.buffer.length;
  }

  public void ensureCapacity(int paramInt)
  {
    if (paramInt > this.buffer.length)
      expand(paramInt);
  }

  public int fillBuffer()
    throws IOException
  {
    if (this.tempBuffer)
    {
      if (this.bufpos != this.buflen)
        throw new IllegalStateException("unread only works when a buffer is fully read before the next refill is asked!");
      this.buffer = this.origBuffer;
      this.buflen = this.origBuflen;
      this.bufpos = this.origBufpos;
      this.tempBuffer = false;
      return bufferLen();
    }
    if (this.bufpos > 0)
    {
      int m = bufferLen();
      if (m > 0)
        System.arraycopy(this.buffer, this.bufpos, this.buffer, 0, m);
      this.bufpos = 0;
      this.buflen = m;
    }
    int i = this.buflen;
    int j = this.buffer.length - i;
    int k = this.in.read(this.buffer, i, j);
    if (k == -1)
      return -1;
    this.buflen = (i + k);
    return k;
  }

  public boolean hasBufferedData()
  {
    return bufferLen() > 0;
  }

  public int indexOf(byte paramByte)
  {
    return indexOf(paramByte, this.bufpos, bufferLen());
  }

  public int indexOf(byte paramByte, int paramInt1, int paramInt2)
  {
    if ((paramInt1 < this.bufpos) || (paramInt2 < 0) || (paramInt1 + paramInt2 > this.buflen))
      throw new IndexOutOfBoundsException();
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
      if (this.buffer[i] == paramByte)
        return i;
    return -1;
  }

  public int indexOf(byte[] paramArrayOfByte)
  {
    return indexOf(paramArrayOfByte, this.bufpos, this.buflen - this.bufpos);
  }

  public int indexOf(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Pattern may not be null");
    if ((paramInt1 < this.bufpos) || (paramInt2 < 0) || (paramInt1 + paramInt2 > this.buflen))
      throw new IndexOutOfBoundsException("looking for " + paramInt1 + "(" + paramInt2 + ")" + " in " + this.bufpos + "/" + this.buflen);
    int m;
    if (paramInt2 < paramArrayOfByte.length)
    {
      m = -1;
      return m;
    }
    int[] arrayOfInt = new int[256];
    for (int i = 0; i < arrayOfInt.length; i++)
      arrayOfInt[i] = (1 + paramArrayOfByte.length);
    for (int j = 0; j < paramArrayOfByte.length; j++)
      arrayOfInt[(0xFF & paramArrayOfByte[j])] = (paramArrayOfByte.length - j);
    int k = 0;
    while (true)
    {
      int n;
      if (k <= paramInt2 - paramArrayOfByte.length)
      {
        m = paramInt1 + k;
        n = 1;
      }
      int i2;
      for (int i1 = 0; ; i1++)
      {
        if (i1 < paramArrayOfByte.length)
        {
          if (this.buffer[(m + i1)] == paramArrayOfByte[i1])
            continue;
          n = 0;
        }
        if (n != 0)
          break;
        i2 = m + paramArrayOfByte.length;
        if (i2 < this.buffer.length)
          break label259;
        return -1;
      }
      label259: k += arrayOfInt[(0xFF & this.buffer[i2])];
    }
  }

  protected int length()
  {
    return bufferLen();
  }

  protected int limit()
  {
    return this.buflen;
  }

  public boolean markSupported()
  {
    return false;
  }

  protected int pos()
  {
    return this.bufpos;
  }

  public int read()
    throws IOException
  {
    if (!readAllowed())
      return -1;
    while (!hasBufferedData())
      if (fillBuffer() == -1)
        return -1;
    byte[] arrayOfByte = this.buffer;
    int i = this.bufpos;
    this.bufpos = (i + 1);
    return 0xFF & arrayOfByte[i];
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    int i;
    if (!readAllowed())
      i = -1;
    do
    {
      return i;
      i = 0;
    }
    while (paramArrayOfByte == null);
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (!readAllowed())
      return -1;
    if (paramArrayOfByte == null)
      return 0;
    while (!hasBufferedData())
      if (fillBuffer() == -1)
        return -1;
    int i = bufferLen();
    if (i > paramInt2)
      i = paramInt2;
    System.arraycopy(this.buffer, this.bufpos, paramArrayOfByte, paramInt1, i);
    this.bufpos = (i + this.bufpos);
    return i;
  }

  protected boolean readAllowed()
  {
    return !this.truncated;
  }

  public int readLine(ByteArrayBuffer paramByteArrayBuffer)
    throws MaxLineLimitException, IOException
  {
    if (paramByteArrayBuffer == null)
      throw new IllegalArgumentException("Buffer may not be null");
    int i;
    if (!readAllowed())
      i = -1;
    int j;
    while (true)
    {
      return i;
      i = 0;
      j = 0;
      int k = 0;
      if (j == 0)
      {
        if (hasBufferedData())
          break;
        k = fillBuffer();
        if (k != -1)
          break;
      }
      else if ((i == 0) && (k == -1))
      {
        return -1;
      }
    }
    int m = indexOf(10);
    if (m != -1)
      j = 1;
    for (int n = m + 1 - pos(); ; n = length())
    {
      if (n > 0)
      {
        paramByteArrayBuffer.append(buf(), pos(), n);
        skip(n);
        i += n;
      }
      if ((this.maxLineLen <= 0) || (paramByteArrayBuffer.length() < this.maxLineLen))
        break;
      throw new MaxLineLimitException("Maximum line length limit exceeded");
    }
  }

  protected int skip(int paramInt)
  {
    int i = Math.min(paramInt, bufferLen());
    this.bufpos = (i + this.bufpos);
    return i;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[pos: ");
    localStringBuilder.append(this.bufpos);
    localStringBuilder.append("]");
    localStringBuilder.append("[limit: ");
    localStringBuilder.append(this.buflen);
    localStringBuilder.append("]");
    localStringBuilder.append("[");
    for (int i = this.bufpos; i < this.buflen; i++)
      localStringBuilder.append((char)this.buffer[i]);
    localStringBuilder.append("]");
    if (this.tempBuffer)
    {
      localStringBuilder.append("-ORIG[pos: ");
      localStringBuilder.append(this.origBufpos);
      localStringBuilder.append("]");
      localStringBuilder.append("[limit: ");
      localStringBuilder.append(this.origBuflen);
      localStringBuilder.append("]");
      localStringBuilder.append("[");
      for (int j = this.origBufpos; j < this.origBuflen; j++)
        localStringBuilder.append((char)this.origBuffer[j]);
      localStringBuilder.append("]");
    }
    return localStringBuilder.toString();
  }

  public void truncate()
  {
    clear();
    this.truncated = true;
  }

  public boolean unread(ByteArrayBuffer paramByteArrayBuffer)
  {
    if (this.tempBuffer)
      return false;
    this.origBuffer = this.buffer;
    this.origBuflen = this.buflen;
    this.origBufpos = this.bufpos;
    this.bufpos = 0;
    this.buflen = paramByteArrayBuffer.length();
    this.buffer = paramByteArrayBuffer.buffer();
    this.tempBuffer = true;
    return true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.io.BufferedLineReaderInputStream
 * JD-Core Version:    0.6.0
 */