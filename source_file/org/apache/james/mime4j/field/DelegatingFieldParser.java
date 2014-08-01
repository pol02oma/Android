package org.apache.james.mime4j.field;

import java.util.HashMap;
import java.util.Map;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public class DelegatingFieldParser
  implements FieldParser<ParsedField>
{
  private final FieldParser<? extends ParsedField> defaultParser;
  private final Map<String, FieldParser<? extends ParsedField>> parsers;

  public DelegatingFieldParser(FieldParser<? extends ParsedField> paramFieldParser)
  {
    this.defaultParser = paramFieldParser;
    this.parsers = new HashMap();
  }

  public FieldParser<? extends ParsedField> getParser(String paramString)
  {
    FieldParser localFieldParser = (FieldParser)this.parsers.get(paramString.toLowerCase());
    if (localFieldParser == null)
      localFieldParser = this.defaultParser;
    return localFieldParser;
  }

  public ParsedField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    return getParser(paramField.getName()).parse(paramField, paramDecodeMonitor);
  }

  public void setFieldParser(String paramString, FieldParser<? extends ParsedField> paramFieldParser)
  {
    this.parsers.put(paramString.toLowerCase(), paramFieldParser);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.DelegatingFieldParser
 * JD-Core Version:    0.6.0
 */