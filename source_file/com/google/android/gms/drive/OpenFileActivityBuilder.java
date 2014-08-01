package com.google.android.gms.drive;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.internal.OpenFileIntentSenderRequest;
import com.google.android.gms.drive.internal.n;
import com.google.android.gms.drive.internal.u;
import com.google.android.gms.internal.er;

public class OpenFileActivityBuilder
{
  public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
  private String CX;
  private DriveId CY;
  private String[] Dk;

  public IntentSender build(GoogleApiClient paramGoogleApiClient)
  {
    er.b(this.Dk, "setMimeType(String[]) must be called on this builder before calling build()");
    er.a(paramGoogleApiClient.isConnected(), "Client must be connected");
    u localu = ((n)paramGoogleApiClient.a(Drive.va)).eT();
    try
    {
      IntentSender localIntentSender = localu.a(new OpenFileIntentSenderRequest(this.CX, this.Dk, this.CY));
      return localIntentSender;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeException("Unable to connect Drive Play Service", localRemoteException);
  }

  public OpenFileActivityBuilder setActivityStartFolder(DriveId paramDriveId)
  {
    this.CY = ((DriveId)er.f(paramDriveId));
    return this;
  }

  public OpenFileActivityBuilder setActivityTitle(String paramString)
  {
    this.CX = ((String)er.f(paramString));
    return this;
  }

  public OpenFileActivityBuilder setMimeType(String[] paramArrayOfString)
  {
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0));
    for (boolean bool = true; ; bool = false)
    {
      er.b(bool, "mimeTypes may not be null and must contain at least one value");
      this.Dk = paramArrayOfString;
      return this;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.OpenFileActivityBuilder
 * JD-Core Version:    0.6.0
 */