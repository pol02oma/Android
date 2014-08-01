package com.ibuildapp.romanblack.TableReservationPlugin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.HouresMinutes;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationHTTP;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.Utils;
import com.seppius.i18n.plurals.PluralResources;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TableReservationReservation extends AppBuilderModuleMain
{
  private final int ADD_REQUEST_ERROR = 4;
  private final int CONFIRM = 10003;
  private final int HIDE_PROGRESS_DIALOG = 1;
  private final int IMAGE_HEIGHT = 100;
  private final int MAIL_SEND_ERROR = 3;
  private final int MODIFY = 10002;
  private final int SHOW_PROGRESS_DIALOG = 0;
  private final int SPECIAL_REQUEST = 10001;
  private final int START_SUMMARY = 2;
  private int backColor;
  private ImageView banner;
  private String cachePath = "";
  private boolean colorSchema;
  private LinearLayout confirmButton;
  private LinearLayout custom;
  private TextView emailText;
  private int fontColor;
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
        TableReservationReservation.this.showProgressDialog();
        return;
      case 1:
        TableReservationReservation.this.hideProgressDialog();
        return;
      case 2:
        TableReservationReservation.this.startSummaryActivity();
        return;
      case 3:
        Log.d("MAIL_SEND_ERROR", "");
        return;
      case 4:
      }
      Toast localToast = Toast.makeText(TableReservationReservation.this, R.string.tablereservation_warning_timeout, 1);
      localToast.setGravity(81, 0, 95);
      localToast.show();
      Log.d("", "");
    }
  };
  private View headerView;
  private LayoutInflater layoutInflater;
  private LinearLayout mainLayout;
  private TableReservationInfo orderInfo;
  private String orderUUID;
  private ProgressDialog progressDialog = null;
  private TextView reservationDate;
  private TextView reservationTime;
  private TextView restaurantGreetings;
  private TextView restaurantName;
  private LinearLayout specReqButton;
  private TextView specialRequestText;
  private User user;
  private TextView userName;
  private TextView userPhone;
  private Thread workerThread;

  private Bitmap decodeImageFile(String paramString)
  {
    try
    {
      File localFile = new File(paramString);
      BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
      localOptions1.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions1);
      int i = localOptions1.outHeight;
      int j = 1;
      while (true)
      {
        if (i / 2 < 100)
        {
          BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
          localOptions2.inSampleSize = j;
          return BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions2);
        }
        i /= 2;
        j *= 2;
      }
    }
    catch (Exception localException)
    {
      Log.d("", "");
    }
    return null;
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
    {
      this.progressDialog.dismiss();
      this.progressDialog = null;
    }
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
            if ((TableReservationReservation.this.workerThread != null) && (TableReservationReservation.this.workerThread.isAlive()))
              TableReservationReservation.this.workerThread.interrupt();
            TableReservationReservation.this.progressDialog.dismiss();
            TableReservationReservation.access$1202(TableReservationReservation.this, null);
            return;
          }
          catch (Exception localException)
          {
            Log.d("", "");
          }
        }
      });
  }

  private void startSummaryActivity()
  {
    Intent localIntent = new Intent(this, TableReservationSummary.class);
    localIntent.putExtra("orderUUID", this.orderUUID);
    localIntent.putExtra("userinfo", this.user);
    localIntent.putExtra("xml", this.orderInfo);
    localIntent.putExtra("fontColor", this.fontColor);
    localIntent.putExtra("backColor", this.backColor);
    localIntent.putExtra("startPoint", "summary");
    startActivityForResult(localIntent, 10003);
  }

  public void create()
  {
    setContentView(R.layout.sergeyb_tablereservation_reservation);
    this.layoutInflater = LayoutInflater.from(this);
    setTopBarTitle(getResources().getString(R.string.tablereservation_reservation));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TableReservationReservation.this.finish();
      }
    });
    setTopBarRightButtonText(getResources().getString(R.string.common_modify_upper), false, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(TableReservationReservation.this, TableReservationModify.class);
        localIntent.putExtra("xml", TableReservationReservation.this.orderInfo);
        localIntent.putExtra("cachePath", TableReservationReservation.this.cachePath);
        localIntent.putExtra("specRequest", TableReservationReservation.this.orderInfo.getSpecialRequest());
        localIntent.putExtra("fontColor", TableReservationReservation.this.fontColor);
        localIntent.putExtra("backColor", TableReservationReservation.this.backColor);
        localIntent.putExtra("colorSchema", TableReservationReservation.this.colorSchema);
        TableReservationReservation.this.startActivityForResult(localIntent, 10002);
      }
    });
    this.mainLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_main_layout));
    this.reservationDate = ((TextView)findViewById(R.id.sergeyb_tablereservation_reservation_date));
    this.reservationTime = ((TextView)findViewById(R.id.sergeyb_tablereservation_reservation_time_and_persons));
    this.userName = ((TextView)findViewById(R.id.sergeyb_tablereservation_reservation_username));
    this.userPhone = ((TextView)findViewById(R.id.sergeyb_tablereservation_reservation_userphone));
    this.specReqButton = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_special_request));
    this.confirmButton = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_confirm));
    this.specialRequestText = ((TextView)findViewById(R.id.sergeyb_tablereservation_special_request_text));
    this.emailText = ((TextView)findViewById(R.id.sergeyb_tablereservation_reservation_email));
    Intent localIntent = getIntent();
    this.cachePath = localIntent.getStringExtra("cachePath");
    this.user = ((User)localIntent.getSerializableExtra("userinfo"));
    this.orderInfo = ((TableReservationInfo)localIntent.getSerializableExtra("xml"));
    this.fontColor = localIntent.getIntExtra("fontColor", -1);
    this.backColor = localIntent.getIntExtra("backColor", Color.parseColor("#37393b"));
    this.colorSchema = localIntent.getBooleanExtra("colorSchema", true);
    label372: String str1;
    label524: HouresMinutes localHouresMinutes;
    Object localObject;
    if (this.colorSchema)
    {
      this.specReqButton.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha_dark);
      if (this.orderInfo.getRestaurantimageurl() == null)
        break label811;
      this.headerView = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_reservation_with_img, null);
      this.banner = ((ImageView)this.headerView.findViewById(R.id.sergeyb_tablereservation_restaurant_image));
      Bitmap localBitmap = decodeImageFile(this.orderInfo.getrestaurantimageFilePath());
      if (localBitmap != null)
        this.banner.setImageBitmap(localBitmap);
      this.mainLayout.setBackgroundColor(this.backColor);
      this.reservationDate.setTextColor(this.fontColor);
      this.reservationTime.setTextColor(this.fontColor);
      this.emailText.setTextColor(this.fontColor);
      this.userName.setTextColor(this.fontColor);
      this.userPhone.setTextColor(this.fontColor);
      this.specialRequestText.setTextColor(this.fontColor);
      this.custom = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_custom_layout));
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
      this.custom.addView(this.headerView, localLayoutParams);
      str1 = "";
      if (!Locale.getDefault().toString().equals("en_US"))
        break label891;
      str1 = new SimpleDateFormat("EEEEE, MMMMM dd").format(this.orderInfo.getOrderDate());
      this.reservationDate.setText(str1);
      localHouresMinutes = this.orderInfo.getOrderTime();
      localObject = "";
      if (!Locale.getDefault().toString().equals("ru_RU"))
        break label940;
    }
    while (true)
    {
      try
      {
        PluralResources localPluralResources = new PluralResources(getResources());
        int i = R.plurals.orderTableForPerson;
        int j = this.orderInfo.getPersonsAmount();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(this.orderInfo.getPersonsAmount());
        String str2 = localPluralResources.getQuantityString(i, j, arrayOfObject);
        String str3 = Utils.convertTimeToFormat(localHouresMinutes.houres, localHouresMinutes.minutes, true) + " " + str2;
        localObject = str3;
        this.reservationTime.setText((CharSequence)localObject);
        if (this.user.getAccountType() != User.ACCOUNT_TYPES.GUEST)
          break label997;
        this.userName.setText(getResources().getString(R.string.tablereservation_guest));
        if (this.orderInfo.getPhoneNumber() == null)
          break label1128;
        if (this.orderInfo.getPhoneNumber().length() != 0)
          break label1103;
        this.userPhone.setVisibility(8);
        if (this.orderInfo.getCustomerEmail() == null)
          break label1165;
        if (this.orderInfo.getCustomerEmail().length() != 0)
          break label1140;
        this.emailText.setVisibility(8);
        this.specReqButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            Intent localIntent = new Intent(TableReservationReservation.this, TableReservationSpecialRequest.class);
            localIntent.putExtra("specRequest", TableReservationReservation.this.orderInfo.getSpecialRequest());
            localIntent.putExtra("backColor", TableReservationReservation.this.backColor);
            localIntent.putExtra("fontColor", TableReservationReservation.this.fontColor);
            localIntent.putExtra("colorSchema", TableReservationReservation.this.colorSchema);
            TableReservationReservation.this.startActivityForResult(localIntent, 10001);
          }
        });
        this.confirmButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            NetworkInfo localNetworkInfo = ((ConnectivityManager)TableReservationReservation.this.getSystemService("connectivity")).getActiveNetworkInfo();
            if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
            {
              Toast localToast = Toast.makeText(TableReservationReservation.this, R.string.alert_no_internet, 1);
              localToast.setGravity(81, 0, 95);
              localToast.show();
              return;
            }
            TableReservationReservation.this.handler.sendEmptyMessage(0);
            TableReservationReservation.access$902(TableReservationReservation.this, new Thread(new Runnable()
            {
              public void run()
              {
                TableReservationReservation.access$1002(TableReservationReservation.this, TableReservationHTTP.sendAddOrderRequest(TableReservationReservation.this.user, TableReservationReservation.this.handler, TableReservationReservation.this.orderInfo));
                if (TableReservationReservation.this.orderUUID != null)
                {
                  if ((TableReservationReservation.this.user.getUserEmail() != null) && (TableReservationReservation.this.user.getUserEmail().length() != 0))
                    TableReservationHTTP.sendMail(3, TableReservationReservation.this.user, TableReservationReservation.this.orderInfo, TableReservationReservation.this.getResources().getString(R.string.app_name), TableReservationReservation.this.handler);
                  TableReservationReservation.this.handler.sendEmptyMessage(2);
                }
                TableReservationReservation.this.handler.sendEmptyMessage(1);
              }
            }));
            TableReservationReservation.this.workerThread.start();
          }
        });
        return;
        this.specReqButton.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha);
        break;
        label811: this.headerView = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_reservation_no_image, null);
        this.restaurantName = ((TextView)this.headerView.findViewById(R.id.sergeyb_tablereservation_restaurant_name));
        this.restaurantName.setText(this.orderInfo.getRestaurantName());
        this.restaurantGreetings = ((TextView)this.headerView.findViewById(R.id.sergeyb_tablereservation_restaurant_greetings));
        this.restaurantGreetings.setText(this.orderInfo.getRestaurantGreeting());
        break label372;
        label891: if (!Locale.getDefault().toString().equals("ru_RU"))
          break label524;
        str1 = new SimpleDateFormat("EEEEE, dd MMMMM").format(this.orderInfo.getOrderDate());
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        localNoSuchMethodException.printStackTrace();
        continue;
      }
      label940: localObject = Utils.convertTimeToFormat(localHouresMinutes.houres, localHouresMinutes.minutes, false) + " for " + Integer.toString(this.orderInfo.getPersonsAmount()) + " people";
      continue;
      label997: if (this.user.getAccountType() == User.ACCOUNT_TYPES.IBUILDAPP)
      {
        this.userName.setText(this.user.getUserName());
        continue;
      }
      if (this.user.getAccountType() == User.ACCOUNT_TYPES.TWITTER)
      {
        this.userName.setText(this.user.getUserName());
        continue;
      }
      this.userName.setText(this.user.getUserFirstName() + " " + this.user.getUserLastName());
      continue;
      label1103: this.userPhone.setVisibility(0);
      this.userPhone.setText(this.orderInfo.getPhoneNumber());
      continue;
      label1128: this.userPhone.setVisibility(8);
      continue;
      label1140: this.emailText.setVisibility(0);
      this.emailText.setText(this.orderInfo.getCustomerEmail());
      continue;
      label1165: this.emailText.setVisibility(8);
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
    case 10001:
    case 10002:
    case 10003:
    }
    label461: String str1;
    label518: label543: 
    do
    {
      do
      {
        do
          return;
        while (paramInt2 != -1);
        this.orderInfo.setSpecialRequest(paramIntent.getStringExtra("special"));
        return;
      }
      while (paramInt2 != -1);
      this.orderInfo.setOrderDate((Date)paramIntent.getSerializableExtra("date"));
      HouresMinutes localHouresMinutes1 = (HouresMinutes)paramIntent.getSerializableExtra("time");
      this.orderInfo.setOrderTime(localHouresMinutes1.houres, localHouresMinutes1.minutes, localHouresMinutes1.am_pm);
      this.orderInfo.setPhoneNumber(paramIntent.getStringExtra("phone"));
      this.orderInfo.setCustomerEmail(paramIntent.getStringExtra("email"));
      this.orderInfo.setSpecialRequest(paramIntent.getStringExtra("special"));
      this.orderInfo.setPersonsAmount(Integer.parseInt(paramIntent.getStringExtra("persons")));
      String str2;
      HouresMinutes localHouresMinutes2;
      Object localObject;
      if (Locale.getDefault().toString().equals("en_US"))
      {
        str2 = new SimpleDateFormat("EEEEE, MMMMM dd").format(this.orderInfo.getOrderDate());
        this.reservationDate.setText(str2);
        localHouresMinutes2 = this.orderInfo.getOrderTime();
        localObject = "";
        if (!Locale.getDefault().toString().equals("ru_RU"))
          break label461;
      }
      while (true)
      {
        try
        {
          PluralResources localPluralResources = new PluralResources(getResources());
          int i = R.plurals.orderTableForPerson;
          int j = this.orderInfo.getPersonsAmount();
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(this.orderInfo.getPersonsAmount());
          String str3 = localPluralResources.getQuantityString(i, j, arrayOfObject);
          String str4 = Utils.convertTimeToFormat(localHouresMinutes2.houres, localHouresMinutes2.minutes, true) + " " + str3;
          localObject = str4;
          this.reservationTime.setText((CharSequence)localObject);
          if (!TextUtils.isEmpty(this.orderInfo.getPhoneNumber()))
            break label518;
          this.userPhone.setVisibility(8);
          if (!TextUtils.isEmpty(this.orderInfo.getCustomerEmail()))
            break label543;
          this.emailText.setVisibility(8);
          return;
          boolean bool = Locale.getDefault().toString().equals("ru_RU");
          str2 = null;
          if (!bool)
            break;
          str2 = new SimpleDateFormat("EEEEE, dd MMMMM").format(this.orderInfo.getOrderDate());
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          localNoSuchMethodException.printStackTrace();
          continue;
        }
        localObject = Utils.convertTimeToFormat(localHouresMinutes2.houres, localHouresMinutes2.minutes, false) + " for " + Integer.toString(this.orderInfo.getPersonsAmount()) + " people";
        continue;
        this.userPhone.setVisibility(0);
        this.userPhone.setText(this.orderInfo.getPhoneNumber());
      }
      this.emailText.setVisibility(0);
      this.emailText.setText(this.orderInfo.getCustomerEmail());
      return;
      str1 = paramIntent.getStringExtra("howtoclose");
    }
    while ((str1.length() == 0) || (str1 == null));
    setResult(-1, new Intent());
    finish();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationReservation
 * JD-Core Version:    0.6.0
 */