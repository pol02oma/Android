package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Locale;
import java.util.Map;

class bc extends aj
{
  private static final String ID = a.W.toString();

  public bc()
  {
    super(ID, new String[0]);
  }

  public boolean iy()
  {
    return false;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    Locale localLocale = Locale.getDefault();
    if (localLocale == null)
      return di.ku();
    String str = localLocale.getLanguage();
    if (str == null)
      return di.ku();
    return di.r(str.toLowerCase());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.bc
 * JD-Core Version:    0.6.0
 */