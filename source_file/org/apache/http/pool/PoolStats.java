package org.apache.http.pool;

import org.apache.http.annotation.Immutable;

@Immutable
public class PoolStats
{
  private final int available;
  private final int leased;
  private final int max;
  private final int pending;

  public PoolStats(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.leased = paramInt1;
    this.pending = paramInt2;
    this.available = paramInt3;
    this.max = paramInt4;
  }

  public int getAvailable()
  {
    return this.available;
  }

  public int getLeased()
  {
    return this.leased;
  }

  public int getMax()
  {
    return this.max;
  }

  public int getPending()
  {
    return this.pending;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[leased: ");
    localStringBuilder.append(this.leased);
    localStringBuilder.append("; pending: ");
    localStringBuilder.append(this.pending);
    localStringBuilder.append("; available: ");
    localStringBuilder.append(this.available);
    localStringBuilder.append("; max: ");
    localStringBuilder.append(this.max);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.pool.PoolStats
 * JD-Core Version:    0.6.0
 */