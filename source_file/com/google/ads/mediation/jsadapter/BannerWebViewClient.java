package com.google.ads.mediation.jsadapter;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.da;
import java.net.URI;
import java.net.URISyntaxException;

public final class BannerWebViewClient extends WebViewClient
{
  private final String A = c(paramString);
  private boolean B;
  private final JavascriptAdapter r;

  public BannerWebViewClient(JavascriptAdapter paramJavascriptAdapter, String paramString)
  {
    this.r = paramJavascriptAdapter;
    this.B = false;
  }

  private boolean b(String paramString)
  {
    String str1 = c(paramString);
    if (TextUtils.isEmpty(str1));
    while (true)
    {
      return false;
      try
      {
        URI localURI1 = new URI(str1);
        if ("passback".equals(localURI1.getScheme()))
        {
          da.s("Passback received");
          this.r.sendAdNotReceivedUpdate();
          return true;
        }
        if (TextUtils.isEmpty(this.A))
          continue;
        URI localURI2 = new URI(this.A);
        String str2 = localURI2.getHost();
        String str3 = localURI1.getHost();
        String str4 = localURI2.getPath();
        String str5 = localURI1.getPath();
        if ((!equals(str2, str3)) || (!equals(str4, str5)))
          continue;
        da.s("Passback received");
        this.r.sendAdNotReceivedUpdate();
        return true;
      }
      catch (URISyntaxException localURISyntaxException)
      {
        da.t(localURISyntaxException.getMessage());
      }
    }
    return false;
  }

  private String c(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return paramString;
      try
      {
        if (!paramString.endsWith("/"))
          continue;
        String str = paramString.substring(0, -1 + paramString.length());
        return str;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        da.t(localIndexOutOfBoundsException.getMessage());
      }
    }
    return paramString;
  }

  private static boolean equals(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  public void onLoadResource(WebView paramWebView, String paramString)
  {
    da.v("onLoadResource: " + paramString);
    if (!b(paramString))
      super.onLoadResource(paramWebView, paramString);
  }

  public void onPageFinished(WebView paramWebView, String paramString)
  {
    da.v("onPageFinished: " + paramString);
    super.onPageFinished(paramWebView, paramString);
    if (!this.B)
    {
      this.r.startCheckingForAd();
      this.B = true;
    }
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    da.v("shouldOverrideUrlLoading: " + paramString);
    if (b(paramString))
    {
      da.s("shouldOverrideUrlLoading: received passback url");
      return true;
    }
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.ads.mediation.jsadapter.BannerWebViewClient
 * JD-Core Version:    0.6.0
 */