package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.dom.BinaryBody;
import org.apache.james.mime4j.dom.TextBody;

public abstract interface BodyFactory
{
  public abstract BinaryBody binaryBody(InputStream paramInputStream)
    throws IOException;

  public abstract TextBody textBody(InputStream paramInputStream, String paramString)
    throws IOException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.BodyFactory
 * JD-Core Version:    0.6.0
 */