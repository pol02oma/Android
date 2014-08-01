package org.apache.james.mime4j.field;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.NameValuePair;
import org.apache.james.mime4j.stream.RawBody;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.stream.RawFieldParser;

public class ContentTypeFieldLenientImpl extends AbstractField
  implements ContentTypeField
{
  public static final FieldParser<ContentTypeField> PARSER = new FieldParser()
  {
    public ContentTypeField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentTypeFieldLenientImpl(paramField, paramDecodeMonitor);
    }
  };
  private String mediaType = null;
  private String mimeType = null;
  private Map<String, String> parameters = new HashMap();
  private boolean parsed = false;
  private String subType = null;

  ContentTypeFieldLenientImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    RawField localRawField = getRawField();
    RawBody localRawBody = RawFieldParser.DEFAULT.parseRawBody(localRawField);
    String str1 = localRawBody.getValue();
    String str2 = null;
    String str3 = null;
    if (str1 != null)
    {
      str1 = str1.toLowerCase().trim();
      int i = str1.indexOf('/');
      str2 = null;
      str3 = null;
      int j = 0;
      if (i != -1)
      {
        str3 = str1.substring(0, i).trim();
        str2 = str1.substring(i + 1).trim();
        int k = str3.length();
        j = 0;
        if (k > 0)
        {
          int m = str2.length();
          j = 0;
          if (m > 0)
          {
            str1 = str3 + "/" + str2;
            j = 1;
          }
        }
      }
      if (j == 0)
      {
        if (this.monitor.isListening())
          this.monitor.warn("Invalid Content-Type: " + localRawBody, "Content-Type value ignored");
        str1 = null;
        str3 = null;
        str2 = null;
      }
    }
    this.mimeType = str1;
    this.mediaType = str3;
    this.subType = str2;
    this.parameters.clear();
    Iterator localIterator = localRawBody.getParams().iterator();
    while (localIterator.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
      String str4 = localNameValuePair.getName().toLowerCase(Locale.US);
      this.parameters.put(str4, localNameValuePair.getValue());
    }
  }

  public String getBoundary()
  {
    return getParameter("boundary");
  }

  public String getCharset()
  {
    return getParameter("charset");
  }

  public String getMediaType()
  {
    if (!this.parsed)
      parse();
    return this.mediaType;
  }

  public String getMimeType()
  {
    if (!this.parsed)
      parse();
    return this.mimeType;
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

  public String getSubType()
  {
    if (!this.parsed)
      parse();
    return this.subType;
  }

  public boolean isMimeType(String paramString)
  {
    if (!this.parsed)
      parse();
    return (this.mimeType != null) && (this.mimeType.equalsIgnoreCase(paramString));
  }

  public boolean isMultipart()
  {
    if (!this.parsed)
      parse();
    return (this.mimeType != null) && (this.mimeType.startsWith("multipart/"));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentTypeFieldLenientImpl
 * JD-Core Version:    0.6.0
 */