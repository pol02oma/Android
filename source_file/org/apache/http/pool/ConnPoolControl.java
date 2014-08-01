package org.apache.http.pool;

public abstract interface ConnPoolControl<T>
{
  public abstract int getDefaultMaxPerRoute();

  public abstract int getMaxPerRoute(T paramT);

  public abstract int getMaxTotal();

  public abstract PoolStats getStats(T paramT);

  public abstract PoolStats getTotalStats();

  public abstract void setDefaultMaxPerRoute(int paramInt);

  public abstract void setMaxPerRoute(T paramT, int paramInt);

  public abstract void setMaxTotal(int paramInt);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.pool.ConnPoolControl
 * JD-Core Version:    0.6.0
 */