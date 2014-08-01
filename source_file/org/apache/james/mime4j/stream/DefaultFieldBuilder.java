package org.apache.james.mime4j.stream;

import java.util.BitSet;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.io.MaxHeaderLengthLimitException;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class DefaultFieldBuilder
  implements FieldBuilder
{
  private static final BitSet FIELD_CHARS = new BitSet();
  private final ByteArrayBuffer buf = new ByteArrayBuffer(1024);
  private final int maxlen;

  static
  {
    for (int i = 33; i <= 57; i++)
      FIELD_CHARS.set(i);
    for (int j = 59; j <= 126; j++)
      FIELD_CHARS.set(j);
  }

  public DefaultFieldBuilder(int paramInt)
  {
    this.maxlen = paramInt;
  }

  public void append(ByteArrayBuffer paramByteArrayBuffer)
    throws MaxHeaderLengthLimitException
  {
    if (paramByteArrayBuffer == null)
      return;
    int i = paramByteArrayBuffer.length();
    if ((this.maxlen > 0) && (i + this.buf.length() >= this.maxlen))
      throw new MaxHeaderLengthLimitException("Maximum header length limit exceeded");
    this.buf.append(paramByteArrayBuffer.buffer(), 0, paramByteArrayBuffer.length());
  }

  public RawField build()
    throws MimeException
  {
    int i = this.buf.length();
    if (i > 0)
    {
      if (this.buf.byteAt(i - 1) == 10)
        i--;
      if (this.buf.byteAt(i - 1) == 13)
        i--;
    }
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(this.buf.buffer(), i, false);
    RawField localRawField = RawFieldParser.DEFAULT.parseField(localByteArrayBuffer);
    String str = localRawField.getName();
    for (int j = 0; j < str.length(); j++)
    {
      int k = str.charAt(j);
      if (FIELD_CHARS.get(k))
        continue;
      throw new MimeException("MIME field name contains illegal characters: " + localRawField.getName());
    }
    return localRawField;
  }

  public ByteArrayBuffer getRaw()
  {
    return this.buf;
  }

  public void reset()
  {
    this.buf.clear();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.DefaultFieldBuilder
 * JD-Core Version:    0.6.0
 */