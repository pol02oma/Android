package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;

public abstract class cb extends ct
{
  private final cd mf;
  private final ca.a nY;

  public cb(cd paramcd, ca.a parama)
  {
    this.mf = paramcd;
    this.nY = parama;
  }

  private static cf a(ch paramch, cd paramcd)
  {
    try
    {
      cf localcf = paramch.b(paramcd);
      return localcf;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not fetch ad response from ad request service.", localRemoteException);
    }
    return null;
  }

  public final void aB()
  {
    try
    {
      ch localch = aE();
      cf localcf;
      if (localch == null)
        localcf = new cf(0);
      while (true)
      {
        aD();
        this.nY.a(localcf);
        return;
        localcf = a(localch, this.mf);
        if (localcf != null)
          continue;
        localcf = new cf(0);
      }
    }
    finally
    {
      aD();
    }
    throw localObject;
  }

  public abstract void aD();

  public abstract ch aE();

  public final void onStop()
  {
    aD();
  }

  public static final class a extends cb
  {
    private final Context mContext;

    public a(Context paramContext, cd paramcd, ca.a parama)
    {
      super(parama);
      this.mContext = paramContext;
    }

    public void aD()
    {
    }

    public ch aE()
    {
      return ci.a(this.mContext, new av());
    }
  }

  public static final class b extends cb
    implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener
  {
    private final Object mg = new Object();
    private final ca.a nY;
    private final cc nZ;

    public b(Context paramContext, cd paramcd, ca.a parama)
    {
      super(parama);
      this.nY = parama;
      this.nZ = new cc(paramContext, this, this, paramcd.kN.pW);
      this.nZ.connect();
    }

    public void aD()
    {
      synchronized (this.mg)
      {
        if ((this.nZ.isConnected()) || (this.nZ.isConnecting()))
          this.nZ.disconnect();
        return;
      }
    }

    public ch aE()
    {
      synchronized (this.mg)
      {
        try
        {
          ch localch = this.nZ.aH();
          return localch;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          return null;
        }
      }
    }

    public void onConnected(Bundle paramBundle)
    {
      start();
    }

    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      this.nY.a(new cf(0));
    }

    public void onDisconnected()
    {
      da.s("Disconnected from remote ad request service.");
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cb
 * JD-Core Version:    0.6.0
 */