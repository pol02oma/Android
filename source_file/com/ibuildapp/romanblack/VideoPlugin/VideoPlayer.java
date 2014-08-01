package com.ibuildapp.romanblack.VideoPlugin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.BarDesigner.TitleDesign;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.ibuildapp.romanblack.VideoPlugin.callbacks.OnCommentPushedListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.http.util.ByteArrayBuffer;

public class VideoPlayer extends AppBuilderModuleMain
  implements View.OnClickListener, OnCommentPushedListener
{
  private final int AUTHORIZATION_ACTIVITY = 10002;
  private final int FACEBOOK_AUTH = 10000;
  private final int HIDE_LIKE_BUTTON = 7;
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int INITIALIZATION_FAILED = 0;
  private final int LOADING_ABORTED = 1;
  private final int NEED_INTERNET_CONNECTION = 5;
  private final int REFRESH_LIST = 6;
  private final int SEND_COMMENT_ACTIVITY = 10003;
  private final int SHOW_COMMENTS_LIST = 4;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private final int TWITTER_AUTH = 10001;
  private final int UPDATE_LIKE_COUNTER = 8;
  private ACTIONS action = ACTIONS.ACTION_NONE;
  private Intent actionIntent = null;
  private CommentsAdapter adapter = null;
  private LinearLayout bottomPanel = null;
  private String cachePath = "";
  private ArrayList<CommentItem> comments = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        Toast.makeText(VideoPlayer.this, VideoPlayer.this.getResources().getString(R.string.romanblack_video_alert_cannot_init), 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            VideoPlayer.this.finish();
          }
        }
        , 2000L);
        return;
      case 1:
        VideoPlayer.this.closeActivity();
        return;
      case 2:
        VideoPlayer.this.showProgressDialog();
        return;
      case 3:
        VideoPlayer.this.hideProgressDialog();
        return;
      case 4:
        VideoPlayer.this.showCommentsList();
        return;
      case 5:
        Toast.makeText(VideoPlayer.this, VideoPlayer.this.getResources().getString(R.string.romanblack_video_alert_no_internet), 1).show();
        return;
      case 6:
        VideoPlayer.this.refreshList();
        return;
      case 7:
        VideoPlayer.this.hideLikeButton();
        return;
      case 8:
      }
      VideoPlayer.this.updateLikeCounter();
    }
  };
  private View headerView = null;
  private VideoItem item = null;
  private ArrayList<VideoItem> items = null;
  private LinearLayout likeButton = null;
  private TextView likesCountTextView = null;
  private ListView listView = null;
  private LinearLayout mainLayout = null;
  private boolean needMenu = false;
  private int position = 0;
  private ImageView postCommentButton = null;
  private ProgressDialog progressDialog = null;
  private ImageView shareButton = null;
  private RelativeLayout videoPreview = null;
  private ImageView videoPreviewImageView = null;
  private TextView videoTitleTextView = null;
  private Widget widget = null;

  private void cacheMessages()
  {
    File localFile1 = new File(this.cachePath);
    if (!localFile1.exists())
      localFile1.mkdirs();
    File localFile2 = new File(this.cachePath + "/" + "ca-" + this.item.getId() + "-0");
    if (localFile2.exists())
      localFile2.delete();
    try
    {
      localFile2.createNewFile();
      localArrayList = new ArrayList();
      if ((this.comments.size() <= 20) && (!this.comments.isEmpty()))
        localArrayList = this.comments;
    }
    catch (IOException localIOException1)
    {
      try
      {
        ArrayList localArrayList;
        do
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
          ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
          localObjectOutputStream.writeObject(localArrayList);
          localObjectOutputStream.close();
          localFileOutputStream.close();
          return;
        }
        while (this.comments.size() <= 20);
        for (int i = 0; i < 20; i++)
          localArrayList.add(this.comments.get(i));
        localIOException1 = localIOException1;
      }
      catch (IOException localIOException2)
      {
      }
    }
  }

  private void closeActivity()
  {
    hideProgressDialog();
    finish();
  }

  private Bitmap decodeImageFile(String paramString)
  {
    try
    {
      int i = getResources().getDisplayMetrics().widthPixels;
      int j = (int)(150.0F * getResources().getDisplayMetrics().density);
      File localFile = new File(paramString);
      BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
      localOptions1.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions1);
      int k = localOptions1.outWidth;
      int m = localOptions1.outHeight;
      int n = 1;
      while (true)
      {
        if ((k / 2 < i) || (m / 2 < j))
        {
          BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
          localOptions2.inSampleSize = n;
          return BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions2);
        }
        k /= 2;
        m /= 2;
        n *= 2;
      }
    }
    catch (Exception localException)
    {
      Log.d("", "");
    }
    return null;
  }

  private void downloadComplete()
  {
    setThumb();
  }

  private void downloadRegistration(String paramString)
  {
    this.item.setCoverPath(paramString);
  }

  private void hideLikeButton()
  {
    ((ImageView)this.headerView.findViewById(R.id.romanblack_video_player_comments_header_like_image)).setAlpha(100);
    ((TextView)this.headerView.findViewById(R.id.romanblack_video_player_comments_header_like_caption)).setTextColor(Color.parseColor("#9bffffff"));
    this.likeButton.getBackground().setAlpha(100);
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void like()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL("https://graph.facebook.com/me/og.likes").openConnection();
          localHttpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7");
          localHttpURLConnection.setRequestMethod("POST");
          localHttpURLConnection.setDoOutput(true);
          StringBuilder localStringBuilder1 = new StringBuilder();
          localStringBuilder1.append("method=");
          localStringBuilder1.append("POST");
          localStringBuilder1.append("&");
          localStringBuilder1.append("access_token=");
          localStringBuilder1.append(Authorization.getAuthorizedUser(1).getAccessToken());
          localStringBuilder1.append("&");
          localStringBuilder1.append("object=");
          localStringBuilder1.append(VideoPlayer.this.item.getUrl());
          String str1 = localStringBuilder1.toString();
          localHttpURLConnection.getOutputStream().write(str1.getBytes("UTF-8"));
          try
          {
            InputStream localInputStream2 = localHttpURLConnection.getInputStream();
            StringBuilder localStringBuilder3 = new StringBuilder();
            BufferedReader localBufferedReader2 = new BufferedReader(new InputStreamReader(localInputStream2), 1000);
            for (String str3 = localBufferedReader2.readLine(); str3 != null; str3 = localBufferedReader2.readLine())
              localStringBuilder3.append(str3);
            localInputStream2.close();
            localStringBuilder3.toString();
            VideoPlayer.this.handler.sendEmptyMessage(8);
            Log.d("", "");
            VideoPlayer.this.handler.sendEmptyMessage(7);
            return;
          }
          catch (FileNotFoundException localFileNotFoundException)
          {
            while (true)
            {
              InputStream localInputStream1 = localHttpURLConnection.getErrorStream();
              StringBuilder localStringBuilder2 = new StringBuilder();
              BufferedReader localBufferedReader1 = new BufferedReader(new InputStreamReader(localInputStream1), 1000);
              for (String str2 = localBufferedReader1.readLine(); str2 != null; str2 = localBufferedReader1.readLine())
                localStringBuilder2.append(str2);
              localInputStream1.close();
              localStringBuilder2.toString();
            }
          }
        }
        catch (MalformedURLException localMalformedURLException)
        {
          Log.d("", "");
          return;
        }
        catch (IOException localIOException)
        {
          Log.d("", "");
          return;
        }
        catch (Exception localException)
        {
          Log.d("", "");
        }
      }
    }).start();
  }

  private void refreshList()
  {
    this.adapter.notifyDataSetChanged();
  }

  private void setThumb()
  {
    if ((this.videoPreviewImageView != null) && (this.item.getCoverPath().length() > 0));
    try
    {
      Bitmap localBitmap2 = decodeImageFile(this.item.getCoverPath());
      localBitmap1 = localBitmap2;
      if (localBitmap1 != null)
      {
        BitmapDrawable localBitmapDrawable = new BitmapDrawable(localBitmap1);
        this.videoPreviewImageView.setImageDrawable(localBitmapDrawable);
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.d("", "");
        Bitmap localBitmap1 = null;
      }
    }
  }

  private void shareFacebook()
  {
    Intent localIntent = new Intent(this, SharingActivity.class);
    localIntent.putExtra("type", "facebook");
    localIntent.putExtra("link", this.item.getUrl());
    localIntent.putExtra("item", this.item);
    startActivity(localIntent);
  }

  private void shareTwitter()
  {
    Intent localIntent = new Intent(this, SharingActivity.class);
    localIntent.putExtra("type", "twitter");
    localIntent.putExtra("link", this.item.getUrl());
    localIntent.putExtra("item", this.item);
    startActivity(localIntent);
  }

  private void showCommentsList()
  {
    if (this.comments == null)
      this.comments = new ArrayList();
    this.adapter = new CommentsAdapter(this, this.comments, this.item, this.widget);
    this.adapter.setCachePath(this.cachePath);
    this.listView.setAdapter(this.adapter);
    this.handler.sendEmptyMessage(3);
    cacheMessages();
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
          VideoPlayer.this.handler.sendEmptyMessage(1);
        }
      });
    }
  }

  private void updateLikeCounter()
  {
    this.item.setLikesCount(1 + this.item.getLikesCount());
    this.likesCountTextView.setText(this.item.getLikesCount() + "");
  }

  public void create()
  {
    setContentView(R.layout.romanblack_video_player);
    Intent localIntent = getIntent();
    this.items = ((ArrayList)localIntent.getSerializableExtra("items"));
    if ((this.items == null) || (this.items.isEmpty()))
    {
      this.handler.sendEmptyMessage(0);
      return;
    }
    this.position = localIntent.getIntExtra("position", 0);
    try
    {
      this.item = ((VideoItem)this.items.get(this.position));
      if (this.item == null)
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      this.handler.sendEmptyMessage(0);
      return;
    }
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null)
      if (localNetworkInfo.isConnectedOrConnecting())
        Statics.isOnline = true;
    while (true)
    {
      this.widget = ((Widget)localIntent.getSerializableExtra("Widget"));
      this.cachePath = localIntent.getStringExtra("cachePath");
      this.mainLayout = ((LinearLayout)findViewById(R.id.romanblack_video_player_main));
      this.mainLayout.setBackgroundColor(Statics.color1);
      if (!TextUtils.isEmpty(this.item.getTitle()))
      {
        setTopBarTitle(this.item.getTitle());
        label216: swipeBlock();
        setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            VideoPlayer.this.finish();
          }
        });
        this.headerView = LayoutInflater.from(this).inflate(R.layout.romanblack_video_player_comments_header, null);
        this.headerView.setBackgroundColor(Statics.color1);
        this.videoTitleTextView = ((TextView)this.headerView.findViewById(R.id.romanblack_video_player_video_title));
        this.videoTitleTextView.setText(this.item.getTitle());
        this.likesCountTextView = ((TextView)this.headerView.findViewById(R.id.romanblack_video_player_comments_header_likes_count));
        this.likesCountTextView.setText(this.item.getLikesCount() + "");
        this.likeButton = ((LinearLayout)this.headerView.findViewById(R.id.romanblack_video_player_comments_header_like_btn));
        this.likeButton.setOnClickListener(this);
        this.listView = ((ListView)findViewById(R.id.romanblack_video_player_listview));
        this.listView.setCacheColorHint(Color.parseColor("#41464b"));
        this.listView.setHeaderDividersEnabled(true);
        this.listView.addHeaderView(this.headerView, null, true);
        this.videoPreview = ((RelativeLayout)this.headerView.findViewById(R.id.romanblack_video_player_preview));
        this.videoPreview.setOnClickListener(this);
        this.videoPreviewImageView = ((ImageView)this.headerView.findViewById(R.id.romanblack_video_player_preview_img));
        ImageDownloadTask localImageDownloadTask = new ImageDownloadTask(null);
        VideoItem[] arrayOfVideoItem = new VideoItem[1];
        arrayOfVideoItem[0] = this.item;
        localImageDownloadTask.execute(arrayOfVideoItem);
        this.shareButton = ((ImageView)findViewById(R.id.romanblack_video_player_share_btn));
      }
      try
      {
        this.shareButton.setColorFilter(this.bottomBarDesign.leftButtonDesign.textColor);
        label527: this.shareButton.setOnClickListener(this);
        this.postCommentButton = ((ImageView)findViewById(R.id.romanblack_video_player_comment_btn));
        try
        {
          this.postCommentButton.setColorFilter(this.bottomBarDesign.leftButtonDesign.textColor);
          label566: this.postCommentButton.setOnClickListener(this);
          this.bottomPanel = ((LinearLayout)findViewById(R.id.romanblack_video_player_bottom_panel));
          if (!Statics.isOnline)
          {
            this.shareButton.setAlpha(100);
            this.postCommentButton.setAlpha(100);
            ((ImageView)this.headerView.findViewById(R.id.romanblack_video_player_comments_header_like_image)).setAlpha(100);
            ((TextView)this.headerView.findViewById(R.id.romanblack_video_player_comments_header_like_caption)).setTextColor(Color.parseColor("#9bffffff"));
            this.likeButton.getBackground().setAlpha(100);
          }
          if (this.item.isLiked())
            this.handler.sendEmptyMessage(7);
          if ((Statics.sharingOn.equalsIgnoreCase("off")) && (Statics.commentsOn.equalsIgnoreCase("off")))
            this.bottomPanel.setVisibility(8);
          while (true)
          {
            this.handler.sendEmptyMessage(2);
            new Thread(new Runnable()
            {
              public void run()
              {
                NetworkInfo localNetworkInfo = ((ConnectivityManager)VideoPlayer.this.getSystemService("connectivity")).getActiveNetworkInfo();
                boolean bool = false;
                if (localNetworkInfo != null)
                  bool = localNetworkInfo.isConnectedOrConnecting();
                if (bool)
                {
                  String str = Statics.BASE_URL + "/getcomments/" + com.appbuilder.sdk.android.Statics.appId + "/" + Statics.MODULE_ID + "/" + VideoPlayer.this.item.getId() + "/0/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken;
                  VideoPlayer.access$902(VideoPlayer.this, JSONParser.parseCommentsUrl(str));
                }
                while (true)
                {
                  if (VideoPlayer.this.comments != null)
                    Collections.reverse(VideoPlayer.this.comments);
                  VideoPlayer.this.handler.sendEmptyMessage(4);
                  return;
                  try
                  {
                    FileInputStream localFileInputStream = new FileInputStream(VideoPlayer.this.cachePath + "/" + "ca-" + VideoPlayer.this.item.getId() + "-0");
                    ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
                    VideoPlayer.access$902(VideoPlayer.this, (ArrayList)localObjectInputStream.readObject());
                    localObjectInputStream.close();
                    localFileInputStream.close();
                  }
                  catch (FileNotFoundException localFileNotFoundException)
                  {
                    Log.d("", "");
                  }
                  catch (IOException localIOException)
                  {
                    Log.d("", "");
                  }
                  catch (ClassNotFoundException localClassNotFoundException)
                  {
                    Log.d("", "");
                  }
                }
              }
            }).start();
            Statics.onCommentPushedListeners.add(this);
            return;
            Statics.isOnline = false;
            break;
            Statics.isOnline = false;
            break;
            setTopBarTitle(getString(R.string.romanblack_video_player_capture));
            break label216;
            if (Statics.sharingOn.equalsIgnoreCase("off"))
            {
              this.shareButton.setVisibility(4);
              continue;
            }
            if (!Statics.commentsOn.equalsIgnoreCase("off"))
              continue;
            this.postCommentButton.setVisibility(4);
          }
        }
        catch (NullPointerException localNullPointerException2)
        {
          break label566;
        }
      }
      catch (NullPointerException localNullPointerException1)
      {
        break label527;
      }
    }
  }

  public void destroy()
  {
    Statics.onCommentPushedListeners.remove(this);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 10000)
      if ((paramInt2 == -1) && (Authorization.getAuthorizedUser(1) != null))
      {
        if (this.action != ACTIONS.FACEBOOK_LIKE)
          break label41;
        like();
      }
    label40: label41: 
    do
      while (true)
      {
        break label40;
        do
          return;
        while (this.action != ACTIONS.FACEBOOK_SHARE);
        shareFacebook();
        return;
        if (paramInt1 == 10001)
        {
          if ((paramInt2 != -1) || (Authorization.getAuthorizedUser(2) == null))
            continue;
          shareTwitter();
          return;
        }
        if (paramInt1 != 10002)
          break;
        if ((paramInt2 != -1) || (this.action != ACTIONS.SEND_MESSAGE))
          continue;
        startActivityForResult(this.actionIntent, 10003);
        return;
      }
    while ((paramInt1 != 10003) || (paramInt2 != -1));
  }

  public void onClick(View paramView)
  {
    if (paramView == this.videoPreview)
      if (this.item.getUrl().contains("youtube.com"))
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com")).setData(Uri.parse(this.item.getUrl())));
    NetworkInfo localNetworkInfo1;
    do
    {
      do
      {
        return;
        if (this.item.getUrl().contains("vimeo.com"))
        {
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.item.getUrl())));
          return;
        }
        if (this.item.getUrl().contains("m3u8"))
        {
          Intent localIntent3 = new Intent(this, VideoBuffer.class);
          localIntent3.putExtra("position", this.position);
          localIntent3.putExtra("items", this.items);
          localIntent3.putExtra("Widget", this.widget);
          startActivity(localIntent3);
          return;
        }
        Intent localIntent4 = new Intent(this, PlayerWebActivity.class);
        localIntent4.putExtra("position", this.position);
        localIntent4.putExtra("items", this.items);
        localIntent4.putExtra("Widget", this.widget);
        startActivity(localIntent4);
        return;
        if (paramView == this.likeButton)
        {
          NetworkInfo localNetworkInfo2 = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
          boolean bool = false;
          if (localNetworkInfo2 != null)
            bool = localNetworkInfo2.isConnectedOrConnecting();
          if (bool)
          {
            if (Authorization.getAuthorizedUser(1) != null)
            {
              like();
              return;
            }
            Authorization.authorize(this, 10000, 1);
            return;
          }
          Toast.makeText(this, getResources().getString(R.string.romanblack_video_alert_like_need_internet), 1).show();
          return;
        }
        if (paramView != this.shareButton)
          continue;
        this.needMenu = true;
        openOptionsMenu();
        return;
      }
      while (paramView != this.postCommentButton);
      localNetworkInfo1 = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    }
    while (localNetworkInfo1 == null);
    if (localNetworkInfo1.isConnectedOrConnecting())
    {
      if (!Authorization.isAuthorized())
      {
        this.actionIntent = new Intent(this, SendMessageActivity.class);
        this.actionIntent.putExtra("user", Authorization.getAuthorizedUser());
        this.actionIntent.putExtra("Widget", this.widget);
        this.actionIntent.putExtra("video", this.item);
        this.action = ACTIONS.SEND_MESSAGE;
        Intent localIntent2 = new Intent(this, AuthorizationActivity.class);
        localIntent2.putExtra("Widget", this.widget);
        startActivityForResult(localIntent2, 10002);
        return;
      }
      Intent localIntent1 = new Intent(this, SendMessageActivity.class);
      localIntent1.putExtra("user", Authorization.getAuthorizedUser());
      localIntent1.putExtra("Widget", this.widget);
      localIntent1.putExtra("video", this.item);
      startActivityForResult(localIntent1, 10003);
      return;
    }
    this.handler.sendEmptyMessage(5);
  }

  public void onCommentPushed(CommentItem paramCommentItem)
  {
    if (paramCommentItem != null)
    {
      if ((paramCommentItem.getReplyId() != 0L) || (paramCommentItem.getTrackId() != this.item.getId()))
        break label78;
      if (this.comments == null)
        this.comments = new ArrayList();
      this.comments.add(paramCommentItem);
      if (this.comments.size() != 1)
        break label67;
    }
    label67: label78: 
    do
    {
      return;
      this.handler.sendEmptyMessage(6);
      return;
    }
    while ((paramCommentItem.getTrackId() != this.item.getId()) || (this.comments == null) || (this.comments.isEmpty()));
    for (int i = 0; ; i++)
    {
      if (i < this.comments.size())
      {
        if (((CommentItem)this.comments.get(i)).getId() != paramCommentItem.getId())
          continue;
        ((CommentItem)this.comments.get(i)).setCommentsCount(1 + ((CommentItem)this.comments.get(i)).getCommentsCount());
      }
      this.handler.sendEmptyMessage(6);
      return;
    }
  }

  public void onCommentsUpdate(VideoItem paramVideoItem, CommentItem paramCommentItem, int paramInt1, int paramInt2, ArrayList<CommentItem> paramArrayList)
  {
    if (paramVideoItem == null);
    do
      while (true)
      {
        return;
        if (paramCommentItem != null)
          break;
        if (paramVideoItem.getId() != this.item.getId())
          continue;
        this.comments = paramArrayList;
        Collections.reverse(this.comments);
        this.adapter.setItems(this.comments);
        this.handler.sendEmptyMessageDelayed(6, 100L);
        return;
      }
    while (paramVideoItem.getId() != this.item.getId());
    ArrayList localArrayList = this.comments;
    for (int i = 0; i < localArrayList.size(); i++)
    {
      if (((CommentItem)localArrayList.get(i)).getId() != paramCommentItem.getId())
        continue;
      ((CommentItem)localArrayList.get(i)).setCommentsCount(paramInt1);
    }
    this.handler.sendEmptyMessageDelayed(6, 100L);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.add("Facebook").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        if (Authorization.getAuthorizedUser(1) != null)
          VideoPlayer.this.shareFacebook();
        while (true)
        {
          VideoPlayer.access$1402(VideoPlayer.this, false);
          return true;
          VideoPlayer.access$1302(VideoPlayer.this, VideoPlayer.ACTIONS.FACEBOOK_SHARE);
          Authorization.authorize(VideoPlayer.this, 10000, 1);
        }
      }
    });
    paramMenu.add("Twitter").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        if (Authorization.getAuthorizedUser(2) != null)
          VideoPlayer.this.shareTwitter();
        while (true)
        {
          VideoPlayer.access$1402(VideoPlayer.this, false);
          return true;
          Authorization.authorize(VideoPlayer.this, 10001, 2);
        }
      }
    });
    paramMenu.add("Email").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        String str = VideoPlayer.this.getResources().getString(R.string.romanblack_video_sharingsms_first_part) + " " + VideoPlayer.this.item.getUrl() + " " + VideoPlayer.this.getResources().getString(R.string.romanblack_video_sharingsms_second_part) + " " + Statics.APP_NAME + " " + VideoPlayer.this.getResources().getString(R.string.romanblack_video_sharingsms_third_part) + Statics.APP_NAME + " " + VideoPlayer.this.getResources().getString(R.string.romanblack_video_sharingsms_fourth_part) + " " + "http://ibuildapp.com/projects.php?action=info&projectid=" + Statics.APP_ID;
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/html");
        localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml(str));
        VideoPlayer.this.startActivity(localIntent);
        VideoPlayer.access$1402(VideoPlayer.this, false);
        return true;
      }
    });
    paramMenu.add("SMS").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        String str = VideoPlayer.this.getResources().getString(R.string.romanblack_video_sharingsms_first_part) + " " + VideoPlayer.this.item.getUrl() + " " + VideoPlayer.this.getResources().getString(R.string.romanblack_video_sharingsms_second_part) + " " + Statics.APP_NAME + " " + VideoPlayer.this.getResources().getString(R.string.romanblack_video_sharingsms_third_part) + Statics.APP_NAME + " " + VideoPlayer.this.getResources().getString(R.string.romanblack_video_sharingsms_fourth_part) + " " + "http://ibuildapp.com/projects.php?action=info&projectid=" + Statics.APP_ID;
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:"));
        localIntent.putExtra("sms_body", str);
        VideoPlayer.this.startActivity(localIntent);
        VideoPlayer.access$1402(VideoPlayer.this, false);
        return true;
      }
    });
    paramMenu.add(getString(R.string.romanblack_video_cancel)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        VideoPlayer.access$1402(VideoPlayer.this, false);
        return true;
      }
    });
    return true;
  }

  public void onOptionsMenuClosed(Menu paramMenu)
  {
    this.needMenu = false;
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return this.needMenu;
  }

  private static enum ACTIONS
  {
    static
    {
      ACTION_NONE = new ACTIONS("ACTION_NONE", 2);
      SEND_MESSAGE = new ACTIONS("SEND_MESSAGE", 3);
      ACTIONS[] arrayOfACTIONS = new ACTIONS[4];
      arrayOfACTIONS[0] = FACEBOOK_LIKE;
      arrayOfACTIONS[1] = FACEBOOK_SHARE;
      arrayOfACTIONS[2] = ACTION_NONE;
      arrayOfACTIONS[3] = SEND_MESSAGE;
      $VALUES = arrayOfACTIONS;
    }
  }

  private class ImageDownloadTask extends AsyncTask<VideoItem, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    protected Void doInBackground(VideoItem[] paramArrayOfVideoItem)
    {
      try
      {
        if (isCancelled())
        {
          VideoPlayer.this.downloadComplete();
          return null;
        }
        paramArrayOfVideoItem[0].setCoverPath(VideoPlayer.this.cachePath + "/images/" + Utils.md5(paramArrayOfVideoItem[0].getCoverUrl()));
        if ((paramArrayOfVideoItem[0].getCoverPath().length() > 0) && (new File(paramArrayOfVideoItem[0].getCoverPath()).exists()))
        {
          VideoPlayer.this.downloadComplete();
          return null;
        }
        if (paramArrayOfVideoItem[0].getCoverUrl().length() == 0)
        {
          VideoPlayer.this.downloadComplete();
          return null;
        }
        SystemClock.sleep(10L);
        while (true)
        {
          ByteArrayBuffer localByteArrayBuffer;
          try
          {
            BufferedInputStream localBufferedInputStream = new BufferedInputStream(new URL(URLDecoder.decode(paramArrayOfVideoItem[0].getCoverUrl())).openConnection().getInputStream());
            localByteArrayBuffer = new ByteArrayBuffer(32);
            int i = localBufferedInputStream.read();
            if (i != -1)
            {
              localByteArrayBuffer.append((byte)i);
              continue;
            }
          }
          catch (Exception localException2)
          {
            Log.e("", "");
            publishProgress(new String[0]);
            return null;
          }
          File localFile = new File(VideoPlayer.this.cachePath + "/images/");
          if (!localFile.exists())
            localFile.mkdirs();
          String str = VideoPlayer.this.cachePath + "/images/" + Utils.md5(paramArrayOfVideoItem[0].getCoverUrl());
          FileOutputStream localFileOutputStream = new FileOutputStream(new File(str));
          localFileOutputStream.write(localByteArrayBuffer.toByteArray());
          localFileOutputStream.close();
          VideoPlayer.this.downloadRegistration(str);
        }
      }
      catch (Exception localException1)
      {
      }
      return null;
    }

    protected void onPostExecute(Void paramVoid)
    {
      VideoPlayer.this.downloadComplete();
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.VideoPlayer
 * JD-Core Version:    0.6.0
 */