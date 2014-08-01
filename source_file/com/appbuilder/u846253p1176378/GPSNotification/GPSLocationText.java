package com.appbuilder.u846253p1176378.GPSNotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class GPSLocationText extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903044);
    ((Button)findViewById(2131361812)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        GPSLocationText.this.finish();
      }
    });
    try
    {
      localGPSItem = (GPSItem)getIntent().getSerializableExtra("gpsNotificationData");
      if (localGPSItem != null)
      {
        ((TextView)findViewById(2131361809)).setText(localGPSItem.getDistance() + " m");
        ((TextView)findViewById(2131361810)).setText(localGPSItem.getTitle());
        ((TextView)findViewById(2131361811)).setText(localGPSItem.getDescription());
        return;
      }
      finish();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        GPSItem localGPSItem = null;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.GPSNotification.GPSLocationText
 * JD-Core Version:    0.6.0
 */