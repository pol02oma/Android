package com.ibuildapp.romanblack.PhotoGalleryPlugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.CommentItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.JSONParser;

public class PushBroadcast extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("app_id");
    String str2 = paramIntent.getStringExtra("module_id");
    String str3 = paramIntent.getStringExtra("comment_id");
    try
    {
      CommentItem localCommentItem2 = JSONParser.parseSingleCommentUrl(Statics.BASE_URL + "/getcommentbyid/" + str3 + "/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken);
      localCommentItem1 = localCommentItem2;
      Statics.onCommentPushed(str1, str2, localCommentItem1);
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.d("", "");
        CommentItem localCommentItem1 = null;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.PushBroadcast
 * JD-Core Version:    0.6.0
 */