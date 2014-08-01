package org.apache.james.mime4j.util;

final class EmptyByteSequence
  implements ByteSequence
{
  private static final byte[] EMPTY_BYTES = new byte[0];

  public byte byteAt(int paramInt)
  {
    throw new IndexOutOfBoundsException();
  }

  public int length()
  {
    return 0;
  }

  public byte[] toByteArray()
  {
    return EMPTY_BYTES;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.util.EmptyByteSequence
 * JD-Core Version:    0.6.0
 */