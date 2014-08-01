package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

class StringInputStream extends InputStream
{
  private final ByteBuffer bbuf;
  private final CharBuffer cbuf;
  private final CharsetEncoder encoder;
  private int mark;

  StringInputStream(CharSequence paramCharSequence, Charset paramCharset)
  {
    this(paramCharSequence, paramCharset, 2048);
  }

  StringInputStream(CharSequence paramCharSequence, Charset paramCharset, int paramInt)
  {
    this.encoder = paramCharset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
    this.bbuf = ByteBuffer.allocate(124);
    this.bbuf.flip();
    this.cbuf = CharBuffer.wrap(paramCharSequence);
    this.mark = -1;
  }

  private void fillBuffer()
    throws IOException
  {
    this.bbuf.compact();
    CoderResult localCoderResult = this.encoder.encode(this.cbuf, this.bbuf, true);
    if (localCoderResult.isError())
      localCoderResult.throwException();
    this.bbuf.flip();
  }

  public int available()
    throws IOException
  {
    return this.cbuf.remaining();
  }

  public void close()
    throws IOException
  {
  }

  public void mark(int paramInt)
  {
    this.mark = this.cbuf.position();
  }

  public boolean markSupported()
  {
    return true;
  }

  public int read()
    throws IOException
  {
    do
    {
      if (this.bbuf.hasRemaining())
        return 0xFF & this.bbuf.get();
      fillBuffer();
    }
    while ((this.bbuf.hasRemaining()) || (this.cbuf.hasRemaining()));
    return -1;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new NullPointerException("Byte array is null");
    if ((paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length))
      throw new IndexOutOfBoundsException("Array Size=" + paramArrayOfByte.length + ", offset=" + paramInt1 + ", length=" + paramInt2);
    if ((!this.bbuf.hasRemaining()) && (!this.cbuf.hasRemaining()))
      return -1;
    int i = 0;
    do
    {
      while (true)
      {
        if (paramInt2 <= 0)
          break label180;
        if (!this.bbuf.hasRemaining())
          break;
        int j = Math.min(this.bbuf.remaining(), paramInt2);
        this.bbuf.get(paramArrayOfByte, paramInt1, j);
        paramInt1 += j;
        paramInt2 -= j;
        i += j;
      }
      fillBuffer();
    }
    while ((this.bbuf.hasRemaining()) || (this.cbuf.hasRemaining()));
    label180: if ((i == 0) && (!this.cbuf.hasRemaining()))
      i = -1;
    return i;
  }

  public void reset()
    throws IOException
  {
    if (this.mark != -1)
    {
      this.cbuf.position(this.mark);
      this.mark = -1;
    }
  }

  public long skip(long paramLong)
    throws IOException
  {
    for (int i = 0; (paramLong > 0L) && (this.cbuf.hasRemaining()); i++)
    {
      this.cbuf.get();
      paramLong -= 1L;
    }
    return i;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.StringInputStream
 * JD-Core Version:    0.6.0
 */