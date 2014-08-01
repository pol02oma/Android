package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.address.Mailbox;
import org.apache.james.mime4j.dom.field.MailboxField;
import org.apache.james.mime4j.field.address.AddressBuilder;
import org.apache.james.mime4j.field.address.ParseException;
import org.apache.james.mime4j.stream.Field;

public class MailboxFieldImpl extends AbstractField
  implements MailboxField
{
  public static final FieldParser<MailboxField> PARSER = new FieldParser()
  {
    public MailboxField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new MailboxFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private Mailbox mailbox;
  private ParseException parseException;
  private boolean parsed = false;

  MailboxFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    String str = getBody();
    try
    {
      this.mailbox = AddressBuilder.DEFAULT.parseMailbox(str, this.monitor);
      this.parsed = true;
      return;
    }
    catch (ParseException localParseException)
    {
      while (true)
        this.parseException = localParseException;
    }
  }

  public Mailbox getMailbox()
  {
    if (!this.parsed)
      parse();
    return this.mailbox;
  }

  public ParseException getParseException()
  {
    if (!this.parsed)
      parse();
    return this.parseException;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.MailboxFieldImpl
 * JD-Core Version:    0.6.0
 */