package com.ibuildapp.romanblack.VideoPlugin;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.apache.http.util.ByteArrayBuffer;

public class CommentsAdapter extends BaseAdapter
{
  private HashMap<String, Bitmap> bitmaps = new HashMap();
  private String cachePath = "";
  private Context ctx = null;
  private int imageHeight = 25;
  private int imageWidth = 25;
  private LayoutInflater inflater = null;
  private ArrayList<CommentItem> items = null;
  private VideoItem video = null;
  private Widget widget = null;

  public CommentsAdapter(Context paramContext, ArrayList<CommentItem> paramArrayList, VideoItem paramVideoItem, Widget paramWidget)
  {
    this.ctx = paramContext;
    this.items = paramArrayList;
    this.widget = paramWidget;
    this.video = paramVideoItem;
    this.inflater = LayoutInflater.from(this.ctx);
    new ImageDownloadTask(null).execute(new ArrayList[] { paramArrayList });
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
    notifyDataSetChanged();
  }

  private void downloadRegistration(int paramInt, String paramString)
  {
    ((CommentItem)this.items.get(paramInt)).setAvatarPath(paramString);
  }

  private void viewUpdated()
  {
    notifyDataSetChanged();
  }

  public int getCount()
  {
    try
    {
      int i = this.items.size();
      return i;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    return 0;
  }

  public Object getItem(int paramInt)
  {
    try
    {
      Object localObject = this.items.get(paramInt);
      return localObject;
    }
    catch (NullPointerException localNullPointerException)
    {
      return null;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
    }
    return null;
  }

  public long getItemId(int paramInt)
  {
    return 0L;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = this.inflater.inflate(R.layout.romanblack_video_comments_list_item, null);
    int i = R.id.romanblack_video_comments_list_item_thumb;
    ImageView localImageView = (ImageView)paramView.findViewById(i);
    Bitmap localBitmap;
    String str4;
    if (((CommentItem)this.items.get(paramInt)).getAvatarUrl().length() > 0)
      if ((localImageView != null) && (((CommentItem)this.items.get(paramInt)).getAvatarPath().length() > 0))
      {
        localImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        localBitmap = null;
        str4 = ((CommentItem)this.items.get(paramInt)).getAvatarUrl();
        if (!this.bitmaps.containsValue(str4))
          break label475;
        localBitmap = (Bitmap)this.bitmaps.get(str4);
      }
    while (true)
    {
      if (localBitmap != null)
      {
        localImageView.setImageBitmap(localBitmap);
        localImageView.setBackgroundColor(Color.argb(180, 255, 255, 255));
      }
      label162: int j = R.id.romanblack_video_comments_list_item_author;
      TextView localTextView1 = (TextView)paramView.findViewById(j);
      localTextView1.setText(((CommentItem)this.items.get(paramInt)).getAuthor());
      localTextView1.setTextColor(Statics.color3);
      int k = R.id.romanblack_video_comments_list_item_text;
      TextView localTextView2 = (TextView)paramView.findViewById(k);
      localTextView2.setText(((CommentItem)this.items.get(paramInt)).getText());
      localTextView2.setTextColor(Statics.color4);
      int m = R.id.romanblack_video_comments_list_item_date;
      TextView localTextView3 = (TextView)paramView.findViewById(m);
      localTextView3.setTextColor(Statics.color5);
      Object localObject = "";
      try
      {
        long l1 = ((CommentItem)this.items.get(paramInt)).getDate().getTime();
        long l2 = System.currentTimeMillis() - l1;
        if (l2 < 60000L)
        {
          String str1 = this.ctx.getResources().getString(R.string.romanblack_video_date_just_now);
          localObject = str1;
        }
        while (true)
        {
          while (true)
          {
            localTextView3.setText((CharSequence)localObject);
            int n = R.id.romanblack_video_comments_list_item_comments_count_layput;
            localLinearLayout = (LinearLayout)paramView.findViewById(n);
            int i1 = R.id.romanblack_video_comments_list_item_comments_count;
            localTextView4 = (TextView)paramView.findViewById(i1);
            if (((CommentItem)this.items.get(paramInt)).getCommentsCount() <= 0)
              break label864;
            localTextView4.setText(((CommentItem)this.items.get(paramInt)).getCommentsCount() + "+");
            if (((CommentItem)this.items.get(paramInt)).getReplyId() != 0L)
              break label875;
            btnCommentsCountListener localbtnCommentsCountListener = new btnCommentsCountListener(paramInt);
            localLinearLayout.setOnClickListener(localbtnCommentsCountListener);
            int i2 = Statics.color1;
            paramView.setBackgroundColor(i2);
            return paramView;
            try
            {
              label475: localBitmap = decodeImageFile(((CommentItem)this.items.get(paramInt)).getAvatarPath());
              this.bitmaps.put(str4, localBitmap);
            }
            catch (Exception localException3)
            {
              Log.d("", "");
            }
          }
          break;
          localImageView.setImageBitmap(null);
          break label162;
          if (l2 < 120000L)
          {
            localObject = this.ctx.getResources().getString(R.string.romanblack_video_date_one_minute);
            continue;
          }
          if (l2 < 3600000L)
          {
            localObject = l2 / 60L / 1000L + " " + this.ctx.getResources().getString(R.string.romanblack_video_date_minutes);
            continue;
          }
          if (l2 < 7200000L)
          {
            localObject = this.ctx.getResources().getString(R.string.romanblack_video_date_one_hour);
            continue;
          }
          if (l2 < 86400000L)
          {
            localObject = l2 / 60L / 60L / 1000L + " " + this.ctx.getResources().getString(R.string.romanblack_video_date_hours);
            continue;
          }
          if (l2 < 172800000L)
          {
            localObject = this.ctx.getResources().getString(R.string.romanblack_video_date_yesterday);
            continue;
          }
          if (l2 < 432000000L)
          {
            String str2 = l2 / 24L / 60L / 60L / 1000L + " " + this.ctx.getResources().getString(R.string.romanblack_video_date_days);
            localObject = str2;
            continue;
          }
          try
          {
            SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MMM, d");
            String str3 = localSimpleDateFormat.format(((CommentItem)this.items.get(paramInt)).getDate());
            localObject = str3;
          }
          catch (Exception localException2)
          {
            Log.d("", "");
          }
        }
      }
      catch (Exception localException1)
      {
        while (true)
        {
          LinearLayout localLinearLayout;
          TextView localTextView4;
          continue;
          label864: localTextView4.setText("+");
          continue;
          label875: localLinearLayout.setVisibility(8);
        }
      }
    }
  }

  public void setCachePath(String paramString)
  {
    this.cachePath = paramString;
  }

  public void setItems(ArrayList<CommentItem> paramArrayList)
  {
    this.items = paramArrayList;
    new ImageDownloadTask(null).execute(new ArrayList[] { paramArrayList });
  }

  private class ImageDownloadTask extends AsyncTask<ArrayList<CommentItem>, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    protected Void doInBackground(ArrayList<CommentItem>[] paramArrayOfArrayList)
    {
      while (true)
      {
        int i;
        ByteArrayBuffer localByteArrayBuffer;
        try
        {
          new BitmapFactory.Options().inSampleSize = 4;
          i = 0;
          if (i >= paramArrayOfArrayList[0].size())
            break label384;
          if (isCancelled())
            return null;
          String str1 = CommentsAdapter.this.cachePath + "/images/" + Utils.md5(((CommentItem)paramArrayOfArrayList[0].get(i)).getAvatarUrl());
          if ((str1.length() <= 0) || (!new File(str1).exists()))
            continue;
          CommentsAdapter.this.downloadRegistration(i, str1);
          publishProgress(new String[0]);
          break label386;
          if (((CommentItem)paramArrayOfArrayList[0].get(i)).getAvatarUrl().length() == 0)
            break label386;
          SystemClock.sleep(10L);
          try
          {
            BufferedInputStream localBufferedInputStream = new BufferedInputStream(new URL(URLDecoder.decode(((CommentItem)paramArrayOfArrayList[0].get(i)).getAvatarUrl())).openConnection().getInputStream());
            localByteArrayBuffer = new ByteArrayBuffer(32);
            int j = localBufferedInputStream.read();
            if (j == -1)
              break label244;
            localByteArrayBuffer.append((byte)j);
            continue;
          }
          catch (Exception localException2)
          {
            Log.e("", "");
          }
          publishProgress(new String[0]);
        }
        catch (Exception localException1)
        {
          return null;
        }
        label244: File localFile = new File(CommentsAdapter.this.cachePath + "/images/");
        if (!localFile.exists())
          localFile.mkdirs();
        String str2 = CommentsAdapter.this.cachePath + "/images/" + Utils.md5(((CommentItem)paramArrayOfArrayList[0].get(i)).getAvatarUrl());
        FileOutputStream localFileOutputStream = new FileOutputStream(new File(str2));
        localFileOutputStream.write(localByteArrayBuffer.toByteArray());
        localFileOutputStream.close();
        CommentsAdapter.this.downloadRegistration(i, str2);
        continue;
        label384: return null;
        label386: i++;
      }
    }

    protected void onPostExecute(Void paramVoid)
    {
      CommentsAdapter.this.downloadComplete();
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
      CommentsAdapter.this.viewUpdated();
    }
  }

  public class btnCommentsCountListener
    implements View.OnClickListener
  {
    private int position = 0;

    public btnCommentsCountListener(int arg2)
    {
      int i;
      this.position = i;
    }

    public void onClick(View paramView)
    {
      Intent localIntent = new Intent(CommentsAdapter.this.ctx, CommentsToCommentActivity.class);
      localIntent.putExtra("video", CommentsAdapter.this.video);
      localIntent.putExtra("items", CommentsAdapter.this.items);
      localIntent.putExtra("position", this.position);
      localIntent.putExtra("cachePath", CommentsAdapter.this.cachePath);
      localIntent.putExtra("Widget", CommentsAdapter.this.widget);
      CommentsAdapter.this.ctx.startActivity(localIntent);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.CommentsAdapter
 * JD-Core Version:    0.6.0
 */