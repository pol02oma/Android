package com.ibuildapp.romanblack.TableReservationPlugin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
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

public class TableReservationMapRoute extends AppBuilderModule
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
        Toast.makeText(TableReservationMapRoute.this, R.string.alert_gps_wrong_route, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            TableReservationMapRoute.this.finish();
          }
        }
        , 5000L);
        return;
      case 1:
        Toast.makeText(TableReservationMapRoute.this, R.string.alert_no_internet, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            TableReservationMapRoute.this.finish();
          }
        }
        , 5000L);
        return;
      case 2:
        TableReservationMapRoute.this.showProgress();
        return;
      case 3:
        TableReservationMapRoute.this.hideProgress();
        return;
      case 5:
      }
      TableReservationMapRoute.this.closeActivity();
    }
  };
  private ProgressDialog progressDialog = null;
  private String routeUrl = "";
  private WebView webView = null;

  private void hideProgress()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void showProgress()
  {
    this.progressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.common_loading_upper), true);
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
      setContentView(R.layout.sergeyb_tablereservation_mapweb_route);
      setTitle("Route");
      this.routeUrl = getIntent().getExtras().getString("url");
      if (this.routeUrl.length() == 0)
        this.handler.sendEmptyMessage(0);
      NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()));
      while (true)
      {
        this.webView = ((WebView)findViewById(R.id.sergeyb_tablereservation_mapweb_route_webview));
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
            TableReservationMapRoute.this.handler.sendEmptyMessage(3);
          }

          public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
          {
            if (TableReservationMapRoute.this.progressDialog != null)
            {
              if (!TableReservationMapRoute.this.progressDialog.isShowing())
                TableReservationMapRoute.this.handler.sendEmptyMessage(2);
              return;
            }
            TableReservationMapRoute.this.handler.sendEmptyMessage(2);
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
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationMapRoute
 * JD-Core Version:    0.6.0
 */