package com.ibuildapp.romanblack.VideoPlugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
import android.widget.Toast;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.flurry.android.FlurryAgent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.util.ByteArrayBuffer;

public class MediaAdapter extends BaseAdapter
{
  private HashMap<Integer, Bitmap> bitmaps = new HashMap();
  private String cachePath = "";
  private Activity ctx = null;
  private FBLikePressedListener fBLikePressedListener = null;
  private int imageHeight = 68;
  private int imageWidth = 96;
  private LayoutInflater inflater = null;
  private ArrayList<VideoItem> items = null;
  private SharePressedListener sharePressedListener = null;
  private HashMap<Integer, View> views = new HashMap();
  private Widget widget = null;

  public MediaAdapter(Activity paramActivity, ArrayList<VideoItem> paramArrayList, Widget paramWidget)
  {
    this.ctx = paramActivity;
    this.items = paramArrayList;
    this.widget = paramWidget;
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
      if ((i / 2 < this.imageWidth) || (j / 2 < this.imageHeight))
      {
        BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
        localOptions2.inSampleSize = k;
        localBitmap = BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions2);
        if (i <= j)
          break label193;
        ((i - j) / 2);
      }
      for (int m = j - 1; ; m = i - 1)
      {
        float f1 = (-4 + this.imageWidth) / m;
        float f2 = (-4 + this.imageHeight) / m;
        new Matrix().postScale(f1, f2);
        return localBitmap;
        i /= 2;
        j /= 2;
        k *= 2;
        break;
        label193: ((j - i) / 2);
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
    ((VideoItem)this.items.get(paramInt)).setCoverPath(paramString);
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
      paramView = this.inflater.inflate(R.layout.romanblack_video_list_item, null);
    int i = R.id.romanblack_video_listview_item_thumb;
    ImageView localImageView1 = (ImageView)paramView.findViewById(i);
    thumbClickListener localthumbClickListener = new thumbClickListener(paramInt);
    localImageView1.setOnClickListener(localthumbClickListener);
    Bitmap localBitmap;
    Integer localInteger;
    label154: LinearLayout localLinearLayout1;
    LinearLayout localLinearLayout2;
    ImageView localImageView2;
    TextView localTextView3;
    TextView localTextView4;
    label433: TextView localTextView5;
    if (((VideoItem)this.items.get(paramInt)).getCoverUrl().length() > 0)
    {
      if ((localImageView1 != null) && (((VideoItem)this.items.get(paramInt)).getCoverPath().length() > 0))
      {
        localImageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        localBitmap = null;
        localInteger = new Integer(paramInt);
        if (!this.bitmaps.containsKey(localInteger))
          break label674;
        localBitmap = (Bitmap)this.bitmaps.get(localInteger);
        if (localBitmap != null)
          localImageView1.setImageBitmap(localBitmap);
      }
      int j = R.id.romanblack_video_listview_item_title;
      TextView localTextView1 = (TextView)paramView.findViewById(j);
      localTextView1.setText(((VideoItem)this.items.get(paramInt)).getTitle());
      localTextView1.setTextColor(Statics.color3);
      int k = R.id.romanblack_video_listview_item_description;
      TextView localTextView2 = (TextView)paramView.findViewById(k);
      localTextView2.setText(((VideoItem)this.items.get(paramInt)).getDescription());
      localTextView2.setTextColor(Statics.color4);
      int m = R.id.romanblack_video_listview_item_share_btn;
      localLinearLayout1 = (LinearLayout)paramView.findViewById(m);
      btnShareListener localbtnShareListener = new btnShareListener(paramInt);
      localLinearLayout1.setOnClickListener(localbtnShareListener);
      if (Statics.sharingOn.equalsIgnoreCase("off"))
        localLinearLayout1.setVisibility(4);
      int n = R.id.romanblack_video_listview_item_like_btn;
      localLinearLayout2 = (LinearLayout)paramView.findViewById(n);
      btnLikeListener localbtnLikeListener = new btnLikeListener(paramInt);
      localLinearLayout2.setOnClickListener(localbtnLikeListener);
      int i1 = R.id.romanblack_video_list_like_image;
      localImageView2 = (ImageView)paramView.findViewById(i1);
      int i2 = R.id.romanblack_video_list_like_caption;
      localTextView3 = (TextView)paramView.findViewById(i2);
      int i3 = R.id.romanblack_video_listview_item_comments_count_layput;
      LinearLayout localLinearLayout3 = (LinearLayout)paramView.findViewById(i3);
      if (!Statics.commentsOn.equalsIgnoreCase("on"))
        localLinearLayout3.setVisibility(4);
      int i4 = R.id.romanblack_video_listview_item_comments_count;
      localTextView4 = (TextView)paramView.findViewById(i4);
      if (((VideoItem)this.items.get(paramInt)).getTotalComments() != 0)
        break label731;
      localTextView4.setText("+");
      int i5 = R.id.romanblack_video_listview_item_likes_count;
      localTextView5 = (TextView)paramView.findViewById(i5);
      localTextView5.setText(((VideoItem)this.items.get(paramInt)).getLikesCount() + "");
      if (Utils.isChemeDark(Statics.color1))
        break label801;
      localTextView5.setTextColor(Color.parseColor("#ffffff"));
    }
    while (true)
    {
      while (true)
      {
        if (!Statics.isOnline)
        {
          localImageView2.setAlpha(100);
          int i7 = R.id.romanblack_video_list_share_image;
          ((ImageView)paramView.findViewById(i7)).setAlpha(100);
          localTextView3.setTextColor(Color.parseColor("#9bffffff"));
          int i8 = R.id.romanblack_video_list_share_caption;
          ((TextView)paramView.findViewById(i8)).setTextColor(Color.parseColor("#9bffffff"));
          localLinearLayout2.getBackground().setAlpha(100);
          localLinearLayout1.getBackground().setAlpha(100);
        }
        if ((((VideoItem)this.items.get(paramInt)).isLiked()) && (Authorization.getAuthorizedUser(1) != null))
        {
          localImageView2.setAlpha(100);
          localTextView3.setTextColor(Color.parseColor("#9bffffff"));
          localLinearLayout2.getBackground().setAlpha(100);
        }
        playerStartListener localplayerStartListener = new playerStartListener(paramInt);
        paramView.setOnClickListener(localplayerStartListener);
        int i6 = Statics.color1;
        paramView.setBackgroundColor(i6);
        return paramView;
        try
        {
          label674: localBitmap = decodeImageFile(((VideoItem)this.items.get(paramInt)).getCoverPath());
          this.bitmaps.put(localInteger, localBitmap);
        }
        catch (Exception localException)
        {
          Log.d("", "");
        }
      }
      break;
      localImageView1.setImageBitmap(null);
      break label154;
      label731: if (((VideoItem)this.items.get(paramInt)).getTotalComments() > 99)
      {
        localTextView4.setText("99+");
        break label433;
      }
      localTextView4.setText(((VideoItem)this.items.get(paramInt)).getTotalComments() + "");
      break label433;
      label801: localTextView5.setTextColor(Color.parseColor("#000000"));
    }
  }

