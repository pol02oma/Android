package com.appbuilder.u846253p1176378.GPSNotification;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.appbuilder.u846253p1176378.AppConfigure;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GPSService extends IntentService
  implements LocationListener
{
  private final int LOCATION_LISTENER_ERROR = 2;
  private final int LOCATION_LISTENER_START = 0;
  private final int LOCATION_LISTENER_STOP = 1;
  private String TAG = "GPSService";
  private AppConfigure appConfig = null;
  private String cachePath = "";
  private int counter = 0;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        GPSService.this.startLocationListener(0, 0);
        return;
      case 1:
        GPSService.this.stopLocationListener();
        return;
      case 2:
      }
      GPSService.access$202(GPSService.this, false);
    }
  };
  private boolean isListen = false;
  private boolean isRunning = false;
  private Location locationBefore = null;
  private LocationManager locationManager = null;
  private String notificationBarTitle = "";
  private String notificationText = "";
  private String notificationTitle = "";
  private HashMap<Integer, GPSItem> notifications = new HashMap();
  private long timeBefore = 0L;

  public GPSService()
  {
    super("GPSService");
  }

  private void startLocationListener(int paramInt1, int paramInt2)
  {
    int i = 0;
    String str = getPackageName() + ".GPSNotification.GPSService";
    Iterator localIterator = ((ActivityManager)getSystemService("activity")).getRunningServices(2147483647).iterator();
    while (localIterator.hasNext())
    {
      if (!str.equals(((ActivityManager.RunningServiceInfo)localIterator.next()).service.getClassName()))
        continue;
      i = 1;
    }
    if (i != 0)
    {
      writeToLog("Start Location Listener");
      if (this.locationManager.isProviderEnabled("gps"))
      {
        this.locationManager.requestLocationUpdates("gps", paramInt1, paramInt2, this);
        this.isListen = true;
      }
      return;
    }
    stopLocationListener();
  }

  private void stopLocationListener()
  {
    writeToLog("Stop Location Listener");
    if (this.locationManager != null)
    {
      this.locationManager.removeUpdates(this);
      this.isListen = false;
    }
  }

  private void writeToLog(String paramString)
  {
    String str = Environment.getExternalStorageDirectory() + "/gpsservice.log";
    try
    {
      BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(new File(str), true));
      Date localDate = new Date();
      localDate.setTime(System.currentTimeMillis());
      localBufferedWriter.append(localDate.toLocaleString() + " : " + paramString + "\n");
      localBufferedWriter.close();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void onDestroy()
  {
    if (this.locationManager != null)
      this.locationManager.removeUpdates(this);
    this.isRunning = false;
    super.onDestroy();
  }

  // ERROR //
  protected void onHandleIntent(Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: invokevirtual 243	com/appbuilder/u846253p1176378/GPSNotification/GPSService:getResources	()Landroid/content/res/Resources;
    //   5: ldc 244
    //   7: invokevirtual 250	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   10: putfield 68	com/appbuilder/u846253p1176378/GPSNotification/GPSService:notificationBarTitle	Ljava/lang/String;
    //   13: aload_0
    //   14: aload_0
    //   15: invokevirtual 243	com/appbuilder/u846253p1176378/GPSNotification/GPSService:getResources	()Landroid/content/res/Resources;
    //   18: ldc 244
    //   20: invokevirtual 250	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   23: putfield 70	com/appbuilder/u846253p1176378/GPSNotification/GPSService:notificationTitle	Ljava/lang/String;
    //   26: aload_0
    //   27: ldc 252
    //   29: invokespecial 164	com/appbuilder/u846253p1176378/GPSNotification/GPSService:writeToLog	(Ljava/lang/String;)V
    //   32: aload_0
    //   33: getfield 54	com/appbuilder/u846253p1176378/GPSNotification/GPSService:isRunning	Z
    //   36: ifeq +356 -> 392
    //   39: aload_0
    //   40: monitorenter
    //   41: new 201	java/io/File
    //   44: dup
    //   45: aload_0
    //   46: getfield 60	com/appbuilder/u846253p1176378/GPSNotification/GPSService:cachePath	Ljava/lang/String;
    //   49: invokespecial 202	java/io/File:<init>	(Ljava/lang/String;)V
    //   52: astore_2
    //   53: aload_2
    //   54: invokevirtual 255	java/io/File:exists	()Z
    //   57: ifne +72 -> 129
    //   60: aload_0
    //   61: iconst_0
    //   62: putfield 54	com/appbuilder/u846253p1176378/GPSNotification/GPSService:isRunning	Z
    //   65: aload_0
    //   66: getfield 52	com/appbuilder/u846253p1176378/GPSNotification/GPSService:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   69: invokevirtual 261	com/appbuilder/u846253p1176378/AppConfigure:getGPSNotifications	()Ljava/util/ArrayList;
    //   72: invokevirtual 267	java/util/ArrayList:size	()I
    //   75: ifle +309 -> 384
    //   78: aload_0
    //   79: getfield 56	com/appbuilder/u846253p1176378/GPSNotification/GPSService:isListen	Z
    //   82: ifne +12 -> 94
    //   85: aload_0
    //   86: getfield 85	com/appbuilder/u846253p1176378/GPSNotification/GPSService:handler	Landroid/os/Handler;
    //   89: iconst_0
    //   90: invokevirtual 273	android/os/Handler:sendEmptyMessage	(I)Z
    //   93: pop
    //   94: aload_0
    //   95: ldc2_w 274
    //   98: invokevirtual 280	java/lang/Object:wait	(J)V
    //   101: aload_0
    //   102: getfield 54	com/appbuilder/u846253p1176378/GPSNotification/GPSService:isRunning	Z
    //   105: ifne +12 -> 117
    //   108: aload_0
    //   109: getfield 85	com/appbuilder/u846253p1176378/GPSNotification/GPSService:handler	Landroid/os/Handler;
    //   112: iconst_1
    //   113: invokevirtual 273	android/os/Handler:sendEmptyMessage	(I)Z
    //   116: pop
    //   117: aload_0
    //   118: monitorexit
    //   119: goto -87 -> 32
    //   122: astore 5
    //   124: aload_0
    //   125: monitorexit
    //   126: aload 5
    //   128: athrow
    //   129: new 282	java/io/ObjectInputStream
    //   132: dup
    //   133: new 284	java/io/FileInputStream
    //   136: dup
    //   137: aload_2
    //   138: invokespecial 287	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   141: invokespecial 290	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   144: astore 6
    //   146: aload_0
    //   147: aload 6
    //   149: invokevirtual 293	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   152: checkcast 257	com/appbuilder/u846253p1176378/AppConfigure
    //   155: putfield 52	com/appbuilder/u846253p1176378/GPSNotification/GPSService:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   158: aload 6
    //   160: invokevirtual 294	java/io/ObjectInputStream:close	()V
    //   163: aload_0
    //   164: getfield 66	com/appbuilder/u846253p1176378/GPSNotification/GPSService:notifications	Ljava/util/HashMap;
    //   167: invokevirtual 298	java/util/HashMap:keySet	()Ljava/util/Set;
    //   170: invokeinterface 301 1 0
    //   175: astore 8
    //   177: aload 8
    //   179: invokeinterface 138 1 0
    //   184: ifeq -119 -> 65
    //   187: aload 8
    //   189: invokeinterface 142 1 0
    //   194: checkcast 303	java/lang/Integer
    //   197: astore 9
    //   199: iconst_0
    //   200: istore 10
    //   202: aload_0
    //   203: getfield 52	com/appbuilder/u846253p1176378/GPSNotification/GPSService:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   206: invokevirtual 261	com/appbuilder/u846253p1176378/AppConfigure:getGPSNotifications	()Ljava/util/ArrayList;
    //   209: invokevirtual 267	java/util/ArrayList:size	()I
    //   212: istore 11
    //   214: iconst_0
    //   215: istore 12
    //   217: iload 10
    //   219: iload 11
    //   221: if_icmpge +71 -> 292
    //   224: aload_0
    //   225: getfield 52	com/appbuilder/u846253p1176378/GPSNotification/GPSService:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   228: invokevirtual 261	com/appbuilder/u846253p1176378/AppConfigure:getGPSNotifications	()Ljava/util/ArrayList;
    //   231: iload 10
    //   233: invokevirtual 307	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   236: checkcast 309	com/appbuilder/u846253p1176378/GPSNotification/GPSItem
    //   239: astore 13
    //   241: aload_0
    //   242: getfield 66	com/appbuilder/u846253p1176378/GPSNotification/GPSService:notifications	Ljava/util/HashMap;
    //   245: aload 9
    //   247: invokevirtual 312	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   250: checkcast 309	com/appbuilder/u846253p1176378/GPSNotification/GPSItem
    //   253: invokevirtual 316	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:getLatitude	()D
    //   256: aload 13
    //   258: invokevirtual 316	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:getLatitude	()D
    //   261: dcmpl
    //   262: ifne +131 -> 393
    //   265: aload_0
    //   266: getfield 66	com/appbuilder/u846253p1176378/GPSNotification/GPSService:notifications	Ljava/util/HashMap;
    //   269: aload 9
    //   271: invokevirtual 312	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   274: checkcast 309	com/appbuilder/u846253p1176378/GPSNotification/GPSItem
    //   277: invokevirtual 319	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:getLongitude	()D
    //   280: aload 13
    //   282: invokevirtual 319	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:getLongitude	()D
    //   285: dcmpl
    //   286: ifne +107 -> 393
    //   289: iconst_1
    //   290: istore 12
    //   292: iload 12
    //   294: ifne -117 -> 177
    //   297: aload_0
    //   298: getfield 66	com/appbuilder/u846253p1176378/GPSNotification/GPSService:notifications	Ljava/util/HashMap;
    //   301: aload 9
    //   303: invokevirtual 312	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   306: checkcast 309	com/appbuilder/u846253p1176378/GPSNotification/GPSItem
    //   309: astore 14
    //   311: ldc2_w 320
    //   314: aload 14
    //   316: invokevirtual 316	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:getLatitude	()D
    //   319: dmul
    //   320: ldc2_w 320
    //   323: aload 14
    //   325: invokevirtual 319	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:getLongitude	()D
    //   328: dmul
    //   329: dadd
    //   330: d2i
    //   331: istore 15
    //   333: aload_0
    //   334: getfield 66	com/appbuilder/u846253p1176378/GPSNotification/GPSService:notifications	Ljava/util/HashMap;
    //   337: aload 9
    //   339: invokevirtual 324	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   342: pop
    //   343: aload_0
    //   344: ldc_w 326
    //   347: invokevirtual 119	com/appbuilder/u846253p1176378/GPSNotification/GPSService:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   350: checkcast 328	android/app/NotificationManager
    //   353: iload 15
    //   355: invokevirtual 332	android/app/NotificationManager:cancel	(I)V
    //   358: goto -181 -> 177
    //   361: astore_3
    //   362: aload_0
    //   363: getfield 42	com/appbuilder/u846253p1176378/GPSNotification/GPSService:TAG	Ljava/lang/String;
    //   366: aload_3
    //   367: invokestatic 338	android/util/Log:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   370: pop
    //   371: goto -254 -> 117
    //   374: astore 7
    //   376: aload_0
    //   377: iconst_0
    //   378: putfield 54	com/appbuilder/u846253p1176378/GPSNotification/GPSService:isRunning	Z
    //   381: goto -218 -> 163
    //   384: aload_0
    //   385: iconst_0
    //   386: putfield 54	com/appbuilder/u846253p1176378/GPSNotification/GPSService:isRunning	Z
    //   389: goto -288 -> 101
    //   392: return
    //   393: iinc 10 1
    //   396: goto -194 -> 202
    //
    // Exception table:
    //   from	to	target	type
    //   41	65	122	finally
    //   65	94	122	finally
    //   94	101	122	finally
    //   101	117	122	finally
    //   117	119	122	finally
    //   124	126	122	finally
    //   129	163	122	finally
    //   163	177	122	finally
    //   177	199	122	finally
    //   202	214	122	finally
    //   224	289	122	finally
    //   297	358	122	finally
    //   362	371	122	finally
    //   376	381	122	finally
    //   384	389	122	finally
    //   41	65	361	java/lang/Exception
    //   65	94	361	java/lang/Exception
    //   94	101	361	java/lang/Exception
    //   101	117	361	java/lang/Exception
    //   163	177	361	java/lang/Exception
    //   177	199	361	java/lang/Exception
    //   202	214	361	java/lang/Exception
    //   224	289	361	java/lang/Exception
    //   297	358	361	java/lang/Exception
    //   376	381	361	java/lang/Exception
    //   384	389	361	java/lang/Exception
    //   129	163	374	java/lang/Exception
  }

  public void onLocationChanged(Location paramLocation)
  {
    int i = 100000000;
    int j = 0;
    if (j < this.appConfig.getGPSNotifications().size())
    {
      GPSItem localGPSItem = (GPSItem)this.appConfig.getGPSNotifications().get(j);
      Location localLocation = new Location(localGPSItem.getDescription());
      localLocation.setLatitude(localGPSItem.getLatitude());
      localLocation.setLongitude(localGPSItem.getLongitude());
      localGPSItem.setDistance((int)paramLocation.distanceTo(localLocation));
      int k = (int)(1000000.0D * localGPSItem.getLatitude() + 1000000.0D * localGPSItem.getLongitude());
      Intent localIntent;
      if (localGPSItem.getDistance() < localGPSItem.getRadius())
      {
        if (i > localGPSItem.getRadius() - localGPSItem.getDistance())
          i = localGPSItem.getRadius() - localGPSItem.getDistance();
        this.notifications.put(Integer.valueOf(k), localGPSItem);
        Bundle localBundle = new Bundle();
        localBundle.putString("gpsNotificationMessage", "hasMessage");
        localBundle.putSerializable("gpsNotificationData", localGPSItem);
        localBundle.putDouble("srcLatitude", paramLocation.getLatitude());
        localBundle.putDouble("srcLongitude", paramLocation.getLongitude());
        NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
        int m = 0;
        if (localNetworkInfo != null)
        {
          boolean bool = localNetworkInfo.isConnectedOrConnecting();
          m = 0;
          if (bool)
            m = 1;
        }
        if (m != 0)
        {
          localIntent = new Intent(this, GPSLocationMap.class);
          label272: localIntent.addFlags(268435456);
          localIntent.putExtras(localBundle);
          this.notificationText = localGPSItem.getDescription();
          NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
          Notification localNotification = new Notification(2130837517, this.notificationBarTitle, System.currentTimeMillis());
          localNotification.flags = 16;
          PendingIntent localPendingIntent = PendingIntent.getActivity(getBaseContext(), k, localIntent, 0);
          localNotification.setLatestEventInfo(this, this.notificationTitle, this.notificationText, localPendingIntent);
          localNotificationManager.notify(k, localNotification);
        }
      }
      while (true)
      {
        writeToLog("Found location lon:" + paramLocation.getLongitude() + " lat:" + paramLocation.getLatitude() + " distance: " + localGPSItem.getDistance() + " to " + localGPSItem.getTitle());
        j++;
        break;
        localIntent = new Intent(this, GPSLocationText.class);
        break label272;
        if (i > localGPSItem.getDistance() - localGPSItem.getRadius())
          i = localGPSItem.getDistance() - localGPSItem.getRadius();
        Iterator localIterator = this.notifications.keySet().iterator();
        while (localIterator.hasNext())
        {
          Integer localInteger = (Integer)localIterator.next();
          if ((((GPSItem)this.notifications.get(localInteger)).getLatitude() != localGPSItem.getLatitude()) || (((GPSItem)this.notifications.get(localInteger)).getLongitude() != localGPSItem.getLongitude()))
            continue;
          this.notifications.remove(localInteger);
          ((NotificationManager)getSystemService("notification")).cancel(localInteger.intValue());
        }
      }
    }
    long l = 300000L;
    if (this.locationBefore == null)
    {
      this.locationBefore = paramLocation;
      this.timeBefore = System.currentTimeMillis();
      writeToLog("Next request trough: " + l);
      if (this.locationManager != null)
        this.locationManager.removeUpdates(this);
      this.handler.sendEmptyMessageDelayed(0, l);
      return;
    }
    float f1 = paramLocation.distanceTo(this.locationBefore);
    float f2 = f1 / 1000.0F / ((float)(System.currentTimeMillis() - this.timeBefore) / 3600000.0F);
    l = ()(3600000.0F * (i / 1000.0F / f2));
    if (f2 < 1.0F)
    {
      this.counter = (1 + this.counter);
      label748: writeToLog("Distance from last location: " + f1 + " speed: " + f2 + " km/h");
      writeToLog("near location: " + i + " next request: " + l + " counter:" + this.counter);
      if (this.counter != 1)
        break label928;
      l = 300000L;
    }
    while (true)
    {
      writeToLog("next request: " + l + " counter:" + this.counter);
      if (l < 300000L)
        l = 300000L;
      if (l <= 1800000L)
        break;
      l = 1800000L;
      break;
      this.counter = 0;
      break label748;
      label928: if (this.counter == 2)
      {
        l = 900000L;
        continue;
      }
      if (this.counter <= 2)
        continue;
      l = 1800000L;
    }
  }

  public void onProviderDisabled(String paramString)
  {
    writeToLog("GPS provider disabled");
    this.isListen = true;
    this.handler.sendEmptyMessage(1);
  }

  public void onProviderEnabled(String paramString)
  {
    writeToLog("GPS provider enabled");
    this.isListen = false;
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    this.isRunning = true;
    this.cachePath = (Environment.getExternalStorageDirectory() + "/AppBuilder/" + getPackageName() + "/cache.data");
    this.locationManager = ((LocationManager)getSystemService("location"));
    if (this.locationManager == null)
      this.isRunning = false;
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
    writeToLog("GPS provider status: " + paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.GPSNotification.GPSService
 * JD-Core Version:    0.6.0
 */