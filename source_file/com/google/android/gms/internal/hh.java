package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.a.a;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class hh
{
  private final hl<hg> Lk;
  private ContentProviderClient Ll = null;
  private boolean Lm = false;
  private HashMap<LocationListener, b> Ln = new HashMap();
  private final Context mContext;

  public hh(Context paramContext, hl<hg> paramhl)
  {
    this.mContext = paramContext;
    this.Lk = paramhl;
  }

  public Location getLastLocation()
  {
    this.Lk.bm();
    try
    {
      Location localLocation = ((hg)this.Lk.eb()).aF(this.mContext.getPackageName());
      return localLocation;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  public void gl()
  {
    if (this.Lm)
      setMockMode(false);
  }

  public void removeAllListeners()
  {
    try
    {
      synchronized (this.Ln)
      {
        Iterator localIterator = this.Ln.values().iterator();
        while (localIterator.hasNext())
        {
          b localb = (b)localIterator.next();
          if (localb == null)
            continue;
          ((hg)this.Lk.eb()).a(localb);
        }
      }
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
    this.Ln.clear();
    monitorexit;
  }

  public void removeLocationUpdates(PendingIntent paramPendingIntent)
  {
    this.Lk.bm();
    try
    {
      ((hg)this.Lk.eb()).a(paramPendingIntent);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  public void removeLocationUpdates(LocationListener paramLocationListener)
  {
    this.Lk.bm();
    er.b(paramLocationListener, "Invalid null listener");
    synchronized (this.Ln)
    {
      b localb = (b)this.Ln.remove(paramLocationListener);
      if ((this.Ll != null) && (this.Ln.isEmpty()))
      {
        this.Ll.release();
        this.Ll = null;
      }
      if (localb != null)
        localb.release();
      try
      {
        ((hg)this.Lk.eb()).a(localb);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new IllegalStateException(localRemoteException);
      }
    }
  }

  public void requestLocationUpdates(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
  {
    this.Lk.bm();
    try
    {
      ((hg)this.Lk.eb()).a(paramLocationRequest, paramPendingIntent);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  public void requestLocationUpdates(LocationRequest paramLocationRequest, LocationListener paramLocationListener, Looper paramLooper)
  {
    this.Lk.bm();
    if (paramLooper == null)
      er.b(Looper.myLooper(), "Can't create handler inside thread that has not called Looper.prepare()");
    while (true)
    {
      b localb1;
      synchronized (this.Ln)
      {
        localb1 = (b)this.Ln.get(paramLocationListener);
        if (localb1 == null)
        {
          localb2 = new b(paramLocationListener, paramLooper);
          this.Ln.put(paramLocationListener, localb2);
          try
          {
            ((hg)this.Lk.eb()).a(paramLocationRequest, localb2, this.mContext.getPackageName());
            return;
          }
          catch (RemoteException localRemoteException)
          {
            throw new IllegalStateException(localRemoteException);
          }
        }
      }
      b localb2 = localb1;
    }
  }

  public void setMockLocation(Location paramLocation)
  {
    this.Lk.bm();
    try
    {
      ((hg)this.Lk.eb()).setMockLocation(paramLocation);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  public void setMockMode(boolean paramBoolean)
  {
    this.Lk.bm();
    try
    {
      ((hg)this.Lk.eb()).setMockMode(paramBoolean);
      this.Lm = paramBoolean;
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  private static class a extends Handler
  {
    private final LocationListener Lo;

    public a(LocationListener paramLocationListener)
    {
      this.Lo = paramLocationListener;
    }

    public a(LocationListener paramLocationListener, Looper paramLooper)
    {
      super();
      this.Lo = paramLocationListener;
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
        return;
      case 1:
      }
      Location localLocation = new Location((Location)paramMessage.obj);
      this.Lo.onLocationChanged(localLocation);
    }
  }

  private static class b extends a.a
  {
    private Handler Lp;

    b(LocationListener paramLocationListener, Looper paramLooper)
    {
      if (paramLooper == null);
      for (hh.a locala = new hh.a(paramLocationListener); ; locala = new hh.a(paramLocationListener, paramLooper))
      {
        this.Lp = locala;
        return;
      }
    }

    public void onLocationChanged(Location paramLocation)
    {
      if (this.Lp == null)
      {
        Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
        return;
      }
      Message localMessage = Message.obtain();
      localMessage.what = 1;
      localMessage.obj = paramLocation;
      this.Lp.sendMessage(localMessage);
    }

    public void release()
    {
      this.Lp = null;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hh
 * JD-Core Version:    0.6.0
 */