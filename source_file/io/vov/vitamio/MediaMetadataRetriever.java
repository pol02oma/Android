package io.vov.vitamio;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import io.vov.vitamio.utils.FileUtils;
import java.io.FileDescriptor;
import java.io.IOException;

public class MediaMetadataRetriever
{
  public static final String METADATA_KEY_ALBUM = "album";
  public static final String METADATA_KEY_ARTIST = "artist";
  public static final String METADATA_KEY_AUTHOR = "author";
  public static final String METADATA_KEY_COMPOSER = "composer";
  public static final String METADATA_KEY_DURATION = "duration";
  public static final String METADATA_KEY_GENRE = "genre";
  public static final String METADATA_KEY_TITLE = "title";
  public static final String METADATA_KEY_VIDEO_HEIGHT = "height";
  public static final String METADATA_KEY_VIDEO_WIDTH = "width";
  private Context mContext;
  private AssetFileDescriptor mFD = null;

  static
  {
    String str = Vitamio.getLibraryPath();
    Log.i("LIB ROOT: %s", str);
    System.load(str + "libstlport_shared.so");
    System.load(str + "libvscanner.so");
    loadFFmpeg_native(str + "libffmpeg.so");
  }

  public MediaMetadataRetriever(Context paramContext)
  {
    this.mContext = paramContext;
    native_init();
  }

  private native void _release();

  private void closeFD()
  {
    if (this.mFD != null);
    try
    {
      this.mFD.close();
      label14: this.mFD = null;
      return;
    }
    catch (IOException localIOException)
    {
      break label14;
    }
  }

  private static native boolean loadFFmpeg_native(String paramString);

  private final native void native_finalize();

  private final native void native_init();

  public native String extractMetadata(String paramString)
    throws IllegalStateException;

  protected void finalize()
    throws Throwable
  {
    try
    {
      native_finalize();
      return;
    }
    finally
    {
      super.finalize();
    }
    throw localObject;
  }

  public native Bitmap getFrameAtTime(long paramLong)
    throws IllegalStateException;

  public void release()
  {
    _release();
    closeFD();
  }

  public void setDataSource(Context paramContext, Uri paramUri)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    if ((paramContext == null) || (paramUri == null))
      throw new IllegalArgumentException();
    String str = paramUri.getScheme();
    if ((str == null) || (str.equals("file")))
      setDataSource(FileUtils.getPath(paramUri.toString()));
    while (true)
    {
      return;
      try
      {
        this.mFD = paramContext.getContentResolver().openAssetFileDescriptor(paramUri, "r");
        if (this.mFD == null)
          continue;
        setDataSource(this.mFD.getParcelFileDescriptor().getFileDescriptor());
        return;
      }
      catch (Exception localException)
      {
        closeFD();
        Log.e("Couldn't open file on client side, trying server side %s", paramUri.toString());
        setDataSource(paramUri.toString());
      }
    }
  }

  public native void setDataSource(FileDescriptor paramFileDescriptor)
    throws IOException, IllegalArgumentException, IllegalStateException;

  public native void setDataSource(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.MediaMetadataRetriever
 * JD-Core Version:    0.6.0
 */