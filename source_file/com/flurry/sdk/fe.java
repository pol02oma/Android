package com.flurry.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class fe extends BroadcastReceiver
{
  private static fe e;
  boolean a;
  Boolean b;
  private List<WeakReference<fd>> c = new LinkedList();
  private boolean d = false;

  public static fe a()
  {
    monitorenter;
    try
    {
      if (e == null)
        e = new fe();
      fe localfe = e;
      return localfe;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private boolean a(Context paramContext)
  {
    if ((!this.d) || (paramContext == null))
      return true;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }

  public void a(fd paramfd)
  {
    monitorenter;
    if (paramfd == null);
    while (true)
    {
      monitorexit;
      return;
      try
      {
        this.c.add(new WeakReference(paramfd));
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public void b()
  {
    monitorenter;
    try
    {
      Context localContext = eg.a().b();
      if (localContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0);
      for (boolean bool = true; ; bool = false)
      {
        this.d = bool;
        this.a = a(localContext);
        if (this.d)
          d();
        return;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean c()
  {
    if (this.b != null)
      return this.b.booleanValue();
    return this.a;
  }

  void d()
  {
    Context localContext = eg.a().b();
    this.a = a(localContext);
    localContext.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }

  public a e()
  {
    if (!this.d)
      return a.a;
    ConnectivityManager localConnectivityManager = (ConnectivityManager)eg.a().b().getSystemService("connectivity");
    NetworkInfo localNetworkInfo1 = localConnectivityManager.getNetworkInfo(1);
    if ((localNetworkInfo1 != null) && (localNetworkInfo1.isConnected()))
      return a.b;
    NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(0);
    if ((localNetworkInfo2 != null) && (localNetworkInfo2.isConnected()))
      return a.c;
    return a.a;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool = a(paramContext);
    if (this.a != bool)
    {
      this.a = bool;
      Iterator localIterator = new LinkedList(this.c).iterator();
      while (localIterator.hasNext())
      {
        fd localfd = (fd)((WeakReference)localIterator.next()).get();
        if (localfd == null)
          continue;
        localfd.b(this.a);
      }
    }
  }

  public static enum a
  {
    private int d;

    static
    {
      a[] arrayOfa = new a[3];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      arrayOfa[2] = c;
      e = arrayOfa;
    }

    private a(int paramInt)
    {
      this.d = paramInt;
    }

    public int a()
    {
      return this.d;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.fe
 * JD-Core Version:    0.6.0
 */