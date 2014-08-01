package com.ibuildapp.romanblack.TableReservationPlugin;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.HouresMinutes;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationPhoneNumberFormattingTextWatcher;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TableReservationModify extends AppBuilderModuleMain
  implements View.OnFocusChangeListener
{
  private final String DEFAULT_PHONE_TEXT = "--- --- ----";
  private int backColor;
  private AlertDialog.Builder builder;
  private String cachePath;
  private boolean colorSchema;
  private ImageView dateImg;
  private View dateTimePickerView;
  private LinearLayout doneButton;
  private int fontColor;
  private LinearLayout hideKeyboard;
  private LayoutInflater layoutInflater;
  private Dialog mAlertDialog;
  private EditText mailEdit;
  private LinearLayout mailEditLayout;
  private ImageView mailImg;
  private TextView mailText;
  private FrameLayout mainLayout;
  private TextView orderDateText;
  private TableReservationInfo orderInfo;
  private TextView orderTimeText;
  private ImageView personImg;
  private LinearLayout personModify;
  private View personPickerView;
  private TextView personText;
  private TextView phoneContactText;
  private EditText phoneEdit;
  private LinearLayout phoneEditLayout;
  private ImageView phoneImg;
  private TextView reservDetailsText;
  private TextView specReqText;
  private EditText specText;
  private LinearLayout specTextLayout;
  private LinearLayout taskBar;
  private LinearLayout timeDateModify;

  private void closeActivityBad()
  {
    setResult(0, new Intent());
    finish();
  }

  private void closeActivityOk()
  {
    File localFile = new File(this.cachePath + "/userphone.data");
    if (!localFile.exists());
    try
    {
      localFile.createNewFile();
    }
    catch (IOException localIOException)
    {
      try
      {
        while (true)
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
          localFileOutputStream.write(this.phoneEdit.getText().toString().getBytes());
          localFileOutputStream.flush();
          localFileOutputStream.close();
          Intent localIntent = new Intent();
          localIntent.putExtra("special", this.specText.getText().toString());
          if (this.phoneEdit.getText().toString().compareTo("--- --- ----") == 0)
            this.phoneEdit.setText("");
          localIntent.putExtra("phone", this.phoneEdit.getText().toString());
          localIntent.putExtra("email", this.mailEdit.getText().toString());
          localIntent.putExtra("persons", this.personText.getText());
          localIntent.putExtra("date", this.orderInfo.getOrderDate());
          localIntent.putExtra("time", this.orderInfo.getOrderTime());
          setResult(-1, localIntent);
          finish();
          return;
          localIOException = localIOException;
          Log.d("CACHE APP CONF", "success");
        }
      }
      catch (Exception localException)
      {
        while (true)
          localFile.delete();
      }
    }
  }

  private String convertTimeToFormat(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      String str7;
      String str9;
      if (Integer.toString(paramInt1).length() < 2)
      {
        String str10 = Integer.toString(paramInt1);
        str7 = "0" + str10;
        if (Integer.toString(paramInt2).length() >= 2)
          break label120;
        str9 = Integer.toString(paramInt2);
      }
      label120: for (String str8 = "0" + str9; ; str8 = Integer.toString(paramInt2))
      {
        return str7 + ":" + str8;
        str7 = Integer.toString(paramInt1);
        break;
      }
    }
    String str1;
    String str2;
    label186: int i;
    String str3;
    if (paramInt2 + paramInt1 * 100 >= 1200)
    {
      str1 = "PM";
      if (Integer.toString(paramInt2).length() >= 2)
        break label286;
      String str6 = Integer.toString(paramInt2);
      str2 = "0" + str6;
      if (paramInt1 <= 12)
        break label305;
      i = paramInt1 - 12;
      if (Integer.toString(i).length() >= 2)
        break label295;
      String str5 = Integer.toString(i);
      str3 = "0" + str5;
    }
    while (true)
    {
      return str3 + ":" + str2 + " " + str1;
      str1 = "AM";
      break;
      label286: str2 = Integer.toString(paramInt2);
      break label186;
      label295: str3 = Integer.toString(i);
      continue;
      label305: if (Integer.toString(paramInt1).length() < 2)
      {
        String str4 = Integer.toString(paramInt1).toString();
        str3 = "0" + str4;
        continue;
      }
      str3 = Integer.toString(paramInt1).toString();
    }
  }

  private View dateTimeModifierViewinflate()
  {
    if (this.layoutInflater == null)
      this.layoutInflater = LayoutInflater.from(this);
    View localView = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_datetimepicker_alert, null);
    ((Button)localView.findViewById(R.id.okButton)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        View localView = (View)paramView.getParent().getParent();
        DatePicker localDatePicker = (DatePicker)localView.findViewById(R.id.datePicker);
        localDatePicker.clearChildFocus(TableReservationModify.this.getCurrentFocus());
        Date localDate = TableReservationModify.this.orderInfo.getOrderDate();
        localDate.setYear(-1900 + localDatePicker.getYear());
        localDate.setMonth(localDatePicker.getMonth());
        localDate.setDate(localDatePicker.getDayOfMonth());
        TableReservationModify.this.orderInfo.setOrderDate(localDate);
        String str1 = new SimpleDateFormat("E").format(TableReservationModify.this.orderInfo.getOrderDate()) + ", " + new SimpleDateFormat("MMMMMMM").format(TableReservationModify.this.orderInfo.getOrderDate()) + " " + new SimpleDateFormat("d").format(TableReservationModify.this.orderInfo.getOrderDate()) + ", ";
        TableReservationModify.this.orderDateText.setText(str1);
        TimePicker localTimePicker = (TimePicker)localView.findViewById(R.id.timePicker);
        localTimePicker.clearChildFocus(TableReservationModify.this.getCurrentFocus());
        EditText localEditText = (EditText)((LinearLayout)(LinearLayout)((ViewGroup)localTimePicker.getChildAt(0)).getChildAt(1)).getChildAt(1);
        String str2 = ((Button)((ViewGroup)localTimePicker.getChildAt(0)).getChildAt(2)).getText().toString();
        TableReservationModify.this.orderInfo.setOrderTime(localTimePicker.getCurrentHour().intValue(), Integer.parseInt(localEditText.getText().toString()), str2);
        HouresMinutes localHouresMinutes = TableReservationModify.this.orderInfo.getOrderTime();
        String str3 = TableReservationModify.this.convertTimeToFormat(localHouresMinutes.houres, localHouresMinutes.minutes, false);
        TableReservationModify.this.orderTimeText.setText(str3);
        if (TableReservationModify.this.mAlertDialog != null)
          TableReservationModify.this.mAlertDialog.dismiss();
      }
    });
    ((Button)localView.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (TableReservationModify.this.mAlertDialog != null)
          TableReservationModify.this.mAlertDialog.dismiss();
      }
    });
    return localView;
  }

  private View personPickerViewinflate()
  {
    if (this.layoutInflater == null)
      this.layoutInflater = LayoutInflater.from(this);
    View localView = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_person_picker, null);
    ListView localListView = (ListView)localView.findViewById(R.id.sergeyb_tablereservation_person_layout_listView);
    localListView.setAdapter(new TableReservationPersonPickerAdapter(this, 15));
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        TableReservationModify.this.orderInfo.setPersonsAmount(paramInt + 1);
        if (paramInt + 1 == 1)
          TableReservationModify.this.personText.setText(Integer.toString(paramInt + 1));
        while (true)
        {
          if (TableReservationModify.this.mAlertDialog != null)
            TableReservationModify.this.mAlertDialog.dismiss();
          return;
          TableReservationModify.this.personText.setText(Integer.toString(paramInt + 1));
        }
      }
    });
    return localView;
  }

  public void create()
  {
    requestWindowFeature(1);
    setContentView(R.layout.sergeyb_tablereservation_modify);
    getWindow().setFlags(1024, 1024);
    setTopBarTitle(getResources().getString(R.string.common_modify_upper));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TableReservationModify.this.closeActivityBad();
      }
    });
    this.builder = new AlertDialog.Builder(this);
    this.personPickerView = personPickerViewinflate();
    Intent localIntent = getIntent();
    this.cachePath = localIntent.getStringExtra("cachePath");
    this.orderInfo = ((TableReservationInfo)localIntent.getSerializableExtra("xml"));
    this.fontColor = localIntent.getIntExtra("fontColor", -1);
    this.backColor = localIntent.getIntExtra("backColor", Color.parseColor("#37393b"));
    this.colorSchema = localIntent.getBooleanExtra("colorSchema", true);
    Date localDate = this.orderInfo.getOrderDate();
    this.mainLayout = ((FrameLayout)findViewById(R.id.sergeyb_tablereservation_main_layout));
    this.personText = ((TextView)findViewById(R.id.sergeyb_tablereservation_person_text));
    this.specText = ((EditText)findViewById(R.id.sergeyb_tablereservation_special_request_edittext));
    this.specText.setText(localIntent.getStringExtra("specRequest"));
    this.personText = ((TextView)findViewById(R.id.sergeyb_tablereservation_person_text));
    this.personText.setText(Integer.toString(this.orderInfo.getPersonsAmount()));
    this.orderDateText = ((TextView)findViewById(R.id.sergeyb_tablereservation_date_text));
    this.orderTimeText = ((TextView)findViewById(R.id.sergeyb_tablereservation_time_text));
    this.timeDateModify = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_time_date));
    this.personModify = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_person));
    this.phoneEdit = ((EditText)findViewById(R.id.sergeyb_tablereservation_phone_edittext));
    this.reservDetailsText = ((TextView)findViewById(R.id.sergeyb_tablereservation_reserv_details_text));
    this.phoneEditLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_phone_layout));
    this.phoneContactText = ((TextView)findViewById(R.id.sergeyb_tablereservation_phone_text));
    this.specReqText = ((TextView)findViewById(R.id.sergeyb_tablereservation_special_request_text));
    this.mailText = ((TextView)findViewById(R.id.sergeyb_tablereservation_mail_text));
    this.mailEdit = ((EditText)findViewById(R.id.sergeyb_tablereservation_mail_edittext));
    this.taskBar = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_bottom_taskbar));
    this.hideKeyboard = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_hide_keyboard));
    this.dateImg = ((ImageView)findViewById(R.id.sergeyb_tablereservation_dateimg));
    this.personImg = ((ImageView)findViewById(R.id.sergeyb_tablereservation_personimg));
    this.phoneImg = ((ImageView)findViewById(R.id.sergeyb_tablereservation_phoneimg));
    this.mailImg = ((ImageView)findViewById(R.id.sergeyb_tablereservation_mailimg));
    this.mailEditLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_mail_layout));
    this.specTextLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_special_request_edittext_layout));
    this.timeDateModify.requestFocus();
    getWindow().setSoftInputMode(3);
    if (this.colorSchema)
    {
      this.dateImg.setImageResource(R.drawable.sergeyb_tablereservation_time_img);
      this.personImg.setImageResource(R.drawable.sergeyb_tablereservation_persons_img);
      this.phoneImg.setImageResource(R.drawable.sergeyb_tablereservation_phone);
      this.mailImg.setImageResource(R.drawable.sergeyb_tablereservation_email);
      this.timeDateModify.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha_dark);
      this.personModify.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha_dark);
      this.phoneEditLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha_dark);
      this.mailEditLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha_dark);
      this.specTextLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha_dark);
    }
    while (true)
    {
      this.mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          Rect localRect = new Rect();
          TableReservationModify.this.mainLayout.getWindowVisibleDisplayFrame(localRect);
          if (TableReservationModify.this.mainLayout.getRootView().getHeight() - (localRect.bottom - localRect.top) > 100)
          {
            TableReservationModify.this.taskBar.setVisibility(0);
            return;
          }
          TableReservationModify.this.taskBar.setVisibility(4);
        }
      });
      this.hideKeyboard.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          ((InputMethodManager)TableReservationModify.this.getSystemService("input_method")).hideSoftInputFromWindow(TableReservationModify.this.mailEdit.getWindowToken(), 0);
        }
      });
      this.mainLayout.setBackgroundColor(this.backColor);
      this.reservDetailsText.setTextColor(this.fontColor);
      this.phoneContactText.setTextColor(this.fontColor);
      this.specReqText.setTextColor(this.fontColor);
      this.orderDateText.setTextColor(this.fontColor);
      this.orderTimeText.setTextColor(this.fontColor);
      this.personText.setTextColor(this.fontColor);
      this.phoneEdit.setTextColor(this.fontColor);
      this.mailEdit.setTextColor(this.fontColor);
      this.specText.setTextColor(this.fontColor);
      this.mailText.setTextColor(this.fontColor);
      this.phoneEdit.setBackgroundColor(0);
      this.mailEdit.setBackgroundColor(0);
      this.specText.setText(localIntent.getStringExtra("specRequest"));
      this.dateTimePickerView = dateTimeModifierViewinflate();
      TimePicker localTimePicker = (TimePicker)this.dateTimePickerView.findViewById(R.id.timePicker);
      localTimePicker.setCurrentHour(Integer.valueOf(this.orderInfo.getOrderTime().houres));
      localTimePicker.setCurrentMinute(Integer.valueOf(this.orderInfo.getOrderTime().minutes));
      String str1;
      if (convertTimeToFormat(this.orderInfo.getOrderTime().houres, this.orderInfo.getOrderTime().minutes, false).contains("AM"))
        str1 = "AM";
      try
      {
        while (true)
        {
          localButton = (Button)((ViewGroup)localTimePicker.getChildAt(0)).getChildAt(2);
          if (localButton != null)
            localButton.setText(str1);
          ((DatePicker)this.dateTimePickerView.findViewById(R.id.datePicker)).init(1900 + localDate.getYear(), localDate.getMonth(), localDate.getDate(), null);
          String str2 = new SimpleDateFormat("E").format(this.orderInfo.getOrderDate()) + ", " + new SimpleDateFormat("MMMMMMM").format(this.orderInfo.getOrderDate()) + " " + new SimpleDateFormat("d").format(this.orderInfo.getOrderDate()) + ", ";
          this.orderDateText.setText(str2);
          HouresMinutes localHouresMinutes = this.orderInfo.getOrderTime();
          String str3 = convertTimeToFormat(localHouresMinutes.houres, localHouresMinutes.minutes, false);
          this.orderTimeText.setText(str3);
          this.timeDateModify.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              ViewGroup localViewGroup = (ViewGroup)TableReservationModify.this.dateTimePickerView.getParent();
              if (localViewGroup != null)
                localViewGroup.removeAllViews();
              TimePicker localTimePicker = (TimePicker)TableReservationModify.this.dateTimePickerView.findViewById(R.id.timePicker);
              localTimePicker.setCurrentHour(Integer.valueOf(TableReservationModify.this.orderInfo.getOrderTime().houres));
              localTimePicker.setCurrentMinute(Integer.valueOf(TableReservationModify.this.orderInfo.getOrderTime().minutes));
              String str;
              if (TableReservationModify.this.convertTimeToFormat(TableReservationModify.this.orderInfo.getOrderTime().houres, TableReservationModify.this.orderInfo.getOrderTime().minutes, false).contains("AM"))
                str = "AM";
              try
              {
                while (true)
                {
                  localButton = (Button)((ViewGroup)localTimePicker.getChildAt(0)).getChildAt(2);
                  if (localButton != null)
                    localButton.setText(str);
                  ((DatePicker)TableReservationModify.this.dateTimePickerView.findViewById(R.id.datePicker)).init(1900 + TableReservationModify.this.orderInfo.getOrderDate().getYear(), TableReservationModify.this.orderInfo.getOrderDate().getMonth(), TableReservationModify.this.orderInfo.getOrderDate().getDate(), new DatePicker.OnDateChangedListener()
                  {
                    public void onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
                    {
                    }
                  });
                  TableReservationModify.this.builder.setView(TableReservationModify.this.dateTimePickerView);
                  TableReservationModify.access$802(TableReservationModify.this, TableReservationModify.this.builder.create());
                  TableReservationModify.this.mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
                  {
                    public void onCancel(DialogInterface paramDialogInterface)
                    {
                      if (TableReservationModify.this.mAlertDialog != null)
                        TableReservationModify.this.mAlertDialog.dismiss();
                    }
                  });
                  TableReservationModify.this.mAlertDialog.show();
                  return;
                  str = "PM";
                }
              }
              catch (Exception localException)
              {
                while (true)
                  Button localButton = null;
              }
            }
          });
          this.personModify.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              ViewGroup localViewGroup = (ViewGroup)TableReservationModify.this.personPickerView.getParent();
              if (localViewGroup != null)
                localViewGroup.removeAllViews();
              TableReservationModify.this.builder.setView(TableReservationModify.this.personPickerView);
              TableReservationModify.access$802(TableReservationModify.this, TableReservationModify.this.builder.create());
              TableReservationModify.this.mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
              {
                public void onCancel(DialogInterface paramDialogInterface)
                {
                  if (TableReservationModify.this.mAlertDialog != null)
                    TableReservationModify.this.mAlertDialog.dismiss();
                }
              });
              TableReservationModify.this.mAlertDialog.show();
            }
          });
          if (this.orderInfo.getPhoneNumber() == null)
            break label1404;
          if (this.orderInfo.getPhoneNumber() == "")
            break label1392;
          this.phoneEdit.setText(this.orderInfo.getPhoneNumber());
          this.phoneEdit.setOnFocusChangeListener(this);
          this.phoneEdit.addTextChangedListener(new TableReservationPhoneNumberFormattingTextWatcher());
          if ((this.orderInfo.getCustomerEmail() != null) && (this.orderInfo.getCustomerEmail().length() != 0))
            this.mailEdit.setText(this.orderInfo.getCustomerEmail());
          this.mailEdit.setOnFocusChangeListener(this);
          this.doneButton = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_done_button));
          this.doneButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              Date localDate = TableReservationModify.this.orderInfo.getOrderDate();
              localDate.setHours(0);
              localDate.setMinutes(0);
              localDate.setSeconds(0);
              localDate.setHours(TableReservationModify.this.orderInfo.getOrderTime().houres);
              localDate.setMinutes(TableReservationModify.this.orderInfo.getOrderTime().minutes);
              long l1 = localDate.getTime();
              if (l1 < new Date().getTime())
              {
                Toast localToast2 = Toast.makeText(TableReservationModify.this, R.string.tablereservation_warning_too_late, 1);
                localToast2.setGravity(81, 0, 95);
                localToast2.show();
                return;
              }
              localDate.setHours(0);
              localDate.setMinutes(0);
              localDate.setSeconds(0);
              localDate.setHours(TableReservationModify.this.orderInfo.getStartTime().houres);
              localDate.setMinutes(TableReservationModify.this.orderInfo.getStartTime().minutes);
              long l2 = localDate.getTime();
              localDate.setHours(0);
              localDate.setMinutes(0);
              localDate.setSeconds(0);
              localDate.setHours(TableReservationModify.this.orderInfo.getEndTime().houres);
              localDate.setMinutes(TableReservationModify.this.orderInfo.getEndTime().minutes);
              long l3 = localDate.getTime();
              if (l2 > l3)
                l3 += 86400000L;
              if ((l1 >= l2) && (l1 <= l3))
              {
                TableReservationModify.this.closeActivityOk();
                return;
              }
              Toast localToast1 = Toast.makeText(TableReservationModify.this, R.string.tablereservation_warning_wrong_time, 1);
              localToast1.setGravity(81, 0, 95);
              localToast1.show();
            }
          });
          return;
          this.dateImg.setImageResource(R.drawable.sergeyb_tablereservation_time_img_dark);
          this.personImg.setImageResource(R.drawable.sergeyb_tablereservation_persons_img_dark);
          this.phoneImg.setImageResource(R.drawable.sergeyb_tablereservation_phone_dark);
          this.mailImg.setImageResource(R.drawable.sergeyb_tablereservation_email_dark);
          this.timeDateModify.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha);
          this.personModify.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha);
          this.phoneEditLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha);
          this.mailEditLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha);
          this.specTextLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha);
          break;
          str1 = "PM";
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          Button localButton = null;
          continue;
          label1392: this.phoneEdit.setText("--- --- ----");
          continue;
          label1404: this.phoneEdit.setText("--- --- ----");
        }
      }
    }
  }

  public void onBackPressed()
  {
    closeActivityBad();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.hardKeyboardHidden == 1)
      Toast.makeText(this, "keyboard visible", 1).show();
    do
      return;
    while (paramConfiguration.hardKeyboardHidden != 2);
    Toast.makeText(this, "keyboard hidden", 1).show();
  }

  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramView.getId() == R.id.sergeyb_tablereservation_phone_edittext)
    {
      if (!paramBoolean)
        break label42;
      if (((EditText)paramView).getText().toString().equals("--- --- ----"))
        ((EditText)paramView).setText("");
    }
    label42: 
    do
      return;
    while ((paramBoolean) || (!((EditText)paramView).getText().toString().trim().equals("")));
    ((EditText)paramView).setText("--- --- ----");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationModify
 * JD-Core Version:    0.6.0
 */