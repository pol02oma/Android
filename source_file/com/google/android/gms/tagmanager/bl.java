package com.google.android.gms.tagmanager;

import java.io.IOException;
import java.io.InputStream;

abstract interface bl
{
  public abstract InputStream bo(String paramString)
    throws IOException;

  public abstract void close();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.bl
 * JD-Core Version:    0.6.0
 */