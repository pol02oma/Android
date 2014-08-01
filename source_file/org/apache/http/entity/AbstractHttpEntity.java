package org.apache.http.entity;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.message.BasicHeader;

@NotThreadSafe
public abstract class AbstractHttpEntity
  implements HttpEntity
{
  protected boolean chunked;
  protected Header contentEncoding;
  protected Header contentType;

  @Deprecated
  public void consumeContent()
    throws IOException
  {
  }

  public Header getContentEncoding()
  {
    return this.contentEncoding;
  }

  public Header getContentType()
  {
    return this.contentType;
  }

  public boolean isChunked()
  {
    return this.chunked;
  }

  public void setChunked(boolean paramBoolean)
  {
    this.chunked = paramBoolean;
  }

  public void setContentEncoding(String paramString)
  {
    BasicHeader localBasicHeader = null;
    if (paramString != null)
      localBasicHeader = new BasicHeader("Content-Encoding", paramString);
    setContentEncoding(localBasicHeader);
  }

  public void setContentEncoding(Header paramHeader)
  {
    this.contentEncoding = paramHeader;
  }

  public void setContentType(String paramString)
  {
    BasicHeader localBasicHeader = null;
    if (paramString != null)
      localBasicHeader = new BasicHeader("Content-Type", paramString);
    setContentType(localBasicHeader);
  }

  public void setContentType(Header paramHeader)
  {
    this.contentType = paramHeader;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.entity.AbstractHttpEntity
 * JD-Core Version:    0.6.0
 */