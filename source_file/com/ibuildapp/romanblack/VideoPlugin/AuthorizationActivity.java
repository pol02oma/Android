package com.ibuildapp.romanblack.VideoPlugin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.authorization.Authorization;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorizationActivity extends AppBuilderModuleMain
  implements View.OnClickListener, View.OnFocusChangeListener, TextWatcher
{
  private final int CLOSE_ACTIVITY_BAD = 1;
  private final int CLOSE_ACTIVITY_OK = 0;
  private String DEFAULT_EMAIL_TEXT = "";
  private String DEFAULT_PASSWORD_TEXT = "";
  private final int EMAIL_AUTHORIZATION_REQUEST_CODE = 10002;
  private final int EMAIL_SIGNUP_REQUEST_CODE = 10003;
  private final int FACEBOOK_AUTHORIZATION_REQUEST_CODE = 10000;
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private final int SIGNUP_FAILED_MESSAGE = 4;
  private final int TWITTER_AUTHORIZATION_REQUEST_CODE = 10001;
  private LinearLayout btnEmailAuth = null;
  private TextView btnEmailAuthTextView = null;
  private LinearLayout btnFacebookAuth = null;
  private TextView btnSignUp = null;
  private LinearLayout btnTwitterAuth = null;
  private EditText emailEditText = null;
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
        AuthorizationActivity.this.closeActivityOk();
        return;
      case 1:
        AuthorizationActivity.this.closeActivityBad();
        return;
      case 2:
        AuthorizationActivity.this.showProgressDialog();
        return;
      case 3:
        AuthorizationActivity.this.hideProgressDialog();
        return;
      case 4:
      }
      Toast.makeText(AuthorizationActivity.this, AuthorizationActivity.this.getResources().getString(R.string.romanblack_video_signup_failed), 1).show();
    }
  };
  private boolean needCheckFields = false;
  private EditText passwordEditText = null;
  private ProgressDialog progressDialog = null;
  private boolean signUpActive = false;

  private void checkFields()
  {
    if (!this.needCheckFields)
      return;
    if ((this.emailEditText.getText().toString().length() > 0) && (this.passwordEditText.getText().toString().length() > 0))
    {
      if (Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", 2).matcher(this.emailEditText.getText().toString()).matches())
      {
        if (this.passwordEditText.getText().toString().length() >= 4)
        {
          this.signUpActive = true;
          return;
        }
      }
      else
      {
        this.signUpActive = false;
        return;
      }
      this.signUpActive = false;
      return;
    }
    this.signUpActive = false;
  }

  private void closeActivityBad()
  {
    finish();
  }

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

  private void showProgressDialog()
  {
    if (this.progressDialog == null)
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_loading));
    do
      return;
    while (this.progressDialog.isShowing());
    this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_loading));
  }

  public void afterTextChanged(Editable paramEditable)
  {
    checkFields();
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void create()
  {
    setContentView(R.layout.romanblack_video_authorization_main);
    this.DEFAULT_EMAIL_TEXT = getString(R.string.romanblack_video_email);
    this.DEFAULT_PASSWORD_TEXT = getString(R.string.romanblack_video_password);
    this.btnFacebookAuth = ((LinearLayout)findViewById(R.id.romanblack_video_login_facebookbtn));
    this.btnFacebookAuth.setOnClickListener(this);
    this.btnTwitterAuth = ((LinearLayout)findViewById(R.id.romanblack_video_login_twitterbtn));
    this.btnTwitterAuth.setOnClickListener(this);
    this.btnEmailAuth = ((LinearLayout)findViewById(R.id.romanblack_video_login_emailbtn));
    this.btnEmailAuth.setOnClickListener(this);
    this.btnEmailAuthTextView = ((TextView)findViewById(R.id.romanblack_video_login_emailbtn_text));
    this.btnEmailAuthTextView.setText(Html.fromHtml("<u>" + getString(R.string.romanblack_video_have_an_account) + "</u>"));
    this.btnSignUp = ((TextView)findViewById(R.id.romanblack_video_login_signuplabel));
    this.btnSignUp.setOnClickListener(this);
    this.emailEditText = ((EditText)findViewById(R.id.romanblack_video_login_email));
    this.emailEditText.addTextChangedListener(this);
    this.passwordEditText = ((EditText)findViewById(R.id.romanblack_video_login_password));
    this.passwordEditText.addTextChangedListener(this);
    setTopBarTitle(getString(R.string.romanblack_video_login));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        AuthorizationActivity.this.finish();
      }
    });
    if (!com.appbuilder.sdk.android.Statics.BASE_DOMEN.contains("ibuildapp.com"));
    this.needCheckFields = true;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
      Statics.onAuth();
    if (paramInt1 == 10000)
      if (paramInt2 == -1)
      {
        setResult(-1, paramIntent);
        finish();
      }
    label37: 
    do
    {
      do
        while (true)
        {
          break label37;
          break label37;
          do
            return;
          while (paramInt2 != 0);
          Toast.makeText(this, getString(R.string.romanblack_video_alert_facebook_authorization_failed), 1).show();
          return;
          if (paramInt1 == 10001)
          {
            if (paramInt2 == -1)
            {
              setResult(-1, paramIntent);
              finish();
              return;
            }
            if (paramInt2 != 0)
              continue;
            Toast.makeText(this, getString(R.string.romanblack_video_alert_twitter_authorization_failed), 1).show();
            return;
          }
          if (paramInt1 != 10002)
            break;
          if (paramInt2 != -1)
            continue;
          setResult(-1, paramIntent);
          finish();
          return;
        }
      while (paramInt1 != 10003);
      if (paramInt2 != -1)
        continue;
      setResult(-1, paramIntent);
      finish();
      return;
    }
    while (paramInt2 != 0);
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.romanblack_video_login_facebookbtn)
      Authorization.authorize(this, 10000, 1);
    do
      while (true)
      {
        return;
        if (paramView.getId() == R.id.romanblack_video_login_twitterbtn)
        {
          Authorization.authorize(this, 10001, 2);
          return;
        }
        if (paramView.getId() == R.id.romanblack_video_login_emailbtn)
        {
          startActivityForResult(new Intent(this, EMailAuthorizationActivity.class), 10002);
          return;
        }
        if (paramView.getId() != R.id.romanblack_video_login_signuplabel)
          break;
        if (!this.signUpActive)
          continue;
        this.handler.sendEmptyMessage(2);
        new Thread(new Runnable()
        {
          public void run()
          {
            if (Authorization.registerEmail("", "", AuthorizationActivity.this.emailEditText.getText().toString(), AuthorizationActivity.this.passwordEditText.getText().toString(), AuthorizationActivity.this.passwordEditText.getText().toString()))
            {
              AuthorizationActivity.this.handler.sendEmptyMessage(3);
              AuthorizationActivity.this.handler.sendEmptyMessage(0);
              return;
            }
            AuthorizationActivity.this.handler.sendEmptyMessage(3);
            AuthorizationActivity.this.handler.sendEmptyMessage(4);
          }
        }).start();
        return;
      }
    while (paramView.getId() != R.id.romanblack_fanwall_main_home);
    finish();
  }

  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramView.getId() == R.id.romanblack_video_emailsignup_email)
      if (paramBoolean)
        if (((TextView)paramView).getText().toString().equals(this.DEFAULT_EMAIL_TEXT))
          ((TextView)paramView).setText("");
    label45: 
    do
      while (true)
      {
        break label45;
        do
          return;
        while (!((TextView)paramView).getText().toString().equals(""));
        ((TextView)paramView).setText(this.DEFAULT_EMAIL_TEXT);
        return;
        if (paramView.getId() != R.id.romanblack_video_emailsignup_pwd)
          continue;
        if (!paramBoolean)
          break;
        if (!((TextView)paramView).getText().toString().equals(this.DEFAULT_PASSWORD_TEXT))
          continue;
        ((TextView)paramView).setText("");
        return;
      }
    while (!((TextView)paramView).getText().toString().equals(""));
    ((TextView)paramView).setText(this.DEFAULT_PASSWORD_TEXT);
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void resume()
  {
    super.resume();
    getWindow().setSoftInputMode(3);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.AuthorizationActivity
 * JD-Core Version:    0.6.0
 */