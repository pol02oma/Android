package com.ibuildapp.romanblack.PhotoGalleryPlugin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.Album;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AlbumsAdapter extends BaseAdapter
{
  private ArrayList<Album> albums = new ArrayList();
  private HashMap<Integer, Bitmap> bitmaps = new HashMap();
  private String cachePath = "";
  private Context ctx = null;
  private boolean decodeEnabled = true;
  private ImageDownloadTask idt = new ImageDownloadTask(null);
  private int imageHeight = 50;
  private int imageWidth = 50;
  private LayoutInflater inflater = null;

  public AlbumsAdapter(Context paramContext, ArrayList<Album> paramArrayList, String paramString)
  {
    this.ctx = paramContext;
    this.albums = paramArrayList;
    this.cachePath = paramString;
    float f = paramContext.getResources().getDisplayMetrics().density;
    this.imageHeight = Math.round(50.0F * f);
    this.imageWidth = Math.round(50.0F * f);
    this.inflater = LayoutInflater.from(paramContext);
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
    ((ImageItem)((Album)this.albums.get(paramInt)).getImages().get(0)).setImagePath(paramString);
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
    return this.albums.size();
  }

  public Object getItem(int paramInt)
  {
    try
    {
      Object localObject = this.albums.get(paramInt);
      return localObject;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      return null;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    return null;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = this.inflater.inflate(R.layout.romanblack_photogallery_album_item, null);
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.romanblack_photogallery_album_item_thumb);
    localImageView.setImageResource(R.drawable.romanblack_photogallery_picture_placeholder);
    Bitmap localBitmap;
    Integer localInteger;
    if (((!((Album)this.albums.get(paramInt)).isRSS()) || ((((Album)this.albums.get(paramInt)).isRSS()) && (((Album)this.albums.get(paramInt)).getImages().size() > 0))) && (((ImageItem)((Album)this.albums.get(paramInt)).getImages().get(0)).getImagePath().length() > 0))
    {
      localBitmap = null;
      localInteger = new Integer(paramInt);
      if (!this.bitmaps.containsKey(localInteger))
        break label219;
      localBitmap = (Bitmap)this.bitmaps.get(localInteger);
      if (localBitmap == null)
        break label294;
      localImageView.setImageBitmap(localBitmap);
    }
    while (true)
    {
      while (true)
      {
        TextView localTextView = (TextView)paramView.findViewById(R.id.romanblack_photogallery_album_item_title);
        localTextView.setTextColor(Statics.color2);
        localTextView.setText(((Album)this.albums.get(paramInt)).getTitle());
        paramView.setBackgroundColor(Statics.color1);
        return paramView;
        label219: if (!this.decodeEnabled)
          break label285;
        try
        {
          localBitmap = decodeImageFile(((ImageItem)((Album)this.albums.get(paramInt)).getImages().get(0)).getImagePath());
          this.bitmaps.put(localInteger, localBitmap);
        }
        catch (Exception localException)
        {
          Log.w("IMAGE ADAPTER", localException);
        }
      }
      break;
      label285: System.gc();
      localBitmap = null;
      break;
      label294: localImageView.setImageResource(R.drawable.romanblack_photogallery_picture_placeholder);
    }
  }

  private class ImageDownloadTask extends AsyncTask<ArrayList<Album>, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    // ERROR //
    protected Void doInBackground(ArrayList<Album>[] paramArrayOfArrayList)
    {
      // Byte code:
      //   0: new 30	android/graphics/BitmapFactory$Options
      //   3: dup
      //   4: invokespecial 31	android/graphics/BitmapFactory$Options:<init>	()V
      //   7: iconst_4
      //   8: putfield 35	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   11: iconst_0
      //   12: istore_3
      //   13: iload_3
      //   14: aload_1
      //   15: iconst_0
      //   16: aaload
      //   17: invokevirtual 41	java/util/ArrayList:size	()I
      //   20: if_icmpge +665 -> 685
      //   23: aload_0
      //   24: invokevirtual 45	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:isCancelled	()Z
      //   27: ifeq +5 -> 32
      //   30: aconst_null
      //   31: areturn
      //   32: aload_1
      //   33: iconst_0
      //   34: aaload
      //   35: iload_3
      //   36: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   39: checkcast 51	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album
      //   42: invokevirtual 54	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album:isRSS	()Z
      //   45: ifeq +62 -> 107
      //   48: aload_1
      //   49: iconst_0
      //   50: aaload
      //   51: iload_3
      //   52: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   55: checkcast 51	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album
      //   58: invokevirtual 58	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album:getImages	()Ljava/util/ArrayList;
      //   61: invokevirtual 41	java/util/ArrayList:size	()I
      //   64: ifne +43 -> 107
      //   67: new 60	com/ibuildapp/romanblack/PhotoGalleryPlugin/utils/FeedParser
      //   70: dup
      //   71: aload_1
      //   72: iconst_0
      //   73: aaload
      //   74: iload_3
      //   75: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   78: checkcast 51	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album
      //   81: invokevirtual 64	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album:getRssUrl	()Ljava/lang/String;
      //   84: invokespecial 67	com/ibuildapp/romanblack/PhotoGalleryPlugin/utils/FeedParser:<init>	(Ljava/lang/String;)V
      //   87: astore 29
      //   89: aload_1
      //   90: iconst_0
      //   91: aaload
      //   92: iload_3
      //   93: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   96: checkcast 51	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album
      //   99: aload 29
      //   101: invokevirtual 70	com/ibuildapp/romanblack/PhotoGalleryPlugin/utils/FeedParser:parseFeed	()Ljava/util/ArrayList;
      //   104: invokevirtual 74	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album:setImages	(Ljava/util/ArrayList;)V
      //   107: aload_1
      //   108: iconst_0
      //   109: aaload
      //   110: iload_3
      //   111: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   114: checkcast 51	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album
      //   117: invokevirtual 58	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album:getImages	()Ljava/util/ArrayList;
      //   120: iconst_0
      //   121: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   124: checkcast 76	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem
      //   127: astore 5
      //   129: aload 5
      //   131: invokevirtual 79	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImageUrl	()Ljava/lang/String;
      //   134: invokevirtual 84	java/lang/String:length	()I
      //   137: ifne +6 -> 143
      //   140: goto +547 -> 687
      //   143: new 86	java/lang/StringBuilder
      //   146: dup
      //   147: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   150: aload_0
      //   151: getfield 11	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:this$0	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;
      //   154: invokestatic 93	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter:access$100	(Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;)Ljava/lang/String;
      //   157: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   160: ldc 99
      //   162: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   165: aload 5
      //   167: invokevirtual 79	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImageUrl	()Ljava/lang/String;
      //   170: invokestatic 105	com/appbuilder/sdk/android/Utils:md5	(Ljava/lang/String;)Ljava/lang/String;
      //   173: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   176: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   179: astore 6
      //   181: aload 5
      //   183: invokevirtual 79	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImageUrl	()Ljava/lang/String;
      //   186: invokevirtual 84	java/lang/String:length	()I
      //   189: ifle +42 -> 231
      //   192: new 110	java/io/File
      //   195: dup
      //   196: aload 6
      //   198: invokespecial 111	java/io/File:<init>	(Ljava/lang/String;)V
      //   201: invokevirtual 114	java/io/File:exists	()Z
      //   204: ifeq +27 -> 231
      //   207: aload_0
      //   208: getfield 11	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:this$0	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;
      //   211: iload_3
      //   212: aload 6
      //   214: invokestatic 118	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter:access$200	(Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;ILjava/lang/String;)V
      //   217: aload_0
      //   218: iconst_0
      //   219: anewarray 81	java/lang/String
      //   222: invokevirtual 122	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:publishProgress	([Ljava/lang/Object;)V
      //   225: goto +462 -> 687
      //   228: astore_2
      //   229: aconst_null
      //   230: areturn
      //   231: new 124	java/net/URL
      //   234: dup
      //   235: aload 5
      //   237: invokevirtual 79	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImageUrl	()Ljava/lang/String;
      //   240: invokespecial 125	java/net/URL:<init>	(Ljava/lang/String;)V
      //   243: astore 7
      //   245: ldc2_w 126
      //   248: invokestatic 133	android/os/SystemClock:sleep	(J)V
      //   251: new 135	java/io/BufferedInputStream
      //   254: dup
      //   255: aload 7
      //   257: invokevirtual 139	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   260: invokevirtual 145	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   263: invokespecial 148	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   266: astore 24
      //   268: new 110	java/io/File
      //   271: dup
      //   272: aload 6
      //   274: invokespecial 111	java/io/File:<init>	(Ljava/lang/String;)V
      //   277: astore 25
      //   279: new 150	java/io/FileOutputStream
      //   282: dup
      //   283: aload 25
      //   285: invokespecial 153	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   288: astore 26
      //   290: sipush 512
      //   293: newarray byte
      //   295: astore 27
      //   297: aload 27
      //   299: iconst_0
      //   300: invokestatic 159	java/util/Arrays:fill	([BB)V
      //   303: aload 24
      //   305: aload 27
      //   307: iconst_0
      //   308: sipush 512
      //   311: invokevirtual 163	java/io/BufferedInputStream:read	([BII)I
      //   314: istore 28
      //   316: iload 28
      //   318: iconst_m1
      //   319: if_icmpeq +297 -> 616
      //   322: aload 26
      //   324: aload 27
      //   326: iconst_0
      //   327: iload 28
      //   329: invokevirtual 167	java/io/FileOutputStream:write	([BII)V
      //   332: aload 27
      //   334: iconst_0
      //   335: invokestatic 159	java/util/Arrays:fill	([BB)V
      //   338: goto -35 -> 303
      //   341: astore 8
      //   343: ldc 169
      //   345: new 86	java/lang/StringBuilder
      //   348: dup
      //   349: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   352: ldc 171
      //   354: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   357: aload 5
      //   359: invokevirtual 79	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImageUrl	()Ljava/lang/String;
      //   362: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   365: ldc 173
      //   367: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   370: aload 8
      //   372: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   375: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   378: invokestatic 182	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   381: pop
      //   382: aload_0
      //   383: getfield 11	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:this$0	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;
      //   386: aload_1
      //   387: iconst_0
      //   388: aaload
      //   389: iload_3
      //   390: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   393: checkcast 51	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album
      //   396: invokevirtual 58	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album:getImages	()Ljava/util/ArrayList;
      //   399: iconst_0
      //   400: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   403: checkcast 76	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem
      //   406: invokevirtual 185	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImagePath	()Ljava/lang/String;
      //   409: invokestatic 189	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter:access$400	(Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;Ljava/lang/String;)Landroid/graphics/Bitmap;
      //   412: astore 23
      //   414: aload 23
      //   416: astore 12
      //   418: new 86	java/lang/StringBuilder
      //   421: dup
      //   422: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   425: aload_0
      //   426: getfield 11	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:this$0	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;
      //   429: invokestatic 93	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter:access$100	(Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;)Ljava/lang/String;
      //   432: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   435: ldc 191
      //   437: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   440: aload_1
      //   441: iconst_0
      //   442: aaload
      //   443: iload_3
      //   444: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   447: checkcast 51	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album
      //   450: invokevirtual 58	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album:getImages	()Ljava/util/ArrayList;
      //   453: iconst_0
      //   454: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   457: checkcast 76	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem
      //   460: invokevirtual 79	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImageUrl	()Ljava/lang/String;
      //   463: invokestatic 105	com/appbuilder/sdk/android/Utils:md5	(Ljava/lang/String;)Ljava/lang/String;
      //   466: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   469: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   472: astore 13
      //   474: aload 12
      //   476: ifnull +129 -> 605
      //   479: new 110	java/io/File
      //   482: dup
      //   483: new 86	java/lang/StringBuilder
      //   486: dup
      //   487: invokespecial 87	java/lang/StringBuilder:<init>	()V
      //   490: aload_0
      //   491: getfield 11	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:this$0	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;
      //   494: invokestatic 93	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter:access$100	(Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;)Ljava/lang/String;
      //   497: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   500: ldc 191
      //   502: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   505: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   508: invokespecial 111	java/io/File:<init>	(Ljava/lang/String;)V
      //   511: astore 14
      //   513: aload 14
      //   515: invokevirtual 114	java/io/File:exists	()Z
      //   518: ifne +9 -> 527
      //   521: aload 14
      //   523: invokevirtual 194	java/io/File:mkdirs	()Z
      //   526: pop
      //   527: new 110	java/io/File
      //   530: dup
      //   531: aload 13
      //   533: invokespecial 111	java/io/File:<init>	(Ljava/lang/String;)V
      //   536: astore 17
      //   538: aload 17
      //   540: invokevirtual 114	java/io/File:exists	()Z
      //   543: ifne +9 -> 552
      //   546: aload 17
      //   548: invokevirtual 197	java/io/File:createNewFile	()Z
      //   551: pop
      //   552: getstatic 203	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
      //   555: astore 18
      //   557: new 150	java/io/FileOutputStream
      //   560: dup
      //   561: aload 17
      //   563: invokespecial 153	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   566: astore 19
      //   568: aload 12
      //   570: aload 18
      //   572: bipush 100
      //   574: aload 19
      //   576: invokevirtual 209	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
      //   579: pop
      //   580: aload_1
      //   581: iconst_0
      //   582: aaload
      //   583: iload_3
      //   584: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   587: checkcast 51	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album
      //   590: invokevirtual 58	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album:getImages	()Ljava/util/ArrayList;
      //   593: iconst_0
      //   594: invokevirtual 49	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   597: checkcast 76	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem
      //   600: aload 13
      //   602: invokevirtual 212	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:setImageThumbPath	(Ljava/lang/String;)V
      //   605: aload_0
      //   606: iconst_0
      //   607: anewarray 81	java/lang/String
      //   610: invokevirtual 122	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:publishProgress	([Ljava/lang/Object;)V
      //   613: goto +74 -> 687
      //   616: aload 26
      //   618: invokevirtual 215	java/io/FileOutputStream:flush	()V
      //   621: aload 26
      //   623: invokevirtual 218	java/io/FileOutputStream:close	()V
      //   626: aload 24
      //   628: invokevirtual 219	java/io/BufferedInputStream:close	()V
      //   631: aload_0
      //   632: getfield 11	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:this$0	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;
      //   635: iload_3
      //   636: aload 6
      //   638: invokestatic 118	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter:access$200	(Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;ILjava/lang/String;)V
      //   641: aload_0
      //   642: getfield 11	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter$ImageDownloadTask:this$0	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;
      //   645: aload 5
      //   647: invokestatic 223	com/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter:access$300	(Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/AlbumsAdapter;Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem;)V
      //   650: invokestatic 228	java/lang/System:gc	()V
      //   653: goto -271 -> 382
      //   656: astore 10
      //   658: ldc 230
      //   660: ldc 230
      //   662: invokestatic 233	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   665: pop
      //   666: aconst_null
      //   667: astore 12
      //   669: goto -251 -> 418
      //   672: astore 15
      //   674: ldc 230
      //   676: ldc 230
      //   678: invokestatic 233	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   681: pop
      //   682: goto -77 -> 605
      //   685: aconst_null
      //   686: areturn
      //   687: iinc 3 1
      //   690: goto -677 -> 13
      //   693: astore 4
      //   695: goto -8 -> 687
      //
      // Exception table:
      //   from	to	target	type
      //   0	11	228	java/lang/Exception
      //   13	30	228	java/lang/Exception
      //   32	107	228	java/lang/Exception
      //   107	129	228	java/lang/Exception
      //   129	140	228	java/lang/Exception
      //   143	225	228	java/lang/Exception
      //   343	382	228	java/lang/Exception
      //   418	474	228	java/lang/Exception
      //   605	613	228	java/lang/Exception
      //   658	666	228	java/lang/Exception
      //   674	682	228	java/lang/Exception
      //   231	303	341	java/lang/Exception
      //   303	316	341	java/lang/Exception
      //   322	338	341	java/lang/Exception
      //   616	653	341	java/lang/Exception
      //   382	414	656	java/lang/Exception
      //   479	527	672	java/lang/Exception
      //   527	552	672	java/lang/Exception
      //   552	605	672	java/lang/Exception
      //   107	129	693	java/lang/IndexOutOfBoundsException
    }

    protected void onPostExecute(Void paramVoid)
    {
      AlbumsAdapter.this.downloadComplete();
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
      AlbumsAdapter.this.viewUpdated();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.AlbumsAdapter
 * JD-Core Version:    0.6.0
 */