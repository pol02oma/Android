package org.apache.james.mime4j.storage;

import java.io.IOException;
import java.io.InputStream;

public class MultiReferenceStorage
  implements Storage
{
  private int referenceCounter;
  private final Storage storage;

  public MultiReferenceStorage(Storage paramStorage)
  {
    if (paramStorage == null)
      throw new IllegalArgumentException();
    this.storage = paramStorage;
    this.referenceCounter = 1;
  }

  private boolean decrementCounter()
  {
    monitorenter;
    try
    {
      if (this.referenceCounter == 0)
        throw new IllegalStateException("storage has been deleted");
    }
    finally
    {
      monitorexit;
    }
    int i = -1 + this.referenceCounter;
    this.referenceCounter = i;
    if (i == 0);
    for (int j = 1; ; j = 0)
    {
      monitorexit;
      return j;
    }
  }

  private void incrementCounter()
  {
    monitorenter;
    try
    {
      if (this.referenceCounter == 0)
        throw new IllegalStateException("storage has been deleted");
    }
    finally
    {
      monitorexit;
    }
    this.referenceCounter = (1 + this.referenceCounter);
    monitorexit;
  }

  public void addReference()
  {
    incrementCounter();
  }

  public void delete()
  {
    if (decrementCounter())
      this.storage.delete();
  }

  public InputStream getInputStream()
    throws IOException
  {
    return this.storage.getInputStream();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.storage.MultiReferenceStorage
 * JD-Core Version:    0.6.0
 */