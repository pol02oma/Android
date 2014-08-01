package com.ibuildapp.romanblack.MapPlugin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModule;
import com.appbuilder.statistics.StatisticsCollector;

public class MapRoute extends AppBuilderModule
{
  private final int HIDE_PROGRESS = 3;
  private final int INITIALIZATION_FAILED = 0;
  private final int LOADING_ABORTED = 5;
  private final int NEED_INTERNET_CONNECTION = 1;
  private final int SHOW_PROGRESS = 2;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      case 4:
      default:
        return;
      case 0:
        Toast.makeText(MapRoute.this, R.string.romanblack_map_alert_cannot_init_route, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            MapRoute.this.finish();
          }
        }
        , 5000L);
        return;
      case 1:
        Toast.makeText(MapRoute.this, R.string.alert_no_internet, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            MapRoute.this.finish();
          }
        }
        , 5000L);
        return;
      case 2:
        MapRoute.this.showProgress();
        return;
      case 3:
        MapRoute.this.hideProgress();
        return;
      case 5:
      }
      MapRoute.this.closeActivity();
    }
  };
  private boolean isOnline = false;
  private ProgressDialog progressDialog = null;
  private String routeUrl = "";
  private WebView webView = null;

  private void hideProgress()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void logError(String paramString, Exception paramException)
  {
    StatisticsCollector.newError(paramException, paramString);
  }

  private void showProgress()
  {
    this.progressDialog = ProgressDialog.show(this, null, getString(R.string.common_loading_upper), true);
  }

  public void closeActivity()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
    finish();
  }

  public void create()
  {
    try
    {
      getWindow().setFlags(1024, 1024);
      setContentView(R.layout.romanblack_mapweb_route);
      setTitle(R.string.romanblack_map_route_upper);
      this.routeUrl = getIntent().getExtras().getString("url");
      if (this.routeUrl.length() == 0)
        this.handler.sendEmptyMessage(0);
      NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
        this.isOnline = true;
      while (true)
      {
        this.webView = ((WebView)findViewById(R.id.romanblack_mapweb_route_webview));
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setGeolocationEnabled(true);
        this.webView.getSettings().setPluginsEnabled(true);
        this.webView.getSettings().setAllowFileAccess(true);
        this.webView.getSettings().setAppCacheEnabled(true);
        this.webView.getSettings().setCacheMode(-1);
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.clearHistory();
        this.webView.setScrollBarStyle(0);
        this.webView.setWebViewClient(new WebViewClient()
        {
          public void onPageFinished(WebView paramWebView, String paramString)
          {
            MapRoute.this.handler.sendEmptyMessage(3);
          }

          public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
          {
            if (MapRoute.this.progressDialog != null)
            {
              if (!MapRoute.this.progressDialog.isShowing())
                MapRoute.this.handler.sendEmptyMessage(2);
              return;
            }
            MapRoute.this.handler.sendEmptyMessage(2);
          }

          public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
          {
            return !paramString.contains("maps.google");
          }
        });
        this.webView.loadUrl(this.routeUrl);
        return;
        this.handler.sendEmptyMessage(1);
      }
    }
    catch (Exception localException)
    {
      logError("MapRoute.create()", localException);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MapPlugin.MapRoute
 * JD-Core Version:    0.6.0
 */