package org.apache.james.mime4j.message;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.MessageBuilder;
import org.apache.james.mime4j.dom.MessageServiceFactory;
import org.apache.james.mime4j.dom.MessageWriter;
import org.apache.james.mime4j.stream.BodyDescriptorBuilder;
import org.apache.james.mime4j.stream.MimeConfig;

public class MessageServiceFactoryImpl extends MessageServiceFactory
{
  private BodyDescriptorBuilder bodyDescriptorBuilder = null;
  private BodyFactory bodyFactory = null;
  private Boolean contentDecoding = null;
  private DecodeMonitor decodeMonitor = null;
  private Boolean flatMode = null;
  private MimeConfig mimeEntityConfig = null;

  public MessageBuilder newMessageBuilder()
  {
    DefaultMessageBuilder localDefaultMessageBuilder = new DefaultMessageBuilder();
    if (this.bodyFactory != null)
      localDefaultMessageBuilder.setBodyFactory(this.bodyFactory);
    if (this.mimeEntityConfig != null)
      localDefaultMessageBuilder.setMimeEntityConfig(this.mimeEntityConfig);
    if (this.bodyDescriptorBuilder != null)
      localDefaultMessageBuilder.setBodyDescriptorBuilder(this.bodyDescriptorBuilder);
    if (this.flatMode != null)
      localDefaultMessageBuilder.setFlatMode(this.flatMode.booleanValue());
    if (this.contentDecoding != null)
      localDefaultMessageBuilder.setContentDecoding(this.contentDecoding.booleanValue());
    if (this.decodeMonitor != null)
      localDefaultMessageBuilder.setDecodeMonitor(this.decodeMonitor);
    return localDefaultMessageBuilder;
  }

  public MessageWriter newMessageWriter()
  {
    return new DefaultMessageWriter();
  }

  public void setAttribute(String paramString, Object paramObject)
    throws IllegalArgumentException
  {
    if ("BodyFactory".equals(paramString))
    {
      if ((paramObject instanceof BodyFactory))
      {
        this.bodyFactory = ((BodyFactory)paramObject);
        return;
      }
      throw new IllegalArgumentException("Unsupported attribute value type for " + paramString + ", expected a BodyFactory");
    }
    if ("MimeEntityConfig".equals(paramString))
    {
      if ((paramObject instanceof MimeConfig))
      {
        this.mimeEntityConfig = ((MimeConfig)paramObject);
        return;
      }
      throw new IllegalArgumentException("Unsupported attribute value type for " + paramString + ", expected a MimeConfig");
    }
    if ("MutableBodyDescriptorFactory".equals(paramString))
    {
      if ((paramObject instanceof BodyDescriptorBuilder))
      {
        this.bodyDescriptorBuilder = ((BodyDescriptorBuilder)paramObject);
        return;
      }
      throw new IllegalArgumentException("Unsupported attribute value type for " + paramString + ", expected a MutableBodyDescriptorFactory");
    }
    if ("DecodeMonitor".equals(paramString))
    {
      if ((paramObject instanceof DecodeMonitor))
      {
        this.decodeMonitor = ((DecodeMonitor)paramObject);
        return;
      }
      throw new IllegalArgumentException("Unsupported attribute value type for " + paramString + ", expected a DecodeMonitor");
    }
    if ("FlatMode".equals(paramString))
    {
      if ((paramObject instanceof Boolean))
      {
        this.flatMode = ((Boolean)paramObject);
        return;
      }
      throw new IllegalArgumentException("Unsupported attribute value type for " + paramString + ", expected a Boolean");
    }
    if ("ContentDecoding".equals(paramString))
    {
      if ((paramObject instanceof Boolean))
      {
        this.contentDecoding = ((Boolean)paramObject);
        return;
      }
      throw new IllegalArgumentException("Unsupported attribute value type for " + paramString + ", expected a Boolean");
    }
    throw new IllegalArgumentException("Unsupported attribute: " + paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.MessageServiceFactoryImpl
 * JD-Core Version:    0.6.0
 */