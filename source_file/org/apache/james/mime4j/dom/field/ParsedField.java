package org.apache.james.mime4j.dom.field;

import org.apache.james.mime4j.stream.Field;

public abstract interface ParsedField extends Field
{
  public abstract ParseException getParseException();

  public abstract boolean isValidField();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.field.ParsedField
 * JD-Core Version:    0.6.0
 */