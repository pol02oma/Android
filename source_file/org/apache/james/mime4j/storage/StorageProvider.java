package org.apache.james.mime4j.storage;

import java.io.IOException;
import java.io.InputStream;

public abstract interface StorageProvider
{
  public abstract StorageOutputStream createStorageOutputStream()
    throws IOException;

  public abstract Storage store(InputStream paramInputStream)
    throws IOException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.storage.StorageProvider
 * JD-Core Version:    0.6.0
 */