package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EntityTemplate extends AbstractHttpEntity
{
  private final ContentProducer contentproducer;

  public EntityTemplate(ContentProducer paramContentProducer)
  {
    if (paramContentProducer == null)
      throw new IllegalArgumentException("Content producer may not be null");
    this.contentproducer = paramContentProducer;
  }

  public InputStream getContent()
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    writeTo(localByteArrayOutputStream);
    return new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
  }

  public long getContentLength()
  {
    return -1L;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public boolean isStreaming()
  {
    return false;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    this.contentproducer.writeTo(paramOutputStream);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.entity.EntityTemplate
 * JD-Core Version:    0.6.0
 */