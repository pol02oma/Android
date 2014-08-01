package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;
import com.google.android.gms.drive.DriveFolder.DriveFolderResult;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.Query.Builder;
import com.google.android.gms.drive.query.SearchableField;

public class q extends r
  implements DriveFolder
{
  public q(DriveId paramDriveId)
  {
    super(paramDriveId);
  }

  public PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, Contents paramContents)
  {
    if (paramMetadataChangeSet == null)
      throw new IllegalArgumentException("MetatadataChangeSet must be provided.");
    if (paramContents == null)
      throw new IllegalArgumentException("Contents must be provided.");
    if ("application/vnd.google-apps.folder".equals(paramMetadataChangeSet.getMimeType()))
      throw new IllegalArgumentException("May not create folders (mimetype: application/vnd.google-apps.folder) using this method. Use DriveFolder.createFolder() instead.");
    return paramGoogleApiClient.b(new m(paramContents, paramMetadataChangeSet)
    {
      protected void a(n paramn)
        throws RemoteException
      {
        this.Dw.close();
        paramn.eT().a(new CreateFileRequest(q.this.getDriveId(), this.DK.eS(), this.Dw), new q.a(this));
      }

      public DriveFolder.DriveFileResult p(Status paramStatus)
      {
        return new q.d(paramStatus, null);
      }
    });
  }

  public PendingResult<DriveFolder.DriveFolderResult> createFolder(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet)
  {
    if (paramMetadataChangeSet == null)
      throw new IllegalArgumentException("MetatadataChangeSet must be provided.");
    if ((paramMetadataChangeSet.getMimeType() != null) && (!paramMetadataChangeSet.getMimeType().equals("application/vnd.google-apps.folder")))
      throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
    return paramGoogleApiClient.b(new c(paramMetadataChangeSet)
    {
      protected void a(n paramn)
        throws RemoteException
      {
        paramn.eT().a(new CreateFolderRequest(q.this.getDriveId(), this.DK.eS()), new q.b(this));
      }
    });
  }

  public PendingResult<DriveApi.MetadataBufferResult> listChildren(GoogleApiClient paramGoogleApiClient)
  {
    return queryChildren(paramGoogleApiClient, null);
  }

  public PendingResult<DriveApi.MetadataBufferResult> queryChildren(GoogleApiClient paramGoogleApiClient, Query paramQuery)
  {
    Query.Builder localBuilder = new Query.Builder().addFilter(Filters.in(SearchableField.PARENTS, getDriveId()));
    if (paramQuery != null)
    {
      if (paramQuery.getFilter() != null)
        localBuilder.addFilter(paramQuery.getFilter());
      localBuilder.setPageToken(paramQuery.getPageToken());
    }
    return new l().query(paramGoogleApiClient, localBuilder.build());
  }

  private static class a extends c
  {
    private final a.c<DriveFolder.DriveFileResult> vj;

    public a(a.c<DriveFolder.DriveFileResult> paramc)
    {
      this.vj = paramc;
    }

    public void a(OnDriveIdResponse paramOnDriveIdResponse)
      throws RemoteException
    {
      this.vj.b(new q.d(Status.zQ, new o(paramOnDriveIdResponse.getDriveId())));
    }

    public void l(Status paramStatus)
      throws RemoteException
    {
      this.vj.b(new q.d(paramStatus, null));
    }
  }

  private static class b extends c
  {
    private final a.c<DriveFolder.DriveFolderResult> vj;

    public b(a.c<DriveFolder.DriveFolderResult> paramc)
    {
      this.vj = paramc;
    }

    public void a(OnDriveIdResponse paramOnDriveIdResponse)
      throws RemoteException
    {
      this.vj.b(new q.e(Status.zQ, new q(paramOnDriveIdResponse.getDriveId())));
    }

    public void l(Status paramStatus)
      throws RemoteException
    {
      this.vj.b(new q.e(paramStatus, null));
    }
  }

  private abstract class c extends m<DriveFolder.DriveFolderResult>
  {
    private c()
    {
    }

    public DriveFolder.DriveFolderResult q(Status paramStatus)
    {
      return new q.e(paramStatus, null);
    }
  }

  private static class d
    implements DriveFolder.DriveFileResult
  {
    private final DriveFile DN;
    private final Status vl;

    public d(Status paramStatus, DriveFile paramDriveFile)
    {
      this.vl = paramStatus;
      this.DN = paramDriveFile;
    }

    public DriveFile getDriveFile()
    {
      return this.DN;
    }

    public Status getStatus()
    {
      return this.vl;
    }
  }

  private static class e
    implements DriveFolder.DriveFolderResult
  {
    private final DriveFolder DO;
    private final Status vl;

    public e(Status paramStatus, DriveFolder paramDriveFolder)
    {
      this.vl = paramStatus;
      this.DO = paramDriveFolder;
    }

    public DriveFolder getDriveFolder()
    {
      return this.DO;
    }

    public Status getStatus()
    {
      return this.vl;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.q
 * JD-Core Version:    0.6.0
 */