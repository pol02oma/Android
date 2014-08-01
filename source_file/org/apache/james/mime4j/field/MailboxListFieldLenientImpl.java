package org.apache.james.mime4j.field;

import java.util.Collections;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.address.MailboxList;
import org.apache.james.mime4j.dom.field.MailboxListField;
import org.apache.james.mime4j.field.address.LenientAddressBuilder;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.ParserCursor;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.ContentUtil;

public class MailboxListFieldLenientImpl extends AbstractField
  implements MailboxListField
{
  public static final FieldParser<MailboxListField> PARSER = new FieldParser()
  {
    public MailboxListField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new MailboxListFieldLenientImpl(paramField, paramDecodeMonitor);
    }
  };
  private MailboxList mailboxList;
  private boolean parsed = false;

  MailboxListFieldLenientImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
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
      {
        this.mailboxList = new MailboxList(Collections.emptyList(), true);
        return;
      }
      localByteSequence = ContentUtil.encode(str);
      i = 0;
    }
    ParserCursor localParserCursor = new ParserCursor(i, localByteSequence.length());
    this.mailboxList = LenientAddressBuilder.DEFAULT.parseAddressList(localByteSequence, localParserCursor).flatten();
  }

  public MailboxList getMailboxList()
  {
    if (!this.parsed)
      parse();
    return this.mailboxList;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.MailboxListFieldLenientImpl
 * JD-Core Version:    0.6.0
 */