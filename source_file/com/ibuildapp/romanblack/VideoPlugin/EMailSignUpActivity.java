package com.ibuildapp.romanblack.VideoPlugin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.authorization.Authorization;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EMailSignUpActivity extends AppBuilderModuleMain
  implements View.OnClickListener, TextWatcher, View.OnFocusChangeListener
{
  private final int CHECK_ACTIVE_SIGN_IN = 4;
  private final int CLOSE_ACTIVITY_BAD = 1;
  private final int CLOSE_ACTIVITY_OK = 0;
  private String DEFAULT_EMAIL_TEXT = "Email";
  private String DEFAULT_FIRST_NAME_TEXT = "First name";
  private String DEFAULT_LAST_NAME_TEXT = "Last name";
  private String DEFAULT_PASSWORD_TEXT = "Password";
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private EditText emailEditText = null;
  private EditText firstNameEditText = null;
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
        EMailSignUpActivity.this.closeActivityOk();
        return;
      case 1:
        EMailSignUpActivity.this.closeActivityBad();
        return;
      case 2:
        EMailSignUpActivity.this.showProgressDialog();
        return;
      case 3:
      }
      EMailSignUpActivity.this.hideProgressDialog();
    }
  };
  private TextView homeImageView = null;
  private EditText lastNameEditText = null;
  private boolean needCheckFields = false;
  private EditText passwordEditText = null;
  private ProgressDialog progressDialog = null;
  private EditText rePasswordEditText = null;
  private boolean signUpActive = false;
  private TextView signUpimageView = null;
  private CheckBox termsCheckBox = null;
  private LinearLayout termsLayout = null;

  private void checkFields()
  {
    if (!this.needCheckFields)
      return;
    if ((this.firstNameEditText.getText().toString().length() > 0) && (this.lastNameEditText.getText().toString().length() > 0) && (this.emailEditText.getText().toString().length() > 0) && (this.passwordEditText.getText().toString().length() > 0) && (this.rePasswordEditText.getText().toString().length() > 0))
    {
      if (Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", 2).matcher(this.emailEditText.getText().toString()).matches())
      {
        if (!this.firstNameEditText.getText().toString().equals(this.lastNameEditText.getText().toString()))
        {
          if (!this.passwordEditText.getText().toString().equals(this.rePasswordEditText.getText().toString()))
            break label200;
          if (this.passwordEditText.getText().toString().length() < 4)
            break label206;
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
      label200: this.signUpActive = false;
      return;
      label206: this.signUpActive = false;
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
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_registration) + "...");
    do
      return;
    while (this.progressDialog.isShowing());
    this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_registration) + "...");
  }

  public void afterTextChanged(Editable paramEditable)
  {
    checkFields();
    this.handler.sendEmptyMessage(4);
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void create()
  {
    requestWindowFeature(1);
    setContentView(R.layout.romanblack_video_emailsignup);
    this.DEFAULT_FIRST_NAME_TEXT = getString(R.string.romanblack_video_first_name);
    this.DEFAULT_LAST_NAME_TEXT = getString(R.string.romanblack_video_last_name);
    this.DEFAULT_EMAIL_TEXT = getString(R.string.romanblack_video_email);
    this.DEFAULT_PASSWORD_TEXT = getString(R.string.romanblack_video_password);
    this.homeImageView = ((TextView)findViewById(R.id.romanblack_fanwall_main_home));
    this.homeImageView.setOnClickListener(this);
    setTopBarTitle(getString(R.string.romanblack_video_registration));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        EMailSignUpActivity.this.finish();
      }
    });
    this.signUpimageView = ((TextView)findViewById(R.id.romanblack_video_login_signuplabel));
    this.signUpimageView.setOnClickListener(this);
    this.firstNameEditText = ((EditText)findViewById(R.id.romanblack_video_emailsignup_fname));
    this.firstNameEditText.addTextChangedListener(this);
    this.firstNameEditText.setOnFocusChangeListener(this);
    this.firstNameEditText.setText(this.DEFAULT_FIRST_NAME_TEXT);
    this.firstNameEditText.setTextColor(-7829368);
    this.lastNameEditText = ((EditText)findViewById(R.id.romanblack_video_emailsignup_lname));
    this.lastNameEditText.addTextChangedListener(this);
    this.lastNameEditText.setOnFocusChangeListener(this);
    this.lastNameEditText.setText(this.DEFAULT_LAST_NAME_TEXT);
    this.lastNameEditText.setTextColor(-7829368);
    this.emailEditText = ((EditText)findViewById(R.id.romanblack_video_emailsignup_email));
    this.emailEditText.addTextChangedListener(this);
    this.emailEditText.setOnFocusChangeListener(this);
    this.emailEditText.setText(this.DEFAULT_EMAIL_TEXT);
    this.emailEditText.setTextColor(-7829368);
    this.passwordEditText = ((EditText)findViewById(R.id.romanblack_video_emailsignup_pwd));
    this.passwordEditText.addTextChangedListener(this);
    this.passwordEditText.setOnFocusChangeListener(this);
    this.passwordEditText.setText(this.DEFAULT_PASSWORD_TEXT);
    this.passwordEditText.setTextColor(-7829368);
    this.rePasswordEditText = ((EditText)findViewById(R.id.romanblack_video_emailsignup_rpwd));
    this.rePasswordEditText.addTextChangedListener(this);
    this.rePasswordEditText.setOnFocusChangeListener(this);
    this.rePasswordEditText.setText(this.DEFAULT_PASSWORD_TEXT);
    this.rePasswordEditText.setTextColor(-7829368);
    this.termsCheckBox = ((CheckBox)findViewById(R.id.romanblack_video_emailsignup_chbterms));
    this.termsCheckBox.setOnClickListener(this);
    this.termsLayout = ((LinearLayout)findViewById(R.id.romanblack_video_emailsignup_layouttems));
    this.termsLayout.setVisibility(4);
    this.needCheckFields = true;
  }

  public void onClick(View paramView)
  {
    if (this.homeImageView == paramView)
      finish();
    do
      while (true)
      {
        return;
        if (this.signUpimageView != paramView)
          break;
        if (this.signUpActive)
        {
          this.handler.sendEmptyMessage(2);
          new Thread(new Runnable()
          {
            public void run()
            {
              if (Authorization.registerEmail(EMailSignUpActivity.this.firstNameEditText.getText().toString(), EMailSignUpActivity.this.lastNameEditText.getText().toString(), EMailSignUpActivity.this.emailEditText.getText().toString(), EMailSignUpActivity.this.passwordEditText.getText().toString(), EMailSignUpActivity.this.rePasswordEditText.getText().toString()))
              {
                EMailSignUpActivity.this.handler.sendEmptyMessage(3);
                EMailSignUpActivity.this.handler.sendEmptyMessage(0);
                return;
              }
              EMailSignUpActivity.this.handler.sendEmptyMessage(1);
            }
          }).start();
          return;
        }
        if ((this.firstNameEditText.getText().toString().length() == 0) || (this.lastNameEditText.getText().toString().length() == 0) || (this.emailEditText.getText().toString().length() == 0) || (this.passwordEditText.getText().toString().length() == 0) || (this.rePasswordEditText.getText().toString().length() == 0))
        {
          Toast.makeText(this, R.string.romanblack_video_alert_fill_all_fields, 1).show();
          return;
        }
        if (this.firstNameEditText.getText().toString().equals(this.lastNameEditText.getText().toString()))
        {
          Toast.makeText(this, R.string.romanblack_video_alert_names_match_each, 1).show();
          return;
        }
        if ((this.firstNameEditText.getText().toString().length() <= 2) || (this.lastNameEditText.getText().toString().length() <= 2))
        {
          Toast.makeText(this, R.string.romanblack_video_alert_short_name, 1).show();
          return;
        }
        if (Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", 2).matcher(this.emailEditText.getText().toString()).matches())
        {
          if (this.passwordEditText.getText().toString().length() < 4)
          {
            Toast.makeText(this, R.string.romanblack_video_alert_short_password, 1).show();
            return;
          }
        }
        else
        {
          Toast.makeText(this, R.string.romanblack_video_alert_enter_correct_email, 1).show();
          return;
        }
        if (this.passwordEditText.getText().toString().equals(this.rePasswordEditText.getText().toString()))
          continue;
        Toast.makeText(this, R.string.romanblack_video_alert_passwords_dont_match, 1).show();
        return;
      }
    while (paramView != this.termsCheckBox);
    checkFields();
    this.handler.sendEmptyMessage(4);
  }

  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramView.getId() == R.id.romanblack_video_emailsignup_fname)
      if (paramBoolean)
        if (((TextView)paramView).getText().toString().equals(this.DEFAULT_FIRST_NAME_TEXT))
        {
          ((TextView)paramView).setText("");
          ((TextView)paramView).setTextColor(-16777216);
        }
    label56: 
    do
      while (true)
      {
        break label56;
        break label56;
        break label56;
        break label56;
        break label56;
        break label56;
        break label56;
        do
          return;
        while (!((TextView)paramView).getText().toString().equals(""));
        ((TextView)paramView).setText(this.DEFAULT_FIRST_NAME_TEXT);
        ((TextView)paramView).setTextColor(-7829368);
        return;
        if (paramView.getId() == R.id.romanblack_video_emailsignup_lname)
        {
          if (paramBoolean)
          {
            if (!((TextView)paramView).getText().toString().equals(this.DEFAULT_LAST_NAME_TEXT))
              continue;
            ((TextView)paramView).setText("");
            ((TextView)paramView).setTextColor(-16777216);
            return;
          }
          if (!((TextView)paramView).getText().toString().equals(""))
            continue;
          ((TextView)paramView).setText(this.DEFAULT_LAST_NAME_TEXT);
          ((TextView)paramView).setTextColor(-7829368);
          return;
        }
        if (paramView.getId() == R.id.romanblack_video_emailsignup_email)
        {
          if (paramBoolean)
          {
            if (!((TextView)paramView).getText().toString().equals(this.DEFAULT_EMAIL_TEXT))
              continue;
            ((TextView)paramView).setText("");
            ((TextView)paramView).setTextColor(-16777216);
            return;
          }
          if (!((TextView)paramView).getText().toString().equals(""))
            continue;
          ((TextView)paramView).setText(this.DEFAULT_EMAIL_TEXT);
          ((TextView)paramView).setTextColor(-7829368);
          return;
        }
        if (paramView.getId() == R.id.romanblack_video_emailsignup_pwd)
        {
          if (paramBoolean)
          {
            if (!((TextView)paramView).getText().toString().equals(this.DEFAULT_PASSWORD_TEXT))
              continue;
            ((TextView)paramView).setText("");
            ((TextView)paramView).setTextColor(-16777216);
            return;
          }
          if (!((TextView)paramView).getText().toString().equals(""))
            continue;
          ((TextView)paramView).setText(this.DEFAULT_PASSWORD_TEXT);
          ((TextView)paramView).setTextColor(-7829368);
          return;
        }
        if (paramView.getId() != R.id.romanblack_video_emailsignup_rpwd)
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

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.EMailSignUpActivity
 * JD-Core Version:    0.6.0
 */