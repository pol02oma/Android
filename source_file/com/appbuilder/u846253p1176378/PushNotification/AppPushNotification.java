package com.appbuilder.u846253p1176378.PushNotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appbuilder.u846253p1176378.AppBuilder;
import com.appbuilder.u846253p1176378.AppConfigure;
import java.util.ArrayList;
import java.util.Locale;

public class AppPushNotification extends Activity
  implements View.OnTouchListener
{
  private final int SLIDE_COMPLETE = 2;
  private final int SLIDE_TO_LEFT_START = 1;
  private final int SLIDE_TO_RIGHT_START = 0;
  private AppConfigure appConfig = null;
  private Button btnApp;
  private Button btnClose;
  private ImageView btnNext = null;
  private ImageView btnPrev = null;
  private String cachePath;
  Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 0:
        do
        {
          do
            return;
          while (AppPushNotification.this.position >= -1 + AppPushNotification.this.notifications.size());
          AppPushNotification.access$008(AppPushNotification.this);
          AppPushNotification.this.slidePanel(-500);
          return;
        }
        while (AppPushNotification.this.position <= 0);
        AppPushNotification.access$010(AppPushNotification.this);
        AppPushNotification.this.slidePanel(500);
        return;
      case 2:
      }
      AppPushNotification.this.showNotification();
    }
  };
  private RelativeLayout notificationMain;
  private LinearLayout notificationPanel = null;
  private ArrayList<String> notifications = new ArrayList();
  private int position = 0;
  private boolean sdAvailable = false;
  float startPos = 0.0F;
  private TextView textViewCounter = null;
  private TextView textViewNotification = null;

  private void closeMessage()
  {
    finish();
  }

  private void openApp()
  {
    Intent localIntent = new Intent(this, AppBuilder.class);
    localIntent.setFlags(131072);
    startActivity(localIntent);
    finish();
  }

  private void showNotification()
  {
    if (this.notifications.size() > 1)
    {
      if (this.position > 0)
      {
        this.btnPrev.setVisibility(0);
        if (this.position >= -1 + this.notifications.size())
          break label152;
        this.btnNext.setVisibility(0);
      }
    }
    else
    {
      label50: this.textViewNotification.setText((CharSequence)this.notifications.get(this.position));
      if (!Locale.getDefault().toString().equals("ru_RU"))
        break label163;
      this.textViewCounter.setText(1 + this.position + " из " + this.notifications.size());
    }
    while (true)
    {
      this.notificationPanel.clearAnimation();
      this.notificationPanel.refreshDrawableState();
      return;
      this.btnPrev.setVisibility(4);
      break;
      label152: this.btnNext.setVisibility(4);
      break label50;
      label163: this.textViewCounter.setText(1 + this.position + " from " + this.notifications.size());
    }
  }

  private void slidePanel(int paramInt)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, paramInt, 0.0F, 0.0F);
    localTranslateAnimation.setFillAfter(true);
    localTranslateAnimation.setInterpolator(new LinearInterpolator());
    localTranslateAnimation.setDuration(500L);
    localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        AppPushNotification.this.handler.sendEmptyMessage(2);
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    this.notificationPanel.startAnimation(localTranslateAnimation);
  }

  // ERROR //
  public void onCreate(android.os.Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 227	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: iconst_1
    //   7: invokevirtual 231	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:requestWindowFeature	(I)Z
    //   10: pop
    //   11: aload_0
    //   12: invokevirtual 235	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:getWindow	()Landroid/view/Window;
    //   15: sipush 1024
    //   18: sipush 1024
    //   21: invokevirtual 240	android/view/Window:setFlags	(II)V
    //   24: aload_0
    //   25: ldc 241
    //   27: invokevirtual 244	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:setContentView	(I)V
    //   30: aload_0
    //   31: aload_0
    //   32: ldc 245
    //   34: invokevirtual 249	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:findViewById	(I)Landroid/view/View;
    //   37: checkcast 137	android/widget/TextView
    //   40: putfield 52	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:textViewNotification	Landroid/widget/TextView;
    //   43: aload_0
    //   44: getfield 52	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:textViewNotification	Landroid/widget/TextView;
    //   47: new 251	android/text/method/ScrollingMovementMethod
    //   50: dup
    //   51: invokespecial 252	android/text/method/ScrollingMovementMethod:<init>	()V
    //   54: invokevirtual 256	android/widget/TextView:setMovementMethod	(Landroid/text/method/MovementMethod;)V
    //   57: aload_0
    //   58: aload_0
    //   59: ldc_w 257
    //   62: invokevirtual 249	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:findViewById	(I)Landroid/view/View;
    //   65: checkcast 137	android/widget/TextView
    //   68: putfield 54	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:textViewCounter	Landroid/widget/TextView;
    //   71: aload_0
    //   72: aload_0
    //   73: ldc_w 258
    //   76: invokevirtual 249	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:findViewById	(I)Landroid/view/View;
    //   79: checkcast 260	android/widget/RelativeLayout
    //   82: putfield 262	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notificationMain	Landroid/widget/RelativeLayout;
    //   85: aload_0
    //   86: aload_0
    //   87: ldc_w 263
    //   90: invokevirtual 249	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:findViewById	(I)Landroid/view/View;
    //   93: checkcast 174	android/widget/LinearLayout
    //   96: putfield 56	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notificationPanel	Landroid/widget/LinearLayout;
    //   99: aload_0
    //   100: aload_0
    //   101: ldc_w 264
    //   104: invokevirtual 249	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:findViewById	(I)Landroid/view/View;
    //   107: checkcast 266	android/widget/Button
    //   110: putfield 268	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:btnClose	Landroid/widget/Button;
    //   113: aload_0
    //   114: aload_0
    //   115: ldc_w 269
    //   118: invokevirtual 249	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:findViewById	(I)Landroid/view/View;
    //   121: checkcast 266	android/widget/Button
    //   124: putfield 271	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:btnApp	Landroid/widget/Button;
    //   127: aload_0
    //   128: aload_0
    //   129: ldc_w 272
    //   132: invokevirtual 249	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:findViewById	(I)Landroid/view/View;
    //   135: checkcast 126	android/widget/ImageView
    //   138: putfield 62	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:btnNext	Landroid/widget/ImageView;
    //   141: aload_0
    //   142: aload_0
    //   143: ldc_w 273
    //   146: invokevirtual 249	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:findViewById	(I)Landroid/view/View;
    //   149: checkcast 126	android/widget/ImageView
    //   152: putfield 64	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:btnPrev	Landroid/widget/ImageView;
    //   155: invokestatic 279	com/appbuilder/sdk/android/Utils:sdcardAvailable	()Z
    //   158: ifne +426 -> 584
    //   161: aload_0
    //   162: iconst_0
    //   163: putfield 60	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:sdAvailable	Z
    //   166: aload_0
    //   167: getfield 60	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:sdAvailable	Z
    //   170: ifeq +131 -> 301
    //   173: aload_0
    //   174: new 161	java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial 162	java/lang/StringBuilder:<init>	()V
    //   181: invokestatic 285	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   184: invokevirtual 288	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   187: ldc_w 290
    //   190: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: aload_0
    //   194: invokevirtual 293	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:getPackageName	()Ljava/lang/String;
    //   197: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: putfield 295	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:cachePath	Ljava/lang/String;
    //   206: new 297	java/io/File
    //   209: dup
    //   210: new 161	java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial 162	java/lang/StringBuilder:<init>	()V
    //   217: aload_0
    //   218: getfield 295	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:cachePath	Ljava/lang/String;
    //   221: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: ldc_w 299
    //   227: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: invokespecial 302	java/io/File:<init>	(Ljava/lang/String;)V
    //   236: astore 7
    //   238: aload 7
    //   240: invokevirtual 305	java/io/File:exists	()Z
    //   243: ifeq +58 -> 301
    //   246: new 307	java/io/ObjectInputStream
    //   249: dup
    //   250: new 309	java/io/FileInputStream
    //   253: dup
    //   254: aload 7
    //   256: invokespecial 312	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   259: invokespecial 315	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   262: astore 8
    //   264: aload_0
    //   265: aload 8
    //   267: invokevirtual 319	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   270: checkcast 321	com/appbuilder/u846253p1176378/AppConfigure
    //   273: putfield 58	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   276: aload 8
    //   278: invokevirtual 324	java/io/ObjectInputStream:close	()V
    //   281: aload_0
    //   282: getfield 58	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   285: invokevirtual 327	com/appbuilder/u846253p1176378/AppConfigure:getShowLink	()Z
    //   288: ifeq +349 -> 637
    //   291: aload_0
    //   292: getfield 262	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notificationMain	Landroid/widget/RelativeLayout;
    //   295: ldc_w 328
    //   298: invokevirtual 331	android/widget/RelativeLayout:setBackgroundResource	(I)V
    //   301: new 297	java/io/File
    //   304: dup
    //   305: new 161	java/lang/StringBuilder
    //   308: dup
    //   309: invokespecial 162	java/lang/StringBuilder:<init>	()V
    //   312: invokestatic 285	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   315: invokevirtual 288	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   318: ldc_w 290
    //   321: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: aload_0
    //   325: invokevirtual 293	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:getPackageName	()Ljava/lang/String;
    //   328: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: ldc_w 333
    //   334: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   340: invokespecial 302	java/io/File:<init>	(Ljava/lang/String;)V
    //   343: astore_3
    //   344: aload_3
    //   345: invokevirtual 305	java/io/File:exists	()Z
    //   348: ifeq +445 -> 793
    //   351: new 307	java/io/ObjectInputStream
    //   354: dup
    //   355: new 309	java/io/FileInputStream
    //   358: dup
    //   359: aload_3
    //   360: invokespecial 312	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   363: invokespecial 315	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   366: astore 4
    //   368: aload_0
    //   369: aload 4
    //   371: invokevirtual 319	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   374: checkcast 47	java/util/ArrayList
    //   377: putfield 50	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notifications	Ljava/util/ArrayList;
    //   380: aload 4
    //   382: invokevirtual 324	java/io/ObjectInputStream:close	()V
    //   385: aload_3
    //   386: invokevirtual 336	java/io/File:delete	()Z
    //   389: pop
    //   390: aload_0
    //   391: getfield 268	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:btnClose	Landroid/widget/Button;
    //   394: new 338	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification$2
    //   397: dup
    //   398: aload_0
    //   399: invokespecial 339	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification$2:<init>	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotification;)V
    //   402: invokevirtual 343	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   405: aload_0
    //   406: getfield 271	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:btnApp	Landroid/widget/Button;
    //   409: new 345	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification$3
    //   412: dup
    //   413: aload_0
    //   414: invokespecial 346	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification$3:<init>	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotification;)V
    //   417: invokevirtual 343	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   420: aload_0
    //   421: getfield 62	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:btnNext	Landroid/widget/ImageView;
    //   424: new 348	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification$4
    //   427: dup
    //   428: aload_0
    //   429: invokespecial 349	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification$4:<init>	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotification;)V
    //   432: invokevirtual 350	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   435: aload_0
    //   436: getfield 64	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:btnPrev	Landroid/widget/ImageView;
    //   439: new 352	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification$5
    //   442: dup
    //   443: aload_0
    //   444: invokespecial 353	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification$5:<init>	(Lcom/appbuilder/u846253p1176378/PushNotification/AppPushNotification;)V
    //   447: invokevirtual 350	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   450: aload_0
    //   451: getfield 52	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:textViewNotification	Landroid/widget/TextView;
    //   454: ldc_w 355
    //   457: invokevirtual 141	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   460: aload_0
    //   461: getfield 54	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:textViewCounter	Landroid/widget/TextView;
    //   464: ldc_w 355
    //   467: invokevirtual 141	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   470: aload_0
    //   471: getfield 50	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notifications	Ljava/util/ArrayList;
    //   474: invokevirtual 124	java/util/ArrayList:size	()I
    //   477: ifle +98 -> 575
    //   480: invokestatic 147	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   483: invokevirtual 151	java/util/Locale:toString	()Ljava/lang/String;
    //   486: ldc 153
    //   488: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   491: ifeq +258 -> 749
    //   494: aload_0
    //   495: getfield 54	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:textViewCounter	Landroid/widget/TextView;
    //   498: new 161	java/lang/StringBuilder
    //   501: dup
    //   502: invokespecial 162	java/lang/StringBuilder:<init>	()V
    //   505: iconst_1
    //   506: aload_0
    //   507: getfield 43	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:position	I
    //   510: iadd
    //   511: invokevirtual 166	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   514: ldc 168
    //   516: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   519: aload_0
    //   520: getfield 50	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notifications	Ljava/util/ArrayList;
    //   523: invokevirtual 124	java/util/ArrayList:size	()I
    //   526: invokevirtual 166	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   529: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   532: invokevirtual 141	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   535: aload_0
    //   536: getfield 52	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:textViewNotification	Landroid/widget/TextView;
    //   539: aload_0
    //   540: getfield 50	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notifications	Ljava/util/ArrayList;
    //   543: aload_0
    //   544: getfield 43	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:position	I
    //   547: invokevirtual 133	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   550: checkcast 135	java/lang/CharSequence
    //   553: invokevirtual 141	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   556: aload_0
    //   557: getfield 50	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notifications	Ljava/util/ArrayList;
    //   560: invokevirtual 124	java/util/ArrayList:size	()I
    //   563: iconst_1
    //   564: if_icmple +11 -> 575
    //   567: aload_0
    //   568: getfield 62	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:btnNext	Landroid/widget/ImageView;
    //   571: iconst_0
    //   572: invokevirtual 129	android/widget/ImageView:setVisibility	(I)V
    //   575: aload_0
    //   576: getfield 262	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notificationMain	Landroid/widget/RelativeLayout;
    //   579: aload_0
    //   580: invokevirtual 359	android/widget/RelativeLayout:setOnTouchListener	(Landroid/view/View$OnTouchListener;)V
    //   583: return
    //   584: aload_0
    //   585: iconst_1
    //   586: putfield 60	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:sdAvailable	Z
    //   589: goto -423 -> 166
    //   592: astore 20
    //   594: ldc_w 355
    //   597: ldc_w 355
    //   600: invokestatic 365	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   603: pop
    //   604: goto -323 -> 281
    //   607: astore 19
    //   609: ldc_w 355
    //   612: ldc_w 355
    //   615: invokestatic 365	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   618: pop
    //   619: goto -338 -> 281
    //   622: astore 18
    //   624: ldc_w 355
    //   627: ldc_w 355
    //   630: invokestatic 365	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   633: pop
    //   634: goto -353 -> 281
    //   637: new 297	java/io/File
    //   640: dup
    //   641: new 161	java/lang/StringBuilder
    //   644: dup
    //   645: invokespecial 162	java/lang/StringBuilder:<init>	()V
    //   648: aload_0
    //   649: getfield 295	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:cachePath	Ljava/lang/String;
    //   652: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: ldc_w 367
    //   658: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   661: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   664: invokespecial 302	java/io/File:<init>	(Ljava/lang/String;)V
    //   667: invokevirtual 305	java/io/File:exists	()Z
    //   670: ifeq +66 -> 736
    //   673: new 369	android/graphics/drawable/BitmapDrawable
    //   676: dup
    //   677: aload_0
    //   678: invokevirtual 373	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:getResources	()Landroid/content/res/Resources;
    //   681: new 161	java/lang/StringBuilder
    //   684: dup
    //   685: invokespecial 162	java/lang/StringBuilder:<init>	()V
    //   688: aload_0
    //   689: getfield 295	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:cachePath	Ljava/lang/String;
    //   692: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   695: ldc_w 367
    //   698: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   701: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   704: invokespecial 376	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Ljava/lang/String;)V
    //   707: astore 13
    //   709: aload_0
    //   710: getfield 262	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notificationMain	Landroid/widget/RelativeLayout;
    //   713: aload 13
    //   715: invokevirtual 380	android/widget/RelativeLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   718: goto -417 -> 301
    //   721: astore 11
    //   723: ldc_w 355
    //   726: ldc_w 355
    //   729: invokestatic 365	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   732: pop
    //   733: goto -432 -> 301
    //   736: aload_0
    //   737: getfield 262	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notificationMain	Landroid/widget/RelativeLayout;
    //   740: ldc_w 381
    //   743: invokevirtual 331	android/widget/RelativeLayout:setBackgroundResource	(I)V
    //   746: goto -445 -> 301
    //   749: aload_0
    //   750: getfield 54	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:textViewCounter	Landroid/widget/TextView;
    //   753: new 161	java/lang/StringBuilder
    //   756: dup
    //   757: invokespecial 162	java/lang/StringBuilder:<init>	()V
    //   760: iconst_1
    //   761: aload_0
    //   762: getfield 43	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:position	I
    //   765: iadd
    //   766: invokevirtual 166	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   769: ldc 182
    //   771: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   774: aload_0
    //   775: getfield 50	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:notifications	Ljava/util/ArrayList;
    //   778: invokevirtual 124	java/util/ArrayList:size	()I
    //   781: invokevirtual 166	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   784: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   787: invokevirtual 141	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   790: goto -255 -> 535
    //   793: aload_0
    //   794: invokevirtual 104	com/appbuilder/u846253p1176378/PushNotification/AppPushNotification:finish	()V
    //   797: return
    //   798: astore 5
    //   800: goto -415 -> 385
    //   803: astore 16
    //   805: goto -181 -> 624
    //   808: astore 14
    //   810: goto -201 -> 609
    //   813: astore 9
    //   815: goto -221 -> 594
    //
    // Exception table:
    //   from	to	target	type
    //   246	264	592	java/io/StreamCorruptedException
    //   246	264	607	java/io/IOException
    //   246	264	622	java/lang/ClassNotFoundException
    //   281	301	721	java/lang/Exception
    //   637	718	721	java/lang/Exception
    //   736	746	721	java/lang/Exception
    //   351	385	798	java/lang/Exception
    //   264	281	803	java/lang/ClassNotFoundException
    //   264	281	808	java/io/IOException
    //   264	281	813	java/io/StreamCorruptedException
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 2:
    default:
    case 0:
    case 1:
    }
    do
    {
      return true;
      this.startPos = paramMotionEvent.getX();
      return true;
      if (paramMotionEvent.getX() - this.startPos <= 90.0F)
        continue;
      this.handler.sendEmptyMessage(0);
      return true;
    }
    while (this.startPos - paramMotionEvent.getX() <= 90.0F);
    this.handler.sendEmptyMessage(1);
    return true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.PushNotification.AppPushNotification
 * JD-Core Version:    0.6.0
 */