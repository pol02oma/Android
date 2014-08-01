package org.apache.james.mime4j.dom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class SingleBody
  implements Body
{
  static final int DEFAULT_ENCODING_BUFFER_SIZE = 1024;
  private Entity parent = null;

  private static void copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1024];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (-1 == i)
        break;
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }

  public SingleBody copy()
  {
    throw new UnsupportedOperationException();
  }

  public void dispose()
  {
  }

  public abstract InputStream getInputStream()
    throws IOException;

  public Entity getParent()
  {
    return this.parent;
  }

  public void setParent(Entity paramEntity)
  {
    this.parent = paramEntity;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException();
    InputStream localInputStream = getInputStream();
    copy(localInputStream, paramOutputStream);
    localInputStream.close();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.SingleBody
 * JD-Core Version:    0.6.0
 */