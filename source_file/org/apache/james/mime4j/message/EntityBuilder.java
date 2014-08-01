package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.dom.Body;
import org.apache.james.mime4j.dom.Entity;
import org.apache.james.mime4j.dom.Header;
import org.apache.james.mime4j.dom.Message;
import org.apache.james.mime4j.dom.Multipart;
import org.apache.james.mime4j.parser.ContentHandler;
import org.apache.james.mime4j.stream.BodyDescriptor;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.util.ByteArrayBuffer;
import org.apache.james.mime4j.util.ByteSequence;

class EntityBuilder
  implements ContentHandler
{
  private final BodyFactory bodyFactory;
  private final Entity entity;
  private final Stack<Object> stack;

  EntityBuilder(Entity paramEntity, BodyFactory paramBodyFactory)
  {
    this.entity = paramEntity;
    this.bodyFactory = paramBodyFactory;
    this.stack = new Stack();
  }

  private void expect(Class<?> paramClass)
  {
    if (!paramClass.isInstance(this.stack.peek()))
      throw new IllegalStateException("Internal stack error: Expected '" + paramClass.getName() + "' found '" + this.stack.peek().getClass().getName() + "'");
  }

  private static ByteSequence loadStream(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(64);
    while (true)
    {
      int i = paramInputStream.read();
      if (i == -1)
        break;
      localByteArrayBuffer.append(i);
    }
    return localByteArrayBuffer;
  }

  public void body(BodyDescriptor paramBodyDescriptor, InputStream paramInputStream)
    throws MimeException, IOException
  {
    expect(Entity.class);
    if (paramBodyDescriptor.getMimeType().startsWith("text/"));
    for (Object localObject = this.bodyFactory.textBody(paramInputStream, paramBodyDescriptor.getCharset()); ; localObject = this.bodyFactory.binaryBody(paramInputStream))
    {
      ((Entity)this.stack.peek()).setBody((Body)localObject);
      return;
    }
  }

  public void endBodyPart()
    throws MimeException
  {
    expect(BodyPart.class);
    this.stack.pop();
  }

  public void endHeader()
    throws MimeException
  {
    expect(Header.class);
    Header localHeader = (Header)this.stack.pop();
    expect(Entity.class);
    ((Entity)this.stack.peek()).setHeader(localHeader);
  }

  public void endMessage()
    throws MimeException
  {
    expect(Message.class);
    this.stack.pop();
  }

  public void endMultipart()
    throws MimeException
  {
    this.stack.pop();
  }

  public void epilogue(InputStream paramInputStream)
    throws MimeException, IOException
  {
    expect(MultipartImpl.class);
    ByteSequence localByteSequence = loadStream(paramInputStream);
    ((MultipartImpl)this.stack.peek()).setEpilogueRaw(localByteSequence);
  }

  public void field(Field paramField)
    throws MimeException
  {
    expect(Header.class);
    ((Header)this.stack.peek()).addField(paramField);
  }

  public void preamble(InputStream paramInputStream)
    throws MimeException, IOException
  {
    expect(MultipartImpl.class);
    ByteSequence localByteSequence = loadStream(paramInputStream);
    ((MultipartImpl)this.stack.peek()).setPreambleRaw(localByteSequence);
  }

  public void raw(InputStream paramInputStream)
    throws MimeException, IOException
  {
    throw new UnsupportedOperationException("Not supported");
  }

  public void startBodyPart()
    throws MimeException
  {
    expect(Multipart.class);
    BodyPart localBodyPart = new BodyPart();
    ((Multipart)this.stack.peek()).addBodyPart(localBodyPart);
    this.stack.push(localBodyPart);
  }

  public void startHeader()
    throws MimeException
  {
    this.stack.push(new HeaderImpl());
  }

  public void startMessage()
    throws MimeException
  {
    if (this.stack.isEmpty())
    {
      this.stack.push(this.entity);
      return;
    }
    expect(Entity.class);
    MessageImpl localMessageImpl = new MessageImpl();
    ((Entity)this.stack.peek()).setBody(localMessageImpl);
    this.stack.push(localMessageImpl);
  }

  public void startMultipart(BodyDescriptor paramBodyDescriptor)
    throws MimeException
  {
    expect(Entity.class);
    Entity localEntity = (Entity)this.stack.peek();
    MultipartImpl localMultipartImpl = new MultipartImpl(paramBodyDescriptor.getSubType());
    localEntity.setBody(localMultipartImpl);
    this.stack.push(localMultipartImpl);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.EntityBuilder
 * JD-Core Version:    0.6.0
 */