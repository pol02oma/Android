package com.ibuildapp.romanblack.VideoPlugin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Secure;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import com.flurry.android.FlurryAgent;
import com.ibuildapp.romanblack.VideoPlugin.callbacks.OnAuthListener;
import com.ibuildapp.romanblack.VideoPlugin.callbacks.OnCommentPushedListener;
import com.ibuildapp.romanblack.VideoPlugin.callbacks.OnPostListener;
import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class VideoPlugin extends AppBuilderModuleMain
  implements AdapterView.OnItemClickListener, View.OnClickListener, MediaAdapter.FBLikePressedListener, MediaAdapter.SharePressedListener, OnPostListener, OnAuthListener, OnCommentPushedListener
{
  public static final int VIDEO_PLAYER = 10002;
  public static String userID = null;
  private final int COLORS_RECIEVED = 8;
  private final int FACEBOOK_AUTH = 10000;
  private final int GET_OG_LIKES = 7;
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int INITIALIZATION_FAILED = 0;
  private final int LOADING_ABORTED = 1;
  private final int PING = 6;
  private final int REFRESH_LIST = 5;
  private final int SHOW_MEDIA_LIST = 4;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private final int TWITTER_AUTH = 10001;
  private ACTIONS action = ACTIONS.ACTION_NONE;
  private MediaAdapter adapter = null;
  private String cachePath = "";
  private boolean destroyed = false;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        Toast.makeText(VideoPlugin.this, VideoPlugin.this.getResources().getString(R.string.romanblack_video_alert_cannot_init), 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            VideoPlugin.this.finish();
          }
        }
        , 2000L);
        return;
      case 1:
        VideoPlugin.this.closeActivity();
        return;
      case 2:
        VideoPlugin.this.showProgressDialog();
        return;
      case 3:
        VideoPlugin.this.hideProgressDialog();
        return;
      case 4:
        VideoPlugin.this.showMediaList();
        return;
      case 5:
        VideoPlugin.this.refreshList();
        return;
      case 6:
        VideoPlugin.this.ping();
        return;
      case 7:
        VideoPlugin.this.getOgLikes();
        return;
      case 8:
      }
      VideoPlugin.this.colorsRecieved();
    }
  };
  private TextView homeBtn = null;
  private ArrayList<VideoItem> items = new ArrayList();
  private int likePosition = 0;
  private ListView listView = null;
  private LinearLayout mainLayout = null;
  private boolean needMenu = false;
  private ProgressDialog progressDialog = null;
  private int sharingPosition = 0;
  private Widget widget = null;

  private void closeActivity()
  {
    hideProgressDialog();
    finish();
  }

  private void colorsRecieved()
  {
    this.mainLayout.setBackgroundColor(Statics.color1);
  }

  private void getOgLikes()
  {
    if (Authorization.isAuthorized(1))
      new Thread(new Runnable()
      {
        public void run()
        {
          ArrayList localArrayList = JSONParser.getUserOgLikes();
          int i = 0;
          if (i < VideoPlugin.this.items.size())
            for (int j = 0; ; j++)
            {
              if (j < localArrayList.size())
              {
                if (!((VideoItem)VideoPlugin.this.items.get(i)).getUrl().equalsIgnoreCase((String)localArrayList.get(j)))
                  continue;
                ((VideoItem)VideoPlugin.this.items.get(i)).setLiked(true);
              }
              i++;
              break;
            }
          VideoPlugin.this.handler.sendEmptyMessage(5);
        }
      }).start();
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void like(int paramInt)
  {
    new Thread(new Runnable(paramInt)
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: new 37	java/net/URL
        //   3: dup
        //   4: ldc 39
        //   6: invokespecial 42	java/net/URL:<init>	(Ljava/lang/String;)V
        //   9: invokevirtual 46	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   12: checkcast 48	java/net/HttpURLConnection
        //   15: astore 7
        //   17: aload 7
        //   19: ldc 50
        //   21: ldc 52
        //   23: invokevirtual 56	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
        //   26: aload 7
        //   28: ldc 58
        //   30: invokevirtual 61	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
        //   33: aload 7
        //   35: iconst_1
        //   36: invokevirtual 65	java/net/HttpURLConnection:setDoOutput	(Z)V
        //   39: new 67	java/lang/StringBuilder
        //   42: dup
        //   43: invokespecial 68	java/lang/StringBuilder:<init>	()V
        //   46: astore 8
        //   48: aload 8
        //   50: ldc 70
        //   52: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   55: pop
        //   56: aload 8
        //   58: ldc 58
        //   60: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   63: pop
        //   64: aload 8
        //   66: ldc 76
        //   68: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   71: pop
        //   72: aload 8
        //   74: ldc 78
        //   76: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   79: pop
        //   80: aload 8
        //   82: iconst_1
        //   83: invokestatic 84	com/appbuilder/sdk/android/authorization/Authorization:getAuthorizedUser	(I)Lcom/appbuilder/sdk/android/authorization/entities/User;
        //   86: invokevirtual 90	com/appbuilder/sdk/android/authorization/entities/User:getAccessToken	()Ljava/lang/String;
        //   89: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   92: pop
        //   93: aload 8
        //   95: ldc 76
        //   97: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: pop
        //   101: aload 8
        //   103: ldc 92
        //   105: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: pop
        //   109: aload 8
        //   111: aload_0
        //   112: getfield 19	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin$12:this$0	Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;
        //   115: invokestatic 96	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin:access$1000	(Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;)Ljava/util/ArrayList;
        //   118: aload_0
        //   119: getfield 21	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin$12:val$position	I
        //   122: invokevirtual 102	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   125: checkcast 104	com/ibuildapp/romanblack/VideoPlugin/VideoItem
        //   128: invokevirtual 107	com/ibuildapp/romanblack/VideoPlugin/VideoItem:getUrl	()Ljava/lang/String;
        //   131: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   134: pop
        //   135: aload 8
        //   137: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   140: astore 17
        //   142: aload 7
        //   144: invokevirtual 114	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
        //   147: aload 17
        //   149: ldc 116
        //   151: invokevirtual 122	java/lang/String:getBytes	(Ljava/lang/String;)[B
        //   154: invokevirtual 128	java/io/OutputStream:write	([B)V
        //   157: aload 7
        //   159: invokevirtual 132	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
        //   162: astore 30
        //   164: new 67	java/lang/StringBuilder
        //   167: dup
        //   168: invokespecial 68	java/lang/StringBuilder:<init>	()V
        //   171: astore 31
        //   173: new 134	java/io/BufferedReader
        //   176: dup
        //   177: new 136	java/io/InputStreamReader
        //   180: dup
        //   181: aload 30
        //   183: invokespecial 139	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   186: sipush 1000
        //   189: invokespecial 142	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
        //   192: astore 32
        //   194: aload 32
        //   196: invokevirtual 145	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   199: astore 33
        //   201: aload 33
        //   203: ifnull +21 -> 224
        //   206: aload 31
        //   208: aload 33
        //   210: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   213: pop
        //   214: aload 32
        //   216: invokevirtual 145	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   219: astore 33
        //   221: goto -20 -> 201
        //   224: aload 30
        //   226: invokevirtual 150	java/io/InputStream:close	()V
        //   229: aload 31
        //   231: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   234: astore 35
        //   236: aload 35
        //   238: astore 25
        //   240: new 152	org/json/JSONObject
        //   243: dup
        //   244: aload 25
        //   246: invokespecial 153	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   249: ldc 155
        //   251: invokevirtual 159	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   254: pop
        //   255: aload_0
        //   256: getfield 19	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin$12:this$0	Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;
        //   259: invokestatic 96	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin:access$1000	(Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;)Ljava/util/ArrayList;
        //   262: aload_0
        //   263: getfield 21	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin$12:val$position	I
        //   266: invokevirtual 102	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   269: checkcast 104	com/ibuildapp/romanblack/VideoPlugin/VideoItem
        //   272: iconst_1
        //   273: aload_0
        //   274: getfield 19	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin$12:this$0	Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;
        //   277: invokestatic 96	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin:access$1000	(Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;)Ljava/util/ArrayList;
        //   280: aload_0
        //   281: getfield 21	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin$12:val$position	I
        //   284: invokevirtual 102	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   287: checkcast 104	com/ibuildapp/romanblack/VideoPlugin/VideoItem
        //   290: invokevirtual 163	com/ibuildapp/romanblack/VideoPlugin/VideoItem:getLikesCount	()I
        //   293: iadd
        //   294: invokevirtual 166	com/ibuildapp/romanblack/VideoPlugin/VideoItem:setLikesCount	(I)V
        //   297: aload_0
        //   298: getfield 19	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin$12:this$0	Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;
        //   301: invokestatic 96	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin:access$1000	(Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;)Ljava/util/ArrayList;
        //   304: aload_0
        //   305: getfield 21	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin$12:val$position	I
        //   308: invokevirtual 102	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   311: checkcast 104	com/ibuildapp/romanblack/VideoPlugin/VideoItem
        //   314: iconst_1
        //   315: invokevirtual 169	com/ibuildapp/romanblack/VideoPlugin/VideoItem:setLiked	(Z)V
        //   318: aload_0
        //   319: getfield 19	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin$12:this$0	Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;
        //   322: invokestatic 173	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin:access$1100	(Lcom/ibuildapp/romanblack/VideoPlugin/VideoPlugin;)Landroid/os/Handler;
        //   325: iconst_5
        //   326: invokevirtual 179	android/os/Handler:sendEmptyMessage	(I)Z
        //   329: pop
        //   330: ldc 181
        //   332: ldc 181
        //   334: invokestatic 187	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   337: pop
        //   338: return
        //   339: astore 18
        //   341: aload 7
        //   343: invokevirtual 190	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
        //   346: astore 19
        //   348: new 67	java/lang/StringBuilder
        //   351: dup
        //   352: invokespecial 68	java/lang/StringBuilder:<init>	()V
        //   355: astore 20
        //   357: new 134	java/io/BufferedReader
        //   360: dup
        //   361: new 136	java/io/InputStreamReader
        //   364: dup
        //   365: aload 19
        //   367: invokespecial 139	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   370: sipush 1000
        //   373: invokespecial 142	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
        //   376: astore 21
        //   378: aload 21
        //   380: invokevirtual 145	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   383: astore 22
        //   385: aload 22
        //   387: ifnull +21 -> 408
        //   390: aload 20
        //   392: aload 22
        //   394: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   397: pop
        //   398: aload 21
        //   400: invokevirtual 145	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   403: astore 22
        //   405: goto -20 -> 385
        //   408: aload 19
        //   410: invokevirtual 150	java/io/InputStream:close	()V
        //   413: aload 20
        //   415: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   418: astore 24
        //   420: aload 24
        //   422: astore 25
        //   424: goto -184 -> 240
        //   427: astore 5
        //   429: ldc 181
        //   431: ldc 181
        //   433: invokestatic 187	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   436: pop
        //   437: return
        //   438: astore_3
        //   439: ldc 181
        //   441: ldc 181
        //   443: invokestatic 187	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   446: pop
        //   447: return
        //   448: astore_1
        //   449: ldc 181
        //   451: ldc 181
        //   453: invokestatic 187	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   456: pop
        //   457: return
        //   458: astore 26
        //   460: goto -130 -> 330
        //
        // Exception table:
        //   from	to	target	type
        //   157	201	339	java/io/FileNotFoundException
        //   206	221	339	java/io/FileNotFoundException
        //   224	236	339	java/io/FileNotFoundException
        //   0	157	427	java/net/MalformedURLException
        //   157	201	427	java/net/MalformedURLException
        //   206	221	427	java/net/MalformedURLException
        //   224	236	427	java/net/MalformedURLException
        //   240	330	427	java/net/MalformedURLException
        //   330	338	427	java/net/MalformedURLException
        //   341	385	427	java/net/MalformedURLException
        //   390	405	427	java/net/MalformedURLException
        //   408	420	427	java/net/MalformedURLException
        //   0	157	438	java/io/IOException
        //   157	201	438	java/io/IOException
        //   206	221	438	java/io/IOException
        //   224	236	438	java/io/IOException
        //   240	330	438	java/io/IOException
        //   330	338	438	java/io/IOException
        //   341	385	438	java/io/IOException
        //   390	405	438	java/io/IOException
        //   408	420	438	java/io/IOException
        //   0	157	448	java/lang/Exception
        //   157	201	448	java/lang/Exception
        //   206	221	448	java/lang/Exception
        //   224	236	448	java/lang/Exception
        //   240	330	448	java/lang/Exception
        //   330	338	448	java/lang/Exception
        //   341	385	448	java/lang/Exception
        //   390	405	448	java/lang/Exception
        //   408	420	448	java/lang/Exception
        //   240	330	458	org/json/JSONException
      }
    }).start();
  }

  private void ping()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        localBasicHttpParams.setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
        try
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(Statics.BASE_URL);
          localStringBuilder.append("/");
          HttpPost localHttpPost = new HttpPost(localStringBuilder.toString());
          String str = Utils.md5(Settings.Secure.getString(VideoPlugin.this.getContentResolver(), "android_id"));
          localMultipartEntity = new MultipartEntity();
          localMultipartEntity.addPart("action", new StringBody("ping", Charset.forName("UTF-8")));
          localMultipartEntity.addPart("platform", new StringBody("android", Charset.forName("UTF-8")));
          localMultipartEntity.addPart("app_id", new StringBody(Statics.APP_ID, Charset.forName("UTF-8")));
          localMultipartEntity.addPart("module_id", new StringBody(Statics.MODULE_ID, Charset.forName("UTF-8")));
          localMultipartEntity.addPart("device", new StringBody(str, Charset.forName("UTF-8")));
          if (Authorization.getAuthorizedUser() != null)
          {
            localMultipartEntity.addPart("account_id", new StringBody(Authorization.getAuthorizedUser().getAccountId(), Charset.forName("UTF-8")));
            if (Authorization.getAuthorizedUser().getAccountType() != User.ACCOUNT_TYPES.FACEBOOK)
              break label327;
            localMultipartEntity.addPart("account_type", new StringBody("facebook", Charset.forName("UTF-8")));
          }
          while (true)
          {
            localHttpPost.setEntity(localMultipartEntity);
            ((String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler()));
            Log.d("", "");
            if (!VideoPlugin.this.destroyed)
              VideoPlugin.this.handler.sendEmptyMessageDelayed(6, 30000L);
            return;
            label327: if (Authorization.getAuthorizedUser().getAccountType() != User.ACCOUNT_TYPES.TWITTER)
              break;
            localMultipartEntity.addPart("account_type", new StringBody("twitter", Charset.forName("UTF-8")));
          }
        }
        catch (Exception localException)
        {
          while (true)
          {
            MultipartEntity localMultipartEntity;
            Log.d("", "");
            continue;
            localMultipartEntity.addPart("account_type", new StringBody("ibuildapp", Charset.forName("UTF-8")));
          }
        }
      }
    }).start();
  }

  // ERROR //
  private String readXmlFromFile(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 248	com/ibuildapp/romanblack/VideoPlugin/VideoPlugin:getApplicationContext	()Landroid/content/Context;
    //   4: pop
    //   5: new 250	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   12: astore_3
    //   13: new 253	java/io/BufferedReader
    //   16: dup
    //   17: new 255	java/io/FileReader
    //   20: dup
    //   21: new 257	java/io/File
    //   24: dup
    //   25: aload_1
    //   26: invokespecial 260	java/io/File:<init>	(Ljava/lang/String;)V
    //   29: invokespecial 263	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   32: invokespecial 266	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   35: astore 4
    //   37: aload 4
    //   39: invokevirtual 270	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   42: astore 7
    //   44: aload 7
    //   46: ifnull +20 -> 66
    //   49: aload_3
    //   50: aload 7
    //   52: invokevirtual 274	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: pop
    //   56: goto -19 -> 37
    //   59: astore 6
    //   61: aload_3
    //   62: invokevirtual 277	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: areturn
    //   66: goto -5 -> 61
    //   69: astore 10
    //   71: goto -10 -> 61
    //   74: astore 5
    //   76: goto -15 -> 61
    //   79: astore 9
    //   81: goto -20 -> 61
    //
    // Exception table:
    //   from	to	target	type
    //   37	44	59	java/io/FileNotFoundException
    //   49	56	59	java/io/FileNotFoundException
    //   13	37	69	java/io/IOException
    //   37	44	74	java/io/IOException
    //   49	56	74	java/io/IOException
    //   13	37	79	java/io/FileNotFoundException
  }

  private void refreshList()
  {
    try
    {
      this.adapter.notifyDataSetChanged();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void shareFacebook(int paramInt)
  {
    Intent localIntent = new Intent(this, SharingActivity.class);
    localIntent.putExtra("type", "facebook");
    localIntent.putExtra("link", ((VideoItem)this.items.get(paramInt)).getUrl());
    localIntent.putExtra("item", (Serializable)this.items.get(paramInt));
    startActivity(localIntent);
  }

  private void shareTwitter(int paramInt)
  {
    Intent localIntent = new Intent(this, SharingActivity.class);
    localIntent.putExtra("type", "twitter");
    localIntent.putExtra("link", ((VideoItem)this.items.get(paramInt)).getUrl());
    localIntent.putExtra("item", (Serializable)this.items.get(paramInt));
    startActivity(localIntent);
  }

  private void showMediaList()
  {
    if (this.items.isEmpty())
      return;
    this.adapter = new MediaAdapter(this, this.items, this.widget);
    this.adapter.setCachePath(this.cachePath);
    this.adapter.setfBLikePressedListener(this);
    this.adapter.setSharePressedListener(this);
    this.listView.setAdapter(this.adapter);
    Statics.onCommentPushedListeners.add(this);
    this.listView.setOnItemClickListener(this);
    this.handler.sendEmptyMessage(7);
    new Thread(new Runnable()
    {
      public void run()
      {
        HashMap localHashMap1 = JSONParser.getVideoCommentsCount();
        int i = 0;
        while (true)
          if (i < VideoPlugin.this.items.size())
            try
            {
              int m = Integer.parseInt((String)localHashMap1.get(((VideoItem)VideoPlugin.this.items.get(i)).getId() + ""));
              k = m;
              ((VideoItem)VideoPlugin.this.items.get(i)).setTotalComments(k);
              i++;
            }
            catch (Exception localException2)
            {
              while (true)
              {
                Log.d("", "");
                int k = 0;
              }
            }
        VideoPlugin.this.handler.sendEmptyMessage(5);
        HashMap localHashMap2 = JSONParser.getVideoLikesCount(VideoPlugin.this.items);
        int j = 0;
        while (true)
          if (j < VideoPlugin.this.items.size())
            try
            {
              String str = (String)localHashMap2.get(((VideoItem)VideoPlugin.this.items.get(j)).getUrl());
              ((VideoItem)VideoPlugin.this.items.get(j)).setLikesCount(Integer.parseInt(str));
              j++;
            }
            catch (Exception localException1)
            {
              while (true)
                Log.d("", "");
            }
        VideoPlugin.this.handler.sendEmptyMessage(5);
      }
    }).start();
    this.handler.sendEmptyMessage(3);
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
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_loading), true);
      this.progressDialog.setCancelable(true);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          VideoPlugin.this.handler.sendEmptyMessage(1);
        }
      });
    }
  }

  public void create()
  {
    Log.d("", "");
    setContentView(R.layout.romanblack_video_main);
    setTopBarTitle(getString(R.string.romanblack_video_main_capture));
    if (!((Boolean)getIntent().getExtras().getSerializable("showSideBar")).booleanValue())
      setTopBarLeftButtonText(getResources().getString(R.string.common_home_upper), true, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          VideoPlugin.this.finish();
        }
      });
    try
    {
      String str1 = getPackageName();
      String str2 = str1.substring(1 + str1.lastIndexOf("."));
      userID = str2.substring(1 + str2.indexOf("u"), str2.indexOf("p"));
      label120: Intent localIntent = getIntent();
      this.widget = ((Widget)localIntent.getExtras().getSerializable("Widget"));
      if (this.widget == null)
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
      if (this.widget.getTitle().length() > 0)
        setTitle(this.widget.getTitle());
      if ((this.widget.getPluginXmlData().length() == 0) && (localIntent.getStringExtra("WidgetFile").length() == 0))
      {
        this.handler.sendEmptyMessageDelayed(0, 3000L);
        return;
      }
      if ((this.widget.getTitle() != null) && (this.widget.getTitle().length() != 0))
      {
        setTopBarTitle(this.widget.getTitle());
        NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
        if (localNetworkInfo == null)
          break label468;
        if (!localNetworkInfo.isConnectedOrConnecting())
          break label461;
        Statics.isOnline = true;
      }
      while (true)
      {
        this.homeBtn = ((TextView)findViewById(R.id.romanblack_fanwall_main_home));
        this.listView = ((ListView)findViewById(R.id.romanblack_video_main_listview));
        this.mainLayout = ((LinearLayout)findViewById(R.id.romanblack_video_main_layout));
        this.cachePath = (this.widget.getCachePath() + "/video-" + this.widget.getOrder());
        File localFile = new File(this.cachePath);
        if (!localFile.exists())
          localFile.mkdirs();
        this.handler.sendEmptyMessage(6);
        this.handler.sendEmptyMessage(2);
        new Thread()
        {
          public void run()
          {
            EntityParser localEntityParser;
            if (VideoPlugin.this.widget.getPluginXmlData() != null)
              if (VideoPlugin.this.widget.getPluginXmlData().length() > 0)
                localEntityParser = new EntityParser(VideoPlugin.this.widget.getPluginXmlData());
            while (true)
            {
              localEntityParser.parse();
              VideoPlugin.access$1002(VideoPlugin.this, localEntityParser.getItems());
              Statics.APP_ID = localEntityParser.getAppId();
              Statics.APP_NAME = localEntityParser.getAppName();
              Statics.MODULE_ID = localEntityParser.getModuleId();
              Statics.sharingOn = localEntityParser.getSharingOn();
              Statics.commentsOn = localEntityParser.getCommentsOn();
              Statics.color1 = localEntityParser.getColor1();
              Statics.color2 = localEntityParser.getColor2();
              Statics.color3 = localEntityParser.getColor3();
              Statics.color4 = localEntityParser.getColor4();
              Statics.color5 = localEntityParser.getColor5();
              VideoPlugin.this.handler.sendEmptyMessage(8);
              for (int i = 0; i < VideoPlugin.this.items.size(); i++)
                ((VideoItem)VideoPlugin.this.items.get(i)).setTextColor(VideoPlugin.this.widget.getTextColor());
              localEntityParser = new EntityParser(VideoPlugin.this.readXmlFromFile(VideoPlugin.this.getIntent().getStringExtra("WidgetFile")));
              continue;
              localEntityParser = new EntityParser(VideoPlugin.this.readXmlFromFile(VideoPlugin.this.getIntent().getStringExtra("WidgetFile")));
            }
            String[] arrayOfString = new File(VideoPlugin.this.cachePath).list();
            if (arrayOfString != null)
              for (int j = 0; j < arrayOfString.length; j++)
              {
                String str = arrayOfString[j];
                if (0 != 0)
                  continue;
                new File(VideoPlugin.this.cachePath + "/" + str).delete();
              }
            VideoPlugin.this.handler.sendEmptyMessage(4);
          }
        }
        .start();
        Statics.onAuthListeners.add(this);
        Statics.onPostListeners.add(this);
        return;
        setTopBarTitle(getResources().getString(R.string.romanblack_video_main_capture));
        break;
        label461: Statics.isOnline = false;
        continue;
        label468: Statics.isOnline = false;
      }
    }
    catch (Throwable localThrowable)
    {
      break label120;
    }
  }

  public void destroy()
  {
    this.destroyed = true;
    try
    {
      Statics.onAuthListeners.remove(this);
      Statics.onPostListeners.remove(this);
      Statics.onCommentPushedListeners.remove(this);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 10000)
      if ((paramInt2 == -1) && (Authorization.isAuthorized(1)))
      {
        if (this.action != ACTIONS.FACEBOOK_LIKE)
          break label49;
        like(this.likePosition);
      }
    label49: 
    do
      while (true)
      {
        while (true)
        {
          onAuth();
          return;
          if (this.action != ACTIONS.FACEBOOK_SHARE)
            continue;
          shareFacebook(this.sharingPosition);
        }
        if (paramInt1 != 10001)
          break;
        if ((paramInt2 != -1) || (Authorization.getAuthorizedUser(2) == null))
          continue;
        shareTwitter(this.sharingPosition);
        return;
      }
    while (paramInt1 != 10002);
    while (true)
    {
      int i;
      try
      {
        Bundle localBundle = paramIntent.getExtras();
        List localList = (List)localBundle.getSerializable("items");
        i = localBundle.getInt("position");
        if ((userID == null) || (!userID.equals("186589")))
          continue;
        FlurryAgent.endTimedEvent("VideoPlugin");
        if (paramInt2 != Statics.PLAY_NEXT_VIDEO)
          continue;
        if (i + 1 >= localList.size())
        {
          k = 0;
          startPlayer(k);
          return;
          if (paramInt2 != 1001)
            break;
          if (i - 1 >= 0)
            continue;
          int j = -1 + localList.size();
          startPlayer(j);
          return;
          j = i - 1;
          continue;
        }
      }
      catch (Exception localException)
      {
        return;
      }
      int k = i + 1;
    }
  }

  public void onAuth()
  {
    this.handler.removeMessages(6);
    this.handler.sendEmptyMessage(6);
    this.handler.sendEmptyMessage(7);
  }

  public void onClick(View paramView)
  {
    if (paramView == this.homeBtn)
      finish();
  }

  public void onCommentPushed(CommentItem paramCommentItem)
  {
    if ((paramCommentItem == null) || (paramCommentItem.getReplyId() != 0L))
      return;
    for (int i = 0; i < this.items.size(); i++)
    {
      if (((VideoItem)this.items.get(i)).getId() != paramCommentItem.getTrackId())
        continue;
      ((VideoItem)this.items.get(i)).setTotalComments(1 + ((VideoItem)this.items.get(i)).getTotalComments());
    }
    this.handler.sendEmptyMessage(5);
  }

  public void onCommentsUpdate(VideoItem paramVideoItem, CommentItem paramCommentItem, int paramInt1, int paramInt2, ArrayList<CommentItem> paramArrayList)
  {
    for (int i = 0; ; i++)
    {
      if (i < this.items.size())
      {
        VideoItem localVideoItem = (VideoItem)this.items.get(i);
        if (paramVideoItem.getId() != localVideoItem.getId())
          continue;
        localVideoItem.setTotalComments(paramInt1);
        this.handler.sendEmptyMessageDelayed(5, 100L);
      }
      return;
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.add("Facebook").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        if (Authorization.isAuthorized(1))
          VideoPlugin.this.shareFacebook(VideoPlugin.this.sharingPosition);
        while (true)
        {
          VideoPlugin.access$1602(VideoPlugin.this, false);
          return true;
          VideoPlugin.access$1502(VideoPlugin.this, VideoPlugin.ACTIONS.FACEBOOK_SHARE);
          Authorization.authorize(VideoPlugin.this, 10000, 1);
        }
      }
    });
    paramMenu.add("Twitter").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        if (Authorization.getAuthorizedUser(2) != null)
          VideoPlugin.this.shareTwitter(VideoPlugin.this.sharingPosition);
        while (true)
        {
          VideoPlugin.access$1602(VideoPlugin.this, false);
          return true;
          Authorization.authorize(VideoPlugin.this, 10001, 2);
        }
      }
    });
    paramMenu.add("Email").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        String str = VideoPlugin.this.getResources().getString(R.string.romanblack_video_sharingsms_first_part) + " " + ((VideoItem)VideoPlugin.this.items.get(VideoPlugin.this.sharingPosition)).getUrl() + " " + VideoPlugin.this.getResources().getString(R.string.romanblack_video_sharingsms_second_part) + " " + Statics.APP_NAME + " " + VideoPlugin.this.getResources().getString(R.string.romanblack_video_sharingsms_third_part) + Statics.APP_NAME + " " + VideoPlugin.this.getResources().getString(R.string.romanblack_video_sharingsms_fourth_part) + " " + "http://ibuildapp.com/projects.php?action=info&projectid=" + Statics.APP_ID;
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/html");
        localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml(str));
        VideoPlugin.this.startActivity(localIntent);
        VideoPlugin.access$1602(VideoPlugin.this, false);
        return true;
      }
    });
    paramMenu.add("SMS").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        String str = VideoPlugin.this.getResources().getString(R.string.romanblack_video_sharingsms_first_part) + " " + ((VideoItem)VideoPlugin.this.items.get(VideoPlugin.this.sharingPosition)).getUrl() + " " + VideoPlugin.this.getResources().getString(R.string.romanblack_video_sharingsms_second_part) + " " + Statics.APP_NAME + " " + VideoPlugin.this.getResources().getString(R.string.romanblack_video_sharingsms_third_part) + Statics.APP_NAME + " " + VideoPlugin.this.getResources().getString(R.string.romanblack_video_sharingsms_fourth_part) + " " + "http://ibuildapp.com/projects.php?action=info&projectid=" + Statics.APP_ID;
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:"));
        localIntent.putExtra("sms_body", str);
        VideoPlugin.this.startActivity(localIntent);
        VideoPlugin.access$1602(VideoPlugin.this, false);
        return true;
      }
    });
    paramMenu.add(getString(R.string.romanblack_video_cancel)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        VideoPlugin.access$1602(VideoPlugin.this, false);
        return true;
      }
    });
    return true;
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onLikePressed(int paramInt)
  {
    if (Authorization.isAuthorized(1))
    {
      like(paramInt);
      return;
    }
    this.action = ACTIONS.FACEBOOK_LIKE;
    this.likePosition = paramInt;
    Authorization.authorize(this, 10000, 1);
  }

  public void onOptionsMenuClosed(Menu paramMenu)
  {
    this.needMenu = false;
  }

  public void onPost()
  {
    this.handler.removeMessages(6);
    this.handler.sendEmptyMessage(6);
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return this.needMenu;
  }

  public void onSharePressed(int paramInt)
  {
    this.sharingPosition = paramInt;
    this.needMenu = true;
    openOptionsMenu();
  }

  public void resume()
  {
    this.handler.sendEmptyMessage(5);
    if ((userID != null) && (userID.equals("186589")))
      FlurryAgent.endTimedEvent("VideoPlugin");
  }

  public void start()
  {
  }

  void startPlayer(int paramInt)
  {
    if ((userID != null) && (userID.equals("186589")))
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("Watch", ((VideoItem)this.items.get(paramInt)).getTitle());
      FlurryAgent.logEvent("VideoPlugin", localHashMap, true);
    }
    if (((VideoItem)this.items.get(paramInt)).getUrl().contains("youtube.com"))
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com")).setData(Uri.parse(((VideoItem)this.items.get(paramInt)).getUrl())));
      return;
    }
    if (((VideoItem)this.items.get(paramInt)).getUrl().contains("vimeo.com"))
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((VideoItem)this.items.get(paramInt)).getUrl())));
      return;
    }
    if ((((VideoItem)this.items.get(paramInt)).getUrl().contains("m3u8")) || (((VideoItem)this.items.get(paramInt)).getUrl().contains("mp4")))
    {
      Intent localIntent1 = new Intent(this, VideoBuffer.class);
      localIntent1.putExtra("position", paramInt);
      localIntent1.putExtra("items", this.items);
      localIntent1.putExtra("Widget", this.widget);
      startActivityForResult(localIntent1, 10002);
      return;
    }
    Intent localIntent2 = new Intent(this, PlayerWebActivity.class);
    localIntent2.putExtra("position", paramInt);
    localIntent2.putExtra("items", this.items);
    localIntent2.putExtra("Widget", this.widget);
    startActivity(localIntent2);
  }

  private static enum ACTIONS
  {
    static
    {
      ACTION_NONE = new ACTIONS("ACTION_NONE", 2);
      ACTIONS[] arrayOfACTIONS = new ACTIONS[3];
      arrayOfACTIONS[0] = FACEBOOK_LIKE;
      arrayOfACTIONS[1] = FACEBOOK_SHARE;
      arrayOfACTIONS[2] = ACTION_NONE;
      $VALUES = arrayOfACTIONS;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.VideoPlugin
 * JD-Core Version:    0.6.0
 */