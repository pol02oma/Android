package com.appbuilder.u846253p1176378.LoginScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.appbuilder.sdk.android.LoginScreen;
import com.appbuilder.u846253p1176378.LoginScreen.service.LoginScreenService;
import com.appbuilder.u846253p1176378.LoginScreen.service.OnDone;

public class RegisterEmailAccountActivity extends Activity
  implements View.OnClickListener
{
  LoginScreen loginScreen;
  EditText passwordText;
  Button signupButton;
  EditText usernameText;

  private TextWatcher getWatcher()
  {
    return new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
        RegisterEmailAccountActivity.this.signupButton.setEnabled(RegisterEmailAccountActivity.this.validate());
      }
    };
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131361835)
    {
      String str1 = this.passwordText.getText().toString();
      String str2 = this.usernameText.getText().toString();
      LoginScreenService.doCreateAccount(this.loginScreen.getSignupEndpoint(), str2, str1, this.loginScreen.getAppId(), new OnDone()
      {
        public void onDone(int paramInt)
        {
          RegisterEmailAccountActivity.this.runOnUiThread(new Runnable(paramInt)
          {
            public void run()
            {
              if (this.val$result == 200)
              {
                Toast.makeText(RegisterEmailAccountActivity.this, "Registration Successful", 1).show();
                RegisterEmailAccountActivity.this.finish();
                return;
              }
              Toast.makeText(RegisterEmailAccountActivity.this, "Registration Failed", 1).show();
            }
          });
        }
      });
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903055);
    this.loginScreen = ((LoginScreen)getIntent().getSerializableExtra("loginScreen"));
    this.usernameText = ((EditText)findViewById(2131361863));
    this.passwordText = ((EditText)findViewById(2131361864));
    this.signupButton = ((Button)findViewById(2131361865));
    this.signupButton.setOnClickListener(this);
    this.usernameText.addTextChangedListener(getWatcher());
    this.passwordText.addTextChangedListener(getWatcher());
    this.signupButton.setEnabled(validate());
  }

  public boolean validate()
  {
    try
    {
      if ((this.usernameText.getText().length() > 0) && (this.passwordText.getText().length() > 0));
      for (boolean bool = true; ; bool = false)
      {
        Boolean localBoolean2 = Boolean.valueOf(bool);
        localBoolean1 = localBoolean2;
        return localBoolean1.booleanValue();
      }
    }
    catch (Exception localException)
    {
      while (true)
        Boolean localBoolean1 = Boolean.valueOf(false);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.LoginScreen.RegisterEmailAccountActivity
 * JD-Core Version:    0.6.0
 */