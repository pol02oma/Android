package org.apache.james.mime4j.message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.apache.james.mime4j.dom.SingleBody;
import org.apache.james.mime4j.dom.TextBody;

class BasicTextBody extends TextBody
{
  private final String charset;
  private final byte[] content;

  BasicTextBody(byte[] paramArrayOfByte, String paramString)
  {
    this.content = paramArrayOfByte;
    this.charset = paramString;
  }

  public SingleBody copy()
  {
    return new BasicTextBody(this.content, this.charset);
  }

  public InputStream getInputStream()
    throws IOException
  {
    return new ByteArrayInputStream(this.content);
  }

  public String getMimeCharset()
  {
    return this.charset;
  }

  public Reader getReader()
    throws IOException
  {
    return new InputStreamReader(getInputStream(), this.charset);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.BasicTextBody
 * JD-Core Version:    0.6.0
 */