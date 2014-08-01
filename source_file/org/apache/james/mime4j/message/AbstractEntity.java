package org.apache.james.mime4j.message;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.james.mime4j.dom.Body;
import org.apache.james.mime4j.dom.Entity;
import org.apache.james.mime4j.dom.Header;
import org.apache.james.mime4j.dom.Message;
import org.apache.james.mime4j.dom.Multipart;
import org.apache.james.mime4j.dom.TextBody;
import org.apache.james.mime4j.dom.field.ContentDispositionField;
import org.apache.james.mime4j.dom.field.ContentTransferEncodingField;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.dom.field.ParsedField;

public abstract class AbstractEntity
  implements Entity
{
  private Body body = null;
  private Header header = null;
  private Entity parent = null;

  private ContentTypeField getContentTypeField()
  {
    return (ContentTypeField)getHeader().getField("Content-Type");
  }

  protected abstract String calcCharset(ContentTypeField paramContentTypeField);

  protected abstract String calcMimeType(ContentTypeField paramContentTypeField1, ContentTypeField paramContentTypeField2);

  protected abstract String calcTransferEncoding(ContentTransferEncodingField paramContentTransferEncodingField);

  public void dispose()
  {
    if (this.body != null)
      this.body.dispose();
  }

  public Body getBody()
  {
    return this.body;
  }

  public String getCharset()
  {
    return calcCharset((ContentTypeField)getHeader().getField("Content-Type"));
  }

  public String getContentTransferEncoding()
  {
    return calcTransferEncoding((ContentTransferEncodingField)getHeader().getField("Content-Transfer-Encoding"));
  }

  public String getDispositionType()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)obtainField("Content-Disposition");
    if (localContentDispositionField == null)
      return null;
    return localContentDispositionField.getDispositionType();
  }

  public String getFilename()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)obtainField("Content-Disposition");
    if (localContentDispositionField == null)
      return null;
    return localContentDispositionField.getFilename();
  }

  public Header getHeader()
  {
    return this.header;
  }

  public String getMimeType()
  {
    ContentTypeField localContentTypeField1 = getContentTypeField();
    if (getParent() != null);
    for (ContentTypeField localContentTypeField2 = (ContentTypeField)getParent().getHeader().getField("Content-Type"); ; localContentTypeField2 = null)
      return calcMimeType(localContentTypeField1, localContentTypeField2);
  }

  public Entity getParent()
  {
    return this.parent;
  }

  public boolean isMimeType(String paramString)
  {
    return getMimeType().equalsIgnoreCase(paramString);
  }

  public boolean isMultipart()
  {
    ContentTypeField localContentTypeField = getContentTypeField();
    return (localContentTypeField != null) && (localContentTypeField.getBoundary() != null) && (getMimeType().startsWith("multipart/"));
  }

  protected abstract ContentDispositionField newContentDisposition(String paramString1, String paramString2, long paramLong, Date paramDate1, Date paramDate2, Date paramDate3);

  protected abstract ContentDispositionField newContentDisposition(String paramString, Map<String, String> paramMap);

  protected abstract ContentTransferEncodingField newContentTransferEncoding(String paramString);

  protected abstract ContentTypeField newContentType(String paramString, Map<String, String> paramMap);

  protected abstract String newUniqueBoundary();

  <F extends ParsedField> F obtainField(String paramString)
  {
    Header localHeader = getHeader();
    if (localHeader == null)
      return null;
    return (ParsedField)localHeader.getField(paramString);
  }

  Header obtainHeader()
  {
    if (this.header == null)
      this.header = new HeaderImpl();
    return this.header;
  }

  public Body removeBody()
  {
    if (this.body == null)
      return null;
    Body localBody = this.body;
    this.body = null;
    localBody.setParent(null);
    return localBody;
  }

  public void setBody(Body paramBody)
  {
    if (this.body != null)
      throw new IllegalStateException("body already set");
    this.body = paramBody;
    paramBody.setParent(this);
  }

  public void setBody(Body paramBody, String paramString)
  {
    setBody(paramBody, paramString, null);
  }

  public void setBody(Body paramBody, String paramString, Map<String, String> paramMap)
  {
    setBody(paramBody);
    obtainHeader().setField(newContentType(paramString, paramMap));
  }

  public void setContentDisposition(String paramString)
  {
    obtainHeader().setField(newContentDisposition(paramString, null, -1L, null, null, null));
  }

  public void setContentDisposition(String paramString1, String paramString2)
  {
    obtainHeader().setField(newContentDisposition(paramString1, paramString2, -1L, null, null, null));
  }

  public void setContentDisposition(String paramString1, String paramString2, long paramLong)
  {
    obtainHeader().setField(newContentDisposition(paramString1, paramString2, paramLong, null, null, null));
  }

  public void setContentDisposition(String paramString1, String paramString2, long paramLong, Date paramDate1, Date paramDate2, Date paramDate3)
  {
    obtainHeader().setField(newContentDisposition(paramString1, paramString2, paramLong, paramDate1, paramDate2, paramDate3));
  }

  public void setContentTransferEncoding(String paramString)
  {
    obtainHeader().setField(newContentTransferEncoding(paramString));
  }

  public void setFilename(String paramString)
  {
    Header localHeader = obtainHeader();
    ContentDispositionField localContentDispositionField = (ContentDispositionField)localHeader.getField("Content-Disposition");
    if (localContentDispositionField == null)
    {
      if (paramString != null)
        localHeader.setField(newContentDisposition("attachment", paramString, -1L, null, null, null));
      return;
    }
    String str = localContentDispositionField.getDispositionType();
    HashMap localHashMap = new HashMap(localContentDispositionField.getParameters());
    if (paramString == null)
      localHashMap.remove("filename");
    while (true)
    {
      localHeader.setField(newContentDisposition(str, localHashMap));
      return;
      localHashMap.put("filename", paramString);
    }
  }

  public void setHeader(Header paramHeader)
  {
    this.header = paramHeader;
  }

  public void setMessage(Message paramMessage)
  {
    setBody(paramMessage, "message/rfc822", null);
  }

  public void setMultipart(Multipart paramMultipart)
  {
    setBody(paramMultipart, "multipart/" + paramMultipart.getSubType(), Collections.singletonMap("boundary", newUniqueBoundary()));
  }

  public void setMultipart(Multipart paramMultipart, Map<String, String> paramMap)
  {
    String str = "multipart/" + paramMultipart.getSubType();
    if (!paramMap.containsKey("boundary"))
    {
      HashMap localHashMap = new HashMap(paramMap);
      localHashMap.put("boundary", newUniqueBoundary());
      paramMap = localHashMap;
    }
    setBody(paramMultipart, str, paramMap);
  }

  public void setParent(Entity paramEntity)
  {
    this.parent = paramEntity;
  }

  public void setText(TextBody paramTextBody)
  {
    setText(paramTextBody, "plain");
  }

  public void setText(TextBody paramTextBody, String paramString)
  {
    String str1 = "text/" + paramString;
    String str2 = paramTextBody.getMimeCharset();
    Map localMap = null;
    if (str2 != null)
    {
      boolean bool = str2.equalsIgnoreCase("us-ascii");
      localMap = null;
      if (!bool)
        localMap = Collections.singletonMap("charset", str2);
    }
    setBody(paramTextBody, str1, localMap);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.AbstractEntity
 * JD-Core Version:    0.6.0
 */