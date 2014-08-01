package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;

public class ef
  implements DialogInterface.OnClickListener
{
  private final int Bq;
  private final Intent mIntent;
  private final Activity nd;

  public ef(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    this.nd = paramActivity;
    this.mIntent = paramIntent;
    this.Bq = paramInt;
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    try
    {
      if (this.mIntent != null)
        this.nd.startActivityForResult(this.mIntent, this.Bq);
      paramDialogInterface.dismiss();
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ef
 * JD-Core Version:    0.6.0
 */