package com.appbuilder.u846253p1176378.LoginScreen.service;

import android.util.Log;
import java.io.IOException;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class LoginScreenService
{
  public static String TAG = LoginScreenService.class.getName();

  public static void doCreateAccount(String paramString1, String paramString2, String paramString3, String paramString4, OnDone paramOnDone)
  {
    new Thread(new Runnable(paramString1, paramString2, paramString3, paramString4, paramOnDone)
    {
      public void run()
      {
        try
        {
          Object[] arrayOfObject = new Object[4];
          arrayOfObject[0] = this.val$url;
          arrayOfObject[1] = this.val$username;
          arrayOfObject[2] = this.val$password;
          arrayOfObject[3] = this.val$appId;
          String str1 = String.format("%s?login=%s&password=%s&type=%s&app_id=%s", arrayOfObject);
          Log.d(LoginScreenService.TAG, str1);
          HttpGet localHttpGet = new HttpGet(str1);
          String str2 = (String)new DefaultHttpClient().execute(localHttpGet, new ResponseHandler()
          {
            public String handleResponse(HttpResponse paramHttpResponse)
              throws ClientProtocolException, IOException
            {
              return new String(String.valueOf(paramHttpResponse.getStatusLine().getStatusCode()));
            }
          });
          this.val$onDone.onDone(new Integer(str2).intValue());
          return;
        }
        catch (Exception localException)
        {
          Log.d(LoginScreenService.TAG, "", localException);
        }
      }
    }).start();
  }

  public static void doLogin(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, OnDone paramOnDone)
  {
    new Thread(new Runnable(paramString1, paramString2, paramString3, paramString4, paramString5, paramOnDone)
    {
      public void run()
      {
        try
        {
          Object[] arrayOfObject = new Object[5];
          arrayOfObject[0] = this.val$url;
          arrayOfObject[1] = URLEncoder.encode(this.val$username);
          arrayOfObject[2] = URLEncoder.encode(this.val$password);
          arrayOfObject[3] = URLEncoder.encode(this.val$type);
          arrayOfObject[4] = URLEncoder.encode(this.val$appId);
          String str1 = String.format("%s?login=%s&password=%s&type=%s&app_id=%s", arrayOfObject);
          Log.d(LoginScreenService.TAG, str1);
          HttpGet localHttpGet = new HttpGet(str1);
          String str2 = (String)new DefaultHttpClient().execute(localHttpGet, new ResponseHandler()
          {
            public String handleResponse(HttpResponse paramHttpResponse)
              throws ClientProtocolException, IOException
            {
              return new String(String.valueOf(paramHttpResponse.getStatusLine().getStatusCode()));
            }
          });
          this.val$onDone.onDone(new Integer(str2).intValue());
          return;
        }
        catch (Exception localException)
        {
          Log.d(LoginScreenService.TAG, "", localException);
          this.val$onDone.onDone(new Integer("404").intValue());
        }
      }
    }).start();
  }

  public static void doRecovery(String paramString1, String paramString2, String paramString3, OnDone paramOnDone)
  {
    new Thread(new Runnable(paramString1, paramString2, paramString3, paramOnDone)
    {
      public void run()
      {
        try
        {
          Object[] arrayOfObject = new Object[3];
          arrayOfObject[0] = this.val$url;
          arrayOfObject[1] = URLEncoder.encode(this.val$username);
          arrayOfObject[2] = URLEncoder.encode(this.val$appId);
          String str1 = String.format("%s?login=%s&app_id=%s", arrayOfObject);
          Log.d(LoginScreenService.TAG, str1);
          HttpGet localHttpGet = new HttpGet(str1);
          String str2 = (String)new DefaultHttpClient().execute(localHttpGet, new ResponseHandler()
          {
            public String handleResponse(HttpResponse paramHttpResponse)
              throws ClientProtocolException, IOException
            {
              return new String(String.valueOf(paramHttpResponse.getStatusLine().getStatusCode()));
            }
          });
          this.val$onDone.onDone(new Integer(str2).intValue());
          return;
        }
        catch (Exception localException)
        {
          Log.d(LoginScreenService.TAG, "", localException);
          this.val$onDone.onDone(new Integer("404").intValue());
        }
      }
    }).start();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.LoginScreen.service.LoginScreenService
 * JD-Core Version:    0.6.0
 */