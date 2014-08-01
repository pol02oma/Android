package com.ibuildapp.romanblack.VideoPlugin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.authorization.Authorization;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EMailAuthorizationActivity extends AppBuilderModuleMain
  implements View.OnClickListener, View.OnFocusChangeListener
{
  private final int CLOSE_ACTIVITY_BAD = 1;
  private final int CLOSE_ACTIVITY_OK = 0;
  private String DEFAULT_EMAIL_TEXT = "";
  private String DEFAULT_PASSWORD_TEXT = "";
  private final int EMAIL_NOT_MATCHES = 6;
  private final int FAILED_TO_LOGIN = 3;
  private final int HIDE_PROGRESS_DIALOG = 5;
  private final int NEED_INTERNET_CONNECTION = 2;
  private final int SHOW_PROGRESS_DIALOG = 4;
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
        EMailAuthorizationActivity.this.closeActivityOk();
        return;
      case 2:
        Toast.makeText(EMailAuthorizationActivity.this, EMailAuthorizationActivity.this.getString(R.string.romanblack_video_alert_no_internet), 1).show();
        return;
      case 3:
        Toast.makeText(EMailAuthorizationActivity.this, EMailAuthorizationActivity.this.getString(R.string.romanblack_video_login_failed), 1).show();
        return;
      case 4:
        EMailAuthorizationActivity.this.showProgressDialog();
        return;
      case 5:
        EMailAuthorizationActivity.this.hideProgressDialog();
        return;
      case 6:
      }
      Toast.makeText(EMailAuthorizationActivity.this, EMailAuthorizationActivity.this.getString(R.string.romanblack_video_alert_email_invalid), 1).show();
    }
  };
  private Button loginButton = null;
  private EditText loginEditText = null;
  private EditText passwordEditText = null;
  private ProgressDialog progressDialog = null;

  private void closeActivityOk()
  {
    setResult(-1, new Intent());
    finish();
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void login()
  {
    try
    {
      if (Authorization.authorizeEmail(this.loginEditText.getText().toString(), this.passwordEditText.getText().toString()))
      {
        this.handler.sendEmptyMessage(5);
        this.handler.sendEmptyMessage(0);
        return;
      }
      this.handler.sendEmptyMessage(3);
      this.handler.sendEmptyMessage(5);
      return;
    }
    catch (Exception localException)
    {
      Log.d("", "");
    }
  }

  private void showProgressDialog()
  {
    if (this.progressDialog == null)
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_loading));
    do
      return;
    while (this.progressDialog.isShowing());
    this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_loading));
  }

  public void create()
  {
    setContentView(R.layout.romanblack_video_email_auth);
    this.DEFAULT_EMAIL_TEXT = getString(R.string.romanblack_video_email);
    this.DEFAULT_PASSWORD_TEXT = getString(R.string.romanblack_video_password);
    setTopBarTitle(getString(R.string.romanblack_video_login));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        EMailAuthorizationActivity.this.finish();
      }
    });
    this.loginEditText = ((EditText)findViewById(R.id.romanblack_video_emailauth_fname));
    this.loginEditText.setTextColor(-7829368);
    this.passwordEditText = ((EditText)findViewById(R.id.romanblack_video_emailauth_lname));
    this.passwordEditText.setTextColor(-7829368);
    this.loginButton = ((Button)findViewById(R.id.romanblack_video_emailauth_btn_sugnup));
    this.loginButton.setOnClickListener(this);
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.romanblack_fanwall_main_home)
      finish();
    do
      while (true)
      {
        return;
        if (paramView.getId() == R.id.romanblack_video_emailauth_btn_sugnup)
        {
          if (!Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", 2).matcher(this.loginEditText.getText().toString()).matches())
          {
            this.handler.sendEmptyMessage(6);
            return;
          }
          this.handler.sendEmptyMessage(4);
          new Thread(new Runnable()
          {
            public void run()
            {
              EMailAuthorizationActivity.this.login();
            }
          }).start();
          return;
        }
        if (paramView.getId() != R.id.romanblack_video_emailauth_fname)
          break;
        if (!this.loginEditText.getText().toString().equals(this.DEFAULT_EMAIL_TEXT))
          continue;
        this.loginEditText.setText("");
        this.loginEditText.setTextColor(-16777216);
        return;
      }
    while ((paramView.getId() != R.id.romanblack_video_emailauth_lname) || (!this.passwordEditText.getText().toString().equals(this.DEFAULT_PASSWORD_TEXT)));
    this.passwordEditText.setText("");
    this.passwordEditText.setTextColor(-16777216);
  }

  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramView.getId() == R.id.romanblack_video_emailauth_fname)
      if (paramBoolean)
        if (((TextView)paramView).getText().toString().equals(this.DEFAULT_EMAIL_TEXT))
        {
          ((TextView)paramView).setText("");
          ((TextView)paramView).setTextColor(-16777216);
        }
    label55: 
    do
      while (true)
      {
        break label55;
        do
          return;
        while (!((TextView)paramView).getText().toString().equals(""));
        ((TextView)paramView).setText(this.DEFAULT_EMAIL_TEXT);
        ((TextView)paramView).setTextColor(-7829368);
        return;
        if (paramView.getId() != R.id.romanblack_video_emailauth_lname)
          continue;
        if (!paramBoolean)
          break;
        if (!((TextView)paramView).getText().toString().equals(this.DEFAULT_PASSWORD_TEXT))
          continue;
        ((TextView)paramView).setText("");
        ((TextView)paramView).setTextColor(-16777216);
        return;
      }
    while (!((TextView)paramView).getText().toString().equals(""));
    ((TextView)paramView).setText(this.DEFAULT_PASSWORD_TEXT);
    ((TextView)paramView).setTextColor(-7829368);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.EMailAuthorizationActivity
 * JD-Core Version:    0.6.0
 */