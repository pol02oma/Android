package org.apache.james.mime4j.field;

import java.io.StringReader;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.MimeVersionField;
import org.apache.james.mime4j.field.mimeversion.parser.MimeVersionParser;
import org.apache.james.mime4j.stream.Field;

public class MimeVersionFieldImpl extends AbstractField
  implements MimeVersionField
{
  public static final int DEFAULT_MAJOR_VERSION = 1;
  public static final int DEFAULT_MINOR_VERSION;
  public static final FieldParser<MimeVersionField> PARSER = new FieldParser()
  {
    public MimeVersionField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new MimeVersionFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private int major = 1;
  private int minor = 0;
  private boolean parsed = false;
  private org.apache.james.mime4j.field.mimeversion.parser.ParseException parsedException;

  MimeVersionFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    this.major = 1;
    this.minor = 0;
    String str = getBody();
    MimeVersionParser localMimeVersionParser;
    if (str != null)
      localMimeVersionParser = new MimeVersionParser(new StringReader(str));
    try
    {
      localMimeVersionParser.parse();
      int i = localMimeVersionParser.getMajorVersion();
      if (i != -1)
        this.major = i;
      int j = localMimeVersionParser.getMinorVersion();
      if (j != -1)
        this.minor = j;
      return;
    }
    catch (MimeException localMimeException)
    {
      this.parsedException = new org.apache.james.mime4j.field.mimeversion.parser.ParseException(localMimeException);
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

  public org.apache.james.mime4j.dom.field.ParseException getParseException()
  {
    return this.parsedException;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.MimeVersionFieldImpl
 * JD-Core Version:    0.6.0
 */