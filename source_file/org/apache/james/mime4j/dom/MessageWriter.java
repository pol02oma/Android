package org.apache.james.mime4j.dom;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.james.mime4j.stream.Field;

public abstract interface MessageWriter
{
  public abstract void writeBody(Body paramBody, OutputStream paramOutputStream)
    throws IOException;

  public abstract void writeEntity(Entity paramEntity, OutputStream paramOutputStream)
    throws IOException;

  public abstract void writeField(Field paramField, OutputStream paramOutputStream)
    throws IOException;

  public abstract void writeHeader(Header paramHeader, OutputStream paramOutputStream)
    throws IOException;

  public abstract void writeMessage(Message paramMessage, OutputStream paramOutputStream)
    throws IOException;

  public abstract void writeMultipart(Multipart paramMultipart, OutputStream paramOutputStream)
    throws IOException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.MessageWriter
 * JD-Core Version:    0.6.0
 */