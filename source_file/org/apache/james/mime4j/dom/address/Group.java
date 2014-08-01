package org.apache.james.mime4j.dom.address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Group extends Address
{
  private static final long serialVersionUID = 1L;
  private final MailboxList mailboxList;
  private final String name;

  public Group(String paramString, Collection<Mailbox> paramCollection)
  {
    this(paramString, new MailboxList(new ArrayList(paramCollection), true));
  }

  public Group(String paramString, MailboxList paramMailboxList)
  {
    if (paramString == null)
      throw new IllegalArgumentException();
    if (paramMailboxList == null)
      throw new IllegalArgumentException();
    this.name = paramString;
    this.mailboxList = paramMailboxList;
  }

  public Group(String paramString, Mailbox[] paramArrayOfMailbox)
  {
    this(paramString, new MailboxList(Arrays.asList(paramArrayOfMailbox), true));
  }

  protected void doAddMailboxesTo(List<Mailbox> paramList)
  {
    Iterator localIterator = this.mailboxList.iterator();
    while (localIterator.hasNext())
      paramList.add((Mailbox)localIterator.next());
  }

  public MailboxList getMailboxes()
  {
    return this.mailboxList;
  }

  public String getName()
  {
    return this.name;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name);
    localStringBuilder.append(':');
    int i = 1;
    Iterator localIterator = this.mailboxList.iterator();
    if (localIterator.hasNext())
    {
      Mailbox localMailbox = (Mailbox)localIterator.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append(' ');
        localStringBuilder.append(localMailbox);
        break;
        localStringBuilder.append(',');
      }
    }
    localStringBuilder.append(";");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.address.Group
 * JD-Core Version:    0.6.0
 */