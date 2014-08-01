package org.apache.http.impl.client;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultConnectionKeepAliveStrategy
  implements ConnectionKeepAliveStrategy
{
  public long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    BasicHeaderElementIterator localBasicHeaderElementIterator = new BasicHeaderElementIterator(paramHttpResponse.headerIterator("Keep-Alive"));
    while (true)
    {
      String str2;
      if (localBasicHeaderElementIterator.hasNext())
      {
        HeaderElement localHeaderElement = localBasicHeaderElementIterator.nextElement();
        String str1 = localHeaderElement.getName();
        str2 = localHeaderElement.getValue();
        if ((str2 == null) || (!str1.equalsIgnoreCase("timeout")))
          continue;
      }
      try
      {
        long l = Long.parseLong(str2);
        return l * 1000L;
        return -1L;
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy
 * JD-Core Version:    0.6.0
 */