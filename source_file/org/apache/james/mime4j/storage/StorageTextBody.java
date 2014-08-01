package org.apache.james.mime4j.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import org.apache.james.mime4j.dom.TextBody;

class StorageTextBody extends TextBody
{
  private Charset charset;
  private MultiReferenceStorage storage;

  public StorageTextBody(MultiReferenceStorage paramMultiReferenceStorage, Charset paramCharset)
  {
    this.storage = paramMultiReferenceStorage;
    this.charset = paramCharset;
  }

  public StorageTextBody copy()
  {
    this.storage.addReference();
    return new StorageTextBody(this.storage, this.charset);
  }

  public void dispose()
  {
    if (this.storage != null)
    {
      this.storage.delete();
      this.storage = null;
    }
  }

  public InputStream getInputStream()
    throws IOException
  {
    return this.storage.getInputStream();
  }

  public String getMimeCharset()
  {
    return this.charset.name();
  }

  public Reader getReader()
    throws IOException
  {
    return new InputStreamReader(this.storage.getInputStream(), this.charset);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.storage.StorageTextBody
 * JD-Core Version:    0.6.0
 */