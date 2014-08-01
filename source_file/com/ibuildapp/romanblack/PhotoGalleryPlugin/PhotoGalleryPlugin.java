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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.callback.OnAuthListener;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.callback.OnCommentPushedListener;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.Album;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.CommentItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.EntityParser;
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

public class PhotoGalleryPlugin extends AppBuilderModuleMain
  implements OnCommentPushedListener, OnAuthListener, AdapterView.OnItemClickListener
{
  private final int COLORS_RECIEVED = 5;
  private final int GET_COMMENTS_AND_LIKES_COUNTS = 6;
  private final int GET_OG_LIKES = 8;
  private final int HIDE_PROGRESS_DIALOG = 2;
  private final int INITIALIZATION_FAILED = 0;
  private final int LOADING_ABORTED = 4;
  private final int ONE_ALBUM = 1000;
  private final int PING = 3;
  private final int SHOW_ALBUMS = 7;
  private final int SHOW_PROGRESS_DIALOG = 1;
  private AlbumsAdapter adapter = null;
  private ArrayList<Album> albums = null;
  private String cachePath = "";
  private boolean destroyed = false;
  private ExecutorService executorService = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 4:
      default:
        return;
      case 0:
        Toast.makeText(PhotoGalleryPlugin.this, PhotoGalleryPlugin.this.getResources().getString(R.string.romanblack_photogallery_alert_cannot_init), 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            PhotoGalleryPlugin.this.finish();
          }
        }
        , 2000L);
        return;
      case 1:
        PhotoGalleryPlugin.this.showProgressDialog();
        return;
      case 2:
        PhotoGalleryPlugin.this.hideProgressDialog();
        return;
      case 3:
        PhotoGalleryPlugin.this.ping();
        return;
      case 5:
        PhotoGalleryPlugin.this.colorsRecieved();
        return;
      case 6:
        PhotoGalleryPlugin.this.getCommentsLikesCounts();
        return;
      case 7:
        PhotoGalleryPlugin.this.showAlbums();
        return;
      case 8:
      }
      PhotoGalleryPlugin.this.getOgLikes();
    }
  };
  private LinearLayout listSpace = null;
  private ListView listView = null;
  private LinearLayout mainLayout = null;
  private ProgressDialog progressDialog = null;
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

  private void getCommentsLikesCounts()
  {
    this.executorService.execute(new Runnable()
    {
      public void run()
      {
        HashMap localHashMap1 = JSONParser.getVideoCommentsCount();
        for (int i = 0; i < PhotoGalleryPlugin.this.albums.size(); i++)
        {
          if (((Album)PhotoGalleryPlugin.this.albums.get(i)).isRSS())
            continue;
          int m = 0;
          while (true)
            if (m < ((Album)PhotoGalleryPlugin.this.albums.get(i)).getImages().size())
              try
              {
                int i1 = Integer.parseInt((String)localHashMap1.get(((ImageItem)((Album)PhotoGalleryPlugin.this.albums.get(i)).getImages().get(m)).getId() + ""));
                n = i1;
                ((ImageItem)((Album)PhotoGalleryPlugin.this.albums.get(i)).getImages().get(m)).setTotalComments(n);
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
        HashMap localHashMap2 = JSONParser.getImagesLikesCount(PhotoGalleryPlugin.this.albums);
        for (int j = 0; j < PhotoGalleryPlugin.this.albums.size(); j++)
        {
          if (((Album)PhotoGalleryPlugin.this.albums.get(j)).isRSS())
            continue;
          int k = 0;
          while (true)
            if (k < ((Album)PhotoGalleryPlugin.this.albums.get(j)).getImages().size())
              try
              {
                String str = (String)localHashMap2.get(((ImageItem)((Album)PhotoGalleryPlugin.this.albums.get(j)).getImages().get(k)).getImageUrl());
                ((ImageItem)((Album)PhotoGalleryPlugin.this.albums.get(j)).getImages().get(k)).setLikesCount(Integer.parseInt(str));
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
          for (int i = 0; i < PhotoGalleryPlugin.this.albums.size(); i++)
          {
            ArrayList localArrayList1 = JSONParser.getUserOgLikes();
            ArrayList localArrayList2 = ((Album)PhotoGalleryPlugin.this.albums.get(i)).getImages();
            int j = 0;
            if (j >= localArrayList2.size())
              continue;
            for (int k = 0; ; k++)
            {
              if (k < localArrayList1.size())
              {
                if (!((ImageItem)localArrayList2.get(j)).getPermalinkUrl().equalsIgnoreCase((String)localArrayList1.get(k)))
                  continue;
                ((ImageItem)localArrayList2.get(j)).setLiked(true);
              }
              j++;
              break;
            }
          }
        }
      }).start();
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
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
          String str = Utils.md5(Settings.Secure.getString(PhotoGalleryPlugin.this.getContentResolver(), "android_id"));
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
              break label326;
            localMultipartEntity.addPart("account_type", new StringBody("facebook", Charset.forName("UTF-8")));
          }
          while (true)
          {
            localHttpPost.setEntity(localMultipartEntity);
            ((String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler()));
            Log.d("", "");
            if (!PhotoGalleryPlugin.this.destroyed)
              PhotoGalleryPlugin.this.handler.sendEmptyMessageDelayed(3, 30000L);
            return;
            label326: if (Authorization.getAuthorizedUser().getAccountType() != User.ACCOUNT_TYPES.TWITTER)
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

  private void showAlbums()
  {
    if (this.albums.size() == 1)
    {
      this.handler.sendEmptyMessage(2);
      Intent localIntent = new Intent(this, AlbumViewActivity.class);
      localIntent.putExtra("albums", this.albums);
      localIntent.putExtra("position", 0);
      localIntent.putExtra("cachePath", this.cachePath);
      startActivityForResult(localIntent, 1000);
    }
    this.adapter = new AlbumsAdapter(this, this.albums, this.cachePath);
    this.listView.setAdapter(this.adapter);
    visibleTopBar();
    this.listSpace.setVisibility(0);
    this.handler.sendEmptyMessage(2);
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
          PhotoGalleryPlugin.this.handler.sendEmptyMessage(4);
        }
      });
    }
  }

  public void create()
  {
    setContentView(R.layout.romanblack_photogallery_albums);
    setTopBarTitle(getResources().getString(R.string.romanblack_photogallery_main_capture));
    if (!((Boolean)getIntent().getExtras().getSerializable("showSideBar")).booleanValue())
      setTopBarLeftButtonText(getResources().getString(R.string.home), true, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          PhotoGalleryPlugin.this.closeActivity();
        }
      });
    Intent localIntent = getIntent();
    this.widget = ((Widget)localIntent.getExtras().getSerializable("Widget"));
    if (this.widget == null)
    {
      this.handler.sendEmptyMessage(0);
      return;
    }
    if (this.widget.getTitle().length() > 0)
      setTitle(this.widget.getTitle());
    try
    {
      if ((this.widget.getPluginXmlData().length() == 0) && (localIntent.getStringExtra("WidgetFile").length() == 0))
      {
        this.handler.sendEmptyMessageDelayed(0, 3000L);
        return;
      }
    }
    catch (Exception localException)
    {
      this.handler.sendEmptyMessageDelayed(0, 3000L);
      return;
    }
    if (this.widget.getBackgroundColor() != 0)
      Statics.backgroundColor = this.widget.getBackgroundColor();
    this.cachePath = (this.widget.getCachePath() + "/photogallery-" + this.widget.getOrder());
    File localFile = new File(this.cachePath);
    if (!localFile.exists())
      localFile.mkdirs();
    if (this.executorService == null)
      this.executorService = Executors.newFixedThreadPool(5);
    this.handler.sendEmptyMessage(1);
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
      Statics.isOnline = true;
    this.handler.sendEmptyMessage(3);
    this.mainLayout = ((LinearLayout)findViewById(R.id.romanblack_photogallery_albums_main_layout));
    this.mainLayout.setBackgroundColor(Statics.color1);
    invisibleTopBar();
    this.listSpace = ((LinearLayout)findViewById(R.id.romanblack_photogallery_albums_list_space));
    this.listSpace.setVisibility(4);
    this.listView = ((ListView)findViewById(R.id.romanblack_photogallery_albums_listview));
    this.listView.setDivider(null);
    this.listView.setOnItemClickListener(this);
    this.executorService.execute(new Runnable()
    {
      public void run()
      {
        if (PhotoGalleryPlugin.this.widget.getPluginXmlData().length() > 0);
        for (EntityParser localEntityParser = new EntityParser(PhotoGalleryPlugin.this.widget.getPluginXmlData()); ; localEntityParser = new EntityParser(PhotoGalleryPlugin.this.readXmlFromFile(PhotoGalleryPlugin.this.getIntent().getStringExtra("WidgetFile"))))
        {
          localEntityParser.parse();
          PhotoGalleryPlugin.access$802(PhotoGalleryPlugin.this, null);
          Statics.APP_ID = localEntityParser.getAppId();
          Statics.APP_NAME = localEntityParser.getAppName();
          Statics.MODULE_ID = localEntityParser.getModuleId();
          Statics.color1 = localEntityParser.getColor1();
          Statics.color2 = localEntityParser.getColor2();
          Statics.color3 = localEntityParser.getColor3();
          Statics.color4 = localEntityParser.getColor4();
          Statics.color5 = localEntityParser.getColor5();
          Statics.color6 = localEntityParser.getColor6();
          Statics.color7 = localEntityParser.getColor7();
          PhotoGalleryPlugin.this.handler.sendEmptyMessage(5);
          PhotoGalleryPlugin.access$1002(PhotoGalleryPlugin.this, localEntityParser.getAlbums());
          if (PhotoGalleryPlugin.this.albums != null)
            break;
          PhotoGalleryPlugin.this.handler.sendEmptyMessage(0);
          return;
        }
        if (PhotoGalleryPlugin.this.albums.isEmpty())
        {
          PhotoGalleryPlugin.this.handler.sendEmptyMessage(0);
          return;
        }
        PhotoGalleryPlugin.this.handler.sendEmptyMessage(6);
        if (Authorization.getAuthorizedUser(1) != null)
          PhotoGalleryPlugin.this.handler.sendEmptyMessage(8);
        PhotoGalleryPlugin.this.handler.sendEmptyMessage(7);
      }
    });
    Statics.onCommentPushedListeners.add(this);
    Statics.onAuthListeners.add(this);
  }

  public void destroy()
  {
    Statics.onCommentPushedListeners.remove(this);
    Statics.onAuthListeners.remove(this);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 1000)
      closeActivity();
  }

  public void onAuth()
  {
    this.handler.removeMessages(3);
    this.handler.sendEmptyMessage(3);
    this.handler.sendEmptyMessage(8);
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

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramAdapterView == this.listView)
    {
      Intent localIntent = new Intent(this, AlbumViewActivity.class);
      localIntent.putExtra("albums", this.albums);
      localIntent.putExtra("position", paramInt);
      localIntent.putExtra("cachePath", this.cachePath);
      startActivity(localIntent);
    }
  }

  // ERROR //
  protected String readXmlFromFile(String paramString)
  {
    // Byte code:
    //   0: new 367	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 368	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: new 521	java/io/BufferedReader
    //   11: dup
    //   12: new 523	java/io/FileReader
    //   15: dup
    //   16: new 388	java/io/File
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 390	java/io/File:<init>	(Ljava/lang/String;)V
    //   24: invokespecial 526	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   27: invokespecial 529	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   30: astore_3
    //   31: aload_3
    //   32: invokevirtual 532	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   35: astore 6
    //   37: aload 6
    //   39: ifnull +20 -> 59
    //   42: aload_2
    //   43: aload 6
    //   45: invokevirtual 375	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: goto -18 -> 31
    //   52: astore 5
    //   54: aload_2
    //   55: invokevirtual 386	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: areturn
    //   59: goto -5 -> 54
    //   62: astore 9
    //   64: goto -10 -> 54
    //   67: astore 4
    //   69: goto -15 -> 54
    //   72: astore 8
    //   74: goto -20 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   31	37	52	java/io/FileNotFoundException
    //   42	49	52	java/io/FileNotFoundException
    //   8	31	62	java/io/IOException
    //   31	37	67	java/io/IOException
    //   42	49	67	java/io/IOException
    //   8	31	72	java/io/FileNotFoundException
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
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.PhotoGalleryPlugin
 * JD-Core Version:    0.6.0
 */