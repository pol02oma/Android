package com.appbuilder.sdk.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.pushnotification.AppPushNotificationDB;
import com.appbuilder.sdk.android.pushnotification.AppPushNotificationDialogLayout;
import com.appbuilder.sdk.android.pushnotification.AppPushNotificationMessage;
import com.appbuilder.statistics.StatisticsCollector;
import com.flurry.android.FlurryAgent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AppBuilderModuleMain extends Activity
  implements AppBuilderInterface, TopBarInterface
{
  public static String TAG = "com.ibuildapp.core.AppBuilderModuleMain";
  private final int ARROW_HEIGHT = 25;
  private final int ARROW_WIDTH = 15;
  private final int FIND_SURFACEOBJECTS = 5;
  private final String MENU_INDEX = "MenuIndex";
  private final int MENU_ITEM_ADD_CONTACT_CLICK = 3;
  private final int MENU_ITEM_ADD_EVENT_CLICK = 4;
  private final int MENU_ITEM_EMAIL_CLICK = 2;
  private final int MENU_ITEM_SMS_CLICK = 1;
  private final String MENU_TOP_COORDINATE = "MenuCoordinate";
  private final String TOPBAR_ARROW_PARH = "/com/appbuilder/sdk/android/res/api_topbar_arrow.png";
  private final String TOPBAR_SHOW_MENU_PARH = "/com/appbuilder/sdk/android/res/api_show_bar.png";
  private final String WIDGET_HOLDER_BACKGROUND = "#3f434b";
  private final String WIDGET_TOPBAR_BACKGROUND = "#32363c";
  private ArrayList<Widget> actualWidgetList = new ArrayList();
  private LinearLayout adLayout;
  private AppAdvView adView = null;
  private AppAdvData advData = null;
  private AlphaAnimation animHideDialog;
  private AlphaAnimation animHideImage;
  private TranslateAnimation animHideMenu;
  private AlphaAnimation animShowDialog;
  private AlphaAnimation animShowImage;
  private TranslateAnimation animShowMenu;
  private String appId;
  protected BarDesigner bottomBarDesign;
  private BroadcastReceiver broadcastReceiver;
  private String className;
  private float density;
  private LinearLayout dialogHolder;
  private boolean firstStart;
  private String flurryId = "";
  private boolean foreground;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        AppBuilderModuleMain.this.sendSMS();
        return;
      case 2:
        AppBuilderModuleMain.this.sendEmail();
        return;
      case 3:
        AppBuilderModuleMain.this.addContact();
        return;
      case 4:
        AppBuilderModuleMain.this.addEvent();
        return;
      case 5:
      }
      AppBuilderModuleMain.this.getSufrafeObjects((View)paramMessage.obj);
    }
  };
  private LinearLayout homeButton;
  private boolean isDialogShowen = false;
  private boolean isShown = false;
  private LayoutInflater layoutInflater = null;
  private NotificationManager mManager;
  private LinearLayout menuContainer;
  private long millis;
  protected HashMap<Object, HashMap<Object, Object>> nativeFeatures = new HashMap();
  protected BarDesigner navBarDesign;
  private SwipeLinearLayout rootContainer;
  private FrameLayout rootFrameLayout;
  private LinearLayout rootRoot;
  private SharedPreferences sPref;
  private int screenWidth;
  private Serializable session = null;
  private boolean showSideBar = false;
  protected Bundle state = null;
  private ArrayList<View> surfaceObjects = new ArrayList();
  private boolean swipeBlock = false;
  private OnSwipeInterface swipeInterface;
  private BarDesigner tabBarDesign;
  private ArrayList<Bitmap> thumbnails = new ArrayList();
  private LinearLayout titleHolder;
  private LinearLayout topBar;
  private LinearLayout topBarLeftButton;
  private LinearLayout topBarRightButton;
  private TextView topBarTitle;
  private LinearLayout userContainer;
  private View userLayout = null;
  private Widget widget = null;
  private ListView widgetList;
  private ArrayList<Widget> widgets;

  private void LogDebug(String paramString)
  {
    Log.d(TAG, paramString);
  }

  private static void LogError(Exception paramException)
  {
    Log.e(TAG, "", paramException);
  }

  private static void LogWarning(Exception paramException)
  {
    Log.w(TAG, paramException);
  }

  private void addContact()
  {
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    try
    {
      if (!this.nativeFeatures.containsKey(NATIVE_FEATURES.ADD_CONTACT))
        return;
      localArrayList1 = new ArrayList();
      String str1 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_CONTACT)).get(AppNativeFeature.CONTACT.NAME);
      if ((str1 != null) && (!"".equals(str1)))
        localArrayList1.add(new Object(0, str1)
        {
          protected int type;
          protected String value;

          public String getDescription()
          {
            return this.value;
          }

          public int getType()
          {
            return this.type;
          }
        });
      String str2 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_CONTACT)).get(AppNativeFeature.CONTACT.PHONE);
      if ((str2 != null) && (!"".equals(str2)))
        localArrayList1.add(new Object(1, str2)
        {
          protected int type;
          protected String value;

          public String getDescription()
          {
            return this.value;
          }

          public int getType()
          {
            return this.type;
          }
        });
      String str3 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_CONTACT)).get(AppNativeFeature.CONTACT.EMAIL);
      if ((str3 != null) && (!"".equals(str3)))
        localArrayList1.add(new Object(2, str3)
        {
          protected int type;
          protected String value;

          public String getDescription()
          {
            return this.value;
          }

          public int getType()
          {
            return this.type;
          }
        });
      String str4 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_CONTACT)).get(AppNativeFeature.CONTACT.WEBSITE);
      if ((str4 != null) && (!"".equals(str4)))
        localArrayList1.add(new Object(3, str4)
        {
          protected int type;
          protected String value;

          public String getDescription()
          {
            return this.value;
          }

          public int getType()
          {
            return this.type;
          }
        });
      localArrayList2 = new ArrayList();
      String str5 = ((1Contact)localArrayList1.get(0)).getDescription();
      try
      {
        Cursor localCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[] { "_id", "display_name" }, "display_name = ?", new String[] { str5 }, null);
        if (localCursor.getCount() > 0)
        {
          localCursor.moveToFirst();
          localCursor.getString(0);
          String str6 = localCursor.getString(1);
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
          localBuilder.setTitle("The contact is already in your address book.");
          localBuilder.setMessage("Do you want to replace it?");
          localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(localArrayList1, str6)
          {
            public void onClick(DialogInterface paramDialogInterface, int paramInt)
            {
              ArrayList localArrayList = new ArrayList();
              int i = 0;
              if (i < this.val$contacts.size())
              {
                int j = ((AppBuilderModuleMain.1Contact)this.val$contacts.get(i)).getType();
                Cursor localCursor = AppBuilderModuleMain.this.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, null, null, null);
                int k = 0;
                while (localCursor.moveToNext())
                  for (int m = 0; m < localCursor.getColumnCount(); m++)
                  {
                    if ((localCursor.getString(m) == null) || (!localCursor.getString(m).equals(((AppBuilderModuleMain.1Contact)this.val$contacts.get(i)).getDescription())))
                      continue;
                    k = 1;
                    if (!localArrayList.isEmpty())
                      continue;
                    localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
                    localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", this.val$contactName).build());
                  }
                if (k == 0)
                  switch (j)
                  {
                  case 0:
                  default:
                  case 1:
                  case 2:
                  case 3:
                  }
                while (true)
                {
                  i++;
                  break;
                  localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", ((AppBuilderModuleMain.1Contact)this.val$contacts.get(i)).getDescription()).build());
                  continue;
                  localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", ((AppBuilderModuleMain.1Contact)this.val$contacts.get(i)).getDescription()).build());
                  continue;
                  localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", ((AppBuilderModuleMain.1Contact)this.val$contacts.get(i)).getDescription()).build());
                }
              }
              try
              {
                AppBuilderModuleMain.this.getContentResolver().applyBatch("com.android.contacts", localArrayList);
                return;
              }
              catch (RemoteException localRemoteException)
              {
                AppBuilderModuleMain.access$1800(localRemoteException);
                return;
              }
              catch (OperationApplicationException localOperationApplicationException)
              {
                AppBuilderModuleMain.access$1800(localOperationApplicationException);
              }
            }
          });
          localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramDialogInterface, int paramInt)
            {
            }
          });
          localBuilder.create().show();
          return;
        }
      }
      catch (Exception localException2)
      {
        LogError(localException2);
        return;
      }
    }
    catch (Exception localException1)
    {
      StatisticsCollector.newError(localException1, "AppBuilderModule.");
      LogError(localException1);
      return;
    }
    while (true)
    {
      int i;
      int j;
      try
      {
        i = localArrayList2.size();
        localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        j = 0;
        if (j >= localArrayList1.size())
          break label744;
        switch (((1Contact)localArrayList1.get(j)).getType())
        {
        case 0:
          localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", i).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", ((1Contact)localArrayList1.get(j)).getDescription()).build());
        case 1:
        case 2:
        case 3:
        }
      }
      catch (Exception localException3)
      {
        LogError(localException3);
        return;
      }
      localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", i).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", ((1Contact)localArrayList1.get(j)).getDescription()).build());
      break label769;
      localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", i).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", ((1Contact)localArrayList1.get(j)).getDescription()).build());
      break label769;
      localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", i).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", ((1Contact)localArrayList1.get(j)).getDescription()).build());
      break label769;
      label744: getContentResolver().applyBatch("com.android.contacts", localArrayList2);
      Toast.makeText(this, "The contact has beed saved into your address book.", 1).show();
      return;
      label769: j++;
    }
  }

  private void addEvent()
  {
    try
    {
      if (this.nativeFeatures.containsKey(NATIVE_FEATURES.ADD_EVENT))
      {
        String str1 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_EVENT)).get(AppNativeFeature.EVENT.TITLE);
        String str2 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_EVENT)).get(AppNativeFeature.EVENT.BEGIN);
        ((String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_EVENT)).get(AppNativeFeature.EVENT.END));
        ((String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_EVENT)).get(AppNativeFeature.EVENT.FREQUENCY));
        ContentResolver localContentResolver = getContentResolver();
        Uri.Builder localBuilder = Uri.parse("content://com.android.calendar/instances/when").buildUpon();
        Long localLong = Long.valueOf(new Date(str2).getTime());
        ContentUris.appendId(localBuilder, localLong.longValue() - 600000L);
        ContentUris.appendId(localBuilder, 600000L + localLong.longValue());
        String[] arrayOfString = { "title", "begin" };
        Cursor localCursor = localContentResolver.query(localBuilder.build(), arrayOfString, null, null, null);
        int i = 0;
        if (localCursor != null)
          while (localCursor.moveToNext())
          {
            if ((localLong.longValue() != localCursor.getLong(1)) || (!str1.equals(localCursor.getString(0))))
              continue;
            i = 1;
          }
        if (i == 0)
        {
          Intent localIntent = new Intent("android.intent.action.EDIT");
          localIntent.setType("vnd.android.cursor.item/event");
          localIntent.putExtra("beginTime", localLong);
          localIntent.putExtra("allDay", false);
          localIntent.putExtra("endTime", 3600000L + localLong.longValue());
          localIntent.putExtra("title", str1);
          startActivity(localIntent);
          return;
        }
        Toast.makeText(this, "Event already exist!", 1).show();
        return;
      }
    }
    catch (Exception localException)
    {
      StatisticsCollector.newError(localException, "AppBuilderModule.addEvent()");
      LogError(localException);
    }
  }

  private void createPushDialog()
  {
    Log.e("createPushDialog - AppBuilderModuleMain", "createPushDialog");
    AppPushNotificationMessage localAppPushNotificationMessage = AppPushNotificationDB.getNotificationIfExist();
    if (localAppPushNotificationMessage != null)
    {
      AppPushNotificationDialogLayout localAppPushNotificationDialogLayout;
      if (!this.isDialogShowen)
      {
        if (!localAppPushNotificationMessage.isPackageExist)
          break label181;
        localAppPushNotificationDialogLayout = new AppPushNotificationDialogLayout(this, localAppPushNotificationMessage.titleText, localAppPushNotificationMessage.descriptionText, localAppPushNotificationMessage.imagePath, new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            AppBuilderModuleMain.this.dialogHolder.clearAnimation();
            AppBuilderModuleMain.access$1902(AppBuilderModuleMain.this, false);
            AppBuilderModuleMain.this.dialogHolder.startAnimation(AppBuilderModuleMain.this.animHideDialog);
            AppBuilderModuleMain.this.createPushDialog();
          }
        }
        , new View.OnClickListener(localAppPushNotificationMessage)
        {
          public void onClick(View paramView)
          {
            AppBuilderModuleMain.this.dialogHolder.clearAnimation();
            AppBuilderModuleMain.access$1902(AppBuilderModuleMain.this, false);
            AppBuilderModuleMain.this.dialogHolder.startAnimation(AppBuilderModuleMain.this.animHideDialog);
            AppBuilderModuleMain.this.forceCloseModule(this.val$freshMessage.widgetOrder);
          }
        });
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
        Log.e("NOTIFICATION - AppBuilderModuleMain", "We have message. Text = " + localAppPushNotificationMessage.descriptionText + "ImgPath = " + localAppPushNotificationMessage.imagePath);
        return;
        label181: if (localAppPushNotificationMessage.widgetOrder == -1)
        {
          localAppPushNotificationDialogLayout = new AppPushNotificationDialogLayout(this, localAppPushNotificationMessage.titleText, localAppPushNotificationMessage.descriptionText, localAppPushNotificationMessage.imagePath, new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              AppBuilderModuleMain.this.dialogHolder.clearAnimation();
              AppBuilderModuleMain.access$1902(AppBuilderModuleMain.this, false);
              AppBuilderModuleMain.this.dialogHolder.startAnimation(AppBuilderModuleMain.this.animHideDialog);
              AppBuilderModuleMain.this.createPushDialog();
            }
          }
          , null);
          continue;
        }
        localAppPushNotificationDialogLayout = new AppPushNotificationDialogLayout(this, localAppPushNotificationMessage.titleText, localAppPushNotificationMessage.descriptionText, localAppPushNotificationMessage.imagePath, new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            AppBuilderModuleMain.this.dialogHolder.clearAnimation();
            AppBuilderModuleMain.access$1902(AppBuilderModuleMain.this, false);
            AppBuilderModuleMain.this.dialogHolder.startAnimation(AppBuilderModuleMain.this.animHideDialog);
            AppBuilderModuleMain.this.createPushDialog();
          }
        }
        , new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            AppBuilderModuleMain.this.dialogHolder.clearAnimation();
            AppBuilderModuleMain.access$1902(AppBuilderModuleMain.this, false);
            AppBuilderModuleMain.this.dialogHolder.startAnimation(AppBuilderModuleMain.this.animHideDialog);
          }
        });
      }
    }
    Log.e("NOTIFICATION - AppBuilderModuleMain", " no message found");
  }

  private LinearLayout createTextButton(int paramInt)
  {
    LinearLayout localLinearLayout = new LinearLayout(this);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -1);
    if (paramInt == 3)
      localLinearLayout.setGravity(19);
    while (true)
    {
      localLinearLayout.setOrientation(0);
      localLinearLayout.setLayoutParams(localLayoutParams1);
      ImageView localImageView = new ImageView(this);
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams((int)(15.0F * this.density), (int)(25.0F * this.density));
      localImageView.setBackgroundDrawable(new BitmapDrawable(getClass().getResourceAsStream("/com/appbuilder/sdk/android/res/api_topbar_arrow.png")));
      localImageView.setLayoutParams(localLayoutParams2);
      TextView localTextView = new TextView(this);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams3.setMargins((int)(5.0F * this.density), 0, 0, 0);
      localTextView.setLayoutParams(localLayoutParams3);
      localTextView.setSingleLine();
      localTextView.setTextSize(2, this.navBarDesign.itemDesign.fontSize);
      localTextView.setTextColor(this.navBarDesign.itemDesign.textColor);
      if (this.navBarDesign.itemDesign.fontWeight.compareToIgnoreCase("bold") == 0)
        localTextView.setTypeface(Typeface.DEFAULT_BOLD);
      localLinearLayout.addView(localImageView);
      localLinearLayout.addView(localTextView);
      return localLinearLayout;
      localLinearLayout.setGravity(21);
    }
  }

  private LinearLayout create_main_layout()
  {
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    localLinearLayout.setOrientation(1);
    this.adLayout = new LinearLayout(this);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    this.adLayout.setLayoutParams(localLayoutParams);
    this.adLayout.setOrientation(1);
    this.rootFrameLayout = new FrameLayout(this);
    FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-1, -1);
    this.rootFrameLayout.setLayoutParams(localLayoutParams1);
    localLinearLayout.addView(this.adLayout);
    localLinearLayout.addView(this.rootFrameLayout);
    return localLinearLayout;
  }

  private LinearLayout create_menu_topbar_layout()
  {
    LinearLayout localLinearLayout1 = new LinearLayout(this);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -1);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setLayoutParams(localLayoutParams1);
    LinearLayout localLinearLayout2 = new LinearLayout(this);
    localLinearLayout2.setOrientation(0);
    localLinearLayout2.setBackgroundColor(this.navBarDesign.color);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, (int)(50.0F * this.density));
    localLayoutParams2.gravity = 16;
    localLinearLayout2.setLayoutParams(localLayoutParams2);
    localLinearLayout2.setBackgroundColor(Color.parseColor("#32363c"));
    this.homeButton = new LinearLayout(this);
    this.homeButton.setOrientation(0);
    LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-2, (int)(42.0F * this.density));
    localLayoutParams3.setMargins((int)(10.0F * this.density), 0, 0, 0);
    localLayoutParams3.gravity = 17;
    this.homeButton.setLayoutParams(localLayoutParams3);
    this.homeButton.setGravity(16);
    TextView localTextView = new TextView(this);
    localTextView.setTextSize(16.0F);
    localTextView.setTextColor(-1);
    localTextView.setTypeface(null, 1);
    localTextView.setText("Home");
    this.homeButton.addView(localTextView);
    localLinearLayout2.addView(this.homeButton);
    localLinearLayout1.addView(localLinearLayout2);
    return localLinearLayout1;
  }

  private LinearLayout create_topbar_layout()
  {
    LinearLayout localLinearLayout1 = new LinearLayout(this);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -1);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setLayoutParams(localLayoutParams1);
    LinearLayout localLinearLayout2 = new LinearLayout(this);
    localLinearLayout2.setPadding((int)(10.0F * this.density), (int)(10.0F * this.density), (int)(10.0F * this.density), (int)(10.0F * this.density));
    localLinearLayout2.setOrientation(0);
    localLinearLayout2.setBackgroundColor(this.navBarDesign.color);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, (int)(50.0F * this.density));
    localLayoutParams2.gravity = 17;
    localLinearLayout2.setLayoutParams(localLayoutParams2);
    this.topBarLeftButton = new LinearLayout(this);
    this.topBarLeftButton.setGravity(3);
    this.topBarLeftButton.setOrientation(0);
    LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -1);
    localLayoutParams3.weight = 5.0F;
    localLayoutParams3.gravity = 19;
    this.topBarLeftButton.setLayoutParams(localLayoutParams3);
    this.topBarLeftButton.setVisibility(0);
    if (this.showSideBar)
    {
      LinearLayout localLinearLayout3 = new LinearLayout(this);
      LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams((int)(38.0F * this.density), (int)(30.0F * this.density));
      localLinearLayout3.setGravity(17);
      localLinearLayout3.setOrientation(0);
      localLinearLayout3.setLayoutParams(localLayoutParams4);
      ImageView localImageView = new ImageView(this);
      localImageView.setLayoutParams(new LinearLayout.LayoutParams((int)(25.0F * this.density), (int)(20.0F * this.density)));
      BitmapDrawable localBitmapDrawable = new BitmapDrawable(getClass().getResourceAsStream("/com/appbuilder/sdk/android/res/api_show_bar.png"));
      localBitmapDrawable.setColorFilter(this.navBarDesign.itemDesign.textColor, PorterDuff.Mode.MULTIPLY);
      localImageView.setImageDrawable(localBitmapDrawable);
      localLinearLayout3.addView(localImageView);
      this.topBarLeftButton.addView(localLinearLayout3);
    }
    this.titleHolder = new LinearLayout(this);
    this.titleHolder.setOrientation(0);
    LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-1, -1);
    localLayoutParams5.weight = 4.0F;
    localLayoutParams5.gravity = 81;
    this.titleHolder.setLayoutParams(localLayoutParams5);
    this.topBarTitle = new TextView(this);
    this.topBarTitle.setLines(1);
    this.topBarTitle.setTextSize(2, this.navBarDesign.titleDesign.fontSize);
    this.topBarTitle.setTextColor(this.navBarDesign.titleDesign.textColor);
    this.topBarTitle.setText("Title");
    if (this.navBarDesign.titleDesign.textAlignment.compareTo("left") == 0)
      this.topBarTitle.setGravity(19);
    while (true)
    {
      if (this.navBarDesign.titleDesign.fontWeight.compareToIgnoreCase("bold") == 0)
        this.topBarTitle.setTypeface(Typeface.DEFAULT_BOLD);
      LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-1, -1);
      this.topBarTitle.setLayoutParams(localLayoutParams6);
      this.titleHolder.addView(this.topBarTitle);
      this.topBarRightButton = new LinearLayout(this);
      this.topBarRightButton.setOrientation(0);
      this.topBarRightButton.setGravity(21);
      LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-1, -1);
      localLayoutParams7.weight = 5.0F;
      localLayoutParams7.gravity = 21;
      this.topBarRightButton.setLayoutParams(localLayoutParams7);
      this.topBarRightButton.setVisibility(4);
      localLinearLayout2.addView(this.topBarLeftButton);
      localLinearLayout2.addView(this.titleHolder);
      localLinearLayout2.addView(this.topBarRightButton);
      localLinearLayout1.addView(localLinearLayout2);
      return localLinearLayout1;
      if (this.navBarDesign.titleDesign.textAlignment.compareTo("center") == 0)
      {
        this.topBarTitle.setGravity(17);
        continue;
      }
      if (this.navBarDesign.titleDesign.textAlignment.compareTo("right") != 0)
        continue;
      this.topBarTitle.setGravity(21);
    }
  }

  private void getSufrafeObjects(View paramView)
  {
    if ((paramView instanceof ViewGroup))
    {
      int i = ((ViewGroup)paramView).getChildCount();
      int j = 0;
      if (j < i)
      {
        if ((((ViewGroup)paramView).getChildAt(j) instanceof ViewGroup))
          getSufrafeObjects(((ViewGroup)paramView).getChildAt(j));
        while (true)
        {
          j++;
          break;
          if (!(((ViewGroup)paramView).getChildAt(j) instanceof SurfaceView))
            continue;
          this.surfaceObjects.add(((ViewGroup)paramView).getChildAt(j));
        }
      }
    }
    if ((paramView instanceof SurfaceHolder))
      this.surfaceObjects.add(paramView);
  }

  // ERROR //
  public static Bitmap proccessBitmap(String paramString)
  {
    // Byte code:
    //   0: new 925	android/graphics/BitmapFactory$Options
    //   3: dup
    //   4: invokespecial 926	android/graphics/BitmapFactory$Options:<init>	()V
    //   7: astore_1
    //   8: new 928	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 929	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: astore_2
    //   17: invokestatic 934	java/lang/System:gc	()V
    //   20: new 936	java/io/FileInputStream
    //   23: dup
    //   24: aload_2
    //   25: invokespecial 939	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   28: aconst_null
    //   29: aload_1
    //   30: invokestatic 945	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   33: astore 11
    //   35: aload 11
    //   37: astore 6
    //   39: aload 6
    //   41: areturn
    //   42: astore 10
    //   44: aload 10
    //   46: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   49: aconst_null
    //   50: astore 6
    //   52: goto -13 -> 39
    //   55: astore 7
    //   57: aload 7
    //   59: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   62: aconst_null
    //   63: areturn
    //   64: astore_3
    //   65: invokestatic 934	java/lang/System:gc	()V
    //   68: new 936	java/io/FileInputStream
    //   71: dup
    //   72: aload_2
    //   73: invokespecial 939	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   76: aconst_null
    //   77: aload_1
    //   78: invokestatic 945	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   81: astore 9
    //   83: aload 9
    //   85: astore 6
    //   87: goto -48 -> 39
    //   90: astore 8
    //   92: aload 8
    //   94: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   97: aconst_null
    //   98: astore 6
    //   100: goto -61 -> 39
    //   103: astore 4
    //   105: getstatic 111	com/appbuilder/sdk/android/AppBuilderModuleMain:TAG	Ljava/lang/String;
    //   108: ldc_w 947
    //   111: aload 4
    //   113: invokestatic 213	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   116: pop
    //   117: aconst_null
    //   118: astore 6
    //   120: goto -81 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   17	35	42	java/lang/Exception
    //   8	17	55	java/lang/Exception
    //   44	49	55	java/lang/Exception
    //   65	68	55	java/lang/Exception
    //   92	97	55	java/lang/Exception
    //   105	117	55	java/lang/Exception
    //   17	35	64	java/lang/OutOfMemoryError
    //   68	83	90	java/lang/Exception
    //   68	83	103	java/lang/OutOfMemoryError
  }

  private void readConfiguration()
  {
  }

  // ERROR //
  private void sendEmail()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 138	com/appbuilder/sdk/android/AppBuilderModuleMain:nativeFeatures	Ljava/util/HashMap;
    //   4: getstatic 953	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   7: invokevirtual 300	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   10: ifeq +148 -> 158
    //   13: new 565	android/content/Intent
    //   16: dup
    //   17: ldc_w 955
    //   20: invokespecial 568	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   23: astore_2
    //   24: aload_2
    //   25: ldc_w 957
    //   28: invokevirtual 574	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   31: pop
    //   32: aload_0
    //   33: getfield 138	com/appbuilder/sdk/android/AppBuilderModuleMain:nativeFeatures	Ljava/util/HashMap;
    //   36: getstatic 953	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   39: invokevirtual 304	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   42: checkcast 135	java/util/HashMap
    //   45: getstatic 963	com/appbuilder/sdk/android/AppNativeFeature$EMAIL:TEXT	Lcom/appbuilder/sdk/android/AppNativeFeature$EMAIL;
    //   48: invokevirtual 300	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   51: istore 4
    //   53: iload 4
    //   55: ifeq +36 -> 91
    //   58: aload_2
    //   59: ldc_w 965
    //   62: aload_0
    //   63: getfield 138	com/appbuilder/sdk/android/AppBuilderModuleMain:nativeFeatures	Ljava/util/HashMap;
    //   66: getstatic 953	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   69: invokevirtual 304	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   72: checkcast 135	java/util/HashMap
    //   75: getstatic 963	com/appbuilder/sdk/android/AppNativeFeature$EMAIL:TEXT	Lcom/appbuilder/sdk/android/AppNativeFeature$EMAIL;
    //   78: invokevirtual 304	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   81: checkcast 312	java/lang/String
    //   84: invokestatic 971	android/text/Html:fromHtml	(Ljava/lang/String;)Landroid/text/Spanned;
    //   87: invokevirtual 974	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   90: pop
    //   91: aload_0
    //   92: getfield 138	com/appbuilder/sdk/android/AppBuilderModuleMain:nativeFeatures	Ljava/util/HashMap;
    //   95: getstatic 953	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   98: invokevirtual 304	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   101: checkcast 135	java/util/HashMap
    //   104: getstatic 977	com/appbuilder/sdk/android/AppNativeFeature$EMAIL:SUBJECT	Lcom/appbuilder/sdk/android/AppNativeFeature$EMAIL;
    //   107: invokevirtual 300	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   110: istore 5
    //   112: iload 5
    //   114: ifeq +33 -> 147
    //   117: aload_2
    //   118: ldc_w 979
    //   121: aload_0
    //   122: getfield 138	com/appbuilder/sdk/android/AppBuilderModuleMain:nativeFeatures	Ljava/util/HashMap;
    //   125: getstatic 953	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   128: invokevirtual 304	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   131: checkcast 135	java/util/HashMap
    //   134: getstatic 977	com/appbuilder/sdk/android/AppNativeFeature$EMAIL:SUBJECT	Lcom/appbuilder/sdk/android/AppNativeFeature$EMAIL;
    //   137: invokevirtual 304	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   140: checkcast 312	java/lang/String
    //   143: invokevirtual 595	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   146: pop
    //   147: aload_0
    //   148: aload_2
    //   149: ldc_w 981
    //   152: invokestatic 985	android/content/Intent:createChooser	(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   155: invokevirtual 599	com/appbuilder/sdk/android/AppBuilderModuleMain:startActivity	(Landroid/content/Intent;)V
    //   158: return
    //   159: astore_1
    //   160: aload_1
    //   161: ldc_w 987
    //   164: invokestatic 427	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   167: aload_1
    //   168: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   171: return
    //   172: astore 6
    //   174: goto -27 -> 147
    //   177: astore 8
    //   179: goto -88 -> 91
    //
    // Exception table:
    //   from	to	target	type
    //   0	53	159	java/lang/Exception
    //   91	112	159	java/lang/Exception
    //   147	158	159	java/lang/Exception
    //   117	147	172	java/lang/Exception
    //   58	91	177	java/lang/Exception
  }

  private void sendSMS()
  {
    try
    {
      if (this.nativeFeatures.containsKey(AppBuilderModule.NATIVE_FEATURES.SMS))
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:"));
        Object localObject = "";
        try
        {
          if (((HashMap)this.nativeFeatures.get(AppBuilderModule.NATIVE_FEATURES.SMS)).containsKey(AppNativeFeature.SMS.TEXT))
            localObject = (CharSequence)((HashMap)this.nativeFeatures.get(AppBuilderModule.NATIVE_FEATURES.SMS)).get(AppNativeFeature.SMS.TEXT);
          localIntent.putExtra("sms_body", (CharSequence)localObject);
          startActivity(localIntent);
          return;
        }
        catch (Exception localException2)
        {
          while (true)
            LogError(localException2);
        }
      }
    }
    catch (Exception localException1)
    {
      StatisticsCollector.newError(localException1, "AppBuilderModule.sendSMS");
      LogError(localException1);
    }
  }

  private void writeConfiguration()
  {
  }

  public final void addNativeFeature(NATIVE_FEATURES paramNATIVE_FEATURES, Object paramObject1, Object paramObject2)
  {
    while (true)
    {
      HashMap localHashMap1;
      try
      {
        if (this.nativeFeatures.containsKey(paramNATIVE_FEATURES))
          continue;
        localHashMap1 = new HashMap();
        if (!paramNATIVE_FEATURES.equals(AppBuilderModule.NATIVE_FEATURES.SMS))
          continue;
        HashMap localHashMap5 = (HashMap)paramObject2;
        localHashMap1.put(AppNativeFeature.SMS.TEXT, localHashMap5.get("text"));
        this.nativeFeatures.put(paramNATIVE_FEATURES, localHashMap1);
        if ((!this.nativeFeatures.containsKey(paramNATIVE_FEATURES)) || (!((HashMap)this.nativeFeatures.get(paramNATIVE_FEATURES)).containsKey(paramObject1)))
          break;
        ((HashMap)this.nativeFeatures.get(paramNATIVE_FEATURES)).put(paramObject1, paramObject2);
        return;
        if (paramNATIVE_FEATURES.equals(AppBuilderModule.NATIVE_FEATURES.EMAIL))
        {
          HashMap localHashMap4 = (HashMap)paramObject2;
          localHashMap1.put(AppNativeFeature.EMAIL.ADDRESS, null);
          localHashMap1.put(AppNativeFeature.EMAIL.SUBJECT, localHashMap4.get("subject"));
          localHashMap1.put(AppNativeFeature.EMAIL.TEXT, localHashMap4.get("text"));
          continue;
        }
      }
      catch (Exception localException)
      {
        StatisticsCollector.newError(localException, "AppBuilderModule.addNativeFeature()");
        LogError(localException);
        return;
      }
      if (paramNATIVE_FEATURES.equals(AppBuilderModule.NATIVE_FEATURES.ADD_CONTACT))
      {
        HashMap localHashMap3 = (HashMap)paramObject2;
        localHashMap1.put(AppNativeFeature.CONTACT.NAME, localHashMap3.get("contactName"));
        localHashMap1.put(AppNativeFeature.CONTACT.PHONE, localHashMap3.get("contactNumber"));
        localHashMap1.put(AppNativeFeature.CONTACT.EMAIL, localHashMap3.get("contactEmail"));
        localHashMap1.put(AppNativeFeature.CONTACT.WEBSITE, localHashMap3.get("contactSite"));
        continue;
      }
      if (!paramNATIVE_FEATURES.equals(AppBuilderModule.NATIVE_FEATURES.ADD_EVENT))
        continue;
      HashMap localHashMap2 = (HashMap)paramObject2;
      localHashMap1.put(AppNativeFeature.EVENT.TITLE, localHashMap2.get("title"));
      localHashMap1.put(AppNativeFeature.EVENT.BEGIN, localHashMap2.get("begin"));
      localHashMap1.put(AppNativeFeature.EVENT.END, localHashMap2.get("end"));
      localHashMap1.put(AppNativeFeature.EVENT.FREQUENCY, localHashMap2.get("frequency"));
    }
  }

  public void create()
  {
  }

  public void designButton(TextView paramTextView, BarDesigner.TitleDesign paramTitleDesign)
  {
    paramTextView.setTextColor(paramTitleDesign.textColor);
    paramTextView.setTextSize(paramTitleDesign.fontSize);
    if (paramTitleDesign.fontWeight.compareTo("bold") == 0)
    {
      paramTextView.setTypeface(null, 1);
      if (paramTitleDesign.textAlignment.compareTo("left") != 0)
        break label108;
      paramTextView.setGravity(3);
    }
    label108: 
    do
    {
      return;
      if (paramTitleDesign.fontWeight.compareTo("italic") == 0)
      {
        paramTextView.setTypeface(null, 2);
        break;
      }
      if ((paramTitleDesign.fontWeight.compareTo("") != 0) && (paramTitleDesign.fontWeight.length() != 0))
        break;
      paramTextView.setTypeface(null, 0);
      break;
      if (paramTitleDesign.textAlignment.compareTo("center") != 0)
        continue;
      paramTextView.setGravity(1);
      return;
    }
    while (paramTitleDesign.textAlignment.compareTo("right") != 0);
    paramTextView.setGravity(5);
  }

  public void destroy()
  {
  }

  public void disableSwipe()
  {
    this.rootContainer.disableSwipe();
  }

  public void drawTopBarLeftButton(View paramView)
  {
    if (paramView != null);
    try
    {
      this.topBarLeftButton.removeAllViews();
      this.topBarLeftButton.addView(paramView);
      this.topBarLeftButton.setVisibility(0);
      return;
    }
    catch (Exception localException)
    {
      LogError(localException);
    }
  }

  public void drawTopBarRightButton(View paramView)
  {
    if (paramView != null);
    try
    {
      this.topBarRightButton.removeAllViews();
      this.topBarRightButton.addView(paramView);
      this.topBarRightButton.setVisibility(0);
      return;
    }
    catch (Exception localException)
    {
      LogError(localException);
    }
  }

  public void drawTopBarTitleView(View paramView, int paramInt)
    throws NullPointerException
  {
    if (paramView != null)
    {
      this.titleHolder.removeAllViews();
      if ((paramInt != -1) || (paramInt != 0))
        this.titleHolder.setGravity(paramInt);
      while (true)
      {
        this.titleHolder.addView(paramView);
        return;
        this.titleHolder.setGravity(17);
      }
    }
    throw new NullPointerException("View object is null");
  }

  public void forceCloseModule(int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("closeme_flag", "closeme");
    localIntent.putExtra("new_order", paramInt);
    setResult(-1, localIntent);
    finish();
  }

  protected final String getAdvType()
  {
    return this.advData.getAdvType();
  }

  public final Serializable getSession()
  {
    return this.session;
  }

  protected final boolean hasAdView()
  {
    return this.adView != null;
  }

  public void hideMenu()
  {
    if ((this.showSideBar) && (this.isShown))
    {
      this.rootContainer.clearAnimation();
      this.rootContainer.startAnimation(this.animHideMenu);
      this.isShown = false;
    }
  }

  public void hideTopBar()
  {
    try
    {
      this.topBar.setVisibility(8);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  public void invisibleTopBar()
  {
    try
    {
      this.topBar.setVisibility(4);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramIntent != null)
    {
      String str = paramIntent.getStringExtra("closeme_flag");
      if ((!TextUtils.isEmpty(str)) && (str.compareToIgnoreCase("closeme") == 0))
        forceCloseModule(paramIntent.getIntExtra("new_order", -1));
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.screenWidth = getWindowManager().getDefaultDisplay().getWidth();
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams((int)(this.screenWidth - 0.15D * this.screenWidth), -1);
    this.menuContainer.setLayoutParams(localLayoutParams1);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(this.screenWidth, -1);
    this.userContainer.setLayoutParams(localLayoutParams2);
    if (this.isShown)
    {
      FrameLayout.LayoutParams localLayoutParams3 = new FrameLayout.LayoutParams((int)(this.screenWidth + 0.85D * this.screenWidth), -1);
      localLayoutParams3.gravity = 3;
      localLayoutParams3.setMargins(0, 0, 0, 0);
      this.rootContainer.setLayoutParams(localLayoutParams3);
      return;
    }
    FrameLayout.LayoutParams localLayoutParams4 = new FrameLayout.LayoutParams((int)(this.screenWidth + 0.85D * this.screenWidth), -1);
    localLayoutParams4.gravity = 3;
    localLayoutParams4.setMargins(-(this.screenWidth - (int)(0.15D * this.screenWidth)), 0, 0, 0);
    this.rootContainer.setLayoutParams(localLayoutParams4);
  }

  // ERROR //
  public final void onCreate(Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: invokevirtual 1124	com/appbuilder/sdk/android/AppBuilderModuleMain:getWindowManager	()Landroid/view/WindowManager;
    //   5: invokeinterface 1130 1 0
    //   10: invokevirtual 1135	android/view/Display:getWidth	()I
    //   13: putfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   16: aload_0
    //   17: aload_0
    //   18: invokevirtual 1152	com/appbuilder/sdk/android/AppBuilderModuleMain:getResources	()Landroid/content/res/Resources;
    //   21: invokevirtual 1158	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   24: getfield 1161	android/util/DisplayMetrics:density	F
    //   27: putfield 730	com/appbuilder/sdk/android/AppBuilderModuleMain:density	F
    //   30: aload_0
    //   31: iconst_1
    //   32: invokevirtual 1165	com/appbuilder/sdk/android/AppBuilderModuleMain:requestWindowFeature	(I)Z
    //   35: pop
    //   36: aload_0
    //   37: aload_1
    //   38: invokespecial 1167	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   41: aload_0
    //   42: aload_0
    //   43: invokestatic 1173	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
    //   46: putfield 184	com/appbuilder/sdk/android/AppBuilderModuleMain:layoutInflater	Landroid/view/LayoutInflater;
    //   49: aload_0
    //   50: aload_0
    //   51: invokespecial 1175	com/appbuilder/sdk/android/AppBuilderModuleMain:create_main_layout	()Landroid/widget/LinearLayout;
    //   54: putfield 1177	com/appbuilder/sdk/android/AppBuilderModuleMain:rootRoot	Landroid/widget/LinearLayout;
    //   57: aload_0
    //   58: aload_1
    //   59: putfield 126	com/appbuilder/sdk/android/AppBuilderModuleMain:state	Landroid/os/Bundle;
    //   62: aload_0
    //   63: aload_1
    //   64: ldc_w 1178
    //   67: invokevirtual 1184	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   70: putfield 124	com/appbuilder/sdk/android/AppBuilderModuleMain:session	Ljava/io/Serializable;
    //   73: aload_0
    //   74: invokespecial 1186	com/appbuilder/sdk/android/AppBuilderModuleMain:readConfiguration	()V
    //   77: aload_0
    //   78: invokevirtual 1190	com/appbuilder/sdk/android/AppBuilderModuleMain:getIntent	()Landroid/content/Intent;
    //   81: astore 7
    //   83: aload_0
    //   84: aload 7
    //   86: invokevirtual 1194	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   89: invokevirtual 1199	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   92: putfield 281	com/appbuilder/sdk/android/AppBuilderModuleMain:className	Ljava/lang/String;
    //   95: aload 7
    //   97: invokevirtual 1203	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   100: astore 8
    //   102: aload_0
    //   103: aload 8
    //   105: ldc_w 1205
    //   108: invokevirtual 1184	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   111: checkcast 1084	com/appbuilder/sdk/android/AppAdvData
    //   114: putfield 120	com/appbuilder/sdk/android/AppBuilderModuleMain:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   117: aload_0
    //   118: aload 8
    //   120: ldc_w 1206
    //   123: invokevirtual 1210	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
    //   126: putfield 1212	com/appbuilder/sdk/android/AppBuilderModuleMain:firstStart	Z
    //   129: aload_0
    //   130: getfield 120	com/appbuilder/sdk/android/AppBuilderModuleMain:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   133: ifnull +36 -> 169
    //   136: aload_0
    //   137: getfield 120	com/appbuilder/sdk/android/AppBuilderModuleMain:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   140: invokevirtual 1086	com/appbuilder/sdk/android/AppAdvData:getAdvType	()Ljava/lang/String;
    //   143: invokevirtual 1048	java/lang/String:length	()I
    //   146: ifle +23 -> 169
    //   149: aload_0
    //   150: new 1214	com/appbuilder/sdk/android/AppAdvView
    //   153: dup
    //   154: aload_0
    //   155: aload_0
    //   156: getfield 120	com/appbuilder/sdk/android/AppBuilderModuleMain:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   159: aload_0
    //   160: getfield 1212	com/appbuilder/sdk/android/AppBuilderModuleMain:firstStart	Z
    //   163: invokespecial 1217	com/appbuilder/sdk/android/AppAdvView:<init>	(Landroid/content/Context;Lcom/appbuilder/sdk/android/AppAdvData;Z)V
    //   166: putfield 122	com/appbuilder/sdk/android/AppBuilderModuleMain:adView	Lcom/appbuilder/sdk/android/AppAdvView;
    //   169: aload_0
    //   170: aload 8
    //   172: ldc_w 1219
    //   175: invokevirtual 1221	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   178: putfield 118	com/appbuilder/sdk/android/AppBuilderModuleMain:flurryId	Ljava/lang/String;
    //   181: aload_0
    //   182: aload 8
    //   184: ldc_w 1223
    //   187: invokevirtual 1184	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   190: checkcast 1225	com/appbuilder/sdk/android/Widget
    //   193: putfield 128	com/appbuilder/sdk/android/AppBuilderModuleMain:widget	Lcom/appbuilder/sdk/android/Widget;
    //   196: aload_0
    //   197: aload 8
    //   199: ldc_w 1227
    //   202: invokevirtual 1184	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   205: checkcast 130	java/util/ArrayList
    //   208: putfield 1229	com/appbuilder/sdk/android/AppBuilderModuleMain:widgets	Ljava/util/ArrayList;
    //   211: aload_0
    //   212: aload 8
    //   214: ldc_w 1230
    //   217: invokevirtual 1184	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   220: checkcast 769	com/appbuilder/sdk/android/BarDesigner
    //   223: putfield 767	com/appbuilder/sdk/android/AppBuilderModuleMain:navBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   226: aload_0
    //   227: aload 8
    //   229: ldc_w 1231
    //   232: invokevirtual 1184	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   235: checkcast 769	com/appbuilder/sdk/android/BarDesigner
    //   238: putfield 1233	com/appbuilder/sdk/android/AppBuilderModuleMain:tabBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   241: aload_0
    //   242: aload 8
    //   244: ldc_w 1234
    //   247: invokevirtual 1184	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   250: checkcast 769	com/appbuilder/sdk/android/BarDesigner
    //   253: putfield 1236	com/appbuilder/sdk/android/AppBuilderModuleMain:bottomBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   256: aload_0
    //   257: aload 8
    //   259: ldc_w 1237
    //   262: iconst_0
    //   263: invokevirtual 1240	android/os/Bundle:getBoolean	(Ljava/lang/String;Z)Z
    //   266: invokestatic 1245	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   269: invokevirtual 1248	java/lang/Boolean:booleanValue	()Z
    //   272: putfield 182	com/appbuilder/sdk/android/AppBuilderModuleMain:showSideBar	Z
    //   275: aload_0
    //   276: aload 8
    //   278: ldc_w 1250
    //   281: invokevirtual 1221	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   284: putfield 1252	com/appbuilder/sdk/android/AppBuilderModuleMain:appId	Ljava/lang/String;
    //   287: new 680	java/lang/StringBuilder
    //   290: dup
    //   291: invokespecial 681	java/lang/StringBuilder:<init>	()V
    //   294: getstatic 111	com/appbuilder/sdk/android/AppBuilderModuleMain:TAG	Ljava/lang/String;
    //   297: invokevirtual 687	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: ldc_w 1254
    //   303: invokevirtual 687	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: invokevirtual 692	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: new 680	java/lang/StringBuilder
    //   312: dup
    //   313: invokespecial 681	java/lang/StringBuilder:<init>	()V
    //   316: ldc_w 1256
    //   319: invokevirtual 687	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: aload_0
    //   323: getfield 1252	com/appbuilder/sdk/android/AppBuilderModuleMain:appId	Ljava/lang/String;
    //   326: invokevirtual 687	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   329: invokevirtual 692	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   332: invokestatic 608	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   335: pop
    //   336: aload_0
    //   337: invokestatic 1259	com/appbuilder/sdk/android/pushnotification/AppPushNotificationDB:init	(Landroid/content/Context;)V
    //   340: aload_0
    //   341: new 1261	com/appbuilder/sdk/android/AppBuilderModuleMain$2
    //   344: dup
    //   345: aload_0
    //   346: invokespecial 1262	com/appbuilder/sdk/android/AppBuilderModuleMain$2:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   349: putfield 1264	com/appbuilder/sdk/android/AppBuilderModuleMain:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   352: new 1266	android/content/IntentFilter
    //   355: dup
    //   356: aload_0
    //   357: getfield 1252	com/appbuilder/sdk/android/AppBuilderModuleMain:appId	Ljava/lang/String;
    //   360: invokespecial 1267	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   363: astore 10
    //   365: aload_0
    //   366: aload_0
    //   367: getfield 1264	com/appbuilder/sdk/android/AppBuilderModuleMain:broadcastReceiver	Landroid/content/BroadcastReceiver;
    //   370: aload 10
    //   372: invokevirtual 1271	com/appbuilder/sdk/android/AppBuilderModuleMain:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   375: pop
    //   376: aload_0
    //   377: aload_0
    //   378: ldc_w 1273
    //   381: invokevirtual 1277	com/appbuilder/sdk/android/AppBuilderModuleMain:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   384: checkcast 673	android/app/NotificationManager
    //   387: putfield 671	com/appbuilder/sdk/android/AppBuilderModuleMain:mManager	Landroid/app/NotificationManager;
    //   390: aload_0
    //   391: new 1052	com/appbuilder/sdk/android/SwipeLinearLayout
    //   394: dup
    //   395: aload_0
    //   396: aload_0
    //   397: getfield 182	com/appbuilder/sdk/android/AppBuilderModuleMain:showSideBar	Z
    //   400: invokespecial 1280	com/appbuilder/sdk/android/SwipeLinearLayout:<init>	(Landroid/content/Context;Z)V
    //   403: putfield 285	com/appbuilder/sdk/android/AppBuilderModuleMain:rootContainer	Lcom/appbuilder/sdk/android/SwipeLinearLayout;
    //   406: aload_0
    //   407: getfield 285	com/appbuilder/sdk/android/AppBuilderModuleMain:rootContainer	Lcom/appbuilder/sdk/android/SwipeLinearLayout;
    //   410: iconst_0
    //   411: invokevirtual 1281	com/appbuilder/sdk/android/SwipeLinearLayout:setOrientation	(I)V
    //   414: aload_0
    //   415: new 643	android/widget/LinearLayout
    //   418: dup
    //   419: aload_0
    //   420: invokespecial 709	android/widget/LinearLayout:<init>	(Landroid/content/Context;)V
    //   423: putfield 1139	com/appbuilder/sdk/android/AppBuilderModuleMain:menuContainer	Landroid/widget/LinearLayout;
    //   426: aload_0
    //   427: getfield 1139	com/appbuilder/sdk/android/AppBuilderModuleMain:menuContainer	Landroid/widget/LinearLayout;
    //   430: iconst_1
    //   431: invokevirtual 720	android/widget/LinearLayout:setOrientation	(I)V
    //   434: aload_0
    //   435: getfield 1139	com/appbuilder/sdk/android/AppBuilderModuleMain:menuContainer	Landroid/widget/LinearLayout;
    //   438: ldc 170
    //   440: invokestatic 836	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   443: invokevirtual 827	android/widget/LinearLayout:setBackgroundColor	(I)V
    //   446: aload_0
    //   447: getfield 182	com/appbuilder/sdk/android/AppBuilderModuleMain:showSideBar	Z
    //   450: ifeq +982 -> 1432
    //   453: aload_0
    //   454: invokespecial 1283	com/appbuilder/sdk/android/AppBuilderModuleMain:create_menu_topbar_layout	()Landroid/widget/LinearLayout;
    //   457: astore 18
    //   459: new 711	android/widget/LinearLayout$LayoutParams
    //   462: dup
    //   463: iconst_m1
    //   464: bipush 254
    //   466: invokespecial 714	android/widget/LinearLayout$LayoutParams:<init>	(II)V
    //   469: astore 12
    //   471: aload_0
    //   472: getfield 838	com/appbuilder/sdk/android/AppBuilderModuleMain:homeButton	Landroid/widget/LinearLayout;
    //   475: new 1285	com/appbuilder/sdk/android/AppBuilderModuleMain$3
    //   478: dup
    //   479: aload_0
    //   480: invokespecial 1286	com/appbuilder/sdk/android/AppBuilderModuleMain$3:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   483: invokevirtual 1290	android/widget/LinearLayout:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   486: aload_0
    //   487: new 1292	android/widget/ListView
    //   490: dup
    //   491: aload_0
    //   492: invokespecial 1293	android/widget/ListView:<init>	(Landroid/content/Context;)V
    //   495: putfield 1295	com/appbuilder/sdk/android/AppBuilderModuleMain:widgetList	Landroid/widget/ListView;
    //   498: aload_0
    //   499: getfield 1295	com/appbuilder/sdk/android/AppBuilderModuleMain:widgetList	Landroid/widget/ListView;
    //   502: ldc 170
    //   504: invokestatic 836	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   507: invokevirtual 1296	android/widget/ListView:setBackgroundColor	(I)V
    //   510: aload_0
    //   511: getfield 1295	com/appbuilder/sdk/android/AppBuilderModuleMain:widgetList	Landroid/widget/ListView;
    //   514: ldc 170
    //   516: invokestatic 836	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   519: invokevirtual 1299	android/widget/ListView:setCacheColorHint	(I)V
    //   522: aload_0
    //   523: getfield 1295	com/appbuilder/sdk/android/AppBuilderModuleMain:widgetList	Landroid/widget/ListView;
    //   526: iconst_0
    //   527: invokevirtual 1303	android/widget/ListView:setVerticalScrollBarEnabled	(Z)V
    //   530: aload_0
    //   531: getfield 1295	com/appbuilder/sdk/android/AppBuilderModuleMain:widgetList	Landroid/widget/ListView;
    //   534: iconst_0
    //   535: invokevirtual 1303	android/widget/ListView:setVerticalScrollBarEnabled	(Z)V
    //   538: iconst_0
    //   539: istore 19
    //   541: iload 19
    //   543: aload_0
    //   544: getfield 1229	com/appbuilder/sdk/android/AppBuilderModuleMain:widgets	Ljava/util/ArrayList;
    //   547: invokevirtual 430	java/util/ArrayList:size	()I
    //   550: if_icmpge +75 -> 625
    //   553: aload_0
    //   554: getfield 1229	com/appbuilder/sdk/android/AppBuilderModuleMain:widgets	Ljava/util/ArrayList;
    //   557: iload 19
    //   559: invokevirtual 335	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   562: checkcast 1225	com/appbuilder/sdk/android/Widget
    //   565: invokevirtual 1306	com/appbuilder/sdk/android/Widget:isAddToSidebar	()Z
    //   568: ifeq +20 -> 588
    //   571: aload_0
    //   572: getfield 178	com/appbuilder/sdk/android/AppBuilderModuleMain:actualWidgetList	Ljava/util/ArrayList;
    //   575: aload_0
    //   576: getfield 1229	com/appbuilder/sdk/android/AppBuilderModuleMain:widgets	Ljava/util/ArrayList;
    //   579: iload 19
    //   581: invokevirtual 335	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   584: invokevirtual 323	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   587: pop
    //   588: iinc 19 1
    //   591: goto -50 -> 541
    //   594: astore 5
    //   596: aload 5
    //   598: invokestatic 1308	com/appbuilder/sdk/android/AppBuilderModuleMain:LogWarning	(Ljava/lang/Exception;)V
    //   601: goto -528 -> 73
    //   604: astore_2
    //   605: aload_2
    //   606: ldc_w 1310
    //   609: invokestatic 427	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   612: aload_2
    //   613: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   616: aload_0
    //   617: invokevirtual 1081	com/appbuilder/sdk/android/AppBuilderModuleMain:finish	()V
    //   620: aload_0
    //   621: invokevirtual 1312	com/appbuilder/sdk/android/AppBuilderModuleMain:create	()V
    //   624: return
    //   625: iconst_0
    //   626: istore 20
    //   628: aload_0
    //   629: getfield 178	com/appbuilder/sdk/android/AppBuilderModuleMain:actualWidgetList	Ljava/util/ArrayList;
    //   632: invokevirtual 430	java/util/ArrayList:size	()I
    //   635: istore 21
    //   637: iload 20
    //   639: iload 21
    //   641: if_icmpge +60 -> 701
    //   644: aload_0
    //   645: getfield 178	com/appbuilder/sdk/android/AppBuilderModuleMain:actualWidgetList	Ljava/util/ArrayList;
    //   648: iload 20
    //   650: invokevirtual 335	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   653: checkcast 1225	com/appbuilder/sdk/android/Widget
    //   656: invokevirtual 1315	com/appbuilder/sdk/android/Widget:getFaviconFilePath	()Ljava/lang/String;
    //   659: invokestatic 1317	com/appbuilder/sdk/android/AppBuilderModuleMain:proccessBitmap	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   662: astore 24
    //   664: aload_0
    //   665: getfield 190	com/appbuilder/sdk/android/AppBuilderModuleMain:thumbnails	Ljava/util/ArrayList;
    //   668: iload 20
    //   670: aload 24
    //   672: invokevirtual 1320	java/util/ArrayList:add	(ILjava/lang/Object;)V
    //   675: iinc 20 1
    //   678: goto -50 -> 628
    //   681: astore 23
    //   683: aload 23
    //   685: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   688: goto -13 -> 675
    //   691: astore 6
    //   693: aload 6
    //   695: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   698: goto -78 -> 620
    //   701: new 1322	com/appbuilder/sdk/android/MenuAdapter
    //   704: dup
    //   705: aload_0
    //   706: aload_0
    //   707: getfield 178	com/appbuilder/sdk/android/AppBuilderModuleMain:actualWidgetList	Ljava/util/ArrayList;
    //   710: aload_0
    //   711: getfield 190	com/appbuilder/sdk/android/AppBuilderModuleMain:thumbnails	Ljava/util/ArrayList;
    //   714: invokespecial 1325	com/appbuilder/sdk/android/MenuAdapter:<init>	(Landroid/content/Context;Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    //   717: astore 22
    //   719: aload_0
    //   720: getfield 1295	com/appbuilder/sdk/android/AppBuilderModuleMain:widgetList	Landroid/widget/ListView;
    //   723: aload 22
    //   725: invokevirtual 1329	android/widget/ListView:setAdapter	(Landroid/widget/ListAdapter;)V
    //   728: aload_0
    //   729: getfield 1295	com/appbuilder/sdk/android/AppBuilderModuleMain:widgetList	Landroid/widget/ListView;
    //   732: new 1331	com/appbuilder/sdk/android/AppBuilderModuleMain$4
    //   735: dup
    //   736: aload_0
    //   737: invokespecial 1332	com/appbuilder/sdk/android/AppBuilderModuleMain$4:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   740: invokevirtual 1336	android/widget/ListView:setOnItemClickListener	(Landroid/widget/AdapterView$OnItemClickListener;)V
    //   743: aload_0
    //   744: getfield 1139	com/appbuilder/sdk/android/AppBuilderModuleMain:menuContainer	Landroid/widget/LinearLayout;
    //   747: aload 18
    //   749: aload 12
    //   751: invokevirtual 1339	android/widget/LinearLayout:addView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   754: aload_0
    //   755: getfield 1139	com/appbuilder/sdk/android/AppBuilderModuleMain:menuContainer	Landroid/widget/LinearLayout;
    //   758: aload_0
    //   759: getfield 1295	com/appbuilder/sdk/android/AppBuilderModuleMain:widgetList	Landroid/widget/ListView;
    //   762: aload 12
    //   764: invokevirtual 1339	android/widget/LinearLayout:addView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   767: aload_0
    //   768: new 643	android/widget/LinearLayout
    //   771: dup
    //   772: aload_0
    //   773: invokespecial 709	android/widget/LinearLayout:<init>	(Landroid/content/Context;)V
    //   776: putfield 1141	com/appbuilder/sdk/android/AppBuilderModuleMain:userContainer	Landroid/widget/LinearLayout;
    //   779: aload_0
    //   780: getfield 1141	com/appbuilder/sdk/android/AppBuilderModuleMain:userContainer	Landroid/widget/LinearLayout;
    //   783: iconst_1
    //   784: invokevirtual 720	android/widget/LinearLayout:setOrientation	(I)V
    //   787: aload_0
    //   788: aload_0
    //   789: invokespecial 1341	com/appbuilder/sdk/android/AppBuilderModuleMain:create_topbar_layout	()Landroid/widget/LinearLayout;
    //   792: putfield 1095	com/appbuilder/sdk/android/AppBuilderModuleMain:topBar	Landroid/widget/LinearLayout;
    //   795: aload_0
    //   796: getfield 859	com/appbuilder/sdk/android/AppBuilderModuleMain:topBarLeftButton	Landroid/widget/LinearLayout;
    //   799: new 1343	com/appbuilder/sdk/android/AppBuilderModuleMain$5
    //   802: dup
    //   803: aload_0
    //   804: invokespecial 1344	com/appbuilder/sdk/android/AppBuilderModuleMain$5:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   807: invokevirtual 1290	android/widget/LinearLayout:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   810: aload_0
    //   811: getfield 1141	com/appbuilder/sdk/android/AppBuilderModuleMain:userContainer	Landroid/widget/LinearLayout;
    //   814: aload_0
    //   815: getfield 1095	com/appbuilder/sdk/android/AppBuilderModuleMain:topBar	Landroid/widget/LinearLayout;
    //   818: aload 12
    //   820: invokevirtual 1339	android/widget/LinearLayout:addView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   823: new 711	android/widget/LinearLayout$LayoutParams
    //   826: dup
    //   827: aload_0
    //   828: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   831: i2d
    //   832: ldc2_w 1136
    //   835: aload_0
    //   836: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   839: i2d
    //   840: dmul
    //   841: dsub
    //   842: d2i
    //   843: iconst_m1
    //   844: invokespecial 714	android/widget/LinearLayout$LayoutParams:<init>	(II)V
    //   847: astore 14
    //   849: aload_0
    //   850: getfield 285	com/appbuilder/sdk/android/AppBuilderModuleMain:rootContainer	Lcom/appbuilder/sdk/android/SwipeLinearLayout;
    //   853: aload_0
    //   854: getfield 1139	com/appbuilder/sdk/android/AppBuilderModuleMain:menuContainer	Landroid/widget/LinearLayout;
    //   857: aload 14
    //   859: invokevirtual 1345	com/appbuilder/sdk/android/SwipeLinearLayout:addView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   862: new 711	android/widget/LinearLayout$LayoutParams
    //   865: dup
    //   866: aload_0
    //   867: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   870: iconst_m1
    //   871: invokespecial 714	android/widget/LinearLayout$LayoutParams:<init>	(II)V
    //   874: astore 15
    //   876: aload_0
    //   877: getfield 285	com/appbuilder/sdk/android/AppBuilderModuleMain:rootContainer	Lcom/appbuilder/sdk/android/SwipeLinearLayout;
    //   880: aload_0
    //   881: getfield 1141	com/appbuilder/sdk/android/AppBuilderModuleMain:userContainer	Landroid/widget/LinearLayout;
    //   884: aload 15
    //   886: invokevirtual 1345	com/appbuilder/sdk/android/SwipeLinearLayout:addView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   889: new 818	android/widget/FrameLayout$LayoutParams
    //   892: dup
    //   893: aload_0
    //   894: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   897: i2d
    //   898: ldc2_w 1142
    //   901: aload_0
    //   902: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   905: i2d
    //   906: dmul
    //   907: dadd
    //   908: d2i
    //   909: iconst_m1
    //   910: invokespecial 819	android/widget/FrameLayout$LayoutParams:<init>	(II)V
    //   913: astore 16
    //   915: aload 16
    //   917: iconst_3
    //   918: putfield 1144	android/widget/FrameLayout$LayoutParams:gravity	I
    //   921: aload 16
    //   923: aload_0
    //   924: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   927: ldc2_w 1136
    //   930: aload_0
    //   931: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   934: i2d
    //   935: dmul
    //   936: d2i
    //   937: isub
    //   938: ineg
    //   939: iconst_0
    //   940: iconst_0
    //   941: iconst_0
    //   942: invokevirtual 1145	android/widget/FrameLayout$LayoutParams:setMargins	(IIII)V
    //   945: aload_0
    //   946: getfield 816	com/appbuilder/sdk/android/AppBuilderModuleMain:rootFrameLayout	Landroid/widget/FrameLayout;
    //   949: aload_0
    //   950: getfield 285	com/appbuilder/sdk/android/AppBuilderModuleMain:rootContainer	Lcom/appbuilder/sdk/android/SwipeLinearLayout;
    //   953: aload 16
    //   955: invokevirtual 1346	android/widget/FrameLayout:addView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   958: aload_0
    //   959: new 643	android/widget/LinearLayout
    //   962: dup
    //   963: aload_0
    //   964: invokespecial 709	android/widget/LinearLayout:<init>	(Landroid/content/Context;)V
    //   967: putfield 241	com/appbuilder/sdk/android/AppBuilderModuleMain:dialogHolder	Landroid/widget/LinearLayout;
    //   970: aload_0
    //   971: getfield 241	com/appbuilder/sdk/android/AppBuilderModuleMain:dialogHolder	Landroid/widget/LinearLayout;
    //   974: iconst_4
    //   975: invokevirtual 653	android/widget/LinearLayout:setVisibility	(I)V
    //   978: new 818	android/widget/FrameLayout$LayoutParams
    //   981: dup
    //   982: bipush 254
    //   984: bipush 254
    //   986: invokespecial 819	android/widget/FrameLayout$LayoutParams:<init>	(II)V
    //   989: astore 17
    //   991: aload 17
    //   993: bipush 17
    //   995: putfield 1144	android/widget/FrameLayout$LayoutParams:gravity	I
    //   998: aload_0
    //   999: getfield 241	com/appbuilder/sdk/android/AppBuilderModuleMain:dialogHolder	Landroid/widget/LinearLayout;
    //   1002: aload 17
    //   1004: invokevirtual 724	android/widget/LinearLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   1007: aload_0
    //   1008: getfield 816	com/appbuilder/sdk/android/AppBuilderModuleMain:rootFrameLayout	Landroid/widget/FrameLayout;
    //   1011: aload_0
    //   1012: getfield 241	com/appbuilder/sdk/android/AppBuilderModuleMain:dialogHolder	Landroid/widget/LinearLayout;
    //   1015: invokevirtual 1347	android/widget/FrameLayout:addView	(Landroid/view/View;)V
    //   1018: aload_0
    //   1019: new 1349	android/view/animation/TranslateAnimation
    //   1022: dup
    //   1023: fconst_0
    //   1024: aload_0
    //   1025: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   1028: ldc2_w 1136
    //   1031: aload_0
    //   1032: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   1035: i2d
    //   1036: dmul
    //   1037: d2i
    //   1038: isub
    //   1039: i2f
    //   1040: fconst_0
    //   1041: fconst_0
    //   1042: invokespecial 1352	android/view/animation/TranslateAnimation:<init>	(FFFF)V
    //   1045: putfield 246	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowMenu	Landroid/view/animation/TranslateAnimation;
    //   1048: aload_0
    //   1049: getfield 246	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowMenu	Landroid/view/animation/TranslateAnimation;
    //   1052: new 1354	com/appbuilder/sdk/android/AppBuilderModuleMain$SmoothInterpolator
    //   1055: dup
    //   1056: aload_0
    //   1057: invokespecial 1355	com/appbuilder/sdk/android/AppBuilderModuleMain$SmoothInterpolator:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   1060: invokevirtual 1359	android/view/animation/TranslateAnimation:setInterpolator	(Landroid/view/animation/Interpolator;)V
    //   1063: aload_0
    //   1064: getfield 246	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowMenu	Landroid/view/animation/TranslateAnimation;
    //   1067: ldc2_w 1360
    //   1070: invokevirtual 1364	android/view/animation/TranslateAnimation:setDuration	(J)V
    //   1073: aload_0
    //   1074: getfield 246	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowMenu	Landroid/view/animation/TranslateAnimation;
    //   1077: iconst_1
    //   1078: invokevirtual 1367	android/view/animation/TranslateAnimation:setFillEnabled	(Z)V
    //   1081: aload_0
    //   1082: getfield 246	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowMenu	Landroid/view/animation/TranslateAnimation;
    //   1085: new 1369	com/appbuilder/sdk/android/AppBuilderModuleMain$6
    //   1088: dup
    //   1089: aload_0
    //   1090: invokespecial 1370	com/appbuilder/sdk/android/AppBuilderModuleMain$6:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   1093: invokevirtual 1374	android/view/animation/TranslateAnimation:setAnimationListener	(Landroid/view/animation/Animation$AnimationListener;)V
    //   1096: aload_0
    //   1097: new 1349	android/view/animation/TranslateAnimation
    //   1100: dup
    //   1101: fconst_0
    //   1102: aload_0
    //   1103: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   1106: ldc2_w 1136
    //   1109: aload_0
    //   1110: getfield 237	com/appbuilder/sdk/android/AppBuilderModuleMain:screenWidth	I
    //   1113: i2d
    //   1114: dmul
    //   1115: d2i
    //   1116: isub
    //   1117: ineg
    //   1118: i2f
    //   1119: fconst_0
    //   1120: fconst_0
    //   1121: invokespecial 1352	android/view/animation/TranslateAnimation:<init>	(FFFF)V
    //   1124: putfield 288	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideMenu	Landroid/view/animation/TranslateAnimation;
    //   1127: aload_0
    //   1128: getfield 288	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideMenu	Landroid/view/animation/TranslateAnimation;
    //   1131: new 1354	com/appbuilder/sdk/android/AppBuilderModuleMain$SmoothInterpolator
    //   1134: dup
    //   1135: aload_0
    //   1136: invokespecial 1355	com/appbuilder/sdk/android/AppBuilderModuleMain$SmoothInterpolator:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   1139: invokevirtual 1359	android/view/animation/TranslateAnimation:setInterpolator	(Landroid/view/animation/Interpolator;)V
    //   1142: aload_0
    //   1143: getfield 288	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideMenu	Landroid/view/animation/TranslateAnimation;
    //   1146: ldc2_w 1360
    //   1149: invokevirtual 1364	android/view/animation/TranslateAnimation:setDuration	(J)V
    //   1152: aload_0
    //   1153: getfield 288	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideMenu	Landroid/view/animation/TranslateAnimation;
    //   1156: iconst_1
    //   1157: invokevirtual 1367	android/view/animation/TranslateAnimation:setFillEnabled	(Z)V
    //   1160: aload_0
    //   1161: getfield 288	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideMenu	Landroid/view/animation/TranslateAnimation;
    //   1164: new 1376	com/appbuilder/sdk/android/AppBuilderModuleMain$7
    //   1167: dup
    //   1168: aload_0
    //   1169: invokespecial 1377	com/appbuilder/sdk/android/AppBuilderModuleMain$7:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   1172: invokevirtual 1374	android/view/animation/TranslateAnimation:setAnimationListener	(Landroid/view/animation/Animation$AnimationListener;)V
    //   1175: aload_0
    //   1176: new 1379	android/view/animation/AlphaAnimation
    //   1179: dup
    //   1180: fconst_0
    //   1181: fconst_1
    //   1182: invokespecial 1382	android/view/animation/AlphaAnimation:<init>	(FF)V
    //   1185: putfield 1384	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowImage	Landroid/view/animation/AlphaAnimation;
    //   1188: aload_0
    //   1189: getfield 1384	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowImage	Landroid/view/animation/AlphaAnimation;
    //   1192: new 1386	android/view/animation/LinearInterpolator
    //   1195: dup
    //   1196: invokespecial 1387	android/view/animation/LinearInterpolator:<init>	()V
    //   1199: invokevirtual 1388	android/view/animation/AlphaAnimation:setInterpolator	(Landroid/view/animation/Interpolator;)V
    //   1202: aload_0
    //   1203: getfield 1384	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowImage	Landroid/view/animation/AlphaAnimation;
    //   1206: ldc2_w 1389
    //   1209: invokevirtual 1391	android/view/animation/AlphaAnimation:setDuration	(J)V
    //   1212: aload_0
    //   1213: getfield 1384	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowImage	Landroid/view/animation/AlphaAnimation;
    //   1216: new 1393	com/appbuilder/sdk/android/AppBuilderModuleMain$8
    //   1219: dup
    //   1220: aload_0
    //   1221: invokespecial 1394	com/appbuilder/sdk/android/AppBuilderModuleMain$8:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   1224: invokevirtual 1395	android/view/animation/AlphaAnimation:setAnimationListener	(Landroid/view/animation/Animation$AnimationListener;)V
    //   1227: aload_0
    //   1228: new 1379	android/view/animation/AlphaAnimation
    //   1231: dup
    //   1232: fconst_1
    //   1233: fconst_0
    //   1234: invokespecial 1382	android/view/animation/AlphaAnimation:<init>	(FF)V
    //   1237: putfield 1397	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideImage	Landroid/view/animation/AlphaAnimation;
    //   1240: aload_0
    //   1241: getfield 1397	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideImage	Landroid/view/animation/AlphaAnimation;
    //   1244: new 1386	android/view/animation/LinearInterpolator
    //   1247: dup
    //   1248: invokespecial 1387	android/view/animation/LinearInterpolator:<init>	()V
    //   1251: invokevirtual 1388	android/view/animation/AlphaAnimation:setInterpolator	(Landroid/view/animation/Interpolator;)V
    //   1254: aload_0
    //   1255: getfield 1397	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideImage	Landroid/view/animation/AlphaAnimation;
    //   1258: ldc2_w 1389
    //   1261: invokevirtual 1391	android/view/animation/AlphaAnimation:setDuration	(J)V
    //   1264: aload_0
    //   1265: getfield 1397	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideImage	Landroid/view/animation/AlphaAnimation;
    //   1268: new 1399	com/appbuilder/sdk/android/AppBuilderModuleMain$9
    //   1271: dup
    //   1272: aload_0
    //   1273: invokespecial 1400	com/appbuilder/sdk/android/AppBuilderModuleMain$9:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   1276: invokevirtual 1395	android/view/animation/AlphaAnimation:setAnimationListener	(Landroid/view/animation/Animation$AnimationListener;)V
    //   1279: aload_0
    //   1280: new 1379	android/view/animation/AlphaAnimation
    //   1283: dup
    //   1284: fconst_0
    //   1285: fconst_1
    //   1286: invokespecial 1382	android/view/animation/AlphaAnimation:<init>	(FF)V
    //   1289: putfield 658	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowDialog	Landroid/view/animation/AlphaAnimation;
    //   1292: aload_0
    //   1293: getfield 658	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowDialog	Landroid/view/animation/AlphaAnimation;
    //   1296: ldc2_w 1401
    //   1299: invokevirtual 1391	android/view/animation/AlphaAnimation:setDuration	(J)V
    //   1302: aload_0
    //   1303: getfield 658	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowDialog	Landroid/view/animation/AlphaAnimation;
    //   1306: iconst_1
    //   1307: invokevirtual 1405	android/view/animation/AlphaAnimation:setFillAfter	(Z)V
    //   1310: aload_0
    //   1311: getfield 658	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowDialog	Landroid/view/animation/AlphaAnimation;
    //   1314: new 1386	android/view/animation/LinearInterpolator
    //   1317: dup
    //   1318: invokespecial 1387	android/view/animation/LinearInterpolator:<init>	()V
    //   1321: invokevirtual 1388	android/view/animation/AlphaAnimation:setInterpolator	(Landroid/view/animation/Interpolator;)V
    //   1324: aload_0
    //   1325: getfield 658	com/appbuilder/sdk/android/AppBuilderModuleMain:animShowDialog	Landroid/view/animation/AlphaAnimation;
    //   1328: new 1407	com/appbuilder/sdk/android/AppBuilderModuleMain$10
    //   1331: dup
    //   1332: aload_0
    //   1333: invokespecial 1408	com/appbuilder/sdk/android/AppBuilderModuleMain$10:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   1336: invokevirtual 1395	android/view/animation/AlphaAnimation:setAnimationListener	(Landroid/view/animation/Animation$AnimationListener;)V
    //   1339: aload_0
    //   1340: new 1379	android/view/animation/AlphaAnimation
    //   1343: dup
    //   1344: fconst_1
    //   1345: fconst_0
    //   1346: invokespecial 1382	android/view/animation/AlphaAnimation:<init>	(FF)V
    //   1349: putfield 260	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideDialog	Landroid/view/animation/AlphaAnimation;
    //   1352: aload_0
    //   1353: getfield 260	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideDialog	Landroid/view/animation/AlphaAnimation;
    //   1356: ldc2_w 1401
    //   1359: invokevirtual 1391	android/view/animation/AlphaAnimation:setDuration	(J)V
    //   1362: aload_0
    //   1363: getfield 260	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideDialog	Landroid/view/animation/AlphaAnimation;
    //   1366: iconst_1
    //   1367: invokevirtual 1405	android/view/animation/AlphaAnimation:setFillAfter	(Z)V
    //   1370: aload_0
    //   1371: getfield 260	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideDialog	Landroid/view/animation/AlphaAnimation;
    //   1374: new 1386	android/view/animation/LinearInterpolator
    //   1377: dup
    //   1378: invokespecial 1387	android/view/animation/LinearInterpolator:<init>	()V
    //   1381: invokevirtual 1388	android/view/animation/AlphaAnimation:setInterpolator	(Landroid/view/animation/Interpolator;)V
    //   1384: aload_0
    //   1385: getfield 260	com/appbuilder/sdk/android/AppBuilderModuleMain:animHideDialog	Landroid/view/animation/AlphaAnimation;
    //   1388: new 1410	com/appbuilder/sdk/android/AppBuilderModuleMain$11
    //   1391: dup
    //   1392: aload_0
    //   1393: invokespecial 1411	com/appbuilder/sdk/android/AppBuilderModuleMain$11:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   1396: invokevirtual 1395	android/view/animation/AlphaAnimation:setAnimationListener	(Landroid/view/animation/Animation$AnimationListener;)V
    //   1399: aload_0
    //   1400: new 1413	com/appbuilder/sdk/android/AppBuilderModuleMain$12
    //   1403: dup
    //   1404: aload_0
    //   1405: invokespecial 1414	com/appbuilder/sdk/android/AppBuilderModuleMain$12:<init>	(Lcom/appbuilder/sdk/android/AppBuilderModuleMain;)V
    //   1408: putfield 1416	com/appbuilder/sdk/android/AppBuilderModuleMain:swipeInterface	Lcom/appbuilder/sdk/android/OnSwipeInterface;
    //   1411: aload_0
    //   1412: getfield 182	com/appbuilder/sdk/android/AppBuilderModuleMain:showSideBar	Z
    //   1415: ifeq -795 -> 620
    //   1418: aload_0
    //   1419: getfield 285	com/appbuilder/sdk/android/AppBuilderModuleMain:rootContainer	Lcom/appbuilder/sdk/android/SwipeLinearLayout;
    //   1422: aload_0
    //   1423: getfield 1416	com/appbuilder/sdk/android/AppBuilderModuleMain:swipeInterface	Lcom/appbuilder/sdk/android/OnSwipeInterface;
    //   1426: invokevirtual 1420	com/appbuilder/sdk/android/SwipeLinearLayout:setOnSwipeEvents	(Lcom/appbuilder/sdk/android/OnSwipeInterface;)V
    //   1429: goto -809 -> 620
    //   1432: new 711	android/widget/LinearLayout$LayoutParams
    //   1435: dup
    //   1436: iconst_m1
    //   1437: bipush 254
    //   1439: invokespecial 714	android/widget/LinearLayout$LayoutParams:<init>	(II)V
    //   1442: astore 12
    //   1444: goto -677 -> 767
    //   1447: astore_3
    //   1448: aload_3
    //   1449: aload_0
    //   1450: invokevirtual 739	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1453: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   1456: invokestatic 427	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   1459: aload_3
    //   1460: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   1463: return
    //   1464: astore 13
    //   1466: goto -656 -> 810
    //
    // Exception table:
    //   from	to	target	type
    //   62	73	594	java/lang/Exception
    //   0	62	604	java/lang/Exception
    //   73	77	604	java/lang/Exception
    //   596	601	604	java/lang/Exception
    //   693	698	604	java/lang/Exception
    //   644	675	681	java/lang/Exception
    //   77	169	691	java/lang/Exception
    //   169	538	691	java/lang/Exception
    //   541	588	691	java/lang/Exception
    //   628	637	691	java/lang/Exception
    //   683	688	691	java/lang/Exception
    //   701	767	691	java/lang/Exception
    //   767	795	691	java/lang/Exception
    //   795	810	691	java/lang/Exception
    //   810	1429	691	java/lang/Exception
    //   1432	1444	691	java/lang/Exception
    //   620	624	1447	java/lang/Exception
    //   795	810	1464	java/lang/NullPointerException
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }

  public final void onDestroy()
  {
    try
    {
      writeConfiguration();
      if (this.broadcastReceiver != null)
        unregisterReceiver(this.broadcastReceiver);
    }
    catch (Exception localException1)
    {
      try
      {
        destroy();
        super.onDestroy();
        return;
        localException1 = localException1;
        StatisticsCollector.newError(localException1, "AppBuilderModule.onDestroy()");
        LogError(localException1);
      }
      catch (Exception localException2)
      {
        while (true)
        {
          StatisticsCollector.newError(localException2, getClass().getName());
          LogError(localException2);
        }
      }
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return super.onOptionsItemSelected(paramMenuItem);
  }

  public final void onPause()
  {
    try
    {
      int i;
      View localView;
      int j;
      if (this.showSideBar)
      {
        i = this.widgetList.getFirstVisiblePosition();
        localView = this.widgetList.getChildAt(0);
        j = 0;
        if (localView != null)
          break label94;
      }
      while (true)
      {
        this.sPref = getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor localEditor = this.sPref.edit();
        localEditor.putInt("MenuIndex", i);
        localEditor.putInt("MenuCoordinate", j);
        localEditor.commit();
        pause();
        super.onPause();
        return;
        label94: int k = localView.getTop();
        j = k;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        StatisticsCollector.newError(localException, getClass().getName());
        LogError(localException);
      }
    }
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    while (true)
    {
      Object localObject;
      try
      {
        if (this.nativeFeatures.isEmpty())
          return false;
        paramMenu.clear();
        Iterator localIterator = this.nativeFeatures.entrySet().iterator();
        if (!localIterator.hasNext())
          break;
        localObject = ((Map.Entry)localIterator.next()).getKey();
        if (localObject.equals(AppBuilderModule.NATIVE_FEATURES.SMS))
        {
          MenuItem localMenuItem4 = paramMenu.add("");
          localMenuItem4.setTitle("Send SMS");
          localMenuItem4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
          {
            public boolean onMenuItemClick(MenuItem paramMenuItem)
            {
              AppBuilderModuleMain.this.handler.sendEmptyMessage(1);
              return true;
            }
          });
          continue;
        }
      }
      catch (Exception localException)
      {
        StatisticsCollector.newError(localException, "AppBuilderModule.onPrepareOptionsMenu()");
        LogError(localException);
        return false;
      }
      if (localObject.equals(AppBuilderModule.NATIVE_FEATURES.EMAIL))
      {
        MenuItem localMenuItem3 = paramMenu.add("");
        localMenuItem3.setTitle("Send Email");
        localMenuItem3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
          public boolean onMenuItemClick(MenuItem paramMenuItem)
          {
            AppBuilderModuleMain.this.handler.sendEmptyMessage(2);
            return true;
          }
        });
        continue;
      }
      if (localObject.equals(AppBuilderModule.NATIVE_FEATURES.ADD_CONTACT))
      {
        MenuItem localMenuItem2 = paramMenu.add("");
        localMenuItem2.setTitle("Add Contact");
        localMenuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
          public boolean onMenuItemClick(MenuItem paramMenuItem)
          {
            AppBuilderModuleMain.this.handler.sendEmptyMessage(3);
            return true;
          }
        });
        continue;
      }
      if (!localObject.equals(AppBuilderModule.NATIVE_FEATURES.ADD_EVENT))
        continue;
      MenuItem localMenuItem1 = paramMenu.add("");
      localMenuItem1.setTitle("Add Event");
      localMenuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
      {
        public boolean onMenuItemClick(MenuItem paramMenuItem)
        {
          AppBuilderModuleMain.this.handler.sendEmptyMessage(4);
          return true;
        }
      });
    }
    boolean bool = super.onPrepareOptionsMenu(paramMenu);
    return bool;
  }

  public final void onRestart()
  {
    try
    {
      restart();
      super.onRestart();
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        StatisticsCollector.newError(localException, getClass().getName());
        LogError(localException);
      }
    }
  }

  public final void onResume()
  {
    try
    {
      this.foreground = true;
      if (this.showSideBar)
      {
        this.sPref = getSharedPreferences("MyPref", 0);
        this.widgetList.setSelectionFromTop(this.sPref.getInt("MenuIndex", 0), this.sPref.getInt("MenuCoordinate", 0));
      }
      resume();
      super.onResume();
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        StatisticsCollector.newError(localException, getClass().getName());
        LogError(localException);
      }
    }
  }

  public final void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putSerializable("session", this.session);
  }

  public final void onStart()
  {
    try
    {
      this.millis = System.currentTimeMillis();
      HashMap localHashMap = new HashMap();
      localHashMap.put("action", "start");
      FlurryAgent.logEvent(getClass().getSimpleName(), localHashMap);
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          start();
          super.onStart();
          return;
          localException1 = localException1;
          LogError(localException1);
        }
      }
      catch (Exception localException2)
      {
        while (true)
        {
          StatisticsCollector.newError(localException2, getClass().getName());
          LogError(localException2);
        }
      }
    }
  }

  public final void onStop()
  {
    this.foreground = false;
    try
    {
      int i = (int)(System.currentTimeMillis() - this.millis) / 1000;
      HashMap localHashMap = new HashMap();
      localHashMap.put("action", "stop");
      localHashMap.put("usage interval", "" + i);
      FlurryAgent.logEvent(getClass().getSimpleName(), localHashMap);
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          stop();
          super.onPause();
          return;
          localException1 = localException1;
          LogError(localException1);
        }
      }
      catch (Exception localException2)
      {
        while (true)
        {
          StatisticsCollector.newError(localException2, getClass().getName());
          LogError(localException2);
        }
      }
    }
  }

  public void pause()
  {
  }

  public final void removeNativeFeature(AppBuilderModule.NATIVE_FEATURES paramNATIVE_FEATURES)
  {
    if (this.nativeFeatures.containsKey(paramNATIVE_FEATURES))
      this.nativeFeatures.remove(paramNATIVE_FEATURES);
  }

  public void restart()
  {
  }

  public void resume()
  {
  }

  public final void setContentView(int paramInt)
  {
    try
    {
      if (this.adView != null)
      {
        if (this.adView.getParent() != null)
          ((ViewGroup)this.adView.getParent()).removeAllViews();
        this.adLayout.addView(this.adView);
      }
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          if (this.userLayout != null)
            this.userContainer.removeView(this.userLayout);
          this.userLayout = this.layoutInflater.inflate(paramInt, null);
          LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
          localLayoutParams.weight = 1.0F;
          this.userContainer.addView(this.userLayout, localLayoutParams);
          Message localMessage = this.handler.obtainMessage(5, this.userContainer);
          this.handler.sendMessageDelayed(localMessage, 500L);
          super.setContentView(this.rootRoot);
          Log.d("", "");
          return;
          localException1 = localException1;
          LogError(localException1);
        }
      }
      catch (Exception localException2)
      {
        StatisticsCollector.newError(localException2, "AppBilderModule.setContentView(int)");
        LogError(localException2);
      }
    }
  }

  public final void setContentView(View paramView)
  {
    try
    {
      if (this.adView != null)
      {
        if (this.adView.getParent() != null)
          ((ViewGroup)this.adView.getParent()).removeAllViews();
        this.adLayout.addView(this.adView);
      }
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          if (this.userLayout != null)
            this.userContainer.removeView(this.userLayout);
          this.userLayout = paramView;
          LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
          localLayoutParams.weight = 1.0F;
          this.userContainer.addView(this.userLayout, localLayoutParams);
          Message localMessage = this.handler.obtainMessage(5, this.userContainer);
          this.handler.sendMessageDelayed(localMessage, 500L);
          super.setContentView(this.rootRoot);
          return;
          localException1 = localException1;
          LogError(localException1);
        }
      }
      catch (Exception localException2)
      {
        StatisticsCollector.newError(localException2, "AppBuilderModule.setContentView(View)");
        LogError(localException2);
      }
    }
  }

  public final void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    try
    {
      if (this.adView != null)
        this.adLayout.addView(this.adView);
      if (this.userLayout != null)
        this.userContainer.removeView(this.userLayout);
      this.userContainer.addView(paramView, paramLayoutParams);
      this.userLayout = paramView;
      super.setContentView(this.rootRoot);
      return;
    }
    catch (Exception localException)
    {
      StatisticsCollector.newError(localException, "AppBuilderModule.setContentView(View,ViewGroup.LayoutParams)");
      LogError(localException);
    }
  }

  public final void setSession(Serializable paramSerializable)
  {
    this.session = paramSerializable;
  }

  public final void setTitle(int paramInt)
  {
  }

  public final void setTitle(CharSequence paramCharSequence)
  {
  }

  public void setTitleLineAmount(int paramInt)
  {
    if (paramInt > 0)
    {
      if (paramInt > 2)
      {
        this.topBarTitle.setMaxLines(2);
        return;
      }
      this.topBarTitle.setMaxLines(paramInt);
      return;
    }
    this.topBarTitle.setMaxLines(2);
  }

  protected void setTitleView(View paramView)
  {
    this.titleHolder.removeAllViews();
    this.titleHolder.addView(paramView);
  }

  public void setTopBarBackground(int paramInt)
  {
    if (paramInt > 0)
      this.topBar.setBackgroundResource(paramInt);
  }

  public void setTopBarBackground(Bitmap paramBitmap)
  {
    if (paramBitmap != null)
    {
      BitmapDrawable localBitmapDrawable = new BitmapDrawable(paramBitmap);
      this.topBar.setBackgroundDrawable(localBitmapDrawable);
    }
  }

  public void setTopBarLeftButtonOnClickListener(View.OnClickListener paramOnClickListener)
  {
    try
    {
      this.topBarLeftButton.setOnClickListener(paramOnClickListener);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  public void setTopBarLeftButtonText(String paramString, boolean paramBoolean, View.OnClickListener paramOnClickListener)
  {
    try
    {
      this.topBarLeftButton.removeAllViews();
      LinearLayout localLinearLayout = createTextButton(3);
      TextView localTextView = (TextView)localLinearLayout.getChildAt(1);
      ImageView localImageView = (ImageView)localLinearLayout.getChildAt(0);
      localTextView.setTextColor(this.navBarDesign.itemDesign.textColor);
      Drawable localDrawable = localImageView.getBackground();
      localDrawable.setColorFilter(this.navBarDesign.itemDesign.textColor, PorterDuff.Mode.MULTIPLY);
      localImageView.setBackgroundDrawable(localDrawable);
      localTextView.setText(paramString);
      if (!paramBoolean)
        localImageView.setVisibility(8);
      if (paramOnClickListener != null)
        this.topBarLeftButton.setOnClickListener(paramOnClickListener);
      this.topBarLeftButton.addView(localLinearLayout);
      this.topBarLeftButton.setVisibility(0);
      return;
    }
    catch (Exception localException)
    {
      LogError(localException);
    }
  }

  public void setTopBarRightButtonOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.topBarRightButton.setOnClickListener(paramOnClickListener);
  }

  public void setTopBarRightButtonText(String paramString, boolean paramBoolean, View.OnClickListener paramOnClickListener)
  {
    try
    {
      this.topBarRightButton.removeAllViews();
      LinearLayout localLinearLayout = createTextButton(5);
      TextView localTextView = (TextView)localLinearLayout.getChildAt(1);
      ImageView localImageView = (ImageView)localLinearLayout.getChildAt(0);
      localTextView.setTextColor(this.navBarDesign.itemDesign.textColor);
      Drawable localDrawable = localImageView.getBackground();
      localDrawable.setColorFilter(this.navBarDesign.itemDesign.textColor, PorterDuff.Mode.MULTIPLY);
      localImageView.setBackgroundDrawable(localDrawable);
      localTextView.setText(paramString);
      if (!paramBoolean)
        localImageView.setVisibility(8);
      if (paramOnClickListener != null)
        this.topBarRightButton.setOnClickListener(paramOnClickListener);
      this.topBarRightButton.addView(localLinearLayout);
      this.topBarRightButton.setVisibility(0);
      localLinearLayout.setGravity(21);
      return;
    }
    catch (Exception localException)
    {
      LogError(localException);
    }
  }

  public void setTopBarRightVeiw(View paramView, boolean paramBoolean, View.OnClickListener paramOnClickListener)
  {
    try
    {
      this.topBarRightButton.removeAllViews();
      LinearLayout localLinearLayout = new LinearLayout(this);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
      localLinearLayout.setGravity(21);
      localLinearLayout.setOrientation(0);
      localLinearLayout.setLayoutParams(localLayoutParams);
      localLinearLayout.addView(paramView, new LinearLayout.LayoutParams(-2, -2));
      if (paramOnClickListener != null)
        this.topBarRightButton.setOnClickListener(paramOnClickListener);
      this.topBarRightButton.addView(localLinearLayout);
      this.topBarRightButton.setVisibility(0);
      return;
    }
    catch (Exception localException)
    {
      Log.d("", "");
    }
  }

  public void setTopBarTitle(String paramString)
  {
    if ((paramString != null) && (!paramString.equals("")))
      this.topBarTitle.setText(paramString);
  }

  public void setTopBarTitleColor(int paramInt)
  {
    this.topBarTitle.setTextColor(paramInt);
  }

  public void showMenu()
  {
    if ((this.showSideBar) && (!this.isShown))
    {
      this.rootContainer.clearAnimation();
      this.rootContainer.startAnimation(this.animShowMenu);
      this.isShown = true;
    }
  }

  public void start()
  {
  }

  // ERROR //
  public final void startActivity(Intent paramIntent)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_1
    //   3: invokevirtual 1684	android/content/Intent:getAction	()Ljava/lang/String;
    //   6: astore 4
    //   8: aload 4
    //   10: ifnonnull +371 -> 381
    //   13: aload_1
    //   14: ldc_w 1205
    //   17: aload_0
    //   18: getfield 120	com/appbuilder/sdk/android/AppBuilderModuleMain:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   21: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   24: pop
    //   25: aload_1
    //   26: ldc_w 1223
    //   29: aload_0
    //   30: getfield 128	com/appbuilder/sdk/android/AppBuilderModuleMain:widget	Lcom/appbuilder/sdk/android/Widget;
    //   33: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   36: pop
    //   37: aload_1
    //   38: ldc_w 1227
    //   41: aload_0
    //   42: getfield 1229	com/appbuilder/sdk/android/AppBuilderModuleMain:widgets	Ljava/util/ArrayList;
    //   45: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   48: pop
    //   49: aload_1
    //   50: ldc_w 1686
    //   53: aload_0
    //   54: getfield 128	com/appbuilder/sdk/android/AppBuilderModuleMain:widget	Lcom/appbuilder/sdk/android/Widget;
    //   57: invokevirtual 1689	com/appbuilder/sdk/android/Widget:getPluginXmlData	()Ljava/lang/String;
    //   60: invokevirtual 595	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   63: pop
    //   64: aload_1
    //   65: ldc_w 1237
    //   68: aload_0
    //   69: getfield 182	com/appbuilder/sdk/android/AppBuilderModuleMain:showSideBar	Z
    //   72: invokevirtual 585	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   75: pop
    //   76: aload_1
    //   77: ldc_w 1230
    //   80: aload_0
    //   81: getfield 767	com/appbuilder/sdk/android/AppBuilderModuleMain:navBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   84: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   87: pop
    //   88: aload_1
    //   89: ldc_w 1231
    //   92: aload_0
    //   93: getfield 1233	com/appbuilder/sdk/android/AppBuilderModuleMain:tabBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   96: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   99: pop
    //   100: aload_1
    //   101: ldc_w 1206
    //   104: aload_0
    //   105: getfield 1212	com/appbuilder/sdk/android/AppBuilderModuleMain:firstStart	Z
    //   108: invokevirtual 585	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   111: pop
    //   112: aload_1
    //   113: ldc_w 1234
    //   116: aload_0
    //   117: getfield 1236	com/appbuilder/sdk/android/AppBuilderModuleMain:bottomBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   120: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   123: pop
    //   124: aload_1
    //   125: ldc_w 1219
    //   128: aload_0
    //   129: getfield 118	com/appbuilder/sdk/android/AppBuilderModuleMain:flurryId	Ljava/lang/String;
    //   132: invokevirtual 595	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   135: pop
    //   136: aload_1
    //   137: ldc_w 1250
    //   140: aload_0
    //   141: getfield 1252	com/appbuilder/sdk/android/AppBuilderModuleMain:appId	Ljava/lang/String;
    //   144: invokevirtual 595	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   147: pop
    //   148: aload_1
    //   149: aload_0
    //   150: invokevirtual 1693	com/appbuilder/sdk/android/AppBuilderModuleMain:getPackageManager	()Landroid/content/pm/PackageManager;
    //   153: invokevirtual 1697	android/content/Intent:resolveActivity	(Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;
    //   156: invokevirtual 1199	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   159: astore 6
    //   161: aload 6
    //   163: invokestatic 1701	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   166: astore 14
    //   168: aload 14
    //   170: astore 8
    //   172: aload 8
    //   174: invokevirtual 1704	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   177: astore 10
    //   179: aload 10
    //   181: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   184: ldc_w 1706
    //   187: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifne +189 -> 379
    //   193: aload 10
    //   195: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   198: ldc_w 1708
    //   201: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   204: ifne +175 -> 379
    //   207: aload 8
    //   209: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   212: ldc_w 1710
    //   215: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   218: ifne +161 -> 379
    //   221: aload 10
    //   223: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   226: ldc_w 1712
    //   229: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   232: ifne +147 -> 379
    //   235: aload 8
    //   237: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   240: ldc_w 1714
    //   243: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   246: ifne +133 -> 379
    //   249: aload 8
    //   251: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   254: ldc_w 1716
    //   257: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   260: ifne +119 -> 379
    //   263: aload 8
    //   265: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   268: ldc_w 1718
    //   271: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   274: ifne +105 -> 379
    //   277: aload 8
    //   279: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   282: ldc_w 1720
    //   285: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   288: ifne +91 -> 379
    //   291: aload 8
    //   293: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   296: ldc_w 1722
    //   299: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   302: ifne +77 -> 379
    //   305: aload 8
    //   307: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   310: ldc_w 1724
    //   313: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   316: ifne +63 -> 379
    //   319: aload 8
    //   321: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   324: ldc_w 1726
    //   327: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   330: ifne +49 -> 379
    //   333: aload 8
    //   335: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   338: ldc_w 1728
    //   341: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   344: ifne +35 -> 379
    //   347: aload 8
    //   349: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   352: ldc_w 1730
    //   355: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   358: ifne +21 -> 379
    //   361: aload 8
    //   363: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   366: ldc_w 1732
    //   369: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   372: istore 11
    //   374: iload 11
    //   376: ifeq +52 -> 428
    //   379: iconst_1
    //   380: istore_2
    //   381: iload_2
    //   382: ifeq +92 -> 474
    //   385: aload_0
    //   386: aload_1
    //   387: iconst_0
    //   388: invokespecial 1736	android/app/Activity:startActivityForResult	(Landroid/content/Intent;I)V
    //   391: return
    //   392: astore 5
    //   394: aload 5
    //   396: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   399: goto -251 -> 148
    //   402: astore_3
    //   403: aload_3
    //   404: ldc_w 1738
    //   407: invokestatic 427	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   410: aload_3
    //   411: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   414: return
    //   415: astore 7
    //   417: aload 7
    //   419: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   422: aconst_null
    //   423: astore 8
    //   425: goto -253 -> 172
    //   428: aload 10
    //   430: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   433: ldc_w 1740
    //   436: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   439: istore 12
    //   441: iconst_0
    //   442: istore_2
    //   443: iload 12
    //   445: ifne -64 -> 381
    //   448: aload 10
    //   450: invokevirtual 1704	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   453: astore 13
    //   455: aload 13
    //   457: astore 10
    //   459: goto -280 -> 179
    //   462: astore 9
    //   464: aload 9
    //   466: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   469: iconst_0
    //   470: istore_2
    //   471: goto -90 -> 381
    //   474: aload_0
    //   475: ldc_w 1742
    //   478: iconst_1
    //   479: invokestatic 491	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   482: invokevirtual 492	android/widget/Toast:show	()V
    //   485: return
    //
    // Exception table:
    //   from	to	target	type
    //   13	148	392	java/lang/Exception
    //   2	8	402	java/lang/Exception
    //   148	161	402	java/lang/Exception
    //   161	168	402	java/lang/Exception
    //   172	179	402	java/lang/Exception
    //   179	374	402	java/lang/Exception
    //   385	391	402	java/lang/Exception
    //   394	399	402	java/lang/Exception
    //   417	422	402	java/lang/Exception
    //   428	441	402	java/lang/Exception
    //   448	455	402	java/lang/Exception
    //   464	469	402	java/lang/Exception
    //   474	485	402	java/lang/Exception
    //   161	168	415	java/lang/ClassNotFoundException
    //   172	179	462	java/lang/NullPointerException
    //   179	374	462	java/lang/NullPointerException
    //   428	441	462	java/lang/NullPointerException
    //   448	455	462	java/lang/NullPointerException
  }

  // ERROR //
  public final void startActivityForResult(Intent paramIntent, int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual 1684	android/content/Intent:getAction	()Ljava/lang/String;
    //   6: astore 5
    //   8: aload 5
    //   10: ifnonnull +371 -> 381
    //   13: aload_1
    //   14: ldc_w 1205
    //   17: aload_0
    //   18: getfield 120	com/appbuilder/sdk/android/AppBuilderModuleMain:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   21: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   24: pop
    //   25: aload_1
    //   26: ldc_w 1223
    //   29: aload_0
    //   30: getfield 128	com/appbuilder/sdk/android/AppBuilderModuleMain:widget	Lcom/appbuilder/sdk/android/Widget;
    //   33: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   36: pop
    //   37: aload_1
    //   38: ldc_w 1227
    //   41: aload_0
    //   42: getfield 1229	com/appbuilder/sdk/android/AppBuilderModuleMain:widgets	Ljava/util/ArrayList;
    //   45: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   48: pop
    //   49: aload_1
    //   50: ldc_w 1686
    //   53: aload_0
    //   54: getfield 128	com/appbuilder/sdk/android/AppBuilderModuleMain:widget	Lcom/appbuilder/sdk/android/Widget;
    //   57: invokevirtual 1689	com/appbuilder/sdk/android/Widget:getPluginXmlData	()Ljava/lang/String;
    //   60: invokevirtual 595	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   63: pop
    //   64: aload_1
    //   65: ldc_w 1237
    //   68: aload_0
    //   69: getfield 182	com/appbuilder/sdk/android/AppBuilderModuleMain:showSideBar	Z
    //   72: invokevirtual 585	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   75: pop
    //   76: aload_1
    //   77: ldc_w 1230
    //   80: aload_0
    //   81: getfield 767	com/appbuilder/sdk/android/AppBuilderModuleMain:navBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   84: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   87: pop
    //   88: aload_1
    //   89: ldc_w 1231
    //   92: aload_0
    //   93: getfield 1233	com/appbuilder/sdk/android/AppBuilderModuleMain:tabBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   96: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   99: pop
    //   100: aload_1
    //   101: ldc_w 1206
    //   104: aload_0
    //   105: getfield 1212	com/appbuilder/sdk/android/AppBuilderModuleMain:firstStart	Z
    //   108: invokevirtual 585	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   111: pop
    //   112: aload_1
    //   113: ldc_w 1219
    //   116: aload_0
    //   117: getfield 118	com/appbuilder/sdk/android/AppBuilderModuleMain:flurryId	Ljava/lang/String;
    //   120: invokevirtual 595	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   123: pop
    //   124: aload_1
    //   125: ldc_w 1234
    //   128: aload_0
    //   129: getfield 1236	com/appbuilder/sdk/android/AppBuilderModuleMain:bottomBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   132: invokevirtual 580	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   135: pop
    //   136: aload_1
    //   137: ldc_w 1250
    //   140: aload_0
    //   141: getfield 1252	com/appbuilder/sdk/android/AppBuilderModuleMain:appId	Ljava/lang/String;
    //   144: invokevirtual 595	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   147: pop
    //   148: aload_1
    //   149: aload_0
    //   150: invokevirtual 1693	com/appbuilder/sdk/android/AppBuilderModuleMain:getPackageManager	()Landroid/content/pm/PackageManager;
    //   153: invokevirtual 1697	android/content/Intent:resolveActivity	(Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;
    //   156: invokevirtual 1199	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   159: astore 7
    //   161: aload 7
    //   163: invokestatic 1701	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   166: astore 15
    //   168: aload 15
    //   170: astore 9
    //   172: aload 9
    //   174: invokevirtual 1704	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   177: astore 11
    //   179: aload 11
    //   181: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   184: ldc_w 1706
    //   187: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifne +189 -> 379
    //   193: aload 11
    //   195: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   198: ldc_w 1708
    //   201: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   204: ifne +175 -> 379
    //   207: aload 9
    //   209: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   212: ldc_w 1710
    //   215: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   218: ifne +161 -> 379
    //   221: aload 11
    //   223: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   226: ldc_w 1712
    //   229: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   232: ifne +147 -> 379
    //   235: aload 9
    //   237: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   240: ldc_w 1714
    //   243: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   246: ifne +133 -> 379
    //   249: aload 9
    //   251: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   254: ldc_w 1716
    //   257: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   260: ifne +119 -> 379
    //   263: aload 9
    //   265: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   268: ldc_w 1718
    //   271: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   274: ifne +105 -> 379
    //   277: aload 9
    //   279: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   282: ldc_w 1720
    //   285: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   288: ifne +91 -> 379
    //   291: aload 9
    //   293: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   296: ldc_w 1722
    //   299: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   302: ifne +77 -> 379
    //   305: aload 9
    //   307: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   310: ldc_w 1724
    //   313: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   316: ifne +63 -> 379
    //   319: aload 9
    //   321: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   324: ldc_w 1726
    //   327: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   330: ifne +49 -> 379
    //   333: aload 9
    //   335: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   338: ldc_w 1728
    //   341: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   344: ifne +35 -> 379
    //   347: aload 9
    //   349: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   352: ldc_w 1730
    //   355: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   358: ifne +21 -> 379
    //   361: aload 9
    //   363: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   366: ldc_w 1732
    //   369: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   372: istore 12
    //   374: iload 12
    //   376: ifeq +50 -> 426
    //   379: iconst_1
    //   380: istore_3
    //   381: iload_3
    //   382: ifeq +90 -> 472
    //   385: aload_0
    //   386: aload_1
    //   387: iload_2
    //   388: invokespecial 1736	android/app/Activity:startActivityForResult	(Landroid/content/Intent;I)V
    //   391: return
    //   392: astore 6
    //   394: aload 6
    //   396: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   399: goto -251 -> 148
    //   402: astore 4
    //   404: aload 4
    //   406: ldc_w 1738
    //   409: invokestatic 427	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   412: return
    //   413: astore 8
    //   415: aload 8
    //   417: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   420: aconst_null
    //   421: astore 9
    //   423: goto -251 -> 172
    //   426: aload 11
    //   428: invokevirtual 1423	java/lang/Class:getName	()Ljava/lang/String;
    //   431: ldc_w 1740
    //   434: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   437: istore 13
    //   439: iconst_0
    //   440: istore_3
    //   441: iload 13
    //   443: ifne -62 -> 381
    //   446: aload 11
    //   448: invokevirtual 1704	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   451: astore 14
    //   453: aload 14
    //   455: astore 11
    //   457: goto -278 -> 179
    //   460: astore 10
    //   462: aload 10
    //   464: invokestatic 251	com/appbuilder/sdk/android/AppBuilderModuleMain:LogError	(Ljava/lang/Exception;)V
    //   467: iconst_0
    //   468: istore_3
    //   469: goto -88 -> 381
    //   472: aload_0
    //   473: ldc_w 1742
    //   476: iconst_1
    //   477: invokestatic 491	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   480: invokevirtual 492	android/widget/Toast:show	()V
    //   483: return
    //
    // Exception table:
    //   from	to	target	type
    //   13	148	392	java/lang/Exception
    //   2	8	402	java/lang/Exception
    //   148	161	402	java/lang/Exception
    //   161	168	402	java/lang/Exception
    //   172	179	402	java/lang/Exception
    //   179	374	402	java/lang/Exception
    //   385	391	402	java/lang/Exception
    //   394	399	402	java/lang/Exception
    //   415	420	402	java/lang/Exception
    //   426	439	402	java/lang/Exception
    //   446	453	402	java/lang/Exception
    //   462	467	402	java/lang/Exception
    //   472	483	402	java/lang/Exception
    //   161	168	413	java/lang/ClassNotFoundException
    //   172	179	460	java/lang/NullPointerException
    //   179	374	460	java/lang/NullPointerException
    //   426	439	460	java/lang/NullPointerException
    //   446	453	460	java/lang/NullPointerException
  }

  public void stop()
  {
  }

  public void swipeBlock()
  {
    this.swipeBlock = true;
  }

  public void visibleTopBar()
  {
    try
    {
      this.topBar.setVisibility(0);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  public static enum NATIVE_FEATURES
  {
    static
    {
      EMAIL = new NATIVE_FEATURES("EMAIL", 1);
      ADD_CONTACT = new NATIVE_FEATURES("ADD_CONTACT", 2);
      ADD_EVENT = new NATIVE_FEATURES("ADD_EVENT", 3);
      LOCAL_NOTIFICATION = new NATIVE_FEATURES("LOCAL_NOTIFICATION", 4);
      NATIVE_FEATURES[] arrayOfNATIVE_FEATURES = new NATIVE_FEATURES[5];
      arrayOfNATIVE_FEATURES[0] = SMS;
      arrayOfNATIVE_FEATURES[1] = EMAIL;
      arrayOfNATIVE_FEATURES[2] = ADD_CONTACT;
      arrayOfNATIVE_FEATURES[3] = ADD_EVENT;
      arrayOfNATIVE_FEATURES[4] = LOCAL_NOTIFICATION;
      $VALUES = arrayOfNATIVE_FEATURES;
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
 * Qualified Name:     com.appbuilder.sdk.android.AppBuilderModuleMain
 * JD-Core Version:    0.6.0
 */