package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.annotation.Immutable;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.params.HttpParams;

@Immutable
public class LaxContentLengthStrategy
  implements ContentLengthStrategy
{
  private final int implicitLen;

  public LaxContentLengthStrategy()
  {
    this(-1);
  }

  public LaxContentLengthStrategy(int paramInt)
  {
    this.implicitLen = paramInt;
  }

  public long determineLength(HttpMessage paramHttpMessage)
    throws HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    boolean bool = paramHttpMessage.getParams().isParameterTrue("http.protocol.strict-transfer-encoding");
    Header localHeader1 = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    long l1;
    if (localHeader1 != null)
    {
      HeaderElement[] arrayOfHeaderElement;
      while (true)
      {
        int k;
        try
        {
          arrayOfHeaderElement = localHeader1.getElements();
          if (!bool)
            break;
          k = 0;
          if (k >= arrayOfHeaderElement.length)
            break;
          String str = arrayOfHeaderElement[k].getName();
          if ((str != null) && (str.length() > 0) && (!str.equalsIgnoreCase("chunked")) && (!str.equalsIgnoreCase("identity")))
            throw new ProtocolException("Unsupported transfer encoding: " + str);
        }
        catch (ParseException localParseException)
        {
          throw new ProtocolException("Invalid Transfer-Encoding header value: " + localHeader1, localParseException);
        }
        k++;
      }
      int j = arrayOfHeaderElement.length;
      if ("identity".equalsIgnoreCase(localHeader1.getValue()))
      {
        l1 = -1L;
        return l1;
      }
      if ((j > 0) && ("chunked".equalsIgnoreCase(arrayOfHeaderElement[(j - 1)].getName())))
        return -2L;
      if (bool)
        throw new ProtocolException("Chunk-encoding must be the last one applied");
      return -1L;
    }
    if (paramHttpMessage.getFirstHeader("Content-Length") != null)
    {
      l1 = -1L;
      Header[] arrayOfHeader = paramHttpMessage.getHeaders("Content-Length");
      if ((bool) && (arrayOfHeader.length > 1))
        throw new ProtocolException("Multiple content length headers");
      int i = -1 + arrayOfHeader.length;
      while (true)
        while (true)
        {
          Header localHeader2;
          if (i >= 0)
            localHeader2 = arrayOfHeader[i];
          try
          {
            long l2 = Long.parseLong(localHeader2.getValue());
            l1 = l2;
            if (l1 >= 0L)
              break;
            return -1L;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            if (bool)
              throw new ProtocolException("Invalid content length: " + localHeader2.getValue());
            i--;
          }
        }
    }
    return this.implicitLen;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.entity.LaxContentLengthStrategy
 * JD-Core Version:    0.6.0
 */