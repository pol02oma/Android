package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.field.ParseException;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.util.ByteSequence;

public abstract class AbstractField
  implements ParsedField
{
  protected final DecodeMonitor monitor;
  protected final Field rawField;

  protected AbstractField(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    this.rawField = paramField;
    if (paramDecodeMonitor != null);
    while (true)
    {
      this.monitor = paramDecodeMonitor;
      return;
      paramDecodeMonitor = DecodeMonitor.SILENT;
    }
  }

  public String getBody()
  {
    return this.rawField.getBody();
  }

  public String getName()
  {
    return this.rawField.getName();
  }

  public ParseException getParseException()
  {
    return null;
  }

  public ByteSequence getRaw()
  {
    return this.rawField.getRaw();
  }

  protected RawField getRawField()
  {
    if ((this.rawField instanceof RawField))
      return (RawField)this.rawField;
    return new RawField(this.rawField.getName(), this.rawField.getBody());
  }

  public boolean isValidField()
  {
    return getParseException() == null;
  }

  public String toString()
  {
    return this.rawField.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.AbstractField
 * JD-Core Version:    0.6.0
 */