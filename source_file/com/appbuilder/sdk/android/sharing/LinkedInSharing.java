package com.appbuilder.sdk.android.sharing;

import android.app.Activity;
import android.util.Log;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class LinkedInSharing
{
  private static final String logname = "LinkedIn";

  public static String createSharingUrl()
  {
    String str = String.format("https://api.linkedin.com/v1/people/~/shares?oauth2_access_token=%s", new Object[] { Authorization.linkedinUser.getAccessToken() });
    Log.d("LinkedIn", "createSharingUrl = " + str);
    return str;
  }

  public static String createXML(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "";
    if (paramString3 == null)
      paramString3 = "";
    if (paramString4 == null)
      paramString4 = "";
    String str = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?><share><comment>%s</comment><content><title>%s</title><description>%s</description><submitted-url>%s</submitted-url><submitted-image-url>%s</submitted-image-url></content><visibility><code>anyone</code></visibility></share>", new Object[] { paramString2, paramString1, paramString3, "ibuildapp.com", paramString4 });
    Log.d("LinkedIn", "createXML" + str);
    return str;
  }

  public static void share(String paramString1, String paramString2, Integer paramInteger, String paramString3, Activity paramActivity, String paramString4)
    throws IllegalStateException
  {
    Log.d("LinkedIn", "onClick");
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpPost localHttpPost = new HttpPost(createSharingUrl());
    try
    {
      StringEntity localStringEntity = new StringEntity(createXML(paramString1, paramString2, paramString4, paramString3), "UTF-8");
      localStringEntity.setContentType("application/xml");
      localHttpPost.setHeader("Content-Type", "application/xml;charset=UTF-8");
      localHttpPost.setEntity(localStringEntity);
      localDefaultHttpClient.execute(localHttpPost);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.sharing.LinkedInSharing
 * JD-Core Version:    0.6.0
 */