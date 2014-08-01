package com.ibuildapp.romanblack.MenuPlugin;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MenuPlugin extends AppBuilderModuleMain
  implements View.OnClickListener, AdapterView.OnItemClickListener
{
  private final int DOWNLOAD_IMAGE = 10007;
  private final int DRAW_IMAGE_LIST = 10008;
  private final int DRAW_IMAGE_PLACEHOLDER_GRID = 10010;
  private final int INITIALIZATIOIN_ERROR = 10001;
  private final int NEED_INTERNET_CONNECTION = 10006;
  private final int NULL_BACKGROUND_DRAWABLE = 10011;
  private final int SET_CONTENT = 10100;
  private AssetManager assetMgr;
  private String cachePath;
  private ArrayList<MenuPluginStatics.ItemEntry> entriesId = new ArrayList();
  private ImageView firstImg;
  private LinearLayout firstLayout;
  private TextView firstName;
  private ProgressBar firstProgress;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 10008:
        MenuPlugin.this.drawInterfaceList();
        return;
      case 10100:
        if (MenuPlugin.this.resultXml.showimages)
        {
          MenuPlugin.access$202(MenuPlugin.this, (ImageView)((MenuPluginStatics.ItemEntry)MenuPlugin.this.entriesId.get(paramMessage.arg1)).imgId);
          MenuPlugin.access$402(MenuPlugin.this, (TextView)((MenuPluginStatics.ItemEntry)MenuPlugin.this.entriesId.get(paramMessage.arg1)).textId);
          MenuPlugin.access$502(MenuPlugin.this, (ProgressBar)((MenuPluginStatics.ItemEntry)MenuPlugin.this.entriesId.get(paramMessage.arg1)).progressId);
          MenuPlugin.this.firstImg.setImageBitmap(((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(paramMessage.arg1)).getCategoryBitmap());
          MenuPlugin.this.firstName.setText(((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(paramMessage.arg1)).getCategoryName());
          MenuPlugin.this.firstProgress.setVisibility(4);
          return;
        }
        MenuPlugin.access$402(MenuPlugin.this, (TextView)((MenuPluginStatics.ItemEntry)MenuPlugin.this.entriesId.get(paramMessage.arg1)).textId);
        MenuPlugin.this.firstName.setText(((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(paramMessage.arg1)).getCategoryName());
        return;
      case 10010:
        MenuPlugin.this.drawPlaceholderGrid();
        return;
      case 10007:
        MenuPlugin.this.downloadImages();
        return;
      case 10001:
        Toast localToast2 = Toast.makeText(MenuPlugin.this, MenuPlugin.this.getResources().getString(R.string.alert_cannot_init), 1);
        localToast2.setGravity(81, 0, 95);
        localToast2.show();
        MenuPlugin.this.finish();
        return;
      case 10006:
        Toast localToast1 = Toast.makeText(MenuPlugin.this, MenuPlugin.this.getResources().getString(R.string.alert_no_internet), 1);
        localToast1.setGravity(81, 0, 95);
        localToast1.show();
        MenuPlugin.this.finish();
        return;
      case 10011:
      }
      ((View)paramMessage.obj).setBackgroundColor(Color.parseColor("#00ffffff"));
    }
  };
  private boolean hasAd;
  private MenuPluginXmlClasses resultXml = new MenuPluginXmlClasses();
  private LinearLayout root;
  private LinearLayout rootLayout;
  private ScrollView scroll;
  private ImageView secondImg;
  private LinearLayout secondLayout;
  private TextView secondName;
  private ProgressBar secondProgress;
  private Widget widget;
  private MenuPluginXmlParser xmlParser;

  private void downloadImages()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        if (MenuPlugin.this.resultXml.showimages)
        {
          int j = 0;
          if (j < MenuPlugin.this.resultXml.categoryList.size())
          {
            if (((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).items.size() == 0);
            while (true)
            {
              j++;
              break;
              try
              {
                String str5 = ((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).getCategoryResFile();
                InputStream localInputStream2;
                if ((str5 != null) && (!str5.equals("")))
                  localInputStream2 = MenuPlugin.this.assetMgr.open(str5);
                for (localInputStream1 = localInputStream2; ; localInputStream1 = null)
                {
                  if (localInputStream1 == null)
                    break label209;
                  Bitmap localBitmap4 = BitmapFactory.decodeStream(localInputStream1);
                  ((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).setCategoryBitmap(localBitmap4);
                  Message localMessage5 = MenuPlugin.this.handler.obtainMessage(10100, j, -1);
                  MenuPlugin.this.handler.sendMessage(localMessage5);
                  break;
                }
              }
              catch (IOException localIOException)
              {
                while (true)
                  InputStream localInputStream1 = null;
                label209: String str3 = MenuPlugin.this.cachePath + File.separator + Utils.md5(((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).getCategoryImg()) + ".jpg";
                File localFile = new File(str3);
                if (localFile.exists())
                {
                  Bitmap localBitmap3 = MenuPluginUtils.proccessBitmap(localFile.getAbsolutePath());
                  ((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).setCategoryBitmap(localBitmap3);
                  ((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).setCategoryImgFileName(localFile.getAbsolutePath());
                  Message localMessage4 = MenuPlugin.this.handler.obtainMessage(10100, j, -1);
                  MenuPlugin.this.handler.sendMessage(localMessage4);
                  continue;
                }
                String str4 = MenuPluginUtils.downloadImg(((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).getCategoryImg(), str3);
                if (str4 != null)
                {
                  Bitmap localBitmap2 = MenuPluginUtils.proccessBitmap(str4);
                  ((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).setCategoryBitmap(localBitmap2);
                  ((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).setCategoryImgFileName(localFile.getAbsolutePath());
                  Message localMessage3 = MenuPlugin.this.handler.obtainMessage(10100, j, -1);
                  MenuPlugin.this.handler.sendMessage(localMessage3);
                  continue;
                }
                Bitmap localBitmap1 = BitmapFactory.decodeResource(MenuPlugin.this.getResources(), R.drawable.sergeyb_menuplugin_no_image);
                ((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(j)).setCategoryBitmap(localBitmap1);
                Message localMessage2 = MenuPlugin.this.handler.obtainMessage(10100, j, -1);
                MenuPlugin.this.handler.sendMessage(localMessage2);
              }
            }
          }
          File[] arrayOfFile = new File(MenuPlugin.this.cachePath).listFiles();
          if (arrayOfFile != null)
            for (int k = 0; k < arrayOfFile.length; k++)
            {
              if (!arrayOfFile[k].isFile())
                continue;
              String str1 = arrayOfFile[k].getName();
              int m = 0;
              if (m >= MenuPlugin.this.resultXml.categoryList.size())
                continue;
              String str2 = ((MenuPluginXmlClasses.MenuCategory)MenuPlugin.this.resultXml.categoryList.get(m)).getCategoryImgFileName();
              if (str2 == null);
              do
              {
                m++;
                break;
              }
              while ((str2.equals("")) || (str2.compareToIgnoreCase(str1) != 0));
              arrayOfFile[k].delete();
            }
        }
        else
        {
          for (int i = 0; i < MenuPlugin.this.resultXml.categoryList.size(); i++)
          {
            Message localMessage1 = MenuPlugin.this.handler.obtainMessage(10100, i, -1);
            MenuPlugin.this.handler.sendMessage(localMessage1);
          }
        }
      }
    }).start();
  }

  private void drawInterfaceList()
  {
    this.root.removeView(this.scroll);
    float f = getResources().getDisplayMetrics().density;
    ListView localListView = new ListView(this);
    localListView.setDivider(null);
    localListView.setCacheColorHint(0);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    localLayoutParams.setMargins(0, (int)(10.0F * f), 0, 0);
    this.root.addView(localListView, localLayoutParams);
    localListView.setAdapter(new MenuPluginMainListAdapter(this, this.resultXml.categoryList, this.cachePath, this.resultXml.showimages, this.resultXml.colorSkin));
    localListView.setOnItemClickListener(this);
    localListView.setSelector(R.drawable.sergeyb_menuplugin_custom_background);
  }

  private void drawPlaceholderGrid()
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(this);
    if (this.resultXml.showimages)
      for (int j = 0; j < Math.ceil(this.resultXml.categoryList.size() / 2.0D); j++)
      {
        MenuPluginStatics.ItemEntry localItemEntry3 = new MenuPluginStatics.ItemEntry();
        MenuPluginStatics.ItemEntry localItemEntry4 = new MenuPluginStatics.ItemEntry();
        View localView2 = localLayoutInflater.inflate(R.layout.sergeyb_menuplugin_main_row, null);
        this.firstImg = ((ImageView)localView2.findViewById(R.id.sergeyb_menuplugin_category_layout_first_img));
        this.secondImg = ((ImageView)localView2.findViewById(R.id.sergeyb_menuplugin_category_layout_second_img));
        localItemEntry3.imgId = this.firstImg;
        localItemEntry4.imgId = this.secondImg;
        this.firstName = ((TextView)localView2.findViewById(R.id.sergeyb_menuplugin_category_layout_first_name));
        this.firstName.setTextColor(this.resultXml.colorSkin.color2);
        this.secondName = ((TextView)localView2.findViewById(R.id.sergeyb_menuplugin_category_layout_second_name));
        this.secondName.setTextColor(this.resultXml.colorSkin.color2);
        localItemEntry3.textId = this.firstName;
        localItemEntry4.textId = this.secondName;
        this.firstLayout = ((LinearLayout)localView2.findViewById(R.id.sergeyb_menuplugin_first_cell));
        this.secondLayout = ((LinearLayout)localView2.findViewById(R.id.sergeyb_menuplugin_second_cell));
        localItemEntry3.layoutId = this.firstLayout;
        localItemEntry4.layoutId = this.secondLayout;
        this.firstProgress = ((ProgressBar)localView2.findViewById(R.id.sergeyb_menuplugin_category_layout_first_progress));
        this.secondProgress = ((ProgressBar)localView2.findViewById(R.id.sergeyb_menuplugin_category_layout_second_progress));
        localItemEntry3.progressId = this.firstProgress;
        localItemEntry4.progressId = this.secondProgress;
        this.entriesId.add(j * 2, localItemEntry3);
        this.entriesId.add(1 + j * 2, localItemEntry4);
        this.firstLayout.setOnClickListener(this);
        this.secondLayout.setOnClickListener(this);
        if (1 + j * 2 <= -1 + this.resultXml.categoryList.size())
          this.secondLayout.setVisibility(0);
        this.rootLayout.addView(localView2);
      }
    for (int i = 0; i < Math.ceil(this.resultXml.categoryList.size() / 2.0D); i++)
    {
      MenuPluginStatics.ItemEntry localItemEntry1 = new MenuPluginStatics.ItemEntry();
      MenuPluginStatics.ItemEntry localItemEntry2 = new MenuPluginStatics.ItemEntry();
      View localView1 = localLayoutInflater.inflate(R.layout.sergeyb_menuplugin_main_row_no_image, null);
      this.firstName = ((TextView)localView1.findViewById(R.id.sergeyb_menuplugin_category_layout_first_name));
      this.firstName.setTextColor(this.resultXml.colorSkin.color2);
      this.secondName = ((TextView)localView1.findViewById(R.id.sergeyb_menuplugin_category_layout_second_name));
      this.secondName.setTextColor(this.resultXml.colorSkin.color2);
      localItemEntry1.textId = this.firstName;
      localItemEntry2.textId = this.secondName;
      this.firstLayout = ((LinearLayout)localView1.findViewById(R.id.sergeyb_menuplugin_first_cell));
      this.secondLayout = ((LinearLayout)localView1.findViewById(R.id.sergeyb_menuplugin_second_cell));
      localItemEntry1.layoutId = this.firstLayout;
      localItemEntry2.layoutId = this.secondLayout;
      this.entriesId.add(i * 2, localItemEntry1);
      this.entriesId.add(1 + i * 2, localItemEntry2);
      this.firstLayout.setOnClickListener(this);
      this.secondLayout.setOnClickListener(this);
      if (1 + i * 2 <= -1 + this.resultXml.categoryList.size())
        this.secondLayout.setVisibility(0);
      this.rootLayout.addView(localView1);
    }
    this.handler.sendEmptyMessage(10007);
  }

  private void showCategory(MenuPluginXmlClasses.MenuCategory paramMenuCategory)
  {
    Intent localIntent = new Intent(this, MenuPluginItemsList.class);
    localIntent.putExtra("categoryName", paramMenuCategory.getCategoryName());
    localIntent.putExtra("categoryItems", paramMenuCategory.items);
    localIntent.putExtra("cachePath", this.cachePath);
    localIntent.putExtra("colorskin", this.resultXml.colorSkin);
    localIntent.putExtra("currency", this.resultXml.currency);
    localIntent.putExtra("hasAd", this.hasAd);
    localIntent.putExtra("appname", this.resultXml.app_name);
    localIntent.putExtra("appid", this.resultXml.app_id);
    localIntent.putExtra("currencyposition", this.resultXml.currencyposition);
    startActivity(localIntent);
  }

  public void create()
  {
    requestWindowFeature(1);
    setContentView(R.layout.sergeyb_menuplugin_main);
    getWindow().setFlags(1024, 1024);
    this.assetMgr = getAssets();
    this.rootLayout = ((LinearLayout)findViewById(R.id.sergeyb_menuplugin_main_root_layout));
    this.root = ((LinearLayout)findViewById(R.id.sergeyb_menuplugin_root));
    this.scroll = ((ScrollView)findViewById(R.id.sergeyb_menuplugin_scroll));
    if (!((Boolean)getIntent().getExtras().getSerializable("showSideBar")).booleanValue())
      setTopBarLeftButtonText(getResources().getString(R.string.common_home_upper), true, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          MenuPlugin.this.finish();
        }
      });
    Intent localIntent = getIntent();
    Bundle localBundle = localIntent.getExtras();
    if (localBundle != null)
    {
      this.widget = ((Widget)localBundle.getSerializable("Widget"));
      this.hasAd = this.widget.isHaveAdvertisement();
    }
    if (this.widget == null)
    {
      this.handler.sendEmptyMessageDelayed(10001, 3000L);
      return;
    }
    if ((this.widget.getPluginXmlData().length() == 0) && (localIntent.getStringExtra("WidgetFile").length() == 0))
    {
      this.handler.sendEmptyMessageDelayed(10001, 3000L);
      return;
    }
    if ((this.widget.getTitle() != null) && (this.widget.getTitle().length() != 0))
    {
      setTopBarTitle(this.widget.getTitle());
      if (this.widget.getPluginXmlData().length() <= 0)
        break label345;
    }
    label345: for (this.xmlParser = new MenuPluginXmlParser(this.widget.getPluginXmlData()); ; this.xmlParser = new MenuPluginXmlParser(readXmlFromFile(localIntent.getStringExtra("WidgetFile"))))
    {
      this.xmlParser.parse();
      this.resultXml = this.xmlParser.getResult();
      if (this.resultXml != null)
        break label370;
      this.handler.sendEmptyMessageDelayed(10001, 3000L);
      return;
      setTopBarTitle(getResources().getString(R.string.menuplugin_menu));
      break;
    }
    label370: this.cachePath = (getExternalCacheDir().getAbsolutePath() + "/menuplugin-" + this.resultXml.module_id);
    this.root.setBackgroundColor(this.resultXml.colorSkin.color1);
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < this.resultXml.categoryList.size(); i++)
    {
      MenuPluginXmlClasses.MenuCategory localMenuCategory = (MenuPluginXmlClasses.MenuCategory)this.resultXml.categoryList.get(i);
      if (localMenuCategory.items.size() == 0)
        continue;
      localArrayList.add(localMenuCategory);
    }
    this.resultXml.categoryList = localArrayList;
    if (this.resultXml.categoryList.size() == 1)
    {
      showCategory((MenuPluginXmlClasses.MenuCategory)this.resultXml.categoryList.get(0));
      finish();
      return;
    }
    if (this.resultXml.mainPageStyle.compareToIgnoreCase("grid") == 0)
    {
      this.handler.sendEmptyMessage(10010);
      return;
    }
    this.handler.sendEmptyMessage(10008);
  }

  public void onClick(View paramView)
  {
    paramView.setBackgroundColor(Color.parseColor("#5d5d5d"));
    Message localMessage = this.handler.obtainMessage(10011, Color.parseColor("#ffb676"), -1, paramView);
    this.handler.sendMessageDelayed(localMessage, 300L);
    int i = -1;
    for (int j = 0; ; j++)
    {
      int k = this.entriesId.size();
      String str = null;
      if (j < k)
      {
        if (((MenuPluginStatics.ItemEntry)this.entriesId.get(j)).layoutId != paramView)
          continue;
        str = ((TextView)(TextView)((MenuPluginStatics.ItemEntry)this.entriesId.get(j)).textId).getText().toString();
        i = j;
      }
      MenuPluginXmlClasses.MenuCategory localMenuCategory = (MenuPluginXmlClasses.MenuCategory)this.resultXml.categoryList.get(i);
      Intent localIntent = new Intent(this, MenuPluginItemsList.class);
      localIntent.putExtra("categoryName", str);
      localIntent.putExtra("categoryItems", localMenuCategory.items);
      localIntent.putExtra("cachePath", this.cachePath);
      localIntent.putExtra("colorskin", this.resultXml.colorSkin);
      localIntent.putExtra("currency", this.resultXml.currency);
      localIntent.putExtra("hasAd", this.hasAd);
      localIntent.putExtra("appname", this.resultXml.app_name);
      localIntent.putExtra("appid", this.resultXml.app_id);
      localIntent.putExtra("currencyposition", this.resultXml.currencyposition);
      startActivity(localIntent);
      return;
    }
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    MenuPluginXmlClasses.MenuCategory localMenuCategory = (MenuPluginXmlClasses.MenuCategory)this.resultXml.categoryList.get(paramInt);
    Intent localIntent = new Intent(this, MenuPluginItemsList.class);
    localIntent.putExtra("categoryName", localMenuCategory.getCategoryName());
    localIntent.putExtra("categoryItems", localMenuCategory.items);
    localIntent.putExtra("cachePath", this.cachePath);
    localIntent.putExtra("colorskin", this.resultXml.colorSkin);
    localIntent.putExtra("currency", this.resultXml.currency);
    localIntent.putExtra("hasAd", this.hasAd);
    localIntent.putExtra("appname", this.resultXml.app_name);
    localIntent.putExtra("appid", this.resultXml.app_id);
    localIntent.putExtra("currencyposition", this.resultXml.currencyposition);
    startActivity(localIntent);
  }

  // ERROR //
  protected String readXmlFromFile(String paramString)
  {
    // Byte code:
    //   0: new 536	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 537	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: new 621	java/io/BufferedReader
    //   11: dup
    //   12: new 623	java/io/FileReader
    //   15: dup
    //   16: new 543	java/io/File
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 624	java/io/File:<init>	(Ljava/lang/String;)V
    //   24: invokespecial 627	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   27: invokespecial 630	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   30: astore_3
    //   31: aload_3
    //   32: invokevirtual 633	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   35: astore 6
    //   37: aload 6
    //   39: ifnull +20 -> 59
    //   42: aload_2
    //   43: aload 6
    //   45: invokevirtual 550	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: goto -18 -> 31
    //   52: astore 5
    //   54: aload_2
    //   55: invokevirtual 558	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: areturn
    //   59: goto -5 -> 54
    //   62: astore 9
    //   64: goto -10 -> 54
    //   67: astore 4
    //   69: goto -15 -> 54
    //   72: astore 8
    //   74: goto -20 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   31	37	52	java/io/FileNotFoundException
    //   42	49	52	java/io/FileNotFoundException
    //   8	31	62	java/io/IOException
    //   31	37	67	java/io/IOException
    //   42	49	67	java/io/IOException
    //   8	31	72	java/io/FileNotFoundException
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MenuPlugin.MenuPlugin
 * JD-Core Version:    0.6.0
 */