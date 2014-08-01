package org.apache.james.mime4j.stream;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;

public abstract interface EntityStateMachine
{
  public abstract EntityStateMachine advance()
    throws IOException, MimeException;

  public abstract BodyDescriptor getBodyDescriptor()
    throws IllegalStateException;

  public abstract InputStream getContentStream()
    throws IllegalStateException;

  public abstract InputStream getDecodedContentStream()
    throws IllegalStateException;

  public abstract Field getField()
    throws IllegalStateException;

  public abstract EntityState getState();

  public abstract void setRecursionMode(RecursionMode paramRecursionMode);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.EntityStateMachine
 * JD-Core Version:    0.6.0
 */