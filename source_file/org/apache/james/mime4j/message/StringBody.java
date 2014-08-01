package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import org.apache.james.mime4j.dom.SingleBody;
import org.apache.james.mime4j.dom.TextBody;

class StringBody extends TextBody
{
  private final Charset charset;
  private final String content;

  StringBody(String paramString, Charset paramCharset)
  {
    this.content = paramString;
    this.charset = paramCharset;
  }

  public SingleBody copy()
  {
    return new StringBody(this.content, this.charset);
  }

  public InputStream getInputStream()
    throws IOException
  {
    return new StringInputStream(this.content, this.charset, 2048);
  }

  public String getMimeCharset()
  {
    return this.charset.name();
  }

  public Reader getReader()
    throws IOException
  {
    return new StringReader(this.content);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.StringBody
 * JD-Core Version:    0.6.0
 */