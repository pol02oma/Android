package com.ibuildapp.romanblack.PhotoGalleryPlugin;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.appbuilder.sdk.android.Utils;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class GalleryAdapter extends BaseAdapter
{
  HashMap<Integer, Bitmap> bitmaps = new HashMap();
  private String cachePath = "";
  private Context ctx = null;
  private boolean decodeEnabled = true;
  private Display display = null;
  ImageDownloadTask idt = null;
  private int imageHeight = 0;
  private int imageWidth = 0;
  private ArrayList<ImageItem> items = null;
  private LayoutInflater layoutInflater = null;
  private DisplayMetrics metrix = null;

  public GalleryAdapter(ArrayList<ImageItem> paramArrayList, Context paramContext)
  {
    this.items = paramArrayList;
    this.ctx = paramContext;
    this.display = ((Activity)paramContext).getWindowManager().getDefaultDisplay();
    this.metrix = paramContext.getResources().getDisplayMetrics();
    this.imageHeight = (int)((this.display.getWidth() - 40.0F * this.metrix.density) / 4.0F);
    this.imageWidth = this.imageHeight;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.idt = new ImageDownloadTask(null);
    this.idt.execute(new ArrayList[] { paramArrayList });
  }

  private void compressImage(ImageItem paramImageItem)
  {
    while (true)
    {
      int i;
      int j;
      int k;
      int m;
      try
      {
        BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
        localOptions1.inJustDecodeBounds = true;
        int n;
        try
        {
          BitmapFactory.decodeFile(paramImageItem.getImagePath(), localOptions1);
          DisplayMetrics localDisplayMetrics = this.ctx.getResources().getDisplayMetrics();
          i = localDisplayMetrics.heightPixels;
          j = localDisplayMetrics.widthPixels;
          k = localOptions1.outHeight;
          m = localOptions1.outWidth;
          n = 1;
          break label163;
          m /= 2;
          k /= 2;
          n *= 2;
        }
        catch (Throwable localThrowable)
        {
          Log.d("", "");
          return;
        }
        BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
        localOptions2.inSampleSize = n;
        Bitmap localBitmap = BitmapFactory.decodeFile(paramImageItem.getImagePath(), localOptions2);
        localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(paramImageItem.getImagePath()));
        localBitmap.recycle();
        return;
      }
      catch (Exception localException)
      {
        Log.d("", "");
        return;
      }
      label163: if (m > j)
        continue;
      if (k <= i)
        continue;
    }
  }

  private void createThumbnail(String paramString1, String paramString2, ImageItem paramImageItem)
  {
    try
    {
      Bitmap localBitmap2 = decodeImageFile(paramImageItem.getImagePath());
      localBitmap1 = localBitmap2;
      if (localBitmap1 == null);
    }
    catch (Exception localException1)
    {
      try
      {
        File localFile1 = new File(this.cachePath + "/thumbs/");
        if (!localFile1.exists())
          localFile1.mkdirs();
        File localFile2 = new File(paramString2);
        if (!localFile2.exists())
          localFile2.createNewFile();
        localBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(localFile2));
        localBitmap1.recycle();
        System.gc();
        paramImageItem.setImageThumbPath(paramString2);
        return;
        localException1 = localException1;
        Log.d("", "");
        Bitmap localBitmap1 = null;
      }
      catch (Exception localException2)
      {
        Log.d("", "");
      }
    }
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
      int i1;
      if ((i / 2 < this.imageWidth) || (j / 2 < this.imageHeight))
      {
        BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
        localOptions2.inSampleSize = k;
        localBitmap = BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions2);
        if (i <= j)
          break label199;
        m = (i - j) / 2;
        n = 0;
        i1 = j;
      }
      while (true)
      {
        float f = (-4 + this.imageWidth) / i1;
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(f, f);
        return Bitmap.createBitmap(localBitmap, m, n, i1, i1, localMatrix, true);
        i /= 2;
        j /= 2;
        k *= 2;
        break;
        label199: n = (j - i) / 2;
        i1 = i;
        m = 0;
      }
    }
    catch (Exception localException)
    {
      Log.w("IMAGE TRANSFORMATION", localException);
    }
    return null;
  }

  private void downloadComplete()
  {
    notifyDataSetChanged();
  }

  private void downloadRegistration(int paramInt, String paramString)
  {
    ((ImageItem)this.items.get(paramInt)).setImagePath(paramString);
  }

  private void viewUpdated()
  {
    notifyDataSetChanged();
  }

  public void cleanBitmaps(boolean paramBoolean)
  {
    if (!paramBoolean);
    for (boolean bool = true; ; bool = false)
    {
      this.decodeEnabled = bool;
      Iterator localIterator = this.bitmaps.keySet().iterator();
      while (localIterator.hasNext())
      {
        Integer localInteger = (Integer)localIterator.next();
        ((Bitmap)this.bitmaps.get(localInteger)).recycle();
      }
    }
    this.bitmaps.clear();
    notifyDataSetChanged();
  }

  public void disableDecoding()
  {
    this.decodeEnabled = false;
  }

  public void enableDecoding()
  {
    this.decodeEnabled = true;
    notifyDataSetChanged();
  }

  public int getCount()
  {
    if (this.items == null)
      return 0;
    return this.items.size();
  }

  public Object getItem(int paramInt)
  {
    if (this.items == null)
      return null;
    try
    {
      Object localObject = this.items.get(paramInt);
      return localObject;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
    }
    return null;
  }

  public long getItemId(int paramInt)
  {
    if (this.items == null)
      return -1L;
    try
    {
      long l = ((ImageItem)this.items.get(paramInt)).getId();
      return l;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
    }
    return -1L;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ImageView localImageView;
    Bitmap localBitmap;
    Integer localInteger;
    if (paramView == null)
    {
      localImageView = new ImageView(this.ctx);
      localImageView.setLayoutParams(new AbsListView.LayoutParams(this.imageWidth, this.imageHeight));
      localImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      localImageView.setBackgroundColor(-1);
      int i = (int)this.metrix.density;
      localImageView.setPadding(i, i, i, i);
      localImageView.setImageResource(R.drawable.romanblack_photogallery_no_image);
      if (((ImageItem)this.items.get(paramInt)).getImageThumbPath().length() <= 0)
        break label259;
      localBitmap = null;
      localInteger = new Integer(paramInt);
      if (!this.bitmaps.containsKey(localInteger))
        break label183;
      localBitmap = (Bitmap)this.bitmaps.get(localInteger);
    }
    while (true)
    {
      while (true)
      {
        if (localBitmap == null)
          break label248;
        localImageView.setImageBitmap(localBitmap);
        return localImageView;
        try
        {
          localImageView = (ImageView)paramView;
        }
        catch (ClassCastException localClassCastException)
        {
          localImageView = new ImageView(this.ctx);
        }
      }
      break;
      label183: if (this.decodeEnabled)
      {
        try
        {
          localBitmap = decodeImageFile(((ImageItem)this.items.get(paramInt)).getImageThumbPath());
          this.bitmaps.put(localInteger, localBitmap);
        }
        catch (Exception localException)
        {
          Log.w("IMAGE ADAPTER", localException);
        }
        continue;
      }
      System.gc();
      localBitmap = null;
    }
    label248: localImageView.setImageResource(R.drawable.romanblack_photogallery_picture_placeholder);
    return localImageView;
    label259: View localView = this.layoutInflater.inflate(R.layout.romanblack_photogallery_adapter_progress, null);
    localView.setLayoutParams(new AbsListView.LayoutParams(this.imageWidth, this.imageHeight));
    return localView;
  }

  public void setCachePath(String paramString)
  {
    this.cachePath = paramString;
  }

  public void setImageSize(int paramInt1, int paramInt2)
  {
    this.imageWidth = paramInt1;
    this.imageHeight = paramInt2;
  }

  private class ImageDownloadTask extends AsyncTask<ArrayList<ImageItem>, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    protected Void doInBackground(ArrayList<ImageItem>[] paramArrayOfArrayList)
    {
      try
      {
        new BitmapFactory.Options().inSampleSize = 4;
        for (int i = 0; ; i++)
          if (i < paramArrayOfArrayList[0].size())
          {
            if (isCancelled())
              return null;
            if (((ImageItem)paramArrayOfArrayList[0].get(i)).getImageUrl().length() == 0)
              continue;
            String str1 = GalleryAdapter.this.cachePath + "/" + Utils.md5(((ImageItem)paramArrayOfArrayList[0].get(i)).getImageUrl());
            String str2 = GalleryAdapter.this.cachePath + "/thumbs/" + Utils.md5(((ImageItem)paramArrayOfArrayList[0].get(i)).getImageUrl());
            if ((((ImageItem)paramArrayOfArrayList[0].get(i)).getImageUrl().length() > 0) && (new File(str1).exists()))
            {
              GalleryAdapter.this.downloadRegistration(i, str1);
              publishProgress(new String[0]);
              if (new File(str2).exists())
              {
                ((ImageItem)paramArrayOfArrayList[0].get(i)).setImageThumbPath(str2);
                publishProgress(new String[0]);
              }
              else
              {
                GalleryAdapter.this.createThumbnail(str1, str2, (ImageItem)paramArrayOfArrayList[0].get(i));
              }
            }
            else
            {
              BufferedInputStream localBufferedInputStream;
              FileOutputStream localFileOutputStream;
              try
              {
                URL localURL = new URL(((ImageItem)paramArrayOfArrayList[0].get(i)).getImageUrl());
                SystemClock.sleep(10L);
                localBufferedInputStream = new BufferedInputStream(localURL.openConnection().getInputStream());
                localFileOutputStream = new FileOutputStream(new File(str1));
                byte[] arrayOfByte = new byte[512];
                Arrays.fill(arrayOfByte, 0);
                while (true)
                {
                  int j = localBufferedInputStream.read(arrayOfByte, 0, 512);
                  if (j == -1)
                    break;
                  localFileOutputStream.write(arrayOfByte, 0, j);
                  Arrays.fill(arrayOfByte, 0);
                }
              }
              catch (Exception localException2)
              {
                Log.e("IMAGE ADAPTER", "An error has occurred downloading the image: " + ((ImageItem)paramArrayOfArrayList[0].get(i)).getImageUrl() + " " + localException2);
              }
              while (true)
              {
                GalleryAdapter.this.createThumbnail(str1, str2, (ImageItem)paramArrayOfArrayList[0].get(i));
                publishProgress(new String[0]);
                break;
                localFileOutputStream.flush();
                localFileOutputStream.close();
                localBufferedInputStream.close();
                GalleryAdapter.this.downloadRegistration(i, str1);
                GalleryAdapter.this.compressImage((ImageItem)paramArrayOfArrayList[0].get(i));
                System.gc();
              }
            }
          }
          else
          {
            return null;
          }
      }
      catch (Exception localException1)
      {
      }
      return null;
    }

    protected void onPostExecute(Void paramVoid)
    {
      GalleryAdapter.this.downloadComplete();
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
      GalleryAdapter.this.viewUpdated();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.GalleryAdapter
 * JD-Core Version:    0.6.0
 */