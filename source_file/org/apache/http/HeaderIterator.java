package org.apache.http;

import java.util.Iterator;

public abstract interface HeaderIterator extends Iterator<Object>
{
  public abstract boolean hasNext();

  public abstract Header nextHeader();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.HeaderIterator
 * JD-Core Version:    0.6.0
 */