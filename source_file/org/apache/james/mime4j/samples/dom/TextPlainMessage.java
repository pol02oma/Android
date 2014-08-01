package org.apache.james.mime4j.samples.dom;

import java.io.IOException;
import java.util.Date;
import org.apache.james.mime4j.dom.MessageWriter;
import org.apache.james.mime4j.field.address.AddressBuilder;
import org.apache.james.mime4j.field.address.ParseException;
import org.apache.james.mime4j.message.DefaultMessageWriter;
import org.apache.james.mime4j.message.MessageImpl;
import org.apache.james.mime4j.storage.StorageBodyFactory;

public class TextPlainMessage
{
  public static void main(String[] paramArrayOfString)
    throws IOException, ParseException
  {
    MessageImpl localMessageImpl = new MessageImpl();
    localMessageImpl.setDate(new Date());
    localMessageImpl.setFrom(AddressBuilder.DEFAULT.parseMailbox("John Doe <jdoe@machine.example>"));
    localMessageImpl.createMessageId("machine.example");
    localMessageImpl.setTo(AddressBuilder.DEFAULT.parseMailbox("Mary Smith <mary@example.net>"));
    localMessageImpl.setSubject("Saying Hello");
    localMessageImpl.setText(new StorageBodyFactory().textBody("This is a message just to say hello.\r\nSo, \"Hello\"."));
    new DefaultMessageWriter().writeMessage(localMessageImpl, System.out);
    localMessageImpl.dispose();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.samples.dom.TextPlainMessage
 * JD-Core Version:    0.6.0
 */