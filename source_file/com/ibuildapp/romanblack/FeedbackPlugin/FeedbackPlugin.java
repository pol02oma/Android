package com.ibuildapp.romanblack.FeedbackPlugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Widget;
import java.util.ArrayList;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FeedbackPlugin extends AppBuilderModuleMain
{
  private static final int CLOSE_ALL = 4;
  private static final int HIDE_PROGRESS_DIALOG = 3;
  private static final int INITIALIZATION_FAILED = 0;
  private static final int SEND = 1;
  private static final int SHOW_DIALOG = 5;
  private static final int SHOW_PROGRESS_DIALOG = 2;
  private String appid = null;
  private String endpoint = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
      while (true)
      {
        super.handleMessage(paramMessage);
        return;
        Toast.makeText(FeedbackPlugin.this, FeedbackPlugin.this.getResources().getIdentifier("alert_cannot_init", "string", FeedbackPlugin.this.getPackageName()), 1).show();
        FeedbackPlugin.this.finish();
        continue;
        FeedbackPlugin.this.send();
        continue;
        if (FeedbackPlugin.this.progressDialog != null)
        {
          FeedbackPlugin.this.progressDialog.dismiss();
          FeedbackPlugin.access$102(FeedbackPlugin.this, null);
        }
        FeedbackPlugin.access$102(FeedbackPlugin.this, ProgressDialog.show(FeedbackPlugin.this, "", FeedbackPlugin.this.getResources().getString(FeedbackPlugin.this.getResources().getIdentifier("load", "string", FeedbackPlugin.this.getPackageName()))));
        continue;
        if (FeedbackPlugin.this.progressDialog == null)
          continue;
        FeedbackPlugin.this.progressDialog.dismiss();
        FeedbackPlugin.access$102(FeedbackPlugin.this, null);
        continue;
        FeedbackPlugin.this.finish();
        continue;
        FeedbackPlugin.this.showDialog();
      }
    }
  };
  private ProgressDialog progressDialog = null;
  private EditText sendEditText = null;
  private Widget widget = null;

  private void send()
  {
    if (this.sendEditText.getText().length() == 0)
    {
      Toast.makeText(this, getResources().getIdentifier("feedback_type_message", "string", getPackageName()), 1).show();
      return;
    }
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
    {
      this.handler.sendEmptyMessage(2);
      ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.sendEditText.getWindowToken(), 0);
      new Thread()
      {
        public void run()
        {
          BasicHttpParams localBasicHttpParams = new BasicHttpParams();
          localBasicHttpParams.setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
          DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
          try
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(FeedbackPlugin.this.endpoint);
            localStringBuilder.append("/");
            HttpPost localHttpPost = new HttpPost(localStringBuilder.toString());
            ArrayList localArrayList = new ArrayList();
            localArrayList.add(new BasicNameValuePair("app_id", FeedbackPlugin.this.appid));
            localArrayList.add(new BasicNameValuePair("message", FeedbackPlugin.this.sendEditText.getText().toString()));
            localArrayList.add(new BasicNameValuePair("platform", "android"));
            localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "utf-8"));
            ((String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler()));
            com.appbuilder.sdk.android.Statics.closeMain = true;
            FeedbackPlugin.this.handler.sendEmptyMessage(3);
            FeedbackPlugin.this.handler.sendEmptyMessage(5);
            return;
          }
          catch (Exception localException)
          {
            FeedbackPlugin.this.handler.sendEmptyMessage(3);
            Log.d("", "");
          }
        }
      }
      .start();
      return;
    }
    Toast.makeText(this, getResources().getIdentifier("need_internet_connection", "string", getPackageName()), 1).show();
  }

  private void showDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(getString(getResources().getIdentifier("feedback_alert_send_ok", "string", getPackageName())));
    localBuilder.setPositiveButton(getString(getResources().getIdentifier("ok", "string", getPackageName())), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        FeedbackPlugin.this.finish();
      }
    });
    localBuilder.create().show();
  }

  public void create()
  {
    setContentView(getResources().getIdentifier("feedback_main", "layout", getPackageName()));
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    Intent localIntent;
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
    {
      localIntent = getIntent();
      this.widget = ((Widget)localIntent.getExtras().getSerializable("Widget"));
      if (this.widget != null)
        break label111;
      this.handler.sendEmptyMessage(0);
    }
    label111: 
    do
    {
      return;
      Toast.makeText(this, getResources().getIdentifier("need_internet_connection", "string", getPackageName()), 1).show();
      finish();
      break;
      if (this.widget.getTitle().length() > 0)
        setTitle(this.widget.getTitle());
      try
      {
        if ((this.widget.getPluginXmlData().length() == 0) && (localIntent.getStringExtra("WidgetFile").length() == 0))
        {
          this.handler.sendEmptyMessageDelayed(0, 3000L);
          return;
        }
      }
      catch (Exception localException)
      {
        this.handler.sendEmptyMessageDelayed(0, 3000L);
        return;
      }
      if ((this.widget.getTitle() != null) && (this.widget.getTitle().length() != 0))
        setTopBarTitle(this.widget.getTitle());
      while (true)
      {
        if (!((Boolean)getIntent().getExtras().getSerializable("showSideBar")).booleanValue())
          setTopBarLeftButtonText(getResources().getString(getResources().getIdentifier("common_home_upper", "string", getPackageName())), true, new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              FeedbackPlugin.this.onBackPressed();
            }
          });
        setTopBarRightButtonText(getResources().getString(getResources().getIdentifier("common_send_upper", "string", getPackageName())), false, new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            FeedbackPlugin.this.handler.sendEmptyMessage(1);
          }
        });
        this.sendEditText = ((EditText)findViewById(getResources().getIdentifier("feedback_editfield", "id", getPackageName())));
        this.appid = localIntent.getStringExtra("appid");
        if (this.appid != null)
          break;
        this.handler.sendEmptyMessage(0);
        return;
        setTopBarTitle("");
      }
      if (this.appid.length() == 0)
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
      this.endpoint = new Parser(null).parse(this.widget.getPluginXmlData());
      if (this.endpoint != null)
        continue;
      this.handler.sendEmptyMessage(0);
      return;
    }
    while (this.endpoint.length() != 0);
    this.handler.sendEmptyMessage(0);
  }

  private class Parser extends DefaultHandler
  {
    private boolean endpointStarted = false;
    private StringBuilder sb = new StringBuilder();

    private Parser()
    {
    }

    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      if (this.endpointStarted)
        this.sb.append(paramArrayOfChar, paramInt1, paramInt2);
      super.characters(paramArrayOfChar, paramInt1, paramInt2);
    }

    public void endElement(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      if (paramString2.equalsIgnoreCase("endpoint"))
        this.endpointStarted = false;
      super.endElement(paramString1, paramString2, paramString3);
    }

    public String parse(String paramString)
    {
      try
      {
        Xml.parse(paramString, this);
        String str = this.sb.toString().trim();
        return str;
      }
      catch (Exception localException)
      {
      }
      return "";
    }

    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      if (paramString2.equalsIgnoreCase("endpoint"))
        this.endpointStarted = true;
      super.startElement(paramString1, paramString2, paramString3, paramAttributes);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.FeedbackPlugin.FeedbackPlugin
 * JD-Core Version:    0.6.0
 */