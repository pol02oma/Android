package org.apache.http.impl.entity;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.annotation.Immutable;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.io.ChunkedOutputStream;
import org.apache.http.impl.io.ContentLengthOutputStream;
import org.apache.http.impl.io.IdentityOutputStream;
import org.apache.http.io.SessionOutputBuffer;

@Immutable
public class EntitySerializer
{
  private final ContentLengthStrategy lenStrategy;

  public EntitySerializer(ContentLengthStrategy paramContentLengthStrategy)
  {
    if (paramContentLengthStrategy == null)
      throw new IllegalArgumentException("Content length strategy may not be null");
    this.lenStrategy = paramContentLengthStrategy;
  }

  protected OutputStream doSerialize(SessionOutputBuffer paramSessionOutputBuffer, HttpMessage paramHttpMessage)
    throws HttpException, IOException
  {
    long l = this.lenStrategy.determineLength(paramHttpMessage);
    if (l == -2L)
      return new ChunkedOutputStream(paramSessionOutputBuffer);
    if (l == -1L)
      return new IdentityOutputStream(paramSessionOutputBuffer);
    return new ContentLengthOutputStream(paramSessionOutputBuffer, l);
  }

  public void serialize(SessionOutputBuffer paramSessionOutputBuffer, HttpMessage paramHttpMessage, HttpEntity paramHttpEntity)
    throws HttpException, IOException
  {
    if (paramSessionOutputBuffer == null)
      throw new IllegalArgumentException("Session output buffer may not be null");
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    OutputStream localOutputStream = doSerialize(paramSessionOutputBuffer, paramHttpMessage);
    paramHttpEntity.writeTo(localOutputStream);
    localOutputStream.close();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.entity.EntitySerializer
 * JD-Core Version:    0.6.0
 */