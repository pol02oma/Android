package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.util.EntityUtils;

@NotThreadSafe
public class BufferedHttpEntity extends HttpEntityWrapper
{
  private final byte[] buffer;

  public BufferedHttpEntity(HttpEntity paramHttpEntity)
    throws IOException
  {
    super(paramHttpEntity);
    if ((!paramHttpEntity.isRepeatable()) || (paramHttpEntity.getContentLength() < 0L))
    {
      this.buffer = EntityUtils.toByteArray(paramHttpEntity);
      return;
    }
    this.buffer = null;
  }

  public InputStream getContent()
    throws IOException
  {
    if (this.buffer != null)
      return new ByteArrayInputStream(this.buffer);
    return this.wrappedEntity.getContent();
  }

  public long getContentLength()
  {
    if (this.buffer != null)
      return this.buffer.length;
    return this.wrappedEntity.getContentLength();
  }

  public boolean isChunked()
  {
    return (this.buffer == null) && (this.wrappedEntity.isChunked());
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public boolean isStreaming()
  {
    return (this.buffer == null) && (this.wrappedEntity.isStreaming());
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    if (this.buffer != null)
    {
      paramOutputStream.write(this.buffer);
      return;
    }
    this.wrappedEntity.writeTo(paramOutputStream);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.entity.BufferedHttpEntity
 * JD-Core Version:    0.6.0
 */