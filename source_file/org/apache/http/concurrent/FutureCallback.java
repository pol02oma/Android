package org.apache.http.concurrent;

public abstract interface FutureCallback<T>
{
  public abstract void cancelled();

  public abstract void completed(T paramT);

  public abstract void failed(Exception paramException);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.concurrent.FutureCallback
 * JD-Core Version:    0.6.0
 */