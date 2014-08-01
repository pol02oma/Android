package com.ibuildapp.romanblack.NewsPlugin;

import android.content.Intent;
import android.webkit.WebView;
import android.widget.TextView;
import com.appbuilder.sdk.android.AppBuilderModule;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationDetails extends AppBuilderModule
{
  private TextView dateText;
  private WebView descriptionText;
  private TextView titleText;

  public void create()
  {
    setContentView(R.layout.romanblack_notification_details);
    this.titleText = ((TextView)findViewById(R.id.romanblack_feed_title));
    this.dateText = ((TextView)findViewById(R.id.romanblack_feed_date));
    this.descriptionText = ((WebView)findViewById(R.id.romanblack_feed_description));
    Intent localIntent = getIntent();
    String str1 = localIntent.getStringExtra("TITLE");
    String str2 = localIntent.getStringExtra("DATE");
    String str3 = localIntent.getStringExtra("DESCRIPTION");
    try
    {
      String str4 = new String(str3.getBytes(), "UTF-8");
      str5 = str4;
      this.titleText.setText(str1);
      this.dateText.setText(str2);
      this.descriptionText.loadDataWithBaseURL("fake://not/needed", str5, "text/html", "utf-8", "");
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        Logger.getLogger(NotificationDetails.class.getName()).log(Level.SEVERE, null, localUnsupportedEncodingException);
        String str5 = null;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.NotificationDetails
 * JD-Core Version:    0.6.0
 */