package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public abstract class ProxyReader extends FilterReader
{
  public ProxyReader(Reader paramReader)
  {
    super(paramReader);
  }

  protected void afterRead(int paramInt)
    throws IOException
  {
  }

  protected void beforeRead(int paramInt)
    throws IOException
  {
  }

  public void close()
    throws IOException
  {
    try
    {
      this.in.close();
      return;
    }
    catch (IOException localIOException)
    {
      handleIOException(localIOException);
    }
  }

  protected void handleIOException(IOException paramIOException)
    throws IOException
  {
    throw paramIOException;
  }

  public void mark(int paramInt)
    throws IOException
  {
    monitorenter;
    try
    {
      this.in.mark(paramInt);
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        handleIOException(localIOException);
    }
    finally
    {
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
    int i = 1;
    try
    {
      beforeRead(1);
      int j = this.in.read();
      if (j != -1);
      while (true)
      {
        afterRead(i);
        return j;
        i = -1;
      }
    }
    catch (IOException localIOException)
    {
      handleIOException(localIOException);
    }
    return -1;
  }

  public int read(CharBuffer paramCharBuffer)
    throws IOException
  {
    if (paramCharBuffer != null);
    try
    {
      for (int i = paramCharBuffer.length(); ; i = 0)
      {
        beforeRead(i);
        int j = this.in.read(paramCharBuffer);
        afterRead(j);
        return j;
      }
    }
    catch (IOException localIOException)
    {
      handleIOException(localIOException);
    }
    return -1;
  }

  public int read(char[] paramArrayOfChar)
    throws IOException
  {
    if (paramArrayOfChar != null);
    try
    {
      for (int i = paramArrayOfChar.length; ; i = 0)
      {
        beforeRead(i);
        int j = this.in.read(paramArrayOfChar);
        afterRead(j);
        return j;
      }
    }
    catch (IOException localIOException)
    {
      handleIOException(localIOException);
    }
    return -1;
  }

  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      beforeRead(paramInt2);
      int i = this.in.read(paramArrayOfChar, paramInt1, paramInt2);
      afterRead(i);
      return i;
    }
    catch (IOException localIOException)
    {
      handleIOException(localIOException);
    }
    return -1;
  }

  public boolean ready()
    throws IOException
  {
    try
    {
      boolean bool = this.in.ready();
      return bool;
    }
    catch (IOException localIOException)
    {
      handleIOException(localIOException);
    }
    return false;
  }

  public void reset()
    throws IOException
  {
    monitorenter;
    try
    {
      this.in.reset();
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        handleIOException(localIOException);
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public long skip(long paramLong)
    throws IOException
  {
    try
    {
      long l = this.in.skip(paramLong);
      return l;
    }
    catch (IOException localIOException)
    {
      handleIOException(localIOException);
    }
    return 0L;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.ProxyReader
 * JD-Core Version:    0.6.0
 */