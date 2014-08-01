package com.ibuildapp.romanblack.MenuPlugin;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Statics;
import com.appbuilder.sdk.android.authorization.Authorization;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class MenuPluginItemDetails extends AppBuilderModuleMain
{
  private final String EMAIL_IMAGE_NAME = "image.jpg";
  private final int EMAIL_SEND = 10004;
  private final int FACEBOOK_AUTHORIZATION_ACTIVITY = 10000;
  private final int FACEBOOK_PUBLISH_ACTIVITY = 10002;
  private final int IMAGE_WIDTH = 270;
  private final int TWITTER_AUTHORIZATION_ACTIVITY = 10001;
  private final int TWITTER_PUBLISH_ACTIVITY = 10003;
  private String appid;
  private String appname;
  private AssetManager assetMgr;
  private String cachePath;
  private MenuPluginXmlClasses.ColorSkin colorskin;
  private String currency;
  private String currencyposition;
  private TextView description;
  private LinearLayout descriptionHolder;
  private boolean hasAd;
  private ImageView image;
  private MenuPluginXmlClasses.categoryItem item;
  private LinearLayout itemnameHolder;
  private TextView name;
  private TextView price;
  private LinearLayout priceHolder;
  private ImageView priceLeftImage;
  private ImageView priceRightImage;
  private ProgressBar progress;
  private LinearLayout root;
  private LinearLayout shareButton;
  private String stringPrice;

  private Intent chooseEmailClient()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    List localList = getPackageManager().queryIntentActivities(localIntent, 0);
    Object localObject = null;
    Iterator localIterator1 = localList.iterator();
    while (localIterator1.hasNext())
    {
      ResolveInfo localResolveInfo2 = (ResolveInfo)localIterator1.next();
      if ((!localResolveInfo2.activityInfo.packageName.endsWith(".gm")) && (!localResolveInfo2.activityInfo.name.toLowerCase().contains("gmail")))
        continue;
      localObject = localResolveInfo2;
    }
    if (localObject == null)
    {
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
      {
        ResolveInfo localResolveInfo1 = (ResolveInfo)localIterator2.next();
        if (!localResolveInfo1.activityInfo.name.toLowerCase().contains("mail"))
          continue;
        localObject = localResolveInfo1;
      }
    }
    if (localObject != null)
      localIntent.setClassName(localObject.activityInfo.packageName, localObject.activityInfo.name);
    return localIntent;
  }

  private String inputStreamToFile(InputStream paramInputStream)
  {
    try
    {
      File localFile1 = new File(this.cachePath);
      if (!localFile1.exists())
        localFile1.mkdirs();
      File localFile2 = new File(this.cachePath + File.separator + "image.jpg");
      try
      {
        if (localFile2.exists())
        {
          localFile2.delete();
          localFile2.createNewFile();
        }
        while (true)
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
          try
          {
            byte[] arrayOfByte = new byte[1024];
            while (true)
            {
              int i = paramInputStream.read(arrayOfByte);
              if (i == -1)
                break;
              localFileOutputStream.write(arrayOfByte, 0, i);
            }
          }
          catch (IOException localIOException1)
          {
          }
          label127: localIOException1.printStackTrace();
          return null;
          localFile2.createNewFile();
        }
      }
      catch (IOException localIOException2)
      {
        break label127;
      }
      return localFile2.getAbsolutePath();
    }
    catch (IOException localIOException3)
    {
      break label127;
    }
  }

  private void shareFacebook()
  {
    Intent localIntent = new Intent(this, SharingActivity.class);
    localIntent.putExtra("itemname", this.item.getItemName());
    localIntent.putExtra("file_link", this.item.getItemImagePath());
    localIntent.putExtra("description", this.item.getItemDescripton());
    localIntent.putExtra("hasAd", this.hasAd);
    localIntent.putExtra("appname", this.appname);
    localIntent.putExtra("appid", this.appid);
    localIntent.putExtra("type", "facebook");
    localIntent.putExtra("image_url", this.item.getItemUrl());
    startActivityForResult(localIntent, 10002);
  }

  private void shareTwitter()
  {
    Intent localIntent = new Intent(this, SharingActivity.class);
    localIntent.putExtra("itemname", this.item.getItemName());
    localIntent.putExtra("file_link", this.item.getItemImagePath());
    localIntent.putExtra("description", this.item.getItemDescripton());
    localIntent.putExtra("hasAd", this.hasAd);
    localIntent.putExtra("appname", this.appname);
    localIntent.putExtra("appid", this.appid);
    localIntent.putExtra("image_url", this.item.getItemUrl());
    localIntent.putExtra("type", "twitter");
    startActivityForResult(localIntent, 10003);
  }

  // ERROR //
  public void create()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: invokevirtual 289	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:requestWindowFeature	(I)Z
    //   5: pop
    //   6: aload_0
    //   7: getstatic 294	com/ibuildapp/romanblack/MenuPlugin/R$layout:sergeyb_menuplugin_itemdetails	I
    //   10: invokevirtual 298	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:setContentView	(I)V
    //   13: aload_0
    //   14: invokevirtual 302	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:getWindow	()Landroid/view/Window;
    //   17: sipush 1024
    //   20: sipush 1024
    //   23: invokevirtual 308	android/view/Window:setFlags	(II)V
    //   26: aload_0
    //   27: aload_0
    //   28: invokevirtual 312	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:getAssets	()Landroid/content/res/AssetManager;
    //   31: putfield 100	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:assetMgr	Landroid/content/res/AssetManager;
    //   34: aload_0
    //   35: aload_0
    //   36: getstatic 317	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_itemdetails_progress	I
    //   39: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   42: checkcast 323	android/widget/ProgressBar
    //   45: putfield 325	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:progress	Landroid/widget/ProgressBar;
    //   48: aload_0
    //   49: aload_0
    //   50: getstatic 328	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_itemdetails_fullimage	I
    //   53: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   56: checkcast 330	android/widget/ImageView
    //   59: putfield 332	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:image	Landroid/widget/ImageView;
    //   62: aload_0
    //   63: aload_0
    //   64: getstatic 335	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_itemdetails_item_name	I
    //   67: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   70: checkcast 337	android/widget/TextView
    //   73: putfield 339	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:name	Landroid/widget/TextView;
    //   76: aload_0
    //   77: aload_0
    //   78: getstatic 342	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_itemdetails_item_description	I
    //   81: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   84: checkcast 337	android/widget/TextView
    //   87: putfield 344	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:description	Landroid/widget/TextView;
    //   90: aload_0
    //   91: aload_0
    //   92: getstatic 347	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_itemdetails_item_price	I
    //   95: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   98: checkcast 337	android/widget/TextView
    //   101: putfield 349	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:price	Landroid/widget/TextView;
    //   104: aload_0
    //   105: aload_0
    //   106: getstatic 352	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_menuplugin_root	I
    //   109: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   112: checkcast 354	android/widget/LinearLayout
    //   115: putfield 356	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:root	Landroid/widget/LinearLayout;
    //   118: aload_0
    //   119: aload_0
    //   120: getstatic 359	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_itemdetails_item_description_layout	I
    //   123: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   126: checkcast 354	android/widget/LinearLayout
    //   129: putfield 361	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:descriptionHolder	Landroid/widget/LinearLayout;
    //   132: aload_0
    //   133: aload_0
    //   134: getstatic 364	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_itemdetails_item_price_layout	I
    //   137: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   140: checkcast 354	android/widget/LinearLayout
    //   143: putfield 366	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceHolder	Landroid/widget/LinearLayout;
    //   146: aload_0
    //   147: aload_0
    //   148: getstatic 369	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_tablereservation_list_button	I
    //   151: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   154: checkcast 354	android/widget/LinearLayout
    //   157: putfield 371	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:shareButton	Landroid/widget/LinearLayout;
    //   160: aload_0
    //   161: aload_0
    //   162: getstatic 374	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_itemdetails_item_name_layout	I
    //   165: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   168: checkcast 354	android/widget/LinearLayout
    //   171: putfield 376	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:itemnameHolder	Landroid/widget/LinearLayout;
    //   174: aload_0
    //   175: aload_0
    //   176: getstatic 379	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_menuplugin_prive_left_border	I
    //   179: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   182: checkcast 330	android/widget/ImageView
    //   185: putfield 381	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceLeftImage	Landroid/widget/ImageView;
    //   188: aload_0
    //   189: aload_0
    //   190: getstatic 384	com/ibuildapp/romanblack/MenuPlugin/R$id:sergeyb_menuplugin_prive_right_border	I
    //   193: invokevirtual 321	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:findViewById	(I)Landroid/view/View;
    //   196: checkcast 330	android/widget/ImageView
    //   199: putfield 386	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceRightImage	Landroid/widget/ImageView;
    //   202: aload_0
    //   203: invokevirtual 390	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:getResources	()Landroid/content/res/Resources;
    //   206: invokevirtual 396	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   209: getfield 402	android/util/DisplayMetrics:density	F
    //   212: fstore_2
    //   213: new 398	android/util/DisplayMetrics
    //   216: dup
    //   217: invokespecial 403	android/util/DisplayMetrics:<init>	()V
    //   220: astore_3
    //   221: aload_0
    //   222: invokevirtual 407	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:getWindowManager	()Landroid/view/WindowManager;
    //   225: invokeinterface 413 1 0
    //   230: aload_3
    //   231: invokevirtual 419	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   234: aload_3
    //   235: getfield 422	android/util/DisplayMetrics:widthPixels	I
    //   238: ldc_w 423
    //   241: fload_2
    //   242: fmul
    //   243: f2i
    //   244: isub
    //   245: iconst_2
    //   246: idiv
    //   247: istore 4
    //   249: new 425	android/widget/LinearLayout$LayoutParams
    //   252: dup
    //   253: iconst_m1
    //   254: bipush 254
    //   256: invokespecial 427	android/widget/LinearLayout$LayoutParams:<init>	(II)V
    //   259: astore 5
    //   261: aload 5
    //   263: iload 4
    //   265: bipush 15
    //   267: iload 4
    //   269: iconst_0
    //   270: invokevirtual 431	android/widget/LinearLayout$LayoutParams:setMargins	(IIII)V
    //   273: aload_0
    //   274: getfield 361	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:descriptionHolder	Landroid/widget/LinearLayout;
    //   277: aload 5
    //   279: invokevirtual 435	android/widget/LinearLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   282: aload_0
    //   283: getfield 376	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:itemnameHolder	Landroid/widget/LinearLayout;
    //   286: aload 5
    //   288: invokevirtual 435	android/widget/LinearLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   291: new 425	android/widget/LinearLayout$LayoutParams
    //   294: dup
    //   295: aload 5
    //   297: invokespecial 438	android/widget/LinearLayout$LayoutParams:<init>	(Landroid/view/ViewGroup$MarginLayoutParams;)V
    //   300: astore 6
    //   302: aload 6
    //   304: iload 4
    //   306: bipush 15
    //   308: iload 4
    //   310: bipush 10
    //   312: invokevirtual 431	android/widget/LinearLayout$LayoutParams:setMargins	(IIII)V
    //   315: aload_0
    //   316: getfield 366	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceHolder	Landroid/widget/LinearLayout;
    //   319: aload 6
    //   321: invokevirtual 435	android/widget/LinearLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   324: aload_0
    //   325: getfield 371	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:shareButton	Landroid/widget/LinearLayout;
    //   328: astore 7
    //   330: new 440	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails$1
    //   333: dup
    //   334: aload_0
    //   335: invokespecial 442	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails$1:<init>	(Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails;)V
    //   338: astore 8
    //   340: aload 7
    //   342: aload 8
    //   344: invokevirtual 446	android/widget/LinearLayout:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   347: aload_0
    //   348: invokevirtual 449	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:getIntent	()Landroid/content/Intent;
    //   351: astore 9
    //   353: aload_0
    //   354: aload 9
    //   356: ldc_w 451
    //   359: invokevirtual 455	android/content/Intent:getSerializableExtra	(Ljava/lang/String;)Ljava/io/Serializable;
    //   362: checkcast 246	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem
    //   365: putfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   368: aload_0
    //   369: aload 9
    //   371: ldc_w 456
    //   374: invokevirtual 455	android/content/Intent:getSerializableExtra	(Ljava/lang/String;)Ljava/io/Serializable;
    //   377: checkcast 458	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin
    //   380: putfield 460	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:colorskin	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin;
    //   383: aload_0
    //   384: aload 9
    //   386: ldc_w 461
    //   389: invokevirtual 465	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   392: putfield 467	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:currency	Ljava/lang/String;
    //   395: aload_0
    //   396: aload 9
    //   398: ldc_w 262
    //   401: iconst_1
    //   402: invokevirtual 471	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   405: putfield 96	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:hasAd	Z
    //   408: aload_0
    //   409: aload 9
    //   411: ldc_w 266
    //   414: invokevirtual 465	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   417: putfield 85	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:appname	Ljava/lang/String;
    //   420: aload_0
    //   421: aload 9
    //   423: ldc_w 267
    //   426: invokevirtual 465	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   429: putfield 82	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:appid	Ljava/lang/String;
    //   432: aload_0
    //   433: aload 9
    //   435: ldc_w 472
    //   438: invokevirtual 465	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   441: putfield 188	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:cachePath	Ljava/lang/String;
    //   444: aload_0
    //   445: aload 9
    //   447: ldc_w 473
    //   450: invokevirtual 465	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   453: putfield 475	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:currencyposition	Ljava/lang/String;
    //   456: aload_0
    //   457: getfield 356	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:root	Landroid/widget/LinearLayout;
    //   460: aload_0
    //   461: getfield 460	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:colorskin	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin;
    //   464: getfield 478	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin:color1	I
    //   467: invokevirtual 481	android/widget/LinearLayout:setBackgroundColor	(I)V
    //   470: aload_0
    //   471: getfield 339	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:name	Landroid/widget/TextView;
    //   474: aload_0
    //   475: getfield 460	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:colorskin	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin;
    //   478: getfield 484	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin:color3	I
    //   481: invokevirtual 487	android/widget/TextView:setTextColor	(I)V
    //   484: aload_0
    //   485: getfield 344	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:description	Landroid/widget/TextView;
    //   488: aload_0
    //   489: getfield 460	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:colorskin	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin;
    //   492: getfield 490	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin:color4	I
    //   495: invokevirtual 487	android/widget/TextView:setTextColor	(I)V
    //   498: aload_0
    //   499: getfield 349	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:price	Landroid/widget/TextView;
    //   502: aload_0
    //   503: getfield 460	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:colorskin	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin;
    //   506: getfield 493	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$ColorSkin:color5	I
    //   509: invokevirtual 487	android/widget/TextView:setTextColor	(I)V
    //   512: aload_0
    //   513: aload_0
    //   514: invokevirtual 390	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:getResources	()Landroid/content/res/Resources;
    //   517: getstatic 498	com/ibuildapp/romanblack/MenuPlugin/R$string:common_back_upper	I
    //   520: invokevirtual 502	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   523: iconst_1
    //   524: new 504	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails$2
    //   527: dup
    //   528: aload_0
    //   529: invokespecial 505	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails$2:<init>	(Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails;)V
    //   532: invokevirtual 509	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:setTopBarLeftButtonText	(Ljava/lang/String;ZLandroid/view/View$OnClickListener;)V
    //   535: aload_0
    //   536: aload_0
    //   537: getfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   540: invokevirtual 249	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem:getItemName	()Ljava/lang/String;
    //   543: invokevirtual 512	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:setTopBarTitle	(Ljava/lang/String;)V
    //   546: aload_0
    //   547: invokevirtual 515	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:swipeBlock	()V
    //   550: aload_0
    //   551: getfield 339	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:name	Landroid/widget/TextView;
    //   554: aload_0
    //   555: getfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   558: invokevirtual 249	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem:getItemName	()Ljava/lang/String;
    //   561: invokevirtual 519	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   564: aload_0
    //   565: getfield 344	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:description	Landroid/widget/TextView;
    //   568: aload_0
    //   569: getfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   572: invokevirtual 261	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem:getItemDescripton	()Ljava/lang/String;
    //   575: invokevirtual 519	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   578: aload_0
    //   579: getfield 467	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:currency	Ljava/lang/String;
    //   582: ifnull +348 -> 930
    //   585: aload_0
    //   586: getfield 467	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:currency	Ljava/lang/String;
    //   589: invokevirtual 523	java/lang/String:length	()I
    //   592: ifeq +338 -> 930
    //   595: aload_0
    //   596: getfield 349	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:price	Landroid/widget/TextView;
    //   599: ifnull +294 -> 893
    //   602: aload_0
    //   603: getfield 349	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:price	Landroid/widget/TextView;
    //   606: invokevirtual 524	android/widget/TextView:length	()I
    //   609: ifeq +284 -> 893
    //   612: aload_0
    //   613: getfield 475	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:currencyposition	Ljava/lang/String;
    //   616: ifnull +208 -> 824
    //   619: aload_0
    //   620: getfield 475	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:currencyposition	Ljava/lang/String;
    //   623: ldc_w 526
    //   626: invokevirtual 530	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   629: ifeq +122 -> 751
    //   632: aload_0
    //   633: new 197	java/lang/StringBuilder
    //   636: dup
    //   637: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   640: aload_0
    //   641: getfield 467	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:currency	Ljava/lang/String;
    //   644: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   647: ldc_w 532
    //   650: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   653: aload_0
    //   654: getfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   657: invokevirtual 535	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem:getItemPrice	()Ljava/lang/String;
    //   660: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   663: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   666: putfield 92	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:stringPrice	Ljava/lang/String;
    //   669: aload_0
    //   670: getfield 349	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:price	Landroid/widget/TextView;
    //   673: aload_0
    //   674: getfield 92	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:stringPrice	Ljava/lang/String;
    //   677: invokevirtual 519	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   680: aload_0
    //   681: getfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   684: invokevirtual 538	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem:getItemImageResPath	()Ljava/lang/String;
    //   687: astore 21
    //   689: aload 21
    //   691: ifnull +444 -> 1135
    //   694: aload 21
    //   696: ldc_w 540
    //   699: invokevirtual 530	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   702: ifne +433 -> 1135
    //   705: aload_0
    //   706: getfield 100	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:assetMgr	Landroid/content/res/AssetManager;
    //   709: aload 21
    //   711: invokevirtual 546	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   714: astore 22
    //   716: aload 22
    //   718: astore 13
    //   720: aload 13
    //   722: ifnull +245 -> 967
    //   725: aload 13
    //   727: invokestatic 552	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   730: astore 20
    //   732: aload_0
    //   733: getfield 332	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:image	Landroid/widget/ImageView;
    //   736: aload 20
    //   738: invokevirtual 556	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   741: aload_0
    //   742: getfield 325	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:progress	Landroid/widget/ProgressBar;
    //   745: bipush 8
    //   747: invokevirtual 559	android/widget/ProgressBar:setVisibility	(I)V
    //   750: return
    //   751: aload_0
    //   752: new 197	java/lang/StringBuilder
    //   755: dup
    //   756: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   759: aload_0
    //   760: getfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   763: invokevirtual 535	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem:getItemPrice	()Ljava/lang/String;
    //   766: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   769: ldc_w 532
    //   772: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   775: aload_0
    //   776: getfield 467	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:currency	Ljava/lang/String;
    //   779: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   782: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   785: putfield 92	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:stringPrice	Ljava/lang/String;
    //   788: aload_0
    //   789: getfield 349	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:price	Landroid/widget/TextView;
    //   792: aload_0
    //   793: getfield 92	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:stringPrice	Ljava/lang/String;
    //   796: invokevirtual 519	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   799: goto -119 -> 680
    //   802: astore 10
    //   804: ldc_w 540
    //   807: ldc_w 540
    //   810: invokestatic 565	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   813: pop
    //   814: aload_0
    //   815: getfield 325	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:progress	Landroid/widget/ProgressBar;
    //   818: bipush 8
    //   820: invokevirtual 559	android/widget/ProgressBar:setVisibility	(I)V
    //   823: return
    //   824: aload_0
    //   825: new 197	java/lang/StringBuilder
    //   828: dup
    //   829: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   832: aload_0
    //   833: getfield 467	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:currency	Ljava/lang/String;
    //   836: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   839: ldc_w 532
    //   842: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   845: aload_0
    //   846: getfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   849: invokevirtual 535	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem:getItemPrice	()Ljava/lang/String;
    //   852: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   855: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   858: putfield 92	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:stringPrice	Ljava/lang/String;
    //   861: aload_0
    //   862: getfield 349	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:price	Landroid/widget/TextView;
    //   865: aload_0
    //   866: getfield 92	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:stringPrice	Ljava/lang/String;
    //   869: invokevirtual 519	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   872: aload_0
    //   873: getfield 381	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceLeftImage	Landroid/widget/ImageView;
    //   876: bipush 8
    //   878: invokevirtual 566	android/widget/ImageView:setVisibility	(I)V
    //   881: aload_0
    //   882: getfield 386	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceRightImage	Landroid/widget/ImageView;
    //   885: bipush 8
    //   887: invokevirtual 566	android/widget/ImageView:setVisibility	(I)V
    //   890: goto -210 -> 680
    //   893: aload_0
    //   894: ldc_w 540
    //   897: putfield 92	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:stringPrice	Ljava/lang/String;
    //   900: aload_0
    //   901: getfield 381	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceLeftImage	Landroid/widget/ImageView;
    //   904: bipush 8
    //   906: invokevirtual 566	android/widget/ImageView:setVisibility	(I)V
    //   909: aload_0
    //   910: getfield 386	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceRightImage	Landroid/widget/ImageView;
    //   913: bipush 8
    //   915: invokevirtual 566	android/widget/ImageView:setVisibility	(I)V
    //   918: aload_0
    //   919: getfield 349	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:price	Landroid/widget/TextView;
    //   922: bipush 8
    //   924: invokevirtual 567	android/widget/TextView:setVisibility	(I)V
    //   927: goto -247 -> 680
    //   930: aload_0
    //   931: ldc_w 540
    //   934: putfield 92	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:stringPrice	Ljava/lang/String;
    //   937: aload_0
    //   938: getfield 381	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceLeftImage	Landroid/widget/ImageView;
    //   941: bipush 8
    //   943: invokevirtual 566	android/widget/ImageView:setVisibility	(I)V
    //   946: aload_0
    //   947: getfield 386	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:priceRightImage	Landroid/widget/ImageView;
    //   950: bipush 8
    //   952: invokevirtual 566	android/widget/ImageView:setVisibility	(I)V
    //   955: aload_0
    //   956: getfield 349	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:price	Landroid/widget/TextView;
    //   959: bipush 8
    //   961: invokevirtual 567	android/widget/TextView:setVisibility	(I)V
    //   964: goto -284 -> 680
    //   967: new 197	java/lang/StringBuilder
    //   970: dup
    //   971: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   974: aload_0
    //   975: getfield 188	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:cachePath	Ljava/lang/String;
    //   978: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   981: getstatic 205	java/io/File:separator	Ljava/lang/String;
    //   984: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   987: aload_0
    //   988: getfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   991: invokevirtual 276	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem:getItemUrl	()Ljava/lang/String;
    //   994: invokestatic 572	com/appbuilder/sdk/android/Utils:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   997: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1000: ldc_w 574
    //   1003: invokevirtual 202	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1006: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1009: astore 14
    //   1011: new 186	java/io/File
    //   1014: dup
    //   1015: aload 14
    //   1017: invokespecial 189	java/io/File:<init>	(Ljava/lang/String;)V
    //   1020: astore 15
    //   1022: aload 15
    //   1024: invokevirtual 192	java/io/File:exists	()Z
    //   1027: ifeq +32 -> 1059
    //   1030: aload 15
    //   1032: invokevirtual 237	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1035: invokestatic 580	com/ibuildapp/romanblack/MenuPlugin/MenuPluginUtils:proccessBitmap	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   1038: astore 19
    //   1040: aload_0
    //   1041: getfield 332	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:image	Landroid/widget/ImageView;
    //   1044: aload 19
    //   1046: invokevirtual 556	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   1049: aload_0
    //   1050: getfield 325	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:progress	Landroid/widget/ProgressBar;
    //   1053: bipush 8
    //   1055: invokevirtual 559	android/widget/ProgressBar:setVisibility	(I)V
    //   1058: return
    //   1059: aload_0
    //   1060: getfield 89	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:item	Lcom/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem;
    //   1063: invokevirtual 276	com/ibuildapp/romanblack/MenuPlugin/MenuPluginXmlClasses$categoryItem:getItemUrl	()Ljava/lang/String;
    //   1066: aload 14
    //   1068: invokestatic 584	com/ibuildapp/romanblack/MenuPlugin/MenuPluginUtils:downloadImg	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   1071: astore 16
    //   1073: aload 16
    //   1075: ifnull +29 -> 1104
    //   1078: aload 16
    //   1080: invokestatic 580	com/ibuildapp/romanblack/MenuPlugin/MenuPluginUtils:proccessBitmap	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   1083: astore 17
    //   1085: aload_0
    //   1086: getfield 332	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:image	Landroid/widget/ImageView;
    //   1089: aload 17
    //   1091: invokevirtual 556	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   1094: aload_0
    //   1095: getfield 325	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:progress	Landroid/widget/ProgressBar;
    //   1098: bipush 8
    //   1100: invokevirtual 559	android/widget/ProgressBar:setVisibility	(I)V
    //   1103: return
    //   1104: aload_0
    //   1105: invokevirtual 390	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:getResources	()Landroid/content/res/Resources;
    //   1108: getstatic 589	com/ibuildapp/romanblack/MenuPlugin/R$drawable:sergeyb_menuplugin_no_image	I
    //   1111: invokestatic 593	android/graphics/BitmapFactory:decodeResource	(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;
    //   1114: astore 18
    //   1116: aload_0
    //   1117: getfield 332	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:image	Landroid/widget/ImageView;
    //   1120: aload 18
    //   1122: invokevirtual 556	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   1125: aload_0
    //   1126: getfield 325	com/ibuildapp/romanblack/MenuPlugin/MenuPluginItemDetails:progress	Landroid/widget/ProgressBar;
    //   1129: bipush 8
    //   1131: invokevirtual 559	android/widget/ProgressBar:setVisibility	(I)V
    //   1134: return
    //   1135: aconst_null
    //   1136: astore 13
    //   1138: goto -418 -> 720
    //   1141: astore 12
    //   1143: aconst_null
    //   1144: astore 13
    //   1146: goto -426 -> 720
    //
    // Exception table:
    //   from	to	target	type
    //   550	680	802	java/lang/Exception
    //   680	689	802	java/lang/Exception
    //   694	716	802	java/lang/Exception
    //   725	750	802	java/lang/Exception
    //   751	799	802	java/lang/Exception
    //   824	890	802	java/lang/Exception
    //   893	927	802	java/lang/Exception
    //   930	964	802	java/lang/Exception
    //   967	1058	802	java/lang/Exception
    //   1059	1073	802	java/lang/Exception
    //   1078	1103	802	java/lang/Exception
    //   1104	1134	802	java/lang/Exception
    //   680	689	1141	java/io/IOException
    //   694	716	1141	java/io/IOException
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
    case 10000:
    case 10001:
    case 10003:
    case 10002:
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            if (paramInt2 != -1)
              continue;
            shareFacebook();
            return;
          }
          while (paramInt2 != 0);
          Toast localToast6 = Toast.makeText(this, getResources().getString(R.string.alert_facebook_auth_error), 1);
          localToast6.setGravity(81, 0, 95);
          localToast6.show();
          return;
          if (paramInt2 != -1)
            continue;
          shareTwitter();
          return;
        }
        while (paramInt2 != 0);
        Toast localToast5 = Toast.makeText(this, getResources().getString(R.string.alert_twitter_auth_error), 1);
        localToast5.setGravity(81, 0, 95);
        localToast5.show();
        return;
        if (paramInt2 != -1)
          continue;
        Toast localToast4 = Toast.makeText(this, getResources().getString(R.string.menuplugin_twitter_posted_success), 1);
        localToast4.setGravity(81, 0, 95);
        localToast4.show();
        return;
      }
      while (paramInt2 != 0);
      Toast localToast3 = Toast.makeText(this, getResources().getString(R.string.menuplugin_twitter_posted_error), 1);
      localToast3.setGravity(81, 0, 95);
      localToast3.show();
      return;
      if (paramInt2 != -1)
        continue;
      Toast localToast2 = Toast.makeText(this, getResources().getString(R.string.menuplugin_facebook_posted_success), 1);
      localToast2.setGravity(81, 0, 95);
      localToast2.show();
      return;
    }
    while (paramInt2 != 0);
    Toast localToast1 = Toast.makeText(this, getResources().getString(R.string.menuplugin_facebook_posted_error), 1);
    localToast1.setGravity(81, 0, 95);
    localToast1.show();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.add("Facebook").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)MenuPluginItemDetails.this.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
        {
          Toast localToast = Toast.makeText(MenuPluginItemDetails.this, MenuPluginItemDetails.this.getResources().getString(R.string.alert_no_internet), 1);
          localToast.setGravity(81, 0, 95);
          localToast.show();
          return true;
        }
        if (Authorization.getAuthorizedUser(1) != null)
        {
          MenuPluginItemDetails.this.shareFacebook();
          return true;
        }
        Authorization.authorize(MenuPluginItemDetails.this, 10000, 1);
        return true;
      }
    });
    paramMenu.add("Twitter").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)MenuPluginItemDetails.this.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
        {
          Toast localToast = Toast.makeText(MenuPluginItemDetails.this, MenuPluginItemDetails.this.getResources().getString(R.string.alert_no_internet), 1);
          localToast.setGravity(81, 0, 95);
          localToast.show();
          return true;
        }
        if (Authorization.getAuthorizedUser(2) != null)
        {
          MenuPluginItemDetails.this.shareTwitter();
          return true;
        }
        Authorization.authorize(MenuPluginItemDetails.this, 10001, 2);
        return true;
      }
    });
    paramMenu.add("Email").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)MenuPluginItemDetails.this.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
        {
          Toast localToast = Toast.makeText(MenuPluginItemDetails.this, MenuPluginItemDetails.this.getResources().getString(R.string.alert_no_internet), 1);
          localToast.setGravity(81, 0, 95);
          localToast.show();
        }
        while (true)
        {
          return true;
          Intent localIntent = MenuPluginItemDetails.this.chooseEmailClient();
          localIntent.setType("text/html");
          String str1 = MenuPluginItemDetails.this.getString(R.string.menuplugin_email_download_this);
          String str2 = MenuPluginItemDetails.this.getString(R.string.menuplugin_email_android_iphone_app);
          String str3 = MenuPluginItemDetails.this.getString(R.string.menuplugin_email_posted_via);
          MenuPluginItemDetails.this.getString(R.string.menuplugin_email_found_this);
          Object[] arrayOfObject1 = new Object[2];
          arrayOfObject1[0] = Statics.BASE_DOMEN;
          arrayOfObject1[1] = MenuPluginItemDetails.access$300(MenuPluginItemDetails.this);
          String str4 = String.format("http://%s/projects.php?action=info&projectid=%s", arrayOfObject1);
          String str5 = str1 + " %s " + str2 + ": <a href=\"%s\">%s</a><br>%s";
          Object[] arrayOfObject2 = new Object[4];
          arrayOfObject2[0] = MenuPluginItemDetails.access$400(MenuPluginItemDetails.this);
          arrayOfObject2[1] = str4;
          arrayOfObject2[2] = str4;
          arrayOfObject2[3] = (str3 + " <a href=\"http://ibuildapp.com\">www.ibuildapp.com</a>");
          String str6 = String.format(str5, arrayOfObject2);
          Object[] arrayOfObject3 = new Object[4];
          arrayOfObject3[0] = MenuPluginItemDetails.access$500(MenuPluginItemDetails.this).getItemName();
          arrayOfObject3[1] = MenuPluginItemDetails.access$500(MenuPluginItemDetails.this).getItemDescripton();
          arrayOfObject3[2] = MenuPluginItemDetails.access$600(MenuPluginItemDetails.this);
          label310: String str7;
          Object localObject;
          if (MenuPluginItemDetails.this.hasAd)
          {
            arrayOfObject3[3] = str6;
            str7 = String.format("<!DOCTYPE html><html><body><b>%s</b><br><br>%s<br><b>%s</b><br>%s</body></html>", arrayOfObject3).replaceAll("\\<img.*?>", "");
            localObject = null;
          }
          try
          {
            String str9 = MenuPluginItemDetails.this.item.getItemImageResPath();
            String str10 = MenuPluginItemDetails.this.item.getItemThumbnailPath();
            if (str10 != null)
            {
              boolean bool = str10.equals("");
              if (!bool)
                str8 = str10;
            }
            while (true)
            {
              if (localObject != null)
                str8 = MenuPluginItemDetails.this.inputStreamToFile((InputStream)localObject);
              if ((str8 != null) && (str8.length() > 0))
                localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str8)));
              localIntent.putExtra("android.intent.extra.SUBJECT", MenuPluginItemDetails.this.item.getItemName());
              localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml(str7));
              MenuPluginItemDetails.this.startActivityForResult(localIntent, 10004);
              break;
              str6 = "";
              break label310;
              if ((str9 != null) && (!str9.equals("")))
              {
                InputStream localInputStream = MenuPluginItemDetails.this.assetMgr.open(str9);
                localObject = localInputStream;
                str8 = null;
                continue;
              }
              str8 = null;
              localObject = null;
            }
          }
          catch (IOException localIOException)
          {
            while (true)
            {
              String str8 = null;
              localObject = null;
            }
          }
        }
      }
    });
    paramMenu.add(getResources().getString(R.string.common_cancel_upper)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        return true;
      }
    });
    return true;
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MenuPlugin.MenuPluginItemDetails
 * JD-Core Version:    0.6.0
 */