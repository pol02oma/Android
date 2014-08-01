package com.google.android.c2dm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class C2DMBroadcastReceiver extends BroadcastReceiver
{
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    C2DMBaseReceiver.runIntentInService(paramContext, paramIntent);
    setResult(-1, null, null);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.c2dm.C2DMBroadcastReceiver
 * JD-Core Version:    0.6.0
 */