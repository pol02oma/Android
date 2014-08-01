package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class BoundedInputStream extends InputStream
{
  private final InputStream in;
  private long mark = -1L;
  private final long max;
  private long pos = 0L;
  private boolean propagateClose = true;

  public BoundedInputStream(InputStream paramInputStream)
  {
    this(paramInputStream, -1L);
  }

  public BoundedInputStream(InputStream paramInputStream, long paramLong)
  {
    this.max = paramLong;
    this.in = paramInputStream;
  }

  public int available()
    throws IOException
  {
    if ((this.max >= 0L) && (this.pos >= this.max))
      return 0;
    return this.in.available();
  }

  public void close()
    throws IOException
  {
    if (this.propagateClose)
      this.in.close();
  }

  public boolean isPropagateClose()
  {
    return this.propagateClose;
  }

  public void mark(int paramInt)
  {
    monitorenter;
    try
    {
      this.in.mark(paramInt);
      this.mark = this.pos;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean markSupported()
  {
    return this.in.markSupported();
  }

  public int read()
    throws IOException
  {
    if ((this.max >= 0L) && (this.pos >= this.max))
      return -1;
    int i = this.in.read();
    this.pos = (1L + this.pos);
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if ((this.max >= 0L) && (this.pos >= this.max))
      return -1;
    long l;
    if (this.max >= 0L)
      l = Math.min(paramInt2, this.max - this.pos);
    int i;
    while (true)
    {
      i = this.in.read(paramArrayOfByte, paramInt1, (int)l);
      if (i != -1)
        break;
      return -1;
      l = paramInt2;
    }
    this.pos += i;
    return i;
  }

  public void reset()
    throws IOException
  {
    monitorenter;
    try
    {
      this.in.reset();
      this.pos = this.mark;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setPropagateClose(boolean paramBoolean)
  {
    this.propagateClose = paramBoolean;
  }

  public long skip(long paramLong)
    throws IOException
  {
    long l1;
    if (this.max >= 0L)
      l1 = Math.min(paramLong, this.max - this.pos);
    while (true)
    {
      long l2 = this.in.skip(l1);
      this.pos = (l2 + this.pos);
      return l2;
      l1 = paramLong;
    }
  }

  public String toString()
  {
    return this.in.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.BoundedInputStream
 * JD-Core Version:    0.6.0
 */