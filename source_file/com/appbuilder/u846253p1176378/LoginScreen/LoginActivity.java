package com.appbuilder.u846253p1176378.LoginScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.appbuilder.sdk.android.LoginScreen;

public class LoginActivity extends Activity
  implements View.OnClickListener
{
  public LoginScreen ls;

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131361815);
    do
    {
      do
        return;
      while (paramView.getId() == 2131361814);
      if (paramView.getId() != 2131361816)
        continue;
      Intent localIntent1 = new Intent(this, LoginWithEmailActivity.class);
      localIntent1.putExtra("loginScreen", this.ls);
      startActivity(localIntent1);
      return;
    }
    while (paramView.getId() != 2131361819);
    Intent localIntent2 = new Intent(this, RegisterEmailAccountActivity.class);
    localIntent2.putExtra("loginScreen", this.ls);
    startActivity(localIntent2);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().requestFeature(1);
    setContentView(2130903045);
    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131361815);
    LinearLayout localLinearLayout2 = (LinearLayout)findViewById(2131361814);
    LinearLayout localLinearLayout3 = (LinearLayout)findViewById(2131361816);
    TextView localTextView = (TextView)findViewById(2131361819);
    LinearLayout localLinearLayout4 = (LinearLayout)findViewById(2131361817);
    this.ls = ((LoginScreen)getIntent().getSerializableExtra("loginScreen"));
    if (!this.ls.getAllowSignup().booleanValue())
      localLinearLayout4.setVisibility(8);
    if (!this.ls.getUseEmail().booleanValue())
      localLinearLayout3.setVisibility(8);
    if (this.ls.getUseFacebook().booleanValue())
      localLinearLayout2.setVisibility(8);
    if (this.ls.getUseTwitter().booleanValue())
      localLinearLayout1.setVisibility(8);
    localLinearLayout3.setOnClickListener(this);
    localLinearLayout1.setOnClickListener(this);
    localLinearLayout2.setOnClickListener(this);
    localTextView.setOnClickListener(this);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.LoginScreen.LoginActivity
 * JD-Core Version:    0.6.0
 */