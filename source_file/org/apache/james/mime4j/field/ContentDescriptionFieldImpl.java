package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentDescriptionField;
import org.apache.james.mime4j.stream.Field;

public class ContentDescriptionFieldImpl extends AbstractField
  implements ContentDescriptionField
{
  public static final FieldParser<ContentDescriptionField> PARSER = new FieldParser()
  {
    public ContentDescriptionField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentDescriptionFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private String description;
  private boolean parsed = false;

  ContentDescriptionFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    String str = getBody();
    if (str != null)
    {
      this.description = str.trim();
      return;
    }
    this.description = null;
  }

  public String getDescription()
  {
    if (!this.parsed)
      parse();
    return this.description;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentDescriptionFieldImpl
 * JD-Core Version:    0.6.0
 */