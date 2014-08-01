package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class TeeOutputStream extends ProxyOutputStream
{
  protected OutputStream branch;

  public TeeOutputStream(OutputStream paramOutputStream1, OutputStream paramOutputStream2)
  {
    super(paramOutputStream1);
    this.branch = paramOutputStream2;
  }

  public void close()
    throws IOException
  {
    try
    {
      super.close();
      return;
    }
    finally
    {
      this.branch.close();
    }
    throw localObject;
  }

  public void flush()
    throws IOException
  {
    super.flush();
    this.branch.flush();
  }

  public void write(int paramInt)
    throws IOException
  {
    monitorenter;
    try
    {
      super.write(paramInt);
      this.branch.write(paramInt);
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

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    monitorenter;
    try
    {
      super.write(paramArrayOfByte);
      this.branch.write(paramArrayOfByte);
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

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    monitorenter;
    try
    {
      super.write(paramArrayOfByte, paramInt1, paramInt2);
      this.branch.write(paramArrayOfByte, paramInt1, paramInt2);
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.TeeOutputStream
 * JD-Core Version:    0.6.0
 */