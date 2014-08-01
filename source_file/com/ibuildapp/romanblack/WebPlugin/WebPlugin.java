package com.ibuildapp.romanblack.WebPlugin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.MailTo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Widget;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebPlugin extends AppBuilderModuleMain
{
  private final int DOWNLOAD_REQUEST_CODE = 1000;
  private final int DOWNLOAD_REQUEST_CODE_WITHOUT_START = 1001;
  private final int FILECHOOSER_RESULTCODE = 10002;
  private final int HIDE_PROGRESS = 6;
  private final int INITIALIZATION_FAILED = 3;
  private final int LOADING_ABORTED = 7;
  private final int NEED_INTERNET_CONNECTION = 4;
  private final int SHOW_HTML = 0;
  private final int SHOW_PROGRESS = 5;
  private final int STOP_LOADING = 1;
  private String appName;
  private String currentUrl = "";
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 2:
      default:
        return;
      case 3:
        Toast.makeText(WebPlugin.this, R.string.romanblack_html_cannot_init, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            WebPlugin.this.hideProgress();
            WebPlugin.this.finish();
          }
        }
        , 5000L);
        return;
      case 4:
        if (WebPlugin.this.progressDialog != null)
          WebPlugin.this.progressDialog.dismiss();
        Toast.makeText(WebPlugin.this, R.string.romanblack_html_alert_no_internet, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            WebPlugin.this.hideProgress();
            WebPlugin.this.finish();
          }
        }
        , 5000L);
        return;
      case 0:
        WebPlugin.this.showHtml();
        return;
      case 1:
        WebPlugin.this.progressDialog.dismiss();
        Toast.makeText(WebPlugin.this, R.string.romanblack_html_alert_no_internet, 1).show();
        WebPlugin.this.webView.stopLoading();
        return;
      case 5:
        WebPlugin.this.showProgress();
        return;
      case 6:
        WebPlugin.this.hideProgress();
        return;
      case 7:
      }
      WebPlugin.this.closeActivity();
    }
  };
  private String html = "";
  private boolean isOnline = false;
  private ValueCallback<Uri> mUploadMessage;
  private boolean needRefresh = false;
  LinearLayout panel = null;
  private ProgressDialog progressDialog = null;
  private states state = states.EMPTY;
  private String url = "";
  private ObservableWebView webView = null;
  private Widget widget = null;

  private String getPageType(String paramString)
  {
    Object localObject = "page";
    try
    {
      HttpEntity localHttpEntity = new DefaultHttpClient().execute(new HttpGet(paramString)).getEntity();
      try
      {
        String str = localHttpEntity.getContentType().getValue();
        localObject = str;
        Log.w("", "");
        return localObject;
      }
      catch (NullPointerException localNullPointerException)
      {
        while (true)
          Log.d("", "");
      }
    }
    catch (IOException localIOException)
    {
      return localObject;
    }
    catch (Exception localException1)
    {
      try
      {
        Log.e("", localException1.getMessage());
        return localObject;
      }
      catch (Exception localException2)
      {
      }
    }
    return (String)null;
  }

  private void hideProgress()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
    this.state = states.EMPTY;
  }

  // ERROR //
  private String readXmlFromFile(String paramString)
  {
    // Byte code:
    //   0: new 212	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 213	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: new 215	java/io/BufferedReader
    //   11: dup
    //   12: new 217	java/io/FileReader
    //   15: dup
    //   16: new 219	java/io/File
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 220	java/io/File:<init>	(Ljava/lang/String;)V
    //   24: invokespecial 223	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   27: invokespecial 226	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   30: astore_3
    //   31: aload_3
    //   32: invokevirtual 229	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   35: astore 6
    //   37: aload 6
    //   39: ifnull +20 -> 59
    //   42: aload_2
    //   43: aload 6
    //   45: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: goto -18 -> 31
    //   52: astore 5
    //   54: aload_2
    //   55: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: areturn
    //   59: goto -5 -> 54
    //   62: astore 9
    //   64: goto -10 -> 54
    //   67: astore 4
    //   69: goto -15 -> 54
    //   72: astore 8
    //   74: goto -20 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   31	37	52	java/io/FileNotFoundException
    //   42	49	52	java/io/FileNotFoundException
    //   8	31	62	java/io/IOException
    //   31	37	67	java/io/IOException
    //   42	49	67	java/io/IOException
    //   8	31	72	java/io/FileNotFoundException
  }

  private void showHtml()
  {
    try
    {
      if (this.isOnline)
      {
        if ((this.currentUrl.length() > 0) && (!this.currentUrl.equals("about:blank")))
          this.url = this.currentUrl;
        if (this.url.length() > 0)
        {
          this.webView.getSettings().setLoadWithOverviewMode(true);
          this.webView.getSettings().setUseWideViewPort(true);
          String str2 = getPageType(this.url);
          if ((str2.contains("application")) && (!str2.contains("html")) && (!str2.contains("xml")))
            startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(this.url)), 1000);
        }
      }
      while (true)
      {
        this.handler.sendEmptyMessageDelayed(6, 10000L);
        return;
        this.webView.loadUrl(this.url);
        continue;
        Document localDocument = Jsoup.parse(this.html);
        Element localElement1 = localDocument.select("iframe").first();
        Object localObject = "";
        if (localElement1 != null);
        try
        {
          String str1 = localElement1.attr("src");
          localObject = str1;
          label207: int i = ((String)localObject).length();
          boolean bool1 = false;
          boolean bool2 = false;
          if (i > 0)
          {
            bool1 = ((String)localObject).contains("www.google.com/calendar");
            bool2 = ((String)localObject).contains("google.com/forms");
          }
          if (bool1)
          {
            this.webView.loadUrl((String)localObject);
            continue;
          }
          if (bool2)
          {
            this.webView.getSettings().setBuiltInZoomControls(false);
            DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
            int j = localDisplayMetrics.widthPixels;
            int k = localDisplayMetrics.heightPixels;
            float f = localDisplayMetrics.density;
            localElement1.attr("width", (int)(j / f) + "");
            localElement1.attr("height", (int)(k / f - 75.0F) + "");
            localElement1.attr("style", "margin: 0; padding: 0");
            localDocument.select("body").first().attr("style", "margin: 0; padding: 0");
            this.html = localDocument.outerHtml();
            this.webView.loadDataWithBaseURL("http://", this.html, "text/html", "utf-8", "");
            continue;
          }
          Iterator localIterator = localDocument.select("form").iterator();
          while (localIterator.hasNext())
          {
            Element localElement2 = (Element)localIterator.next();
            if (localElement2.attr("action").contains("paypal.com"))
              localElement2.append("<input type=\"hidden\" name=\"bn\" value=\"ibuildapp_SP\">");
            this.html = localDocument.html();
          }
          this.webView.loadDataWithBaseURL("http://", this.html, "text/html", "utf-8", "");
          continue;
          if (this.html.length() <= 0)
            continue;
          this.webView.loadDataWithBaseURL("http://", this.html, "text/html", "utf-8", "");
        }
        catch (Exception localException2)
        {
          break label207;
        }
      }
    }
    catch (Exception localException1)
    {
    }
  }

  private void showProgress()
  {
    if (this.state == states.LOAD_START)
      this.state = states.LOAD_PROGRESS;
    this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_html_loading), true);
    this.progressDialog.setCancelable(true);
    this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        WebPlugin.this.handler.sendEmptyMessage(7);
      }
    });
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
      setContentView(R.layout.romanblack_html_main);
      RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.romanblack_root_layout);
      this.webView = new ObservableWebView(this);
      this.webView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
      localRelativeLayout.addView(this.webView);
      this.webView.setHorizontalScrollBarEnabled(false);
      setTitle("HTML");
      Intent localIntent = getIntent();
      this.widget = ((Widget)localIntent.getExtras().getSerializable("Widget"));
      if (this.widget == null)
      {
        this.handler.sendEmptyMessageDelayed(3, 100L);
        return;
      }
      this.appName = this.widget.getAppName();
      if ((this.widget.getPluginXmlData().length() == 0) && (localIntent.getStringExtra("WidgetFile").length() == 0))
      {
        this.handler.sendEmptyMessageDelayed(3, 100L);
        return;
      }
      if ((this.widget.getTitle() != null) && (this.widget.getTitle().length() > 0))
        setTopBarTitle(this.widget.getTitle());
      while (true)
      {
        this.currentUrl = ((String)getSession());
        if (this.currentUrl == null)
          this.currentUrl = "";
        NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
          this.isOnline = true;
        if (!((Boolean)getIntent().getExtras().getSerializable("showSideBar")).booleanValue())
          setTopBarLeftButtonText(getString(R.string.common_home_upper), true, new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              WebPlugin.this.finish();
            }
          });
        if (this.isOnline)
          this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setScrollBarStyle(33554432);
        this.webView.getSettings().setGeolocationEnabled(true);
        this.webView.getSettings().setAllowFileAccess(true);
        this.webView.getSettings().setAppCacheEnabled(true);
        this.webView.getSettings().setCacheMode(-1);
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setUseWideViewPort(false);
        this.webView.getSettings().setSavePassword(false);
        this.webView.clearHistory();
        this.webView.invalidate();
        this.webView.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
          {
            paramView.invalidate();
            switch (paramMotionEvent.getAction())
            {
            case 0:
            default:
            case 1:
            }
            while (true)
            {
              return false;
              if (paramView.hasFocus())
                continue;
              paramView.requestFocus();
            }
          }
        });
        this.webView.setBackgroundColor(-1);
        try
        {
          if (this.widget.getBackgroundColor() != 0)
            this.webView.setBackgroundColor(this.widget.getBackgroundColor());
          label471: this.webView.setDownloadListener(new DownloadListener()
          {
            public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
            {
              Intent localIntent = new Intent("android.intent.action.VIEW");
              localIntent.setData(Uri.parse(paramString1));
              WebPlugin.this.startActivity(localIntent);
            }
          });
          this.webView.setWebChromeClient(new WebChromeClient()
          {
            public void openFileChooser(ValueCallback<Uri> paramValueCallback)
            {
              WebPlugin.access$502(WebPlugin.this, paramValueCallback);
              Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
              localIntent.addCategory("android.intent.category.OPENABLE");
              localIntent.setType("image/*");
              WebPlugin.this.startActivityForResult(Intent.createChooser(localIntent, "File Chooser"), 10002);
            }

            public void openFileChooser(ValueCallback paramValueCallback, String paramString)
            {
              WebPlugin.access$502(WebPlugin.this, paramValueCallback);
              Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
              localIntent.addCategory("android.intent.category.OPENABLE");
              localIntent.setType("*/*");
              WebPlugin.this.startActivityForResult(Intent.createChooser(localIntent, "File Browser"), 10002);
            }

            public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString1, String paramString2)
            {
              WebPlugin.access$502(WebPlugin.this, paramValueCallback);
              Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
              localIntent.addCategory("android.intent.category.OPENABLE");
              localIntent.setType("image/*");
              WebPlugin.this.startActivityForResult(Intent.createChooser(localIntent, "File Chooser"), 10002);
            }
          });
          this.webView.setWebViewClient(new WebViewClient()
          {
            public void onFormResubmission(WebView paramWebView, Message paramMessage1, Message paramMessage2)
            {
              super.onFormResubmission(paramWebView, paramMessage1, paramMessage2);
            }

            public void onLoadResource(WebView paramWebView, String paramString)
            {
              if (paramString.startsWith("http://www.youtube.com/get_video_info?"));
              try
              {
                String[] arrayOfString = paramString.replace("http://www.youtube.com/get_video_info?", "").split("&");
                int i = arrayOfString.length;
                for (int j = 0; ; j++)
                {
                  String str1 = null;
                  if (j < i)
                  {
                    String str2 = arrayOfString[j];
                    if (!str2.startsWith("video_id"))
                      continue;
                    str1 = str2.split("=")[1];
                  }
                  if (str1 != null)
                  {
                    WebPlugin.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com")).setData(Uri.parse("http://www.youtube.com/watch?v=" + str1)));
                    WebPlugin.access$902(WebPlugin.this, true);
                  }
                  return;
                }
                super.onLoadResource(paramWebView, paramString);
                return;
              }
              catch (Exception localException)
              {
              }
            }

            public void onPageFinished(WebView paramWebView, String paramString)
            {
              WebPlugin.access$602(WebPlugin.this, WebPlugin.states.LOAD_COMPLETE);
              WebPlugin.this.handler.sendEmptyMessage(6);
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = WebPlugin.access$1000(WebPlugin.this);
              paramWebView.loadUrl(String.format("javascript:ibuildapp_getAppName('%s')", arrayOfObject));
              super.onPageFinished(paramWebView, paramString);
            }

            public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
            {
              super.onPageStarted(paramWebView, paramString, paramBitmap);
              if (WebPlugin.this.state == WebPlugin.states.EMPTY)
              {
                WebPlugin.access$702(WebPlugin.this, paramString);
                WebPlugin.this.setSession(WebPlugin.this.currentUrl);
                WebPlugin.access$602(WebPlugin.this, WebPlugin.states.LOAD_START);
                if (!WebPlugin.this.progressDialog.isShowing())
                  WebPlugin.this.handler.sendEmptyMessage(5);
              }
            }

            public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
            {
              if (paramInt == -12)
                WebPlugin.this.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(WebPlugin.this.url)), 1000);
            }

            public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
            {
              try
              {
                boolean bool1 = paramString.contains("youtube.com");
                if (bool1)
                  try
                  {
                    WebPlugin.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com")).setData(Uri.parse(paramString)));
                    return true;
                  }
                  catch (Exception localException3)
                  {
                    return false;
                  }
                if (paramString.contains("paypal.com"))
                {
                  if (paramString.contains("&bn=ibuildapp_SP"))
                    return false;
                  String str = paramString + "&bn=ibuildapp_SP";
                  WebPlugin.this.webView.loadUrl(str);
                  return true;
                }
                boolean bool2 = paramString.contains("sms:");
                if (bool2)
                  try
                  {
                    Intent localIntent4 = new Intent("android.intent.action.VIEW");
                    localIntent4.setData(Uri.parse(paramString));
                    WebPlugin.this.startActivity(localIntent4);
                    return true;
                  }
                  catch (Exception localException2)
                  {
                    Log.e("", localException2.getMessage());
                    return false;
                  }
                if (paramString.contains("tel:"))
                {
                  Intent localIntent1 = new Intent("android.intent.action.CALL");
                  localIntent1.setData(Uri.parse(paramString));
                  WebPlugin.this.startActivity(localIntent1);
                  return true;
                }
                if (paramString.contains("mailto:"))
                {
                  MailTo localMailTo = MailTo.parse(paramString);
                  Intent localIntent3 = new Intent("android.intent.action.SEND");
                  localIntent3.setType("plain/text");
                  String[] arrayOfString = new String[1];
                  arrayOfString[0] = localMailTo.getTo();
                  localIntent3.putExtra("android.intent.extra.EMAIL", arrayOfString);
                  localIntent3.putExtra("android.intent.extra.SUBJECT", localMailTo.getSubject());
                  localIntent3.putExtra("android.intent.extra.TEXT", localMailTo.getBody());
                  WebPlugin.this.startActivity(Intent.createChooser(localIntent3, WebPlugin.this.getString(R.string.romanblack_html_send_email)));
                  return true;
                }
                if (paramString.contains("rtsp:"))
                {
                  Intent localIntent2 = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
                  if (WebPlugin.this.getPackageManager().queryIntentActivities(localIntent2, 0).size() > 0)
                    WebPlugin.this.startActivity(localIntent2);
                  else
                    Toast.makeText(WebPlugin.this, WebPlugin.this.getString(R.string.romanblack_html_no_video_player), 0).show();
                }
                else
                {
                  WebPlugin.access$702(WebPlugin.this, paramString);
                  WebPlugin.this.setSession(WebPlugin.this.currentUrl);
                  if (!WebPlugin.this.isOnline)
                  {
                    WebPlugin.this.handler.sendEmptyMessage(6);
                    WebPlugin.this.handler.sendEmptyMessage(1);
                    break label569;
                  }
                  if (!paramString.contains("vk.com"))
                    WebPlugin.this.getPageType(paramString);
                  if (("application/html".contains("application")) && (!"application/html".contains("html")) && (!"application/html".contains("xml")))
                  {
                    WebPlugin.this.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(paramString)), 1000);
                    return super.shouldOverrideUrlLoading(paramWebView, paramString);
                  }
                  paramWebView.getSettings().setLoadWithOverviewMode(true);
                  paramWebView.getSettings().setUseWideViewPort(true);
                  paramWebView.setBackgroundColor(-1);
                  break label569;
                }
                return true;
              }
              catch (Exception localException1)
              {
                return false;
              }
              label569: return false;
            }
          });
          this.handler.sendEmptyMessage(5);
          new Thread()
          {
            public void run()
            {
              EntityParser localEntityParser;
              if (WebPlugin.this.widget.getPluginXmlData() != null)
                if (WebPlugin.this.widget.getPluginXmlData().length() > 0)
                  localEntityParser = new EntityParser(WebPlugin.this.widget.getPluginXmlData());
              while (true)
              {
                localEntityParser.parse();
                WebPlugin.access$1102(WebPlugin.this, localEntityParser.getUrl());
                WebPlugin.access$1602(WebPlugin.this, localEntityParser.getHtml());
                if ((WebPlugin.this.url.length() <= 0) || (WebPlugin.this.isOnline))
                  break;
                WebPlugin.this.handler.sendEmptyMessage(4);
                return;
                localEntityParser = new EntityParser(WebPlugin.this.readXmlFromFile(WebPlugin.this.getIntent().getStringExtra("WidgetFile")));
                continue;
                localEntityParser = new EntityParser(WebPlugin.this.readXmlFromFile(WebPlugin.this.getIntent().getStringExtra("WidgetFile")));
              }
              if (WebPlugin.this.isOnline);
              while ((WebPlugin.this.html.length() > 0) || (WebPlugin.this.url.length() > 0))
              {
                WebPlugin.this.handler.sendEmptyMessageDelayed(0, 700L);
                return;
                if (WebPlugin.this.html.length() != 0)
                  continue;
              }
              if (WebPlugin.this.progressDialog != null)
                WebPlugin.this.progressDialog.dismiss();
              WebPlugin.this.handler.sendEmptyMessage(3);
            }
          }
          .start();
          return;
          setTopBarTitle(getResources().getString(R.string.romanblack_html_web));
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          break label471;
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void destroy()
  {
    this.webView.stopLoading();
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
    monitorenter;
    try
    {
      this.webView.setWebViewClient(null);
      this.webView.loadData("", "text/html", "utf-8");
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    case 1000:
    default:
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    case 1001:
    case 10002:
    }
    do
    {
      return;
      finish();
      break;
    }
    while (this.mUploadMessage == null);
    if ((paramIntent == null) || (paramInt2 != -1));
    for (Uri localUri1 = null; ; localUri1 = paramIntent.getData())
    {
      Uri localUri2 = Uri.fromFile(new File(localUri1.getPath()));
      this.mUploadMessage.onReceiveValue(localUri2);
      this.mUploadMessage = null;
      break;
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    try
    {
      paramMenu.clear();
      MenuItem localMenuItem1 = paramMenu.add("");
      localMenuItem1.setIcon(R.drawable.romanblack_html_menu_back);
      localMenuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
      {
        public boolean onMenuItemClick(MenuItem paramMenuItem)
        {
          WebPlugin.this.webView.goBack();
          return true;
        }
      });
      if (!this.webView.canGoBack())
        localMenuItem1.setEnabled(false);
      MenuItem localMenuItem2 = paramMenu.add("");
      localMenuItem2.setIcon(R.drawable.romanblack_html_menu_refresh);
      localMenuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
      {
        public boolean onMenuItemClick(MenuItem paramMenuItem)
        {
          WebPlugin.this.webView.reload();
          return true;
        }
      });
      if (("".equals(this.currentUrl)) || ("about:blank".equals(this.currentUrl)))
        localMenuItem2.setEnabled(false);
      MenuItem localMenuItem3 = paramMenu.add("");
      localMenuItem3.setIcon(R.drawable.romanblack_html_menu_forward);
      localMenuItem3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
      {
        public boolean onMenuItemClick(MenuItem paramMenuItem)
        {
          WebPlugin.this.webView.goForward();
          return true;
        }
      });
      if (!this.webView.canGoForward())
        localMenuItem3.setEnabled(false);
      return true;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public void pause()
  {
    this.webView.stopLoading();
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  public void resume()
  {
    super.resume();
    if (this.needRefresh)
    {
      this.webView.reload();
      this.needRefresh = false;
    }
  }

  private static enum states
  {
    static
    {
      LOAD_PROGRESS = new states("LOAD_PROGRESS", 2);
      LOAD_COMPLETE = new states("LOAD_COMPLETE", 3);
      states[] arrayOfstates = new states[4];
      arrayOfstates[0] = EMPTY;
      arrayOfstates[1] = LOAD_START;
      arrayOfstates[2] = LOAD_PROGRESS;
      arrayOfstates[3] = LOAD_COMPLETE;
      $VALUES = arrayOfstates;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.WebPlugin.WebPlugin
 * JD-Core Version:    0.6.0
 */