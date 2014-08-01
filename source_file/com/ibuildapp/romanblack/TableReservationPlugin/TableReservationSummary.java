package com.ibuildapp.romanblack.TableReservationPlugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.BarDesigner.TitleDesign;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.HouresMinutes;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationHTTP;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.Utils;
import com.seppius.i18n.plurals.PluralResources;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class TableReservationSummary extends AppBuilderModuleMain
{
  private final int CANCEL_REQUEST_ERROR = 3;
  private final int DRAW_IMAGE_MAP = 2;
  private final int DRAW_MARGINS = 4;
  private final int HIDE_PROGRESS_DIALOG = 1;
  private final int SHOW_MAP_ACTIVIRY = 10001;
  private final int SHOW_PROGRESS_DIALOG = 0;
  private TextView additionalPointer;
  private TextView additionalText;
  private TextView addressPointer;
  private TextView addressText;
  private int backColor;
  private LinearLayout calendarButton;
  private LinearLayout contentLayout;
  private int contentLayoutH = -1;
  private LinearLayout directioinButton;
  private LinearLayout emailButton;
  private int fontColor;
  private LinearLayout footerLayout;
  private int footerLayoutH = -1;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        TableReservationSummary.this.showProgressDialog();
        return;
      case 1:
        TableReservationSummary.this.hideProgressDialog();
        return;
      case 2:
        TableReservationSummary.this.drawImageMap();
        TableReservationSummary.this.hideProgressDialog();
        return;
      case 3:
        Toast localToast = Toast.makeText(TableReservationSummary.this, R.string.tablereservation_warning_timeout, 1);
        localToast.setGravity(81, 0, 95);
        localToast.show();
        Log.d("", "");
        return;
      case 4:
      }
      TableReservationSummary.this.setMargins();
    }
  };
  private String htmlSource;
  private LinearLayout infoLayout;
  private int infoLayoutH = -1;
  private boolean isPressed = false;
  private TextView kitchen;
  private LinearLayout mailCalendarHolder;
  private RelativeLayout mainLayout;
  private WebView map;
  private LinearLayout mapHolder;
  private LinearLayout okButton;
  private TextView orderDateText;
  private TableReservationInfo orderInfo;
  private String orderUUID;
  private TextView personsText;
  private ProgressDialog progressDialog = null;
  private Resources res;
  private String reservationTime;
  private LinearLayout restNameHolder;
  private TextView restaurantText;
  private String startPoint = null;
  private LinearLayout tableTextLayout;
  private int tableTextLayoutH = -1;
  private User user;

  private Intent chooseEmailClient()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    List localList = getPackageManager().queryIntentActivities(localIntent, 0);
    Object localObject = null;
    Iterator localIterator1 = localList.iterator();
    while (localIterator1.hasNext())
    {
      ResolveInfo localResolveInfo2 = (ResolveInfo)localIterator1.next();
      if ((!localResolveInfo2.activityInfo.packageName.endsWith(".gm")) && (!localResolveInfo2.activityInfo.name.toLowerCase().contains("gmail")))
        continue;
      localObject = localResolveInfo2;
    }
    if (localObject == null)
    {
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
      {
        ResolveInfo localResolveInfo1 = (ResolveInfo)localIterator2.next();
        if (!localResolveInfo1.activityInfo.name.toLowerCase().contains("mail"))
          continue;
        localObject = localResolveInfo1;
      }
    }
    if (localObject != null)
      localIntent.setClassName(localObject.activityInfo.packageName, localObject.activityInfo.name);
    return localIntent;
  }

  private void closeActivity()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("howtoclose", "");
    setResult(-1, localIntent);
    finish();
  }

  private void closeActivityOnCancel()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("howtoclose", "root");
    setResult(-1, localIntent);
    finish();
  }

  private String createPoints(Double paramDouble1, Double paramDouble2, String paramString1, String paramString2)
  {
    return "myMap.points.push({\nlatitude:" + Double.toString(paramDouble1.doubleValue()) + ",\n" + "longitude:" + Double.toString(paramDouble2.doubleValue()) + "\n" + "})\n\n";
  }

  private void drawImageMap()
  {
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
    {
      this.progressDialog.dismiss();
      this.progressDialog = null;
    }
  }

  private void setMargins()
  {
    float f = getResources().getDisplayMetrics().density;
    int i = this.contentLayoutH - this.infoLayoutH - this.footerLayoutH - this.tableTextLayoutH;
    if (i > 20)
      i -= 15;
    int j = i / 2;
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams.setMargins((int)(20.0F * f), j, (int)(20.0F * f), 0);
    this.tableTextLayout.setLayoutParams(localLayoutParams);
    this.footerLayout.setLayoutParams(localLayoutParams);
  }

  private void showProgressDialog()
  {
    if (this.progressDialog == null)
      this.progressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.common_loading_upper), true, true, new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          try
          {
            TableReservationSummary.this.progressDialog.dismiss();
            TableReservationSummary.access$2402(TableReservationSummary.this, null);
            return;
          }
          catch (Exception localException)
          {
            Log.d("", "");
          }
        }
      });
  }

  public void create()
  {
    setContentView(R.layout.sergeyb_tablereservation_summary);
    this.res = getResources();
    setTopBarTitle(getResources().getString(R.string.tablereservation_summary));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (TableReservationSummary.this.startPoint == null)
          TableReservationSummary.this.closeActivity();
        do
          return;
        while (TableReservationSummary.this.startPoint.compareTo("summary") != 0);
        TableReservationSummary.this.closeActivityOnCancel();
      }
    });
    View localView = LayoutInflater.from(this).inflate(R.layout.romanblack_tablereservation_listbutton, null);
    ImageView localImageView = (ImageView)localView.findViewById(R.id.image);
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)localImageView.getDrawable();
    localBitmapDrawable.setColorFilter(this.navBarDesign.itemDesign.textColor, PorterDuff.Mode.MULTIPLY);
    localImageView.setImageDrawable(localBitmapDrawable);
    setTopBarRightVeiw(localView, false, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)TableReservationSummary.this.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        int i = 0;
        if (localNetworkInfo != null)
        {
          boolean bool = localNetworkInfo.isConnectedOrConnecting();
          i = 0;
          if (bool)
            i = 1;
        }
        if (i == 0)
        {
          Toast localToast = Toast.makeText(TableReservationSummary.this, R.string.alert_no_internet, 1);
          localToast.setGravity(81, 0, 95);
          localToast.show();
          return;
        }
        Intent localIntent = new Intent(TableReservationSummary.this, TableReservatioinListOfReservations.class);
        localIntent.putExtra("userinfo", TableReservationSummary.this.user);
        localIntent.putExtra("xml", TableReservationSummary.this.orderInfo);
        localIntent.putExtra("fontColor", TableReservationSummary.this.fontColor);
        localIntent.putExtra("backColor", TableReservationSummary.this.backColor);
        TableReservationSummary.this.startActivity(localIntent);
      }
    });
    Intent localIntent = getIntent();
    this.orderInfo = ((TableReservationInfo)localIntent.getSerializableExtra("xml"));
    this.fontColor = localIntent.getIntExtra("fontColor", -1);
    this.backColor = localIntent.getIntExtra("backColor", Color.parseColor("#37393b"));
    this.user = ((User)localIntent.getSerializableExtra("userinfo"));
    this.orderUUID = localIntent.getStringExtra("orderUUID");
    this.reservationTime = localIntent.getStringExtra("reservationTIme");
    this.startPoint = localIntent.getStringExtra("startPoint");
    this.emailButton = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_mail));
    this.calendarButton = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_calendar));
    this.directioinButton = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_direction));
    this.okButton = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_ok));
    this.mainLayout = ((RelativeLayout)findViewById(R.id.sergeyb_tablereservation_main_layout));
    this.addressText = ((TextView)findViewById(R.id.sergeyb_tablereservation_address));
    this.additionalText = ((TextView)findViewById(R.id.sergeyb_tablereservation_additional));
    this.orderDateText = ((TextView)findViewById(R.id.sergeyb_tablereservation_order_date));
    this.personsText = ((TextView)findViewById(R.id.sergeyb_tablereservation_persons));
    this.restaurantText = ((TextView)findViewById(R.id.sergeyb_tablereservation_restautant_name));
    this.addressPointer = ((TextView)findViewById(R.id.sergeyb_tablereservation_address_pointer));
    this.additionalPointer = ((TextView)findViewById(R.id.sergeyb_tablereservation_additional_pointer));
    this.infoLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_info_layout));
    this.mapHolder = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_map_holder));
    this.restNameHolder = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_restname_holder));
    this.kitchen = ((TextView)findViewById(R.id.sergeyb_tablereservation_restautant_kitchen));
    this.mailCalendarHolder = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_mail_calendar_holder));
    this.tableTextLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_table_text));
    this.footerLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_footer));
    this.contentLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_content_holder));
    this.infoLayout.post(new Runnable()
    {
      public void run()
      {
        TableReservationSummary.access$1102(TableReservationSummary.this, TableReservationSummary.this.infoLayout.getHeight());
      }
    });
    this.contentLayout.post(new Runnable()
    {
      public void run()
      {
        TableReservationSummary.access$1302(TableReservationSummary.this, TableReservationSummary.this.contentLayout.getHeight());
      }
    });
    this.tableTextLayout.post(new Runnable()
    {
      public void run()
      {
        TableReservationSummary.access$1502(TableReservationSummary.this, TableReservationSummary.this.tableTextLayout.getHeight());
      }
    });
    this.footerLayout.post(new Runnable()
    {
      public void run()
      {
        TableReservationSummary.access$1702(TableReservationSummary.this, TableReservationSummary.this.footerLayout.getHeight());
      }
    });
    new Thread(new Runnable()
    {
      public void run()
      {
        while ((TableReservationSummary.this.infoLayoutH == -1) || (TableReservationSummary.this.tableTextLayoutH == -1) || (TableReservationSummary.this.footerLayoutH == -1) || (TableReservationSummary.this.contentLayoutH == -1));
        TableReservationSummary.this.handler.sendEmptyMessage(4);
      }
    }).start();
    if ((this.reservationTime != null) && (this.reservationTime.length() != 0))
    {
      this.mailCalendarHolder.setVisibility(8);
      this.okButton.setVisibility(8);
    }
    this.mainLayout.setBackgroundColor(this.backColor);
    this.restaurantText.setTextColor(this.fontColor);
    this.addressText.setTextColor(this.fontColor);
    this.orderDateText.setTextColor(this.fontColor);
    this.personsText.setTextColor(this.fontColor);
    this.additionalText.setTextColor(this.fontColor);
    this.addressPointer.setTextColor(this.fontColor);
    this.additionalPointer.setTextColor(this.fontColor);
    this.kitchen.setTextColor(this.fontColor);
    this.addressText.setText(this.orderInfo.getRestaurantadress());
    this.additionalText.setText(this.orderInfo.getRestaurantadditional());
    Date localDate = this.orderInfo.getOrderDate();
    if (Locale.getDefault().toString().equals("ru_RU"))
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEEEE HH:mm, d MMMMM yyyy");
      this.orderDateText.setText(localSimpleDateFormat.format(localDate));
      if (!Locale.getDefault().toString().equals("ru_RU"))
        break label1532;
    }
    while (true)
    {
      ByteArrayOutputStream localByteArrayOutputStream;
      try
      {
        PluralResources localPluralResources = new PluralResources(getResources());
        int j = R.plurals.orderTableForPerson;
        int k = this.orderInfo.getPersonsAmount();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(this.orderInfo.getPersonsAmount());
        String str2 = localPluralResources.getQuantityString(j, k, arrayOfObject);
        this.personsText.setText(str2);
        this.restaurantText.setText(this.orderInfo.getRestaurantName());
        this.kitchen.setText(this.orderInfo.getKitchen());
        this.map = ((WebView)findViewById(R.id.sergeyb_tablereservation_map));
        this.map.setBackgroundColor(-16777216);
        this.map.setScrollBarStyle(33554432);
        this.map.getSettings().setJavaScriptEnabled(true);
        this.map.getSettings().setPluginsEnabled(true);
        this.map.getSettings().setGeolocationEnabled(true);
        this.map.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
          {
            if (!TableReservationSummary.this.isPressed)
            {
              Intent localIntent = new Intent(TableReservationSummary.this, TableReservationMap.class);
              localIntent.putExtra("xml", TableReservationSummary.this.orderInfo);
              TableReservationSummary.this.startActivityForResult(localIntent, 10001);
              TableReservationSummary.access$2002(TableReservationSummary.this, true);
            }
            return true;
          }
        });
        this.map.setWebViewClient(new WebViewClient()
        {
          public void onPageFinished(WebView paramWebView, String paramString)
          {
            super.onPageFinished(paramWebView, paramString);
            TableReservationSummary.this.handler.sendEmptyMessage(1);
          }

          public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
          {
            super.onPageStarted(paramWebView, paramString, paramBitmap);
            TableReservationSummary.this.handler.sendEmptyMessage(0);
          }
        });
        this.htmlSource = "";
        try
        {
          InputStream localInputStream = getResources().openRawResource(R.raw.sergeyb_tablereservation_page_mini);
          localByteArrayOutputStream = new ByteArrayOutputStream();
          byte[] arrayOfByte = new byte[512];
          int i = localInputStream.read(arrayOfByte, 0, 512);
          if (i == -1)
            break label1633;
          localByteArrayOutputStream.write(arrayOfByte, 0, i);
          Arrays.fill(arrayOfByte, 0);
          continue;
        }
        catch (IOException localIOException)
        {
          Log.e("", "");
        }
        String str1 = createPoints(this.orderInfo.getLatitude(), this.orderInfo.getLongitude(), this.orderInfo.getRestaurantName(), this.orderInfo.getRestaurantadditional());
        this.htmlSource = this.htmlSource.replace("__RePlAcE-Points__", str1);
        this.htmlSource = this.htmlSource.replace("__RePlAcE-Lat__", Double.toString(this.orderInfo.getLatitude().doubleValue()));
        this.htmlSource = this.htmlSource.replace("__RePlAcE-Lng__", Double.toString(this.orderInfo.getLongitude().doubleValue()));
        this.htmlSource = this.htmlSource.replace("__RePlAcE-Zoom__", "15");
        NetworkInfo localNetworkInfo = ((ConnectivityManager)getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
          continue;
        this.map.loadDataWithBaseURL("", this.htmlSource, "text/html", "utf-8", "");
        this.emailButton.setOnClickListener(new View.OnClickListener()
        {
          // ERROR //
          public void onClick(View paramView)
          {
            // Byte code:
            //   0: aload_0
            //   1: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   4: ldc 27
            //   6: invokevirtual 31	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
            //   9: checkcast 33	android/net/ConnectivityManager
            //   12: invokevirtual 37	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
            //   15: astore_2
            //   16: aload_2
            //   17: ifnull +10 -> 27
            //   20: aload_2
            //   21: invokevirtual 43	android/net/NetworkInfo:isConnectedOrConnecting	()Z
            //   24: ifne +29 -> 53
            //   27: aload_0
            //   28: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   31: getstatic 49	com/ibuildapp/romanblack/TableReservationPlugin/R$string:alert_no_internet	I
            //   34: iconst_1
            //   35: invokestatic 55	android/widget/Toast:makeText	(Landroid/content/Context;II)Landroid/widget/Toast;
            //   38: astore_3
            //   39: aload_3
            //   40: bipush 81
            //   42: iconst_0
            //   43: bipush 95
            //   45: invokevirtual 59	android/widget/Toast:setGravity	(III)V
            //   48: aload_3
            //   49: invokevirtual 62	android/widget/Toast:show	()V
            //   52: return
            //   53: invokestatic 68	java/util/Locale:getDefault	()Ljava/util/Locale;
            //   56: invokevirtual 72	java/util/Locale:toString	()Ljava/lang/String;
            //   59: ldc 74
            //   61: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
            //   64: ifeq +500 -> 564
            //   67: new 82	java/text/SimpleDateFormat
            //   70: dup
            //   71: ldc 84
            //   73: invokespecial 87	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
            //   76: aload_0
            //   77: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   80: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   83: invokevirtual 97	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderDate	()Ljava/util/Date;
            //   86: invokevirtual 101	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
            //   89: astore 7
            //   91: invokestatic 68	java/util/Locale:getDefault	()Ljava/util/Locale;
            //   94: invokevirtual 72	java/util/Locale:toString	()Ljava/lang/String;
            //   97: ldc 103
            //   99: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
            //   102: ifeq +510 -> 612
            //   105: aload_0
            //   106: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   109: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   112: invokevirtual 107	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderTime	()Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
            //   115: getfield 112	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:houres	I
            //   118: aload_0
            //   119: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   122: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   125: invokevirtual 107	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderTime	()Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
            //   128: getfield 115	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:minutes	I
            //   131: iconst_1
            //   132: invokestatic 121	com/ibuildapp/romanblack/TableReservationPlugin/utils/Utils:convertTimeToFormat	(IIZ)Ljava/lang/String;
            //   135: astore 8
            //   137: invokestatic 68	java/util/Locale:getDefault	()Ljava/util/Locale;
            //   140: invokevirtual 72	java/util/Locale:toString	()Ljava/lang/String;
            //   143: ldc 103
            //   145: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
            //   148: istore 9
            //   150: iload 9
            //   152: ifeq +508 -> 660
            //   155: new 123	com/seppius/i18n/plurals/PluralResources
            //   158: dup
            //   159: aload_0
            //   160: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   163: invokestatic 127	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$2100	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Landroid/content/res/Resources;
            //   166: invokespecial 130	com/seppius/i18n/plurals/PluralResources:<init>	(Landroid/content/res/Resources;)V
            //   169: astore 20
            //   171: getstatic 135	com/ibuildapp/romanblack/TableReservationPlugin/R$plurals:orderTableForPerson	I
            //   174: istore 22
            //   176: aload_0
            //   177: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   180: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   183: invokevirtual 139	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getPersonsAmount	()I
            //   186: istore 23
            //   188: iconst_1
            //   189: anewarray 4	java/lang/Object
            //   192: astore 24
            //   194: aload 24
            //   196: iconst_0
            //   197: aload_0
            //   198: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   201: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   204: invokevirtual 139	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getPersonsAmount	()I
            //   207: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
            //   210: aastore
            //   211: aload 20
            //   213: iload 22
            //   215: iload 23
            //   217: aload 24
            //   219: invokevirtual 149	com/seppius/i18n/plurals/PluralResources:getQuantityString	(II[Ljava/lang/Object;)Ljava/lang/String;
            //   222: astore 25
            //   224: aload 25
            //   226: astore 11
            //   228: new 151	java/lang/StringBuilder
            //   231: dup
            //   232: invokespecial 152	java/lang/StringBuilder:<init>	()V
            //   235: ldc 154
            //   237: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   240: aload_0
            //   241: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   244: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   247: invokevirtual 162	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getLatitude	()Ljava/lang/Double;
            //   250: invokevirtual 168	java/lang/Double:doubleValue	()D
            //   253: invokestatic 171	java/lang/Double:toString	(D)Ljava/lang/String;
            //   256: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   259: ldc 173
            //   261: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   264: aload_0
            //   265: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   268: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   271: invokevirtual 176	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getLongitude	()Ljava/lang/Double;
            //   274: invokevirtual 168	java/lang/Double:doubleValue	()D
            //   277: invokestatic 171	java/lang/Double:toString	(D)Ljava/lang/String;
            //   280: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   283: ldc 178
            //   285: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   288: aload_0
            //   289: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   292: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   295: invokevirtual 162	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getLatitude	()Ljava/lang/Double;
            //   298: invokevirtual 168	java/lang/Double:doubleValue	()D
            //   301: invokestatic 171	java/lang/Double:toString	(D)Ljava/lang/String;
            //   304: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   307: ldc 173
            //   309: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   312: aload_0
            //   313: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   316: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   319: invokevirtual 176	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getLongitude	()Ljava/lang/Double;
            //   322: invokevirtual 168	java/lang/Double:doubleValue	()D
            //   325: invokestatic 171	java/lang/Double:toString	(D)Ljava/lang/String;
            //   328: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   331: ldc 180
            //   333: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   336: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   339: astore 12
            //   341: new 151	java/lang/StringBuilder
            //   344: dup
            //   345: invokespecial 152	java/lang/StringBuilder:<init>	()V
            //   348: ldc 183
            //   350: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   353: aload 12
            //   355: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   358: ldc 185
            //   360: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   363: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   366: astore 13
            //   368: new 151	java/lang/StringBuilder
            //   371: dup
            //   372: invokespecial 152	java/lang/StringBuilder:<init>	()V
            //   375: aload_0
            //   376: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   379: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   382: invokevirtual 188	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getRestaurantName	()Ljava/lang/String;
            //   385: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   388: ldc 190
            //   390: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   393: aload_0
            //   394: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   397: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   400: invokevirtual 193	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getRestaurantadress	()Ljava/lang/String;
            //   403: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   406: ldc 190
            //   408: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   411: aload_0
            //   412: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   415: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   418: invokevirtual 188	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getRestaurantName	()Ljava/lang/String;
            //   421: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   424: ldc 195
            //   426: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   429: aload 7
            //   431: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   434: ldc 190
            //   436: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   439: aload 8
            //   441: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   444: ldc 190
            //   446: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   449: aload 11
            //   451: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   454: ldc 195
            //   456: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   459: aload 13
            //   461: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   464: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   467: astore 14
            //   469: aload_0
            //   470: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   473: invokestatic 199	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$2200	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Landroid/content/Intent;
            //   476: astore 15
            //   478: aload 15
            //   480: ldc 200
            //   482: invokevirtual 206	android/content/Intent:addFlags	(I)Landroid/content/Intent;
            //   485: pop
            //   486: aload 15
            //   488: ldc 208
            //   490: invokevirtual 212	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
            //   493: pop
            //   494: aload 15
            //   496: ldc 214
            //   498: new 151	java/lang/StringBuilder
            //   501: dup
            //   502: invokespecial 152	java/lang/StringBuilder:<init>	()V
            //   505: ldc 216
            //   507: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   510: aload_0
            //   511: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   514: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   517: invokevirtual 188	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getRestaurantName	()Ljava/lang/String;
            //   520: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   523: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   526: invokevirtual 220	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
            //   529: pop
            //   530: aload 15
            //   532: ldc 222
            //   534: aload 14
            //   536: invokestatic 228	android/text/Html:fromHtml	(Ljava/lang/String;)Landroid/text/Spanned;
            //   539: invokevirtual 231	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/CharSequence;)Landroid/content/Intent;
            //   542: pop
            //   543: aload_0
            //   544: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   547: aload 15
            //   549: invokevirtual 235	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:startActivity	(Landroid/content/Intent;)V
            //   552: return
            //   553: astore 4
            //   555: ldc 237
            //   557: ldc 237
            //   559: invokestatic 243	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
            //   562: pop
            //   563: return
            //   564: invokestatic 68	java/util/Locale:getDefault	()Ljava/util/Locale;
            //   567: invokevirtual 72	java/util/Locale:toString	()Ljava/lang/String;
            //   570: ldc 103
            //   572: invokevirtual 80	java/lang/String:equals	(Ljava/lang/Object;)Z
            //   575: istore 6
            //   577: aconst_null
            //   578: astore 7
            //   580: iload 6
            //   582: ifeq -491 -> 91
            //   585: new 82	java/text/SimpleDateFormat
            //   588: dup
            //   589: ldc 245
            //   591: invokespecial 87	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
            //   594: aload_0
            //   595: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   598: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   601: invokevirtual 97	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderDate	()Ljava/util/Date;
            //   604: invokevirtual 101	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
            //   607: astore 7
            //   609: goto -518 -> 91
            //   612: aload_0
            //   613: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   616: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   619: invokevirtual 107	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderTime	()Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
            //   622: getfield 112	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:houres	I
            //   625: aload_0
            //   626: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   629: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   632: invokevirtual 107	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderTime	()Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
            //   635: getfield 115	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:minutes	I
            //   638: iconst_0
            //   639: invokestatic 121	com/ibuildapp/romanblack/TableReservationPlugin/utils/Utils:convertTimeToFormat	(IIZ)Ljava/lang/String;
            //   642: astore 8
            //   644: goto -507 -> 137
            //   647: astore 21
            //   649: aload 21
            //   651: invokevirtual 248	java/lang/NoSuchMethodException:printStackTrace	()V
            //   654: aconst_null
            //   655: astore 11
            //   657: goto -429 -> 228
            //   660: new 151	java/lang/StringBuilder
            //   663: dup
            //   664: invokespecial 152	java/lang/StringBuilder:<init>	()V
            //   667: ldc 250
            //   669: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   672: aload_0
            //   673: getfield 17	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary$11:this$0	Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;
            //   676: invokestatic 91	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary:access$800	(Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationSummary;)Lcom/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo;
            //   679: invokevirtual 139	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getPersonsAmount	()I
            //   682: invokestatic 253	java/lang/Integer:toString	(I)Ljava/lang/String;
            //   685: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   688: ldc 255
            //   690: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   693: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   696: astore 10
            //   698: aload 10
            //   700: astore 11
            //   702: goto -474 -> 228
            //
            // Exception table:
            //   from	to	target	type
            //   53	91	553	java/lang/Exception
            //   91	137	553	java/lang/Exception
            //   137	150	553	java/lang/Exception
            //   155	224	553	java/lang/Exception
            //   228	552	553	java/lang/Exception
            //   564	577	553	java/lang/Exception
            //   585	609	553	java/lang/Exception
            //   612	644	553	java/lang/Exception
            //   649	654	553	java/lang/Exception
            //   660	698	553	java/lang/Exception
            //   155	224	647	java/lang/NoSuchMethodException
          }
        });
        this.calendarButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            Calendar localCalendar1 = Calendar.getInstance();
            int i;
            if (-1 + TableReservationSummary.this.orderInfo.getOrderTime().houres < 0)
              i = 23;
            for (int j = -1 + TableReservationSummary.this.orderInfo.getOrderDate().getDate(); ; j = TableReservationSummary.this.orderInfo.getOrderDate().getDate())
            {
              localCalendar1.set(1900 + TableReservationSummary.this.orderInfo.getOrderDate().getYear(), TableReservationSummary.this.orderInfo.getOrderDate().getMonth(), j, i, TableReservationSummary.this.orderInfo.getOrderTime().minutes);
              long l1 = localCalendar1.getTimeInMillis();
              Calendar localCalendar2 = Calendar.getInstance();
              localCalendar2.set(1900 + TableReservationSummary.this.orderInfo.getOrderDate().getYear(), TableReservationSummary.this.orderInfo.getOrderDate().getMonth(), TableReservationSummary.this.orderInfo.getOrderDate().getDate(), TableReservationSummary.this.orderInfo.getOrderTime().houres, TableReservationSummary.this.orderInfo.getOrderTime().minutes);
              long l2 = localCalendar2.getTimeInMillis();
              Intent localIntent = new Intent("android.intent.action.EDIT");
              localIntent.setType("vnd.android.cursor.item/event");
              localIntent.putExtra("beginTime", l1);
              localIntent.putExtra("allDay", false);
              localIntent.putExtra("rrule", "FREQ=YEARLY");
              localIntent.putExtra("endTime", l2);
              localIntent.putExtra("title", "Reservation for " + TableReservationSummary.this.orderInfo.getRestaurantName());
              TableReservationSummary.this.startActivity(localIntent);
              return;
              i = -1 + TableReservationSummary.this.orderInfo.getOrderTime().houres;
            }
          }
        });
        this.directioinButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            NetworkInfo localNetworkInfo = ((ConnectivityManager)TableReservationSummary.this.getSystemService("connectivity")).getActiveNetworkInfo();
            if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
            {
              Toast localToast = Toast.makeText(TableReservationSummary.this, R.string.alert_no_internet, 1);
              localToast.setGravity(81, 0, 95);
              localToast.show();
              return;
            }
            Intent localIntent = new Intent(TableReservationSummary.this, TableReservationMap.class);
            localIntent.putExtra("xml", TableReservationSummary.this.orderInfo);
            TableReservationSummary.this.startActivity(localIntent);
          }
        });
        this.okButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(TableReservationSummary.this);
            localBuilder.setMessage(TableReservationSummary.this.getResources().getString(R.string.tablereservation_warning_cancel_reservation));
            localBuilder.setPositiveButton(TableReservationSummary.this.getResources().getString(R.string.common_yes_upper), new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramDialogInterface, int paramInt)
              {
                NetworkInfo localNetworkInfo = ((ConnectivityManager)TableReservationSummary.this.getSystemService("connectivity")).getActiveNetworkInfo();
                if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
                {
                  Toast localToast = Toast.makeText(TableReservationSummary.this, R.string.alert_no_internet, 1);
                  localToast.setGravity(81, 0, 95);
                  localToast.show();
                  return;
                }
                if ((TableReservationSummary.this.user.getUserEmail() != null) && (TableReservationSummary.this.user.getUserEmail().length() != 0))
                  TableReservationHTTP.sendMail(4, TableReservationSummary.this.user, TableReservationSummary.this.orderInfo, TableReservationSummary.this.getResources().getString(R.string.app_name), TableReservationSummary.this.handler);
                TableReservationHTTP.sendCancelOrderRequest(TableReservationSummary.this.orderUUID, TableReservationSummary.this.user, TableReservationSummary.this.handler, TableReservationSummary.this.orderInfo);
                Toast.makeText(TableReservationSummary.this, TableReservationSummary.this.getResources().getString(R.string.tablereservation_warning_reservation_canceled), 0).show();
                TableReservationSummary.this.closeActivityOnCancel();
              }
            });
            localBuilder.setNegativeButton(TableReservationSummary.this.getResources().getString(R.string.common_no_upper), new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramDialogInterface, int paramInt)
              {
              }
            });
            localBuilder.create().show();
          }
        });
        return;
        String str3 = new SimpleDateFormat("E").format(this.orderInfo.getOrderDate());
        String str4 = new SimpleDateFormat("MMMMMM").format(this.orderInfo.getOrderDate());
        String str5 = new SimpleDateFormat("d").format(this.orderInfo.getOrderDate());
        String str6 = new SimpleDateFormat("y").format(this.orderInfo.getOrderDate());
        String str7 = Utils.convertTimeToFormat(this.orderInfo.getOrderTime().houres, this.orderInfo.getOrderTime().minutes, false);
        this.orderDateText.setTypeface(null, 1);
        this.orderDateText.setText(str3 + ", " + str4 + " " + str5 + ", " + str6 + ", " + str7);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        localNoSuchMethodException.printStackTrace();
        continue;
      }
      label1532: if (this.orderInfo.getPersonsAmount() == 1)
      {
        this.personsText.setText("Table for " + Integer.toString(this.orderInfo.getPersonsAmount()) + " person");
        continue;
      }
      this.personsText.setText("Table for " + Integer.toString(this.orderInfo.getPersonsAmount()) + " persons");
      continue;
      label1633: this.htmlSource = localByteArrayOutputStream.toString();
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
      return;
    case 10001:
    }
    this.isPressed = false;
  }

  public void onBackPressed()
  {
    if (this.startPoint == null)
      closeActivity();
    do
      return;
    while (this.startPoint.compareTo("summary") != 0);
    closeActivityOnCancel();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationSummary
 * JD-Core Version:    0.6.0
 */