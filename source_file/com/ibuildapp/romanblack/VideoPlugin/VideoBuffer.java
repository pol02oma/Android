package com.ibuildapp.romanblack.VideoPlugin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.VideoView;
import java.io.Serializable;
import java.util.List;

public class VideoBuffer extends AppBuilderModuleMain
  implements MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener
{
  ImageView buttonPlay;
  LinearLayout controls;
  TextView downloadRateView;
  private boolean isStart;
  List<VideoItem> items;
  TextView loadRateView;
  private VideoView mVideoView;
  private String path;
  ProgressBar pb;
  Integer position;
  private Uri uri;

  public void create()
  {
    hideTopBar();
    getWindow().addFlags(1024);
    setContentView(R.layout.romanblack_videobuffer);
    this.mVideoView = ((VideoView)findViewById(R.id.buffer));
    this.pb = ((ProgressBar)findViewById(R.id.probar));
    Bundle localBundle = getIntent().getExtras();
    this.position = ((Integer)localBundle.get("position"));
    this.items = ((List)localBundle.get("items"));
    this.path = ((VideoItem)this.items.get(this.position.intValue())).getUrl();
    if (!TextUtils.isEmpty(((VideoItem)this.items.get(this.position.intValue())).getTitle()))
      setTopBarTitle(((VideoItem)this.items.get(this.position.intValue())).getTitle());
    while (true)
    {
      this.downloadRateView = ((TextView)findViewById(R.id.download_rate));
      this.loadRateView = ((TextView)findViewById(R.id.load_rate));
      this.uri = Uri.parse(this.path);
      this.mVideoView.setVideoURI(this.uri);
      this.mVideoView.requestFocus();
      this.mVideoView.setOnInfoListener(this);
      this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
      {
        public void onCompletion(MediaPlayer paramMediaPlayer)
        {
          VideoBuffer.this.mVideoView.setVideoURI(VideoBuffer.this.uri);
          VideoBuffer.this.mVideoView.requestFocus();
        }
      });
      this.controls = ((LinearLayout)findViewById(R.id.romanblack_video_playerweb_contolpanel));
      this.controls.setVisibility(4);
      this.mVideoView.setOnBufferingUpdateListener(this);
      this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
      {
        public void onPrepared(MediaPlayer paramMediaPlayer)
        {
          paramMediaPlayer.setPlaybackSpeed(1.0F);
        }
      });
      this.mVideoView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (VideoBuffer.this.controls.getVisibility() == 4)
          {
            VideoBuffer.this.controls.setVisibility(0);
            return;
          }
          VideoBuffer.this.controls.setVisibility(4);
        }
      });
      findViewById(R.id.video_surface).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (VideoBuffer.this.controls.getVisibility() == 4)
          {
            VideoBuffer.this.controls.setVisibility(0);
            return;
          }
          VideoBuffer.this.controls.setVisibility(4);
        }
      });
      this.buttonPlay = ((ImageView)findViewById(R.id.romanblack_video_playerweb_btn_play));
      this.buttonPlay.setImageResource(R.drawable.romanblack_video_play);
      ImageView localImageView = (ImageView)findViewById(R.id.romanblack_video_playerweb_btn_prev);
      ((ImageView)findViewById(R.id.romanblack_video_playerweb_btn_next)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          VideoBuffer.this.mVideoView.stop();
          Intent localIntent = new Intent();
          Bundle localBundle = new Bundle();
          localBundle.putSerializable("items", (Serializable)VideoBuffer.this.items);
          localBundle.putInt("position", VideoBuffer.this.position.intValue());
          localIntent.putExtras(localBundle);
          VideoBuffer.this.setResult(Statics.PLAY_NEXT_VIDEO, localIntent);
          VideoBuffer.this.finish();
        }
      });
      localImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          VideoBuffer.this.mVideoView.stop();
          Intent localIntent = new Intent();
          Bundle localBundle = new Bundle();
          localBundle.putSerializable("items", (Serializable)VideoBuffer.this.items);
          localBundle.putInt("position", VideoBuffer.this.position.intValue());
          localIntent.putExtras(localBundle);
          VideoBuffer.this.setResult(1001, localIntent);
          VideoBuffer.this.finish();
        }
      });
      this.buttonPlay.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (VideoBuffer.this.mVideoView.isPlaying())
          {
            VideoBuffer.this.mVideoView.pause();
            VideoBuffer.this.buttonPlay.setImageResource(R.drawable.romanblack_video_play);
            return;
          }
          VideoBuffer.this.mVideoView.start();
          VideoBuffer.this.buttonPlay.setImageResource(R.drawable.romanblack_video_pause);
        }
      });
      return;
      setTopBarTitle("");
    }
  }

  public void destroy()
  {
    this.mVideoView.stop();
    super.destroy();
  }

  public void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt)
  {
    this.loadRateView.setText(paramInt + "%");
  }

  public boolean onInfo(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default:
    case 701:
    case 702:
      do
      {
        do
          return true;
        while (!this.mVideoView.isPlaying());
        this.mVideoView.pause();
        this.isStart = true;
        this.pb.setVisibility(0);
        this.downloadRateView.setVisibility(0);
        this.loadRateView.setVisibility(0);
        return true;
      }
      while (!this.isStart);
      this.mVideoView.start();
      this.pb.setVisibility(8);
      this.downloadRateView.setVisibility(8);
      this.loadRateView.setVisibility(8);
      this.buttonPlay.setImageResource(R.drawable.romanblack_video_pause);
      return true;
    case 901:
    }
    this.downloadRateView.setText("" + paramInt2 + "kb/s" + "  ");
    return true;
  }

  public void pause()
  {
    super.pause();
  }

  public void restart()
  {
    super.restart();
  }

  public void resume()
  {
    super.resume();
  }

  public void start()
  {
    super.start();
  }

  public void stop()
  {
    super.stop();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.VideoBuffer
 * JD-Core Version:    0.6.0
 */