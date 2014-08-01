package org.apache.james.mime4j.field;

import java.io.StringReader;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.datetime.DateTime;
import org.apache.james.mime4j.dom.field.ContentDispositionField;
import org.apache.james.mime4j.field.contentdisposition.parser.ContentDispositionParser;
import org.apache.james.mime4j.field.contentdisposition.parser.TokenMgrError;
import org.apache.james.mime4j.field.datetime.parser.DateTimeParser;
import org.apache.james.mime4j.stream.Field;

public class ContentDispositionFieldImpl extends AbstractField
  implements ContentDispositionField
{
  public static final FieldParser<ContentDispositionField> PARSER = new FieldParser()
  {
    public ContentDispositionField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentDispositionFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private Date creationDate;
  private boolean creationDateParsed;
  private String dispositionType = "";
  private Date modificationDate;
  private boolean modificationDateParsed;
  private Map<String, String> parameters = new HashMap();
  private org.apache.james.mime4j.field.contentdisposition.parser.ParseException parseException;
  private boolean parsed = false;
  private Date readDate;
  private boolean readDateParsed;

  ContentDispositionFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    ContentDispositionParser localContentDispositionParser = new ContentDispositionParser(new StringReader(getBody()));
    try
    {
      localContentDispositionParser.parseAll();
      String str1 = localContentDispositionParser.getDispositionType();
      if (str1 != null)
      {
        this.dispositionType = str1.toLowerCase(Locale.US);
        List localList1 = localContentDispositionParser.getParamNames();
        List localList2 = localContentDispositionParser.getParamValues();
        if ((localList1 != null) && (localList2 != null))
        {
          int i = Math.min(localList1.size(), localList2.size());
          for (int j = 0; j < i; j++)
          {
            String str2 = ((String)localList1.get(j)).toLowerCase(Locale.US);
            String str3 = (String)localList2.get(j);
            this.parameters.put(str2, str3);
          }
        }
      }
    }
    catch (org.apache.james.mime4j.field.contentdisposition.parser.ParseException localParseException)
    {
      while (true)
        this.parseException = localParseException;
    }
    catch (TokenMgrError localTokenMgrError)
    {
      while (true)
        this.parseException = new org.apache.james.mime4j.field.contentdisposition.parser.ParseException(localTokenMgrError.getMessage());
      this.parsed = true;
    }
  }

  private Date parseDate(String paramString)
  {
    String str = getParameter(paramString);
    if (str == null)
      this.monitor.warn("Parsing " + paramString + " null", "returning null");
    while (true)
    {
      return null;
      try
      {
        Date localDate = new DateTimeParser(new StringReader(str)).parseAll().getDate();
        return localDate;
      }
      catch (org.apache.james.mime4j.field.datetime.parser.ParseException localParseException)
      {
        if (!this.monitor.isListening())
          continue;
        this.monitor.warn(paramString + " parameter is invalid: " + str, paramString + " parameter is ignored");
        return null;
      }
      catch (TokenMgrError localTokenMgrError)
      {
        this.monitor.warn(paramString + " parameter is invalid: " + str, paramString + "parameter is ignored");
      }
    }
    return null;
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

  public org.apache.james.mime4j.field.contentdisposition.parser.ParseException getParseException()
  {
    if (!this.parsed)
      parse();
    return this.parseException;
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
 * Qualified Name:     org.apache.james.mime4j.field.ContentDispositionFieldImpl
 * JD-Core Version:    0.6.0
 */