package com.appbuilder.sdk.android.authorization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import com.appbuilder.sdk.android.authorization.entities.User;
import java.net.MalformedURLException;
import java.net.URL;

public class VkontakteAuthorizationActivity extends AppBuilderModule
{
  private static final String LOGNAME = "Vkontakte";
  private static final String REDIRECT_URL = "http://oauth.vk.com/blank.html";
  private ProgressDialog progressDialog = null;
  private User vkUser = new User();
  private WebView webView;

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
    localIntent.putExtra("user", this.vkUser);
    setResult(-1, localIntent);
    finish();
  }

  private String createUrlForAccessToken()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Statics.VKONTAKTE_CLIENT_ID;
    String str = String.format("http://oauth.vk.com/oauth/authorize?client_id=%s&scope=wall,photos,offline&redirect_uri=http://oauth.vk.com/blank.html&display=mobile&response_type=token", arrayOfObject);
    Log.d("Vkontakte", "createUrlForAccessToken = " + str);
    return str;
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
      this.progressDialog = ProgressDialog.show(this, null, "Loading");
    }
  }

  public void create()
  {
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setOrientation(1);
    localLinearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    localLinearLayout.setLayoutParams(localLayoutParams);
    this.webView = new WebView(this);
    this.webView.setLayoutParams(localLayoutParams);
    localLinearLayout.addView(this.webView);
    setContentView(localLinearLayout);
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
      public static final String USER_ID = "";

      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        super.onPageStarted(paramWebView, paramString, paramBitmap);
        Log.d("Vkontakte", "pageStarted url = " + paramString);
        boolean bool = paramString.startsWith("http://oauth.vk.com/blank.html");
        String str1 = null;
        String str2 = null;
        String str3 = null;
        if (bool);
        try
        {
          URL localURL1 = new URL(paramString);
          localURL2 = localURL1;
          String[] arrayOfString = localURL2.getRef().split("&");
          for (int i = 0; i < arrayOfString.length; i++)
          {
            if (arrayOfString[i].contains("access_token"))
              str1 = arrayOfString[i].split("=")[1];
            if (arrayOfString[i].contains("expires_in"))
              str2 = arrayOfString[i].split("=")[1];
            if (!arrayOfString[i].contains("user_id"))
              continue;
            str3 = arrayOfString[i].split("=")[1];
          }
        }
        catch (NullPointerException localNullPointerException)
        {
          localNullPointerException.printStackTrace();
          if (str1 != null)
          {
            Log.d("Vkontakte", "accessToken = " + str1);
            Log.d("Vkontakte", "expiresIn = " + str2);
            Log.d("Vkontakte", "userId = " + str3);
            if (VkontakteAuthorizationActivity.this.vkUser != null)
            {
              VkontakteAuthorizationActivity.this.vkUser.setAccessToken(str1);
              VkontakteAuthorizationActivity.this.vkUser.setAccountId(str3);
              VkontakteAuthorizationActivity.this.vkUser.setAccountType("vkontakte");
              Authorization.vkontakteUser = VkontakteAuthorizationActivity.this.vkUser;
              VkontakteAuthorizationActivity.this.finish();
            }
          }
          return;
        }
        catch (MalformedURLException localMalformedURLException)
        {
          while (true)
            URL localURL2 = null;
        }
      }
    });
    this.webView.loadUrl(createUrlForAccessToken());
  }

  public void destroy()
  {
    CookieSyncManager.createInstance(this);
    CookieManager.getInstance().removeAllCookie();
    super.destroy();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.authorization.VkontakteAuthorizationActivity
 * JD-Core Version:    0.6.0
 */