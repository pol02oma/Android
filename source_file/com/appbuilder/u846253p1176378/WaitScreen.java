package com.appbuilder.u846253p1176378;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class WaitScreen extends Activity
{
  private static ProgressDialog activityWaitDialog = null;
  private static WaitScreen mWaitScreen = null;
  private ProgressDialog waitDialog = null;

  public static void DismissWait()
  {
    monitorenter;
    try
    {
      activityWaitDialog.dismiss();
      activityWaitDialog = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void Remove()
  {
    monitorenter;
    try
    {
      if (mWaitScreen != null)
      {
        mWaitScreen.waitDialog.dismiss();
        mWaitScreen.finish();
        mWaitScreen = null;
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void Show(Activity paramActivity)
  {
    monitorenter;
    try
    {
      paramActivity.startActivity(new Intent(paramActivity, WaitScreen.class));
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void ShowWait(Activity paramActivity)
  {
    monitorenter;
    try
    {
      activityWaitDialog = new ProgressDialog(paramActivity);
      activityWaitDialog.setCancelable(false);
      activityWaitDialog.getWindow().setGravity(48);
      activityWaitDialog.show();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mWaitScreen = this;
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903138);
    this.waitDialog = new ProgressDialog(this);
    this.waitDialog.setCancelable(false);
    this.waitDialog.setMessage(getString(2131100003));
    this.waitDialog.getWindow().setGravity(48);
    this.waitDialog.show();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.WaitScreen
 * JD-Core Version:    0.6.0
 */