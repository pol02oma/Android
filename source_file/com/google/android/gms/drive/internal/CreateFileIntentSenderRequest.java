package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class CreateFileIntentSenderRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<CreateFileIntentSenderRequest> CREATOR = new g();
  final int CQ;
  final String CX;
  final DriveId CY;
  final MetadataBundle Ds;
  final int wj;

  CreateFileIntentSenderRequest(int paramInt1, MetadataBundle paramMetadataBundle, int paramInt2, String paramString, DriveId paramDriveId)
  {
    this.wj = paramInt1;
    this.Ds = paramMetadataBundle;
    this.CQ = paramInt2;
    this.CX = paramString;
    this.CY = paramDriveId;
  }

  public CreateFileIntentSenderRequest(MetadataBundle paramMetadataBundle, int paramInt, String paramString, DriveId paramDriveId)
  {
    this(1, paramMetadataBundle, paramInt, paramString, paramDriveId);
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    g.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.CreateFileIntentSenderRequest
 * JD-Core Version:    0.6.0
 */