package org.apache.james.mime4j.dom.address;

import java.io.Serializable;
import java.util.List;

public abstract class Address
  implements Serializable
{
  private static final long serialVersionUID = 634090661990433426L;

  final void addMailboxesTo(List<Mailbox> paramList)
  {
    doAddMailboxesTo(paramList);
  }

  protected abstract void doAddMailboxesTo(List<Mailbox> paramList);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.address.Address
 * JD-Core Version:    0.6.0
 */