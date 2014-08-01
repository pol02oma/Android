package com.ibuildapp.romanblack.PhotoGalleryPlugin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.callback.OnAuthListener;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.callback.OnCommentPushedListener;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.callback.OnLikeListener;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.Album;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.CommentItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.FeedParser;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.JSONParser;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class AlbumViewActivity extends AppBuilderModuleMain
  implements AdapterView.OnItemClickListener, OnAuthListener, OnCommentPushedListener, OnLikeListener
{
  private final int CHANGE_ALBUM = 11;
  private final int CHECK_ALBUM = 8;
  private final int GET_COMMENTS_AND_LIKES_COUNTS = 10;
  private final int GET_OG_LIKES = 7;
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int INITIALIZATION_FAILED = 0;
  private final int LOADING_ABORTED = 1;
  private final int PARSE_RSS = 9;
  private final int PING = 6;
  private final int REFRESH_GALLERY = 5;
  private final int SHOW_GALLERY = 4;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private GalleryAdapter adapter = null;
  private int albumPosition = 0;
  private ArrayList<Album> albums = null;
  private String cachePath = "";
  private int columnWidth = 75;
  private boolean destroyed = false;
  private ExecutorService executorService = null;
  private GridView gridView = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        Toast.makeText(AlbumViewActivity.this, AlbumViewActivity.this.getResources().getString(R.string.romanblack_photogallery_alert_cannot_init), 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            AlbumViewActivity.this.finish();
          }
        }
        , 2000L);
        return;
      case 1:
        AlbumViewActivity.this.closeActivity();
        return;
      case 2:
        AlbumViewActivity.this.showProgressDialog();
        return;
      case 3:
        AlbumViewActivity.this.hideProgressDialog();
        return;
      case 4:
        AlbumViewActivity.this.showGallery();
        return;
      case 5:
        AlbumViewActivity.this.refreshGallery();
        return;
      case 6:
        AlbumViewActivity.this.ping();
        return;
      case 7:
        AlbumViewActivity.this.getOgLikes();
        return;
      case 8:
        AlbumViewActivity.this.checkAlbum();
        return;
      case 9:
        AlbumViewActivity.this.parseRSS();
        return;
      case 10:
        AlbumViewActivity.this.getCommentsLikesCounts();
        return;
      case 11:
      }
      AlbumViewActivity.access$1002(AlbumViewActivity.this, paramMessage.arg1);
      AlbumViewActivity.this.handler.sendEmptyMessage(8);
    }
  };
  private LinearLayout mainLayout = null;
  private boolean needMenu = false;
  private ProgressDialog progressDialog = null;

  private void checkAlbum()
  {
    this.handler.sendEmptyMessage(2);
    if (this.albums == null)
    {
      this.handler.sendEmptyMessage(0);
      return;
    }
    if (this.albums.isEmpty())
    {
      this.handler.sendEmptyMessage(0);
      return;
    }
    if (((Album)this.albums.get(this.albumPosition)).isRSS())
    {
      this.handler.sendEmptyMessage(9);
      return;
    }
    if (((Album)this.albums.get(this.albumPosition)).getImages() == null)
    {
      this.handler.sendEmptyMessage(3);
      return;
    }
    if (((Album)this.albums.get(this.albumPosition)).getImages().isEmpty())
    {
      this.handler.sendEmptyMessage(3);
      return;
    }
    this.handler.sendEmptyMessage(4);
  }

  private void closeActivity()
  {
    hideProgressDialog();
    finish();
  }

  private void getCommentsLikesCounts()
  {
    this.executorService.execute(new Runnable()
    {
      public void run()
      {
        HashMap localHashMap1 = JSONParser.getVideoCommentsCount();
        for (int i = 0; i < AlbumViewActivity.this.albums.size(); i++)
        {
          if (((Album)AlbumViewActivity.this.albums.get(i)).isRSS())
            continue;
          int m = 0;
          while (true)
            if (m < ((Album)AlbumViewActivity.this.albums.get(i)).getImages().size())
              try
              {
                int i1 = Integer.parseInt((String)localHashMap1.get(((ImageItem)((Album)AlbumViewActivity.this.albums.get(i)).getImages().get(m)).getId() + ""));
                n = i1;
                ((ImageItem)((Album)AlbumViewActivity.this.albums.get(i)).getImages().get(m)).setTotalComments(n);
                m++;
              }
              catch (Exception localException2)
              {
                while (true)
                {
                  Log.d("", "");
                  int n = 0;
                }
              }
        }
        HashMap localHashMap2 = JSONParser.getImagesLikesCount(AlbumViewActivity.this.albums);
        for (int j = 0; j < AlbumViewActivity.this.albums.size(); j++)
        {
          if (((Album)AlbumViewActivity.this.albums.get(j)).isRSS())
            continue;
          int k = 0;
          while (true)
            if (k < ((Album)AlbumViewActivity.this.albums.get(j)).getImages().size())
              try
              {
                String str = (String)localHashMap2.get(((ImageItem)((Album)AlbumViewActivity.this.albums.get(j)).getImages().get(k)).getImageUrl());
                ((ImageItem)((Album)AlbumViewActivity.this.albums.get(j)).getImages().get(k)).setLikesCount(Integer.parseInt(str));
                k++;
              }
              catch (Exception localException1)
              {
                while (true)
                  Log.d("", "");
              }
        }
      }
    });
  }

  private void getOgLikes()
  {
    if (Authorization.getAuthorizedUser(1) != null)
      new Thread(new Runnable()
      {
        public void run()
        {
          ArrayList localArrayList1 = JSONParser.getUserOgLikes();
          ArrayList localArrayList2 = ((Album)AlbumViewActivity.this.albums.get(AlbumViewActivity.this.albumPosition)).getImages();
          int i = 0;
          if (i < localArrayList2.size())
            for (int j = 0; ; j++)
            {
              if (j < localArrayList1.size())
              {
                if (!((ImageItem)localArrayList2.get(i)).getPermalinkUrl().equalsIgnoreCase((String)localArrayList1.get(j)))
                  continue;
                ((ImageItem)localArrayList2.get(i)).setLiked(true);
              }
              i++;
              break;
            }
        }
      }).start();
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void parseRSS()
  {
    this.executorService.execute(new Runnable()
    {
      public void run()
      {
        FeedParser localFeedParser = new FeedParser(((Album)AlbumViewActivity.this.albums.get(AlbumViewActivity.this.albumPosition)).getRssUrl());
        ((Album)AlbumViewActivity.this.albums.get(AlbumViewActivity.this.albumPosition)).setImages(localFeedParser.parseFeed());
        Log.d("", "");
        AlbumViewActivity.this.handler.sendEmptyMessage(4);
      }
    });
  }

  private void ping()
  {
    this.executorService.execute(new Runnable()
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
          String str = Utils.md5(Settings.Secure.getString(AlbumViewActivity.this.getContentResolver(), "android_id"));
          localMultipartEntity = new MultipartEntity();
          localMultipartEntity.addPart("action", new StringBody("ping", Charset.forName("UTF-8")));
          localMultipartEntity.addPart("platform", new StringBody("android", Charset.forName("UTF-8")));
          localMultipartEntity.addPart("app_id", new StringBody(Statics.APP_ID, Charset.forName("UTF-8")));
          localMultipartEntity.addPart("module_id", new StringBody(Statics.MODULE_ID, Charset.forName("UTF-8")));
          localMultipartEntity.addPart("device", new StringBody(str, Charset.forName("UTF-8")));
          if (Authorization.getAuthorizedUser(1) != null)
          {
            localMultipartEntity.addPart("account_id", new StringBody(Authorization.getAuthorizedUser().getAccountId(), Charset.forName("UTF-8")));
            if (Authorization.getAuthorizedUser().getAccountType() != User.ACCOUNT_TYPES.FACEBOOK)
              break label328;
            localMultipartEntity.addPart("account_type", new StringBody("facebook", Charset.forName("UTF-8")));
          }
          while (true)
          {
            localHttpPost.setEntity(localMultipartEntity);
            ((String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler()));
            Log.d("", "");
            if (!AlbumViewActivity.this.destroyed)
              AlbumViewActivity.this.handler.sendEmptyMessageDelayed(6, 30000L);
            return;
            label328: if (Authorization.getAuthorizedUser().getAccountType() != User.ACCOUNT_TYPES.TWITTER)
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
    });
  }

  private void refreshGallery()
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

  private void showGallery()
  {
    this.adapter = new GalleryAdapter(((Album)this.albums.get(this.albumPosition)).getImages(), this);
    this.adapter.setCachePath(this.cachePath);
    this.adapter.setImageSize(this.columnWidth, this.columnWidth);
    this.gridView.setAdapter(this.adapter);
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
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_photogallery_loading), true);
      this.progressDialog.setCancelable(true);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          AlbumViewActivity.this.handler.sendEmptyMessage(1);
        }
      });
    }
  }

  public void create()
  {
    setContentView(R.layout.romanblack_photogallery_main);
    setTopBarTitle(getResources().getString(R.string.romanblack_photogallery_main_capture));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.back), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        AlbumViewActivity.this.closeActivity();
      }
    });
    Bundle localBundle = getIntent().getExtras();
    this.cachePath = localBundle.getString("cachePath");
    this.albums = ((ArrayList)localBundle.getSerializable("albums"));
    this.albumPosition = localBundle.getInt("position", 0);
    File localFile = new File(this.cachePath);
    if (!localFile.exists())
      localFile.mkdirs();
    if (this.executorService == null)
      this.executorService = Executors.newFixedThreadPool(5);
    this.handler.sendEmptyMessage(2);
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
      Statics.isOnline = true;
    this.gridView = ((GridView)findViewById(R.id.romanblack_photogallery_gridview));
    this.gridView.setOnItemClickListener(this);
    float f = getResources().getDisplayMetrics().density;
    this.columnWidth = (int)((getWindowManager().getDefaultDisplay().getWidth() - 34.0F * f) / 4.0F);
    int i = (int)(3.0F * f);
    this.mainLayout = ((LinearLayout)findViewById(R.id.romanblack_photogallery_main_layout));
    this.mainLayout.setBackgroundColor(Statics.color1);
    this.gridView.setPadding(0, 0, 0, 0);
    this.gridView.setColumnWidth(this.columnWidth);
    this.gridView.setVerticalSpacing((int)(6.0F * f));
    this.gridView.setHorizontalSpacing(i);
    this.gridView.setNumColumns(4);
    this.handler.sendEmptyMessage(8);
    Statics.onCommentPushedListeners.add(this);
    Statics.onAuthListeners.add(this);
  }

  public void destroy()
  {
    this.destroyed = true;
    Statics.onAuthListeners.remove(this);
    Statics.onCommentPushedListeners.remove(this);
  }

  public void onAuth()
  {
  }

  public void onCommentPushed(CommentItem paramCommentItem)
  {
    if (paramCommentItem.getReplyId() != 0L);
    while (true)
    {
      return;
      for (int i = 0; i < this.albums.size(); i++)
      {
        if (!((Album)this.albums.get(i)).isRSS())
          continue;
        for (int j = 0; j < ((Album)this.albums.get(i)).getImages().size(); j++)
        {
          if (((ImageItem)((Album)this.albums.get(i)).getImages().get(j)).getId() != paramCommentItem.getTrackId())
            continue;
          ((ImageItem)((Album)this.albums.get(i)).getImages().get(j)).setTotalComments(1 + ((ImageItem)((Album)this.albums.get(i)).getImages().get(j)).getTotalComments());
          return;
        }
      }
    }
  }

  public void onCommentsUpdate(ImageItem paramImageItem, CommentItem paramCommentItem, int paramInt1, int paramInt2, ArrayList<CommentItem> paramArrayList)
  {
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    3 local3 = new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        int i = paramMenuItem.getItemId();
        AlbumViewActivity.this.handler.sendMessage(AlbumViewActivity.this.handler.obtainMessage(11, i, 0));
        AlbumViewActivity.access$1202(AlbumViewActivity.this, false);
        return true;
      }
    };
    for (int i = 0; i < this.albums.size(); i++)
      paramMenu.add(0, i, i, ((Album)this.albums.get(i)).getTitle()).setOnMenuItemClickListener(local3);
    return true;
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramAdapterView == this.gridView)
    {
      Intent localIntent = new Intent(this, PhotoGalleryDetails.class);
      localIntent.putExtra("items", ((Album)this.albums.get(this.albumPosition)).getImages());
      localIntent.putExtra("albums", this.albums);
      localIntent.putExtra("albumPosition", this.albumPosition);
      localIntent.putExtra("position", paramInt);
      localIntent.putExtra("cachePath", this.cachePath);
      startActivity(localIntent);
    }
  }

  public void onLike(ImageItem paramImageItem)
  {
    for (int i = 0; i < this.albums.size(); i++)
    {
      if (((Album)this.albums.get(i)).isRSS())
        continue;
      for (int j = 0; j < ((Album)this.albums.get(i)).getImages().size(); j++)
      {
        ((ImageItem)((Album)this.albums.get(i)).getImages().get(j)).setLikesCount(1 + ((ImageItem)((Album)this.albums.get(i)).getImages().get(j)).getLikesCount());
        ((ImageItem)((Album)this.albums.get(i)).getImages().get(j)).setLiked(true);
      }
    }
  }

  public void onOptionsMenuClosed(Menu paramMenu)
  {
    this.needMenu = false;
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return this.needMenu;
  }

  public void start()
  {
    this.adapter.enableDecoding();
  }

  public void stop()
  {
    this.adapter.cleanBitmaps(true);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.AlbumViewActivity
 * JD-Core Version:    0.6.0
 */