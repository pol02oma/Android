package org.apache.james.mime4j.message;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.Header;
import org.apache.james.mime4j.dom.address.Address;
import org.apache.james.mime4j.dom.address.Mailbox;
import org.apache.james.mime4j.dom.field.AddressListField;
import org.apache.james.mime4j.dom.field.ContentDispositionField;
import org.apache.james.mime4j.dom.field.ContentTransferEncodingField;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.james.mime4j.dom.field.MailboxField;
import org.apache.james.mime4j.dom.field.MailboxListField;
import org.apache.james.mime4j.dom.field.UnstructuredField;
import org.apache.james.mime4j.field.ContentTransferEncodingFieldImpl;
import org.apache.james.mime4j.field.ContentTypeFieldImpl;
import org.apache.james.mime4j.field.Fields;
import org.apache.james.mime4j.field.MimeVersionFieldLenientImpl;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.util.MimeUtil;

public class MessageImpl extends AbstractMessage
{
  public MessageImpl()
  {
    Header localHeader = obtainHeader();
    RawField localRawField = new RawField("MIME-Version", "1.0");
    localHeader.addField(MimeVersionFieldLenientImpl.PARSER.parse(localRawField, DecodeMonitor.SILENT));
  }

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

  protected AddressListField newAddressList(String paramString, Collection<? extends Address> paramCollection)
  {
    return Fields.addressList(paramString, paramCollection);
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

  protected DateTimeField newDate(Date paramDate, TimeZone paramTimeZone)
  {
    return Fields.date("Date", paramDate, paramTimeZone);
  }

  protected MailboxField newMailbox(String paramString, Mailbox paramMailbox)
  {
    return Fields.mailbox(paramString, paramMailbox);
  }

  protected MailboxListField newMailboxList(String paramString, Collection<Mailbox> paramCollection)
  {
    return Fields.mailboxList(paramString, paramCollection);
  }

  protected UnstructuredField newMessageId(String paramString)
  {
    return Fields.messageId(paramString);
  }

  protected UnstructuredField newSubject(String paramString)
  {
    return Fields.subject(paramString);
  }

  protected String newUniqueBoundary()
  {
    return MimeUtil.createUniqueBoundary();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.MessageImpl
 * JD-Core Version:    0.6.0
 */