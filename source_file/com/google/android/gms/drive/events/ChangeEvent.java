package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public final class ChangeEvent
  implements SafeParcelable, ResourceEvent
{
  public static final Parcelable.Creator<ChangeEvent> CREATOR = new a();
  final DriveId CS;
  final int Dl;
  final int wj;

  ChangeEvent(int paramInt1, DriveId paramDriveId, int paramInt2)
  {
    this.wj = paramInt1;
    this.CS = paramDriveId;
    this.Dl = paramInt2;
  }

  public int describeContents()
  {
    return 0;
  }

  public DriveId getDriveId()
  {
    return this.CS;
  }

  public int getType()
  {
    return 1;
  }

  public boolean hasContentChanged()
  {
    return (0x2 & this.Dl) != 0;
  }

  public boolean hasMetadataChanged()
  {
    return (0x1 & this.Dl) != 0;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.CS;
    arrayOfObject[1] = Integer.valueOf(this.Dl);
    return String.format("ChangeEvent [id=%s,changeFlags=%x]", arrayOfObject);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.events.ChangeEvent
 * JD-Core Version:    0.6.0
 */