package org.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.AbstractPoolEntry;

@Deprecated
public class BasicPoolEntry extends AbstractPoolEntry
{
  private final long created;
  private long expiry;
  private long updated;
  private long validUntil;

  public BasicPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute)
  {
    this(paramClientConnectionOperator, paramHttpRoute, -1L, TimeUnit.MILLISECONDS);
  }

  public BasicPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute, long paramLong, TimeUnit paramTimeUnit)
  {
    super(paramClientConnectionOperator, paramHttpRoute);
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("HTTP route may not be null");
    this.created = System.currentTimeMillis();
    if (paramLong > 0L);
    for (this.validUntil = (this.created + paramTimeUnit.toMillis(paramLong)); ; this.validUntil = 9223372036854775807L)
    {
      this.expiry = this.validUntil;
      return;
    }
  }

  public BasicPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute, ReferenceQueue<Object> paramReferenceQueue)
  {
    super(paramClientConnectionOperator, paramHttpRoute);
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("HTTP route may not be null");
    this.created = System.currentTimeMillis();
    this.validUntil = 9223372036854775807L;
    this.expiry = this.validUntil;
  }

  protected final OperatedClientConnection getConnection()
  {
    return this.connection;
  }

  public long getCreated()
  {
    return this.created;
  }

  public long getExpiry()
  {
    return this.expiry;
  }

  protected final HttpRoute getPlannedRoute()
  {
    return this.route;
  }

  public long getUpdated()
  {
    return this.updated;
  }

  public long getValidUntil()
  {
    return this.validUntil;
  }

  protected final BasicPoolEntryRef getWeakRef()
  {
    return null;
  }

  public boolean isExpired(long paramLong)
  {
    return paramLong >= this.expiry;
  }

  protected void shutdownEntry()
  {
    super.shutdownEntry();
  }

  public void updateExpiry(long paramLong, TimeUnit paramTimeUnit)
  {
    this.updated = System.currentTimeMillis();
    long l;
    if (paramLong > 0L)
      l = this.updated + paramTimeUnit.toMillis(paramLong);
    while (true)
    {
      this.expiry = Math.min(this.validUntil, l);
      return;
      l = 9223372036854775807L;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.tsccm.BasicPoolEntry
 * JD-Core Version:    0.6.0
 */