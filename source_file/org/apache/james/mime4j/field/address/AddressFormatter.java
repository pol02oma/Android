package org.apache.james.mime4j.field.address;

import java.util.Iterator;
import org.apache.james.mime4j.codec.EncoderUtil;
import org.apache.james.mime4j.dom.address.Address;
import org.apache.james.mime4j.dom.address.DomainList;
import org.apache.james.mime4j.dom.address.Group;
import org.apache.james.mime4j.dom.address.Mailbox;
import org.apache.james.mime4j.dom.address.MailboxList;

public class AddressFormatter
{
  public static final AddressFormatter DEFAULT = new AddressFormatter();

  public String encode(Group paramGroup)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    encode(localStringBuilder, paramGroup);
    return localStringBuilder.toString();
  }

  public String encode(Mailbox paramMailbox)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    encode(localStringBuilder, paramMailbox);
    return localStringBuilder.toString();
  }

  public void encode(StringBuilder paramStringBuilder, Address paramAddress)
  {
    if (paramAddress == null)
      return;
    if ((paramAddress instanceof Mailbox))
    {
      encode(paramStringBuilder, (Mailbox)paramAddress);
      return;
    }
    if ((paramAddress instanceof Group))
    {
      encode(paramStringBuilder, (Group)paramAddress);
      return;
    }
    throw new IllegalArgumentException("Unsuppported Address class: " + paramAddress.getClass());
  }

  public void encode(StringBuilder paramStringBuilder, Group paramGroup)
  {
    if (paramStringBuilder == null)
      throw new IllegalArgumentException("StringBuilder may not be null");
    if (paramGroup == null)
      throw new IllegalArgumentException("Group may not be null");
    paramStringBuilder.append(EncoderUtil.encodeAddressDisplayName(paramGroup.getName()));
    paramStringBuilder.append(':');
    int i = 1;
    Iterator localIterator = paramGroup.getMailboxes().iterator();
    if (localIterator.hasNext())
    {
      Mailbox localMailbox = (Mailbox)localIterator.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        paramStringBuilder.append(' ');
        encode(paramStringBuilder, localMailbox);
        break;
        paramStringBuilder.append(',');
      }
    }
    paramStringBuilder.append(';');
  }

  public void encode(StringBuilder paramStringBuilder, Mailbox paramMailbox)
  {
    if (paramStringBuilder == null)
      throw new IllegalArgumentException("StringBuilder may not be null");
    if (paramMailbox == null)
      throw new IllegalArgumentException("Mailbox may not be null");
    if (paramMailbox.getName() != null)
    {
      paramStringBuilder.append(EncoderUtil.encodeAddressDisplayName(paramMailbox.getName()));
      paramStringBuilder.append(" <");
    }
    paramStringBuilder.append(EncoderUtil.encodeAddressLocalPart(paramMailbox.getLocalPart()));
    if (paramMailbox.getDomain() != null)
    {
      paramStringBuilder.append('@');
      paramStringBuilder.append(paramMailbox.getDomain());
    }
    if (paramMailbox.getName() != null)
      paramStringBuilder.append('>');
  }

  public String format(Group paramGroup, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    format(localStringBuilder, paramGroup, paramBoolean);
    return localStringBuilder.toString();
  }

  public String format(Mailbox paramMailbox, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    format(localStringBuilder, paramMailbox, paramBoolean);
    return localStringBuilder.toString();
  }

  public void format(StringBuilder paramStringBuilder, Address paramAddress, boolean paramBoolean)
  {
    if (paramAddress == null)
      return;
    if ((paramAddress instanceof Mailbox))
    {
      format(paramStringBuilder, (Mailbox)paramAddress, paramBoolean);
      return;
    }
    if ((paramAddress instanceof Group))
    {
      format(paramStringBuilder, (Group)paramAddress, paramBoolean);
      return;
    }
    throw new IllegalArgumentException("Unsuppported Address class: " + paramAddress.getClass());
  }

  public void format(StringBuilder paramStringBuilder, Group paramGroup, boolean paramBoolean)
  {
    if (paramStringBuilder == null)
      throw new IllegalArgumentException("StringBuilder may not be null");
    if (paramGroup == null)
      throw new IllegalArgumentException("Group may not be null");
    paramStringBuilder.append(paramGroup.getName());
    paramStringBuilder.append(':');
    int i = 1;
    Iterator localIterator = paramGroup.getMailboxes().iterator();
    if (localIterator.hasNext())
    {
      Mailbox localMailbox = (Mailbox)localIterator.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        paramStringBuilder.append(' ');
        format(paramStringBuilder, localMailbox, paramBoolean);
        break;
        paramStringBuilder.append(',');
      }
    }
    paramStringBuilder.append(";");
  }

  public void format(StringBuilder paramStringBuilder, Mailbox paramMailbox, boolean paramBoolean)
  {
    if (paramStringBuilder == null)
      throw new IllegalArgumentException("StringBuilder may not be null");
    if (paramMailbox == null)
      throw new IllegalArgumentException("Mailbox may not be null");
    boolean bool1;
    boolean bool2;
    if (paramMailbox.getRoute() != null)
    {
      bool1 = true;
      bool2 = paramBoolean & bool1;
      if ((paramMailbox.getName() == null) && (!bool2))
        break label169;
    }
    label169: for (int i = 1; ; i = 0)
    {
      if (paramMailbox.getName() != null)
      {
        paramStringBuilder.append(paramMailbox.getName());
        paramStringBuilder.append(' ');
      }
      if (i != 0)
        paramStringBuilder.append('<');
      if (bool2)
      {
        paramStringBuilder.append(paramMailbox.getRoute().toRouteString());
        paramStringBuilder.append(':');
      }
      paramStringBuilder.append(paramMailbox.getLocalPart());
      if (paramMailbox.getDomain() != null)
      {
        paramStringBuilder.append('@');
        paramStringBuilder.append(paramMailbox.getDomain());
      }
      if (i != 0)
        paramStringBuilder.append('>');
      return;
      bool1 = false;
      break;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.AddressFormatter
 * JD-Core Version:    0.6.0
 */