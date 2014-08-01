package com.ibuildapp.romanblack.TableReservationPlugin;

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
import com.appbuilder.sdk.android.AppBuilderModule;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationMapWebPageCreator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class TableReservationMap extends AppBuilderModule
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
      if (!TableReservationMap.this.locationManager.isProviderEnabled("gps"));
      while (true)
      {
        return;
        if (TableReservationMap.this.gpsLocation != null)
          break;
        if (TableReservationMap.this.tempLocation == null)
          continue;
        TableReservationMap.access$1202(TableReservationMap.this, TableReservationMap.this.tempLocation);
      }
      while (true)
      {
        TableReservationMap.access$1302(TableReservationMap.this, TableReservationMap.this.userLocation.getLatitude());
        TableReservationMap.access$1402(TableReservationMap.this, TableReservationMap.this.userLocation.getLongitude());
        TableReservationMap.access$1302(TableReservationMap.this, TableReservationMap.this.srcLatitude / 1000000.0F);
        TableReservationMap.access$1402(TableReservationMap.this, TableReservationMap.this.srcLongitude / 1000000.0F);
        BigDecimal localBigDecimal1 = new BigDecimal(TableReservationMap.this.srcLatitude).setScale(6, 4);
        BigDecimal localBigDecimal2 = new BigDecimal(TableReservationMap.this.srcLongitude).setScale(6, 4);
        TableReservationMap.this.mapView.loadUrl("javascript:moveUserMarker(" + localBigDecimal1.toString() + "," + localBigDecimal2.toString() + ")");
        return;
        TableReservationMap.access$1102(TableReservationMap.this, TableReservationMap.this.gpsLocation);
        TableReservationMap.access$1202(TableReservationMap.this, TableReservationMap.this.tempLocation);
      }
    }
  };
  private Button btnDirection = null;
  private Button btnMyLocation = null;
  private float dstLatitude = 0.0F;
  private float dstLongitude = 0.0F;
  private TableReservationMapLocation gpsLocation = null;
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
        Toast.makeText(TableReservationMap.this, R.string.alert_cannot_init, 1).show();
        TableReservationMap.this.closeActivity();
        return;
      case 1:
        Toast.makeText(TableReservationMap.this, R.string.alert_no_internet, 1).show();
        TableReservationMap.this.closeActivity();
        return;
      case 2:
        TableReservationMap.this.showMap();
        return;
      case 3:
        TableReservationMap.this.hideProgressDialog();
        return;
      case 4:
        TableReservationMap.this.closeActivity();
        return;
      case 5:
        Toast.makeText(TableReservationMap.this, R.string.alert_gps_not_available, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
          }
        }
        , 5000L);
        return;
      case 6:
        Toast.makeText(TableReservationMap.this, R.string.alert_gps_initialization, 1).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
          }
        }
        , 7000L);
        return;
      case 7:
        TableReservationMap.this.startRoute();
        return;
      case 8:
        TableReservationMap.this.choseRouteFinal();
        return;
      case 9:
        TableReservationMap.this.showProgressDialog();
        return;
      case 10:
      }
      TableReservationMap.this.goToUrl(TableReservationMap.this.urlToGo, "");
    }
  };
  private String htmlSource = "";
  private LocationManager locationManager = null;
  private Spinner locationSpinner = null;
  private ArrayList<TableReservationMapLocation> locations = new ArrayList();
  private TimerTask mTask;
  private Timer mTimer;
  private TableReservationMapBottomPanel mapBottomPanel = null;
  private WebView mapView = null;
  private TableReservationInfo orderInfo;
  private ProgressDialog progressDialog = null;
  private float srcLatitude = 0.0F;
  private float srcLongitude = 0.0F;
  private TableReservationMapLocation tempLocation = null;
  private String title = "";
  private String urlToGo = "";
  private TableReservationMapLocation userLocation = null;

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
        localArrayList.add(getResources().getString(R.string.common_cancel_upper));
        for (int i = 0; i < this.locations.size(); i++)
          localArrayList.add(((TableReservationMapLocation)this.locations.get(i)).getTitle());
        ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048, localArrayList);
        this.locationSpinner.setAdapter(localArrayAdapter);
        this.locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
          {
            if (paramInt != 0);
            try
            {
              TableReservationMap.access$1702(TableReservationMap.this, ((TableReservationMapLocation)TableReservationMap.this.locations.get(paramInt - 1)).getLatitude());
              TableReservationMap.access$1802(TableReservationMap.this, ((TableReservationMapLocation)TableReservationMap.this.locations.get(paramInt - 1)).getLongitude());
              TableReservationMap.this.handler.sendEmptyMessage(7);
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
    }
  }

  private void closeActivity()
  {
    hideProgressDialog();
    finish();
  }

  private void goToUrl(String paramString1, String paramString2)
  {
    this.mapView.loadUrl(paramString1);
    this.mapBottomPanel.setVisibility(8);
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
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
    }
  }

  private void showProgressDialog()
  {
    if ((this.progressDialog != null) && (!this.progressDialog.isShowing()))
      this.progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.common_loading_upper));
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
        break label429;
        localStringBuilder.append(k);
        String str = localStringBuilder.toString();
        Intent localIntent = new Intent(this, TableReservationMapRoute.class);
        localIntent.putExtra("url", str);
        startActivity(localIntent);
        return;
        int i = Math.abs((int)(this.srcLongitude - this.dstLongitude));
        j = i;
        break label429;
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
        return;
      }
      label429: if (j <= 120000000.0D)
        continue;
      int k = 1;
    }
  }

  public void create()
  {
    try
    {
      requestWindowFeature(1);
      setContentView(R.layout.sergeyb_tablereservation_mapweb_main);
      setTitle("Google Map");
      this.orderInfo = ((TableReservationInfo)getIntent().getSerializableExtra("xml"));
      NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
      {
        this.mapView = ((WebView)findViewById(R.id.sergeyb_tablereservation_mapweb_webview));
        this.mapView.setScrollBarStyle(33554432);
        this.mapView.setWebViewClient(new WebViewClient()
        {
          public void onPageFinished(WebView paramWebView, String paramString)
          {
            super.onPageFinished(paramWebView, paramString);
            TableReservationMap.this.handler.sendEmptyMessage(3);
          }

          public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
          {
            super.onPageStarted(paramWebView, paramString, paramBitmap);
            TableReservationMap.this.handler.sendEmptyMessage(9);
          }
        });
        this.mapView.addJavascriptInterface(new JavaScriptInterface(), "googleredirect");
        this.mapView.getSettings().setJavaScriptEnabled(true);
        this.mapView.getSettings().setPluginsEnabled(true);
        this.mapView.getSettings().setGeolocationEnabled(true);
        this.btnMyLocation = ((Button)findViewById(R.id.sergeyb_tablereservation_mapweb_back_to_my_location));
        this.btnMyLocation.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            if (!TableReservationMap.this.locationManager.isProviderEnabled("gps"))
            {
              TableReservationMap.this.handler.sendEmptyMessage(5);
              return;
            }
            if (TableReservationMap.this.gpsLocation == null)
            {
              if (TableReservationMap.this.tempLocation == null)
              {
                TableReservationMap.this.handler.sendEmptyMessage(6);
                return;
              }
              TableReservationMap.access$1202(TableReservationMap.this, TableReservationMap.this.tempLocation);
            }
            while (true)
            {
              TableReservationMap.access$1302(TableReservationMap.this, TableReservationMap.this.userLocation.getLatitude());
              TableReservationMap.access$1402(TableReservationMap.this, TableReservationMap.this.userLocation.getLongitude());
              TableReservationMap.access$1302(TableReservationMap.this, TableReservationMap.this.srcLatitude / 1000000.0F);
              TableReservationMap.access$1402(TableReservationMap.this, TableReservationMap.this.srcLongitude / 1000000.0F);
              BigDecimal localBigDecimal1 = new BigDecimal(TableReservationMap.this.srcLatitude).setScale(6, 4);
              BigDecimal localBigDecimal2 = new BigDecimal(TableReservationMap.this.srcLongitude).setScale(6, 4);
              TableReservationMap.this.mapView.loadUrl("javascript:backToMyLocation(" + localBigDecimal1.toString() + "," + localBigDecimal2.toString() + ")");
              return;
              TableReservationMap.access$1102(TableReservationMap.this, TableReservationMap.this.gpsLocation);
              TableReservationMap.access$1202(TableReservationMap.this, TableReservationMap.this.tempLocation);
            }
          }
        });
        this.btnDirection = ((Button)findViewById(R.id.sergeyb_tablereservation_mapweb_user_direction));
        this.btnDirection.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            try
            {
              if (!TableReservationMap.this.locationManager.isProviderEnabled("gps"))
              {
                TableReservationMap.this.handler.sendEmptyMessage(5);
                return;
              }
              if (TableReservationMap.this.gpsLocation == null)
              {
                if (TableReservationMap.this.tempLocation == null)
                {
                  TableReservationMap.this.handler.sendEmptyMessage(6);
                  return;
                }
                TableReservationMap.access$1202(TableReservationMap.this, TableReservationMap.this.tempLocation);
              }
              while (true)
              {
                TableReservationMap.access$1302(TableReservationMap.this, TableReservationMap.this.userLocation.getLatitude());
                TableReservationMap.access$1402(TableReservationMap.this, TableReservationMap.this.userLocation.getLongitude());
                if (TableReservationMap.this.locations.isEmpty())
                  break;
                if (TableReservationMap.this.locations.size() == 1)
                {
                  TableReservationMap.access$1702(TableReservationMap.this, ((TableReservationMapLocation)TableReservationMap.this.locations.get(0)).getLatitude());
                  TableReservationMap.access$1802(TableReservationMap.this, ((TableReservationMapLocation)TableReservationMap.this.locations.get(0)).getLongitude());
                  TableReservationMap.this.handler.sendEmptyMessage(7);
                  return;
                  TableReservationMap.access$1102(TableReservationMap.this, TableReservationMap.this.gpsLocation);
                  TableReservationMap.access$1202(TableReservationMap.this, TableReservationMap.this.tempLocation);
                  continue;
                }
                TableReservationMap.this.handler.sendEmptyMessage(8);
              }
              return;
            }
            catch (Exception localException)
            {
            }
          }
        });
        this.mapBottomPanel = ((TableReservationMapBottomPanel)findViewById(R.id.sergeyb_tablereservation_mapweb_bottom_panel));
        this.locationManager = ((LocationManager)getSystemService("location"));
        if (!this.locationManager.isProviderEnabled("gps"))
          this.handler.sendEmptyMessage(5);
        this.progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.common_loading_upper));
        this.progressDialog.setCancelable(true);
        this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramDialogInterface)
          {
            TableReservationMap.this.handler.sendEmptyMessage(4);
          }
        });
        this.mTimer = new Timer();
        this.mTask = new TimerTask()
        {
          public void run()
          {
            TableReservationMap.this.TimerMethod();
          }
        };
        this.mTimer.scheduleAtFixedRate(this.mTask, 0L, 12000L);
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              TableReservationMap.access$2002(TableReservationMap.this, "");
              while (true)
              {
                ByteArrayOutputStream localByteArrayOutputStream;
                try
                {
                  InputStream localInputStream = TableReservationMap.this.getResources().openRawResource(R.raw.sergeyb_tablereservation_page_normal);
                  localByteArrayOutputStream = new ByteArrayOutputStream();
                  byte[] arrayOfByte = new byte[512];
                  int i = localInputStream.read(arrayOfByte, 0, 512);
                  if (i != -1)
                  {
                    localByteArrayOutputStream.write(arrayOfByte, 0, i);
                    Arrays.fill(arrayOfByte, 0);
                    continue;
                  }
                }
                catch (IOException localIOException)
                {
                  TableReservationMap.this.handler.sendEmptyMessage(0);
                  TableReservationMap.access$2002(TableReservationMap.this, TableReservationMapWebPageCreator.createMapPage(TableReservationMap.this.htmlSource, TableReservationMap.this.orderInfo.getLatitude(), TableReservationMap.this.orderInfo.getLongitude(), TableReservationMap.this.orderInfo.getRestaurantName(), TableReservationMap.this.orderInfo.getRestaurantadditional()));
                  TableReservationMap.this.handler.sendEmptyMessage(2);
                  return;
                }
                TableReservationMap.access$2002(TableReservationMap.this, localByteArrayOutputStream.toString());
              }
            }
            catch (Exception localException)
            {
            }
          }
        }).start();
        return;
      }
      finish();
      Toast.makeText(getApplicationContext(), R.string.alert_no_internet, 1).show();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void destroy()
  {
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
      this.gpsLocation = new TableReservationMapLocation(paramLocation.getLatitude(), paramLocation.getLongitude());
  }

  public void onProviderDisabled(String paramString)
  {
  }

  public void onProviderEnabled(String paramString)
  {
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
  }

  public void pause()
  {
    super.pause();
    this.locationManager.removeUpdates(this);
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
      TableReservationMap.access$602(TableReservationMap.this, paramString1);
      TableReservationMap.this.handler.sendEmptyMessage(10);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationMap
 * JD-Core Version:    0.6.0
 */