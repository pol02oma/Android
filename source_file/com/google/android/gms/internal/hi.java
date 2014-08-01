package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient.OnAddGeofencesResultListener;
import com.google.android.gms.location.LocationClient.OnRemoveGeofencesResultListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationStatusCodes;
import java.util.List;
import java.util.Locale;

public class hi extends eh<hg>
{
  private final hl<hg> Lk = new c(null);
  private final hh Lq = new hh(paramContext, this.Lk);
  private final ib Lr;
  private final String Ls;
  private final String vi;

  public hi(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString)
  {
    super(paramContext, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
    this.Ls = paramString;
    this.vi = null;
    this.Lr = new ib(getContext(), Locale.getDefault(), this.Lk);
  }

  protected hg Q(IBinder paramIBinder)
  {
    return hg.a.P(paramIBinder);
  }

  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("client_name", this.Ls);
    paramen.e(parame, 4323000, getContext().getPackageName(), localBundle);
  }

  protected String aF()
  {
    return "com.google.android.location.internal.GoogleLocationManagerService.START";
  }

  protected String aG()
  {
    return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
  }

  public void addGeofences(List<hj> paramList, PendingIntent paramPendingIntent, LocationClient.OnAddGeofencesResultListener paramOnAddGeofencesResultListener)
  {
    bm();
    if ((paramList != null) && (paramList.size() > 0));
    for (boolean bool = true; ; bool = false)
    {
      er.b(bool, "At least one geofence must be specified.");
      er.b(paramPendingIntent, "PendingIntent must be specified.");
      er.b(paramOnAddGeofencesResultListener, "OnAddGeofencesResultListener not provided.");
      Object localObject;
      if (paramOnAddGeofencesResultListener == null)
        localObject = null;
      try
      {
        while (true)
        {
          ((hg)eb()).a(paramList, paramPendingIntent, (hf)localObject, getContext().getPackageName());
          return;
          b localb = new b(paramOnAddGeofencesResultListener, this);
          localObject = localb;
        }
      }
      catch (RemoteException localRemoteException)
      {
        throw new IllegalStateException(localRemoteException);
      }
    }
  }

  public void disconnect()
  {
    synchronized (this.Lq)
    {
      if (isConnected())
      {
        this.Lq.removeAllListeners();
        this.Lq.gl();
      }
      super.disconnect();
      return;
    }
  }

  public Location getLastLocation()
  {
    return this.Lq.getLastLocation();
  }

