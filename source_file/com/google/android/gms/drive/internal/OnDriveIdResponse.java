package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class OnDriveIdResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnDriveIdResponse> CREATOR = new ab();
  DriveId Do;
  final int wj;

  OnDriveIdResponse(int paramInt, DriveId paramDriveId)
  {
    this.wj = paramInt;
    this.Do = paramDriveId;
  }

  public int describeContents()
  {
    return 0;
  }

  public DriveId getDriveId()
  {
    return this.Do;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ab.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.OnDriveIdResponse
 * JD-Core Version:    0.6.0
 */