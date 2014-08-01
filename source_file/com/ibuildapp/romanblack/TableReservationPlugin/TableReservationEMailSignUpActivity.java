package com.ibuildapp.romanblack.TableReservationPlugin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Statics;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.FanWallUser;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationHTTP;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class TableReservationEMailSignUpActivity extends AppBuilderModuleMain
  implements View.OnClickListener, TextWatcher
{
  private final int CHECK_ACTIVE_SIGN_IN = 4;
  private final int CLOSE_ACTIVITY_BAD = 1;
  private final int CLOSE_ACTIVITY_OK = 0;
  private final int EMEIL_IN_USE = 5;
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private EditText emailEditText = null;
  private EditText firstNameEditText = null;
  private FanWallUser fwUser = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      case 4:
      default:
        return;
      case 0:
        TableReservationEMailSignUpActivity.this.closeActivityOk();
        return;
      case 1:
        TableReservationEMailSignUpActivity.this.closeActivityBad();
        return;
      case 2:
        TableReservationEMailSignUpActivity.this.showProgressDialog();
        return;
      case 3:
        TableReservationEMailSignUpActivity.this.hideProgressDialog();
        return;
      case 5:
      }
      Toast.makeText(TableReservationEMailSignUpActivity.this, R.string.alert_registration_email_inuse, 1).show();
    }
  };
  private EditText lastNameEditText = null;
  private boolean needCheckFields = false;
  private EditText passwordEditText = null;
  private ProgressDialog progressDialog = null;
  private EditText rePasswordEditText = null;
  private User resUser = new User();
  private boolean signUpActive = false;
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
    hideProgressDialog();
    finish();
  }

  private void closeActivityOk()
  {
    hideProgressDialog();
    Intent localIntent = new Intent();
    this.resUser.setAccountId(this.fwUser.getAccountId());
    this.resUser.setUserEmail(this.emailEditText.getText().toString());
    this.resUser.setUserName(this.fwUser.getUserName());
    this.resUser.setAccountType("twitter");
    this.resUser.setAvatarUrl(this.fwUser.getAvatarUrl());
    this.resUser.setUserFirstName(this.fwUser.getUserFirstName());
    this.resUser.setUserLastName(this.fwUser.getUserLastName());
    localIntent.putExtra("user", this.resUser);
    setResult(-1, localIntent);
    finish();
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void registration()
  {
    if (this.signUpActive)
    {
      this.handler.sendEmptyMessage(2);
      new Thread(new Runnable()
      {
        public void run()
        {
          BasicHttpParams localBasicHttpParams = new BasicHttpParams();
          localBasicHttpParams.setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
          DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
          try
          {
            HttpPost localHttpPost = new HttpPost(TableReservationHTTP.SIGNUP_URL);
            String str1 = TableReservationEMailSignUpActivity.this.firstNameEditText.getText().toString();
            String str2 = TableReservationEMailSignUpActivity.this.lastNameEditText.getText().toString();
            String str3 = TableReservationEMailSignUpActivity.this.emailEditText.getText().toString();
            String str4 = TableReservationEMailSignUpActivity.this.passwordEditText.getText().toString();
            String str5 = TableReservationEMailSignUpActivity.this.rePasswordEditText.getText().toString();
            MultipartEntity localMultipartEntity = new MultipartEntity();
            localMultipartEntity.addPart("firstname", new StringBody(str1, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("lastname", new StringBody(str2, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("email", new StringBody(str3, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("password", new StringBody(str4, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("password_confirm", new StringBody(str5, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("app_id", new StringBody(Statics.appId, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("token", new StringBody(Statics.appToken, Charset.forName("UTF-8")));
            localHttpPost.setEntity(localMultipartEntity);
            String str6 = (String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler());
            TableReservationEMailSignUpActivity.access$1002(TableReservationEMailSignUpActivity.this, JSONParser.parseLoginRequestString(str6));
            if (TableReservationEMailSignUpActivity.this.fwUser == null)
            {
              TableReservationEMailSignUpActivity.this.handler.sendEmptyMessage(5);
              TableReservationEMailSignUpActivity.this.handler.sendEmptyMessage(3);
              return;
            }
            TableReservationEMailSignUpActivity.this.handler.sendEmptyMessage(3);
            TableReservationEMailSignUpActivity.this.handler.sendEmptyMessage(0);
            return;
          }
          catch (Exception localException)
          {
            TableReservationEMailSignUpActivity.this.handler.sendEmptyMessage(1);
          }
        }
      }).start();
    }
    do
    {
      return;
      if ((this.firstNameEditText.getText().toString().length() == 0) || (this.lastNameEditText.getText().toString().length() == 0) || (this.emailEditText.getText().toString().length() == 0) || (this.passwordEditText.getText().toString().length() == 0) || (this.rePasswordEditText.getText().toString().length() == 0))
      {
        Toast.makeText(this, R.string.alert_registration_fillin_fields, 1).show();
        return;
      }
      if (this.firstNameEditText.getText().toString().equals(this.lastNameEditText.getText().toString()))
      {
        Toast.makeText(this, R.string.alert_registration_spam, 1).show();
        return;
      }
      if ((this.firstNameEditText.getText().toString().length() <= 2) || (this.lastNameEditText.getText().toString().length() <= 2))
      {
        Toast.makeText(this, R.string.alert_registration_two_symbols_name, 1).show();
        return;
      }
      if (Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", 2).matcher(this.emailEditText.getText().toString()).matches())
      {
        if (this.passwordEditText.getText().toString().length() >= 4)
          continue;
        Toast.makeText(this, R.string.alert_registration_two_symbols_password, 1).show();
        return;
      }
      Toast.makeText(this, R.string.alert_registration_correct_email, 1).show();
      return;
    }
    while (this.passwordEditText.getText().toString().equals(this.rePasswordEditText.getText().toString()));
    Toast.makeText(this, "Passwords don't match.", 1).show();
  }

  private void showProgressDialog()
  {
    if (this.progressDialog == null)
      this.progressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.common_registration_upper));
    do
      return;
    while (this.progressDialog.isShowing());
    this.progressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.common_registration_upper));
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
    setContentView(R.layout.sergeyb_tablereservation_emailsignup);
    setTopBarTitle(getResources().getString(R.string.common_sign_up_upper));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TableReservationEMailSignUpActivity.this.setResult(-1, null);
        TableReservationEMailSignUpActivity.this.finish();
      }
    });
    setTopBarRightButtonText(getResources().getString(R.string.common_sign_up_upper), false, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TableReservationEMailSignUpActivity.this.registration();
      }
    });
    this.firstNameEditText = ((EditText)findViewById(R.id.sergeyb_tablereservation_emailsignup_fname));
    this.firstNameEditText.addTextChangedListener(this);
    this.firstNameEditText.clearFocus();
    this.lastNameEditText = ((EditText)findViewById(R.id.sergeyb_tablereservation_emailsignup_lname));
    this.lastNameEditText.addTextChangedListener(this);
    this.firstNameEditText.clearFocus();
    this.emailEditText = ((EditText)findViewById(R.id.sergeyb_tablereservation_emailsignup_email));
    this.emailEditText.addTextChangedListener(this);
    this.firstNameEditText.clearFocus();
    this.passwordEditText = ((EditText)findViewById(R.id.sergeyb_tablereservation_emailsignup_pwd));
    this.passwordEditText.addTextChangedListener(this);
    this.firstNameEditText.clearFocus();
    this.rePasswordEditText = ((EditText)findViewById(R.id.sergeyb_tablereservation_emailsignup_rpwd));
    this.rePasswordEditText.addTextChangedListener(this);
    this.termsCheckBox = ((CheckBox)findViewById(R.id.sergeyb_tablereservation_emailsignup_chbterms));
    this.termsCheckBox.setOnClickListener(this);
    this.termsLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_emailsignup_layouttems));
    this.termsLayout.setVisibility(4);
    getWindow().setSoftInputMode(3);
    this.needCheckFields = true;
  }

  public void onBackPressed()
  {
    setResult(-1, null);
    finish();
  }

  public void onClick(View paramView)
  {
    if (paramView == this.termsCheckBox)
    {
      checkFields();
      this.handler.sendEmptyMessage(4);
    }
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationEMailSignUpActivity
 * JD-Core Version:    0.6.0
 */