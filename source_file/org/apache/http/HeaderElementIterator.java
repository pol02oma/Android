package org.apache.http;

import java.util.Iterator;

public abstract interface HeaderElementIterator extends Iterator<Object>
{
  public abstract boolean hasNext();

  public abstract HeaderElement nextElement();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.HeaderElementIterator
 * JD-Core Version:    0.6.0
 */