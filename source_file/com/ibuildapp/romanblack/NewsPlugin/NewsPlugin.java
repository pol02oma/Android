package com.ibuildapp.romanblack.NewsPlugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NewsPlugin extends AppBuilderModuleMain
{
  private final int ADD_NOTIFICATIONS = 10;
  private final int CANT_REFRESH_RSS = 9;
  private final int CLEAR_ITEM_VIEW = 6;
  private final int COLORS_RECIEVED = 11;
  private final int HIDE_PROGRESS_DIALOG = 12;
  private final int INITIALIZATION_FAILED = 3;
  private final int LOADING_ABORTED = 5;
  private final int NEED_INTERNET_CONNECTION = 4;
  private final int REFRESH_RSS = 8;
  private final int RESET_COLOR = 13;
  private final int REVERSE_LIST = 7;
  private final int SHOW_EVENTS = 2;
  private final int SHOW_FEED = 0;
  private final int SHOW_NEWS = 1;
  private BaseAdapter adapter;
  private String cacheMD5 = "";
  private String cachePath = "";
  private ConnectivityManager cm = null;
  private Intent currentIntent;
  private String encoding = "";
  private String feedURL = "";
  private String funcName = "";
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 3:
      case 4:
      case 0:
      case 1:
      case 2:
      case 5:
      case 6:
      case 7:
      case 8:
      case 10:
      case 9:
      case 11:
      case 12:
        do
        {
          return;
          Toast.makeText(NewsPlugin.this, R.string.romanblack_rss_cannot_init, 1).show();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              NewsPlugin.this.finish();
            }
          }
          , 5000L);
          return;
          Toast.makeText(NewsPlugin.this, R.string.romanblack_rss_alert_no_internet, 1).show();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              NewsPlugin.this.finish();
            }
          }
          , 5000L);
          return;
          NewsPlugin.this.showFeed();
          return;
          NewsPlugin.this.showNews();
          return;
          NewsPlugin.this.showEvents();
          return;
          NewsPlugin.this.closeActivity();
          return;
          NewsPlugin.this.clearItemView();
          return;
          NewsPlugin.this.reverseItems();
          return;
          NewsPlugin.this.loadRSS();
          return;
          NewsPlugin.this.addNotification();
          return;
          Toast.makeText(NewsPlugin.this, R.string.romanblack_rss_alert_no_internet, 1).show();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
            }
          }
          , 5000L);
          return;
          NewsPlugin.this.colorsRecieved();
          return;
        }
        while (NewsPlugin.this.progressDialog == null);
        NewsPlugin.this.progressDialog.dismiss();
        return;
      case 13:
      }
      ((View)paramMessage.obj).setBackgroundColor(Statics.color1);
      NewsPlugin.this.listView.invalidate();
    }
  };
  private boolean isOnline = false;
  private boolean isRSS = false;
  private ArrayList<FeedItem> items = new ArrayList();
  private ListView listView = null;
  private View mainlLayout = null;
  private ArrayList<String> notifications;
  private ProgressDialog progressDialog = null;
  private Timer timer = null;
  private String title = "";
  private boolean useCache = false;
  private Widget widget;
  private String widgetMD5 = "";

  private void addNotification()
  {
    int i = 0;
    int j = 0;
    while (true)
      if (j < this.items.size())
      {
        if (((FeedItem)this.items.get(j)).getPubdate().getTime() > System.currentTimeMillis());
        try
        {
          EventsTimerTask localEventsTimerTask = new EventsTimerTask(null);
          localEventsTimerTask.setItem((FeedItem)this.items.get(j), j);
          this.timer.schedule(localEventsTimerTask, new Date(((FeedItem)this.items.get(j)).getPubdate().getTime() - 1800000L));
          i++;
          j++;
        }
        catch (Exception localException)
        {
          while (true)
            Log.w("", "");
        }
      }
    Toast.makeText(this, getString(R.string.romanblack_rss_alert_notifications_added_first_part) + " " + i + "  " + getString(R.string.romanblack_rss_alert_notifications_added_seconds_part), 1).show();
  }

  private void clearItemView()
  {
    int i = Statics.color1;
    int j = 0;
    if (j < this.listView.getChildCount())
    {
      if ((j == 0) && (this.isRSS));
      while (true)
      {
        j++;
        break;
        ((ViewGroup)this.listView.getChildAt(j)).getChildAt(0).setBackgroundColor(i);
      }
    }
  }

  private void closeActivity()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
    finish();
  }

  private void colorsRecieved()
  {
    try
    {
      this.mainlLayout.setBackgroundColor(Statics.color1);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
  }

  private String convertTimeToFormat(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      String str7;
      String str9;
      if (Integer.toString(paramInt1).length() < 2)
      {
        String str10 = Integer.toString(paramInt1);
        str7 = "0" + str10;
        if (Integer.toString(paramInt2).length() >= 2)
          break label120;
        str9 = Integer.toString(paramInt2);
      }
      label120: for (String str8 = "0" + str9; ; str8 = Integer.toString(paramInt2))
      {
        return str7 + ":" + str8;
        str7 = Integer.toString(paramInt1);
        break;
      }
    }
    String str1;
    String str2;
    label186: int i;
    String str3;
    if (paramInt2 + paramInt1 * 100 >= 1200)
    {
      str1 = "PM";
      if (Integer.toString(paramInt2).length() >= 2)
        break label286;
      String str6 = Integer.toString(paramInt2);
      str2 = "0" + str6;
      if (paramInt1 <= 12)
        break label305;
      i = paramInt1 - 12;
      if (Integer.toString(i).length() >= 2)
        break label295;
      String str5 = Integer.toString(i);
      str3 = "0" + str5;
    }
    while (true)
    {
      return str3 + ":" + str2 + " " + str1;
      str1 = "AM";
      break;
      label286: str2 = Integer.toString(paramInt2);
      break label186;
      label295: str3 = Integer.toString(i);
      continue;
      label305: if (Integer.toString(paramInt1).length() < 2)
      {
        String str4 = Integer.toString(paramInt1).toString();
        str3 = "0" + str4;
        continue;
      }
      str3 = Integer.toString(paramInt1).toString();
    }
  }

  private void loadRSS()
  {
    try
    {
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_rss_loading), true);
      new Thread()
      {
        public void run()
        {
          FeedParser localFeedParser = new FeedParser(NewsPlugin.this.feedURL);
          NewsPlugin.access$1802(NewsPlugin.this, localFeedParser.parseFeed());
          if (NewsPlugin.this.items.size() > 0)
          {
            File[] arrayOfFile = new File(NewsPlugin.this.cachePath).listFiles();
            for (int j = 0; j < arrayOfFile.length; j++)
            {
              if (arrayOfFile[j].getName().equals("cache.md5"))
                continue;
              arrayOfFile[j].delete();
            }
          }
          try
          {
            ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(NewsPlugin.this.cachePath + "/cache.data"));
            localObjectOutputStream.writeObject(NewsPlugin.this.items);
            localObjectOutputStream.flush();
            localObjectOutputStream.close();
            label164: for (int i = 0; i < NewsPlugin.this.items.size(); i++)
            {
              ((FeedItem)NewsPlugin.this.items.get(i)).setTextColor(NewsPlugin.this.widget.getTextColor());
              ((FeedItem)NewsPlugin.this.items.get(i)).setDateFormat(NewsPlugin.this.widget.getDateFormat());
            }
            NewsPlugin.this.selectShowType();
            return;
          }
          catch (Exception localException)
          {
            break label164;
          }
        }
      }
      .start();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void loadRSSOnScroll()
  {
    if ((this.isRSS) && (this.cm != null))
    {
      NetworkInfo localNetworkInfo = this.cm.getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
      {
        this.isOnline = true;
        loadRSS();
      }
    }
    else
    {
      return;
    }
    this.isOnline = false;
  }

  private String readFileToString(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    BufferedReader localBufferedReader;
    try
    {
      localBufferedReader = new BufferedReader(new FileReader(new File(paramString)));
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        localStringBuilder.append(str);
        localStringBuilder.append("\n");
      }
    }
    catch (Exception localException)
    {
    }
    while (true)
    {
      return localStringBuilder.toString();
      localBufferedReader.close();
    }
  }

  private void reverseItems()
  {
    Collections.reverse(this.items);
    this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_rss_loading), true);
    selectShowType();
  }

  private void selectShowType()
  {
    if ((this.items == null) || (this.items.size() <= 0))
    {
      this.handler.sendEmptyMessage(3);
      finish();
    }
    do
    {
      return;
      if (this.funcName.equalsIgnoreCase("EVENTS"))
      {
        this.handler.sendEmptyMessage(2);
        return;
      }
      if (this.funcName.equalsIgnoreCase("NEWS"))
      {
        this.handler.sendEmptyMessage(1);
        return;
      }
      if (!this.funcName.equalsIgnoreCase("RSS"))
        continue;
      this.handler.sendEmptyMessage(0);
      return;
    }
    while (this.progressDialog == null);
    this.progressDialog.dismiss();
  }

  private void showDetails(int paramInt, View paramView)
  {
    try
    {
      if ((!((FeedItem)this.items.get(paramInt)).hasMedia()) || (((FeedItem)this.items.get(paramInt)).getDescription().length() > 70))
      {
        Intent localIntent1 = new Intent(this, FeedDetails.class);
        Bundle localBundle1 = new Bundle();
        localBundle1.putString("func", this.funcName);
        localBundle1.putSerializable("Widget", this.widget);
        localBundle1.putSerializable("item", (Serializable)this.items.get(paramInt));
        if (this.encoding.equals(""))
          localBundle1.putString("enc", "UTF-8");
        while (true)
        {
          localIntent1.putExtras(localBundle1);
          startActivity(localIntent1);
          return;
          localBundle1.putString("enc", this.encoding);
        }
      }
      if (((FeedItem)this.items.get(paramInt)).getMediaType().contains("video"))
      {
        Intent localIntent2 = new Intent(this, VideoPlayer.class);
        Bundle localBundle2 = new Bundle();
        localBundle2.putString("link", ((FeedItem)this.items.get(paramInt)).getMediaUrl());
        localBundle2.putString("cache", this.cachePath);
        localBundle2.putSerializable("Widget", this.widget);
        localBundle2.putSerializable("item", (Serializable)this.items.get(paramInt));
        localIntent2.putExtras(localBundle2);
        startActivity(localIntent2);
        return;
      }
      if (((FeedItem)this.items.get(paramInt)).getMediaType().contains("audio"))
      {
        Intent localIntent3 = new Intent("android.intent.action.VIEW");
        localIntent3.setDataAndType(Uri.parse(((FeedItem)this.items.get(paramInt)).getMediaUrl()), "audio/*");
        startActivity(localIntent3);
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void showEvents()
  {
    try
    {
      Log.e("ibuildapp time", "Show events start " + new Date(System.currentTimeMillis()));
      setTitle(this.title);
      if (this.items.isEmpty())
        return;
      this.listView.setCacheColorHint(0);
      this.listView.setBackgroundColor(-1);
      try
      {
        this.listView.setBackgroundColor(Statics.color1);
        label81: EventsAdapter localEventsAdapter = new EventsAdapter(this, R.layout.romanblack_events_item, this.items, this.widget.getBackgroundColor());
        this.adapter = localEventsAdapter;
        this.listView.setAdapter(localEventsAdapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
          {
            float[] arrayOfFloat = new float[3];
            Color.colorToHSV(-7829368, arrayOfFloat);
            paramView.setBackgroundColor(Color.HSVToColor(127, arrayOfFloat));
            if (NewsPlugin.this.isRSS)
            {
              NewsPlugin.this.showDetails(paramInt - 1, paramView);
              return;
            }
            NewsPlugin.this.showDetails(paramInt, paramView);
          }
        });
        if (this.isRSS)
          ((PullToRefreshListView)this.listView).setOnRefreshListener(new PullToRefreshListView.OnRefreshListener()
          {
            public void onRefresh()
            {
              NewsPlugin.this.loadRSSOnScroll();
              ((PullToRefreshListView)NewsPlugin.this.listView).onRefreshComplete();
            }
          });
        while (true)
        {
          if (this.widget.hasParameter("add_local_notific"))
          {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
            localBuilder.setMessage(getString(R.string.romanblack_rss_dialog_add_notofications));
            localBuilder.setPositiveButton(getString(R.string.romanblack_rss_yes), new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramDialogInterface, int paramInt)
              {
                NewsPlugin.this.handler.sendEmptyMessage(10);
              }
            });
            localBuilder.setNegativeButton(getString(R.string.romanblack_rss_no), new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramDialogInterface, int paramInt)
              {
              }
            });
            localBuilder.create().show();
          }
          this.handler.sendEmptyMessage(12);
          return;
          ((PullToRefreshListView)this.listView).refreshOff();
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        break label81;
      }
    }
    catch (Exception localException)
    {
    }
  }

  private void showFeed()
  {
    try
    {
      setTitle(this.title);
      if (this.items.isEmpty())
        return;
      this.listView.setCacheColorHint(0);
      FeedAdapter localFeedAdapter = new FeedAdapter(this, this.items, this.widget.getBackgroundColor());
      this.adapter = localFeedAdapter;
      localFeedAdapter.setCachePath(this.cachePath);
      this.listView.setAdapter(localFeedAdapter);
      this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          float[] arrayOfFloat = new float[3];
          Color.colorToHSV(-7829368, arrayOfFloat);
          int i = Color.HSVToColor(127, arrayOfFloat);
          ((ViewGroup)paramView).getChildAt(0).setBackgroundColor(i);
          Message localMessage = NewsPlugin.this.handler.obtainMessage(13, paramView);
          NewsPlugin.this.handler.sendMessageDelayed(localMessage, 300L);
          if (NewsPlugin.this.isRSS)
          {
            NewsPlugin.this.showDetails(paramInt - 1, paramView);
            return;
          }
          NewsPlugin.this.showDetails(paramInt, paramView);
        }
      });
      if (this.isRSS)
        ((PullToRefreshListView)this.listView).setOnRefreshListener(new PullToRefreshListView.OnRefreshListener()
        {
          public void onRefresh()
          {
            NewsPlugin.this.loadRSSOnScroll();
            ((PullToRefreshListView)NewsPlugin.this.listView).onRefreshComplete();
          }
        });
      while (true)
      {
        this.handler.sendEmptyMessage(12);
        return;
        ((PullToRefreshListView)this.listView).refreshOff();
      }
    }
    catch (Exception localException)
    {
    }
  }

  private void showNews()
  {
    try
    {
      setTitle(this.title);
      if (this.items.isEmpty())
        return;
      this.listView.setBackgroundColor(-1);
      try
      {
        this.listView.setBackgroundColor(Statics.color1);
        label37: FeedAdapter localFeedAdapter = new FeedAdapter(this, this.items, this.widget.getBackgroundColor());
        localFeedAdapter.setCachePath(this.cachePath);
        this.listView.setAdapter(localFeedAdapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
          {
            float[] arrayOfFloat = new float[3];
            Color.colorToHSV(-7829368, arrayOfFloat);
            int i = Color.HSVToColor(127, arrayOfFloat);
            ((ViewGroup)paramView).getChildAt(0).setBackgroundColor(i);
            if (NewsPlugin.this.isRSS)
            {
              NewsPlugin.this.showDetails(paramInt - 1, paramView);
              return;
            }
            NewsPlugin.this.showDetails(paramInt, paramView);
          }
        });
        if (this.isRSS)
          ((PullToRefreshListView)this.listView).setOnRefreshListener(new PullToRefreshListView.OnRefreshListener()
          {
            public void onRefresh()
            {
              NewsPlugin.this.loadRSSOnScroll();
              ((PullToRefreshListView)NewsPlugin.this.listView).onRefreshComplete();
            }
          });
        while (true)
        {
          this.handler.sendEmptyMessage(12);
          return;
          ((PullToRefreshListView)this.listView).refreshOff();
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        break label37;
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void create()
  {
    try
    {
      requestWindowFeature(1);
      setContentView(R.layout.romanblack_feed_main);
      setTitle(R.string.romanblack_rss_feed);
      this.mainlLayout = findViewById(R.id.romanblack_feed_main);
      this.listView = ((ListView)findViewById(R.id.romanblack_feedList));
      this.currentIntent = getIntent();
      this.widget = ((Widget)this.currentIntent.getExtras().getSerializable("Widget"));
      if (this.widget == null)
      {
        this.handler.sendEmptyMessageDelayed(3, 100L);
        return;
      }
      this.widgetMD5 = Utils.md5(this.widget.getPluginXmlData());
      if ((this.widget.getTitle() != null) && (this.widget.getTitle().length() != 0))
        setTopBarTitle(this.widget.getTitle());
      if (!((Boolean)getIntent().getExtras().getSerializable("showSideBar")).booleanValue())
        setTopBarLeftButtonText(getString(R.string.rss_home_button), true, new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            NewsPlugin.this.finish();
          }
        });
      try
      {
        if ((this.widget.getPluginXmlData().length() == 0) && (this.currentIntent.getStringExtra("WidgetFile").length() == 0))
        {
          this.handler.sendEmptyMessageDelayed(3, 3000L);
          return;
        }
      }
      catch (Exception localException2)
      {
        this.handler.sendEmptyMessageDelayed(3, 3000L);
        return;
      }
      this.cachePath = (this.widget.getCachePath() + "/feed-" + this.widget.getOrder());
      File localFile1 = new File(this.cachePath);
      if (!localFile1.exists())
        localFile1.mkdirs();
      File localFile2 = new File(this.cachePath + "/cache.data");
      if ((localFile2.exists()) && (localFile2.length() > 0L))
      {
        this.cacheMD5 = readFileToString(this.cachePath + "/cache.md5").replace("\n", "");
        if (!this.cacheMD5.equals(this.widgetMD5))
          break label479;
        this.useCache = true;
      }
      while (true)
      {
        this.cm = ((ConnectivityManager)getSystemService("connectivity"));
        NetworkInfo localNetworkInfo = this.cm.getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
          this.isOnline = true;
        if ((this.isOnline) || (this.useCache))
          break;
        this.handler.sendEmptyMessage(4);
        return;
        label479: File[] arrayOfFile = localFile1.listFiles();
        for (int i = 0; i < arrayOfFile.length; i++)
          arrayOfFile[i].delete();
        try
        {
          BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(new File(this.cachePath + "/cache.md5")));
          localBufferedWriter.write(this.widgetMD5);
          localBufferedWriter.close();
          Log.d("IMAGES PLUGIN CACHE MD5", "SUCCESS");
        }
        catch (Exception localException3)
        {
          Log.w("IMAGES PLUGIN CACHE MD5", localException3);
        }
      }
      this.progressDialog = ProgressDialog.show(this, null, getString(R.string.romanblack_rss_loading), true);
      this.progressDialog.setCancelable(true);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          NewsPlugin.this.handler.sendEmptyMessage(5);
        }
      });
      new Thread()
      {
        public void run()
        {
          NewsPlugin.access$1502(NewsPlugin.this, new Timer("EventsTimer", true));
          EntityParser localEntityParser;
          if (NewsPlugin.this.widget.getPluginXmlData().length() > 0)
            localEntityParser = new EntityParser(NewsPlugin.this.widget.getPluginXmlData());
          while (true)
          {
            localEntityParser.parse();
            Statics.color1 = localEntityParser.getColor1();
            Statics.color2 = localEntityParser.getColor2();
            Statics.color3 = localEntityParser.getColor3();
            Statics.color4 = localEntityParser.getColor4();
            Statics.color5 = localEntityParser.getColor5();
            NewsPlugin.this.handler.sendEmptyMessage(11);
            NewsPlugin localNewsPlugin = NewsPlugin.this;
            String str;
            if (NewsPlugin.this.widget.getTitle().length() > 0)
            {
              str = NewsPlugin.this.widget.getTitle();
              label138: NewsPlugin.access$1702(localNewsPlugin, str);
              NewsPlugin.access$1802(NewsPlugin.this, localEntityParser.getItems());
              if ("rss".equals(localEntityParser.getFeedType()))
              {
                NewsPlugin.access$1902(NewsPlugin.this, true);
                NewsPlugin.access$2002(NewsPlugin.this, localEntityParser.getFeedUrl());
                if (!NewsPlugin.this.isOnline)
                  break label435;
                FeedParser localFeedParser = new FeedParser(localEntityParser.getFeedUrl());
                NewsPlugin.access$1802(NewsPlugin.this, localFeedParser.parseFeed());
                NewsPlugin.access$2202(NewsPlugin.this, localFeedParser.getEncoding());
                if (NewsPlugin.this.items.size() <= 0);
              }
            }
            try
            {
              ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(NewsPlugin.this.cachePath + "/cache.data"));
              localObjectOutputStream.writeObject(NewsPlugin.this.items);
              localObjectOutputStream.flush();
              localObjectOutputStream.close();
              while (true)
              {
                label316: for (int i = 0; i < NewsPlugin.this.items.size(); i++)
                {
                  ((FeedItem)NewsPlugin.this.items.get(i)).setTextColor(NewsPlugin.this.widget.getTextColor());
                  ((FeedItem)NewsPlugin.this.items.get(i)).setDateFormat(NewsPlugin.this.widget.getDateFormat());
                }
                localEntityParser = new EntityParser(NewsPlugin.this.readXmlFromFile(NewsPlugin.this.currentIntent.getStringExtra("WidgetFile")));
                break;
                str = localEntityParser.getFuncName();
                break label138;
                try
                {
                  label435: ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream(NewsPlugin.this.cachePath + "/cache.data"));
                  NewsPlugin.access$1802(NewsPlugin.this, (ArrayList)localObjectInputStream.readObject());
                  localObjectInputStream.close();
                }
                catch (Exception localException2)
                {
                }
              }
              NewsPlugin.access$2402(NewsPlugin.this, localEntityParser.getFuncName());
              NewsPlugin.this.selectShowType();
              return;
            }
            catch (Exception localException1)
            {
              break label316;
            }
          }
        }
      }
      .start();
      return;
    }
    catch (Exception localException1)
    {
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(R.menu.romanblack_rss_menu_main, paramMenu);
    paramMenu.clear();
    MenuItem localMenuItem1 = paramMenu.add("");
    localMenuItem1.setTitle(getString(R.string.romanblack_rss_reverse));
    localMenuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramMenuItem)
      {
        NewsPlugin.this.handler.sendEmptyMessage(7);
        return true;
      }
    });
    if (this.isRSS)
    {
      MenuItem localMenuItem2 = paramMenu.add("");
      localMenuItem2.setTitle(R.string.romanblack_rss_refresh);
      localMenuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
      {
        public boolean onMenuItemClick(MenuItem paramMenuItem)
        {
          if (NewsPlugin.this.isOnline)
            NewsPlugin.this.handler.sendEmptyMessage(8);
          while (true)
          {
            return true;
            NewsPlugin.this.handler.sendEmptyMessage(9);
          }
        }
      });
    }
    return true;
  }

  // ERROR //
  protected String readXmlFromFile(String paramString)
  {
    // Byte code:
    //   0: new 278	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 279	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: new 394	java/io/BufferedReader
    //   11: dup
    //   12: new 396	java/io/FileReader
    //   15: dup
    //   16: new 398	java/io/File
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 401	java/io/File:<init>	(Ljava/lang/String;)V
    //   24: invokespecial 404	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   27: invokespecial 407	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   30: astore_3
    //   31: aload_3
    //   32: invokevirtual 410	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   35: astore 6
    //   37: aload 6
    //   39: ifnull +20 -> 59
    //   42: aload_2
    //   43: aload 6
    //   45: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: goto -18 -> 31
    //   52: astore 5
    //   54: aload_2
    //   55: invokevirtual 306	java/lang/StringBuilder:toString	()Ljava/lang/String;
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

  public void restart()
  {
    super.restart();
    if (this.adapter != null)
      this.adapter.notifyDataSetChanged();
    this.listView.setCacheColorHint(0);
    this.listView.invalidate();
  }

  public void resume()
  {
    super.resume();
    clearItemView();
  }

  private class EventsTimerTask extends TimerTask
  {
    private FeedItem item = null;
    private int order = -1;

    private EventsTimerTask()
    {
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   4: ifnull +687 -> 691
      //   7: new 29	java/lang/StringBuilder
      //   10: dup
      //   11: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   14: aload_0
      //   15: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   18: sipush 250
      //   21: invokevirtual 36	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getAnounce	(I)Ljava/lang/String;
      //   24: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   27: ldc 42
      //   29: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   32: aload_0
      //   33: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   36: getstatic 47	com/ibuildapp/romanblack/NewsPlugin/R$string:romanblack_rss_at	I
      //   39: invokevirtual 52	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:getString	(I)Ljava/lang/String;
      //   42: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   45: ldc 42
      //   47: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   50: aload_0
      //   51: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   54: ldc 54
      //   56: invokevirtual 58	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getPubdate	(Ljava/lang/String;)Ljava/lang/String;
      //   59: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   62: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   65: astore_2
      //   66: new 29	java/lang/StringBuilder
      //   69: dup
      //   70: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   73: invokestatic 68	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
      //   76: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   79: ldc 73
      //   81: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   84: aload_0
      //   85: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   88: invokevirtual 76	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:getPackageName	()Ljava/lang/String;
      //   91: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   94: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   97: astore_3
      //   98: new 78	java/io/File
      //   101: dup
      //   102: aload_3
      //   103: invokespecial 81	java/io/File:<init>	(Ljava/lang/String;)V
      //   106: astore 4
      //   108: aload 4
      //   110: invokevirtual 85	java/io/File:exists	()Z
      //   113: ifne +9 -> 122
      //   116: aload 4
      //   118: invokevirtual 88	java/io/File:mkdirs	()Z
      //   121: pop
      //   122: new 78	java/io/File
      //   125: dup
      //   126: new 29	java/lang/StringBuilder
      //   129: dup
      //   130: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   133: aload_3
      //   134: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   137: ldc 90
      //   139: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   142: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   145: invokespecial 81	java/io/File:<init>	(Ljava/lang/String;)V
      //   148: astore 5
      //   150: aload 5
      //   152: invokevirtual 85	java/io/File:exists	()Z
      //   155: istore 6
      //   157: iload 6
      //   159: ifne +460 -> 619
      //   162: aload 5
      //   164: invokevirtual 93	java/io/File:createNewFile	()Z
      //   167: pop
      //   168: aload_0
      //   169: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   172: invokestatic 97	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:access$000	(Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;)Ljava/util/ArrayList;
      //   175: ifnonnull +18 -> 193
      //   178: aload_0
      //   179: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   182: new 99	java/util/ArrayList
      //   185: dup
      //   186: invokespecial 100	java/util/ArrayList:<init>	()V
      //   189: invokestatic 104	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:access$002	(Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;Ljava/util/ArrayList;)Ljava/util/ArrayList;
      //   192: pop
      //   193: aload_0
      //   194: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   197: invokestatic 97	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:access$000	(Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;)Ljava/util/ArrayList;
      //   200: aload_2
      //   201: invokevirtual 108	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   204: pop
      //   205: new 110	java/io/FileOutputStream
      //   208: dup
      //   209: aload 5
      //   211: invokespecial 113	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   214: astore 12
      //   216: new 115	java/io/ObjectOutputStream
      //   219: dup
      //   220: aload 12
      //   222: invokespecial 118	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   225: astore 13
      //   227: aload 13
      //   229: aload_0
      //   230: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   233: invokestatic 97	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:access$000	(Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;)Ljava/util/ArrayList;
      //   236: invokevirtual 122	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
      //   239: aload 13
      //   241: invokevirtual 125	java/io/ObjectOutputStream:close	()V
      //   244: aload_0
      //   245: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   248: invokevirtual 128	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getTitle	()Ljava/lang/String;
      //   251: astore 16
      //   253: aload_0
      //   254: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   257: invokevirtual 128	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getTitle	()Ljava/lang/String;
      //   260: astore 17
      //   262: aload_0
      //   263: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   266: bipush 70
      //   268: invokevirtual 36	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getAnounce	(I)Ljava/lang/String;
      //   271: astore 18
      //   273: new 130	android/content/Intent
      //   276: dup
      //   277: aload_0
      //   278: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   281: ldc 132
      //   283: invokespecial 135	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
      //   286: astore 19
      //   288: aload_0
      //   289: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   292: invokevirtual 138	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getPubdate	()Ljava/util/Date;
      //   295: astore 20
      //   297: new 29	java/lang/StringBuilder
      //   300: dup
      //   301: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   304: new 140	java/text/SimpleDateFormat
      //   307: dup
      //   308: ldc 142
      //   310: invokespecial 143	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
      //   313: aload 20
      //   315: invokevirtual 147	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   318: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   321: ldc 42
      //   323: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   326: new 140	java/text/SimpleDateFormat
      //   329: dup
      //   330: ldc 149
      //   332: invokespecial 143	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
      //   335: aload 20
      //   337: invokevirtual 147	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   340: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   343: ldc 42
      //   345: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   348: new 140	java/text/SimpleDateFormat
      //   351: dup
      //   352: ldc 151
      //   354: invokespecial 143	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
      //   357: aload 20
      //   359: invokevirtual 147	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   362: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   365: ldc 42
      //   367: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   370: aload_0
      //   371: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   374: aload 20
      //   376: invokevirtual 157	java/util/Date:getHours	()I
      //   379: aload 20
      //   381: invokevirtual 160	java/util/Date:getMinutes	()I
      //   384: iconst_0
      //   385: invokestatic 164	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:access$100	(Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;IIZ)Ljava/lang/String;
      //   388: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   391: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   394: astore 21
      //   396: aload 19
      //   398: ldc 166
      //   400: aload_0
      //   401: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   404: invokevirtual 128	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getTitle	()Ljava/lang/String;
      //   407: invokevirtual 170	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
      //   410: pop
      //   411: aload 19
      //   413: ldc 172
      //   415: aload 21
      //   417: invokevirtual 170	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
      //   420: pop
      //   421: aload 19
      //   423: ldc 174
      //   425: aload_0
      //   426: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   429: invokevirtual 177	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getDescription	()Ljava/lang/String;
      //   432: invokevirtual 170	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
      //   435: pop
      //   436: aload_0
      //   437: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   440: ldc 179
      //   442: invokevirtual 183	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   445: checkcast 185	android/app/NotificationManager
      //   448: astore 25
      //   450: new 187	android/app/Notification
      //   453: dup
      //   454: getstatic 192	com/ibuildapp/romanblack/NewsPlugin/R$drawable:icon_notification	I
      //   457: aload 16
      //   459: invokestatic 198	java/lang/System:currentTimeMillis	()J
      //   462: invokespecial 201	android/app/Notification:<init>	(ILjava/lang/CharSequence;J)V
      //   465: astore 26
      //   467: aload 26
      //   469: bipush 16
      //   471: aload 26
      //   473: getfield 204	android/app/Notification:flags	I
      //   476: ior
      //   477: putfield 204	android/app/Notification:flags	I
      //   480: aload_0
      //   481: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   484: sipush 1000
      //   487: aload_0
      //   488: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   491: invokestatic 208	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:access$200	(Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;)Lcom/appbuilder/sdk/android/Widget;
      //   494: invokevirtual 213	com/appbuilder/sdk/android/Widget:getOrder	()I
      //   497: imul
      //   498: aload_0
      //   499: getfield 21	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:order	I
      //   502: iadd
      //   503: aload 19
      //   505: ldc 214
      //   507: invokestatic 220	android/app/PendingIntent:getActivity	(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
      //   510: astore 27
      //   512: aload 26
      //   514: aload_0
      //   515: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   518: aload 17
      //   520: aload 18
      //   522: aload 27
      //   524: invokevirtual 224	android/app/Notification:setLatestEventInfo	(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V
      //   527: aload 25
      //   529: new 29	java/lang/StringBuilder
      //   532: dup
      //   533: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   536: aload_0
      //   537: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   540: invokevirtual 228	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:getResources	()Landroid/content/res/Resources;
      //   543: getstatic 231	com/ibuildapp/romanblack/NewsPlugin/R$string:app_name	I
      //   546: invokevirtual 234	android/content/res/Resources:getString	(I)Ljava/lang/String;
      //   549: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   552: ldc 236
      //   554: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   557: aload_0
      //   558: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   561: invokestatic 208	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:access$200	(Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;)Lcom/appbuilder/sdk/android/Widget;
      //   564: invokevirtual 213	com/appbuilder/sdk/android/Widget:getOrder	()I
      //   567: invokevirtual 239	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   570: ldc 42
      //   572: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   575: aload_0
      //   576: getfield 19	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:item	Lcom/ibuildapp/romanblack/NewsPlugin/FeedItem;
      //   579: invokevirtual 128	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getTitle	()Ljava/lang/String;
      //   582: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   585: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   588: aload_0
      //   589: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   592: invokestatic 208	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:access$200	(Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;)Lcom/appbuilder/sdk/android/Widget;
      //   595: invokevirtual 213	com/appbuilder/sdk/android/Widget:getOrder	()I
      //   598: aload 26
      //   600: invokevirtual 243	android/app/NotificationManager:notify	(Ljava/lang/String;ILandroid/app/Notification;)V
      //   603: return
      //   604: astore 30
      //   606: ldc 54
      //   608: ldc 54
      //   610: invokestatic 249	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   613: pop
      //   614: goto -446 -> 168
      //   617: astore_1
      //   618: return
      //   619: new 251	java/io/FileInputStream
      //   622: dup
      //   623: aload 5
      //   625: invokespecial 252	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   628: astore 7
      //   630: new 254	java/io/ObjectInputStream
      //   633: dup
      //   634: aload 7
      //   636: invokespecial 257	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
      //   639: astore 8
      //   641: aload_0
      //   642: getfield 14	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin$EventsTimerTask:this$0	Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;
      //   645: aload 8
      //   647: invokevirtual 261	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
      //   650: checkcast 99	java/util/ArrayList
      //   653: invokestatic 104	com/ibuildapp/romanblack/NewsPlugin/NewsPlugin:access$002	(Lcom/ibuildapp/romanblack/NewsPlugin/NewsPlugin;Ljava/util/ArrayList;)Ljava/util/ArrayList;
      //   656: pop
      //   657: aload 8
      //   659: invokevirtual 262	java/io/ObjectInputStream:close	()V
      //   662: goto -494 -> 168
      //   665: astore 9
      //   667: ldc 54
      //   669: ldc 54
      //   671: invokestatic 249	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   674: pop
      //   675: goto -507 -> 168
      //   678: astore 14
      //   680: ldc 54
      //   682: ldc 54
      //   684: invokestatic 249	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   687: pop
      //   688: goto -444 -> 244
      //   691: return
      //
      // Exception table:
      //   from	to	target	type
      //   162	168	604	java/lang/Exception
      //   0	122	617	java/lang/Exception
      //   122	157	617	java/lang/Exception
      //   168	193	617	java/lang/Exception
      //   193	205	617	java/lang/Exception
      //   244	603	617	java/lang/Exception
      //   606	614	617	java/lang/Exception
      //   667	675	617	java/lang/Exception
      //   680	688	617	java/lang/Exception
      //   619	662	665	java/lang/Exception
      //   205	244	678	java/lang/Exception
    }

    public void setItem(FeedItem paramFeedItem, int paramInt)
    {
      this.item = paramFeedItem;
      this.order = paramInt;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.NewsPlugin
 * JD-Core Version:    0.6.0
 */