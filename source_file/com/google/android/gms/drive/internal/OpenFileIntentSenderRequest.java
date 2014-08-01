package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class OpenFileIntentSenderRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<OpenFileIntentSenderRequest> CREATOR = new ah();
  final String CX;
  final DriveId CY;
  final String[] Dk;
  final int wj;

  OpenFileIntentSenderRequest(int paramInt, String paramString, String[] paramArrayOfString, DriveId paramDriveId)
  {
    this.wj = paramInt;
    this.CX = paramString;
    this.Dk = paramArrayOfString;
    this.CY = paramDriveId;
  }

  public OpenFileIntentSenderRequest(String paramString, String[] paramArrayOfString, DriveId paramDriveId)
  {
    this(1, paramString, paramArrayOfString, paramDriveId);
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ah.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.OpenFileIntentSenderRequest
 * JD-Core Version:    0.6.0
 */