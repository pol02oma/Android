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
import android.graphics.Matrix;
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
import android.widget.ImageView.ScaleType;
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
import com.ibuildapp.romanblack.VideoPlugin.callbacks.OnCommentPushedListener;
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

public class CommentsToCommentActivity extends AppBuilderModuleMain
  implements View.OnClickListener, AdapterView.OnItemClickListener, OnCommentPushedListener
{
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
        Toast.makeText(CommentsToCommentActivity.this, CommentsToCommentActivity.this.getResources().getString(R.string.romanblack_video_alert_cannot_init), 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            CommentsToCommentActivity.this.finish();
          }
        }
        , 2000L);
        return;
      case 1:
        CommentsToCommentActivity.this.closeActivity();
        return;
      case 2:
        CommentsToCommentActivity.this.showProgressDialog();
        return;
      case 3:
        CommentsToCommentActivity.this.hideProgressDialog();
        return;
      case 4:
        CommentsToCommentActivity.this.showCommentsList();
        return;
      case 5:
        CommentsToCommentActivity.this.showNoComments();
        return;
      case 6:
        Toast.makeText(CommentsToCommentActivity.this, CommentsToCommentActivity.this.getResources().getString(R.string.romanblack_video_alert_no_internet), 1).show();
        return;
      case 7:
      }
      CommentsToCommentActivity.this.refreshList();
    }
  };
  private LinearLayout hasCommentsLayout = null;
  private LinearLayout hasntCommentsLayout = null;
  private LinearLayout headerLayout = null;
  private View headerView = null;
  private int imageHeight = 50;
  private int imageWidth = 50;
  private CommentItem item = null;
  private ArrayList<CommentItem> items = null;
  private ListView listView = null;
  private LinearLayout mainLayout = null;
  private int position = 0;
  private Button postCommentButton = null;
  private ProgressDialog progressDialog = null;
  private ImageView thumbImageView = null;
  private TextView titleTextView = null;
  private VideoItem videoItem = null;
  private Widget widget = null;

  private void cacheMessages()
  {
    File localFile1 = new File(this.cachePath);
    if (!localFile1.exists())
      localFile1.mkdirs();
    File localFile2 = new File(this.cachePath + "/" + "ca-" + this.videoItem.getId() + "-" + this.item.getId());
    if (localFile2.exists())
      localFile2.delete();
    try
    {
      localFile2.createNewFile();
      localArrayList = new ArrayList();
      if ((this.comments.size() <= 20) && (!this.comments.isEmpty()))
        localArrayList = this.comments;
    }
    catch (IOException localIOException2)
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
        localIOException2 = localIOException2;
      }
      catch (IOException localIOException1)
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
      File localFile = new File(paramString);
      BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
      localOptions1.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions1);
      int i = localOptions1.outWidth;
      int j = localOptions1.outHeight;
      int k = 1;
      Bitmap localBitmap;
      int m;
      int n;
      if ((i / 2 < this.imageWidth) || (j / 2 < this.imageHeight))
      {
        BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
        localOptions2.inSampleSize = k;
        localBitmap = BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions2);
        if (i <= j)
          break label215;
        m = (i - j) / 2;
        n = 1;
      }
      for (int i1 = j - 1; ; i1 = i - 1)
      {
        float f1 = (-4 + this.imageWidth) / i1;
        float f2 = (-4 + this.imageHeight) / i1;
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(f1, f2);
        return Bitmap.createBitmap(localBitmap, m, n, i1, i1, localMatrix, true);
        i /= 2;
        j /= 2;
        k *= 2;
        break;
        label215: m = 1;
        n = (j - i) / 2;
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
    this.item.setAvatarPath(paramString);
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void postCommentButtonClick()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null)
    {
      if (!localNetworkInfo.isConnectedOrConnecting())
        break label191;
      if (!Authorization.isAuthorized())
      {
        this.actionIntent = new Intent(this, SendMessageActivity.class);
        this.actionIntent.putExtra("Widget", this.widget);
        this.actionIntent.putExtra("video", this.videoItem);
        this.actionIntent.putExtra("message", this.item);
        this.action = ACTIONS.SEND_MESSAGE;
        Intent localIntent2 = new Intent(this, AuthorizationActivity.class);
        localIntent2.putExtra("Widget", this.widget);
        startActivityForResult(localIntent2, 10000);
      }
    }
    else
    {
      return;
    }
    Intent localIntent1 = new Intent(this, SendMessageActivity.class);
    localIntent1.putExtra("Widget", this.widget);
    localIntent1.putExtra("video", this.videoItem);
    localIntent1.putExtra("message", this.item);
    startActivityForResult(localIntent1, 10003);
    return;
    label191: this.handler.sendEmptyMessage(6);
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

  private void setThumb()
  {
    if ((this.thumbImageView != null) && (this.item.getAvatarPath().length() > 0))
      this.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    try
    {
      Bitmap localBitmap2 = decodeImageFile(this.item.getAvatarPath());
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
          this.headerView.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
          this.listView.addHeaderView(this.headerView, null, false);
          this.hasCommentsLayout.setVisibility(0);
          this.hasntCommentsLayout.setVisibility(8);
          Collections.reverse(this.comments);
          this.adapter = new CommentsAdapter(this, this.comments, this.videoItem, this.widget);
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
      int i = (int)TypedValue.applyDimension(1, 100.0F, getResources().getDisplayMetrics());
      this.headerLayout.addView(this.headerView, new LinearLayout.LayoutParams(-1, i));
      this.hasntCommentsLayout.setVisibility(0);
      this.hasCommentsLayout.setVisibility(8);
      postCommentButtonClick();
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
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_loading), true);
      this.progressDialog.setCancelable(true);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          CommentsToCommentActivity.this.handler.sendEmptyMessage(1);
        }
      });
    }
  }

  public void create()
  {
    requestWindowFeature(1);
    setContentView(R.layout.romanblack_video_comments_main);
    Intent localIntent = getIntent();
    this.items = ((ArrayList)localIntent.getSerializableExtra("items"));
    if (this.items == null)
    {
      this.handler.sendEmptyMessage(0);
      return;
    }
    if (this.items.isEmpty())
    {
      this.handler.sendEmptyMessage(0);
      return;
    }
    this.position = localIntent.getIntExtra("position", 0);
    try
    {
      this.item = ((CommentItem)this.items.get(this.position));
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
    this.videoItem = ((VideoItem)localIntent.getSerializableExtra("video"));
    if (this.videoItem == null)
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
      this.mainLayout = ((LinearLayout)findViewById(R.id.romanblack_video_comments_main));
      this.mainLayout.setBackgroundColor(Statics.color1);
      setTopBarTitle(getString(R.string.romanblack_video_player_comments));
      swipeBlock();
      setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          CommentsToCommentActivity.this.finish();
        }
      });
      this.listView = ((ListView)findViewById(R.id.romanblack_video_comments_main_listview));
      this.listView.setCacheColorHint(Color.parseColor("#41464b"));
      this.listView.setHeaderDividersEnabled(false);
      this.headerView = LayoutInflater.from(this).inflate(R.layout.romanblack_video_commentstocomments_header, null);
      this.headerView.setBackgroundColor(Statics.color1);
      this.thumbImageView = ((ImageView)this.headerView.findViewById(R.id.romanblack_video_commentstocomment_listview_header_thumb));
      ImageDownloadTask localImageDownloadTask = new ImageDownloadTask(null);
      CommentItem[] arrayOfCommentItem = new CommentItem[1];
      arrayOfCommentItem[0] = this.item;
      localImageDownloadTask.execute(arrayOfCommentItem);
      this.titleTextView = ((TextView)this.headerView.findViewById(R.id.romanblack_video_commentstocomment_listview_header_name));
      this.titleTextView.setText(this.item.getAuthor());
      this.descriptionTextView = ((TextView)this.headerView.findViewById(R.id.romanblack_video_commentstocomment_listview_header_text));
      this.descriptionTextView.setText(this.item.getText());
      this.hasCommentsLayout = ((LinearLayout)findViewById(R.id.romanblack_video_comments_main_has_layout));
      this.hasCommentsLayout.setVisibility(8);
      this.hasntCommentsLayout = ((LinearLayout)findViewById(R.id.romanblack_video_comments_main_hasnt_layout));
      this.headerLayout = ((LinearLayout)findViewById(R.id.romanblack_video_comments_main_header));
      this.postCommentButton = ((Button)findViewById(R.id.romanblack_video_comments_main_post_comment));
      this.postCommentButton.setOnClickListener(this);
      View localView = getLayoutInflater().inflate(R.layout.romanblack_video_write_btn, null);
      ((ImageView)localView.findViewById(R.id.romanblack_video_main_comments_voice)).setColorFilter(this.bottomBarDesign.leftButtonDesign.textColor);
      setTopBarRightVeiw(localView, true, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          CommentsToCommentActivity.this.postCommentButtonClick();
        }
      });
      this.handler.sendEmptyMessage(2);
      new Thread(new Runnable()
      {
        public void run()
        {
          NetworkInfo localNetworkInfo = ((ConnectivityManager)CommentsToCommentActivity.this.getSystemService("connectivity")).getActiveNetworkInfo();
          boolean bool = false;
          if (localNetworkInfo != null)
            bool = localNetworkInfo.isConnectedOrConnecting();
          if (bool)
          {
            String str = Statics.BASE_URL + "/getcomments/" + com.appbuilder.sdk.android.Statics.appId + "/" + Statics.MODULE_ID + "/" + CommentsToCommentActivity.this.videoItem.getId() + "/" + CommentsToCommentActivity.this.item.getId() + "/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken;
            CommentsToCommentActivity.access$1002(CommentsToCommentActivity.this, JSONParser.parseCommentsUrl(str));
          }
          try
          {
            if (CommentsToCommentActivity.this.comments.isEmpty())
              CommentsToCommentActivity.this.handler.sendEmptyMessage(5);
            while (true)
            {
              while (true)
              {
                CommentsToCommentActivity.this.handler.sendEmptyMessage(3);
                return;
                try
                {
                  FileInputStream localFileInputStream = new FileInputStream(CommentsToCommentActivity.this.cachePath + "/" + "ca-" + CommentsToCommentActivity.this.videoItem.getId() + "-" + CommentsToCommentActivity.this.item.getId());
                  ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
                  CommentsToCommentActivity.access$1002(CommentsToCommentActivity.this, (ArrayList)localObjectInputStream.readObject());
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
              CommentsToCommentActivity.this.handler.sendEmptyMessage(4);
            }
          }
          catch (NullPointerException localNullPointerException)
          {
            while (true)
              CommentsToCommentActivity.this.handler.sendEmptyMessage(5);
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
        if (paramInt1 != 10003)
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
      postCommentButtonClick();
  }

  public void onCommentPushed(CommentItem paramCommentItem)
  {
    if ((paramCommentItem != null) && (paramCommentItem.getReplyId() == this.item.getId()) && (paramCommentItem.getTrackId() == this.videoItem.getId()))
    {
      if (this.comments == null)
        this.comments = new ArrayList();
      this.comments.add(paramCommentItem);
      if (this.comments.size() == 1)
        this.handler.sendEmptyMessage(4);
    }
    else
    {
      return;
    }
    this.handler.sendEmptyMessage(7);
  }

  public void onCommentsUpdate(VideoItem paramVideoItem, CommentItem paramCommentItem, int paramInt1, int paramInt2, ArrayList<CommentItem> paramArrayList)
  {
    if ((paramVideoItem == null) || (paramCommentItem == null));
    do
      return;
    while ((this.videoItem.getId() != paramVideoItem.getId()) || (this.item.getId() != paramCommentItem.getId()));
    try
    {
      Collections.reverse(paramArrayList);
      this.adapter.setItems(paramArrayList);
      label53: this.handler.sendEmptyMessageDelayed(7, 100L);
      if ((this.comments.isEmpty()) && (!paramArrayList.isEmpty()))
        this.handler.sendEmptyMessageDelayed(4, 150L);
      this.comments = paramArrayList;
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      break label53;
    }
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

  private class ImageDownloadTask extends AsyncTask<CommentItem, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    protected Void doInBackground(CommentItem[] paramArrayOfCommentItem)
    {
      try
      {
        new BitmapFactory.Options().inSampleSize = 4;
        if (isCancelled())
        {
          CommentsToCommentActivity.this.downloadComplete();
          return null;
        }
        paramArrayOfCommentItem[0].setAvatarPath(CommentsToCommentActivity.this.cachePath + "/images/" + Utils.md5(paramArrayOfCommentItem[0].getAvatarUrl()));
        if ((paramArrayOfCommentItem[0].getAvatarPath().length() > 0) && (new File(paramArrayOfCommentItem[0].getAvatarPath()).exists()))
        {
          CommentsToCommentActivity.this.downloadComplete();
          return null;
        }
        if (paramArrayOfCommentItem[0].getAvatarUrl().length() == 0)
        {
          CommentsToCommentActivity.this.downloadComplete();
          return null;
        }
        SystemClock.sleep(10L);
        while (true)
        {
          ByteArrayBuffer localByteArrayBuffer;
          try
          {
            BufferedInputStream localBufferedInputStream = new BufferedInputStream(new URL(URLDecoder.decode(paramArrayOfCommentItem[0].getAvatarUrl())).openConnection().getInputStream());
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
          File localFile = new File(CommentsToCommentActivity.this.cachePath + "/images/");
          if (!localFile.exists())
            localFile.mkdirs();
          String str = CommentsToCommentActivity.this.cachePath + "/images/" + Utils.md5(paramArrayOfCommentItem[0].getAvatarUrl());
          FileOutputStream localFileOutputStream = new FileOutputStream(new File(str));
          localFileOutputStream.write(localByteArrayBuffer.toByteArray());
          localFileOutputStream.close();
          CommentsToCommentActivity.this.downloadRegistration(str);
        }
      }
      catch (Exception localException1)
      {
      }
      return null;
    }

    protected void onPostExecute(Void paramVoid)
    {
      CommentsToCommentActivity.this.downloadComplete();
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.CommentsToCommentActivity
 * JD-Core Version:    0.6.0
 */