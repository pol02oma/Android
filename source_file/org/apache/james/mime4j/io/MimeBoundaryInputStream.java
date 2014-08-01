package org.apache.james.mime4j.io;

import java.io.IOException;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.MimeIOException;
import org.apache.james.mime4j.util.ByteArrayBuffer;
import org.apache.james.mime4j.util.CharsetUtil;

public class MimeBoundaryInputStream extends LineReaderInputStream
{
  private boolean atBoundary;
  private final byte[] boundary;
  private int boundaryLen;
  private BufferedLineReaderInputStream buffer;
  private boolean completed;
  private boolean eof;
  private int initialLength;
  private boolean lastPart;
  private int limit;
  private final boolean strict;

  public MimeBoundaryInputStream(BufferedLineReaderInputStream paramBufferedLineReaderInputStream, String paramString)
    throws IOException
  {
    this(paramBufferedLineReaderInputStream, paramString, false);
  }

  public MimeBoundaryInputStream(BufferedLineReaderInputStream paramBufferedLineReaderInputStream, String paramString, boolean paramBoolean)
    throws IOException
  {
    super(paramBufferedLineReaderInputStream);
    int i = 2 * paramString.length();
    if (i < 4096)
      i = 4096;
    paramBufferedLineReaderInputStream.ensureCapacity(i);
    this.buffer = paramBufferedLineReaderInputStream;
    this.eof = false;
    this.limit = -1;
    this.atBoundary = false;
    this.boundaryLen = 0;
    this.lastPart = false;
    this.initialLength = -1;
    this.completed = false;
    this.strict = paramBoolean;
    this.boundary = new byte[2 + paramString.length()];
    this.boundary[0] = 45;
    this.boundary[1] = 45;
    for (int j = 0; j < paramString.length(); j++)
    {
      int k = (byte)paramString.charAt(j);
      this.boundary[(j + 2)] = k;
    }
    fillBuffer();
  }

  private void calculateBoundaryLen()
    throws IOException
  {
    this.boundaryLen = this.boundary.length;
    int i = this.limit - this.buffer.pos();
    if ((i >= 0) && (this.initialLength == -1))
      this.initialLength = i;
    if ((i > 0) && (this.buffer.byteAt(-1 + this.limit) == 10))
    {
      this.boundaryLen = (1 + this.boundaryLen);
      this.limit = (-1 + this.limit);
    }
    if ((i > 1) && (this.buffer.byteAt(-1 + this.limit) == 13))
    {
      this.boundaryLen = (1 + this.boundaryLen);
      this.limit = (-1 + this.limit);
    }
  }

  private boolean endOfStream()
  {
    return (this.eof) || (this.atBoundary);
  }

  private int fillBuffer()
    throws IOException
  {
    if (this.eof)
      return -1;
    int i;
    if (!hasData())
    {
      i = this.buffer.fillBuffer();
      if (i == -1)
        this.eof = true;
    }
    int k;
    label170: for (int j = this.buffer.pos(); ; j = k + this.boundary.length)
    {
      k = this.buffer.indexOf(this.boundary, j, this.buffer.limit() - j);
      if (k == -1);
      char c;
      do
      {
        int m;
        do
        {
          if (k == -1)
            break label181;
          this.limit = k;
          this.atBoundary = true;
          calculateBoundaryLen();
          return i;
          i = 0;
          break;
          if ((k != this.buffer.pos()) && (this.buffer.byteAt(k - 1) != 10))
            break label170;
          m = k + this.boundary.length;
        }
        while (this.buffer.limit() - m <= 0);
        c = (char)this.buffer.byteAt(m);
      }
      while ((CharsetUtil.isWhitespace(c)) || (c == '-'));
    }
    label181: if (this.eof)
    {
      this.limit = this.buffer.limit();
      return i;
    }
    this.limit = (this.buffer.limit() - (2 + this.boundary.length));
    return i;
  }

  private boolean hasData()
  {
    return (this.limit > this.buffer.pos()) && (this.limit <= this.buffer.limit());
  }

