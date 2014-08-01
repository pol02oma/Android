package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.internal.ef;
import com.google.android.gms.internal.ek;
import com.google.android.gms.internal.fr;
import java.io.UnsupportedEncodingException;

public final class GooglePlayServicesUtil
{
  public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 4323000;
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  static final byte[][] yL;
  static final byte[][] yM;
  static final byte[][] yN;
  static final byte[][] yO;
  private static final byte[][] yP;
  private static final byte[][] yQ;
  static final byte[][] yR;
  public static boolean yS;
  public static boolean yT;
  static boolean yU;
  private static int yV;
  private static final Object yW;

  static
  {
    byte[][] arrayOfByte1 = new byte[2][];
    arrayOfByte1[0] = Z("");
    arrayOfByte1[1] = Z("");
    yL = arrayOfByte1;
    byte[][] arrayOfByte2 = new byte[2][];
    arrayOfByte2[0] = Z("");
    arrayOfByte2[1] = Z("");
    yM = arrayOfByte2;
    byte[][] arrayOfByte3 = new byte[1][];
    arrayOfByte3[0] = Z("");
    yN = arrayOfByte3;
    byte[][] arrayOfByte4 = new byte[2][];
    arrayOfByte4[0] = Z("");
    arrayOfByte4[1] = Z("");
    yO = arrayOfByte4;
    byte[][][] arrayOfByte = new byte[4][][];
    arrayOfByte[0] = yL;
    arrayOfByte[1] = yM;
    arrayOfByte[2] = yN;
    arrayOfByte[3] = yO;
    yP = a(arrayOfByte);
    byte[][] arrayOfByte5 = new byte[3][];
    arrayOfByte5[0] = yL[0];
    arrayOfByte5[1] = yM[0];
    arrayOfByte5[2] = yO[0];
    yQ = arrayOfByte5;
    byte[][] arrayOfByte6 = new byte[1][];
    arrayOfByte6[0] = Z("");
    yR = arrayOfByte6;
    yS = false;
    yT = false;
    yU = false;
    yV = -1;
    yW = new Object();
  }

  static boolean E(int paramInt)
  {
    int i = 1;
    switch (F(paramInt))
    {
    default:
      i = 0;
    case 1:
    case 0:
      do
        return i;
      while (!jdMethod_do());
      return false;
    case 2:
    }
    return false;
  }

  private static int F(int paramInt)
  {
    if (paramInt == -1)
      paramInt = 2;
    return paramInt;
  }

  private static byte[] Z(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("ISO-8859-1");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new AssertionError(localUnsupportedEncodingException);
  }

