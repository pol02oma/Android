package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class GetMetadataRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<GetMetadataRequest> CREATOR = new t();
  final DriveId Do;
  final int wj;

  GetMetadataRequest(int paramInt, DriveId paramDriveId)
  {
    this.wj = paramInt;
    this.Do = paramDriveId;
  }

  public GetMetadataRequest(DriveId paramDriveId)
  {
    this(1, paramDriveId);
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    t.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.GetMetadataRequest
 * JD-Core Version:    0.6.0
 */