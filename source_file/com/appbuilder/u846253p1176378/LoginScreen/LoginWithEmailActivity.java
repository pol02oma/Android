package com.appbuilder.u846253p1176378.LoginScreen;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.BarDesigner.TitleDesign;
import com.appbuilder.sdk.android.LoginScreen;
import com.appbuilder.u846253p1176378.LoginScreen.service.LoginScreenService;
import com.appbuilder.u846253p1176378.LoginScreen.service.LoginSettings;
import com.appbuilder.u846253p1176378.LoginScreen.service.LoginSettingsService;
import com.appbuilder.u846253p1176378.LoginScreen.service.OnDone;

public class LoginWithEmailActivity extends Activity
  implements View.OnClickListener
{
  private static final String SETTINGS_NAME = "LOGIN_SETTINGS";
  private CheckBox checkBox;
  private Button loginButton;
  private LoginScreen loginScreen;
  private BarDesigner navBarDesign = null;
  private EditText passwordText;
  private ProgressDialog progressDialog;
  private TextView recoveryPasswordButton;
  private EditText usernameText;

  private void doLogin()
  {
    String str1 = this.passwordText.getText().toString();
    String str2 = this.usernameText.getText().toString();
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
    {
      startAction();
      LoginScreenService.doLogin(this.loginScreen.getLoginEndpoint(), str2, str1, "email", this.loginScreen.getAppId(), new OnDone(str2, str1)
      {
        public void onDone(int paramInt)
        {
          LoginWithEmailActivity.this.runOnUiThread(new Runnable(paramInt)
          {
            public void run()
            {
              LoginWithEmailActivity.this.stopAction();
              if (this.val$result == 200)
              {
                if (LoginWithEmailActivity.this.checkBox.isChecked())
                  LoginSettingsService.saveSettings(LoginWithEmailActivity.this.getSharedPreferences("LOGIN_SETTINGS", 0), new LoginSettings(LoginWithEmailActivity.2.this.val$username, LoginWithEmailActivity.2.this.val$password));
                while (true)
                {
                  Intent localIntent = new Intent();
                  localIntent.putExtra("username", LoginWithEmailActivity.2.this.val$username);
                  localIntent.putExtra("password", LoginWithEmailActivity.2.this.val$password);
                  LoginWithEmailActivity.this.setResult(-1, localIntent);
                  LoginWithEmailActivity.this.finish();
                  return;
                  try
                  {
                    LoginSettingsService.deleteSettings(LoginWithEmailActivity.this.getSharedPreferences("LOGIN_SETTINGS", 0));
                  }
                  catch (Exception localException)
                  {
                  }
                }
              }
              AlertDialog.Builder localBuilder = new AlertDialog.Builder(LoginWithEmailActivity.this);
              localBuilder.setTitle(LoginWithEmailActivity.this.getString(2131100030));
              localBuilder.setMessage(LoginWithEmailActivity.this.getString(2131100031));
              localBuilder.setPositiveButton("OK", null);
              localBuilder.show();
            }
          });
        }
      });
      return;
    }
    Toast.makeText(this, 2131100028, 1).show();
  }

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
        LoginWithEmailActivity.this.loginButton.setEnabled(LoginWithEmailActivity.this.validate());
      }
    };
  }

  private boolean validate()
  {
    int i = 1;
    try
    {
      if ((this.usernameText.getText().length() > i) && (this.passwordText.getText().length() > 4));
      while (true)
      {
        Boolean localBoolean2 = Boolean.valueOf(i);
        localBoolean1 = localBoolean2;
        return localBoolean1.booleanValue();
        int j = 0;
      }
    }
    catch (Exception localException)
    {
      while (true)
        Boolean localBoolean1 = Boolean.valueOf(false);
    }
  }

  public void onBackPressed()
  {
    com.appbuilder.sdk.android.Statics.closeMain = true;
    super.onBackPressed();
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131361835)
      doLogin();
    do
      return;
    while (paramView.getId() != 2131361836);
    Intent localIntent = new Intent(this, RecoveryPasswordActivity.class);
    localIntent.putExtra("loginScreen", this.loginScreen);
    localIntent.putExtra("navBarDesign", this.navBarDesign);
    startActivity(localIntent);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().requestFeature(1);
    setContentView(2130903047);
    this.loginScreen = ((LoginScreen)getIntent().getSerializableExtra("loginScreen"));
    this.navBarDesign = ((BarDesigner)getIntent().getSerializableExtra("navBarDesign"));
    TextView localTextView = (TextView)findViewById(2131361824);
    localTextView.setTextSize(2, this.navBarDesign.titleDesign.fontSize);
    localTextView.setTextColor(this.navBarDesign.titleDesign.textColor);
    this.passwordText = ((EditText)findViewById(2131361833));
    this.usernameText = ((EditText)findViewById(2131361832));
    this.loginButton = ((Button)findViewById(2131361835));
    this.recoveryPasswordButton = ((TextView)findViewById(2131361836));
    this.usernameText.setOnClickListener(this);
    this.passwordText.setOnClickListener(this);
    this.recoveryPasswordButton.setOnClickListener(this);
    this.passwordText.addTextChangedListener(getWatcher());
    this.usernameText.addTextChangedListener(getWatcher());
    this.loginButton.setOnClickListener(this);
    this.loginButton.setEnabled(validate());
    this.checkBox = ((CheckBox)findViewById(2131361834));
    LoginSettings localLoginSettings = LoginSettingsService.loadSettings(getSharedPreferences("LOGIN_SETTINGS", 0));
    this.usernameText.setText(localLoginSettings.getUsername());
    this.passwordText.setText(localLoginSettings.getPassword());
    if (validate())
      doLogin();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  protected void onResume()
  {
    super.onResume();
    getWindow().setSoftInputMode(3);
  }

  public void startAction()
  {
    if (this.progressDialog == null)
    {
      this.progressDialog = new ProgressDialog(this);
      this.progressDialog.setMessage(getResources().getString(2131100023));
      this.progressDialog.setCancelable(true);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          LoginWithEmailActivity.this.onBackPressed();
        }
      });
    }
    if (!this.progressDialog.isShowing())
      this.progressDialog.show();
  }

  public void stopAction()
  {
    if ((this.progressDialog != null) && (this.progressDialog.isShowing()))
      this.progressDialog.hide();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.LoginScreen.LoginWithEmailActivity
 * JD-Core Version:    0.6.0
 */