  public static Dialog a(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener, int paramInt3)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity).setMessage(b(paramActivity, paramInt1, paramInt3));
    if (paramOnCancelListener != null)
      localBuilder.setOnCancelListener(paramOnCancelListener);
    ef localef = new ef(paramActivity, a(paramActivity, paramInt1, paramInt3), paramInt2);
    String str = b(paramActivity, paramInt1);
    if (str != null)
      localBuilder.setPositiveButton(str, localef);
    switch (paramInt1)
    {
    default:
      Log.e("GooglePlayServicesUtil", "Unexpected error code " + paramInt1);
      return localBuilder.create();
    case 0:
      return null;
    case 4:
    case 6:
      return localBuilder.create();
    case 1:
      return localBuilder.setTitle(R.string.common_google_play_services_install_title).create();
    case 3:
      return localBuilder.setTitle(R.string.common_google_play_services_enable_title).create();
    case 2:
      return localBuilder.setTitle(R.string.common_google_play_services_update_title).create();
    case 9:
      Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
      return localBuilder.setTitle(R.string.common_google_play_services_unsupported_title).create();
    case 7:
      Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
      return localBuilder.setTitle(R.string.common_google_play_services_network_error_title).create();
    case 8:
      Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
      return localBuilder.create();
    case 10:
      Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
      return localBuilder.create();
    case 5:
      Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
      return localBuilder.setTitle(R.string.common_google_play_services_invalid_account_title).create();
    case 11:
      Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
      return localBuilder.create();
    case 12:
    }
    Log.e("GooglePlayServicesUtil", "The date of the device is not valid.");
    return localBuilder.setTitle(R.string.common_google_play_services_unsupported_title).create();
  }

  public static Intent a(Context paramContext, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default:
      return null;
    case 1:
    case 2:
      if (E(paramInt2))
      {
        if (u(paramContext))
          return ek.ai("com.google.android.gms");
        return ek.ah("com.google.android.apps.bazaar");
      }
      return ek.ah("com.google.android.gms");
    case 3:
      return ek.af("com.google.android.gms");
    case 12:
    }
    return ek.eh();
  }

  public static boolean a(Resources paramResources)
  {
    if (paramResources == null);
    while (true)
    {
      return false;
      if ((0xF & paramResources.getConfiguration().screenLayout) > 3);
      for (int i = 1; ((fr.eJ()) && (i != 0)) || (b(paramResources)); i = 0)
        return true;
    }
  }

  // ERROR //
  private static byte[] a(android.content.pm.PackageInfo paramPackageInfo, byte[][] paramArrayOfByte)
  {
    // Byte code:
    //   0: ldc_w 262
    //   3: invokestatic 268	java/security/cert/CertificateFactory:getInstance	(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
    //   6: astore 4
    //   8: aload_0
    //   9: getfield 274	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   12: arraylength
    //   13: iconst_1
    //   14: if_icmpeq +26 -> 40
    //   17: ldc 146
    //   19: ldc_w 276
    //   22: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   25: pop
    //   26: aconst_null
    //   27: areturn
    //   28: astore_2
    //   29: ldc 146
    //   31: ldc_w 281
    //   34: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   37: pop
    //   38: aconst_null
    //   39: areturn
    //   40: new 283	java/io/ByteArrayInputStream
    //   43: dup
    //   44: aload_0
    //   45: getfield 274	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   48: iconst_0
    //   49: aaload
    //   50: invokevirtual 289	android/content/pm/Signature:toByteArray	()[B
    //   53: invokespecial 292	java/io/ByteArrayInputStream:<init>	([B)V
    //   56: astore 5
    //   58: aload 4
    //   60: aload 5
    //   62: invokevirtual 296	java/security/cert/CertificateFactory:generateCertificate	(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
    //   65: checkcast 298	java/security/cert/X509Certificate
    //   68: astore 8
    //   70: aload 8
    //   72: invokevirtual 301	java/security/cert/X509Certificate:checkValidity	()V
    //   75: aload_0
    //   76: getfield 274	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   79: iconst_0
    //   80: aaload
    //   81: invokevirtual 289	android/content/pm/Signature:toByteArray	()[B
    //   84: astore 13
    //   86: iconst_0
    //   87: istore 14
    //   89: iload 14
    //   91: aload_1
    //   92: arraylength
    //   93: if_icmpge +67 -> 160
    //   96: aload_1
    //   97: iload 14
    //   99: aaload
    //   100: astore 16
    //   102: aload 16
    //   104: aload 13
    //   106: invokestatic 307	java/util/Arrays:equals	([B[B)Z
    //   109: ifeq +45 -> 154
    //   112: aload 16
    //   114: areturn
    //   115: astore 6
    //   117: ldc 146
    //   119: ldc_w 309
    //   122: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   125: pop
    //   126: aconst_null
    //   127: areturn
    //   128: astore 11
    //   130: ldc 146
    //   132: ldc_w 311
    //   135: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   138: pop
    //   139: aconst_null
    //   140: areturn
    //   141: astore 9
    //   143: ldc 146
    //   145: ldc_w 313
    //   148: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   151: pop
    //   152: aconst_null
    //   153: areturn
    //   154: iinc 14 1
    //   157: goto -68 -> 89
    //   160: ldc 146
    //   162: iconst_2
    //   163: invokestatic 317	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   166: ifeq +34 -> 200
    //   169: ldc 146
    //   171: new 148	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   178: ldc_w 319
    //   181: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: aload 13
    //   186: iconst_0
    //   187: invokestatic 325	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   190: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: invokestatic 328	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   199: pop
    //   200: aconst_null
    //   201: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	28	java/security/cert/CertificateException
    //   58	70	115	java/security/cert/CertificateException
    //   70	75	128	java/security/cert/CertificateExpiredException
    //   70	75	141	java/security/cert/CertificateNotYetValidException
  }

  private static byte[][] a(byte[][][] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      k += paramArrayOfByte[j].length;
      j++;
    }
    byte[][] arrayOfByte = new byte[k][];
    int m = paramArrayOfByte.length;
    int n = 0;
    int i2;
    for (int i1 = 0; n < m; i1 = i2)
    {
      byte[][] arrayOfByte1 = paramArrayOfByte[n];
      i2 = i1;
      int i3 = 0;
      while (i3 < arrayOfByte1.length)
      {
        int i4 = i2 + 1;
        arrayOfByte[i2] = arrayOfByte1[i3];
        i3++;
        i2 = i4;
      }
      n++;
    }
    return arrayOfByte;
  }

  public static String b(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    switch (paramInt)
    {
    default:
      return localResources.getString(17039370);
    case 1:
      return localResources.getString(R.string.common_google_play_services_install_button);
    case 3:
      return localResources.getString(R.string.common_google_play_services_enable_button);
    case 2:
    }
    return localResources.getString(R.string.common_google_play_services_update_button);
  }

  public static String b(Context paramContext, int paramInt1, int paramInt2)
  {
    Resources localResources = paramContext.getResources();
    String str;
    switch (paramInt1)
    {
    case 4:
    case 6:
    case 8:
    case 10:
    case 11:
    default:
      str = localResources.getString(R.string.common_google_play_services_unknown_issue);
    case 1:
    case 3:
    case 2:
      do
      {
        return str;
        if (a(paramContext.getResources()));
        for (str = localResources.getString(R.string.common_google_play_services_install_text_tablet); E(paramInt2); str = localResources.getString(R.string.common_google_play_services_install_text_phone))
          return str + " (via Bazaar)";
        return localResources.getString(R.string.common_google_play_services_enable_text);
        str = localResources.getString(R.string.common_google_play_services_update_text);
      }
      while (!E(paramInt2));
      return str + " (via Bazaar)";
    case 9:
      return localResources.getString(R.string.common_google_play_services_unsupported_text);
    case 7:
      return localResources.getString(R.string.common_google_play_services_network_error_text);
    case 5:
      return localResources.getString(R.string.common_google_play_services_invalid_account_text);
    case 12:
    }
    return localResources.getString(R.string.common_google_play_services_unsupported_date_text);
  }

  private static boolean b(Resources paramResources)
  {
    Configuration localConfiguration = paramResources.getConfiguration();
    boolean bool = fr.eL();
    int i = 0;
    if (bool)
    {
      int j = 0xF & localConfiguration.screenLayout;
      i = 0;
      if (j <= 3)
      {
        int k = localConfiguration.smallestScreenWidthDp;
        i = 0;
        if (k >= 600)
          i = 1;
      }
    }
    return i;
  }

  public static boolean jdMethod_do()
  {
    if (yS)
      return yT;
    return "user".equals(Build.TYPE);
  }

  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2)
  {
    return a(paramInt1, paramActivity, paramInt2, null, -1);
  }

  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return a(paramInt1, paramActivity, paramInt2, paramOnCancelListener, -1);
  }

  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2)
  {
    Intent localIntent = a(paramContext, paramInt1, -1);
    if (localIntent == null)
      return null;
    return PendingIntent.getActivity(paramContext, paramInt2, localIntent, 268435456);
  }

  public static String getErrorString(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "UNKNOWN_ERROR_CODE";
    case 0:
      return "SUCCESS";
    case 1:
      return "SERVICE_MISSING";
    case 2:
      return "SERVICE_VERSION_UPDATE_REQUIRED";
    case 3:
      return "SERVICE_DISABLED";
    case 4:
      return "SIGN_IN_REQUIRED";
    case 5:
      return "INVALID_ACCOUNT";
    case 6:
      return "RESOLUTION_REQUIRED";
    case 7:
      return "NETWORK_ERROR";
    case 8:
      return "INTERNAL_ERROR";
    case 9:
      return "SERVICE_INVALID";
    case 10:
      return "DEVELOPER_ERROR";
    case 11:
      return "LICENSE_CHECK_FAILED";
    case 12:
    }
    return "DATE_INVALID";
  }

  // ERROR //
  public static String getOpenSourceSoftwareLicenseInfo(Context paramContext)
  {
    // Byte code:
    //   0: new 446	android/net/Uri$Builder
    //   3: dup
    //   4: invokespecial 447	android/net/Uri$Builder:<init>	()V
    //   7: ldc_w 449
    //   10: invokevirtual 453	android/net/Uri$Builder:scheme	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   13: ldc 11
    //   15: invokevirtual 456	android/net/Uri$Builder:authority	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   18: ldc_w 458
    //   21: invokevirtual 461	android/net/Uri$Builder:appendPath	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   24: ldc_w 463
    //   27: invokevirtual 461	android/net/Uri$Builder:appendPath	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   30: invokevirtual 467	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   33: astore_1
    //   34: aload_0
    //   35: invokevirtual 471	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   38: aload_1
    //   39: invokevirtual 477	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   42: astore 4
    //   44: new 479	java/util/Scanner
    //   47: dup
    //   48: aload 4
    //   50: invokespecial 482	java/util/Scanner:<init>	(Ljava/io/InputStream;)V
    //   53: ldc_w 484
    //   56: invokevirtual 488	java/util/Scanner:useDelimiter	(Ljava/lang/String;)Ljava/util/Scanner;
    //   59: invokevirtual 491	java/util/Scanner:next	()Ljava/lang/String;
    //   62: astore 7
    //   64: aload 7
    //   66: astore_3
    //   67: aload 4
    //   69: ifnull +43 -> 112
    //   72: aload 4
    //   74: invokevirtual 496	java/io/InputStream:close	()V
    //   77: aload_3
    //   78: areturn
    //   79: astore 6
    //   81: aload 4
    //   83: ifnull +31 -> 114
    //   86: aload 4
    //   88: invokevirtual 496	java/io/InputStream:close	()V
    //   91: goto +23 -> 114
    //   94: astore 5
    //   96: aload 4
    //   98: ifnull +8 -> 106
    //   101: aload 4
    //   103: invokevirtual 496	java/io/InputStream:close	()V
    //   106: aload 5
    //   108: athrow
    //   109: astore_2
    //   110: aconst_null
    //   111: astore_3
    //   112: aload_3
    //   113: areturn
    //   114: aconst_null
    //   115: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   44	64	79	java/util/NoSuchElementException
    //   44	64	94	finally
    //   34	44	109	java/lang/Exception
    //   72	77	109	java/lang/Exception
    //   86	91	109	java/lang/Exception
    //   101	106	109	java/lang/Exception
    //   106	109	109	java/lang/Exception
  }

  public static Context getRemoteContext(Context paramContext)
  {
    try
    {
      Context localContext = paramContext.createPackageContext("com.google.android.gms", 3);
      return localContext;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return null;
  }

  public static Resources getRemoteResource(Context paramContext)
  {
    try
    {
      Resources localResources = paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
      return localResources;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return null;
  }

  // ERROR //
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 510	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_1
    //   5: aload_0
    //   6: invokevirtual 334	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   9: getstatic 351	com/google/android/gms/R$string:common_google_play_services_unknown_issue	I
    //   12: invokevirtual 339	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   15: pop
    //   16: invokestatic 526	java/lang/System:currentTimeMillis	()J
    //   19: ldc2_w 527
    //   22: lcmp
    //   23: ifge +19 -> 42
    //   26: bipush 12
    //   28: ireturn
    //   29: astore_2
    //   30: ldc 146
    //   32: ldc_w 530
    //   35: invokestatic 168	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   38: pop
    //   39: goto -23 -> 16
    //   42: aload_0
    //   43: invokestatic 533	com/google/android/gms/common/GooglePlayServicesUtil:t	(Landroid/content/Context;)V
    //   46: aload_1
    //   47: ldc 11
    //   49: bipush 64
    //   51: invokevirtual 537	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   54: astore 6
    //   56: aload_1
    //   57: ldc 17
    //   59: bipush 64
    //   61: invokevirtual 537	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   64: astore 9
    //   66: aload 9
    //   68: getstatic 45	com/google/android/gms/common/GooglePlayServicesUtil:yL	[[B
    //   71: invokestatic 539	com/google/android/gms/common/GooglePlayServicesUtil:a	(Landroid/content/pm/PackageInfo;[[B)[B
    //   74: astore 10
    //   76: aload 10
    //   78: ifnonnull +42 -> 120
    //   81: ldc 146
    //   83: ldc_w 541
    //   86: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   89: pop
    //   90: bipush 9
    //   92: ireturn
    //   93: astore 4
    //   95: ldc 146
    //   97: ldc_w 543
    //   100: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   103: pop
    //   104: iconst_1
    //   105: ireturn
    //   106: astore 7
    //   108: ldc 146
    //   110: ldc_w 545
    //   113: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   116: pop
    //   117: bipush 9
    //   119: ireturn
    //   120: aload 6
    //   122: iconst_1
    //   123: anewarray 36	[B
    //   126: dup
    //   127: iconst_0
    //   128: aload 10
    //   130: aastore
    //   131: invokestatic 539	com/google/android/gms/common/GooglePlayServicesUtil:a	(Landroid/content/pm/PackageInfo;[[B)[B
    //   134: ifnonnull +15 -> 149
    //   137: ldc 146
    //   139: ldc_w 547
    //   142: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   145: pop
    //   146: bipush 9
    //   148: ireturn
    //   149: aload 6
    //   151: getfield 550	android/content/pm/PackageInfo:versionCode	I
    //   154: ldc 14
    //   156: if_icmpge +35 -> 191
    //   159: ldc 146
    //   161: new 148	java/lang/StringBuilder
    //   164: dup
    //   165: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   168: ldc_w 552
    //   171: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: aload 6
    //   176: getfield 550	android/content/pm/PackageInfo:versionCode	I
    //   179: invokevirtual 158	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   182: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokestatic 279	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   188: pop
    //   189: iconst_2
    //   190: ireturn
    //   191: aload_1
    //   192: ldc 11
    //   194: iconst_0
    //   195: invokevirtual 556	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   198: astore 13
    //   200: aload 13
    //   202: getfield 561	android/content/pm/ApplicationInfo:enabled	Z
    //   205: ifne +23 -> 228
    //   208: iconst_3
    //   209: ireturn
    //   210: astore 11
    //   212: ldc 146
    //   214: ldc_w 563
    //   217: invokestatic 566	android/util/Log:wtf	(Ljava/lang/String;Ljava/lang/String;)I
    //   220: pop
    //   221: aload 11
    //   223: invokevirtual 569	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   226: iconst_1
    //   227: ireturn
    //   228: iconst_0
    //   229: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   5	16	29	java/lang/Throwable
    //   46	56	93	android/content/pm/PackageManager$NameNotFoundException
    //   56	66	106	android/content/pm/PackageManager$NameNotFoundException
    //   191	200	210	android/content/pm/PackageManager$NameNotFoundException
  }

  public static boolean isUserRecoverableError(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return false;
    case 1:
    case 2:
    case 3:
    case 9:
    case 12:
    }
    return true;
  }

  public static void s(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    int i = isGooglePlayServicesAvailable(paramContext);
    if (i != 0)
    {
      Intent localIntent = a(paramContext, i, -1);
      Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + i);
      if (localIntent == null)
        throw new GooglePlayServicesNotAvailableException(i);
      throw new GooglePlayServicesRepairableException(i, "Google Play Services not available", localIntent);
    }
  }

  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2)
  {
    return showErrorDialogFragment(paramInt1, paramActivity, paramInt2, null);
  }

  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    Dialog localDialog = getErrorDialog(paramInt1, paramActivity, paramInt2, paramOnCancelListener);
    if (localDialog == null)
      return false;
    try
    {
      bool = paramActivity instanceof FragmentActivity;
      if (bool)
      {
        android.support.v4.app.FragmentManager localFragmentManager1 = ((FragmentActivity)paramActivity).getSupportFragmentManager();
        b.a(localDialog, paramOnCancelListener).show(localFragmentManager1, "GooglePlayServicesErrorDialog");
      }
      while (true)
      {
        return true;
        if (!fr.eJ())
          break;
        android.app.FragmentManager localFragmentManager = paramActivity.getFragmentManager();
        ErrorDialogFragment.newInstance(localDialog, paramOnCancelListener).show(localFragmentManager, "GooglePlayServicesErrorDialog");
      }
      throw new RuntimeException("This Activity does not support Fragments.");
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      while (true)
        boolean bool = false;
    }
  }

  private static void t(Context paramContext)
  {
    int i;
    try
    {
      ApplicationInfo localApplicationInfo2 = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      localApplicationInfo1 = localApplicationInfo2;
      Bundle localBundle = localApplicationInfo1.metaData;
      if (localBundle == null)
        break label123;
      i = localBundle.getInt("com.google.android.gms.version");
      if (i == 4323000)
        return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        Log.wtf("GooglePlayServicesUtil", "This should never happen.", localNameNotFoundException);
        ApplicationInfo localApplicationInfo1 = null;
      }
    }
    throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 4323000 but found " + i + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
    label123: throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
  }

  private static boolean u(Context paramContext)
  {
    boolean bool;
    if (yS)
      bool = yU;
    while (true)
    {
      return bool;
      try
      {
        byte[] arrayOfByte = a(paramContext.getPackageManager().getPackageInfo("com.google.android.apps.bazaar", 64), yR);
        bool = false;
        if (arrayOfByte != null)
          return true;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
      }
    }
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.GooglePlayServicesUtil
 * JD-Core Version:    0.6.0
 */