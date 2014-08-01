package org.apache.http;

import org.apache.http.util.CharArrayBuffer;

public abstract interface FormattedHeader extends Header
{
  public abstract CharArrayBuffer getBuffer();

  public abstract int getValuePos();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.FormattedHeader
 * JD-Core Version:    0.6.0
 */