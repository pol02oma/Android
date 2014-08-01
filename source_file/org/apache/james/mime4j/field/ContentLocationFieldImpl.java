package org.apache.james.mime4j.field;

import java.io.StringReader;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentLocationField;
import org.apache.james.mime4j.field.structured.parser.StructuredFieldParser;
import org.apache.james.mime4j.stream.Field;

public class ContentLocationFieldImpl extends AbstractField
  implements ContentLocationField
{
  public static final FieldParser<ContentLocationField> PARSER = new FieldParser()
  {
    public ContentLocationField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentLocationFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private String location;
  private org.apache.james.mime4j.field.structured.parser.ParseException parseException;
  private boolean parsed = false;

  ContentLocationFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    String str = getBody();
    this.location = null;
    StructuredFieldParser localStructuredFieldParser;
    if (str != null)
      localStructuredFieldParser = new StructuredFieldParser(new StringReader(str));
    try
    {
      this.location = localStructuredFieldParser.parse().replaceAll("\\s", "");
      return;
    }
    catch (org.apache.james.mime4j.field.structured.parser.ParseException localParseException)
    {
      this.parseException = localParseException;
    }
  }

  public String getLocation()
  {
    if (!this.parsed)
      parse();
    return this.location;
  }

  public org.apache.james.mime4j.dom.field.ParseException getParseException()
  {
    return this.parseException;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentLocationFieldImpl
 * JD-Core Version:    0.6.0
 */