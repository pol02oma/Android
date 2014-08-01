package org.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class DeflateDecompressingEntity extends DecompressingEntity
{
  public DeflateDecompressingEntity(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
  }

  public Header getContentEncoding()
  {
    return null;
  }

  public long getContentLength()
  {
    return -1L;
  }

  InputStream getDecompressingInputStream(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte1 = new byte[6];
    PushbackInputStream localPushbackInputStream = new PushbackInputStream(paramInputStream, arrayOfByte1.length);
    int i = localPushbackInputStream.read(arrayOfByte1);
    if (i == -1)
      throw new IOException("Unable to read the response");
    byte[] arrayOfByte2 = new byte[1];
    Inflater localInflater = new Inflater();
    while (true)
    {
      int j;
      try
      {
        j = localInflater.inflate(arrayOfByte2);
        if (j != 0)
          break label120;
        if (localInflater.finished())
          throw new IOException("Unable to read the response");
      }
      catch (DataFormatException localDataFormatException)
      {
        localPushbackInputStream.unread(arrayOfByte1, 0, i);
        return new InflaterInputStream(localPushbackInputStream, new Inflater(true));
      }
      if (localInflater.needsDictionary())
      {
        label120: if (j != -1)
          break;
        throw new IOException("Unable to read the response");
      }
      if (!localInflater.needsInput())
        continue;
      localInflater.setInput(arrayOfByte1);
    }
    localPushbackInputStream.unread(arrayOfByte1, 0, i);
    InflaterInputStream localInflaterInputStream = new InflaterInputStream(localPushbackInputStream);
    return localInflaterInputStream;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.entity.DeflateDecompressingEntity
 * JD-Core Version:    0.6.0
 */