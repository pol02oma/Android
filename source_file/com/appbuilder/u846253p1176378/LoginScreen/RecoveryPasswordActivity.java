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
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.BarDesigner.TitleDesign;
import com.appbuilder.sdk.android.LoginScreen;
import com.appbuilder.u846253p1176378.LoginScreen.service.LoginScreenService;
import com.appbuilder.u846253p1176378.LoginScreen.service.OnDone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecoveryPasswordActivity extends Activity
  implements View.OnClickListener
{
  private final int ARROW_HEIGHT = 25;
  private final int ARROW_WIDTH = 15;
  private ImageView backArrow = null;
  TextView backText;
  EditText emailText;
  LoginScreen loginScreen;
  private BarDesigner navBarDesign = null;
  private ProgressDialog progressDialog = null;
  Button reset;

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
        RecoveryPasswordActivity.this.reset.setEnabled(RecoveryPasswordActivity.this.validate());
      }
    };
  }

  private boolean validate()
  {
    try
    {
      if (this.emailText.getText().length() > 0);
      for (int i = 1; ; i = 0)
      {
        boolean bool = i & Patterns.EMAIL_ADDRESS.matcher(this.emailText.getText().toString()).matches();
        Log.d("", "");
        return bool;
      }
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131361830)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
        if (validate())
        {
          startAction();
          LoginScreenService.doRecovery(this.loginScreen.getRecoveryPasswordEndpoint(), this.emailText.getText().toString(), this.loginScreen.getAppId(), new OnDone()
          {
            public void onDone(int paramInt)
            {
              RecoveryPasswordActivity.this.runOnUiThread(new Runnable(paramInt)
              {
                public void run()
                {
                  RecoveryPasswordActivity.this.stopAction();
                  if (this.val$result == 200)
                  {
                    Toast.makeText(RecoveryPasswordActivity.this, 2131100024, 1).show();
                    RecoveryPasswordActivity.this.finish();
                    return;
                  }
                  AlertDialog.Builder localBuilder = new AlertDialog.Builder(RecoveryPasswordActivity.this);
                  localBuilder.setTitle(2131100025);
                  localBuilder.setMessage(2131100026);
                  localBuilder.setPositiveButton("OK", null);
                  localBuilder.show();
                }
              });
            }
          });
        }
    }
    do
    {
      return;
      Toast.makeText(this, 2131100029, 1).show();
      return;
    }
    while (paramView.getId() != 2131361821);
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().requestFeature(1);
    setContentView(2130903046);
    this.loginScreen = ((LoginScreen)getIntent().getSerializableExtra("loginScreen"));
    this.navBarDesign = ((BarDesigner)getIntent().getSerializableExtra("navBarDesign"));
    TextView localTextView = (TextView)findViewById(2131361824);
    localTextView.setTextSize(2, this.navBarDesign.titleDesign.fontSize);
    localTextView.setTextColor(this.navBarDesign.titleDesign.textColor);
    float f = getResources().getDisplayMetrics().density;
    this.backArrow = ((ImageView)findViewById(2131361822));
    this.backArrow.setImageResource(2130837504);
    this.backArrow.setLayoutParams(new LinearLayout.LayoutParams((int)(15.0F * f), (int)(25.0F * f)));
    this.backArrow.setColorFilter(this.navBarDesign.itemDesign.textColor);
    this.backText = ((TextView)findViewById(2131361823));
    this.backText.setTextColor(this.navBarDesign.itemDesign.textColor);
    this.backText.setTextSize(2, this.navBarDesign.itemDesign.fontSize);
    this.emailText = ((EditText)findViewById(2131361829));
    this.reset = ((Button)findViewById(2131361830));
    this.reset.setOnClickListener(this);
    findViewById(2131361821).setOnClickListener(this);
    this.emailText.addTextChangedListener(getWatcher());
    this.reset.setEnabled(false);
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
      this.progressDialog.setMessage(getResources().getString(2131100027));
      this.progressDialog.setCancelable(true);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          RecoveryPasswordActivity.this.onBackPressed();
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
 * Qualified Name:     com.appbuilder.u846253p1176378.LoginScreen.RecoveryPasswordActivity
 * JD-Core Version:    0.6.0
 */