package com.appbuilder.u846253p1176378;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.appbuilder.sdk.android.AppAdvData;
import com.appbuilder.sdk.android.GoogleAnaliticsHandler;
import com.appbuilder.sdk.android.Statics;
import com.appbuilder.sdk.android.Widget;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

public class PluginManager
{
  private static final String ERR_FILE_NOT_EXISTS = "ERR_FILE_NOT_EXISTS";
  private static final String ERR_INIT_BASE_CLASS = "ERR_INIT_BASE_CLASS";
  private static final String ERR_INIT_CLASS_LOADER = "ERR_INIT_CLASS_LOADER";
  private static final String ERR_LOAD_BASE_CLASS = "ERR_LOAD_BASE_CLASS";
  private static final String ERR_NO_ERROR = "ERR_NO_ERROR";
  private static final String ERR_PLUGIN_EMBEDDED = "ERR_PLUGIN_EMBEDDED";
  private static final String ERR_PLUGIN_METHOD = "ERR_PLUGIN_METHOD";
  private static final String ERR_RESOLVE_METHOD = "ERR_RESOLVE_METHOD";
  private static final String ERR_SECURITY_IO = "ERR_SECURITY_IO";
  private static final String TAG = "com.ibuildapp.PluginManager";
  private static PluginManager mPluginManager;
  private final int START_MODULE = 10001;
  private boolean firstStart = false;
  private Activity mCaller;
  private String mErrString = "ERR_NO_ERROR";
  private Activity mHolder;

  static
  {
    if (!PluginManager.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      mPluginManager = null;
      return;
    }
  }

  private void LogDebug(String paramString)
  {
    Log.d("com.ibuildapp.PluginManager", paramString);
  }

  private void LogError(Exception paramException)
  {
    Log.e("com.ibuildapp.PluginManager", "", paramException);
  }

  public static boolean classExists(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    return false;
  }

