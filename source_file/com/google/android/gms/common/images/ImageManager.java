package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.internal.ed;
import com.google.android.gms.internal.ev;
import com.google.android.gms.internal.fr;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager
{
  private static final Object Ar = new Object();
  private static HashSet<Uri> As = new HashSet();
  private static ImageManager At;
  private static ImageManager Au;
  private final ExecutorService Av;
  private final b Aw;
  private final Map<a, ImageReceiver> Ax;
  private final Map<Uri, ImageReceiver> Ay;
  private final Context mContext;
  private final Handler mHandler;

  private ImageManager(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.Av = Executors.newFixedThreadPool(4);
    if (paramBoolean)
    {
      this.Aw = new b(this.mContext);
      if (fr.eM())
        dL();
    }
    while (true)
    {
      this.Ax = new HashMap();
      this.Ay = new HashMap();
      return;
      this.Aw = null;
    }
  }

  private Bitmap a(a.a parama)
  {
    if (this.Aw == null)
      return null;
    return (Bitmap)this.Aw.get(parama);
  }

  public static ImageManager a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (Au == null)
        Au = new ImageManager(paramContext, true);
      return Au;
    }
    if (At == null)
      At = new ImageManager(paramContext, false);
    return At;
  }

  private boolean b(a parama)
  {
    ed.ac("ImageManager.cleanupHashMaps() must be called in the main thread");
    if (parama.AI == 1)
      return true;
    ImageReceiver localImageReceiver = (ImageReceiver)this.Ax.get(parama);
    if (localImageReceiver == null)
      return true;
    if (localImageReceiver.AA)
      return false;
    this.Ax.remove(parama);
    localImageReceiver.d(parama);
    return true;
  }

  public static ImageManager create(Context paramContext)
  {
    return a(paramContext, false);
  }

  private void dL()
  {
    this.mContext.registerComponentCallbacks(new e(this.Aw));
  }

  public void a(a parama)
  {
    ed.ac("ImageManager.loadImage() must be called in the main thread");
    boolean bool = b(parama);
    d locald = new d(parama);
    if (bool)
    {
      locald.run();
      return;
    }
    this.mHandler.post(locald);
  }

  public void loadImage(ImageView paramImageView, int paramInt)
  {
    a locala = new a(paramInt);
    locala.a(paramImageView);
    a(locala);
  }

  public void loadImage(ImageView paramImageView, Uri paramUri)
  {
    a locala = new a(paramUri);
    locala.a(paramImageView);
    a(locala);
  }

  public void loadImage(ImageView paramImageView, Uri paramUri, int paramInt)
  {
    a locala = new a(paramUri);
    locala.L(paramInt);
    locala.a(paramImageView);
    a(locala);
  }

  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    a locala = new a(paramUri);
    locala.a(paramOnImageLoadedListener);
    a(locala);
  }

  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt)
  {
    a locala = new a(paramUri);
    locala.L(paramInt);
    locala.a(paramOnImageLoadedListener);
    a(locala);
  }

  private final class ImageReceiver extends ResultReceiver
  {
    boolean AA = false;
    private final ArrayList<a> Az;
    private final Uri mUri;

    ImageReceiver(Uri arg2)
    {
      super();
      Object localObject;
      this.mUri = localObject;
      this.Az = new ArrayList();
    }

    public void c(a parama)
    {
      if (!this.AA);
      for (boolean bool = true; ; bool = false)
      {
        ed.a(bool, "Cannot add an ImageRequest when mHandlingRequests is true");
        ed.ac("ImageReceiver.addImageRequest() must be called in the main thread");
        this.Az.add(parama);
        return;
      }
    }

    public void d(a parama)
    {
      if (!this.AA);
      for (boolean bool = true; ; bool = false)
      {
        ed.a(bool, "Cannot remove an ImageRequest when mHandlingRequests is true");
        ed.ac("ImageReceiver.removeImageRequest() must be called in the main thread");
        this.Az.remove(parama);
        return;
      }
    }

    public void dN()
    {
      Intent localIntent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      localIntent.putExtra("com.google.android.gms.extras.uri", this.mUri);
      localIntent.putExtra("com.google.android.gms.extras.resultReceiver", this);
      localIntent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.a(ImageManager.this).sendBroadcast(localIntent);
    }

    public void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      ParcelFileDescriptor localParcelFileDescriptor = (ParcelFileDescriptor)paramBundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.d(ImageManager.this).execute(new ImageManager.c(ImageManager.this, this.mUri, localParcelFileDescriptor));
    }
  }

  public static abstract interface OnImageLoadedListener
  {
    public abstract void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  }

  private static final class a
  {
    static int a(ActivityManager paramActivityManager)
    {
      return paramActivityManager.getLargeMemoryClass();
    }
  }

  private static final class b extends ev<a.a, Bitmap>
  {
    public b(Context paramContext)
    {
      super();
    }

    private static int w(Context paramContext)
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      int i;
      if ((0x100000 & paramContext.getApplicationInfo().flags) != 0)
      {
        i = 1;
        if ((i == 0) || (!fr.eJ()))
          break label55;
      }
      label55: for (int j = ImageManager.a.a(localActivityManager); ; j = localActivityManager.getMemoryClass())
      {
        return (int)(0.33F * (j * 1048576));
        i = 0;
        break;
      }
    }

    protected int a(a.a parama, Bitmap paramBitmap)
    {
      return paramBitmap.getHeight() * paramBitmap.getRowBytes();
    }

    protected void a(boolean paramBoolean, a.a parama, Bitmap paramBitmap1, Bitmap paramBitmap2)
    {
      super.entryRemoved(paramBoolean, parama, paramBitmap1, paramBitmap2);
    }
  }

  private final class c
    implements Runnable
  {
    private final ParcelFileDescriptor AC;
    private final Uri mUri;

    public c(Uri paramParcelFileDescriptor, ParcelFileDescriptor arg3)
    {
      this.mUri = paramParcelFileDescriptor;
      Object localObject;
      this.AC = localObject;
    }

    public void run()
    {
      ed.ad("LoadBitmapFromDiskRunnable can't be executed in the main thread");
      ParcelFileDescriptor localParcelFileDescriptor = this.AC;
      Object localObject = null;
      boolean bool = false;
      if (localParcelFileDescriptor != null);
      try
      {
        Bitmap localBitmap = BitmapFactory.decodeFileDescriptor(this.AC.getFileDescriptor());
        localObject = localBitmap;
      }
      catch (IOException localIOException)
      {
        try
        {
          this.AC.close();
          localCountDownLatch = new CountDownLatch(1);
          ImageManager.e(ImageManager.this).post(new ImageManager.f(ImageManager.this, this.mUri, localObject, bool, localCountDownLatch));
        }
        catch (IOException localIOException)
        {
          try
          {
            while (true)
            {
              CountDownLatch localCountDownLatch;
              localCountDownLatch.await();
              return;
              localOutOfMemoryError = localOutOfMemoryError;
              Log.e("ImageManager", "OOM while loading bitmap for uri: " + this.mUri, localOutOfMemoryError);
              bool = true;
              localObject = null;
            }
            localIOException = localIOException;
            Log.e("ImageManager", "closed failed", localIOException);
          }
          catch (InterruptedException localInterruptedException)
          {
            Log.w("ImageManager", "Latch interrupted while posting " + this.mUri);
          }
        }
      }
    }
  }

  private final class d
    implements Runnable
  {
    private final a AD;

    public d(a arg2)
    {
      Object localObject;
      this.AD = localObject;
    }

    public void run()
    {
      ed.ac("LoadImageRunnable must be executed on the main thread");
      ImageManager.a(ImageManager.this, this.AD);
      a.a locala = this.AD.AF;
      if (locala.uri == null)
      {
        this.AD.b(ImageManager.a(ImageManager.this), true);
        return;
      }
      Bitmap localBitmap = ImageManager.a(ImageManager.this, locala);
      if (localBitmap != null)
      {
        this.AD.a(ImageManager.a(ImageManager.this), localBitmap, true);
        return;
      }
      this.AD.x(ImageManager.a(ImageManager.this));
      ImageManager.ImageReceiver localImageReceiver = (ImageManager.ImageReceiver)ImageManager.b(ImageManager.this).get(locala.uri);
      if (localImageReceiver == null)
      {
        localImageReceiver = new ImageManager.ImageReceiver(ImageManager.this, locala.uri);
        ImageManager.b(ImageManager.this).put(locala.uri, localImageReceiver);
      }
      localImageReceiver.c(this.AD);
      if (this.AD.AI != 1)
        ImageManager.c(ImageManager.this).put(this.AD, localImageReceiver);
      synchronized (ImageManager.dd())
      {
        if (!ImageManager.dM().contains(locala.uri))
        {
          ImageManager.dM().add(locala.uri);
          localImageReceiver.dN();
        }
        return;
      }
    }
  }

  private static final class e
    implements ComponentCallbacks2
  {
    private final ImageManager.b Aw;

    public e(ImageManager.b paramb)
    {
      this.Aw = paramb;
    }

    public void onConfigurationChanged(Configuration paramConfiguration)
    {
    }

    public void onLowMemory()
    {
      this.Aw.evictAll();
    }

    public void onTrimMemory(int paramInt)
    {
      if (paramInt >= 60)
        this.Aw.evictAll();
      do
        return;
      while (paramInt < 20);
      this.Aw.trimToSize(this.Aw.size() / 2);
    }
  }

  private final class f
    implements Runnable
  {
    private boolean AE;
    private final Bitmap mBitmap;
    private final Uri mUri;
    private final CountDownLatch zf;

    public f(Uri paramBitmap, Bitmap paramBoolean, boolean paramCountDownLatch, CountDownLatch arg5)
    {
      this.mUri = paramBitmap;
      this.mBitmap = paramBoolean;
      this.AE = paramCountDownLatch;
      Object localObject;
      this.zf = localObject;
    }

    private void a(ImageManager.ImageReceiver paramImageReceiver, boolean paramBoolean)
    {
      paramImageReceiver.AA = true;
      ArrayList localArrayList = ImageManager.ImageReceiver.a(paramImageReceiver);
      int i = localArrayList.size();
      int j = 0;
      if (j < i)
      {
        a locala = (a)localArrayList.get(j);
        if (paramBoolean)
          locala.a(ImageManager.a(ImageManager.this), this.mBitmap, false);
        while (true)
        {
          if (locala.AI != 1)
            ImageManager.c(ImageManager.this).remove(locala);
          j++;
          break;
          locala.b(ImageManager.a(ImageManager.this), false);
        }
      }
      paramImageReceiver.AA = false;
    }

    public void run()
    {
      ed.ac("OnBitmapLoadedRunnable must be executed in the main thread");
      boolean bool;
      if (this.mBitmap != null)
        bool = true;
      while (ImageManager.f(ImageManager.this) != null)
      {
        if (this.AE)
        {
          ImageManager.f(ImageManager.this).evictAll();
          System.gc();
          this.AE = false;
          ImageManager.e(ImageManager.this).post(this);
          return;
          bool = false;
          continue;
        }
        if (!bool)
          break;
        ImageManager.f(ImageManager.this).put(new a.a(this.mUri), this.mBitmap);
      }
      ImageManager.ImageReceiver localImageReceiver = (ImageManager.ImageReceiver)ImageManager.b(ImageManager.this).remove(this.mUri);
      if (localImageReceiver != null)
        a(localImageReceiver, bool);
      this.zf.countDown();
      synchronized (ImageManager.dd())
      {
        ImageManager.dM().remove(this.mUri);
        return;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.images.ImageManager
 * JD-Core Version:    0.6.0
 */