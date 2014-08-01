package com.ibuildapp.romanblack.PhotoGalleryPlugin.utils;

import android.util.Log;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.Album;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.CommentItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser
{
  public static HashMap<String, String> getImagesLikesCount(ArrayList<Album> paramArrayList)
  {
    while (true)
    {
      int i;
      try
      {
        HashMap localHashMap = new HashMap();
        com.ibuildapp.romanblack.PhotoGalleryPlugin.Statics.FACEBOOK_APP_TOKEN = loadURLData("https://graph.facebook.com/oauth/access_token?client_id=" + com.appbuilder.sdk.android.Statics.FACEBOOK_APP_ID + "&client_secret=" + com.appbuilder.sdk.android.Statics.FACEBOOK_APP_SECRET + "&grant_type=client_credentials").split("=")[1];
        StringBuilder localStringBuilder = new StringBuilder();
        i = 0;
        if (i >= paramArrayList.size())
          continue;
        if (!((Album)paramArrayList.get(i)).isRSS())
        {
          int k = 0;
          if (k < ((Album)paramArrayList.get(i)).getImages().size())
          {
            localStringBuilder.append("\"");
            localStringBuilder.append(URLEncoder.encode(((ImageItem)((Album)paramArrayList.get(i)).getImages().get(k)).getPermalinkUrl()));
            localStringBuilder.append("\"");
            localStringBuilder.append(",");
            k++;
            continue;
            String str = localStringBuilder.toString().substring(0, -1 + localStringBuilder.length());
            JSONArray localJSONArray = new JSONObject(loadURLData("https://graph.facebook.com/fql?q=SELECT+total_count,+url+FROM+link_stat+WHERE+url+IN+(" + str + ")&access_token=" + com.ibuildapp.romanblack.PhotoGalleryPlugin.Statics.FACEBOOK_APP_TOKEN)).getJSONArray("data");
            int j = 0;
            if (j >= localJSONArray.length())
              continue;
            JSONObject localJSONObject = localJSONArray.getJSONObject(j);
            localHashMap.put(localJSONObject.getString("url"), localJSONObject.getString("total_count"));
            j++;
            continue;
            Log.d("", "");
            return localHashMap;
          }
        }
      }
      catch (Exception localException)
      {
        Log.d("", "");
        return null;
      }
      i++;
    }
  }

  public static ArrayList<String> getUserOgLikes()
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      JSONArray localJSONArray = new JSONObject(loadURLData("https://graph.facebook.com/me/og.likes?fields=data&limit=999999999&access_token=" + Authorization.getAuthorizedUser(1).getAccessToken())).getJSONArray("data");
      for (int i = 0; i < localJSONArray.length(); i++)
        localArrayList.add(localJSONArray.getJSONObject(i).getJSONObject("data").getJSONObject("object").getString("url"));
      Log.d("", "");
      return localArrayList;
    }
    catch (Exception localException)
    {
      Log.d("", "");
    }
    return null;
  }

  public static HashMap<String, String> getVideoCommentsCount()
  {
    return getVideoCommentsCount(com.ibuildapp.romanblack.PhotoGalleryPlugin.Statics.BASE_URL + "/getcommentscount/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.ibuildapp.romanblack.PhotoGalleryPlugin.Statics.MODULE_ID + "/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken);
  }

  public static HashMap<String, String> getVideoCommentsCount(String paramString)
  {
    HashMap localHashMap;
    try
    {
      Log.e("URLTAG", paramString);
      String str = loadURLData(paramString);
      localHashMap = new HashMap();
      JSONArray localJSONArray = new JSONObject(str).getJSONArray("data");
      int i = 0;
      while (true)
      {
        int j = localJSONArray.length();
        if (i < j)
          try
          {
            JSONObject localJSONObject = localJSONArray.getJSONObject(i);
            localHashMap.put(localJSONObject.getString("id"), localJSONObject.getString("total_comments"));
            i++;
          }
          catch (Exception localException2)
          {
            while (true)
              Log.d("", "");
          }
      }
    }
    catch (Exception localException1)
    {
      Log.d("", "");
      localHashMap = null;
    }
    return localHashMap;
  }

  private static String loadURLData(String paramString)
  {
    BufferedReader localBufferedReader;
    StringBuilder localStringBuilder;
    try
    {
      localBufferedReader = new BufferedReader(new InputStreamReader(new URL(paramString).openConnection().getInputStream()));
      localStringBuilder = new StringBuilder();
      while (true)
      {
        String str1 = localBufferedReader.readLine();
        if (str1 == null)
          break;
        localStringBuilder.append(str1);
        localStringBuilder.append("\n");
      }
    }
    catch (IOException localIOException)
    {
      return "";
    }
    localBufferedReader.close();
    String str2 = localStringBuilder.toString();
    return str2;
  }

  public static ArrayList<CommentItem> parseCommentsString(String paramString)
  {
    ArrayList localArrayList;
    if (paramString == null)
      localArrayList = null;
    while (true)
    {
      return localArrayList;
      try
      {
        if (paramString.length() == 0)
          return null;
        JSONArray localJSONArray = new JSONObject(paramString).getJSONArray("data");
        localArrayList = new ArrayList();
        int i = 0;
        while (i < localJSONArray.length())
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          CommentItem localCommentItem = new CommentItem();
          localCommentItem.setId(Long.valueOf(localJSONObject.getString("id")).longValue());
          localCommentItem.setAuthor(localJSONObject.getString("username"));
          localCommentItem.setDate(new Date(Long.valueOf(localJSONObject.getString("create")).longValue()));
          localCommentItem.setAvatarUrl(localJSONObject.getString("avatar"));
          localCommentItem.setText(localJSONObject.getString("text"));
          try
          {
            localCommentItem.setTrackId(Long.valueOf(localJSONObject.getString("parent_id")).longValue());
          }
          catch (NumberFormatException localNumberFormatException2)
          {
            try
            {
              localCommentItem.setReplyId(Integer.valueOf(localJSONObject.getString("reply_id")).intValue());
            }
            catch (NumberFormatException localNumberFormatException2)
            {
              try
              {
                while (true)
                {
                  localCommentItem.setCommentsCount(Integer.valueOf(localJSONObject.getString("total_comments")).intValue());
                  localArrayList.add(localCommentItem);
                  i++;
                  break;
                  localNumberFormatException1 = localNumberFormatException1;
                  Log.e("", "");
                  continue;
                  localNumberFormatException2 = localNumberFormatException2;
                  Log.e("", "");
                }
              }
              catch (Exception localException)
              {
                while (true)
                  Log.d("", "");
              }
            }
          }
        }
      }
      catch (JSONException localJSONException)
      {
      }
    }
    return null;
  }

  public static ArrayList<CommentItem> parseCommentsUrl(String paramString)
  {
    try
    {
      Log.e("URLTAG", paramString);
      String str = loadURLData(paramString);
      if (str == null)
        return null;
      if (str.length() == 0)
        return null;
      JSONArray localJSONArray = new JSONObject(str).getJSONArray("data");
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      while (true)
        if (i < localJSONArray.length())
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          CommentItem localCommentItem = new CommentItem();
          localCommentItem.setId(Long.valueOf(localJSONObject.getString("id")).longValue());
          localCommentItem.setAuthor(localJSONObject.getString("username"));
          localCommentItem.setDate(new Date(Long.valueOf(localJSONObject.getString("create")).longValue()));
          localCommentItem.setAvatarUrl(localJSONObject.getString("avatar"));
          localCommentItem.setText(localJSONObject.getString("text"));
          try
          {
            localCommentItem.setTrackId(Long.valueOf(localJSONObject.getString("parent_id")).longValue());
          }
          catch (NumberFormatException localNumberFormatException1)
          {
            try
            {
              while (true)
              {
                localCommentItem.setReplyId(Integer.valueOf(localJSONObject.getString("reply_id")).intValue());
                localCommentItem.setCommentsCount(Integer.valueOf(localJSONObject.getString("total_comments")).intValue());
                localArrayList.add(localCommentItem);
                i++;
                break;
                localNumberFormatException1 = localNumberFormatException1;
                Log.e("", "");
              }
            }
            catch (NumberFormatException localNumberFormatException2)
            {
              while (true)
                Log.e("", "");
            }
          }
        }
      return localArrayList;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  public static CommentItem parseSingleCommentUrl(String paramString)
  {
    try
    {
      Log.e("URLTAG", paramString);
      String str = loadURLData(paramString);
      if (str == null)
        return null;
      if (str.length() == 0)
        return null;
      JSONObject localJSONObject = new JSONObject(str).getJSONArray("data").getJSONObject(0);
      CommentItem localCommentItem = new CommentItem();
      localCommentItem.setId(Long.valueOf(localJSONObject.getString("id")).longValue());
      localCommentItem.setAuthor(localJSONObject.getString("username"));
      localCommentItem.setDate(new Date(Long.valueOf(localJSONObject.getString("create")).longValue()));
      localCommentItem.setAvatarUrl(localJSONObject.getString("avatar"));
      localCommentItem.setText(localJSONObject.getString("text"));
      try
      {
        localCommentItem.setTrackId(Long.valueOf(localJSONObject.getString("parent_id")).longValue());
      }
      catch (NumberFormatException localNumberFormatException1)
      {
        try
        {
          while (true)
          {
            localCommentItem.setReplyId(Integer.valueOf(localJSONObject.getString("reply_id")).intValue());
            try
            {
              localCommentItem.setCommentsCount(Integer.valueOf(localJSONObject.getString("total_comments")).intValue());
              return localCommentItem;
            }
            catch (Exception localException)
            {
              Log.d("", "");
              return localCommentItem;
            }
            localNumberFormatException1 = localNumberFormatException1;
            Log.e("", "");
          }
        }
        catch (NumberFormatException localNumberFormatException2)
        {
          while (true)
            Log.e("", "");
        }
      }
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.JSONParser
 * JD-Core Version:    0.6.0
 */