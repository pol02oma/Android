package org.apache.james.mime4j.field;

import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.field.contenttype.parser.ContentTypeParser;
import org.apache.james.mime4j.field.contenttype.parser.ParseException;
import org.apache.james.mime4j.field.contenttype.parser.TokenMgrError;
import org.apache.james.mime4j.stream.Field;

public class ContentTypeFieldImpl extends AbstractField
  implements ContentTypeField
{
  public static final FieldParser<ContentTypeField> PARSER = new FieldParser()
  {
    public ContentTypeField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentTypeFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private String mediaType = null;
  private String mimeType = null;
  private Map<String, String> parameters = new HashMap();
  private ParseException parseException;
  private boolean parsed = false;
  private String subType = null;

  ContentTypeFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  public static String getCharset(ContentTypeField paramContentTypeField)
  {
    if (paramContentTypeField != null)
    {
      String str = paramContentTypeField.getCharset();
      if ((str != null) && (str.length() > 0))
        return str;
    }
    return "us-ascii";
  }

  public static String getMimeType(ContentTypeField paramContentTypeField1, ContentTypeField paramContentTypeField2)
  {
    if ((paramContentTypeField1 == null) || (paramContentTypeField1.getMimeType() == null) || ((paramContentTypeField1.isMultipart()) && (paramContentTypeField1.getBoundary() == null)))
    {
      if ((paramContentTypeField2 != null) && (paramContentTypeField2.isMimeType("multipart/digest")))
        return "message/rfc822";
      return "text/plain";
    }
    return paramContentTypeField1.getMimeType();
  }

  private void parse()
  {
    ContentTypeParser localContentTypeParser = new ContentTypeParser(new StringReader(getBody()));
    try
    {
      localContentTypeParser.parseAll();
      this.mediaType = localContentTypeParser.getType();
      this.subType = localContentTypeParser.getSubType();
      if ((this.mediaType != null) && (this.subType != null))
      {
        this.mimeType = (this.mediaType + "/" + this.subType).toLowerCase();
        List localList1 = localContentTypeParser.getParamNames();
        List localList2 = localContentTypeParser.getParamValues();
        if ((localList1 != null) && (localList2 != null))
        {
          int i = Math.min(localList1.size(), localList2.size());
          for (int j = 0; j < i; j++)
          {
            String str1 = ((String)localList1.get(j)).toLowerCase();
            String str2 = (String)localList2.get(j);
            this.parameters.put(str1, str2);
          }
        }
      }
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
      this.parsed = true;
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

  public ParseException getParseException()
  {
    if (!this.parsed)
      parse();
    return this.parseException;
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
 * Qualified Name:     org.apache.james.mime4j.field.ContentTypeFieldImpl
 * JD-Core Version:    0.6.0
 */