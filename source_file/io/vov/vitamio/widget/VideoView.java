package io.vov.vitamio.widget;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.MediaPlayer.OnSeekCompleteListener;
import io.vov.vitamio.MediaPlayer.OnTimedTextListener;
import io.vov.vitamio.MediaPlayer.OnVideoSizeChangedListener;
import io.vov.vitamio.Metadata;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.utils.Log;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class VideoView extends SurfaceView
  implements MediaController.MediaPlayerControl
{
  private static final int STATE_ERROR = -1;
  private static final int STATE_IDLE = 0;
  private static final int STATE_PAUSED = 4;
  private static final int STATE_PLAYBACK_COMPLETED = 5;
  private static final int STATE_PLAYING = 3;
  private static final int STATE_PREPARED = 2;
  private static final int STATE_PREPARING = 1;
  private static final int STATE_RESUME = 7;
  private static final int STATE_SUSPEND = 6;
  private static final int STATE_SUSPEND_UNSUPPORTED = 8;
  public static final int VIDEO_LAYOUT_ORIGIN = 0;
  public static final int VIDEO_LAYOUT_SCALE = 1;
  public static final int VIDEO_LAYOUT_STRETCH = 2;
  public static final int VIDEO_LAYOUT_ZOOM = 3;
  private float mAspectRatio = 0.0F;
  private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener()
  {
    public void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt)
    {
      VideoView.access$2202(VideoView.this, paramInt);
      if (VideoView.this.mOnBufferingUpdateListener != null)
        VideoView.this.mOnBufferingUpdateListener.onBufferingUpdate(paramMediaPlayer, paramInt);
    }
  };
  private boolean mCanPause;
  private boolean mCanSeekBack;
  private boolean mCanSeekForward;
  private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramMediaPlayer)
    {
      Log.d("onCompletion", new Object[0]);
      VideoView.access$502(VideoView.this, 5);
      VideoView.access$602(VideoView.this, 5);
      if (VideoView.this.mMediaController != null)
        VideoView.this.mMediaController.hide();
      if (VideoView.this.mOnCompletionListener != null)
        VideoView.this.mOnCompletionListener.onCompletion(VideoView.this.mMediaPlayer);
    }
  };
  private Context mContext;
  private int mCurrentBufferPercentage;
  private int mCurrentState = 0;
  private long mDuration;
  private MediaPlayer.OnErrorListener mErrorListener = new MediaPlayer.OnErrorListener()
  {
    public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      Log.d("Error: %d, %d", arrayOfObject);
      VideoView.access$502(VideoView.this, -1);
      VideoView.access$602(VideoView.this, -1);
      if (VideoView.this.mMediaController != null)
        VideoView.this.mMediaController.hide();
      if ((VideoView.this.mOnErrorListener != null) && (VideoView.this.mOnErrorListener.onError(VideoView.this.mMediaPlayer, paramInt1, paramInt2)));
      do
        return true;
      while (VideoView.this.getWindowToken() == null);
      if (paramInt1 == 200);
      for (String str = "Sorry, this video is not valid for streaming to this device."; ; str = "Sorry, this video cannot be played.")
      {
        new AlertDialog.Builder(VideoView.this.mContext).setTitle("Cannot play video").setMessage(str).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            if (VideoView.this.mOnCompletionListener != null)
              VideoView.this.mOnCompletionListener.onCompletion(VideoView.this.mMediaPlayer);
          }
        }).setCancelable(false).show();
        return true;
      }
    }
  };
  private Map<String, String> mHeaders;
  private MediaPlayer.OnInfoListener mInfoListener = new MediaPlayer.OnInfoListener()
  {
    public boolean onInfo(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      Log.d("onInfo: (%d, %d)", arrayOfObject);
      if (VideoView.this.mOnInfoListener != null)
        VideoView.this.mOnInfoListener.onInfo(paramMediaPlayer, paramInt1, paramInt2);
      do
      {
        do
          while (true)
          {
            return true;
            if (VideoView.this.mMediaPlayer == null)
              continue;
            if (paramInt1 != 701)
              break;
            VideoView.this.mMediaPlayer.pause();
            if (VideoView.this.mMediaBufferingIndicator == null)
              continue;
            VideoView.this.mMediaBufferingIndicator.setVisibility(0);
            return true;
          }
        while (paramInt1 != 702);
        VideoView.this.mMediaPlayer.start();
      }
      while (VideoView.this.mMediaBufferingIndicator == null);
      VideoView.this.mMediaBufferingIndicator.setVisibility(8);
      return true;
    }
  };
  private View mMediaBufferingIndicator;
  private MediaController mMediaController;
  private MediaPlayer mMediaPlayer = null;
  private MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
  private MediaPlayer.OnCompletionListener mOnCompletionListener;
  private MediaPlayer.OnErrorListener mOnErrorListener;
  private MediaPlayer.OnInfoListener mOnInfoListener;
  private MediaPlayer.OnPreparedListener mOnPreparedListener;
  private MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener;
  private MediaPlayer.OnTimedTextListener mOnTimedTextListener;
  MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener()
  {
    public void onPrepared(MediaPlayer paramMediaPlayer)
    {
      Log.d("onPrepared", new Object[0]);
      VideoView.access$502(VideoView.this, 2);
      VideoView.access$602(VideoView.this, 3);
      Metadata localMetadata = paramMediaPlayer.getMetadata();
      boolean bool1;
      boolean bool2;
      label104: boolean bool3;
      label141: label149: long l;
      if (localMetadata != null)
      {
        VideoView localVideoView1 = VideoView.this;
        if ((!localMetadata.has(29)) || (localMetadata.getBoolean(29)))
        {
          bool1 = true;
          VideoView.access$702(localVideoView1, bool1);
          VideoView localVideoView2 = VideoView.this;
          if ((localMetadata.has(30)) && (!localMetadata.getBoolean(30)))
            break label380;
          bool2 = true;
          VideoView.access$802(localVideoView2, bool2);
          VideoView localVideoView3 = VideoView.this;
          if ((localMetadata.has(31)) && (!localMetadata.getBoolean(31)))
            break label386;
          bool3 = true;
          VideoView.access$902(localVideoView3, bool3);
          if (VideoView.this.mOnPreparedListener != null)
            VideoView.this.mOnPreparedListener.onPrepared(VideoView.this.mMediaPlayer);
          if (VideoView.this.mMediaController != null)
            VideoView.this.mMediaController.setEnabled(true);
          VideoView.access$002(VideoView.this, paramMediaPlayer.getVideoWidth());
          VideoView.access$102(VideoView.this, paramMediaPlayer.getVideoHeight());
          VideoView.access$202(VideoView.this, paramMediaPlayer.getVideoAspectRatio());
          l = VideoView.this.mSeekWhenPrepared;
          if (l != 0L)
            VideoView.this.seekTo(l);
          if ((VideoView.this.mVideoWidth == 0) || (VideoView.this.mVideoHeight == 0))
            break label469;
          VideoView.this.setVideoLayout(VideoView.this.mVideoLayout, VideoView.this.mAspectRatio);
          if ((VideoView.this.mSurfaceWidth == VideoView.this.mVideoWidth) && (VideoView.this.mSurfaceHeight == VideoView.this.mVideoHeight))
          {
            if (VideoView.this.mTargetState != 3)
              break label418;
            VideoView.this.start();
            if (VideoView.this.mMediaController != null)
              VideoView.this.mMediaController.show();
          }
        }
      }
      label380: 
      do
      {
        do
        {
          return;
          bool1 = false;
          break;
          bool2 = false;
          break label104;
          bool3 = false;
          break label141;
          VideoView.access$702(VideoView.this, VideoView.access$802(VideoView.this, VideoView.access$902(VideoView.this, true)));
          break label149;
        }
        while ((VideoView.this.isPlaying()) || ((l == 0L) && (VideoView.this.getCurrentPosition() <= 0L)) || (VideoView.this.mMediaController == null));
        VideoView.this.mMediaController.show(0);
        return;
      }
      while (VideoView.this.mTargetState != 3);
      label386: label418: label469: VideoView.this.start();
    }
  };
  SurfaceHolder.Callback mSHCallback = new SurfaceHolder.Callback()
  {
    public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
    {
      VideoView.access$1402(VideoView.this, paramInt2);
      VideoView.access$1502(VideoView.this, paramInt3);
      int i;
      if (VideoView.this.mTargetState == 3)
      {
        i = 1;
        if ((VideoView.this.mVideoWidth != paramInt2) || (VideoView.this.mVideoHeight != paramInt3))
          break label162;
      }
      label162: for (int j = 1; ; j = 0)
      {
        if ((VideoView.this.mMediaPlayer != null) && (i != 0) && (j != 0))
        {
          if (VideoView.this.mSeekWhenPrepared != 0L)
            VideoView.this.seekTo(VideoView.this.mSeekWhenPrepared);
          VideoView.this.start();
          if (VideoView.this.mMediaController != null)
          {
            if (VideoView.this.mMediaController.isShowing())
              VideoView.this.mMediaController.hide();
            VideoView.this.mMediaController.show();
          }
        }
        return;
        i = 0;
        break;
      }
    }

    public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
    {
      VideoView.access$1602(VideoView.this, paramSurfaceHolder);
      if ((VideoView.this.mMediaPlayer != null) && (VideoView.this.mCurrentState == 6) && (VideoView.this.mTargetState == 7))
      {
        VideoView.this.mMediaPlayer.setDisplay(VideoView.this.mSurfaceHolder);
        VideoView.this.resume();
        return;
      }
      VideoView.this.openVideo();
    }

    public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
    {
      VideoView.access$1602(VideoView.this, null);
      if (VideoView.this.mMediaController != null)
        VideoView.this.mMediaController.hide();
      if (VideoView.this.mCurrentState != 6)
        VideoView.this.release(true);
    }
  };
  private MediaPlayer.OnSeekCompleteListener mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener()
  {
    public void onSeekComplete(MediaPlayer paramMediaPlayer)
    {
      Log.d("onSeekComplete", new Object[0]);
      if (VideoView.this.mOnSeekCompleteListener != null)
        VideoView.this.mOnSeekCompleteListener.onSeekComplete(paramMediaPlayer);
    }
  };
  private long mSeekWhenPrepared;
  MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener()
  {
    public void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      Log.d("onVideoSizeChanged: (%dx%d)", arrayOfObject);
      VideoView.access$002(VideoView.this, paramMediaPlayer.getVideoWidth());
      VideoView.access$102(VideoView.this, paramMediaPlayer.getVideoHeight());
      VideoView.access$202(VideoView.this, paramMediaPlayer.getVideoAspectRatio());
      if ((VideoView.this.mVideoWidth != 0) && (VideoView.this.mVideoHeight != 0))
        VideoView.this.setVideoLayout(VideoView.this.mVideoLayout, VideoView.this.mAspectRatio);
    }
  };
  private int mSurfaceHeight;
  private SurfaceHolder mSurfaceHolder = null;
  private int mSurfaceWidth;
  private int mTargetState = 0;
  private MediaPlayer.OnTimedTextListener mTimedTextListener = new MediaPlayer.OnTimedTextListener()
  {
    public void onTimedText(String paramString)
    {
      Log.i("onSubtitleUpdate: %s", new Object[] { paramString });
      if (VideoView.this.mOnTimedTextListener != null)
        VideoView.this.mOnTimedTextListener.onTimedText(paramString);
    }

    public void onTimedTextUpdate(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      Log.i("onSubtitleUpdate: bitmap subtitle, %dx%d", arrayOfObject);
      if (VideoView.this.mOnTimedTextListener != null)
        VideoView.this.mOnTimedTextListener.onTimedTextUpdate(paramArrayOfByte, paramInt1, paramInt2);
    }
  };
  private Uri mUri;
  private float mVideoAspectRatio;
  private int mVideoChroma = 1;
  private int mVideoHeight;
  private int mVideoLayout = 1;
  private int mVideoWidth;

  public VideoView(Context paramContext)
  {
    super(paramContext);
    initVideoView(paramContext);
  }

  public VideoView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
    initVideoView(paramContext);
  }

  public VideoView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initVideoView(paramContext);
  }

  private void attachMediaController()
  {
    Object localObject;
    List localList;
    if ((this.mMediaPlayer != null) && (this.mMediaController != null))
    {
      this.mMediaController.setMediaPlayer(this);
      if (!(getParent() instanceof View))
        break label100;
      localObject = (View)getParent();
      this.mMediaController.setAnchorView((View)localObject);
      this.mMediaController.setEnabled(isInPlaybackState());
      if (this.mUri != null)
      {
        localList = this.mUri.getPathSegments();
        if ((localList != null) && (!localList.isEmpty()))
          break label105;
      }
    }
    label100: label105: for (String str = "null"; ; str = (String)localList.get(-1 + localList.size()))
    {
      this.mMediaController.setFileName(str);
      return;
      localObject = this;
      break;
    }
  }

  private void initVideoView(Context paramContext)
  {
    this.mContext = paramContext;
    this.mVideoWidth = 0;
    this.mVideoHeight = 0;
    getHolder().setFormat(1);
    getHolder().addCallback(this.mSHCallback);
    setFocusable(true);
    setFocusableInTouchMode(true);
    requestFocus();
    this.mCurrentState = 0;
    this.mTargetState = 0;
    if ((paramContext instanceof Activity))
      ((Activity)paramContext).setVolumeControlStream(3);
  }

  private void openVideo()
  {
    if ((this.mUri == null) || (this.mSurfaceHolder == null) || (!Vitamio.isInitialized(this.mContext)))
      return;
    Intent localIntent = new Intent("com.android.music.musicservicecommand");
    localIntent.putExtra("command", "pause");
    this.mContext.sendBroadcast(localIntent);
    release(false);
    try
    {
      this.mDuration = -1L;
      this.mCurrentBufferPercentage = 0;
      this.mMediaPlayer = new MediaPlayer(this.mContext);
      this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
      this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
      this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
      this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
      this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
      this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
      this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
      this.mMediaPlayer.setOnTimedTextListener(this.mTimedTextListener);
      this.mMediaPlayer.setDataSource(this.mContext, this.mUri, this.mHeaders);
      this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
      MediaPlayer localMediaPlayer = this.mMediaPlayer;
      if (this.mVideoChroma == 0)
      {
        i = 0;
        localMediaPlayer.setVideoChroma(i);
        this.mMediaPlayer.setScreenOnWhilePlaying(true);
        this.mMediaPlayer.prepareAsync();
        this.mCurrentState = 1;
        attachMediaController();
        return;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        Log.e("Unable to open content: " + this.mUri, localIOException);
        this.mCurrentState = -1;
        this.mTargetState = -1;
        this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        return;
        int i = 1;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.e("Unable to open content: " + this.mUri, localIllegalArgumentException);
      this.mCurrentState = -1;
      this.mTargetState = -1;
      this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
    }
  }

  private void release(boolean paramBoolean)
  {
    if (this.mMediaPlayer != null)
    {
      this.mMediaPlayer.reset();
      this.mMediaPlayer.release();
      this.mMediaPlayer = null;
      this.mCurrentState = 0;
      if (paramBoolean)
        this.mTargetState = 0;
    }
  }

  private void toggleMediaControlsVisiblity()
  {
    if (this.mMediaController.isShowing())
    {
      this.mMediaController.hide();
      return;
    }
    this.mMediaController.show();
  }

  public void addTimedTextSource(String paramString)
  {
    if (this.mMediaPlayer != null)
      this.mMediaPlayer.addTimedTextSource(paramString);
  }

  public boolean canPause()
  {
    return this.mCanPause;
  }

  public boolean canSeekBackward()
  {
    return this.mCanSeekBack;
  }

  public boolean canSeekForward()
  {
    return this.mCanSeekForward;
  }

  public int getAudioTrack()
  {
    if (this.mMediaPlayer != null)
      return this.mMediaPlayer.getAudioTrack();
    return -1;
  }

  public SparseArray<String> getAudioTrackMap(String paramString)
  {
    if (this.mMediaPlayer != null)
      return this.mMediaPlayer.findTrackFromTrackInfo(2, this.mMediaPlayer.getTrackInfo(paramString));
    return null;
  }

  public int getBufferPercentage()
  {
    if (this.mMediaPlayer != null)
      return this.mCurrentBufferPercentage;
    return 0;
  }

  public long getCurrentPosition()
  {
    if (isInPlaybackState())
      return this.mMediaPlayer.getCurrentPosition();
    return 0L;
  }

  public long getDuration()
  {
    if (isInPlaybackState())
    {
      if (this.mDuration > 0L)
        return this.mDuration;
      this.mDuration = this.mMediaPlayer.getDuration();
      return this.mDuration;
    }
    this.mDuration = -1L;
    return this.mDuration;
  }

  public String getMetaEncoding()
  {
    if (this.mMediaPlayer != null)
      return this.mMediaPlayer.getMetaEncoding();
    return null;
  }

  public SparseArray<String> getSubTrackMap(String paramString)
  {
    if (this.mMediaPlayer != null)
      return this.mMediaPlayer.findTrackFromTrackInfo(3, this.mMediaPlayer.getTrackInfo(paramString));
    return null;
  }

  public int getTimedTextLocation()
  {
    if (this.mMediaPlayer != null)
      return this.mMediaPlayer.getTimedTextLocation();
    return -1;
  }

  public String getTimedTextPath()
  {
    if (this.mMediaPlayer != null)
      return this.mMediaPlayer.getTimedTextPath();
    return null;
  }

  public int getTimedTextTrack()
  {
    if (this.mMediaPlayer != null)
      return this.mMediaPlayer.getTimedTextTrack();
    return -1;
  }

  public float getVideoAspectRatio()
  {
    return this.mVideoAspectRatio;
  }

  public int getVideoHeight()
  {
    return this.mVideoHeight;
  }

  public int getVideoWidth()
  {
    return this.mVideoWidth;
  }

  public boolean isBuffering()
  {
    if (this.mMediaPlayer != null)
      return this.mMediaPlayer.isBuffering();
    return false;
  }

  protected boolean isInPlaybackState()
  {
    return (this.mMediaPlayer != null) && (this.mCurrentState != -1) && (this.mCurrentState != 0) && (this.mCurrentState != 1);
  }

  public boolean isPlaying()
  {
    return (isInPlaybackState()) && (this.mMediaPlayer.isPlaying());
  }

  public boolean isValid()
  {
    return (this.mSurfaceHolder != null) && (this.mSurfaceHolder.getSurface().isValid());
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    int i;
    if ((paramInt != 4) && (paramInt != 24) && (paramInt != 25) && (paramInt != 82) && (paramInt != 5) && (paramInt != 6))
    {
      i = 1;
      if ((!isInPlaybackState()) || (i == 0) || (this.mMediaController == null))
        break label181;
      if ((paramInt != 79) && (paramInt != 85) && (paramInt != 62))
        break label113;
      if (!this.mMediaPlayer.isPlaying())
        break label100;
      pause();
      this.mMediaController.show();
    }
    label100: label113: 
    do
    {
      do
      {
        return true;
        i = 0;
        break;
        start();
        this.mMediaController.hide();
        return true;
        if (paramInt != 126)
          break label142;
      }
      while (this.mMediaPlayer.isPlaying());
      start();
      this.mMediaController.hide();
      return true;
      if ((paramInt != 86) && (paramInt != 127))
        break label177;
    }
    while (!this.mMediaPlayer.isPlaying());
    label142: pause();
    this.mMediaController.show();
    return true;
    label177: toggleMediaControlsVisiblity();
    label181: return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(getDefaultSize(this.mVideoWidth, paramInt1), getDefaultSize(this.mVideoHeight, paramInt2));
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((isInPlaybackState()) && (this.mMediaController != null))
      toggleMediaControlsVisiblity();
    return false;
  }

  public boolean onTrackballEvent(MotionEvent paramMotionEvent)
  {
    if ((isInPlaybackState()) && (this.mMediaController != null))
      toggleMediaControlsVisiblity();
    return false;
  }

  public void pause()
  {
    if ((isInPlaybackState()) && (this.mMediaPlayer.isPlaying()))
    {
      this.mMediaPlayer.pause();
      this.mCurrentState = 4;
    }
    this.mTargetState = 4;
  }

  public void resume()
  {
    if ((this.mSurfaceHolder == null) && (this.mCurrentState == 6))
      this.mTargetState = 7;
    do
      return;
    while (this.mCurrentState != 8);
    openVideo();
  }

  public void seekTo(long paramLong)
  {
    if (isInPlaybackState())
    {
      this.mMediaPlayer.seekTo(paramLong);
      this.mSeekWhenPrepared = 0L;
      return;
    }
    this.mSeekWhenPrepared = paramLong;
  }

  public void setAudioTrack(int paramInt)
  {
    if (this.mMediaPlayer != null)
      this.mMediaPlayer.selectTrack(paramInt);
  }

  public void setBufferSize(int paramInt)
  {
    if (this.mMediaPlayer != null)
      this.mMediaPlayer.setBufferSize(paramInt);
  }

  public void setMediaBufferingIndicator(View paramView)
  {
    if (this.mMediaBufferingIndicator != null)
      this.mMediaBufferingIndicator.setVisibility(8);
    this.mMediaBufferingIndicator = paramView;
  }

  public void setMediaController(MediaController paramMediaController)
  {
    if (this.mMediaController != null)
      this.mMediaController.hide();
    this.mMediaController = paramMediaController;
    attachMediaController();
  }

  public void setMetaEncoding(String paramString)
  {
    if (this.mMediaPlayer != null)
      this.mMediaPlayer.setMetaEncoding(paramString);
  }

  public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener paramOnBufferingUpdateListener)
  {
    this.mOnBufferingUpdateListener = paramOnBufferingUpdateListener;
  }

  public void setOnCompletionListener(MediaPlayer.OnCompletionListener paramOnCompletionListener)
  {
    this.mOnCompletionListener = paramOnCompletionListener;
  }

  public void setOnErrorListener(MediaPlayer.OnErrorListener paramOnErrorListener)
  {
    this.mOnErrorListener = paramOnErrorListener;
  }

  public void setOnInfoListener(MediaPlayer.OnInfoListener paramOnInfoListener)
  {
    this.mOnInfoListener = paramOnInfoListener;
  }

  public void setOnPreparedListener(MediaPlayer.OnPreparedListener paramOnPreparedListener)
  {
    this.mOnPreparedListener = paramOnPreparedListener;
  }

  public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener paramOnSeekCompleteListener)
  {
    this.mOnSeekCompleteListener = paramOnSeekCompleteListener;
  }

  public void setOnTimedTextListener(MediaPlayer.OnTimedTextListener paramOnTimedTextListener)
  {
    this.mOnTimedTextListener = paramOnTimedTextListener;
  }

  public void setSubTrack(int paramInt)
  {
    if (this.mMediaPlayer != null)
      this.mMediaPlayer.selectTrack(paramInt);
  }

  public void setTimedTextEncoding(String paramString)
  {
    if (this.mMediaPlayer != null)
      this.mMediaPlayer.setTimedTextEncoding(paramString);
  }

  public void setTimedTextShown(boolean paramBoolean)
  {
    if (this.mMediaPlayer != null)
      this.mMediaPlayer.setTimedTextShown(paramBoolean);
  }

  public void setVideoChroma(int paramInt)
  {
    SurfaceHolder localSurfaceHolder = getHolder();
    if (paramInt == 0);
    for (int i = 4; ; i = 1)
    {
      localSurfaceHolder.setFormat(i);
      this.mVideoChroma = paramInt;
      return;
    }
  }

  public void setVideoHeaders(Map<String, String> paramMap)
  {
    this.mHeaders = paramMap;
  }

  public void setVideoLayout(int paramInt, float paramFloat)
  {
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    DisplayMetrics localDisplayMetrics = this.mContext.getResources().getDisplayMetrics();
    int i = localDisplayMetrics.widthPixels;
    int j = localDisplayMetrics.heightPixels;
    float f1 = i / j;
    float f2;
    if (paramFloat <= 0.01F)
      f2 = this.mVideoAspectRatio;
    while (true)
    {
      this.mSurfaceHeight = this.mVideoHeight;
      this.mSurfaceWidth = this.mVideoWidth;
      if ((paramInt != 0) || (this.mSurfaceWidth >= i) || (this.mSurfaceHeight >= j))
        break;
      localLayoutParams.width = (int)(f2 * this.mSurfaceHeight);
      localLayoutParams.height = this.mSurfaceHeight;
      setLayoutParams(localLayoutParams);
      getHolder().setFixedSize(this.mSurfaceWidth, this.mSurfaceHeight);
      Object[] arrayOfObject = new Object[10];
      arrayOfObject[0] = Integer.valueOf(this.mVideoWidth);
      arrayOfObject[1] = Integer.valueOf(this.mVideoHeight);
      arrayOfObject[2] = Float.valueOf(this.mVideoAspectRatio);
      arrayOfObject[3] = Integer.valueOf(this.mSurfaceWidth);
      arrayOfObject[4] = Integer.valueOf(this.mSurfaceHeight);
      arrayOfObject[5] = Integer.valueOf(localLayoutParams.width);
      arrayOfObject[6] = Integer.valueOf(localLayoutParams.height);
      arrayOfObject[7] = Integer.valueOf(i);
      arrayOfObject[8] = Integer.valueOf(j);
      arrayOfObject[9] = Float.valueOf(f1);
      Log.d("VIDEO: %dx%dx%f, Surface: %dx%d, LP: %dx%d, Window: %dx%dx%f", arrayOfObject);
      this.mVideoLayout = paramInt;
      this.mAspectRatio = paramFloat;
      return;
      f2 = paramFloat;
    }
    if (paramInt == 3)
    {
      int i1;
      if (f1 > f2)
      {
        i1 = i;
        label292: localLayoutParams.width = i1;
        if (f1 >= f2)
          break label331;
      }
      label331: for (int i2 = j; ; i2 = (int)(i / f2))
      {
        localLayoutParams.height = i2;
        break;
        i1 = (int)(f2 * j);
        break label292;
      }
    }
    int k;
    label351: int m;
    if (paramInt == 2)
    {
      k = 1;
      if ((k == 0) && (f1 >= f2))
        break label406;
      m = i;
      label368: localLayoutParams.width = m;
      if ((k == 0) && (f1 <= f2))
        break label418;
    }
    label406: label418: for (int n = j; ; n = (int)(i / f2))
    {
      localLayoutParams.height = n;
      break;
      k = 0;
      break label351;
      m = (int)(f2 * j);
      break label368;
    }
  }

  public void setVideoPath(String paramString)
  {
    setVideoURI(Uri.parse(paramString));
  }

  public void setVideoQuality(int paramInt)
  {
    if (this.mMediaPlayer != null)
      this.mMediaPlayer.setVideoQuality(paramInt);
  }

  public void setVideoURI(Uri paramUri)
  {
    this.mUri = paramUri;
    this.mSeekWhenPrepared = 0L;
    openVideo();
    requestLayout();
    invalidate();
  }

  public void setVolume(float paramFloat1, float paramFloat2)
  {
    if (this.mMediaPlayer != null)
      this.mMediaPlayer.setVolume(paramFloat1, paramFloat2);
  }

  public void start()
  {
    if (isInPlaybackState())
    {
      this.mMediaPlayer.start();
      this.mCurrentState = 3;
    }
    this.mTargetState = 3;
  }

  public void stop()
  {
  }

  public void stopPlayback()
  {
    if (this.mMediaPlayer != null)
    {
      this.mMediaPlayer.stop();
      this.mMediaPlayer.release();
      this.mMediaPlayer = null;
      this.mCurrentState = 0;
      this.mTargetState = 0;
    }
  }

  public void suspend()
  {
    if (isInPlaybackState())
    {
      release(false);
      this.mCurrentState = 8;
      Log.d("Unable to suspend video. Release MediaPlayer.", new Object[0]);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.widget.VideoView
 * JD-Core Version:    0.6.0
 */