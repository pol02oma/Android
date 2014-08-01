package org.apache.james.mime4j.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public abstract class LineReaderInputStream extends FilterInputStream
{
  protected LineReaderInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }

  public abstract int readLine(ByteArrayBuffer paramByteArrayBuffer)
    throws MaxLineLimitException, IOException;

  public abstract boolean unread(ByteArrayBuffer paramByteArrayBuffer);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.io.LineReaderInputStream
 * JD-Core Version:    0.6.0
 */