  public void removeActivityUpdates(PendingIntent paramPendingIntent)
  {
    bm();
    er.f(paramPendingIntent);
    try
    {
      ((hg)eb()).removeActivityUpdates(paramPendingIntent);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  public void removeGeofences(PendingIntent paramPendingIntent, LocationClient.OnRemoveGeofencesResultListener paramOnRemoveGeofencesResultListener)
  {
    bm();
    er.b(paramPendingIntent, "PendingIntent must be specified.");
    er.b(paramOnRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
    Object localObject;
    if (paramOnRemoveGeofencesResultListener == null)
      localObject = null;
    try
    {
      while (true)
      {
        ((hg)eb()).a(paramPendingIntent, (hf)localObject, getContext().getPackageName());
        return;
        b localb = new b(paramOnRemoveGeofencesResultListener, this);
        localObject = localb;
      }
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  public void removeGeofences(List<String> paramList, LocationClient.OnRemoveGeofencesResultListener paramOnRemoveGeofencesResultListener)
  {
    bm();
    if ((paramList != null) && (paramList.size() > 0));
    for (boolean bool = true; ; bool = false)
    {
      er.b(bool, "geofenceRequestIds can't be null nor empty.");
      er.b(paramOnRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
      String[] arrayOfString = (String[])paramList.toArray(new String[0]);
      Object localObject;
      if (paramOnRemoveGeofencesResultListener == null)
        localObject = null;
      try
      {
        while (true)
        {
          ((hg)eb()).a(arrayOfString, (hf)localObject, getContext().getPackageName());
          return;
          b localb = new b(paramOnRemoveGeofencesResultListener, this);
          localObject = localb;
        }
      }
      catch (RemoteException localRemoteException)
      {
        throw new IllegalStateException(localRemoteException);
      }
    }
  }

  public void removeLocationUpdates(PendingIntent paramPendingIntent)
  {
    this.Lq.removeLocationUpdates(paramPendingIntent);
  }

  public void removeLocationUpdates(LocationListener paramLocationListener)
  {
    this.Lq.removeLocationUpdates(paramLocationListener);
  }

  public void requestActivityUpdates(long paramLong, PendingIntent paramPendingIntent)
  {
    boolean bool = true;
    bm();
    er.f(paramPendingIntent);
    if (paramLong >= 0L);
    while (true)
    {
      er.b(bool, "detectionIntervalMillis must be >= 0");
      try
      {
        ((hg)eb()).a(paramLong, true, paramPendingIntent);
        return;
        bool = false;
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new IllegalStateException(localRemoteException);
  }

  public void requestLocationUpdates(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
  {
    this.Lq.requestLocationUpdates(paramLocationRequest, paramPendingIntent);
  }

  public void requestLocationUpdates(LocationRequest paramLocationRequest, LocationListener paramLocationListener)
  {
    requestLocationUpdates(paramLocationRequest, paramLocationListener, null);
  }

  public void requestLocationUpdates(LocationRequest paramLocationRequest, LocationListener paramLocationListener, Looper paramLooper)
  {
    synchronized (this.Lq)
    {
      this.Lq.requestLocationUpdates(paramLocationRequest, paramLocationListener, paramLooper);
      return;
    }
  }

  public void setMockLocation(Location paramLocation)
  {
    this.Lq.setMockLocation(paramLocation);
  }

  public void setMockMode(boolean paramBoolean)
  {
    this.Lq.setMockMode(paramBoolean);
  }

  private final class a extends eh<hg>.b<LocationClient.OnAddGeofencesResultListener>
  {
    private final String[] Lt;
    private final int yJ;

    public a(LocationClient.OnAddGeofencesResultListener paramInt, int paramArrayOfString, String[] arg4)
    {
      super(paramInt);
      this.yJ = LocationStatusCodes.bl(paramArrayOfString);
      Object localObject;
      this.Lt = localObject;
    }

    protected void a(LocationClient.OnAddGeofencesResultListener paramOnAddGeofencesResultListener)
    {
      if (paramOnAddGeofencesResultListener != null)
        paramOnAddGeofencesResultListener.onAddGeofencesResult(this.yJ, this.Lt);
    }

    protected void cP()
    {
    }
  }

  private static final class b extends hf.a
  {
    private LocationClient.OnAddGeofencesResultListener Lv;
    private LocationClient.OnRemoveGeofencesResultListener Lw;
    private hi Lx;

    public b(LocationClient.OnAddGeofencesResultListener paramOnAddGeofencesResultListener, hi paramhi)
    {
      this.Lv = paramOnAddGeofencesResultListener;
      this.Lw = null;
      this.Lx = paramhi;
    }

    public b(LocationClient.OnRemoveGeofencesResultListener paramOnRemoveGeofencesResultListener, hi paramhi)
    {
      this.Lw = paramOnRemoveGeofencesResultListener;
      this.Lv = null;
      this.Lx = paramhi;
    }

    public void onAddGeofencesResult(int paramInt, String[] paramArrayOfString)
      throws RemoteException
    {
      if (this.Lx == null)
      {
        Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
        return;
      }
      hi localhi1 = this.Lx;
      hi localhi2 = this.Lx;
      localhi2.getClass();
      localhi1.a(new hi.a(localhi2, this.Lv, paramInt, paramArrayOfString));
      this.Lx = null;
      this.Lv = null;
      this.Lw = null;
    }

    public void onRemoveGeofencesByPendingIntentResult(int paramInt, PendingIntent paramPendingIntent)
    {
      if (this.Lx == null)
      {
        Log.wtf("LocationClientImpl", "onRemoveGeofencesByPendingIntentResult called multiple times");
        return;
      }
      hi localhi1 = this.Lx;
      hi localhi2 = this.Lx;
      localhi2.getClass();
      localhi1.a(new hi.d(localhi2, 1, this.Lw, paramInt, paramPendingIntent));
      this.Lx = null;
      this.Lv = null;
      this.Lw = null;
    }

    public void onRemoveGeofencesByRequestIdsResult(int paramInt, String[] paramArrayOfString)
    {
      if (this.Lx == null)
      {
        Log.wtf("LocationClientImpl", "onRemoveGeofencesByRequestIdsResult called multiple times");
        return;
      }
      hi localhi1 = this.Lx;
      hi localhi2 = this.Lx;
      localhi2.getClass();
      localhi1.a(new hi.d(localhi2, 2, this.Lw, paramInt, paramArrayOfString));
      this.Lx = null;
      this.Lv = null;
      this.Lw = null;
    }
  }

  private final class c
    implements hl<hg>
  {
    private c()
    {
    }

    public void bm()
    {
      hi.a(hi.this);
    }

    public hg gm()
    {
      return (hg)hi.b(hi.this);
    }
  }

  private final class d extends eh<hg>.b<LocationClient.OnRemoveGeofencesResultListener>
  {
    private final String[] Lt;
    private final int Ly;
    private final PendingIntent mPendingIntent;
    private final int yJ;

    public d(int paramOnRemoveGeofencesResultListener, LocationClient.OnRemoveGeofencesResultListener paramInt1, int paramPendingIntent, PendingIntent arg5)
    {
      super(paramInt1);
      if (paramOnRemoveGeofencesResultListener == localOnRemoveGeofencesResultListener);
      while (true)
      {
        ed.v(localOnRemoveGeofencesResultListener);
        this.Ly = paramOnRemoveGeofencesResultListener;
        this.yJ = LocationStatusCodes.bl(paramPendingIntent);
        Object localObject;
        this.mPendingIntent = localObject;
        this.Lt = null;
        return;
        int i = 0;
      }
    }

    public d(int paramOnRemoveGeofencesResultListener, LocationClient.OnRemoveGeofencesResultListener paramInt1, int paramArrayOfString, String[] arg5)
    {
      super(paramInt1);
      if (paramOnRemoveGeofencesResultListener == 2);
      for (boolean bool = true; ; bool = false)
      {
        ed.v(bool);
        this.Ly = paramOnRemoveGeofencesResultListener;
        this.yJ = LocationStatusCodes.bl(paramArrayOfString);
        Object localObject;
        this.Lt = localObject;
        this.mPendingIntent = null;
        return;
      }
    }

    protected void a(LocationClient.OnRemoveGeofencesResultListener paramOnRemoveGeofencesResultListener)
    {
      if (paramOnRemoveGeofencesResultListener != null);
      switch (this.Ly)
      {
      default:
        Log.wtf("LocationClientImpl", "Unsupported action: " + this.Ly);
        return;
      case 1:
        paramOnRemoveGeofencesResultListener.onRemoveGeofencesByPendingIntentResult(this.yJ, this.mPendingIntent);
        return;
      case 2:
      }
      paramOnRemoveGeofencesResultListener.onRemoveGeofencesByRequestIdsResult(this.yJ, this.Lt);
    }

    protected void cP()
    {
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hi
 * JD-Core Version:    0.6.0
 */