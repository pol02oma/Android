package com.ibuildapp.romanblack.NewsPlugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.MailTo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.AppBuilderModuleMain.NATIVE_FEATURES;
import com.appbuilder.sdk.android.Widget;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FeedDetails extends AppBuilderModuleMain
  implements View.OnClickListener
{
  private final int HIDE_PROGRESS = 1;
  private final int LOADING_ABORTED = 5;
  private final int NEED_INTERNET_CONNECTION = 2;
  private final int SHOW_PROGRESS = 0;
  private AlertDialog ad;
  private String currentUrl = "";
  private String func;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 3:
      case 4:
      default:
        return;
      case 0:
        FeedDetails.this.showProgress();
        return;
      case 1:
        FeedDetails.this.hideProgress();
        return;
      case 2:
        Toast.makeText(FeedDetails.this, R.string.romanblack_rss_alert_no_internet, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            FeedDetails.this.closeActivity();
          }
        }
        , 5000L);
        return;
      case 5:
      }
      FeedDetails.this.closeActivity();
    }
  };
  private boolean isOnline = false;
  private FeedItem item = null;
  private boolean needRefresh = false;
  private ProgressDialog progressDialog = null;
  private states state = states.EMPTY;
  private WebView webView = null;
  private Widget widget = null;

  private void hideProgress()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
    this.state = states.EMPTY;
  }

  public static String html2text(String paramString)
  {
    return Jsoup.parse(paramString).text();
  }

  private void showProgress()
  {
    if (this.state == states.LOAD_START)
      this.state = states.LOAD_PROGRESS;
    this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_rss_loading), true);
    this.progressDialog.setCancelable(true);
    this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        FeedDetails.this.handler.sendEmptyMessage(5);
      }
    });
  }

  void addToCalendar(Long paramLong, String paramString)
  {
    ContentResolver localContentResolver = getContentResolver();
    Uri.Builder localBuilder = Uri.parse("content://com.android.calendar/instances/when").buildUpon();
    Long localLong = Long.valueOf(new Date(paramLong.longValue()).getTime());
    ContentUris.appendId(localBuilder, localLong.longValue() - 600000L);
    ContentUris.appendId(localBuilder, 600000L + localLong.longValue());
    String[] arrayOfString = { "title", "begin" };
    Cursor localCursor = localContentResolver.query(localBuilder.build(), arrayOfString, null, null, null);
    int i = 0;
    if (localCursor != null)
      while (localCursor.moveToNext())
      {
        if ((localLong.longValue() != localCursor.getLong(1)) || (!paramString.equals(localCursor.getString(0))))
          continue;
        i = 1;
      }
    if (i == 0)
    {
      Intent localIntent = new Intent("android.intent.action.EDIT");
      localIntent.setType("vnd.android.cursor.item/event");
      localIntent.putExtra("beginTime", localLong);
      localIntent.putExtra("allDay", false);
      localIntent.putExtra("endTime", 3600000L + localLong.longValue());
      localIntent.putExtra("title", paramString);
      startActivity(localIntent);
      return;
    }
    Toast.makeText(this, "Event already exist!", 1).show();
  }

  public void closeActivity()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
    finish();
  }

  public void create()
  {
    setContentView(R.layout.romanblack_feed_details);
    setTopBarTitle(getString(R.string.romanblack_rss_html_page));
    Bundle localBundle = getIntent().getExtras();
    this.item = ((FeedItem)localBundle.getSerializable("item"));
    if (this.item == null)
      finish();
    this.widget = ((Widget)localBundle.getSerializable("Widget"));
    if ((this.widget != null) && (this.widget.getTitle().length() > 0))
      setTopBarTitle(this.widget.getTitle());
    setTopBarLeftButtonText(getString(R.string.rss_back_button), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FeedDetails.this.finish();
      }
    });
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
      this.isOnline = true;
    if (this.widget.hasParameter("add_event"))
    {
      HashMap localHashMap1 = new HashMap();
      localHashMap1.put("title", this.item.getTitle());
      localHashMap1.put("begin", this.item.getPubdate("dd MMM yyyy HH:mm"));
      localHashMap1.put("end", "");
      localHashMap1.put("frequency", "FREQ=ONCE");
      addNativeFeature(AppBuilderModuleMain.NATIVE_FEATURES.ADD_EVENT, null, localHashMap1);
    }
    if (this.widget.hasParameter("send_sms"))
    {
      HashMap localHashMap2 = new HashMap();
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(this.item.getTitle());
      localStringBuilder1.append(", ");
      if (this.item.getPubdate("").length() > 0)
      {
        localStringBuilder1.append(this.item.getPubdate(""));
        localStringBuilder1.append(", ");
      }
      localStringBuilder1.append(this.item.getAnounce(0));
      localHashMap2.put("text", localStringBuilder1.toString());
      addNativeFeature(AppBuilderModuleMain.NATIVE_FEATURES.SMS, null, localHashMap2);
    }
    if (this.widget.hasParameter("send_mail"))
    {
      HashMap localHashMap3 = new HashMap();
      localHashMap3.put("subject", this.item.getTitle());
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(this.item.getTitle());
      localStringBuilder2.append("<br>\n");
      if (this.item.getPubdate("").length() > 0)
      {
        localStringBuilder2.append("");
        localStringBuilder2.append(this.item.getPubdate(""));
        localStringBuilder2.append("<br>\n");
      }
      localStringBuilder2.append(this.item.getAnounce(0));
      if (this.widget.isHaveAdvertisement())
        localStringBuilder2.append("<br/><br/>\n (sent from <a href=\"http://ibuildapp.com\">iBuildApp</a>)");
      localHashMap3.put("text", localStringBuilder2.toString());
      addNativeFeature(AppBuilderModuleMain.NATIVE_FEATURES.EMAIL, null, localHashMap3);
    }
    this.func = localBundle.getString("func");
    this.webView = ((WebView)findViewById(R.id.romanblack_rss_feedDetails));
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.getSettings().setPluginsEnabled(true);
    this.webView.getSettings().setBuiltInZoomControls(true);
    this.webView.getSettings().setPluginsEnabled(true);
    this.webView.getSettings().setLoadsImagesAutomatically(true);
    this.webView.clearHistory();
    this.webView.setWebViewClient(new WebViewClient()
    {
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
              FeedDetails.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com")).setData(Uri.parse("http://www.youtube.com/watch?v=" + str1)));
              FeedDetails.access$502(FeedDetails.this, true);
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
        FeedDetails.access$202(FeedDetails.this, FeedDetails.states.LOAD_COMPLETE);
        FeedDetails.this.handler.sendEmptyMessage(1);
      }

      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        if (FeedDetails.this.state == FeedDetails.states.EMPTY)
        {
          FeedDetails.access$302(FeedDetails.this, paramString);
          FeedDetails.this.setSession(FeedDetails.this.currentUrl);
          FeedDetails.access$202(FeedDetails.this, FeedDetails.states.LOAD_START);
          FeedDetails.this.handler.sendEmptyMessage(0);
        }
      }

      public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
      {
      }

      public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
      {
        if (paramString.contains("youtube.com"))
          try
          {
            FeedDetails.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com")).setData(Uri.parse(paramString)));
            return true;
          }
          catch (Exception localException)
          {
            return false;
          }
        if (paramString.contains("mailto"))
        {
          MailTo localMailTo = MailTo.parse(paramString);
          Intent localIntent = new Intent("android.intent.action.SEND");
          localIntent.setType("plain/text");
          String[] arrayOfString = new String[1];
          arrayOfString[0] = localMailTo.getTo();
          localIntent.putExtra("android.intent.extra.EMAIL", arrayOfString);
          localIntent.putExtra("android.intent.extra.SUBJECT", localMailTo.getSubject());
          localIntent.putExtra("android.intent.extra.TEXT", localMailTo.getBody());
          FeedDetails.this.startActivity(Intent.createChooser(localIntent, FeedDetails.this.getString(R.string.romanblack_rss_send_email)));
          return super.shouldOverrideUrlLoading(paramWebView, paramString);
        }
        FeedDetails.access$302(FeedDetails.this, paramString);
        FeedDetails.this.setSession(FeedDetails.this.currentUrl);
        if (!FeedDetails.this.isOnline)
        {
          FeedDetails.this.handler.sendEmptyMessage(1);
          FeedDetails.this.handler.sendEmptyMessage(2);
        }
        while (true)
        {
          return false;
          paramWebView.getSettings().setLoadWithOverviewMode(true);
          paramWebView.getSettings().setUseWideViewPort(true);
          paramWebView.setBackgroundColor(-1);
        }
      }
    });
    this.webView.setWebChromeClient(new WebChromeClient()
    {
    });
    this.currentUrl = ((String)getSession());
    if (this.currentUrl == null)
      this.currentUrl = "";
    if ((this.currentUrl.length() > 0) && (!this.currentUrl.equals("about:blank")))
    {
      this.webView.loadUrl(this.currentUrl);
      this.handler.sendEmptyMessageDelayed(1, 10000L);
      return;
    }
    StringBuilder localStringBuilder3 = new StringBuilder();
    localStringBuilder3.append("<html>");
    localStringBuilder3.append("<body>");
    localStringBuilder3.append("<font size=\"4\"><b>");
    localStringBuilder3.append(this.item.getTitle());
    localStringBuilder3.append("</b></font><br/>");
    localStringBuilder3.append("<font size=\"2\" color=\"#5A5A5A\">");
    SimpleDateFormat localSimpleDateFormat;
    if (Locale.getDefault().toString().equals("en_US"))
    {
      localSimpleDateFormat = new SimpleDateFormat("MMMM dd yyyy hh:mm");
      if (this.item.getPubdate() != null)
      {
        if (this.widget.getDateFormat() != 0)
          break label1142;
        localStringBuilder3.append(localSimpleDateFormat.format(this.item.getPubdate()));
      }
    }
    while (true)
    {
      localStringBuilder3.append("</font>");
      localStringBuilder3.append("<br/><br/>");
      if ((this.item.hasImage()) && (!this.item.isDescriptionContainsImages()))
      {
        localStringBuilder3.append("<img src=\"");
        localStringBuilder3.append(this.item.getImageUrl());
        localStringBuilder3.append("\"/ width=\"100%\">");
        localStringBuilder3.append("<br/><br/>");
      }
      localStringBuilder3.append(this.item.getDescription());
      if (this.item.getLink().length() > 0)
      {
        localStringBuilder3.append("<br><br><a href=\"");
        localStringBuilder3.append(this.item.getLink());
        localStringBuilder3.append("\"><strong>" + getString(R.string.romanblack_rss_read_more) + "...</strong></a>");
      }
      if (this.item.hasMedia())
      {
        localStringBuilder3.append("<br/><a href=\"\" onClick=\"window.jsi.clickOnAndroid()\">");
        localStringBuilder3.append(getString(R.string.romanblack_rss_show_media) + "...");
        localStringBuilder3.append("</a>");
      }
      localStringBuilder3.append("</body>");
      localStringBuilder3.append("</html>");
      String str = localStringBuilder3.toString().replace("img src=\"//", "img src=\"http://");
      this.webView.loadDataWithBaseURL(null, str, "text/html", "UTF-8", null);
      return;
      localSimpleDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
      break;
      label1142: localStringBuilder3.append(localSimpleDateFormat.format(this.item.getPubdate()));
    }
  }

  public void destroy()
  {
    this.webView.stopLoading();
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.menu_button_add_to_calendar)
    {
      addToCalendar(Long.valueOf(this.item.getPubdate().getTime()), this.item.getTitle());
      this.ad.cancel();
    }
    do
    {
      return;
      if (paramView.getId() == R.id.menu_button_share_via_email)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        if (Locale.getDefault().toString().equals("en_US"));
        for (SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MMMM dd yyyy hh:mm"); ; localSimpleDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm"))
        {
          localStringBuilder.append(localSimpleDateFormat.format(this.item.getPubdate()));
          localStringBuilder.append("\n");
          localStringBuilder.append(html2text(this.item.getDescription()));
          Intent localIntent1 = new Intent("android.intent.action.SEND");
          localIntent1.putExtra("android.intent.extra.SUBJECT", this.item.getTitle());
          localIntent1.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
          localIntent1.setType("message/rfc822");
          startActivity(Intent.createChooser(localIntent1, "Choose an Email client"));
          this.ad.cancel();
          return;
        }
      }
      if (paramView.getId() != R.id.menu_button_share_via_sms)
        continue;
      Intent localIntent2 = new Intent("android.intent.action.VIEW");
      String str = this.item.getTitle();
      localIntent2.putExtra("sms_body", str + "\n" + html2text(this.item.getDescription()));
      localIntent2.putExtra("address", "");
      localIntent2.setType("vnd.android-dir/mms-sms");
      startActivity(localIntent2);
      this.ad.cancel();
      return;
    }
    while (paramView.getId() != R.id.menu_button_cancel);
    this.ad.cancel();
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    LinearLayout localLinearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.news_sharing_dialog, null);
    Button localButton1 = (Button)localLinearLayout.findViewById(R.id.menu_button_add_to_calendar);
    Button localButton2 = (Button)localLinearLayout.findViewById(R.id.menu_button_share_via_email);
    Button localButton3 = (Button)localLinearLayout.findViewById(R.id.menu_button_share_via_sms);
    Button localButton4 = (Button)localLinearLayout.findViewById(R.id.menu_button_cancel);
    if ((!this.func.equals("events")) || (!this.widget.hasParameter("add_event")))
      localButton1.setVisibility(8);
    if (!this.widget.hasParameter("send_sms"))
      localButton3.setVisibility(8);
    if (!this.widget.hasParameter("send_mail"))
      localButton2.setVisibility(8);
    localButton1.setOnClickListener(this);
    localButton2.setOnClickListener(this);
    localButton3.setOnClickListener(this);
    localButton4.setOnClickListener(this);
    localBuilder.setView(localLinearLayout);
    this.ad = localBuilder.create();
    if ((this.widget.hasParameter("send_mail")) || (this.widget.hasParameter("send_sms")) || ((this.func.equals("events")) && (this.widget.hasParameter("add_event"))))
      this.ad.show();
    return true;
  }

  public void pause()
  {
    this.webView.stopLoading();
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  public void resume()
  {
    StringBuilder localStringBuilder;
    if (this.currentUrl.equals("about:blank"))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("<html>");
      localStringBuilder.append("<body>");
      localStringBuilder.append("<font size=\"4\"><b>");
      localStringBuilder.append(this.item.getTitle());
      localStringBuilder.append("</b></font><br/>");
      localStringBuilder.append("<font size=\"2\" color=\"#5A5A5A\">");
      if (this.widget.getDateFormat() == 0)
      {
        localStringBuilder.append(this.item.getPubdate("MMM d yy hh:mm a"));
        localStringBuilder.append("</font>");
        localStringBuilder.append("<br/><br/>");
        if ((this.item.hasImage()) && (!this.item.isDescriptionContainsImages()))
        {
          localStringBuilder.append("<img src=\"");
          localStringBuilder.append(this.item.getImageUrl());
          localStringBuilder.append("\"/ width=\"100%\">");
          localStringBuilder.append("<br/><br/>");
        }
        localStringBuilder.append(this.item.getDescription());
        if (this.item.getLink().length() > 0)
        {
          localStringBuilder.append("<br><br><a href=\"");
          localStringBuilder.append(this.item.getLink());
          localStringBuilder.append("\"><strong>" + getString(R.string.romanblack_rss_read_more) + "...</strong></a>");
        }
        if (this.item.hasMedia())
        {
          localStringBuilder.append("<br/><a href=\"\" onClick=\"window.jsi.clickOnAndroid()\">");
          localStringBuilder.append(getString(R.string.romanblack_rss_show_media) + "...");
          localStringBuilder.append("</a>");
        }
        localStringBuilder.append("</body>");
        localStringBuilder.append("</html>");
        String str = localStringBuilder.toString().replace("img src=\"//", "img src=\"http://");
        this.webView.loadDataWithBaseURL(null, str, "text/html", "UTF-8", null);
      }
    }
    do
    {
      return;
      localStringBuilder.append(this.item.getPubdate("MMM d yy HH:mm"));
      break;
    }
    while (!this.needRefresh);
    this.webView.reload();
    this.needRefresh = false;
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
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.FeedDetails
 * JD-Core Version:    0.6.0
 */