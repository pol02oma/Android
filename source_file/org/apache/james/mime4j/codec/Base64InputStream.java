package org.apache.james.mime4j.codec;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class Base64InputStream extends InputStream
{
  private static final int[] BASE64_DECODE;
  private static final byte BASE64_PAD = 61;
  private static final int ENCODED_BUFFER_SIZE = 1536;
  private static final int EOF = -1;
  private boolean closed = false;
  private final ByteArrayBuffer decodedBuf;
  private final byte[] encoded;
  private boolean eof;
  private final InputStream in;
  private final DecodeMonitor monitor;
  private int position = 0;
  private final byte[] singleByte = new byte[1];
  private int size = 0;

  static
  {
    if (!Base64InputStream.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      BASE64_DECODE = new int[256];
      for (int i = 0; i < 256; i++)
        BASE64_DECODE[i] = -1;
    }
    for (int j = 0; j < Base64OutputStream.BASE64_TABLE.length; j++)
      BASE64_DECODE[(0xFF & Base64OutputStream.BASE64_TABLE[j])] = j;
  }

  protected Base64InputStream(int paramInt, InputStream paramInputStream, DecodeMonitor paramDecodeMonitor)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException();
    this.encoded = new byte[paramInt];
    this.decodedBuf = new ByteArrayBuffer(512);
    this.in = paramInputStream;
    this.monitor = paramDecodeMonitor;
  }

  public Base64InputStream(InputStream paramInputStream)
  {
    this(paramInputStream, false);
  }

  public Base64InputStream(InputStream paramInputStream, DecodeMonitor paramDecodeMonitor)
  {
    this(1536, paramInputStream, paramDecodeMonitor);
  }

  public Base64InputStream(InputStream paramInputStream, boolean paramBoolean)
  {
  }

  private int decodePad(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3, int paramInt4)
    throws IOException
  {
    this.eof = true;
    if (paramInt2 == 2)
    {
      int i1 = (byte)(paramInt1 >>> 4);
      if (paramInt3 < paramInt4)
      {
        int i2 = paramInt3 + 1;
        paramArrayOfByte[paramInt3] = i1;
        return i2;
      }
      this.decodedBuf.append(i1);
      return paramInt3;
    }
    if (paramInt2 == 3)
    {
      int i = (byte)(paramInt1 >>> 10);
      int j = (byte)(0xFF & paramInt1 >>> 2);
      if (paramInt3 < paramInt4 - 1)
      {
        int m = paramInt3 + 1;
        paramArrayOfByte[paramInt3] = i;
        int n = m + 1;
        paramArrayOfByte[m] = j;
        return n;
      }
      if (paramInt3 < paramInt4)
      {
        int k = paramInt3 + 1;
        paramArrayOfByte[paramInt3] = i;
        this.decodedBuf.append(j);
        return k;
      }
      this.decodedBuf.append(i);
      this.decodedBuf.append(j);
      return paramInt3;
    }
    handleUnexpecedPad(paramInt2);
    return paramInt3;
  }

  private void handleUnexpecedPad(int paramInt)
    throws IOException
  {
    if (this.monitor.warn("Unexpected padding character", "dropping " + paramInt + " sextet(s)"))
      throw new IOException("Unexpected padding character");
  }

  private void handleUnexpectedEof(int paramInt)
    throws IOException
  {
    if (this.monitor.warn("Unexpected end of BASE64 stream", "dropping " + paramInt + " sextet(s)"))
      throw new IOException("Unexpected end of BASE64 stream");
  }

  private int read0(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt1 + paramInt2;
    int j = paramInt1;
    if (this.decodedBuf.length() > 0)
    {
      int i12 = Math.min(this.decodedBuf.length(), paramInt2);
      System.arraycopy(this.decodedBuf.buffer(), 0, paramArrayOfByte, j, i12);
      this.decodedBuf.remove(0, i12);
      j += i12;
    }
    if (this.eof)
    {
      if (j == paramInt1)
        return -1;
      return j - paramInt1;
    }
    int k = 0;
    int m = 0;
    int i3;
    int i4;
    int i5;
    while (true)
    {
      break label292;
      if (j >= i)
        break label583;
      while (this.position == this.size)
      {
        int i11 = this.in.read(this.encoded, 0, this.encoded.length);
        if (i11 == -1)
        {
          this.eof = true;
          if (m != 0)
            handleUnexpectedEof(m);
          if (j == paramInt1)
            return -1;
          return j - paramInt1;
        }
        if (i11 > 0)
        {
          this.position = 0;
          this.size = i11;
          continue;
        }
        if (($assertionsDisabled) || (i11 == 0))
          continue;
        throw new AssertionError();
      }
      int i2;
      label292: int i1;
      do
      {
        k = i2 | k << 6;
        m++;
        if (m == 4)
        {
          m = 0;
          i3 = (byte)(k >>> 16);
          i4 = (byte)(k >>> 8);
          i5 = (byte)k;
          if (j >= i - 2)
            break label441;
          int i8 = j + 1;
          paramArrayOfByte[j] = i3;
          int i9 = i8 + 1;
          paramArrayOfByte[i8] = i4;
          int i10 = i9 + 1;
          paramArrayOfByte[i9] = i5;
          j = i10;
        }
        if ((this.position >= this.size) || (j >= i))
          break;
        byte[] arrayOfByte = this.encoded;
        int n = this.position;
        this.position = (n + 1);
        i1 = 0xFF & arrayOfByte[n];
        if (i1 == 61)
          return decodePad(k, m, paramArrayOfByte, j, i) - paramInt1;
        i2 = BASE64_DECODE[i1];
      }
      while (i2 >= 0);
      if ((i1 == 13) || (i1 == 10) || (i1 == 32) || (!this.monitor.warn("Unexpected base64 byte: " + (byte)i1, "ignoring.")))
        continue;
      throw new IOException("Unexpected base64 byte");
    }
    label441: if (j < i - 1)
    {
      int i7 = j + 1;
      paramArrayOfByte[j] = i3;
      j = i7 + 1;
      paramArrayOfByte[i7] = i4;
      this.decodedBuf.append(i5);
    }
    while ((!$assertionsDisabled) && (j != i))
    {
      throw new AssertionError();
      if (j < i)
      {
        int i6 = j + 1;
        paramArrayOfByte[j] = i3;
        this.decodedBuf.append(i4);
        this.decodedBuf.append(i5);
        j = i6;
        continue;
      }
      this.decodedBuf.append(i3);
      this.decodedBuf.append(i4);
      this.decodedBuf.append(i5);
    }
    return i - paramInt1;
    label583: assert (m == 0);
    assert (j == i);
    return i - paramInt1;
  }

  public void close()
    throws IOException
  {
    if (this.closed)
      return;
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
      i = read0(this.singleByte, 0, 1);
      if (i == -1)
        return -1;
    }
    while (i != 1);
    return 0xFF & this.singleByte[0];
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Stream has been closed");
    if (paramArrayOfByte == null)
      throw new NullPointerException();
    if (paramArrayOfByte.length == 0)
      return 0;
    return read0(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Stream has been closed");
    if (paramArrayOfByte == null)
      throw new NullPointerException();
    if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length))
      throw new IndexOutOfBoundsException();
    if (paramInt2 == 0)
      return 0;
    return read0(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.codec.Base64InputStream
 * JD-Core Version:    0.6.0
 */