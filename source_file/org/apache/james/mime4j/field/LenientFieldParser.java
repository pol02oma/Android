package org.apache.james.mime4j.field;

import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.stream.RawFieldParser;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.ContentUtil;

public class LenientFieldParser extends DelegatingFieldParser
{
  private static final FieldParser<ParsedField> PARSER = new LenientFieldParser();

  public LenientFieldParser()
  {
    super(UnstructuredFieldImpl.PARSER);
    setFieldParser("Content-Type", ContentTypeFieldLenientImpl.PARSER);
    setFieldParser("Content-Length", ContentLengthFieldImpl.PARSER);
    setFieldParser("Content-Transfer-Encoding", ContentTransferEncodingFieldImpl.PARSER);
    setFieldParser("Content-Disposition", ContentDispositionFieldLenientImpl.PARSER);
    setFieldParser("Content-ID", ContentIdFieldImpl.PARSER);
    setFieldParser("Content-MD5", ContentMD5FieldImpl.PARSER);
    setFieldParser("Content-Description", ContentDescriptionFieldImpl.PARSER);
    setFieldParser("Content-Language", ContentLanguageFieldLenientImpl.PARSER);
    setFieldParser("Content-Location", ContentLocationFieldLenientImpl.PARSER);
    setFieldParser("MIME-Version", MimeVersionFieldImpl.PARSER);
    FieldParser localFieldParser1 = DateTimeFieldLenientImpl.PARSER;
    setFieldParser("Date", localFieldParser1);
    setFieldParser("Resent-Date", localFieldParser1);
    FieldParser localFieldParser2 = MailboxListFieldLenientImpl.PARSER;
    setFieldParser("From", localFieldParser2);
    setFieldParser("Resent-From", localFieldParser2);
    FieldParser localFieldParser3 = MailboxFieldLenientImpl.PARSER;
    setFieldParser("Sender", localFieldParser3);
    setFieldParser("Resent-Sender", localFieldParser3);
    FieldParser localFieldParser4 = AddressListFieldLenientImpl.PARSER;
    setFieldParser("To", localFieldParser4);
    setFieldParser("Resent-To", localFieldParser4);
    setFieldParser("Cc", localFieldParser4);
    setFieldParser("Resent-Cc", localFieldParser4);
    setFieldParser("Bcc", localFieldParser4);
    setFieldParser("Resent-Bcc", localFieldParser4);
    setFieldParser("Reply-To", localFieldParser4);
  }

  public static FieldParser<ParsedField> getParser()
  {
    return PARSER;
  }

  public static ParsedField parse(String paramString)
    throws MimeException
  {
    return parse(paramString, DecodeMonitor.SILENT);
  }

  public static ParsedField parse(String paramString, DecodeMonitor paramDecodeMonitor)
    throws MimeException
  {
    ByteSequence localByteSequence = ContentUtil.encode(paramString);
    RawField localRawField = RawFieldParser.DEFAULT.parseField(localByteSequence);
    return PARSER.parse(localRawField, paramDecodeMonitor);
  }

  public static ParsedField parse(ByteSequence paramByteSequence, DecodeMonitor paramDecodeMonitor)
    throws MimeException
  {
    RawField localRawField = RawFieldParser.DEFAULT.parseField(paramByteSequence);
    return PARSER.parse(localRawField, paramDecodeMonitor);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.LenientFieldParser
 * JD-Core Version:    0.6.0
 */