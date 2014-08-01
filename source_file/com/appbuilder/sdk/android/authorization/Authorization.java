package com.appbuilder.sdk.android.authorization;

import android.app.Activity;
import android.content.Intent;
import com.appbuilder.sdk.android.Statics;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

public class Authorization
{
  public static final int AUTHORIZATION_TYPE_ANY = 0;
  public static final int AUTHORIZATION_TYPE_FACEBOOK = 1;
  public static final int AUTHORIZATION_TYPE_IBUILDAPP = 3;
  public static final int AUTHORIZATION_TYPE_LINKEDIN = 5;
  public static final int AUTHORIZATION_TYPE_TWITTER = 2;
  public static final int AUTHORIZATION_TYPE_VKONTAKTE = 4;
  static User facebookUser;
  static User ibuildappUser;
  public static User linkedinUser;
  static User primaryUser = null;
  static User twitterUser;
  public static User vkontakteUser;

  static
  {
    facebookUser = null;
    twitterUser = null;
    ibuildappUser = null;
    vkontakteUser = null;
    linkedinUser = null;
  }

  public static void authorize(Activity paramActivity, int paramInt1, int paramInt2)
  {
    switch (paramInt2)
    {
    case 3:
    default:
      throw new IllegalArgumentException("You must provide correct authorization type (facebook or twitter).");
    case 1:
      paramActivity.startActivityForResult(new Intent(paramActivity, FacebookAuthorizationActivity.class), paramInt1);
      return;
    case 2:
      paramActivity.startActivityForResult(new Intent(paramActivity, TwitterAuthorizationActivity.class), paramInt1);
      return;
    case 5:
      paramActivity.startActivityForResult(new Intent(paramActivity, LinkedInAuthorizationActivity.class), paramInt1);
      return;
    case 4:
    }
    paramActivity.startActivityForResult(new Intent(paramActivity, VkontakteAuthorizationActivity.class), paramInt1);
  }

