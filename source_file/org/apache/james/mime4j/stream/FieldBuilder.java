package org.apache.james.mime4j.stream;

import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public abstract interface FieldBuilder
{
  public abstract void append(ByteArrayBuffer paramByteArrayBuffer)
    throws MimeException;

  public abstract RawField build()
    throws MimeException;

  public abstract ByteArrayBuffer getRaw();

  public abstract void reset();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.FieldBuilder
 * JD-Core Version:    0.6.0
 */