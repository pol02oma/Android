package com.appbuilder.statistics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class StatisticsCollector
{
  private static final String MAIN_URL = "http://ibuildapp.com/utl-android-crashlog.php";
  private static Context context;
  private static String deviceModel;
  private static String deviceOS;
  private static String deviceRAMSize;
  private static long deviceSDFree = 0L;
  private static long deviceSDFull = 0L;
  private static String deviceUuid;
  private static ArrayList<StatisticsError> errors;
  private static String projectId = "";
  private static final String projectType = "android";
  private static String projectVersion = "1";
  private static StatisticsSession session;

  static
  {
    deviceUuid = "";
    deviceModel = "";
    deviceOS = "";
    deviceRAMSize = "";
    deviceSDFull = 0L;
    deviceSDFree = 0L;
    session = new StatisticsSession(new Date(System.currentTimeMillis()));
    errors = new ArrayList();
    context = null;
  }

  private static void deInit()
  {
    projectId = "";
    projectVersion = "1";
    deviceUuid = "";
    deviceModel = "";
    deviceOS = "";
    deviceRAMSize = "";
    deviceSDFull = 0L;
    deviceSDFree = 0L;
    session = new StatisticsSession(new Date(System.currentTimeMillis()));
    errors = new ArrayList();
    context = null;
  }

  public static void finish()
  {
    session.endSession();
    NetworkInfo localNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
    int i = 0;
    if (localNetworkInfo != null)
    {
      boolean bool = localNetworkInfo.isConnectedOrConnecting();
      i = 0;
      if (bool)
        i = 1;
    }
    if (i != 0)
      sendStatistics();
    deInit();
  }

  public static void init(Context paramContext)
  {
    context = paramContext;
    projectId = paramContext.getPackageName().substring(1 + paramContext.getPackageName().lastIndexOf("."));
    try
    {
      projectVersion = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      label43: deviceUuid = md5(Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
      deviceModel = Build.MANUFACTURER + " " + Build.MODEL + " " + Build.HARDWARE;
      deviceOS = Build.VERSION.RELEASE + " " + Build.VERSION.SDK_INT;
      deviceRAMSize = readTotalRam();
      deviceSDFull = totalSdSize();
      deviceSDFree = sdSize();
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label43;
    }
  }

  private static String md5(String paramString)
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

  public static void newAction(String paramString)
  {
    session.newAction(paramString);
  }

  public static void newError(Exception paramException, String paramString)
  {
    StatisticsError localStatisticsError = new StatisticsError();
    localStatisticsError.setDate(new Date(System.currentTimeMillis()));
    localStatisticsError.setPlace(paramString);
    String str = paramException.toString();
    if (paramException.getMessage() != null)
      str = str + " " + paramException.getMessage();
    try
    {
      if (paramException.getStackTrace().length > 0)
        for (int i = 0; i < paramException.getStackTrace().length; i++)
          new StringBuilder().append(paramException.getStackTrace()[i].getClassName()).append(".").append(paramException.getStackTrace()[i].getMethodName()).append(" at line ").append(paramException.getStackTrace()[i].getLineNumber()).append("\n").toString();
    }
    catch (NullPointerException localNullPointerException)
    {
      localStatisticsError.setDescription(str);
      errors.add(localStatisticsError);
    }
  }

  private static String prepareXml()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<statistic>\n");
    localStringBuilder.append("    <project>\n");
    localStringBuilder.append("        <id><![CDATA[").append(projectId).append("]]></id>\n");
    localStringBuilder.append("        <type><![CDATA[").append("android").append("]]></type>");
    localStringBuilder.append("        <version><![CDATA[").append(projectVersion).append("]]></version>\n");
    localStringBuilder.append("    </project>\n");
    localStringBuilder.append("    <device>\n");
    localStringBuilder.append("        <uuid><![CDATA[").append(deviceUuid).append("]]></uuid>\n");
    localStringBuilder.append("        <model><![CDATA[").append(deviceModel).append("]]></model>\n");
    localStringBuilder.append("        <os><![CDATA[").append(deviceOS).append("]]></os>\n");
    localStringBuilder.append("        <ram><![CDATA[").append(deviceRAMSize).append("]]></ram>\n");
    localStringBuilder.append("        <sd_full><![CDATA[").append(deviceSDFull).append("]]></sd_full>\n");
    localStringBuilder.append("        <sd_free><![CDATA[").append(deviceSDFree).append("]]></sd_free>\n");
    localStringBuilder.append("    </device>\n");
    localStringBuilder.append("    <session>\n");
    localStringBuilder.append("        <start><![CDATA[").append(session.getStartDate().toString()).append("]]></start>\n");
    localStringBuilder.append("        <end><![CDATA[").append(session.getEndDate().toString()).append("]]></end>\n");
    localStringBuilder.append("        <actions>\n");
    Iterator localIterator1 = session.getActions().iterator();
    while (localIterator1.hasNext())
    {
      StatisticsAction localStatisticsAction = (StatisticsAction)localIterator1.next();
      localStringBuilder.append("        <module>\n");
      localStringBuilder.append("            <id><![CDATA[").append(localStatisticsAction.moduleId).append("]]></id>\n");
      localStringBuilder.append("            <usage><![CDATA[").append(localStatisticsAction.usage).append("]]></usage>\n");
      localStringBuilder.append("        </module>\n");
    }
    localStringBuilder.append("        </actions>\n");
    localStringBuilder.append("    </session>\n");
    localStringBuilder.append("    <errors>\n");
    Iterator localIterator2 = errors.iterator();
    while (localIterator2.hasNext())
    {
      StatisticsError localStatisticsError = (StatisticsError)localIterator2.next();
      localStringBuilder.append("    <error>\n");
      localStringBuilder.append("        <date><![CDATA[").append(localStatisticsError.getDate()).append("]]></date>\n");
      localStringBuilder.append("        <place><![CDATA[").append(localStatisticsError.getPlace()).append("]]></place>\n");
      localStringBuilder.append("        <description><![CDATA[").append(localStatisticsError.getDescription()).append("]]></description>\n");
      localStringBuilder.append("    </error>\n");
    }
    localStringBuilder.append("    </errors>\n");
    localStringBuilder.append("</statistic>\n");
    return localStringBuilder.toString();
  }

  private static String readTotalRam()
  {
    int i = 1000;
    try
    {
      String[] arrayOfString = new java.io.RandomAccessFile("/proc/meminfo", "r").readLine().split(" kB")[0].split(" ");
      i = Integer.parseInt(arrayOfString[(-1 + arrayOfString.length)]);
      int j = Math.round(i / 1024);
      i = j;
      label56: return new Integer(i).toString();
    }
    catch (IOException localIOException)
    {
      break label56;
    }
  }

  private static long sdSize()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getAvailableBlocks();
  }

  private static void sendStatistics()
  {
    String str = prepareXml();
    if (str == null);
    do
      return;
    while (str.length() == 0);
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpPost localHttpPost = new HttpPost("http://ibuildapp.com/utl-android-crashlog.php");
    ArrayList localArrayList = new ArrayList(2);
    localArrayList.add(new BasicNameValuePair("error", str));
    try
    {
      localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList));
      BasicResponseHandler localBasicResponseHandler = new BasicResponseHandler();
      try
      {
        ((String)localDefaultHttpClient.execute(localHttpPost, localBasicResponseHandler));
        Log.e("", "");
        return;
      }
      catch (IOException localIOException)
      {
        Log.e("", "");
        return;
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        Log.e("", "");
    }
  }

  private static long totalSdSize()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getBlockCount();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.statistics.StatisticsCollector
 * JD-Core Version:    0.6.0
 */