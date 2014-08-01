package org.apache.james.mime4j.codec;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class QuotedPrintableInputStream extends InputStream
{
  private static final byte CR = 13;
  private static final int DEFAULT_BUFFER_SIZE = 2048;
  private static final byte EQ = 61;
  private static final byte LF = 10;
  private final ByteArrayBuffer blanks;
  private boolean closed;
  private final ByteArrayBuffer decodedBuf;
  private final byte[] encoded;
  private final InputStream in;
  private int limit = 0;
  private final DecodeMonitor monitor;
  private int pos = 0;
  private final byte[] singleByte = new byte[1];

  protected QuotedPrintableInputStream(int paramInt, InputStream paramInputStream, DecodeMonitor paramDecodeMonitor)
  {
    this.in = paramInputStream;
    this.encoded = new byte[paramInt];
    this.decodedBuf = new ByteArrayBuffer(512);
    this.blanks = new ByteArrayBuffer(512);
    this.closed = false;
    this.monitor = paramDecodeMonitor;
  }

  protected QuotedPrintableInputStream(int paramInt, InputStream paramInputStream, boolean paramBoolean)
  {
  }

  public QuotedPrintableInputStream(InputStream paramInputStream)
  {
    this(paramInputStream, false);
  }

  public QuotedPrintableInputStream(InputStream paramInputStream, DecodeMonitor paramDecodeMonitor)
  {
    this(2048, paramInputStream, paramDecodeMonitor);
  }

  public QuotedPrintableInputStream(InputStream paramInputStream, boolean paramBoolean)
  {
    this(2048, paramInputStream, paramBoolean);
  }

  private int convert(int paramInt)
  {
    if ((paramInt >= 48) && (paramInt <= 57))
      return paramInt - 48;
    if ((paramInt >= 65) && (paramInt <= 70))
      return 10 + (paramInt - 65);
    if ((paramInt >= 97) && (paramInt <= 102))
      return 10 + (paramInt - 97);
    return -1;
  }

  private int fillBuffer()
    throws IOException
  {
    if (this.pos < this.limit)
    {
      System.arraycopy(this.encoded, this.pos, this.encoded, 0, this.limit - this.pos);
      this.limit -= this.pos;
    }
    for (this.pos = 0; ; this.pos = 0)
    {
      int i = this.encoded.length - this.limit;
      int j = 0;
      if (i > 0)
      {
        j = this.in.read(this.encoded, this.limit, i);
        if (j > 0)
          this.limit = (j + this.limit);
      }
      return j;
      this.limit = 0;
    }
  }

  private int getnext()
  {
    if (this.pos < this.limit)
    {
      int i = this.encoded[this.pos];
      this.pos = (1 + this.pos);
      return i & 0xFF;
    }
    return -1;
  }

  private int peek(int paramInt)
  {
    if (paramInt + this.pos < this.limit)
      return 0xFF & this.encoded[(paramInt + this.pos)];
    return -1;
  }

  private int read0(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt1 + paramInt2;
    int j = paramInt1;
    int k = this.decodedBuf.length();
    int m = 0;
    if (k > 0)
    {
      int i9 = Math.min(this.decodedBuf.length(), i - j);
      System.arraycopy(this.decodedBuf.buffer(), 0, paramArrayOfByte, j, i9);
      this.decodedBuf.remove(0, i9);
      j += i9;
    }
    label131: label800: 
    while (j < i)
    {
      if (this.limit - this.pos < 3)
      {
        if (fillBuffer() != -1)
          break label131;
        m = 1;
      }
      while ((this.limit - this.pos == 0) && (m != 0))
      {
        if (j == paramInt1)
        {
          return -1;
          m = 0;
          continue;
        }
        return j - paramInt1;
      }
      int n = 0;
      while (true)
      {
        if ((this.pos >= this.limit) || (j >= i))
          break label800;
        byte[] arrayOfByte = this.encoded;
        int i1 = this.pos;
        this.pos = (i1 + 1);
        int i2 = 0xFF & arrayOfByte[i1];
        if ((n != 0) && (i2 != 10))
        {
          if (this.monitor.warn("Found CR without LF", "Leaving it as is"))
            throw new IOException("Found CR without LF");
          j = transfer(13, paramArrayOfByte, j, i, false);
        }
        do
        {
          if (i2 != 13)
            break label293;
          n = 1;
          break;
        }
        while ((n != 0) || (i2 != 10) || (!this.monitor.warn("Found LF without CR", "Translating to CRLF")));
        throw new IOException("Found LF without CR");
        label293: if (i2 == 10)
        {
          if (this.blanks.length() == 0)
            j = transfer(10, paramArrayOfByte, transfer(13, paramArrayOfByte, j, i, false), i, false);
          while (true)
          {
            this.blanks.clear();
            n = 0;
            break;
            if (this.blanks.byteAt(0) == 61)
              continue;
            j = transfer(10, paramArrayOfByte, transfer(13, paramArrayOfByte, j, i, false), i, false);
          }
        }
        if (i2 == 61)
        {
          if ((this.limit - this.pos < 2) && (m == 0))
          {
            this.pos = (-1 + this.pos);
            break;
          }
          int i3 = getnext();
          if (i3 == 61)
          {
            j = transfer(i3, paramArrayOfByte, j, i, true);
            int i7 = peek(0);
            int i8 = peek(1);
            if ((i7 == 10) || ((i7 == 13) && (i8 == 10)))
            {
              this.monitor.warn("Unexpected ==EOL encountered", "== 0x" + i7 + " 0x" + i8);
              this.blanks.append(i3);
              n = 0;
              continue;
            }
            this.monitor.warn("Unexpected == encountered", "==");
            n = 0;
            continue;
          }
          if (Character.isWhitespace((char)i3))
          {
            j = transfer(-1, paramArrayOfByte, j, i, true);
            n = 0;
            if (i3 == 10)
              continue;
            this.blanks.append(i2);
            this.blanks.append(i3);
            n = 0;
            continue;
          }
          int i4 = getnext();
          int i5 = convert(i3);
          int i6 = convert(i4);
          if ((i5 < 0) || (i6 < 0))
          {
            this.monitor.warn("Malformed encoded value encountered", "leaving =" + (char)i3 + (char)i4 + " as is");
            j = transfer(i4, paramArrayOfByte, transfer(i3, paramArrayOfByte, transfer(61, paramArrayOfByte, j, i, true), i, false), i, false);
            n = 0;
            continue;
          }
          j = transfer(i6 | i5 << 4, paramArrayOfByte, j, i, true);
          n = 0;
          continue;
        }
        if (Character.isWhitespace(i2))
        {
          this.blanks.append(i2);
          n = 0;
          continue;
        }
        j = transfer(i2 & 0xFF, paramArrayOfByte, j, i, true);
        n = 0;
      }
    }
    return i - paramInt1;
  }

  private int transfer(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3, boolean paramBoolean)
    throws IOException
  {
    int n;
    if ((paramBoolean) && (this.blanks.length() > 0))
    {
      int m = Math.min(this.blanks.length(), paramInt3 - paramInt2);
      System.arraycopy(this.blanks.buffer(), 0, paramArrayOfByte, paramInt2, m);
      n = paramInt2 + m;
      int i1 = this.blanks.length() - m;
      if (i1 > 0)
        this.decodedBuf.append(this.blanks.buffer(), m, i1);
      this.blanks.clear();
    }
    for (int i = n; ; i = paramInt2)
    {
      if (paramInt1 != -1)
      {
        if (i < paramInt3)
        {
          int j = i + 1;
          paramArrayOfByte[i] = (byte)paramInt1;
          return j;
          if ((this.blanks.length() <= 0) || (paramBoolean))
            continue;
          StringBuilder localStringBuilder = new StringBuilder(3 * this.blanks.length());
          for (int k = 0; k < this.blanks.length(); k++)
            localStringBuilder.append(" " + this.blanks.byteAt(k));
          if (!this.monitor.warn("ignored blanks", localStringBuilder.toString()))
            continue;
          throw new IOException("ignored blanks");
        }
        this.decodedBuf.append(paramInt1);
      }
      return i;
    }
  }

  public void close()
    throws IOException
  {
    this.closed = true;
  }

  public int read()
    throws IOException
  {
    if (this.closed)
      throw new IOException("Stream has been closed");
    int i;
    do
    {
      i = read(this.singleByte, 0, 1);
      if (i == -1)
        return -1;
    }
    while (i != 1);
    return 0xFF & this.singleByte[0];
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Stream has been closed");
    return read0(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.codec.QuotedPrintableInputStream
 * JD-Core Version:    0.6.0
 */