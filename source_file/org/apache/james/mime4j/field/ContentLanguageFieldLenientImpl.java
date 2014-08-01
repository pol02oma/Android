package org.apache.james.mime4j.field;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentLanguageField;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.ParserCursor;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.stream.RawFieldParser;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.ContentUtil;

public class ContentLanguageFieldLenientImpl extends AbstractField
  implements ContentLanguageField
{
  private static final int COMMA = 44;
  private static final BitSet DELIM = RawFieldParser.INIT_BITSET(new int[] { 44 });
  public static final FieldParser<ContentLanguageField> PARSER = new FieldParser()
  {
    public ContentLanguageField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentLanguageFieldLenientImpl(paramField, paramDecodeMonitor);
    }
  };
  private List<String> languages;
  private boolean parsed = false;

  ContentLanguageFieldLenientImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    this.languages = new ArrayList();
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
    RawFieldParser localRawFieldParser = RawFieldParser.DEFAULT;
    ParserCursor localParserCursor = new ParserCursor(i, localByteSequence.length());
    while (true)
    {
      String str1 = localRawFieldParser.parseToken(localByteSequence, localParserCursor, DELIM);
      if (str1.length() > 0)
        this.languages.add(str1);
      if (localParserCursor.atEnd())
        break;
      int j = localParserCursor.getPos();
      if (localByteSequence.byteAt(j) != 44)
        continue;
      localParserCursor.updatePos(j + 1);
    }
  }

  public List<String> getLanguages()
  {
    if (!this.parsed)
      parse();
    return new ArrayList(this.languages);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentLanguageFieldLenientImpl
 * JD-Core Version:    0.6.0
 */