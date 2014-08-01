package org.apache.james.mime4j.dom;

import java.io.IOException;
import java.io.Reader;

public abstract class TextBody extends SingleBody
{
  public abstract String getMimeCharset();

  public abstract Reader getReader()
    throws IOException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.TextBody
 * JD-Core Version:    0.6.0
 */