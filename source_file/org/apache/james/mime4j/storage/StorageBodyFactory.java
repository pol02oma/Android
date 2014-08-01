package org.apache.james.mime4j.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.BinaryBody;
import org.apache.james.mime4j.dom.TextBody;
import org.apache.james.mime4j.message.BodyFactory;
import org.apache.james.mime4j.util.CharsetUtil;

public class StorageBodyFactory
  implements BodyFactory
{
  private static final Charset FALLBACK_CHARSET = CharsetUtil.DEFAULT_CHARSET;
  private final DecodeMonitor monitor;
  private final StorageProvider storageProvider;

  public StorageBodyFactory()
  {
    this(null, null);
  }

  public StorageBodyFactory(StorageProvider paramStorageProvider, DecodeMonitor paramDecodeMonitor)
  {
    if (paramStorageProvider != null)
    {
      this.storageProvider = paramStorageProvider;
      if (paramDecodeMonitor == null)
        break label30;
    }
    while (true)
    {
      this.monitor = paramDecodeMonitor;
      return;
      paramStorageProvider = DefaultStorageProvider.getInstance();
      break;
      label30: paramDecodeMonitor = DecodeMonitor.SILENT;
    }
  }

  private static Charset toJavaCharset(String paramString, boolean paramBoolean, DecodeMonitor paramDecodeMonitor)
  {
    Charset localCharset = CharsetUtil.lookup(paramString);
    if (localCharset == null)
    {
      if (paramDecodeMonitor.isListening())
        paramDecodeMonitor.warn("MIME charset '" + paramString + "' has no " + "corresponding Java charset", "Using " + FALLBACK_CHARSET + " instead.");
      localCharset = FALLBACK_CHARSET;
    }
    return localCharset;
  }

  public BinaryBody binaryBody(InputStream paramInputStream)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException();
    return new StorageBinaryBody(new MultiReferenceStorage(this.storageProvider.store(paramInputStream)));
  }

  public BinaryBody binaryBody(Storage paramStorage)
    throws IOException
  {
    if (paramStorage == null)
      throw new IllegalArgumentException();
    return new StorageBinaryBody(new MultiReferenceStorage(paramStorage));
  }

  public StorageProvider getStorageProvider()
  {
    return this.storageProvider;
  }

  public TextBody textBody(InputStream paramInputStream)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException();
    return new StorageTextBody(new MultiReferenceStorage(this.storageProvider.store(paramInputStream)), CharsetUtil.DEFAULT_CHARSET);
  }

  public TextBody textBody(InputStream paramInputStream, String paramString)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException();
    if (paramString == null)
      throw new IllegalArgumentException();
    Storage localStorage = this.storageProvider.store(paramInputStream);
    Charset localCharset = toJavaCharset(paramString, false, this.monitor);
    return new StorageTextBody(new MultiReferenceStorage(localStorage), localCharset);
  }

  public TextBody textBody(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException();
    return new StringTextBody(paramString, CharsetUtil.DEFAULT_CHARSET);
  }

  public TextBody textBody(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException();
    if (paramString2 == null)
      throw new IllegalArgumentException();
    return new StringTextBody(paramString1, toJavaCharset(paramString2, true, this.monitor));
  }

  public TextBody textBody(Storage paramStorage)
    throws IOException
  {
    if (paramStorage == null)
      throw new IllegalArgumentException();
    return new StorageTextBody(new MultiReferenceStorage(paramStorage), CharsetUtil.DEFAULT_CHARSET);
  }

  public TextBody textBody(Storage paramStorage, String paramString)
    throws IOException
  {
    if (paramStorage == null)
      throw new IllegalArgumentException();
    if (paramString == null)
      throw new IllegalArgumentException();
    Charset localCharset = toJavaCharset(paramString, false, this.monitor);
    return new StorageTextBody(new MultiReferenceStorage(paramStorage), localCharset);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.storage.StorageBodyFactory
 * JD-Core Version:    0.6.0
 */