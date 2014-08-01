package org.apache.james.mime4j.message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.dom.BinaryBody;

class BasicBinaryBody extends BinaryBody
{
  private final byte[] content;

  BasicBinaryBody(byte[] paramArrayOfByte)
  {
    this.content = paramArrayOfByte;
  }

  public BasicBinaryBody copy()
  {
    return new BasicBinaryBody(this.content);
  }

  public InputStream getInputStream()
    throws IOException
  {
    return new ByteArrayInputStream(this.content);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.BasicBinaryBody
 * JD-Core Version:    0.6.0
 */