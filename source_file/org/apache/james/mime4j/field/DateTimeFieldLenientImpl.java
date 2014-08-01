package org.apache.james.mime4j.field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.james.mime4j.stream.Field;

public class DateTimeFieldLenientImpl extends AbstractField
  implements DateTimeField
{
  private static final String[] DEFAULT_DATE_FORMATS = { "EEE, dd MMM yyyy HH:mm:ss ZZZZ", "dd MMM yyyy HH:mm:ss ZZZZ" };
  public static final FieldParser<DateTimeField> PARSER = new FieldParser()
  {
    public DateTimeField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new DateTimeFieldLenientImpl(paramField, null, paramDecodeMonitor);
    }
  };
  private Date date;
  private final List<String> datePatterns = new ArrayList();
  private boolean parsed = false;

  DateTimeFieldLenientImpl(Field paramField, Collection<String> paramCollection, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
    if (paramCollection != null)
      this.datePatterns.addAll(paramCollection);
    while (true)
    {
      return;
      for (String str : DEFAULT_DATE_FORMATS)
        this.datePatterns.add(str);
    }
  }

  public static FieldParser<DateTimeField> createParser(Collection<String> paramCollection)
  {
    return new FieldParser(paramCollection)
    {
      public DateTimeField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
      {
        return new DateTimeFieldLenientImpl(paramField, this.val$dateParsers, paramDecodeMonitor);
      }
    };
  }

  private void parse()
  {
    this.parsed = true;
    this.date = null;
    String str1 = getBody();
    Iterator localIterator = this.datePatterns.iterator();
    while (true)
    {
      String str2;
      if (localIterator.hasNext())
        str2 = (String)localIterator.next();
      try
      {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str2, Locale.US);
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        localSimpleDateFormat.setLenient(true);
        this.date = localSimpleDateFormat.parse(str1);
        return;
      }
      catch (ParseException localParseException)
      {
      }
    }
  }

  public Date getDate()
  {
    if (!this.parsed)
      parse();
    return this.date;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.DateTimeFieldLenientImpl
 * JD-Core Version:    0.6.0
 */