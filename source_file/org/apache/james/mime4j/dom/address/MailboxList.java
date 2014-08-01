package org.apache.james.mime4j.dom.address;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MailboxList extends AbstractList<Mailbox>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final List<Mailbox> mailboxes;

  public MailboxList(List<Mailbox> paramList, boolean paramBoolean)
  {
    if (paramList != null)
    {
      if (paramBoolean);
      while (true)
      {
        this.mailboxes = paramList;
        return;
        paramList = new ArrayList(paramList);
      }
    }
    this.mailboxes = Collections.emptyList();
  }

  public Mailbox get(int paramInt)
  {
    return (Mailbox)this.mailboxes.get(paramInt);
  }

  public int size()
  {
    return this.mailboxes.size();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.address.MailboxList
 * JD-Core Version:    0.6.0
 */