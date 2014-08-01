package com.appbuilder.u846253p1176378;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;
import java.io.File;

public class SplashScreen extends Activity
  implements OnPostListener
{
  private static final String TAG = "com.ibuildapp.core.SplashScreen";
  private final int SET_SPLASH = 1;
  private AppConfigure appConfig = null;
  private String cachePath;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
      }
      SplashScreen.this.setSplash();
    }
  };
  private Logger mLog;
  private ProgressDialog progressDialog = null;
  private boolean sdAvailable;
  private Bitmap splashScreen = null;
  private boolean splashSet = false;
  private LinearLayout temp;

  private void LogError(Exception paramException)
  {
    Log.e("com.ibuildapp.core.SplashScreen", "", paramException);
  }

  private void setSplash()
  {
    if (this.appConfig.getShowLink())
    {
      this.temp.setBackgroundResource(2130837771);
      return;
    }
    if (new File(this.cachePath + "/splash.jpg").exists())
    {
      BitmapDrawable localBitmapDrawable = new BitmapDrawable(getResources(), this.cachePath + "/splash.jpg");
      this.temp.setBackgroundDrawable(localBitmapDrawable);
      return;
    }
    this.temp.setBackgroundResource(2130837772);
  }

  // ERROR //
  public void onCreate(android.os.Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 127	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: invokestatic 133	com/appbuilder/u846253p1176378/Statics:addActivityIntefrace	(Lcom/appbuilder/u846253p1176378/OnPostListener;)V
    //   9: aload_0
    //   10: iconst_1
    //   11: invokevirtual 137	com/appbuilder/u846253p1176378/SplashScreen:requestWindowFeature	(I)Z
    //   14: pop
    //   15: aload_0
    //   16: invokevirtual 141	com/appbuilder/u846253p1176378/SplashScreen:getWindow	()Landroid/view/Window;
    //   19: sipush 1024
    //   22: sipush 1024
    //   25: invokevirtual 147	android/view/Window:setFlags	(II)V
    //   28: aload_0
    //   29: ldc 148
    //   31: invokevirtual 151	com/appbuilder/u846253p1176378/SplashScreen:setContentView	(I)V
    //   34: aload_0
    //   35: aload_0
    //   36: ldc 152
    //   38: invokevirtual 156	com/appbuilder/u846253p1176378/SplashScreen:findViewById	(I)Landroid/view/View;
    //   41: checkcast 74	android/widget/LinearLayout
    //   44: putfield 71	com/appbuilder/u846253p1176378/SplashScreen:temp	Landroid/widget/LinearLayout;
    //   47: aload_0
    //   48: new 158	android/app/ProgressDialog
    //   51: dup
    //   52: aload_0
    //   53: invokespecial 161	android/app/ProgressDialog:<init>	(Landroid/content/Context;)V
    //   56: putfield 36	com/appbuilder/u846253p1176378/SplashScreen:progressDialog	Landroid/app/ProgressDialog;
    //   59: aload_0
    //   60: getfield 36	com/appbuilder/u846253p1176378/SplashScreen:progressDialog	Landroid/app/ProgressDialog;
    //   63: iconst_0
    //   64: invokevirtual 165	android/app/ProgressDialog:setCancelable	(Z)V
    //   67: aload_0
    //   68: getfield 36	com/appbuilder/u846253p1176378/SplashScreen:progressDialog	Landroid/app/ProgressDialog;
    //   71: aload_0
    //   72: ldc 166
    //   74: invokevirtual 170	com/appbuilder/u846253p1176378/SplashScreen:getString	(I)Ljava/lang/String;
    //   77: invokevirtual 174	android/app/ProgressDialog:setMessage	(Ljava/lang/CharSequence;)V
    //   80: aload_0
    //   81: getfield 36	com/appbuilder/u846253p1176378/SplashScreen:progressDialog	Landroid/app/ProgressDialog;
    //   84: invokevirtual 175	android/app/ProgressDialog:getWindow	()Landroid/view/Window;
    //   87: iconst_1
    //   88: invokevirtual 178	android/view/Window:setGravity	(I)V
    //   91: aload_0
    //   92: getfield 36	com/appbuilder/u846253p1176378/SplashScreen:progressDialog	Landroid/app/ProgressDialog;
    //   95: invokevirtual 181	android/app/ProgressDialog:show	()V
    //   98: invokestatic 186	com/appbuilder/sdk/android/Utils:sdcardAvailable	()Z
    //   101: ifne +151 -> 252
    //   104: ldc 188
    //   106: ldc 190
    //   108: invokestatic 194	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   111: pop
    //   112: aload_0
    //   113: iconst_0
    //   114: putfield 196	com/appbuilder/u846253p1176378/SplashScreen:sdAvailable	Z
    //   117: aload_0
    //   118: getfield 196	com/appbuilder/u846253p1176378/SplashScreen:sdAvailable	Z
    //   121: ifeq +125 -> 246
    //   124: aload_0
    //   125: new 82	java/lang/StringBuilder
    //   128: dup
    //   129: invokespecial 83	java/lang/StringBuilder:<init>	()V
    //   132: invokestatic 202	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   135: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   138: ldc 207
    //   140: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload_0
    //   144: invokevirtual 210	com/appbuilder/u846253p1176378/SplashScreen:getPackageName	()Ljava/lang/String;
    //   147: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: putfield 85	com/appbuilder/u846253p1176378/SplashScreen:cachePath	Ljava/lang/String;
    //   156: new 80	java/io/File
    //   159: dup
    //   160: new 82	java/lang/StringBuilder
    //   163: dup
    //   164: invokespecial 83	java/lang/StringBuilder:<init>	()V
    //   167: aload_0
    //   168: getfield 85	com/appbuilder/u846253p1176378/SplashScreen:cachePath	Ljava/lang/String;
    //   171: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: ldc 212
    //   176: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokespecial 98	java/io/File:<init>	(Ljava/lang/String;)V
    //   185: astore_3
    //   186: aload_3
    //   187: invokevirtual 101	java/io/File:exists	()Z
    //   190: ifeq +56 -> 246
    //   193: new 214	java/io/ObjectInputStream
    //   196: dup
    //   197: new 216	java/io/FileInputStream
    //   200: dup
    //   201: aload_3
    //   202: invokespecial 219	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   205: invokespecial 222	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   208: astore 4
    //   210: aload_0
    //   211: aload 4
    //   213: invokevirtual 226	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   216: checkcast 65	com/appbuilder/u846253p1176378/AppConfigure
    //   219: putfield 40	com/appbuilder/u846253p1176378/SplashScreen:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   222: aload 4
    //   224: invokevirtual 229	java/io/ObjectInputStream:close	()V
    //   227: aload_0
    //   228: getfield 40	com/appbuilder/u846253p1176378/SplashScreen:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   231: invokevirtual 69	com/appbuilder/u846253p1176378/AppConfigure:getShowLink	()Z
    //   234: ifeq +65 -> 299
    //   237: aload_0
    //   238: getfield 71	com/appbuilder/u846253p1176378/SplashScreen:temp	Landroid/widget/LinearLayout;
    //   241: ldc 72
    //   243: invokevirtual 78	android/widget/LinearLayout:setBackgroundResource	(I)V
    //   246: aload_0
    //   247: iconst_1
    //   248: putfield 42	com/appbuilder/u846253p1176378/SplashScreen:splashSet	Z
    //   251: return
    //   252: aload_0
    //   253: iconst_1
    //   254: putfield 196	com/appbuilder/u846253p1176378/SplashScreen:sdAvailable	Z
    //   257: goto -140 -> 117
    //   260: astore 11
    //   262: ldc 53
    //   264: ldc 53
    //   266: invokestatic 232	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   269: pop
    //   270: goto -43 -> 227
    //   273: astore 9
    //   275: ldc 53
    //   277: ldc 53
    //   279: invokestatic 232	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   282: pop
    //   283: goto -56 -> 227
    //   286: astore 5
    //   288: ldc 53
    //   290: ldc 53
    //   292: invokestatic 232	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   295: pop
    //   296: goto -69 -> 227
    //   299: new 80	java/io/File
    //   302: dup
    //   303: new 82	java/lang/StringBuilder
    //   306: dup
    //   307: invokespecial 83	java/lang/StringBuilder:<init>	()V
    //   310: aload_0
    //   311: getfield 85	com/appbuilder/u846253p1176378/SplashScreen:cachePath	Ljava/lang/String;
    //   314: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: ldc 91
    //   319: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   325: invokespecial 98	java/io/File:<init>	(Ljava/lang/String;)V
    //   328: invokevirtual 101	java/io/File:exists	()Z
    //   331: ifeq +61 -> 392
    //   334: new 103	android/graphics/drawable/BitmapDrawable
    //   337: dup
    //   338: aload_0
    //   339: invokevirtual 107	com/appbuilder/u846253p1176378/SplashScreen:getResources	()Landroid/content/res/Resources;
    //   342: new 82	java/lang/StringBuilder
    //   345: dup
    //   346: invokespecial 83	java/lang/StringBuilder:<init>	()V
    //   349: aload_0
    //   350: getfield 85	com/appbuilder/u846253p1176378/SplashScreen:cachePath	Ljava/lang/String;
    //   353: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: ldc 91
    //   358: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   364: invokespecial 110	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Ljava/lang/String;)V
    //   367: astore 8
    //   369: aload_0
    //   370: getfield 71	com/appbuilder/u846253p1176378/SplashScreen:temp	Landroid/widget/LinearLayout;
    //   373: aload 8
    //   375: invokevirtual 114	android/widget/LinearLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   378: goto -132 -> 246
    //   381: astore 7
    //   383: aload_0
    //   384: aload 7
    //   386: invokespecial 234	com/appbuilder/u846253p1176378/SplashScreen:LogError	(Ljava/lang/Exception;)V
    //   389: goto -143 -> 246
    //   392: aload_0
    //   393: getfield 71	com/appbuilder/u846253p1176378/SplashScreen:temp	Landroid/widget/LinearLayout;
    //   396: ldc 115
    //   398: invokevirtual 78	android/widget/LinearLayout:setBackgroundResource	(I)V
    //   401: goto -155 -> 246
    //
    // Exception table:
    //   from	to	target	type
    //   193	227	260	java/io/StreamCorruptedException
    //   193	227	273	java/io/IOException
    //   193	227	286	java/lang/ClassNotFoundException
    //   227	246	381	java/lang/Exception
    //   299	378	381	java/lang/Exception
    //   392	401	381	java/lang/Exception
  }

  public void onPause()
  {
    this.progressDialog.hide();
    super.onPause();
  }

  public void onPost(AppConfigure paramAppConfigure)
  {
    if (!this.splashSet)
    {
      this.appConfig = paramAppConfigure;
      this.handler.sendEmptyMessage(1);
    }
  }

  public void onStop()
  {
    this.progressDialog.dismiss();
    super.onStop();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.SplashScreen
 * JD-Core Version:    0.6.0
 */