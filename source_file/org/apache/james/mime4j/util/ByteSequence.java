package org.apache.james.mime4j.util;

public abstract interface ByteSequence
{
  public static final ByteSequence EMPTY = new EmptyByteSequence();

  public abstract byte byteAt(int paramInt);

  public abstract int length();

  public abstract byte[] toByteArray();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.util.ByteSequence
 * JD-Core Version:    0.6.0
 */