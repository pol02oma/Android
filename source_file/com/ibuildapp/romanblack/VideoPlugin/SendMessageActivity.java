package com.ibuildapp.romanblack.VideoPlugin;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.BarDesigner.TitleDesign;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class SendMessageActivity extends AppBuilderModuleMain
  implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher
{
  private final int CLOSE_ACTIVITY_BAD = 1;
  private final int CLOSE_ACTIVITY_OK = 0;
  private final int PICK_IMAGE_ACTIVITY = 10001;
  private final int TAKE_A_PICTURE_ACTIVITY = 10000;
  private TextView cancelButton = null;
  private TextView clearButton = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        SendMessageActivity.this.closeActivityOK();
        return;
      case 1:
      }
      SendMessageActivity.this.closeActivityBad();
    }
  };
  private LinearLayout imageLayout = null;
  private String imagePath = "";
  private LinearLayout mainLayout = null;
  private CommentItem message = null;
  private EditText messageEditText = null;
  private TextView postButton = null;
  private ProgressDialog progressDialog;
  private CommentItem recievedMessage = null;
  private TextView symbolCounter = null;
  private boolean uploading = false;
  private VideoItem video = null;

  private void closeActivityBad()
  {
    setResult(0);
    finish();
  }

  private void closeActivityOK()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("message", this.recievedMessage);
    setResult(-1, localIntent);
    finish();
  }

  private void hideProgressDialog()
  {
    try
    {
      this.progressDialog.dismiss();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
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
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.loading));
      this.progressDialog.setCancelable(true);
    }
  }

  public void afterTextChanged(Editable paramEditable)
  {
    if (this.symbolCounter == null)
      this.symbolCounter = ((TextView)findViewById(R.id.romanblack_fanwall_sendmessage_symbols_counter));
    this.symbolCounter.setText(paramEditable.length() + "/150");
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void create()
  {
    setContentView(R.layout.romanblack_video_send_message);
    Intent localIntent = getIntent();
    setTopBarTitle(" ");
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        SendMessageActivity.this.finish();
      }
    });
    this.message = ((CommentItem)localIntent.getSerializableExtra("message"));
    this.video = ((VideoItem)localIntent.getSerializableExtra("video"));
    this.mainLayout = ((LinearLayout)findViewById(R.id.romanblack_video_send_message_main));
    this.mainLayout.setBackgroundColor(Statics.color1);
    this.messageEditText = ((EditText)findViewById(R.id.romanblack_video_sendmessage_edittext));
    this.messageEditText.setImeOptions(268435456);
    this.messageEditText.addTextChangedListener(this);
    this.cancelButton = ((TextView)findViewById(R.id.romanblack_fanwall_sendmessage_cancelbtn));
    try
    {
      this.cancelButton.setTextColor(this.bottomBarDesign.leftButtonDesign.textColor);
      this.cancelButton.setTextSize(this.bottomBarDesign.leftButtonDesign.fontSize);
      label176: this.cancelButton.setOnClickListener(this);
      this.clearButton = ((TextView)findViewById(R.id.romanblack_fanwall_sendmessage_clear_btn));
      try
      {
        this.clearButton.setTextColor(this.bottomBarDesign.leftButtonDesign.textColor);
        this.clearButton.setTextSize(this.bottomBarDesign.leftButtonDesign.fontSize);
        label233: this.clearButton.setOnClickListener(this);
        this.postButton = ((TextView)findViewById(R.id.romanblack_fanwall_sendmessage_post_btn));
        try
        {
          this.postButton.setTextColor(this.bottomBarDesign.leftButtonDesign.textColor);
          this.postButton.setTextSize(this.bottomBarDesign.leftButtonDesign.fontSize);
          label290: this.postButton.setOnClickListener(this);
          this.symbolCounter = ((TextView)findViewById(R.id.romanblack_fanwall_sendmessage_symbols_counter));
          return;
        }
        catch (NullPointerException localNullPointerException3)
        {
          break label290;
        }
      }
      catch (NullPointerException localNullPointerException2)
      {
        break label233;
      }
    }
    catch (NullPointerException localNullPointerException1)
    {
      break label176;
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 10000)
      if (paramInt2 == -1)
      {
        this.imagePath = paramIntent.getStringExtra("imagePath");
        if (this.imagePath != null)
          break label38;
      }
    label38: 
    do
    {
      do
      {
        do
          return;
        while (this.imagePath.length() != 0);
        return;
      }
      while ((paramInt1 != 10001) || (paramInt2 != -1));
      Uri localUri = paramIntent.getData();
      String[] arrayOfString = { "_data" };
      Cursor localCursor = getContentResolver().query(localUri, arrayOfString, null, null, null);
      localCursor.moveToFirst();
      String str = localCursor.getString(localCursor.getColumnIndex(arrayOfString[0]));
      localCursor.close();
      this.imagePath = str;
    }
    while ((this.imagePath == null) || (this.imagePath.length() == 0) || (!this.imagePath.startsWith("http")));
    Toast.makeText(this, R.string.romanblack_video_alert_image_cannot_be_selected, 1).show();
  }

  public void onBackPressed()
  {
    finish();
  }

  public void onClick(View paramView)
  {
    if (!this.uploading)
    {
      if (paramView.getId() != R.id.romanblack_fanwall_sendmessage_cancelbtn)
        break label22;
      finish();
    }
    label22: 
    do
    {
      return;
      if (paramView.getId() != R.id.romanblack_fanwall_sendmessage_clear_btn)
        continue;
      this.messageEditText.setText("");
      return;
    }
    while (paramView.getId() != R.id.romanblack_fanwall_sendmessage_post_btn);
    if (this.messageEditText.getText().length() < 1)
      Toast.makeText(this, R.string.romanblack_video_alert_empty_message, 1).show();
    showProgressDialog();
    this.uploading = true;
    if (this.messageEditText.getText().length() > 150)
    {
      Toast.makeText(this, R.string.romanblack_video_alert_big_text, 1).show();
      this.uploading = false;
      hideProgressDialog();
      return;
    }
    try
    {
      if ((this.messageEditText.getText().length() == 0) && (this.imageLayout.getVisibility() == 8))
      {
        Toast.makeText(this, R.string.romanblack_video_alert_empty_message, 1).show();
        this.uploading = false;
        hideProgressDialog();
        return;
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      Toast.makeText(this, R.string.romanblack_video_alert_empty_message, 1).show();
      this.uploading = false;
      hideProgressDialog();
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        localBasicHttpParams.setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
        while (true)
        {
          MultipartEntity localMultipartEntity;
          try
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(Statics.BASE_URL);
            localStringBuilder.append("/");
            HttpPost localHttpPost = new HttpPost(localStringBuilder.toString());
            localMultipartEntity = new MultipartEntity();
            localMultipartEntity.addPart("action", new StringBody("postcomment", Charset.forName("UTF-8")));
            localMultipartEntity.addPart("app_id", new StringBody(com.appbuilder.sdk.android.Statics.appId, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("token", new StringBody(com.appbuilder.sdk.android.Statics.appToken, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("module_id", new StringBody(Statics.MODULE_ID, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("parent_id", new StringBody(SendMessageActivity.this.video.getId() + "", Charset.forName("UTF-8")));
            if (SendMessageActivity.this.message == null)
              continue;
            localMultipartEntity.addPart("reply_id", new StringBody(SendMessageActivity.this.message.getId() + "", Charset.forName("UTF-8")));
            if (Authorization.getAuthorizedUser().getAccountType() != User.ACCOUNT_TYPES.FACEBOOK)
              continue;
            localMultipartEntity.addPart("account_type", new StringBody("facebook", Charset.forName("UTF-8")));
            localMultipartEntity.addPart("account_id", new StringBody(Authorization.getAuthorizedUser().getAccountId(), Charset.forName("UTF-8")));
            localMultipartEntity.addPart("username", new StringBody(Authorization.getAuthorizedUser().getUserName(), Charset.forName("UTF-8")));
            localMultipartEntity.addPart("avatar", new StringBody(Authorization.getAuthorizedUser().getAvatarUrl(), Charset.forName("UTF-8")));
            localMultipartEntity.addPart("text", new StringBody(SendMessageActivity.this.messageEditText.getText().toString(), Charset.forName("UTF-8")));
            localHttpPost.setEntity(localMultipartEntity);
            Statics.onPost();
            String str1 = (String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler());
            SendMessageActivity.access$502(SendMessageActivity.this, (CommentItem)JSONParser.parseCommentsString(str1).get(0));
            if (SendMessageActivity.this.message != null)
              break label700;
            localObject = Statics.BASE_URL + "/getcomments/" + com.appbuilder.sdk.android.Statics.appId + "/" + Statics.MODULE_ID + "/" + SendMessageActivity.this.video.getId() + "/0/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken;
            ArrayList localArrayList = JSONParser.parseCommentsUrl((String)localObject);
            if ((localArrayList == null) || (localArrayList.isEmpty()))
              continue;
            Statics.onCommentsUpdate(SendMessageActivity.this.video, SendMessageActivity.this.message, localArrayList.size(), 0, localArrayList);
            Log.d("", "");
            SendMessageActivity.this.handler.sendEmptyMessage(0);
            return;
            if (Authorization.getAuthorizedUser().getAccountType() == User.ACCOUNT_TYPES.TWITTER)
            {
              localMultipartEntity.addPart("account_type", new StringBody("twitter", Charset.forName("UTF-8")));
              continue;
            }
          }
          catch (Exception localException)
          {
            Log.d("", "");
            SendMessageActivity.this.handler.sendEmptyMessage(1);
            return;
          }
          localMultipartEntity.addPart("account_type", new StringBody("ibuildapp", Charset.forName("UTF-8")));
          continue;
          label700: String str2 = Statics.BASE_URL + "/getcomments/" + com.appbuilder.sdk.android.Statics.appId + "/" + Statics.MODULE_ID + "/" + SendMessageActivity.this.video.getId() + "/" + SendMessageActivity.this.message.getId() + "/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken;
          Object localObject = str2;
        }
      }
    }).start();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    Log.d("", "");
  }

  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.SendMessageActivity
 * JD-Core Version:    0.6.0
 */