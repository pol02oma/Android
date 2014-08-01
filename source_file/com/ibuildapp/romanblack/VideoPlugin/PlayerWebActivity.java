package com.ibuildapp.romanblack.VideoPlugin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
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
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PlayerWebActivity extends AppBuilderModuleMain
  implements View.OnClickListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, SurfaceHolder.Callback
{
  private final int CHECK_CONTROLS_STATE = 10;
  private final int HIDE_CONTROLS = 14;
  private final int HIDE_PROGRESS_DIALOG = 4;
  private final int INITIALIZATION_FAILED = 1;
  private final int LOADING_ABORTED = 2;
  private final int NEED_INTERNET_CONNECTION = 0;
  private final int RESOLVE_YOUTUBE_URL = 11;
  private final int SHOW_CONTROLS = 8;
  private final int SHOW_PROGRESS_DIALOG = 3;
  private final int SHOW_SURFACE = 6;
  private final int SHOW_WEB = 5;
  private final int SHOW_YOUTUBE = 13;
  private final int UPDATE_CONTROLS_STATE = 15;
  private final int UPDATE_SEEK_BAR = 9;
  private final int VIDEOPLAYER_ERROR = 7;
  private final int VIDEO_PLAYER_START = 12;
  private int btnActive = 0;
  private ImageView btnNextImageView = null;
  private ImageView btnPlayImageView = null;
  private ImageView btnPrevImageView = null;
  private LinearLayout controlsLayout = null;
  private TextView durationNegativeTextView = null;
  private TextView durationPositiveTextView = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 11:
      case 12:
      case 13:
      case 10:
        do
        {
          return;
          Toast.makeText(PlayerWebActivity.this, PlayerWebActivity.this.getResources().getString(R.string.romanblack_video_alert_no_internet), 1).show();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              PlayerWebActivity.this.finish();
            }
          }
          , 2000L);
          return;
          Toast.makeText(PlayerWebActivity.this, PlayerWebActivity.this.getResources().getString(R.string.romanblack_video_alert_cannot_init), 1).show();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              PlayerWebActivity.this.finish();
            }
          }
          , 2000L);
          return;
          PlayerWebActivity.this.closeActivity();
          return;
          PlayerWebActivity.this.showProgressDialog();
          return;
          PlayerWebActivity.this.hideProgressDialog();
          return;
          PlayerWebActivity.this.showWeb();
          return;
          PlayerWebActivity.this.showSurface();
          return;
          Toast.makeText(PlayerWebActivity.this, PlayerWebActivity.this.getResources().getString(R.string.romanblack_video_alert_link_not_valid), 1).show();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              PlayerWebActivity.this.finish();
            }
          }
          , 2000L);
          return;
          PlayerWebActivity.this.showControls();
          return;
          PlayerWebActivity.this.updateSeekBar();
          return;
          PlayerWebActivity.this.resolveYouTubeUrl();
          return;
          PlayerWebActivity.this.playVideo();
          return;
          PlayerWebActivity.this.showYouTube();
          return;
        }
        while (PlayerWebActivity.this.playerState != PlayerWebActivity.PLAYER_STATES.STATE_PLAY);
        PlayerWebActivity.this.checkControlsState();
        return;
      case 14:
        PlayerWebActivity.this.hideControls();
        return;
      case 15:
      }
      PlayerWebActivity.access$1302(PlayerWebActivity.this, 3);
    }
  };
  private TextView homeImageView = null;
  private boolean isTouchSeekBar = false;
  private ArrayList<VideoItem> items = new ArrayList();
  private boolean linkResolved = false;
  private MediaPlayer mediaPlayer = null;
  private boolean playIsActive = false;
  private boolean playerPrepared = false;
  private PLAYER_STATES playerState = PLAYER_STATES.STATE_STOP;
  private String playingUrl = "";
  private int position = 0;
  private ProgressDialog progressDialog = null;
  private SeekBar seekBar = null;
  private boolean surfaceCreated = false;
  private SurfaceHolder surfaceHolder = null;
  private LinearLayout surfaceLayout = null;
  private SurfaceView surfaceView = null;
  private TelephonyManager telephonyManager = null;
  private int videoCurrentPos = 0;
  private int videoHeight = 0;
  private VideoItem videoItem = null;
  private int videoWidth = 0;
  private LinearLayout webLayout = null;
  private WebView webView = null;
  private String youTubeHTML = "";

  private void checkControlsState()
  {
    if (this.btnActive > 0)
    {
      this.btnActive = (-1 + this.btnActive);
      this.handler.sendEmptyMessageDelayed(10, 1000L);
      return;
    }
    this.handler.sendEmptyMessageDelayed(14, 1000L);
  }

  private void closeActivity()
  {
    hideProgressDialog();
    finish();
  }

  public static int getSupportedFallbackId(int paramInt)
  {
    int[] arrayOfInt = { 13, 17, 18, 22, 37 };
    int i = paramInt;
    for (int j = -1 + arrayOfInt.length; j >= 0; j--)
    {
      if ((paramInt != arrayOfInt[j]) || (j <= 0))
        continue;
      i = arrayOfInt[(j - 1)];
    }
    return i;
  }

  private void hideControls()
  {
    this.controlsLayout.setVisibility(4);
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void playVideo()
  {
    try
    {
      this.handler.sendEmptyMessage(3);
      this.mediaPlayer.reset();
      this.mediaPlayer.setDataSource(this.playingUrl);
      this.mediaPlayer.prepareAsync();
      return;
    }
    catch (IOException localIOException)
    {
      Log.d("", "");
    }
  }

  private void prepareSurfaceLayoutParams()
  {
    int i = getWindowManager().getDefaultDisplay().getWidth();
    int j = getWindowManager().getDefaultDisplay().getHeight() - (int)(50.0F * getResources().getDisplayMetrics().density);
    int m;
    int k;
    if (this.videoWidth > this.videoHeight)
    {
      m = i;
      k = (int)(i / this.videoWidth * this.videoHeight);
      if (k > j)
      {
        k = j;
        m = (int)(j / this.videoHeight * this.videoWidth);
      }
    }
    while (true)
    {
      ViewGroup.LayoutParams localLayoutParams = this.surfaceView.getLayoutParams();
      localLayoutParams.width = m;
      localLayoutParams.height = k;
      this.surfaceView.setLayoutParams(localLayoutParams);
      return;
      k = j;
      m = (int)(j / this.videoHeight * this.videoWidth);
      if (m <= i)
        continue;
      m = i;
      k = (int)(i / this.videoWidth * this.videoHeight);
    }
  }

  private String prepareVimeoHTML(String paramString, int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = (String)Uri.parse(paramString).getPathSegments().get(0);
    localStringBuilder.append("<html>");
    localStringBuilder.append("<head>");
    localStringBuilder.append("<body style=\"margin:0\" bgcolor=\"black\">");
    localStringBuilder.append("<iframe src=\"");
    localStringBuilder.append("http://player.vimeo.com/video/");
    localStringBuilder.append(str);
    localStringBuilder.append("\" width=\"100%");
    localStringBuilder.append("\" height=\"100%");
    localStringBuilder.append("\" frameborder=\"0\" webkitAllowFullScreen mozallowfullscreen allowFullScreen>");
    localStringBuilder.append("</iframe>");
    localStringBuilder.append("</body>");
    localStringBuilder.append("</html>");
    return localStringBuilder.toString();
  }

  private void resolveYouTubeUrl()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Uri.parse(((VideoItem)PlayerWebActivity.this.items.get(PlayerWebActivity.this.position)).getUrl()).getQueryParameter("v");
          Log.d("", "");
          HttpResponse localHttpResponse = new DefaultHttpClient().execute(new HttpGet("http://www.youtube.com/oembed?url=" + URLEncoder.encode(((VideoItem)PlayerWebActivity.this.items.get(PlayerWebActivity.this.position)).getUrl()) + "&format=json"));
          ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
          localHttpResponse.getEntity().writeTo(localByteArrayOutputStream);
          String str = localByteArrayOutputStream.toString("UTF-8");
          Log.d("", "");
          Document localDocument = Jsoup.parse(new JSONObject(str).getString("html"));
          Attributes localAttributes = localDocument.select("iframe").first().attributes();
          int i = Integer.parseInt(localAttributes.get("height"));
          int j = Integer.parseInt(localAttributes.get("width"));
          float f = PlayerWebActivity.this.getResources().getDisplayMetrics().widthPixels / j;
          localAttributes.remove("height");
          localAttributes.remove("width");
          int k = (int)(f * i);
          int m = (int)(f * j);
          localAttributes.put("height", k + "");
          localAttributes.put("width", m + "");
          PlayerWebActivity.access$2202(PlayerWebActivity.this, localDocument.outerHtml());
          Log.d("", "");
          PlayerWebActivity.this.handler.sendEmptyMessage(13);
          if (PlayerWebActivity.this.surfaceCreated)
            PlayerWebActivity.this.handler.sendEmptyMessage(12);
          return;
        }
        catch (Exception localException)
        {
          Log.d("", "");
        }
      }
    }).start();
  }

  private void showControls()
  {
    this.controlsLayout.setVisibility(0);
  }

  private void showProgressDialog()
  {
    try
    {
      boolean bool = this.progressDialog.isShowing();
      if (bool)
        return;
    }
    catch (NullPointerException localNullPointerException)
    {
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_loading), true);
      this.progressDialog.setCancelable(true);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          PlayerWebActivity.this.handler.sendEmptyMessage(2);
        }
      });
    }
  }

  private void showSurface()
  {
    this.webLayout.setVisibility(4);
    this.surfaceLayout.setVisibility(0);
    this.controlsLayout.setVisibility(0);
  }

  private void showWeb()
  {
    this.webLayout.setVisibility(0);
    this.surfaceLayout.setVisibility(4);
    this.controlsLayout.setVisibility(4);
  }

  private void showYouTube()
  {
    this.webView.loadDataWithBaseURL("", this.youTubeHTML, "text/html", "utf-8", "");
  }

  private void updateSeekBar()
  {
    if (this.mediaPlayer != null);
    try
    {
      if ((this.playerState == PLAYER_STATES.STATE_PLAY) && (!this.isTouchSeekBar))
      {
        this.seekBar.setProgress((int)(100.0F * (this.mediaPlayer.getCurrentPosition() / this.mediaPlayer.getDuration())));
        int i = this.mediaPlayer.getDuration();
        int j = this.mediaPlayer.getCurrentPosition();
        Log.d("", "");
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("mm:ss");
        this.durationPositiveTextView.setText(localSimpleDateFormat.format(new Date(j)));
        this.durationNegativeTextView.setText("-" + localSimpleDateFormat.format(new Date(i - j)));
      }
      if ((this.playerState == PLAYER_STATES.STATE_PAUSE) && (!this.isTouchSeekBar))
        this.seekBar.setProgress((int)(100.0F * (this.videoCurrentPos / this.mediaPlayer.getDuration())));
      label198: if (this.playerState == PLAYER_STATES.STATE_PLAY)
        this.handler.sendEmptyMessageDelayed(9, 300L);
      return;
    }
    catch (Exception localException)
    {
      break label198;
    }
  }

  public void create()
  {
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(R.layout.romanblack_video_playerweb);
    Intent localIntent = getIntent();
    this.items = ((ArrayList)localIntent.getSerializableExtra("items"));
    if (this.items == null)
    {
      this.handler.sendEmptyMessage(1);
      return;
    }
    if (this.items.isEmpty())
    {
      this.handler.sendEmptyMessage(1);
      return;
    }
    this.position = localIntent.getIntExtra("position", 0);
    try
    {
      this.videoItem = ((VideoItem)this.items.get(this.position));
      if (this.videoItem == null)
      {
        this.handler.sendEmptyMessage(1);
        return;
      }
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      this.handler.sendEmptyMessage(1);
      return;
    }
    this.homeImageView = ((TextView)findViewById(R.id.romanblack_fanwall_main_home));
    this.homeImageView.setOnClickListener(this);
    if (!TextUtils.isEmpty(((VideoItem)this.items.get(this.position)).getTitle()))
      setTopBarTitle(((VideoItem)this.items.get(this.position)).getTitle());
    while (true)
    {
      swipeBlock();
      setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          PlayerWebActivity.this.onBackPressed();
        }
      });
      this.webLayout = ((LinearLayout)findViewById(R.id.romanblack_video_playerweb_webviewlayout));
      this.webView = ((WebView)findViewById(R.id.romanblack_video_playerweb_webview));
      this.webView.setScrollBarStyle(33554432);
      this.webView.getSettings().setJavaScriptEnabled(true);
      this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
      this.webView.getSettings().setPluginsEnabled(true);
      this.webView.getSettings().setSupportMultipleWindows(false);
      this.webView.getSettings().setSupportZoom(false);
      this.webView.getSettings().setUseWideViewPort(true);
      this.webView.setVerticalScrollBarEnabled(false);
      this.webView.setHorizontalScrollBarEnabled(false);
      this.webView.setWebChromeClient(new WebChromeClient()
      {
        public boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
        {
          Log.d("", "");
          return super.onCreateWindow(paramWebView, paramBoolean1, paramBoolean2, paramMessage);
        }

        public void onProgressChanged(WebView paramWebView, int paramInt)
        {
          Log.d("", "");
          super.onProgressChanged(paramWebView, paramInt);
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
          super.onShowCustomView(paramView, paramCustomViewCallback);
          Log.d("", "");
        }
      });
      this.webView.setWebViewClient(new WebViewClient()
      {
        public void onPageFinished(WebView paramWebView, String paramString)
        {
          super.onPageFinished(paramWebView, paramString);
          if (paramString.equals("end"))
          {
            PlayerWebActivity.this.handler.sendEmptyMessage(2);
            return;
          }
          PlayerWebActivity.this.handler.sendEmptyMessage(4);
        }

        public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
        {
          super.onPageStarted(paramWebView, paramString, paramBitmap);
          PlayerWebActivity.this.handler.sendEmptyMessage(3);
        }
      });
      this.btnPlayImageView = ((ImageView)findViewById(R.id.romanblack_video_playerweb_btn_play));
      this.btnPlayImageView.setOnClickListener(this);
      this.btnNextImageView = ((ImageView)findViewById(R.id.romanblack_video_playerweb_btn_next));
      this.btnNextImageView.setOnClickListener(this);
      this.btnPrevImageView = ((ImageView)findViewById(R.id.romanblack_video_playerweb_btn_prev));
      this.btnPrevImageView.setOnClickListener(this);
      this.surfaceLayout = ((LinearLayout)findViewById(R.id.romanblack_video_playerweb_surfacelayout));
      this.surfaceView = ((SurfaceView)findViewById(R.id.romanblack_video_playerweb_surface));
      this.surfaceHolder = this.surfaceView.getHolder();
      this.surfaceHolder.addCallback(this);
      this.surfaceHolder.setType(3);
      this.surfaceView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
        {
          PlayerWebActivity.this.handler.sendEmptyMessage(8);
          PlayerWebActivity.this.handler.sendEmptyMessage(15);
          PlayerWebActivity.this.handler.sendEmptyMessageDelayed(10, 300L);
          return false;
        }
      });
      this.controlsLayout = ((LinearLayout)findViewById(R.id.romanblack_video_playerweb_controlslayout));
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
            try
            {
              Log.d("DEBUG", "OFFHOOK");
              return;
            }
            catch (NullPointerException localNullPointerException)
            {
              Log.d("", "");
              return;
            }
          case 1:
          }
          if (PlayerWebActivity.this.playerState == PlayerWebActivity.PLAYER_STATES.STATE_PLAY)
          {
            PlayerWebActivity.this.btnPlayImageView.setImageResource(R.drawable.romanblack_video_pause);
            PlayerWebActivity.this.mediaPlayer.pause();
            PlayerWebActivity.access$1002(PlayerWebActivity.this, PlayerWebActivity.PLAYER_STATES.STATE_PAUSE);
          }
          Log.d("DEBUG", "RINGING");
        }
      }
      , 32);
      this.seekBar = ((SeekBar)findViewById(R.id.romanblack_video_playerweb_seekbar));
      this.seekBar.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
        {
          PlayerWebActivity.access$1302(PlayerWebActivity.this, 100);
          PlayerWebActivity.access$1702(PlayerWebActivity.this, true);
          PlayerWebActivity.access$1802(PlayerWebActivity.this, PlayerWebActivity.this.mediaPlayer.getDuration() / 100 * PlayerWebActivity.this.seekBar.getProgress());
          if (paramMotionEvent.getAction() == 1)
          {
            PlayerWebActivity.this.mediaPlayer.seekTo(PlayerWebActivity.this.videoCurrentPos);
            PlayerWebActivity.access$1702(PlayerWebActivity.this, false);
            PlayerWebActivity.access$1302(PlayerWebActivity.this, 3);
          }
          return false;
        }
      });
      this.durationPositiveTextView = ((TextView)findViewById(R.id.romanblack_video_playerweb_duration));
      this.durationNegativeTextView = ((TextView)findViewById(R.id.romanblack_video_playerweb_negative_duration));
      this.mediaPlayer = new MediaPlayer();
      this.mediaPlayer.setAudioStreamType(3);
      this.mediaPlayer.setOnErrorListener(this);
      this.mediaPlayer.setOnPreparedListener(this);
      this.mediaPlayer.setOnCompletionListener(this);
      if (!this.videoItem.getUrl().contains("youtube"))
        break;
      this.handler.sendEmptyMessage(5);
      this.handler.sendEmptyMessage(11);
      return;
      setTopBarTitle("");
    }
    if (this.videoItem.getUrl().contains("vimeo"))
    {
      this.handler.sendEmptyMessage(5);
      this.webView.loadDataWithBaseURL("", prepareVimeoHTML(this.videoItem.getUrl(), 100, 100), "text/html", "utf-8", "");
      return;
    }
    this.linkResolved = true;
    this.playingUrl = this.videoItem.getUrl();
    this.handler.sendEmptyMessage(6);
    playVideo();
  }

  public void destroy()
  {
  }

  public void onBackPressed()
  {
    try
    {
      this.mediaPlayer.stop();
      this.mediaPlayer.release();
      label14: super.onBackPressed();
      return;
    }
    catch (Exception localException)
    {
      break label14;
    }
  }

  public void onClick(View paramView)
  {
    try
    {
      if (paramView == this.homeImageView)
      {
        finish();
        return;
      }
      if (paramView == this.btnPlayImageView)
      {
        if (!this.playIsActive)
          break label378;
        if (this.playerState == PLAYER_STATES.STATE_PLAY)
        {
          this.btnPlayImageView.setImageResource(R.drawable.romanblack_video_play);
          this.mediaPlayer.pause();
          this.playerState = PLAYER_STATES.STATE_PAUSE;
          return;
        }
        PLAYER_STATES localPLAYER_STATES1 = this.playerState;
        PLAYER_STATES localPLAYER_STATES2 = PLAYER_STATES.STATE_PAUSE;
        if (localPLAYER_STATES1 == localPLAYER_STATES2)
          try
          {
            this.btnPlayImageView.setImageResource(R.drawable.romanblack_video_pause);
            this.mediaPlayer.start();
            this.playerState = PLAYER_STATES.STATE_PLAY;
            this.handler.sendEmptyMessage(10);
            this.handler.sendEmptyMessage(9);
            return;
          }
          catch (Exception localException2)
          {
            Log.d("", "");
            return;
          }
        PLAYER_STATES localPLAYER_STATES3 = this.playerState;
        PLAYER_STATES localPLAYER_STATES4 = PLAYER_STATES.STATE_STOP;
        if (localPLAYER_STATES3 != localPLAYER_STATES4)
          break label378;
        try
        {
          this.btnPlayImageView.setImageResource(R.drawable.romanblack_video_pause);
          this.mediaPlayer.start();
          this.playerState = PLAYER_STATES.STATE_PLAY;
          this.handler.sendEmptyMessage(10);
          this.handler.sendEmptyMessage(9);
          return;
        }
        catch (Exception localException1)
        {
          Log.d("", "");
          return;
        }
      }
      if (paramView == this.btnNextImageView)
      {
        if (this.playIsActive)
        {
          int i = this.mediaPlayer.getDuration();
          this.videoCurrentPos = (5000 + this.mediaPlayer.getCurrentPosition());
          if (this.videoCurrentPos > i - 256)
            this.videoCurrentPos = (i - 256);
          this.mediaPlayer.seekTo(this.videoCurrentPos);
          if (this.playerState == PLAYER_STATES.STATE_PAUSE)
          {
            this.handler.sendEmptyMessage(9);
            return;
          }
        }
      }
      else if ((paramView == this.btnPrevImageView) && (this.playIsActive))
      {
        this.videoCurrentPos = (-5000 + this.mediaPlayer.getCurrentPosition());
        if (this.videoCurrentPos < 0)
          this.videoCurrentPos = 0;
        this.mediaPlayer.seekTo(this.videoCurrentPos);
        if (this.playerState == PLAYER_STATES.STATE_PAUSE)
          this.handler.sendEmptyMessage(9);
      }
      label378: return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    this.videoCurrentPos = 0;
    this.playerState = PLAYER_STATES.STATE_STOP;
    this.btnPlayImageView.setImageResource(R.drawable.romanblack_video_play);
    paramMediaPlayer.seekTo(this.videoCurrentPos);
    this.handler.sendEmptyMessage(8);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    prepareSurfaceLayoutParams();
  }

  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    this.handler.sendEmptyMessage(4);
    this.handler.sendEmptyMessage(7);
    return true;
  }

  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    this.handler.sendEmptyMessage(4);
    this.videoWidth = paramMediaPlayer.getVideoWidth();
    this.videoHeight = paramMediaPlayer.getVideoHeight();
    prepareSurfaceLayoutParams();
    this.handler.sendEmptyMessage(9);
    this.playerPrepared = true;
    if (this.surfaceCreated)
    {
      if (this.playerState != PLAYER_STATES.STATE_PLAY)
        break label90;
      this.btnActive = 5;
      this.handler.sendEmptyMessage(10);
    }
    while (true)
    {
      this.playIsActive = true;
      paramMediaPlayer.seekTo(this.videoCurrentPos);
      return;
      label90: Toast.makeText(this, R.string.romanblack_video_alert_press_play, 1).show();
    }
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    this.mediaPlayer.setDisplay(this.surfaceHolder);
    this.surfaceCreated = true;
    if (this.playerPrepared)
    {
      if (this.playerState != PLAYER_STATES.STATE_PLAY)
        break label65;
      this.btnActive = 5;
      this.handler.sendEmptyMessage(10);
    }
    while (true)
    {
      this.playIsActive = true;
      this.mediaPlayer.seekTo(this.videoCurrentPos);
      return;
      label65: Toast.makeText(this, R.string.romanblack_video_alert_press_play, 1).show();
    }
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
  }

  private static enum PLAYER_STATES
  {
    static
    {
      STATE_PAUSE = new PLAYER_STATES("STATE_PAUSE", 2);
      PLAYER_STATES[] arrayOfPLAYER_STATES = new PLAYER_STATES[3];
      arrayOfPLAYER_STATES[0] = STATE_PLAY;
      arrayOfPLAYER_STATES[1] = STATE_STOP;
      arrayOfPLAYER_STATES[2] = STATE_PAUSE;
      $VALUES = arrayOfPLAYER_STATES;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.PlayerWebActivity
 * JD-Core Version:    0.6.0
 */