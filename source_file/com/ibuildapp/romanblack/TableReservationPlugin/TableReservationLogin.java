package com.ibuildapp.romanblack.TableReservationPlugin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.FanWallUser;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationHTTP;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableReservationLogin extends AppBuilderModuleMain
{
  private final int CLOSE_ACTIVITY_OK = 0;
  private final int DRAW_MARGINS = 7;
  private final int EMAIL_NOT_MATCHES = 6;
  private final int FACEBOOK_AUTHORIZATION_ACTIVITY = 10001;
  private final int FAILED_TO_LOGIN = 3;
  private final int HIDE_PROGRESS_DIALOG = 5;
  private final int IBUILD_AUTHORIZATION_ACTIVITY = 10002;
  private final int NEED_INTERNET_CONNECTION = 2;
  private final int SHOW_ERROR = 8;
  private final int SHOW_PROGRESS_DIALOG = 4;
  private final int TWITTER_AUTHORIZATION_ACTIVITY = 10000;
  private RelativeLayout asAGuest;
  private int backColor;
  private boolean colorSchema;
  private LinearLayout emailLayout;
  private EditText emailText;
  private TextView emailTextView;
  private LinearLayout facebook;
  private int fontColor;
  private User fwUser = new User();
  private LinearLayout guestHolder;
  private int guestHolderH = -1;
  private TextView guestText;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      case 1:
      default:
        return;
      case 0:
        TableReservationLogin.this.closeActivityLoginIbuildapp();
        return;
      case 4:
        TableReservationLogin.this.showProgressDialog();
        return;
      case 5:
        TableReservationLogin.this.hideProgressDialog();
        return;
      case 6:
        Toast.makeText(TableReservationLogin.this, R.string.alert_invalid_email, 1).show();
        return;
      case 3:
        TableReservationLogin.this.showFailedLogin();
        return;
      case 7:
        TableReservationLogin.this.setMargins();
        return;
      case 2:
        Toast localToast = Toast.makeText(TableReservationLogin.this, R.string.alert_no_internet, 1);
        localToast.setGravity(81, 0, 95);
        localToast.show();
        return;
      case 8:
      }
      Toast.makeText(TableReservationLogin.this, (String)paramMessage.obj, 1).show();
    }
  };
  private LinearLayout hideKeyboard;
  private Thread ibuildappAuthThread;
  private LinearLayout ibuildappHolder;
  private int ibuildappHolderH = -1;
  private RelativeLayout ibuildappLogin;
  private FrameLayout mainLayout;
  private LinearLayout passLayout;
  private EditText passText;
  private TextView passTextView;
  private ProgressDialog progressDialog = null;
  private String redirectTo;
  private LinearLayout scrollHolder;
  private int scrollHolderH = -1;
  private ScrollView scrollView;
  private int scrollViewH = -1;
  private RelativeLayout signIn;
  private LinearLayout taskBar;
  private FanWallUser tmpUser;
  private LinearLayout twitter;
  private LinearLayout twitterFbHolder;
  private int twitterFbHolderH = -1;

  private void closeActivityLoginIbuildapp()
  {
    Intent localIntent = new Intent();
    this.fwUser.setUserName(this.tmpUser.getUserName());
    this.fwUser.setUserEmail(this.emailText.getText().toString());
    this.fwUser.setAccountId(this.tmpUser.getAccountId());
    this.fwUser.setUserFirstName(this.tmpUser.getUserFirstName());
    this.fwUser.setUserLastName(this.tmpUser.getUserLastName());
    this.fwUser.setAccountType("ibuildapp");
    localIntent.putExtra("user", this.fwUser);
    localIntent.putExtra("redirectTo", this.redirectTo);
    setResult(-1, localIntent);
    finish();
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void loginIbuildapp()
  {
    try
    {
      this.tmpUser = JSONParser.parseLoginRequestString(TableReservationHTTP.loginPost(this.emailText.getText().toString(), this.passText.getText().toString()));
      if (this.tmpUser == null)
      {
        this.handler.sendEmptyMessage(3);
        this.handler.sendEmptyMessage(5);
        return;
      }
      this.handler.sendEmptyMessage(5);
      this.handler.sendEmptyMessage(0);
      return;
    }
    catch (Exception localException)
    {
      Log.d("", "");
    }
  }

  private void setMargins()
  {
    int i = this.scrollViewH - this.twitterFbHolderH - this.ibuildappHolderH - this.guestHolderH;
    if (i > 15)
      i -= 10;
    int j = i / 2;
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    localLayoutParams.setMargins(0, j, 0, 0);
    this.ibuildappHolder.setLayoutParams(localLayoutParams);
    this.guestHolder.setLayoutParams(localLayoutParams);
  }

  private void showFailedLogin()
  {
    Toast localToast = Toast.makeText(this, R.string.alert_not_logged_in, 0);
    localToast.setGravity(81, 0, 95);
    localToast.show();
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
            if ((TableReservationLogin.this.ibuildappAuthThread != null) && (TableReservationLogin.this.ibuildappAuthThread.isAlive()))
              TableReservationLogin.this.ibuildappAuthThread.interrupt();
            TableReservationLogin.this.progressDialog.dismiss();
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
    setContentView(R.layout.sergeyb_tablereservation_login);
    setTopBarTitle(getResources().getString(R.string.tablereservation_login));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TableReservationLogin.this.finish();
      }
    });
    this.mainLayout = ((FrameLayout)findViewById(R.id.sergeyb_tablereservation_main_layout));
    this.scrollHolder = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_scroll_holder));
    this.twitterFbHolder = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_twitter_fb_holder));
    this.ibuildappHolder = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_ibuildapp_holder));
    this.guestHolder = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_guest_holder));
    this.scrollView = ((ScrollView)findViewById(R.id.sergeyb_tablereservation_scrollView));
    this.hideKeyboard = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_hide_keyboard));
    this.taskBar = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_bottom_taskbar));
    this.emailText = ((EditText)findViewById(R.id.sergeyb_tablereservation_ibuildapp_email_editText));
    this.passText = ((EditText)findViewById(R.id.sergeyb_tablereservation_ibuildapp_pass_editText));
    this.asAGuest = ((RelativeLayout)findViewById(R.id.sergeyb_tablereservation_guest));
    this.emailLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_ibuildapp_email));
    this.passLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_ibuildapp_password));
    this.twitterFbHolder.requestFocus();
    getWindow().setSoftInputMode(3);
    this.mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        Rect localRect = new Rect();
        TableReservationLogin.this.mainLayout.getWindowVisibleDisplayFrame(localRect);
        if (TableReservationLogin.this.mainLayout.getRootView().getHeight() - (localRect.bottom - localRect.top) > 100)
        {
          TableReservationLogin.this.taskBar.setVisibility(0);
          return;
        }
        TableReservationLogin.this.taskBar.setVisibility(4);
      }
    });
    this.hideKeyboard.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ((InputMethodManager)TableReservationLogin.this.getSystemService("input_method")).hideSoftInputFromWindow(TableReservationLogin.this.emailText.getWindowToken(), 0);
      }
    });
    this.scrollHolder.post(new Runnable()
    {
      public void run()
      {
        TableReservationLogin.access$802(TableReservationLogin.this, TableReservationLogin.this.scrollHolder.getHeight());
      }
    });
    this.twitterFbHolder.post(new Runnable()
    {
      public void run()
      {
        TableReservationLogin.access$1002(TableReservationLogin.this, TableReservationLogin.this.twitterFbHolder.getHeight());
      }
    });
    this.ibuildappHolder.post(new Runnable()
    {
      public void run()
      {
        TableReservationLogin.access$1202(TableReservationLogin.this, TableReservationLogin.this.ibuildappHolder.getHeight());
      }
    });
    this.guestHolder.post(new Runnable()
    {
      public void run()
      {
        TableReservationLogin.access$1402(TableReservationLogin.this, TableReservationLogin.this.guestHolder.getHeight());
      }
    });
    this.scrollView.post(new Runnable()
    {
      public void run()
      {
        TableReservationLogin.access$1602(TableReservationLogin.this, TableReservationLogin.this.scrollView.getHeight());
      }
    });
    new Thread(new Runnable()
    {
      public void run()
      {
        while ((TableReservationLogin.this.scrollHolderH == -1) || (TableReservationLogin.this.twitterFbHolderH == -1) || (TableReservationLogin.this.ibuildappHolderH == -1) || (TableReservationLogin.this.guestHolderH == -1) || (TableReservationLogin.this.scrollViewH == -1));
        TableReservationLogin.this.handler.sendEmptyMessage(7);
      }
    }).start();
    Intent localIntent = getIntent();
    this.redirectTo = localIntent.getStringExtra("redirectTo");
    this.fontColor = localIntent.getIntExtra("fontColor", -1);
    this.backColor = localIntent.getIntExtra("backColor", Color.parseColor("#37393b"));
    this.colorSchema = localIntent.getBooleanExtra("colorSchema", true);
    if (this.colorSchema)
    {
      this.emailLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_first_dark);
      this.passLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_last_dark);
      this.asAGuest.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha_dark);
    }
    while (true)
    {
      this.mainLayout = ((FrameLayout)findViewById(R.id.sergeyb_tablereservation_main_layout));
      this.mainLayout.setBackgroundColor(this.backColor);
      this.emailTextView = ((TextView)findViewById(R.id.sergeyb_tablereservation_email_textview));
      this.emailTextView.setTextColor(this.fontColor);
      this.passTextView = ((TextView)findViewById(R.id.sergeyb_tablereservation_pass_textview));
      this.passTextView.setTextColor(this.fontColor);
      this.emailText.setTextColor(this.fontColor);
      this.emailText.setBackgroundColor(0);
      this.passText.setTextColor(this.fontColor);
      this.passText.setBackgroundColor(0);
      this.guestText = ((TextView)findViewById(R.id.sergeyb_tablereservation_guest_text));
      this.guestText.setTextColor(this.fontColor);
      this.twitter = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_twitter_login));
      this.twitter.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          NetworkInfo localNetworkInfo = ((ConnectivityManager)TableReservationLogin.this.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
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
            TableReservationLogin.this.handler.sendEmptyMessage(2);
            return;
          }
          Authorization.authorize(TableReservationLogin.this, 10000, 2);
        }
      });
      this.facebook = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_facebook_login));
      this.facebook.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          NetworkInfo localNetworkInfo = ((ConnectivityManager)TableReservationLogin.this.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
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
            TableReservationLogin.this.handler.sendEmptyMessage(2);
            return;
          }
          Authorization.authorize(TableReservationLogin.this, 10001, 1);
        }
      });
      this.ibuildappLogin = ((RelativeLayout)findViewById(R.id.sergeyb_tablereservation_ibuildapp_login));
      this.ibuildappLogin.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          NetworkInfo localNetworkInfo = ((ConnectivityManager)TableReservationLogin.this.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
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
            TableReservationLogin.this.handler.sendEmptyMessage(2);
            return;
          }
          if (!Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", 2).matcher(TableReservationLogin.this.emailText.getText().toString()).matches())
          {
            TableReservationLogin.this.handler.sendEmptyMessage(6);
            return;
          }
          TableReservationLogin.this.handler.sendEmptyMessage(4);
          TableReservationLogin.access$1902(TableReservationLogin.this, new Thread(new Runnable()
          {
            public void run()
            {
              TableReservationLogin.this.loginIbuildapp();
            }
          }));
          TableReservationLogin.this.ibuildappAuthThread.start();
        }
      });
      this.signIn = ((RelativeLayout)findViewById(R.id.sergeyb_tablereservation_ibuildapp_signin));
      this.signIn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          NetworkInfo localNetworkInfo = ((ConnectivityManager)TableReservationLogin.this.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
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
            TableReservationLogin.this.handler.sendEmptyMessage(2);
            return;
          }
          Intent localIntent = new Intent(TableReservationLogin.this, TableReservationEMailSignUpActivity.class);
          try
          {
            TableReservationLogin.this.startActivityForResult(localIntent, 10002);
            return;
          }
          catch (Exception localException)
          {
            Log.d("", "");
          }
        }
      });
      this.asAGuest.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Intent localIntent = new Intent();
          TableReservationLogin.this.fwUser.setUserName("guest");
          TableReservationLogin.this.fwUser.setAccountId(UUID.randomUUID().toString());
          TableReservationLogin.this.fwUser.setAccountType("guest");
          TableReservationLogin.this.fwUser.setUserEmail("");
          localIntent.putExtra("user", TableReservationLogin.this.fwUser);
          localIntent.putExtra("redirectTo", TableReservationLogin.this.redirectTo);
          TableReservationLogin.this.setResult(-1, localIntent);
          TableReservationLogin.this.finish();
        }
      });
      return;
      this.emailLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_first);
      this.passLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_row_last);
      this.asAGuest.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha);
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
    case 10002:
    }
    do
      while (true)
      {
        return;
        if (paramInt2 == -1)
        {
          paramIntent.putExtra("redirectTo", this.redirectTo);
          paramIntent.putExtra("user", Authorization.getAuthorizedUser(1));
          setResult(-1, paramIntent);
          finish();
          return;
        }
        if (paramInt2 != 0)
          continue;
        Toast localToast3 = Toast.makeText(this, R.string.alert_facebook_auth_error, 1);
        localToast3.setGravity(81, 0, 95);
        localToast3.show();
        return;
        if (paramInt2 == -1)
        {
          paramIntent.putExtra("redirectTo", this.redirectTo);
          paramIntent.putExtra("user", Authorization.getAuthorizedUser(2));
          setResult(-1, paramIntent);
          finish();
          return;
        }
        if (paramInt2 != 0)
          continue;
        Toast localToast2 = Toast.makeText(this, R.string.alert_twitter_auth_error, 1);
        localToast2.setGravity(81, 0, 95);
        localToast2.show();
        return;
        if (paramInt2 != -1)
          break;
        if (paramIntent == null)
          continue;
        paramIntent.putExtra("redirectTo", this.redirectTo);
        setResult(-1, paramIntent);
        finish();
        return;
      }
    while (paramInt2 != 0);
    Toast localToast1 = Toast.makeText(this, R.string.alert_registration_error, 1);
    localToast1.setGravity(81, 0, 95);
    localToast1.show();
  }

  public void onBackPressed()
  {
    Intent localIntent = new Intent();
    this.fwUser.setUserName("noname");
    this.fwUser.setAccountId("noname");
    this.fwUser.setAccountType("noname");
    localIntent.putExtra("user", this.fwUser);
    setResult(0, localIntent);
    finish();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationLogin
 * JD-Core Version:    0.6.0
 */