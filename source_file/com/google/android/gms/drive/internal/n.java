package com.google.android.gms.drive.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.DriveEvent.Listener;
import com.google.android.gms.drive.events.c;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.eh;
import com.google.android.gms.internal.eh.e;
import com.google.android.gms.internal.en;
import com.google.android.gms.internal.er;
import java.util.HashMap;
import java.util.Map;

public class n extends eh<u>
{
  private DriveId DA;
  final GoogleApiClient.ConnectionCallbacks DB;
  Map<DriveId, Map<DriveEvent.Listener<?>, s<?>>> DC = new HashMap();
  private DriveId Dz;
  private final String vi;

  public n(Context paramContext, Looper paramLooper, ee paramee, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String[] paramArrayOfString)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramArrayOfString);
    this.vi = ((String)er.b(paramee.dR(), "Must call Api.ClientBuilder.setAccountName()"));
    this.DB = paramConnectionCallbacks;
  }

  protected u C(IBinder paramIBinder)
  {
    return u.a.D(paramIBinder);
  }

  <C extends DriveEvent> PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId, int paramInt, DriveEvent.Listener<C> paramListener)
  {
    er.b(c.a(paramInt, paramDriveId), "id");
    er.b(paramListener, "listener");
    er.a(isConnected(), "Client must be connected");
    synchronized (this.DC)
    {
      Object localObject2 = (Map)this.DC.get(paramDriveId);
      if (localObject2 == null)
      {
        localObject2 = new HashMap();
        this.DC.put(paramDriveId, localObject2);
      }
      if (((Map)localObject2).containsKey(paramListener))
      {
        l.k localk = new l.k(Status.zQ);
        return localk;
      }
      s locals = new s(getLooper(), paramInt, paramListener);
      ((Map)localObject2).put(paramListener, locals);
      a.a locala = paramGoogleApiClient.b(new l.j(paramDriveId, paramInt, locals)
      {
        protected void a(n paramn)
          throws RemoteException
        {
          paramn.eT().a(new AddEventListenerRequest(this.DD, this.DE), this.DF, null, new ak(this));
        }
      });
      return locala;
    }
  }

  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      this.Dz = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.root_id"));
      this.DA = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.appdata_id"));
    }
    super.a(paramInt, paramIBinder, paramBundle);
  }

  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    String str = getContext().getPackageName();
    er.f(parame);
    er.f(str);
    er.f(ea());
    paramen.a(parame, 4323000, str, ea(), this.vi, new Bundle());
  }

  protected String aF()
  {
    return "com.google.android.gms.drive.ApiService.START";
  }

  protected String aG()
  {
    return "com.google.android.gms.drive.internal.IDriveService";
  }

  PendingResult<Status> b(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId, int paramInt, DriveEvent.Listener<?> paramListener)
  {
    er.b(c.a(paramInt, paramDriveId), "id");
    er.b(paramListener, "listener");
    er.a(isConnected(), "Client must be connected");
    Map localMap2;
    s locals;
    synchronized (this.DC)
    {
      localMap2 = (Map)this.DC.get(paramDriveId);
      if (localMap2 == null)
      {
        l.k localk1 = new l.k(Status.zQ);
        return localk1;
      }
      locals = (s)localMap2.remove(paramListener);
      if (locals == null)
      {
        l.k localk2 = new l.k(Status.zQ);
        return localk2;
      }
    }
    if (localMap2.isEmpty())
      this.DC.remove(paramDriveId);
    a.a locala = paramGoogleApiClient.b(new l.j(paramDriveId, paramInt, locals)
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new RemoveEventListenerRequest(this.DD, this.DE), this.DF, null, new ak(this));
      }
    });
    monitorexit;
    return locala;
  }

  public void disconnect()
  {
    u localu = (u)eb();
    if (localu != null);
    try
    {
      localu.a(new DisconnectRequest());
      label25: super.disconnect();
      this.DC.clear();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      break label25;
    }
  }

  public u eT()
  {
    return (u)eb();
  }

  public DriveId eU()
  {
    return this.Dz;
  }

  public DriveId eV()
  {
    return this.DA;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.n
 * JD-Core Version:    0.6.0
 */