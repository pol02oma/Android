package org.apache.james.mime4j.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LineNumberInputStream extends FilterInputStream
  implements LineNumberSource
{
  private int lineNumber = 1;

  public LineNumberInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }

  public int getLineNumber()
  {
    return this.lineNumber;
  }

  public int read()
    throws IOException
  {
    int i = this.in.read();
    if (i == 10)
      this.lineNumber = (1 + this.lineNumber);
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    for (int j = paramInt1; j < paramInt1 + i; j++)
    {
      if (paramArrayOfByte[j] != 10)
        continue;
      this.lineNumber = (1 + this.lineNumber);
    }
    return i;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.io.LineNumberInputStream
 * JD-Core Version:    0.6.0
 */