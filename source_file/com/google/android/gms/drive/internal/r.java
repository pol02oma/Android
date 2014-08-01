package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.DriveResource.MetadataResult;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.DriveEvent.Listener;

public class r
  implements DriveResource
{
  protected final DriveId CS;

  protected r(DriveId paramDriveId)
  {
    this.CS = paramDriveId;
  }

  public PendingResult<Status> addChangeListener(GoogleApiClient paramGoogleApiClient, DriveEvent.Listener<ChangeEvent> paramListener)
  {
    return ((n)paramGoogleApiClient.a(Drive.va)).a(paramGoogleApiClient, this.CS, 1, paramListener);
  }

  public DriveId getDriveId()
  {
    return this.CS;
  }

  public PendingResult<DriveResource.MetadataResult> getMetadata(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.a(new a()
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new GetMetadataRequest(r.this.CS), new r.d(this));
      }
    });
  }

  public PendingResult<DriveApi.MetadataBufferResult> listParents(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.a(new c()
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new ListParentsRequest(r.this.CS), new r.b(this));
      }
    });
  }

  public PendingResult<Status> removeChangeListener(GoogleApiClient paramGoogleApiClient, DriveEvent.Listener<ChangeEvent> paramListener)
  {
    return ((n)paramGoogleApiClient.a(Drive.va)).b(paramGoogleApiClient, this.CS, 1, paramListener);
  }

  public PendingResult<DriveResource.MetadataResult> updateMetadata(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet)
  {
    if (paramMetadataChangeSet == null)
      throw new IllegalArgumentException("ChangeSet must be provided.");
    return paramGoogleApiClient.b(new f(paramMetadataChangeSet)
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new UpdateMetadataRequest(r.this.CS, this.DK.eS()), new r.d(this));
      }
    });
  }

  private abstract class a extends m<DriveResource.MetadataResult>
  {
    private a()
    {
    }

    public DriveResource.MetadataResult r(Status paramStatus)
    {
      return new r.e(paramStatus, null);
    }
  }

  private static class b extends c
  {
    private final a.c<DriveApi.MetadataBufferResult> vj;

    public b(a.c<DriveApi.MetadataBufferResult> paramc)
    {
      this.vj = paramc;
    }

    public void a(OnListParentsResponse paramOnListParentsResponse)
      throws RemoteException
    {
      MetadataBuffer localMetadataBuffer = new MetadataBuffer(paramOnListParentsResponse.fd(), null);
      this.vj.b(new l.e(Status.zQ, localMetadataBuffer));
    }

    public void l(Status paramStatus)
      throws RemoteException
    {
      this.vj.b(new l.e(paramStatus, null));
    }
  }

  private abstract class c extends m<DriveApi.MetadataBufferResult>
  {
    private c()
    {
    }

    public DriveApi.MetadataBufferResult o(Status paramStatus)
    {
      return new l.e(paramStatus, null);
    }
  }

  private static class d extends c
  {
    private final a.c<DriveResource.MetadataResult> vj;

    public d(a.c<DriveResource.MetadataResult> paramc)
    {
      this.vj = paramc;
    }

    public void a(OnMetadataResponse paramOnMetadataResponse)
      throws RemoteException
    {
      this.vj.b(new r.e(Status.zQ, new j(paramOnMetadataResponse.fe())));
    }

    public void l(Status paramStatus)
      throws RemoteException
    {
      this.vj.b(new r.e(paramStatus, null));
    }
  }

  private static class e
    implements DriveResource.MetadataResult
  {
    private final Metadata DQ;
    private final Status vl;

    public e(Status paramStatus, Metadata paramMetadata)
    {
      this.vl = paramStatus;
      this.DQ = paramMetadata;
    }

    public Metadata getMetadata()
    {
      return this.DQ;
    }

    public Status getStatus()
    {
      return this.vl;
    }
  }

  private abstract class f extends m<DriveResource.MetadataResult>
  {
    private f()
    {
    }

    public DriveResource.MetadataResult r(Status paramStatus)
    {
      return new r.e(paramStatus, null);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.r
 * JD-Core Version:    0.6.0
 */