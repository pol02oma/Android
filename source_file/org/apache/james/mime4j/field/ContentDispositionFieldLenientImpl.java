package org.apache.james.mime4j.field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentDispositionField;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.NameValuePair;
import org.apache.james.mime4j.stream.RawBody;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.stream.RawFieldParser;

public class ContentDispositionFieldLenientImpl extends AbstractField
  implements ContentDispositionField
{
  private static final String DEFAULT_DATE_FORMAT = "EEE, dd MMM yyyy hh:mm:ss ZZZZ";
  public static final FieldParser<ContentDispositionField> PARSER = new FieldParser()
  {
    public ContentDispositionField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentDispositionFieldLenientImpl(paramField, null, paramDecodeMonitor);
    }
  };
  private Date creationDate;
  private boolean creationDateParsed;
  private final List<String> datePatterns = new ArrayList();
  private String dispositionType = "";
  private Date modificationDate;
  private boolean modificationDateParsed;
  private Map<String, String> parameters = new HashMap();
  private boolean parsed = false;
  private Date readDate;
  private boolean readDateParsed;

  ContentDispositionFieldLenientImpl(Field paramField, Collection<String> paramCollection, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
    if (paramCollection != null)
    {
      this.datePatterns.addAll(paramCollection);
      return;
    }
    this.datePatterns.add("EEE, dd MMM yyyy hh:mm:ss ZZZZ");
  }

  private void parse()
  {
    this.parsed = true;
    RawField localRawField = getRawField();
    RawBody localRawBody = RawFieldParser.DEFAULT.parseRawBody(localRawField);
    String str1 = localRawBody.getValue();
    if (str1 != null);
    for (this.dispositionType = str1.toLowerCase(Locale.US); ; this.dispositionType = null)
    {
      this.parameters.clear();
      Iterator localIterator = localRawBody.getParams().iterator();
      while (localIterator.hasNext())
      {
        NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
        String str2 = localNameValuePair.getName().toLowerCase(Locale.US);
        this.parameters.put(str2, localNameValuePair.getValue());
      }
    }
  }

  private Date parseDate(String paramString)
  {
    String str1 = getParameter(paramString);
    if (str1 == null)
      return null;
    Iterator localIterator = this.datePatterns.iterator();
    while (true)
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
          Date localDate = localSimpleDateFormat.parse(str1);
          return localDate;
          if (!this.monitor.isListening())
            break;
          this.monitor.warn(paramString + " parameter is invalid: " + str1, paramString + " parameter is ignored");
          return null;
        }
        catch (ParseException localParseException)
        {
        }
      }
  }

  public Date getCreationDate()
  {
    if (!this.creationDateParsed)
    {
      this.creationDate = parseDate("creation-date");
      this.creationDateParsed = true;
    }
    return this.creationDate;
  }

  public String getDispositionType()
  {
    if (!this.parsed)
      parse();
    return this.dispositionType;
  }

  public String getFilename()
  {
    return getParameter("filename");
  }

  public Date getModificationDate()
  {
    if (!this.modificationDateParsed)
    {
      this.modificationDate = parseDate("modification-date");
      this.modificationDateParsed = true;
    }
    return this.modificationDate;
  }

  public String getParameter(String paramString)
  {
    if (!this.parsed)
      parse();
    return (String)this.parameters.get(paramString.toLowerCase());
  }

  public Map<String, String> getParameters()
  {
    if (!this.parsed)
      parse();
    return Collections.unmodifiableMap(this.parameters);
  }

  public Date getReadDate()
  {
    if (!this.readDateParsed)
    {
      this.readDate = parseDate("read-date");
      this.readDateParsed = true;
    }
    return this.readDate;
  }

  public long getSize()
  {
    String str = getParameter("size");
    if (str == null)
      return -1L;
    try
    {
      long l1 = Long.parseLong(str);
      long l2 = l1;
      if (l2 < 0L)
        l2 = -1L;
      return l2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return -1L;
  }

  public boolean isAttachment()
  {
    if (!this.parsed)
      parse();
    return this.dispositionType.equals("attachment");
  }

  public boolean isDispositionType(String paramString)
  {
    if (!this.parsed)
      parse();
    return this.dispositionType.equalsIgnoreCase(paramString);
  }

  public boolean isInline()
  {
    if (!this.parsed)
      parse();
    return this.dispositionType.equals("inline");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentDispositionFieldLenientImpl
 * JD-Core Version:    0.6.0
 */