package com.appbuilder.u846253p1176378.GPSNotification;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Iterator;
import java.util.List;

public class GPSReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i = 0;
    String str = paramContext.getPackageName() + ".GPSNotification.GPSService";
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(2147483647).iterator();
    while (localIterator.hasNext())
    {
      if (!str.equals(((ActivityManager.RunningServiceInfo)localIterator.next()).service.getClassName()))
        continue;
      i = 1;
    }
    if (i == 0)
      paramContext.startService(new Intent(paramContext, GPSService.class));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.GPSNotification.GPSReceiver
 * JD-Core Version:    0.6.0
 */