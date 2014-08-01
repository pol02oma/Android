package com.google.android.gms.auth;

import android.accounts.AccountManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.R.string;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.IOException;
import java.net.URISyntaxException;

public final class GoogleAuthUtil
{
  public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
  public static final String KEY_ANDROID_PACKAGE_NAME;
  public static final String KEY_CALLER_UID;
  public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";

  @Deprecated
  public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
  public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
  private static final ComponentName vo;
  private static final ComponentName vp;
  private static final Intent vq;
  private static final Intent vr;

  static
  {
    String str1;
    if (Build.VERSION.SDK_INT >= 11)
    {
      str1 = "callerUid";
      KEY_CALLER_UID = str1;
      if (Build.VERSION.SDK_INT < 14)
        break label107;
    }
    label107: for (String str2 = "androidPackageName"; ; str2 = "androidPackageName")
    {
      KEY_ANDROID_PACKAGE_NAME = str2;
      vo = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
      vp = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
      vq = new Intent().setPackage("com.google.android.gms").setComponent(vo);
      vr = new Intent().setPackage("com.google.android.gms").setComponent(vp);
      return;
      str1 = "callerUid";
      break;
    }
  }

  private static boolean K(String paramString)
  {
    return ("NetworkError".equals(paramString)) || ("ServiceUnavailable".equals(paramString)) || ("Timeout".equals(paramString));
  }

  private static boolean L(String paramString)
  {
    return ("BadAuthentication".equals(paramString)) || ("CaptchaRequired".equals(paramString)) || ("DeviceManagementRequiredOrSyncDisabled".equals(paramString)) || ("NeedPermission".equals(paramString)) || ("NeedsBrowser".equals(paramString)) || ("UserCancel".equals(paramString)) || ("AppDownloadRequired".equals(paramString));
  }

  private static String a(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    if (paramBundle == null)
      paramBundle = new Bundle();
    try
    {
      String str3 = getToken(paramContext, paramString1, paramString2, paramBundle);
      return str3;
    }
    catch (GooglePlayServicesAvailabilityException localGooglePlayServicesAvailabilityException)
    {
      PendingIntent localPendingIntent = GooglePlayServicesUtil.getErrorPendingIntent(localGooglePlayServicesAvailabilityException.getConnectionStatusCode(), paramContext, 0);
      Resources localResources = paramContext.getResources();
      Notification localNotification = new Notification(17301642, localResources.getString(R.string.auth_client_play_services_err_notification_msg), System.currentTimeMillis());
      localNotification.flags = (0x10 | localNotification.flags);
      String str1 = paramContext.getApplicationInfo().name;
      PackageManager localPackageManager;
      if (TextUtils.isEmpty(str1))
      {
        str1 = paramContext.getPackageName();
        localPackageManager = paramContext.getApplicationContext().getPackageManager();
      }
      try
      {
        ApplicationInfo localApplicationInfo2 = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
        localApplicationInfo1 = localApplicationInfo2;
        if (localApplicationInfo1 != null)
          str1 = localPackageManager.getApplicationLabel(localApplicationInfo1).toString();
        String str2 = localResources.getString(R.string.auth_client_requested_by_msg, new Object[] { str1 });
        switch (localGooglePlayServicesAvailabilityException.getConnectionStatusCode())
        {
        default:
          i = R.string.auth_client_using_bad_version_title;
          localNotification.setLatestEventInfo(paramContext, localResources.getString(i), str2, localPendingIntent);
          ((NotificationManager)paramContext.getSystemService("notification")).notify(39789, localNotification);
          throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        case 1:
        case 2:
        case 3:
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
        {
          ApplicationInfo localApplicationInfo1 = null;
          continue;
          int i = R.string.auth_client_needs_installation_title;
          continue;
          i = R.string.auth_client_needs_update_title;
          continue;
          i = R.string.auth_client_needs_enabling_title;
        }
      }
    }
    catch (UserRecoverableAuthException localUserRecoverableAuthException)
    {
    }
    throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
  }

  private static void b(Intent paramIntent)
  {
    if (paramIntent == null)
      throw new IllegalArgumentException("Callack cannot be null.");
    String str = paramIntent.toUri(1);
    try
    {
      Intent.parseUri(str, 1);
      return;
    }
    catch (URISyntaxException localURISyntaxException)
    {
    }
    throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
  }

  // ERROR //
  public static void clearToken(Context paramContext, String paramString)
    throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 187	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: astore_2
    //   5: ldc_w 269
    //   8: invokestatic 274	com/google/android/gms/internal/er:ad	(Ljava/lang/String;)V
    //   11: aload_2
    //   12: invokestatic 278	com/google/android/gms/auth/GoogleAuthUtil:s	(Landroid/content/Context;)V
    //   15: new 118	android/os/Bundle
    //   18: dup
    //   19: invokespecial 119	android/os/Bundle:<init>	()V
    //   22: astore_3
    //   23: aload_0
    //   24: invokevirtual 168	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   27: getfield 281	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   30: astore 4
    //   32: aload_3
    //   33: ldc_w 283
    //   36: aload 4
    //   38: invokevirtual 286	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   41: aload_3
    //   42: getstatic 40	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   45: invokevirtual 289	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   48: ifne +12 -> 60
    //   51: aload_3
    //   52: getstatic 40	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   55: aload 4
    //   57: invokevirtual 286	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   60: new 291	com/google/android/gms/common/a
    //   63: dup
    //   64: invokespecial 292	com/google/android/gms/common/a:<init>	()V
    //   67: astore 5
    //   69: aload_2
    //   70: getstatic 70	com/google/android/gms/auth/GoogleAuthUtil:vq	Landroid/content/Intent;
    //   73: aload 5
    //   75: iconst_1
    //   76: invokevirtual 296	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   79: ifeq +107 -> 186
    //   82: aload 5
    //   84: invokevirtual 300	com/google/android/gms/common/a:dm	()Landroid/os/IBinder;
    //   87: invokestatic 305	com/google/android/gms/internal/s$a:a	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/s;
    //   90: aload_1
    //   91: aload_3
    //   92: invokeinterface 310 3 0
    //   97: astore 10
    //   99: aload 10
    //   101: getstatic 315	com/google/android/gms/internal/do:wf	Ljava/lang/String;
    //   104: invokevirtual 318	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   107: astore 11
    //   109: aload 10
    //   111: ldc_w 320
    //   114: invokevirtual 323	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
    //   117: ifne +49 -> 166
    //   120: new 110	com/google/android/gms/auth/GoogleAuthException
    //   123: dup
    //   124: aload 11
    //   126: invokespecial 324	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   129: athrow
    //   130: astore 8
    //   132: ldc_w 326
    //   135: ldc_w 328
    //   138: aload 8
    //   140: invokestatic 334	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   143: pop
    //   144: new 106	java/io/IOException
    //   147: dup
    //   148: ldc_w 336
    //   151: invokespecial 337	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   154: athrow
    //   155: astore 7
    //   157: aload_2
    //   158: aload 5
    //   160: invokevirtual 341	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   163: aload 7
    //   165: athrow
    //   166: aload_2
    //   167: aload 5
    //   169: invokevirtual 341	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   172: return
    //   173: astore 6
    //   175: new 110	com/google/android/gms/auth/GoogleAuthException
    //   178: dup
    //   179: ldc_w 343
    //   182: invokespecial 324	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   185: athrow
    //   186: new 106	java/io/IOException
    //   189: dup
    //   190: ldc_w 345
    //   193: invokespecial 337	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   196: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   82	130	130	android/os/RemoteException
    //   82	130	155	finally
    //   132	155	155	finally
    //   175	186	155	finally
    //   82	130	173	java/lang/InterruptedException
  }

