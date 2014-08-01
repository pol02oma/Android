package org.apache.james.mime4j.dom.field;

import org.apache.james.mime4j.dom.address.Mailbox;

public abstract interface MailboxField extends ParsedField
{
  public abstract Mailbox getMailbox();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.field.MailboxField
 * JD-Core Version:    0.6.0
 */