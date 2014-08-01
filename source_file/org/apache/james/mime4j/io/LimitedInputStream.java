package org.apache.james.mime4j.io;

import java.io.IOException;
import java.io.InputStream;

public class LimitedInputStream extends PositionInputStream
{
  private final long limit;

  public LimitedInputStream(InputStream paramInputStream, long paramLong)
  {
    super(paramInputStream);
    if (paramLong < 0L)
      throw new IllegalArgumentException("Limit may not be negative");
    this.limit = paramLong;
  }

  private void enforceLimit()
    throws IOException
  {
    if (this.position >= this.limit)
      throw new IOException("Input stream limit exceeded");
  }

  private int getBytesLeft()
  {
    return (int)Math.min(2147483647L, this.limit - this.position);
  }

  public int read()
    throws IOException
  {
    enforceLimit();
    return super.read();
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    enforceLimit();
    return super.read(paramArrayOfByte, paramInt1, Math.min(paramInt2, getBytesLeft()));
  }

  public long skip(long paramLong)
    throws IOException
  {
    enforceLimit();
    return super.skip(Math.min(paramLong, getBytesLeft()));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.io.LimitedInputStream
 * JD-Core Version:    0.6.0
 */