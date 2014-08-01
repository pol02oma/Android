package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentLocationField;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.ParserCursor;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.stream.RawFieldParser;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.CharsetUtil;
import org.apache.james.mime4j.util.ContentUtil;

public class ContentLocationFieldLenientImpl extends AbstractField
  implements ContentLocationField
{
  public static final FieldParser<ContentLocationField> PARSER = new FieldParser()
  {
    public ContentLocationField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentLocationFieldLenientImpl(paramField, paramDecodeMonitor);
    }
  };
  private String location;
  private boolean parsed = false;

  ContentLocationFieldLenientImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    this.location = null;
    RawField localRawField = getRawField();
    ByteSequence localByteSequence = localRawField.getRaw();
    int i = 1 + localRawField.getDelimiterIdx();
    if (localByteSequence == null)
    {
      String str2 = localRawField.getBody();
      if (str2 == null)
        return;
      localByteSequence = ContentUtil.encode(str2);
      i = 0;
    }
    String str1 = RawFieldParser.DEFAULT.parseValue(localByteSequence, new ParserCursor(i, localByteSequence.length()), null);
    StringBuilder localStringBuilder = new StringBuilder(str1.length());
    for (int j = 0; j < str1.length(); j++)
    {
      char c = str1.charAt(j);
      if (CharsetUtil.isWhitespace(c))
        continue;
      localStringBuilder.append(c);
    }
    this.location = localStringBuilder.toString();
  }

  public String getLocation()
  {
    if (!this.parsed)
      parse();
    return this.location;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentLocationFieldLenientImpl
 * JD-Core Version:    0.6.0
 */