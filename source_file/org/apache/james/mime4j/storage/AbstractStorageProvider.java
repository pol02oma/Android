package org.apache.james.mime4j.storage;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.codec.CodecUtil;

public abstract class AbstractStorageProvider
  implements StorageProvider
{
  public final Storage store(InputStream paramInputStream)
    throws IOException
  {
    StorageOutputStream localStorageOutputStream = createStorageOutputStream();
    CodecUtil.copy(paramInputStream, localStorageOutputStream);
    return localStorageOutputStream.toStorage();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.storage.AbstractStorageProvider
 * JD-Core Version:    0.6.0
 */