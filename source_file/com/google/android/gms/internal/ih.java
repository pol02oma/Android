package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;
import com.google.android.gms.panorama.Panorama.PanoramaResult;
import com.google.android.gms.panorama.Panorama.a;

public class ih extends eh<ig>
{
  public ih(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, (String[])null);
  }

  @Deprecated
  public ih(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext, paramContext.getMainLooper(), new eh.c(paramConnectionCallbacks), new eh.g(paramOnConnectionFailedListener));
  }

  public void a(a.c<Panorama.PanoramaResult> paramc, Uri paramUri, boolean paramBoolean)
  {
    if (paramBoolean);
    for (Uri localUri = paramUri; ; localUri = null)
    {
      a(new b(null, paramc, localUri), paramUri, null, paramBoolean);
      return;
    }
  }

  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    Bundle localBundle = new Bundle();
    paramen.a(parame, 4323000, getContext().getPackageName(), localBundle);
  }

  public void a(b paramb, Uri paramUri, Bundle paramBundle, boolean paramBoolean)
  {
    bm();
    if (paramBoolean)
      getContext().grantUriPermission("com.google.android.gms", paramUri, 1);
    try
    {
      ((ig)eb()).a(paramb, paramUri, paramBundle, paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      paramb.a(8, null, 0, null);
    }
  }

  protected String aF()
  {
    return "com.google.android.gms.panorama.service.START";
  }

  protected String aG()
  {
    return "com.google.android.gms.panorama.internal.IPanoramaService";
  }

  public ig ax(IBinder paramIBinder)
  {
    return ig.a.aw(paramIBinder);
  }

  final class a extends eh<ig>.b<a.c<Panorama.a>>
    implements Panorama.a
  {
    public final Status QE;
    public final Intent QF;
    public final int type;

    public a(Status paramInt, int paramIntent, Intent arg4)
    {
      super(paramInt);
      this.QE = paramIntent;
      int i;
      this.type = i;
      Object localObject;
      this.QF = localObject;
    }

    protected void c(a.c<Panorama.a> paramc)
    {
      paramc.b(this);
    }

    protected void cP()
    {
    }

    public Status getStatus()
    {
      return this.QE;
    }

    public Intent getViewerIntent()
    {
      return this.QF;
    }
  }

  final class b extends if.a
  {
    private final a.c<Panorama.a> QH;
    private final a.c<Panorama.PanoramaResult> QI;
    private final Uri QJ;

    public b(a.c<Panorama.PanoramaResult> paramUri, Uri arg3)
    {
      this.QH = paramUri;
      Object localObject1;
      this.QI = localObject1;
      Object localObject2;
      this.QJ = localObject2;
    }

    public void a(int paramInt1, Bundle paramBundle, int paramInt2, Intent paramIntent)
    {
      if (this.QJ != null)
        ih.this.getContext().revokeUriPermission(this.QJ, 1);
      if (paramBundle != null);
      for (PendingIntent localPendingIntent = (PendingIntent)paramBundle.getParcelable("pendingIntent"); ; localPendingIntent = null)
      {
        Status localStatus = new Status(paramInt1, null, localPendingIntent);
        if (this.QI != null)
          ih.this.a(new ih.c(ih.this, this.QI, localStatus, paramIntent));
        do
          return;
        while (this.QH == null);
        ih.this.a(new ih.a(ih.this, this.QH, localStatus, paramInt2, paramIntent));
        return;
      }
    }
  }

  final class c extends eh<ig>.b<a.c<Panorama.PanoramaResult>>
    implements Panorama.PanoramaResult
  {
    private final Status QE;
    private final Intent QF;

    public c(Status paramIntent, Intent arg3)
    {
      super(paramIntent);
      Object localObject1;
      this.QE = localObject1;
      Object localObject2;
      this.QF = localObject2;
    }

    protected void c(a.c<Panorama.PanoramaResult> paramc)
    {
      paramc.b(this);
    }

    protected void cP()
    {
    }

    public Status getStatus()
    {
      return this.QE;
    }

    public Intent getViewerIntent()
    {
      return this.QF;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ih
 * JD-Core Version:    0.6.0
 */