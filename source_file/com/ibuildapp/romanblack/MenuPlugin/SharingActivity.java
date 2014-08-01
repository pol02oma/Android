package com.ibuildapp.romanblack.MenuPlugin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
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
import java.io.InputStream;
import java.net.URL;
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
  private final int NEED_INTERNET_CONNECTION = 0;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private String appid;
  private String appname;
  private TextView captionTextView = null;
  private String description;
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
  private View homeImageView = null;
  private String image_url;
  private EditText mainEditText = null;
  private String name;
  private View postImageView = null;
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
    requestWindowFeature(1);
    setContentView(R.layout.sergeyb_menuplugin_sharing);
    Intent localIntent = getIntent();
    this.name = localIntent.getStringExtra("itemname");
    this.hasAd = localIntent.getBooleanExtra("hasAd", true);
    this.appname = localIntent.getStringExtra("appname");
    this.appid = localIntent.getStringExtra("appid");
    this.sharingType = localIntent.getStringExtra("type");
    this.image_url = localIntent.getStringExtra("image_url");
    this.description = localIntent.getStringExtra("description");
    if (this.image_url == null)
      this.image_url = "";
    this.homeImageView = findViewById(R.id.sergeyb_menuplugin_sharing_home);
    this.homeImageView.setOnClickListener(this);
    this.postImageView = findViewById(R.id.sergeyb_menuplugin_sharing_post);
    this.postImageView.setOnClickListener(this);
    this.mainEditText = ((EditText)findViewById(R.id.sergeyb_menuplugin_sharing_edittext));
    this.mainEditText.setText(this.text);
    this.captionTextView = ((TextView)findViewById(R.id.sergeyb_menuplugin_sharing_label));
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
          while (true)
          {
            try
            {
              String str1 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
              String str2 = "IMG_IBUILDAPP_" + str1 + ".jpg";
              String str3 = this.val$edittext;
              if (SharingActivity.this.appid.equals("709653"))
                continue;
              String str5 = SharingActivity.this.getString(R.string.menuplugin_email_download_this);
              String str6 = SharingActivity.this.getString(R.string.menuplugin_email_android_iphone_app);
              String str7 = SharingActivity.this.getString(R.string.menuplugin_email_posted_via);
              String str8 = SharingActivity.this.getString(R.string.menuplugin_email_found_this);
              Object[] arrayOfObject1 = new Object[2];
              arrayOfObject1[0] = Statics.BASE_DOMEN;
              arrayOfObject1[1] = SharingActivity.access$200(SharingActivity.this);
              String str9 = String.format("http://%s/projects.php?action=info&projectid=%s", arrayOfObject1);
              String str10 = str5 + " %s " + str6 + ": %s\n%s";
              Object[] arrayOfObject2 = new Object[3];
              arrayOfObject2[0] = SharingActivity.access$300(SharingActivity.this);
              arrayOfObject2[1] = str9;
              arrayOfObject2[2] = (str7 + " http://ibuildapp.com");
              str11 = String.format(str10, arrayOfObject2);
              String str12 = "%s\n%s\n" + str8 + " %s: %s \n%s";
              Object[] arrayOfObject3 = new Object[5];
              arrayOfObject3[0] = SharingActivity.access$400(SharingActivity.this);
              arrayOfObject3[1] = Html.fromHtml(SharingActivity.access$500(SharingActivity.this).replaceAll("\\<img.*?>", ""));
              arrayOfObject3[2] = SharingActivity.access$300(SharingActivity.this);
              arrayOfObject3[3] = SharingActivity.access$600(SharingActivity.this);
              if (SharingActivity.this.hasAd)
              {
                arrayOfObject3[4] = str11;
                String str13 = String.format(str12, arrayOfObject3);
                String str4 = str3 + "\n" + str13;
                if ((SharingActivity.this.image_url == null) || (SharingActivity.this.image_url.length() <= 0))
                  continue;
                InputStream localInputStream = new URL(SharingActivity.this.image_url).openStream();
                FacebookClient localFacebookClient2 = this.val$fbClient;
                BinaryAttachment localBinaryAttachment = BinaryAttachment.with(str2, localInputStream);
                Parameter[] arrayOfParameter2 = new Parameter[2];
                arrayOfParameter2[0] = Parameter.with("description", str4);
                arrayOfParameter2[1] = Parameter.with("message", str4);
                ((FacebookType)localFacebookClient2.publish("me/photos", FacebookType.class, localBinaryAttachment, arrayOfParameter2));
                SharingActivity.this.handler.sendEmptyMessage(4);
                return;
                str4 = SharingActivity.this.description + "\n" + str3;
                continue;
                FacebookClient localFacebookClient1 = this.val$fbClient;
                Parameter[] arrayOfParameter1 = new Parameter[1];
                arrayOfParameter1[0] = Parameter.with("message", str4);
                ((FacebookType)localFacebookClient1.publish("me/feed", FacebookType.class, arrayOfParameter1));
                continue;
              }
            }
            catch (Exception localException)
            {
              Log.e("", "");
              SharingActivity.this.handler.sendEmptyMessage(5);
              return;
            }
            String str11 = "";
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
          SharingActivity.access$902(SharingActivity.this, SharingActivity.this.reInitTwitter());
          String str1 = this.val$edittext;
          if (SharingActivity.this.hasAd == true)
            str1 = str1 + SharingActivity.this.getString(R.string.menuplugin_email_posted_via) + " http://ibuildapp.com.";
          if (!SharingActivity.this.appid.equals("709653"))
          {
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = SharingActivity.access$400(SharingActivity.this);
            arrayOfObject[1] = Html.fromHtml(SharingActivity.access$500(SharingActivity.this).replaceAll("\\<img.*?>", ""));
            String str3 = String.format("%s\n%s\n", arrayOfObject);
            localObject = str1 + "\n" + str3;
            if (((String)localObject).length() > 140)
              if (!TextUtils.isEmpty(SharingActivity.this.image_url))
                break label320;
          }
          label320: String str2;
          for (Object localObject = ((String)localObject).substring(0, 110); ; localObject = str2)
          {
            StatusUpdate localStatusUpdate = new StatusUpdate((String)localObject);
            if ((SharingActivity.this.image_url != null) && (SharingActivity.this.image_url.length() > 0))
            {
              InputStream localInputStream = new URL(SharingActivity.this.image_url).openStream();
              localStatusUpdate.setMedia(SharingActivity.this.image_url, localInputStream);
            }
            SharingActivity.this.twitter.updateStatus(localStatusUpdate);
            SharingActivity.this.handler.sendEmptyMessage(4);
            return;
            localObject = SharingActivity.this.description + "\n" + str1;
            break;
            str2 = ((String)localObject).substring(0, 140 - SharingActivity.this.image_url.length());
          }
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
 * Qualified Name:     com.ibuildapp.romanblack.MenuPlugin.SharingActivity
 * JD-Core Version:    0.6.0
 */