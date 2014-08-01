package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class y
  implements aq
{
  private static y UO;
  private static final Object qI = new Object();
  private String UP;
  private String UQ;
  private ar UR;
  private cg Uc;

  private y(Context paramContext)
  {
    this(as.H(paramContext), new cw());
  }

  y(ar paramar, cg paramcg)
  {
    this.UR = paramar;
    this.Uc = paramcg;
  }

  public static aq F(Context paramContext)
  {
    synchronized (qI)
    {
      if (UO == null)
        UO = new y(paramContext);
      y localy = UO;
      return localy;
    }
  }

  public boolean bk(String paramString)
  {
    if (!this.Uc.cl())
    {
      bh.w("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
      return false;
    }
    if ((this.UP != null) && (this.UQ != null));
    try
    {
      paramString = this.UP + "?" + this.UQ + "=" + URLEncoder.encode(paramString, "UTF-8");
      bh.v("Sending wrapped url hit: " + paramString);
      this.UR.bn(paramString);
      return true;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      bh.b("Error wrapping URL for testing.", localUnsupportedEncodingException);
    }
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.y
 * JD-Core Version:    0.6.0
 */