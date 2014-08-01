package io.vov.vitamio;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import io.vov.vitamio.utils.FileUtils;
import io.vov.vitamio.utils.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class MediaPlayer
{
  public static final int CACHE_INFO_NO_SPACE = 1;
  public static final int CACHE_INFO_STREAM_NOT_SUPPORT = 2;
  public static final int CACHE_TYPE_NOT_AVAILABLE = 1;
  public static final int CACHE_TYPE_SPEED = 3;
  public static final int CACHE_TYPE_UPDATE = 2;
  private static final int MEDIA_BUFFERING_UPDATE = 3;
  private static final int MEDIA_CACHE = 300;
  private static final String MEDIA_CACHING_INFO = "caching_info";
  private static final String MEDIA_CACHING_SEGMENTS = "caching_segment";
  private static final String MEDIA_CACHING_TYPE = "caching_type";
  private static final int MEDIA_CACHING_UPDATE = 2000;
  private static final int MEDIA_ERROR = 100;
  public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;
  public static final int MEDIA_ERROR_UNKNOWN = 1;
  private static final int MEDIA_HW_ERROR = 400;
  private static final int MEDIA_INFO = 200;
  public static final int MEDIA_INFO_BUFFERING_END = 702;
  public static final int MEDIA_INFO_BUFFERING_START = 701;
  public static final int MEDIA_INFO_DOWNLOAD_RATE_CHANGED = 901;
  public static final int MEDIA_INFO_NOT_SEEKABLE = 801;
  public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
  private static final int MEDIA_NOP = 0;
  private static final int MEDIA_PLAYBACK_COMPLETE = 2;
  private static final int MEDIA_PREPARED = 1;
  private static final int MEDIA_SEEK_COMPLETE = 4;
  private static final int MEDIA_SET_VIDEO_SIZE = 5;
  private static final String MEDIA_SUBTITLE_BYTES = "sub_bytes";
  private static final String MEDIA_SUBTITLE_STRING = "sub_string";
  private static final String MEDIA_SUBTITLE_TYPE = "sub_type";
  private static final int MEDIA_TIMED_TEXT = 1000;
  private static AtomicBoolean NATIVE_OMX_LOADED;
  private static final int SUBTITLE_BITMAP = 1;
  public static final int SUBTITLE_EXTERNAL = 1;
  public static final int SUBTITLE_INTERNAL = 0;
  private static final int SUBTITLE_TEXT = 0;
  public static final String[] SUB_TYPES = { ".srt", ".ssa", ".smi", ".txt", ".sub", ".ass" };
  public static final int VIDEOCHROMA_RGB565 = 0;
  public static final int VIDEOCHROMA_RGBA = 1;
  public static final int VIDEOQUALITY_HIGH = 16;
  public static final int VIDEOQUALITY_LOW = -16;
  public static final int VIDEOQUALITY_MEDIUM;
  private AudioTrack mAudioTrack;
  private int mAudioTrackBufferSize;
  private Bitmap mBitmap;
  private ByteBuffer mByteBuffer;
  private Context mContext;
  private EventHandler mEventHandler;
  private AssetFileDescriptor mFD = null;
  private Surface mLocalSurface;
  private Metadata mMeta;
  private OnBufferingUpdateListener mOnBufferingUpdateListener;
  private OnCachingUpdateListener mOnCachingUpdateListener;
  private OnCompletionListener mOnCompletionListener;
  private OnErrorListener mOnErrorListener;
  private OnHWRenderFailedListener mOnHWRenderFailedListener;
  private OnInfoListener mOnInfoListener;
  private OnPreparedListener mOnPreparedListener;
  private OnSeekCompleteListener mOnSeekCompleteListener;
  private OnTimedTextListener mOnTimedTextListener;
  private OnVideoSizeChangedListener mOnVideoSizeChangedListener;
  private boolean mScreenOnWhilePlaying;
  private boolean mStayAwake;
  private Surface mSurface;
  private SurfaceHolder mSurfaceHolder;
  private PowerManager.WakeLock mWakeLock = null;

  static
  {
    NATIVE_OMX_LOADED = new AtomicBoolean(false);
    String str = Vitamio.getLibraryPath();
    try
    {
      Log.i("LIB ROOT: %s", new Object[] { str });
      System.load(str + "libstlport_shared.so");
      System.load(str + "libvplayer.so");
      loadFFmpeg_native(str + "libffmpeg.so");
      boolean bool2;
      if (Build.VERSION.SDK_INT > 8)
        bool2 = loadVVO_native(str + "libvvo.9.so");
      while (true)
      {
        if (!bool2)
        {
          boolean bool3 = loadVVO_native(str + "libvvo.j.so");
          Log.d("FALLBACK TO VVO JNI " + bool3, new Object[0]);
        }
        loadVAO_native(str + "libvao.0.so");
        return;
        if (Build.VERSION.SDK_INT > 7)
        {
          bool2 = loadVVO_native(str + "libvvo.8.so");
          continue;
        }
        boolean bool1 = loadVVO_native(str + "libvvo.7.so");
        bool2 = bool1;
      }
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      Log.e("Error loading libs", localUnsatisfiedLinkError);
    }
  }

  public MediaPlayer(Context paramContext)
  {
    this(paramContext, false);
  }

  public MediaPlayer(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext;
    String str = Vitamio.getLibraryPath();
    if (paramBoolean)
      if (!NATIVE_OMX_LOADED.get())
      {
        if (Build.VERSION.SDK_INT > 17)
        {
          loadOMX_native(str + "libOMX.18.so");
          NATIVE_OMX_LOADED.set(true);
        }
      }
      else
      {
        label74: Looper localLooper1 = Looper.myLooper();
        if (localLooper1 == null)
          break label214;
        this.mEventHandler = new EventHandler(this, localLooper1);
      }
    while (true)
    {
      native_init();
      return;
      if (Build.VERSION.SDK_INT > 13)
      {
        loadOMX_native(str + "libOMX.14.so");
        break;
      }
      if (Build.VERSION.SDK_INT > 10)
      {
        loadOMX_native(str + "libOMX.11.so");
        break;
      }
      loadOMX_native(str + "libOMX.9.so");
      break;
      unloadOMX_native();
      NATIVE_OMX_LOADED.set(false);
      break label74;
      label214: Looper localLooper2 = Looper.getMainLooper();
      if (localLooper2 != null)
      {
        this.mEventHandler = new EventHandler(this, localLooper2);
        continue;
      }
      this.mEventHandler = null;
    }
  }

  private native void _pause()
    throws IllegalStateException;

  private native void _release();

  private native void _reset();

  private native void _setDataSegmentsSource(String[] paramArrayOfString, String paramString);

  private native void _setDataSource(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws IOException, IllegalArgumentException, IllegalStateException;

  private native void _setVideoSurface();

  private native void _start()
    throws IllegalStateException;

  private native void _stop()
    throws IllegalStateException;

  private int audioTrackInit(int paramInt1, int paramInt2)
  {
    audioTrackRelease();
    int i;
    if (paramInt2 >= 2)
      i = 12;
    try
    {
      while (true)
      {
        this.mAudioTrackBufferSize = AudioTrack.getMinBufferSize(paramInt1, i, 2);
        this.mAudioTrack = new AudioTrack(3, paramInt1, i, 2, this.mAudioTrackBufferSize, 1);
        return this.mAudioTrackBufferSize;
        i = 4;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        this.mAudioTrackBufferSize = 0;
        Log.e("audioTrackInit", localException);
      }
    }
  }

  private void audioTrackPause()
  {
    if ((this.mAudioTrack != null) && (this.mAudioTrack.getState() == 1))
      this.mAudioTrack.pause();
  }

  private void audioTrackRelease()
  {
    if (this.mAudioTrack != null)
    {
      if (this.mAudioTrack.getState() == 1)
        this.mAudioTrack.stop();
      this.mAudioTrack.release();
    }
    this.mAudioTrack = null;
  }

  private void audioTrackSetVolume(float paramFloat1, float paramFloat2)
  {
    if (this.mAudioTrack != null)
      this.mAudioTrack.setStereoVolume(paramFloat1, paramFloat2);
  }

  private void audioTrackStart()
  {
    if ((this.mAudioTrack != null) && (this.mAudioTrack.getState() == 1) && (this.mAudioTrack.getPlayState() != 3))
      this.mAudioTrack.play();
  }

  private void audioTrackWrite(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.mAudioTrack != null)
    {
      audioTrackStart();
      if (paramInt2 > 0)
      {
        if (paramInt2 > this.mAudioTrackBufferSize);
        for (int i = this.mAudioTrackBufferSize; ; i = paramInt2)
        {
          this.mAudioTrack.write(paramArrayOfByte, paramInt1, i);
          paramInt2 -= i;
          paramInt1 += i;
          break;
        }
      }
    }
  }

  private void closeFD()
  {
    if (this.mFD != null);
    try
    {
      this.mFD.close();
      this.mFD = null;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.e("closeFD", localIOException);
    }
  }

  private native int getVideoHeight_a();

  private native int getVideoWidth_a();

  private static native boolean loadFFmpeg_native(String paramString);

  private static native boolean loadOMX_native(String paramString);

  private static native boolean loadVAO_native(String paramString);

  private static native boolean loadVVO_native(String paramString);

  private final native void native_finalize();

  private final native boolean native_getMetadata(Map<byte[], byte[]> paramMap);

  private final native boolean native_getTrackInfo(SparseArray<byte[]> paramSparseArray);

  private final native void native_init();

  // ERROR //
  private SparseArray<String> parseTrackInfo(byte[] paramArrayOfByte, String paramString)
  {
    // Byte code:
    //   0: new 417	android/util/SparseArray
    //   3: dup
    //   4: invokespecial 418	android/util/SparseArray:<init>	()V
    //   7: astore_3
    //   8: new 131	java/lang/String
    //   11: dup
    //   12: aload_1
    //   13: aload_2
    //   14: invokespecial 421	java/lang/String:<init>	([BLjava/lang/String;)V
    //   17: astore 4
    //   19: aload 4
    //   21: ldc_w 423
    //   24: invokevirtual 427	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   27: astore 5
    //   29: aload 5
    //   31: arraylength
    //   32: istore 6
    //   34: iconst_0
    //   35: istore 7
    //   37: iload 7
    //   39: iload 6
    //   41: if_icmpge +89 -> 130
    //   44: aload 5
    //   46: iload 7
    //   48: aaload
    //   49: astore 8
    //   51: aload 8
    //   53: ldc_w 429
    //   56: invokevirtual 433	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   59: ifeq +57 -> 116
    //   62: aload 8
    //   64: ldc_w 435
    //   67: invokevirtual 427	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   70: iconst_0
    //   71: aaload
    //   72: invokestatic 441	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   75: istore 11
    //   77: aload_3
    //   78: iload 11
    //   80: aload 8
    //   82: invokevirtual 445	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   85: iinc 7 1
    //   88: goto -51 -> 37
    //   91: astore 12
    //   93: ldc_w 447
    //   96: iconst_0
    //   97: anewarray 4	java/lang/Object
    //   100: invokestatic 449	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   103: new 131	java/lang/String
    //   106: dup
    //   107: aload_1
    //   108: invokespecial 452	java/lang/String:<init>	([B)V
    //   111: astore 4
    //   113: goto -94 -> 19
    //   116: aload 8
    //   118: invokestatic 441	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   121: istore 10
    //   123: iload 10
    //   125: istore 11
    //   127: goto -50 -> 77
    //   130: aload_3
    //   131: areturn
    //   132: astore 9
    //   134: goto -49 -> 85
    //
    // Exception table:
    //   from	to	target	type
    //   8	19	91	java/lang/Exception
    //   51	77	132	java/lang/NumberFormatException
    //   77	85	132	java/lang/NumberFormatException
    //   116	123	132	java/lang/NumberFormatException
  }

  private static void postEventFromNative(Object paramObject1, int paramInt1, int paramInt2, int paramInt3, Object paramObject2)
  {
    MediaPlayer localMediaPlayer = (MediaPlayer)(MediaPlayer)paramObject1;
    if (localMediaPlayer == null);
    do
      return;
    while (localMediaPlayer.mEventHandler == null);
    Message localMessage = localMediaPlayer.mEventHandler.obtainMessage(paramInt1, paramInt2, paramInt3, paramObject2);
    localMediaPlayer.mEventHandler.sendMessage(localMessage);
  }

  private native void selectOrDeselectTrack(int paramInt, boolean paramBoolean);

  @SuppressLint({"Wakelock"})
  private void stayAwake(boolean paramBoolean)
  {
    if (this.mWakeLock != null)
    {
      if ((!paramBoolean) || (this.mWakeLock.isHeld()))
        break label38;
      this.mWakeLock.acquire();
    }
    while (true)
    {
      this.mStayAwake = paramBoolean;
      updateSurfaceScreenOn();
      return;
      label38: if ((paramBoolean) || (!this.mWakeLock.isHeld()))
        continue;
      this.mWakeLock.release();
    }
  }

  private ByteBuffer surfaceInit()
  {
    monitorenter;
    try
    {
      this.mLocalSurface = this.mSurface;
      int i = getVideoWidth_a();
      int j = getVideoHeight_a();
      if ((this.mLocalSurface != null) && (i != 0) && (j != 0))
        this.mBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
      for (this.mByteBuffer = ByteBuffer.allocateDirect(2 * (i * j)); ; this.mByteBuffer = null)
      {
        ByteBuffer localByteBuffer = this.mByteBuffer;
        return localByteBuffer;
        this.mBitmap = null;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void surfaceRelease()
  {
    monitorenter;
    try
    {
      this.mLocalSurface = null;
      this.mBitmap = null;
      this.mByteBuffer = null;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  private void surfaceRender()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 487	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   6: ifnull +27 -> 33
    //   9: aload_0
    //   10: getfield 487	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   13: invokevirtual 520	android/view/Surface:isValid	()Z
    //   16: ifeq +17 -> 33
    //   19: aload_0
    //   20: getfield 505	io/vov/vitamio/MediaPlayer:mBitmap	Landroid/graphics/Bitmap;
    //   23: ifnull +10 -> 33
    //   26: aload_0
    //   27: getfield 513	io/vov/vitamio/MediaPlayer:mByteBuffer	Ljava/nio/ByteBuffer;
    //   30: ifnonnull +6 -> 36
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: aload_0
    //   37: getfield 487	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   40: aconst_null
    //   41: invokevirtual 524	android/view/Surface:lockCanvas	(Landroid/graphics/Rect;)Landroid/graphics/Canvas;
    //   44: astore_3
    //   45: aload_0
    //   46: getfield 505	io/vov/vitamio/MediaPlayer:mBitmap	Landroid/graphics/Bitmap;
    //   49: aload_0
    //   50: getfield 513	io/vov/vitamio/MediaPlayer:mByteBuffer	Ljava/nio/ByteBuffer;
    //   53: invokevirtual 528	android/graphics/Bitmap:copyPixelsFromBuffer	(Ljava/nio/Buffer;)V
    //   56: aload_3
    //   57: aload_0
    //   58: getfield 505	io/vov/vitamio/MediaPlayer:mBitmap	Landroid/graphics/Bitmap;
    //   61: fconst_0
    //   62: fconst_0
    //   63: aconst_null
    //   64: invokevirtual 534	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V
    //   67: aload_0
    //   68: getfield 487	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   71: aload_3
    //   72: invokevirtual 538	android/view/Surface:unlockCanvasAndPost	(Landroid/graphics/Canvas;)V
    //   75: aload_0
    //   76: monitorexit
    //   77: return
    //   78: astore_1
    //   79: aload_0
    //   80: monitorexit
    //   81: aload_1
    //   82: athrow
    //   83: astore_2
    //   84: ldc_w 539
    //   87: aload_2
    //   88: invokestatic 229	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   91: goto -16 -> 75
    //
    // Exception table:
    //   from	to	target	type
    //   2	33	78	finally
    //   33	35	78	finally
    //   36	75	78	finally
    //   75	77	78	finally
    //   79	81	78	finally
    //   84	91	78	finally
    //   36	75	83	java/lang/Exception
  }

  private static native void unloadOMX_native();

  private void updateCacheStatus(int paramInt1, int paramInt2, long[] paramArrayOfLong)
  {
    if (this.mEventHandler != null)
    {
      Message localMessage = this.mEventHandler.obtainMessage(2000);
      Bundle localBundle = localMessage.getData();
      localBundle.putInt("caching_type", paramInt1);
      localBundle.putInt("caching_info", paramInt2);
      localBundle.putLongArray("caching_segment", paramArrayOfLong);
      this.mEventHandler.sendMessage(localMessage);
    }
  }

  private void updateSub(int paramInt1, byte[] paramArrayOfByte, String paramString, int paramInt2, int paramInt3)
  {
    Message localMessage;
    Bundle localBundle;
    if (this.mEventHandler != null)
    {
      localMessage = this.mEventHandler.obtainMessage(1000, paramInt2, paramInt3);
      localBundle = localMessage.getData();
      if (paramInt1 != 0)
        break label122;
      localBundle.putInt("sub_type", 0);
      if (paramString != null)
        break label72;
      localBundle.putString("sub_string", new String(paramArrayOfByte));
    }
    while (true)
    {
      this.mEventHandler.sendMessage(localMessage);
      return;
      try
      {
        label72: localBundle.putString("sub_string", new String(paramArrayOfByte, paramString.trim()));
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        Log.e("updateSub", localUnsupportedEncodingException);
        localBundle.putString("sub_string", new String(paramArrayOfByte));
      }
      continue;
      label122: if (paramInt1 != 1)
        continue;
      localBundle.putInt("sub_type", 1);
      localBundle.putByteArray("sub_bytes", paramArrayOfByte);
    }
  }

  private void updateSurfaceScreenOn()
  {
    SurfaceHolder localSurfaceHolder;
    if (this.mSurfaceHolder != null)
    {
      localSurfaceHolder = this.mSurfaceHolder;
      if ((!this.mScreenOnWhilePlaying) || (!this.mStayAwake))
        break label36;
    }
    label36: for (boolean bool = true; ; bool = false)
    {
      localSurfaceHolder.setKeepScreenOn(bool);
      return;
    }
  }

  protected native void _releaseVideoSurface();

  public native void addTimedTextSource(String paramString);

  public void deselectTrack(int paramInt)
  {
    selectOrDeselectTrack(paramInt, false);
  }

  protected void finalize()
  {
    native_finalize();
  }

  public SparseArray<String> findTrackFromTrackInfo(int paramInt, TrackInfo[] paramArrayOfTrackInfo)
  {
    for (int i = 0; i < paramArrayOfTrackInfo.length; i++)
      if (paramArrayOfTrackInfo[i].getTrackType() == paramInt)
        return paramArrayOfTrackInfo[i].getTrackInfoArray();
    return null;
  }

  public native int getAudioTrack();

  public native int getBufferProgress();

  public native Bitmap getCurrentFrame();

  public native long getCurrentPosition();

  public native long getDuration();

  public native String getMetaEncoding();

  public Metadata getMetadata()
  {
    if (this.mMeta == null)
    {
      this.mMeta = new Metadata();
      HashMap localHashMap = new HashMap();
      if (!native_getMetadata(localHashMap));
      do
        return null;
      while (!this.mMeta.parse(localHashMap, getMetaEncoding()));
    }
    return this.mMeta;
  }

  public native int getTimedTextLocation();

  public native String getTimedTextPath();

  public native int getTimedTextTrack();

  public TrackInfo[] getTrackInfo()
  {
    return getTrackInfo(Charset.defaultCharset().name());
  }

  public TrackInfo[] getTrackInfo(String paramString)
  {
    SparseArray localSparseArray = new SparseArray();
    TrackInfo[] arrayOfTrackInfo;
    if (!native_getTrackInfo(localSparseArray))
      arrayOfTrackInfo = null;
    while (true)
    {
      return arrayOfTrackInfo;
      int i = localSparseArray.size();
      arrayOfTrackInfo = new TrackInfo[i];
      for (int j = 0; j < i; j++)
        arrayOfTrackInfo[j] = new TrackInfo(localSparseArray.keyAt(j), parseTrackInfo((byte[])localSparseArray.valueAt(j), paramString));
    }
  }

  public native float getVideoAspectRatio();

  public native int getVideoHeight();

  public native int getVideoTrack();

  public native int getVideoWidth();

  public native boolean isBuffering();

  public native boolean isPlaying();

  public void pause()
    throws IllegalStateException
  {
    stayAwake(false);
    _pause();
  }

  public native void prepare()
    throws IOException, IllegalStateException;

  public native void prepareAsync()
    throws IllegalStateException;

  public void release()
  {
    stayAwake(false);
    updateSurfaceScreenOn();
    this.mOnPreparedListener = null;
    this.mOnBufferingUpdateListener = null;
    this.mOnCompletionListener = null;
    this.mOnSeekCompleteListener = null;
    this.mOnErrorListener = null;
    this.mOnInfoListener = null;
    this.mOnVideoSizeChangedListener = null;
    this.mOnCachingUpdateListener = null;
    this.mOnHWRenderFailedListener = null;
    _release();
    closeFD();
  }

  public void releaseDisplay()
  {
    _releaseVideoSurface();
    this.mSurfaceHolder = null;
    this.mSurface = null;
  }

  public void reset()
  {
    stayAwake(false);
    _reset();
    this.mEventHandler.removeCallbacksAndMessages(null);
    closeFD();
  }

  public native void seekTo(long paramLong)
    throws IllegalStateException;

  public void selectTrack(int paramInt)
  {
    selectOrDeselectTrack(paramInt, true);
  }

  public native void setAdaptiveStream(boolean paramBoolean);

  public native void setAudioAmplify(float paramFloat);

  public native void setBufferSize(long paramLong);

  public void setDataSegments(String[] paramArrayOfString, String paramString)
  {
    _setDataSegmentsSource(paramArrayOfString, paramString);
  }

  public void setDataSource(Context paramContext, Uri paramUri)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    setDataSource(paramContext, paramUri, null);
  }

  public void setDataSource(Context paramContext, Uri paramUri, Map<String, String> paramMap)
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
        setDataSource(paramUri.toString(), paramMap);
      }
    }
  }

  public native void setDataSource(FileDescriptor paramFileDescriptor)
    throws IOException, IllegalArgumentException, IllegalStateException;

  public void setDataSource(String paramString)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    _setDataSource(paramString, null, null);
  }

  public void setDataSource(String paramString, Map<String, String> paramMap)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    String[] arrayOfString1 = null;
    String[] arrayOfString2 = null;
    if (paramMap != null)
    {
      arrayOfString1 = new String[paramMap.size()];
      arrayOfString2 = new String[paramMap.size()];
      int i = 0;
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        arrayOfString1[i] = ((String)localEntry.getKey());
        arrayOfString2[i] = ((String)localEntry.getValue());
        i++;
      }
    }
    setDataSource(paramString, arrayOfString1, arrayOfString2);
  }

  public void setDataSource(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    Uri localUri = Uri.parse(paramString);
    if ("file".equals(localUri.getScheme()))
      paramString = localUri.getPath();
    File localFile = new File(paramString);
    if (localFile.exists())
    {
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      setDataSource(localFileInputStream.getFD());
      localFileInputStream.close();
      return;
    }
    _setDataSource(paramString, paramArrayOfString1, paramArrayOfString2);
  }

  public native void setDeinterlace(boolean paramBoolean);

  public void setDisplay(SurfaceHolder paramSurfaceHolder)
  {
    if (paramSurfaceHolder == null)
    {
      releaseDisplay();
      return;
    }
    this.mSurfaceHolder = paramSurfaceHolder;
    this.mSurface = paramSurfaceHolder.getSurface();
    _setVideoSurface();
    updateSurfaceScreenOn();
  }

  public native void setMetaEncoding(String paramString);

  public void setOnBufferingUpdateListener(OnBufferingUpdateListener paramOnBufferingUpdateListener)
  {
    this.mOnBufferingUpdateListener = paramOnBufferingUpdateListener;
  }

  public void setOnCachingUpdateListener(OnCachingUpdateListener paramOnCachingUpdateListener)
  {
    this.mOnCachingUpdateListener = paramOnCachingUpdateListener;
  }

  public void setOnCompletionListener(OnCompletionListener paramOnCompletionListener)
  {
    this.mOnCompletionListener = paramOnCompletionListener;
  }

  public void setOnErrorListener(OnErrorListener paramOnErrorListener)
  {
    this.mOnErrorListener = paramOnErrorListener;
  }

  public void setOnHWRenderFailedListener(OnHWRenderFailedListener paramOnHWRenderFailedListener)
  {
    this.mOnHWRenderFailedListener = paramOnHWRenderFailedListener;
  }

  public void setOnInfoListener(OnInfoListener paramOnInfoListener)
  {
    this.mOnInfoListener = paramOnInfoListener;
  }

  public void setOnPreparedListener(OnPreparedListener paramOnPreparedListener)
  {
    this.mOnPreparedListener = paramOnPreparedListener;
  }

  public void setOnSeekCompleteListener(OnSeekCompleteListener paramOnSeekCompleteListener)
  {
    this.mOnSeekCompleteListener = paramOnSeekCompleteListener;
  }

  public void setOnTimedTextListener(OnTimedTextListener paramOnTimedTextListener)
  {
    this.mOnTimedTextListener = paramOnTimedTextListener;
  }

  public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener paramOnVideoSizeChangedListener)
  {
    this.mOnVideoSizeChangedListener = paramOnVideoSizeChangedListener;
  }

  public native void setPlaybackSpeed(float paramFloat);

  public void setScreenOnWhilePlaying(boolean paramBoolean)
  {
    if (this.mScreenOnWhilePlaying != paramBoolean)
    {
      this.mScreenOnWhilePlaying = paramBoolean;
      updateSurfaceScreenOn();
    }
  }

  public void setSurface(Surface paramSurface)
  {
    if (paramSurface == null)
    {
      releaseDisplay();
      return;
    }
    this.mSurfaceHolder = null;
    this.mSurface = paramSurface;
    _setVideoSurface();
    updateSurfaceScreenOn();
  }

  public native void setTimedTextEncoding(String paramString);

  public native void setTimedTextShown(boolean paramBoolean);

  public native void setVideoChroma(int paramInt);

  public native void setVideoQuality(int paramInt);

  public native void setVolume(float paramFloat1, float paramFloat2);

  @SuppressLint({"Wakelock"})
  public void setWakeMode(Context paramContext, int paramInt)
  {
    PowerManager.WakeLock localWakeLock = this.mWakeLock;
    int i = 0;
    if (localWakeLock != null)
    {
      boolean bool = this.mWakeLock.isHeld();
      i = 0;
      if (bool)
      {
        i = 1;
        this.mWakeLock.release();
      }
      this.mWakeLock = null;
    }
    this.mWakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(0x20000000 | paramInt, MediaPlayer.class.getName());
    this.mWakeLock.setReferenceCounted(false);
    if (i != 0)
      this.mWakeLock.acquire();
  }

  public void start()
    throws IllegalStateException
  {
    stayAwake(true);
    _start();
  }

  public void stop()
    throws IllegalStateException
  {
    stayAwake(false);
    _stop();
  }

  @SuppressLint({"HandlerLeak"})
  private class EventHandler extends Handler
  {
    private Bundle mData;
    private MediaPlayer mMediaPlayer;

    public EventHandler(MediaPlayer paramLooper, Looper arg3)
    {
      super();
      this.mMediaPlayer = paramLooper;
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        Log.e("Unknown message type " + paramMessage.what, new Object[0]);
      case 0:
      case 300:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 100:
      case 200:
      case 1000:
      case 2000:
      case 400:
      }
      do
      {
        int i;
        do
        {
          do
          {
            do
            {
              do
                while (true)
                {
                  return;
                  if (MediaPlayer.this.mOnPreparedListener == null)
                    continue;
                  MediaPlayer.this.mOnPreparedListener.onPrepared(this.mMediaPlayer);
                  return;
                  if (MediaPlayer.this.mOnCompletionListener != null)
                    MediaPlayer.this.mOnCompletionListener.onCompletion(this.mMediaPlayer);
                  MediaPlayer.this.stayAwake(false);
                  return;
                  if (MediaPlayer.this.mOnBufferingUpdateListener == null)
                    continue;
                  MediaPlayer.this.mOnBufferingUpdateListener.onBufferingUpdate(this.mMediaPlayer, paramMessage.arg1);
                  return;
                  if (MediaPlayer.this.isPlaying())
                    MediaPlayer.this.stayAwake(true);
                  if (MediaPlayer.this.mOnSeekCompleteListener == null)
                    continue;
                  MediaPlayer.this.mOnSeekCompleteListener.onSeekComplete(this.mMediaPlayer);
                  return;
                  if (MediaPlayer.this.mOnVideoSizeChangedListener == null)
                    continue;
                  MediaPlayer.this.mOnVideoSizeChangedListener.onVideoSizeChanged(this.mMediaPlayer, paramMessage.arg1, paramMessage.arg2);
                  return;
                  Object[] arrayOfObject3 = new Object[2];
                  arrayOfObject3[0] = Integer.valueOf(paramMessage.arg1);
                  arrayOfObject3[1] = Integer.valueOf(paramMessage.arg2);
                  Log.e("Error (%d, %d)", arrayOfObject3);
                  MediaPlayer.OnErrorListener localOnErrorListener = MediaPlayer.this.mOnErrorListener;
                  boolean bool = false;
                  if (localOnErrorListener != null)
                    bool = MediaPlayer.this.mOnErrorListener.onError(this.mMediaPlayer, paramMessage.arg1, paramMessage.arg2);
                  if ((MediaPlayer.this.mOnCompletionListener != null) && (!bool))
                    MediaPlayer.this.mOnCompletionListener.onCompletion(this.mMediaPlayer);
                  MediaPlayer.this.stayAwake(false);
                  return;
                  Object[] arrayOfObject2 = new Object[2];
                  arrayOfObject2[0] = Integer.valueOf(paramMessage.arg1);
                  arrayOfObject2[1] = Integer.valueOf(paramMessage.arg2);
                  Log.i("Info (%d, %d)", arrayOfObject2);
                  if (MediaPlayer.this.mOnInfoListener == null)
                    continue;
                  MediaPlayer.this.mOnInfoListener.onInfo(this.mMediaPlayer, paramMessage.arg1, paramMessage.arg2);
                  return;
                  this.mData = paramMessage.getData();
                  if (this.mData.getInt("sub_type") != 0)
                    break;
                  Object[] arrayOfObject1 = new Object[1];
                  arrayOfObject1[0] = this.mData.getString("sub_string");
                  Log.i("Subtitle : %s", arrayOfObject1);
                  if (MediaPlayer.this.mOnTimedTextListener == null)
                    continue;
                  MediaPlayer.this.mOnTimedTextListener.onTimedText(this.mData.getString("sub_string"));
                  return;
                }
              while (this.mData.getInt("sub_type") != 1);
              Log.i("Subtitle : bitmap", new Object[0]);
            }
            while (MediaPlayer.this.mOnTimedTextListener == null);
            MediaPlayer.this.mOnTimedTextListener.onTimedTextUpdate(this.mData.getByteArray("sub_bytes"), paramMessage.arg1, paramMessage.arg2);
            return;
          }
          while (MediaPlayer.this.mOnCachingUpdateListener == null);
          i = paramMessage.getData().getInt("caching_type");
          if (i == 1)
          {
            MediaPlayer.this.mOnCachingUpdateListener.onCachingNotAvailable(this.mMediaPlayer, paramMessage.getData().getInt("caching_info"));
            return;
          }
          if (i != 2)
            continue;
          MediaPlayer.this.mOnCachingUpdateListener.onCachingUpdate(this.mMediaPlayer, paramMessage.getData().getLongArray("caching_segment"));
          return;
        }
        while (i != 3);
        MediaPlayer.this.mOnCachingUpdateListener.onCachingSpeed(this.mMediaPlayer, paramMessage.getData().getInt("caching_info"));
        return;
      }
      while (MediaPlayer.this.mOnHWRenderFailedListener == null);
      MediaPlayer.this.mOnHWRenderFailedListener.onFailed();
    }
  }

  public static abstract interface OnBufferingUpdateListener
  {
    public abstract void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt);
  }

  public static abstract interface OnCachingUpdateListener
  {
    public abstract void onCachingNotAvailable(MediaPlayer paramMediaPlayer, int paramInt);

    public abstract void onCachingSpeed(MediaPlayer paramMediaPlayer, int paramInt);

    public abstract void onCachingUpdate(MediaPlayer paramMediaPlayer, long[] paramArrayOfLong);
  }

  public static abstract interface OnCompletionListener
  {
    public abstract void onCompletion(MediaPlayer paramMediaPlayer);
  }

  public static abstract interface OnErrorListener
  {
    public abstract boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2);
  }

  public static abstract interface OnHWRenderFailedListener
  {
    public abstract void onFailed();
  }

  public static abstract interface OnInfoListener
  {
    public abstract boolean onInfo(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2);
  }

  public static abstract interface OnPreparedListener
  {
    public abstract void onPrepared(MediaPlayer paramMediaPlayer);
  }

  public static abstract interface OnSeekCompleteListener
  {
    public abstract void onSeekComplete(MediaPlayer paramMediaPlayer);
  }

  public static abstract interface OnTimedTextListener
  {
    public abstract void onTimedText(String paramString);

    public abstract void onTimedTextUpdate(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  }

  public static abstract interface OnVideoSizeChangedListener
  {
    public abstract void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2);
  }

  public static class TrackInfo
  {
    public static final int MEDIA_TRACK_TYPE_AUDIO = 2;
    public static final int MEDIA_TRACK_TYPE_TIMEDTEXT = 3;
    public static final int MEDIA_TRACK_TYPE_UNKNOWN = 0;
    public static final int MEDIA_TRACK_TYPE_VIDEO = 1;
    final SparseArray<String> mTrackInfoArray;
    final int mTrackType;

    TrackInfo(int paramInt, SparseArray<String> paramSparseArray)
    {
      this.mTrackType = paramInt;
      this.mTrackInfoArray = paramSparseArray;
    }

    public SparseArray<String> getTrackInfoArray()
    {
      return this.mTrackInfoArray;
    }

    public int getTrackType()
    {
      return this.mTrackType;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.MediaPlayer
 * JD-Core Version:    0.6.0
 */