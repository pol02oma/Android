package com.ibuildapp.romanblack.CameraPlugin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.appbuilder.sdk.android.AppBuilderModule;
import com.appbuilder.sdk.android.Statics;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SharingActivity extends AppBuilderModule
  implements View.OnClickListener
{
  private final int CLOSE_ACTIVITY = 7;
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int HIDE_PROGRESS_DIALOG_FAILURE = 5;
  private final int HIDE_PROGRESS_DIALOG_SUCCESS = 4;
  private final int INITIALIZATION_FAILED = 1;
  private final int LOGIN_SUCCESS = 6;
  private final int NEED_INTERNET_CONNECTION = 0;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private String appname;
  private TextView captionTextView = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      case 0:
      case 6:
      default:
        return;
      case 1:
        SharingActivity.this.finish();
        return;
      case 2:
        SharingActivity.this.showProgressDialog();
        return;
      case 3:
        SharingActivity.this.hideProgressDialog();
        return;
      case 7:
        SharingActivity.this.finish();
        return;
      case 4:
        SharingActivity.this.setResult(-1);
        SharingActivity.this.hideProgressDialog();
        return;
      case 5:
      }
      SharingActivity.this.setResult(0);
      SharingActivity.this.hideProgressDialog();
    }
  };
  private boolean hasAd;
  private LinearLayout homeImageView = null;
  private String image;
  private InputStream image_inputstream;
  private EditText mainEditText = null;
  private LinearLayout postImageView = null;
  private ProgressDialog progressDialog = null;
  private String sharingType;
  private String text = "";
  private Twitter twitter = null;

  private void hideProgressDialog()
  {
    try
    {
      this.progressDialog.dismiss();
      label7: finish();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      break label7;
    }
  }

  private Twitter reInitTwitter()
  {
    User localUser = Authorization.getAuthorizedUser(2);
    ConfigurationBuilder localConfigurationBuilder = new ConfigurationBuilder();
    localConfigurationBuilder.setDebugEnabled(true).setOAuthAccessToken(localUser.getAccessToken()).setOAuthAccessTokenSecret(localUser.getAccessTokenSecret()).setOAuthConsumerSecret(Statics.TWITTER_CONSUMER_SECRET).setOAuthConsumerKey(Statics.TWITTER_CONSUMER_KEY);
    return new TwitterFactory(localConfigurationBuilder.build()).getInstance();
  }

  private void showProgressDialog()
  {
    try
    {
      boolean bool = this.progressDialog.isShowing();
      if (bool)
        return;
    }
    catch (NullPointerException localNullPointerException)
    {
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.common_loading_upper));
      this.progressDialog.setCancelable(true);
    }
  }

  public void create()
  {
    setContentView(R.layout.romanblack_camera_sharing);
    Intent localIntent = getIntent();
    this.hasAd = localIntent.getBooleanExtra("hasAd", true);
    this.appname = localIntent.getStringExtra("appname");
    this.sharingType = localIntent.getStringExtra("type");
    this.image = localIntent.getStringExtra("image");
    this.homeImageView = ((LinearLayout)findViewById(R.id.romanblack_soundcloud_sharing_home));
    this.homeImageView.setOnClickListener(this);
    this.postImageView = ((LinearLayout)findViewById(R.id.romanblack_soundcloud_sharing_post));
    this.postImageView.setOnClickListener(this);
    this.mainEditText = ((EditText)findViewById(R.id.romanblack_soundcloud_sharing_edittext));
    this.mainEditText.setText(this.text);
    this.captionTextView = ((TextView)findViewById(R.id.romanblack_soundcloud_sharing_label));
    if (this.sharingType.equalsIgnoreCase("facebook"))
      this.captionTextView.setText("Facebook");
    do
      return;
    while (!this.sharingType.equalsIgnoreCase("twitter"));
    this.captionTextView.setText("Twitter");
  }

  public void onClick(View paramView)
  {
    String str = this.mainEditText.getText().toString();
    if (paramView == this.homeImageView)
      finish();
    do
    {
      do
        return;
      while (paramView != this.postImageView);
      NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo == null)
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
      if (!localNetworkInfo.isConnectedOrConnecting())
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
      if (!this.sharingType.equalsIgnoreCase("facebook"))
        continue;
      DefaultFacebookClient localDefaultFacebookClient = new DefaultFacebookClient(Authorization.getAuthorizedUser(1).getAccessToken());
      this.handler.sendEmptyMessage(2);
      new Thread(new Runnable(str, localDefaultFacebookClient)
      {
        public void run()
        {
          try
          {
            String str1 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String str2 = "IMG_IBUILDAPP_" + str1 + ".jpg";
            String str3 = this.val$edittext;
            if (SharingActivity.this.hasAd == true)
              str3 = str3 + "\nPosted via http://ibuildapp.com.";
            FileInputStream localFileInputStream = new FileInputStream(SharingActivity.this.image);
            FacebookClient localFacebookClient = this.val$fbClient;
            BinaryAttachment localBinaryAttachment = BinaryAttachment.with(str2, localFileInputStream);
            Parameter[] arrayOfParameter = new Parameter[1];
            arrayOfParameter[0] = Parameter.with("message", str3);
            ((FacebookType)localFacebookClient.publish("me/photos", FacebookType.class, localBinaryAttachment, arrayOfParameter));
            SharingActivity.this.handler.sendEmptyMessage(4);
            return;
          }
          catch (Exception localException)
          {
            Log.e("", "");
            SharingActivity.this.handler.sendEmptyMessage(5);
          }
        }
      }).start();
      return;
    }
    while (!this.sharingType.equalsIgnoreCase("twitter"));
    this.handler.sendEmptyMessage(2);
    new Thread(new Runnable(str)
    {
      public void run()
      {
        try
        {
          SharingActivity.access$502(SharingActivity.this, SharingActivity.this.reInitTwitter());
          String str = this.val$edittext;
          if (SharingActivity.this.hasAd == true)
            str = str + "\nPosted via http://ibuildapp.com.";
          if (str.length() > 140)
            str = str.substring(0, 140);
          StatusUpdate localStatusUpdate = new StatusUpdate(str);
          if ((SharingActivity.this.image != null) && (SharingActivity.this.image.length() > 0))
          {
            FileInputStream localFileInputStream = new FileInputStream(SharingActivity.this.image);
            localStatusUpdate.setMedia(SharingActivity.this.image, localFileInputStream);
          }
          SharingActivity.this.twitter.updateStatus(localStatusUpdate);
          SharingActivity.this.handler.sendEmptyMessage(4);
          return;
        }
        catch (Exception localException)
        {
          Log.d("", "");
          SharingActivity.this.handler.sendEmptyMessage(5);
        }
      }
    }).start();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.CameraPlugin.SharingActivity
 * JD-Core Version:    0.6.0
 */