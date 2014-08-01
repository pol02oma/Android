package org.apache.http;

import java.nio.charset.Charset;

public final class Consts
{
  public static final Charset ASCII;
  public static final int CR = 13;
  public static final int HT = 9;
  public static final Charset ISO_8859_1;
  public static final int LF = 10;
  public static final int SP = 32;
  public static final Charset UTF_8 = Charset.forName("UTF-8");

  static
  {
    ASCII = Charset.forName("US-ASCII");
    ISO_8859_1 = Charset.forName("ISO-8859-1");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.Consts
 * JD-Core Version:    0.6.0
 */