package org.apache.james.mime4j.samples.dom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.james.mime4j.dom.BinaryBody;
import org.apache.james.mime4j.dom.MessageWriter;
import org.apache.james.mime4j.dom.Multipart;
import org.apache.james.mime4j.dom.TextBody;
import org.apache.james.mime4j.field.address.AddressBuilder;
import org.apache.james.mime4j.message.BodyPart;
import org.apache.james.mime4j.message.DefaultMessageWriter;
import org.apache.james.mime4j.message.MessageImpl;
import org.apache.james.mime4j.message.MultipartImpl;
import org.apache.james.mime4j.storage.Storage;
import org.apache.james.mime4j.storage.StorageBodyFactory;
import org.apache.james.mime4j.storage.StorageOutputStream;
import org.apache.james.mime4j.storage.StorageProvider;

public class MultipartMessage
{
  private static BodyPart createImagePart(StorageBodyFactory paramStorageBodyFactory, BufferedImage paramBufferedImage)
    throws IOException
  {
    BinaryBody localBinaryBody = paramStorageBodyFactory.binaryBody(storeImage(paramStorageBodyFactory.getStorageProvider(), paramBufferedImage, "png"));
    BodyPart localBodyPart = new BodyPart();
    localBodyPart.setBody(localBinaryBody, "image/png");
    localBodyPart.setContentTransferEncoding("base64");
    localBodyPart.setFilename("smiley.png");
    return localBodyPart;
  }

  private static BodyPart createTextPart(StorageBodyFactory paramStorageBodyFactory, String paramString)
  {
    TextBody localTextBody = paramStorageBodyFactory.textBody(paramString, "UTF-8");
    BodyPart localBodyPart = new BodyPart();
    localBodyPart.setText(localTextBody);
    localBodyPart.setContentTransferEncoding("quoted-printable");
    return localBodyPart;
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    MessageImpl localMessageImpl = new MessageImpl();
    localMessageImpl.setDate(new Date());
    localMessageImpl.setFrom(AddressBuilder.DEFAULT.parseMailbox("John Doe <jdoe@machine.example>"));
    localMessageImpl.createMessageId("machine.example");
    localMessageImpl.setTo(AddressBuilder.DEFAULT.parseMailbox("Mary Smith <mary@example.net>"));
    localMessageImpl.setSubject("An image for you");
    MultipartImpl localMultipartImpl = new MultipartImpl("mixed");
    localMultipartImpl.setPreamble("This is a multi-part message in MIME format.");
    StorageBodyFactory localStorageBodyFactory = new StorageBodyFactory();
    localMultipartImpl.addBodyPart(createTextPart(localStorageBodyFactory, "Why so serious?"));
    localMultipartImpl.addBodyPart(createImagePart(localStorageBodyFactory, renderSampleImage()));
    localMessageImpl.setMultipart(localMultipartImpl);
    new DefaultMessageWriter().writeMessage(localMessageImpl, System.out);
    localMessageImpl.dispose();
  }

  private static BufferedImage renderSampleImage()
  {
    System.setProperty("java.awt.headless", "true");
    BufferedImage localBufferedImage = new BufferedImage(100, 100, 10);
    Graphics2D localGraphics2D = localBufferedImage.createGraphics();
    localGraphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    localGraphics2D.setStroke(new BasicStroke(2.5F, 1, 1));
    localGraphics2D.setColor(Color.BLACK);
    localGraphics2D.setBackground(Color.WHITE);
    localGraphics2D.clearRect(0, 0, 100, 100);
    localGraphics2D.drawOval(3, 3, 93, 93);
    localGraphics2D.drawOval(27, 27, 14, 14);
    localGraphics2D.drawOval(58, 27, 14, 14);
    localGraphics2D.drawArc(20, 20, 59, 59, 200, 140);
    return localBufferedImage;
  }

  private static Storage storeImage(StorageProvider paramStorageProvider, BufferedImage paramBufferedImage, String paramString)
    throws IOException
  {
    StorageOutputStream localStorageOutputStream = paramStorageProvider.createStorageOutputStream();
    ImageIO.write(paramBufferedImage, paramString, localStorageOutputStream);
    return localStorageOutputStream.toStorage();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.samples.dom.MultipartMessage
 * JD-Core Version:    0.6.0
 */