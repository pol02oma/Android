package org.apache.james.mime4j.storage;

public class DefaultStorageProvider
{
  public static final String DEFAULT_STORAGE_PROVIDER_PROPERTY = "org.apache.james.mime4j.defaultStorageProvider";
  private static volatile StorageProvider instance = null;

  static
  {
    initialize();
  }

  public static StorageProvider getInstance()
  {
    return instance;
  }

  private static void initialize()
  {
    String str = System.getProperty("org.apache.james.mime4j.defaultStorageProvider");
    if (str != null);
    try
    {
      instance = (StorageProvider)Class.forName(str).newInstance();
      label23: if (instance == null)
        instance = new ThresholdStorageProvider(new TempFileStorageProvider(), 1024);
      return;
    }
    catch (Exception localException)
    {
      break label23;
    }
  }

  static void reset()
  {
    instance = null;
    initialize();
  }

  public static void setInstance(StorageProvider paramStorageProvider)
  {
    if (paramStorageProvider == null)
      throw new IllegalArgumentException();
    instance = paramStorageProvider;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.storage.DefaultStorageProvider
 * JD-Core Version:    0.6.0
 */