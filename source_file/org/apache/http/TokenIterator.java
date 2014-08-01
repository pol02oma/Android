package org.apache.http;

import java.util.Iterator;

public abstract interface TokenIterator extends Iterator<Object>
{
  public abstract boolean hasNext();

  public abstract String nextToken();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.TokenIterator
 * JD-Core Version:    0.6.0
 */