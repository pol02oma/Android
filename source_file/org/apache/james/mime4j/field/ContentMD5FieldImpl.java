package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentMD5Field;
import org.apache.james.mime4j.stream.Field;

public class ContentMD5FieldImpl extends AbstractField
  implements ContentMD5Field
{
  public static final FieldParser<ContentMD5Field> PARSER = new FieldParser()
  {
    public ContentMD5Field parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentMD5FieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private String md5raw;
  private boolean parsed = false;

  ContentMD5FieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    String str = getBody();
    if (str != null)
    {
      this.md5raw = str.trim();
      return;
    }
    this.md5raw = null;
  }

  public String getMD5Raw()
  {
    if (!this.parsed)
      parse();
    return this.md5raw;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentMD5FieldImpl
 * JD-Core Version:    0.6.0
 */