package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class z extends aj
{
  private static final String ID = a.ai.toString();
  private final Context mContext;

  public z(Context paramContext)
  {
    super(ID, new String[0]);
    this.mContext = paramContext;
  }

  protected String G(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    String str = G(this.mContext);
    if (str == null)
      return di.ku();
    return di.r(str);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.z
 * JD-Core Version:    0.6.0
 */