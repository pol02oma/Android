package com.google.android.gms.internal;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.net.UrlQuerySanitizer.ParameterValuePair;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class de extends WebViewClient
{
  private ap lV;
  private final Object mg = new Object();
  protected final dd ng;
  private final HashMap<String, ar> qf = new HashMap();
  private u qg;
  private br qh;
  private a qi;
  private boolean qj = false;
  private boolean qk;
  private bu ql;

  public de(dd paramdd, boolean paramBoolean)
  {
    this.ng = paramdd;
    this.qk = paramBoolean;
  }

  private void a(bq parambq)
  {
    bo.a(this.ng.getContext(), parambq);
  }

  private static boolean b(Uri paramUri)
  {
    String str = paramUri.getScheme();
    return ("http".equalsIgnoreCase(str)) || ("https".equalsIgnoreCase(str));
  }

  private void c(Uri paramUri)
  {
    String str1 = paramUri.getPath();
    ar localar = (ar)this.qf.get(str1);
    if (localar != null)
    {
      HashMap localHashMap = new HashMap();
      UrlQuerySanitizer localUrlQuerySanitizer = new UrlQuerySanitizer();
      localUrlQuerySanitizer.setAllowUnregisteredParamaters(true);
      localUrlQuerySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
      localUrlQuerySanitizer.parseUrl(paramUri.toString());
      Iterator localIterator1 = localUrlQuerySanitizer.getParameterList().iterator();
      while (localIterator1.hasNext())
      {
        UrlQuerySanitizer.ParameterValuePair localParameterValuePair = (UrlQuerySanitizer.ParameterValuePair)localIterator1.next();
        localHashMap.put(localParameterValuePair.mParameter, localParameterValuePair.mValue);
      }
      if (da.n(2))
      {
        da.v("Received GMSG: " + str1);
        Iterator localIterator2 = localHashMap.keySet().iterator();
        while (localIterator2.hasNext())
        {
          String str2 = (String)localIterator2.next();
          da.v("  " + str2 + ": " + (String)localHashMap.get(str2));
        }
      }
      localar.a(this.ng, localHashMap);
      return;
    }
    da.w("No GMSG handler found for GMSG: " + paramUri);
  }

  public final void a(bn parambn)
  {
    boolean bool = this.ng.be();
    u localu;
    br localbr;
    if ((bool) && (!this.ng.Q().lo))
    {
      localu = null;
      localbr = null;
      if (!bool)
        break label69;
    }
    while (true)
    {
      a(new bq(parambn, localu, localbr, this.ql, this.ng.bd()));
      return;
      localu = this.qg;
      break;
      label69: localbr = this.qh;
    }
  }

  public final void a(a parama)
  {
    this.qi = parama;
  }

  public void a(u paramu, br parambr, ap paramap, bu parambu, boolean paramBoolean)
  {
    a("/appEvent", new ao(paramap));
    a("/canOpenURLs", aq.lW);
    a("/click", aq.lX);
    a("/close", aq.lY);
    a("/customClose", aq.lZ);
    a("/httpTrack", aq.ma);
    a("/log", aq.mb);
    a("/open", aq.mc);
    a("/touch", aq.md);
    a("/video", aq.me);
    this.qg = paramu;
    this.qh = parambr;
    this.lV = paramap;
    this.ql = parambu;
    o(paramBoolean);
  }

  public final void a(String paramString, ar paramar)
  {
    this.qf.put(paramString, paramar);
  }

  public final void a(boolean paramBoolean, int paramInt)
  {
    if ((this.ng.be()) && (!this.ng.Q().lo));
    for (u localu = null; ; localu = this.qg)
    {
      a(new bq(localu, this.qh, this.ql, this.ng, paramBoolean, paramInt, this.ng.bd()));
      return;
    }
  }

  public final void a(boolean paramBoolean, int paramInt, String paramString)
  {
    boolean bool = this.ng.be();
    u localu;
    br localbr;
    if ((bool) && (!this.ng.Q().lo))
    {
      localu = null;
      localbr = null;
      if (!bool)
        break label85;
    }
    while (true)
    {
      a(new bq(localu, localbr, this.lV, this.ql, this.ng, paramBoolean, paramInt, paramString, this.ng.bd()));
      return;
      localu = this.qg;
      break;
      label85: localbr = this.qh;
    }
  }

  public final void a(boolean paramBoolean, int paramInt, String paramString1, String paramString2)
  {
    boolean bool = this.ng.be();
    u localu;
    br localbr;
    if ((bool) && (!this.ng.Q().lo))
    {
      localu = null;
      localbr = null;
      if (!bool)
        break label87;
    }
    while (true)
    {
      a(new bq(localu, localbr, this.lV, this.ql, this.ng, paramBoolean, paramInt, paramString1, paramString2, this.ng.bd()));
      return;
      localu = this.qg;
      break;
      label87: localbr = this.qh;
    }
  }

  public final void ar()
  {
    synchronized (this.mg)
    {
      this.qj = false;
      this.qk = true;
      bo localbo = this.ng.ba();
      if (localbo != null)
      {
        if (!cz.aX())
          cz.pT.post(new Runnable(localbo)
          {
            public void run()
            {
              this.qm.ar();
            }
          });
      }
      else
        return;
      localbo.ar();
    }
  }

  public boolean bi()
  {
    synchronized (this.mg)
    {
      boolean bool = this.qk;
      return bool;
    }
  }

  public final void o(boolean paramBoolean)
  {
    this.qj = paramBoolean;
  }

  public final void onPageFinished(WebView paramWebView, String paramString)
  {
    if (this.qi != null)
    {
      this.qi.a(this.ng);
      this.qi = null;
    }
  }

  public final void reset()
  {
    synchronized (this.mg)
    {
      this.qf.clear();
      this.qg = null;
      this.qh = null;
      this.qi = null;
      this.lV = null;
      this.qj = false;
      this.qk = false;
      this.ql = null;
      return;
    }
  }

  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    da.v("AdWebView shouldOverrideUrlLoading: " + paramString);
    Object localObject1 = Uri.parse(paramString);
    if (("gmsg".equalsIgnoreCase(((Uri)localObject1).getScheme())) && ("mobileads.google.com".equalsIgnoreCase(((Uri)localObject1).getHost())))
      c((Uri)localObject1);
    while (true)
    {
      return true;
      if ((this.qj) && (paramWebView == this.ng) && (b((Uri)localObject1)))
        return super.shouldOverrideUrlLoading(paramWebView, paramString);
      if (!this.ng.willNotDraw())
        try
        {
          l locall = this.ng.bc();
          if ((locall != null) && (locall.a((Uri)localObject1)))
          {
            Uri localUri = locall.a((Uri)localObject1, this.ng.getContext());
            localObject1 = localUri;
          }
          localObject2 = localObject1;
          a(new bn("android.intent.action.VIEW", localObject2.toString(), null, null, null, null, null));
        }
        catch (m localm)
        {
          while (true)
          {
            da.w("Unable to append parameter to URL: " + paramString);
            Object localObject2 = localObject1;
          }
        }
      da.w("AdWebView unable to handle URL: " + paramString);
    }
  }

  public static abstract interface a
  {
    public abstract void a(dd paramdd);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.de
 * JD-Core Version:    0.6.0
 */