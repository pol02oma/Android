package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.eh;
import com.google.android.gms.internal.eh.b;
import com.google.android.gms.internal.eh.c;
import com.google.android.gms.internal.eh.d;
import com.google.android.gms.internal.eh.e;
import com.google.android.gms.internal.eh.g;
import com.google.android.gms.internal.en;
import com.google.android.gms.internal.fh;
import com.google.android.gms.internal.io;
import com.google.android.gms.internal.ir;
import com.google.android.gms.plus.Moments.LoadMomentsResult;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class e extends eh<d>
  implements GooglePlayServicesClient
{
  private Person Rd;
  private final h Re;

  public e(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, h paramh)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramh.hq());
    this.Re = paramh;
  }

  @Deprecated
  public e(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener, h paramh)
  {
    this(paramContext, paramContext.getMainLooper(), new eh.c(paramConnectionCallbacks), new eh.g(paramOnConnectionFailedListener), paramh);
  }

  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    if ((paramInt == 0) && (paramBundle != null) && (paramBundle.containsKey("loaded_person")))
      this.Rd = ir.i(paramBundle.getByteArray("loaded_person"));
    super.a(paramInt, paramIBinder, paramBundle);
  }

  public void a(a.c<People.LoadPeopleResult> paramc, int paramInt, String paramString)
  {
    bm();
    e locale = new e(paramc);
    try
    {
      ((d)eb()).a(locale, 1, paramInt, -1, paramString);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      locale.a(DataHolder.empty(8), null);
    }
  }

  public void a(a.c<Moments.LoadMomentsResult> paramc, int paramInt, String paramString1, Uri paramUri, String paramString2, String paramString3)
  {
    bm();
    b localb;
    if (paramc != null)
      localb = new b(paramc);
    try
    {
      while (true)
      {
        ((d)eb()).a(localb, paramInt, paramString1, paramUri, paramString2, paramString3);
        return;
        localb = null;
      }
    }
    catch (RemoteException localRemoteException)
    {
      localb.a(DataHolder.empty(8), null, null);
    }
  }

  public void a(a.c<Status> paramc, Moment paramMoment)
  {
    bm();
    a locala;
    if (paramc != null)
      locala = new a(paramc);
    try
    {
      while (true)
      {
        fh localfh = fh.a((io)paramMoment);
        ((d)eb()).a(locala, localfh);
        return;
        locala = null;
      }
    }
    catch (RemoteException localRemoteException)
    {
      if (locala == null)
        throw new IllegalStateException(localRemoteException);
      locala.L(new Status(8, null, null));
    }
  }

  public void a(a.c<People.LoadPeopleResult> paramc, Collection<String> paramCollection)
  {
    bm();
    e locale = new e(paramc);
    try
    {
      ((d)eb()).a(locale, new ArrayList(paramCollection));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      locale.a(DataHolder.empty(8), null);
    }
  }

  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    Bundle localBundle = this.Re.hy();
    localBundle.putStringArray("request_visible_actions", this.Re.hr());
    paramen.a(parame, 4323000, this.Re.hu(), this.Re.ht(), ea(), this.Re.getAccountName(), localBundle);
  }

  protected d aB(IBinder paramIBinder)
  {
    return d.a.aA(paramIBinder);
  }

  protected String aF()
  {
    return "com.google.android.gms.plus.service.START";
  }

  protected String aG()
  {
    return "com.google.android.gms.plus.internal.IPlusService";
  }

  public boolean aR(String paramString)
  {
    return Arrays.asList(ea()).contains(paramString);
  }

  public void c(a.c<People.LoadPeopleResult> paramc, String[] paramArrayOfString)
  {
    a(paramc, Arrays.asList(paramArrayOfString));
  }

  public void clearDefaultAccount()
  {
    bm();
    try
    {
      this.Rd = null;
      ((d)eb()).clearDefaultAccount();
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  public String getAccountName()
  {
    bm();
    try
    {
      String str = ((d)eb()).getAccountName();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  public Person getCurrentPerson()
  {
    bm();
    return this.Rd;
  }

  public void i(a.c<Moments.LoadMomentsResult> paramc)
  {
    a(paramc, 20, null, null, null, "me");
  }

  public void i(a.c<People.LoadPeopleResult> paramc, String paramString)
  {
    a(paramc, 0, paramString);
  }

  public void j(a.c<People.LoadPeopleResult> paramc)
  {
    bm();
    e locale = new e(paramc);
    try
    {
      ((d)eb()).a(locale, 2, 1, -1, null);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      locale.a(DataHolder.empty(8), null);
    }
  }

  public void k(a.c<Status> paramc)
  {
    bm();
    clearDefaultAccount();
    g localg = new g(paramc);
    try
    {
      ((d)eb()).b(localg);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localg.d(8, null);
    }
  }

  public void removeMoment(String paramString)
  {
    bm();
    try
    {
      ((d)eb()).removeMoment(paramString);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new IllegalStateException(localRemoteException);
  }

  final class a extends a
  {
    private final a.c<Status> QI;

    public a()
    {
      Object localObject;
      this.QI = localObject;
    }

    public void L(Status paramStatus)
    {
      e.this.a(new e.d(e.this, this.QI, paramStatus));
    }
  }

  final class b extends a
  {
    private final a.c<Moments.LoadMomentsResult> QI;

    public b()
    {
      Object localObject;
      this.QI = localObject;
    }

    public void a(DataHolder paramDataHolder, String paramString1, String paramString2)
    {
      if (paramDataHolder.getMetadata() != null);
      for (PendingIntent localPendingIntent = (PendingIntent)paramDataHolder.getMetadata().getParcelable("pendingIntent"); ; localPendingIntent = null)
      {
        Status localStatus = new Status(paramDataHolder.getStatusCode(), null, localPendingIntent);
        if ((!localStatus.isSuccess()) && (paramDataHolder != null))
          if (!paramDataHolder.isClosed())
            paramDataHolder.close();
        for (DataHolder localDataHolder = null; ; localDataHolder = paramDataHolder)
        {
          e.this.a(new e.c(e.this, this.QI, localStatus, localDataHolder, paramString1, paramString2));
          return;
        }
      }
    }
  }

  final class c extends eh<d>.d<a.c<Moments.LoadMomentsResult>>
    implements Moments.LoadMomentsResult
  {
    private final String Dh;
    private final String Rg;
    private MomentBuffer Rh;
    private final Status vl;

    public c(Status paramDataHolder, DataHolder paramString1, String paramString2, String arg5)
    {
      super(paramDataHolder, paramString2);
      this.vl = paramString1;
      Object localObject1;
      this.Dh = localObject1;
      Object localObject2;
      this.Rg = localObject2;
    }

    protected void a(a.c<Moments.LoadMomentsResult> paramc, DataHolder paramDataHolder)
    {
      if (paramDataHolder != null);
      for (MomentBuffer localMomentBuffer = new MomentBuffer(paramDataHolder); ; localMomentBuffer = null)
      {
        this.Rh = localMomentBuffer;
        paramc.b(this);
        return;
      }
    }

    public MomentBuffer getMomentBuffer()
    {
      return this.Rh;
    }

    public String getNextPageToken()
    {
      return this.Dh;
    }

    public Status getStatus()
    {
      return this.vl;
    }

    public String getUpdated()
    {
      return this.Rg;
    }

    public void release()
    {
      if (this.Rh != null)
        this.Rh.close();
    }
  }

  final class d extends eh<d>.b<a.c<Status>>
  {
    private final Status vl;

    public d(Status arg2)
    {
      super(localObject1);
      Object localObject2;
      this.vl = localObject2;
    }

    protected void c(a.c<Status> paramc)
    {
      if (paramc != null)
        paramc.b(this.vl);
    }

    protected void cP()
    {
    }
  }

  final class e extends a
  {
    private final a.c<People.LoadPeopleResult> QI;

    public e()
    {
      Object localObject;
      this.QI = localObject;
    }

    public void a(DataHolder paramDataHolder, String paramString)
    {
      if (paramDataHolder.getMetadata() != null);
      for (PendingIntent localPendingIntent = (PendingIntent)paramDataHolder.getMetadata().getParcelable("pendingIntent"); ; localPendingIntent = null)
      {
        Status localStatus = new Status(paramDataHolder.getStatusCode(), null, localPendingIntent);
        if ((!localStatus.isSuccess()) && (paramDataHolder != null))
          if (!paramDataHolder.isClosed())
            paramDataHolder.close();
        for (DataHolder localDataHolder = null; ; localDataHolder = paramDataHolder)
        {
          e.this.a(new e.f(e.this, this.QI, localStatus, localDataHolder, paramString));
          return;
        }
      }
    }
  }

  final class f extends eh<d>.d<a.c<People.LoadPeopleResult>>
    implements People.LoadPeopleResult
  {
    private final String Dh;
    private PersonBuffer Ri;
    private final Status vl;

    public f(Status paramDataHolder, DataHolder paramString, String arg4)
    {
      super(paramDataHolder, localDataHolder);
      this.vl = paramString;
      Object localObject;
      this.Dh = localObject;
    }

    protected void a(a.c<People.LoadPeopleResult> paramc, DataHolder paramDataHolder)
    {
      if (paramDataHolder != null);
      for (PersonBuffer localPersonBuffer = new PersonBuffer(paramDataHolder); ; localPersonBuffer = null)
      {
        this.Ri = localPersonBuffer;
        paramc.b(this);
        return;
      }
    }

    public String getNextPageToken()
    {
      return this.Dh;
    }

    public PersonBuffer getPersonBuffer()
    {
      return this.Ri;
    }

    public Status getStatus()
    {
      return this.vl;
    }

    public void release()
    {
      if (this.Ri != null)
        this.Ri.close();
    }
  }

  final class g extends a
  {
    private final a.c<Status> QI;

    public g()
    {
      Object localObject;
      this.QI = localObject;
    }

    public void d(int paramInt, Bundle paramBundle)
    {
      if (paramBundle != null);
      for (PendingIntent localPendingIntent = (PendingIntent)paramBundle.getParcelable("pendingIntent"); ; localPendingIntent = null)
      {
        Status localStatus = new Status(paramInt, null, localPendingIntent);
        e.this.a(new e.h(e.this, this.QI, localStatus));
        return;
      }
    }
  }

  final class h extends eh<d>.b<a.c<Status>>
  {
    private final Status vl;

    public h(Status arg2)
    {
      super(localObject1);
      Object localObject2;
      this.vl = localObject2;
    }

    protected void c(a.c<Status> paramc)
    {
      e.this.disconnect();
      if (paramc != null)
        paramc.b(this.vl);
    }

    protected void cP()
    {
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.plus.internal.e
 * JD-Core Version:    0.6.0
 */