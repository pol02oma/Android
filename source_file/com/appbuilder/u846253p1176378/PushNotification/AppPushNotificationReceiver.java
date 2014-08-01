package com.appbuilder.u846253p1176378.PushNotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import com.appbuilder.u846253p1176378.AppBuilder;
import com.appbuilder.u846253p1176378.Statics;
import com.flurry.android.FlurryAgent;
import com.google.android.c2dm.C2DMBaseReceiver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class AppPushNotificationReceiver extends C2DMBaseReceiver
{
  private final String TAG = "PUSHNS_receiver";
  private AppPushNotificationMessage messageObject;
  private String notificationBarTitle = "iBuildApp message";
  private String notificationText = "Notification brief description";
  private String notificationTitle = "Notification Title";
  private String pushRegistrationUrl = "http://ibuildapp.com/pushns.registration.php?project=1176378&platform=android";

  public AppPushNotificationReceiver()
  {
    super("");
  }

  public AppPushNotificationReceiver(String paramString)
  {
    super(paramString);
  }

  private String getAppStatus()
  {
    return getSharedPreferences(getString(2131100035), 2).getString(getPackageName(), getString(2131100037));
  }

  private String md5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      int i = arrayOfByte.length;
      StringBuilder localStringBuilder = new StringBuilder(i << 1);
      for (int j = 0; j < i; j++)
      {
        localStringBuilder.append(Character.forDigit((0xF0 & arrayOfByte[j]) >> 4, 16));
        localStringBuilder.append(Character.forDigit(0xF & arrayOfByte[j], 16));
      }
      String str = localStringBuilder.toString();
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      Log.w("WebPlugin CREATE MD5", localNoSuchAlgorithmException);
    }
    return null;
  }

  public void onError(Context paramContext, String paramString)
  {
    Log.w("PUSHNS_receiver", "onError: " + paramString);
  }

  protected void onMessage(Context paramContext, Intent paramIntent)
  {
    Log.e("PUSHNS_receiver", "onMessage");
    Log.e("PUSHNS_receiver", "App status = " + getAppStatus());
    Log.e("PUSHNS_receiver", "All data = " + paramIntent.getExtras().toString());
    String str1 = paramIntent.getStringExtra("type");
    Log.e("PUSHNS_receiver", "Type = " + str1);
    switch (Integer.parseInt(str1))
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    String str2;
    do
    {
      return;
      Log.e("PUSHNS_receiver", "TYPE = PushNS");
      FlurryAgent.logEvent("PushNS");
      int i = -1;
      if (!TextUtils.isEmpty(paramIntent.getStringExtra("order")));
      try
      {
        int j = Integer.parseInt(paramIntent.getStringExtra("order"));
        i = j;
        if ((!TextUtils.isEmpty(paramIntent.getStringExtra("message"))) && (TextUtils.isEmpty(paramIntent.getStringExtra("title"))) && (TextUtils.isEmpty(paramIntent.getStringExtra("descr"))) && (TextUtils.isEmpty(paramIntent.getStringExtra("image"))) && (i == -1))
        {
          Log.e("PUSHNS_receiver", "push with status bar message only");
          this.notificationBarTitle = (getResources().getString(2131099718) + " " + getResources().getString(2131100032));
          this.notificationTitle = (getResources().getString(2131099718) + " " + getResources().getString(2131100032));
          this.notificationText = paramIntent.getStringExtra("message");
          try
          {
            Intent localIntent5 = new Intent(this, AppBuilder.class);
            NotificationManager localNotificationManager3 = (NotificationManager)getSystemService("notification");
            Notification localNotification3 = new Notification(2130837517, this.notificationBarTitle, System.currentTimeMillis());
            localNotification3.flags = 17;
            localNotification3.defaults = 1;
            PendingIntent localPendingIntent3 = PendingIntent.getActivity(getBaseContext(), 0, localIntent5, 268435456);
            localNotification3.setLatestEventInfo(this, this.notificationTitle, this.notificationText, localPendingIntent3);
            localNotificationManager3.notify(1, localNotification3);
            return;
          }
          catch (Exception localException3)
          {
            return;
          }
        }
      }
      catch (Exception localException4)
      {
        while (true)
          i = -1;
        if ((!TextUtils.isEmpty(paramIntent.getStringExtra("message"))) && (TextUtils.isEmpty(paramIntent.getStringExtra("title"))) && (TextUtils.isEmpty(paramIntent.getStringExtra("descr"))));
        for (AppPushNotificationMessage localAppPushNotificationMessage = new AppPushNotificationMessage(paramIntent.getStringExtra("package"), "", paramIntent.getStringExtra("message"), paramIntent.getStringExtra("message"), paramIntent.getStringExtra("image"), new Date(), i); ; localAppPushNotificationMessage = new AppPushNotificationMessage(paramIntent.getStringExtra("package"), paramIntent.getStringExtra("title"), paramIntent.getStringExtra("message"), paramIntent.getStringExtra("descr"), paramIntent.getStringExtra("image"), new Date(), i))
        {
          this.messageObject = new AppPushNotificationMessage(localAppPushNotificationMessage.packageName, localAppPushNotificationMessage.titleText, localAppPushNotificationMessage.statusBarText, localAppPushNotificationMessage.descriptionText, localAppPushNotificationMessage.imgUrl, new Date(), i);
          Log.e("PUSHNS_receiver", " Normal push = " + this.messageObject.uid + " messageObject.statusBar = " + this.messageObject.statusBarText + " messageObject.description = " + this.messageObject.descriptionText + " messageObject.title = " + this.messageObject.titleText + " messageObject.imgUrl = " + this.messageObject.imgUrl + " messageObject.path = " + this.messageObject.imagePath + " messageObject.widgetOrder = " + this.messageObject.widgetOrder);
          AppPushNotificationDB.init(paramContext);
          long l2 = AppPushNotificationDB.insertNotification(this.messageObject);
          this.messageObject.uid = l2;
          Log.e("PUSHNS_receiver", " Push in DB. UID = " + this.messageObject.uid);
          new CustomThread(this.messageObject, null).start();
          return;
        }
      }
      Log.e("PUSHNS_receiver", " TYPE = PushNS ConfigurationUpdate");
      FlurryAgent.logEvent("PushNS ConfigurationUpdate");
      if (getAppStatus().compareToIgnoreCase(getString(2131100036)) == 0)
      {
        this.messageObject = new AppPushNotificationMessage("", "", "", getString(2131100038), "", new Date(), -1);
        AppPushNotificationDB.init(paramContext);
        long l1 = AppPushNotificationDB.insertNotification(this.messageObject);
        this.messageObject.uid = l1;
        Log.e("PUSHNS_receiver", " Push in DB. UID = " + this.messageObject.uid);
        Intent localIntent3 = new Intent(Statics.BROADCAST_UID);
        sendBroadcast(localIntent3);
        this.notificationBarTitle = (getResources().getString(2131099718) + " " + getResources().getString(2131100032));
        this.notificationTitle = (getResources().getString(2131099718) + " " + getResources().getString(2131100032));
        this.notificationText = paramIntent.getStringExtra("message");
        try
        {
          Intent localIntent4 = new Intent(this, AppBuilder.class);
          NotificationManager localNotificationManager2 = (NotificationManager)getSystemService("notification");
          Notification localNotification2 = new Notification(2130837517, this.notificationBarTitle, System.currentTimeMillis());
          localNotification2.flags = 17;
          localNotification2.defaults = 1;
          PendingIntent localPendingIntent2 = PendingIntent.getActivity(getBaseContext(), 0, localIntent4, 268435456);
          localNotification2.setLatestEventInfo(this, this.notificationTitle, this.notificationText, localPendingIntent2);
          localNotificationManager2.notify((int)this.messageObject.uid, localNotification2);
          return;
        }
        catch (Exception localException2)
        {
          return;
        }
      }
      this.notificationBarTitle = (getResources().getString(2131099718) + " " + getResources().getString(2131100032));
      this.notificationTitle = (getResources().getString(2131099718) + " " + getResources().getString(2131100032));
      this.notificationText = paramIntent.getStringExtra("message");
      try
      {
        Intent localIntent2 = new Intent(this, AppBuilder.class);
        NotificationManager localNotificationManager1 = (NotificationManager)getSystemService("notification");
        Notification localNotification1 = new Notification(2130837517, this.notificationBarTitle, System.currentTimeMillis());
        localNotification1.flags = 17;
        localNotification1.defaults = 1;
        PendingIntent localPendingIntent1 = PendingIntent.getActivity(getBaseContext(), 0, localIntent2, 268435456);
        localNotification1.setLatestEventInfo(this, this.notificationTitle, this.notificationText, localPendingIntent1);
        localNotificationManager1.notify(-1, localNotification1);
        return;
      }
      catch (Exception localException1)
      {
        return;
      }
      Log.e("PUSHNS_receiver", "TYPE = PushNS Widget");
      FlurryAgent.logEvent("PushNS Widget");
      str2 = paramIntent.getStringExtra("package");
    }
    while ((str2 == null) || (str2.length() <= 0));
    Intent localIntent1 = new Intent(str2 + ".PUSH");
    localIntent1.putExtras(paramIntent.getExtras());
    sendBroadcast(localIntent1);
  }

  public void onRegistered(Context paramContext, String paramString)
  {
    Log.d("PUSHNS_receiver", "Registration ID arrived: Fantastic!!!");
    Log.d("PUSHNS_receiver", paramString);
    String str1 = md5(Settings.Secure.getString(getContentResolver(), "android_id"));
    this.pushRegistrationUrl = (this.pushRegistrationUrl + "&device=" + str1);
    this.pushRegistrationUrl = (this.pushRegistrationUrl + "&token=" + paramString);
    Log.d("PUSHNS_receiver", this.pushRegistrationUrl);
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new URL(this.pushRegistrationUrl).openStream()));
      StringBuilder localStringBuilder = new StringBuilder();
      while (true)
      {
        String str2 = localBufferedReader.readLine();
        if (str2 == null)
          break;
        localStringBuilder.append(str2);
      }
      localBufferedReader.close();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void onUnregistered(Context paramContext)
  {
    Log.w("PUSHNS_receiver", "onUnregistered");
  }

  private class CustomThread extends Thread
  {
    private AppPushNotificationMessage pushMessage;

    private CustomThread(AppPushNotificationMessage arg2)
    {
      Object localObject;
      this.pushMessage = localObject;
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: invokespecial 27	java/lang/Thread:run	()V
      //   4: aload_0
      //   5: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   8: getfield 33	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:imgUrl	Ljava/lang/String;
      //   11: invokestatic 39	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   14: ifne +183 -> 197
      //   17: ldc 41
      //   19: ldc 43
      //   21: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   24: pop
      //   25: new 51	java/lang/StringBuilder
      //   28: dup
      //   29: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   32: invokestatic 58	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
      //   35: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   38: ldc 64
      //   40: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   43: aload_0
      //   44: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   47: invokevirtual 73	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:getPackageName	()Ljava/lang/String;
      //   50: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   53: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   56: astore 25
      //   58: aload_0
      //   59: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   62: getfield 33	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:imgUrl	Ljava/lang/String;
      //   65: aload 25
      //   67: invokestatic 82	com/appbuilder/sdk/android/Utils:downloadFile	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   70: astore 26
      //   72: aload 26
      //   74: invokestatic 39	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   77: ifeq +7 -> 84
      //   80: ldc 84
      //   82: astore 26
      //   84: aload_0
      //   85: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   88: getfield 88	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:uid	J
      //   91: aload 26
      //   93: invokestatic 94	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationDB:updateNotificationImage	(JLjava/lang/String;)V
      //   96: ldc 41
      //   98: new 51	java/lang/StringBuilder
      //   101: dup
      //   102: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   105: ldc 96
      //   107: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   110: aload_0
      //   111: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   114: getfield 88	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:uid	J
      //   117: invokevirtual 99	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   120: ldc 101
      //   122: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   125: aload_0
      //   126: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   129: getfield 104	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:titleText	Ljava/lang/String;
      //   132: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   135: ldc 106
      //   137: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   140: aload_0
      //   141: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   144: getfield 109	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:statusBarText	Ljava/lang/String;
      //   147: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   150: ldc 111
      //   152: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   155: aload_0
      //   156: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   159: getfield 114	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:descriptionText	Ljava/lang/String;
      //   162: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   165: ldc 116
      //   167: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   170: aload_0
      //   171: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   174: getfield 33	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:imgUrl	Ljava/lang/String;
      //   177: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   180: ldc 118
      //   182: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   185: aload 26
      //   187: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   190: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   193: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   196: pop
      //   197: invokestatic 122	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationDB:selectAllNotifications	()Ljava/util/List;
      //   200: invokeinterface 128 1 0
      //   205: astore_1
      //   206: aload_1
      //   207: invokeinterface 134 1 0
      //   212: ifeq +32 -> 244
      //   215: ldc 41
      //   217: aload_1
      //   218: invokeinterface 138 1 0
      //   223: checkcast 29	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage
      //   226: invokevirtual 139	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:toString	()Ljava/lang/String;
      //   229: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   232: pop
      //   233: ldc 41
      //   235: ldc 141
      //   237: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   240: pop
      //   241: goto -35 -> 206
      //   244: invokestatic 144	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationDB:getXmlUrl	()Ljava/lang/String;
      //   247: astore_2
      //   248: ldc 41
      //   250: new 51	java/lang/StringBuilder
      //   253: dup
      //   254: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   257: ldc 146
      //   259: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   262: aload_2
      //   263: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   266: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   269: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   272: pop
      //   273: aload_0
      //   274: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   277: aload_2
      //   278: invokestatic 152	com/appbuilder/u846253p1176378/tools/Tools:updateAppConfig	(Landroid/content/Context;Ljava/lang/String;)Lcom/appbuilder/u846253p1176378/AppConfigure;
      //   281: astore 4
      //   283: aload 4
      //   285: ifnull +474 -> 759
      //   288: ldc 41
      //   290: ldc 154
      //   292: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   295: pop
      //   296: aload 4
      //   298: aload_0
      //   299: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   302: getfield 158	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:widgetOrder	I
      //   305: invokevirtual 164	com/appbuilder/u846253p1176378/AppConfigure:getWidgetWithOrder	(I)Lcom/appbuilder/sdk/android/Widget;
      //   308: astore 16
      //   310: aload 16
      //   312: ifnull +424 -> 736
      //   315: new 51	java/lang/StringBuilder
      //   318: dup
      //   319: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   322: aload 16
      //   324: invokevirtual 169	com/appbuilder/sdk/android/Widget:getPluginPackage	()Ljava/lang/String;
      //   327: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   330: ldc 171
      //   332: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   335: aload 16
      //   337: invokevirtual 174	com/appbuilder/sdk/android/Widget:getPluginName	()Ljava/lang/String;
      //   340: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   343: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   346: invokestatic 180	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   349: pop
      //   350: aload_0
      //   351: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   354: getfield 88	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:uid	J
      //   357: iconst_1
      //   358: invokestatic 184	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationDB:updateNotificationPackageStatus	(JZ)V
      //   361: ldc 41
      //   363: new 51	java/lang/StringBuilder
      //   366: dup
      //   367: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   370: ldc 186
      //   372: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   375: aload 16
      //   377: invokevirtual 169	com/appbuilder/sdk/android/Widget:getPluginPackage	()Ljava/lang/String;
      //   380: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   383: ldc 171
      //   385: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   388: aload 16
      //   390: invokevirtual 174	com/appbuilder/sdk/android/Widget:getPluginName	()Ljava/lang/String;
      //   393: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   396: ldc 188
      //   398: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   401: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   404: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   407: pop
      //   408: new 190	android/content/Intent
      //   411: dup
      //   412: getstatic 195	com/appbuilder/u846253p1176378/Statics:BROADCAST_UID	Ljava/lang/String;
      //   415: invokespecial 198	android/content/Intent:<init>	(Ljava/lang/String;)V
      //   418: astore 6
      //   420: aload_0
      //   421: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   424: aload 6
      //   426: invokevirtual 202	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:sendBroadcast	(Landroid/content/Intent;)V
      //   429: aload_0
      //   430: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   433: new 51	java/lang/StringBuilder
      //   436: dup
      //   437: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   440: aload_0
      //   441: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   444: invokevirtual 206	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:getResources	()Landroid/content/res/Resources;
      //   447: ldc 207
      //   449: invokevirtual 213	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   452: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   455: ldc 215
      //   457: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   460: aload_0
      //   461: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   464: invokevirtual 206	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:getResources	()Landroid/content/res/Resources;
      //   467: ldc 216
      //   469: invokevirtual 213	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   472: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   475: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   478: invokestatic 220	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:access$102	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;Ljava/lang/String;)Ljava/lang/String;
      //   481: pop
      //   482: aload_0
      //   483: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   486: new 51	java/lang/StringBuilder
      //   489: dup
      //   490: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   493: aload_0
      //   494: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   497: invokevirtual 206	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:getResources	()Landroid/content/res/Resources;
      //   500: ldc 207
      //   502: invokevirtual 213	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   505: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   508: ldc 215
      //   510: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   513: aload_0
      //   514: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   517: invokevirtual 206	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:getResources	()Landroid/content/res/Resources;
      //   520: ldc 216
      //   522: invokevirtual 213	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   525: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   528: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   531: invokestatic 223	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:access$202	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;Ljava/lang/String;)Ljava/lang/String;
      //   534: pop
      //   535: aload_0
      //   536: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   539: aload_0
      //   540: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   543: getfield 109	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:statusBarText	Ljava/lang/String;
      //   546: invokestatic 226	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:access$302	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;Ljava/lang/String;)Ljava/lang/String;
      //   549: pop
      //   550: new 190	android/content/Intent
      //   553: dup
      //   554: aload_0
      //   555: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   558: ldc 228
      //   560: invokespecial 231	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
      //   563: astore 10
      //   565: aload_0
      //   566: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   569: ldc 233
      //   571: invokevirtual 237	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   574: checkcast 239	android/app/NotificationManager
      //   577: astore 12
      //   579: new 241	android/app/Notification
      //   582: dup
      //   583: ldc 242
      //   585: aload_0
      //   586: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   589: invokestatic 246	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:access$100	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;)Ljava/lang/String;
      //   592: invokestatic 252	java/lang/System:currentTimeMillis	()J
      //   595: invokespecial 255	android/app/Notification:<init>	(ILjava/lang/CharSequence;J)V
      //   598: astore 13
      //   600: aload 13
      //   602: bipush 17
      //   604: putfield 258	android/app/Notification:flags	I
      //   607: aload 13
      //   609: iconst_1
      //   610: putfield 261	android/app/Notification:defaults	I
      //   613: aload_0
      //   614: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   617: invokevirtual 265	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:getBaseContext	()Landroid/content/Context;
      //   620: iconst_0
      //   621: aload 10
      //   623: ldc_w 266
      //   626: invokestatic 272	android/app/PendingIntent:getActivity	(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
      //   629: astore 14
      //   631: aload 13
      //   633: aload_0
      //   634: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   637: aload_0
      //   638: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   641: invokestatic 275	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:access$200	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;)Ljava/lang/String;
      //   644: aload_0
      //   645: getfield 12	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:this$0	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;
      //   648: invokestatic 278	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver:access$300	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver;)Ljava/lang/String;
      //   651: aload 14
      //   653: invokevirtual 282	android/app/Notification:setLatestEventInfo	(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V
      //   656: aload 12
      //   658: aload_0
      //   659: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   662: getfield 88	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:uid	J
      //   665: l2i
      //   666: aload 13
      //   668: invokevirtual 286	android/app/NotificationManager:notify	(ILandroid/app/Notification;)V
      //   671: return
      //   672: astore 18
      //   674: aload_0
      //   675: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   678: getfield 88	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:uid	J
      //   681: iconst_0
      //   682: invokestatic 184	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationDB:updateNotificationPackageStatus	(JZ)V
      //   685: ldc 41
      //   687: new 51	java/lang/StringBuilder
      //   690: dup
      //   691: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   694: ldc 186
      //   696: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   699: aload 16
      //   701: invokevirtual 169	com/appbuilder/sdk/android/Widget:getPluginPackage	()Ljava/lang/String;
      //   704: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   707: ldc 171
      //   709: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   712: aload 16
      //   714: invokevirtual 174	com/appbuilder/sdk/android/Widget:getPluginName	()Ljava/lang/String;
      //   717: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   720: ldc_w 288
      //   723: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   726: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   729: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   732: pop
      //   733: goto -325 -> 408
      //   736: aload_0
      //   737: getfield 17	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationReceiver$CustomThread:pushMessage	Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage;
      //   740: getfield 88	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationMessage:uid	J
      //   743: iconst_0
      //   744: invokestatic 184	com/appbuilder/u846253p1176378/PushNotification/AppPushNotificationDB:updateNotificationPackageStatus	(JZ)V
      //   747: ldc 41
      //   749: ldc_w 290
      //   752: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   755: pop
      //   756: goto -348 -> 408
      //   759: ldc 41
      //   761: ldc_w 292
      //   764: invokestatic 49	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   767: pop
      //   768: goto -360 -> 408
      //   771: astore 11
      //   773: return
      //
      // Exception table:
      //   from	to	target	type
      //   315	408	672	java/lang/ClassNotFoundException
      //   550	671	771	java/lang/Exception
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.PushNotification.AppPushNotificationReceiver
 * JD-Core Version:    0.6.0
 */