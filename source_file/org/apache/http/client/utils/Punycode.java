package org.apache.http.client.utils;

import org.apache.http.annotation.Immutable;

@Immutable
public class Punycode
{
  private static final Idn impl;

  static
  {
    try
    {
      localObject = new JdkIdn();
      impl = (Idn)localObject;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Object localObject = new Rfc3492Idn();
    }
  }

  public static String toUnicode(String paramString)
  {
    return impl.toUnicode(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.utils.Punycode
 * JD-Core Version:    0.6.0
 */