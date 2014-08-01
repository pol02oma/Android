package org.apache.james.mime4j.dom;

import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;
import org.apache.james.mime4j.dom.address.Address;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.address.Mailbox;
import org.apache.james.mime4j.dom.address.MailboxList;

public abstract interface Message extends Entity, Body
{
  public abstract void createMessageId(String paramString);

  public abstract AddressList getBcc();

  public abstract AddressList getCc();

  public abstract Date getDate();

  public abstract MailboxList getFrom();

  public abstract String getMessageId();

  public abstract AddressList getReplyTo();

  public abstract Mailbox getSender();

  public abstract String getSubject();

  public abstract AddressList getTo();

  public abstract void setBcc(Collection<? extends Address> paramCollection);

  public abstract void setBcc(Address paramAddress);

  public abstract void setBcc(Address[] paramArrayOfAddress);

  public abstract void setCc(Collection<? extends Address> paramCollection);

  public abstract void setCc(Address paramAddress);

  public abstract void setCc(Address[] paramArrayOfAddress);

  public abstract void setDate(Date paramDate);

  public abstract void setDate(Date paramDate, TimeZone paramTimeZone);

  public abstract void setFrom(Collection<Mailbox> paramCollection);

  public abstract void setFrom(Mailbox paramMailbox);

  public abstract void setFrom(Mailbox[] paramArrayOfMailbox);

  public abstract void setReplyTo(Collection<? extends Address> paramCollection);

  public abstract void setReplyTo(Address paramAddress);

  public abstract void setReplyTo(Address[] paramArrayOfAddress);

  public abstract void setSender(Mailbox paramMailbox);

  public abstract void setSubject(String paramString);

  public abstract void setTo(Collection<? extends Address> paramCollection);

  public abstract void setTo(Address paramAddress);

  public abstract void setTo(Address[] paramArrayOfAddress);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.Message
 * JD-Core Version:    0.6.0
 */