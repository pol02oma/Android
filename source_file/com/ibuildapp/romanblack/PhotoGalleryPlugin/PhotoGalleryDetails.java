package com.ibuildapp.romanblack.PhotoGalleryPlugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.callback.OnCommentPushedListener;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.Album;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.CommentItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import com.romanblack.android.widget.imagegallery.ImageGallery;
import com.romanblack.android.widget.imagegallery.ImageGallery.OnSlideEndListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PhotoGalleryDetails extends AppBuilderModuleMain
  implements View.OnClickListener, OnCommentPushedListener, ImageGallery.OnSlideEndListener
{
  private final int AUTHORIZATION_ACTIVITY = 10002;
  private final int CHECK_CONTROLS_STATE = 6;
  private final int FACEBOOK_AUTH = 10000;
  private final int HIDE_INFO = 8;
  private final int HIDE_LIKE_BUTTON = 9;
  private final int HIDE_PROGRESS_BAR = 15;
  private final int HIDE_PROGRESS_DIALOG = 1;
  private final int INITIALIZATION_FAILED = 3;
  private final int LOADING_ABORTED = 2;
  private final int PROGRESS_SIZE = 20;
  private final int SAVE_IMAGE = 13;
  private final int SEND_COMMENT_ACTIVITY = 10003;
  private final int SHOW_INFO = 7;
  private final int SHOW_PROGRESS_BAR = 14;
  private final int SHOW_PROGRESS_DIALOG = 0;
  private final int SHOW_SAVE_IMAGE_DIALOG = 12;
  private final int SLIDE_TO_LEFT_START = 4;
  private final int SLIDE_TO_RIGHT_START = 5;
  private final int SLIDING = 11;
  private final int TWITTER_AUTH = 10001;
  private final int UPDATE_LIKE_COUNTER = 10;
  private ACTIONS action = ACTIONS.ACTION_NONE;
  private Intent actionIntent = null;
  private int activeTime = 4;
  private Album album = null;
  private int albumPosition = 0;
  private ArrayList<Album> albums = new ArrayList();
  private LinearLayout bottomPanel = null;
  private String cachePath = "";
  private ImageView commentsImageView = null;
  private LinearLayout controlsLayout = null;
  private LinearLayout descriptionPanel = null;
  private TextView descriptionTextView = null;
  private int displayHeight = 0;
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
        do
        {
          do
          {
            return;
            PhotoGalleryDetails.this.showProgressDialog();
            return;
            PhotoGalleryDetails.this.hideProgressDialog();
            return;
            PhotoGalleryDetails.this.closeActivity();
            return;
            Toast.makeText(PhotoGalleryDetails.this, PhotoGalleryDetails.this.getResources().getString(R.string.romanblack_photogallery_alert_cannot_init), 1).show();
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                PhotoGalleryDetails.this.finish();
              }
            }
            , 2000L);
            return;
          }
          while (PhotoGalleryDetails.this.position >= -1 + PhotoGalleryDetails.this.items.size());
          PhotoGalleryDetails.access$308(PhotoGalleryDetails.this);
          PhotoGalleryDetails.this.slideImage(-500);
          return;
        }
        while (PhotoGalleryDetails.this.position <= 0);
        PhotoGalleryDetails.access$310(PhotoGalleryDetails.this);
        PhotoGalleryDetails.this.slideImage(500);
        return;
      case 8:
        PhotoGalleryDetails.this.hideInfo();
        return;
      case 7:
        PhotoGalleryDetails.this.showInfo();
        return;
      case 6:
        PhotoGalleryDetails.this.checkControlsState();
        return;
      case 9:
        PhotoGalleryDetails.this.hideLikeButton();
        return;
      case 10:
        PhotoGalleryDetails.this.updateLikeCounter();
        return;
      case 11:
        Log.d("ROMAN", System.currentTimeMillis() + "");
        if (PhotoGalleryDetails.this.position < -1 + PhotoGalleryDetails.this.items.size())
        {
          PhotoGalleryDetails.this.imageGallery.slideRight();
          return;
        }
        PhotoGalleryDetails.access$1202(PhotoGalleryDetails.this, false);
        PhotoGalleryDetails.this.slideShowButton.setImageResource(R.drawable.romanblack_photogallery_play);
        return;
      case 12:
        PhotoGalleryDetails.this.showSaveImageDialog();
        return;
      case 13:
        PhotoGalleryDetails.this.saveImage();
        return;
      case 14:
        PhotoGalleryDetails.this.progressBar.setVisibility(0);
        return;
      case 15:
      }
      PhotoGalleryDetails.this.progressBar.setVisibility(4);
    }
  };
  private ImageGallery imageGallery = null;
  private ImageView infoButton = null;
  private ArrayList<ImageItem> items = null;
  private LinearLayout layoutDescription = null;
  private ImageView likeButton = null;
  private LinearLayout mainLayout = null;
  private DisplayMetrics metrix = new DisplayMetrics();
  private boolean needMenu = false;
  private int position = 0;
  private ProgressBar progressBar = null;
  private ProgressDialog progressDialog = null;
  private ImageView shareButton = null;
  private ImageView slideShowButton = null;
  private boolean sliding = false;
  private boolean slidingPaused = false;
  private TextView titleTextView = null;

  private void checkControlsState()
  {
    if (this.activeTime > 0)
    {
      this.activeTime = (-1 + this.activeTime);
      this.handler.sendEmptyMessageDelayed(6, 1000L);
      return;
    }
    this.handler.sendEmptyMessageDelayed(8, 1000L);
  }

  private void closeActivity()
  {
    hideProgressDialog();
    finish();
  }

  private void hideInfo()
  {
    this.descriptionPanel.setVisibility(4);
    this.controlsLayout.setVisibility(4);
    invisibleTopBar();
    if ((this.sliding) && (this.slidingPaused))
    {
      this.slidingPaused = false;
      this.handler.sendEmptyMessage(11);
    }
  }

  private void hideLikeButton()
  {
    this.likeButton.setAlpha(100);
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void like()
  {
    if ((Authorization.getAuthorizedUser(1) != null) && (!((ImageItem)this.items.get(this.position)).isLiked()))
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            PhotoGalleryDetails.this.handler.sendEmptyMessage(14);
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL("https://graph.facebook.com/me/og.likes").openConnection();
            localHttpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7");
            localHttpURLConnection.setRequestMethod("POST");
            localHttpURLConnection.setDoOutput(true);
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("method=");
            localStringBuilder1.append("POST");
            localStringBuilder1.append("&");
            localStringBuilder1.append("access_token=");
            localStringBuilder1.append(Authorization.getAuthorizedUser(1).getAccessToken());
            localStringBuilder1.append("&");
            localStringBuilder1.append("object=");
            localStringBuilder1.append(((ImageItem)PhotoGalleryDetails.this.items.get(PhotoGalleryDetails.this.position)).getPermalinkUrl());
            String str1 = localStringBuilder1.toString();
            localHttpURLConnection.getOutputStream().write(str1.getBytes("UTF-8"));
            try
            {
              InputStream localInputStream2 = localHttpURLConnection.getInputStream();
              StringBuilder localStringBuilder3 = new StringBuilder();
              BufferedReader localBufferedReader2 = new BufferedReader(new InputStreamReader(localInputStream2), 1000);
              for (String str3 = localBufferedReader2.readLine(); str3 != null; str3 = localBufferedReader2.readLine())
                localStringBuilder3.append(str3);
              localInputStream2.close();
              localStringBuilder3.toString();
              PhotoGalleryDetails.this.handler.sendEmptyMessage(9);
              PhotoGalleryDetails.this.handler.sendEmptyMessage(10);
              label277: PhotoGalleryDetails.this.handler.sendEmptyMessage(15);
              return;
            }
            catch (FileNotFoundException localFileNotFoundException)
            {
              while (true)
              {
                InputStream localInputStream1 = localHttpURLConnection.getErrorStream();
                StringBuilder localStringBuilder2 = new StringBuilder();
                BufferedReader localBufferedReader1 = new BufferedReader(new InputStreamReader(localInputStream1), 1000);
                for (String str2 = localBufferedReader1.readLine(); str2 != null; str2 = localBufferedReader1.readLine())
                  localStringBuilder2.append(str2);
                localInputStream1.close();
                localStringBuilder2.toString();
              }
            }
          }
          catch (Exception localException)
          {
            break label277;
          }
          catch (IOException localIOException)
          {
            break label277;
          }
          catch (MalformedURLException localMalformedURLException)
          {
            break label277;
          }
        }
      }).start();
  }

  private void saveImage()
  {
    if (((ImageItem)this.items.get(this.position)).getImagePath().length() == 0);
    String str;
    File localFile1;
    do
    {
      return;
      str = Environment.getExternalStorageDirectory() + "/images/" + Statics.APP_NAME;
      localFile1 = new File(str);
      if (localFile1.exists())
        continue;
      localFile1.mkdirs();
    }
    while (!localFile1.exists());
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.ENGLISH);
    File localFile2 = new File(((ImageItem)this.items.get(this.position)).getImagePath());
    File localFile3 = new File(str + "/image " + localSimpleDateFormat.format(new Date()) + ".png");
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeStream(new FileInputStream(localFile2), null, null);
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localFile3));
      localBitmap.compress(Bitmap.CompressFormat.PNG, 0, localBufferedOutputStream);
      localBufferedOutputStream.flush();
      localBufferedOutputStream.close();
      return;
    }
    catch (Exception localException)
    {
      Log.w("ImageDetails -->", "Error copying image " + localException);
      return;
    }
    finally
    {
    }
    throw localObject;
  }

  private void shareFacebook()
  {
    String str = ((ImageItem)this.items.get(this.position)).getPermalinkUrl();
    Intent localIntent = new Intent(this, SharingActivity.class);
    localIntent.putExtra("item", (Serializable)this.items.get(this.position));
    localIntent.putExtra("type", "facebook");
    localIntent.putExtra("link", str);
    startActivity(localIntent);
  }

  private void shareTwitter()
  {
    String str = ((ImageItem)this.items.get(this.position)).getPermalinkUrl();
    Intent localIntent = new Intent(this, SharingActivity.class);
    localIntent.putExtra("item", (Serializable)this.items.get(this.position));
    localIntent.putExtra("type", "twitter");
    localIntent.putExtra("link", str);
    startActivity(localIntent);
  }

  private void showImage()
  {
    try
    {
      hideInfo();
      setTitle(1 + this.position + " " + getString(R.string.romanblack_photogallery_from) + " " + this.items.size());
      this.titleTextView.setText(((ImageItem)this.items.get(this.position)).getTitle());
      Document localDocument = Jsoup.parse(((ImageItem)this.items.get(this.position)).getDescription());
      this.descriptionTextView.setText(localDocument.text());
      showInfo();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void showInfo()
  {
    try
    {
      this.controlsLayout.setVisibility(0);
      visibleTopBar();
      this.bottomPanel.setVisibility(0);
      return;
    }
    catch (Exception localException)
    {
    }
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
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_photogallery_loading), true);
      this.progressDialog.setCancelable(true);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          PhotoGalleryDetails.this.handler.sendEmptyMessage(2);
        }
      });
    }
  }

  private void showSaveImageDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(getString(R.string.romanblack_photogallery_dialog_sure_save_image));
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton(getString(R.string.romanblack_photogallery_yes), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        PhotoGalleryDetails.this.handler.sendEmptyMessage(13);
      }
    }).setNegativeButton(getString(R.string.romanblack_photogallery_no), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.cancel();
      }
    });
    localBuilder.create().show();
  }

  private void slideImage(int paramInt)
  {
    try
    {
      hideInfo();
      setTitle(1 + this.position + " " + getString(R.string.romanblack_photogallery_from) + " " + this.items.size());
      this.titleTextView.setText(((ImageItem)this.items.get(this.position)).getTitle());
      Document localDocument = Jsoup.parse(((ImageItem)this.items.get(this.position)).getDescription());
      this.descriptionTextView.setText(localDocument.text());
      setTopBarTitle(1 + this.position + " " + getResources().getString(R.string.romanblack_photogallery_of) + " " + this.items.size());
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void updateLikeCounter()
  {
    ((ImageItem)this.items.get(this.position)).setLikesCount(1 + ((ImageItem)this.items.get(this.position)).getLikesCount());
    Toast.makeText(this, R.string.romanblack_photogallery_alert_liked, 1).show();
  }

  // ERROR //
  public void create()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: invokevirtual 635	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:requestWindowFeature	(I)Z
    //   5: pop
    //   6: aload_0
    //   7: getstatic 640	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$layout:romanblack_photogallery_details	I
    //   10: invokevirtual 643	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:setContentView	(I)V
    //   13: aload_0
    //   14: invokevirtual 646	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:disableSwipe	()V
    //   17: aload_0
    //   18: aload_0
    //   19: invokevirtual 605	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:getResources	()Landroid/content/res/Resources;
    //   22: getstatic 649	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:back	I
    //   25: invokevirtual 611	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   28: iconst_1
    //   29: new 651	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails$2
    //   32: dup
    //   33: aload_0
    //   34: invokespecial 652	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails$2:<init>	(Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails;)V
    //   37: invokevirtual 656	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:setTopBarLeftButtonText	(Ljava/lang/String;ZLandroid/view/View$OnClickListener;)V
    //   40: aload_0
    //   41: new 658	android/widget/ProgressBar
    //   44: dup
    //   45: aload_0
    //   46: invokespecial 659	android/widget/ProgressBar:<init>	(Landroid/content/Context;)V
    //   49: putfield 159	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:progressBar	Landroid/widget/ProgressBar;
    //   52: aload_0
    //   53: getfield 159	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:progressBar	Landroid/widget/ProgressBar;
    //   56: iconst_4
    //   57: invokevirtual 660	android/widget/ProgressBar:setVisibility	(I)V
    //   60: aload_0
    //   61: invokevirtual 605	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:getResources	()Landroid/content/res/Resources;
    //   64: invokevirtual 664	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   67: getfield 668	android/util/DisplayMetrics:density	F
    //   70: fstore 5
    //   72: new 670	android/view/ViewGroup$LayoutParams
    //   75: dup
    //   76: ldc_w 671
    //   79: fload 5
    //   81: fmul
    //   82: f2i
    //   83: ldc_w 671
    //   86: fload 5
    //   88: fmul
    //   89: f2i
    //   90: invokespecial 674	android/view/ViewGroup$LayoutParams:<init>	(II)V
    //   93: astore 6
    //   95: aload_0
    //   96: getfield 159	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:progressBar	Landroid/widget/ProgressBar;
    //   99: aload 6
    //   101: invokevirtual 678	android/widget/ProgressBar:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   104: aload_0
    //   105: aload_0
    //   106: getfield 159	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:progressBar	Landroid/widget/ProgressBar;
    //   109: invokevirtual 682	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:drawTopBarRightButton	(Landroid/view/View;)V
    //   112: aload_0
    //   113: aload_0
    //   114: invokevirtual 686	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:getWindowManager	()Landroid/view/WindowManager;
    //   117: invokeinterface 692 1 0
    //   122: invokevirtual 697	android/view/Display:getHeight	()I
    //   125: putfield 143	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:displayHeight	I
    //   128: aload_0
    //   129: invokevirtual 686	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:getWindowManager	()Landroid/view/WindowManager;
    //   132: invokeinterface 692 1 0
    //   137: aload_0
    //   138: getfield 188	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:metrix	Landroid/util/DisplayMetrics;
    //   141: invokevirtual 701	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   144: aload_0
    //   145: invokevirtual 704	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:hasAdView	()Z
    //   148: ifeq +27 -> 175
    //   151: aload_0
    //   152: invokevirtual 707	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:getAdvType	()Ljava/lang/String;
    //   155: ldc_w 709
    //   158: invokevirtual 713	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   161: ifeq +101 -> 262
    //   164: aload_0
    //   165: bipush 206
    //   167: aload_0
    //   168: getfield 143	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:displayHeight	I
    //   171: iadd
    //   172: putfield 143	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:displayHeight	I
    //   175: aload_0
    //   176: invokevirtual 717	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:getIntent	()Landroid/content/Intent;
    //   179: invokevirtual 721	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   182: astore 7
    //   184: aload_0
    //   185: aload 7
    //   187: ldc_w 722
    //   190: invokevirtual 728	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   193: checkcast 192	java/util/ArrayList
    //   196: putfield 195	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:albums	Ljava/util/ArrayList;
    //   199: aload_0
    //   200: aload 7
    //   202: ldc_w 729
    //   205: iconst_0
    //   206: invokevirtual 733	android/os/Bundle:getInt	(Ljava/lang/String;I)I
    //   209: putfield 141	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:albumPosition	I
    //   212: aload_0
    //   213: aload_0
    //   214: getfield 195	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:albums	Ljava/util/ArrayList;
    //   217: aload_0
    //   218: getfield 141	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:albumPosition	I
    //   221: invokevirtual 319	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   224: checkcast 735	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album
    //   227: putfield 151	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:album	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album;
    //   230: aload_0
    //   231: aload 7
    //   233: ldc_w 736
    //   236: invokevirtual 728	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   239: checkcast 192	java/util/ArrayList
    //   242: putfield 190	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:items	Ljava/util/ArrayList;
    //   245: aload_0
    //   246: getfield 190	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:items	Ljava/util/ArrayList;
    //   249: ifnonnull +61 -> 310
    //   252: aload_0
    //   253: getfield 202	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:handler	Landroid/os/Handler;
    //   256: iconst_3
    //   257: invokevirtual 298	android/os/Handler:sendEmptyMessage	(I)Z
    //   260: pop
    //   261: return
    //   262: aload_0
    //   263: aload_0
    //   264: getfield 143	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:displayHeight	I
    //   267: ldc_w 737
    //   270: aload_0
    //   271: getfield 188	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:metrix	Landroid/util/DisplayMetrics;
    //   274: getfield 668	android/util/DisplayMetrics:density	F
    //   277: fmul
    //   278: f2i
    //   279: isub
    //   280: putfield 143	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:displayHeight	I
    //   283: goto -108 -> 175
    //   286: astore_2
    //   287: ldc 147
    //   289: ldc 147
    //   291: invokestatic 740	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   294: pop
    //   295: return
    //   296: astore 10
    //   298: aload_0
    //   299: getfield 202	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:handler	Landroid/os/Handler;
    //   302: iconst_3
    //   303: invokevirtual 298	android/os/Handler:sendEmptyMessage	(I)Z
    //   306: pop
    //   307: goto -62 -> 245
    //   310: aload_0
    //   311: getfield 190	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:items	Ljava/util/ArrayList;
    //   314: invokevirtual 743	java/util/ArrayList:isEmpty	()Z
    //   317: ifeq +13 -> 330
    //   320: aload_0
    //   321: getfield 202	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:handler	Landroid/os/Handler;
    //   324: iconst_3
    //   325: invokevirtual 298	android/os/Handler:sendEmptyMessage	(I)Z
    //   328: pop
    //   329: return
    //   330: aload_0
    //   331: aload 7
    //   333: ldc_w 744
    //   336: iconst_0
    //   337: invokevirtual 733	android/os/Bundle:getInt	(Ljava/lang/String;I)I
    //   340: putfield 139	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:position	I
    //   343: aload_0
    //   344: aload 7
    //   346: ldc_w 745
    //   349: invokevirtual 748	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   352: putfield 149	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:cachePath	Ljava/lang/String;
    //   355: new 376	java/io/File
    //   358: dup
    //   359: aload_0
    //   360: getfield 149	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:cachePath	Ljava/lang/String;
    //   363: invokespecial 379	java/io/File:<init>	(Ljava/lang/String;)V
    //   366: astore 12
    //   368: aload 12
    //   370: invokevirtual 382	java/io/File:exists	()Z
    //   373: ifne +9 -> 382
    //   376: aload 12
    //   378: invokevirtual 385	java/io/File:mkdirs	()Z
    //   381: pop
    //   382: aload_0
    //   383: aload_0
    //   384: getstatic 753	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_main_layout	I
    //   387: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   390: checkcast 288	android/widget/LinearLayout
    //   393: putfield 155	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:mainLayout	Landroid/widget/LinearLayout;
    //   396: aload_0
    //   397: getfield 155	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:mainLayout	Landroid/widget/LinearLayout;
    //   400: ldc_w 759
    //   403: invokestatic 765	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   406: invokevirtual 768	android/widget/LinearLayout:setBackgroundColor	(I)V
    //   409: aload_0
    //   410: aload_0
    //   411: getstatic 771	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_share	I
    //   414: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   417: checkcast 300	android/widget/ImageView
    //   420: putfield 161	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:shareButton	Landroid/widget/ImageView;
    //   423: aload_0
    //   424: getfield 161	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:shareButton	Landroid/widget/ImageView;
    //   427: aload_0
    //   428: invokevirtual 775	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   431: aload_0
    //   432: new 350	java/lang/StringBuilder
    //   435: dup
    //   436: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   439: iconst_1
    //   440: aload_0
    //   441: getfield 139	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:position	I
    //   444: iadd
    //   445: invokevirtual 493	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   448: ldc_w 495
    //   451: invokevirtual 366	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: aload_0
    //   455: invokevirtual 605	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:getResources	()Landroid/content/res/Resources;
    //   458: getstatic 608	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_of	I
    //   461: invokevirtual 611	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   464: invokevirtual 366	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   467: ldc_w 495
    //   470: invokevirtual 366	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: aload_0
    //   474: getfield 190	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:items	Ljava/util/ArrayList;
    //   477: invokevirtual 507	java/util/ArrayList:size	()I
    //   480: invokevirtual 493	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   483: invokevirtual 374	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   486: invokevirtual 614	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:setTopBarTitle	(Ljava/lang/String;)V
    //   489: aload_0
    //   490: aload_0
    //   491: getstatic 778	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_title	I
    //   494: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   497: checkcast 516	android/widget/TextView
    //   500: putfield 167	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:titleTextView	Landroid/widget/TextView;
    //   503: aload_0
    //   504: getfield 167	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:titleTextView	Landroid/widget/TextView;
    //   507: aload_0
    //   508: getfield 190	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:items	Ljava/util/ArrayList;
    //   511: aload_0
    //   512: getfield 139	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:position	I
    //   515: invokevirtual 319	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   518: checkcast 321	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem
    //   521: invokevirtual 514	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getTitle	()Ljava/lang/String;
    //   524: invokevirtual 519	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   527: aload_0
    //   528: aload_0
    //   529: getstatic 781	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_description	I
    //   532: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   535: checkcast 516	android/widget/TextView
    //   538: putfield 169	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:descriptionTextView	Landroid/widget/TextView;
    //   541: aload_0
    //   542: getfield 169	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:descriptionTextView	Landroid/widget/TextView;
    //   545: new 783	android/text/method/ScrollingMovementMethod
    //   548: dup
    //   549: invokespecial 784	android/text/method/ScrollingMovementMethod:<init>	()V
    //   552: invokevirtual 788	android/widget/TextView:setMovementMethod	(Landroid/text/method/MovementMethod;)V
    //   555: aload_0
    //   556: getfield 190	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:items	Ljava/util/ArrayList;
    //   559: aload_0
    //   560: getfield 139	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:position	I
    //   563: invokevirtual 319	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   566: checkcast 321	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem
    //   569: invokevirtual 522	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getDescription	()Ljava/lang/String;
    //   572: invokestatic 528	org/jsoup/Jsoup:parse	(Ljava/lang/String;)Lorg/jsoup/nodes/Document;
    //   575: astore 13
    //   577: aload_0
    //   578: getfield 169	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:descriptionTextView	Landroid/widget/TextView;
    //   581: aload 13
    //   583: invokevirtual 533	org/jsoup/nodes/Document:text	()Ljava/lang/String;
    //   586: invokevirtual 519	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   589: aload_0
    //   590: aload_0
    //   591: getstatic 791	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_bottom_panel	I
    //   594: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   597: checkcast 288	android/widget/LinearLayout
    //   600: putfield 173	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:bottomPanel	Landroid/widget/LinearLayout;
    //   603: aload_0
    //   604: aload_0
    //   605: getstatic 794	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_info	I
    //   608: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   611: checkcast 300	android/widget/ImageView
    //   614: putfield 175	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:infoButton	Landroid/widget/ImageView;
    //   617: aload_0
    //   618: getfield 175	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:infoButton	Landroid/widget/ImageView;
    //   621: aload_0
    //   622: invokevirtual 775	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   625: aload_0
    //   626: aload_0
    //   627: getstatic 797	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_play	I
    //   630: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   633: checkcast 300	android/widget/ImageView
    //   636: putfield 163	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:slideShowButton	Landroid/widget/ImageView;
    //   639: aload_0
    //   640: getfield 163	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:slideShowButton	Landroid/widget/ImageView;
    //   643: aload_0
    //   644: invokevirtual 775	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   647: aload_0
    //   648: aload_0
    //   649: getstatic 800	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_description_panel	I
    //   652: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   655: checkcast 288	android/widget/LinearLayout
    //   658: putfield 177	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:descriptionPanel	Landroid/widget/LinearLayout;
    //   661: aload_0
    //   662: getfield 177	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:descriptionPanel	Landroid/widget/LinearLayout;
    //   665: iconst_4
    //   666: invokevirtual 291	android/widget/LinearLayout:setVisibility	(I)V
    //   669: aload_0
    //   670: aload_0
    //   671: getstatic 803	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_like	I
    //   674: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   677: checkcast 300	android/widget/ImageView
    //   680: putfield 179	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:likeButton	Landroid/widget/ImageView;
    //   683: aload_0
    //   684: getfield 179	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:likeButton	Landroid/widget/ImageView;
    //   687: aload_0
    //   688: invokevirtual 775	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   691: aload_0
    //   692: aload_0
    //   693: getstatic 806	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_comments	I
    //   696: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   699: checkcast 300	android/widget/ImageView
    //   702: putfield 165	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:commentsImageView	Landroid/widget/ImageView;
    //   705: aload_0
    //   706: getfield 165	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:commentsImageView	Landroid/widget/ImageView;
    //   709: aload_0
    //   710: invokevirtual 775	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   713: aload_0
    //   714: getfield 151	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:album	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album;
    //   717: ifnull +21 -> 738
    //   720: aload_0
    //   721: getfield 151	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:album	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album;
    //   724: invokevirtual 809	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/Album:isRSS	()Z
    //   727: ifeq +11 -> 738
    //   730: aload_0
    //   731: getfield 165	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:commentsImageView	Landroid/widget/ImageView;
    //   734: iconst_4
    //   735: invokevirtual 810	android/widget/ImageView:setVisibility	(I)V
    //   738: aload_0
    //   739: aload_0
    //   740: getstatic 813	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_controls_layout	I
    //   743: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   746: checkcast 288	android/widget/LinearLayout
    //   749: putfield 157	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:controlsLayout	Landroid/widget/LinearLayout;
    //   752: aload_0
    //   753: aload_0
    //   754: getstatic 816	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_details_imagegallery	I
    //   757: invokevirtual 757	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:findViewById	(I)Landroid/view/View;
    //   760: checkcast 818	com/romanblack/android/widget/imagegallery/ImageGallery
    //   763: putfield 183	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:imageGallery	Lcom/romanblack/android/widget/imagegallery/ImageGallery;
    //   766: aload_0
    //   767: invokevirtual 704	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:hasAdView	()Z
    //   770: ifeq +12 -> 782
    //   773: aload_0
    //   774: getfield 183	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:imageGallery	Lcom/romanblack/android/widget/imagegallery/ImageGallery;
    //   777: bipush 206
    //   779: invokevirtual 821	com/romanblack/android/widget/imagegallery/ImageGallery:setPadding	(I)V
    //   782: new 192	java/util/ArrayList
    //   785: dup
    //   786: invokespecial 193	java/util/ArrayList:<init>	()V
    //   789: astore 14
    //   791: iconst_0
    //   792: istore 15
    //   794: iload 15
    //   796: aload_0
    //   797: getfield 190	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:items	Ljava/util/ArrayList;
    //   800: invokevirtual 507	java/util/ArrayList:size	()I
    //   803: if_icmpge +30 -> 833
    //   806: aload 14
    //   808: aload_0
    //   809: getfield 190	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:items	Ljava/util/ArrayList;
    //   812: iload 15
    //   814: invokevirtual 319	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   817: checkcast 321	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem
    //   820: invokevirtual 342	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImagePath	()Ljava/lang/String;
    //   823: invokevirtual 825	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   826: pop
    //   827: iinc 15 1
    //   830: goto -36 -> 794
    //   833: aload_0
    //   834: getfield 183	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:imageGallery	Lcom/romanblack/android/widget/imagegallery/ImageGallery;
    //   837: aload_0
    //   838: getfield 139	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:position	I
    //   841: invokevirtual 828	com/romanblack/android/widget/imagegallery/ImageGallery:setPosition	(I)V
    //   844: aload_0
    //   845: getfield 183	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:imageGallery	Lcom/romanblack/android/widget/imagegallery/ImageGallery;
    //   848: aload 14
    //   850: invokevirtual 832	com/romanblack/android/widget/imagegallery/ImageGallery:setImagePaths	(Ljava/util/ArrayList;)V
    //   853: aload_0
    //   854: getfield 183	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:imageGallery	Lcom/romanblack/android/widget/imagegallery/ImageGallery;
    //   857: aload_0
    //   858: invokevirtual 833	com/romanblack/android/widget/imagegallery/ImageGallery:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   861: aload_0
    //   862: getfield 183	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:imageGallery	Lcom/romanblack/android/widget/imagegallery/ImageGallery;
    //   865: aload_0
    //   866: invokevirtual 837	com/romanblack/android/widget/imagegallery/ImageGallery:setOnSlideEndListener	(Lcom/romanblack/android/widget/imagegallery/ImageGallery$OnSlideEndListener;)V
    //   869: aload_0
    //   870: invokespecial 839	com/ibuildapp/romanblack/PhotoGalleryPlugin/PhotoGalleryDetails:showImage	()V
    //   873: getstatic 842	com/ibuildapp/romanblack/PhotoGalleryPlugin/Statics:onCommentPushedListeners	Ljava/util/ArrayList;
    //   876: aload_0
    //   877: invokevirtual 825	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   880: pop
    //   881: return
    //   882: astore 9
    //   884: goto -654 -> 230
    //   887: astore 8
    //   889: goto -690 -> 199
    //   892: astore_1
    //   893: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	175	286	java/lang/Exception
    //   175	184	286	java/lang/Exception
    //   245	261	286	java/lang/Exception
    //   262	283	286	java/lang/Exception
    //   298	307	286	java/lang/Exception
    //   310	329	286	java/lang/Exception
    //   330	382	286	java/lang/Exception
    //   382	738	286	java/lang/Exception
    //   738	782	286	java/lang/Exception
    //   782	791	286	java/lang/Exception
    //   794	827	286	java/lang/Exception
    //   833	881	286	java/lang/Exception
    //   230	245	296	java/lang/Exception
    //   199	230	882	java/lang/Exception
    //   184	199	887	java/lang/Exception
    //   0	175	892	java/lang/Error
    //   175	184	892	java/lang/Error
    //   184	199	892	java/lang/Error
    //   199	230	892	java/lang/Error
    //   230	245	892	java/lang/Error
    //   245	261	892	java/lang/Error
    //   262	283	892	java/lang/Error
    //   298	307	892	java/lang/Error
    //   310	329	892	java/lang/Error
    //   330	382	892	java/lang/Error
    //   382	738	892	java/lang/Error
    //   738	782	892	java/lang/Error
    //   782	791	892	java/lang/Error
    //   794	827	892	java/lang/Error
    //   833	881	892	java/lang/Error
  }

  public void destroy()
  {
    Statics.onCommentPushedListeners.remove(this);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 10000)
      if ((paramInt2 == -1) && (Authorization.getAuthorizedUser(1) != null))
      {
        if (this.action != ACTIONS.FACEBOOK_LIKE)
          break label41;
        like();
      }
    label40: label41: 
    do
      while (true)
      {
        break label40;
        do
          return;
        while (this.action != ACTIONS.FACEBOOK_SHARE);
        shareFacebook();
        return;
        if (paramInt1 == 10001)
        {
          if ((paramInt2 != -1) || (Authorization.getAuthorizedUser(2) == null))
            continue;
          shareTwitter();
          return;
        }
        if (paramInt1 != 10002)
          break;
        if ((paramInt2 != -1) || (this.action != ACTIONS.SEND_MESSAGE))
          continue;
        startActivityForResult(this.actionIntent, 10003);
        return;
      }
    while ((paramInt1 != 10003) || (paramInt2 != -1));
  }

  public void onAdClosed()
  {
    this.imageGallery.setPadding(0);
  }

  public void onClick(View paramView)
  {
    if (paramView == this.shareButton)
    {
      this.needMenu = true;
      openOptionsMenu();
    }
    do
    {
      do
        while (true)
        {
          return;
          if (paramView == this.infoButton)
          {
            if (this.descriptionPanel.getVisibility() == 0)
            {
              this.descriptionPanel.setVisibility(4);
              return;
            }
            this.descriptionPanel.setVisibility(0);
            return;
          }
          if (paramView == this.likeButton)
          {
            NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
            boolean bool = false;
            if (localNetworkInfo != null)
              bool = localNetworkInfo.isConnectedOrConnecting();
            if (bool)
            {
              if (Authorization.getAuthorizedUser(1) != null)
              {
                like();
                return;
              }
              this.action = ACTIONS.FACEBOOK_LIKE;
              Authorization.authorize(this, 10000, 1);
              return;
            }
            Toast.makeText(this, getResources().getString(R.string.romanblack_photogallery_alert_like_need_internet), 1).show();
            return;
          }
          if (paramView == this.commentsImageView)
          {
            Intent localIntent = new Intent(this, CommentsActivity.class);
            localIntent.putExtra("items", this.items);
            localIntent.putExtra("position", this.position);
            localIntent.putExtra("cachePath", this.cachePath);
            startActivity(localIntent);
            return;
          }
          if (paramView == this.slideShowButton)
          {
            if (this.sliding)
            {
              this.handler.removeMessages(11);
              this.sliding = false;
              this.slideShowButton.setImageResource(R.drawable.romanblack_photogallery_play);
              return;
            }
            this.controlsLayout.setVisibility(4);
            this.descriptionPanel.setVisibility(4);
            invisibleTopBar();
            this.handler.sendEmptyMessageDelayed(11, 3000L);
            this.sliding = true;
            this.slideShowButton.setImageResource(R.drawable.romanblack_photogallery_pause);
            return;
          }
          if (paramView != this.imageGallery)
            continue;
          if ((this.controlsLayout.getVisibility() != 8) && (this.controlsLayout.getVisibility() != 4))
            break;
          this.controlsLayout.setVisibility(0);
          visibleTopBar();
          if (!this.sliding)
            continue;
          this.handler.removeMessages(11);
          this.slidingPaused = false;
          this.sliding = false;
          this.slideShowButton.setImageResource(R.drawable.romanblack_photogallery_play);
          return;
        }
      while (this.controlsLayout.getVisibility() != 0);
      this.controlsLayout.setVisibility(4);
      this.descriptionPanel.setVisibility(4);
      invisibleTopBar();
    }
    while (!this.sliding);
    this.handler.sendEmptyMessage(11);
    this.slidingPaused = false;
  }

  public void onCommentPushed(CommentItem paramCommentItem)
  {
    if (paramCommentItem.getReplyId() != 0L);
    do
      return;
    while (paramCommentItem.getTrackId() != ((ImageItem)this.items.get(this.position)).getId());
    ((ImageItem)this.items.get(this.position)).setTotalComments(1 + ((ImageItem)this.items.get(this.position)).getTotalComments());
  }

  public void onCommentsUpdate(ImageItem paramImageItem, CommentItem paramCommentItem, int paramInt1, int paramInt2, ArrayList<CommentItem> paramArrayList)
  {
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.displayHeight = getWindowManager().getDefaultDisplay().getHeight();
    showImage();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.add("Facebook").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        if (Authorization.getAuthorizedUser(1) != null)
          PhotoGalleryDetails.this.shareFacebook();
        while (true)
        {
          PhotoGalleryDetails.access$1902(PhotoGalleryDetails.this, false);
          return true;
          PhotoGalleryDetails.access$1802(PhotoGalleryDetails.this, PhotoGalleryDetails.ACTIONS.FACEBOOK_SHARE);
          Authorization.authorize(PhotoGalleryDetails.this, 10000, 1);
        }
      }
    });
    paramMenu.add("Twitter").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        if (Authorization.getAuthorizedUser(2) != null)
          PhotoGalleryDetails.this.shareTwitter();
        while (true)
        {
          PhotoGalleryDetails.access$1902(PhotoGalleryDetails.this, false);
          return true;
          Authorization.authorize(PhotoGalleryDetails.this, 10001, 2);
        }
      }
    });
    paramMenu.add("Email").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        String str = ((ImageItem)PhotoGalleryDetails.this.items.get(PhotoGalleryDetails.this.position)).getImageUrl();
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/html");
        localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml(str));
        PhotoGalleryDetails.this.startActivity(localIntent);
        PhotoGalleryDetails.access$1902(PhotoGalleryDetails.this, false);
        return true;
      }
    });
    paramMenu.add("SMS").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        String str = ((ImageItem)PhotoGalleryDetails.this.items.get(PhotoGalleryDetails.this.position)).getImageUrl();
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:"));
        localIntent.putExtra("sms_body", str);
        PhotoGalleryDetails.this.startActivity(localIntent);
        PhotoGalleryDetails.access$1902(PhotoGalleryDetails.this, false);
        return true;
      }
    });
    paramMenu.add(getString(R.string.romanblack_photogallery_save)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        PhotoGalleryDetails.this.handler.sendEmptyMessage(12);
        return true;
      }
    });
    paramMenu.add(getString(R.string.romanblack_photogallery_cancel)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        PhotoGalleryDetails.access$1902(PhotoGalleryDetails.this, false);
        return true;
      }
    });
    return true;
  }

  public void onOptionsMenuClosed(Menu paramMenu)
  {
    this.needMenu = false;
  }

  public void onPost()
  {
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return this.needMenu;
  }

  public void onSlideEnd(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0)
      this.handler.sendEmptyMessage(5);
    while (true)
    {
      if (this.sliding)
        this.handler.sendEmptyMessageDelayed(11, 3000L);
      return;
      if (paramInt1 != 1)
        continue;
      this.handler.sendEmptyMessage(4);
    }
  }

  private static enum ACTIONS
  {
    static
    {
      ACTION_NONE = new ACTIONS("ACTION_NONE", 2);
      SEND_MESSAGE = new ACTIONS("SEND_MESSAGE", 3);
      ACTIONS[] arrayOfACTIONS = new ACTIONS[4];
      arrayOfACTIONS[0] = FACEBOOK_LIKE;
      arrayOfACTIONS[1] = FACEBOOK_SHARE;
      arrayOfACTIONS[2] = ACTION_NONE;
      arrayOfACTIONS[3] = SEND_MESSAGE;
      $VALUES = arrayOfACTIONS;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.PhotoGalleryDetails
 * JD-Core Version:    0.6.0
 */