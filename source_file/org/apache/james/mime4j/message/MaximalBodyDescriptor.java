package org.apache.james.mime4j.message;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.james.mime4j.dom.field.ContentDescriptionField;
import org.apache.james.mime4j.dom.field.ContentDispositionField;
import org.apache.james.mime4j.dom.field.ContentIdField;
import org.apache.james.mime4j.dom.field.ContentLanguageField;
import org.apache.james.mime4j.dom.field.ContentLengthField;
import org.apache.james.mime4j.dom.field.ContentLocationField;
import org.apache.james.mime4j.dom.field.ContentMD5Field;
import org.apache.james.mime4j.dom.field.ContentTransferEncodingField;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.dom.field.MimeVersionField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.BodyDescriptor;

public class MaximalBodyDescriptor
  implements BodyDescriptor
{
  private static final String CONTENT_DESCRIPTION;
  private static final String CONTENT_DISPOSITION;
  private static final String CONTENT_ID;
  private static final String CONTENT_LANGUAGE;
  private static final String CONTENT_LENGTH;
  private static final String CONTENT_LOCATION;
  private static final String CONTENT_MD5;
  private static final String CONTENT_TRANSFER_ENCODING;
  private static final String CONTENT_TYPE = "Content-Type".toLowerCase(Locale.US);
  private static final String MIME_VERSION;
  private final String boundary;
  private final String charset;
  private final Map<String, ParsedField> fields;
  private final String mediaType;
  private final String mimeType;
  private final String subType;

  static
  {
    CONTENT_LENGTH = "Content-Length".toLowerCase(Locale.US);
    CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding".toLowerCase(Locale.US);
    CONTENT_DISPOSITION = "Content-Disposition".toLowerCase(Locale.US);
    CONTENT_ID = "Content-ID".toLowerCase(Locale.US);
    CONTENT_MD5 = "Content-MD5".toLowerCase(Locale.US);
    CONTENT_DESCRIPTION = "Content-Description".toLowerCase(Locale.US);
    CONTENT_LANGUAGE = "Content-Language".toLowerCase(Locale.US);
    CONTENT_LOCATION = "Content-Location".toLowerCase(Locale.US);
    MIME_VERSION = "MIME-Version".toLowerCase(Locale.US);
  }

  MaximalBodyDescriptor(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Map<String, ParsedField> paramMap)
  {
    this.mimeType = paramString1;
    this.mediaType = paramString2;
    this.subType = paramString3;
    this.boundary = paramString4;
    this.charset = paramString5;
    if (paramMap != null);
    for (Object localObject = new HashMap(paramMap); ; localObject = Collections.emptyMap())
    {
      this.fields = ((Map)localObject);
      return;
    }
  }

  public String getBoundary()
  {
    return this.boundary;
  }

  public String getCharset()
  {
    return this.charset;
  }

  public String getContentDescription()
  {
    ContentDescriptionField localContentDescriptionField = (ContentDescriptionField)this.fields.get(CONTENT_DESCRIPTION);
    if (localContentDescriptionField != null)
      return localContentDescriptionField.getDescription();
    return null;
  }

  public Date getContentDispositionCreationDate()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)this.fields.get(CONTENT_DISPOSITION);
    if (localContentDispositionField != null)
      return localContentDispositionField.getCreationDate();
    return null;
  }

  public String getContentDispositionFilename()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)this.fields.get(CONTENT_DISPOSITION);
    if (localContentDispositionField != null)
      return localContentDispositionField.getFilename();
    return null;
  }

  public Date getContentDispositionModificationDate()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)this.fields.get(CONTENT_DISPOSITION);
    if (localContentDispositionField != null)
      return localContentDispositionField.getModificationDate();
    return null;
  }

  public Map<String, String> getContentDispositionParameters()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)this.fields.get(CONTENT_DISPOSITION);
    if (localContentDispositionField != null)
      return localContentDispositionField.getParameters();
    return Collections.emptyMap();
  }

  public Date getContentDispositionReadDate()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)this.fields.get(CONTENT_DISPOSITION);
    if (localContentDispositionField != null)
      return localContentDispositionField.getReadDate();
    return null;
  }

  public long getContentDispositionSize()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)this.fields.get(CONTENT_DISPOSITION);
    if (localContentDispositionField != null)
      return localContentDispositionField.getSize();
    return -1L;
  }

  public String getContentDispositionType()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)this.fields.get(CONTENT_DISPOSITION);
    if (localContentDispositionField != null)
      return localContentDispositionField.getDispositionType();
    return null;
  }

  public String getContentId()
  {
    ContentIdField localContentIdField = (ContentIdField)this.fields.get(CONTENT_ID);
    if (localContentIdField != null)
      return localContentIdField.getId();
    return null;
  }

  public List<String> getContentLanguage()
  {
    ContentLanguageField localContentLanguageField = (ContentLanguageField)this.fields.get(CONTENT_LANGUAGE);
    if (localContentLanguageField != null)
      return localContentLanguageField.getLanguages();
    return Collections.emptyList();
  }

  public long getContentLength()
  {
    ContentLengthField localContentLengthField = (ContentLengthField)this.fields.get(CONTENT_LENGTH);
    if (localContentLengthField != null)
      return localContentLengthField.getContentLength();
    return -1L;
  }

  public String getContentLocation()
  {
    ContentLocationField localContentLocationField = (ContentLocationField)this.fields.get(CONTENT_LOCATION);
    if (localContentLocationField != null)
      return localContentLocationField.getLocation();
    return null;
  }

  public String getContentMD5Raw()
  {
    ContentMD5Field localContentMD5Field = (ContentMD5Field)this.fields.get(CONTENT_MD5);
    if (localContentMD5Field != null)
      return localContentMD5Field.getMD5Raw();
    return null;
  }

  public Map<String, String> getContentTypeParameters()
  {
    ContentTypeField localContentTypeField = (ContentTypeField)this.fields.get(CONTENT_TYPE);
    if (localContentTypeField != null)
      return localContentTypeField.getParameters();
    return Collections.emptyMap();
  }

  public String getMediaType()
  {
    return this.mediaType;
  }

  public int getMimeMajorVersion()
  {
    MimeVersionField localMimeVersionField = (MimeVersionField)this.fields.get(MIME_VERSION);
    if (localMimeVersionField != null)
      return localMimeVersionField.getMajorVersion();
    return 1;
  }

  public int getMimeMinorVersion()
  {
    MimeVersionField localMimeVersionField = (MimeVersionField)this.fields.get(MIME_VERSION);
    if (localMimeVersionField != null)
      return localMimeVersionField.getMinorVersion();
    return 0;
  }

  public String getMimeType()
  {
    return this.mimeType;
  }

  public String getSubType()
  {
    return this.subType;
  }

  public String getTransferEncoding()
  {
    ContentTransferEncodingField localContentTransferEncodingField = (ContentTransferEncodingField)this.fields.get(CONTENT_TRANSFER_ENCODING);
    if (localContentTransferEncodingField != null)
      return localContentTransferEncodingField.getEncoding();
    return "7bit";
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[mimeType=");
    localStringBuilder.append(this.mimeType);
    localStringBuilder.append(", mediaType=");
    localStringBuilder.append(this.mediaType);
    localStringBuilder.append(", subType=");
    localStringBuilder.append(this.subType);
    localStringBuilder.append(", boundary=");
    localStringBuilder.append(this.boundary);
    localStringBuilder.append(", charset=");
    localStringBuilder.append(this.charset);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.MaximalBodyDescriptor
 * JD-Core Version:    0.6.0
 */