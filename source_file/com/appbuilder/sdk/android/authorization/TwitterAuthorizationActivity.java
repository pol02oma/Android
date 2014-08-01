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
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.appbuilder.sdk.android.AppBuilderModule;
import com.appbuilder.sdk.android.Statics;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterAuthorizationActivity extends AppBuilderModule
{
  public static final String ADV_TWITTER_CONSUMER_KEY = "szTSJaoSHMviPwfdb1PDCg";
  public static final String ADV_TWITTER_CONSUMER_SECRET = "nv4PGlEDLKVgxzGoPJ7uumWzYW2eMYuxo9XtLWNbM";
  private static final String CALLBACK_URL = "twitterapp://connect";
  private final int HIDE_PROGRESS_DIALOG = 0;
  private final int POST_INITIALIZATION = 2;
  private final int PROCESS_TOKEN = 1;
  private String authUrl = "";
  private com.appbuilder.sdk.android.authorization.entities.User fwUser = null;
  private AccessToken mAccessToken;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        TwitterAuthorizationActivity.this.hideProgressDialog();
        return;
      case 1:
        TwitterAuthorizationActivity.this.processToken(TwitterAuthorizationActivity.this.url);
        return;
      case 2:
      }
      TwitterAuthorizationActivity.this.webView.loadUrl(TwitterAuthorizationActivity.this.authUrl);
    }
  };
  private CommonsHttpOAuthConsumer mHttpOauthConsumer;
  private CommonsHttpOAuthProvider mHttpOauthprovider;
  private Twitter mTwitter;
  private ProgressDialog progressDialog = null;
  private String twitpic_api_key = "cd457da64a01a64105a6db68728ea1a6";
  private String twitterConsumerKey = Statics.TWITTER_CONSUMER_KEY;
  private String twitterSecretKey = Statics.TWITTER_CONSUMER_SECRET;
  private String url = "";
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
    Intent localIntent = new Intent();
    localIntent.putExtra("user", this.fwUser);
    setResult(-1, localIntent);
    finish();
  }

  private String getVerifier(String paramString)
  {
    Object localObject = "";
    try
    {
      String[] arrayOfString1 = new URL(paramString.replace("twitterapp", "http")).getQuery().split("&");
      int i = arrayOfString1.length;
      for (int j = 0; ; j++)
      {
        if (j < i)
        {
          String[] arrayOfString2 = arrayOfString1[j].split("=");
          if (!URLDecoder.decode(arrayOfString2[0]).equals("oauth_verifier"))
            continue;
          String str = URLDecoder.decode(arrayOfString2[1]);
          localObject = str;
        }
        return localObject;
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      localMalformedURLException.printStackTrace();
    }
    return (String)localObject;
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void postInitializatin()
  {
    Log.e("TRACE", new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()) + " 1");
    this.mTwitter = new TwitterFactory().getInstance();
    this.mHttpOauthConsumer = new CommonsHttpOAuthConsumer(this.twitterConsumerKey, this.twitterSecretKey);
    this.mHttpOauthprovider = new CommonsHttpOAuthProvider("https://twitter.com/oauth/request_token", "https://twitter.com/oauth/access_token", "https://twitter.com/oauth/authorize");
    try
    {
      this.authUrl = this.mHttpOauthprovider.retrieveRequestToken(this.mHttpOauthConsumer, "twitterapp://connect");
      Log.e("TRACE", new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()) + " 2");
      Log.e("TRACE", new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()) + " 3");
      return;
    }
    catch (OAuthCommunicationException localOAuthCommunicationException)
    {
      while (true)
        Log.d("", "");
    }
    catch (OAuthMessageSignerException localOAuthMessageSignerException)
    {
      while (true)
        Log.d("", "");
    }
    catch (OAuthNotAuthorizedException localOAuthNotAuthorizedException)
    {
      while (true)
        Log.d("", "");
    }
    catch (OAuthExpectationFailedException localOAuthExpectationFailedException)
    {
      while (true)
        Log.d("", "");
    }
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
      this.progressDialog = ProgressDialog.show(this, null, "Loading...");
    }
  }

  public void create()
  {
    Log.e("TRACE", new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()) + "TwitterAuth");
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setOrientation(1);
    localLinearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    localLinearLayout.setLayoutParams(localLayoutParams);
    this.webView = new WebView(this);
    this.webView.setLayoutParams(localLayoutParams);
    this.webView.setScrollBarStyle(33554432);
    localLinearLayout.addView(this.webView);
    setContentView(localLinearLayout);
    if (Statics.showLink)
    {
      this.twitterConsumerKey = "szTSJaoSHMviPwfdb1PDCg";
      this.twitterSecretKey = "nv4PGlEDLKVgxzGoPJ7uumWzYW2eMYuxo9XtLWNbM";
    }
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
        TwitterAuthorizationActivity.this.mHandler.sendEmptyMessage(0);
        Log.e("TRACE", new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()) + "finished");
      }

      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        super.onPageStarted(paramWebView, paramString, paramBitmap);
      }

      public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
      {
        if (paramString.startsWith("twitterapp://connect"))
        {
          TwitterAuthorizationActivity.access$102(TwitterAuthorizationActivity.this, paramString);
          TwitterAuthorizationActivity.this.mHandler.sendEmptyMessage(1);
        }
        do
          return true;
        while (!paramString.startsWith("authorize"));
        return false;
      }
    });
    showProgressDialog();
    new Thread(new Runnable()
    {
      public void run()
      {
        TwitterAuthorizationActivity.this.postInitializatin();
        TwitterAuthorizationActivity.this.mHandler.sendEmptyMessage(2);
      }
    }).start();
  }

  public void destroy()
  {
    CookieSyncManager.createInstance(this);
    CookieManager.getInstance().removeAllCookie();
    super.destroy();
  }

  public void processToken(String paramString)
  {
    String str = getVerifier(paramString);
    try
    {
      this.mHttpOauthprovider.retrieveAccessToken(this.mHttpOauthConsumer, str);
      this.mAccessToken = new AccessToken(this.mHttpOauthConsumer.getToken(), this.mHttpOauthConsumer.getTokenSecret());
      this.mTwitter.setOAuthConsumer(this.twitterConsumerKey, this.twitterSecretKey);
      this.mTwitter.setOAuthAccessToken(this.mAccessToken);
      twitter4j.User localUser = this.mTwitter.verifyCredentials();
      this.fwUser = new com.appbuilder.sdk.android.authorization.entities.User();
      this.fwUser.setUserName(localUser.getName());
      this.fwUser.setAvatarUrl(localUser.getOriginalProfileImageURL());
      this.fwUser.setAccountId(localUser.getId() + "");
      this.fwUser.setAccountType("twitter");
      this.fwUser.setAccessToken(this.mHttpOauthConsumer.getToken());
      this.fwUser.setAccessTokenSecret(this.mHttpOauthConsumer.getTokenSecret());
      this.fwUser.setConsumerKey(this.mHttpOauthConsumer.getConsumerKey());
      this.fwUser.setConsumerSecret(this.mHttpOauthConsumer.getConsumerSecret());
      Authorization.twitterUser = this.fwUser;
      if (Authorization.primaryUser == null)
        Authorization.primaryUser = this.fwUser;
      closeActivityWithOkResult();
      return;
    }
    catch (Exception localException)
    {
      closeActivityWithBadResult();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.authorization.TwitterAuthorizationActivity
 * JD-Core Version:    0.6.0
 */