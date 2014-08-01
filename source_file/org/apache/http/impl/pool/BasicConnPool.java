package org.apache.http.impl.pool;

import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.params.HttpParams;
import org.apache.http.pool.AbstractConnPool;
import org.apache.http.pool.ConnFactory;

@ThreadSafe
public class BasicConnPool extends AbstractConnPool<HttpHost, HttpClientConnection, BasicPoolEntry>
{
  private static AtomicLong COUNTER = new AtomicLong();

  public BasicConnPool(HttpParams paramHttpParams)
  {
    super(new BasicConnFactory(paramHttpParams), 2, 20);
  }

  public BasicConnPool(ConnFactory<HttpHost, HttpClientConnection> paramConnFactory)
  {
    super(paramConnFactory, 2, 20);
  }

  protected BasicPoolEntry createEntry(HttpHost paramHttpHost, HttpClientConnection paramHttpClientConnection)
  {
    return new BasicPoolEntry(Long.toString(COUNTER.getAndIncrement()), paramHttpHost, paramHttpClientConnection);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.pool.BasicConnPool
 * JD-Core Version:    0.6.0
 */