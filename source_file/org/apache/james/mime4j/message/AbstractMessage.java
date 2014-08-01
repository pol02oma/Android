package org.apache.james.mime4j.message;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;
import org.apache.james.mime4j.dom.Header;
import org.apache.james.mime4j.dom.Message;
import org.apache.james.mime4j.dom.address.Address;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.address.Mailbox;
import org.apache.james.mime4j.dom.address.MailboxList;
import org.apache.james.mime4j.dom.field.AddressListField;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.james.mime4j.dom.field.MailboxField;
import org.apache.james.mime4j.dom.field.MailboxListField;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.dom.field.UnstructuredField;
import org.apache.james.mime4j.stream.Field;

public abstract class AbstractMessage extends AbstractEntity
  implements Message
{
  private AddressList getAddressList(String paramString)
  {
    AddressListField localAddressListField = (AddressListField)obtainField(paramString);
    if (localAddressListField == null)
      return null;
    return localAddressListField.getAddressList();
  }

  private Mailbox getMailbox(String paramString)
  {
    MailboxField localMailboxField = (MailboxField)obtainField(paramString);
    if (localMailboxField == null)
      return null;
    return localMailboxField.getMailbox();
  }

  private MailboxList getMailboxList(String paramString)
  {
    MailboxListField localMailboxListField = (MailboxListField)obtainField(paramString);
    if (localMailboxListField == null)
      return null;
    return localMailboxListField.getMailboxList();
  }

  private void setAddressList(String paramString, Collection<? extends Address> paramCollection)
  {
    Header localHeader = obtainHeader();
    if ((paramCollection == null) || (paramCollection.isEmpty()))
    {
      localHeader.removeFields(paramString);
      return;
    }
    localHeader.setField(newAddressList(paramString, paramCollection));
  }

  private void setAddressList(String paramString, Address paramAddress)
  {
    if (paramAddress == null);
    for (Object localObject = null; ; localObject = Collections.singleton(paramAddress))
    {
      setAddressList(paramString, (Collection)localObject);
      return;
    }
  }

  private void setAddressList(String paramString, Address[] paramArrayOfAddress)
  {
    if (paramArrayOfAddress == null);
    for (Object localObject = null; ; localObject = Arrays.asList(paramArrayOfAddress))
    {
      setAddressList(paramString, (Collection)localObject);
      return;
    }
  }

  private void setMailbox(String paramString, Mailbox paramMailbox)
  {
    Header localHeader = obtainHeader();
    if (paramMailbox == null)
    {
      localHeader.removeFields(paramString);
      return;
    }
    localHeader.setField(newMailbox(paramString, paramMailbox));
  }

  private void setMailboxList(String paramString, Collection<Mailbox> paramCollection)
  {
    Header localHeader = obtainHeader();
    if ((paramCollection == null) || (paramCollection.isEmpty()))
    {
      localHeader.removeFields(paramString);
      return;
    }
    localHeader.setField(newMailboxList(paramString, paramCollection));
  }

  private void setMailboxList(String paramString, Mailbox paramMailbox)
  {
    if (paramMailbox == null);
    for (Object localObject = null; ; localObject = Collections.singleton(paramMailbox))
    {
      setMailboxList(paramString, (Collection)localObject);
      return;
    }
  }

  private void setMailboxList(String paramString, Mailbox[] paramArrayOfMailbox)
  {
    if (paramArrayOfMailbox == null);
    for (Object localObject = null; ; localObject = Arrays.asList(paramArrayOfMailbox))
    {
      setMailboxList(paramString, (Collection)localObject);
      return;
    }
  }

  public void createMessageId(String paramString)
  {
    obtainHeader().setField(newMessageId(paramString));
  }

  public AddressList getBcc()
  {
    return getAddressList("Bcc");
  }

  public AddressList getCc()
  {
    return getAddressList("Cc");
  }

  public Date getDate()
  {
    DateTimeField localDateTimeField = (DateTimeField)obtainField("Date");
    if (localDateTimeField == null)
      return null;
    return localDateTimeField.getDate();
  }

  public MailboxList getFrom()
  {
    return getMailboxList("From");
  }

  public String getMessageId()
  {
    ParsedField localParsedField = obtainField("Message-ID");
    if (localParsedField == null)
      return null;
    return localParsedField.getBody();
  }

  public AddressList getReplyTo()
  {
    return getAddressList("Reply-To");
  }

  public Mailbox getSender()
  {
    return getMailbox("Sender");
  }

  public String getSubject()
  {
    UnstructuredField localUnstructuredField = (UnstructuredField)obtainField("Subject");
    if (localUnstructuredField == null)
      return null;
    return localUnstructuredField.getValue();
  }

  public AddressList getTo()
  {
    return getAddressList("To");
  }

  protected abstract AddressListField newAddressList(String paramString, Collection<? extends Address> paramCollection);

  protected abstract DateTimeField newDate(Date paramDate, TimeZone paramTimeZone);

  protected abstract MailboxField newMailbox(String paramString, Mailbox paramMailbox);

  protected abstract MailboxListField newMailboxList(String paramString, Collection<Mailbox> paramCollection);

  protected abstract ParsedField newMessageId(String paramString);

  protected abstract UnstructuredField newSubject(String paramString);

  public void setBcc(Collection<? extends Address> paramCollection)
  {
    setAddressList("Bcc", paramCollection);
  }

  public void setBcc(Address paramAddress)
  {
    setAddressList("Bcc", paramAddress);
  }

  public void setBcc(Address[] paramArrayOfAddress)
  {
    setAddressList("Bcc", paramArrayOfAddress);
  }

  public void setCc(Collection<? extends Address> paramCollection)
  {
    setAddressList("Cc", paramCollection);
  }

  public void setCc(Address paramAddress)
  {
    setAddressList("Cc", paramAddress);
  }

  public void setCc(Address[] paramArrayOfAddress)
  {
    setAddressList("Cc", paramArrayOfAddress);
  }

  public void setDate(Date paramDate)
  {
    setDate(paramDate, null);
  }

  public void setDate(Date paramDate, TimeZone paramTimeZone)
  {
    Header localHeader = obtainHeader();
    if (paramDate == null)
    {
      localHeader.removeFields("Date");
      return;
    }
    localHeader.setField(newDate(paramDate, paramTimeZone));
  }

  public void setFrom(Collection<Mailbox> paramCollection)
  {
    setMailboxList("From", paramCollection);
  }

  public void setFrom(Mailbox paramMailbox)
  {
    setMailboxList("From", paramMailbox);
  }

  public void setFrom(Mailbox[] paramArrayOfMailbox)
  {
    setMailboxList("From", paramArrayOfMailbox);
  }

  public void setReplyTo(Collection<? extends Address> paramCollection)
  {
    setAddressList("Reply-To", paramCollection);
  }

  public void setReplyTo(Address paramAddress)
  {
    setAddressList("Reply-To", paramAddress);
  }

  public void setReplyTo(Address[] paramArrayOfAddress)
  {
    setAddressList("Reply-To", paramArrayOfAddress);
  }

  public void setSender(Mailbox paramMailbox)
  {
    setMailbox("Sender", paramMailbox);
  }

  public void setSubject(String paramString)
  {
    Header localHeader = obtainHeader();
    if (paramString == null)
    {
      localHeader.removeFields("Subject");
      return;
    }
    localHeader.setField(newSubject(paramString));
  }

  public void setTo(Collection<? extends Address> paramCollection)
  {
    setAddressList("To", paramCollection);
  }

  public void setTo(Address paramAddress)
  {
    setAddressList("To", paramAddress);
  }

  public void setTo(Address[] paramArrayOfAddress)
  {
    setAddressList("To", paramArrayOfAddress);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.AbstractMessage
 * JD-Core Version:    0.6.0
 */