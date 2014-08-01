package org.apache.http.impl.client;

class SystemClock
  implements Clock
{
  public long getCurrentTime()
  {
    return System.currentTimeMillis();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.SystemClock
 * JD-Core Version:    0.6.0
 */