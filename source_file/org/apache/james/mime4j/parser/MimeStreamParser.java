package org.apache.james.mime4j.parser;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.stream.BodyDescriptor;
import org.apache.james.mime4j.stream.BodyDescriptorBuilder;
import org.apache.james.mime4j.stream.EntityState;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.MimeConfig;
import org.apache.james.mime4j.stream.MimeTokenStream;
import org.apache.james.mime4j.stream.RecursionMode;

public class MimeStreamParser
{
  private boolean contentDecoding;
  private ContentHandler handler = null;
  private final MimeTokenStream mimeTokenStream;

  public MimeStreamParser()
  {
    this(new MimeTokenStream(new MimeConfig(), null, null));
  }

  public MimeStreamParser(MimeConfig paramMimeConfig)
  {
    this(paramMimeConfig, null, null);
  }

  public MimeStreamParser(MimeConfig paramMimeConfig, DecodeMonitor paramDecodeMonitor, BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
  }

  public MimeStreamParser(MimeTokenStream paramMimeTokenStream)
  {
    this.mimeTokenStream = paramMimeTokenStream;
    this.contentDecoding = false;
  }

  public boolean isContentDecoding()
  {
    return this.contentDecoding;
  }

  public boolean isRaw()
  {
    return this.mimeTokenStream.isRaw();
  }

  public void parse(InputStream paramInputStream)
    throws MimeException, IOException
  {
    MimeConfig localMimeConfig = this.mimeTokenStream.getConfig();
    if (localMimeConfig.getHeadlessParsing() != null)
    {
      Field localField = this.mimeTokenStream.parseHeadless(paramInputStream, localMimeConfig.getHeadlessParsing());
      this.handler.startMessage();
      this.handler.startHeader();
      this.handler.field(localField);
      this.handler.endHeader();
    }
    while (true)
    {
      EntityState localEntityState = this.mimeTokenStream.getState();
      switch (1.$SwitchMap$org$apache$james$mime4j$stream$EntityState[localEntityState.ordinal()])
      {
      default:
        throw new IllegalStateException("Invalid state: " + localEntityState);
        this.mimeTokenStream.parse(paramInputStream);
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 6:
      }
    }
    BodyDescriptor localBodyDescriptor = this.mimeTokenStream.getBodyDescriptor();
    InputStream localInputStream;
    if (this.contentDecoding)
    {
      localInputStream = this.mimeTokenStream.getDecodedInputStream();
      label215: this.handler.body(localBodyDescriptor, localInputStream);
    }
    while (true)
    {
      this.mimeTokenStream.next();
      break;
      localInputStream = this.mimeTokenStream.getInputStream();
      break label215;
      this.handler.endBodyPart();
      continue;
      this.handler.endHeader();
      continue;
      this.handler.endMessage();
      continue;
      this.handler.endMultipart();
      continue;
      this.handler.epilogue(this.mimeTokenStream.getInputStream());
      continue;
      this.handler.field(this.mimeTokenStream.getField());
      continue;
      this.handler.preamble(this.mimeTokenStream.getInputStream());
      continue;
      this.handler.raw(this.mimeTokenStream.getInputStream());
      continue;
      this.handler.startBodyPart();
      continue;
      this.handler.startHeader();
      continue;
      this.handler.startMessage();
      continue;
      this.handler.startMultipart(this.mimeTokenStream.getBodyDescriptor());
    }
  }

  public void setContentDecoding(boolean paramBoolean)
  {
    this.contentDecoding = paramBoolean;
  }

  public void setContentHandler(ContentHandler paramContentHandler)
  {
    this.handler = paramContentHandler;
  }

  public void setFlat()
  {
    this.mimeTokenStream.setRecursionMode(RecursionMode.M_FLAT);
  }

  public void setRaw()
  {
    this.mimeTokenStream.setRecursionMode(RecursionMode.M_RAW);
  }

  public void setRecurse()
  {
    this.mimeTokenStream.setRecursionMode(RecursionMode.M_RECURSE);
  }

  public void stop()
  {
    this.mimeTokenStream.stop();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.parser.MimeStreamParser
 * JD-Core Version:    0.6.0
 */