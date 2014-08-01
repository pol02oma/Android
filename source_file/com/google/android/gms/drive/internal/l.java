package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveApi.ContentsResult;
import com.google.android.gms.drive.DriveApi.DriveIdResult;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.query.Query;

public class l
  implements DriveApi
{
  public PendingResult<Status> discardContents(GoogleApiClient paramGoogleApiClient, Contents paramContents)
  {
    return paramGoogleApiClient.b(new j(paramContents)
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new CloseContentsRequest(this.Dw, false), new ak(this));
      }
    });
  }

  public PendingResult<DriveApi.DriveIdResult> fetchDriveId(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.a(new d(paramString)
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new GetMetadataRequest(DriveId.aq(this.Dx)), new l.b(this));
      }
    });
  }

  public DriveFolder getAppFolder(GoogleApiClient paramGoogleApiClient)
  {
    if (!paramGoogleApiClient.isConnected())
      throw new IllegalStateException("Client must be connected");
    DriveId localDriveId = ((n)paramGoogleApiClient.a(Drive.va)).eV();
    if (localDriveId != null)
      return new q(localDriveId);
    return null;
  }

  public DriveFile getFile(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId)
  {
    if (paramDriveId == null)
      throw new IllegalArgumentException("Id must be provided.");
    if (!paramGoogleApiClient.isConnected())
      throw new IllegalStateException("Client must be connected");
    return new o(paramDriveId);
  }

  public DriveFolder getFolder(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId)
  {
    if (paramDriveId == null)
      throw new IllegalArgumentException("Id must be provided.");
    if (!paramGoogleApiClient.isConnected())
      throw new IllegalStateException("Client must be connected");
    return new q(paramDriveId);
  }

  public DriveFolder getRootFolder(GoogleApiClient paramGoogleApiClient)
  {
    if (!paramGoogleApiClient.isConnected())
      throw new IllegalStateException("Client must be connected");
    return new q(((n)paramGoogleApiClient.a(Drive.va)).eU());
  }

  public PendingResult<DriveApi.ContentsResult> newContents(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.a(new g()
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new CreateContentsRequest(), new l.f(this));
      }
    });
  }

  public CreateFileActivityBuilder newCreateFileActivityBuilder()
  {
    return new CreateFileActivityBuilder();
  }

  public OpenFileActivityBuilder newOpenFileActivityBuilder()
  {
    return new OpenFileActivityBuilder();
  }

  public PendingResult<DriveApi.MetadataBufferResult> query(GoogleApiClient paramGoogleApiClient, Query paramQuery)
  {
    if (paramQuery == null)
      throw new IllegalArgumentException("Query must be provided.");
    return paramGoogleApiClient.a(new i(paramQuery)
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new QueryRequest(this.Du), new l.h(this));
      }
    });
  }

  public PendingResult<Status> requestSync(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.b(new l()
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new ak(this));
      }
    });
  }

  static class a
    implements DriveApi.ContentsResult
  {
    private final Contents CW;
    private final Status vl;

    public a(Status paramStatus, Contents paramContents)
    {
      this.vl = paramStatus;
      this.CW = paramContents;
    }

    public Contents getContents()
    {
      return this.CW;
    }

    public Status getStatus()
    {
      return this.vl;
    }
  }

  private static class b extends c
  {
    private final a.c<DriveApi.DriveIdResult> vj;

    public b(a.c<DriveApi.DriveIdResult> paramc)
    {
      this.vj = paramc;
    }

    public void a(OnMetadataResponse paramOnMetadataResponse)
      throws RemoteException
    {
      this.vj.b(new l.c(Status.zQ, new j(paramOnMetadataResponse.fe()).getDriveId()));
    }

    public void l(Status paramStatus)
      throws RemoteException
    {
      this.vj.b(new l.c(paramStatus, null));
    }
  }

  static class c
    implements DriveApi.DriveIdResult
  {
    private final DriveId CS;
    private final Status vl;

    public c(Status paramStatus, DriveId paramDriveId)
    {
      this.vl = paramStatus;
      this.CS = paramDriveId;
    }

    public DriveId getDriveId()
    {
      return this.CS;
    }

    public Status getStatus()
    {
      return this.vl;
    }
  }

  abstract class d extends m<DriveApi.DriveIdResult>
  {
    d()
    {
    }

    public DriveApi.DriveIdResult m(Status paramStatus)
    {
      return new l.c(paramStatus, null);
    }
  }

  static class e
    implements DriveApi.MetadataBufferResult
  {
    private final MetadataBuffer Dy;
    private final Status vl;

    public e(Status paramStatus, MetadataBuffer paramMetadataBuffer)
    {
      this.vl = paramStatus;
      this.Dy = paramMetadataBuffer;
    }

    public MetadataBuffer getMetadataBuffer()
    {
      return this.Dy;
    }

    public Status getStatus()
    {
      return this.vl;
    }
  }

  private static class f extends c
  {
    private final a.c<DriveApi.ContentsResult> vj;

    public f(a.c<DriveApi.ContentsResult> paramc)
    {
      this.vj = paramc;
    }

    public void a(OnContentsResponse paramOnContentsResponse)
      throws RemoteException
    {
      this.vj.b(new l.a(Status.zQ, paramOnContentsResponse.eX()));
    }

    public void l(Status paramStatus)
      throws RemoteException
    {
      this.vj.b(new l.a(paramStatus, null));
    }
  }

  abstract class g extends m<DriveApi.ContentsResult>
  {
    g()
    {
    }

    public DriveApi.ContentsResult n(Status paramStatus)
    {
      return new l.a(paramStatus, null);
    }
  }

  static class h extends c
  {
    private final a.c<DriveApi.MetadataBufferResult> vj;

    public h(a.c<DriveApi.MetadataBufferResult> paramc)
    {
      this.vj = paramc;
    }

    public void a(OnListEntriesResponse paramOnListEntriesResponse)
      throws RemoteException
    {
      MetadataBuffer localMetadataBuffer = new MetadataBuffer(paramOnListEntriesResponse.fc(), null);
      this.vj.b(new l.e(Status.zQ, localMetadataBuffer));
    }

    public void l(Status paramStatus)
      throws RemoteException
    {
      this.vj.b(new l.e(paramStatus, null));
    }
  }

  abstract class i extends m<DriveApi.MetadataBufferResult>
  {
    i()
    {
    }

    public DriveApi.MetadataBufferResult o(Status paramStatus)
    {
      return new l.e(paramStatus, null);
    }
  }

  static abstract class j extends m<Status>
  {
    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }

  static class k extends l.j
  {
    k(Status paramStatus)
    {
      a(paramStatus);
    }

    protected void a(n paramn)
    {
    }
  }

  abstract class l extends m<Status>
  {
    l()
    {
    }

    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.l
 * JD-Core Version:    0.6.0
 */