package com.appbuilder.sdk.android.authorization;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.appbuilder.sdk.android.AppBuilderModule;
import com.appbuilder.sdk.android.Statics;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.authorization.entities.User;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class LinkedInAuthorizationActivity extends AppBuilderModule
{
  private static String REDIRECT_URL;
  private static String accessToken;
  private static String bearerToken;
  private static final String logname = "LinkedIn";
  private static String state = "123123123";
  private User linkedInUser = new User();
  private ProgressDialog progressDialog = null;
  private WebView webView;

  static
  {
    REDIRECT_URL = "http://ibuildapp.com";
  }

  private static String createUrlForAccessToken()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = bearerToken;
    arrayOfObject[1] = REDIRECT_URL;
    arrayOfObject[2] = Statics.LINKEDIN_CLIENT_ID;
    arrayOfObject[3] = Statics.LINKEDIN_CLIENT_SECRET;
    String str = String.format("https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code&code=%s&redirect_uri=%s&client_id=%s&client_secret=%s", arrayOfObject);
    Log.d("LinkedIn", "createUrlForAccessToken = " + str);
    return str;
  }

  private static String createUrlForBearerToken()
  {
    Log.d("LinkedIn", "createUrlForBearerToken");
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Statics.LINKEDIN_CLIENT_ID;
    arrayOfObject[1] = state;
    arrayOfObject[2] = REDIRECT_URL;
    String str = String.format("https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=%s&state=%s&redirect_uri=%s", arrayOfObject);
    Log.d("LinkedIn", "url = " + str);
    return str;
  }

  public static String getAccessToken()
  {
    accessToken = null;
    String str1 = createUrlForAccessToken();
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpGet localHttpGet = new HttpGet(str1);
    try
    {
      JSONObject localJSONObject = Utils.inputStreamToJSONObject(localDefaultHttpClient.execute(localHttpGet).getEntity().getContent());
      accessToken = localJSONObject.getString("access_token");
      String str2 = localJSONObject.getString("expires_in");
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = accessToken;
      arrayOfObject[1] = str2;
      Log.d("LinkedIn", String.format("accessToken = %s, experiseIn = %s", arrayOfObject));
      return accessToken;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
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
      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        super.onPageStarted(paramWebView, paramString, paramBitmap);
        Log.d("LinkedIn", "pageStarted url = " + paramString);
        boolean bool = paramString.startsWith(LinkedInAuthorizationActivity.REDIRECT_URL);
        Object localObject = null;
        if (bool);
        try
        {
          URL localURL1 = new URL(paramString);
          localURL2 = localURL1;
          String[] arrayOfString = localURL2.getQuery().split("&");
          for (int i = 0; ; i++)
          {
            int j = arrayOfString.length;
            localObject = null;
            if (i < j)
            {
              if (!arrayOfString[i].contains("code"))
                continue;
              LinkedInAuthorizationActivity.access$102(arrayOfString[i].split("=")[1]);
              String str = LinkedInAuthorizationActivity.getAccessToken();
              localObject = str;
            }
            if (localObject != null)
            {
              Log.d("LinkedIn", "bearerToken = " + LinkedInAuthorizationActivity.bearerToken);
              Log.d("LinkedIn", "accessToken = " + localObject);
              LinkedInAuthorizationActivity.this.linkedInUser.setAccessToken(localObject);
              LinkedInAuthorizationActivity.this.linkedInUser.setAccountType("linkedin");
              Authorization.linkedinUser = LinkedInAuthorizationActivity.this.linkedInUser;
              LinkedInAuthorizationActivity.this.finish();
            }
            return;
          }
        }
        catch (NullPointerException localNullPointerException)
        {
          while (true)
          {
            Log.d("", "");
            localObject = null;
          }
        }
        catch (MalformedURLException localMalformedURLException)
        {
          while (true)
            URL localURL2 = null;
        }
      }

      public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
      {
        Log.d("LinkedIn", "shouldOverrideUrlLoading: url = " + paramString);
        return super.shouldOverrideUrlLoading(paramWebView, paramString);
      }
    });
    String str = createUrlForBearerToken();
    this.webView.loadUrl(str);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.authorization.LinkedInAuthorizationActivity
 * JD-Core Version:    0.6.0
 */