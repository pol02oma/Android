package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.james.mime4j.codec.CodecUtil;
import org.apache.james.mime4j.dom.BinaryBody;
import org.apache.james.mime4j.dom.Body;
import org.apache.james.mime4j.dom.Entity;
import org.apache.james.mime4j.dom.Header;
import org.apache.james.mime4j.dom.Message;
import org.apache.james.mime4j.dom.MessageWriter;
import org.apache.james.mime4j.dom.Multipart;
import org.apache.james.mime4j.dom.SingleBody;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.util.ByteArrayBuffer;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.ContentUtil;
import org.apache.james.mime4j.util.MimeUtil;

public class DefaultMessageWriter
  implements MessageWriter
{
  private static final byte[] CRLF = { 13, 10 };
  private static final byte[] DASHES = { 45, 45 };

  private ByteSequence getBoundary(ContentTypeField paramContentTypeField)
  {
    String str = paramContentTypeField.getBoundary();
    if (str == null)
      throw new IllegalArgumentException("Multipart boundary not specified. Mime-Type: " + paramContentTypeField.getMimeType() + ", Raw: " + paramContentTypeField.toString());
    return ContentUtil.encode(str);
  }

  private ContentTypeField getContentType(Multipart paramMultipart)
  {
    Entity localEntity = paramMultipart.getParent();
    if (localEntity == null)
      throw new IllegalArgumentException("Missing parent entity in multipart");
    Header localHeader = localEntity.getHeader();
    if (localHeader == null)
      throw new IllegalArgumentException("Missing header in parent entity");
    ContentTypeField localContentTypeField = (ContentTypeField)localHeader.getField("Content-Type");
    if (localContentTypeField == null)
      throw new IllegalArgumentException("Content-Type field not specified");
    return localContentTypeField;
  }

  private void writeBytes(ByteSequence paramByteSequence, OutputStream paramOutputStream)
    throws IOException
  {
    if ((paramByteSequence instanceof ByteArrayBuffer))
    {
      ByteArrayBuffer localByteArrayBuffer = (ByteArrayBuffer)paramByteSequence;
      paramOutputStream.write(localByteArrayBuffer.buffer(), 0, localByteArrayBuffer.length());
      return;
    }
    paramOutputStream.write(paramByteSequence.toByteArray());
  }

  protected OutputStream encodeStream(OutputStream paramOutputStream, String paramString, boolean paramBoolean)
    throws IOException
  {
    if (MimeUtil.isBase64Encoding(paramString))
      paramOutputStream = CodecUtil.wrapBase64(paramOutputStream);
    do
      return paramOutputStream;
    while (!MimeUtil.isQuotedPrintableEncoded(paramString));
    return CodecUtil.wrapQuotedPrintable(paramOutputStream, paramBoolean);
  }

  public void writeBody(Body paramBody, OutputStream paramOutputStream)
    throws IOException
  {
    if ((paramBody instanceof Message))
    {
      writeEntity((Message)paramBody, paramOutputStream);
      return;
    }
    if ((paramBody instanceof Multipart))
    {
      writeMultipart((Multipart)paramBody, paramOutputStream);
      return;
    }
    if ((paramBody instanceof SingleBody))
    {
      ((SingleBody)paramBody).writeTo(paramOutputStream);
      return;
    }
    throw new IllegalArgumentException("Unsupported body class");
  }

  public void writeEntity(Entity paramEntity, OutputStream paramOutputStream)
    throws IOException
  {
    Header localHeader = paramEntity.getHeader();
    if (localHeader == null)
      throw new IllegalArgumentException("Missing header");
    writeHeader(localHeader, paramOutputStream);
    Body localBody = paramEntity.getBody();
    if (localBody == null)
      throw new IllegalArgumentException("Missing body");
    boolean bool = localBody instanceof BinaryBody;
    OutputStream localOutputStream = encodeStream(paramOutputStream, paramEntity.getContentTransferEncoding(), bool);
    writeBody(localBody, localOutputStream);
    if (localOutputStream != paramOutputStream)
      localOutputStream.close();
  }

  public void writeField(Field paramField, OutputStream paramOutputStream)
    throws IOException
  {
    ByteSequence localByteSequence = paramField.getRaw();
    if (localByteSequence == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramField.getName());
      localStringBuilder.append(": ");
      String str = paramField.getBody();
      if (str != null)
        localStringBuilder.append(str);
      localByteSequence = ContentUtil.encode(MimeUtil.fold(localStringBuilder.toString(), 0));
    }
    writeBytes(localByteSequence, paramOutputStream);
    paramOutputStream.write(CRLF);
  }

  public void writeHeader(Header paramHeader, OutputStream paramOutputStream)
    throws IOException
  {
    Iterator localIterator = paramHeader.iterator();
    while (localIterator.hasNext())
      writeField((Field)localIterator.next(), paramOutputStream);
    paramOutputStream.write(CRLF);
  }

  public void writeMessage(Message paramMessage, OutputStream paramOutputStream)
    throws IOException
  {
    writeEntity(paramMessage, paramOutputStream);
  }

  public void writeMultipart(Multipart paramMultipart, OutputStream paramOutputStream)
    throws IOException
  {
    ByteSequence localByteSequence1 = getBoundary(getContentType(paramMultipart));
    ByteSequence localByteSequence2;
    if ((paramMultipart instanceof MultipartImpl))
    {
      localByteSequence2 = ((MultipartImpl)paramMultipart).getPreambleRaw();
      localByteSequence3 = ((MultipartImpl)paramMultipart).getEpilogueRaw();
      if (localByteSequence2 != null)
      {
        writeBytes(localByteSequence2, paramOutputStream);
        paramOutputStream.write(CRLF);
      }
      Iterator localIterator = paramMultipart.getBodyParts().iterator();
      while (localIterator.hasNext())
      {
        Entity localEntity = (Entity)localIterator.next();
        paramOutputStream.write(DASHES);
        writeBytes(localByteSequence1, paramOutputStream);
        paramOutputStream.write(CRLF);
        writeEntity(localEntity, paramOutputStream);
        paramOutputStream.write(CRLF);
      }
    }
    if (paramMultipart.getPreamble() != null)
    {
      localByteSequence2 = ContentUtil.encode(paramMultipart.getPreamble());
      label146: if (paramMultipart.getEpilogue() == null)
        break label175;
    }
    label175: for (ByteSequence localByteSequence3 = ContentUtil.encode(paramMultipart.getEpilogue()); ; localByteSequence3 = null)
    {
      break;
      localByteSequence2 = null;
      break label146;
    }
    paramOutputStream.write(DASHES);
    writeBytes(localByteSequence1, paramOutputStream);
    paramOutputStream.write(DASHES);
    paramOutputStream.write(CRLF);
    if (localByteSequence3 != null)
      writeBytes(localByteSequence3, paramOutputStream);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.DefaultMessageWriter
 * JD-Core Version:    0.6.0
 */