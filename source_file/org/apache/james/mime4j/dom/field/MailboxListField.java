package org.apache.james.mime4j.dom.field;

import org.apache.james.mime4j.dom.address.MailboxList;

public abstract interface MailboxListField extends ParsedField
{
  public abstract MailboxList getMailboxList();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.field.MailboxListField
 * JD-Core Version:    0.6.0
 */