package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class BrokenInputStream extends InputStream
{
  private final IOException exception;

  public BrokenInputStream()
  {
    this(new IOException("Broken input stream"));
  }

  public BrokenInputStream(IOException paramIOException)
  {
    this.exception = paramIOException;
  }

  public int available()
    throws IOException
  {
    throw this.exception;
  }

  public void close()
    throws IOException
  {
    throw this.exception;
  }

  public int read()
    throws IOException
  {
    throw this.exception;
  }

  public void reset()
    throws IOException
  {
    throw this.exception;
  }

  public long skip(long paramLong)
    throws IOException
  {
    throw this.exception;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.BrokenInputStream
 * JD-Core Version:    0.6.0
 */