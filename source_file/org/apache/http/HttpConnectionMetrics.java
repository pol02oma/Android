package org.apache.http;

public abstract interface HttpConnectionMetrics
{
  public abstract Object getMetric(String paramString);

  public abstract long getReceivedBytesCount();

  public abstract long getRequestCount();

  public abstract long getResponseCount();

  public abstract long getSentBytesCount();

  public abstract void reset();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpConnectionMetrics
 * JD-Core Version:    0.6.0
 */