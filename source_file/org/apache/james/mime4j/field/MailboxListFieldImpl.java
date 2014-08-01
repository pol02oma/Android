package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.address.MailboxList;
import org.apache.james.mime4j.dom.field.MailboxListField;
import org.apache.james.mime4j.field.address.AddressBuilder;
import org.apache.james.mime4j.field.address.ParseException;
import org.apache.james.mime4j.stream.Field;

public class MailboxListFieldImpl extends AbstractField
  implements MailboxListField
{
  public static final FieldParser<MailboxListField> PARSER = new FieldParser()
  {
    public MailboxListField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new MailboxListFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private MailboxList mailboxList;
  private ParseException parseException;
  private boolean parsed = false;

  MailboxListFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    String str = getBody();
    try
    {
      this.mailboxList = AddressBuilder.DEFAULT.parseAddressList(str, this.monitor).flatten();
      this.parsed = true;
      return;
    }
    catch (ParseException localParseException)
    {
      while (true)
        this.parseException = localParseException;
    }
  }

  public MailboxList getMailboxList()
  {
    if (!this.parsed)
      parse();
    return this.mailboxList;
  }

  public ParseException getParseException()
  {
    if (!this.parsed)
      parse();
    return this.parseException;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.MailboxListFieldImpl
 * JD-Core Version:    0.6.0
 */