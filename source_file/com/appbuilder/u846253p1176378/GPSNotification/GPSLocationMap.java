package com.appbuilder.u846253p1176378.GPSNotification;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GPSLocationMap extends MapActivity
  implements LocationListener
{
  private final int HIDE_PROGRESS = 6;
  private final int LOADING_ABORTED = 4;
  private final int LOCATION_LISTENER_ERROR = 2;
  private final int LOCATION_LISTENER_START = 0;
  private final int LOCATION_LISTENER_STOP = 1;
  private final int LOCATION_MAP_SHOW = 3;
  private final int SHOW_PROGRESS = 5;
  private String dstDescription = "";
  private double dstLatitude = -999.0D;
  private double dstLongitude = -999.0D;
  private String dstTitle = "";
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 4:
        GPSLocationMap.this.closeActivity();
        return;
      case 0:
        GPSLocationMap.this.startLocationListener();
        return;
      case 1:
        GPSLocationMap.this.stopLocationListener();
        return;
      case 2:
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            GPSLocationMap.this.finish();
          }
        }
        , 5000L);
        return;
      case 3:
        GPSLocationMap.this.showLocationMap();
        return;
      case 5:
        GPSLocationMap.this.showProgress();
        return;
      case 6:
      }
      GPSLocationMap.this.hideProgress();
    }
  };
  private LocationManager locationManager = null;
  private ProgressDialog progressDialog = null;
  private String srcDescription = "";
  private double srcLatitude = -999.0D;
  private double srcLongitude = -999.0D;
  private String srcTitle = "";

  private void DrawPath(GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2, int paramInt, MapView paramMapView)
  {
    Bitmap localBitmap1 = BitmapFactory.decodeResource(getResources(), 2130837530);
    Bitmap localBitmap2 = BitmapFactory.decodeResource(getResources(), 2130837529);
    Bitmap localBitmap3 = BitmapFactory.decodeResource(getResources(), 2130837531);
    GPSLocationMapOverlay localGPSLocationMapOverlay = new GPSLocationMapOverlay();
    ArrayList localArrayList = new ArrayList();
    if (paramGeoPoint1 != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("http://maps.google.com/maps?f=d&hl=en");
      localStringBuilder.append("&saddr=");
      localStringBuilder.append(Double.toString(paramGeoPoint1.getLatitudeE6() / 1000000.0D));
      localStringBuilder.append(",");
      localStringBuilder.append(Double.toString(paramGeoPoint1.getLongitudeE6() / 1000000.0D));
      localStringBuilder.append("&daddr=");
      localStringBuilder.append(Double.toString(paramGeoPoint2.getLatitudeE6() / 1000000.0D));
      localStringBuilder.append(",");
      localStringBuilder.append(Double.toString(paramGeoPoint2.getLongitudeE6() / 1000000.0D));
      localStringBuilder.append("&ie=UTF8&0&om=0&output=kml");
      try
      {
        URL localURL = new URL(localStringBuilder.toString());
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
        localHttpURLConnection.setRequestMethod("GET");
        localHttpURLConnection.setDoOutput(true);
        localHttpURLConnection.setDoInput(true);
        localHttpURLConnection.connect();
        Document localDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(localHttpURLConnection.getInputStream());
        if (localDocument.getElementsByTagName("GeometryCollection").getLength() > 0)
        {
          String[] arrayOfString1 = localDocument.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue().split(" ");
          String[] arrayOfString2 = arrayOfString1[0].split(",");
          localArrayList.add(new GeoPoint((int)(1000000.0D * Double.parseDouble(arrayOfString2[1])), (int)(1000000.0D * Double.parseDouble(arrayOfString2[0]))));
          for (int i = 1; i < arrayOfString1.length; i++)
          {
            String[] arrayOfString3 = arrayOfString1[i].split(",");
            localArrayList.add(new GeoPoint((int)(1000000.0D * Double.parseDouble(arrayOfString3[1])), (int)(1000000.0D * Double.parseDouble(arrayOfString3[0]))));
          }
        }
      }
      catch (Exception localException)
      {
      }
    }
    if (localArrayList.size() > 1)
      localGPSLocationMapOverlay.setRoute(localArrayList, Color.argb(127, 204, 51, 255));
    if (paramGeoPoint1 != null)
    {
      GPSLocationMapItem localGPSLocationMapItem1 = new GPSLocationMapItem();
      localGPSLocationMapItem1.setGeoPoint(paramGeoPoint1);
      localGPSLocationMapItem1.setTitle(this.srcTitle);
      localGPSLocationMapItem1.setDescription(this.srcDescription);
      localGPSLocationMapItem1.setIcon(localBitmap2);
      localGPSLocationMapItem1.setIconShadow(localBitmap3);
      localGPSLocationMapOverlay.addPoint(localGPSLocationMapItem1);
    }
    GPSLocationMapItem localGPSLocationMapItem2 = new GPSLocationMapItem();
    localGPSLocationMapItem2.setGeoPoint(paramGeoPoint2);
    localGPSLocationMapItem2.setTitle(this.dstTitle);
    localGPSLocationMapItem2.setDescription(this.dstDescription);
    localGPSLocationMapItem2.setIcon(localBitmap1);
    localGPSLocationMapItem2.setIconShadow(localBitmap3);
    localGPSLocationMapOverlay.addPoint(localGPSLocationMapItem2);
    paramMapView.getOverlays().clear();
    paramMapView.getOverlays().add(localGPSLocationMapOverlay);
    if (paramGeoPoint1 == null)
    {
      paramMapView.getController().setZoom(12);
      paramMapView.getController().animateTo(paramGeoPoint2);
      return;
    }
    paramMapView.getController().zoomToSpan(paramGeoPoint1.getLatitudeE6() - paramGeoPoint2.getLatitudeE6(), paramGeoPoint1.getLongitudeE6() - paramGeoPoint2.getLongitudeE6());
    paramMapView.getController().animateTo(paramGeoPoint1);
  }

  private void hideProgress()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
  }

  private void showLocationMap()
  {
    WebView localWebView = (WebView)findViewById(2131361807);
    localWebView.getSettings().setJavaScriptEnabled(true);
    localWebView.getSettings().setGeolocationEnabled(true);
    localWebView.getSettings().setPluginsEnabled(true);
    localWebView.getSettings().setAllowFileAccess(true);
    localWebView.getSettings().setAppCacheEnabled(true);
    localWebView.getSettings().setCacheMode(-1);
    localWebView.getSettings().setBuiltInZoomControls(true);
    localWebView.clearHistory();
    localWebView.setScrollBarStyle(0);
    localWebView.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramWebView, String paramString)
      {
        GPSLocationMap.this.handler.sendEmptyMessage(6);
      }

      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        if (GPSLocationMap.this.progressDialog != null)
        {
          if (!GPSLocationMap.this.progressDialog.isShowing())
            GPSLocationMap.this.handler.sendEmptyMessage(5);
          return;
        }
        GPSLocationMap.this.handler.sendEmptyMessage(5);
      }

      public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
      {
        return !paramString.contains("maps.google");
      }
    });
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://maps.google.com/maps?saddr=");
    localStringBuilder.append(this.srcLatitude);
    localStringBuilder.append(",");
    localStringBuilder.append(this.srcLongitude);
    localStringBuilder.append("&daddr=");
    localStringBuilder.append(this.dstLatitude);
    localStringBuilder.append(",");
    localStringBuilder.append(this.dstLongitude);
    localStringBuilder.append("&ll=");
    localStringBuilder.append((this.srcLatitude + this.dstLatitude) / 2.0D);
    localStringBuilder.append(",");
    localStringBuilder.append((this.srcLongitude + this.dstLongitude) / 2.0D);
    localStringBuilder.append("&z=");
    int i;
    int j;
    if (Math.abs(this.srcLatitude - this.dstLatitude) > Math.abs(this.srcLongitude - this.dstLongitude))
    {
      i = Math.abs((int)(this.srcLatitude - this.dstLatitude));
      if (i <= 120000000.0D)
        break label310;
      j = 1;
    }
    while (true)
    {
      localStringBuilder.append(j);
      localWebView.loadUrl(localStringBuilder.toString());
      return;
      i = Math.abs((int)(this.srcLongitude - this.dstLongitude));
      break;
      label310: if (i > 60000000.0D)
      {
        j = 2;
        continue;
      }
      if (i > 30000000.0D)
      {
        j = 3;
        continue;
      }
      if (i > 15000000.0D)
      {
        j = 4;
        continue;
      }
      if (i > 8000000.0D)
      {
        j = 5;
        continue;
      }
      if (i > 4000000.0D)
      {
        j = 6;
        continue;
      }
      if (i > 2000000.0D)
      {
        j = 7;
        continue;
      }
      if (i > 1000000.0D)
      {
        j = 8;
        continue;
      }
      if (i > 500000.0D)
      {
        j = 9;
        continue;
      }
      j = 10;
    }
  }

  private void showProgress()
  {
    if ((this.progressDialog != null) && (!this.progressDialog.isShowing()))
      this.progressDialog = ProgressDialog.show(this, null, getString(2131100003), true);
  }

  private void startLocationListener()
  {
    if (this.locationManager != null)
    {
      if (this.locationManager.isProviderEnabled("gps"))
        this.locationManager.requestLocationUpdates("gps", 300000L, 0.0F, this);
      return;
    }
    this.handler.sendEmptyMessage(2);
  }

  private void stopLocationListener()
  {
    if (this.locationManager != null)
      this.locationManager.removeUpdates(this);
  }

  public void closeActivity()
  {
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
    finish();
  }

  protected boolean isRouteDisplayed()
  {
    return false;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903043);
    this.progressDialog = ProgressDialog.show(this, null, getString(2131100003), true);
    this.progressDialog.setCancelable(true);
    this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        GPSLocationMap.this.handler.sendEmptyMessage(4);
      }
    });
    try
    {
      localGPSItem = (GPSItem)getIntent().getSerializableExtra("gpsNotificationData");
      if (localGPSItem != null)
      {
        this.dstLatitude = localGPSItem.getLatitude();
        this.dstLongitude = localGPSItem.getLongitude();
        this.dstTitle = localGPSItem.getTitle();
        this.dstDescription = localGPSItem.getDescription();
      }
      this.srcLatitude = getIntent().getDoubleExtra("srcLatitude", 0.0D);
      this.srcLongitude = getIntent().getDoubleExtra("srcLongitude", 0.0D);
      this.locationManager = ((LocationManager)getSystemService("location"));
      if (this.locationManager.isProviderEnabled("gps"))
        this.locationManager.requestLocationUpdates("gps", 0L, 0.0F, this);
      this.handler.sendEmptyMessage(3);
      this.handler.sendEmptyMessageDelayed(1, 180000L);
      this.progressDialog.dismiss();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        GPSItem localGPSItem = null;
    }
  }

  public void onDestroy()
  {
    if (this.locationManager != null)
      this.locationManager.removeUpdates(this);
    super.onDestroy();
  }

  public void onLocationChanged(Location paramLocation)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Geocoder localGeocoder = new Geocoder(getBaseContext(), Locale.getDefault());
    try
    {
      List localList = localGeocoder.getFromLocation((int)(1000000.0D * paramLocation.getLatitude()) / 1000000.0D, (int)(1000000.0D * paramLocation.getLongitude()) / 1000000.0D, 1);
      if (localList.size() > 0)
        for (int i = 0; i < ((Address)localList.get(0)).getMaxAddressLineIndex(); i++)
        {
          localStringBuilder.append(" ");
          localStringBuilder.append(((Address)localList.get(0)).getAddressLine(i));
          localStringBuilder.append(" ");
        }
    }
    catch (Exception localException)
    {
      this.srcLatitude = paramLocation.getLatitude();
      this.srcLongitude = paramLocation.getLongitude();
      this.srcDescription = localStringBuilder.toString();
      this.handler.sendEmptyMessage(1);
      this.handler.sendEmptyMessage(3);
    }
  }

  public void onPause()
  {
    if (this.locationManager != null)
      this.locationManager.removeUpdates(this);
    super.onPause();
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.GPSNotification.GPSLocationMap
 * JD-Core Version:    0.6.0
 */