  public static PluginManager getManager()
  {
    monitorenter;
    try
    {
      if (mPluginManager == null)
        mPluginManager = new PluginManager();
      PluginManager localPluginManager = mPluginManager;
      return localPluginManager;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void obtainPluginFromUrl(String paramString, Widget paramWidget, ArrayList<Widget> paramArrayList, AppConfigure paramAppConfigure)
    throws RuntimeException
  {
    DownloadHelper localDownloadHelper = new DownloadHelper(this.mCaller, paramWidget.getPluginUrl(), paramString, null, false, true);
    localDownloadHelper.setStartedRunnable(new Runnable()
    {
      public void run()
      {
        Log.d("Download plugin", "Start");
      }
    });
    localDownloadHelper.setFailedRunnable(new Runnable(localDownloadHelper)
    {
      public void run()
      {
        Log.d("Download plugin", "Failed: " + this.val$dh.getErrorString());
        throw new RuntimeException();
      }
    });
    localDownloadHelper.setSuccessRunnable(new Runnable(paramString, paramWidget, paramArrayList, paramAppConfigure)
    {
      public void run()
      {
        Log.d("Download plugin", "Success");
        PluginManager.getManager().loadLocalPluginFile(this.val$pluginPath, this.val$widget, this.val$getmWidgets, this.val$appConfig);
      }
    });
    localDownloadHelper.start();
  }

  private String saveXmlToFile(String paramString)
  {
    String str1 = Environment.getExternalStorageDirectory() + "/AppBuilder/" + this.mCaller.getPackageName() + "/cache";
    File localFile1 = new File(str1);
    if (!localFile1.exists())
      localFile1.mkdirs();
    String str2 = str1 + "/galleryCacheXmlData.xml";
    try
    {
      File localFile2 = new File(str2);
      if (!localFile2.createNewFile())
      {
        localFile2.delete();
        localFile2.createNewFile();
      }
      FileWriter localFileWriter = new FileWriter(localFile2);
      localFileWriter.write(paramString);
      localFileWriter.close();
      return str2;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return str2;
  }

  public String getErrorString()
  {
    monitorenter;
    try
    {
      String str = this.mErrString;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void installPlugin(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    this.mCaller.startActivityForResult(localIntent, 10001);
  }

  public void loadEmbeddedPlugin(String paramString, Widget paramWidget, AppAdvData paramAppAdvData, ArrayList<Widget> paramArrayList, AppConfigure paramAppConfigure)
    throws RuntimeException
  {
    Intent localIntent = new Intent();
    try
    {
      localIntent.setClass(this.mCaller, Class.forName(paramWidget.getPluginPackage() + "." + paramWidget.getPluginName()));
      localArrayList = new ArrayList();
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
      {
        Widget localWidget1 = new Widget((Widget)localIterator.next());
        localWidget1.setPluginXmlData("");
        localArrayList.add(localWidget1);
      }
    }
    catch (Exception localException1)
    {
      ArrayList localArrayList;
      while (true)
        LogError(localException1);
      if (paramWidget.getPluginXmlData().length() > 50000)
      {
        localIntent.putExtra("WidgetFile", saveXmlToFile(paramWidget.getPluginXmlData()));
        Widget localWidget2 = new Widget(paramWidget);
        localWidget2.setPluginXmlData("");
        paramWidget = localWidget2;
      }
      localIntent.putExtra("Widget", paramWidget);
      localIntent.putExtra("Widgets", localArrayList);
      localIntent.putExtra("Advertisement", paramAppAdvData);
      localIntent.putExtra("showSideBar", paramAppConfigure.isShowSidebar());
      localIntent.putExtra("navBarDesign", paramAppConfigure.getNavBarDesign());
      localIntent.putExtra("tabBarDesign", paramAppConfigure.getTabBarDesign());
      localIntent.putExtra("bottomBarDesign", paramAppConfigure.getBottomBarDesign());
      localIntent.putExtra("appid", paramAppConfigure.getmAppId());
      localIntent.putExtra("firstStart", this.firstStart);
      localIntent.putExtra("flurry_id", AppBuilder.FLURYY_ID);
      try
      {
        this.mHolder.startActivityForResult(localIntent, 10001);
        try
        {
          String str1 = paramWidget.getmPluginType();
          if (TextUtils.isEmpty(str1))
            str1 = paramWidget.getPluginName();
          Statics.analiticsHandler.sendIbuildAppEvent("Start Module", str1);
          String str2 = paramWidget.getTitle();
          if (TextUtils.isEmpty(str2))
            str2 = paramWidget.getPluginName();
          Statics.analiticsHandler.sendUserEvent("Start Module", str2);
          return;
        }
        catch (Exception localException3)
        {
          LogError(localException3);
          return;
        }
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        Log.d("loadEmbeddedPlugin", "activity exception");
        throw new RuntimeException();
      }
      catch (Exception localException2)
      {
        LogError(localException2);
      }
    }
    throw new RuntimeException();
  }

  // ERROR //
  public void loadLocalPluginFile(String paramString, Widget paramWidget, ArrayList<Widget> paramArrayList, AppConfigure paramAppConfigure)
    throws RuntimeException, java.lang.SecurityException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 94	com/appbuilder/u846253p1176378/PluginManager:mCaller	Landroid/app/Activity;
    //   4: ifnonnull +11 -> 15
    //   7: new 90	java/lang/RuntimeException
    //   10: dup
    //   11: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   14: athrow
    //   15: new 165	java/io/File
    //   18: dup
    //   19: aload_1
    //   20: invokespecial 167	java/io/File:<init>	(Ljava/lang/String;)V
    //   23: invokevirtual 170	java/io/File:exists	()Z
    //   26: ifne +27 -> 53
    //   29: ldc_w 376
    //   32: ldc_w 378
    //   35: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   38: pop
    //   39: aload_0
    //   40: ldc 10
    //   42: putfield 59	com/appbuilder/u846253p1176378/PluginManager:mErrString	Ljava/lang/String;
    //   45: new 90	java/lang/RuntimeException
    //   48: dup
    //   49: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   52: athrow
    //   53: new 380	dalvik/system/DexClassLoader
    //   56: dup
    //   57: aload_1
    //   58: aload_0
    //   59: getfield 94	com/appbuilder/u846253p1176378/PluginManager:mCaller	Landroid/app/Activity;
    //   62: invokestatic 386	com/appbuilder/sdk/android/Utils:PluginFolder	(Landroid/app/Activity;)Ljava/lang/String;
    //   65: aconst_null
    //   66: aload_0
    //   67: invokevirtual 390	java/lang/Object:getClass	()Ljava/lang/Class;
    //   70: invokevirtual 394	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   73: invokespecial 397	dalvik/system/DexClassLoader:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/ClassLoader;)V
    //   76: astore 5
    //   78: aload 5
    //   80: ifnonnull +27 -> 107
    //   83: ldc_w 376
    //   86: ldc_w 399
    //   89: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   92: pop
    //   93: aload_0
    //   94: ldc 14
    //   96: putfield 59	com/appbuilder/u846253p1176378/PluginManager:mErrString	Ljava/lang/String;
    //   99: new 90	java/lang/RuntimeException
    //   102: dup
    //   103: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   106: athrow
    //   107: new 137	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 138	java/lang/StringBuilder:<init>	()V
    //   114: aload_2
    //   115: invokevirtual 230	com/appbuilder/sdk/android/Widget:getPluginPackage	()Ljava/lang/String;
    //   118: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: ldc_w 401
    //   124: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: astore 6
    //   132: aload 6
    //   134: ldc_w 376
    //   137: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   140: pop
    //   141: aload 5
    //   143: aload 6
    //   145: invokevirtual 404	dalvik/system/DexClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   148: astore 9
    //   150: ldc_w 376
    //   153: ldc_w 406
    //   156: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   159: pop
    //   160: ldc_w 376
    //   163: ldc_w 408
    //   166: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   169: pop
    //   170: aload 9
    //   172: invokevirtual 411	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   175: astore 42
    //   177: aload 42
    //   179: astore 13
    //   181: ldc_w 376
    //   184: ldc_w 413
    //   187: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   190: pop
    //   191: getstatic 48	com/appbuilder/u846253p1176378/PluginManager:$assertionsDisabled	Z
    //   194: ifne +90 -> 284
    //   197: aload 13
    //   199: ifnonnull +85 -> 284
    //   202: new 415	java/lang/AssertionError
    //   205: dup
    //   206: invokespecial 416	java/lang/AssertionError:<init>	()V
    //   209: athrow
    //   210: astore 15
    //   212: ldc_w 376
    //   215: ldc_w 418
    //   218: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   221: pop
    //   222: aload_0
    //   223: ldc 24
    //   225: putfield 59	com/appbuilder/u846253p1176378/PluginManager:mErrString	Ljava/lang/String;
    //   228: new 90	java/lang/RuntimeException
    //   231: dup
    //   232: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   235: athrow
    //   236: astore 8
    //   238: aload_0
    //   239: ldc 16
    //   241: putfield 59	com/appbuilder/u846253p1176378/PluginManager:mErrString	Ljava/lang/String;
    //   244: new 90	java/lang/RuntimeException
    //   247: dup
    //   248: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   251: athrow
    //   252: astore 41
    //   254: aload_0
    //   255: ldc 12
    //   257: putfield 59	com/appbuilder/u846253p1176378/PluginManager:mErrString	Ljava/lang/String;
    //   260: new 90	java/lang/RuntimeException
    //   263: dup
    //   264: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   267: athrow
    //   268: astore 40
    //   270: aload_0
    //   271: ldc 12
    //   273: putfield 59	com/appbuilder/u846253p1176378/PluginManager:mErrString	Ljava/lang/String;
    //   276: new 90	java/lang/RuntimeException
    //   279: dup
    //   280: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   283: athrow
    //   284: aload 13
    //   286: invokevirtual 390	java/lang/Object:getClass	()Ljava/lang/Class;
    //   289: ldc_w 420
    //   292: iconst_0
    //   293: anewarray 42	java/lang/Class
    //   296: invokevirtual 424	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   299: astore 17
    //   301: aload 13
    //   303: invokevirtual 390	java/lang/Object:getClass	()Ljava/lang/Class;
    //   306: ldc_w 426
    //   309: iconst_1
    //   310: anewarray 42	java/lang/Class
    //   313: dup
    //   314: iconst_0
    //   315: ldc_w 272
    //   318: aastore
    //   319: invokevirtual 424	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   322: astore 18
    //   324: aload 13
    //   326: invokevirtual 390	java/lang/Object:getClass	()Ljava/lang/Class;
    //   329: ldc_w 428
    //   332: iconst_1
    //   333: anewarray 42	java/lang/Class
    //   336: dup
    //   337: iconst_0
    //   338: ldc 4
    //   340: aastore
    //   341: invokevirtual 424	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   344: astore 19
    //   346: aload 17
    //   348: ifnull +13 -> 361
    //   351: aload 18
    //   353: ifnull +8 -> 361
    //   356: aload 19
    //   358: ifnonnull +27 -> 385
    //   361: ldc_w 376
    //   364: ldc_w 430
    //   367: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   370: pop
    //   371: aload_0
    //   372: ldc 24
    //   374: putfield 59	com/appbuilder/u846253p1176378/PluginManager:mErrString	Ljava/lang/String;
    //   377: new 90	java/lang/RuntimeException
    //   380: dup
    //   381: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   384: athrow
    //   385: ldc_w 376
    //   388: ldc_w 432
    //   391: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   394: pop
    //   395: aload 17
    //   397: aload 13
    //   399: iconst_0
    //   400: anewarray 4	java/lang/Object
    //   403: invokevirtual 438	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   406: checkcast 272	java/lang/String
    //   409: astore 24
    //   411: aload 24
    //   413: ldc_w 440
    //   416: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   419: pop
    //   420: aload_0
    //   421: getfield 94	com/appbuilder/u846253p1176378/PluginManager:mCaller	Landroid/app/Activity;
    //   424: invokevirtual 444	android/app/Activity:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   427: astore 27
    //   429: getstatic 48	com/appbuilder/u846253p1176378/PluginManager:$assertionsDisabled	Z
    //   432: ifne +58 -> 490
    //   435: aload 27
    //   437: ifnonnull +53 -> 490
    //   440: new 415	java/lang/AssertionError
    //   443: dup
    //   444: invokespecial 416	java/lang/AssertionError:<init>	()V
    //   447: athrow
    //   448: astore 26
    //   450: aload_0
    //   451: ldc 22
    //   453: putfield 59	com/appbuilder/u846253p1176378/PluginManager:mErrString	Ljava/lang/String;
    //   456: new 90	java/lang/RuntimeException
    //   459: dup
    //   460: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   463: athrow
    //   464: astore 22
    //   466: ldc_w 376
    //   469: ldc_w 446
    //   472: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   475: pop
    //   476: aload_0
    //   477: ldc 22
    //   479: putfield 59	com/appbuilder/u846253p1176378/PluginManager:mErrString	Ljava/lang/String;
    //   482: new 90	java/lang/RuntimeException
    //   485: dup
    //   486: invokespecial 368	java/lang/RuntimeException:<init>	()V
    //   489: athrow
    //   490: iconst_1
    //   491: anewarray 4	java/lang/Object
    //   494: astore 28
    //   496: aload 28
    //   498: iconst_0
    //   499: aload 27
    //   501: getfield 451	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   504: aastore
    //   505: aload 18
    //   507: aload 13
    //   509: aload 28
    //   511: invokevirtual 438	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   514: pop
    //   515: new 201	android/content/Intent
    //   518: dup
    //   519: invokespecial 227	android/content/Intent:<init>	()V
    //   522: astore 30
    //   524: aload 30
    //   526: aload_2
    //   527: invokevirtual 230	com/appbuilder/sdk/android/Widget:getPluginPackage	()Ljava/lang/String;
    //   530: aload 24
    //   532: invokevirtual 454	android/content/Intent:setClassName	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   535: pop
    //   536: aload 30
    //   538: ldc_w 456
    //   541: invokevirtual 460	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   544: pop
    //   545: aload 30
    //   547: aload 5
    //   549: invokevirtual 464	android/content/Intent:setExtrasClassLoader	(Ljava/lang/ClassLoader;)V
    //   552: aload 30
    //   554: ldc_w 466
    //   557: invokevirtual 469	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   560: pop
    //   561: aload 30
    //   563: ldc_w 287
    //   566: aload_2
    //   567: invokevirtual 290	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   570: pop
    //   571: aload 30
    //   573: ldc_w 292
    //   576: aload_3
    //   577: invokevirtual 290	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   580: pop
    //   581: aload 30
    //   583: ldc_w 471
    //   586: aload_2
    //   587: invokevirtual 270	com/appbuilder/sdk/android/Widget:getPluginXmlData	()Ljava/lang/String;
    //   590: invokevirtual 285	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   593: pop
    //   594: aload 30
    //   596: ldc_w 296
    //   599: aload 4
    //   601: invokevirtual 301	com/appbuilder/u846253p1176378/AppConfigure:isShowSidebar	()Z
    //   604: invokevirtual 304	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   607: pop
    //   608: aload_0
    //   609: getfield 335	com/appbuilder/u846253p1176378/PluginManager:mHolder	Landroid/app/Activity;
    //   612: aload 30
    //   614: sipush 10001
    //   617: invokevirtual 220	android/app/Activity:startActivityForResult	(Landroid/content/Intent;I)V
    //   620: return
    //   621: astore 38
    //   623: ldc_w 376
    //   626: ldc_w 473
    //   629: invokestatic 67	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   632: pop
    //   633: aload_0
    //   634: aload_1
    //   635: invokevirtual 475	com/appbuilder/u846253p1176378/PluginManager:installPlugin	(Ljava/lang/String;)V
    //   638: return
    //   639: astore 12
    //   641: aconst_null
    //   642: astore 13
    //   644: goto -463 -> 181
    //
    // Exception table:
    //   from	to	target	type
    //   191	197	210	java/lang/Exception
    //   202	210	210	java/lang/Exception
    //   284	346	210	java/lang/Exception
    //   141	160	236	java/lang/Exception
    //   170	177	252	java/lang/IllegalAccessException
    //   170	177	268	java/lang/InstantiationException
    //   420	435	448	java/lang/Exception
    //   440	448	448	java/lang/Exception
    //   490	515	448	java/lang/Exception
    //   395	411	464	java/lang/Exception
    //   608	620	621	android/content/ActivityNotFoundException
    //   170	177	639	java/lang/SecurityException
  }

  public void loadPlugin(Activity paramActivity1, Activity paramActivity2, Widget paramWidget, AppAdvData paramAppAdvData, boolean paramBoolean, ArrayList<Widget> paramArrayList, AppConfigure paramAppConfigure)
    throws RuntimeException
  {
    monitorenter;
    try
    {
      this.mCaller = paramActivity1;
      this.mHolder = paramActivity2;
      try
      {
        loadEmbeddedPlugin(null, paramWidget, paramAppAdvData, paramArrayList, paramAppConfigure);
        monitorexit;
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        this.mErrString = "ERR_PLUGIN_EMBEDDED";
        throw new RuntimeException();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setContext(Activity paramActivity1, Activity paramActivity2)
  {
    monitorenter;
    try
    {
      this.mCaller = paramActivity1;
      this.mHolder = paramActivity2;
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

  public void setFirstStart(boolean paramBoolean)
  {
    this.firstStart = paramBoolean;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.PluginManager
 * JD-Core Version:    0.6.0
 */