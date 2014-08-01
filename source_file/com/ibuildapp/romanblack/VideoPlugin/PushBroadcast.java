package com.ibuildapp.romanblack.VideoPlugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;

public class PushBroadcast extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.d("", "");
    String str1 = paramIntent.getStringExtra("app_id");
    String str2 = paramIntent.getStringExtra("module_id");
    String str3 = paramIntent.getStringExtra("comment_id");
    try
    {
      CommentItem localCommentItem2 = JSONParser.parseSingleCommentUrl(Statics.BASE_URL + "/getcommentbyid/" + str3 + "/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken);
      localCommentItem1 = localCommentItem2;
      localUser = Authorization.getAuthorizedUser();
      if ((localCommentItem1 == null) || (localUser == null));
    }
    catch (Exception localException1)
    {
      CommentItem localCommentItem1;
      try
      {
        User localUser;
        if (localCommentItem1.getAccountType() == localUser.getAccountType())
        {
          String str4 = localCommentItem1.getAccountId();
          String str5 = localUser.getAccountId();
          if (str4 == str5)
          {
            return;
            localException1 = localException1;
            Log.d("", "");
            localCommentItem1 = null;
          }
        }
      }
      catch (Exception localException2)
      {
      }
      Statics.onCommentPushed(str1, str2, localCommentItem1);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.PushBroadcast
 * JD-Core Version:    0.6.0
 */