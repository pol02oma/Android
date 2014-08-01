package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.annotation.Immutable;
import org.apache.http.entity.ContentLengthStrategy;

@Immutable
public class StrictContentLengthStrategy
  implements ContentLengthStrategy
{
  private final int implicitLen;

  public StrictContentLengthStrategy()
  {
    this(-1);
  }

  public StrictContentLengthStrategy(int paramInt)
  {
    this.implicitLen = paramInt;
  }

  public long determineLength(HttpMessage paramHttpMessage)
    throws HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    Header localHeader1 = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    String str2;
    long l;
    if (localHeader1 != null)
    {
      str2 = localHeader1.getValue();
      if ("chunked".equalsIgnoreCase(str2))
      {
        if (paramHttpMessage.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0))
          throw new ProtocolException("Chunked transfer encoding not allowed for " + paramHttpMessage.getProtocolVersion());
        l = -2L;
      }
    }
    while (true)
    {
      return l;
      if ("identity".equalsIgnoreCase(str2))
        return -1L;
      throw new ProtocolException("Unsupported transfer encoding: " + str2);
      Header localHeader2 = paramHttpMessage.getFirstHeader("Content-Length");
      if (localHeader2 == null)
        break;
      String str1 = localHeader2.getValue();
      try
      {
        l = Long.parseLong(str1);
        if (l >= 0L)
          continue;
        throw new ProtocolException("Negative content length: " + str1);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new ProtocolException("Invalid content length: " + str1);
      }
    }
    return this.implicitLen;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.entity.StrictContentLengthStrategy
 * JD-Core Version:    0.6.0
 */