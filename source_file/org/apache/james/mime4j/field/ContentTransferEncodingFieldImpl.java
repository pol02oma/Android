package org.apache.james.mime4j.field;

import java.util.Locale;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentTransferEncodingField;
import org.apache.james.mime4j.stream.Field;

public class ContentTransferEncodingFieldImpl extends AbstractField
  implements ContentTransferEncodingField
{
  public static final FieldParser<ContentTransferEncodingField> PARSER = new FieldParser()
  {
    public ContentTransferEncodingField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentTransferEncodingFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private String encoding;
  private boolean parsed = false;

  ContentTransferEncodingFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  public static String getEncoding(ContentTransferEncodingField paramContentTransferEncodingField)
  {
    if ((paramContentTransferEncodingField != null) && (paramContentTransferEncodingField.getEncoding().length() != 0))
      return paramContentTransferEncodingField.getEncoding();
    return "7bit";
  }

  private void parse()
  {
    this.parsed = true;
    String str = getBody();
    if (str != null)
    {
      this.encoding = str.trim().toLowerCase(Locale.US);
      return;
    }
    this.encoding = null;
  }

  public String getEncoding()
  {
    if (!this.parsed)
      parse();
    return this.encoding;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentTransferEncodingFieldImpl
 * JD-Core Version:    0.6.0
 */