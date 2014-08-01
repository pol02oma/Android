package com.appbuilder.sdk.android.authorization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModule;
import com.appbuilder.sdk.android.Statics;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import java.net.MalformedURLException;
import java.net.URL;

public class FacebookAuthorizationActivity extends AppBuilderModule
{
  public static final String ADV_APPLICATION_ID = "207296122640913";
  public static final String ADV_APPLICATION_SECRET = "8ce2f515309ba56afbfe9c1431b59d9a";
  public static final String NO_ADV_APPLICATION_ID = Statics.FACEBOOK_APP_ID;
  private final String ACCESS_TOKEN = "access_token";
  private final int CLOSE_ACTIVITY = 1;
  private final int CLOSE_ACTIVITY_OK = 2;
  private final int GET_USER_INFO = 3;
  private final int HIDE_PROGRESS_DIALOG = 4;
  private final int NEED_INTERNET_CONNECTION = 0;
  private final String REDIRECT_URL = "ibuildapp.com/facebook.stub.php";
  private String accessToken = "";
  private String applicationId = "207296122640913";
  private com.appbuilder.sdk.android.authorization.entities.User fwUser = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        Toast.makeText(FacebookAuthorizationActivity.this, "Cellular data is turned off.", 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
          }
        }
        , 3000L);
        FacebookAuthorizationActivity.this.closeActivityWithBadResult();
        return;
      case 1:
        FacebookAuthorizationActivity.this.closeActivityWithBadResult();
        return;
      case 2:
        FacebookAuthorizationActivity.this.closeActivityWithOkResult();
        return;
      case 3:
        FacebookAuthorizationActivity.this.getUserInfo();
        return;
      case 4:
      }
      FacebookAuthorizationActivity.this.hideProgressDialog();
    }
  };
  private ProgressDialog progressDialog = null;
  private String redirectURL = "ibuildapp.com/facebook.stub.php";
  private WebView webView = null;

  private void closeActivityWithBadResult()
  {
    hideProgressDialog();
    setResult(0);
    finish();
  }

  private void closeActivityWithOkResult()
  {
    hideProgressDialog();
    Authorization.facebookUser = this.fwUser;
    Intent localIntent = new Intent();
    localIntent.putExtra("user", this.fwUser);
    setResult(-1, localIntent);
    finish();
  }

  private void getUserInfo()
  {
    try
    {
      com.restfb.types.User localUser = (com.restfb.types.User)new DefaultFacebookClient(this.accessToken).fetchObject("me", com.restfb.types.User.class, new Parameter[0]);
      this.fwUser = new com.appbuilder.sdk.android.authorization.entities.User();
      this.fwUser.setUserEmail(localUser.getEmail());
      this.fwUser.setUserFirstName(localUser.getFirstName());
      this.fwUser.setUserLastName(localUser.getLastName());
      this.fwUser.setAvatarUrl("https://graph.facebook.com/" + localUser.getId() + "/picture?type=large");
      this.fwUser.setAccountId(localUser.getId());
      this.fwUser.setAccountType("facebook");
      this.fwUser.setAccessToken(this.accessToken);
      Authorization.facebookUser = this.fwUser;
      if (Authorization.primaryUser == null)
        Authorization.primaryUser = this.fwUser;
      this.handler.sendEmptyMessage(2);
      Log.e("", "");
      return;
    }
    catch (Exception localException)
    {
      this.handler.sendEmptyMessage(1);
    }
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void showProgressDialog()
  {
    try
    {
      boolean bool = this.progressDialog.isShowing();
      if (bool)
        return;
    }
    catch (NullPointerException localNullPointerException)
    {
      try
      {
        this.progressDialog = ProgressDialog.show(this, null, "Loading...");
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }
  }

  public void create()
  {
    requestWindowFeature(1);
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setOrientation(1);
    localLinearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    localLinearLayout.setLayoutParams(localLayoutParams);
    this.webView = new WebView(this);
    this.webView.setLayoutParams(localLayoutParams);
    localLinearLayout.addView(this.webView);
    setContentView(localLinearLayout);
    if (!Statics.showLink)
    {
      this.applicationId = NO_ADV_APPLICATION_ID;
      if ((Statics.BASE_DOMEN != null) && (Statics.BASE_DOMEN.length() > 0))
        this.redirectURL = (Statics.BASE_DOMEN + "/facebook.stub.php");
    }
    this.webView.setBackgroundColor(-1);
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
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
    this.webView.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramWebView, String paramString)
      {
        super.onPageFinished(paramWebView, paramString);
        if (paramString.startsWith("http://" + FacebookAuthorizationActivity.this.redirectURL));
        while (true)
        {
          int i;
          try
          {
            URL localURL1 = new URL(paramString);
            localURL2 = localURL1;
            String[] arrayOfString = localURL2.getRef().split("&");
            i = 0;
            if (i >= arrayOfString.length)
              continue;
            if (!arrayOfString[i].contains("access_token"))
              break label175;
            FacebookAuthorizationActivity.access$602(FacebookAuthorizationActivity.this, arrayOfString[i].split("=")[1]);
          }
          catch (MalformedURLException localMalformedURLException)
          {
            Log.d("", "");
            URL localURL2 = null;
            continue;
          }
          catch (NullPointerException localNullPointerException)
          {
            Log.d("", "");
            FacebookAuthorizationActivity.this.handler.sendEmptyMessage(4);
            FacebookAuthorizationActivity.this.handler.sendEmptyMessage(3);
            return;
          }
          FacebookAuthorizationActivity.this.handler.sendEmptyMessage(4);
          return;
          label175: i++;
        }
      }

      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        super.onPageStarted(paramWebView, paramString, paramBitmap);
        FacebookAuthorizationActivity.this.showProgressDialog();
      }
    });
    this.webView.loadUrl("https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + this.applicationId + "&redirect_uri=http%3A%2F%2F" + this.redirectURL + "&scope=publish_stream");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.authorization.FacebookAuthorizationActivity
 * JD-Core Version:    0.6.0
 */