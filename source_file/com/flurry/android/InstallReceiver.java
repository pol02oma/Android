package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.flurry.sdk.by;
import com.flurry.sdk.ex;
import com.flurry.sdk.fh;

public final class InstallReceiver extends BroadcastReceiver
{
  static final String a = InstallReceiver.class.getSimpleName();

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    ex.a(4, a, "Received an Install nofication of " + paramIntent.getAction());
    String str = paramIntent.getExtras().getString("referrer");
    ex.a(4, a, "Received an Install referrer of " + str);
    if ((str == null) || (!"com.android.vending.INSTALL_REFERRER".equals(paramIntent.getAction())))
    {
      ex.a(5, a, "referrer is null");
      return;
    }
    if (!str.contains("="))
    {
      ex.a(4, a, "referrer is before decoding: " + str);
      str = fh.c(str);
      ex.a(4, a, "referrer is: " + str);
    }
    new by(paramContext).a(str);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.android.InstallReceiver
 * JD-Core Version:    0.6.0
 */