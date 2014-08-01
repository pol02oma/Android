package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.ArrayList;

public final class ei
{
  private final b BJ;
  private ArrayList<GoogleApiClient.ConnectionCallbacks> BK = new ArrayList();
  final ArrayList<GoogleApiClient.ConnectionCallbacks> BL = new ArrayList();
  private boolean BM = false;
  private ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> BN = new ArrayList();
  private boolean BO = false;
  private final Handler mHandler;

  public ei(Context paramContext, Looper paramLooper, b paramb)
  {
    this.BJ = paramb;
    this.mHandler = new a(paramLooper);
  }

  public void P(int paramInt)
  {
    this.mHandler.removeMessages(1);
    while (true)
    {
      int j;
      synchronized (this.BK)
      {
        this.BM = true;
        ArrayList localArrayList2 = this.BK;
        int i = localArrayList2.size();
        j = 0;
        if ((j < i) && (this.BJ.dC()))
          continue;
        this.BM = false;
        return;
        if (this.BK.contains(localArrayList2.get(j)))
          ((GoogleApiClient.ConnectionCallbacks)localArrayList2.get(j)).onConnectionSuspended(paramInt);
      }
      j++;
    }
  }

  public void a(ConnectionResult paramConnectionResult)
  {
    this.mHandler.removeMessages(1);
    while (true)
    {
      int j;
      synchronized (this.BN)
      {
        this.BO = true;
        ArrayList localArrayList2 = this.BN;
        int i = localArrayList2.size();
        j = 0;
        if (j >= i)
          continue;
        if (!this.BJ.dC())
          return;
        if (this.BN.contains(localArrayList2.get(j)))
        {
          ((GooglePlayServicesClient.OnConnectionFailedListener)localArrayList2.get(j)).onConnectionFailed(paramConnectionResult);
          break label107;
          this.BO = false;
          return;
        }
      }
      label107: j++;
    }
  }

  public void b(Bundle paramBundle)
  {
    boolean bool1 = true;
    while (true)
    {
      int j;
      synchronized (this.BK)
      {
        if (!this.BM)
        {
          bool2 = bool1;
          er.v(bool2);
          this.mHandler.removeMessages(1);
          this.BM = true;
          if (this.BL.size() != 0)
            break label170;
          er.v(bool1);
          ArrayList localArrayList2 = this.BK;
          int i = localArrayList2.size();
          j = 0;
          if ((j < i) && (this.BJ.dC()) && (this.BJ.isConnected()))
            continue;
          this.BL.clear();
          this.BM = false;
          return;
          this.BL.size();
          if (this.BL.contains(localArrayList2.get(j)))
            break label175;
          ((GoogleApiClient.ConnectionCallbacks)localArrayList2.get(j)).onConnected(paramBundle);
        }
      }
      boolean bool2 = false;
      continue;
      label170: bool1 = false;
      continue;
      label175: j++;
    }
  }

  protected void bo()
  {
    synchronized (this.BK)
    {
      b(this.BJ.cY());
      return;
    }
  }

  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    er.f(paramConnectionCallbacks);
    synchronized (this.BK)
    {
      boolean bool = this.BK.contains(paramConnectionCallbacks);
      return bool;
    }
  }

  public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    er.f(paramOnConnectionFailedListener);
    synchronized (this.BN)
    {
      boolean bool = this.BN.contains(paramOnConnectionFailedListener);
      return bool;
    }
  }

  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    er.f(paramConnectionCallbacks);
    synchronized (this.BK)
    {
      if (this.BK.contains(paramConnectionCallbacks))
      {
        Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + paramConnectionCallbacks + " is already registered");
        if (this.BJ.isConnected())
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramConnectionCallbacks));
        return;
      }
      if (this.BM)
        this.BK = new ArrayList(this.BK);
      this.BK.add(paramConnectionCallbacks);
    }
  }

  public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    er.f(paramOnConnectionFailedListener);
    synchronized (this.BN)
    {
      if (this.BN.contains(paramOnConnectionFailedListener))
      {
        Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " is already registered");
        return;
      }
      if (this.BO)
        this.BN = new ArrayList(this.BN);
      this.BN.add(paramOnConnectionFailedListener);
    }
  }

  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    er.f(paramConnectionCallbacks);
    synchronized (this.BK)
    {
      if (this.BK != null)
      {
        if (this.BM)
          this.BK = new ArrayList(this.BK);
        if (this.BK.remove(paramConnectionCallbacks))
          break label85;
        Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + paramConnectionCallbacks + " not found");
      }
      label85: 
      do
        return;
      while ((!this.BM) || (this.BL.contains(paramConnectionCallbacks)));
      this.BL.add(paramConnectionCallbacks);
    }
  }

  public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    er.f(paramOnConnectionFailedListener);
    synchronized (this.BN)
    {
      if (this.BN != null)
      {
        if (this.BO)
          this.BN = new ArrayList(this.BN);
        if (!this.BN.remove(paramOnConnectionFailedListener))
          Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " not found");
      }
      return;
    }
  }

  final class a extends Handler
  {
    public a(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1)
        synchronized (ei.a(ei.this))
        {
          if ((ei.b(ei.this).dC()) && (ei.b(ei.this).isConnected()) && (ei.a(ei.this).contains(paramMessage.obj)))
          {
            Bundle localBundle = ei.b(ei.this).cY();
            ((GoogleApiClient.ConnectionCallbacks)paramMessage.obj).onConnected(localBundle);
          }
          return;
        }
      Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
    }
  }

  public static abstract interface b
  {
    public abstract Bundle cY();

    public abstract boolean dC();

    public abstract boolean isConnected();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ei
 * JD-Core Version:    0.6.0
 */