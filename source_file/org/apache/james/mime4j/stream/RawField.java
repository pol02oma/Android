package org.apache.james.mime4j.stream;

import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.CharsetUtil;
import org.apache.james.mime4j.util.ContentUtil;
import org.apache.james.mime4j.util.MimeUtil;

public final class RawField
  implements Field
{
  private final String body;
  private final int delimiterIdx;
  private final String name;
  private final ByteSequence raw;

  public RawField(String paramString1, String paramString2)
  {
    this(null, -1, paramString1, paramString2);
  }

  RawField(ByteSequence paramByteSequence, int paramInt, String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Field may not be null");
    this.raw = paramByteSequence;
    this.delimiterIdx = paramInt;
    this.name = paramString1.trim();
    this.body = paramString2;
  }

  public String getBody()
  {
    if (this.body != null)
      return this.body;
    if (this.raw != null)
    {
      int i = this.raw.length();
      int j = 1 + this.delimiterIdx;
      if ((i > j + 1) && (CharsetUtil.isWhitespace((char)(0xFF & this.raw.byteAt(j)))))
        j++;
      return MimeUtil.unfold(ContentUtil.decode(this.raw, j, i - j));
    }
    return null;
  }

  public int getDelimiterIdx()
  {
    return this.delimiterIdx;
  }

  public String getName()
  {
    return this.name;
  }

  public ByteSequence getRaw()
  {
    return this.raw;
  }

  public String toString()
  {
    if (this.raw != null)
      return ContentUtil.decode(this.raw);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name);
    localStringBuilder.append(": ");
    if (this.body != null)
      localStringBuilder.append(this.body);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.RawField
 * JD-Core Version:    0.6.0
 */