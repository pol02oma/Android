package com.ibuildapp.romanblack.VideoPlugin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
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

public class SharingActivity extends AppBuilderModuleMain
  implements View.OnClickListener
{
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int INITIALIZATION_FAILED = 1;
  private final int NEED_INTERNET_CONNECTION = 0;
  private final int SHARED_ON_FACEBOOK = 4;
  private final int SHARED_ON_TWITTER = 5;
  private final int SHOW_PROGRESS_DIALOG = 2;
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
        SharingActivity.this.hideProgressDialog();
        return;
      case 4:
        Toast.makeText(SharingActivity.this, R.string.romanblack_video_shared_on_facebook, 1).show();
        return;
      case 5:
      }
      Toast.makeText(SharingActivity.this, R.string.romanblack_video_shared_on_twitter, 1).show();
    }
  };
  private VideoItem item = null;
  private String link = "";
  private EditText mainEditText = null;
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

  private void post()
  {
    this.text = this.mainEditText.getText().toString();
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null)
      this.handler.sendEmptyMessage(0);
    do
    {
      return;
      if (!localNetworkInfo.isConnectedOrConnecting())
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
      if (!this.sharingType.equalsIgnoreCase("facebook"))
        continue;
      DefaultFacebookClient localDefaultFacebookClient = new DefaultFacebookClient(Authorization.getAuthorizedUser(1).getAccessToken());
      this.handler.sendEmptyMessage(2);
      this.text = (Authorization.getAuthorizedUser(1).getUserName() + " " + getString(R.string.romanblack_video_sharing_first_part) + " " + this.link + " " + getString(R.string.romanblack_video_sharing_second_part) + " " + Statics.APP_NAME + " app:\n\"" + this.mainEditText.getText() + "\"\n");
      if (com.appbuilder.sdk.android.Statics.showLink)
        this.text = (this.text + getString(R.string.romanblack_video_sharing_third_part) + " app: http://" + com.appbuilder.sdk.android.Statics.BASE_DOMEN + "/projects.php?action=info&projectid=" + Statics.APP_ID);
      new Thread(new Runnable(localDefaultFacebookClient)
      {
        public void run()
        {
          try
          {
            FacebookClient localFacebookClient = this.val$fbClient;
            Parameter[] arrayOfParameter = new Parameter[1];
            arrayOfParameter[0] = Parameter.with("message", SharingActivity.access$300(SharingActivity.this));
            ((FacebookType)localFacebookClient.publish("me/feed", FacebookType.class, arrayOfParameter));
            SharingActivity.this.setResult(-1);
            SharingActivity.this.handler.sendEmptyMessage(4);
            label63: SharingActivity.this.handler.sendEmptyMessage(3);
            return;
          }
          catch (FacebookOAuthException localFacebookOAuthException)
          {
            break label63;
          }
        }
      }).start();
      return;
    }
    while (!this.sharingType.equalsIgnoreCase("twitter"));
    this.handler.sendEmptyMessage(2);
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          SharingActivity.access$502(SharingActivity.this, new TwitterFactory().getInstance());
          SharingActivity.this.twitter.setOAuthConsumer(Authorization.getAuthorizedUser(2).getConsumerKey(), Authorization.getAuthorizedUser(2).getConsumerSecret());
          SharingActivity.this.twitter.setOAuthAccessToken(new AccessToken(Authorization.getAuthorizedUser(2).getAccessToken(), Authorization.getAuthorizedUser(2).getAccessTokenSecret()));
          SharingActivity.access$302(SharingActivity.this, Authorization.getAuthorizedUser(2).getUserName() + " " + SharingActivity.this.getString(R.string.romanblack_video_sharing_first_part) + " " + SharingActivity.this.link + " " + SharingActivity.this.getString(R.string.romanblack_video_sharing_second_part) + " " + Statics.APP_NAME + " app:\n\"" + SharingActivity.this.mainEditText.getText() + "\"\n");
          if (com.appbuilder.sdk.android.Statics.showLink)
            SharingActivity.access$302(SharingActivity.this, SharingActivity.this.text + SharingActivity.this.getString(R.string.romanblack_video_sharing_third_part) + " app: http://" + com.appbuilder.sdk.android.Statics.BASE_DOMEN + "/projects.php?action=info&projectid=" + Statics.APP_ID);
          if (SharingActivity.this.text.length() > 140)
            SharingActivity.access$302(SharingActivity.this, SharingActivity.this.text.substring(0, 139));
          SharingActivity.this.twitter.updateStatus(SharingActivity.this.text);
          SharingActivity.this.setResult(-1);
          SharingActivity.this.handler.sendEmptyMessage(5);
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
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_video_loading));
      this.progressDialog.setCancelable(true);
    }
  }

  public void create()
  {
    setContentView(R.layout.romanblack_video_sharing);
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
      this.item = ((VideoItem)localIntent.getSerializableExtra("item"));
      if (this.item == null)
      {
        this.handler.sendEmptyMessage(1);
        return;
      }
      setTopBarTitle(" ");
      swipeBlock();
      setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          SharingActivity.this.finish();
        }
      });
      setTopBarRightButtonText(getString(R.string.post), false, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          SharingActivity.this.post();
        }
      });
      this.mainEditText = ((EditText)findViewById(R.id.romanblack_video_sharing_edittext));
      if (!this.sharingType.equalsIgnoreCase("facebook"))
        continue;
      setTopBarTitle("Facebook");
      return;
    }
    while (!this.sharingType.equalsIgnoreCase("twitter"));
    setTopBarTitle("Twitter");
  }

  public void onClick(View paramView)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.SharingActivity
 * JD-Core Version:    0.6.0
 */