package org.apache.james.mime4j.stream;

import java.io.InputStream;

public class RawEntity
  implements EntityStateMachine
{
  private EntityState state;
  private final InputStream stream;

  RawEntity(InputStream paramInputStream)
  {
    this.stream = paramInputStream;
    this.state = EntityState.T_RAW_ENTITY;
  }

  public EntityStateMachine advance()
  {
    this.state = EntityState.T_END_OF_STREAM;
    return null;
  }

  public BodyDescriptor getBodyDescriptor()
  {
    return null;
  }

  public InputStream getContentStream()
  {
    return this.stream;
  }

  public InputStream getDecodedContentStream()
    throws IllegalStateException
  {
    throw new IllegalStateException("Raw entity does not support stream decoding");
  }

  public RawField getField()
  {
    return null;
  }

  public String getFieldName()
  {
    return null;
  }

  public String getFieldValue()
  {
    return null;
  }

  public EntityState getState()
  {
    return this.state;
  }

  public void setRecursionMode(RecursionMode paramRecursionMode)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.RawEntity
 * JD-Core Version:    0.6.0
 */