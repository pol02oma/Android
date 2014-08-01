package com.google.android.gms.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.hi;
import com.google.android.gms.internal.hj;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocationClient
  implements GooglePlayServicesClient
{
  public static final String KEY_LOCATION_CHANGED = "com.google.android.location.LOCATION";
  public static final String KEY_MOCK_LOCATION = "mockLocation";
  private final hi KO;

  public LocationClient(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.KO = new hi(paramContext, paramConnectionCallbacks, paramOnConnectionFailedListener, "location");
  }

  public static int getErrorCode(Intent paramIntent)
  {
    return paramIntent.getIntExtra("gms_error_code", -1);
  }

  public static int getGeofenceTransition(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
    if (i == -1);
    do
      return -1;
    while ((i != 1) && (i != 2) && (i != 4));
    return i;
  }

  public static List<Geofence> getTriggeringGeofences(Intent paramIntent)
  {
    ArrayList localArrayList1 = (ArrayList)paramIntent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
    if (localArrayList1 == null)
      return null;
    ArrayList localArrayList2 = new ArrayList(localArrayList1.size());
    Iterator localIterator = localArrayList1.iterator();
    while (localIterator.hasNext())
      localArrayList2.add(hj.h((byte[])localIterator.next()));
    return localArrayList2;
  }

  public static boolean hasError(Intent paramIntent)
  {
    return paramIntent.hasExtra("gms_error_code");
  }

  public void addGeofences(List<Geofence> paramList, PendingIntent paramPendingIntent, OnAddGeofencesResultListener paramOnAddGeofencesResultListener)
  {
    Object localObject = null;
    if (paramList != null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Geofence localGeofence = (Geofence)localIterator.next();
        er.b(localGeofence instanceof hj, "Geofence must be created using Geofence.Builder.");
        localArrayList.add((hj)localGeofence);
      }
      localObject = localArrayList;
    }
    this.KO.addGeofences(localObject, paramPendingIntent, paramOnAddGeofencesResultListener);
  }

  public void connect()
  {
    this.KO.connect();
  }

  public void disconnect()
  {
    this.KO.disconnect();
  }

  public Location getLastLocation()
  {
    return this.KO.getLastLocation();
  }

  public boolean isConnected()
  {
    return this.KO.isConnected();
  }

  public boolean isConnecting()
  {
    return this.KO.isConnecting();
  }

  public boolean isConnectionCallbacksRegistered(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.KO.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }

  public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.KO.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }

  public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.KO.registerConnectionCallbacks(paramConnectionCallbacks);
  }

  public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.KO.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }

  public void removeGeofences(PendingIntent paramPendingIntent, OnRemoveGeofencesResultListener paramOnRemoveGeofencesResultListener)
  {
    this.KO.removeGeofences(paramPendingIntent, paramOnRemoveGeofencesResultListener);
  }

  public void removeGeofences(List<String> paramList, OnRemoveGeofencesResultListener paramOnRemoveGeofencesResultListener)
  {
    this.KO.removeGeofences(paramList, paramOnRemoveGeofencesResultListener);
  }

  public void removeLocationUpdates(PendingIntent paramPendingIntent)
  {
    this.KO.removeLocationUpdates(paramPendingIntent);
  }

  public void removeLocationUpdates(LocationListener paramLocationListener)
  {
    this.KO.removeLocationUpdates(paramLocationListener);
  }

  public void requestLocationUpdates(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
  {
    this.KO.requestLocationUpdates(paramLocationRequest, paramPendingIntent);
  }

  public void requestLocationUpdates(LocationRequest paramLocationRequest, LocationListener paramLocationListener)
  {
    this.KO.requestLocationUpdates(paramLocationRequest, paramLocationListener);
  }

  public void requestLocationUpdates(LocationRequest paramLocationRequest, LocationListener paramLocationListener, Looper paramLooper)
  {
    this.KO.requestLocationUpdates(paramLocationRequest, paramLocationListener, paramLooper);
  }

  public void setMockLocation(Location paramLocation)
  {
    this.KO.setMockLocation(paramLocation);
  }

  public void setMockMode(boolean paramBoolean)
  {
    this.KO.setMockMode(paramBoolean);
  }

  public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.KO.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }

  public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.KO.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }

  public static abstract interface OnAddGeofencesResultListener
  {
    public abstract void onAddGeofencesResult(int paramInt, String[] paramArrayOfString);
  }

  public static abstract interface OnRemoveGeofencesResultListener
  {
    public abstract void onRemoveGeofencesByPendingIntentResult(int paramInt, PendingIntent paramPendingIntent);

    public abstract void onRemoveGeofencesByRequestIdsResult(int paramInt, String[] paramArrayOfString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.location.LocationClient
 * JD-Core Version:    0.6.0
 */