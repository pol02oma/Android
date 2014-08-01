package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api.a;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

public abstract class eh<T extends IInterface>
  implements GooglePlayServicesClient, Api.a, ei.b
{
  public static final String[] BB = { "service_esmobile", "service_googleme" };
  boolean BA = false;
  private T Bv;
  private final ArrayList<eh<T>.b<?>> Bw = new ArrayList();
  private eh<T>.f Bx;
  private volatile int By = 1;
  private final String[] Bz;
  private final Context mContext;
  final Handler mHandler;
  private final Looper zs;
  private final ei zx;

  protected eh(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String[] paramArrayOfString)
  {
    this.mContext = ((Context)er.f(paramContext));
    this.zs = ((Looper)er.b(paramLooper, "Looper must not be null"));
    this.zx = new ei(paramContext, paramLooper, this);
    this.mHandler = new a(paramLooper);
    b(paramArrayOfString);
    this.Bz = paramArrayOfString;
    registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)er.f(paramConnectionCallbacks));
    registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)er.f(paramOnConnectionFailedListener));
  }

  protected eh(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener, String[] paramArrayOfString)
  {
    this(paramContext, paramContext.getMainLooper(), new c(paramConnectionCallbacks), new g(paramOnConnectionFailedListener), paramArrayOfString);
  }

  public void O(int paramInt)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(4, Integer.valueOf(paramInt)));
  }

  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(1, new h(paramInt, paramIBinder, paramBundle)));
  }

  public final void a(eh<T>.b<?> parameh)
  {
    synchronized (this.Bw)
    {
      this.Bw.add(parameh);
      this.mHandler.sendMessage(this.mHandler.obtainMessage(2, parameh));
      return;
    }
  }

  protected abstract void a(en paramen, e parame)
    throws RemoteException;

  protected abstract String aF();

  protected abstract String aG();

  protected void b(String[] paramArrayOfString)
  {
  }

  protected final void bm()
  {
    if (!isConnected())
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
  }

  public Bundle cY()
  {
    return null;
  }

  public void connect()
  {
    this.BA = true;
    this.By = 2;
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
    if (i != 0)
    {
      this.By = 1;
      this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(i)));
    }
    do
    {
      return;
      if (this.Bx != null)
      {
        Log.e("GmsClient", "Calling connect() while still connected, missing disconnect().");
        this.Bv = null;
        ej.y(this.mContext).b(aF(), this.Bx);
      }
      this.Bx = new f();
    }
    while (ej.y(this.mContext).a(aF(), this.Bx));
    Log.e("GmsClient", "unable to connect to service: " + aF());
    this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(9)));
  }

  public boolean dC()
  {
    return this.BA;
  }

  public void disconnect()
  {
    this.BA = false;
    synchronized (this.Bw)
    {
      int i = this.Bw.size();
      for (int j = 0; j < i; j++)
        ((b)this.Bw.get(j)).ed();
      this.Bw.clear();
      this.By = 1;
      this.Bv = null;
      if (this.Bx != null)
      {
        ej.y(this.mContext).b(aF(), this.Bx);
        this.Bx = null;
      }
      return;
    }
  }

  public final String[] ea()
  {
    return this.Bz;
  }

  protected final T eb()
  {
    bm();
    return this.Bv;
  }

  public final Context getContext()
  {
    return this.mContext;
  }

  public final Looper getLooper()
  {
    return this.zs;
  }

  public boolean isConnected()
  {
    return this.By == 3;
  }

  public boolean isConnecting()
  {
    return this.By == 2;
  }

  public boolean isConnectionCallbacksRegistered(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.zx.isConnectionCallbacksRegistered(new c(paramConnectionCallbacks));
  }

  public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.zx.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }

  protected abstract T p(IBinder paramIBinder);

  public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zx.registerConnectionCallbacks(new c(paramConnectionCallbacks));
  }

  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zx.registerConnectionCallbacks(paramConnectionCallbacks);
  }

  public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zx.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }

  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zx.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }

  public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zx.unregisterConnectionCallbacks(new c(paramConnectionCallbacks));
  }

  public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zx.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }

  protected final void x(IBinder paramIBinder)
  {
    try
    {
      a(en.a.z(paramIBinder), new e(this));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("GmsClient", "service died");
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
      if ((paramMessage.what == 1) && (!eh.this.isConnecting()))
      {
        eh.b localb2 = (eh.b)paramMessage.obj;
        localb2.cP();
        localb2.unregister();
        return;
      }
      if (paramMessage.what == 3)
      {
        eh.a(eh.this).a(new ConnectionResult(((Integer)paramMessage.obj).intValue(), null));
        return;
      }
      if (paramMessage.what == 4)
      {
        eh.a(eh.this, 1);
        eh.a(eh.this, null);
        eh.a(eh.this).P(((Integer)paramMessage.obj).intValue());
        return;
      }
      if ((paramMessage.what == 2) && (!eh.this.isConnected()))
      {
        eh.b localb1 = (eh.b)paramMessage.obj;
        localb1.cP();
        localb1.unregister();
        return;
      }
      if ((paramMessage.what == 2) || (paramMessage.what == 1))
      {
        ((eh.b)paramMessage.obj).ec();
        return;
      }
      Log.wtf("GmsClient", "Don't know how to handle this message.");
    }
  }

  protected abstract class b<TListener>
  {
    private boolean BD;
    private TListener mListener;

    public b()
    {
      Object localObject;
      this.mListener = localObject;
      this.BD = false;
    }

    protected abstract void a(TListener paramTListener);

    protected abstract void cP();

    // ERROR //
    public void ec()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 21	com/google/android/gms/internal/eh$b:mListener	Ljava/lang/Object;
      //   6: astore_2
      //   7: aload_0
      //   8: getfield 23	com/google/android/gms/internal/eh$b:BD	Z
      //   11: ifeq +33 -> 44
      //   14: ldc 31
      //   16: new 33	java/lang/StringBuilder
      //   19: dup
      //   20: invokespecial 34	java/lang/StringBuilder:<init>	()V
      //   23: ldc 36
      //   25: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   28: aload_0
      //   29: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   32: ldc 45
      //   34: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   37: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   40: invokestatic 55	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   43: pop
      //   44: aload_0
      //   45: monitorexit
      //   46: aload_2
      //   47: ifnull +36 -> 83
      //   50: aload_0
      //   51: aload_2
      //   52: invokevirtual 57	com/google/android/gms/internal/eh$b:a	(Ljava/lang/Object;)V
      //   55: aload_0
      //   56: monitorenter
      //   57: aload_0
      //   58: iconst_1
      //   59: putfield 23	com/google/android/gms/internal/eh$b:BD	Z
      //   62: aload_0
      //   63: monitorexit
      //   64: aload_0
      //   65: invokevirtual 60	com/google/android/gms/internal/eh$b:unregister	()V
      //   68: return
      //   69: astore_1
      //   70: aload_0
      //   71: monitorexit
      //   72: aload_1
      //   73: athrow
      //   74: astore 4
      //   76: aload_0
      //   77: invokevirtual 62	com/google/android/gms/internal/eh$b:cP	()V
      //   80: aload 4
      //   82: athrow
      //   83: aload_0
      //   84: invokevirtual 62	com/google/android/gms/internal/eh$b:cP	()V
      //   87: goto -32 -> 55
      //   90: astore_3
      //   91: aload_0
      //   92: monitorexit
      //   93: aload_3
      //   94: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   2	44	69	finally
      //   44	46	69	finally
      //   70	72	69	finally
      //   50	55	74	java/lang/RuntimeException
      //   57	64	90	finally
      //   91	93	90	finally
    }

    public void ed()
    {
      monitorenter;
      try
      {
        this.mListener = null;
        return;
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public void unregister()
    {
      ed();
      synchronized (eh.b(eh.this))
      {
        eh.b(eh.this).remove(this);
        return;
      }
    }
  }

  public static final class c
    implements GoogleApiClient.ConnectionCallbacks
  {
    private final GooglePlayServicesClient.ConnectionCallbacks BE;

    public c(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
    {
      this.BE = paramConnectionCallbacks;
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof c))
        return this.BE.equals(((c)paramObject).BE);
      return this.BE.equals(paramObject);
    }

    public void onConnected(Bundle paramBundle)
    {
      this.BE.onConnected(paramBundle);
    }

    public void onConnectionSuspended(int paramInt)
    {
      this.BE.onDisconnected();
    }
  }

  public abstract class d<TListener> extends eh<T>.b<TListener>
  {
    private final DataHolder zU;

    public d(DataHolder arg2)
    {
      super(localObject1);
      Object localObject2;
      this.zU = localObject2;
    }

    protected final void a(TListener paramTListener)
    {
      a(paramTListener, this.zU);
    }

    protected abstract void a(TListener paramTListener, DataHolder paramDataHolder);

    protected void cP()
    {
      if (this.zU != null)
        this.zU.close();
    }
  }

  public static final class e extends em.a
  {
    private eh BF;

    public e(eh parameh)
    {
      this.BF = parameh;
    }

    public void b(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      er.b("onPostInitComplete can be called only once per call to getServiceFromBroker", this.BF);
      this.BF.a(paramInt, paramIBinder, paramBundle);
      this.BF = null;
    }
  }

  final class f
    implements ServiceConnection
  {
    f()
    {
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      eh.this.x(paramIBinder);
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      eh.this.mHandler.sendMessage(eh.this.mHandler.obtainMessage(4, Integer.valueOf(1)));
    }
  }

  public static final class g
    implements GoogleApiClient.OnConnectionFailedListener
  {
    private final GooglePlayServicesClient.OnConnectionFailedListener BG;

    public g(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this.BG = paramOnConnectionFailedListener;
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof g))
        return this.BG.equals(((g)paramObject).BG);
      return this.BG.equals(paramObject);
    }

    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      this.BG.onConnectionFailed(paramConnectionResult);
    }
  }

  protected final class h extends eh<T>.b<Boolean>
  {
    public final Bundle BH;
    public final IBinder BI;
    public final int statusCode;

    public h(int paramIBinder, IBinder paramBundle, Bundle arg4)
    {
      super(Boolean.valueOf(true));
      this.statusCode = paramIBinder;
      this.BI = paramBundle;
      Object localObject;
      this.BH = localObject;
    }

    protected void b(Boolean paramBoolean)
    {
      if (paramBoolean == null)
      {
        eh.a(eh.this, 1);
        return;
      }
      switch (this.statusCode)
      {
      default:
        if (this.BH == null)
          break;
      case 0:
      case 10:
      }
      for (PendingIntent localPendingIntent = (PendingIntent)this.BH.getParcelable("pendingIntent"); ; localPendingIntent = null)
      {
        if (eh.d(eh.this) != null)
        {
          ej.y(eh.e(eh.this)).b(eh.this.aF(), eh.d(eh.this));
          eh.a(eh.this, null);
        }
        eh.a(eh.this, 1);
        eh.a(eh.this, null);
        eh.a(eh.this).a(new ConnectionResult(this.statusCode, localPendingIntent));
        return;
        try
        {
          String str = this.BI.getInterfaceDescriptor();
          if (eh.this.aG().equals(str))
          {
            eh.a(eh.this, eh.this.p(this.BI));
            if (eh.c(eh.this) != null)
            {
              eh.a(eh.this, 3);
              eh.a(eh.this).bo();
              return;
            }
          }
        }
        catch (RemoteException localRemoteException)
        {
          ej.y(eh.e(eh.this)).b(eh.this.aF(), eh.d(eh.this));
          eh.a(eh.this, null);
          eh.a(eh.this, 1);
          eh.a(eh.this, null);
          eh.a(eh.this).a(new ConnectionResult(8, null));
          return;
        }
        eh.a(eh.this, 1);
        throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
      }
    }

    protected void cP()
    {
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.eh
 * JD-Core Version:    0.6.0
 */