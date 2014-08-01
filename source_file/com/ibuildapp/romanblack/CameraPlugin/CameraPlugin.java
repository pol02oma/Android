package com.ibuildapp.romanblack.CameraPlugin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.statistics.StatisticsCollector;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CameraPlugin extends AppBuilderModuleMain
{
  private final int CAMERA_HARDWARE_ERROR = 8;
  private final int CHECK_CONTROLS_STATE = 3;
  private final int EMAIL_SEND = 1889;
  private final int FACEBOOK_AUTHORIZATION_ACTIVITY = 10000;
  private final int FACEBOOK_PUBLISH_ACTIVITY = 10002;
  private final int HAVE_NO_CAMERA = 6;
  private final int HIDE_BUTTON = 5;
  private final int INITIALIZATION_FAILED = 0;
  private final int LOAD_IMAGE_SHARING = 7;
  private final int NEED_INTERNET_CONNECTION = 1;
  private final int NO_IMAGE = 2;
  private final int SHOW_BUTTON = 4;
  private final String TAG = "com.ibuildapp.CameraPlugin";
  private final int TURN_BUTTON_COLOR_BACK = 90;
  private final int TWITTER_AUTHORIZATION_ACTIVITY = 10001;
  private final int TWITTER_PUBLISH_ACTIVITY = 10003;
  private int activeTime = 4;
  private LinearLayout btnSaveImage;
  private Button btnShare = null;
  private LinearLayout btnShareEmail;
  private LinearLayout btnShareFacebook;
  private LinearLayout btnShareTwitter;
  private String cachePath = "";
  private Camera camera = null;
  private State cameraState = State.CAPTURING;
  Drawable d;
  private View galery_view;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        Toast localToast3 = Toast.makeText(CameraPlugin.this, R.string.alert_cannot_init, 1);
        localToast3.setGravity(81, 0, 95);
        localToast3.show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            CameraPlugin.this.finish();
          }
        }
        , 5000L);
        return;
      case 1:
        Toast localToast2 = Toast.makeText(CameraPlugin.this, R.string.alert_no_internet, 1);
        localToast2.setGravity(81, 0, 95);
        localToast2.show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            CameraPlugin.this.finish();
          }
        }
        , 5000L);
        return;
      case 2:
        Toast.makeText(CameraPlugin.this, R.string.warning_camera_take_picture_before, 1).show();
        return;
      case 3:
        CameraPlugin.this.checkControlsState();
        return;
      case 5:
        CameraPlugin.this.hideButton();
        return;
      case 4:
        CameraPlugin.this.showButton();
        return;
      case 6:
        Toast localToast1 = Toast.makeText(CameraPlugin.this, R.string.warning_camera_nocamera, 1);
        localToast1.setGravity(81, 0, 95);
        localToast1.show();
        CameraPlugin.this.finish();
        return;
      case 7:
        CameraPlugin.this.loadImageSharing_v2();
        CameraPlugin.access$1102(CameraPlugin.this, false);
        return;
      case 8:
        Toast.makeText(CameraPlugin.this, "Camera error! Please try again.", 0).show();
        CameraPlugin.this.finish();
        return;
      case 90:
      }
      CameraPlugin.this.d.clearColorFilter();
      CameraPlugin.this.galery_view.setBackgroundDrawable(CameraPlugin.this.d);
    }
  };
  private Bitmap image = null;
  private String image_url;
  private Camera.PictureCallback mPicture = new Camera.PictureCallback()
  {
    public void onPictureTaken(byte[] paramArrayOfByte, Camera paramCamera)
    {
      if (paramArrayOfByte == null)
        CameraPlugin.this.handler.sendEmptyMessage(8);
      while (true)
      {
        return;
        try
        {
          CameraPlugin.this.image.recycle();
          label28: MediaPlayer localMediaPlayer = MediaPlayer.create(CameraPlugin.this, R.raw.romanblack_camera_shot_sound);
          localMediaPlayer.setVolume(10.0F, 10.0F);
          localMediaPlayer.start();
          File localFile = new File(CameraPlugin.this.cachePath);
          if (localFile == null)
            continue;
          if (localFile.mkdirs());
          CameraPlugin.access$302(CameraPlugin.this, new File(CameraPlugin.this.cachePath + "/image.jpg"));
          if (CameraPlugin.this.pictureFile == null)
            continue;
          try
          {
            FileOutputStream localFileOutputStream = new FileOutputStream(CameraPlugin.this.pictureFile);
            localFileOutputStream.write(paramArrayOfByte);
            localFileOutputStream.close();
            System.gc();
            CameraPlugin.this.stopCamera();
            CameraPlugin.this.correctPictureRotation();
            CameraPlugin.this.handler.sendEmptyMessage(7);
            CameraPlugin.access$602(CameraPlugin.this, CameraPlugin.State.SHARING);
            return;
          }
          catch (Exception localException)
          {
            while (true)
              Log.w("CameraPlugin", "");
          }
        }
        catch (NullPointerException localNullPointerException)
        {
          break label28;
        }
      }
    }
  };
  private CameraParcer parser = null;
  private ImageView photoButton = null;
  private File pictureFile = null;
  private PackageManager pm;
  private CameraPreview preview = null;
  private RelativeLayout previewLayout = null;
  private LinearLayout retake_layout;
  private int screenHeight;
  private int screenWidth;
  private RelativeLayout share_root_layout;
  private ImageView showimage;
  private boolean wasCameraClick = false;
  private Widget widget = null;

  private void appropriateOrientation()
  {
    String str = Build.MODEL;
    if (str.compareTo("GT-I9100") == 0)
    {
      changeLandscapeToPortrait();
      return;
    }
    if (str.compareTo("LG-P500") == 0)
    {
      changeLandscapeToPortrait();
      return;
    }
    changeLandscapeToPortrait();
  }

  private void checkControlsState()
  {
    if (this.activeTime > 0)
    {
      this.activeTime = (-1 + this.activeTime);
      this.handler.sendEmptyMessageDelayed(3, 1000L);
      return;
    }
    this.handler.sendEmptyMessageDelayed(5, 1000L);
  }

  // ERROR //
  private void correctPictureRotation()
  {
    // Byte code:
    //   0: invokestatic 259	java/lang/System:gc	()V
    //   3: new 261	android/graphics/BitmapFactory$Options
    //   6: dup
    //   7: invokespecial 262	android/graphics/BitmapFactory$Options:<init>	()V
    //   10: astore_1
    //   11: aload_1
    //   12: iconst_1
    //   13: putfield 265	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   16: aload_0
    //   17: getfield 119	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:pictureFile	Ljava/io/File;
    //   20: invokevirtual 271	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   23: aload_1
    //   24: invokestatic 277	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   27: pop
    //   28: aload_1
    //   29: getfield 280	android/graphics/BitmapFactory$Options:outWidth	I
    //   32: istore_3
    //   33: aload_1
    //   34: getfield 283	android/graphics/BitmapFactory$Options:outHeight	I
    //   37: istore 4
    //   39: invokestatic 259	java/lang/System:gc	()V
    //   42: new 285	android/graphics/Matrix
    //   45: dup
    //   46: invokespecial 286	android/graphics/Matrix:<init>	()V
    //   49: astore 5
    //   51: new 261	android/graphics/BitmapFactory$Options
    //   54: dup
    //   55: invokespecial 262	android/graphics/BitmapFactory$Options:<init>	()V
    //   58: astore 6
    //   60: iload_3
    //   61: sipush 600
    //   64: if_icmplt +11 -> 75
    //   67: iload 4
    //   69: sipush 600
    //   72: if_icmpge +117 -> 189
    //   75: aconst_null
    //   76: astore 6
    //   78: invokestatic 259	java/lang/System:gc	()V
    //   81: aload_0
    //   82: aload_0
    //   83: getfield 119	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:pictureFile	Ljava/io/File;
    //   86: invokevirtual 271	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   89: aload 6
    //   91: invokestatic 277	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   94: putfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   97: new 288	android/media/ExifInterface
    //   100: dup
    //   101: aload_0
    //   102: getfield 119	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:pictureFile	Ljava/io/File;
    //   105: invokevirtual 271	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   108: invokespecial 291	android/media/ExifInterface:<init>	(Ljava/lang/String;)V
    //   111: ldc_w 293
    //   114: iconst_1
    //   115: invokevirtual 297	android/media/ExifInterface:getAttributeInt	(Ljava/lang/String;I)I
    //   118: tableswitch	default:+46 -> 164, 1:+46->164, 2:+46->164, 3:+206->324, 4:+46->164, 5:+46->164, 6:+153->271, 7:+46->164, 8:+248->366
    //   165: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   168: getstatic 303	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   171: bipush 100
    //   173: new 305	java/io/FileOutputStream
    //   176: dup
    //   177: aload_0
    //   178: getfield 119	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:pictureFile	Ljava/io/File;
    //   181: invokespecial 308	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   184: invokevirtual 314	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   187: pop
    //   188: return
    //   189: aload 6
    //   191: iconst_2
    //   192: putfield 317	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   195: goto -117 -> 78
    //   198: astore 19
    //   200: ldc 107
    //   202: ldc 107
    //   204: invokestatic 322	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   207: pop
    //   208: goto -111 -> 97
    //   211: astore 7
    //   213: ldc 107
    //   215: ldc 107
    //   217: invokestatic 322	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   220: pop
    //   221: invokestatic 259	java/lang/System:gc	()V
    //   224: aload_0
    //   225: aload_0
    //   226: getfield 119	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:pictureFile	Ljava/io/File;
    //   229: invokevirtual 271	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   232: invokestatic 325	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   235: putfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   238: goto -141 -> 97
    //   241: astore 17
    //   243: ldc 107
    //   245: ldc 107
    //   247: invokestatic 322	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   250: pop
    //   251: goto -154 -> 97
    //   254: astore 9
    //   256: ldc 107
    //   258: ldc 107
    //   260: invokestatic 322	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   263: pop
    //   264: aload_0
    //   265: invokevirtual 328	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:finish	()V
    //   268: goto -171 -> 97
    //   271: aload 5
    //   273: ldc_w 329
    //   276: invokevirtual 333	android/graphics/Matrix:postRotate	(F)Z
    //   279: pop
    //   280: aload_0
    //   281: aload_0
    //   282: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   285: iconst_0
    //   286: iconst_0
    //   287: aload_0
    //   288: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   291: invokevirtual 337	android/graphics/Bitmap:getWidth	()I
    //   294: aload_0
    //   295: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   298: invokevirtual 340	android/graphics/Bitmap:getHeight	()I
    //   301: aload 5
    //   303: iconst_1
    //   304: invokestatic 344	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
    //   307: putfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   310: goto -146 -> 164
    //   313: astore 11
    //   315: ldc 107
    //   317: ldc 107
    //   319: invokestatic 322	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   322: pop
    //   323: return
    //   324: aload 5
    //   326: ldc_w 345
    //   329: invokevirtual 333	android/graphics/Matrix:postRotate	(F)Z
    //   332: pop
    //   333: aload_0
    //   334: aload_0
    //   335: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   338: iconst_0
    //   339: iconst_0
    //   340: aload_0
    //   341: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   344: invokevirtual 337	android/graphics/Bitmap:getWidth	()I
    //   347: aload_0
    //   348: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   351: invokevirtual 340	android/graphics/Bitmap:getHeight	()I
    //   354: aload 5
    //   356: iconst_1
    //   357: invokestatic 344	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
    //   360: putfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   363: goto -199 -> 164
    //   366: aload 5
    //   368: ldc_w 346
    //   371: invokevirtual 333	android/graphics/Matrix:postRotate	(F)Z
    //   374: pop
    //   375: aload_0
    //   376: aload_0
    //   377: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   380: iconst_0
    //   381: iconst_0
    //   382: aload_0
    //   383: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   386: invokevirtual 337	android/graphics/Bitmap:getWidth	()I
    //   389: aload_0
    //   390: getfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   393: invokevirtual 340	android/graphics/Bitmap:getHeight	()I
    //   396: aload 5
    //   398: iconst_1
    //   399: invokestatic 344	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
    //   402: putfield 115	com/ibuildapp/romanblack/CameraPlugin/CameraPlugin:image	Landroid/graphics/Bitmap;
    //   405: goto -241 -> 164
    //
    // Exception table:
    //   from	to	target	type
    //   78	97	198	java/lang/Exception
    //   78	97	211	java/lang/OutOfMemoryError
    //   224	238	241	java/lang/Exception
    //   224	238	254	java/lang/OutOfMemoryError
    //   97	164	313	java/lang/Exception
    //   164	188	313	java/lang/Exception
    //   271	310	313	java/lang/Exception
    //   324	363	313	java/lang/Exception
    //   366	405	313	java/lang/Exception
  }

  private void hideButton()
  {
    this.btnShare.setVisibility(4);
  }

  private void loadImageSharing_v2()
  {
    setContentView(R.layout.sergeybrazhnik_camera_main);
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (!localNetworkInfo.isConnectedOrConnecting()));
    this.share_root_layout = ((RelativeLayout)findViewById(R.id.share_root_layout));
    this.showimage = ((ImageView)findViewById(R.id.imageView));
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = getResources().getDisplayMetrics().density;
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.sergeybrazhnik_footer, null);
    Drawable localDrawable = localLinearLayout.getBackground();
    localDrawable.setAlpha(127);
    localLinearLayout.setBackgroundDrawable(localDrawable);
    this.btnSaveImage = ((LinearLayout)localLinearLayout.findViewById(R.id.camera_save_btn));
    this.btnShareFacebook = ((LinearLayout)localLinearLayout.findViewById(R.id.camera_facebook_btn));
    this.btnShareTwitter = ((LinearLayout)localLinearLayout.findViewById(R.id.camera_twitter_btn));
    this.btnShareEmail = ((LinearLayout)localLinearLayout.findViewById(R.id.camera_email_btn));
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams1.addRule(14);
    localLayoutParams1.addRule(12);
    this.retake_layout = ((LinearLayout)LayoutInflater.from(this).inflate(R.layout.romanblack_retake, null));
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.addRule(10);
    localLayoutParams2.addRule(11);
    localLayoutParams2.setMargins(0, (int)(10.0F * f), (int)(10.0F * f), 0);
    this.share_root_layout.addView(localLinearLayout, localLayoutParams1);
    this.share_root_layout.addView(this.retake_layout, localLayoutParams2);
    if (this.image != null)
      this.showimage.setImageBitmap(this.image);
    this.btnSaveImage.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        CameraPlugin.this.d = paramView.getBackground();
        CameraPlugin.this.d.setColorFilter(-7829368, PorterDuff.Mode.MULTIPLY);
        CameraPlugin.access$1202(CameraPlugin.this, paramView);
        paramView.setBackgroundDrawable(CameraPlugin.this.d);
        String str = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        CameraPlugin.access$1502(CameraPlugin.this, MediaStore.Images.Media.insertImage(CameraPlugin.this.getContentResolver(), CameraPlugin.this.image, "IMG_" + str, "description"));
        if (CameraPlugin.this.image_url != null)
        {
          Toast localToast2 = Toast.makeText(CameraPlugin.this, CameraPlugin.this.getResources().getString(R.string.alert_galary_save_success), 1);
          localToast2.setGravity(81, 0, 95);
          localToast2.show();
        }
        while (true)
        {
          CameraPlugin.this.handler.sendEmptyMessageDelayed(90, 400L);
          return;
          Toast localToast1 = Toast.makeText(CameraPlugin.this, CameraPlugin.this.getResources().getString(R.string.alert_galary_save_error), 1);
          localToast1.setGravity(81, 0, 95);
          localToast1.show();
        }
      }
    });
    this.retake_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        CameraPlugin.this.setContentView(R.layout.romanblack_camera_preview);
        CameraPlugin.access$1602(CameraPlugin.this, (ImageView)CameraPlugin.this.findViewById(R.id.romanblack_btn_camera_capture));
        CameraPlugin.this.photoButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            if (!CameraPlugin.this.wasCameraClick)
              CameraPlugin.this.camera.autoFocus(new Camera.AutoFocusCallback()
              {
                public void onAutoFocus(boolean paramBoolean, Camera paramCamera)
                {
                  CameraPlugin.access$1102(CameraPlugin.this, true);
                  CameraPlugin.this.camera.takePicture(null, null, CameraPlugin.this.mPicture);
                }
              });
          }
        });
        CameraPlugin.this.stopCamera();
        CameraPlugin.this.startCamera(10);
      }
    });
    this.btnShareFacebook.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)CameraPlugin.this.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
        {
          CameraPlugin.this.redrawButtons(false);
          Toast localToast = Toast.makeText(CameraPlugin.this, CameraPlugin.this.getResources().getString(R.string.alert_no_internet), 1);
          localToast.setGravity(81, 0, 95);
          localToast.show();
          return;
        }
        CameraPlugin.this.redrawButtons(true);
        Authorization.authorize(CameraPlugin.this, 10000, 1);
      }
    });
    this.btnShareTwitter.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)CameraPlugin.this.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
        {
          CameraPlugin.this.redrawButtons(false);
          Toast localToast = Toast.makeText(CameraPlugin.this, CameraPlugin.this.getResources().getString(R.string.alert_no_internet), 1);
          localToast.setGravity(81, 0, 95);
          localToast.show();
          return;
        }
        CameraPlugin.this.redrawButtons(true);
        Authorization.authorize(CameraPlugin.this, 10001, 2);
      }
    });
    this.btnShareEmail.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)CameraPlugin.this.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
        {
          CameraPlugin.this.redrawButtons(false);
          Toast localToast = Toast.makeText(CameraPlugin.this, "No Internet connection available.", 1);
          localToast.setGravity(81, 0, 95);
          localToast.show();
          return;
        }
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/html");
        localIntent.putExtra("android.intent.extra.EMAIL", new String[] { CameraPlugin.access$1900(CameraPlugin.this).getEmail() });
        localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(CameraPlugin.this.pictureFile));
        CameraPlugin.this.startActivityForResult(localIntent, 1889);
      }
    });
  }

  private void logError(String paramString, Exception paramException)
  {
    StatisticsCollector.newError(paramException, paramString);
  }

  private void redrawButtons(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.btnShareTwitter.setEnabled(true);
      Drawable localDrawable4 = getResources().getDrawable(R.drawable.romanblack_camera_twitter);
      localDrawable4.clearColorFilter();
      this.btnShareTwitter.setBackgroundDrawable(localDrawable4);
      this.btnShareFacebook.setEnabled(true);
      Drawable localDrawable5 = getResources().getDrawable(R.drawable.romanblack_camera_facebook);
      localDrawable5.clearColorFilter();
      this.btnShareFacebook.setBackgroundDrawable(localDrawable5);
      this.btnShareEmail.setEnabled(true);
      Drawable localDrawable6 = getResources().getDrawable(R.drawable.romanblack_camera_email);
      localDrawable6.clearColorFilter();
      this.btnShareEmail.setBackgroundDrawable(localDrawable6);
      return;
    }
    Drawable localDrawable1 = getResources().getDrawable(R.drawable.romanblack_camera_twitter);
    ColorMatrix localColorMatrix1 = new ColorMatrix();
    localColorMatrix1.setSaturation(0.0F);
    localDrawable1.setColorFilter(new ColorMatrixColorFilter(localColorMatrix1));
    this.btnShareTwitter.setBackgroundDrawable(localDrawable1);
    this.btnShareTwitter.setEnabled(false);
    Drawable localDrawable2 = getResources().getDrawable(R.drawable.romanblack_camera_facebook);
    ColorMatrix localColorMatrix2 = new ColorMatrix();
    localColorMatrix2.setSaturation(0.0F);
    localDrawable2.setColorFilter(new ColorMatrixColorFilter(localColorMatrix2));
    this.btnShareFacebook.setBackgroundDrawable(localDrawable2);
    this.btnShareFacebook.setEnabled(false);
    Drawable localDrawable3 = getResources().getDrawable(R.drawable.romanblack_camera_email);
    ColorMatrix localColorMatrix3 = new ColorMatrix();
    localColorMatrix3.setSaturation(0.0F);
    localDrawable3.setColorFilter(new ColorMatrixColorFilter(localColorMatrix3));
    this.btnShareEmail.setBackgroundDrawable(localDrawable3);
    this.btnShareEmail.setEnabled(false);
  }

  private String saveImage()
  {
    String str1 = "";
    try
    {
      String str2 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
      this.cachePath = this.widget.getCachePath();
      str1 = this.cachePath + "/" + str2 + ".jpeg";
      FileOutputStream localFileOutputStream = new FileOutputStream(new File(str1));
      this.image.compress(Bitmap.CompressFormat.JPEG, 90, localFileOutputStream);
      localFileOutputStream.close();
      return str1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str1;
  }

  private void setCameraQuality(int paramInt)
  {
    Camera.Parameters localParameters = this.camera.getParameters();
    localParameters.setJpegQuality(paramInt);
    this.camera.setParameters(localParameters);
  }

  private void shareFacebook()
  {
    Intent localIntent = new Intent(this, SharingActivity.class);
    this.image_url = saveImage();
    localIntent.putExtra("image", this.image_url);
    localIntent.putExtra("hasAd", this.widget.isHaveAdvertisement());
    localIntent.putExtra("appname", this.widget.getAppName());
    localIntent.putExtra("type", "facebook");
    startActivityForResult(localIntent, 10002);
  }

  private void shareTwitter()
  {
    Intent localIntent = new Intent(this, SharingActivity.class);
    this.image_url = saveImage();
    localIntent.putExtra("image", this.image_url);
    localIntent.putExtra("hasAd", this.widget.isHaveAdvertisement());
    localIntent.putExtra("appname", this.widget.getAppName());
    localIntent.putExtra("type", "twitter");
    startActivityForResult(localIntent, 10003);
  }

  private void showButton()
  {
    this.btnShare.setVisibility(0);
    this.activeTime = 4;
    checkControlsState();
  }

  private void startCamera(int paramInt)
  {
    this.camera = Camera.open();
    Camera.Parameters localParameters = this.camera.getParameters();
    localParameters.setFlashMode("on");
    localParameters.setFocusMode("auto");
    List localList = localParameters.getSupportedPreviewSizes();
    Object localObject = (Camera.Size)localList.get(0);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Camera.Size localSize2 = (Camera.Size)localIterator.next();
      if (((Camera.Size)localObject).height >= localSize2.height)
        continue;
      localObject = localSize2;
    }
    localParameters.setPreviewSize(((Camera.Size)localObject).width, ((Camera.Size)localObject).height);
    this.camera.setParameters(localParameters);
    this.preview = new CameraPreview(getApplicationContext(), this.camera);
    Camera.Size localSize1 = this.camera.getParameters().getPreviewSize();
    float f = localSize1.height / localSize1.width;
    int i = (int)(this.screenWidth / f);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.screenWidth, i);
    this.previewLayout = ((RelativeLayout)findViewById(R.id.romanblack_camera_preview));
    this.previewLayout.addView(this.preview, localLayoutParams);
    appropriateOrientation();
  }

  private void stopCamera()
  {
    System.out.println("stopCamera method");
    if (this.camera != null)
    {
      this.camera.stopPreview();
      this.camera.setPreviewCallback(null);
      this.previewLayout.removeView(this.preview);
      this.camera.release();
      this.camera = null;
    }
  }

  public void changeLandscapeToPortrait()
  {
    int i = ((WindowManager)getSystemService("window")).getDefaultDisplay().getRotation();
    int k;
    int j;
    if (i == 0)
    {
      k = 90;
      j = 90;
    }
    while (true)
    {
      this.camera.setDisplayOrientation(j);
      Camera.Parameters localParameters = this.camera.getParameters();
      localParameters.setRotation(k);
      this.camera.setParameters(localParameters);
      return;
      if (i == 1)
      {
        j = 0;
        k = 0;
        continue;
      }
      if (i == 2)
      {
        k = 270;
        j = 270;
        continue;
      }
      j = 0;
      k = 0;
      if (i != 3)
        continue;
      k = 180;
      j = 180;
    }
  }

  public void changePortraitToLandscape()
  {
    int i = ((WindowManager)getSystemService("window")).getDefaultDisplay().getRotation();
    int k;
    int j;
    if (i == 0)
    {
      k = 0;
      j = 0;
    }
    while (true)
    {
      this.camera.setDisplayOrientation(j);
      Camera.Parameters localParameters = this.camera.getParameters();
      localParameters.setRotation(k);
      this.camera.setParameters(localParameters);
      return;
      if (i == 1)
      {
        k = 90;
        j = 90;
        continue;
      }
      if (i == 2)
      {
        k = 180;
        j = 180;
        continue;
      }
      j = 0;
      k = 0;
      if (i != 3)
        continue;
      k = 270;
      j = 270;
    }
  }

  public void create()
  {
    try
    {
      Display localDisplay = getWindowManager().getDefaultDisplay();
      this.screenWidth = localDisplay.getWidth();
      this.screenHeight = localDisplay.getHeight();
      setContentView(R.layout.romanblack_camera_preview);
      this.widget = ((Widget)getIntent().getExtras().getSerializable("Widget"));
      if (this.widget == null)
      {
        this.handler.sendEmptyMessageDelayed(0, 100L);
        return;
      }
      if (this.widget.getPluginXmlData().length() == 0)
      {
        this.handler.sendEmptyMessageDelayed(0, 100L);
        return;
      }
    }
    catch (Exception localException)
    {
      logError("CameraPlugin.create()", localException);
      return;
    }
    if ((this.widget.getTitle() != null) && (this.widget.getTitle().length() != 0))
      setTopBarTitle(this.widget.getTitle());
    while (true)
    {
      if (!((Boolean)getIntent().getExtras().getSerializable("showSideBar")).booleanValue())
        setTopBarLeftButtonText(getResources().getString(R.string.common_home_upper), true, new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            CameraPlugin.this.finish();
          }
        });
      this.parser = new CameraParcer(this.widget.getPluginXmlData());
      this.parser.parse();
      this.cachePath = (this.widget.getCachePath() + "/camera-" + this.widget.getOrder());
      ((LinearLayout)findViewById(R.id.romanblack_camera_panel)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (!CameraPlugin.this.wasCameraClick)
          {
            CameraPlugin.access$1102(CameraPlugin.this, true);
            Camera.Parameters localParameters = CameraPlugin.this.camera.getParameters();
            localParameters.setFocusMode("auto");
            localParameters.setPictureFormat(256);
            localParameters.setAntibanding("auto");
            localParameters.setSceneMode("auto");
            List localList = localParameters.getSupportedPictureSizes();
            Object localObject = (Camera.Size)localList.get(0);
            Iterator localIterator = localList.iterator();
            while (localIterator.hasNext())
            {
              Camera.Size localSize = (Camera.Size)localIterator.next();
              if (((Camera.Size)localObject).height >= localSize.height)
                continue;
              localObject = localSize;
            }
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = Integer.valueOf(((Camera.Size)localObject).height);
            arrayOfObject[1] = Integer.valueOf(((Camera.Size)localObject).width);
            Log.d("com.ibuildapp.CameraPlugin", String.format("height = %d, width = %d", arrayOfObject));
            localParameters.setPictureSize(((Camera.Size)localObject).width, ((Camera.Size)localObject).height);
            CameraPlugin.this.camera.setParameters(localParameters);
            CameraPlugin.this.camera.takePicture(null, null, CameraPlugin.this.mPicture);
            MediaPlayer localMediaPlayer = MediaPlayer.create(CameraPlugin.this, R.raw.romanblack_camera_shot_sound);
            localMediaPlayer.setVolume(10.0F, 10.0F);
            localMediaPlayer.start();
          }
        }
      });
      this.pm = getPackageManager();
      if (!this.pm.hasSystemFeature("android.hardware.camera"))
        break;
      startCamera(10);
      return;
      setTopBarTitle(getResources().getString(R.string.camera_plugin));
    }
    this.handler.sendEmptyMessageDelayed(6, 100L);
  }

  public void destroy()
  {
    if (this.camera != null);
    try
    {
      this.camera.stopPreview();
      this.camera.release();
      label21: super.destroy();
      return;
    }
    catch (Exception localException)
    {
      break label21;
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
    case 10001:
    case 10000:
    case 10003:
    case 10002:
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            if (paramInt2 != -1)
              continue;
            shareTwitter();
            return;
          }
          while (paramInt2 != 0);
          Toast localToast6 = Toast.makeText(this, R.string.alert_twitter_posted_error, 1);
          localToast6.setGravity(81, 0, 95);
          localToast6.show();
          return;
          if (paramInt2 != -1)
            continue;
          shareFacebook();
          return;
        }
        while (paramInt2 != 0);
        Toast localToast5 = Toast.makeText(this, R.string.alert_facebook_posted_error, 1);
        localToast5.setGravity(81, 0, 95);
        localToast5.show();
        return;
        if (paramInt2 != -1)
          continue;
        Toast localToast4 = Toast.makeText(this, R.string.alert_twitter_posted_success, 1);
        localToast4.setGravity(81, 0, 95);
        localToast4.show();
        return;
      }
      while (paramInt2 != 0);
      Toast localToast3 = Toast.makeText(this, R.string.alert_twitter_posted_error, 1);
      localToast3.setGravity(81, 0, 95);
      localToast3.show();
      return;
      if (paramInt2 != -1)
        continue;
      Toast localToast2 = Toast.makeText(this, R.string.alert_facebook_posted_success, 1);
      localToast2.setGravity(81, 0, 95);
      localToast2.show();
      return;
    }
    while (paramInt2 != 0);
    Toast localToast1 = Toast.makeText(this, R.string.alert_facebook_posted_error, 1);
    localToast1.setGravity(81, 0, 95);
    localToast1.show();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.camera != null)
    {
      this.camera.stopPreview();
      appropriateOrientation();
    }
    super.onConfigurationChanged(paramConfiguration);
  }

  class CameraPreview extends SurfaceView
    implements SurfaceHolder.Callback
  {
    private Camera mCamera;
    private SurfaceHolder mHolder;

    public CameraPreview(Context paramCamera, Camera arg3)
    {
      super();
      Object localObject;
      this.mCamera = localObject;
      this.mHolder = getHolder();
      this.mHolder.addCallback(this);
      this.mHolder.setType(3);
      Log.d("", "");
    }

    public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
    {
      if (this.mHolder.getSurface() == null)
        return;
      try
      {
        this.mCamera.stopPreview();
        try
        {
          label20: this.mCamera.setPreviewDisplay(this.mHolder);
          this.mCamera.startPreview();
          return;
        }
        catch (Exception localException2)
        {
          Log.w("CameraPreview", localException2.getMessage());
          return;
        }
      }
      catch (Exception localException1)
      {
        break label20;
      }
    }

    public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
    {
      try
      {
        this.mCamera.setPreviewDisplay(paramSurfaceHolder);
        this.mCamera.startPreview();
        return;
      }
      catch (Exception localException)
      {
        Log.w("CameraPlugin", localException.getMessage());
      }
    }

    public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
    {
    }
  }

  private static enum State
  {
    static
    {
      State[] arrayOfState = new State[2];
      arrayOfState[0] = CAPTURING;
      arrayOfState[1] = SHARING;
      $VALUES = arrayOfState;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.CameraPlugin.CameraPlugin
 * JD-Core Version:    0.6.0
 */