  private void skipBoundary()
    throws IOException
  {
    int i;
    if (!this.completed)
    {
      this.completed = true;
      this.buffer.skip(this.boundaryLen);
      i = 1;
    }
    while (true)
    {
      int j;
      if (this.buffer.length() > 1)
      {
        j = this.buffer.byteAt(this.buffer.pos());
        int k = this.buffer.byteAt(1 + this.buffer.pos());
        if ((i != 0) && (j == 45) && (k == 45))
        {
          this.lastPart = true;
          this.buffer.skip(2);
          i = 0;
          continue;
        }
        if ((j == 13) && (k == 10))
          this.buffer.skip(2);
      }
      do
      {
        return;
        if (j == 10)
        {
          this.buffer.skip(1);
          return;
        }
        this.buffer.skip(1);
        break;
      }
      while (this.eof);
      fillBuffer();
    }
  }

  private void verifyEndOfStream()
    throws IOException
  {
    if ((this.strict) && (this.eof) && (!this.atBoundary))
      throw new MimeIOException(new MimeException("Unexpected end of stream"));
  }

  public void close()
    throws IOException
  {
  }

  public boolean eof()
  {
    return (this.eof) && (!this.buffer.hasBufferedData());
  }

  public boolean isEmptyStream()
  {
    return this.initialLength == 0;
  }

  public boolean isFullyConsumed()
  {
    return (this.completed) && (!this.buffer.hasBufferedData());
  }

  public boolean isLastPart()
  {
    return this.lastPart;
  }

  public boolean markSupported()
  {
    return false;
  }

  public int read()
    throws IOException
  {
    while (true)
    {
      if (!readAllowed())
        return -1;
      if (hasData())
        return this.buffer.read();
      fillBuffer();
    }
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    while (true)
    {
      if (!readAllowed())
        return -1;
      if (hasData())
      {
        int i = Math.min(paramInt2, this.limit - this.buffer.pos());
        return this.buffer.read(paramArrayOfByte, paramInt1, i);
      }
      fillBuffer();
    }
  }

  public boolean readAllowed()
    throws IOException
  {
    if (this.completed)
      return false;
    if ((endOfStream()) && (!hasData()))
    {
      skipBoundary();
      verifyEndOfStream();
      return false;
    }
    return true;
  }

  public int readLine(ByteArrayBuffer paramByteArrayBuffer)
    throws IOException
  {
    if (paramByteArrayBuffer == null)
      throw new IllegalArgumentException("Destination buffer may not be null");
    if (!readAllowed())
    {
      i = -1;
      return i;
    }
    int i = 0;
    int j = 0;
    int k = 0;
    label190: 
    while (true)
    {
      if (j == 0)
      {
        if (!hasData())
        {
          k = fillBuffer();
          if ((endOfStream()) && (!hasData()))
          {
            skipBoundary();
            verifyEndOfStream();
            k = -1;
          }
        }
      }
      else
      {
        if ((i != 0) || (k != -1))
          break;
        return -1;
      }
      int m = this.limit - this.buffer.pos();
      int n = this.buffer.indexOf(10, this.buffer.pos(), m);
      if (n != -1)
        j = 1;
      for (int i1 = n + 1 - this.buffer.pos(); ; i1 = m)
      {
        if (i1 <= 0)
          break label190;
        paramByteArrayBuffer.append(this.buffer.buf(), this.buffer.pos(), i1);
        this.buffer.skip(i1);
        i += i1;
        break;
      }
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("MimeBoundaryInputStream, boundary ");
    byte[] arrayOfByte = this.boundary;
    int i = arrayOfByte.length;
    for (int j = 0; j < i; j++)
      localStringBuilder.append((char)arrayOfByte[j]);
    return localStringBuilder.toString();
  }

  public boolean unread(ByteArrayBuffer paramByteArrayBuffer)
  {
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.io.MimeBoundaryInputStream
 * JD-Core Version:    0.6.0
 */