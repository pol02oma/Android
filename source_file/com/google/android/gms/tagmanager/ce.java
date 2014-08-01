package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class ce
{
  private static ce VS;
  private volatile String TM;
  private volatile a VT;
  private volatile String VU;
  private volatile String VV;

  ce()
  {
    clear();
  }

  private String bt(String paramString)
  {
    return paramString.split("&")[0].split("=")[1];
  }

  private String g(Uri paramUri)
  {
    return paramUri.getQuery().replace("&gtm_debug=x", "");
  }

  static ce ju()
  {
    monitorenter;
    try
    {
      if (VS == null)
        VS = new ce();
      ce localce = VS;
      return localce;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void clear()
  {
    this.VT = a.VW;
    this.VU = null;
    this.TM = null;
    this.VV = null;
  }

  boolean f(Uri paramUri)
  {
    int i = 1;
    monitorenter;
    while (true)
    {
      String str;
      try
      {
        str = URLDecoder.decode(paramUri.toString(), "UTF-8");
        if (str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$"))
        {
          bh.v("Container preview url: " + str);
          if (!str.matches(".*?&gtm_debug=x$"))
            continue;
          this.VT = a.VY;
          this.VV = g(paramUri);
          if ((this.VT != a.VX) && (this.VT != a.VY))
            continue;
          this.VU = ("/r?" + this.VV);
          this.TM = bt(this.VV);
          return i;
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        i = 0;
        continue;
        this.VT = a.VX;
        continue;
      }
      finally
      {
        monitorexit;
      }
      if (str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$"))
      {
        if (bt(paramUri.getQuery()).equals(this.TM))
        {
          bh.v("Exit preview mode for container: " + this.TM);
          this.VT = a.VW;
          this.VU = null;
          continue;
        }
      }
      else
      {
        bh.w("Invalid preview uri: " + str);
        i = 0;
        continue;
      }
      i = 0;
    }
  }

  String getContainerId()
  {
    return this.TM;
  }

  a jv()
  {
    return this.VT;
  }

  String jw()
  {
    return this.VU;
  }

  static enum a
  {
    static
    {
      a[] arrayOfa = new a[3];
      arrayOfa[0] = VW;
      arrayOfa[1] = VX;
      arrayOfa[2] = VY;
      VZ = arrayOfa;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ce
 * JD-Core Version:    0.6.0
 */