  public static String getToken(Context paramContext, String paramString1, String paramString2)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return getToken(paramContext, paramString1, paramString2, new Bundle());
  }

  // ERROR //
  public static String getToken(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 187	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: astore 4
    //   6: ldc_w 269
    //   9: invokestatic 274	com/google/android/gms/internal/er:ad	(Ljava/lang/String;)V
    //   12: aload 4
    //   14: invokestatic 278	com/google/android/gms/auth/GoogleAuthUtil:s	(Landroid/content/Context;)V
    //   17: aload_3
    //   18: ifnonnull +126 -> 144
    //   21: new 118	android/os/Bundle
    //   24: dup
    //   25: invokespecial 119	android/os/Bundle:<init>	()V
    //   28: astore 5
    //   30: aload_0
    //   31: invokevirtual 168	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   34: getfield 281	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   37: astore 6
    //   39: aload 5
    //   41: ldc_w 283
    //   44: aload 6
    //   46: invokevirtual 286	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   49: aload 5
    //   51: getstatic 40	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   54: invokevirtual 289	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   57: ifne +13 -> 70
    //   60: aload 5
    //   62: getstatic 40	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   65: aload 6
    //   67: invokevirtual 286	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   70: new 291	com/google/android/gms/common/a
    //   73: dup
    //   74: invokespecial 292	com/google/android/gms/common/a:<init>	()V
    //   77: astore 7
    //   79: aload 4
    //   81: getstatic 70	com/google/android/gms/auth/GoogleAuthUtil:vq	Landroid/content/Intent;
    //   84: aload 7
    //   86: iconst_1
    //   87: invokevirtual 296	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   90: ifeq +188 -> 278
    //   93: aload 7
    //   95: invokevirtual 300	com/google/android/gms/common/a:dm	()Landroid/os/IBinder;
    //   98: invokestatic 305	com/google/android/gms/internal/s$a:a	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/s;
    //   101: aload_1
    //   102: aload_2
    //   103: aload 5
    //   105: invokeinterface 349 4 0
    //   110: astore 12
    //   112: aload 12
    //   114: ldc_w 351
    //   117: invokevirtual 318	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   120: astore 13
    //   122: aload 13
    //   124: invokestatic 179	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   127: istore 14
    //   129: iload 14
    //   131: ifne +26 -> 157
    //   134: aload 4
    //   136: aload 7
    //   138: invokevirtual 341	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   141: aload 13
    //   143: areturn
    //   144: new 118	android/os/Bundle
    //   147: dup
    //   148: aload_3
    //   149: invokespecial 354	android/os/Bundle:<init>	(Landroid/os/Bundle;)V
    //   152: astore 5
    //   154: goto -124 -> 30
    //   157: aload 12
    //   159: ldc_w 356
    //   162: invokevirtual 318	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   165: astore 15
    //   167: aload 12
    //   169: ldc_w 358
    //   172: invokevirtual 362	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   175: checkcast 58	android/content/Intent
    //   178: astore 16
    //   180: aload 15
    //   182: invokestatic 364	com/google/android/gms/auth/GoogleAuthUtil:L	(Ljava/lang/String;)Z
    //   185: ifeq +52 -> 237
    //   188: new 114	com/google/android/gms/auth/UserRecoverableAuthException
    //   191: dup
    //   192: aload 15
    //   194: aload 16
    //   196: invokespecial 367	com/google/android/gms/auth/UserRecoverableAuthException:<init>	(Ljava/lang/String;Landroid/content/Intent;)V
    //   199: athrow
    //   200: astore 10
    //   202: ldc_w 326
    //   205: ldc_w 328
    //   208: aload 10
    //   210: invokestatic 334	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   213: pop
    //   214: new 106	java/io/IOException
    //   217: dup
    //   218: ldc_w 336
    //   221: invokespecial 337	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   224: athrow
    //   225: astore 9
    //   227: aload 4
    //   229: aload 7
    //   231: invokevirtual 341	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   234: aload 9
    //   236: athrow
    //   237: aload 15
    //   239: invokestatic 369	com/google/android/gms/auth/GoogleAuthUtil:K	(Ljava/lang/String;)Z
    //   242: ifeq +26 -> 268
    //   245: new 106	java/io/IOException
    //   248: dup
    //   249: aload 15
    //   251: invokespecial 337	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   254: athrow
    //   255: astore 8
    //   257: new 110	com/google/android/gms/auth/GoogleAuthException
    //   260: dup
    //   261: ldc_w 343
    //   264: invokespecial 324	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   267: athrow
    //   268: new 110	com/google/android/gms/auth/GoogleAuthException
    //   271: dup
    //   272: aload 15
    //   274: invokespecial 324	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   277: athrow
    //   278: new 106	java/io/IOException
    //   281: dup
    //   282: ldc_w 345
    //   285: invokespecial 337	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   288: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   93	129	200	android/os/RemoteException
    //   157	200	200	android/os/RemoteException
    //   237	255	200	android/os/RemoteException
    //   268	278	200	android/os/RemoteException
    //   93	129	225	finally
    //   157	200	225	finally
    //   202	225	225	finally
    //   237	255	225	finally
    //   257	268	225	finally
    //   268	278	225	finally
    //   93	129	255	java/lang/InterruptedException
    //   157	200	255	java/lang/InterruptedException
    //   237	255	255	java/lang/InterruptedException
    //   268	278	255	java/lang/InterruptedException
  }

  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    if (paramBundle == null)
      paramBundle = new Bundle();
    paramBundle.putBoolean("handle_notification", true);
    return a(paramContext, paramString1, paramString2, paramBundle);
  }

  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle, Intent paramIntent)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    b(paramIntent);
    if (paramBundle == null)
      paramBundle = new Bundle();
    paramBundle.putParcelable("callback_intent", paramIntent);
    paramBundle.putBoolean("handle_notification", true);
    return a(paramContext, paramString1, paramString2, paramBundle);
  }

  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle1, String paramString3, Bundle paramBundle2)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    if (TextUtils.isEmpty(paramString3))
      throw new IllegalArgumentException("Authority cannot be empty or null.");
    if (paramBundle1 == null)
      paramBundle1 = new Bundle();
    if (paramBundle2 == null)
      paramBundle2 = new Bundle();
    ContentResolver.validateSyncExtrasBundle(paramBundle2);
    paramBundle1.putString("authority", paramString3);
    paramBundle1.putBundle("sync_extras", paramBundle2);
    paramBundle1.putBoolean("handle_notification", true);
    return a(paramContext, paramString1, paramString2, paramBundle1);
  }

  @Deprecated
  public static void invalidateToken(Context paramContext, String paramString)
  {
    AccountManager.get(paramContext).invalidateAuthToken("com.google", paramString);
  }

  private static void s(Context paramContext)
    throws GooglePlayServicesAvailabilityException, GoogleAuthException
  {
    try
    {
      GooglePlayServicesUtil.s(paramContext);
      return;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      throw new GooglePlayServicesAvailabilityException(localGooglePlayServicesRepairableException.getConnectionStatusCode(), localGooglePlayServicesRepairableException.getMessage(), localGooglePlayServicesRepairableException.getIntent());
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
    }
    throw new GoogleAuthException(localGooglePlayServicesNotAvailableException.getMessage());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.auth.GoogleAuthUtil
 * JD-Core Version:    0.6.0
 */