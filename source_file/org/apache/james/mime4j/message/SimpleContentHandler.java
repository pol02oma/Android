package org.apache.james.mime4j.message;

import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.Header;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.field.LenientFieldParser;
import org.apache.james.mime4j.parser.AbstractContentHandler;
import org.apache.james.mime4j.stream.Field;

public abstract class SimpleContentHandler extends AbstractContentHandler
{
  private Header currHeader;
  private final FieldParser<? extends ParsedField> fieldParser;
  private final DecodeMonitor monitor;

  public SimpleContentHandler()
  {
    this(null, null);
  }

  public SimpleContentHandler(FieldParser<? extends ParsedField> paramFieldParser, DecodeMonitor paramDecodeMonitor)
  {
    if (paramFieldParser != null)
    {
      this.fieldParser = paramFieldParser;
      if (paramDecodeMonitor == null)
        break label30;
    }
    while (true)
    {
      this.monitor = paramDecodeMonitor;
      return;
      paramFieldParser = LenientFieldParser.getParser();
      break;
      label30: paramDecodeMonitor = DecodeMonitor.SILENT;
    }
  }

  public final void endHeader()
  {
    Header localHeader = this.currHeader;
    this.currHeader = null;
    headers(localHeader);
  }

  public final void field(Field paramField)
    throws MimeException
  {
    if ((paramField instanceof ParsedField));
    for (ParsedField localParsedField = (ParsedField)paramField; ; localParsedField = this.fieldParser.parse(paramField, this.monitor))
    {
      this.currHeader.addField(localParsedField);
      return;
    }
  }

  public abstract void headers(Header paramHeader);

  public final void startHeader()
  {
    this.currHeader = new HeaderImpl();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.SimpleContentHandler
 * JD-Core Version:    0.6.0
 */