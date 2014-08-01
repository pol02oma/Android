package com.ibuildapp.romanblack.MapPlugin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.statistics.StatisticsCollector;
import com.google.android.maps.GeoPoint;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class MapPlugin extends AppBuilderModuleMain
  implements LocationListener
{
  private final int CHOSE_ROUTE_FINAL = 8;
  private final int CLOSE_ACTIVITY = 4;
  private final int DRAW_ROUTE = 7;
  private final int GO_TO_URL = 10;
  private final int HIDE_PROGRESS_DIALOG = 3;
  private final int INITIALIZATION_FAILED = 0;
  private final int NEED_INTERNET_CONNECTION = 1;
  private final int NO_GPS_SERVICE = 5;
  private final int SEARCH_LOCATION = 6;
  private final int SHOW_MAP = 2;
  private final int SHOW_PROGRESS_DIALOG = 9;
  private Runnable Timer_Tick = new Runnable()
  {
    public void run()
    {
      if (!MapPlugin.this.locationManager.isProviderEnabled("gps"));
      while (true)
      {
        return;
        if (MapPlugin.this.gpsLocation != null)
          break;
        if (MapPlugin.this.tempLocation == null)
          continue;
        MapPlugin.access$1202(MapPlugin.this, MapPlugin.this.tempLocation);
      }
      while (true)
      {
        MapPlugin.access$1302(MapPlugin.this, MapPlugin.this.userLocation.getLatitude());
        MapPlugin.access$1402(MapPlugin.this, MapPlugin.this.userLocation.getLongitude());
        MapPlugin.access$1302(MapPlugin.this, MapPlugin.this.srcLatitude / 1000000.0F);
        MapPlugin.access$1402(MapPlugin.this, MapPlugin.this.srcLongitude / 1000000.0F);
        BigDecimal localBigDecimal1 = new BigDecimal(MapPlugin.this.srcLatitude).setScale(6, 4);
        BigDecimal localBigDecimal2 = new BigDecimal(MapPlugin.this.srcLongitude).setScale(6, 4);
        MapPlugin.this.mapView.loadUrl("javascript:moveUserMarker(" + localBigDecimal1.toString() + "," + localBigDecimal2.toString() + ")");
        return;
        MapPlugin.access$1102(MapPlugin.this, MapPlugin.this.gpsLocation);
        MapPlugin.access$1202(MapPlugin.this, MapPlugin.this.tempLocation);
      }
    }
  };
  private Button btnDirection = null;
  private Button btnMyLocation = null;
  private GeoPoint dstGeoPoint = null;
  private float dstLatitude = 0.0F;
  private float dstLongitude = 0.0F;
  private MapLocation gpsLocation = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        Toast.makeText(MapPlugin.this, R.string.alert_cannot_init, 1).show();
        MapPlugin.this.closeActivity();
        return;
      case 1:
        Toast.makeText(MapPlugin.this, R.string.alert_no_internet, 1).show();
        MapPlugin.this.closeActivity();
        return;
      case 2:
        MapPlugin.this.showMap();
        return;
      case 3:
        MapPlugin.this.hideProgressDialog();
        return;
      case 4:
        MapPlugin.this.closeActivity();
        return;
      case 5:
        Toast.makeText(MapPlugin.this, R.string.romanblack_map_alert_gps_not_available, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
          }
        }
        , 5000L);
        return;
      case 6:
        Toast.makeText(MapPlugin.this, R.string.romanblack_map_alert_wait_for_gps, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
          }
        }
        , 7000L);
        return;
      case 7:
        MapPlugin.this.startRoute();
        return;
      case 8:
        MapPlugin.this.choseRouteFinal();
        return;
      case 9:
        MapPlugin.this.showProgressDialog();
        return;
      case 10:
      }
      MapPlugin.this.goToUrl(MapPlugin.this.urlToGo, "");
    }
  };
  private String htmlSource = "";
  private boolean isGPS = false;
  private boolean isOnline = false;
  private ArrayList<MapItem> items = null;
  private LocationManager locationManager = null;
  private Spinner locationSpinner = null;
  private ArrayList<MapLocation> locations = new ArrayList();
  private TimerTask mTask;
  private Timer mTimer;
  private MapBottomPanel mapBottomPanel = null;
  private WebView mapView = null;
  private EntityParser parser;
  private ProgressDialog progressDialog = null;
  private GeoPoint srcGeoPoint = null;
  private float srcLatitude = 0.0F;
  private float srcLongitude = 0.0F;
  private MapLocation tempLocation = null;
  private String title = "";
  private String urlToGo = "";
  private MapLocation userLocation = null;
  private Widget widget = null;

  private void TimerMethod()
  {
    runOnUiThread(this.Timer_Tick);
  }

  private void choseRouteFinal()
  {
    try
    {
      if (this.locationSpinner == null)
      {
        this.locationSpinner = new Spinner(this);
        this.locationSpinner.setVisibility(4);
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(getString(R.string.common_cancel_upper));
        for (int i = 0; i < this.locations.size(); i++)
          localArrayList.add(((MapLocation)this.locations.get(i)).getTitle());
        ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048, localArrayList);
        this.locationSpinner.setAdapter(localArrayAdapter);
        this.locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
          {
            if (paramInt != 0);
            try
            {
              MapPlugin.access$1702(MapPlugin.this, ((MapLocation)MapPlugin.this.locations.get(paramInt - 1)).getLatitude());
              MapPlugin.access$1802(MapPlugin.this, ((MapLocation)MapPlugin.this.locations.get(paramInt - 1)).getLongitude());
              MapPlugin.this.handler.sendEmptyMessage(7);
              return;
            }
            catch (NullPointerException localNullPointerException)
            {
            }
          }

          public void onNothingSelected(AdapterView<?> paramAdapterView)
          {
            Log.e("", "");
          }
        });
        this.mapView.addView(this.locationSpinner);
      }
      this.locationSpinner.performClick();
      return;
    }
    catch (Exception localException)
    {
      logError("MapPlugin.chooseRouteFinal()", localException);
    }
  }

  private void closeActivity()
  {
    hideProgressDialog();
    finish();
  }

  private void getCurrentCoordinates()
  {
    if (!this.locationManager.isProviderEnabled("gps"));
    do
    {
      return;
      if (this.gpsLocation != null)
        break;
    }
    while (this.tempLocation == null);
    for (this.userLocation = this.tempLocation; ; this.userLocation = this.tempLocation)
    {
      this.srcLatitude = this.userLocation.getLatitude();
      this.srcLongitude = this.userLocation.getLongitude();
      this.srcLatitude /= 1000000.0F;
      this.srcLongitude /= 1000000.0F;
      new BigDecimal(this.srcLatitude).setScale(6, 4);
      new BigDecimal(this.srcLongitude).setScale(6, 4);
      return;
      this.tempLocation = this.gpsLocation;
    }
  }

  private void goToUrl(String paramString1, String paramString2)
  {
    this.mapView.loadUrl(paramString1);
    this.mapBottomPanel.setVisibility(8);
    Log.d("", "");
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void logError(String paramString, Exception paramException)
  {
    StatisticsCollector.newError(paramException, paramString);
  }

  private void showMap()
  {
    try
    {
      if ((this.title != null) && (this.title.length() > 0))
        setTitle(this.title);
      this.mapView.loadDataWithBaseURL("", this.htmlSource, "text/html", "utf-8", "");
      return;
    }
    catch (Exception localException)
    {
      logError("MapWebPlugin.showMap()", localException);
    }
  }

  private void showProgressDialog()
  {
    if ((this.progressDialog != null) && (!this.progressDialog.isShowing()))
      this.progressDialog = ProgressDialog.show(this, "", getString(R.string.common_loading_upper));
  }

  private void startRoute()
  {
    while (true)
    {
      int j;
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://maps.google.com/maps?saddr=");
        localStringBuilder.append(this.srcLatitude / 1000000.0D);
        localStringBuilder.append(",");
        localStringBuilder.append(this.srcLongitude / 1000000.0D);
        localStringBuilder.append("&daddr=");
        localStringBuilder.append(this.dstLatitude / 1000000.0D);
        localStringBuilder.append(",");
        localStringBuilder.append(this.dstLongitude / 1000000.0D);
        localStringBuilder.append("&ll=");
        localStringBuilder.append((this.srcLatitude / 1000000.0D + this.dstLatitude / 1000000.0D) / 2.0D);
        localStringBuilder.append(",");
        localStringBuilder.append((this.srcLongitude / 1000000.0D + this.dstLongitude / 1000000.0D) / 2.0D);
        localStringBuilder.append("&z=");
        if (Math.abs(this.srcLatitude - this.dstLatitude) <= Math.abs(this.srcLongitude - this.dstLongitude))
          continue;
        j = Math.abs((int)(this.srcLatitude - this.dstLatitude));
        break label437;
        localStringBuilder.append(k);
        String str = localStringBuilder.toString();
        Intent localIntent = new Intent(this, MapRoute.class);
        localIntent.putExtra("url", str);
        startActivity(localIntent);
        return;
        int i = Math.abs((int)(this.srcLongitude - this.dstLongitude));
        j = i;
        break label437;
        if (j <= 60000000.0D)
          continue;
        k = 2;
        continue;
        if (j <= 30000000.0D)
          continue;
        k = 3;
        continue;
        if (j <= 15000000.0D)
          continue;
        k = 4;
        continue;
        if (j <= 8000000.0D)
          continue;
        k = 5;
        continue;
        if (j <= 4000000.0D)
          continue;
        k = 6;
        continue;
        if (j <= 2000000.0D)
          continue;
        k = 7;
        continue;
        if (j <= 1000000.0D)
          continue;
        k = 8;
        continue;
        if (j <= 500000.0D)
          continue;
        k = 9;
        continue;
        k = 10;
        continue;
      }
      catch (Exception localException)
      {
        logError("MapPlugin.startRoute()", localException);
        return;
      }
      label437: if (j <= 120000000.0D)
        continue;
      int k = 1;
    }
  }

  public void create()
  {
    try
    {
      setContentView(R.layout.romanblack_mapweb_main);
      NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
        break label667;
      this.isOnline = true;
      this.mapView = ((WebView)findViewById(R.id.romanblack_mapweb_webview));
      this.mapView.setScrollBarStyle(33554432);
      this.mapView.setWebViewClient(new WebViewClient()
      {
        public void onPageFinished(WebView paramWebView, String paramString)
        {
          super.onPageFinished(paramWebView, paramString);
          MapPlugin.this.handler.sendEmptyMessage(3);
        }

        public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
        {
          super.onPageStarted(paramWebView, paramString, paramBitmap);
          MapPlugin.this.handler.sendEmptyMessage(9);
        }
      });
      this.mapView.addJavascriptInterface(new JavaScriptInterface(), "googleredirect");
      this.mapView.getSettings().setJavaScriptEnabled(true);
      this.mapView.getSettings().setPluginsEnabled(true);
      this.mapView.getSettings().setGeolocationEnabled(true);
      this.widget = ((Widget)getIntent().getExtras().getSerializable("Widget"));
      if (this.widget == null)
        this.handler.sendEmptyMessage(0);
      try
      {
        if ((this.widget.getPluginXmlData().length() == 0) && (getIntent().getStringExtra("WidgetFile").length() == 0))
        {
          this.handler.sendEmptyMessageDelayed(0, 3000L);
          return;
        }
      }
      catch (Exception localException2)
      {
        this.handler.sendEmptyMessageDelayed(0, 3000L);
        return;
      }
    }
    catch (Exception localException1)
    {
      logError("MapWebPlugin.create()", localException1);
      return;
    }
    if ((this.widget.getTitle() != null) && (this.widget.getTitle().length() > 0))
    {
      String str = this.widget.getTitle();
      if (this.widget.getTitle().length() > 12)
        str = str.substring(0, 9) + "...";
      setTopBarTitle(str);
      disableSwipe();
      if (!((Boolean)getIntent().getExtras().getSerializable("showSideBar")).booleanValue())
        setTopBarLeftButtonText(getResources().getString(R.string.common_home_upper), true, new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            MapPlugin.this.finish();
          }
        });
      if (this.widget.getPluginXmlData().length() <= 0)
        break label639;
    }
    label639: for (this.parser = new EntityParser(this.widget.getPluginXmlData()); ; this.parser = new EntityParser(readXmlFromFile(getIntent().getStringExtra("WidgetFile"))))
    {
      this.parser.parse();
      this.btnMyLocation = ((Button)findViewById(R.id.romanblack_mapweb_back_to_my_location));
      this.btnMyLocation.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (!MapPlugin.this.locationManager.isProviderEnabled("gps"))
          {
            MapPlugin.this.handler.sendEmptyMessage(5);
            return;
          }
          if (MapPlugin.this.gpsLocation == null)
          {
            if (MapPlugin.this.tempLocation == null)
            {
              MapPlugin.this.handler.sendEmptyMessage(6);
              return;
            }
            MapPlugin.access$1202(MapPlugin.this, MapPlugin.this.tempLocation);
          }
          while (true)
          {
            MapPlugin.access$1302(MapPlugin.this, MapPlugin.this.userLocation.getLatitude());
            MapPlugin.access$1402(MapPlugin.this, MapPlugin.this.userLocation.getLongitude());
            MapPlugin.access$1302(MapPlugin.this, MapPlugin.this.srcLatitude / 1000000.0F);
            MapPlugin.access$1402(MapPlugin.this, MapPlugin.this.srcLongitude / 1000000.0F);
            BigDecimal localBigDecimal1 = new BigDecimal(MapPlugin.this.srcLatitude).setScale(6, 4);
            BigDecimal localBigDecimal2 = new BigDecimal(MapPlugin.this.srcLongitude).setScale(6, 4);
            MapPlugin.this.mapView.loadUrl("javascript:backToMyLocation(" + localBigDecimal1.toString() + "," + localBigDecimal2.toString() + ")");
            return;
            MapPlugin.access$1102(MapPlugin.this, MapPlugin.this.gpsLocation);
            MapPlugin.access$1202(MapPlugin.this, MapPlugin.this.tempLocation);
          }
        }
      });
      if (!this.parser.showCurrentLocation)
        this.btnMyLocation.setVisibility(8);
      this.btnDirection = ((Button)findViewById(R.id.romanblack_mapweb_user_direction));
      this.btnDirection.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          try
          {
            if (!MapPlugin.this.locationManager.isProviderEnabled("gps"))
            {
              MapPlugin.this.handler.sendEmptyMessage(5);
              return;
            }
            if (MapPlugin.this.gpsLocation != null)
              break label220;
            if (MapPlugin.this.tempLocation == null)
            {
              MapPlugin.this.handler.sendEmptyMessage(6);
              return;
            }
          }
          catch (Exception localException)
          {
            MapPlugin.this.logError("MapPlugin.OnClickListener.onClick()", localException);
            return;
          }
          MapPlugin.access$1202(MapPlugin.this, MapPlugin.this.tempLocation);
          while (true)
          {
            MapPlugin.access$1302(MapPlugin.this, MapPlugin.this.userLocation.getLatitude());
            MapPlugin.access$1402(MapPlugin.this, MapPlugin.this.userLocation.getLongitude());
            if (MapPlugin.this.locations.isEmpty())
              break;
            if (MapPlugin.this.locations.size() == 1)
            {
              MapPlugin.access$1702(MapPlugin.this, ((MapLocation)MapPlugin.this.locations.get(0)).getLatitude());
              MapPlugin.access$1802(MapPlugin.this, ((MapLocation)MapPlugin.this.locations.get(0)).getLongitude());
              MapPlugin.this.handler.sendEmptyMessage(7);
              return;
              label220: MapPlugin.access$1102(MapPlugin.this, MapPlugin.this.gpsLocation);
              MapPlugin.access$1202(MapPlugin.this, MapPlugin.this.tempLocation);
              continue;
            }
            MapPlugin.this.handler.sendEmptyMessage(8);
          }
        }
      });
      this.mapBottomPanel = ((MapBottomPanel)findViewById(R.id.romanblack_mapweb_bottom_panel));
      this.locationManager = ((LocationManager)getSystemService("location"));
      if (!this.locationManager.isProviderEnabled("gps"))
        this.handler.sendEmptyMessage(5);
      this.progressDialog = ProgressDialog.show(this, "", getString(R.string.common_loading_upper));
      this.progressDialog.setCancelable(true);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          MapPlugin.this.handler.sendEmptyMessage(4);
        }
      });
      this.mTimer = new Timer();
      this.mTask = new TimerTask()
      {
        public void run()
        {
          MapPlugin.this.TimerMethod();
        }
      };
      this.mTimer.scheduleAtFixedRate(this.mTask, 0L, 12000L);
      new Thread(new Runnable()
      {
        public void run()
        {
          while (true)
          {
            try
            {
              MapPlugin.access$1302(MapPlugin.this, 0.0F);
              MapPlugin.access$1402(MapPlugin.this, 0.0F);
              MapPlugin localMapPlugin = MapPlugin.this;
              if (MapPlugin.this.parser.getTitle().length() > 0)
              {
                str = MapPlugin.this.parser.getTitle();
                MapPlugin.access$2102(localMapPlugin, str);
                MapPlugin.access$2302(MapPlugin.this, MapPlugin.this.parser.getItems());
                Iterator localIterator = MapPlugin.this.items.iterator();
                if (!localIterator.hasNext())
                  break;
                MapItem localMapItem = (MapItem)localIterator.next();
                MapLocation localMapLocation = new MapLocation(localMapItem.getLatitude(), localMapItem.getLongitude());
                localMapLocation.setTitle(localMapItem.getTitle());
                localMapLocation.setSubtitle(localMapItem.getSubtitle());
                localMapLocation.setDescription(localMapItem.getDescription());
                MapPlugin.this.locations.add(localMapLocation);
                continue;
              }
            }
            catch (Exception localException)
            {
              MapPlugin.this.logError("MapWebPlugin.create().Thread.run()", localException);
              return;
            }
            String str = "";
          }
          MapPlugin.access$2402(MapPlugin.this, "");
          ByteArrayOutputStream localByteArrayOutputStream;
          try
          {
            InputStream localInputStream = MapPlugin.this.getResources().openRawResource(R.raw.romanblack_mapweb_page_refreshable);
            localByteArrayOutputStream = new ByteArrayOutputStream();
            byte[] arrayOfByte = new byte[512];
            while (true)
            {
              int i = localInputStream.read(arrayOfByte, 0, 512);
              if (i == -1)
                break;
              localByteArrayOutputStream.write(arrayOfByte, 0, i);
              Arrays.fill(arrayOfByte, 0);
            }
          }
          catch (IOException localIOException)
          {
            Log.e("", "");
            MapPlugin.this.handler.sendEmptyMessage(0);
          }
          while (true)
          {
            MapPlugin.access$2402(MapPlugin.this, MapWebPageCreator.createMapPage(MapPlugin.this.htmlSource, MapPlugin.this.items, MapPlugin.this.parser.zoom, MapPlugin.this.srcLatitude, MapPlugin.this.srcLongitude));
            MapPlugin.this.handler.sendEmptyMessage(2);
            return;
            MapPlugin.access$2402(MapPlugin.this, localByteArrayOutputStream.toString());
          }
        }
      }).start();
      return;
      setTopBarTitle(getResources().getString(R.string.map));
      break;
    }
    label667: finish();
    Toast.makeText(getApplicationContext(), R.string.alert_no_internet, 1).show();
  }

  public void destroy()
  {
    Log.e("DESTROY", " my destrou");
  }

  public void onBackPressed()
  {
    if (this.mapView.getUrl().equalsIgnoreCase("about:blank"))
    {
      this.mTask.cancel();
      this.mTimer.cancel();
      super.onBackPressed();
      return;
    }
    showMap();
    this.mapBottomPanel.setVisibility(0);
  }

  public void onLocationChanged(Location paramLocation)
  {
    if (paramLocation != null)
      this.gpsLocation = new MapLocation(paramLocation.getLatitude(), paramLocation.getLongitude());
  }

  public void onProviderDisabled(String paramString)
  {
    this.isGPS = false;
  }

  public void onProviderEnabled(String paramString)
  {
    this.isGPS = true;
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
  }

  public void pause()
  {
    super.pause();
    this.locationManager.removeUpdates(this);
  }

  // ERROR //
  protected String readXmlFromFile(String paramString)
  {
    // Byte code:
    //   0: new 389	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 390	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: new 756	java/io/BufferedReader
    //   11: dup
    //   12: new 758	java/io/FileReader
    //   15: dup
    //   16: new 760	java/io/File
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 761	java/io/File:<init>	(Ljava/lang/String;)V
    //   24: invokespecial 764	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   27: invokespecial 767	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   30: astore_3
    //   31: aload_3
    //   32: invokevirtual 770	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   35: astore 6
    //   37: aload 6
    //   39: ifnull +20 -> 59
    //   42: aload_2
    //   43: aload 6
    //   45: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: goto -18 -> 31
    //   52: astore 5
    //   54: aload_2
    //   55: invokevirtual 426	java/lang/StringBuilder:toString	()Ljava/lang/String;
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

  public void resume()
  {
    super.resume();
    this.locationManager.requestLocationUpdates("gps", 2000L, 0.0F, this);
    if (this.locationSpinner != null)
      this.locationSpinner.setSelection(0);
  }

  private final class JavaScriptInterface
  {
    public JavaScriptInterface()
    {
    }

    public void goToUrl(String paramString1, String paramString2)
    {
      MapPlugin.access$602(MapPlugin.this, paramString1);
      MapPlugin.this.handler.sendEmptyMessage(10);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MapPlugin.MapPlugin
 * JD-Core Version:    0.6.0
 */