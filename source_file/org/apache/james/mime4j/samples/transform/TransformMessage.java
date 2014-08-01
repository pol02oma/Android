package org.apache.james.mime4j.samples.transform;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Random;
import org.apache.james.mime4j.dom.BinaryBody;
import org.apache.james.mime4j.dom.Entity;
import org.apache.james.mime4j.dom.Message;
import org.apache.james.mime4j.dom.MessageBuilder;
import org.apache.james.mime4j.dom.MessageWriter;
import org.apache.james.mime4j.dom.Multipart;
import org.apache.james.mime4j.dom.TextBody;
import org.apache.james.mime4j.dom.field.ParseException;
import org.apache.james.mime4j.field.address.AddressBuilder;
import org.apache.james.mime4j.message.BodyPart;
import org.apache.james.mime4j.message.DefaultMessageBuilder;
import org.apache.james.mime4j.message.DefaultMessageWriter;
import org.apache.james.mime4j.message.MessageImpl;
import org.apache.james.mime4j.message.MultipartImpl;
import org.apache.james.mime4j.storage.DefaultStorageProvider;
import org.apache.james.mime4j.storage.StorageBodyFactory;
import org.apache.james.mime4j.storage.TempFileStorageProvider;

public class TransformMessage
{
  private static final String HOSTNAME = "localhost";

  private static BodyPart createRandomBinaryPart(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    new Random().nextBytes(arrayOfByte);
    BinaryBody localBinaryBody = new StorageBodyFactory().binaryBody(new ByteArrayInputStream(arrayOfByte));
    BodyPart localBodyPart = new BodyPart();
    localBodyPart.setBody(localBinaryBody, "application/octet-stream");
    localBodyPart.setContentTransferEncoding("base64");
    return localBodyPart;
  }

  private static Message createTemplate()
    throws IOException
  {
    MultipartImpl localMultipartImpl = new MultipartImpl("mixed");
    localMultipartImpl.addBodyPart(createTextPart("This is the first part of the template.."));
    localMultipartImpl.addBodyPart(createRandomBinaryPart(200));
    localMultipartImpl.addBodyPart(createRandomBinaryPart(300));
    MessageImpl localMessageImpl = new MessageImpl();
    localMessageImpl.setMultipart(localMultipartImpl);
    localMessageImpl.setSubject("Template message");
    return localMessageImpl;
  }

  private static BodyPart createTextPart(String paramString)
  {
    TextBody localTextBody = new StorageBodyFactory().textBody(paramString, "UTF-8");
    BodyPart localBodyPart = new BodyPart();
    localBodyPart.setText(localTextBody);
    localBodyPart.setContentTransferEncoding("quoted-printable");
    return localBodyPart;
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    DefaultStorageProvider.setInstance(new TempFileStorageProvider());
    Message localMessage1 = createTemplate();
    Message localMessage2 = transform(localMessage1);
    DefaultMessageWriter localDefaultMessageWriter = new DefaultMessageWriter();
    System.out.println("\n\nTransformed message:\n--------------------\n");
    localDefaultMessageWriter.writeMessage(localMessage2, System.out);
    localMessage2.dispose();
    System.out.println("\n\nOriginal template:\n------------------\n");
    localDefaultMessageWriter.writeMessage(localMessage1, System.out);
    localMessage1.dispose();
  }

  private static Message transform(Message paramMessage)
    throws IOException, ParseException
  {
    Message localMessage = new DefaultMessageBuilder().newMessage(paramMessage);
    Multipart localMultipart = (Multipart)localMessage.getBody();
    int i = localMultipart.getCount();
    for (int j = 0; j < i; j++)
      localMultipart.addBodyPart(createTextPart("Text inserted after part " + (j + 1)), 1 + j * 2);
    localMultipart.removeBodyPart(4).dispose();
    localMessage.createMessageId("localhost");
    localMessage.setSubject("Transformed message");
    localMessage.setDate(new Date());
    localMessage.setFrom(AddressBuilder.DEFAULT.parseMailbox("John Doe <jdoe@machine.example>"));
    return localMessage;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.samples.transform.TransformMessage
 * JD-Core Version:    0.6.0
 */