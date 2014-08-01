package com.ibuildapp.romanblack.VideoPlugin;

import android.graphics.Color;
import android.location.Location;
import com.ibuildapp.romanblack.VideoPlugin.callbacks.OnAuthListener;
import com.ibuildapp.romanblack.VideoPlugin.callbacks.OnCommentPushedListener;
import com.ibuildapp.romanblack.VideoPlugin.callbacks.OnPostListener;
import java.util.ArrayList;

public class Statics
{
  static String APP_ID;
  static String APP_NAME;
  static final String BASE_URL = "http://" + com.appbuilder.sdk.android.Statics.BASE_DOMEN + "/mdscr/video";
  static String FACEBOOK_APP_TOKEN;
  static String MODULE_ID;
  public static int PLAY_NEXT_VIDEO = 0;
  public static final int PLAY_PREV_VIDEO = 1001;
  static String canEdit;
  static int color1;
  static int color2;
  static int color3;
  static int color4;
  static int color5;
  static String commentsOn;
  static Location currentLocation;
  static boolean isOnline;
  static int moduleId;
  static float near = 180.0F;
  static ArrayList<OnAuthListener> onAuthListeners;
  static ArrayList<OnCommentPushedListener> onCommentPushedListeners;
  static ArrayList<OnPostListener> onPostListeners;
  static String sharingOn;

  static
  {
    moduleId = 0;
    canEdit = "all";
    sharingOn = "off";
    commentsOn = "off";
    color1 = Color.parseColor("#4d4948");
    color2 = Color.parseColor("#fff58d");
    color3 = Color.parseColor("#fff7a2");
    color4 = Color.parseColor("#ffffff");
    color5 = Color.parseColor("#bbbbbb");
    APP_ID = "0";
    MODULE_ID = "0";
    APP_NAME = "";
    FACEBOOK_APP_TOKEN = "";
    currentLocation = null;
    isOnline = false;
    onPostListeners = new ArrayList();
    onAuthListeners = new ArrayList();
    onCommentPushedListeners = new ArrayList();
  }

  static void onAuth()
  {
    if ((onAuthListeners != null) && (!onAuthListeners.isEmpty()))
      for (int i = 0; i < onAuthListeners.size(); i++)
        ((OnAuthListener)onAuthListeners.get(i)).onAuth();
  }

  static void onCommentPushed(String paramString1, String paramString2, CommentItem paramCommentItem)
  {
    if ((onCommentPushedListeners != null) && (!onCommentPushedListeners.isEmpty()))
      for (int i = 0; i < onCommentPushedListeners.size(); i++)
      {
        if ((!APP_ID.equals(paramString1)) || (!MODULE_ID.equals(paramString2)))
          continue;
        ((OnCommentPushedListener)onCommentPushedListeners.get(i)).onCommentPushed(paramCommentItem);
      }
  }

  static void onCommentsUpdate(VideoItem paramVideoItem, CommentItem paramCommentItem, int paramInt1, int paramInt2, ArrayList<CommentItem> paramArrayList)
  {
    if ((onCommentPushedListeners != null) && (!onCommentPushedListeners.isEmpty()))
      for (int i = 0; i < onCommentPushedListeners.size(); i++)
        ((OnCommentPushedListener)onCommentPushedListeners.get(i)).onCommentsUpdate(paramVideoItem, paramCommentItem, paramInt1, paramInt2, paramArrayList);
  }

  static void onPost()
  {
    if ((onPostListeners != null) && (!onPostListeners.isEmpty()))
      for (int i = 0; i < onPostListeners.size(); i++)
        ((OnPostListener)onPostListeners.get(i)).onPost();
  }

  static enum STATES
  {
    static
    {
      HAS_MESSAGES = new STATES("HAS_MESSAGES", 1);
      AUTHORIZATION_NO = new STATES("AUTHORIZATION_NO", 2);
      AUTHORIZATION_YES = new STATES("AUTHORIZATION_YES", 3);
      AUTHORIZATION_FACEBOOK = new STATES("AUTHORIZATION_FACEBOOK", 4);
      AUTHORIZATION_TWITTER = new STATES("AUTHORIZATION_TWITTER", 5);
      AUTHORIZATION_EMAIL = new STATES("AUTHORIZATION_EMAIL", 6);
      STATES[] arrayOfSTATES = new STATES[7];
      arrayOfSTATES[0] = NO_MESSAGES;
      arrayOfSTATES[1] = HAS_MESSAGES;
      arrayOfSTATES[2] = AUTHORIZATION_NO;
      arrayOfSTATES[3] = AUTHORIZATION_YES;
      arrayOfSTATES[4] = AUTHORIZATION_FACEBOOK;
      arrayOfSTATES[5] = AUTHORIZATION_TWITTER;
      arrayOfSTATES[6] = AUTHORIZATION_EMAIL;
      $VALUES = arrayOfSTATES;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.Statics
 * JD-Core Version:    0.6.0
 */