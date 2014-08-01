package com.ibuildapp.romanblack.TableReservationPlugin;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.BarDesigner.TitleDesign;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.HouresMinutes;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.EntityParser;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationHTTP;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.Utils;
import com.seppius.i18n.plurals.PluralResources;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class TableReservationPlugin extends AppBuilderModuleMain
{
  private final int DRAW_NOTIFICATION_VIEW = 6;
  private final int HIDE_PROGRESS_DIALOG = 5;
  private final int LOGIN_ACTIVITY = 10005;
  private final int NEED_INTERNET_CONNECTION = 8;
  private final int PERSON_PICKER_ACTIVITY = 10006;
  private final int RESERVATION_ACTIVITY = 7;
  private final int SHOW_PROGRESS_DIALOG = 4;
  private int backColor;
  private AlertDialog.Builder builder;
  private String cachePath;
  private boolean colorSchemeDark = false;
  private LinearLayout datePicker;
  private ImageView datePickerImg;
  private View datePickerView;
  private TextView dateTextView;
  private int fontColor;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      case 7:
      default:
        return;
      case 4:
        TableReservationPlugin.this.showProgressDialog();
        return;
      case 5:
        TableReservationPlugin.this.hideProgressDialog();
        return;
      case 6:
        TableReservationPlugin.this.drawNotificationView();
        return;
      case 8:
      }
      Toast localToast = Toast.makeText(TableReservationPlugin.this, R.string.alert_no_internet, 1);
      localToast.setGravity(81, 0, 95);
      localToast.show();
    }
  };
  private Thread imgDownloader;
  private boolean isLoggedIn = false;
  private LayoutInflater layoutInflater;
  private Dialog mAlertDialog;
  private LinearLayout mainLayout;
  private LinearLayout notificationLayout;
  private orderParsedInfo orderList;
  private TableReservationInfo parsedXML;
  private LinearLayout personPicker;
  private ImageView personPickerImg;
  private TextView personTextView;
  private ProgressDialog progressDialog = null;
  private LinearLayout submit;
  private TextView submitTextView;
  private LinearLayout timePicker;
  private Integer timePickerHour;
  private ImageView timePickerImg;
  private Integer timePickerMinute;
  private View timePickerView;
  private TextView timeTextView;
  private TextView titleTextView;
  private TableReservationOrderInfo upcoming;
  private User user = new User();
  private Widget widget;

  private int BackColorToFontColor(int paramInt)
  {
    int i = 0xFF & paramInt >> 16;
    int j = 0xFF & paramInt >> 8;
    int k = 0xFF & paramInt >> 0;
    if (0.299D * i + 0.587D * j + 0.114D * k > 127.0D)
      return -16777216;
    return -1;
  }

  private TableReservationOrderInfo checkForUpcoming(orderParsedInfo paramorderParsedInfo)
  {
    long l1 = 1800000L + new Date().getTime();
    Date localDate1 = new Date();
    localDate1.setTime(10000L);
    int i = -1;
    new Date().setTime(l1);
    for (int j = 0; j < paramorderParsedInfo.itemsArray.size(); j++)
    {
      TableReservationOrderInfo localTableReservationOrderInfo = (TableReservationOrderInfo)paramorderParsedInfo.itemsArray.get(j);
      Date localDate2 = localTableReservationOrderInfo.orderDate;
      localDate2.setHours(localTableReservationOrderInfo.orderTime.houres);
      localDate2.setMinutes(localTableReservationOrderInfo.orderTime.minutes);
      long l2 = localDate2.getTime();
      if ((new Date().getTime() >= l2) || (l2 >= l1) || (l2 <= localDate1.getTime()))
        continue;
      localDate1.setTime(l2);
      i = j;
    }
    if (i != -1)
      return (TableReservationOrderInfo)paramorderParsedInfo.itemsArray.get(i);
    return null;
  }

  private View datePickerViewinflate()
  {
    if (this.layoutInflater == null)
      this.layoutInflater = LayoutInflater.from(this);
    View localView = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_date_picker, null);
    Button localButton1 = (Button)localView.findViewById(R.id.sergeyb_tablereservation_date_layout_buttonSave);
    Button localButton2 = (Button)localView.findViewById(R.id.sergeyb_tablereservation_date_layout_buttonCancel);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        DatePicker localDatePicker = (DatePicker)((View)paramView.getParent().getParent()).findViewById(R.id.sergeyb_tablereservation_date_layout_datePicker);
        localDatePicker.clearChildFocus(TableReservationPlugin.this.getCurrentFocus());
        Date localDate = new Date(-1900 + localDatePicker.getYear(), localDatePicker.getMonth(), localDatePicker.getDayOfMonth());
        TableReservationPlugin.this.parsedXML.setOrderDate(localDate);
        if (Locale.getDefault().toString().equals("ru_RU"));
        for (String str = new SimpleDateFormat("dd/MM/yyyy").format(localDate); ; str = new SimpleDateFormat("MM/dd/yyyy").format(localDate))
        {
          TableReservationPlugin.this.dateTextView.setText(str);
          TableReservationPlugin.this.dateTextView.setTextColor(TableReservationPlugin.this.fontColor);
          if (TableReservationPlugin.this.mAlertDialog != null)
            TableReservationPlugin.this.mAlertDialog.dismiss();
          return;
        }
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (TableReservationPlugin.this.mAlertDialog != null)
          TableReservationPlugin.this.mAlertDialog.dismiss();
      }
    });
    return localView;
  }

  private void drawNotificationView()
  {
    this.notificationLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_notification));
    this.notificationLayout.setVisibility(0);
    ((LinearLayout)this.notificationLayout.findViewById(R.id.sergeyb_tablereservation_notification_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(TableReservationPlugin.this, TableReservationSummary.class);
        localIntent.putExtra("fontColor", TableReservationPlugin.this.fontColor);
        localIntent.putExtra("backColor", TableReservationPlugin.this.backColor);
        TableReservationPlugin.this.parsedXML.setOrderDate(TableReservationPlugin.this.upcoming.orderDate);
        TableReservationPlugin.this.parsedXML.setOrderTime(TableReservationPlugin.this.upcoming.orderTime.houres, TableReservationPlugin.this.upcoming.orderTime.minutes, TableReservationPlugin.this.upcoming.orderTime.am_pm);
        TableReservationPlugin.this.parsedXML.setPersonsAmount(TableReservationPlugin.this.upcoming.personsAmount);
        TableReservationPlugin.this.parsedXML.setSpecialRequest(TableReservationPlugin.this.upcoming.specRequest);
        localIntent.putExtra("xml", TableReservationPlugin.this.parsedXML);
        localIntent.putExtra("userinfo", TableReservationPlugin.this.user);
        localIntent.putExtra("orderUUID", TableReservationPlugin.this.upcoming.uuid);
        TableReservationPlugin.this.startActivity(localIntent);
        TableReservationPlugin.this.notificationLayout.setVisibility(8);
      }
    });
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
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
            if ((TableReservationPlugin.this.imgDownloader != null) && (TableReservationPlugin.this.imgDownloader.isAlive()))
              TableReservationPlugin.this.imgDownloader.interrupt();
            TableReservationPlugin.this.progressDialog.dismiss();
            return;
          }
          catch (Exception localException)
          {
            Log.d("", "");
          }
        }
      });
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
        File localFile = new File(paramString2 + "/restaurantbanner.jpg");
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

  private View timePickerViewinflate()
  {
    if (this.layoutInflater == null)
      this.layoutInflater = LayoutInflater.from(this);
    View localView = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_time_picker, null);
    Button localButton1 = (Button)localView.findViewById(R.id.sergeyb_tablereservation_time_layout_buttonSave);
    Button localButton2 = (Button)localView.findViewById(R.id.sergeyb_tablereservation_time_layout_buttonCancel);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TimePicker localTimePicker = (TimePicker)((View)paramView.getParent().getParent()).findViewById(R.id.sergeyb_tablereservation_time_layout_timePicker);
        localTimePicker.clearChildFocus(TableReservationPlugin.this.getCurrentFocus());
        String str = Utils.convertTimeToFormat(localTimePicker.getCurrentHour().intValue(), localTimePicker.getCurrentMinute().intValue(), localTimePicker.is24HourView());
        TableReservationPlugin.this.timeTextView.setText(str);
        TableReservationPlugin.this.timeTextView.setTextColor(TableReservationPlugin.this.fontColor);
        TableReservationPlugin.access$2302(TableReservationPlugin.this, localTimePicker.getCurrentHour());
        TableReservationPlugin.access$2402(TableReservationPlugin.this, localTimePicker.getCurrentMinute());
        TableReservationPlugin.this.parsedXML.setOrderTime(TableReservationPlugin.this.timePickerHour.intValue(), TableReservationPlugin.this.timePickerMinute.intValue(), "AM");
        if (TableReservationPlugin.this.mAlertDialog != null)
          TableReservationPlugin.this.mAlertDialog.dismiss();
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (TableReservationPlugin.this.mAlertDialog != null)
          TableReservationPlugin.this.mAlertDialog.dismiss();
      }
    });
    return localView;
  }

  public void create()
  {
    this.upcoming = null;
    setContentView(R.layout.sergeyb_tablereservation_main);
    if (!((Boolean)getIntent().getExtras().getSerializable("showSideBar")).booleanValue())
    {
      String str4 = getResources().getString(R.string.common_home_upper);
      2 local2 = new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          TableReservationPlugin.this.finish();
        }
      };
      setTopBarLeftButtonText(str4, true, local2);
    }
    View localView = LayoutInflater.from(this).inflate(R.layout.romanblack_tablereservation_listbutton, null);
    ImageView localImageView = (ImageView)localView.findViewById(R.id.image);
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)localImageView.getDrawable();
    localBitmapDrawable.setColorFilter(this.navBarDesign.itemDesign.textColor, PorterDuff.Mode.MULTIPLY);
    localImageView.setImageDrawable(localBitmapDrawable);
    3 local3 = new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)TableReservationPlugin.this.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
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
          TableReservationPlugin.this.handler.sendEmptyMessage(8);
          return;
        }
        if (!TableReservationPlugin.this.isLoggedIn)
        {
          Intent localIntent1 = new Intent(TableReservationPlugin.this, TableReservationLogin.class);
          localIntent1.putExtra("redirectTo", "redirectToOrderList");
          localIntent1.putExtra("orderInfo", TableReservationPlugin.this.parsedXML);
          localIntent1.putExtra("fontColor", TableReservationPlugin.this.fontColor);
          localIntent1.putExtra("backColor", TableReservationPlugin.this.backColor);
          localIntent1.putExtra("colorSchema", TableReservationPlugin.this.colorSchemeDark);
          localIntent1.putExtra("appName", TableReservationPlugin.this.widget.getAppName());
          TableReservationPlugin.this.startActivityForResult(localIntent1, 10005);
          return;
        }
        Intent localIntent2 = new Intent(TableReservationPlugin.this, TableReservatioinListOfReservations.class);
        localIntent2.putExtra("userinfo", TableReservationPlugin.this.user);
        localIntent2.putExtra("xml", TableReservationPlugin.this.parsedXML);
        localIntent2.putExtra("fontColor", TableReservationPlugin.this.fontColor);
        localIntent2.putExtra("backColor", TableReservationPlugin.this.backColor);
        TableReservationPlugin.this.startActivity(localIntent2);
      }
    };
    setTopBarRightVeiw(localView, false, local3);
    this.widget = ((Widget)getIntent().getExtras().getSerializable("Widget"));
    if (this.widget == null)
    {
      Toast.makeText(this, R.string.alert_cannot_init, 1).show();
      Handler localHandler2 = new Handler();
      4 local4 = new Runnable()
      {
        public void run()
        {
          TableReservationPlugin.this.finish();
        }
      };
      localHandler2.postDelayed(local4, 5000L);
      return;
    }
    if (this.widget.getPluginXmlData().length() == 0)
    {
      Toast.makeText(this, R.string.alert_cannot_init, 1).show();
      Handler localHandler1 = new Handler();
      5 local5 = new Runnable()
      {
        public void run()
        {
          TableReservationPlugin.this.finish();
        }
      };
      localHandler1.postDelayed(local5, 5000L);
      return;
    }
    if ((this.widget.getTitle() != null) && (this.widget.getTitle().length() != 0))
      setTopBarTitle(this.widget.getTitle());
    while (true)
    {
      this.mainLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_main_layout));
      this.titleTextView = ((TextView)findViewById(R.id.sergeyb_tablereservation_title_text));
      this.dateTextView = ((TextView)findViewById(R.id.sergeyb_tablereservation_date_textview));
      this.timeTextView = ((TextView)findViewById(R.id.sergeyb_tablereservation_time_textview));
      this.personTextView = ((TextView)findViewById(R.id.sergeyb_tablereservation_person_textview));
      this.submitTextView = ((TextView)findViewById(R.id.sergeyb_tablereservation_submit_text));
      this.datePicker = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_layoutDatePicker));
      this.timePicker = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_layoutTimePicker));
      this.personPicker = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_layoutPersonPicker));
      this.datePickerImg = ((ImageView)findViewById(R.id.sergeyb_tablereservation_imageViewDate));
      this.timePickerImg = ((ImageView)findViewById(R.id.sergeyb_tablereservation_imageViewTime));
      this.personPickerImg = ((ImageView)findViewById(R.id.sergeyb_tablereservation_imageViewPerson));
      EntityParser localEntityParser = new EntityParser(this.widget.getPluginXmlData());
      localEntityParser.parse();
      this.parsedXML = localEntityParser.getTableReservationInfo();
      this.backColor = this.parsedXML.colorskin.color1;
      this.fontColor = this.parsedXML.colorskin.color2;
      label598: File localFile2;
      if (BackColorToFontColor(this.backColor) == -16777216)
      {
        this.colorSchemeDark = false;
        this.datePicker.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_first);
        this.timePicker.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_middle);
        this.personPicker.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_last);
        this.datePickerImg.setImageResource(R.drawable.sergeyb_tablereservation_calendar_img_dark);
        this.timePickerImg.setImageResource(R.drawable.sergeyb_tablereservation_time_img_dark);
        this.personPickerImg.setImageResource(R.drawable.sergeyb_tablereservation_persons_img_dark);
        this.mainLayout.setBackgroundColor(this.backColor);
        this.titleTextView.setTextColor(this.fontColor);
        this.dateTextView.setTextColor(this.fontColor);
        this.timeTextView.setTextColor(this.fontColor);
        this.personTextView.setTextColor(this.fontColor);
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        this.builder = localBuilder;
        this.timePickerView = timePickerViewinflate();
        this.datePickerView = datePickerViewinflate();
        this.cachePath = (getExternalCacheDir().getAbsolutePath() + File.separator + "tablereservation-" + this.parsedXML.getModuleid());
        File localFile1 = new File(this.cachePath);
        if (!localFile1.exists())
          localFile1.mkdirs();
        localFile2 = new File(this.cachePath + "/usercache.data");
        if (!localFile2.exists());
      }
      try
      {
        FileInputStream localFileInputStream2 = new FileInputStream(localFile2);
        ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream2);
        this.user = ((User)localObjectInputStream.readObject());
        localObjectInputStream.close();
        this.isLoggedIn = true;
        this.parsedXML.setCustomerEmail(this.user.getUserEmail());
        localFile3 = new File(this.cachePath + "/userphone.data");
        if (!localFile3.exists());
      }
      catch (Exception localException)
      {
        try
        {
          File localFile3;
          FileInputStream localFileInputStream1 = new FileInputStream(localFile3);
          byte[] arrayOfByte = new byte[512];
          Arrays.fill(arrayOfByte, 0);
          int i = localFileInputStream1.read(arrayOfByte, 0, 512);
          if (i != -1)
          {
            String str3 = new String(arrayOfByte, 0, i, "UTF8");
            this.parsedXML.setPhoneNumber(str3);
          }
          this.parsedXML.setOrderDate(new Date());
          this.parsedXML.setOrderTime(this.parsedXML.getStartTime().houres, this.parsedXML.getStartTime().minutes, "");
          this.parsedXML.setPersonsAmount(2);
          this.dateTextView.setText(getResources().getString(R.string.common_date_upper));
          this.dateTextView.setTextColor(0x3CFFFFFF & this.fontColor);
          this.timeTextView.setTextColor(0x3CFFFFFF & this.fontColor);
          if ((this.widget.getDateFormat() == 1) || (Locale.getDefault().toString().equals("ru_RU")))
          {
            str1 = Utils.convertTimeToFormat(this.parsedXML.getStartTime().houres, this.parsedXML.getStartTime().minutes, true);
            str2 = Utils.convertTimeToFormat(this.parsedXML.getEndTime().houres, this.parsedXML.getEndTime().minutes, true);
            this.timeTextView.setText(getResources().getString(R.string.common_time_upper) + " (" + str1 + " - " + str2 + ")");
            this.personTextView.setText(getResources().getString(R.string.tablereservation_party_size));
            this.personTextView.setTextColor(0x3CFFFFFF & this.fontColor);
            TimePicker localTimePicker = (TimePicker)this.timePickerView.findViewById(R.id.sergeyb_tablereservation_time_layout_timePicker);
            localTimePicker.setCurrentHour(Integer.valueOf(this.parsedXML.getStartTime().houres));
            localTimePicker.setCurrentMinute(Integer.valueOf(this.parsedXML.getStartTime().minutes));
            if (Locale.getDefault().toString().equals("ru_RU"))
              localTimePicker.setIs24HourView(Boolean.valueOf(true));
            ((DatePicker)this.datePickerView.findViewById(R.id.sergeyb_tablereservation_date_layout_datePicker)).init(1900 + this.parsedXML.getOrderDate().getYear(), this.parsedXML.getOrderDate().getMonth(), this.parsedXML.getOrderDate().getDate(), null);
            this.submit = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_submit_button));
            LinearLayout localLinearLayout1 = this.submit;
            6 local6 = new View.OnClickListener()
            {
              public void onClick(View paramView)
              {
                if ((TableReservationPlugin.this.dateTextView.getText().toString().contains(TableReservationPlugin.this.getResources().getString(R.string.common_date_upper))) || (TableReservationPlugin.this.timeTextView.getText().toString().contains(TableReservationPlugin.this.getResources().getString(R.string.common_time_upper))) || (TableReservationPlugin.this.personTextView.getText().toString().contains(TableReservationPlugin.this.getResources().getString(R.string.tablereservation_party_size))))
                {
                  String str1 = "";
                  if (TableReservationPlugin.this.dateTextView.getText().toString().equals(TableReservationPlugin.this.getResources().getString(R.string.common_date_upper)))
                  {
                    if (str1.length() > 0)
                      str1 = str1 + TableReservationPlugin.this.getString(R.string.tablereservation_ampersand);
                    str1 = str1 + TableReservationPlugin.this.getResources().getString(R.string.common_date_upper);
                  }
                  if (TableReservationPlugin.this.timeTextView.getText().toString().contains(TableReservationPlugin.this.getResources().getString(R.string.common_time_upper)))
                  {
                    if (str1.length() > 0)
                      str1 = str1 + TableReservationPlugin.this.getString(R.string.tablereservation_ampersand);
                    str1 = str1 + TableReservationPlugin.this.getResources().getString(R.string.common_time_upper);
                  }
                  if (TableReservationPlugin.this.personTextView.getText().toString().equals(TableReservationPlugin.this.getResources().getString(R.string.tablereservation_party_size)))
                  {
                    if (str1.length() > 0)
                      str1 = str1 + TableReservationPlugin.this.getString(R.string.tablereservation_ampersand);
                    str1 = str1 + TableReservationPlugin.this.getResources().getString(R.string.tablereservation_party_size);
                  }
                  String str2 = TableReservationPlugin.this.getResources().getString(R.string.tablereservation_common_specify_upper) + " " + str1;
                  Toast localToast1 = Toast.makeText(TableReservationPlugin.this, str2, 1);
                  localToast1.setGravity(81, 0, 95);
                  localToast1.show();
                  return;
                }
                Date localDate1 = new Date(TableReservationPlugin.this.parsedXML.getOrderDate().getTime());
                localDate1.setHours(TableReservationPlugin.this.parsedXML.getOrderTime().houres);
                localDate1.setMinutes(TableReservationPlugin.this.parsedXML.getOrderTime().minutes);
                long l1 = localDate1.getTime();
                if (l1 <= new Date().getTime() + 1000 * TableReservationPlugin.this.parsedXML.getOffsetTime().intValue())
                {
                  Toast.makeText(TableReservationPlugin.this, R.string.tablereservation_warning_too_late, 1).show();
                  return;
                }
                long l2;
                Date localDate7;
                long l3;
                if (TableReservationPlugin.this.parsedXML.getEndTime().houres > 24)
                  if (60 * TableReservationPlugin.this.parsedXML.getOrderTime().houres + TableReservationPlugin.this.parsedXML.getOrderTime().minutes < 60 * (-24 + TableReservationPlugin.this.parsedXML.getEndTime().houres) + TableReservationPlugin.this.parsedXML.getEndTime().minutes)
                  {
                    Date localDate6 = TableReservationPlugin.this.parsedXML.getOrderDate();
                    localDate6.setHours(TableReservationPlugin.this.parsedXML.getStartTime().houres);
                    localDate6.setMinutes(TableReservationPlugin.this.parsedXML.getStartTime().minutes);
                    l2 = localDate6.getTime() - 86400000L;
                    localDate7 = TableReservationPlugin.this.parsedXML.getOrderDate();
                    if (TableReservationPlugin.this.parsedXML.getEndTime().houres > 24)
                    {
                      localDate7.setHours(-24 + TableReservationPlugin.this.parsedXML.getEndTime().houres);
                      localDate7.setMinutes(TableReservationPlugin.this.parsedXML.getEndTime().minutes);
                      l3 = localDate7.getTime();
                    }
                  }
                while (true)
                {
                  if ((l1 < l2) || (l1 > l3 - 1000 * TableReservationPlugin.this.parsedXML.getOffsetTime().intValue()))
                    break label1475;
                  if (TableReservationPlugin.this.isLoggedIn)
                    break label1358;
                  Intent localIntent1 = new Intent(TableReservationPlugin.this, TableReservationLogin.class);
                  localIntent1.putExtra("redirectTo", "redirectToReservationDetails");
                  localIntent1.putExtra("orderInfo", TableReservationPlugin.this.parsedXML);
                  localIntent1.putExtra("fontColor", TableReservationPlugin.this.fontColor);
                  localIntent1.putExtra("backColor", TableReservationPlugin.this.backColor);
                  localIntent1.putExtra("colorSchema", TableReservationPlugin.this.colorSchemeDark);
                  localIntent1.putExtra("appName", TableReservationPlugin.this.widget.getAppName());
                  TableReservationPlugin.this.startActivityForResult(localIntent1, 10005);
                  return;
                  localDate7.setHours(TableReservationPlugin.this.parsedXML.getEndTime().houres);
                  break;
                  Date localDate4 = TableReservationPlugin.this.parsedXML.getOrderDate();
                  localDate4.setHours(TableReservationPlugin.this.parsedXML.getStartTime().houres);
                  localDate4.setMinutes(TableReservationPlugin.this.parsedXML.getStartTime().minutes);
                  l2 = localDate4.getTime();
                  Date localDate5 = TableReservationPlugin.this.parsedXML.getOrderDate();
                  if (TableReservationPlugin.this.parsedXML.getEndTime().houres > 24)
                    localDate5.setHours(-24 + TableReservationPlugin.this.parsedXML.getEndTime().houres);
                  while (true)
                  {
                    localDate5.setMinutes(TableReservationPlugin.this.parsedXML.getEndTime().minutes);
                    if (TableReservationPlugin.this.parsedXML.getEndTime().houres <= 24)
                      break label1161;
                    l3 = 86400000L + localDate5.getTime();
                    break;
                    localDate5.setHours(TableReservationPlugin.this.parsedXML.getEndTime().houres);
                  }
                  label1161: l3 = localDate5.getTime();
                  continue;
                  Date localDate2 = TableReservationPlugin.this.parsedXML.getOrderDate();
                  localDate2.setHours(TableReservationPlugin.this.parsedXML.getStartTime().houres);
                  localDate2.setMinutes(TableReservationPlugin.this.parsedXML.getStartTime().minutes);
                  l2 = localDate2.getTime();
                  Date localDate3 = TableReservationPlugin.this.parsedXML.getOrderDate();
                  if (TableReservationPlugin.this.parsedXML.getEndTime().houres > 24)
                    localDate3.setHours(-24 + TableReservationPlugin.this.parsedXML.getEndTime().houres);
                  while (true)
                  {
                    localDate3.setMinutes(TableReservationPlugin.this.parsedXML.getEndTime().minutes);
                    if (TableReservationPlugin.this.parsedXML.getEndTime().houres <= 24)
                      break label1348;
                    l3 = 86400000L + localDate3.getTime();
                    break;
                    localDate3.setHours(TableReservationPlugin.this.parsedXML.getEndTime().houres);
                  }
                  label1348: l3 = localDate3.getTime();
                }
                label1358: Intent localIntent2 = new Intent(TableReservationPlugin.this, TableReservationReservation.class);
                localIntent2.putExtra("userinfo", TableReservationPlugin.this.user);
                localIntent2.putExtra("cachePath", TableReservationPlugin.this.cachePath);
                localIntent2.putExtra("xml", TableReservationPlugin.this.parsedXML);
                localIntent2.putExtra("fontColor", TableReservationPlugin.this.fontColor);
                localIntent2.putExtra("backColor", TableReservationPlugin.this.backColor);
                localIntent2.putExtra("colorSchema", TableReservationPlugin.this.colorSchemeDark);
                TableReservationPlugin.this.startActivityForResult(localIntent2, 7);
                return;
                label1475: Toast localToast2 = Toast.makeText(TableReservationPlugin.this, R.string.tablereservation_warning_wrong_time, 1);
                localToast2.setGravity(81, 0, 95);
                localToast2.show();
              }
            };
            localLinearLayout1.setOnClickListener(local6);
            LinearLayout localLinearLayout2 = this.datePicker;
            7 local7 = new View.OnClickListener()
            {
              protected Object clone()
                throws CloneNotSupportedException
              {
                return super.clone();
              }

              public void onClick(View paramView)
              {
                ViewGroup localViewGroup = (ViewGroup)TableReservationPlugin.this.datePickerView.getParent();
                if (localViewGroup != null)
                  localViewGroup.removeAllViews();
                ((DatePicker)TableReservationPlugin.this.datePickerView.findViewById(R.id.sergeyb_tablereservation_date_layout_datePicker)).init(1900 + TableReservationPlugin.this.parsedXML.getOrderDate().getYear(), TableReservationPlugin.this.parsedXML.getOrderDate().getMonth(), TableReservationPlugin.this.parsedXML.getOrderDate().getDate(), null);
                TableReservationPlugin.this.builder.setView(TableReservationPlugin.this.datePickerView);
                TableReservationPlugin.access$1702(TableReservationPlugin.this, TableReservationPlugin.this.builder.create());
                TableReservationPlugin.this.mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
                {
                  public void onCancel(DialogInterface paramDialogInterface)
                  {
                    if (TableReservationPlugin.this.mAlertDialog != null)
                      TableReservationPlugin.this.mAlertDialog.dismiss();
                  }
                });
                TableReservationPlugin.this.mAlertDialog.show();
              }
            };
            localLinearLayout2.setOnClickListener(local7);
            LinearLayout localLinearLayout3 = this.timePicker;
            8 local8 = new View.OnClickListener()
            {
              public void onClick(View paramView)
              {
                ViewGroup localViewGroup = (ViewGroup)TableReservationPlugin.this.timePickerView.getParent();
                if (localViewGroup != null)
                  localViewGroup.removeAllViews();
                TimePicker localTimePicker = (TimePicker)TableReservationPlugin.this.timePickerView.findViewById(R.id.sergeyb_tablereservation_time_layout_timePicker);
                localTimePicker.setCurrentHour(Integer.valueOf(TableReservationPlugin.this.parsedXML.getOrderTime().houres));
                localTimePicker.setCurrentMinute(Integer.valueOf(TableReservationPlugin.this.parsedXML.getOrderTime().minutes));
                TableReservationPlugin.this.builder.setView(TableReservationPlugin.this.timePickerView);
                TableReservationPlugin.access$1702(TableReservationPlugin.this, TableReservationPlugin.this.builder.create());
                TableReservationPlugin.this.mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
                {
                  public void onCancel(DialogInterface paramDialogInterface)
                  {
                    if (TableReservationPlugin.this.mAlertDialog != null)
                      TableReservationPlugin.this.mAlertDialog.dismiss();
                  }
                });
                TableReservationPlugin.this.mAlertDialog.show();
              }
            };
            localLinearLayout3.setOnClickListener(local8);
            LinearLayout localLinearLayout4 = this.personPicker;
            9 local9 = new View.OnClickListener()
            {
              public void onClick(View paramView)
              {
                Intent localIntent = new Intent(TableReservationPlugin.this, TableReservationPersonPicker.class);
                localIntent.putExtra("maxperson", TableReservationPlugin.this.parsedXML.getMaxpersons());
                TableReservationPlugin.this.startActivityForResult(localIntent, 10006);
              }
            };
            localLinearLayout4.setOnClickListener(local9);
            this.handler.sendEmptyMessage(4);
            10 local10 = new Runnable()
            {
              public void run()
              {
                try
                {
                  if (new File(TableReservationPlugin.this.cachePath + "/restaurantbanner.jpg").exists())
                    TableReservationPlugin.this.parsedXML.setrestaurantimageFilePath(TableReservationPlugin.this.cachePath + "/restaurantbanner.jpg");
                  while (true)
                  {
                    if (TableReservationPlugin.this.isLoggedIn)
                    {
                      String str = TableReservationHTTP.sendListOrdersRequest(TableReservationPlugin.this.user, TableReservationPlugin.this.parsedXML);
                      if (str.compareToIgnoreCase("error") != 0)
                      {
                        TableReservationPlugin.access$2002(TableReservationPlugin.this, JSONParser.parseOrderList(str));
                        TableReservationPlugin.access$2102(TableReservationPlugin.this, TableReservationPlugin.this.checkForUpcoming(TableReservationPlugin.this.orderList));
                        if (TableReservationPlugin.this.upcoming != null)
                          TableReservationPlugin.this.handler.sendEmptyMessage(6);
                      }
                    }
                    TableReservationPlugin.this.handler.sendEmptyMessage(5);
                    return;
                    if (TableReservationPlugin.this.parsedXML.getRestaurantimageurl() == null)
                      continue;
                    TableReservationPlugin.this.splashScreenDownload(TableReservationPlugin.this.parsedXML.getRestaurantimageurl(), TableReservationPlugin.this.cachePath);
                    TableReservationPlugin.this.parsedXML.setrestaurantimageFilePath(TableReservationPlugin.this.cachePath + "/restaurantbanner.jpg");
                  }
                }
                catch (Exception localException)
                {
                  while (true)
                    Log.d("", "");
                }
              }
            };
            this.imgDownloader = new Thread(local10);
            this.imgDownloader.start();
            return;
            setTopBarTitle(getResources().getString(R.string.restaurant));
            continue;
            this.datePicker.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_first_dark);
            this.timePicker.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_middle_dark);
            this.personPicker.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_last_dark);
            this.datePickerImg.setImageResource(R.drawable.sergeyb_tablereservation_calendar_img);
            this.timePickerImg.setImageResource(R.drawable.sergeyb_tablereservation_time_img);
            this.personPickerImg.setImageResource(R.drawable.sergeyb_tablereservation_persons_img);
            this.colorSchemeDark = true;
            break label598;
            localException = localException;
            Log.w("LOAD CONFIG", localException);
          }
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            Log.d("", "");
            continue;
            String str1 = Utils.convertTimeToFormat(this.parsedXML.getStartTime().houres, this.parsedXML.getStartTime().minutes, false);
            String str2 = Utils.convertTimeToFormat(this.parsedXML.getEndTime().houres, this.parsedXML.getEndTime().minutes, false);
          }
        }
      }
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
    case 10005:
    case 7:
    case 10006:
    }
    do
    {
      do
      {
        while (true)
        {
          return;
          if (paramInt2 != -1)
            break;
          String str6 = this.user.getAccountId();
          this.user = ((User)paramIntent.getSerializableExtra("user"));
          if ((str6 != null) && (str6.length() != 0) && (str6 != this.user.getAccountId()))
            this.user.setAccountId(str6);
          if ((this.user.getUserEmail() != null) && (this.user.getUserEmail().length() != 0))
            this.parsedXML.setCustomerEmail(this.user.getUserEmail());
          String str7 = paramIntent.getStringExtra("redirectTo");
          File localFile;
          if (this.user.getAccountType() != User.ACCOUNT_TYPES.GUEST)
          {
            localFile = new File(this.cachePath + "/usercache.data");
            if (localFile.exists());
          }
          try
          {
            localFile.createNewFile();
            ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(localFile));
            localObjectOutputStream.writeObject(this.user);
            localObjectOutputStream.close();
            Log.i("CACHE APP CONF", "success");
            this.isLoggedIn = true;
            if (str7.compareTo("redirectToOrderList") == 0)
            {
              Intent localIntent1 = new Intent(this, TableReservatioinListOfReservations.class);
              localIntent1.putExtra("userinfo", this.user);
              localIntent1.putExtra("xml", this.parsedXML);
              localIntent1.putExtra("fontColor", this.fontColor);
              localIntent1.putExtra("backColor", this.backColor);
              startActivity(localIntent1);
              return;
            }
          }
          catch (Exception localException)
          {
            while (true)
            {
              Log.w("CACHE APP CONF", localException);
              localFile.delete();
            }
          }
          if (str7.compareTo("redirectToReservationDetails") != 0)
            continue;
          Intent localIntent2 = new Intent(this, TableReservationReservation.class);
          localIntent2.putExtra("userinfo", this.user);
          localIntent2.putExtra("cachePath", this.cachePath);
          localIntent2.putExtra("xml", this.parsedXML);
          localIntent2.putExtra("fontColor", this.fontColor);
          localIntent2.putExtra("backColor", this.backColor);
          localIntent2.putExtra("colorSchema", this.colorSchemeDark);
          startActivityForResult(localIntent2, 7);
          return;
        }
        Toast.makeText(this, R.string.alert_not_logged_in, 1).show();
        return;
      }
      while (paramInt2 != -1);
      this.dateTextView.setText(getResources().getString(R.string.common_date_upper));
      this.dateTextView.setTextColor(0x3CFFFFFF & this.fontColor);
      if (this.widget.getDateFormat() == 1)
      {
        String str4 = Utils.convertTimeToFormat(this.parsedXML.getStartTime().houres, this.parsedXML.getStartTime().minutes, true);
        String str5 = Utils.convertTimeToFormat(this.parsedXML.getEndTime().houres, this.parsedXML.getEndTime().minutes, true);
        this.timeTextView.setText(getResources().getString(R.string.common_time_upper) + " (" + str4 + " - " + str5 + ")");
      }
      while (true)
      {
        this.timeTextView.setTextColor(0x3CFFFFFF & this.fontColor);
        this.personTextView.setText(getResources().getString(R.string.tablereservation_party_size));
        this.personTextView.setTextColor(0x3CFFFFFF & this.fontColor);
        this.parsedXML.setOrderDate(new Date());
        this.parsedXML.setOrderTime(this.parsedXML.getStartTime().houres, this.parsedXML.getStartTime().minutes, "");
        return;
        String str2 = Utils.convertTimeToFormat(this.parsedXML.getStartTime().houres, this.parsedXML.getStartTime().minutes, false);
        String str3 = Utils.convertTimeToFormat(this.parsedXML.getEndTime().houres, this.parsedXML.getEndTime().minutes, false);
        this.timeTextView.setText(getResources().getString(R.string.common_date_upper) + " (" + str2 + " - " + str3 + ")");
      }
    }
    while (paramInt2 != -1);
    int i = paramIntent.getIntExtra("persons", 2);
    this.parsedXML.setPersonsAmount(i);
    Object localObject = "";
    try
    {
      PluralResources localPluralResources = new PluralResources(getResources());
      int j = R.plurals.tablereservation_persons_list;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(i);
      String str1 = localPluralResources.getQuantityString(j, i, arrayOfObject);
      localObject = str1;
      this.personTextView.setText((CharSequence)localObject);
      this.personTextView.setTextColor(this.fontColor);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
        localNoSuchMethodException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationPlugin
 * JD-Core Version:    0.6.0
 */