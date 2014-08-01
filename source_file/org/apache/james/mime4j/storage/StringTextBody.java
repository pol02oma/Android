package org.apache.james.mime4j.storage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import org.apache.james.mime4j.dom.TextBody;

class StringTextBody extends TextBody
{
  private final Charset charset;
  private final String text;

  public StringTextBody(String paramString, Charset paramCharset)
  {
    this.text = paramString;
    this.charset = paramCharset;
  }

  public StringTextBody copy()
  {
    return new StringTextBody(this.text, this.charset);
  }

  public InputStream getInputStream()
    throws IOException
  {
    return new ByteArrayInputStream(this.text.getBytes(this.charset.name()));
  }

  public String getMimeCharset()
  {
    return this.charset.name();
  }

  public Reader getReader()
    throws IOException
  {
    return new StringReader(this.text);
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException();
    StringReader localStringReader = new StringReader(this.text);
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream, this.charset);
    char[] arrayOfChar = new char[1024];
    while (true)
    {
      int i = localStringReader.read(arrayOfChar);
      if (i == -1)
      {
        localStringReader.close();
        localOutputStreamWriter.flush();
        return;
      }
      localOutputStreamWriter.write(arrayOfChar, 0, i);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.storage.StringTextBody
 * JD-Core Version:    0.6.0
 */