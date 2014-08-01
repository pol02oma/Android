package com.ibuildapp.romanblack.NewsPlugin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModule;
import com.appbuilder.sdk.android.Utils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class VideoPlayer extends AppBuilderModule
  implements SurfaceHolder.Callback
{
  private final int CHECK_CONTROLS_STATE = 12;
  private final int DOWNLOAD_COMPLETE = 5;
  private final int DOWNLOAD_ERROR = 6;
  private final int DOWNLOAD_START = 10;
  private final int DOWNLOAD_UPDATE = 11;
  private final int HIDE_CONTROLS = 9;
  private final int LOADING_ABORTED = 14;
  private final int SHOW_CONTROLS = 8;
  private final int UPDATE_CONTROLS_STATE = 13;
  private final int UPDATE_SEEK_BAR = 7;
  private final int VIDEO_PLAYER_ERROR = 0;
  private final int VIDEO_PLAYER_PAUSE = 3;
  private final int VIDEO_PLAYER_START = 2;
  private AudioManager audioManager = null;
  private int audioVolume = 0;
  private int btnActive = 0;
  private ImageView btnNext = null;
  private ImageView btnPlay = null;
  private ImageView btnPrev = null;
  private ImageView btnStop = null;
  private String cachePath = "";
  private long downloadFileSize = 0L;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 1:
      case 3:
      case 4:
      default:
      case 2:
      case 0:
      case 10:
      case 11:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 12:
        do
        {
          return;
          VideoPlayer.this.startVideoPlayer();
          return;
          Toast.makeText(VideoPlayer.this, "This Video Link Is Not Valid.", 1).show();
          File localFile2 = new File(VideoPlayer.this.cachePath);
          if (localFile2.exists())
            localFile2.delete();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              VideoPlayer.this.finish();
            }
          }
          , 3000L);
          return;
          VideoPlayer.this.openProgressDialog();
          return;
          VideoPlayer.this.updateProgressDialog();
          return;
          if (new File(VideoPlayer.this.cachePath).exists())
          {
            VideoPlayer.this.startVideoPlayer();
            return;
          }
          sendEmptyMessage(0);
          return;
          sendEmptyMessage(0);
          return;
          VideoPlayer.this.updateSeekBar();
          return;
          VideoPlayer.this.showControls();
          return;
          VideoPlayer.this.hideControls();
          return;
        }
        while (VideoPlayer.this.state != 1);
        VideoPlayer.this.checkControlsState();
        return;
      case 13:
        VideoPlayer.access$902(VideoPlayer.this, 3);
        return;
      case 14:
      }
      VideoPlayer.this.closeProgressDialog();
      File localFile1 = new File(VideoPlayer.this.cachePath);
      if (localFile1.exists())
        localFile1.delete();
      VideoPlayer.this.finish();
    }
  };
  private boolean isTouchSeekBar = false;
  private MediaPlayer mediaPlayer;
  private ProgressDialog progressDialog;
  private SeekBar seekBar = null;
  private final int sourceCache = 1;
  private int sourceType = 2;
  private final int sourceUrl = 2;
  private int state = 2;
  private final int statePause = 2;
  private final int statePlay = 1;
  private SurfaceHolder surfaceHolder;
  private SurfaceView surfaceView;
  private TelephonyManager telephonyManager = null;
  private int videoCurrentPos = 0;
  private String videoUrl = "";

  private void checkControlsState()
  {
    if (this.btnActive > 0)
    {
      this.btnActive = (-1 + this.btnActive);
      this.handler.sendEmptyMessageDelayed(12, 1000L);
      return;
    }
    this.handler.sendEmptyMessageDelayed(9, 1000L);
  }

  private boolean checkInternetConnection()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    int i = 0;
    if (localNetworkInfo != null)
    {
      boolean bool = localNetworkInfo.isConnectedOrConnecting();
      i = 0;
      if (bool)
        i = 1;
    }
    if (i == 0)
      Toast.makeText(this, R.string.romanblack_rss_alert_no_internet, 1000).show();
    return i;
  }

  private void closeProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void downloadFile()
  {
    try
    {
      if (!checkInternetConnection())
        this.handler.sendEmptyMessage(6);
      try
      {
        URLConnection localURLConnection = new URL(this.videoUrl).openConnection();
        this.downloadFileSize = localURLConnection.getContentLength();
        String str = localURLConnection.getContentType();
        if ((!str.contains("mpeg")) && (!str.contains("mp4")) && (!str.contains("3gpp")) && (!str.contains("H263")) && (!str.contains("H264")))
        {
          this.handler.sendEmptyMessage(6);
          return;
        }
        if (this.downloadFileSize <= 0L)
        {
          this.handler.sendEmptyMessage(6);
          return;
        }
      }
      catch (Exception localException2)
      {
        this.handler.sendEmptyMessage(6);
        return;
      }
    }
    catch (Exception localException1)
    {
      return;
    }
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    double d = localStatFs.getAvailableBlocks() * localStatFs.getBlockSize();
    if (0.5D * this.downloadFileSize > d)
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          VideoPlayer.this.handler.sendEmptyMessage(6);
        }
      }
      , 5000L);
      return;
    }
    new Thread()
    {
      public void run()
      {
        try
        {
          URLConnection localURLConnection = new URL(VideoPlayer.this.videoUrl).openConnection();
          File localFile = new File(VideoPlayer.this.cachePath);
          VideoPlayer.this.handler.sendEmptyMessage(10);
          if ((localFile.exists()) && (localFile.length() == VideoPlayer.this.downloadFileSize))
            VideoPlayer.this.handler.sendEmptyMessage(5);
          BufferedInputStream localBufferedInputStream = new BufferedInputStream(localURLConnection.getInputStream());
          byte[] arrayOfByte = new byte[2048];
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
          for (int i = 0; i != -1; i = localBufferedInputStream.read(arrayOfByte, 0, 2048))
            localFileOutputStream.write(arrayOfByte, 0, i);
          localFileOutputStream.close();
          localBufferedInputStream.close();
          VideoPlayer.this.handler.sendEmptyMessage(5);
          return;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          VideoPlayer.this.handler.sendEmptyMessage(6);
          return;
        }
        catch (IOException localIOException)
        {
          VideoPlayer.this.handler.sendEmptyMessage(6);
          return;
        }
        catch (Exception localException)
        {
        }
      }
    }
    .start();
  }

  private void hideControls()
  {
    this.seekBar.setVisibility(4);
    this.btnPlay.setVisibility(4);
    this.btnStop.setVisibility(4);
    this.btnNext.setVisibility(4);
    this.btnPrev.setVisibility(4);
  }

  private void openProgressDialog()
  {
    if (this.downloadFileSize > 0L);
    try
    {
      this.progressDialog = new ProgressDialog(this);
      this.progressDialog.setProgressStyle(1);
      this.progressDialog.setMessage(getString(R.string.romanblack_rss_loading));
      this.progressDialog.setProgress(0);
      this.progressDialog.show();
      this.handler.sendEmptyMessageDelayed(11, 500L);
      while (true)
      {
        label71: this.progressDialog.setCancelable(true);
        this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramDialogInterface)
          {
            VideoPlayer.this.handler.sendEmptyMessage(14);
          }
        });
        return;
        this.progressDialog = ProgressDialog.show(this, "", getString(R.string.romanblack_rss_loading), true);
      }
    }
    catch (Exception localException)
    {
      break label71;
    }
  }

  private void showControls()
  {
    this.seekBar.setVisibility(0);
    this.btnPlay.setVisibility(0);
    this.btnStop.setVisibility(0);
    this.btnNext.setVisibility(0);
    this.btnPrev.setVisibility(0);
  }

  private void startVideoPlayer()
  {
    try
    {
      File localFile = new File(this.cachePath);
      boolean bool1 = localFile.exists();
      int i;
      if (bool1)
        i = 1;
      try
      {
        this.downloadFileSize = new URL(this.videoUrl).openConnection().getContentLength();
        this.sourceType = 1;
        long l1 = localFile.length();
        long l2 = this.downloadFileSize;
        boolean bool2 = l1 < l2;
        j = 0;
        if (bool2);
        for (j = 1; j == 1; j = 0)
        {
          closeProgressDialog();
          this.handler.sendEmptyMessage(10);
          return;
          this.sourceType = 2;
          i = 0;
        }
        if (this.mediaPlayer != null)
        {
          if (i == 0)
            openProgressDialog();
          new Thread()
          {
            public void run()
            {
              String str = "";
              if (VideoPlayer.this.sourceType == 2)
                str = VideoPlayer.this.videoUrl;
              try
              {
                while (true)
                {
                  VideoPlayer.this.mediaPlayer.reset();
                  VideoPlayer.this.mediaPlayer.setDataSource(str);
                  VideoPlayer.this.mediaPlayer.prepare();
                  return;
                  if (VideoPlayer.this.sourceType != 1)
                    continue;
                  str = VideoPlayer.this.cachePath;
                }
              }
              catch (Exception localException)
              {
                do
                  VideoPlayer.this.closeProgressDialog();
                while (VideoPlayer.this.sourceType != 2);
                VideoPlayer.access$1402(VideoPlayer.this, 1);
                VideoPlayer.this.downloadFile();
              }
            }
          }
          .start();
          return;
        }
      }
      catch (Exception localException2)
      {
        while (true)
          int j = 0;
      }
      return;
    }
    catch (Exception localException1)
    {
    }
  }

  private void updateProgressDialog()
  {
    if (this.progressDialog != null)
    {
      checkInternetConnection();
      File localFile = new File(this.cachePath);
      int i = this.progressDialog.getProgress();
      if (localFile.exists())
        i = (int)(100.0F * ((float)localFile.length() / (float)this.downloadFileSize));
      this.progressDialog.setProgress(i);
      if (i != 100);
    }
    else
    {
      return;
    }
    this.handler.sendEmptyMessageDelayed(11, 3000L);
  }

  private void updateSeekBar()
  {
    if (this.mediaPlayer != null);
    try
    {
      if ((this.state == 1) && (!this.isTouchSeekBar))
        this.seekBar.setProgress((int)(100.0F * (this.mediaPlayer.getCurrentPosition() / this.mediaPlayer.getDuration())));
      if ((this.state == 2) && (!this.isTouchSeekBar))
        this.seekBar.setProgress((int)(100.0F * (this.videoCurrentPos / this.mediaPlayer.getDuration())));
      label92: if (this.state == 1)
        this.handler.sendEmptyMessageDelayed(7, 300L);
      return;
    }
    catch (Exception localException)
    {
      break label92;
    }
  }

  public void create()
  {
    try
    {
      requestWindowFeature(1);
      getWindow().setFlags(1024, 1024);
      setContentView(R.layout.romanblack_rss_media_videoplayer);
      Bundle localBundle = getIntent().getExtras();
      this.videoUrl = localBundle.getString("link");
      this.cachePath = (localBundle.getString("cache") + "/video");
      this.state = 2;
      this.videoCurrentPos = 0;
      this.telephonyManager = ((TelephonyManager)getApplicationContext().getSystemService("phone"));
      this.telephonyManager.listen(new PhoneStateListener()
      {
        public void onCallStateChanged(int paramInt, String paramString)
        {
          switch (paramInt)
          {
          case 0:
          default:
            return;
          case 2:
            Log.d("DEBUG", "OFFHOOK");
            return;
          case 1:
          }
          if (VideoPlayer.this.state == 1)
          {
            VideoPlayer.this.btnPlay.setImageResource(R.drawable.romanblack_rss_icon_play);
            VideoPlayer.this.mediaPlayer.pause();
            VideoPlayer.access$702(VideoPlayer.this, 2);
          }
          Log.d("DEBUG", "RINGING");
        }
      }
      , 32);
      File localFile = new File(this.cachePath);
      if (!localFile.exists())
        localFile.mkdirs();
      this.cachePath = (this.cachePath + "/" + Utils.md5(this.videoUrl) + this.videoUrl.substring(this.videoUrl.lastIndexOf(".")));
      this.surfaceView = ((SurfaceView)findViewById(R.id.romanblack_rss_videoPlayer));
      this.surfaceHolder = this.surfaceView.getHolder();
      this.surfaceHolder.addCallback(this);
      this.surfaceHolder.setType(3);
      this.surfaceView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
        {
          VideoPlayer.this.handler.sendEmptyMessage(8);
          VideoPlayer.this.handler.sendEmptyMessage(13);
          VideoPlayer.this.handler.sendEmptyMessageDelayed(12, 300L);
          return false;
        }
      });
      this.audioManager = ((AudioManager)getSystemService("audio"));
      this.mediaPlayer = new MediaPlayer();
      this.mediaPlayer.setAudioStreamType(3);
      this.mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener()
      {
        public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
        {
          if (VideoPlayer.this.sourceType == 2)
          {
            VideoPlayer.access$1402(VideoPlayer.this, 1);
            VideoPlayer.this.downloadFile();
            return true;
          }
          VideoPlayer.this.handler.sendEmptyMessage(0);
          return true;
        }
      });
      this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
      {
        public void onCompletion(MediaPlayer paramMediaPlayer)
        {
          VideoPlayer.access$1602(VideoPlayer.this, 0);
          VideoPlayer.access$702(VideoPlayer.this, 2);
          VideoPlayer.this.btnPlay.setImageResource(R.drawable.romanblack_rss_icon_play);
          paramMediaPlayer.seekTo(VideoPlayer.this.videoCurrentPos);
          VideoPlayer.this.handler.sendEmptyMessage(8);
        }
      });
      this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
      {
        public void onPrepared(MediaPlayer paramMediaPlayer)
        {
          VideoPlayer.this.closeProgressDialog();
          int i = paramMediaPlayer.getVideoWidth();
          int j = paramMediaPlayer.getVideoHeight();
          int k = VideoPlayer.this.getWindowManager().getDefaultDisplay().getWidth();
          int m = VideoPlayer.this.getWindowManager().getDefaultDisplay().getHeight();
          int i1;
          int n;
          if (i > j)
          {
            i1 = k;
            n = (int)(k / i * j);
            if (n > m)
            {
              n = m;
              i1 = (int)(m / j * i);
            }
            ViewGroup.LayoutParams localLayoutParams = VideoPlayer.this.surfaceView.getLayoutParams();
            localLayoutParams.width = i1;
            localLayoutParams.height = n;
            VideoPlayer.this.surfaceView.setLayoutParams(localLayoutParams);
            if (VideoPlayer.this.videoCurrentPos > 0)
              VideoPlayer.this.handler.sendEmptyMessage(7);
            if (VideoPlayer.this.state != 1)
              break label243;
            VideoPlayer.access$902(VideoPlayer.this, 5);
            VideoPlayer.this.handler.sendEmptyMessage(12);
          }
          while (true)
          {
            paramMediaPlayer.seekTo(VideoPlayer.this.videoCurrentPos);
            return;
            n = m;
            i1 = (int)(m / j * i);
            if (i1 <= k)
              break;
            i1 = k;
            n = (int)(k / i * j);
            break;
            label243: Toast.makeText(VideoPlayer.this, R.string.romanblack_rss_alert_video_press_play, 1).show();
          }
        }
      });
      this.mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener()
      {
        public void onSeekComplete(MediaPlayer paramMediaPlayer)
        {
          VideoPlayer.access$1802(VideoPlayer.this, VideoPlayer.this.audioManager.getStreamVolume(3));
          if (VideoPlayer.this.state == 2)
            VideoPlayer.this.audioManager.setStreamVolume(3, 0, 0);
          paramMediaPlayer.start();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              VideoPlayer.this.audioManager.setStreamVolume(3, VideoPlayer.this.audioVolume, 0);
              try
              {
                if (VideoPlayer.this.state == 2)
                  VideoPlayer.this.mediaPlayer.pause();
                return;
              }
              catch (Exception localException)
              {
              }
            }
          }
          , 256L);
          if (VideoPlayer.this.seekBar.getVisibility() == 4)
            VideoPlayer.this.seekBar.setVisibility(0);
          VideoPlayer.this.handler.sendEmptyMessage(13);
        }
      });
      this.seekBar = ((SeekBar)findViewById(R.id.romanblack_rss_seekBar));
      this.seekBar.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
        {
          VideoPlayer.access$902(VideoPlayer.this, 100);
          VideoPlayer.access$2102(VideoPlayer.this, true);
          VideoPlayer.access$1602(VideoPlayer.this, VideoPlayer.this.mediaPlayer.getDuration() / 100 * VideoPlayer.this.seekBar.getProgress());
          if (paramMotionEvent.getAction() == 1)
          {
            VideoPlayer.this.mediaPlayer.seekTo(VideoPlayer.this.videoCurrentPos);
            VideoPlayer.access$2102(VideoPlayer.this, false);
            VideoPlayer.access$902(VideoPlayer.this, 3);
          }
          return false;
        }
      });
      this.seekBar.setVisibility(4);
      this.btnPlay = ((ImageView)findViewById(R.id.romanblack_rss_btnPlay));
      if (this.state == 1)
        this.btnPlay.setImageResource(R.drawable.romanblack_rss_icon_pause);
      this.btnPlay.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (VideoPlayer.this.state == 1)
          {
            VideoPlayer.this.btnPlay.setImageResource(R.drawable.romanblack_rss_icon_play);
            VideoPlayer.this.mediaPlayer.pause();
            VideoPlayer.access$702(VideoPlayer.this, 2);
          }
          do
            return;
          while (VideoPlayer.this.state != 2);
          VideoPlayer.this.btnPlay.setImageResource(R.drawable.romanblack_rss_icon_pause);
          VideoPlayer.this.mediaPlayer.start();
          VideoPlayer.access$702(VideoPlayer.this, 1);
          VideoPlayer.this.handler.sendEmptyMessage(12);
          VideoPlayer.this.handler.sendEmptyMessage(7);
        }
      });
      this.btnStop = ((ImageView)findViewById(R.id.romanblack_rss_btnStop));
      this.btnStop.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          VideoPlayer.this.btnPlay.setImageResource(R.drawable.romanblack_rss_icon_play);
          VideoPlayer.access$702(VideoPlayer.this, 2);
          VideoPlayer.this.mediaPlayer.pause();
          VideoPlayer.this.mediaPlayer.seekTo(0);
        }
      });
      this.btnNext = ((ImageView)findViewById(R.id.romanblack_rss_btnNext));
      this.btnNext.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          int i = VideoPlayer.this.mediaPlayer.getDuration();
          VideoPlayer.access$1602(VideoPlayer.this, 5000 + VideoPlayer.this.mediaPlayer.getCurrentPosition());
          if (VideoPlayer.this.videoCurrentPos > i - 256)
            VideoPlayer.access$1602(VideoPlayer.this, i - 256);
          VideoPlayer.this.mediaPlayer.seekTo(VideoPlayer.this.videoCurrentPos);
          if (VideoPlayer.this.state == 2)
            VideoPlayer.this.handler.sendEmptyMessage(7);
        }
      });
      this.btnPrev = ((ImageView)findViewById(R.id.romanblack_rss_btnPrev));
      this.btnPrev.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          VideoPlayer.access$1602(VideoPlayer.this, -5000 + VideoPlayer.this.mediaPlayer.getCurrentPosition());
          if (VideoPlayer.this.videoCurrentPos < 0)
            VideoPlayer.access$1602(VideoPlayer.this, 0);
          VideoPlayer.this.mediaPlayer.seekTo(VideoPlayer.this.videoCurrentPos);
          if (VideoPlayer.this.state == 2)
            VideoPlayer.this.handler.sendEmptyMessage(7);
        }
      });
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void destroy()
  {
    if (this.mediaPlayer != null)
    {
      this.mediaPlayer.stop();
      this.mediaPlayer.release();
    }
  }

  public void restart()
  {
  }

  public void resume()
  {
  }

  public void start()
  {
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    this.mediaPlayer.setDisplay(this.surfaceHolder);
    this.handler.sendEmptyMessage(2);
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.VideoPlayer
 * JD-Core Version:    0.6.0
 */