  public void setCachePath(String paramString)
  {
    this.cachePath = paramString;
  }

  public void setSharePressedListener(SharePressedListener paramSharePressedListener)
  {
    this.sharePressedListener = paramSharePressedListener;
  }

  public void setfBLikePressedListener(FBLikePressedListener paramFBLikePressedListener)
  {
    this.fBLikePressedListener = paramFBLikePressedListener;
  }

  public static abstract interface FBLikePressedListener
  {
    public abstract void onLikePressed(int paramInt);
  }

  private class ImageDownloadTask extends AsyncTask<ArrayList<VideoItem>, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    protected Void doInBackground(ArrayList<VideoItem>[] paramArrayOfArrayList)
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
            break label395;
          if (isCancelled())
            return null;
          String str1 = MediaAdapter.this.cachePath + "/images/" + Utils.md5(((VideoItem)paramArrayOfArrayList[0].get(i)).getCoverUrl());
          if ((str1.length() <= 0) || (!new File(((VideoItem)paramArrayOfArrayList[0].get(i)).getCoverPath()).exists()))
            continue;
          MediaAdapter.this.downloadRegistration(i, str1);
          publishProgress(new String[0]);
          break label397;
          if (((VideoItem)paramArrayOfArrayList[0].get(i)).getCoverUrl().length() == 0)
            break label397;
          SystemClock.sleep(10L);
          try
          {
            BufferedInputStream localBufferedInputStream = new BufferedInputStream(new URL(URLDecoder.decode(((VideoItem)paramArrayOfArrayList[0].get(i)).getCoverUrl())).openConnection().getInputStream());
            localByteArrayBuffer = new ByteArrayBuffer(32);
            int j = localBufferedInputStream.read();
            if (j == -1)
              break label255;
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
        label255: File localFile = new File(MediaAdapter.this.cachePath + "/images/");
        if (!localFile.exists())
          localFile.mkdirs();
        String str2 = MediaAdapter.this.cachePath + "/images/" + Utils.md5(((VideoItem)paramArrayOfArrayList[0].get(i)).getCoverUrl());
        FileOutputStream localFileOutputStream = new FileOutputStream(new File(str2));
        localFileOutputStream.write(localByteArrayBuffer.toByteArray());
        localFileOutputStream.close();
        MediaAdapter.this.downloadRegistration(i, str2);
        continue;
        label395: return null;
        label397: i++;
      }
    }

    protected void onPostExecute(Void paramVoid)
    {
      MediaAdapter.this.downloadComplete();
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
      MediaAdapter.this.viewUpdated();
    }
  }

  public static abstract interface SharePressedListener
  {
    public abstract void onSharePressed(int paramInt);
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
      Intent localIntent = new Intent(MediaAdapter.this.ctx, CommentsActivity.class);
      localIntent.putExtra("items", MediaAdapter.this.items);
      localIntent.putExtra("position", this.position);
      localIntent.putExtra("cachePath", MediaAdapter.this.cachePath);
      localIntent.putExtra("Widget", MediaAdapter.this.widget);
      MediaAdapter.this.ctx.startActivity(localIntent);
    }
  }

  public class btnLikeListener
    implements View.OnClickListener
  {
    private int position = 0;

    public btnLikeListener(int arg2)
    {
      int i;
      this.position = i;
    }

    public void onClick(View paramView)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)MediaAdapter.this.ctx.getSystemService("connectivity")).getActiveNetworkInfo();
      boolean bool = false;
      if (localNetworkInfo != null)
        bool = localNetworkInfo.isConnectedOrConnecting();
      if (bool)
      {
        if (MediaAdapter.this.fBLikePressedListener != null)
          MediaAdapter.this.fBLikePressedListener.onLikePressed(this.position);
        return;
      }
      Toast.makeText(MediaAdapter.this.ctx, MediaAdapter.this.ctx.getResources().getString(R.string.romanblack_video_alert_like_need_internet), 1).show();
    }
  }

  public class btnShareListener
    implements View.OnClickListener
  {
    private int position = 0;

    public btnShareListener(int arg2)
    {
      int i;
      this.position = i;
    }

    public void onClick(View paramView)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)MediaAdapter.this.ctx.getSystemService("connectivity")).getActiveNetworkInfo();
      boolean bool = false;
      if (localNetworkInfo != null)
        bool = localNetworkInfo.isConnectedOrConnecting();
      if (bool)
      {
        if (MediaAdapter.this.sharePressedListener != null)
          MediaAdapter.this.sharePressedListener.onSharePressed(this.position);
        return;
      }
      Toast.makeText(MediaAdapter.this.ctx, MediaAdapter.this.ctx.getResources().getString(R.string.romanblack_video_alert_share_need_internet), 1).show();
    }
  }

  public class playerStartListener
    implements View.OnClickListener
  {
    private int position = 0;

    public playerStartListener(int arg2)
    {
      int i;
      this.position = i;
    }

    public void onClick(View paramView)
    {
      if (Statics.commentsOn.equals("on"))
      {
        Intent localIntent = new Intent(MediaAdapter.this.ctx, VideoPlayer.class);
        localIntent.putExtra("items", MediaAdapter.this.items);
        localIntent.putExtra("position", this.position);
        localIntent.putExtra("cachePath", MediaAdapter.this.cachePath);
        localIntent.putExtra("Widget", MediaAdapter.this.widget);
        MediaAdapter.this.ctx.startActivity(localIntent);
      }
    }
  }

  public class thumbClickListener
    implements View.OnClickListener
  {
    private int position = 0;

    public thumbClickListener(int arg2)
    {
      int i;
      this.position = i;
    }

    private void startVideoPlayer()
    {
      if ((VideoPlugin.userID != null) && (VideoPlugin.userID.equals("186589")))
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put("Watch", ((VideoItem)MediaAdapter.this.items.get(this.position)).getTitle());
        FlurryAgent.logEvent("VideoPlugin", localHashMap, true);
      }
      if (((VideoItem)MediaAdapter.this.items.get(this.position)).getUrl().contains("youtube.com"))
      {
        MediaAdapter.this.ctx.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com")).setData(Uri.parse(((VideoItem)MediaAdapter.this.items.get(this.position)).getUrl())));
        return;
      }
      if (((VideoItem)MediaAdapter.this.items.get(this.position)).getUrl().contains("vimeo.com"))
      {
        MediaAdapter.this.ctx.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((VideoItem)MediaAdapter.this.items.get(this.position)).getUrl())));
        return;
      }
      if (((VideoItem)MediaAdapter.this.items.get(this.position)).getUrl().contains("m3u8"))
      {
        Intent localIntent1 = new Intent(MediaAdapter.this.ctx, VideoBuffer.class);
        localIntent1.putExtra("position", this.position);
        localIntent1.putExtra("items", MediaAdapter.this.items);
        localIntent1.putExtra("Widget", MediaAdapter.this.widget);
        MediaAdapter.this.ctx.startActivityForResult(localIntent1, 10002);
        return;
      }
      Intent localIntent2 = new Intent(MediaAdapter.this.ctx, PlayerWebActivity.class);
      localIntent2.putExtra("position", this.position);
      localIntent2.putExtra("items", MediaAdapter.this.items);
      localIntent2.putExtra("Widget", MediaAdapter.this.widget);
      MediaAdapter.this.ctx.startActivity(localIntent2);
    }

    public void onClick(View paramView)
    {
      startVideoPlayer();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.MediaAdapter
 * JD-Core Version:    0.6.0
 */