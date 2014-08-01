package org.apache.http.impl.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.DnsResolver;

public class InMemoryDnsResolver
  implements DnsResolver
{
  private Map<String, InetAddress[]> dnsMap = new ConcurrentHashMap();
  private final Log log = LogFactory.getLog(InMemoryDnsResolver.class);

  public void add(String paramString, InetAddress[] paramArrayOfInetAddress)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Host name may not be null");
    if (paramArrayOfInetAddress == null)
      throw new IllegalArgumentException("Array of IP addresses may not be null");
    this.dnsMap.put(paramString, paramArrayOfInetAddress);
  }

  public InetAddress[] resolve(String paramString)
    throws UnknownHostException
  {
    InetAddress[] arrayOfInetAddress = (InetAddress[])this.dnsMap.get(paramString);
    if (this.log.isInfoEnabled())
      this.log.info("Resolving " + paramString + " to " + Arrays.deepToString(arrayOfInetAddress));
    if (arrayOfInetAddress == null)
      throw new UnknownHostException(paramString + " cannot be resolved");
    return arrayOfInetAddress;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.InMemoryDnsResolver
 * JD-Core Version:    0.6.0
 */