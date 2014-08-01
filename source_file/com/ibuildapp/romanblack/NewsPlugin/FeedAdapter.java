package com.ibuildapp.romanblack.NewsPlugin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.appbuilder.sdk.android.Utils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.util.ByteArrayBuffer;

public class FeedAdapter extends BaseAdapter
{
  private HashMap<Integer, Bitmap> bitmaps = new HashMap();
  private String cachePath = "";
  private ImageDownloadTask downloadTask = null;
  private int imageHeight = 75;
  private int imageWidth = 75;
  private ArrayList<FeedItem> items;
  private LayoutInflater layoutInflater;

  FeedAdapter(Context paramContext, ArrayList<FeedItem> paramArrayList, int paramInt)
  {
    this.items = paramArrayList;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.downloadTask = new ImageDownloadTask(null);
    ImageDownloadTask localImageDownloadTask = this.downloadTask;
    ArrayList[] arrayOfArrayList = new ArrayList[1];
    arrayOfArrayList[0] = this.items;
    localImageDownloadTask.execute(arrayOfArrayList);
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
      while (true)
      {
        BitmapFactory.Options localOptions2;
        if ((i / 2 < this.imageWidth) || (j / 2 < this.imageHeight))
        {
          localOptions2 = new BitmapFactory.Options();
          localOptions2.inSampleSize = k;
          localOptions2.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        try
        {
          System.gc();
          Bitmap localBitmap3 = BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions2);
          localObject = localBitmap3;
          if (i > j)
          {
            int i3 = i - j;
            i2 = i3 / 2;
            n = 0;
            i1 = j;
            float f = (-4 + this.imageWidth) / i1;
            Matrix localMatrix = new Matrix();
            localMatrix.postScale(f, f);
            Bitmap localBitmap1 = Bitmap.createBitmap((Bitmap)localObject, i2, n, i1, i1, localMatrix, true);
            ((Bitmap)localObject).recycle();
            return localBitmap1;
            i /= 2;
            j /= 2;
            k *= 2;
          }
        }
        catch (Exception localException4)
        {
          try
          {
            Log.d("", "");
            localObject = null;
          }
          catch (Exception localException2)
          {
            Log.d("", "");
            return null;
          }
        }
        catch (OutOfMemoryError localOutOfMemoryError1)
        {
          while (true)
          {
            Object localObject;
            Log.d("", "");
            System.gc();
            try
            {
              Bitmap localBitmap2 = BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions2);
              localObject = localBitmap2;
            }
            catch (Exception localException3)
            {
              Log.d("", "");
              localObject = null;
            }
            catch (OutOfMemoryError localOutOfMemoryError2)
            {
              Log.e("decodeImageFile", "OutOfMemoryError");
              localObject = null;
            }
            continue;
            int m = j - i;
            int n = m / 2;
            int i1 = i;
            int i2 = 0;
          }
        }
      }
    }
    catch (Exception localException1)
    {
      Log.w("IMAGE TRANSFORMATION", localException1);
    }
    return (Bitmap)null;
  }

  private void downloadComplete()
  {
    notifyDataSetChanged();
  }

  private void downloadRegistration(int paramInt, String paramString)
  {
    ((FeedItem)this.items.get(paramInt)).setImagePath(paramString);
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(this.cachePath + "/cache.data"));
      localObjectOutputStream.writeObject(this.items);
      localObjectOutputStream.flush();
      localObjectOutputStream.close();
      Log.d("IMAGES PLUGIN CACHE DATA", "SUCCESS");
      return;
    }
    catch (Exception localException)
    {
      Log.w("IMAGES PLUGIN CACHE DATA", localException);
    }
  }

  private void viewUpdated()
  {
    notifyDataSetChanged();
  }

  public int getCount()
  {
    return this.items.size();
  }

  public FeedItem getItem(int paramInt)
  {
    return (FeedItem)this.items.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return 0L;
  }

  public int getItemViewType(int paramInt)
  {
    if (((FeedItem)this.items.get(paramInt)).hasImage())
      return 0;
    return 1;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = null;
    label59: ImageView localImageView2;
    Bitmap localBitmap;
    Integer localInteger;
    if (paramView == null)
    {
      if (localView == null)
      {
        if (!((FeedItem)this.items.get(paramInt)).hasImage())
          break label346;
        localView = this.layoutInflater.inflate(R.layout.romanblack_feed_item_image, null);
        ((ImageView)localView.findViewById(R.id.romanblack_rss_image)).setImageResource(R.drawable.romanblack_rss_no_image);
      }
      TextView localTextView1 = (TextView)localView.findViewById(R.id.romanblack_rss_title);
      localTextView1.setTextColor(Statics.color3);
      TextView localTextView2 = (TextView)localView.findViewById(R.id.romanblack_rss_description);
      localTextView2.setTextColor(Statics.color4);
      TextView localTextView3 = (TextView)localView.findViewById(R.id.romanblack_rss_pubdate);
      localTextView3.setTextColor(Statics.color4);
      localTextView1.setText(((FeedItem)this.items.get(paramInt)).getTitle());
      localTextView2.setText(((FeedItem)this.items.get(paramInt)).getAnounce(70));
      localTextView3.setText(((FeedItem)this.items.get(paramInt)).getPubdate(""));
      if (((FeedItem)this.items.get(paramInt)).hasImage())
      {
        localImageView2 = (ImageView)localView.findViewById(R.id.romanblack_rss_image);
        if ((localImageView2 != null) && (((FeedItem)this.items.get(paramInt)).getImagePath().length() > 0))
        {
          localBitmap = null;
          localInteger = new Integer(paramInt);
          if (!this.bitmaps.containsKey(localInteger))
            break label362;
          localBitmap = (Bitmap)this.bitmaps.get(localInteger);
        }
      }
    }
    ImageView localImageView1;
    while (true)
    {
      if (localBitmap != null)
        localImageView2.setImageBitmap(localBitmap);
      ((ViewGroup)localView).getChildAt(0).setBackgroundColor(Statics.color1);
      localImageView1 = (ImageView)localView.findViewById(R.id.news_arrow);
      if (Statics.BackColorToFontColor(Statics.color1) != -16777216)
        break label411;
      localImageView1.setImageResource(R.drawable.news_arrow_light);
      return localView;
      localView = paramView;
      break;
      label346: localView = this.layoutInflater.inflate(R.layout.romanblack_feed_item, null);
      break label59;
      try
      {
        label362: localBitmap = decodeImageFile(((FeedItem)this.items.get(paramInt)).getImagePath());
        this.bitmaps.put(localInteger, localBitmap);
      }
      catch (Exception localException)
      {
        Log.w("NEWS ADAPTER", localException);
      }
    }
    label411: localImageView1.setImageResource(R.drawable.news_arrow);
    return localView;
  }

  public int getViewTypeCount()
  {
    return 2;
  }

  public void setCachePath(String paramString)
  {
    this.cachePath = paramString;
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(paramString + "/cache.data"));
      localObjectOutputStream.writeObject(this.items);
      localObjectOutputStream.flush();
      localObjectOutputStream.close();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void setImageSize(int paramInt1, int paramInt2)
  {
    this.imageWidth = paramInt1;
    this.imageHeight = paramInt2;
  }

  private class ImageDownloadTask extends AsyncTask<ArrayList<FeedItem>, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    protected Void doInBackground(ArrayList<FeedItem>[] paramArrayOfArrayList)
    {
      new BitmapFactory.Options().inSampleSize = 4;
      int i = 0;
      if (i < paramArrayOfArrayList[0].size())
      {
        if (isCancelled())
          return null;
        if (((FeedItem)paramArrayOfArrayList[0].get(i)).getImageUrl().length() == 0);
        String str;
        while (true)
        {
          i++;
          break;
          if ((((FeedItem)paramArrayOfArrayList[0].get(i)).getImagePath().length() > 0) && (new File(((FeedItem)paramArrayOfArrayList[0].get(i)).getImagePath()).exists()))
            continue;
          str = FeedAdapter.this.cachePath + "/" + Utils.md5(((FeedItem)paramArrayOfArrayList[0].get(i)).getImageUrl());
          if (!new File(str).exists())
            break label186;
          ((FeedItem)paramArrayOfArrayList[0].get(i)).setImagePath(str);
          publishProgress(new String[0]);
        }
        label186: ByteArrayBuffer localByteArrayBuffer1;
        int j;
        try
        {
          BufferedInputStream localBufferedInputStream1 = new BufferedInputStream(new URL(((FeedItem)paramArrayOfArrayList[0].get(i)).getImageUrl()).openConnection().getInputStream());
          localByteArrayBuffer1 = new ByteArrayBuffer(32);
          while (true)
          {
            int m = localBufferedInputStream1.read();
            if (m == -1)
              break;
            localByteArrayBuffer1.append((byte)m);
          }
        }
        catch (Exception localException1)
        {
          Log.e("IMAGE ADAPTER", "An error has occurred downloading the image: " + ((FeedItem)paramArrayOfArrayList[0].get(i)).getImageUrl() + " " + localException1);
          j = 0;
        }
        label308: ByteArrayBuffer localByteArrayBuffer2;
        if (j == 0)
          try
          {
            BufferedInputStream localBufferedInputStream2 = new BufferedInputStream(new URL(((FeedItem)paramArrayOfArrayList[0].get(i)).getImageUrlAlt()).openConnection().getInputStream());
            localByteArrayBuffer2 = new ByteArrayBuffer(32);
            while (true)
            {
              int k = localBufferedInputStream2.read();
              if (k == -1)
                break;
              localByteArrayBuffer2.append((byte)k);
            }
          }
          catch (Exception localException2)
          {
            Log.e("", "");
          }
        while (true)
        {
          publishProgress(new String[0]);
          break;
          FileOutputStream localFileOutputStream2 = new FileOutputStream(new File(str));
          localFileOutputStream2.write(localByteArrayBuffer1.toByteArray());
          localFileOutputStream2.flush();
          localFileOutputStream2.close();
          FeedAdapter.this.downloadRegistration(i, str);
          j = 1;
          break label308;
          FileOutputStream localFileOutputStream1 = new FileOutputStream(new File(str));
          localFileOutputStream1.write(localByteArrayBuffer2.toByteArray());
          localFileOutputStream1.close();
          FeedAdapter.this.downloadRegistration(i, str);
        }
      }
      return null;
    }

    protected void onPostExecute(Void paramVoid)
    {
      FeedAdapter.this.downloadComplete();
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
      FeedAdapter.this.viewUpdated();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.FeedAdapter
 * JD-Core Version:    0.6.0
 */