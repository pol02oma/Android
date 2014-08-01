package org.apache.james.mime4j.stream;

import org.apache.james.mime4j.util.ByteSequence;

public abstract interface Field
{
  public abstract String getBody();

  public abstract String getName();

  public abstract ByteSequence getRaw();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.Field
 * JD-Core Version:    0.6.0
 */