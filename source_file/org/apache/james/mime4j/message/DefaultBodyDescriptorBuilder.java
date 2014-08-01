package org.apache.james.mime4j.message;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.field.DefaultFieldParser;
import org.apache.james.mime4j.stream.BodyDescriptor;
import org.apache.james.mime4j.stream.BodyDescriptorBuilder;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.util.MimeUtil;

public class DefaultBodyDescriptorBuilder
  implements BodyDescriptorBuilder
{
  private static final String CONTENT_TYPE = "Content-Type".toLowerCase(Locale.US);
  private static final String DEFAULT_MEDIA_TYPE = "text";
  private static final String DEFAULT_MIME_TYPE = "text/plain";
  private static final String DEFAULT_SUB_TYPE = "plain";
  private static final String EMAIL_MESSAGE_MIME_TYPE = "message/rfc822";
  private static final String MEDIA_TYPE_MESSAGE = "message";
  private static final String MEDIA_TYPE_TEXT = "text";
  private static final String SUB_TYPE_EMAIL = "rfc822";
  private static final String US_ASCII = "us-ascii";
  private final FieldParser<? extends ParsedField> fieldParser;
  private final Map<String, ParsedField> fields;
  private final DecodeMonitor monitor;
  private final String parentMimeType;

  public DefaultBodyDescriptorBuilder()
  {
    this(null);
  }

  public DefaultBodyDescriptorBuilder(String paramString)
  {
    this(paramString, null, null);
  }

  public DefaultBodyDescriptorBuilder(String paramString, FieldParser<? extends ParsedField> paramFieldParser, DecodeMonitor paramDecodeMonitor)
  {
    this.parentMimeType = paramString;
    if (paramFieldParser != null)
    {
      this.fieldParser = paramFieldParser;
      if (paramDecodeMonitor == null)
        break label46;
    }
    while (true)
    {
      this.monitor = paramDecodeMonitor;
      this.fields = new HashMap();
      return;
      paramFieldParser = DefaultFieldParser.getParser();
      break;
      label46: paramDecodeMonitor = DecodeMonitor.SILENT;
    }
  }

  public Field addField(RawField paramRawField)
    throws MimeException
  {
    ParsedField localParsedField = this.fieldParser.parse(paramRawField, this.monitor);
    String str = localParsedField.getName().toLowerCase(Locale.US);
    if (!this.fields.containsKey(str))
      this.fields.put(str, localParsedField);
    return localParsedField;
  }

  public BodyDescriptor build()
  {
    ContentTypeField localContentTypeField = (ContentTypeField)this.fields.get(CONTENT_TYPE);
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    if (localContentTypeField != null)
    {
      str1 = localContentTypeField.getMimeType();
      str2 = localContentTypeField.getMediaType();
      str3 = localContentTypeField.getSubType();
      str5 = localContentTypeField.getCharset();
      str4 = localContentTypeField.getBoundary();
    }
    if (str1 == null)
    {
      if (!MimeUtil.isSameMimeType("multipart/digest", this.parentMimeType))
        break label145;
      str1 = "message/rfc822";
      str2 = "message";
    }
    for (str3 = "rfc822"; ; str3 = "plain")
    {
      if ((str5 == null) && ("text".equals(str2)))
        str5 = "us-ascii";
      if (!MimeUtil.isMultipart(str1))
        str4 = null;
      return new MaximalBodyDescriptor(str1, str2, str3, str4, str5, this.fields);
      label145: str1 = "text/plain";
      str2 = "text";
    }
  }

  public BodyDescriptorBuilder newChild()
  {
    ContentTypeField localContentTypeField = (ContentTypeField)this.fields.get(CONTENT_TYPE);
    String str;
    if (localContentTypeField != null)
      str = localContentTypeField.getMimeType();
    while (true)
    {
      return new DefaultBodyDescriptorBuilder(str, this.fieldParser, this.monitor);
      if (MimeUtil.isSameMimeType("multipart/digest", this.parentMimeType))
      {
        str = "message/rfc822";
        continue;
      }
      str = "text/plain";
    }
  }

  public void reset()
  {
    this.fields.clear();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.DefaultBodyDescriptorBuilder
 * JD-Core Version:    0.6.0
 */