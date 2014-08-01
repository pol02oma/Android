package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public final class ConflictEvent
  implements SafeParcelable, DriveEvent
{
  public static final Parcelable.Creator<ConflictEvent> CREATOR = new b();
  final DriveId CS;
  final int wj;

  ConflictEvent(int paramInt, DriveId paramDriveId)
  {
    this.wj = paramInt;
    this.CS = paramDriveId;
  }

  public int describeContents()
  {
    return 0;
  }

  public int getType()
  {
    return 1;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.CS;
    return String.format("ConflictEvent [id=%s]", arrayOfObject);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.events.ConflictEvent
 * JD-Core Version:    0.6.0
 */