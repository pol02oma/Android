package com.appbuilder.sdk.android.sharing;

import android.app.Activity;
import android.util.Log;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class VkontakteSharing
{
  static final String logname = "Vkontakte";
  private String accessToken;
  private String userId;

  private static String createGetWallUploadServerUrl()
  {
    User localUser = Authorization.vkontakteUser;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = localUser.getAccountId();
    arrayOfObject[1] = localUser.getAccessToken();
    String str = String.format("https://api.vk.com/method/photos.getWallUploadServer?owner_id=%s&access_token=%s", arrayOfObject);
    Log.d("Vkontakte", "createGetWallUploadServerUrl = " + str);
    return str;
  }

  private static String createSaveWallPhotoUrl(String paramString1, String paramString2, String paramString3)
  {
    User localUser = Authorization.vkontakteUser;
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = localUser.getAccountId();
    arrayOfObject[1] = localUser.getAccessToken();
    arrayOfObject[2] = paramString2;
    arrayOfObject[3] = URLEncoder.encode(paramString1);
    arrayOfObject[4] = paramString3;
    String str = String.format("https://api.vk.com/method/photos.saveWallPhoto?owner_id=%s&access_token=%s&server=%s&photo=%s&hash=%s", arrayOfObject);
    Log.d("Vkontakte", "createSaveWallPhotoUrl = " + str);
    return str;
  }

  private static String createWallPostUrl(String paramString1, String paramString2)
  {
    User localUser = Authorization.vkontakteUser;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = localUser.getAccountId();
    arrayOfObject[1] = localUser.getAccessToken();
    arrayOfObject[2] = URLEncoder.encode(paramString1);
    arrayOfObject[3] = paramString2;
    String str = String.format("https://api.vk.com/method/wall.post?owner_id=%s&access_token=%s&message=%s&attachment=%s", arrayOfObject);
    Log.d("Vkontakte", "createWallPostUrl = " + str);
    return str;
  }

  private static String createWallSimplePostUrl(String paramString)
  {
    User localUser = Authorization.vkontakteUser;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = localUser.getAccountId();
    arrayOfObject[1] = localUser.getAccessToken();
    arrayOfObject[2] = URLEncoder.encode(paramString);
    String str = String.format("https://api.vk.com/method/wall.post?owner_id=%s&access_token=%s&message=%s", arrayOfObject);
    Log.d("Vkontakte", "createWallSimplePostUrl = " + str);
    return str;
  }

  private static JSONObject requestImageUpload(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpPost localHttpPost = new HttpPost(paramString);
      MultipartEntity localMultipartEntity = new MultipartEntity();
      localMultipartEntity.addPart("photo", new ByteArrayBody(paramArrayOfByte, "image/jpeg", "image.jpg"));
      localHttpPost.setEntity(localMultipartEntity);
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      Log.d("Vkontakte", "image is loaded");
      JSONObject localJSONObject = Utils.inputStreamToJSONObject(localHttpResponse.getEntity().getContent());
      return localJSONObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  private static String requestServerForImageUpload()
  {
    String str1 = "";
    Log.d("Vkontakte", "requestServerForImageUpload");
    String str2 = createGetWallUploadServerUrl();
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpGet localHttpGet = new HttpGet(str2);
    try
    {
      str1 = Utils.inputStreamToJSONObject(localDefaultHttpClient.execute(localHttpGet).getEntity().getContent()).getJSONObject("response").getString("upload_url");
      Log.d("Vkontakte", "uploadUrl = " + str1);
      return str1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str1;
  }

  private static void requestWallPost(String paramString)
  {
    try
    {
      String str = createWallSimplePostUrl(paramString);
      JSONObject localJSONObject = Utils.inputStreamToJSONObject(new DefaultHttpClient().execute(new HttpGet(str)).getEntity().getContent());
      Log.d("Vkontakte", "requestWallPost result = " + localJSONObject.toString());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void requestWallPostWithImage(Integer paramInteger, String paramString1, String paramString2, Activity paramActivity)
    throws IllegalStateException
  {
    try
    {
      if (Authorization.vkontakteUser != null)
      {
        String str = requestServerForImageUpload();
        if (paramInteger != null);
        for (byte[] arrayOfByte = Utils.loadImageFromResource(paramInteger, paramActivity); ; arrayOfByte = Utils.loadImageFromUrl(paramString1))
        {
          wallPost(saveWallPhoto(requestImageUpload(str, arrayOfByte)), paramString2);
          return;
        }
      }
      throw new IllegalStateException("You must be authorized in VK");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    throw new IllegalStateException("You must be authorized in VK");
  }

  private static JSONObject saveWallPhoto(JSONObject paramJSONObject)
  {
    try
    {
      String str1 = paramJSONObject.getString("server");
      String str2 = paramJSONObject.getString("hash");
      HttpGet localHttpGet = new HttpGet(createSaveWallPhotoUrl(paramJSONObject.getString("photo"), str1, str2));
      JSONObject localJSONObject = Utils.inputStreamToJSONObject(new DefaultHttpClient().execute(localHttpGet).getEntity().getContent());
      Log.d("Vkontakte", "result = " + localJSONObject);
      return localJSONObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static void share(String paramString1, String paramString2, Integer paramInteger, String paramString3, Activity paramActivity)
    throws IllegalStateException
  {
    if ((paramInteger != null) || (paramString3 != null))
    {
      requestWallPostWithImage(paramInteger, paramString3, paramString2, paramActivity);
      return;
    }
    requestWallPost(paramString2);
  }

  private static void wallPost(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      String str = createWallPostUrl(paramString, ((JSONObject)paramJSONObject.getJSONArray("response").get(0)).getString("id"));
      JSONObject localJSONObject = Utils.inputStreamToJSONObject(new DefaultHttpClient().execute(new HttpGet(str)).getEntity().getContent());
      Log.d("Vkontakte", "wallPost result = " + localJSONObject.toString());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.sharing.VkontakteSharing
 * JD-Core Version:    0.6.0
 */