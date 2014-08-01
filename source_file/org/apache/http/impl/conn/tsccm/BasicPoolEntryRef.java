package org.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import org.apache.http.conn.routing.HttpRoute;

@Deprecated
public class BasicPoolEntryRef extends WeakReference<BasicPoolEntry>
{
  private final HttpRoute route;

  public BasicPoolEntryRef(BasicPoolEntry paramBasicPoolEntry, ReferenceQueue<Object> paramReferenceQueue)
  {
    super(paramBasicPoolEntry, paramReferenceQueue);
    if (paramBasicPoolEntry == null)
      throw new IllegalArgumentException("Pool entry must not be null.");
    this.route = paramBasicPoolEntry.getPlannedRoute();
  }

  public final HttpRoute getRoute()
  {
    return this.route;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.tsccm.BasicPoolEntryRef
 * JD-Core Version:    0.6.0
 */