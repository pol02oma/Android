package org.apache.http.impl.conn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.http.annotation.Immutable;

@Immutable
public class Wire
{
  private final Log log;

  public Wire(Log paramLog)
  {
    this.log = paramLog;
  }

  private void wire(String paramString, InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      int i = paramInputStream.read();
      if (i == -1)
        break;
      if (i == 13)
      {
        localStringBuilder.append("[\\r]");
        continue;
      }
      if (i == 10)
      {
        localStringBuilder.append("[\\n]\"");
        localStringBuilder.insert(0, "\"");
        localStringBuilder.insert(0, paramString);
        this.log.debug(localStringBuilder.toString());
        localStringBuilder.setLength(0);
        continue;
      }
      if ((i < 32) || (i > 127))
      {
        localStringBuilder.append("[0x");
        localStringBuilder.append(Integer.toHexString(i));
        localStringBuilder.append("]");
        continue;
      }
      localStringBuilder.append((char)i);
    }
    if (localStringBuilder.length() > 0)
    {
      localStringBuilder.append('"');
      localStringBuilder.insert(0, '"');
      localStringBuilder.insert(0, paramString);
      this.log.debug(localStringBuilder.toString());
    }
  }

  public boolean enabled()
  {
    return this.log.isDebugEnabled();
  }

  public void input(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)paramInt;
    input(arrayOfByte);
  }

  public void input(InputStream paramInputStream)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", paramInputStream);
  }

  @Deprecated
  public void input(String paramString)
    throws IOException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Input may not be null");
    input(paramString.getBytes());
  }

  public void input(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte));
  }

  public void input(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }

  public void output(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)paramInt;
    output(arrayOfByte);
  }

  public void output(InputStream paramInputStream)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", paramInputStream);
  }

  @Deprecated
  public void output(String paramString)
    throws IOException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Output may not be null");
    output(paramString.getBytes());
  }

  public void output(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte));
  }

  public void output(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.Wire
 * JD-Core Version:    0.6.0
 */