package io.vov.vitamio.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import io.vov.vitamio.Vitamio;
import java.lang.ref.WeakReference;

public class InitActivity extends Activity
{
  public static final String FROM_ME = "fromVitamioInitActivity";
  static int libarm;
  private ProgressDialog mPD;
  private UIHandler uiHandler;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    libarm = getIntent().getIntExtra("libarm", 0);
    getWindow().addFlags(128);
    this.uiHandler = new UIHandler(this);
    new AsyncTask()
    {
      protected Boolean doInBackground(Object[] paramArrayOfObject)
      {
        return Boolean.valueOf(Vitamio.initialize(InitActivity.this, InitActivity.libarm));
      }

      protected void onPostExecute(Boolean paramBoolean)
      {
        if (paramBoolean.booleanValue())
          InitActivity.this.uiHandler.sendEmptyMessage(0);
      }

      protected void onPreExecute()
      {
        InitActivity.access$002(InitActivity.this, new ProgressDialog(InitActivity.this));
        InitActivity.this.mPD.setCancelable(false);
        InitActivity.this.mPD.setMessage("Initializing decoders…");
        InitActivity.this.mPD.show();
      }
    }
    .execute(new Object[0]);
  }

  private static class UIHandler extends Handler
  {
    private WeakReference<Context> mContext;

    public UIHandler(Context paramContext)
    {
      this.mContext = new WeakReference(paramContext);
    }

    public void handleMessage(Message paramMessage)
    {
      InitActivity localInitActivity = (InitActivity)this.mContext.get();
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
      }
      localInitActivity.mPD.dismiss();
      Intent localIntent1 = localInitActivity.getIntent();
      Intent localIntent2 = new Intent();
      localIntent2.setClassName(localIntent1.getStringExtra("package"), localIntent1.getStringExtra("className"));
      localIntent2.setData(localIntent1.getData());
      localIntent2.putExtras(localIntent1);
      localIntent2.putExtra("fromVitamioInitActivity", true);
      localInitActivity.startActivity(localIntent2);
      localInitActivity.finish();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.activity.InitActivity
 * JD-Core Version:    0.6.0
 */