  public static boolean authorizeEmail(String paramString1, String paramString2)
  {
    if (ibuildappUser != null)
      throw new IllegalStateException("Already authorized");
    String str1 = "http://" + Statics.BASE_DOMEN + "/modules/user/login";
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 15000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 15000);
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
    ArrayList localArrayList = new ArrayList();
    try
    {
      HttpPost localHttpPost = new HttpPost(str1);
      localArrayList.add(new BasicNameValuePair("login", paramString1));
      localArrayList.add(new BasicNameValuePair("password", paramString2));
      localArrayList.add(new BasicNameValuePair("app_id", Statics.appId));
      localArrayList.add(new BasicNameValuePair("token", Statics.appToken));
      localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList));
      String str2 = (String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler());
      if (str2 == null)
        return false;
      if (str2.length() != 0)
      {
        JSONObject localJSONObject = new JSONObject(str2).getJSONObject("data");
        User localUser = new User();
        localUser.setAccountId(localJSONObject.getString("user_id"));
        localUser.setUserName(localJSONObject.getString("username"));
        localUser.setAvatarUrl(localJSONObject.getString("user_avatar"));
        localUser.setAccountType("ibuildapp");
        if (primaryUser == null)
          primaryUser = localUser;
        ibuildappUser = localUser;
        return true;
      }
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static User getAuthorizedUser()
  {
    return primaryUser;
  }

  public static User getAuthorizedUser(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 0:
      return primaryUser;
    case 1:
      return facebookUser;
    case 2:
      return twitterUser;
    case 3:
      return ibuildappUser;
    case 5:
      return linkedinUser;
    case 4:
    }
    return vkontakteUser;
  }

  public static boolean isAuthorized()
  {
    return primaryUser != null;
  }

  public static boolean isAuthorized(int paramInt)
  {
    int i = 1;
    switch (paramInt)
    {
    default:
      i = 0;
    case 0:
    case 1:
    case 2:
    case 3:
    case 5:
    case 4:
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
                return i;
              while (primaryUser != null);
              return false;
            }
            while (facebookUser != null);
            return false;
          }
          while (twitterUser != null);
          return false;
        }
        while (ibuildappUser != null);
        return false;
      }
      while (linkedinUser != null);
      return false;
    }
    while (vkontakteUser != null);
    return false;
  }

  public static boolean registerEmail(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    localBasicHttpParams.setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
    try
    {
      HttpPost localHttpPost = new HttpPost("http://" + Statics.BASE_DOMEN + "/modules/user/signup");
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(new BasicNameValuePair("firstname", paramString1));
      localArrayList.add(new BasicNameValuePair("lastname", paramString2));
      localArrayList.add(new BasicNameValuePair("email", paramString3));
      localArrayList.add(new BasicNameValuePair("password", paramString4));
      localArrayList.add(new BasicNameValuePair("password_confirm", paramString5));
      localArrayList.add(new BasicNameValuePair("app_id", Statics.appId));
      localArrayList.add(new BasicNameValuePair("token", Statics.appToken));
      localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList));
      String str = (String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler());
      if (str == null)
        return false;
      if (str.length() == 0)
        return false;
      JSONObject localJSONObject = new JSONObject(str).getJSONObject("data");
      User localUser = new User();
      localUser.setAccountId(localJSONObject.getString("user_id"));
      localUser.setUserName(localJSONObject.getString("username"));
      localUser.setAvatarUrl(localJSONObject.getString("user_avatar"));
      localUser.setAccountType("ibuildapp");
      localUser.setUserEmail(paramString3);
      if (primaryUser == null)
        primaryUser = localUser;
      ibuildappUser = localUser;
      return true;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static void setPrimaryUser(int paramInt)
  {
    switch (paramInt)
    {
    default:
      throw new IllegalArgumentException("Incorrect authorization type");
    case 1:
      if (facebookUser == null)
        throw new IllegalStateException("There is no facebook authorization");
      primaryUser = facebookUser;
      return;
    case 2:
      if (twitterUser == null)
        throw new IllegalStateException("There is no twitter authorization");
      primaryUser = twitterUser;
      return;
    case 3:
      if (ibuildappUser == null)
        throw new IllegalStateException("There is no email authorization");
      primaryUser = ibuildappUser;
      return;
    case 5:
      if (linkedinUser == null)
        throw new IllegalStateException("There is no linkedin authorization");
      primaryUser = linkedinUser;
      return;
    case 4:
    }
    if (vkontakteUser == null)
      throw new IllegalStateException("There is no vkontakte authorization");
    primaryUser = vkontakteUser;
  }

  public static void unauthorize()
  {
    primaryUser = null;
    facebookUser = null;
    twitterUser = null;
    ibuildappUser = null;
    vkontakteUser = null;
    linkedinUser = null;
  }

  public static void unauthorize(int paramInt)
  {
    unauthorize(paramInt, true);
  }

  public static void unauthorize(int paramInt, boolean paramBoolean)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 5:
    case 4:
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            return;
                            unauthorize();
                            return;
                            facebookUser = null;
                          }
                          while (primaryUser.getAccountType() != User.ACCOUNT_TYPES.FACEBOOK);
                          primaryUser = null;
                        }
                        while (!paramBoolean);
                        if (twitterUser == null)
                          continue;
                        primaryUser = twitterUser;
                        return;
                      }
                      while (ibuildappUser == null);
                      primaryUser = ibuildappUser;
                      return;
                      twitterUser = null;
                    }
                    while (primaryUser.getAccountType() != User.ACCOUNT_TYPES.TWITTER);
                    primaryUser = null;
                  }
                  while (!paramBoolean);
                  if (facebookUser == null)
                    continue;
                  primaryUser = facebookUser;
                  return;
                }
                while (ibuildappUser == null);
                primaryUser = ibuildappUser;
                return;
                ibuildappUser = null;
              }
              while (primaryUser.getAccountType() != User.ACCOUNT_TYPES.IBUILDAPP);
              primaryUser = null;
            }
            while (!paramBoolean);
            if (facebookUser == null)
              continue;
            primaryUser = facebookUser;
            return;
          }
          while (twitterUser == null);
          primaryUser = twitterUser;
          return;
          linkedinUser = null;
          if (primaryUser.getAccountType() == User.ACCOUNT_TYPES.LINKEDIN)
          {
            primaryUser = null;
            if (paramBoolean)
            {
              if (facebookUser != null)
              {
                primaryUser = facebookUser;
                return;
              }
              if (twitterUser != null)
              {
                primaryUser = twitterUser;
                return;
              }
            }
          }
          vkontakteUser = null;
        }
        while (primaryUser.getAccountType() != User.ACCOUNT_TYPES.VKONTAKTE);
        primaryUser = null;
      }
      while (!paramBoolean);
      if (facebookUser == null)
        continue;
      primaryUser = facebookUser;
      return;
    }
    while (twitterUser == null);
    primaryUser = twitterUser;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.authorization.Authorization
 * JD-Core Version:    0.6.0
 */