package org.apache.james.mime4j.dom;

import org.apache.james.mime4j.MimeException;

public abstract class MessageServiceFactory
{
  public static MessageServiceFactory newInstance()
    throws MimeException
  {
    return (MessageServiceFactory)ServiceLoader.load(MessageServiceFactory.class);
  }

  public abstract MessageBuilder newMessageBuilder();

  public abstract MessageWriter newMessageWriter();

  public abstract void setAttribute(String paramString, Object paramObject)
    throws IllegalArgumentException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.MessageServiceFactory
 * JD-Core Version:    0.6.0
 */