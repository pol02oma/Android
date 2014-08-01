package com.appbuilder.u846253p1176378;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.BarDesigner.TitleDesign;
import com.appbuilder.sdk.android.Base64;
import com.appbuilder.sdk.android.GoogleAnaliticsHandler;
import com.appbuilder.sdk.android.LoginScreen;
import com.appbuilder.sdk.android.MenuAdapter;
import com.appbuilder.sdk.android.OnSwipeInterface;
import com.appbuilder.sdk.android.Statics;
import com.appbuilder.sdk.android.SwipeLinearLayout;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.statistics.StatisticsCollector;
import com.appbuilder.u846253p1176378.GPSNotification.GPSService;
import com.appbuilder.u846253p1176378.LoginScreen.LoginWithEmailActivity;
import com.appbuilder.u846253p1176378.LoginScreen.service.LoginScreenService;
import com.appbuilder.u846253p1176378.LoginScreen.service.LoginSettings;
import com.appbuilder.u846253p1176378.LoginScreen.service.LoginSettingsService;
import com.appbuilder.u846253p1176378.LoginScreen.service.OnDone;
import com.appbuilder.u846253p1176378.PushNotification.AppPushNotificationDB;
import com.appbuilder.u846253p1176378.PushNotification.AppPushNotificationDialogLayout;
import com.appbuilder.u846253p1176378.PushNotification.AppPushNotificationMessage;
import com.appbuilder.u846253p1176378.plugin.PluginLoader;
import com.appbuilder.u846253p1176378.tools.Tools;
import com.flurry.android.FlurryAgent;
import com.google.android.c2dm.C2DMessaging;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AppBuilder extends Activity
  implements PluginLoader, DownloadHelperCallback, View.OnClickListener, AdapterView.OnItemClickListener
{
  public static final String DOMEN = "ibuildapp.com";
  static final String FACEBOOK_APP_ID = "207296122640913";
  static final String FACEBOOK_APP_SECRET = "3cae87e561313d4dd07c076566e2c67a";
  static String FLURYY_ID = "WXFRQZ8HX5Q74RDZ447P";
  private static final String TAG = "com.ibuildapp.appbuilder";
  private static final String TAG_PUSH = "PUSHNS_appbuilder";
  static final String TWITTER_CONSUMER_KEY = "p48aBftV8vXXfG6UWo0BcQ";
  static final String TWITTER_CONSUMER_SECRET = "YYkHCKtSD7uYhSC3jtPL1H2b6NaX2u6x5kOLLgRUA";
  private final String APP_ID = "1176378";
  private final String APP_TOKEN = "ibLEBSl0AOeTo";
  private final int CONFIGURATION_LOADED_FAILED = 2;
  private final int CONFIGURATION_LOADED_SUCCESS = 1;
  private final int GPS_NOTIFICATION_SERVICE_INTENT_START = 11;
  private final int GPS_NOTIFICATION_SERVICE_START = 9;
  private final int GPS_NOTIFICATION_SERVICE_STOP = 10;
  private final int INTERFACE_BUILDED_FAILED = 4;
  private final int INTERFACE_BUILDED_SUCCESS = 3;
  private final int LISTEN_INTERFACE_BUILDING = 5;
  private final int LOGIN_SCREEN = 777;
  private final String MENU_INDEX = "MenuIndex";
  private final String MENU_TOP_COORDINATE = "MenuCoordinate";
  private final int NEED_INTERNET_CONNECTION = 13;
  private final int NO_SD_CARD = 7;
  private final int NO_SOURCE_URL = 6;
  private final int PUSH_NOTIFICATION_INIT = 8;
  private final int REFRESH_APP_DATA = 12;
  private final int START_MODULE = 10001;
  private final String WIDGET_HOLDER_BACKGROUND = "#3f434b";
  private ArrayList<Widget> actualWidgetList = new ArrayList();
  private AlphaAnimation animHideDialog;
  private AlphaAnimation animHideImage;
  private TranslateAnimation animHideMenu;
  private AlphaAnimation animShowDialog;
  private AlphaAnimation animShowImage;
  private TranslateAnimation animShowMenu;
  AppConfigure appConfig = new AppConfigure();
  private BroadcastReceiver broadcastReceiver;
  private String cachePath = "";
  private LinearLayout dialogHolder;
  private boolean dontLaunchModule = false;
  private String fileName = "xmlData.xml";
  private boolean firstPluginStart = true;
  private boolean flurryStarted = false;
  private boolean foreground = true;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 7:
        Toast.makeText(AppBuilder.this, AppBuilder.this.getString(2131100008), 1).show();
        new Handler().postDelayed(new AppBuilder.1.1(this), 5000L);
        return;
      case 6:
        Toast.makeText(AppBuilder.this, AppBuilder.this.getString(2131100009), 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            AppBuilder.this.finishActivity(AppBuilder.this.requestCode);
            AppBuilder.this.finish();
          }
        }
        , 5000L);
        return;
      case 1:
        try
        {
          Statics.showLink = AppBuilder.this.appConfig.getShowLink();
          Statics.appName = AppBuilder.this.appConfig.getAppName();
          Statics.analiticsHandler = new GoogleAnaliticsHandler(AppBuilder.this.getApplicationContext(), AppBuilder.this.appConfig.getAppName(), AppBuilder.this.appConfig.getGoogleAnalyticsId());
          Statics.analiticsHandler.sendUserEvent("Start Application", "-");
          Statics.analiticsHandler.sendIbuildAppEvent("Start Application", "-");
          AppBuilder.this.listenInterfaceBuilder();
          return;
        }
        catch (Exception localException2)
        {
          while (true)
            AppBuilder.this.logWarning(localException2);
        }
      case 2:
        Toast.makeText(AppBuilder.this, AppBuilder.this.getString(2131100010), 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            AppBuilder.this.finishActivity(AppBuilder.this.requestCode);
            AppBuilder.this.finish();
          }
        }
        , 5000L);
        return;
      case 3:
        AppBuilder.this.putDataInCache();
        AppBuilder.this.finishActivity(AppBuilder.this.requestCode);
        if ((AppBuilder.this.appConfig.getLoginScreen() != null) && (!AppBuilder.this.isAuthorized))
        {
          AppBuilder.this.isAuthorized = true;
          LoginSettings localLoginSettings = LoginSettingsService.loadSettings(AppBuilder.this.getSharedPreferences("LOGIN_SETTINGS", 0));
          String str1 = localLoginSettings.getPassword();
          String str2 = localLoginSettings.getUsername();
          if ((str1.length() > 0) && (str2.length() > 0))
          {
            NetworkInfo localNetworkInfo = ((ConnectivityManager)AppBuilder.this.getSystemService("connectivity")).getActiveNetworkInfo();
            if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
            {
              LoginScreenService.doLogin(AppBuilder.this.appConfig.getLoginScreen().getLoginEndpoint(), str2, str1, "email", AppBuilder.this.appConfig.getLoginScreen().getAppId(), new OnDone(str2)
              {
                public void onDone(int paramInt)
                {
                  if (paramInt == 200)
                  {
                    if ((AppBuilder.this.userID != null) && (AppBuilder.this.userID.equals("186589")))
                      FlurryAgent.setUserId(this.val$username);
                    return;
                  }
                  AppBuilder.this.runOnUiThread(new Runnable()
                  {
                    public void run()
                    {
                      Intent localIntent = new Intent(AppBuilder.this, LoginWithEmailActivity.class);
                      localIntent.putExtra("loginScreen", AppBuilder.this.appConfig.getLoginScreen());
                      localIntent.putExtra("navBarDesign", AppBuilder.this.appConfig.getNavBarDesign());
                      AppBuilder.this.startActivityForResult(localIntent, 777);
                      AppBuilder.access$502(AppBuilder.this, true);
                    }
                  });
                }
              });
              return;
            }
            Log.d("", "");
            return;
          }
          Intent localIntent = new Intent(AppBuilder.this, LoginWithEmailActivity.class);
          localIntent.putExtra("loginScreen", AppBuilder.this.appConfig.getLoginScreen());
          localIntent.putExtra("navBarDesign", AppBuilder.this.appConfig.getNavBarDesign());
          AppBuilder.this.startActivityForResult(localIntent, 777);
          AppBuilder.access$502(AppBuilder.this, true);
          return;
        }
        if (AppBuilder.this.appConfig.getGPSNotifications().size() > 0)
        {
          AppBuilder.this.handler.sendEmptyMessage(9);
          return;
        }
        AppBuilder.this.handler.sendEmptyMessage(10);
        return;
      case 4:
        Toast.makeText(AppBuilder.this, AppBuilder.this.getString(2131100010), 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            AppBuilder.this.finishActivity(AppBuilder.this.requestCode);
            AppBuilder.this.finish();
          }
        }
        , 5000L);
        return;
      case 5:
        AppBuilder.this.listenInterfaceBuilder();
        return;
      case 8:
        AppBuilder.this.pushNotificationInit();
        return;
      case 9:
        AppBuilder.this.startGPSNotificationService();
        return;
      case 11:
        AppBuilder.this.startService();
        return;
      case 10:
        AppBuilder.this.stopGPSNotificationService();
        return;
      case 12:
        try
        {
          AppBuilder.this.reloadAppConfigure();
          return;
        }
        catch (Exception localException1)
        {
          return;
        }
      case 13:
      }
      Toast.makeText(AppBuilder.this, AppBuilder.this.getString(2131100011), 1).show();
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          AppBuilder.this.finishActivity(AppBuilder.this.requestCode);
          AppBuilder.this.finish();
        }
      }
      , 5000L);
    }
  };
  private LinearLayout homeButton;
  public boolean isAuthorized = false;
  private boolean isDialogShowen = false;
  private boolean isShown = false;
  private int iterator = 0;
  private LayoutInflater layoutInflater;
  private NotificationManager mManager;
  private LinearLayout menuContainer;
  private boolean needToClose = false;
  private ProgressDialog progressDialog = null;
  private String pushRegistrationUrl = "http://ibuildapp.com/pushns.registration.php?project=1176378&platform=android";
  private Random rand = new Random();
  private final int requestCode = this.rand.nextInt(65535);
  private SwipeLinearLayout rootContainer;
  private FrameLayout rootFrameLayout;
  private SharedPreferences sPref;
  private double screenCoef = 1.0D;
  private int screenWidth;
  private boolean sdAvailable = false;
  private LinearLayout showMenu;
  private String startedPluginName;
  private OnSwipeInterface swipeInterface;
  private LinearLayout tapArea;
  private ArrayList<Bitmap> thumbnails = new ArrayList();
  private LinearLayout userContainer;
  private String userID = "";
  private ListView widgetList;
  private String xmlUrl = "http://ibuildapp.com/xml.php?project=1176378&type=android";

  private int DpToPixel(int paramInt)
  {
    return (int)(0.5F + getResources().getDisplayMetrics().density * paramInt);
  }

  private int PixelToDp(int paramInt)
  {
    return (int)(paramInt / getResources().getDisplayMetrics().density);
  }

  private void createPushDialog()
  {
    Log.e("PUSHNS_appbuilder", "createPushDialog");
    AppPushNotificationMessage localAppPushNotificationMessage = AppPushNotificationDB.getNotificationIfExist();
    if (localAppPushNotificationMessage != null)
    {
      AppPushNotificationDialogLayout localAppPushNotificationDialogLayout;
      if (!this.isDialogShowen)
      {
        if (!localAppPushNotificationMessage.isPackageExist)
          break label207;
        localAppPushNotificationDialogLayout = new AppPushNotificationDialogLayout(this, localAppPushNotificationMessage.titleText, localAppPushNotificationMessage.descriptionText, localAppPushNotificationMessage.imagePath, new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            AppBuilder.this.dialogHolder.clearAnimation();
            AppBuilder.access$2402(AppBuilder.this, false);
            AppBuilder.this.dialogHolder.startAnimation(AppBuilder.this.animHideDialog);
            AppBuilder.this.createPushDialog();
          }
        }
        , new View.OnClickListener(localAppPushNotificationMessage)
        {
          public void onClick(View paramView)
          {
            AppBuilder.this.dialogHolder.clearAnimation();
            AppBuilder.access$2402(AppBuilder.this, false);
            AppBuilder.this.dialogHolder.startAnimation(AppBuilder.this.animHideDialog);
            AppBuilder.this.launchWidgetWithOrder(this.val$freshMessage.widgetOrder);
          }
        });
        if (this.appConfig.getTabsCount() == 0)
          break label297;
        FrameLayout.LayoutParams localLayoutParams2 = (FrameLayout.LayoutParams)this.dialogHolder.getLayoutParams();
        localLayoutParams2.setMargins(0, 0, 0, 37);
        this.dialogHolder.setLayoutParams(localLayoutParams2);
      }
      while (true)
      {
        this.dialogHolder.removeAllViews();
        this.dialogHolder.addView(localAppPushNotificationDialogLayout);
        this.dialogHolder.setVisibility(0);
        this.isDialogShowen = true;
        this.dialogHolder.clearAnimation();
        this.dialogHolder.startAnimation(this.animShowDialog);
        AppPushNotificationDB.deleteItemFromRelations(localAppPushNotificationMessage.uid);
        this.mManager.cancel((int)localAppPushNotificationMessage.uid);
        Log.e("PUSHNS_appbuilder", "We have message = " + localAppPushNotificationMessage.toString());
        return;
        label207: if (localAppPushNotificationMessage.widgetOrder == -1)
        {
          localAppPushNotificationDialogLayout = new AppPushNotificationDialogLayout(this, localAppPushNotificationMessage.titleText, localAppPushNotificationMessage.descriptionText, localAppPushNotificationMessage.imagePath, new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              AppBuilder.this.dialogHolder.clearAnimation();
              AppBuilder.access$2402(AppBuilder.this, false);
              AppBuilder.this.dialogHolder.startAnimation(AppBuilder.this.animHideDialog);
              AppBuilder.this.createPushDialog();
            }
          }
          , new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
            }
          });
          break;
        }
        localAppPushNotificationDialogLayout = new AppPushNotificationDialogLayout(this, localAppPushNotificationMessage.titleText, localAppPushNotificationMessage.descriptionText, localAppPushNotificationMessage.imagePath, new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            AppBuilder.this.dialogHolder.clearAnimation();
            AppBuilder.access$2402(AppBuilder.this, false);
            AppBuilder.this.dialogHolder.startAnimation(AppBuilder.this.animHideDialog);
            AppBuilder.this.createPushDialog();
          }
        }
        , new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            AppBuilder.this.dialogHolder.clearAnimation();
            AppBuilder.access$2402(AppBuilder.this, false);
            AppBuilder.this.dialogHolder.startAnimation(AppBuilder.this.animHideDialog);
          }
        });
        break;
        label297: FrameLayout.LayoutParams localLayoutParams1 = (FrameLayout.LayoutParams)this.dialogHolder.getLayoutParams();
        localLayoutParams1.setMargins(0, 0, 0, 0);
        this.dialogHolder.setLayoutParams(localLayoutParams1);
      }
    }
    Log.e("PUSHNS_appbuilder", " no message found");
  }

  private void downloadFavicons(AppConfigure paramAppConfigure)
  {
    for (int i = 0; i < -1 + paramAppConfigure.getWidgetsCount(); i++)
    {
      Widget localWidget = paramAppConfigure.getWidgetAtIndex(i);
      if (localWidget == null)
        continue;
      String str = new String(this.cachePath + File.separator + md5(localWidget.getFaviconURL()));
      if (!new File(str).exists())
        widgetFaviconDownload(localWidget.getFaviconURL());
      localWidget.setFaviconFilePath(str);
    }
  }

  private void drawButton(WidgetUIButton paramWidgetUIButton, RelativeLayout paramRelativeLayout)
  {
    Button localButton = new Button(this);
    Display localDisplay = getWindowManager().getDefaultDisplay();
    int i = -20 + localDisplay.getHeight();
    localDisplay.getWidth();
    Rect localRect;
    if ((((int)(i * this.screenCoef) - (int)((paramWidgetUIButton.getTop() + paramWidgetUIButton.getHeight()) * this.screenCoef) > 14) && (this.appConfig.getShowLink())) || (!this.appConfig.getShowLink()))
      localRect = new Rect((int)(paramWidgetUIButton.getLeft() * this.screenCoef), (int)(paramWidgetUIButton.getTop() * this.screenCoef), (int)((paramWidgetUIButton.getLeft() + paramWidgetUIButton.getWidth()) * this.screenCoef), (int)((paramWidgetUIButton.getTop() + paramWidgetUIButton.getHeight()) * this.screenCoef));
    while (true)
    {
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(DpToPixel(localRect.width()), DpToPixel(localRect.height()));
      localLayoutParams.topMargin = DpToPixel(localRect.top);
      localLayoutParams.leftMargin = DpToPixel(localRect.left);
      localButton.setText(paramWidgetUIButton.getTitle());
      localButton.setTextSize(paramWidgetUIButton.getFontSize());
      localButton.setBackgroundColor(0);
      localButton.setPadding(0, 0, 0, 0);
      String str1 = paramWidgetUIButton.getAlign();
      label258: String str2;
      if (str1.equalsIgnoreCase("left"))
      {
        localButton.setGravity(19);
        str2 = paramWidgetUIButton.getStyle();
        if (!str2.equalsIgnoreCase("bold"))
          break label525;
        localButton.setTypeface(Typeface.DEFAULT, 1);
      }
      try
      {
        label283: localButton.setTextColor(Color.parseColor(paramWidgetUIButton.getColor().toUpperCase()));
        label297: int j = paramWidgetUIButton.getImageSourceCache().length();
        int k = 0;
        if (j > 0)
        {
          File localFile1 = new File(paramWidgetUIButton.getImageSourceCache());
          if (localFile1.exists())
            k = 1;
        }
        else
        {
          if (k == 0)
            break label690;
        }
        while (true)
        {
          try
          {
            System.gc();
          }
          catch (Exception localException2)
          {
            try
            {
              System.gc();
              Drawable localDrawable2 = Drawable.createFromPath(paramWidgetUIButton.getImageSourceCache());
              localObject = localDrawable2;
              localButton.setBackgroundDrawable(new BitmapDrawable(Utils.BmpResize(((BitmapDrawable)((Drawable)localObject).mutate()).getBitmap(), DpToPixel(localRect.width()), DpToPixel(localRect.height()))));
              Log.d("Draw button", "success");
              localButton.setOnClickListener(new View.OnClickListener(paramWidgetUIButton)
              {
                public void onClick(View paramView)
                {
                  Drawable localDrawable = paramView.getBackground();
                  localDrawable.setColorFilter(-7829368, PorterDuff.Mode.MULTIPLY);
                  paramView.setBackgroundDrawable(localDrawable);
                  1 local1 = new Runnable()
                  {
                    public void run()
                    {
                      AppBuilder.this.launchWidgetWithOrder(AppBuilder.24.this.val$button.getOrder());
                    }
                  };
                  new Handler().postDelayed(local1, 10L);
                }
              });
              paramRelativeLayout.addView(localButton, localLayoutParams);
              return;
              localRect = new Rect((int)(paramWidgetUIButton.getLeft() * this.screenCoef), (int)(paramWidgetUIButton.getTop() * this.screenCoef), (int)((paramWidgetUIButton.getLeft() + paramWidgetUIButton.getWidth()) * this.screenCoef), (int)((-18 + (paramWidgetUIButton.getTop() + paramWidgetUIButton.getHeight())) * this.screenCoef));
              break;
              if (!str1.equalsIgnoreCase("right"))
                break label258;
              localButton.setGravity(21);
              break label258;
              label525: if (!str2.equalsIgnoreCase("italic"))
                break label283;
              localButton.setTypeface(Typeface.DEFAULT, 2);
              break label283;
              paramWidgetUIButton.setImageSourceCache("");
              k = 0;
            }
            catch (Exception localException5)
            {
              Log.d("", "");
              localObject = null;
              continue;
              localException2 = localException2;
              Log.w("Draw button", localException2);
              try
              {
                File localFile2 = new File(paramWidgetUIButton.getImageSourceCache());
                localFile2.delete();
                drawButton(paramWidgetUIButton, paramRelativeLayout);
              }
              catch (Exception localException3)
              {
                Log.d("", "");
              }
              continue;
            }
            catch (OutOfMemoryError localOutOfMemoryError1)
            {
              Object localObject;
              Log.d("", "");
              System.gc();
              try
              {
                Drawable localDrawable1 = Drawable.createFromPath(paramWidgetUIButton.getImageSourceCache());
                localObject = localDrawable1;
              }
              catch (Exception localException4)
              {
                Log.d("", "");
                localObject = null;
              }
              catch (OutOfMemoryError localOutOfMemoryError2)
              {
                Log.e("decodeImageFile", "OutOfMemoryError");
                localObject = null;
              }
              continue;
            }
          }
          label690: if (Tools.checkNetwork(this) > 0)
          {
            if (this.sdAvailable)
            {
              String str3 = paramWidgetUIButton.getImageSourceUrl();
              if (str3.length() > 0)
              {
                String str4 = this.cachePath + "/assets/" + md5(str3);
                paramWidgetUIButton.setImageSourceCache(str4);
                DownloadHelper localDownloadHelper = new DownloadHelper(this, str3, str4, null, false, true);
                localDownloadHelper.setStartedRunnable(new Runnable()
                {
                  public void run()
                  {
                    Log.d("Download button image", "Start");
                  }
                });
                localDownloadHelper.setFailedRunnable(new Runnable(localDownloadHelper, paramWidgetUIButton)
                {
                  public void run()
                  {
                    Log.d("Download button image", "Failed: " + this.val$dh.getErrorString());
                    this.val$button.setDownloadStatus(DownloadStatus.FAILED);
                  }
                });
                localDownloadHelper.setSuccessRunnable(new Runnable(str4, localRect, localButton, paramWidgetUIButton)
                {
                  public void run()
                  {
                    try
                    {
                      System.gc();
                      try
                      {
                        System.gc();
                        Drawable localDrawable2 = Drawable.createFromPath(this.val$imageCache);
                        localObject = localDrawable2;
                        BitmapDrawable localBitmapDrawable = new BitmapDrawable(Utils.BmpResize(((BitmapDrawable)((Drawable)localObject).mutate()).getBitmap(), AppBuilder.this.DpToPixel(this.val$viewRect.width()), AppBuilder.this.DpToPixel(this.val$viewRect.height())));
                        this.val$view.setBackgroundDrawable(localBitmapDrawable);
                        label79: this.val$button.setDownloadStatus(DownloadStatus.SUCCESS);
                        return;
                      }
                      catch (Exception localException3)
                      {
                        while (true)
                        {
                          Log.d("", "");
                          localObject = null;
                        }
                      }
                      catch (OutOfMemoryError localOutOfMemoryError1)
                      {
                        while (true)
                        {
                          Object localObject;
                          Log.d("", "");
                          System.gc();
                          try
                          {
                            Drawable localDrawable1 = Drawable.createFromPath(this.val$imageCache);
                            localObject = localDrawable1;
                          }
                          catch (Exception localException2)
                          {
                            Log.d("", "");
                            localObject = null;
                          }
                          catch (OutOfMemoryError localOutOfMemoryError2)
                          {
                            Log.e("decodeImageFile", "OutOfMemoryError");
                            localObject = null;
                          }
                        }
                      }
                    }
                    catch (Exception localException1)
                    {
                      break label79;
                    }
                  }
                });
                localDownloadHelper.start();
                continue;
              }
              paramWidgetUIButton.setDownloadStatus(DownloadStatus.SUCCESS);
              continue;
            }
            try
            {
              URL localURL = new URL(paramWidgetUIButton.getImageSourceUrl());
              HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
              localHttpURLConnection.connect();
              localButton.setBackgroundDrawable(new BitmapDrawable(Utils.BmpResize(BitmapFactory.decodeStream(localHttpURLConnection.getInputStream()), DpToPixel(localRect.width()), DpToPixel(localRect.height()))));
              paramWidgetUIButton.setDownloadStatus(DownloadStatus.SUCCESS);
            }
            catch (IOException localIOException)
            {
              Log.d("", "");
            }
            catch (NullPointerException localNullPointerException)
            {
              Log.d("", "");
            }
            continue;
          }
          if (paramWidgetUIButton.getmImageData() == null)
            continue;
          try
          {
            localButton.setBackgroundDrawable(new BitmapDrawable(Utils.BmpResize(BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(paramWidgetUIButton.getmImageData()))), DpToPixel(localRect.width()), DpToPixel(localRect.height()))));
            paramWidgetUIButton.setDownloadStatus(DownloadStatus.SUCCESS);
          }
          catch (Exception localException1)
          {
            Log.e("", "");
          }
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        break label297;
      }
    }
  }

  private void drawHyperlink(AppConfigure paramAppConfigure, RelativeLayout paramRelativeLayout)
  {
    SpannableString localSpannableString = new SpannableString("Created using iBuildApp.com.\nCreate Your Own App");
    localSpannableString.setSpan(new StyleSpan(0), 0, localSpannableString.length(), 33);
    URLSpan localURLSpan = new URLSpan("http://ibuildapp.com");
    localURLSpan.getUnderlying().updateDrawState(new TextPaint());
    localSpannableString.setSpan(new ForegroundColorSpan(-1), 0, localSpannableString.length(), 33);
    TextView localTextView = new TextView(this);
    localTextView.setText(localSpannableString);
    17 local17 = new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse("http://ibuildapp.com"));
        AppBuilder.this.startActivity(localIntent);
      }
    };
    localTextView.setOnClickListener(local17);
    localTextView.setShadowLayer(0.1F, 2.0F, 2.0F, -16777216);
    localTextView.setGravity(1);
    Display localDisplay = getWindowManager().getDefaultDisplay();
    int i = localDisplay.getHeight();
    int j = localDisplay.getWidth();
    if (j > i)
    {
      int i6 = j;
      j = i;
      i = i6;
    }
    int k = PixelToDp(j);
    int m = PixelToDp(i);
    if (j > i)
    {
      int i5 = k;
      k = m;
      m = i5;
    }
    int n = DpToPixel(180);
    int i1 = DpToPixel(35);
    int i2 = m - (i1 + PixelToDp(0));
    int i3 = (k - 180) / 2;
    if (paramAppConfigure.needShowMenu());
    for (int i4 = -5 + (i2 - 75); ; i4 = i2 - 18)
    {
      Log.d("density", String.valueOf(getResources().getDisplayMetrics().density));
      Log.d("display width", String.valueOf(localDisplay.getWidth()));
      Log.d("display height", String.valueOf(localDisplay.getHeight()));
      Log.d("dpw", String.valueOf(k));
      Log.d("dph", String.valueOf(m));
      Log.d("width", String.valueOf(n));
      Log.d("height", String.valueOf(i1));
      Log.d("top", String.valueOf(i4));
      Log.d("left", String.valueOf(i3));
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, i1);
      localLayoutParams.topMargin = DpToPixel(i4);
      localLayoutParams.leftMargin = 0;
      paramRelativeLayout.addView(localTextView, localLayoutParams);
      return;
    }
  }

  private void drawImage(WidgetUIImage paramWidgetUIImage, RelativeLayout paramRelativeLayout)
  {
    ImageView localImageView = new ImageView(this);
    Rect localRect = new Rect((int)(paramWidgetUIImage.getLeft() * this.screenCoef), (int)(paramWidgetUIImage.getTop() * this.screenCoef), (int)((paramWidgetUIImage.getLeft() + paramWidgetUIImage.getWidth()) * this.screenCoef), (int)((paramWidgetUIImage.getTop() + paramWidgetUIImage.getHeight()) * this.screenCoef));
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(DpToPixel(localRect.width()), DpToPixel(localRect.height()));
    localLayoutParams.topMargin = DpToPixel(localRect.top);
    localLayoutParams.leftMargin = DpToPixel(localRect.left);
    int i = paramWidgetUIImage.getSourceCache().length();
    int j = 0;
    if (i > 0)
    {
      File localFile1 = new File(paramWidgetUIImage.getSourceCache());
      if (localFile1.exists())
        j = 1;
    }
    else
    {
      if (j == 0)
        break label388;
    }
    while (true)
    {
      try
      {
        System.gc();
      }
      catch (Exception localException2)
      {
        try
        {
          System.gc();
          Drawable localDrawable2 = Drawable.createFromPath(paramWidgetUIImage.getSourceCache());
          localObject = localDrawable2;
          localImageView.setBackgroundDrawable(new BitmapDrawable(Utils.BmpResize(((BitmapDrawable)((Drawable)localObject).mutate()).getBitmap(), DpToPixel(localRect.width()), DpToPixel(localRect.height()))));
          Log.d("Draw image from cache", "Success");
          paramRelativeLayout.addView(localImageView, localLayoutParams);
          return;
          paramWidgetUIImage.setSourceCache("");
          j = 0;
        }
        catch (Exception localException5)
        {
          Log.d("", "");
          localObject = null;
          continue;
          localException2 = localException2;
          Log.w("Draw image from cache", "");
          try
          {
            File localFile2 = new File(paramWidgetUIImage.getSourceCache());
            localFile2.delete();
            drawImage(paramWidgetUIImage, paramRelativeLayout);
          }
          catch (Exception localException3)
          {
          }
          continue;
        }
        catch (OutOfMemoryError localOutOfMemoryError1)
        {
          Object localObject;
          Log.d("", "");
          System.gc();
          try
          {
            Drawable localDrawable1 = Drawable.createFromPath(paramWidgetUIImage.getSourceCache());
            localObject = localDrawable1;
          }
          catch (Exception localException4)
          {
            Log.d("", "");
            localObject = null;
          }
          catch (OutOfMemoryError localOutOfMemoryError2)
          {
            Log.e("decodeImageFile", "OutOfMemoryError");
            localObject = null;
          }
          continue;
        }
      }
      label388: if (Tools.checkNetwork(this) > 0)
      {
        if (!this.sdAvailable)
        {
          try
          {
            URL localURL = new URL(paramWidgetUIImage.getSourceUrl());
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
            localHttpURLConnection.connect();
            localImageView.setBackgroundDrawable(new BitmapDrawable(Utils.BmpResize(BitmapFactory.decodeStream(localHttpURLConnection.getInputStream()), DpToPixel(localRect.width()), DpToPixel(localRect.height()))));
            paramWidgetUIImage.setDownloadStatus(DownloadStatus.SUCCESS);
          }
          catch (IOException localIOException)
          {
            Log.d("", "");
          }
          continue;
        }
        String str1 = paramWidgetUIImage.getSourceUrl();
        if (str1.length() > 0)
        {
          String str2 = this.cachePath + "/assets/" + md5(str1);
          paramWidgetUIImage.setSourceCache(str2);
          DownloadHelper localDownloadHelper = new DownloadHelper(this, str1, str2, null, false, true);
          localDownloadHelper.setStartedRunnable(new Runnable()
          {
            public void run()
            {
              Log.d("Download widget image", "Start");
            }
          });
          localDownloadHelper.setFailedRunnable(new Runnable(paramWidgetUIImage, localDownloadHelper)
          {
            public void run()
            {
              this.val$image.setDownloadStatus(DownloadStatus.FAILED);
              Log.w("Download widget image", "Failed: " + this.val$dh.getErrorString());
            }
          });
          localDownloadHelper.setSuccessRunnable(new Runnable(str2, localRect, localImageView, paramWidgetUIImage)
          {
            public void run()
            {
              try
              {
                System.gc();
              }
              catch (Exception localException1)
              {
                try
                {
                  System.gc();
                  Drawable localDrawable2 = Drawable.createFromPath(this.val$imageCache);
                  localObject = localDrawable2;
                  BitmapDrawable localBitmapDrawable = new BitmapDrawable(Utils.BmpResize(((BitmapDrawable)((Drawable)localObject).mutate()).getBitmap(), AppBuilder.this.DpToPixel(this.val$viewRect.width()), AppBuilder.this.DpToPixel(this.val$viewRect.height())));
                  this.val$view.setBackgroundDrawable(localBitmapDrawable);
                  Log.d("Draw widget image", "Success");
                  Log.d("Download widget image", "Success");
                  this.val$image.setDownloadStatus(DownloadStatus.SUCCESS);
                  return;
                }
                catch (Exception localException3)
                {
                  while (true)
                  {
                    Log.d("", "");
                    localObject = null;
                    continue;
                    localException1 = localException1;
                    Log.w("Draw widget image", "");
                  }
                }
                catch (OutOfMemoryError localOutOfMemoryError1)
                {
                  while (true)
                  {
                    Object localObject;
                    Log.d("", "");
                    System.gc();
                    try
                    {
                      Drawable localDrawable1 = Drawable.createFromPath(this.val$imageCache);
                      localObject = localDrawable1;
                    }
                    catch (Exception localException2)
                    {
                      Log.d("", "");
                      localObject = null;
                    }
                    catch (OutOfMemoryError localOutOfMemoryError2)
                    {
                      Log.e("decodeImageFile", "OutOfMemoryError");
                      localObject = null;
                    }
                  }
                }
              }
            }
          });
          localDownloadHelper.start();
          continue;
        }
        paramWidgetUIImage.setDownloadStatus(DownloadStatus.SUCCESS);
        continue;
      }
      if (paramWidgetUIImage.getmImageData() == null)
        continue;
      try
      {
        localImageView.setBackgroundDrawable(new BitmapDrawable(Utils.BmpResize(BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(paramWidgetUIImage.getmImageData()))), DpToPixel(localRect.width()), DpToPixel(localRect.height()))));
        paramWidgetUIImage.setDownloadStatus(DownloadStatus.SUCCESS);
      }
      catch (Exception localException1)
      {
        Log.e("", "");
      }
    }
  }

  private void drawInterface()
  {
    while (true)
    {
      int i;
      RelativeLayout localRelativeLayout2;
      int j;
      AppConfigureItem localAppConfigureItem;
      try
      {
        this.rootFrameLayout = ((FrameLayout)findViewById(2131361804));
        SwipeLinearLayout localSwipeLinearLayout = new SwipeLinearLayout(this, this.appConfig.isShowSidebar());
        this.rootContainer = localSwipeLinearLayout;
        this.rootContainer.setOrientation(0);
        LinearLayout localLinearLayout1 = new LinearLayout(this);
        this.menuContainer = localLinearLayout1;
        this.menuContainer.setBackgroundColor(Color.parseColor("#3f434b"));
        this.menuContainer.setOrientation(1);
        this.menuContainer.setBackgroundColor(Color.parseColor("#000000"));
        LinearLayout localLinearLayout2 = (LinearLayout)this.layoutInflater.inflate(2130903041, null);
        LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
        this.homeButton = ((LinearLayout)localLinearLayout2.findViewById(2131361805));
        this.homeButton.setOnClickListener(this);
        ListView localListView = new ListView(this);
        this.widgetList = localListView;
        this.widgetList.setBackgroundColor(Color.parseColor("#3f434b"));
        this.widgetList.setCacheColorHint(Color.parseColor("#3f434b"));
        this.widgetList.setVerticalScrollBarEnabled(false);
        i = 0;
        if (i >= this.appConfig.getWidgetsCount())
          continue;
        if (!((Widget)this.appConfig.getmWidgets().get(i)).isAddToSidebar())
          break label865;
        this.actualWidgetList.add(this.appConfig.getmWidgets().get(i));
        break label865;
        MenuAdapter localMenuAdapter = new MenuAdapter(this, this.actualWidgetList, this.thumbnails);
        this.widgetList.setAdapter(localMenuAdapter);
        this.widgetList.setOnItemClickListener(this);
        this.menuContainer.addView(localLinearLayout2, localLayoutParams1);
        this.menuContainer.addView(this.widgetList, localLayoutParams1);
        LinearLayout localLinearLayout3 = new LinearLayout(this);
        this.userContainer = localLinearLayout3;
        this.userContainer.setOrientation(1);
        this.userContainer.setBackgroundColor(Color.parseColor("#c0b9ff"));
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams((int)(this.screenWidth - 0.15D * this.screenWidth), -1);
        this.rootContainer.addView(this.menuContainer, localLayoutParams2);
        LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(this.screenWidth, -1);
        this.rootContainer.addView(this.userContainer, localLayoutParams3);
        FrameLayout.LayoutParams localLayoutParams4 = new FrameLayout.LayoutParams((int)(this.screenWidth + 0.85D * this.screenWidth), -1);
        localLayoutParams4.gravity = 3;
        localLayoutParams4.setMargins(-(this.screenWidth - (int)(0.15D * this.screenWidth)), 0, 0, 0);
        this.rootFrameLayout.addView(this.rootContainer, localLayoutParams4);
        LinearLayout localLinearLayout4 = new LinearLayout(this);
        this.dialogHolder = localLinearLayout4;
        this.dialogHolder.setVisibility(4);
        FrameLayout.LayoutParams localLayoutParams5 = new FrameLayout.LayoutParams(-2, -2);
        localLayoutParams5.gravity = 17;
        this.dialogHolder.setLayoutParams(localLayoutParams5);
        this.rootFrameLayout.addView(this.dialogHolder);
        prepareAnimationObjects();
        16 local16 = new OnSwipeInterface()
        {
          public void onSwipeBottom()
          {
          }

          public void onSwipeLeft()
          {
            if (AppBuilder.this.isShown)
            {
              AppBuilder.access$2802(AppBuilder.this, false);
              AppBuilder.this.rootContainer.clearAnimation();
              AppBuilder.this.rootContainer.startAnimation(AppBuilder.this.animHideMenu);
            }
          }

          public void onSwipeRight()
          {
            if (!AppBuilder.this.isShown)
            {
              AppBuilder.access$2802(AppBuilder.this, true);
              AppBuilder.this.rootContainer.clearAnimation();
              AppBuilder.this.rootContainer.startAnimation(AppBuilder.this.animShowMenu);
            }
          }

          public void onSwipeTop()
          {
          }

          public boolean onTouchEvent(float paramFloat)
          {
            Display localDisplay = AppBuilder.this.getWindowManager().getDefaultDisplay();
            boolean bool1 = AppBuilder.this.isShown;
            int i = 0;
            if (bool1)
            {
              int j = localDisplay.getWidth();
              boolean bool2 = paramFloat < (int)(j - 0.15D * j);
              i = 0;
              if (bool2)
              {
                boolean bool3 = paramFloat < j;
                i = 0;
                if (bool3)
                {
                  AppBuilder.access$2802(AppBuilder.this, false);
                  AppBuilder.this.rootContainer.clearAnimation();
                  AppBuilder.this.rootContainer.startAnimation(AppBuilder.this.animHideMenu);
                  i = 1;
                }
              }
            }
            return i;
          }
        };
        this.swipeInterface = local16;
        if (!this.appConfig.isShowSidebar())
          continue;
        this.rootContainer.setOnSwipeEvents(this.swipeInterface);
        RelativeLayout localRelativeLayout1 = (RelativeLayout)this.layoutInflater.inflate(2130903048, null);
        localRelativeLayout2 = (RelativeLayout)localRelativeLayout1.findViewById(2131361838);
        localRelativeLayout2.removeAllViews();
        setBackgroundUseConfig(this.appConfig, localRelativeLayout2, localRelativeLayout1);
        this.userContainer.addView(localRelativeLayout1);
        Display localDisplay = getWindowManager().getDefaultDisplay();
        localDisplay.getWidth();
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        localDisplay.getMetrics(localDisplayMetrics);
        this.screenCoef = (localDisplayMetrics.widthPixels / (320.0F * localDisplayMetrics.scaledDensity));
        if (!this.appConfig.getShowLink())
          break label871;
        drawHyperlink(this.appConfig, localRelativeLayout2);
        break label871;
        if (j >= this.appConfig.getControlsCount())
          break label836;
        localAppConfigureItem = this.appConfig.getControlAtIndex(j);
        if (!(localAppConfigureItem instanceof WidgetUILabel))
          continue;
        drawLabel((WidgetUILabel)this.appConfig.getControlAtIndex(j), localRelativeLayout2);
        break label877;
        if ((localAppConfigureItem instanceof WidgetUIButton))
          drawButton((WidgetUIButton)this.appConfig.getControlAtIndex(j), localRelativeLayout2);
      }
      catch (Exception localException)
      {
        logError("Appbuilder.drawInterface", localException);
        return;
      }
      if ((localAppConfigureItem instanceof WidgetUIImage))
      {
        drawImage((WidgetUIImage)this.appConfig.getControlAtIndex(j), localRelativeLayout2);
        break label877;
        label836: if (this.appConfig.needShowMenu())
          drawTabsUseConfig(this.appConfig, localRelativeLayout2);
        Log.w("", "");
        return;
        label865: i++;
        continue;
        label871: j = 0;
        continue;
      }
      label877: j++;
    }
  }

  private void drawLabel(WidgetUILabel paramWidgetUILabel, RelativeLayout paramRelativeLayout)
  {
    TextView localTextView = new TextView(this);
    Rect localRect = new Rect((int)(paramWidgetUILabel.getLeft() * this.screenCoef), (int)(paramWidgetUILabel.getTop() * this.screenCoef), (int)((paramWidgetUILabel.getLeft() + paramWidgetUILabel.getWidth()) * this.screenCoef), (int)((paramWidgetUILabel.getTop() + paramWidgetUILabel.getHeight()) * this.screenCoef));
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(DpToPixel(localRect.width()), DpToPixel(localRect.height()));
    localLayoutParams.topMargin = DpToPixel(localRect.top);
    localLayoutParams.leftMargin = DpToPixel(localRect.left);
    localTextView.setText(paramWidgetUILabel.getTitle());
    localTextView.setTextSize(paramWidgetUILabel.getFontSize());
    localTextView.setGravity(1);
    String str1 = paramWidgetUILabel.getAlign();
    if (str1.equalsIgnoreCase("left"))
      localTextView.setGravity(3);
    while (true)
    {
      String str2 = paramWidgetUILabel.getStyle();
      if (str2.equalsIgnoreCase("bold"))
        localTextView.setTypeface(Typeface.DEFAULT, 1);
      try
      {
        while (true)
        {
          localTextView.setTextColor(Color.parseColor(paramWidgetUILabel.getColor().toUpperCase()));
          label210: paramRelativeLayout.addView(localTextView, localLayoutParams);
          paramWidgetUILabel.setDownloadStatus(DownloadStatus.SUCCESS);
          return;
          if (str1.equalsIgnoreCase("right"))
          {
            localTextView.setGravity(5);
            break;
          }
          localTextView.setGravity(1);
          break;
          if (!str2.equalsIgnoreCase("italic"))
            continue;
          localTextView.setTypeface(Typeface.DEFAULT, 2);
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        break label210;
      }
    }
  }

  private void drawTabInRect(WidgetUITab paramWidgetUITab, RelativeLayout paramRelativeLayout, Rect paramRect)
  {
    View localView = LayoutInflater.from(this).inflate(2130903050, null);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(DpToPixel(paramRect.width()), DpToPixel(paramRect.height()));
    localLayoutParams.topMargin = DpToPixel(paramRect.top);
    localLayoutParams.leftMargin = DpToPixel(paramRect.left);
    localView.setOnClickListener(new View.OnClickListener(paramWidgetUITab.getOrder())
    {
      public void onClick(View paramView)
      {
        1 local1 = new Runnable()
        {
          public void run()
          {
            AppBuilder.this.launchWidgetWithOrder(AppBuilder.25.this.val$order);
          }
        };
        new Handler().postDelayed(local1, 200L);
      }
    });
    TextView localTextView = (TextView)localView.findViewById(2131361845);
    localTextView.setText(paramWidgetUITab.getLabel());
    localTextView.setTextColor(this.appConfig.getTabBarDesign().itemDesign.textColor);
    localTextView.setLines(1);
    localTextView.setTextSize(10.0F);
    ImageView localImageView;
    int j;
    if (this.appConfig.getTabBarDesign().itemDesign.textAlignment.compareTo("left") == 0)
    {
      localTextView.setGravity(3);
      localImageView = (ImageView)localView.findViewById(2131361844);
      localImageView.setImageBitmap(null);
      localImageView.setBackgroundColor(0);
      int i = paramWidgetUITab.getIconCache().length();
      j = 0;
      if (i > 0)
      {
        if (!new File(paramWidgetUITab.getIconCache()).exists())
          break label533;
        j = 1;
      }
      if (j == 0)
        break label665;
    }
    while (true)
    {
      try
      {
        System.gc();
      }
      catch (Exception localException1)
      {
        try
        {
          System.gc();
          Drawable localDrawable2 = Drawable.createFromPath(paramWidgetUITab.getIconCache());
          localObject = localDrawable2;
          BitmapDrawable localBitmapDrawable3 = new BitmapDrawable(Utils.BmpResize(((BitmapDrawable)((Drawable)localObject).mutate()).getBitmap(), DpToPixel(50), DpToPixel(50)));
          if (localImageView == null)
            continue;
          localImageView.setBackgroundDrawable(localBitmapDrawable3);
          Log.d("Draw tab image", "Success");
          StateListDrawable localStateListDrawable = new StateListDrawable();
          ShapeDrawable localShapeDrawable1 = new ShapeDrawable(new RectShape());
          localShapeDrawable1.getPaint().setColor(this.appConfig.getTabBarDesign().color);
          ShapeDrawable localShapeDrawable2 = new ShapeDrawable(new RectShape());
          localShapeDrawable2.getPaint().setColor(this.appConfig.getTabBarDesign().itemDesign.selectedColor);
          localStateListDrawable.addState(new int[] { 16842919 }, localShapeDrawable2);
          localStateListDrawable.addState(new int[] { 16842913 }, localShapeDrawable2);
          localStateListDrawable.addState(new int[0], localShapeDrawable1);
          localView.setBackgroundDrawable(localStateListDrawable);
          LinearLayout localLinearLayout = new LinearLayout(this);
          localLinearLayout.addView(localView, localLayoutParams);
          localLinearLayout.setBackgroundColor(-16777216);
          paramRelativeLayout.addView(localLinearLayout, localLayoutParams);
          return;
          if (this.appConfig.getTabBarDesign().itemDesign.textAlignment.compareTo("center") != 0)
            continue;
          localTextView.setGravity(1);
          break;
          if (this.appConfig.getTabBarDesign().itemDesign.textAlignment.compareTo("right") != 0)
            break;
          localTextView.setGravity(5);
          break;
          label533: paramWidgetUITab.setIconCache("");
          j = 0;
        }
        catch (Exception localException4)
        {
          Log.d("", "");
          localObject = null;
          continue;
          localException1 = localException1;
          Log.w("Draw tab image", "");
          try
          {
            new File(paramWidgetUITab.getIconCache()).delete();
            drawTabInRect(paramWidgetUITab, paramRelativeLayout, paramRect);
          }
          catch (Exception localException2)
          {
          }
          continue;
        }
        catch (OutOfMemoryError localOutOfMemoryError1)
        {
          Object localObject;
          Log.d("", "");
          System.gc();
          try
          {
            Drawable localDrawable1 = Drawable.createFromPath(paramWidgetUITab.getIconCache());
            localObject = localDrawable1;
          }
          catch (Exception localException3)
          {
            Log.d("", "");
            localObject = null;
          }
          catch (OutOfMemoryError localOutOfMemoryError2)
          {
            Log.e("decodeImageFile", "OutOfMemoryError");
            localObject = null;
          }
          continue;
        }
      }
      label665: if (Tools.checkNetwork(this) > 0)
      {
        if (this.sdAvailable)
        {
          String str1 = paramWidgetUITab.getIconUrl();
          if (str1.length() > 0)
          {
            String str2 = this.cachePath + "/assets/" + md5(str1);
            paramWidgetUITab.setIconCache(str2);
            DownloadHelper localDownloadHelper = new DownloadHelper(this, str1, str2, null, false, true);
            localDownloadHelper.setStartedRunnable(new Runnable()
            {
              public void run()
              {
                Log.d("Download tab image", "Start");
              }
            });
            localDownloadHelper.setFailedRunnable(new Runnable(localDownloadHelper, paramWidgetUITab)
            {
              public void run()
              {
                Log.d("Download tab image", "Failed: " + this.val$dh.getErrorString());
                this.val$tab.setDownloadStatus(DownloadStatus.FAILED);
              }
            });
            localDownloadHelper.setSuccessRunnable(new Runnable(str2, localImageView, paramWidgetUITab)
            {
              public void run()
              {
                try
                {
                  System.gc();
                }
                catch (Exception localException1)
                {
                  try
                  {
                    System.gc();
                    Drawable localDrawable2 = Drawable.createFromPath(this.val$iconCache);
                    localObject = localDrawable2;
                    BitmapDrawable localBitmapDrawable = new BitmapDrawable(Utils.BmpResize(((BitmapDrawable)((Drawable)localObject).mutate()).getBitmap(), AppBuilder.this.DpToPixel(50), AppBuilder.this.DpToPixel(50)));
                    if (this.val$icon != null)
                      this.val$icon.setBackgroundDrawable(localBitmapDrawable);
                    Log.d("Download tab image", "Success");
                    this.val$tab.setDownloadStatus(DownloadStatus.SUCCESS);
                    return;
                  }
                  catch (Exception localException3)
                  {
                    while (true)
                    {
                      Log.d("", "");
                      localObject = null;
                      continue;
                      localException1 = localException1;
                      Log.w("Download tab image", "");
                    }
                  }
                  catch (OutOfMemoryError localOutOfMemoryError1)
                  {
                    while (true)
                    {
                      Object localObject;
                      Log.d("", "");
                      System.gc();
                      try
                      {
                        Drawable localDrawable1 = Drawable.createFromPath(this.val$iconCache);
                        localObject = localDrawable1;
                      }
                      catch (Exception localException2)
                      {
                        Log.d("", "");
                        localObject = null;
                      }
                      catch (OutOfMemoryError localOutOfMemoryError2)
                      {
                        Log.e("decodeImageFile", "OutOfMemoryError");
                        localObject = null;
                      }
                    }
                  }
                }
              }
            });
            localDownloadHelper.start();
            continue;
          }
          paramWidgetUITab.setDownloadStatus(DownloadStatus.SUCCESS);
          continue;
        }
        try
        {
          URL localURL = new URL(paramWidgetUITab.getIconUrl());
          HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
          localHttpURLConnection.connect();
          BitmapDrawable localBitmapDrawable2 = new BitmapDrawable(Utils.BmpResize(BitmapFactory.decodeStream(localHttpURLConnection.getInputStream()), DpToPixel(50), DpToPixel(50)));
          if (localImageView != null)
            localImageView.setBackgroundDrawable(localBitmapDrawable2);
          paramWidgetUITab.setDownloadStatus(DownloadStatus.SUCCESS);
        }
        catch (IOException localIOException2)
        {
        }
        continue;
      }
      try
      {
        BitmapDrawable localBitmapDrawable1 = new BitmapDrawable(Utils.BmpResize(BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(paramWidgetUITab.getmIconData()))), DpToPixel(50), DpToPixel(50)));
        if (localImageView != null)
          localImageView.setBackgroundDrawable(localBitmapDrawable1);
        paramWidgetUITab.setDownloadStatus(DownloadStatus.SUCCESS);
      }
      catch (IOException localIOException1)
      {
      }
      catch (NullPointerException localNullPointerException)
      {
      }
    }
  }

  private void drawTabsUseConfig(AppConfigure paramAppConfigure, RelativeLayout paramRelativeLayout)
  {
    Display localDisplay = getWindowManager().getDefaultDisplay();
    int i = PixelToDp(localDisplay.getWidth());
    int j = PixelToDp(localDisplay.getHeight());
    if (i > j)
    {
      int i3 = i;
      i = j;
      j = i3;
    }
    int k = paramAppConfigure.getTabsCount();
    int m = i / k;
    int n = 0;
    while (true)
      if (n < k)
      {
        WidgetUITab localWidgetUITab = paramAppConfigure.getTabAtIndex(n);
        int i1 = k - 1;
        int i2 = 0;
        if (n == i1)
          i2 = 10;
        try
        {
          drawTabInRect(localWidgetUITab, paramRelativeLayout, new Rect(m * n, j - 75, i2 + (m + m * n), j));
          n++;
        }
        catch (Exception localException1)
        {
          while (true)
            try
            {
              localWidgetUITab.setDownloadStatus(DownloadStatus.FAILED);
            }
            catch (Exception localException2)
            {
            }
        }
      }
  }

  private int getTextWidth(TextView paramTextView)
  {
    Paint localPaint = new Paint();
    new Rect();
    localPaint.setTypeface(Typeface.DEFAULT);
    localPaint.setTextSize(paramTextView.getTextSize());
    return (int)localPaint.measureText((String)paramTextView.getText());
  }

  private void launchWidgetWithOrder(int paramInt)
  {
    if (paramInt == -1);
    try
    {
      finish();
      return;
      if (this.dontLaunchModule)
        return;
      this.dontLaunchModule = true;
      PluginManager localPluginManager = PluginManager.getManager();
      localPluginManager.setFirstStart(this.firstPluginStart);
      this.firstPluginStart = false;
      try
      {
        Widget localWidget = this.appConfig.getWidgetAtIndex(paramInt);
        localWidget.setCachePath(this.cachePath);
        localWidget.setHaveAdvertisement(this.appConfig.getShowLink());
        this.needToClose = localWidget.getPluginName().equals("FeedbackPlugin");
        if (localWidget.getPluginPackage().length() > 0)
        {
          this.startedPluginName = localWidget.getTitle();
          if (TextUtils.isEmpty(this.startedPluginName))
            this.startedPluginName = localWidget.getPluginName();
          localPluginManager.loadPlugin(this, this, localWidget, this.appConfig.getAppAdv(), false, this.appConfig.getmWidgets(), this.appConfig);
          Log.d("PLUGINDATA", localWidget.getPluginXmlData());
          return;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        Log.w("PLUGINDATA", localRuntimeException);
        return;
      }
    }
    catch (Exception localException)
    {
      logError("AppBuilder.launchWidgetWithOrder", localException);
      return;
    }
    this.dontLaunchModule = false;
    Toast.makeText(this, "The functionality is not available.", 1).show();
  }

  private void listenInterfaceBuilder()
  {
    this.iterator = (1 + this.iterator);
    if (this.iterator > 30)
    {
      Handler localHandler4 = this.handler;
      getClass();
      localHandler4.sendEmptyMessage(3);
    }
    int i = this.appConfig.getAllDownloadStatus();
    Log.i("LISTEN INTERFACE BUILDER", this.iterator + ": " + i);
    switch (i)
    {
    default:
      return;
    case -1:
      Handler localHandler3 = this.handler;
      getClass();
      localHandler3.sendEmptyMessage(4);
      return;
    case 0:
      Handler localHandler2 = this.handler;
      getClass();
      localHandler2.sendEmptyMessageDelayed(5, 3000L);
      return;
    case 1:
    }
    Handler localHandler1 = this.handler;
    getClass();
    localHandler1.sendEmptyMessage(3);
  }

  // ERROR //
  private boolean loadDatafromURL()
    throws IOException
  {
    // Byte code:
    //   0: invokestatic 1390	java/lang/System:currentTimeMillis	()J
    //   3: lstore_2
    //   4: new 822	java/net/URL
    //   7: dup
    //   8: aload_0
    //   9: getfield 195	com/appbuilder/u846253p1176378/AppBuilder:xmlUrl	Ljava/lang/String;
    //   12: invokespecial 823	java/net/URL:<init>	(Ljava/lang/String;)V
    //   15: astore 4
    //   17: aload 4
    //   19: invokevirtual 827	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   22: astore 5
    //   24: new 1392	java/io/InputStreamReader
    //   27: dup
    //   28: aload 5
    //   30: invokevirtual 1395	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   33: invokespecial 1398	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   36: astore 6
    //   38: invokestatic 1390	java/lang/System:currentTimeMillis	()J
    //   41: lload_2
    //   42: lsub
    //   43: lstore 7
    //   45: aload_0
    //   46: invokestatic 774	com/appbuilder/u846253p1176378/tools/Tools:checkNetwork	(Landroid/content/Context;)I
    //   49: istore 9
    //   51: iconst_0
    //   52: istore 10
    //   54: iload 9
    //   56: iconst_2
    //   57: if_icmpne +22 -> 79
    //   60: lload 7
    //   62: ldc2_w 1399
    //   65: lcmp
    //   66: istore 119
    //   68: iconst_0
    //   69: istore 10
    //   71: iload 119
    //   73: ifle +6 -> 79
    //   76: iconst_1
    //   77: istore 10
    //   79: new 1402	java/io/BufferedReader
    //   82: dup
    //   83: aload 6
    //   85: invokespecial 1405	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   88: astore 11
    //   90: invokestatic 1411	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   93: astore 12
    //   95: new 532	java/io/File
    //   98: dup
    //   99: aload 12
    //   101: aload_0
    //   102: getfield 159	com/appbuilder/u846253p1176378/AppBuilder:fileName	Ljava/lang/String;
    //   105: invokespecial 1414	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   108: astore 13
    //   110: aload 11
    //   112: new 1416	java/io/FileOutputStream
    //   115: dup
    //   116: aload 13
    //   118: invokespecial 1419	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   121: invokestatic 1425	org/apache/commons/io/IOUtils:copy	(Ljava/io/Reader;Ljava/io/OutputStream;)V
    //   124: aload 11
    //   126: invokevirtual 1428	java/io/BufferedReader:close	()V
    //   129: iconst_1
    //   130: istore 14
    //   132: invokestatic 1411	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   135: astore 116
    //   137: new 532	java/io/File
    //   140: dup
    //   141: aload 116
    //   143: aload_0
    //   144: getfield 159	com/appbuilder/u846253p1176378/AppBuilder:fileName	Ljava/lang/String;
    //   147: invokespecial 1414	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   150: astore 117
    //   152: new 1430	java/io/FileReader
    //   155: dup
    //   156: aload 117
    //   158: invokespecial 1431	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   161: astore 118
    //   163: aload 118
    //   165: aconst_null
    //   166: invokestatic 1437	android/util/Xml:parse	(Ljava/io/Reader;Lorg/xml/sax/ContentHandler;)V
    //   169: aload_0
    //   170: getfield 161	com/appbuilder/u846253p1176378/AppBuilder:sdAvailable	Z
    //   173: ifeq +195 -> 368
    //   176: iload 14
    //   178: ifeq +190 -> 368
    //   181: invokestatic 1411	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   184: astore 17
    //   186: new 532	java/io/File
    //   189: dup
    //   190: aload 17
    //   192: aload_0
    //   193: getfield 159	com/appbuilder/u846253p1176378/AppBuilder:fileName	Ljava/lang/String;
    //   196: invokespecial 1414	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   199: astore 18
    //   201: new 1439	java/io/FileInputStream
    //   204: dup
    //   205: aload 18
    //   207: invokespecial 1440	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   210: astore 19
    //   212: aload_0
    //   213: aload 19
    //   215: invokevirtual 1443	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/io/FileInputStream;)Ljava/lang/String;
    //   218: astore 20
    //   220: new 532	java/io/File
    //   223: dup
    //   224: new 492	java/lang/StringBuilder
    //   227: dup
    //   228: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   231: aload_0
    //   232: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   235: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: ldc_w 1445
    //   241: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   250: astore 21
    //   252: aload 21
    //   254: invokevirtual 547	java/io/File:exists	()Z
    //   257: ifeq +769 -> 1026
    //   260: new 492	java/lang/StringBuilder
    //   263: dup
    //   264: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   267: astore 22
    //   269: new 1430	java/io/FileReader
    //   272: dup
    //   273: aload 21
    //   275: invokespecial 1431	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   278: astore 23
    //   280: new 1402	java/io/BufferedReader
    //   283: dup
    //   284: aload 23
    //   286: invokespecial 1405	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   289: astore 24
    //   291: aload 24
    //   293: invokevirtual 1448	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   296: astore 109
    //   298: aload 109
    //   300: ifnull +612 -> 912
    //   303: aload 22
    //   305: aload 109
    //   307: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: pop
    //   311: goto -20 -> 291
    //   314: astore 25
    //   316: aload 20
    //   318: aload 22
    //   320: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: invokevirtual 1317	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   326: ifeq +594 -> 920
    //   329: new 532	java/io/File
    //   332: dup
    //   333: new 492	java/lang/StringBuilder
    //   336: dup
    //   337: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   340: aload_0
    //   341: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   344: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: ldc_w 1450
    //   350: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   356: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   359: invokevirtual 547	java/io/File:exists	()Z
    //   362: ifeq +6 -> 368
    //   365: iconst_1
    //   366: istore 10
    //   368: iconst_0
    //   369: istore 27
    //   371: iload 10
    //   373: ifeq +518 -> 891
    //   376: new 532	java/io/File
    //   379: dup
    //   380: new 492	java/lang/StringBuilder
    //   383: dup
    //   384: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   387: aload_0
    //   388: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   391: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   394: ldc_w 1450
    //   397: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   403: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   406: astore 28
    //   408: new 532	java/io/File
    //   411: dup
    //   412: new 492	java/lang/StringBuilder
    //   415: dup
    //   416: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   419: aload_0
    //   420: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   423: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: ldc_w 1445
    //   429: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   435: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   438: astore 29
    //   440: aload 28
    //   442: invokevirtual 547	java/io/File:exists	()Z
    //   445: istore 30
    //   447: iload 30
    //   449: ifeq +707 -> 1156
    //   452: aconst_null
    //   453: astore 69
    //   455: new 1439	java/io/FileInputStream
    //   458: dup
    //   459: aload 28
    //   461: invokespecial 1440	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   464: astore 70
    //   466: new 1452	java/io/ObjectInputStream
    //   469: dup
    //   470: aload 70
    //   472: invokespecial 1453	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   475: astore 71
    //   477: aload_0
    //   478: aload 71
    //   480: invokevirtual 1457	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   483: checkcast 148	com/appbuilder/u846253p1176378/AppConfigure
    //   486: putfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   489: aload 71
    //   491: invokevirtual 1458	java/io/ObjectInputStream:close	()V
    //   494: invokestatic 1411	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   497: astore 76
    //   499: new 532	java/io/File
    //   502: dup
    //   503: aload 76
    //   505: aload_0
    //   506: getfield 159	com/appbuilder/u846253p1176378/AppBuilder:fileName	Ljava/lang/String;
    //   509: invokespecial 1414	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   512: astore 77
    //   514: new 1439	java/io/FileInputStream
    //   517: dup
    //   518: aload 77
    //   520: invokespecial 1440	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   523: astore 78
    //   525: new 1460	com/appbuilder/u846253p1176378/AppConfigureParser
    //   528: dup
    //   529: aload 78
    //   531: invokespecial 1461	com/appbuilder/u846253p1176378/AppConfigureParser:<init>	(Ljava/io/InputStream;)V
    //   534: astore 79
    //   536: aload 79
    //   538: invokevirtual 1465	com/appbuilder/u846253p1176378/AppConfigureParser:parseSAX	()Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   541: astore 80
    //   543: aload_0
    //   544: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   547: aload 80
    //   549: invokevirtual 1466	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   552: ifeq +540 -> 1092
    //   555: iconst_1
    //   556: istore 81
    //   558: aload_0
    //   559: aload 80
    //   561: invokevirtual 1469	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundImageUrl	()Ljava/lang/String;
    //   564: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   567: astore 82
    //   569: new 492	java/lang/StringBuilder
    //   572: dup
    //   573: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   576: aload_0
    //   577: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   580: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: ldc_w 779
    //   586: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   589: aload 82
    //   591: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   594: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   597: astore 83
    //   599: new 532	java/io/File
    //   602: dup
    //   603: aload 83
    //   605: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   608: astore 84
    //   610: aload 84
    //   612: invokevirtual 547	java/io/File:exists	()Z
    //   615: ifne +1236 -> 1851
    //   618: iconst_0
    //   619: istore 81
    //   621: goto +1230 -> 1851
    //   624: aload 80
    //   626: invokevirtual 1472	com/appbuilder/u846253p1176378/AppConfigure:getButtonsCount	()I
    //   629: istore 86
    //   631: iload 85
    //   633: iload 86
    //   635: if_icmpge +1227 -> 1862
    //   638: aload_0
    //   639: aload 80
    //   641: iload 85
    //   643: invokevirtual 1476	com/appbuilder/u846253p1176378/AppConfigure:getButtonAtIndex	(I)Lcom/appbuilder/u846253p1176378/WidgetUIButton;
    //   646: invokevirtual 777	com/appbuilder/u846253p1176378/WidgetUIButton:getImageSourceUrl	()Ljava/lang/String;
    //   649: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   652: astore 87
    //   654: new 492	java/lang/StringBuilder
    //   657: dup
    //   658: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   661: aload_0
    //   662: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   665: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   668: ldc_w 779
    //   671: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   674: aload 87
    //   676: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   679: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   682: astore 88
    //   684: new 532	java/io/File
    //   687: dup
    //   688: aload 88
    //   690: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   693: astore 89
    //   695: aload 89
    //   697: invokevirtual 547	java/io/File:exists	()Z
    //   700: ifne +374 -> 1074
    //   703: iconst_0
    //   704: istore 81
    //   706: goto +1156 -> 1862
    //   709: aload 80
    //   711: invokevirtual 1479	com/appbuilder/u846253p1176378/AppConfigure:getImagesCount	()I
    //   714: istore 91
    //   716: iload 90
    //   718: iload 91
    //   720: if_icmpge +1153 -> 1873
    //   723: aload_0
    //   724: aload 80
    //   726: iload 90
    //   728: invokevirtual 1483	com/appbuilder/u846253p1176378/AppConfigure:getImageAtIndex	(I)Lcom/appbuilder/u846253p1176378/WidgetUIImage;
    //   731: invokevirtual 965	com/appbuilder/u846253p1176378/WidgetUIImage:getSourceUrl	()Ljava/lang/String;
    //   734: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   737: astore 92
    //   739: new 492	java/lang/StringBuilder
    //   742: dup
    //   743: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   746: aload_0
    //   747: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   750: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   753: ldc_w 779
    //   756: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   759: aload 92
    //   761: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   764: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   767: astore 93
    //   769: new 532	java/io/File
    //   772: dup
    //   773: aload 93
    //   775: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   778: astore 94
    //   780: aload 94
    //   782: invokevirtual 547	java/io/File:exists	()Z
    //   785: ifne +295 -> 1080
    //   788: iconst_0
    //   789: istore 81
    //   791: goto +1082 -> 1873
    //   794: aload 80
    //   796: invokevirtual 440	com/appbuilder/u846253p1176378/AppConfigure:getTabsCount	()I
    //   799: istore 96
    //   801: iload 95
    //   803: iload 96
    //   805: if_icmpge +75 -> 880
    //   808: aload_0
    //   809: aload 80
    //   811: iload 95
    //   813: invokevirtual 1266	com/appbuilder/u846253p1176378/AppConfigure:getTabAtIndex	(I)Lcom/appbuilder/u846253p1176378/WidgetUITab;
    //   816: invokevirtual 1200	com/appbuilder/u846253p1176378/WidgetUITab:getIconCache	()Ljava/lang/String;
    //   819: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   822: astore 97
    //   824: new 492	java/lang/StringBuilder
    //   827: dup
    //   828: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   831: aload_0
    //   832: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   835: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   838: ldc_w 779
    //   841: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   844: aload 97
    //   846: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   849: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   852: astore 98
    //   854: new 532	java/io/File
    //   857: dup
    //   858: aload 98
    //   860: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   863: astore 99
    //   865: aload 99
    //   867: invokevirtual 547	java/io/File:exists	()Z
    //   870: istore 100
    //   872: iload 100
    //   874: ifne +212 -> 1086
    //   877: iconst_0
    //   878: istore 81
    //   880: iconst_0
    //   881: istore 27
    //   883: iload 81
    //   885: ifeq +6 -> 891
    //   888: iconst_1
    //   889: istore 27
    //   891: iload 27
    //   893: ifeq +997 -> 1890
    //   896: iconst_1
    //   897: ireturn
    //   898: astore_1
    //   899: iconst_0
    //   900: ireturn
    //   901: astore 15
    //   903: iconst_1
    //   904: istore 10
    //   906: iconst_0
    //   907: istore 14
    //   909: goto -740 -> 169
    //   912: aload 24
    //   914: invokevirtual 1428	java/io/BufferedReader:close	()V
    //   917: goto -601 -> 316
    //   920: aload 21
    //   922: invokevirtual 762	java/io/File:delete	()Z
    //   925: pop
    //   926: aload 21
    //   928: invokevirtual 1486	java/io/File:createNewFile	()Z
    //   931: pop
    //   932: new 532	java/io/File
    //   935: dup
    //   936: new 492	java/lang/StringBuilder
    //   939: dup
    //   940: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   943: aload_0
    //   944: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   947: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   950: getstatic 535	java/io/File:separator	Ljava/lang/String;
    //   953: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   956: ldc_w 1488
    //   959: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   962: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   965: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   968: astore 104
    //   970: aload 104
    //   972: invokevirtual 547	java/io/File:exists	()Z
    //   975: ifeq +9 -> 984
    //   978: aload 104
    //   980: invokevirtual 762	java/io/File:delete	()Z
    //   983: pop
    //   984: new 1490	java/io/FileWriter
    //   987: dup
    //   988: aload 21
    //   990: invokespecial 1491	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   993: astore 105
    //   995: new 1493	java/io/BufferedWriter
    //   998: dup
    //   999: aload 105
    //   1001: invokespecial 1496	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   1004: astore 106
    //   1006: aload 106
    //   1008: aload 20
    //   1010: invokevirtual 1499	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   1013: aload 106
    //   1015: invokevirtual 1500	java/io/BufferedWriter:close	()V
    //   1018: goto -650 -> 368
    //   1021: astore 107
    //   1023: goto -655 -> 368
    //   1026: aload 21
    //   1028: invokevirtual 1486	java/io/File:createNewFile	()Z
    //   1031: pop
    //   1032: new 1490	java/io/FileWriter
    //   1035: dup
    //   1036: aload 21
    //   1038: invokespecial 1491	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   1041: astore 113
    //   1043: new 1493	java/io/BufferedWriter
    //   1046: dup
    //   1047: aload 113
    //   1049: invokespecial 1496	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   1052: astore 114
    //   1054: aload 114
    //   1056: aload 20
    //   1058: invokevirtual 1499	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   1061: aload 114
    //   1063: invokevirtual 1500	java/io/BufferedWriter:close	()V
    //   1066: goto -698 -> 368
    //   1069: astore 115
    //   1071: goto -703 -> 368
    //   1074: iinc 85 1
    //   1077: goto -453 -> 624
    //   1080: iinc 90 1
    //   1083: goto -374 -> 709
    //   1086: iinc 95 1
    //   1089: goto -295 -> 794
    //   1092: iconst_0
    //   1093: istore 27
    //   1095: goto -204 -> 891
    //   1098: astore 101
    //   1100: aload 69
    //   1102: ifnull +8 -> 1110
    //   1105: aload 69
    //   1107: invokevirtual 1458	java/io/ObjectInputStream:close	()V
    //   1110: aload 28
    //   1112: invokevirtual 762	java/io/File:delete	()Z
    //   1115: pop
    //   1116: aload 29
    //   1118: invokevirtual 547	java/io/File:exists	()Z
    //   1121: istore 74
    //   1123: iconst_0
    //   1124: istore 27
    //   1126: iload 74
    //   1128: ifeq -237 -> 891
    //   1131: aload 29
    //   1133: invokevirtual 762	java/io/File:delete	()Z
    //   1136: pop
    //   1137: iconst_0
    //   1138: istore 27
    //   1140: goto -249 -> 891
    //   1143: astore 16
    //   1145: aload_0
    //   1146: ldc_w 1502
    //   1149: aload 16
    //   1151: invokespecial 338	com/appbuilder/u846253p1176378/AppBuilder:logError	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   1154: iconst_0
    //   1155: ireturn
    //   1156: aload 29
    //   1158: invokevirtual 547	java/io/File:exists	()Z
    //   1161: ifeq +723 -> 1884
    //   1164: aload 29
    //   1166: invokevirtual 762	java/io/File:delete	()Z
    //   1169: pop
    //   1170: goto +714 -> 1884
    //   1173: invokestatic 1411	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   1176: astore 31
    //   1178: new 532	java/io/File
    //   1181: dup
    //   1182: aload 31
    //   1184: aload_0
    //   1185: getfield 159	com/appbuilder/u846253p1176378/AppBuilder:fileName	Ljava/lang/String;
    //   1188: invokespecial 1414	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   1191: astore 32
    //   1193: new 1439	java/io/FileInputStream
    //   1196: dup
    //   1197: aload 32
    //   1199: invokespecial 1440	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   1202: astore 33
    //   1204: aload_0
    //   1205: new 1460	com/appbuilder/u846253p1176378/AppConfigureParser
    //   1208: dup
    //   1209: aload 33
    //   1211: invokespecial 1461	com/appbuilder/u846253p1176378/AppConfigureParser:<init>	(Ljava/io/InputStream;)V
    //   1214: invokevirtual 1465	com/appbuilder/u846253p1176378/AppConfigureParser:parseSAX	()Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1217: putfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1220: aload_0
    //   1221: getfield 161	com/appbuilder/u846253p1176378/AppBuilder:sdAvailable	Z
    //   1224: ifeq +606 -> 1830
    //   1227: new 237	java/util/ArrayList
    //   1230: dup
    //   1231: invokespecial 238	java/util/ArrayList:<init>	()V
    //   1234: astore 34
    //   1236: aload_0
    //   1237: aload_0
    //   1238: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1241: invokevirtual 1469	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundImageUrl	()Ljava/lang/String;
    //   1244: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   1247: astore 35
    //   1249: new 492	java/lang/StringBuilder
    //   1252: dup
    //   1253: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   1256: aload_0
    //   1257: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   1260: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1263: ldc_w 779
    //   1266: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1269: aload 35
    //   1271: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1274: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1277: astore 36
    //   1279: new 532	java/io/File
    //   1282: dup
    //   1283: aload 36
    //   1285: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   1288: astore 37
    //   1290: aload 37
    //   1292: invokevirtual 547	java/io/File:exists	()Z
    //   1295: ifeq +22 -> 1317
    //   1298: aload_0
    //   1299: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1302: aload 36
    //   1304: invokevirtual 1505	com/appbuilder/u846253p1176378/AppConfigure:setBackgroundImageCache	(Ljava/lang/String;)V
    //   1307: aload_0
    //   1308: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1311: getstatic 816	com/appbuilder/u846253p1176378/DownloadStatus:SUCCESS	Lcom/appbuilder/u846253p1176378/DownloadStatus;
    //   1314: invokevirtual 1508	com/appbuilder/u846253p1176378/AppConfigure:setBackgroundDownloaded	(Lcom/appbuilder/u846253p1176378/DownloadStatus;)V
    //   1317: aload 34
    //   1319: aload 35
    //   1321: invokevirtual 1052	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1324: pop
    //   1325: iconst_0
    //   1326: istore 39
    //   1328: aload_0
    //   1329: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1332: invokevirtual 1472	com/appbuilder/u846253p1176378/AppConfigure:getButtonsCount	()I
    //   1335: istore 40
    //   1337: iload 39
    //   1339: iload 40
    //   1341: if_icmpge +567 -> 1908
    //   1344: aload_0
    //   1345: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1348: iload 39
    //   1350: invokevirtual 1476	com/appbuilder/u846253p1176378/AppConfigure:getButtonAtIndex	(I)Lcom/appbuilder/u846253p1176378/WidgetUIButton;
    //   1353: astore 41
    //   1355: aload_0
    //   1356: aload 41
    //   1358: invokevirtual 777	com/appbuilder/u846253p1176378/WidgetUIButton:getImageSourceUrl	()Ljava/lang/String;
    //   1361: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   1364: astore 42
    //   1366: new 492	java/lang/StringBuilder
    //   1369: dup
    //   1370: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   1373: aload_0
    //   1374: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   1377: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1380: ldc_w 779
    //   1383: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1386: aload 42
    //   1388: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1391: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1394: astore 43
    //   1396: new 532	java/io/File
    //   1399: dup
    //   1400: aload 43
    //   1402: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   1405: astore 44
    //   1407: aload 44
    //   1409: invokevirtual 547	java/io/File:exists	()Z
    //   1412: ifeq +29 -> 1441
    //   1415: aload 41
    //   1417: aload 43
    //   1419: invokevirtual 755	com/appbuilder/u846253p1176378/WidgetUIButton:setImageSourceCache	(Ljava/lang/String;)V
    //   1422: aload 41
    //   1424: getstatic 816	com/appbuilder/u846253p1176378/DownloadStatus:SUCCESS	Lcom/appbuilder/u846253p1176378/DownloadStatus;
    //   1427: invokevirtual 820	com/appbuilder/u846253p1176378/WidgetUIButton:setDownloadStatus	(Lcom/appbuilder/u846253p1176378/DownloadStatus;)V
    //   1430: aload_0
    //   1431: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1434: iload 39
    //   1436: aload 41
    //   1438: invokevirtual 1512	com/appbuilder/u846253p1176378/AppConfigure:setButtonAtIndex	(ILcom/appbuilder/u846253p1176378/WidgetUIButton;)V
    //   1441: aload 34
    //   1443: aload 42
    //   1445: invokevirtual 1052	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1448: pop
    //   1449: iinc 39 1
    //   1452: goto -124 -> 1328
    //   1455: aload_0
    //   1456: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1459: invokevirtual 1479	com/appbuilder/u846253p1176378/AppConfigure:getImagesCount	()I
    //   1462: istore 47
    //   1464: iload 46
    //   1466: iload 47
    //   1468: if_icmpge +446 -> 1914
    //   1471: aload_0
    //   1472: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1475: iload 46
    //   1477: invokevirtual 1483	com/appbuilder/u846253p1176378/AppConfigure:getImageAtIndex	(I)Lcom/appbuilder/u846253p1176378/WidgetUIImage;
    //   1480: astore 48
    //   1482: aload_0
    //   1483: aload 48
    //   1485: invokevirtual 965	com/appbuilder/u846253p1176378/WidgetUIImage:getSourceUrl	()Ljava/lang/String;
    //   1488: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   1491: astore 49
    //   1493: new 492	java/lang/StringBuilder
    //   1496: dup
    //   1497: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   1500: aload_0
    //   1501: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   1504: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1507: ldc_w 779
    //   1510: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1513: aload 49
    //   1515: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1518: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1521: astore 50
    //   1523: new 532	java/io/File
    //   1526: dup
    //   1527: aload 50
    //   1529: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   1532: astore 51
    //   1534: aload 51
    //   1536: invokevirtual 547	java/io/File:exists	()Z
    //   1539: ifeq +29 -> 1568
    //   1542: aload 48
    //   1544: aload 50
    //   1546: invokevirtual 958	com/appbuilder/u846253p1176378/WidgetUIImage:setSourceCache	(Ljava/lang/String;)V
    //   1549: aload 48
    //   1551: getstatic 816	com/appbuilder/u846253p1176378/DownloadStatus:SUCCESS	Lcom/appbuilder/u846253p1176378/DownloadStatus;
    //   1554: invokevirtual 966	com/appbuilder/u846253p1176378/WidgetUIImage:setDownloadStatus	(Lcom/appbuilder/u846253p1176378/DownloadStatus;)V
    //   1557: aload_0
    //   1558: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1561: iload 46
    //   1563: aload 48
    //   1565: invokevirtual 1516	com/appbuilder/u846253p1176378/AppConfigure:setImageAtIndex	(ILcom/appbuilder/u846253p1176378/WidgetUIImage;)V
    //   1568: aload 34
    //   1570: aload 49
    //   1572: invokevirtual 1052	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1575: pop
    //   1576: iinc 46 1
    //   1579: goto -124 -> 1455
    //   1582: aload_0
    //   1583: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1586: invokevirtual 440	com/appbuilder/u846253p1176378/AppConfigure:getTabsCount	()I
    //   1589: istore 54
    //   1591: iload 53
    //   1593: iload 54
    //   1595: if_icmpge +114 -> 1709
    //   1598: aload_0
    //   1599: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1602: iload 53
    //   1604: invokevirtual 1266	com/appbuilder/u846253p1176378/AppConfigure:getTabAtIndex	(I)Lcom/appbuilder/u846253p1176378/WidgetUITab;
    //   1607: astore 55
    //   1609: aload_0
    //   1610: aload 55
    //   1612: invokevirtual 1200	com/appbuilder/u846253p1176378/WidgetUITab:getIconCache	()Ljava/lang/String;
    //   1615: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   1618: astore 56
    //   1620: new 492	java/lang/StringBuilder
    //   1623: dup
    //   1624: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   1627: aload_0
    //   1628: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   1631: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1634: ldc_w 779
    //   1637: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1640: aload 56
    //   1642: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1645: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1648: astore 57
    //   1650: new 532	java/io/File
    //   1653: dup
    //   1654: aload 57
    //   1656: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   1659: astore 58
    //   1661: aload 58
    //   1663: invokevirtual 547	java/io/File:exists	()Z
    //   1666: ifeq +29 -> 1695
    //   1669: aload 55
    //   1671: aload 57
    //   1673: invokevirtual 1240	com/appbuilder/u846253p1176378/WidgetUITab:setIconCache	(Ljava/lang/String;)V
    //   1676: aload 55
    //   1678: getstatic 816	com/appbuilder/u846253p1176378/DownloadStatus:SUCCESS	Lcom/appbuilder/u846253p1176378/DownloadStatus;
    //   1681: invokevirtual 1259	com/appbuilder/u846253p1176378/WidgetUITab:setDownloadStatus	(Lcom/appbuilder/u846253p1176378/DownloadStatus;)V
    //   1684: aload_0
    //   1685: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1688: iload 53
    //   1690: aload 55
    //   1692: invokevirtual 1520	com/appbuilder/u846253p1176378/AppConfigure:setTabAtIndex	(ILcom/appbuilder/u846253p1176378/WidgetUITab;)V
    //   1695: aload 34
    //   1697: aload 56
    //   1699: invokevirtual 1052	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1702: pop
    //   1703: iinc 53 1
    //   1706: goto -124 -> 1582
    //   1709: new 532	java/io/File
    //   1712: dup
    //   1713: new 492	java/lang/StringBuilder
    //   1716: dup
    //   1717: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   1720: aload_0
    //   1721: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   1724: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1727: ldc_w 779
    //   1730: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1733: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1736: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   1739: invokevirtual 1524	java/io/File:listFiles	()[Ljava/io/File;
    //   1742: astore 60
    //   1744: iconst_0
    //   1745: istore 61
    //   1747: aload 60
    //   1749: arraylength
    //   1750: istore 62
    //   1752: iload 61
    //   1754: iload 62
    //   1756: if_icmpge +74 -> 1830
    //   1759: aload 60
    //   1761: iload 61
    //   1763: aaload
    //   1764: invokevirtual 1527	java/io/File:getName	()Ljava/lang/String;
    //   1767: astore 63
    //   1769: iconst_0
    //   1770: istore 64
    //   1772: iconst_0
    //   1773: istore 65
    //   1775: aload 34
    //   1777: invokevirtual 1530	java/util/ArrayList:size	()I
    //   1780: istore 66
    //   1782: iload 65
    //   1784: iload 66
    //   1786: if_icmpge +24 -> 1810
    //   1789: aload 63
    //   1791: aload 34
    //   1793: iload 65
    //   1795: invokevirtual 1045	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1798: invokevirtual 1317	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1801: ifeq +119 -> 1920
    //   1804: iconst_1
    //   1805: istore 64
    //   1807: goto +113 -> 1920
    //   1810: iload 64
    //   1812: ifne +12 -> 1824
    //   1815: aload 60
    //   1817: iload 61
    //   1819: aaload
    //   1820: invokevirtual 762	java/io/File:delete	()Z
    //   1823: pop
    //   1824: iinc 61 1
    //   1827: goto -80 -> 1747
    //   1830: iconst_1
    //   1831: ireturn
    //   1832: astore 72
    //   1834: aload 71
    //   1836: astore 69
    //   1838: goto -738 -> 1100
    //   1841: astore 111
    //   1843: goto -1475 -> 368
    //   1846: astore 26
    //   1848: goto -1480 -> 368
    //   1851: iload 81
    //   1853: ifeq +9 -> 1862
    //   1856: iconst_0
    //   1857: istore 85
    //   1859: goto -1235 -> 624
    //   1862: iload 81
    //   1864: ifeq +9 -> 1873
    //   1867: iconst_0
    //   1868: istore 90
    //   1870: goto -1161 -> 709
    //   1873: iload 81
    //   1875: ifeq -995 -> 880
    //   1878: iconst_0
    //   1879: istore 95
    //   1881: goto -1087 -> 794
    //   1884: iconst_0
    //   1885: istore 27
    //   1887: goto -996 -> 891
    //   1890: iload 14
    //   1892: ifne +5 -> 1897
    //   1895: iconst_0
    //   1896: ireturn
    //   1897: lload 7
    //   1899: ldc2_w 1399
    //   1902: lcmp
    //   1903: ifle -730 -> 1173
    //   1906: iconst_0
    //   1907: ireturn
    //   1908: iconst_0
    //   1909: istore 46
    //   1911: goto -456 -> 1455
    //   1914: iconst_0
    //   1915: istore 53
    //   1917: goto -335 -> 1582
    //   1920: iinc 65 1
    //   1923: goto -148 -> 1775
    //
    // Exception table:
    //   from	to	target	type
    //   269	291	314	java/lang/Exception
    //   291	298	314	java/lang/Exception
    //   303	311	314	java/lang/Exception
    //   912	917	314	java/lang/Exception
    //   0	51	898	java/lang/Exception
    //   79	129	898	java/lang/Exception
    //   132	169	901	java/lang/Exception
    //   984	1018	1021	java/lang/Exception
    //   1032	1066	1069	java/lang/Exception
    //   455	477	1098	java/lang/Exception
    //   169	176	1143	java/lang/Exception
    //   181	269	1143	java/lang/Exception
    //   316	365	1143	java/lang/Exception
    //   376	447	1143	java/lang/Exception
    //   920	984	1143	java/lang/Exception
    //   1026	1032	1143	java/lang/Exception
    //   1105	1110	1143	java/lang/Exception
    //   1110	1123	1143	java/lang/Exception
    //   1131	1137	1143	java/lang/Exception
    //   1156	1170	1143	java/lang/Exception
    //   1173	1317	1143	java/lang/Exception
    //   1317	1325	1143	java/lang/Exception
    //   1328	1337	1143	java/lang/Exception
    //   1344	1441	1143	java/lang/Exception
    //   1441	1449	1143	java/lang/Exception
    //   1455	1464	1143	java/lang/Exception
    //   1471	1568	1143	java/lang/Exception
    //   1568	1576	1143	java/lang/Exception
    //   1582	1591	1143	java/lang/Exception
    //   1598	1695	1143	java/lang/Exception
    //   1695	1703	1143	java/lang/Exception
    //   1709	1744	1143	java/lang/Exception
    //   1747	1752	1143	java/lang/Exception
    //   1759	1769	1143	java/lang/Exception
    //   1775	1782	1143	java/lang/Exception
    //   1789	1804	1143	java/lang/Exception
    //   1815	1824	1143	java/lang/Exception
    //   477	555	1832	java/lang/Exception
    //   558	618	1832	java/lang/Exception
    //   624	631	1832	java/lang/Exception
    //   638	703	1832	java/lang/Exception
    //   709	716	1832	java/lang/Exception
    //   723	788	1832	java/lang/Exception
    //   794	801	1832	java/lang/Exception
    //   808	872	1832	java/lang/Exception
    //   1026	1032	1841	java/io/IOException
    //   1032	1066	1841	java/io/IOException
    //   920	984	1846	java/io/IOException
    //   984	1018	1846	java/io/IOException
  }

  private void logError(Exception paramException)
  {
    Log.e("com.ibuildapp.appbuilder", "", paramException);
  }

  private void logError(String paramString, Exception paramException)
  {
    StatisticsCollector.newError(paramException, paramString);
  }

  private void logWarning(Exception paramException)
  {
    Log.w("com.ibuildapp.appbuilder", paramException);
  }

  private String md5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      int i = arrayOfByte.length;
      StringBuilder localStringBuilder = new StringBuilder(i << 1);
      for (int j = 0; j < i; j++)
      {
        localStringBuilder.append(Character.forDigit((0xF0 & arrayOfByte[j]) >> 4, 16));
        localStringBuilder.append(Character.forDigit(0xF & arrayOfByte[j], 16));
      }
      String str = localStringBuilder.toString();
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      Log.w("WebPlugin CREATE MD5", localNoSuchAlgorithmException);
    }
    return null;
  }

  private void prepareAnimationObjects()
  {
    this.animShowMenu = new TranslateAnimation(0.0F, this.screenWidth - (int)(0.15D * this.screenWidth), 0.0F, 0.0F);
    this.animShowMenu.setInterpolator(new SmoothInterpolator());
    this.animShowMenu.setDuration(400L);
    this.animShowMenu.setFillEnabled(true);
    this.animShowMenu.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams((int)(AppBuilder.this.screenWidth + 0.85D * AppBuilder.this.screenWidth), -1);
        localLayoutParams.gravity = 3;
        localLayoutParams.setMargins(0, 0, 0, 0);
        AppBuilder.this.rootContainer.setLayoutParams(localLayoutParams);
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    this.animHideMenu = new TranslateAnimation(0.0F, -(this.screenWidth - (int)(0.15D * this.screenWidth)), 0.0F, 0.0F);
    this.animHideMenu.setInterpolator(new SmoothInterpolator());
    this.animHideMenu.setDuration(400L);
    this.animHideMenu.setFillEnabled(true);
    this.animHideMenu.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams((int)(AppBuilder.this.screenWidth + 0.85D * AppBuilder.this.screenWidth), -1);
        localLayoutParams.gravity = 3;
        localLayoutParams.setMargins(-(AppBuilder.this.screenWidth - (int)(0.15D * AppBuilder.this.screenWidth)), 0, 0, 0);
        AppBuilder.this.rootContainer.setLayoutParams(localLayoutParams);
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    this.animShowDialog = new AlphaAnimation(0.0F, 1.0F);
    this.animShowDialog.setDuration(500L);
    this.animShowDialog.setFillAfter(true);
    this.animShowDialog.setInterpolator(new LinearInterpolator());
    this.animShowDialog.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        AppBuilder.this.dialogHolder.setVisibility(0);
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    this.animHideDialog = new AlphaAnimation(1.0F, 0.0F);
    this.animHideDialog.setDuration(500L);
    this.animHideDialog.setFillAfter(true);
    this.animHideDialog.setInterpolator(new LinearInterpolator());
    this.animHideDialog.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        AppBuilder.this.dialogHolder.removeAllViews();
        AppBuilder.this.dialogHolder.setVisibility(4);
        Log.e("com.ibuildapp.appbuilder", "dialogHolder.setVisibility(View.GONE)");
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
  }

  // ERROR //
  public static Bitmap proccessBitmap(String paramString)
  {
    // Byte code:
    //   0: new 1630	android/graphics/BitmapFactory$Options
    //   3: dup
    //   4: invokespecial 1631	android/graphics/BitmapFactory$Options:<init>	()V
    //   7: astore_1
    //   8: new 532	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: astore_2
    //   17: invokestatic 698	java/lang/System:gc	()V
    //   20: new 1439	java/io/FileInputStream
    //   23: dup
    //   24: aload_2
    //   25: invokespecial 1440	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   28: aconst_null
    //   29: aload_1
    //   30: invokestatic 1634	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   33: astore 15
    //   35: aload 15
    //   37: astore 7
    //   39: aload 7
    //   41: areturn
    //   42: astore 13
    //   44: ldc 174
    //   46: ldc 174
    //   48: invokestatic 734	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   51: pop
    //   52: aconst_null
    //   53: astore 7
    //   55: goto -16 -> 39
    //   58: astore 8
    //   60: ldc 174
    //   62: ldc 174
    //   64: invokestatic 734	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   67: pop
    //   68: aconst_null
    //   69: areturn
    //   70: astore_3
    //   71: ldc 174
    //   73: ldc 174
    //   75: invokestatic 734	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   78: pop
    //   79: invokestatic 698	java/lang/System:gc	()V
    //   82: new 1439	java/io/FileInputStream
    //   85: dup
    //   86: aload_2
    //   87: invokespecial 1440	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   90: aconst_null
    //   91: aload_1
    //   92: invokestatic 1634	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   95: astore 12
    //   97: aload 12
    //   99: astore 7
    //   101: goto -62 -> 39
    //   104: astore 10
    //   106: ldc 174
    //   108: ldc 174
    //   110: invokestatic 734	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aconst_null
    //   115: astore 7
    //   117: goto -78 -> 39
    //   120: astore 5
    //   122: ldc_w 766
    //   125: ldc_w 768
    //   128: invokestatic 403	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   131: pop
    //   132: aconst_null
    //   133: astore 7
    //   135: goto -96 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   17	35	42	java/lang/Exception
    //   8	17	58	java/lang/Exception
    //   44	52	58	java/lang/Exception
    //   71	82	58	java/lang/Exception
    //   106	114	58	java/lang/Exception
    //   122	132	58	java/lang/Exception
    //   17	35	70	java/lang/OutOfMemoryError
    //   82	97	104	java/lang/Exception
    //   82	97	120	java/lang/OutOfMemoryError
  }

  private void pushNotificationInit()
  {
    String str1;
    try
    {
      if (this.appConfig.getPushNotificationAccount().length() == 0)
        return;
      str1 = C2DMessaging.getRegistrationId(this);
      if (str1.length() == 0)
      {
        Intent localIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
        localIntent.putExtra("app", PendingIntent.getBroadcast(this, 0, new Intent(), 0));
        localIntent.putExtra("sender", this.appConfig.getPushNotificationAccount());
        startService(localIntent);
        Log.d("", "");
        return;
      }
    }
    catch (Exception localException1)
    {
      logError("AppBuilder.pushNotificationInit", localException1);
      return;
    }
    try
    {
      this.pushRegistrationUrl = (this.pushRegistrationUrl + "&token=" + str1);
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new URL(this.pushRegistrationUrl).openStream()));
      StringBuilder localStringBuilder = new StringBuilder();
      while (true)
      {
        String str2 = localBufferedReader.readLine();
        if (str2 == null)
          break;
        localStringBuilder.append(str2);
      }
      localBufferedReader.close();
      return;
    }
    catch (Exception localException2)
    {
    }
  }

  private void putDataInCache()
  {
    File localFile = new File(this.cachePath + "/cache.data");
    if (localFile.exists())
      localFile.delete();
    try
    {
      localFile.createNewFile();
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(localFile));
      localObjectOutputStream.writeObject(this.appConfig);
      localObjectOutputStream.close();
      Log.i("CACHE APP CONF", "success");
      return;
    }
    catch (Exception localException)
    {
      Log.w("CACHE APP CONF", localException);
      localFile.delete();
    }
  }

  private String readFileToString(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    BufferedReader localBufferedReader;
    try
    {
      localBufferedReader = new BufferedReader(new FileReader(new File(paramString)));
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        localStringBuilder.append(str);
        localStringBuilder.append("\n");
      }
    }
    catch (Exception localException)
    {
    }
    while (true)
    {
      return localStringBuilder.toString();
      localBufferedReader.close();
    }
  }

  private void reloadAppConfigure()
  {
    try
    {
      this.progressDialog = ProgressDialog.show(this, null, "Loading...", true);
      this.progressDialog.setCancelable(true);
      this.progressDialog.show();
      new Thread()
      {
        public void run()
        {
          try
          {
            boolean bool2 = AppBuilder.this.loadDatafromURL();
            bool1 = bool2;
            if (bool1)
            {
              AppBuilder.this.handler.sendEmptyMessage(1);
              AppBuilder.this.handler.sendEmptyMessage(8);
              AppBuilder.this.runOnUiThread(new Runnable()
              {
                public void run()
                {
                  try
                  {
                    AppBuilder.this.drawInterface();
                    return;
                  }
                  catch (Exception localException)
                  {
                  }
                }
              });
            }
            if (AppBuilder.this.progressDialog != null)
              AppBuilder.this.progressDialog.dismiss();
            return;
          }
          catch (Exception localException)
          {
            while (true)
              boolean bool1 = false;
          }
        }
      }
      .start();
      return;
    }
    catch (Exception localException)
    {
      logError("AppBuilder.reloadAppConfigure()", localException);
    }
  }

  private long sdAvailableBytes()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getBlockCount();
  }

  // ERROR //
  private void setBackgroundUseConfig(AppConfigure paramAppConfigure, RelativeLayout paramRelativeLayout1, RelativeLayout paramRelativeLayout2)
  {
    // Byte code:
    //   0: aload_3
    //   1: aload_1
    //   2: invokevirtual 1724	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundColor	()Ljava/lang/String;
    //   5: invokestatic 684	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   8: invokevirtual 1725	android/widget/RelativeLayout:setBackgroundColor	(I)V
    //   11: aload_1
    //   12: invokevirtual 1469	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundImageUrl	()Ljava/lang/String;
    //   15: astore 6
    //   17: aload 6
    //   19: invokevirtual 693	java/lang/String:length	()I
    //   22: ifle +471 -> 493
    //   25: aload_1
    //   26: invokevirtual 1728	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundImageCache	()Ljava/lang/String;
    //   29: invokevirtual 693	java/lang/String:length	()I
    //   32: istore 11
    //   34: iconst_0
    //   35: istore 12
    //   37: iload 11
    //   39: ifle +31 -> 70
    //   42: new 532	java/io/File
    //   45: dup
    //   46: aload_1
    //   47: invokevirtual 1728	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundImageCache	()Ljava/lang/String;
    //   50: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   53: astore 13
    //   55: aload 13
    //   57: invokevirtual 547	java/io/File:exists	()Z
    //   60: istore 14
    //   62: iload 14
    //   64: ifeq +58 -> 122
    //   67: iconst_1
    //   68: istore 12
    //   70: iload 12
    //   72: ifeq +176 -> 248
    //   75: invokestatic 698	java/lang/System:gc	()V
    //   78: invokestatic 698	java/lang/System:gc	()V
    //   81: aload_1
    //   82: invokevirtual 1728	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundImageCache	()Ljava/lang/String;
    //   85: invokestatic 1731	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   88: astore 39
    //   90: aload 39
    //   92: astore 32
    //   94: aload_2
    //   95: new 706	android/graphics/drawable/BitmapDrawable
    //   98: dup
    //   99: aload 32
    //   101: aload_0
    //   102: invokestatic 1735	com/appbuilder/sdk/android/Utils:BmpResizeDisplay	(Landroid/graphics/Bitmap;Landroid/app/Activity;)Landroid/graphics/Bitmap;
    //   105: invokespecial 723	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   108: invokevirtual 1736	android/widget/RelativeLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   111: ldc_w 1738
    //   114: ldc_w 955
    //   117: invokestatic 734	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   120: pop
    //   121: return
    //   122: aload_1
    //   123: ldc 174
    //   125: invokevirtual 1505	com/appbuilder/u846253p1176378/AppConfigure:setBackgroundImageCache	(Ljava/lang/String;)V
    //   128: iconst_0
    //   129: istore 12
    //   131: goto -61 -> 70
    //   134: astore 5
    //   136: return
    //   137: astore 37
    //   139: ldc 174
    //   141: ldc 174
    //   143: invokestatic 734	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   146: pop
    //   147: aconst_null
    //   148: astore 32
    //   150: goto -56 -> 94
    //   153: astore 24
    //   155: new 532	java/io/File
    //   158: dup
    //   159: aload_1
    //   160: invokevirtual 1728	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundImageCache	()Ljava/lang/String;
    //   163: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   166: astore 25
    //   168: aload 25
    //   170: invokevirtual 762	java/io/File:delete	()Z
    //   173: pop
    //   174: aload_0
    //   175: aload_1
    //   176: aload_2
    //   177: aload_3
    //   178: invokespecial 1101	com/appbuilder/u846253p1176378/AppBuilder:setBackgroundUseConfig	(Lcom/appbuilder/u846253p1176378/AppConfigure;Landroid/widget/RelativeLayout;Landroid/widget/RelativeLayout;)V
    //   181: return
    //   182: astore 26
    //   184: return
    //   185: astore 28
    //   187: ldc 174
    //   189: ldc 174
    //   191: invokestatic 734	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   194: pop
    //   195: invokestatic 698	java/lang/System:gc	()V
    //   198: aload_1
    //   199: invokevirtual 1728	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundImageCache	()Ljava/lang/String;
    //   202: invokestatic 1731	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   205: astore 36
    //   207: aload 36
    //   209: astore 32
    //   211: goto -117 -> 94
    //   214: astore 34
    //   216: ldc 174
    //   218: ldc 174
    //   220: invokestatic 734	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   223: pop
    //   224: aconst_null
    //   225: astore 32
    //   227: goto -133 -> 94
    //   230: astore 30
    //   232: ldc_w 766
    //   235: ldc_w 768
    //   238: invokestatic 403	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   241: pop
    //   242: aconst_null
    //   243: astore 32
    //   245: goto -151 -> 94
    //   248: aload_0
    //   249: invokestatic 774	com/appbuilder/u846253p1176378/tools/Tools:checkNetwork	(Landroid/content/Context;)I
    //   252: ifle +179 -> 431
    //   255: aload_0
    //   256: getfield 161	com/appbuilder/u846253p1176378/AppBuilder:sdAvailable	Z
    //   259: ifeq +112 -> 371
    //   262: new 492	java/lang/StringBuilder
    //   265: dup
    //   266: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   269: aload_0
    //   270: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   273: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: ldc_w 779
    //   279: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: aload_0
    //   283: aload 6
    //   285: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   288: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   294: astore 22
    //   296: aload_1
    //   297: aload 22
    //   299: invokevirtual 1505	com/appbuilder/u846253p1176378/AppConfigure:setBackgroundImageCache	(Ljava/lang/String;)V
    //   302: new 781	com/appbuilder/u846253p1176378/DownloadHelper
    //   305: dup
    //   306: aload_0
    //   307: aload 6
    //   309: aload 22
    //   311: aconst_null
    //   312: iconst_0
    //   313: iconst_1
    //   314: invokespecial 784	com/appbuilder/u846253p1176378/DownloadHelper:<init>	(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Lcom/appbuilder/u846253p1176378/DownloadHelperCallback;ZZ)V
    //   317: astore 23
    //   319: aload 23
    //   321: new 1740	com/appbuilder/u846253p1176378/AppBuilder$29
    //   324: dup
    //   325: aload_0
    //   326: invokespecial 1741	com/appbuilder/u846253p1176378/AppBuilder$29:<init>	(Lcom/appbuilder/u846253p1176378/AppBuilder;)V
    //   329: invokevirtual 791	com/appbuilder/u846253p1176378/DownloadHelper:setStartedRunnable	(Ljava/lang/Runnable;)V
    //   332: aload 23
    //   334: new 1743	com/appbuilder/u846253p1176378/AppBuilder$30
    //   337: dup
    //   338: aload_0
    //   339: aload 23
    //   341: aload_1
    //   342: invokespecial 1746	com/appbuilder/u846253p1176378/AppBuilder$30:<init>	(Lcom/appbuilder/u846253p1176378/AppBuilder;Lcom/appbuilder/u846253p1176378/DownloadHelper;Lcom/appbuilder/u846253p1176378/AppConfigure;)V
    //   345: invokevirtual 799	com/appbuilder/u846253p1176378/DownloadHelper:setFailedRunnable	(Ljava/lang/Runnable;)V
    //   348: aload 23
    //   350: new 1748	com/appbuilder/u846253p1176378/AppBuilder$31
    //   353: dup
    //   354: aload_0
    //   355: aload 22
    //   357: aload_2
    //   358: aload_1
    //   359: invokespecial 1751	com/appbuilder/u846253p1176378/AppBuilder$31:<init>	(Lcom/appbuilder/u846253p1176378/AppBuilder;Ljava/lang/String;Landroid/widget/RelativeLayout;Lcom/appbuilder/u846253p1176378/AppConfigure;)V
    //   362: invokevirtual 807	com/appbuilder/u846253p1176378/DownloadHelper:setSuccessRunnable	(Ljava/lang/Runnable;)V
    //   365: aload 23
    //   367: invokevirtual 810	com/appbuilder/u846253p1176378/DownloadHelper:start	()V
    //   370: return
    //   371: new 822	java/net/URL
    //   374: dup
    //   375: aload 6
    //   377: invokespecial 823	java/net/URL:<init>	(Ljava/lang/String;)V
    //   380: astore 18
    //   382: aload 18
    //   384: invokevirtual 827	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   387: checkcast 829	java/net/HttpURLConnection
    //   390: astore 21
    //   392: aload 21
    //   394: invokevirtual 832	java/net/HttpURLConnection:connect	()V
    //   397: aload_2
    //   398: new 706	android/graphics/drawable/BitmapDrawable
    //   401: dup
    //   402: aload 21
    //   404: invokevirtual 836	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   407: invokestatic 842	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   410: aload_0
    //   411: invokestatic 1735	com/appbuilder/sdk/android/Utils:BmpResizeDisplay	(Landroid/graphics/Bitmap;Landroid/app/Activity;)Landroid/graphics/Bitmap;
    //   414: invokespecial 723	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   417: invokevirtual 1736	android/widget/RelativeLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   420: aload_1
    //   421: getstatic 816	com/appbuilder/u846253p1176378/DownloadStatus:SUCCESS	Lcom/appbuilder/u846253p1176378/DownloadStatus;
    //   424: invokevirtual 1508	com/appbuilder/u846253p1176378/AppConfigure:setBackgroundDownloaded	(Lcom/appbuilder/u846253p1176378/DownloadStatus;)V
    //   427: return
    //   428: astore 20
    //   430: return
    //   431: aload_1
    //   432: invokevirtual 1754	com/appbuilder/u846253p1176378/AppConfigure:getmBackgorundImageData	()Ljava/lang/String;
    //   435: astore 15
    //   437: aload 15
    //   439: ifnull -318 -> 121
    //   442: aload_2
    //   443: new 706	android/graphics/drawable/BitmapDrawable
    //   446: dup
    //   447: new 847	java/io/ByteArrayInputStream
    //   450: dup
    //   451: aload_1
    //   452: invokevirtual 1754	com/appbuilder/u846253p1176378/AppConfigure:getmBackgorundImageData	()Ljava/lang/String;
    //   455: invokestatic 853	com/appbuilder/sdk/android/Base64:decode	(Ljava/lang/String;)[B
    //   458: invokespecial 856	java/io/ByteArrayInputStream:<init>	([B)V
    //   461: invokestatic 842	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   464: aload_0
    //   465: invokestatic 1735	com/appbuilder/sdk/android/Utils:BmpResizeDisplay	(Landroid/graphics/Bitmap;Landroid/app/Activity;)Landroid/graphics/Bitmap;
    //   468: invokespecial 723	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   471: invokevirtual 1736	android/widget/RelativeLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   474: aload_1
    //   475: getstatic 816	com/appbuilder/u846253p1176378/DownloadStatus:SUCCESS	Lcom/appbuilder/u846253p1176378/DownloadStatus;
    //   478: invokevirtual 1508	com/appbuilder/u846253p1176378/AppConfigure:setBackgroundDownloaded	(Lcom/appbuilder/u846253p1176378/DownloadStatus;)V
    //   481: return
    //   482: astore 16
    //   484: ldc 174
    //   486: ldc 174
    //   488: invokestatic 403	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   491: pop
    //   492: return
    //   493: aload_1
    //   494: invokevirtual 1724	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundColor	()Ljava/lang/String;
    //   497: invokevirtual 693	java/lang/String:length	()I
    //   500: istore 7
    //   502: iload 7
    //   504: ifle +35 -> 539
    //   507: aload_2
    //   508: new 706	android/graphics/drawable/BitmapDrawable
    //   511: dup
    //   512: aload_1
    //   513: invokevirtual 1724	com/appbuilder/u846253p1176378/AppConfigure:getBackgroundColor	()Ljava/lang/String;
    //   516: invokestatic 1757	com/appbuilder/sdk/android/Utils:CreateSquareColorBitmap	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   519: aload_0
    //   520: invokestatic 1735	com/appbuilder/sdk/android/Utils:BmpResizeDisplay	(Landroid/graphics/Bitmap;Landroid/app/Activity;)Landroid/graphics/Bitmap;
    //   523: invokespecial 723	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   526: invokevirtual 1736	android/widget/RelativeLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   529: ldc_w 1759
    //   532: ldc_w 955
    //   535: invokestatic 734	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   538: pop
    //   539: aload_1
    //   540: getstatic 816	com/appbuilder/u846253p1176378/DownloadStatus:SUCCESS	Lcom/appbuilder/u846253p1176378/DownloadStatus;
    //   543: invokevirtual 1508	com/appbuilder/u846253p1176378/AppConfigure:setBackgroundDownloaded	(Lcom/appbuilder/u846253p1176378/DownloadStatus;)V
    //   546: return
    //   547: astore 8
    //   549: ldc_w 1761
    //   552: aload 8
    //   554: invokestatic 759	android/util/Log:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   557: pop
    //   558: goto -19 -> 539
    //   561: astore 19
    //   563: return
    //   564: astore 4
    //   566: goto -555 -> 11
    //
    // Exception table:
    //   from	to	target	type
    //   11	34	134	java/lang/Exception
    //   42	62	134	java/lang/Exception
    //   122	128	134	java/lang/Exception
    //   248	370	134	java/lang/Exception
    //   371	427	134	java/lang/Exception
    //   431	437	134	java/lang/Exception
    //   484	492	134	java/lang/Exception
    //   493	502	134	java/lang/Exception
    //   539	546	134	java/lang/Exception
    //   549	558	134	java/lang/Exception
    //   78	90	137	java/lang/Exception
    //   75	78	153	java/lang/Exception
    //   94	121	153	java/lang/Exception
    //   139	147	153	java/lang/Exception
    //   187	198	153	java/lang/Exception
    //   216	224	153	java/lang/Exception
    //   232	242	153	java/lang/Exception
    //   155	181	182	java/lang/Exception
    //   78	90	185	java/lang/OutOfMemoryError
    //   198	207	214	java/lang/Exception
    //   198	207	230	java/lang/OutOfMemoryError
    //   371	427	428	java/io/IOException
    //   442	481	482	java/lang/Exception
    //   507	539	547	java/lang/Exception
    //   371	427	561	java/lang/NullPointerException
    //   0	11	564	java/lang/Exception
  }

  private void splashScreenDownload(String paramString1, String paramString2)
  {
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString1).openConnection();
      localHttpURLConnection.setReadTimeout(10000);
      localHttpURLConnection.setConnectTimeout(10000);
      localHttpURLConnection.setRequestMethod("GET");
      localHttpURLConnection.setDoInput(true);
      localHttpURLConnection.connect();
      if (localHttpURLConnection.getResponseCode() == 200)
      {
        localBufferedInputStream = new BufferedInputStream(localHttpURLConnection.getInputStream());
        File localFile = new File(paramString2 + "/splash.jpg");
        if (!localFile.exists())
          localFile.createNewFile();
        localFileOutputStream = new FileOutputStream(localFile);
        byte[] arrayOfByte = new byte[1024];
        while (true)
        {
          int i = localBufferedInputStream.read(arrayOfByte, 0, 1024);
          if (i == -1)
            break;
          localFileOutputStream.write(arrayOfByte, 0, i);
          Arrays.fill(arrayOfByte, 0);
        }
      }
    }
    catch (ProtocolException localProtocolException)
    {
      BufferedInputStream localBufferedInputStream;
      FileOutputStream localFileOutputStream;
      Log.d("splashScreenDownload", localProtocolException.getMessage());
      return;
      localFileOutputStream.flush();
      localFileOutputStream.close();
      localBufferedInputStream.close();
      return;
    }
    catch (IOException localIOException)
    {
      Log.d("splashScreenDownload", localIOException.getMessage());
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      Log.d("splashScreenDownload", localNullPointerException.getMessage());
    }
  }

  private void startGPSNotificationService()
  {
    try
    {
      if (((LocationManager)getSystemService("location")).isProviderEnabled("gps"));
      int i = 0;
      String str = getPackageName() + ".GPSNotification.GPSService";
      Iterator localIterator = ((ActivityManager)getSystemService("activity")).getRunningServices(2147483647).iterator();
      while (localIterator.hasNext())
      {
        if (!str.equals(((ActivityManager.RunningServiceInfo)localIterator.next()).service.getClassName()))
          continue;
        i = 1;
      }
      if (i == 0)
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage("\"" + this.appConfig.getAppName() + "\" Would Like to Use Your Current Location?").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            AppBuilder.this.handler.sendEmptyMessage(11);
          }
        }).setNegativeButton("Don't Allow", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            paramDialogInterface.cancel();
          }
        });
        localBuilder.create().show();
      }
      return;
    }
    catch (Exception localException)
    {
      logError("AppBuilder.startGPSNotificationService()", localException);
    }
  }

  private void startService()
  {
    startService(new Intent(this, GPSService.class));
  }

  private void stopGPSNotificationService()
  {
    String str = getPackageName() + ".GPSNotification.GPSService";
    Iterator localIterator = ((ActivityManager)getSystemService("activity")).getRunningServices(2147483647).iterator();
    while (localIterator.hasNext())
    {
      if (!str.equals(((ActivityManager.RunningServiceInfo)localIterator.next()).service.getClassName()))
        continue;
      stopService(new Intent(this, GPSService.class));
    }
  }

  private String widgetFaviconDownload(String paramString)
  {
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
      localHttpURLConnection.setReadTimeout(8000);
      localHttpURLConnection.setConnectTimeout(8000);
      localHttpURLConnection.setRequestMethod("GET");
      localHttpURLConnection.setDoInput(true);
      localHttpURLConnection.connect();
      int i = localHttpURLConnection.getResponseCode();
      localFile = null;
      if (i == 200)
      {
        localBufferedInputStream = new BufferedInputStream(localHttpURLConnection.getInputStream());
        localFile = new File(this.cachePath + File.separator + md5(paramString));
        if (!localFile.exists())
          localFile.createNewFile();
        localFileOutputStream = new FileOutputStream(localFile);
        byte[] arrayOfByte = new byte[1024];
        while (true)
        {
          int j = localBufferedInputStream.read(arrayOfByte, 0, 1024);
          if (j == -1)
            break;
          localFileOutputStream.write(arrayOfByte, 0, j);
          Arrays.fill(arrayOfByte, 0);
        }
      }
    }
    catch (ProtocolException localProtocolException)
    {
      File localFile;
      BufferedInputStream localBufferedInputStream;
      FileOutputStream localFileOutputStream;
      Log.d("splashScreenDownload", localProtocolException.getMessage());
      return null;
      localFileOutputStream.flush();
      localFileOutputStream.close();
      localBufferedInputStream.close();
      String str = localFile.getAbsolutePath();
      return str;
    }
    catch (IOException localIOException)
    {
      Log.d("splashScreenDownload", localIOException.getMessage());
      return null;
    }
    catch (NullPointerException localNullPointerException)
    {
      Log.d("splashScreenDownload", localNullPointerException.getMessage());
    }
    return null;
  }

  private void writeStringToFile(String paramString1, String paramString2)
  {
    try
    {
      BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(new File(paramString1)));
      localBufferedWriter.write(paramString2);
      localBufferedWriter.close();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void DownloadHelperCallbackFailed(DownloadHelper paramDownloadHelper, String paramString)
  {
    15 local15 = new Runnable()
    {
      public void run()
      {
      }
    };
    new Handler().postDelayed(local15, 200L);
    Toast.makeText(this, paramString, 1).show();
  }

  public void DownloadHelperCallbackStarted(DownloadHelper paramDownloadHelper)
  {
    Toast.makeText(this, "Started", 1).show();
  }

  public void DownloadHelperCallbackSuccess(DownloadHelper paramDownloadHelper)
  {
    14 local14 = new Runnable()
    {
      public void run()
      {
      }
    };
    new Handler().postDelayed(local14, 200L);
    Toast.makeText(this, "Finish", 1).show();
  }

  public int LoadPluginFromActivity(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return 0;
  }

  public AppConfigure getAppConfig()
  {
    return this.appConfig;
  }

  // ERROR //
  public String md5(FileInputStream paramFileInputStream)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: ldc_w 1543
    //   9: invokestatic 1549	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   12: astore 8
    //   14: aload_1
    //   15: invokevirtual 1945	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   18: astore 9
    //   20: sipush 2048
    //   23: invokestatic 1951	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   26: astore 10
    //   28: aload 9
    //   30: aload 10
    //   32: invokevirtual 1956	java/nio/channels/FileChannel:read	(Ljava/nio/ByteBuffer;)I
    //   35: iconst_m1
    //   36: if_icmpeq +41 -> 77
    //   39: aload 10
    //   41: invokevirtual 1960	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   44: pop
    //   45: aload 8
    //   47: aload 10
    //   49: invokevirtual 1963	java/security/MessageDigest:update	(Ljava/nio/ByteBuffer;)V
    //   52: aload 10
    //   54: invokevirtual 1966	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   57: pop
    //   58: goto -30 -> 28
    //   61: astore 6
    //   63: aload_1
    //   64: ifnull -60 -> 4
    //   67: aload_1
    //   68: invokevirtual 1967	java/io/FileInputStream:close	()V
    //   71: aconst_null
    //   72: areturn
    //   73: astore 7
    //   75: aconst_null
    //   76: areturn
    //   77: new 530	java/lang/String
    //   80: dup
    //   81: aload 8
    //   83: invokevirtual 1562	java/security/MessageDigest:digest	()[B
    //   86: invokespecial 1968	java/lang/String:<init>	([B)V
    //   89: astore 11
    //   91: aload_1
    //   92: ifnull +7 -> 99
    //   95: aload_1
    //   96: invokevirtual 1967	java/io/FileInputStream:close	()V
    //   99: aload 11
    //   101: areturn
    //   102: astore 4
    //   104: aload_1
    //   105: ifnull -101 -> 4
    //   108: aload_1
    //   109: invokevirtual 1967	java/io/FileInputStream:close	()V
    //   112: aconst_null
    //   113: areturn
    //   114: astore 5
    //   116: aconst_null
    //   117: areturn
    //   118: astore_2
    //   119: aload_1
    //   120: ifnull +7 -> 127
    //   123: aload_1
    //   124: invokevirtual 1967	java/io/FileInputStream:close	()V
    //   127: aload_2
    //   128: athrow
    //   129: astore 12
    //   131: goto -32 -> 99
    //   134: astore_3
    //   135: goto -8 -> 127
    //
    // Exception table:
    //   from	to	target	type
    //   6	28	61	java/security/NoSuchAlgorithmException
    //   28	58	61	java/security/NoSuchAlgorithmException
    //   77	91	61	java/security/NoSuchAlgorithmException
    //   67	71	73	java/io/IOException
    //   6	28	102	java/io/IOException
    //   28	58	102	java/io/IOException
    //   77	91	102	java/io/IOException
    //   108	112	114	java/io/IOException
    //   6	28	118	finally
    //   28	58	118	finally
    //   77	91	118	finally
    //   95	99	129	java/io/IOException
    //   123	127	134	java/io/IOException
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramIntent != null)
    {
      String str2 = paramIntent.getStringExtra("closeme_flag");
      if ((!TextUtils.isEmpty(str2)) && (str2.compareToIgnoreCase("closeme") == 0))
      {
        int i = paramIntent.getIntExtra("new_order", -1);
        Log.e("com.ibuildapp.appbuilder", "Order = " + i);
        if (!this.sdAvailable)
          return;
        File localFile = new File(this.cachePath + "/cache.data");
        if (!localFile.exists())
          return;
        try
        {
          ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream(localFile));
          this.appConfig = ((AppConfigure)localObjectInputStream.readObject());
          localObjectInputStream.close();
          PluginManager localPluginManager2 = PluginManager.getManager();
          localPluginManager2.setFirstStart(this.firstPluginStart);
          this.firstPluginStart = false;
          try
          {
            Widget localWidget2 = this.appConfig.getWidgetWithOrder(i);
            localWidget2.setCachePath(this.cachePath);
            localWidget2.setHaveAdvertisement(this.appConfig.getShowLink());
            this.needToClose = localWidget2.getPluginName().equals("FeedbackPlugin");
            if (localWidget2.getPluginPackage().length() > 0)
            {
              this.startedPluginName = localWidget2.getTitle();
              if (TextUtils.isEmpty(this.startedPluginName))
                this.startedPluginName = localWidget2.getPluginName();
              localPluginManager2.loadPlugin(this, this, localWidget2, this.appConfig.getAppAdv(), false, this.appConfig.getmWidgets(), this.appConfig);
              Log.d("PLUGINDATA", localWidget2.getPluginXmlData());
              return;
            }
            this.dontLaunchModule = false;
            Toast.makeText(this, "The functionality is not available.", 1).show();
            return;
          }
          catch (RuntimeException localRuntimeException)
          {
            Log.w("PLUGINDATA", localRuntimeException);
            return;
          }
        }
        catch (Exception localException2)
        {
          Log.e("com.ibuildapp.appbuilder", "Configuration load error");
          return;
        }
      }
    }
    switch (paramInt1)
    {
    default:
      return;
    case 777:
      if (paramInt2 != -1)
        break;
      String str1 = paramIntent.getStringExtra("username");
      if ((this.userID == null) || (!this.userID.equals("186589")) || (str1 == null))
        break;
      FlurryAgent.setUserId(str1);
      return;
    case 10001:
      if (paramInt2 == -1)
      {
        PluginManager localPluginManager1 = PluginManager.getManager();
        localPluginManager1.setFirstStart(this.firstPluginStart);
        this.firstPluginStart = false;
        try
        {
          Widget localWidget1 = (Widget)paramIntent.getSerializableExtra("widget");
          localWidget1.setCachePath(this.cachePath);
          localWidget1.setHaveAdvertisement(this.appConfig.getShowLink());
          if (localWidget1.getPluginPackage().length() <= 0)
            break;
          this.startedPluginName = localWidget1.getTitle();
          if (TextUtils.isEmpty(this.startedPluginName))
            this.startedPluginName = localWidget1.getPluginName();
          localPluginManager1.loadPlugin(this, this, localWidget1, this.appConfig.getAppAdv(), false, this.appConfig.getmWidgets(), this.appConfig);
          return;
        }
        catch (Exception localException1)
        {
          Log.d("", "");
          return;
        }
      }
      else
      {
        if (!this.isShown)
          break;
        this.isShown = false;
        this.rootContainer.clearAnimation();
        this.rootContainer.startAnimation(this.animHideMenu);
      }
    }
  }

  public void onBackPressed()
  {
    if (this.isDialogShowen)
    {
      this.isDialogShowen = false;
      this.dialogHolder.startAnimation(this.animHideDialog);
      createPushDialog();
      return;
    }
    super.onBackPressed();
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131361805)
    {
      this.rootContainer.clearAnimation();
      this.rootContainer.startAnimation(this.animHideMenu);
      this.isShown = false;
    }
  }

  // ERROR //
  public void onCreate(android.os.Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 2025	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: iconst_1
    //   7: invokevirtual 2028	com/appbuilder/u846253p1176378/AppBuilder:requestWindowFeature	(I)Z
    //   10: pop
    //   11: aload_0
    //   12: ldc_w 2029
    //   15: invokevirtual 2032	com/appbuilder/u846253p1176378/AppBuilder:setContentView	(I)V
    //   18: aload_0
    //   19: aload_0
    //   20: invokestatic 1149	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
    //   23: putfield 1009	com/appbuilder/u846253p1176378/AppBuilder:layoutInflater	Landroid/view/LayoutInflater;
    //   26: aload_0
    //   27: invokevirtual 1825	com/appbuilder/u846253p1176378/AppBuilder:getPackageName	()Ljava/lang/String;
    //   30: astore 24
    //   32: aload 24
    //   34: iconst_1
    //   35: aload 24
    //   37: ldc_w 2034
    //   40: invokevirtual 2037	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   43: iadd
    //   44: invokevirtual 2040	java/lang/String:substring	(I)Ljava/lang/String;
    //   47: astore 25
    //   49: aload_0
    //   50: aload 25
    //   52: iconst_1
    //   53: aload 25
    //   55: ldc_w 2042
    //   58: invokevirtual 2045	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   61: iadd
    //   62: aload 25
    //   64: ldc_w 2047
    //   67: invokevirtual 2045	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   70: invokevirtual 2050	java/lang/String:substring	(II)Ljava/lang/String;
    //   73: putfield 176	com/appbuilder/u846253p1176378/AppBuilder:userID	Ljava/lang/String;
    //   76: aload 24
    //   78: iconst_1
    //   79: aload 24
    //   81: ldc_w 2047
    //   84: invokevirtual 2037	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   87: iadd
    //   88: invokevirtual 2040	java/lang/String:substring	(I)Ljava/lang/String;
    //   91: astore 26
    //   93: aload 26
    //   95: ldc_w 2052
    //   98: invokevirtual 1317	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   101: ifeq +286 -> 387
    //   104: ldc_w 2054
    //   107: putstatic 127	com/appbuilder/u846253p1176378/AppBuilder:FLURYY_ID	Ljava/lang/String;
    //   110: aload_0
    //   111: aload_0
    //   112: invokevirtual 572	com/appbuilder/u846253p1176378/AppBuilder:getWindowManager	()Landroid/view/WindowManager;
    //   115: invokeinterface 578 1 0
    //   120: invokevirtual 586	android/view/Display:getWidth	()I
    //   123: putfield 380	com/appbuilder/u846253p1176378/AppBuilder:screenWidth	I
    //   126: aload_0
    //   127: aload_0
    //   128: invokevirtual 2058	com/appbuilder/u846253p1176378/AppBuilder:getContentResolver	()Landroid/content/ContentResolver;
    //   131: ldc_w 2060
    //   134: invokestatic 2066	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   137: invokespecial 310	com/appbuilder/u846253p1176378/AppBuilder:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   140: astore 5
    //   142: aload_0
    //   143: new 492	java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   150: aload_0
    //   151: getfield 195	com/appbuilder/u846253p1176378/AppBuilder:xmlUrl	Ljava/lang/String;
    //   154: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: ldc_w 2068
    //   160: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: aload 5
    //   165: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: putfield 195	com/appbuilder/u846253p1176378/AppBuilder:xmlUrl	Ljava/lang/String;
    //   174: aload_0
    //   175: new 492	java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   182: aload_0
    //   183: getfield 199	com/appbuilder/u846253p1176378/AppBuilder:pushRegistrationUrl	Ljava/lang/String;
    //   186: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: ldc_w 2068
    //   192: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: aload 5
    //   197: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: putfield 199	com/appbuilder/u846253p1176378/AppBuilder:pushRegistrationUrl	Ljava/lang/String;
    //   206: ldc 185
    //   208: putstatic 2073	com/appbuilder/sdk/android/Statics:appId	Ljava/lang/String;
    //   211: ldc 189
    //   213: putstatic 2076	com/appbuilder/sdk/android/Statics:appToken	Ljava/lang/String;
    //   216: ldc 16
    //   218: putstatic 2079	com/appbuilder/sdk/android/Statics:BASE_DOMEN	Ljava/lang/String;
    //   221: ldc 19
    //   223: putstatic 2081	com/appbuilder/sdk/android/Statics:FACEBOOK_APP_ID	Ljava/lang/String;
    //   226: ldc 22
    //   228: putstatic 2083	com/appbuilder/sdk/android/Statics:FACEBOOK_APP_SECRET	Ljava/lang/String;
    //   231: ldc 32
    //   233: putstatic 2085	com/appbuilder/sdk/android/Statics:TWITTER_CONSUMER_KEY	Ljava/lang/String;
    //   236: ldc 35
    //   238: putstatic 2087	com/appbuilder/sdk/android/Statics:TWITTER_CONSUMER_SECRET	Ljava/lang/String;
    //   241: aload_0
    //   242: getfield 151	com/appbuilder/u846253p1176378/AppBuilder:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   245: ldc 185
    //   247: invokevirtual 2090	com/appbuilder/u846253p1176378/AppConfigure:setmAppId	(Ljava/lang/String;)V
    //   250: aload_0
    //   251: aload_0
    //   252: ldc_w 2092
    //   255: invokevirtual 1815	com/appbuilder/u846253p1176378/AppBuilder:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   258: checkcast 487	android/app/NotificationManager
    //   261: putfield 485	com/appbuilder/u846253p1176378/AppBuilder:mManager	Landroid/app/NotificationManager;
    //   264: new 2094	com/appbuilder/u846253p1176378/AppBuilder$2
    //   267: dup
    //   268: aload_0
    //   269: invokespecial 2095	com/appbuilder/u846253p1176378/AppBuilder$2:<init>	(Lcom/appbuilder/u846253p1176378/AppBuilder;)V
    //   272: astore 6
    //   274: aload_0
    //   275: aload 6
    //   277: putfield 2097	com/appbuilder/u846253p1176378/AppBuilder:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   280: ldc 185
    //   282: putstatic 2102	com/appbuilder/u846253p1176378/Statics:BROADCAST_UID	Ljava/lang/String;
    //   285: new 2104	android/content/IntentFilter
    //   288: dup
    //   289: ldc 185
    //   291: invokespecial 2105	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   294: astore 7
    //   296: aload_0
    //   297: aload_0
    //   298: getfield 2097	com/appbuilder/u846253p1176378/AppBuilder:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   301: aload 7
    //   303: invokevirtual 2109	com/appbuilder/u846253p1176378/AppBuilder:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   306: pop
    //   307: aload_0
    //   308: invokestatic 2112	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationDB:init	(Landroid/content/Context;)V
    //   311: aload_0
    //   312: getfield 195	com/appbuilder/u846253p1176378/AppBuilder:xmlUrl	Ljava/lang/String;
    //   315: invokestatic 2116	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationDB:insertXmlUrl	(Ljava/lang/String;)J
    //   318: pop2
    //   319: aload_0
    //   320: invokevirtual 2120	com/appbuilder/u846253p1176378/AppBuilder:getIntent	()Landroid/content/Intent;
    //   323: ldc_w 2122
    //   326: invokevirtual 1977	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   329: astore 11
    //   331: aload 11
    //   333: ifnull +79 -> 412
    //   336: new 1645	android/content/Intent
    //   339: dup
    //   340: aload_0
    //   341: ldc_w 2124
    //   344: invokespecial 1908	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   347: astore 12
    //   349: new 2126	android/os/Bundle
    //   352: dup
    //   353: invokespecial 2127	android/os/Bundle:<init>	()V
    //   356: astore 13
    //   358: aload 13
    //   360: ldc_w 2122
    //   363: aload 11
    //   365: invokevirtual 2130	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   368: aload 12
    //   370: aload 13
    //   372: invokevirtual 2134	android/content/Intent:putExtras	(Landroid/os/Bundle;)Landroid/content/Intent;
    //   375: pop
    //   376: aload_0
    //   377: aload 12
    //   379: invokevirtual 2138	com/appbuilder/u846253p1176378/AppBuilder:startActivity	(Landroid/content/Intent;)V
    //   382: aload_0
    //   383: invokevirtual 1294	com/appbuilder/u846253p1176378/AppBuilder:finish	()V
    //   386: return
    //   387: aload 26
    //   389: ldc_w 2140
    //   392: invokevirtual 1317	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   395: ifeq -285 -> 110
    //   398: ldc_w 2142
    //   401: putstatic 127	com/appbuilder/u846253p1176378/AppBuilder:FLURYY_ID	Ljava/lang/String;
    //   404: goto -294 -> 110
    //   407: astore 4
    //   409: goto -299 -> 110
    //   412: aload_0
    //   413: new 1645	android/content/Intent
    //   416: dup
    //   417: aload_0
    //   418: ldc_w 2144
    //   421: invokespecial 1908	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   424: aload_0
    //   425: getfield 183	com/appbuilder/u846253p1176378/AppBuilder:requestCode	I
    //   428: invokevirtual 2148	com/appbuilder/u846253p1176378/AppBuilder:startActivityForResult	(Landroid/content/Intent;I)V
    //   431: invokestatic 2151	com/appbuilder/sdk/android/Utils:sdcardAvailable	()Z
    //   434: ifne +192 -> 626
    //   437: ldc_w 2153
    //   440: ldc_w 2155
    //   443: invokestatic 960	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   446: pop
    //   447: aload_0
    //   448: iconst_0
    //   449: putfield 161	com/appbuilder/u846253p1176378/AppBuilder:sdAvailable	Z
    //   452: aload_0
    //   453: getfield 161	com/appbuilder/u846253p1176378/AppBuilder:sdAvailable	Z
    //   456: ifeq +131 -> 587
    //   459: aload_0
    //   460: new 492	java/lang/StringBuilder
    //   463: dup
    //   464: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   467: invokestatic 1411	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   470: invokevirtual 2158	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   473: ldc_w 2160
    //   476: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: aload_0
    //   480: invokevirtual 1825	com/appbuilder/u846253p1176378/AppBuilder:getPackageName	()Ljava/lang/String;
    //   483: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   489: putfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   492: new 532	java/io/File
    //   495: dup
    //   496: new 492	java/lang/StringBuilder
    //   499: dup
    //   500: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   503: aload_0
    //   504: getfield 201	com/appbuilder/u846253p1176378/AppBuilder:cachePath	Ljava/lang/String;
    //   507: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: ldc_w 2162
    //   513: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   516: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   519: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   522: astore 17
    //   524: aload 17
    //   526: invokevirtual 547	java/io/File:exists	()Z
    //   529: ifne +9 -> 538
    //   532: aload 17
    //   534: invokevirtual 2165	java/io/File:mkdirs	()Z
    //   537: pop
    //   538: new 532	java/io/File
    //   541: dup
    //   542: new 492	java/lang/StringBuilder
    //   545: dup
    //   546: invokespecial 493	java/lang/StringBuilder:<init>	()V
    //   549: invokestatic 1411	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   552: invokevirtual 2158	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   555: ldc_w 2167
    //   558: invokevirtual 499	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   561: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   564: invokespecial 544	java/io/File:<init>	(Ljava/lang/String;)V
    //   567: astore 18
    //   569: aload 18
    //   571: invokevirtual 547	java/io/File:exists	()Z
    //   574: istore 19
    //   576: iload 19
    //   578: ifne +9 -> 587
    //   581: aload 18
    //   583: invokevirtual 1486	java/io/File:createNewFile	()Z
    //   586: pop
    //   587: new 2169	com/appbuilder/u846253p1176378/AppBuilder$3
    //   590: dup
    //   591: aload_0
    //   592: invokespecial 2170	com/appbuilder/u846253p1176378/AppBuilder$3:<init>	(Lcom/appbuilder/u846253p1176378/AppBuilder;)V
    //   595: invokevirtual 2173	java/lang/Thread:start	()V
    //   598: return
    //   599: astore_2
    //   600: aload_0
    //   601: ldc_w 2175
    //   604: aload_2
    //   605: invokespecial 338	com/appbuilder/u846253p1176378/AppBuilder:logError	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   608: return
    //   609: astore 15
    //   611: aload_0
    //   612: getfield 251	com/appbuilder/u846253p1176378/AppBuilder:handler	Landroid/os/Handler;
    //   615: iconst_2
    //   616: ldc2_w 2176
    //   619: invokevirtual 1386	android/os/Handler:sendEmptyMessageDelayed	(IJ)Z
    //   622: pop
    //   623: goto -192 -> 431
    //   626: aload_0
    //   627: invokespecial 2179	com/appbuilder/u846253p1176378/AppBuilder:sdAvailableBytes	()J
    //   630: ldc2_w 2180
    //   633: lcmp
    //   634: ifle +11 -> 645
    //   637: aload_0
    //   638: iconst_1
    //   639: putfield 161	com/appbuilder/u846253p1176378/AppBuilder:sdAvailable	Z
    //   642: goto -190 -> 452
    //   645: aload_0
    //   646: iconst_0
    //   647: putfield 161	com/appbuilder/u846253p1176378/AppBuilder:sdAvailable	Z
    //   650: goto -198 -> 452
    //   653: astore 20
    //   655: goto -68 -> 587
    //
    // Exception table:
    //   from	to	target	type
    //   26	110	407	java/lang/Throwable
    //   387	404	407	java/lang/Throwable
    //   0	26	599	java/lang/Exception
    //   26	110	599	java/lang/Exception
    //   110	331	599	java/lang/Exception
    //   336	386	599	java/lang/Exception
    //   387	404	599	java/lang/Exception
    //   431	452	599	java/lang/Exception
    //   452	538	599	java/lang/Exception
    //   538	576	599	java/lang/Exception
    //   587	598	599	java/lang/Exception
    //   611	623	599	java/lang/Exception
    //   626	642	599	java/lang/Exception
    //   645	650	599	java/lang/Exception
    //   412	431	609	java/lang/Exception
    //   581	587	653	java/lang/Exception
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }

  protected void onDestroy()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences(getString(2131100035), 2).edit();
    localEditor.putString(getPackageName(), getString(2131100037));
    localEditor.commit();
    try
    {
      StatisticsCollector.finish();
      label46: if (this.broadcastReceiver != null)
        unregisterReceiver(this.broadcastReceiver);
      super.onDestroy();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      break label46;
    }
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Widget localWidget = (Widget)this.actualWidgetList.get(paramInt);
    PluginManager localPluginManager = PluginManager.getManager();
    localPluginManager.setFirstStart(this.firstPluginStart);
    this.firstPluginStart = false;
    try
    {
      localWidget.setCachePath(this.cachePath);
      localWidget.setHaveAdvertisement(this.appConfig.getShowLink());
      if (localWidget.getPluginPackage().length() > 0)
      {
        this.startedPluginName = localWidget.getTitle();
        if (TextUtils.isEmpty(this.startedPluginName))
          this.startedPluginName = localWidget.getPluginName();
        localPluginManager.loadPlugin(this, this, localWidget, this.appConfig.getAppAdv(), false, this.appConfig.getmWidgets(), this.appConfig);
      }
      return;
    }
    catch (Exception localException)
    {
      Log.d("", "");
    }
  }

  public final void onPause()
  {
    int i;
    View localView;
    if (this.widgetList != null)
    {
      i = this.widgetList.getFirstVisiblePosition();
      localView = this.widgetList.getChildAt(0);
      if (localView != null)
        break label88;
    }
    label88: for (int j = 0; ; j = localView.getTop())
    {
      this.sPref = getSharedPreferences("MyPref", 0);
      SharedPreferences.Editor localEditor = this.sPref.edit();
      localEditor.putInt("MenuIndex", i);
      localEditor.putInt("MenuCoordinate", j);
      localEditor.commit();
      super.onPause();
      return;
    }
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558400, paramMenu);
    paramMenu.clear();
    MenuItem localMenuItem = paramMenu.add("");
    localMenuItem.setTitle(getResources().getString(2131100033));
    localMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        AppBuilder.this.handler.sendEmptyMessage(12);
        return true;
      }
    });
    return true;
  }

  protected void onRestart()
  {
    createPushDialog();
    super.onRestart();
    if (!TextUtils.isEmpty(this.startedPluginName))
      Statics.analiticsHandler.sendUserEvent("Stop Module", this.startedPluginName);
  }

  protected void onResume()
  {
    this.foreground = true;
    SharedPreferences.Editor localEditor = getSharedPreferences(getString(2131100035), 2).edit();
    localEditor.putString(getPackageName(), getString(2131100036));
    localEditor.commit();
    this.dontLaunchModule = false;
    if (this.needToClose)
    {
      if (Statics.closeMain)
      {
        Statics.closeMain = false;
        super.onResume();
        finish();
        return;
      }
      this.needToClose = false;
    }
    RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(2131361838);
    if (localRelativeLayout != null)
      for (int i = 0; i < localRelativeLayout.getChildCount(); i++)
      {
        Drawable localDrawable = localRelativeLayout.getChildAt(i).getBackground();
        if (localDrawable == null)
          continue;
        localDrawable.setColorFilter(null);
        localRelativeLayout.getChildAt(i).setBackgroundDrawable(localDrawable);
      }
    this.sPref = getSharedPreferences("MyPref", 0);
    if (this.widgetList != null)
      this.widgetList.setSelectionFromTop(this.sPref.getInt("MenuIndex", 0), this.sPref.getInt("MenuCoordinate", 0));
    super.onResume();
  }

  protected void onStart()
  {
    super.onStart();
    if ((!"".equals(FLURYY_ID)) && (!"FFLLuuRRRRyy".equals(FLURYY_ID)) && (!this.flurryStarted));
    try
    {
      FlurryAgent.onStartSession(this, FLURYY_ID);
      this.flurryStarted = true;
      return;
    }
    catch (Exception localException)
    {
      Log.e("", "");
    }
  }

  protected void onStop()
  {
    super.onStop();
    this.foreground = false;
    if (this.flurryStarted);
    try
    {
      FlurryAgent.onEndSession(this);
      this.flurryStarted = false;
      return;
    }
    catch (Exception localException)
    {
      Log.e("", "");
    }
  }

  protected class SmoothInterpolator
    implements Interpolator
  {
    protected SmoothInterpolator()
    {
    }

    public float getInterpolation(float paramFloat)
    {
      return 1.0F + (float)Math.pow(paramFloat - 1.0F, 3.0D);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.AppBuilder
 * JD-Core Version:    0.6.0
 */