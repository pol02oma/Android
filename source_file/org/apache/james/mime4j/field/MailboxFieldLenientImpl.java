package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.address.Mailbox;
import org.apache.james.mime4j.dom.field.MailboxField;
import org.apache.james.mime4j.field.address.LenientAddressBuilder;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.ParserCursor;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.ContentUtil;

public class MailboxFieldLenientImpl extends AbstractField
  implements MailboxField
{
  public static final FieldParser<MailboxField> PARSER = new FieldParser()
  {
    public MailboxField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new MailboxFieldLenientImpl(paramField, paramDecodeMonitor);
    }
  };
  private Mailbox mailbox;
  private boolean parsed = false;

  MailboxFieldLenientImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    RawField localRawField = getRawField();
    ByteSequence localByteSequence = localRawField.getRaw();
    int i = 1 + localRawField.getDelimiterIdx();
    if (localByteSequence == null)
    {
      String str = localRawField.getBody();
      if (str == null)
        return;
      localByteSequence = ContentUtil.encode(str);
      i = 0;
    }
    ParserCursor localParserCursor = new ParserCursor(i, localByteSequence.length());
    this.mailbox = LenientAddressBuilder.DEFAULT.parseMailbox(localByteSequence, localParserCursor, null);
  }

  public Mailbox getMailbox()
  {
    if (!this.parsed)
      parse();
    return this.mailbox;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.MailboxFieldLenientImpl
 * JD-Core Version:    0.6.0
 */