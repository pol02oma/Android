package com.ibuildapp.romanblack.PhotoGalleryPlugin;

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
import android.widget.TextView;
import com.appbuilder.sdk.android.AppBuilderModule;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.FacebookType;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class SharingActivity extends AppBuilderModule
  implements View.OnClickListener
{
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int INITIALIZATION_FAILED = 1;
  private final int NEED_INTERNET_CONNECTION = 0;
  private final int SHOW_PROGRESS_DIALOG = 2;
  private TextView captionTextView = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      case 0:
      default:
        return;
      case 1:
        SharingActivity.this.finish();
        return;
      case 2:
        SharingActivity.this.showProgressDialog();
        return;
      case 3:
      }
      SharingActivity.this.hideProgressDialog();
    }
  };
  private TextView homeImageView = null;
  private String link = "";
  private EditText mainEditText = null;
  private TextView postImageView = null;
  private ProgressDialog progressDialog = null;
  private String sharingType = "";
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
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_photogallery_loading));
      this.progressDialog.setCancelable(true);
    }
  }

  public void create()
  {
    setContentView(R.layout.romanblack_photogallery_sharing);
    Intent localIntent = getIntent();
    this.link = localIntent.getStringExtra("link");
    if (this.link == null)
      this.handler.sendEmptyMessage(1);
    do
    {
      return;
      if (this.link.length() == 0)
      {
        this.handler.sendEmptyMessage(1);
        return;
      }
      this.sharingType = localIntent.getStringExtra("type");
      if (this.sharingType == null)
      {
        this.handler.sendEmptyMessage(1);
        return;
      }
      if (this.sharingType.length() == 0)
      {
        this.handler.sendEmptyMessage(1);
        return;
      }
      this.homeImageView = ((TextView)findViewById(R.id.romanblack_login_home));
      this.homeImageView.setOnClickListener(this);
      this.postImageView = ((TextView)findViewById(R.id.romanblack_photogallery_sharing_post));
      this.postImageView.setOnClickListener(this);
      this.mainEditText = ((EditText)findViewById(R.id.romanblack_photogallery_sharing_edittext));
      this.captionTextView = ((TextView)findViewById(R.id.romanblack_photogallery_sharing_label));
      if (!this.sharingType.equalsIgnoreCase("facebook"))
        continue;
      this.captionTextView.setText("Facebook");
      return;
    }
    while (!this.sharingType.equalsIgnoreCase("twitter"));
    this.captionTextView.setText("Twitter");
  }

  public void onClick(View paramView)
  {
    if (paramView == this.homeImageView)
      finish();
    do
    {
      do
        return;
      while (paramView != this.postImageView);
      this.postImageView.setClickable(false);
      this.text = this.mainEditText.getText().toString();
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
      this.text = (Authorization.getAuthorizedUser(1).getUserName() + " " + getString(R.string.romanblack_photogallery_sharing_first_part) + " " + this.link + " " + getString(R.string.romanblack_photogallery_sharing_second_part) + " " + Statics.APP_NAME + " app:\n\"" + this.mainEditText.getText() + "\"\n");
      if (com.appbuilder.sdk.android.Statics.showLink)
        this.text = (this.text + getString(R.string.romanblack_photogallery_sharing_third_part) + " app: http://" + com.appbuilder.sdk.android.Statics.BASE_DOMEN + "/projects.php?action=info&projectid=" + Statics.APP_ID);
      new Thread(new Runnable(localDefaultFacebookClient)
      {
        public void run()
        {
          try
          {
            FacebookClient localFacebookClient = this.val$fbClient;
            Parameter[] arrayOfParameter = new Parameter[1];
            arrayOfParameter[0] = Parameter.with("message", SharingActivity.access$200(SharingActivity.this));
            ((FacebookType)localFacebookClient.publish("me/feed", FacebookType.class, arrayOfParameter));
            SharingActivity.this.setResult(-1);
            label51: SharingActivity.this.handler.sendEmptyMessage(3);
            return;
          }
          catch (FacebookOAuthException localFacebookOAuthException)
          {
            break label51;
          }
        }
      }).start();
      return;
    }
    while (!this.sharingType.equalsIgnoreCase("twitter"));
    this.handler.sendEmptyMessage(2);
    this.text = (Authorization.getAuthorizedUser(2).getUserName() + " " + getString(R.string.romanblack_photogallery_sharing_first_part) + " " + this.link + " " + getString(R.string.romanblack_photogallery_sharing_second_part) + " " + Statics.APP_NAME + " app:\n\"" + this.mainEditText.getText() + "\"\n");
    if (com.appbuilder.sdk.android.Statics.showLink)
      this.text = (this.text + getString(R.string.romanblack_photogallery_sharing_third_part) + " app: http://" + com.appbuilder.sdk.android.Statics.BASE_DOMEN + "/projects.php?action=info&projectid=" + Statics.APP_ID);
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          SharingActivity.access$402(SharingActivity.this, new TwitterFactory().getInstance());
          SharingActivity.this.twitter.setOAuthConsumer(Authorization.getAuthorizedUser(2).getConsumerKey(), Authorization.getAuthorizedUser(2).getConsumerSecret());
          SharingActivity.this.twitter.setOAuthAccessToken(new AccessToken(Authorization.getAuthorizedUser(2).getAccessToken(), Authorization.getAuthorizedUser(2).getAccessTokenSecret()));
          SharingActivity.this.twitter.updateStatus(SharingActivity.this.text.substring(0, 139));
          SharingActivity.this.setResult(-1);
          SharingActivity.this.handler.sendEmptyMessage(3);
          return;
        }
        catch (TwitterException localTwitterException)
        {
          while (true)
            Log.d("", "");
        }
      }
    }).start();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.SharingActivity
 * JD-Core Version:    0.6.0
 */