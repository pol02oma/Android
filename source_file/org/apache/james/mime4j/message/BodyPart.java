package org.apache.james.mime4j.message;

import java.util.Date;
import java.util.Map;
import org.apache.james.mime4j.dom.field.ContentDispositionField;
import org.apache.james.mime4j.dom.field.ContentTransferEncodingField;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.field.ContentTransferEncodingFieldImpl;
import org.apache.james.mime4j.field.ContentTypeFieldImpl;
import org.apache.james.mime4j.field.Fields;
import org.apache.james.mime4j.util.MimeUtil;

public class BodyPart extends AbstractEntity
{
  protected String calcCharset(ContentTypeField paramContentTypeField)
  {
    return ContentTypeFieldImpl.getCharset(paramContentTypeField);
  }

  protected String calcMimeType(ContentTypeField paramContentTypeField1, ContentTypeField paramContentTypeField2)
  {
    return ContentTypeFieldImpl.getMimeType(paramContentTypeField1, paramContentTypeField2);
  }

  protected String calcTransferEncoding(ContentTransferEncodingField paramContentTransferEncodingField)
  {
    return ContentTransferEncodingFieldImpl.getEncoding(paramContentTransferEncodingField);
  }

  protected ContentDispositionField newContentDisposition(String paramString1, String paramString2, long paramLong, Date paramDate1, Date paramDate2, Date paramDate3)
  {
    return Fields.contentDisposition(paramString1, paramString2, paramLong, paramDate1, paramDate2, paramDate3);
  }

  protected ContentDispositionField newContentDisposition(String paramString, Map<String, String> paramMap)
  {
    return Fields.contentDisposition(paramString, paramMap);
  }

  protected ContentTransferEncodingField newContentTransferEncoding(String paramString)
  {
    return Fields.contentTransferEncoding(paramString);
  }

  protected ContentTypeField newContentType(String paramString, Map<String, String> paramMap)
  {
    return Fields.contentType(paramString, paramMap);
  }

  protected String newUniqueBoundary()
  {
    return MimeUtil.createUniqueBoundary();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.BodyPart
 * JD-Core Version:    0.6.0
 */