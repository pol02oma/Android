package org.apache.http.impl.client;

import java.util.HashMap;
import org.apache.http.HttpHost;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthScheme;
import org.apache.http.client.AuthCache;

@NotThreadSafe
public class BasicAuthCache
  implements AuthCache
{
  private final HashMap<HttpHost, AuthScheme> map = new HashMap();

  public void clear()
  {
    this.map.clear();
  }

  public AuthScheme get(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    return (AuthScheme)this.map.get(getKey(paramHttpHost));
  }

  protected HttpHost getKey(HttpHost paramHttpHost)
  {
    if (paramHttpHost.getPort() <= 0)
      if (!paramHttpHost.getSchemeName().equalsIgnoreCase("https"))
        break label42;
    label42: for (int i = 443; ; i = 80)
    {
      paramHttpHost = new HttpHost(paramHttpHost.getHostName(), i, paramHttpHost.getSchemeName());
      return paramHttpHost;
    }
  }

  public void put(HttpHost paramHttpHost, AuthScheme paramAuthScheme)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    this.map.put(getKey(paramHttpHost), paramAuthScheme);
  }

  public void remove(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    this.map.remove(getKey(paramHttpHost));
  }

  public String toString()
  {
    return this.map.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.BasicAuthCache
 * JD-Core Version:    0.6.0
 */