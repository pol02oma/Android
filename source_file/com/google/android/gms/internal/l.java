package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.view.MotionEvent;

public class l
{
  private String ko = "googleads.g.doubleclick.net";
  private String kp = "/pagead/ads";
  private String[] kq = { ".doubleclick.net", ".googleadservices.com", ".googlesyndication.com" };
  private h kr;
  private final g ks = new g();

  public l(h paramh)
  {
    this.kr = paramh;
  }

  private Uri a(Uri paramUri, Context paramContext, String paramString, boolean paramBoolean)
    throws m
  {
    try
    {
      if (paramUri.getQueryParameter("ms") != null)
        throw new m("Query parameter already exists: ms");
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      throw new m("Provided Uri is not in a valid state");
    }
    if (paramBoolean);
    String str;
    for (Object localObject = this.kr.a(paramContext, paramString); ; localObject = str)
    {
      return a(paramUri, "ms", (String)localObject);
      str = this.kr.a(paramContext);
    }
  }

  private Uri a(Uri paramUri, String paramString1, String paramString2)
    throws UnsupportedOperationException
  {
    String str = paramUri.toString();
    int i = str.indexOf("&adurl");
    if (i == -1)
      i = str.indexOf("?adurl");
    if (i != -1)
      return Uri.parse(str.substring(0, i + 1) + paramString1 + "=" + paramString2 + "&" + str.substring(i + 1));
    return paramUri.buildUpon().appendQueryParameter(paramString1, paramString2).build();
  }

  public Uri a(Uri paramUri, Context paramContext)
    throws m
  {
    try
    {
      Uri localUri = a(paramUri, paramContext, paramUri.getQueryParameter("ai"), true);
      return localUri;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
    }
    throw new m("Provided Uri is not in a valid state");
  }

  public void a(MotionEvent paramMotionEvent)
  {
    this.kr.a(paramMotionEvent);
  }

  public boolean a(Uri paramUri)
  {
    if (paramUri == null)
      throw new NullPointerException();
    try
    {
      String str = paramUri.getHost();
      String[] arrayOfString = this.kq;
      int i = arrayOfString.length;
      for (int j = 0; ; j++)
      {
        int k = 0;
        if (j < i)
        {
          boolean bool = str.endsWith(arrayOfString[j]);
          if (!bool)
            continue;
          k = 1;
        }
        return k;
      }
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    return false;
  }

  public h y()
  {
    return this.kr;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.l
 * JD-Core Version:    0.6.0
 */