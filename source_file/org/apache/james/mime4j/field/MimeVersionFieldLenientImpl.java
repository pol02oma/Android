package org.apache.james.mime4j.field;

import java.util.BitSet;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.MimeVersionField;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.ParserCursor;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.stream.RawFieldParser;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.ContentUtil;

public class MimeVersionFieldLenientImpl extends AbstractField
  implements MimeVersionField
{
  public static final int DEFAULT_MAJOR_VERSION = 1;
  public static final int DEFAULT_MINOR_VERSION = 0;
  private static final BitSet DELIM = RawFieldParser.INIT_BITSET(new int[] { 46 });
  private static final int FULL_STOP = 46;
  public static final FieldParser<MimeVersionField> PARSER = new FieldParser()
  {
    public MimeVersionField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new MimeVersionFieldLenientImpl(paramField, paramDecodeMonitor);
    }
  };
  private int major = 1;
  private int minor = 0;
  private boolean parsed = false;

  MimeVersionFieldLenientImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    this.major = 1;
    this.minor = 0;
    RawField localRawField = getRawField();
    ByteSequence localByteSequence = localRawField.getRaw();
    int i = 1 + localRawField.getDelimiterIdx();
    String str3;
    if (localByteSequence == null)
    {
      str3 = localRawField.getBody();
      if (str3 != null);
    }
    while (true)
    {
      return;
      localByteSequence = ContentUtil.encode(str3);
      i = 0;
      RawFieldParser localRawFieldParser = RawFieldParser.DEFAULT;
      ParserCursor localParserCursor = new ParserCursor(i, localByteSequence.length());
      String str1 = localRawFieldParser.parseValue(localByteSequence, localParserCursor, DELIM);
      try
      {
        this.major = Integer.parseInt(str1);
        if (this.major < 0)
          this.major = 0;
        label111: if ((!localParserCursor.atEnd()) && (localByteSequence.byteAt(localParserCursor.getPos()) == 46))
          localParserCursor.updatePos(1 + localParserCursor.getPos());
        String str2 = localRawFieldParser.parseValue(localByteSequence, localParserCursor, null);
        try
        {
          this.minor = Integer.parseInt(str2);
          if (this.minor >= 0)
            continue;
          this.minor = 0;
          return;
        }
        catch (NumberFormatException localNumberFormatException2)
        {
          return;
        }
      }
      catch (NumberFormatException localNumberFormatException1)
      {
        break label111;
      }
    }
  }

  public int getMajorVersion()
  {
    if (!this.parsed)
      parse();
    return this.major;
  }

  public int getMinorVersion()
  {
    if (!this.parsed)
      parse();
    return this.minor;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.MimeVersionFieldLenientImpl
 * JD-Core Version:    0.6.0
 */