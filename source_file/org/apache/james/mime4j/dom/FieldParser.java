package org.apache.james.mime4j.dom;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;

public abstract interface FieldParser<T extends ParsedField>
{
  public abstract T parse(Field paramField, DecodeMonitor paramDecodeMonitor);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.FieldParser
 * JD-Core Version:    0.6.0
 */