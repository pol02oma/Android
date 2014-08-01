package org.apache.james.mime4j.stream;

import org.apache.james.mime4j.MimeException;

public abstract interface BodyDescriptorBuilder
{
  public abstract Field addField(RawField paramRawField)
    throws MimeException;

  public abstract BodyDescriptor build();

  public abstract BodyDescriptorBuilder newChild();

  public abstract void reset();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.BodyDescriptorBuilder
 * JD-Core Version:    0.6.0
 */