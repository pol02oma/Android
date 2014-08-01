package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentIdField;
import org.apache.james.mime4j.stream.Field;

public class ContentIdFieldImpl extends AbstractField
  implements ContentIdField
{
  public static final FieldParser<ContentIdField> PARSER = new FieldParser()
  {
    public ContentIdField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentIdFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private String id;
  private boolean parsed = false;

  ContentIdFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    String str = getBody();
    if (str != null)
    {
      this.id = str.trim();
      return;
    }
    this.id = null;
  }

  public String getId()
  {
    if (!this.parsed)
      parse();
    return this.id;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentIdFieldImpl
 * JD-Core Version:    0.6.0
 */