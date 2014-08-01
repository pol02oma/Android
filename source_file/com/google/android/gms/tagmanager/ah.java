package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ah extends aj
{
  private static final String ID = a.T.toString();
  private final ct TO;

  public ah(ct paramct)
  {
    super(ID, new String[0]);
    this.TO = paramct;
  }

  public boolean iy()
  {
    return false;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    String str = this.TO.jY();
    if (str == null)
      return di.ku();
    return di.r(str);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ah
 * JD-Core Version:    0.6.0
 */