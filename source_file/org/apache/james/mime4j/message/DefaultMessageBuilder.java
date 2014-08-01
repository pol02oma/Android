package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.MimeIOException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.Body;
import org.apache.james.mime4j.dom.Entity;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.Header;
import org.apache.james.mime4j.dom.Message;
import org.apache.james.mime4j.dom.MessageBuilder;
import org.apache.james.mime4j.dom.Multipart;
import org.apache.james.mime4j.dom.SingleBody;
import org.apache.james.mime4j.dom.field.ParsedField;
import org.apache.james.mime4j.field.DefaultFieldParser;
import org.apache.james.mime4j.field.LenientFieldParser;
import org.apache.james.mime4j.parser.AbstractContentHandler;
import org.apache.james.mime4j.parser.MimeStreamParser;
import org.apache.james.mime4j.stream.BodyDescriptorBuilder;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.MimeConfig;

public class DefaultMessageBuilder
  implements MessageBuilder
{
  private BodyDescriptorBuilder bodyDescBuilder = null;
  private BodyFactory bodyFactory = null;
  private MimeConfig config = null;
  private boolean contentDecoding = true;
  private FieldParser<? extends ParsedField> fieldParser = null;
  private boolean flatMode = false;
  private DecodeMonitor monitor = null;

  public Body copy(Body paramBody)
  {
    if (paramBody == null)
      throw new IllegalArgumentException("Body is null");
    if ((paramBody instanceof Message))
      return copy((Message)paramBody);
    if ((paramBody instanceof Multipart))
      return copy((Multipart)paramBody);
    if ((paramBody instanceof SingleBody))
      return ((SingleBody)paramBody).copy();
    throw new IllegalArgumentException("Unsupported body class");
  }

  public Header copy(Header paramHeader)
  {
    HeaderImpl localHeaderImpl = new HeaderImpl();
    Iterator localIterator = paramHeader.getFields().iterator();
    while (localIterator.hasNext())
      localHeaderImpl.addField((Field)localIterator.next());
    return localHeaderImpl;
  }

  public Message copy(Message paramMessage)
  {
    MessageImpl localMessageImpl = new MessageImpl();
    if (paramMessage.getHeader() != null)
      localMessageImpl.setHeader(copy(paramMessage.getHeader()));
    if (paramMessage.getBody() != null)
      localMessageImpl.setBody(copy(paramMessage.getBody()));
    return localMessageImpl;
  }

  public Multipart copy(Multipart paramMultipart)
  {
    MultipartImpl localMultipartImpl = new MultipartImpl(paramMultipart.getSubType());
    Iterator localIterator = paramMultipart.getBodyParts().iterator();
    while (localIterator.hasNext())
      localMultipartImpl.addBodyPart(copy((Entity)localIterator.next()));
    localMultipartImpl.setPreamble(paramMultipart.getPreamble());
    localMultipartImpl.setEpilogue(paramMultipart.getEpilogue());
    return localMultipartImpl;
  }

  public BodyPart copy(Entity paramEntity)
  {
    BodyPart localBodyPart = new BodyPart();
    if (paramEntity.getHeader() != null)
      localBodyPart.setHeader(copy(paramEntity.getHeader()));
    if (paramEntity.getBody() != null)
      localBodyPart.setBody(copy(paramEntity.getBody()));
    return localBodyPart;
  }

  public Header newHeader()
  {
    return new HeaderImpl();
  }

  public Header newHeader(Header paramHeader)
  {
    return copy(paramHeader);
  }

  public Message newMessage()
  {
    return new MessageImpl();
  }

  public Message newMessage(Message paramMessage)
  {
    return copy(paramMessage);
  }

  public Multipart newMultipart(String paramString)
  {
    return new MultipartImpl(paramString);
  }

  public Multipart newMultipart(Multipart paramMultipart)
  {
    return copy(paramMultipart);
  }

  public Header parseHeader(InputStream paramInputStream)
    throws IOException, MimeIOException
  {
    MimeConfig localMimeConfig;
    if (this.config != null)
      localMimeConfig = this.config;
    while (true)
    {
      boolean bool = localMimeConfig.isStrictParsing();
      DecodeMonitor localDecodeMonitor;
      label30: FieldParser localFieldParser;
      label43: HeaderImpl localHeaderImpl;
      MimeStreamParser localMimeStreamParser;
      if (this.monitor != null)
      {
        localDecodeMonitor = this.monitor;
        if (this.fieldParser == null)
          break label122;
        localFieldParser = this.fieldParser;
        localHeaderImpl = new HeaderImpl();
        localMimeStreamParser = new MimeStreamParser();
        localMimeStreamParser.setContentHandler(new AbstractContentHandler(localMimeStreamParser, localFieldParser, localDecodeMonitor, localHeaderImpl)
        {
          public void endHeader()
          {
            this.val$parser.stop();
          }

          public void field(Field paramField)
            throws MimeException
          {
            if ((paramField instanceof ParsedField));
            for (ParsedField localParsedField = (ParsedField)paramField; ; localParsedField = this.val$fp.parse(paramField, this.val$mon))
            {
              this.val$header.addField(localParsedField);
              return;
            }
          }
        });
      }
      try
      {
        localMimeStreamParser.parse(paramInputStream);
        return localHeaderImpl;
        localMimeConfig = new MimeConfig();
        continue;
        if (bool)
        {
          localDecodeMonitor = DecodeMonitor.STRICT;
          break label30;
        }
        localDecodeMonitor = DecodeMonitor.SILENT;
        break label30;
        label122: if (bool)
        {
          localFieldParser = DefaultFieldParser.getParser();
          break label43;
        }
        localFieldParser = LenientFieldParser.getParser();
      }
      catch (MimeException localMimeException)
      {
      }
    }
    throw new MimeIOException(localMimeException);
  }

  public Message parseMessage(InputStream paramInputStream)
    throws IOException, MimeIOException
  {
    while (true)
    {
      boolean bool;
      Object localObject1;
      MimeStreamParser localMimeStreamParser;
      try
      {
        MessageImpl localMessageImpl = new MessageImpl();
        if (this.config == null)
          continue;
        MimeConfig localMimeConfig = this.config;
        bool = localMimeConfig.isStrictParsing();
        if (this.monitor != null)
        {
          localDecodeMonitor = this.monitor;
          if (this.bodyDescBuilder == null)
            break label169;
          localObject1 = this.bodyDescBuilder;
          if (this.bodyFactory == null)
            break label220;
          localObject2 = this.bodyFactory;
          localMimeStreamParser = new MimeStreamParser(localMimeConfig, localDecodeMonitor, (BodyDescriptorBuilder)localObject1);
          localMimeStreamParser.setContentHandler(new EntityBuilder(localMessageImpl, (BodyFactory)localObject2));
          localMimeStreamParser.setContentDecoding(this.contentDecoding);
          if (!this.flatMode)
            break label232;
          localMimeStreamParser.setFlat();
          localMimeStreamParser.parse(paramInputStream);
          return localMessageImpl;
          localMimeConfig = new MimeConfig();
          continue;
        }
      }
      catch (MimeException localMimeException)
      {
        throw new MimeIOException(localMimeException);
      }
      if (bool)
      {
        localDecodeMonitor = DecodeMonitor.STRICT;
        continue;
      }
      DecodeMonitor localDecodeMonitor = DecodeMonitor.SILENT;
      continue;
      label169: FieldParser localFieldParser;
      if (this.fieldParser != null)
        localFieldParser = this.fieldParser;
      while (true)
      {
        localObject1 = new DefaultBodyDescriptorBuilder(null, localFieldParser, localDecodeMonitor);
        break;
        if (bool)
        {
          localFieldParser = DefaultFieldParser.getParser();
          continue;
        }
        localFieldParser = LenientFieldParser.getParser();
      }
      label220: Object localObject2 = new BasicBodyFactory();
      continue;
      label232: localMimeStreamParser.setRecurse();
    }
  }

  public void setBodyDescriptorBuilder(BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
    this.bodyDescBuilder = paramBodyDescriptorBuilder;
  }

  public void setBodyFactory(BodyFactory paramBodyFactory)
  {
    this.bodyFactory = paramBodyFactory;
  }

  public void setContentDecoding(boolean paramBoolean)
  {
    this.contentDecoding = paramBoolean;
  }

  public void setDecodeMonitor(DecodeMonitor paramDecodeMonitor)
  {
    this.monitor = paramDecodeMonitor;
  }

  public void setFieldParser(FieldParser<? extends ParsedField> paramFieldParser)
  {
    this.fieldParser = paramFieldParser;
  }

  public void setFlatMode(boolean paramBoolean)
  {
    this.flatMode = paramBoolean;
  }

  public void setMimeEntityConfig(MimeConfig paramMimeConfig)
  {
    this.config = paramMimeConfig;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.DefaultMessageBuilder
 * JD-Core Version:    0.6.0
 */