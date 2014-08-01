package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.codec.DecoderUtil;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.UnstructuredField;
import org.apache.james.mime4j.stream.Field;

public class UnstructuredFieldImpl extends AbstractField
  implements UnstructuredField
{
  public static final FieldParser<UnstructuredField> PARSER = new FieldParser()
  {
    public UnstructuredField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new UnstructuredFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private boolean parsed = false;
  private String value;

  UnstructuredFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.value = DecoderUtil.decodeEncodedWords(getBody(), this.monitor);
    this.parsed = true;
  }

  public String getValue()
  {
    if (!this.parsed)
      parse();
    return this.value;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.UnstructuredFieldImpl
 * JD-Core Version:    0.6.0
 */