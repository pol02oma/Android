package com.ibuildapp.romanblack.PhotoGalleryPlugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.BarDesigner.TitleDesign;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.callback.OnCommentPushedListener;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.CommentItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.JSONParser;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.http.util.ByteArrayBuffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CommentsActivity extends AppBuilderModuleMain
  implements View.OnClickListener, AdapterView.OnItemClickListener, OnCommentPushedListener
{
  private final int AUTH = 8;
  private final int AUTHORIZATION_ACTIVITY = 10000;
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int INITIALIZATION_FAILED = 0;
  private final int LOADING_ABORTED = 1;
  private final int MESSAGE_VIEW_ACTIVITY = 10001;
  private final int NEED_INTERNET_CONNECTION = 6;
  private final int REFRESH_LIST = 7;
  private final int SEND_COMMENT_ACTIVITY = 10003;
  private final int SHOW_COMMENTS_LIST = 4;
  private final int SHOW_NO_COMMENTS = 5;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private ACTIONS action = ACTIONS.ACTION_NO;
  private Intent actionIntent = null;
  private CommentsAdapter adapter = null;
  private AlertDialog authDialog = null;
  private String cachePath = "";
  private ArrayList<CommentItem> comments = null;
  private TextView descriptionTextView = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        Toast.makeText(CommentsActivity.this, CommentsActivity.this.getResources().getString(R.string.romanblack_photogallery_alert_cannot_init), 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            CommentsActivity.this.finish();
          }
        }
        , 2000L);
        return;
      case 1:
        CommentsActivity.this.closeActivity();
        return;
      case 2:
        CommentsActivity.this.showProgressDialog();
        return;
      case 3:
        CommentsActivity.this.hideProgressDialog();
        return;
      case 4:
        CommentsActivity.this.showCommentsList();
        return;
      case 5:
        CommentsActivity.this.showNoComments();
        return;
      case 6:
        Toast.makeText(CommentsActivity.this, CommentsActivity.this.getResources().getString(R.string.romanblack_photogallery_alert_no_internet), 1).show();
        return;
      case 7:
        CommentsActivity.this.refreshList();
        return;
      case 8:
      }
      CommentsActivity.this.auth();
    }
  };
  private LinearLayout hasCommentsLayout = null;
  private LinearLayout hasntCommentsLayout = null;
  private LinearLayout headerLayout = null;
  private View headerView = null;
  private int imageHeight = 70;
  private int imageWidth = 70;
  private ImageItem item = null;
  private ArrayList<ImageItem> items = null;
  private ListView listView = null;
  private LinearLayout mainLayout = null;
  private int position = 0;
  private Button postCommentButton = null;
  private ImageView postCommentButtonTop = null;
  private ProgressDialog progressDialog = null;
  private ImageView thumbImageView = null;
  private TextView titleTextView = null;
  private Widget widget = null;

  private void auth()
  {
    this.actionIntent = new Intent(this, SendMessageActivity.class);
    this.actionIntent.putExtra("user", Authorization.getAuthorizedUser());
    this.actionIntent.putExtra("Widget", this.widget);
    this.actionIntent.putExtra("item", this.item);
    this.action = ACTIONS.SEND_MESSAGE;
    Intent localIntent = new Intent(this, AuthorizationActivity.class);
    localIntent.putExtra("Widget", this.widget);
    startActivityForResult(localIntent, 10000);
  }

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
          while (true)
          {
            FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
            ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
            localObjectOutputStream.writeObject(localArrayList);
            localObjectOutputStream.close();
            localFileOutputStream.close();
            return;
            localIOException1 = localIOException1;
            Log.d("", "");
          }
        while (this.comments.size() <= 20);
        for (int i = 0; i < 20; i++)
          localArrayList.add(this.comments.get(i));
      }
      catch (IOException localIOException2)
      {
        Log.d("", "");
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
      File localFile = new File(paramString);
      BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
      localOptions1.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions1);
      int i = this.imageWidth;
      int j = this.imageHeight;
      int k = localOptions1.outWidth;
      int m = localOptions1.outHeight;
      int n = 1;
      Bitmap localBitmap;
      int i1;
      int i2;
      if ((k / 2 < i) || (m / 2 < j))
      {
        BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
        localOptions2.inSampleSize = n;
        localBitmap = BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions2);
        if (k <= m)
          break label223;
        i1 = (k - m) / 2;
        i2 = 1;
      }
      for (int i3 = m - 1; ; i3 = k - 1)
      {
        float f1 = (-4 + this.imageWidth) / i3;
        float f2 = (-4 + this.imageHeight) / i3;
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(f1, f2);
        return Bitmap.createBitmap(localBitmap, i1, i2, i3, i3, localMatrix, true);
        k /= 2;
        m /= 2;
        n *= 2;
        break;
        label223: i1 = 1;
        i2 = (m - k) / 2;
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
    this.item.setImagePath(paramString);
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void openAuthDialog()
  {
    if (this.authDialog == null)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setMessage(R.string.romanblack_photogallery_dialog_must_be_logged);
      localBuilder.setPositiveButton(R.string.romanblack_photogallery_log_in, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          CommentsActivity.this.handler.sendEmptyMessage(8);
        }
      });
      localBuilder.setNegativeButton(R.string.romanblack_photogallery_cancel, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
        }
      });
      this.authDialog = localBuilder.create();
    }
    this.authDialog.show();
  }

  private void refreshList()
  {
    this.adapter.notifyDataSetChanged();
  }

  private void setThumb()
  {
    if ((this.thumbImageView != null) && (this.item.getImagePath().length() > 0));
    try
    {
      Bitmap localBitmap2 = decodeImageFile(this.item.getImagePath());
      localBitmap1 = localBitmap2;
      if (localBitmap1 != null)
        this.thumbImageView.setImageBitmap(localBitmap1);
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

  private void showCommentsList()
  {
    if (this.comments == null);
    do
      return;
    while (this.comments.isEmpty());
    try
    {
      this.headerLayout.removeAllViews();
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          this.listView.removeHeaderView(this.headerView);
          TypedValue.applyDimension(1, 100.0F, getResources().getDisplayMetrics());
          this.headerView.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
          this.listView.addHeaderView(this.headerView, null, false);
          this.hasCommentsLayout.setVisibility(0);
          this.hasntCommentsLayout.setVisibility(8);
          this.adapter = new CommentsAdapter(this, this.comments, this.item, this.widget);
          this.adapter.setCachePath(this.cachePath);
          this.listView.setAdapter(this.adapter);
          this.listView.setOnItemClickListener(this);
          this.handler.sendEmptyMessage(3);
          cacheMessages();
          return;
          localException1 = localException1;
          Log.d("", "");
        }
      }
      catch (Exception localException2)
      {
        while (true)
          Log.d("", "");
      }
    }
  }

  private void showNoComments()
  {
    try
    {
      this.listView.removeHeaderView(this.headerView);
      TypedValue.applyDimension(1, 100.0F, getResources().getDisplayMetrics());
      this.headerLayout.addView(this.headerView, new LinearLayout.LayoutParams(-1, -2));
      this.hasntCommentsLayout.setVisibility(0);
      this.hasCommentsLayout.setVisibility(8);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.d("", "");
    }
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
          CommentsActivity.this.handler.sendEmptyMessage(1);
        }
      });
    }
  }

  public void create()
  {
    setContentView(R.layout.romanblack_photogallery_comments_main);
    setTopBarTitle(getResources().getString(R.string.romanblack_photogallery_replies_capture));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.back), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        CommentsActivity.this.finish();
      }
    });
    View localView = LayoutInflater.from(this).inflate(R.layout.romanblack_photogallery_share_btn, null);
    this.postCommentButtonTop = ((ImageView)localView.findViewById(R.id.imageView));
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)this.postCommentButtonTop.getDrawable();
    localBitmapDrawable.setColorFilter(this.navBarDesign.itemDesign.textColor, PorterDuff.Mode.MULTIPLY);
    this.postCommentButtonTop.setImageDrawable(localBitmapDrawable);
    setTopBarRightVeiw(localView, false, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)CommentsActivity.this.getSystemService("connectivity")).getActiveNetworkInfo();
        if (localNetworkInfo != null)
        {
          if (!localNetworkInfo.isConnectedOrConnecting())
            break label110;
          if (!Authorization.isAuthorized())
            CommentsActivity.this.openAuthDialog();
        }
        else
        {
          return;
        }
        Intent localIntent = new Intent(CommentsActivity.this, SendMessageActivity.class);
        localIntent.putExtra("user", Authorization.getAuthorizedUser());
        localIntent.putExtra("Widget", CommentsActivity.this.widget);
        localIntent.putExtra("item", CommentsActivity.this.item);
        CommentsActivity.this.startActivityForResult(localIntent, 10003);
        return;
        label110: CommentsActivity.this.handler.sendEmptyMessage(6);
      }
    });
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
      this.item = ((ImageItem)this.items.get(this.position));
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
      this.mainLayout = ((LinearLayout)findViewById(R.id.romanblack_photogallery_comments_nain_layout));
      this.mainLayout.setBackgroundColor(Statics.color1);
      this.listView = ((ListView)findViewById(R.id.romanblack_photogallery_comments_main_listview));
      this.listView.setCacheColorHint(Statics.color1);
      this.listView.setHeaderDividersEnabled(false);
      this.headerView = LayoutInflater.from(this).inflate(R.layout.romanblack_photogallery_comments_list_header, null);
      this.thumbImageView = ((ImageView)this.headerView.findViewById(R.id.romanblack_photogallery_comments_listview_header_thumb));
      ImageDownloadTask localImageDownloadTask = new ImageDownloadTask(null);
      ImageItem[] arrayOfImageItem = new ImageItem[1];
      arrayOfImageItem[0] = this.item;
      localImageDownloadTask.execute(arrayOfImageItem);
      this.titleTextView = ((TextView)this.headerView.findViewById(R.id.romanblack_photogallery_comments_listview_header_title));
      this.titleTextView.setTextColor(Statics.color3);
      this.titleTextView.setText(this.item.getTitle());
      this.descriptionTextView = ((TextView)this.headerView.findViewById(R.id.romanblack_photogallery_comments_listview_header_description));
      this.descriptionTextView.setTextColor(Statics.color4);
      Document localDocument = Jsoup.parse(this.item.getDescription());
      this.descriptionTextView.setText(localDocument.text());
      this.hasCommentsLayout = ((LinearLayout)findViewById(R.id.romanblack_photogallery_comments_main_has_layout));
      this.hasCommentsLayout.setVisibility(8);
      this.hasntCommentsLayout = ((LinearLayout)findViewById(R.id.romanblack_photogallery_comments_main_hasnt_layout));
      this.headerLayout = ((LinearLayout)findViewById(R.id.romanblack_photogallery_comments_main_header));
      this.postCommentButton = ((Button)findViewById(R.id.romanblack_photogallery_comments_main_post_comment));
      this.postCommentButton.setOnClickListener(this);
      if (!Statics.isOnline)
      {
        this.postCommentButton.getBackground().setAlpha(100);
        this.postCommentButton.setTextColor(Color.parseColor("#9bffffff"));
        this.postCommentButtonTop.setAlpha(100);
      }
      this.handler.sendEmptyMessage(2);
      new Thread(new Runnable()
      {
        public void run()
        {
          NetworkInfo localNetworkInfo = ((ConnectivityManager)CommentsActivity.this.getSystemService("connectivity")).getActiveNetworkInfo();
          boolean bool = false;
          if (localNetworkInfo != null)
            bool = localNetworkInfo.isConnectedOrConnecting();
          if (bool)
          {
            String str = Statics.BASE_URL + "/getcomments/" + com.appbuilder.sdk.android.Statics.appId + "/" + Statics.MODULE_ID + "/" + CommentsActivity.this.item.getId() + "/0/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken;
            CommentsActivity.access$1202(CommentsActivity.this, JSONParser.parseCommentsUrl(str));
          }
          while (true)
          {
            if (CommentsActivity.this.comments != null)
              Collections.reverse(CommentsActivity.this.comments);
            try
            {
              if (CommentsActivity.this.comments.isEmpty())
                CommentsActivity.this.handler.sendEmptyMessage(5);
              while (true)
              {
                while (true)
                {
                  CommentsActivity.this.handler.sendEmptyMessage(3);
                  return;
                  try
                  {
                    FileInputStream localFileInputStream = new FileInputStream(CommentsActivity.this.cachePath + "/" + "ca-" + CommentsActivity.this.item.getId() + "-0");
                    ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
                    CommentsActivity.access$1202(CommentsActivity.this, (ArrayList)localObjectInputStream.readObject());
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
                break;
                CommentsActivity.this.handler.sendEmptyMessage(4);
              }
            }
            catch (NullPointerException localNullPointerException)
            {
              while (true)
                CommentsActivity.this.handler.sendEmptyMessage(5);
            }
          }
        }
      }).start();
      Statics.onCommentPushedListeners.add(this);
      return;
      Statics.isOnline = false;
      continue;
      Statics.isOnline = false;
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
      if ((paramInt2 == -1) && (this.action == ACTIONS.SEND_MESSAGE))
        startActivityForResult(this.actionIntent, 10003);
    while (true)
    {
      return;
      ArrayList localArrayList;
      if (paramInt1 == 10001)
        if (paramInt2 == -1)
          localArrayList = (ArrayList)paramIntent.getSerializableExtra("messages");
      try
      {
        if (!localArrayList.isEmpty())
          this.comments = localArrayList;
        label79: this.handler.sendEmptyMessage(4);
        return;
        if ((paramInt1 != 10003) || (paramInt2 != -1))
          continue;
        return;
      }
      catch (NullPointerException localNullPointerException)
      {
        break label79;
      }
    }
  }

  public void onClick(View paramView)
  {
    if (paramView == this.postCommentButton)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        if (!localNetworkInfo.isConnectedOrConnecting())
          break label101;
        if (Authorization.isAuthorized())
          break label44;
        openAuthDialog();
      }
    }
    return;
    label44: Intent localIntent = new Intent(this, SendMessageActivity.class);
    localIntent.putExtra("user", Authorization.getAuthorizedUser());
    localIntent.putExtra("Widget", this.widget);
    localIntent.putExtra("item", this.item);
    startActivityForResult(localIntent, 10003);
    return;
    label101: this.handler.sendEmptyMessage(6);
  }

  public void onCommentPushed(CommentItem paramCommentItem)
  {
    if (paramCommentItem != null)
    {
      if ((paramCommentItem.getReplyId() != 0L) || (paramCommentItem.getTrackId() != this.item.getId()))
        break label87;
      if (this.comments == null)
        this.comments = new ArrayList();
      this.comments.add(paramCommentItem);
      if (this.comments.size() != 1)
        break label76;
      this.handler.sendEmptyMessage(4);
    }
    label76: label87: 
    do
    {
      return;
      this.handler.sendEmptyMessage(7);
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
      this.handler.sendEmptyMessage(7);
      return;
    }
  }

  public void onCommentsUpdate(ImageItem paramImageItem, CommentItem paramCommentItem, int paramInt1, int paramInt2, ArrayList<CommentItem> paramArrayList)
  {
    if (paramImageItem == null);
    do
      while (true)
      {
        return;
        if (paramCommentItem != null)
          break;
        if (paramImageItem.getId() != this.item.getId())
          continue;
        Collections.reverse(paramArrayList);
        if ((this.comments.isEmpty()) && (!paramArrayList.isEmpty()))
          this.handler.sendEmptyMessageDelayed(4, 150L);
        while (true)
        {
          this.comments = paramArrayList;
          return;
          this.adapter.setItems(paramArrayList);
          this.handler.sendEmptyMessageDelayed(7, 100L);
        }
      }
    while (paramImageItem.getId() != this.item.getId());
    ArrayList localArrayList = this.comments;
    for (int i = 0; i < localArrayList.size(); i++)
    {
      if (((CommentItem)localArrayList.get(i)).getId() != paramCommentItem.getId())
        continue;
      ((CommentItem)localArrayList.get(i)).setCommentsCount(paramInt1);
    }
    this.handler.sendEmptyMessageDelayed(7, 100L);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void start()
  {
  }

  private static enum ACTIONS
  {
    static
    {
      ACTIONS[] arrayOfACTIONS = new ACTIONS[2];
      arrayOfACTIONS[0] = ACTION_NO;
      arrayOfACTIONS[1] = SEND_MESSAGE;
      $VALUES = arrayOfACTIONS;
    }
  }

  private class ImageDownloadTask extends AsyncTask<ImageItem, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    protected Void doInBackground(ImageItem[] paramArrayOfImageItem)
    {
      try
      {
        new BitmapFactory.Options().inSampleSize = 4;
        if (isCancelled())
        {
          CommentsActivity.this.downloadComplete();
          return null;
        }
        paramArrayOfImageItem[0].setImagePath(CommentsActivity.this.cachePath + "/images/" + Utils.md5(paramArrayOfImageItem[0].getImageUrl()));
        if ((paramArrayOfImageItem[0].getImagePath().length() > 0) && (new File(paramArrayOfImageItem[0].getImagePath()).exists()))
        {
          CommentsActivity.this.downloadComplete();
          return null;
        }
        if (paramArrayOfImageItem[0].getImageUrl().length() == 0)
        {
          CommentsActivity.this.downloadComplete();
          return null;
        }
        SystemClock.sleep(10L);
        while (true)
        {
          ByteArrayBuffer localByteArrayBuffer;
          try
          {
            BufferedInputStream localBufferedInputStream = new BufferedInputStream(new URL(URLDecoder.decode(paramArrayOfImageItem[0].getImageUrl())).openConnection().getInputStream());
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
          File localFile = new File(CommentsActivity.this.cachePath + "/images/");
          if (!localFile.exists())
            localFile.mkdirs();
          String str = CommentsActivity.this.cachePath + "/images/" + Utils.md5(paramArrayOfImageItem[0].getImageUrl());
          FileOutputStream localFileOutputStream = new FileOutputStream(new File(str));
          localFileOutputStream.write(localByteArrayBuffer.toByteArray());
          localFileOutputStream.close();
          CommentsActivity.this.downloadRegistration(str);
        }
      }
      catch (Exception localException1)
      {
      }
      return null;
    }

    protected void onPostExecute(Void paramVoid)
    {
      CommentsActivity.this.downloadComplete();
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.CommentsActivity
 * JD-Core Version:    0.6.0
 */