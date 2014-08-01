package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.File;

public final class cw
{
  public static void a(Context paramContext, WebSettings paramWebSettings)
  {
    paramWebSettings.setAppCachePath(new File(paramContext.getCacheDir(), "com.google.android.gms.ads.appcache").getAbsolutePath());
    paramWebSettings.setAppCacheMaxSize(0L);
    paramWebSettings.setAppCacheEnabled(true);
    paramWebSettings.setDatabasePath(paramContext.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
    paramWebSettings.setDatabaseEnabled(true);
    paramWebSettings.setDomStorageEnabled(true);
    paramWebSettings.setDisplayZoomControls(false);
    paramWebSettings.setBuiltInZoomControls(true);
    paramWebSettings.setSupportZoom(true);
  }

  public static void a(Window paramWindow)
  {
    paramWindow.setFlags(16777216, 16777216);
  }

  public static void a(WebView paramWebView)
  {
    paramWebView.onPause();
  }

  public static void b(WebView paramWebView)
  {
    paramWebView.onResume();
  }

  public static void c(View paramView)
  {
    paramView.setLayerType(1, null);
  }

  public static void d(View paramView)
  {
    paramView.setLayerType(0, null);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cw
 * JD-Core Version:    0.6.0
 */