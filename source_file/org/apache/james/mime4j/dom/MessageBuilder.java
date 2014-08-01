package org.apache.james.mime4j.dom;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;

public abstract interface MessageBuilder
{
  public abstract Header newHeader();

  public abstract Header newHeader(Header paramHeader);

  public abstract Message newMessage();

  public abstract Message newMessage(Message paramMessage);

  public abstract Multipart newMultipart(String paramString);

  public abstract Multipart newMultipart(Multipart paramMultipart);

  public abstract Header parseHeader(InputStream paramInputStream)
    throws MimeException, IOException;

  public abstract Message parseMessage(InputStream paramInputStream)
    throws MimeException, IOException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.MessageBuilder
 * JD-Core Version:    0.6.0
 */