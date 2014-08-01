package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentLengthField;
import org.apache.james.mime4j.stream.Field;

public class ContentLengthFieldImpl extends AbstractField
  implements ContentLengthField
{
  public static final FieldParser<ContentLengthField> PARSER = new FieldParser()
  {
    public ContentLengthField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentLengthFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private long contentLength;
  private boolean parsed = false;

  ContentLengthFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    this.contentLength = -1L;
    String str = getBody();
    if (str != null);
    try
    {
      this.contentLength = Long.parseLong(str);
      if (this.contentLength < 0L)
      {
        this.contentLength = -1L;
        if (this.monitor.isListening())
          this.monitor.warn("Negative content length: " + str, "ignoring Content-Length header");
      }
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (!this.monitor.isListening());
      this.monitor.warn("Invalid content length: " + str, "ignoring Content-Length header");
    }
  }

  public long getContentLength()
  {
    if (!this.parsed)
      parse();
    return this.contentLength;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentLengthFieldImpl
 * JD-Core Version:    0.6.0
 */