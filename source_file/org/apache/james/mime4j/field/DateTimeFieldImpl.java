package org.apache.james.mime4j.field;

import java.io.StringReader;
import java.util.Date;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.datetime.DateTime;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.james.mime4j.field.datetime.parser.DateTimeParser;
import org.apache.james.mime4j.field.datetime.parser.ParseException;
import org.apache.james.mime4j.field.datetime.parser.TokenMgrError;
import org.apache.james.mime4j.stream.Field;

public class DateTimeFieldImpl extends AbstractField
  implements DateTimeField
{
  public static final FieldParser<DateTimeField> PARSER = new FieldParser()
  {
    public DateTimeField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new DateTimeFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private Date date;
  private ParseException parseException;
  private boolean parsed = false;

  DateTimeFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    String str = getBody();
    try
    {
      this.date = new DateTimeParser(new StringReader(str)).parseAll().getDate();
      this.parsed = true;
      return;
    }
    catch (ParseException localParseException)
    {
      while (true)
        this.parseException = localParseException;
    }
    catch (TokenMgrError localTokenMgrError)
    {
      while (true)
        this.parseException = new ParseException(localTokenMgrError.getMessage());
    }
  }

  public Date getDate()
  {
    if (!this.parsed)
      parse();
    return this.date;
  }

  public ParseException getParseException()
  {
    if (!this.parsed)
      parse();
    return this.parseException;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.DateTimeFieldImpl
 * JD-Core Version:    0.6.0
 */