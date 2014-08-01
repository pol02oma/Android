package org.apache.james.mime4j.dom.address;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AddressList extends AbstractList<Address>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final List<? extends Address> addresses;

  public AddressList(List<? extends Address> paramList, boolean paramBoolean)
  {
    if (paramList != null)
    {
      if (paramBoolean);
      while (true)
      {
        this.addresses = paramList;
        return;
        paramList = new ArrayList(paramList);
      }
    }
    this.addresses = Collections.emptyList();
  }

  public MailboxList flatten()
  {
    Iterator localIterator1 = this.addresses.iterator();
    int i;
    while (true)
    {
      boolean bool = localIterator1.hasNext();
      i = 0;
      if (!bool)
        break;
      if (((Address)localIterator1.next() instanceof Mailbox))
        continue;
      i = 1;
    }
    if (i == 0)
      return new MailboxList(this.addresses, true);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator2 = this.addresses.iterator();
    while (localIterator2.hasNext())
      ((Address)localIterator2.next()).addMailboxesTo(localArrayList);
    return new MailboxList(localArrayList, false);
  }

  public Address get(int paramInt)
  {
    return (Address)this.addresses.get(paramInt);
  }

  public int size()
  {
    return this.addresses.size();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.address.AddressList
 * JD-Core Version:    0.6.0
 */